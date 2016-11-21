-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 21, 2016 at 07:14 AM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 5.6.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `restaurant`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `getBillCode` ()  BEGIN

SELECT * FROM t_bill;

END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `b_area`
--

CREATE TABLE `b_area` (
  `area_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `area_code` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `area_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `active` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `remark` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `sort1` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `date_create` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `b_area`
--

INSERT INTO `b_area` (`area_id`, `area_code`, `area_name`, `active`, `remark`, `sort1`, `date_create`) VALUES
('a01f92e1-5926-11e6-bfd0-c03fd5b6f2e8', '1000', 'ในร้าน', '1', NULL, '100', NULL),
('a01fa080-5926-11e6-bfd0-c03fd5b6f2e8', '1001', 'ฟุตบาท', '1', 'ยยยยl', '101', NULL),
('c00d1782-5926-11e6-bfd0-c03fd5b6f2e8', '1002', 'ในสวน', '1', NULL, '102', NULL),
('6a0fde50-70b3-11e6-b472-c03fd5b6f2e8', '04', 'ในบ้าน', '1', '', '103', '2016-09-02 09:17:36');

-- --------------------------------------------------------

--
-- Table structure for table `b_foods`
--

CREATE TABLE `b_foods` (
  `foods_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `foods_code` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `foods_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `foods_price` decimal(17,2) DEFAULT NULL,
  `active` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `foods_type_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `remark` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `res_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `res_code` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `status_foods` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '0=default,1=foods,2=drink',
  `printer_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `date_create` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `date_modi` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `b_foods`
--

INSERT INTO `b_foods` (`foods_id`, `foods_code`, `foods_name`, `foods_price`, `active`, `foods_type_id`, `remark`, `res_id`, `res_code`, `status_foods`, `printer_name`, `date_create`, `date_modi`) VALUES
('cbfc636c-7593-11e6-96a5-c03fd5b6f2e8', '1104', 'คะน้าปลาเค็ม', '50.00', '1', '29b9f510-6e7e-11e6-a3fd-c03fd5b6f2e8', '', 'beefcdc0-5a1d-11e6-99a1-c03fd5b6f2e8', '10', '1', '', '2016-09-08 14:13:52', NULL),
('74c833d6-7590-11e6-96a5-c03fd5b6f2e8', '1103', 'คะน้าหมูกรอบ', '50.00', '1', '29b9f510-6e7e-11e6-a3fd-c03fd5b6f2e8', '', 'beefcdc0-5a1d-11e6-99a1-c03fd5b6f2e8', '10', '1', '', '2016-09-08 13:49:57', NULL),
('5debdec7-7590-11e6-96a5-c03fd5b6f2e8', '1006', 'ผัดซีอิวหมู', '45.00', '1', '29b9ecd5-6e7e-11e6-a3fd-c03fd5b6f2e8', '', 'beefcdc0-5a1d-11e6-99a1-c03fd5b6f2e8', '10', '1', '', '2016-09-08 13:49:19', NULL),
('9d5480ab-758e-11e6-96a5-c03fd5b6f2e8', '1102', 'แกงเขียวหวาน', '50.00', '1', '29b9f510-6e7e-11e6-a3fd-c03fd5b6f2e8', '', 'beefcdc0-5a1d-11e6-99a1-c03fd5b6f2e8', '10', '1', '', '2016-09-08 13:36:46', NULL),
('88225c62-758e-11e6-96a5-c03fd5b6f2e8', '1101', 'แกงส้มกุ้ง', '50.00', '1', '29b9f510-6e7e-11e6-a3fd-c03fd5b6f2e8', '', 'beefcdc0-5a1d-11e6-99a1-c03fd5b6f2e8', '10', '1', '', '2016-09-08 13:36:11', NULL),
('722a4860-758e-11e6-96a5-c03fd5b6f2e8', '1005', 'ข้าวกระเพราไก่+ไข่ดาว', '45.00', '1', '29b9ecd5-6e7e-11e6-a3fd-c03fd5b6f2e8', '', 'beefcdc0-5a1d-11e6-99a1-c03fd5b6f2e8', '10', '1', '', '2016-09-08 13:35:34', NULL),
('45588b53-758e-11e6-96a5-c03fd5b6f2e8', '1004', 'ข้าวผัดหมู', '44.00', '1', '29b9ecd5-6e7e-11e6-a3fd-c03fd5b6f2e8', '', 'beefcdc0-5a1d-11e6-99a1-c03fd5b6f2e8', '10', '1', '', '2016-09-08 13:34:19', NULL),
('296650f5-758e-11e6-96a5-c03fd5b6f2e8', '1003', 'ข้าวคะน้าหมูกรอบ', '45.00', '1', '29b9ecd5-6e7e-11e6-a3fd-c03fd5b6f2e8', '', 'beefcdc0-5a1d-11e6-99a1-c03fd5b6f2e8', '10', '1', '', '2016-09-08 13:33:32', NULL),
('16d42722-758e-11e6-96a5-c03fd5b6f2e8', '1002', 'ข้าวกระเพราเนื้อ+ไข่ดาว', '45.00', '1', '29b9ecd5-6e7e-11e6-a3fd-c03fd5b6f2e8', '', 'beefcdc0-5a1d-11e6-99a1-c03fd5b6f2e8', '10', '1', '', '2016-09-08 13:33:01', NULL),
('873daada-6f49-11e6-b472-c03fd5b6f2e8', '1001', 'ข้าวกระเพราหมู ไข่ดาว', '45.00', '1', '29b9ecd5-6e7e-11e6-a3fd-c03fd5b6f2e8', '', 'beefcdc0-5a1d-11e6-99a1-c03fd5b6f2e8', '10', '1', '', '2016-08-31 14:07:07', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `b_foods_type`
--

CREATE TABLE `b_foods_type` (
  `foods_type_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `foods_type_code` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `foods_type_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `active` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `remark` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `sort1` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `date_create` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `b_foods_type`
--

INSERT INTO `b_foods_type` (`foods_type_id`, `foods_type_code`, `foods_type_name`, `active`, `remark`, `sort1`, `date_create`) VALUES
('29b9ecd5-6e7e-11e6-a3fd-c03fd5b6f2e8', '10', 'อาหารจานเดียว', '1', NULL, '100', NULL),
('29b9f510-6e7e-11e6-a3fd-c03fd5b6f2e8', '11', 'กับข้าว', '1', NULL, '101', NULL),
('6e8af84d-6e7e-11e6-a3fd-c03fd5b6f2e8', '12', 'อาหารอีสาน', '1', NULL, '102', NULL),
('6e8b0b40-6e7e-11e6-a3fd-c03fd5b6f2e8', '13', 'สุรา, เบียร์1', '1', 'null', '', NULL),
('cee2df68-6e7e-11e6-a3fd-c03fd5b6f2e8', '14', 'น้ำอัดลม', '1', NULL, '104', NULL),
('cee2eaae-6e7e-11e6-a3fd-c03fd5b6f2e8', '15', 'น้ำผลไม้', '1', NULL, '105', NULL),
('0275e3a8-6e7f-11e6-a3fd-c03fd5b6f2e8', '16', 'ขนมหวาน', '1', NULL, '106', NULL),
('0275ecff-6e7f-11e6-a3fd-c03fd5b6f2e8', '17', 'อาหารกลางโต๊ะ', '1', NULL, '107', NULL),
('923b1bc4-73dc-11e6-a181-c03fd5b6f2e8', '09', 'สุรา, เบียร์', '1', 'null11', '', '2016-09-06 09:49:46'),
('5d9ccb22-74a3-11e6-b621-c03fd5b6f2e8', '10', 'น้ำอัดลม1', '1', 'null', '', '2016-09-07 09:32:48');

-- --------------------------------------------------------

--
-- Table structure for table `b_printername`
--

CREATE TABLE `b_printername` (
  `printer_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `printer_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `active` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `printer_ip` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `date_create` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `b_printername`
--

INSERT INTO `b_printername` (`printer_id`, `printer_name`, `active`, `printer_ip`, `date_create`) VALUES
('92da874f-6e84-11e6-a3fd-c03fd5b6f2e8', 'T88', '1', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `b_restaurant`
--

CREATE TABLE `b_restaurant` (
  `res_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `res_code` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `res_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `active` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `remark` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `date_create` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `default_res` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `receipt_footer1` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `receipt_header1` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `receipt_footer2` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `receipt_header2` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `bill_code` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `bill_month` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='ชื่อร้านอาหาร';

--
-- Dumping data for table `b_restaurant`
--

INSERT INTO `b_restaurant` (`res_id`, `res_code`, `res_name`, `active`, `remark`, `date_create`, `default_res`, `receipt_footer1`, `receipt_header1`, `receipt_footer2`, `receipt_header2`, `bill_code`, `bill_month`) VALUES
('beefcdc0-5a1d-11e6-99a1-c03fd5b6f2e8', '10', 'ครัวมัยลาภ', '1', 'null', NULL, '1', 'โทร 0892017028', '105 ซอยมัยลาภ ถนนรามอินทรา', 'Line id:@nakoyagarden', NULL, '000016', '11'),
('beefd53a-5a1d-11e6-99a1-c03fd5b6f2e8', '11', 'อีสาน รสเด็ด', '1', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `b_table`
--

CREATE TABLE `b_table` (
  `table_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `area_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `table_code` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `table_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `active` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `remark` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `sort1` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `date_create` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `status_use` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '0=default;1=use;',
  `date_use` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `b_table`
--

INSERT INTO `b_table` (`table_id`, `area_id`, `table_code`, `table_name`, `active`, `remark`, `sort1`, `date_create`, `status_use`, `date_use`) VALUES
('dd9f4e7d-5a1b-11e6-99a1-c03fd5b6f2e8', NULL, '100', 'โต๊ะ1', '1', '1', NULL, NULL, '0', NULL),
('3aed5439-5a1c-11e6-99a1-c03fd5b6f2e8', NULL, '101', 'โต๊ะ2', '1', NULL, NULL, NULL, '0', NULL),
('3aed5e6c-5a1c-11e6-99a1-c03fd5b6f2e8', NULL, '103', 'โต๊ะ3', '1', NULL, NULL, NULL, '1', NULL),
('6e7f4e6b-5a1c-11e6-99a1-c03fd5b6f2e8', '', '104', 'โต๊ะ4', '1', 'สสสสบ', 'null', NULL, '1', NULL),
('857880af-5a1c-11e6-99a1-c03fd5b6f2e8', NULL, '105', 'โต๊ะ5', '1', NULL, NULL, NULL, '1', NULL),
('857893df-5a1c-11e6-99a1-c03fd5b6f2e8', NULL, '106', 'โต๊ะ6', '1', NULL, NULL, NULL, '1', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `b_user`
--

CREATE TABLE `b_user` (
  `user_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `user_login` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `user_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `active` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `password1` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `privilege` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '1=all privilege,2=order,3=order bill',
  `remark` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `b_user`
--

INSERT INTO `b_user` (`user_id`, `user_login`, `user_name`, `active`, `password1`, `privilege`, `remark`) VALUES
('447e331c-ad32-11e6-96c5-02004c4f4f50', 'admin', 'admin', '1', '1618', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `t_bill`
--

CREATE TABLE `t_bill` (
  `bill_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `bill_code` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `bill_date` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `lot_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `active` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `remark` varchar(25) COLLATE utf8_bin DEFAULT NULL,
  `status_void` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `void_date` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `table_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `res_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `area_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `device_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `amount` decimal(17,2) DEFAULT NULL,
  `discount` decimal(17,2) DEFAULT NULL,
  `service_charge` decimal(17,2) DEFAULT NULL,
  `vat` decimal(17,2) DEFAULT NULL,
  `total` decimal(17,2) DEFAULT NULL,
  `nettotal` decimal(17,2) DEFAULT NULL,
  `date_create` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `date_modi` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `t_bill`
--

INSERT INTO `t_bill` (`bill_id`, `bill_code`, `bill_date`, `lot_id`, `active`, `remark`, `status_void`, `void_date`, `table_id`, `res_id`, `area_id`, `device_id`, `amount`, `discount`, `service_charge`, `vat`, `total`, `nettotal`, `date_create`, `date_modi`) VALUES
('8605707c-94c6-4d33-94e6-fcbe8c6f5da6', '5911000015', '2016-11-18 14:53:17', NULL, '1', '', '1', NULL, 'dd9f4e7d-5a1b-11e6-99a1-c03fd5b6f2e8', '', 'a01f92e1-5926-11e6-bfd0-c03fd5b6f2e8', '', '45.00', '0.00', '0.00', '0.00', '45.00', '45.00', '2016-11-18 14:53:17', NULL),
('dcf49747-7c91-474f-91b3-f2c6bbe5c894', '5911000016', '2016-11-18 14:56:28', NULL, '1', '', '0', NULL, 'dd9f4e7d-5a1b-11e6-99a1-c03fd5b6f2e8', '', 'a01f92e1-5926-11e6-bfd0-c03fd5b6f2e8', '', '45.00', '0.00', '0.00', '0.00', '45.00', '45.00', '2016-11-18 14:56:28', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `t_bill_detail`
--

CREATE TABLE `t_bill_detail` (
  `bill_detail_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `bill_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `order_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `lot_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `status_void` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `row1` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `foods_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `foods_code` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `price` decimal(17,2) DEFAULT NULL,
  `qty` decimal(17,2) DEFAULT NULL,
  `amount` decimal(17,2) DEFAULT NULL,
  `date_create` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `date_modi` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `active` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `remark` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `t_bill_detail`
--

INSERT INTO `t_bill_detail` (`bill_detail_id`, `bill_id`, `order_id`, `lot_id`, `status_void`, `row1`, `foods_id`, `foods_code`, `price`, `qty`, `amount`, `date_create`, `date_modi`, `active`, `remark`) VALUES
('110423f9-ad64-11e6-96c5-02004c4f4f50', '8605707c-94c6-4d33-94e6-fcbe8c6f5da6', 'ba8684b3-ad48-11e6-96c5-02004c4f4f50', NULL, '0', '1', 'cbfc636c-7593-11e6-96a5-c03fd5b6f2e8', '1104', '45.00', '1.00', '45.00', '2016-11-18 14:53:17', NULL, '1', NULL),
('11085fd7-ad64-11e6-96c5-02004c4f4f50', '8605707c-94c6-4d33-94e6-fcbe8c6f5da6', 'ba88cd18-ad48-11e6-96c5-02004c4f4f50', NULL, '0', '2', '74c833d6-7590-11e6-96a5-c03fd5b6f2e8', '1103', '45.00', '2.00', '90.00', '2016-11-18 14:53:17', NULL, '1', NULL),
('830927f6-ad64-11e6-96c5-02004c4f4f50', 'dcf49747-7c91-474f-91b3-f2c6bbe5c894', 'ba8684b3-ad48-11e6-96c5-02004c4f4f50', NULL, '0', '1', 'cbfc636c-7593-11e6-96a5-c03fd5b6f2e8', '1104', '45.00', '1.00', '45.00', '2016-11-18 14:56:28', NULL, '1', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `t_order`
--

CREATE TABLE `t_order` (
  `order_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `lot_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `row1` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `foods_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `foods_code` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `foods_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `order_date` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `price` decimal(10,0) DEFAULT NULL,
  `qty` decimal(10,0) DEFAULT NULL,
  `remark` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `table_code` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `device_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `res_code` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `area_code` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `status_foods_1` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `status_foods_2` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `status_foods_3` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `status_bill` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '0=default,1=bill check ,2=check complete',
  `bill_check_date` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `status_cook` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '0=default,1=cook receive,2=cook finish',
  `cook_receive_date` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `cook_finish_date` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `active` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `void_date` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `status_void` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `printer_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `date_create` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `date_modi` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `status_to_go` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '0=default,1=in restaurant,2=to go',
  `bill_id` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `t_order`
--

INSERT INTO `t_order` (`order_id`, `lot_id`, `row1`, `foods_id`, `foods_code`, `foods_name`, `order_date`, `price`, `qty`, `remark`, `table_code`, `device_id`, `res_code`, `area_code`, `status_foods_1`, `status_foods_2`, `status_foods_3`, `status_bill`, `bill_check_date`, `status_cook`, `cook_receive_date`, `cook_finish_date`, `active`, `void_date`, `status_void`, `printer_name`, `date_create`, `date_modi`, `status_to_go`, `bill_id`) VALUES
('ba8684b3-ad48-11e6-96c5-02004c4f4f50', 'e82dcf58-3d33-43fd-93e6-afe47e9d09fa', '1', 'cbfc636c-7593-11e6-96a5-c03fd5b6f2e8', '1104', 'คะน้าปลาเค็ม', '2016-11-18 11:37:35', '45', '1', '', '100', NULL, '10', '1000', '', '', '', '2', '2016-11-18 14:56:28', '0', NULL, NULL, '1', NULL, '0', '', '2016-11-18 11:37:35', NULL, '1', 'dcf49747-7c91-474f-91b3-f2c6bbe5c894'),
('ba88cd18-ad48-11e6-96c5-02004c4f4f50', 'e82dcf58-3d33-43fd-93e6-afe47e9d09fa', '2', '74c833d6-7590-11e6-96a5-c03fd5b6f2e8', '1103', 'คะน้าหมูกรอบ', '2016-11-18 11:37:35', '45', '2', '', '100', NULL, '10', '1000', '', '', '', '2', '2016-11-18 14:56:28', '0', NULL, NULL, '1', '2016-11-18 14:56:28', '1', '', '2016-11-18 11:37:35', NULL, '1', 'dcf49747-7c91-474f-91b3-f2c6bbe5c894');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `b_area`
--
ALTER TABLE `b_area`
  ADD PRIMARY KEY (`area_id`);

--
-- Indexes for table `b_foods`
--
ALTER TABLE `b_foods`
  ADD PRIMARY KEY (`foods_id`);

--
-- Indexes for table `b_foods_type`
--
ALTER TABLE `b_foods_type`
  ADD PRIMARY KEY (`foods_type_id`);

--
-- Indexes for table `b_printername`
--
ALTER TABLE `b_printername`
  ADD PRIMARY KEY (`printer_id`);

--
-- Indexes for table `b_restaurant`
--
ALTER TABLE `b_restaurant`
  ADD PRIMARY KEY (`res_id`);

--
-- Indexes for table `b_table`
--
ALTER TABLE `b_table`
  ADD PRIMARY KEY (`table_id`);

--
-- Indexes for table `b_user`
--
ALTER TABLE `b_user`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `t_bill`
--
ALTER TABLE `t_bill`
  ADD PRIMARY KEY (`bill_id`);

--
-- Indexes for table `t_bill_detail`
--
ALTER TABLE `t_bill_detail`
  ADD PRIMARY KEY (`bill_detail_id`);

--
-- Indexes for table `t_order`
--
ALTER TABLE `t_order`
  ADD PRIMARY KEY (`order_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
