-- phpMyAdmin SQL Dump
-- version 4.2.12deb2+deb8u2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 30, 2016 at 04:31 AM
-- Server version: 5.5.52-0+deb8u1
-- PHP Version: 5.6.27-0+deb8u1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `restaurant`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `getBillCode`()
BEGIN

SELECT * FROM t_bill;

END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `b_area`
--

CREATE TABLE IF NOT EXISTS `b_area` (
  `area_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `area_code` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `area_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `active` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `remark` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `sort1` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `date_create` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `date_modi` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `host_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `branch_id` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `b_area`
--

INSERT INTO `b_area` (`area_id`, `area_code`, `area_name`, `active`, `remark`, `sort1`, `date_create`, `date_modi`, `host_id`, `branch_id`) VALUES
('a01f92e1-5926-11e6-bfd0-c03fd5b6f2e8', '1000', 'ในร้าน', '1', NULL, '100', NULL, NULL, NULL, NULL),
('a01fa080-5926-11e6-bfd0-c03fd5b6f2e8', '1001', 'ฟุตบาท', '1', 'ยยยยl', '101', NULL, NULL, NULL, NULL),
('c00d1782-5926-11e6-bfd0-c03fd5b6f2e8', '1002', 'ในสวน', '1', NULL, '102', NULL, NULL, NULL, NULL),
('6a0fde50-70b3-11e6-b472-c03fd5b6f2e8', '04', 'ในบ้าน', '1', '', '103', '2016-09-02 09:17:36', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `b_foods`
--

CREATE TABLE IF NOT EXISTS `b_foods` (
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
  `date_modi` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `host_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `branch_id` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `b_foods`
--

INSERT INTO `b_foods` (`foods_id`, `foods_code`, `foods_name`, `foods_price`, `active`, `foods_type_id`, `remark`, `res_id`, `res_code`, `status_foods`, `printer_name`, `date_create`, `date_modi`, `host_id`, `branch_id`) VALUES
('cbfc636c-7593-11e6-96a5-c03fd5b6f2e8', '1104', 'คะน้าปลาเค็ม', 50.00, '1', '29b9f510-6e7e-11e6-a3fd-c03fd5b6f2e8', '', 'beefcdc0-5a1d-11e6-99a1-c03fd5b6f2e8', '10', '1', '', '2016-09-08 14:13:52', NULL, NULL, NULL),
('74c833d6-7590-11e6-96a5-c03fd5b6f2e8', '1103', 'คะน้าหมูกรอบ', 50.00, '1', '29b9f510-6e7e-11e6-a3fd-c03fd5b6f2e8', '', 'beefcdc0-5a1d-11e6-99a1-c03fd5b6f2e8', '10', '1', '', '2016-09-08 13:49:57', NULL, NULL, NULL),
('5debdec7-7590-11e6-96a5-c03fd5b6f2e8', '1006', 'ผัดซีอิวหมู', 45.00, '1', '29b9ecd5-6e7e-11e6-a3fd-c03fd5b6f2e8', '', 'beefcdc0-5a1d-11e6-99a1-c03fd5b6f2e8', '10', '1', '', '2016-09-08 13:49:19', NULL, NULL, NULL),
('9d5480ab-758e-11e6-96a5-c03fd5b6f2e8', '1102', 'แกงเขียวหวาน', 50.00, '1', '29b9f510-6e7e-11e6-a3fd-c03fd5b6f2e8', '', 'beefcdc0-5a1d-11e6-99a1-c03fd5b6f2e8', '10', '1', '', '2016-09-08 13:36:46', NULL, NULL, NULL),
('88225c62-758e-11e6-96a5-c03fd5b6f2e8', '1101', 'แกงส้มกุ้ง', 50.00, '1', '29b9f510-6e7e-11e6-a3fd-c03fd5b6f2e8', '', 'beefcdc0-5a1d-11e6-99a1-c03fd5b6f2e8', '10', '1', '', '2016-09-08 13:36:11', NULL, NULL, NULL),
('722a4860-758e-11e6-96a5-c03fd5b6f2e8', '1005', 'ข้าวกระเพราไก่+ไข่ดาว', 45.00, '1', '29b9ecd5-6e7e-11e6-a3fd-c03fd5b6f2e8', '', 'beefcdc0-5a1d-11e6-99a1-c03fd5b6f2e8', '10', '1', '', '2016-09-08 13:35:34', NULL, NULL, NULL),
('45588b53-758e-11e6-96a5-c03fd5b6f2e8', '1004', 'ข้าวผัดหมู', 44.00, '1', '29b9ecd5-6e7e-11e6-a3fd-c03fd5b6f2e8', '', 'beefcdc0-5a1d-11e6-99a1-c03fd5b6f2e8', '10', '1', '', '2016-09-08 13:34:19', NULL, NULL, NULL),
('296650f5-758e-11e6-96a5-c03fd5b6f2e8', '1003', 'ข้าวคะน้าหมูกรอบ', 45.00, '1', '29b9ecd5-6e7e-11e6-a3fd-c03fd5b6f2e8', '', 'beefcdc0-5a1d-11e6-99a1-c03fd5b6f2e8', '10', '1', '', '2016-09-08 13:33:32', NULL, NULL, NULL),
('16d42722-758e-11e6-96a5-c03fd5b6f2e8', '1002', 'ข้าวกระเพราเนื้อ+ไข่ดาว', 45.00, '1', '29b9ecd5-6e7e-11e6-a3fd-c03fd5b6f2e8', '', 'beefcdc0-5a1d-11e6-99a1-c03fd5b6f2e8', '10', '1', '', '2016-09-08 13:33:01', NULL, NULL, NULL),
('873daada-6f49-11e6-b472-c03fd5b6f2e8', '1001', 'ข้าวกระเพราหมู ไข่ดาว', 45.00, '1', '29b9ecd5-6e7e-11e6-a3fd-c03fd5b6f2e8', '', 'beefcdc0-5a1d-11e6-99a1-c03fd5b6f2e8', '10', '1', '', '2016-08-31 14:07:07', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `b_foods_type`
--

CREATE TABLE IF NOT EXISTS `b_foods_type` (
  `foods_type_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `foods_type_code` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `foods_type_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `active` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `remark` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `sort1` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `date_create` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `date_modi` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `host_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `branch_id` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `b_foods_type`
--

INSERT INTO `b_foods_type` (`foods_type_id`, `foods_type_code`, `foods_type_name`, `active`, `remark`, `sort1`, `date_create`, `date_modi`, `host_id`, `branch_id`) VALUES
('29b9ecd5-6e7e-11e6-a3fd-c03fd5b6f2e8', '10', 'อาหารจานเดียว', '1', NULL, '100', NULL, NULL, NULL, NULL),
('29b9f510-6e7e-11e6-a3fd-c03fd5b6f2e8', '11', 'กับข้าว', '1', NULL, '101', NULL, NULL, NULL, NULL),
('6e8af84d-6e7e-11e6-a3fd-c03fd5b6f2e8', '12', 'อาหารอีสาน', '1', NULL, '102', NULL, NULL, NULL, NULL),
('6e8b0b40-6e7e-11e6-a3fd-c03fd5b6f2e8', '13', 'สุรา, เบียร์1', '1', 'null', '', NULL, NULL, NULL, NULL),
('cee2df68-6e7e-11e6-a3fd-c03fd5b6f2e8', '14', 'น้ำอัดลม', '1', NULL, '104', NULL, NULL, NULL, NULL),
('cee2eaae-6e7e-11e6-a3fd-c03fd5b6f2e8', '15', 'น้ำผลไม้', '1', NULL, '105', NULL, NULL, NULL, NULL),
('0275e3a8-6e7f-11e6-a3fd-c03fd5b6f2e8', '16', 'ขนมหวาน', '1', NULL, '106', NULL, NULL, NULL, NULL),
('0275ecff-6e7f-11e6-a3fd-c03fd5b6f2e8', '17', 'อาหารกลางโต๊ะ', '1', NULL, '107', NULL, NULL, NULL, NULL),
('923b1bc4-73dc-11e6-a181-c03fd5b6f2e8', '09', 'สุรา, เบียร์', '1', 'null11', '', '2016-09-06 09:49:46', NULL, NULL, NULL),
('5d9ccb22-74a3-11e6-b621-c03fd5b6f2e8', '10', 'น้ำอัดลม1', '1', 'null', '', '2016-09-07 09:32:48', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `b_printername`
--

CREATE TABLE IF NOT EXISTS `b_printername` (
  `printer_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `printer_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `active` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `printer_ip` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `date_create` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `date_modi` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `host_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `branch_id` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `b_printername`
--

INSERT INTO `b_printername` (`printer_id`, `printer_name`, `active`, `printer_ip`, `date_create`, `date_modi`, `host_id`, `branch_id`) VALUES
('92da874f-6e84-11e6-a3fd-c03fd5b6f2e8', 'T88', '1', NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `b_restaurant`
--

CREATE TABLE IF NOT EXISTS `b_restaurant` (
  `res_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `res_code` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `res_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `active` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `remark` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `date_create` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `date_modi` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `default_res` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `receipt_footer1` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `receipt_header1` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `receipt_footer2` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `receipt_header2` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `bill_code` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `bill_month` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `tax_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `printer_foods1` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `printer_foods2` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `printer_foods3` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `printer_waterbar1` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `printer_waterbar2` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `printer_waterbar3` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `sort1` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `host_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `branch_id` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='ชื่อร้านอาหาร';

--
-- Dumping data for table `b_restaurant`
--

INSERT INTO `b_restaurant` (`res_id`, `res_code`, `res_name`, `active`, `remark`, `date_create`, `date_modi`, `default_res`, `receipt_footer1`, `receipt_header1`, `receipt_footer2`, `receipt_header2`, `bill_code`, `bill_month`, `tax_id`, `printer_foods1`, `printer_foods2`, `printer_foods3`, `printer_waterbar1`, `printer_waterbar2`, `printer_waterbar3`, `sort1`, `host_id`, `branch_id`) VALUES
('beefcdc0-5a1d-11e6-99a1-c03fd5b6f2e8', '10', 'ครัวมัยลาภaaaa', '1', '', NULL, NULL, '1', 'โทร 0892017028', '105 ซอยมัยลาภ ถนนรามอินทรา', 'Line id:@nakoyagarden', '', '000019', '11', '33333334444', '', '', '', '', '', '', '', NULL, NULL),
('beefd53a-5a1d-11e6-99a1-c03fd5b6f2e8', '11', 'อีสาน รสเด็ด', '1', 'ggggg', NULL, NULL, '0', '', '', '', '', NULL, NULL, '', '', '', '', '', '', '', '', NULL, NULL),
('4a6cd9a1-b603-11e6-9063-b827eb43d88e', '03', 'test', '1', 'ffff', '2016-11-29 07:13:13', NULL, '0', '', '', '', '', NULL, NULL, '', '', '', '', '', '', '', '', NULL, NULL),
('fb00933e-b617-11e6-9063-b827eb43d88e', '04', 'ทดสอบ', '1', 'dddddd', '2016-11-29 09:41:19', NULL, '0', 'c', 'a', 'd', 'b', '', NULL, '2222', NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL),
('135ebc82-b6a1-11e6-9063-b827eb43d88e', '05', 'ooooo', '1', '', '2016-11-30 02:02:41', NULL, '0', '', '', '', '', '', NULL, '', '', '', '', '', '', '', '', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `b_table`
--

CREATE TABLE IF NOT EXISTS `b_table` (
  `table_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `area_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `table_code` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `table_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `active` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `remark` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `sort1` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `date_create` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `date_modi` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `status_use` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '0=default;1=use;',
  `date_use` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `host_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `branch_id` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `b_table`
--

INSERT INTO `b_table` (`table_id`, `area_id`, `table_code`, `table_name`, `active`, `remark`, `sort1`, `date_create`, `date_modi`, `status_use`, `date_use`, `host_id`, `branch_id`) VALUES
('dd9f4e7d-5a1b-11e6-99a1-c03fd5b6f2e8', NULL, '100', 'โต๊ะ1', '1', '1', NULL, NULL, NULL, '0', NULL, NULL, NULL),
('3aed5439-5a1c-11e6-99a1-c03fd5b6f2e8', NULL, '101', 'โต๊ะ2', '1', NULL, NULL, NULL, NULL, '0', NULL, NULL, NULL),
('3aed5e6c-5a1c-11e6-99a1-c03fd5b6f2e8', NULL, '103', 'โต๊ะ3', '1', NULL, NULL, NULL, NULL, '0', NULL, NULL, NULL),
('6e7f4e6b-5a1c-11e6-99a1-c03fd5b6f2e8', '', '104', 'โต๊ะ4', '1', 'สสสสบ', 'null', NULL, NULL, '1', NULL, NULL, NULL),
('857880af-5a1c-11e6-99a1-c03fd5b6f2e8', NULL, '105', 'โต๊ะ5', '1', NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL),
('857893df-5a1c-11e6-99a1-c03fd5b6f2e8', NULL, '106', 'โต๊ะ6', '1', NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `b_user`
--

CREATE TABLE IF NOT EXISTS `b_user` (
  `user_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `user_login` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `user_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `active` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `password1` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `privilege` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '1=all privilege,2=order,3=order bill',
  `remark` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `date_create` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `date_modi` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `host_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `branch_id` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `b_user`
--

INSERT INTO `b_user` (`user_id`, `user_login`, `user_name`, `active`, `password1`, `privilege`, `remark`, `date_create`, `date_modi`, `host_id`, `branch_id`) VALUES
('447e331c-ad32-11e6-96c5-02004c4f4f50', 'admin', 'admin', '1', '1618', NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `t_bill`
--

CREATE TABLE IF NOT EXISTS `t_bill` (
  `bill_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `bill_code` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `bill_date` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `lot_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `active` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `remark` varchar(25) COLLATE utf8_bin DEFAULT NULL,
  `status_void` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `void_date` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `void_user` varchar(255) COLLATE utf8_bin DEFAULT NULL,
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
  `date_modi` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `bill_user` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `status_closeday` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `closeday_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `host_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `branch_id` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `t_bill`
--

INSERT INTO `t_bill` (`bill_id`, `bill_code`, `bill_date`, `lot_id`, `active`, `remark`, `status_void`, `void_date`, `void_user`, `table_id`, `res_id`, `area_id`, `device_id`, `amount`, `discount`, `service_charge`, `vat`, `total`, `nettotal`, `date_create`, `date_modi`, `bill_user`, `status_closeday`, `closeday_id`, `host_id`, `branch_id`) VALUES
('8605707c-94c6-4d33-94e6-fcbe8c6f5da6', '5911000015', '2016-11-18 14:53:17', NULL, '1', '', '1', NULL, NULL, 'dd9f4e7d-5a1b-11e6-99a1-c03fd5b6f2e8', 'beefcdc0-5a1d-11e6-99a1-c03fd5b6f2e8', 'a01f92e1-5926-11e6-bfd0-c03fd5b6f2e8', '', 45.00, 0.00, 0.00, 0.00, 45.00, 45.00, '2016-11-18 14:53:17', NULL, NULL, '0', NULL, NULL, NULL),
('dcf49747-7c91-474f-91b3-f2c6bbe5c894', '5911000016', '2016-11-18 14:56:28', NULL, '1', '', '0', NULL, NULL, 'dd9f4e7d-5a1b-11e6-99a1-c03fd5b6f2e8', 'beefcdc0-5a1d-11e6-99a1-c03fd5b6f2e8', 'a01f92e1-5926-11e6-bfd0-c03fd5b6f2e8', '', 45.00, 0.00, 0.00, 0.00, 45.00, 45.00, '2016-11-18 14:56:28', NULL, NULL, '0', NULL, NULL, NULL),
('bc8eda0e-27f9-4fa2-9b44-bf9324423d3a', '5911000017', '2016-11-22 03:22:19', NULL, '1', '', '0', NULL, NULL, 'dd9f4e7d-5a1b-11e6-99a1-c03fd5b6f2e8', 'beefcdc0-5a1d-11e6-99a1-c03fd5b6f2e8', 'a01f92e1-5926-11e6-bfd0-c03fd5b6f2e8', '', 405.00, 0.00, 0.00, 0.00, 405.00, 405.00, '2016-11-22 03:22:19', NULL, NULL, '0', NULL, NULL, NULL),
('f3d1c64a-8002-42d9-87b0-fb948cfc855a', '5911000018', '2016-11-22 03:24:56', NULL, '1', '', '0', NULL, NULL, '3aed5e6c-5a1c-11e6-99a1-c03fd5b6f2e8', 'beefcdc0-5a1d-11e6-99a1-c03fd5b6f2e8', 'a01f92e1-5926-11e6-bfd0-c03fd5b6f2e8', '', 270.00, 0.00, 0.00, 0.00, 270.00, 270.00, '2016-11-22 03:24:56', NULL, NULL, '0', NULL, NULL, NULL),
('5a6aa657-5e06-4a3c-9674-5c148b1f2e47', '5911000019', '2016-11-28 07:16:54', NULL, '1', '', '0', NULL, NULL, 'dd9f4e7d-5a1b-11e6-99a1-c03fd5b6f2e8', '', 'a01f92e1-5926-11e6-bfd0-c03fd5b6f2e8', '', 135.00, 0.00, 0.00, 0.00, 135.00, 135.00, '2016-11-28 07:16:54', NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `t_bill_detail`
--

CREATE TABLE IF NOT EXISTS `t_bill_detail` (
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
  `remark` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `host_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `branch_id` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `t_bill_detail`
--

INSERT INTO `t_bill_detail` (`bill_detail_id`, `bill_id`, `order_id`, `lot_id`, `status_void`, `row1`, `foods_id`, `foods_code`, `price`, `qty`, `amount`, `date_create`, `date_modi`, `active`, `remark`, `host_id`, `branch_id`) VALUES
('110423f9-ad64-11e6-96c5-02004c4f4f50', '8605707c-94c6-4d33-94e6-fcbe8c6f5da6', 'ba8684b3-ad48-11e6-96c5-02004c4f4f50', NULL, '0', '1', 'cbfc636c-7593-11e6-96a5-c03fd5b6f2e8', '1104', 45.00, 1.00, 45.00, '2016-11-18 14:53:17', NULL, '1', NULL, NULL, NULL),
('11085fd7-ad64-11e6-96c5-02004c4f4f50', '8605707c-94c6-4d33-94e6-fcbe8c6f5da6', 'ba88cd18-ad48-11e6-96c5-02004c4f4f50', NULL, '0', '2', '74c833d6-7590-11e6-96a5-c03fd5b6f2e8', '1103', 45.00, 2.00, 90.00, '2016-11-18 14:53:17', NULL, '1', NULL, NULL, NULL),
('830927f6-ad64-11e6-96c5-02004c4f4f50', 'dcf49747-7c91-474f-91b3-f2c6bbe5c894', 'ba8684b3-ad48-11e6-96c5-02004c4f4f50', NULL, '0', '1', 'cbfc636c-7593-11e6-96a5-c03fd5b6f2e8', '1104', 45.00, 1.00, 45.00, '2016-11-18 14:56:28', NULL, '1', NULL, NULL, NULL),
('e058ee93-b062-11e6-bf71-b827eb43d88e', 'bc8eda0e-27f9-4fa2-9b44-bf9324423d3a', 'b647772d-b062-11e6-bf71-b827eb43d88e', NULL, '0', '1', 'cbfc636c-7593-11e6-96a5-c03fd5b6f2e8', '1104', 45.00, 1.00, 45.00, '2016-11-22 03:22:19', NULL, '1', NULL, NULL, NULL),
('e06359a1-b062-11e6-bf71-b827eb43d88e', 'bc8eda0e-27f9-4fa2-9b44-bf9324423d3a', 'b64b4152-b062-11e6-bf71-b827eb43d88e', NULL, '0', '2', '74c833d6-7590-11e6-96a5-c03fd5b6f2e8', '1103', 45.00, 2.00, 90.00, '2016-11-22 03:22:19', NULL, '1', NULL, NULL, NULL),
('e067c0c9-b062-11e6-bf71-b827eb43d88e', 'bc8eda0e-27f9-4fa2-9b44-bf9324423d3a', 'ba3728fc-b062-11e6-bf71-b827eb43d88e', NULL, '0', '3', 'cbfc636c-7593-11e6-96a5-c03fd5b6f2e8', '1104', 45.00, 1.00, 45.00, '2016-11-22 03:22:19', NULL, '1', NULL, NULL, NULL),
('e06ab798-b062-11e6-bf71-b827eb43d88e', 'bc8eda0e-27f9-4fa2-9b44-bf9324423d3a', 'ba3a7dba-b062-11e6-bf71-b827eb43d88e', NULL, '0', '4', '74c833d6-7590-11e6-96a5-c03fd5b6f2e8', '1103', 45.00, 2.00, 90.00, '2016-11-22 03:22:19', NULL, '1', NULL, NULL, NULL),
('e06fb152-b062-11e6-bf71-b827eb43d88e', 'bc8eda0e-27f9-4fa2-9b44-bf9324423d3a', 'ba44faad-b062-11e6-bf71-b827eb43d88e', NULL, '0', '5', 'cbfc636c-7593-11e6-96a5-c03fd5b6f2e8', '1104', 45.00, 1.00, 45.00, '2016-11-22 03:22:20', NULL, '1', NULL, NULL, NULL),
('e0742f52-b062-11e6-bf71-b827eb43d88e', 'bc8eda0e-27f9-4fa2-9b44-bf9324423d3a', 'ba47999d-b062-11e6-bf71-b827eb43d88e', NULL, '0', '6', '74c833d6-7590-11e6-96a5-c03fd5b6f2e8', '1103', 45.00, 2.00, 90.00, '2016-11-22 03:22:20', NULL, '1', NULL, NULL, NULL),
('3ddfed0f-b063-11e6-bf71-b827eb43d88e', 'f3d1c64a-8002-42d9-87b0-fb948cfc855a', '36ef1696-b063-11e6-bf71-b827eb43d88e', NULL, '0', '1', '722a4860-758e-11e6-96a5-c03fd5b6f2e8', '1005', 45.00, 1.00, 45.00, '2016-11-22 03:24:56', NULL, '1', NULL, NULL, NULL),
('3dee6e45-b063-11e6-bf71-b827eb43d88e', 'f3d1c64a-8002-42d9-87b0-fb948cfc855a', '36f30e43-b063-11e6-bf71-b827eb43d88e', NULL, '0', '2', '45588b53-758e-11e6-96a5-c03fd5b6f2e8', '1004', 45.00, 2.00, 90.00, '2016-11-22 03:24:56', NULL, '1', NULL, NULL, NULL),
('3df181df-b063-11e6-bf71-b827eb43d88e', 'f3d1c64a-8002-42d9-87b0-fb948cfc855a', '36f97d1e-b063-11e6-bf71-b827eb43d88e', NULL, '0', '3', '88225c62-758e-11e6-96a5-c03fd5b6f2e8', '1101', 45.00, 3.00, 135.00, '2016-11-22 03:24:56', NULL, '1', NULL, NULL, NULL),
('a3eb769a-b53a-11e6-9101-b827eb43d88e', '5a6aa657-5e06-4a3c-9674-5c148b1f2e47', '7ac3debf-b53a-11e6-9101-b827eb43d88e', NULL, '0', '1', 'cbfc636c-7593-11e6-96a5-c03fd5b6f2e8', '1104', 45.00, 1.00, 45.00, '2016-11-28 07:16:54', NULL, '1', NULL, NULL, NULL),
('a3f7aa76-b53a-11e6-9101-b827eb43d88e', '5a6aa657-5e06-4a3c-9674-5c148b1f2e47', '805d56f5-b53a-11e6-9101-b827eb43d88e', NULL, '0', '2', '74c833d6-7590-11e6-96a5-c03fd5b6f2e8', '1103', 45.00, 1.00, 45.00, '2016-11-28 07:16:54', NULL, '1', NULL, NULL, NULL),
('a4033fd5-b53a-11e6-9101-b827eb43d88e', '5a6aa657-5e06-4a3c-9674-5c148b1f2e47', '814b5bef-b53a-11e6-9101-b827eb43d88e', NULL, '0', '3', '9d5480ab-758e-11e6-96a5-c03fd5b6f2e8', '1102', 45.00, 1.00, 45.00, '2016-11-28 07:16:54', NULL, '1', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `t_closeday`
--

CREATE TABLE IF NOT EXISTS `t_closeday` (
  `closeday_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `closeday_date` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `res_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `amount` decimal(17,2) DEFAULT NULL,
  `discount` decimal(17,2) DEFAULT NULL,
  `total` decimal(17,2) DEFAULT NULL,
  `service_charge` decimal(17,2) DEFAULT NULL,
  `vat` decimal(17,2) DEFAULT NULL,
  `nettotal` decimal(17,2) DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `active` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `status_void` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `void_date` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `void_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `cnt_bill` decimal(17,2) DEFAULT NULL,
  `bill_amount` decimal(17,2) DEFAULT NULL,
  `cnt_order` decimal(17,2) DEFAULT NULL,
  `amount_order` decimal(17,2) DEFAULT NULL,
  `closeday_user` varchar(255) DEFAULT NULL,
  `cash_receive1` decimal(17,2) DEFAULT NULL,
  `cash_receive2` decimal(17,2) DEFAULT NULL,
  `cash_receive3` decimal(17,2) DEFAULT NULL,
  `cash_draw1` decimal(17,2) DEFAULT NULL,
  `cash_draw2` decimal(17,2) DEFAULT NULL,
  `cash_draw3` decimal(17,2) DEFAULT NULL,
  `cash_receive1_remark` varchar(255) DEFAULT NULL,
  `cash_receive2_remark` varchar(255) DEFAULT NULL,
  `cash_receive3_remark` varchar(255) DEFAULT NULL,
  `cash_draw1_remark` varchar(255) DEFAULT NULL,
  `cash_draw2_remark` varchar(255) DEFAULT NULL,
  `cash_draw3_remark` varchar(255) DEFAULT NULL,
  `host_id` varchar(255) DEFAULT NULL,
  `branch_id` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `t_closeday`
--

INSERT INTO `t_closeday` (`closeday_id`, `closeday_date`, `res_id`, `amount`, `discount`, `total`, `service_charge`, `vat`, `nettotal`, `remark`, `active`, `status_void`, `void_date`, `void_user`, `cnt_bill`, `bill_amount`, `cnt_order`, `amount_order`, `closeday_user`, `cash_receive1`, `cash_receive2`, `cash_receive3`, `cash_draw1`, `cash_draw2`, `cash_draw3`, `cash_receive1_remark`, `cash_receive2_remark`, `cash_receive3_remark`, `cash_draw1_remark`, `cash_draw2_remark`, `cash_draw3_remark`, `host_id`, `branch_id`) VALUES
('4cf39b32-7160-406c-bbc9-ad9675f32a48', '2016-11-28 03:27:10', 'beefcdc0-5a1d-11e6-99a1-c03fd5b6f2e8', 675.00, 0.00, 675.00, 0.00, 0.00, 675.00, '', '1', '0', '', '', 2.00, 675.00, 0.00, 0.00, '', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, '', '', '', '', '', '', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `t_order`
--

CREATE TABLE IF NOT EXISTS `t_order` (
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
  `bill_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `order_user` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `status_closeday` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `closeday_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `host_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `branch_id` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `t_order`
--

INSERT INTO `t_order` (`order_id`, `lot_id`, `row1`, `foods_id`, `foods_code`, `foods_name`, `order_date`, `price`, `qty`, `remark`, `table_code`, `device_id`, `res_code`, `area_code`, `status_foods_1`, `status_foods_2`, `status_foods_3`, `status_bill`, `bill_check_date`, `status_cook`, `cook_receive_date`, `cook_finish_date`, `active`, `void_date`, `status_void`, `printer_name`, `date_create`, `date_modi`, `status_to_go`, `bill_id`, `order_user`, `status_closeday`, `closeday_id`, `host_id`, `branch_id`) VALUES
('7ac3debf-b53a-11e6-9101-b827eb43d88e', '0595a510-b779-47b2-9e1c-d1151a842f19', '1', 'cbfc636c-7593-11e6-96a5-c03fd5b6f2e8', '1104', 'คะน้าปลาเค็ม', '2016-11-28 07:15:45', 45, 1, '', '100', NULL, '10', '1000', '', '', '', '2', '2016-11-28 07:16:54', '0', NULL, NULL, '1', NULL, '0', '', '2016-11-28 07:15:45', NULL, '1', '5a6aa657-5e06-4a3c-9674-5c148b1f2e47', '', '0', '', NULL, NULL),
('805d56f5-b53a-11e6-9101-b827eb43d88e', '0595a510-b779-47b2-9e1c-d1151a842f19', '2', '74c833d6-7590-11e6-96a5-c03fd5b6f2e8', '1103', 'คะน้าหมูกรอบ', '2016-11-28 07:15:54', 45, 1, '', '100', NULL, '10', '1000', '', '', '', '2', '2016-11-28 07:16:54', '0', NULL, NULL, '1', NULL, '0', '', '2016-11-28 07:15:54', NULL, '1', '5a6aa657-5e06-4a3c-9674-5c148b1f2e47', '', '0', '', NULL, NULL),
('814b5bef-b53a-11e6-9101-b827eb43d88e', '0595a510-b779-47b2-9e1c-d1151a842f19', '3', '9d5480ab-758e-11e6-96a5-c03fd5b6f2e8', '1102', 'แกงเขียวหวาน', '2016-11-28 07:15:56', 45, 1, '', '100', NULL, '10', '1000', '', '', '', '2', '2016-11-28 07:16:54', '0', NULL, NULL, '1', NULL, '0', '', '2016-11-28 07:15:56', NULL, '1', '5a6aa657-5e06-4a3c-9674-5c148b1f2e47', '', '0', '', NULL, NULL);

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
-- Indexes for table `t_closeday`
--
ALTER TABLE `t_closeday`
 ADD PRIMARY KEY (`closeday_id`);

--
-- Indexes for table `t_order`
--
ALTER TABLE `t_order`
 ADD PRIMARY KEY (`order_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
