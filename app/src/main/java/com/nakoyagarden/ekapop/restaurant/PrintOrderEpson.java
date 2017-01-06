package com.nakoyagarden.ekapop.restaurant;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.epson.epos2.Epos2Exception;
import com.epson.epos2.printer.Printer;
import com.epson.epos2.printer.PrinterStatusInfo;
import com.epson.epos2.printer.ReceiveListener;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ekapop on 11/14/2016.
 */

public class PrintOrderEpson extends Activity implements ReceiveListener {
    private Printer mPrinter = null;
    private Context mContext = null;
    //    private RestaurantControl rs;
    public PrintOrderEpson(Context c){
        mContext = c;
    }
    private boolean printDataEpson() {
        if (mPrinter == null) {
            return false;
        }

        if (!connectPrinterEpson()) {
            return false;
        }

        PrinterStatusInfo status = mPrinter.getStatus();

        dispPrinterWarnings(status);

        if (!isPrintableEpson(status)) {
            ShowMsgEpson.showMsg(makeErrorMessageEpson(status), mContext);
            try {
                mPrinter.disconnect();
            }
            catch (Exception ex) {
                // Do nothing
            }
            return false;
        }

        try {
            mPrinter.sendData(Printer.PARAM_DEFAULT);
        }
        catch (Exception e) {
            ShowMsgEpson.showException(e, "sendData", mContext);
            try {
                mPrinter.disconnect();
            }
            catch (Exception ex) {
                // Do nothing
            }
            return false;
        }

        return true;
    }
    private boolean connectPrinterEpson() {
        boolean isBeginTransaction = false;

        if (mPrinter == null) {
            return false;
        }

        try {
//            mPrinter.connect(mEditTarget.getText().toString(), Printer.PARAM_DEFAULT);
//            mPrinter.connect(getString(R.string.printerseries_t82), Printer.PARAM_DEFAULT);
            mPrinter.connect("TCP:10.0.1.198", Printer.PARAM_DEFAULT);
        }
        catch (Exception e) {
            ShowMsgEpson.showException(e, "connect", mContext);
            return false;
        }

        try {
            mPrinter.beginTransaction();
            isBeginTransaction = true;
        }
        catch (Exception e) {
            ShowMsgEpson.showException(e, "beginTransaction", mContext);
        }

        if (isBeginTransaction == false) {
            try {
                mPrinter.disconnect();
            }
            catch (Epos2Exception e) {
                // Do nothing
                return false;
            }
        }

        return true;
    }
    private void dispPrinterWarnings(PrinterStatusInfo status) {
//        EditText edtWarnings = (EditText)findViewById(R.genid.edtWarnings);
        String warningsMsg = "";

        if (status == null) {
            return;
        }

        if (status.getPaper() == Printer.PAPER_NEAR_END) {
            warningsMsg += Resources.getSystem().getString(R.string.handlingmsg_warn_receipt_near_end);
        }

        if (status.getBatteryLevel() == Printer.BATTERY_LEVEL_1) {
            warningsMsg += Resources.getSystem().getString(R.string.handlingmsg_warn_battery_near_end);
        }

//        edtWarnings.setText("Warning");
    }
    private String makeErrorMessageEpson(PrinterStatusInfo status) {
        String msg = "";

        if (status.getOnline() == Printer.FALSE) {
            msg += Resources.getSystem().getString(R.string.handlingmsg_err_offline);
        }
        if (status.getConnection() == Printer.FALSE) {
            msg += Resources.getSystem().getString(R.string.handlingmsg_err_no_response);
        }
        if (status.getCoverOpen() == Printer.TRUE) {
            msg += Resources.getSystem().getString(R.string.handlingmsg_err_cover_open);
        }
        if (status.getPaper() == Printer.PAPER_EMPTY) {
            msg += Resources.getSystem().getString(R.string.handlingmsg_err_receipt_end);
        }
        if (status.getPaperFeed() == Printer.TRUE || status.getPanelSwitch() == Printer.SWITCH_ON) {
            msg += Resources.getSystem().getString(R.string.handlingmsg_err_paper_feed);
        }
        if (status.getErrorStatus() == Printer.MECHANICAL_ERR || status.getErrorStatus() == Printer.AUTOCUTTER_ERR) {
            msg += Resources.getSystem().getString(R.string.handlingmsg_err_autocutter);
            msg += Resources.getSystem().getString(R.string.handlingmsg_err_need_recover);
        }
        if (status.getErrorStatus() == Printer.UNRECOVER_ERR) {
            msg += Resources.getSystem().getString(R.string.handlingmsg_err_unrecover);
        }
        if (status.getErrorStatus() == Printer.AUTORECOVER_ERR) {
            if (status.getAutoRecoverError() == Printer.HEAD_OVERHEAT) {
                msg += Resources.getSystem().getString(R.string.handlingmsg_err_overheat);
                msg += Resources.getSystem().getString(R.string.handlingmsg_err_head);
            }
            if (status.getAutoRecoverError() == Printer.MOTOR_OVERHEAT) {
                msg += Resources.getSystem().getString(R.string.handlingmsg_err_overheat);
                msg += Resources.getSystem().getString(R.string.handlingmsg_err_motor);
            }
            if (status.getAutoRecoverError() == Printer.BATTERY_OVERHEAT) {
                msg += Resources.getSystem().getString(R.string.handlingmsg_err_overheat);
                msg += Resources.getSystem().getString(R.string.handlingmsg_err_battery);
            }
            if (status.getAutoRecoverError() == Printer.WRONG_PAPER) {
                msg += Resources.getSystem().getString(R.string.handlingmsg_err_wrong_paper);
            }
        }
        if (status.getBatteryLevel() == Printer.BATTERY_LEVEL_0) {
            msg += Resources.getSystem().getString(R.string.handlingmsg_err_battery_real_end);
        }

        return msg;
    }

    /**
     * Call this Method for Print Epson
     * @return
     */
    public boolean runPrintReceiptSequenceEpson(String area, String table, String[] prn) {
//        mContext = c;
        if (!initializeObjectEpson()) {
            return false;
        }

        if (!createReceiptDataEpson(area, table, prn)) {
            finalizeObjectEpson();
            return false;
        }

        if (!printDataEpson()) {
            finalizeObjectEpson();
            return false;
        }

        return true;
    }
    private boolean initializeObjectEpson() {
        try {
//            mPrinter = new Printer(((SpnModelsItem) mSpnSeries.getSelectedItem()).getModelConstant(),  ((SpnModelsItem) mSpnLang.getSelectedItem()).getModelConstant(), mContext);
            mPrinter = new Printer(10,  0, mContext);
        }
        catch (Exception e) {
            ShowMsgEpson.showException(e, "Printer", mContext);
            return false;
        }

        mPrinter.setReceiveEventListener(this);

        return true;
    }
    private boolean createReceiptDataEpson(String area, String table, String[] prn) {
        String method = "";
        final int barcodeWidth = 2;
        final int barcodeHeight = 100;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        String currentDateandTime = sdf .format(new Date());
        if (mPrinter == null) {
            return false;
        }
        for(int i=0;i<prn.length;i++){
            try {
                String prn1[] = prn[i].split(";");
                mPrinter.addTextAlign(Printer.ALIGN_LEFT);
                mPrinter.addText(currentDateandTime+ " "+area+" "+table+"\n");
                mPrinter.addFeedLine(1);
                mPrinter.addText("--------------------------------------------\n");
                mPrinter.addTextSize(2, 2);
//                textData.append(prn[0]+"   "+prn[1]+"\n");
                mPrinter.addText(prn1[0]+"   "+prn1[1]+"\n");
                mPrinter.addTextSize(1, 1);
                mPrinter.addText(prn1[2]+"\n");

                mPrinter.addText("--------------------------------------------\n");
//            mPrinter.addFeedLine(1);
                mPrinter.addFeedLine(1);
                method = "addCut";
                mPrinter.addCut(Printer.CUT_FEED);
            }
            catch (Exception e) {
                ShowMsgEpson.showException(e, method, mContext);
                return false;
            }
        }


//        textData = null;

        return true;
    }
    private void finalizeObjectEpson() {
        if (mPrinter == null) {
            return;
        }

        mPrinter.clearCommandBuffer();

        mPrinter.setReceiveEventListener(null);

        mPrinter = null;
    }
    private String alignText(String a1, String a2){
        String a11=a1, p="";
        String aeiou="";
        int l1=0,l2=0;
        a11 = a11.replace("ี","");
        a11 = a11.replace("่","");
        a11 = a11.replace("ิ","");
        a11 = a11.replace("ิ","");
        a11 = a11.replace("ิ็","");
        l1 = a11.length();
        l2 = a2.length();

        for(int i=0;i<(30-(l1+l2));i++){
            p+=" ";
        }
        return a1+p+a2;
    }
    private void disconnectPrinterEpson() {
//        if (mPrinter == null) {
//            return;
//        }
//
//        try {
//            mPrinter.endTransaction();
//        }
//        catch (final Exception e) {
//            runOnUiThread(new Runnable() {
//                @Override
//                public synchronized void run() {
//                    ShowMsgEpson.showException(e, "endTransaction", mContext);
//                }
//            });
//        }
//
//        try {
//            mPrinter.disconnect();
//        }
//        catch (final Exception e) {
//            runOnUiThread(new Runnable() {
//                @Override
//                public synchronized void run() {
//                    ShowMsgEpson.showException(e, "disconnect", mContext);
//                }
//            });
//        }
//
//        finalizeObjectEpson();
    }
    private void updateButtonStateEpson(boolean state) {
//        Button btnReceipt = (Button)findViewById(R.genid.btnSampleReceipt);
//        Button btnCoupon = (Button)findViewById(R.genid.btnSampleCoupon);
//        btnReceipt.setEnabled(state);
//        btnCoupon.setEnabled(state);
    }
    private boolean isPrintableEpson(PrinterStatusInfo status) {
        if (status == null) {
            return false;
        }

        if (status.getConnection() == Printer.FALSE) {
            return false;
        }
        else if (status.getOnline() == Printer.FALSE) {
            return false;
        }
        else {
            ;//print available
        }

        return true;
    }

    @Override
    public void onPtrReceive(Printer printer, int i, PrinterStatusInfo printerStatusInfo, String s) {

    }
}
