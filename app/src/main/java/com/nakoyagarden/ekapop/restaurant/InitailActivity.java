package com.nakoyagarden.ekapop.restaurant;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;


import com.epson.epos2.Epos2Exception;
import com.epson.epos2.printer.Printer;
import com.epson.epos2.printer.PrinterStatusInfo;
import com.epson.epos2.printer.ReceiveListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class InitailActivity extends AppCompatActivity implements ReceiveListener {
    public RestaurantControl rs;
    TextView lbIaHost, lbIaPrint, lbIaPosID, lbIaTaxID, lbIaWebDirectory, lbIaPortID;
    EditText txtIaHost, txtIaPrint, txtIaPosID, txtIaTaxID, txtIaWebDirectory, txtIaPortID;
    Button btnIaSave, btnIaPrint, btnIaTest,btnFoodsV;
    Button btnMFt, btnMTable,btnMArea, btnMRes, btnMBillVoid;
    Spinner cboIaPrinter;
    InputStream is;
    String ab="";

    private int INT_SELECT_PICTURE = 1;

    private int GETSTATUS_TIME = 1000;		//1sec

    public String selectedImagePath = "";

    static String[] ethDeviceList = null;

    static ListView listDevicesView ;
    static ArrayAdapter<String> listAdapter;

    static int lastDeviceSelected = -1;
    static int deviceSelected = -1;

    private String lock="lockAccess";

    static Handler hGetStatus = new Handler();


    private Printer mPrinter = null;
    private Context mContext = null;
    private ArrayList<String> sCboPrinter = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initail);

        mContext = this;
//        Intent intent = getIntent();
        rs = (RestaurantControl) getIntent().getSerializableExtra("RestaurantControl");
        lbIaHost = (TextView) findViewById(R.id.lbIaHost);
        lbIaPrint = (TextView) findViewById(R.id.lbIaPrint);
        txtIaHost = (EditText) findViewById(R.id.txtIaHost);
        txtIaPrint = (EditText) findViewById(R.id.txtIaPrint);
        lbIaPosID = (TextView) findViewById(R.id.lbIaPosID);
        txtIaPosID = (EditText) findViewById(R.id.txtIaPosID);
        lbIaTaxID = (TextView) findViewById(R.id.lbIaTaxID);
        txtIaTaxID = (EditText) findViewById(R.id.txtIaTaxID);
        lbIaWebDirectory = (TextView) findViewById(R.id.lbIaWebDirectory);
        txtIaWebDirectory = (EditText) findViewById(R.id.txtIaWebDirectory);
        lbIaPortID = (TextView) findViewById(R.id.lbIaPortID);
        txtIaPortID = (EditText) findViewById(R.id.txtIaPortID);
        cboIaPrinter = (Spinner) findViewById(R.id.cboIaPrinter);

        btnIaSave = (Button) findViewById(R.id.btnIaSave);
        btnIaPrint = (Button) findViewById(R.id.btnIaPrint);
        btnIaTest = (Button) findViewById(R.id.btnIaTest);
        btnFoodsV = (Button)findViewById(R.id.btnFoodsView);
        btnMFt = (Button)findViewById(R.id.btnMFoodsType);
        btnMTable = (Button)findViewById(R.id.btnMTable);
        btnMArea = (Button)findViewById(R.id.btnMArea);
        btnMRes = (Button)findViewById(R.id.btnMRes);
        btnMBillVoid = (Button)findViewById(R.id.btnMBillVoid);

        btnIaPrint.setText("Test Print");
        lbIaHost.setText("Host IP");
        lbIaPrint.setText("Printer IP");
        lbIaPosID.setText("POS ID");
        lbIaWebDirectory.setText("Web Directory");
        lbIaTaxID.setText("Tax ID");
        lbIaPortID.setText("Port Number");

        btnIaSave.setText(R.string.save);
        btnIaTest.setText("Test");
        btnMBillVoid.setText("ยกเลิกรับชำระ");
        btnMFt.setText(getResources().getString(R.string.add)+getResources().getString(R.string.type)+getResources().getString(R.string.foods));
        btnMTable.setText(getResources().getString(R.string.add)+getResources().getString(R.string.table));
        btnMArea.setText(getResources().getString(R.string.add)+getResources().getString(R.string.area));
        btnMRes.setText(getResources().getString(R.string.add)+getResources().getString(R.string.restaurant));
        btnIaSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                saveText();
            }
        });
        btnIaPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                onPrintText();
//                new retrieveArea().execute();
                saveText();

            }
        });
        btnIaTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runPrintReceiptSequenceEpson();
            }
        });
        btnFoodsV.setText(R.string.foodsadd);
        btnFoodsV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(view.getContext(), FoodsViewActivity.class).putExtra("RestaurantControl",rs), 0);
            }
        });
        btnMArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(view.getContext(), AreaViewActivity.class).putExtra("RestaurantControl",rs), 0);
            }
        });
        btnMTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(view.getContext(), TableViewActivity.class).putExtra("RestaurantControl",rs), 0);
            }
        });
        btnMRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(view.getContext(), ResViewActivity.class).putExtra("RestaurantControl",rs), 0);
            }
        });
        btnMFt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(view.getContext(), FoodsTypeViewActivity.class).putExtra("RestaurantControl",rs), 0);
            }
        });
        btnMBillVoid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(view.getContext(), BillVoidActivity.class).putExtra("RestaurantControl",rs), 0);
            }
        });
        sCboPrinter.add("Epson T82");
        sCboPrinter.add("Custom Kute II");
        ArrayAdapter<String> adaArea = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,sCboPrinter);
        cboIaPrinter.setAdapter(adaArea);
        cboIaPrinter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //txtIaPrint.setText(cboIaPrinter.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        getText();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
//        InitEverything(savedInstanceState);
    }
    private void saveText(){
        FileOutputStream outputStream;
        String string = "host="+txtIaHost.getText().toString().trim()+";\n"
            +"printer="+txtIaPrint.getText().toString().trim()+";\n"
            +"PosID="+txtIaPosID.getText().toString().trim()+";\n"
            +"TaxID="+txtIaTaxID.getText().toString().trim()+";\n"
            +"PortNumber="+txtIaPortID.getText().toString().trim()+";\n"
            +"WebDirectory="+txtIaWebDirectory.getText().toString().trim()+";\n";
        try {
            File file =getFileStreamPath("initial.cnf");
            outputStream = openFileOutput("initial.cnf", Context.MODE_PRIVATE);
//            outputStream = openFileOutput(file.getPath(), Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();
            rs.pageLoad=true;
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void getText(){
        try {
            File file =getFileStreamPath("initial.cnf");
            final int READ_BLOCK_SIZE = 100;
            FileInputStream fileIn=openFileInput("initial.cnf");
//            FileInputStream fileIn=openFileInput(file.getPath());
            InputStreamReader InputRead= new InputStreamReader(fileIn);

            char[] inputBuffer= new char[READ_BLOCK_SIZE];
            String s="";
            int charRead;

            while ((charRead=InputRead.read(inputBuffer))>0) {
                // char to string conversion
                String readstring=String.copyValueOf(inputBuffer,0,charRead);
                s +=readstring;
            }
            InputRead.close();
            String[] p = s.split(";");
            if(p.length>0){
                txtIaHost.setText(p[0].replace("host=",""));
                txtIaPrint.setText(p[1].replace("printer=","").replace("\n",""));
                txtIaPosID.setText(p[2].replace("PosID=","").replace("\n",""));
                txtIaTaxID.setText(p[3].replace("TaxID=","").replace("\n",""));
                txtIaPortID.setText(p[4].replace("PortNumber=","").replace("\n",""));
                txtIaWebDirectory.setText(p[5].replace("WebDirectory=","").replace("\n",""));

                rs.hostIP = txtIaHost.getText().toString();
            }
            fileIn.close();
            rs.refresh();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    private void doPrint() {
//        // Get a PrintManager instance
////        PrintManager printManager = (PrintManager) this.getSystemService(Context.PRINT_SERVICE);
//        it.custom.printer.api.android.CustomPrinter prnDevice1 = new it.custom.printer.api.android.CustomAndroidAPI().getPrinterDriver("10.0.1.199");
//        // Set job name, which will be displayed in the print queue
//        String jobName = this.getString(R.string.app_name) + " Document";
//
//        // Start a print job, passing in a PrintDocumentAdapter implementation
//        // to handle the generation of a print document
////        printManager.print(jobName, new MyPrintDocumentAdapter(this),                null); //
//    }

    void showAlertMsg(String title,String msg)
    {
        AlertDialog.Builder dialogBuilder;
        dialogBuilder = new AlertDialog.Builder(this);

        dialogBuilder.setNeutralButton( "OK", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialogBuilder.setTitle(title);
        dialogBuilder.setMessage(msg);
        dialogBuilder.show();

    }



    @Override
    public void onPtrReceive(final Printer printerObj, final int code, final PrinterStatusInfo status, final String printJobId) {
        runOnUiThread(new Runnable() {
            @Override
            public synchronized void run() {
                ShowMsgEpson.showResult(code, makeErrorMessageEpson(status), mContext);

                dispPrinterWarnings(status);

                updateButtonStateEpson(true);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        disconnectPrinterEpson();
                    }
                }).start();
            }
        });
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
//        EditText edtWarnings = (EditText)findViewById(R.id.edtWarnings);
        String warningsMsg = "";

        if (status == null) {
            return;
        }

        if (status.getPaper() == Printer.PAPER_NEAR_END) {
            warningsMsg += getString(R.string.handlingmsg_warn_receipt_near_end);
        }

        if (status.getBatteryLevel() == Printer.BATTERY_LEVEL_1) {
            warningsMsg += getString(R.string.handlingmsg_warn_battery_near_end);
        }

//        edtWarnings.setText("Warning");
    }
    private String makeErrorMessageEpson(PrinterStatusInfo status) {
        String msg = "";

        if (status.getOnline() == Printer.FALSE) {
            msg += getString(R.string.handlingmsg_err_offline);
        }
        if (status.getConnection() == Printer.FALSE) {
            msg += getString(R.string.handlingmsg_err_no_response);
        }
        if (status.getCoverOpen() == Printer.TRUE) {
            msg += getString(R.string.handlingmsg_err_cover_open);
        }
        if (status.getPaper() == Printer.PAPER_EMPTY) {
            msg += getString(R.string.handlingmsg_err_receipt_end);
        }
        if (status.getPaperFeed() == Printer.TRUE || status.getPanelSwitch() == Printer.SWITCH_ON) {
            msg += getString(R.string.handlingmsg_err_paper_feed);
        }
        if (status.getErrorStatus() == Printer.MECHANICAL_ERR || status.getErrorStatus() == Printer.AUTOCUTTER_ERR) {
            msg += getString(R.string.handlingmsg_err_autocutter);
            msg += getString(R.string.handlingmsg_err_need_recover);
        }
        if (status.getErrorStatus() == Printer.UNRECOVER_ERR) {
            msg += getString(R.string.handlingmsg_err_unrecover);
        }
        if (status.getErrorStatus() == Printer.AUTORECOVER_ERR) {
            if (status.getAutoRecoverError() == Printer.HEAD_OVERHEAT) {
                msg += getString(R.string.handlingmsg_err_overheat);
                msg += getString(R.string.handlingmsg_err_head);
            }
            if (status.getAutoRecoverError() == Printer.MOTOR_OVERHEAT) {
                msg += getString(R.string.handlingmsg_err_overheat);
                msg += getString(R.string.handlingmsg_err_motor);
            }
            if (status.getAutoRecoverError() == Printer.BATTERY_OVERHEAT) {
                msg += getString(R.string.handlingmsg_err_overheat);
                msg += getString(R.string.handlingmsg_err_battery);
            }
            if (status.getAutoRecoverError() == Printer.WRONG_PAPER) {
                msg += getString(R.string.handlingmsg_err_wrong_paper);
            }
        }
        if (status.getBatteryLevel() == Printer.BATTERY_LEVEL_0) {
            msg += getString(R.string.handlingmsg_err_battery_real_end);
        }

        return msg;
    }

    /**
     * Call this Method for Print Epson
     * @return
     */
    private boolean runPrintReceiptSequenceEpson() {
        if (!initializeObjectEpson()) {
            return false;
        }

        if (!createReceiptDataEpson()) {
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
    private boolean createReceiptDataEpson() {
        String method = "";
        Bitmap logoData = BitmapFactory.decodeResource(getResources(), R.drawable.store);
        StringBuilder textData = new StringBuilder();
        final int barcodeWidth = 2;
        final int barcodeHeight = 100;

        if (mPrinter == null) {
            return false;
        }

        try {
            method = "addTextAlign";
            mPrinter.addTextAlign(Printer.ALIGN_CENTER);

            method = "addImage";
            mPrinter.addImage(logoData, 0, 0,
                    logoData.getWidth(),
                    logoData.getHeight(),
                    Printer.COLOR_1,
                    Printer.MODE_MONO,
                    Printer.HALFTONE_DITHER,
                    Printer.PARAM_DEFAULT,
                    Printer.COMPRESS_AUTO);

            method = "addFeedLine";
            mPrinter.addFeedLine(1);
            textData.append("THE STORE 123 (555) 555 – 5555\n");
            textData.append("STORE DIRECTOR – John Smith\n");
            textData.append("\n");
            textData.append("7/01/07 16:58 6153 05 0191 134\n");
            textData.append("ST# 21 OP# 001 TE# 01 TR# 747\n");
            textData.append("------------------------------\n");
            method = "addText";
            mPrinter.addText(textData.toString());
            textData.delete(0, textData.length());

            textData.append("400 OHEIDA 3PK SPRINGF  9.99 R\n");
            textData.append("410 3 CUP BLK TEAPOT    9.99 R\n");
            textData.append("445 EMERIL GRIDDLE/PAN 17.99 R\n");
            textData.append("438 CANDYMAKER ASSORT   4.99 R\n");
            textData.append("474 TRIPOD              8.99 R\n");
            textData.append("433 BLK LOGO PRNTED ZO  7.99 R\n");
            textData.append("458 AQUA MICROTERRY SC  6.99 R\n");
            textData.append("493 30L BLK FF DRESS   16.99 R\n");
            textData.append("407 LEVITATING DESKTOP  7.99 R\n");
            textData.append("441 **Blue Overprint P  2.99 R\n");
            textData.append("476 REPOSE 4PCPM CHOC   5.49 R\n");
            textData.append("461 WESTGATE BLACK 25  59.99 R\n");
            textData.append("------------------------------\n");
            method = "addText";
            mPrinter.addText(textData.toString());
            textData.delete(0, textData.length());

            textData.append("SUBTOTAL                160.38\n");
            textData.append("TAX                      14.43\n");
            method = "addText";
            mPrinter.addText(textData.toString());
            textData.delete(0, textData.length());

            method = "addTextSize";
            mPrinter.addTextSize(2, 2);
            method = "addText";
            mPrinter.addText("TOTAL    174.81\n");
            method = "addTextSize";
            mPrinter.addTextSize(1, 1);
            method = "addFeedLine";
            mPrinter.addFeedLine(1);

            textData.append("CASH                    200.00\n");
            textData.append("CHANGE                   25.19\n");
            textData.append("------------------------------\n");
            method = "addText";
            mPrinter.addText(textData.toString());
            textData.delete(0, textData.length());

            textData.append("Purchased item total number\n");
            textData.append("Sign Up and Save !\n");
            textData.append("With Preferred Saving Card\n");
            method = "addText";
            mPrinter.addText(textData.toString());
//            mPrinter.a;
            textData.delete(0, textData.length());
            method = "addFeedLine";
            mPrinter.addFeedLine(2);

            method = "addBarcode";
            mPrinter.addBarcode("01209457",
                    Printer.BARCODE_CODE39,
                    Printer.HRI_BELOW,
                    Printer.FONT_A,
                    barcodeWidth,
                    barcodeHeight);

            method = "addCut";
            mPrinter.addCut(Printer.CUT_FEED);
        }
        catch (Exception e) {
            ShowMsgEpson.showException(e, method, mContext);
            return false;
        }

        textData = null;

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
    private void disconnectPrinterEpson() {
        if (mPrinter == null) {
            return;
        }

        try {
            mPrinter.endTransaction();
        }
        catch (final Exception e) {
            runOnUiThread(new Runnable() {
                @Override
                public synchronized void run() {
                    ShowMsgEpson.showException(e, "endTransaction", mContext);
                }
            });
        }

        try {
            mPrinter.disconnect();
        }
        catch (final Exception e) {
            runOnUiThread(new Runnable() {
                @Override
                public synchronized void run() {
                    ShowMsgEpson.showException(e, "disconnect", mContext);
                }
            });
        }

        finalizeObjectEpson();
    }
    private void updateButtonStateEpson(boolean state) {
//        Button btnReceipt = (Button)findViewById(R.id.btnSampleReceipt);
//        Button btnCoupon = (Button)findViewById(R.id.btnSampleCoupon);
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
}
