-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jan 06, 2017 at 11:12 AM
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
  `date_create` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `date_modi` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `host_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `branch_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `device_id` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `b_area`
--

INSERT INTO `b_area` (`area_id`, `area_code`, `area_name`, `active`, `remark`, `sort1`, `date_create`, `date_modi`, `host_id`, `branch_id`, `device_id`) VALUES
('a01f92e1-5926-11e6-bfd0-c03fd5b6f2e8', '1000', 'ในร้าน', '1', NULL, '100', NULL, NULL, NULL, NULL, NULL),
('a01fa080-5926-11e6-bfd0-c03fd5b6f2e8', '1001', 'ฟุตบาท', '1', 'ยยยยl', '101', NULL, NULL, NULL, NULL, NULL),
('c00d1782-5926-11e6-bfd0-c03fd5b6f2e8', '1002', 'ในสวน', '1', NULL, '102', NULL, NULL, NULL, NULL, NULL),
('6a0fde50-70b3-11e6-b472-c03fd5b6f2e8', '04', 'ในบ้าน1', '1', '', '103', '2016-09-02 09:17:36', NULL, NULL, NULL, NULL),
('5cb43158-cc11-11e6-9d0b-02004c4f4f50', '05', 'ทเสอบบบ', '1', '', '', '2016-12-27 15:49:21', NULL, NULL, NULL, NULL);

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
  `date_modi` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `host_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `branch_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `device_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `sort1` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `b_foods`
--

INSERT INTO `b_foods` (`foods_id`, `foods_code`, `foods_name`, `foods_price`, `active`, `foods_type_id`, `remark`, `res_id`, `res_code`, `status_foods`, `printer_name`, `date_create`, `date_modi`, `host_id`, `branch_id`, `device_id`, `sort1`) VALUES
('cbfc636c-7593-11e6-96a5-c03fd5b6f2e8', '1104', 'คะน้าปลาเค็ม', '50.00', '1', '29b9f510-6e7e-11e6-a3fd-c03fd5b6f2e8', '', 'beefcdc0-5a1d-11e6-99a1-c03fd5b6f2e8', '10', '1', '', '2016-09-08 14:13:52', NULL, NULL, NULL, NULL, NULL),
('74c833d6-7590-11e6-96a5-c03fd5b6f2e8', '1103', 'คะน้าหมูกรอบ', '50.00', '1', '29b9f510-6e7e-11e6-a3fd-c03fd5b6f2e8', '', 'beefcdc0-5a1d-11e6-99a1-c03fd5b6f2e8', '10', '1', '', '2016-09-08 13:49:57', NULL, NULL, NULL, NULL, NULL),
('5debdec7-7590-11e6-96a5-c03fd5b6f2e8', '1006', 'ผัดซีอิวหมู', '45.00', '1', '29b9ecd5-6e7e-11e6-a3fd-c03fd5b6f2e8', '', 'beefcdc0-5a1d-11e6-99a1-c03fd5b6f2e8', '10', '1', '', '2016-09-08 13:49:19', NULL, NULL, NULL, NULL, NULL),
('9d5480ab-758e-11e6-96a5-c03fd5b6f2e8', '1102', 'แกงเขียวหวาน', '50.00', '1', '29b9f510-6e7e-11e6-a3fd-c03fd5b6f2e8', '', 'beefcdc0-5a1d-11e6-99a1-c03fd5b6f2e8', '10', '1', '', '2016-09-08 13:36:46', NULL, NULL, NULL, NULL, NULL),
('88225c62-758e-11e6-96a5-c03fd5b6f2e8', '1101', 'แกงส้มกุ้ง', '50.00', '1', '29b9f510-6e7e-11e6-a3fd-c03fd5b6f2e8', '', 'beefcdc0-5a1d-11e6-99a1-c03fd5b6f2e8', '10', '1', '', '2016-09-08 13:36:11', NULL, NULL, NULL, NULL, NULL),
('722a4860-758e-11e6-96a5-c03fd5b6f2e8', '1005', 'ข้าวกระเพราไก่+ไข่ดาว', '45.00', '1', '29b9ecd5-6e7e-11e6-a3fd-c03fd5b6f2e8', '', 'beefcdc0-5a1d-11e6-99a1-c03fd5b6f2e8', '10', '1', '', '2016-09-08 13:35:34', NULL, NULL, NULL, NULL, NULL),
('45588b53-758e-11e6-96a5-c03fd5b6f2e8', '1004', 'ข้าวผัดหมู', '0.00', '1', '29b9ecd5-6e7e-11e6-a3fd-c03fd5b6f2e8', '', 'beefcdc0-5a1d-11e6-99a1-c03fd5b6f2e8', '10', '1', '', '2016-09-08 13:34:19', NULL, NULL, NULL, NULL, NULL),
('296650f5-758e-11e6-96a5-c03fd5b6f2e8', '1003', 'ข้าวคะน้าหมูกรอบ', '45.00', '1', '29b9ecd5-6e7e-11e6-a3fd-c03fd5b6f2e8', '', 'beefcdc0-5a1d-11e6-99a1-c03fd5b6f2e8', '10', '1', '', '2016-09-08 13:33:32', NULL, NULL, NULL, NULL, NULL),
('16d42722-758e-11e6-96a5-c03fd5b6f2e8', '1002', 'ข้าวกระเพราเนื้อ+ไข่ดาว', '45.00', '1', '29b9ecd5-6e7e-11e6-a3fd-c03fd5b6f2e8', '', 'beefcdc0-5a1d-11e6-99a1-c03fd5b6f2e8', '10', '1', '', '2016-09-08 13:33:01', NULL, NULL, NULL, NULL, NULL),
('873daada-6f49-11e6-b472-c03fd5b6f2e8', '1001', 'ข้าวกระเพราหมู ไข่ดาว', '45.00', '1', '29b9ecd5-6e7e-11e6-a3fd-c03fd5b6f2e8', '', 'beefcdc0-5a1d-11e6-99a1-c03fd5b6f2e8', '10', '1', '', '2016-08-31 14:07:07', NULL, NULL, NULL, NULL, NULL),
('7c30f3d7-cc10-11e6-9d0b-02004c4f4f50', '01', '', '0.00', '', '', '', '', '', '', '', '2016-12-27 15:43:05', NULL, NULL, NULL, NULL, NULL),
('bdd3f409-cc10-11e6-9d0b-02004c4f4f50', '1007', 'ทดสอบ', '50.00', '1', '29b9ecd5-6e7e-11e6-a3fd-c03fd5b6f2e8', '', 'beefcdc0-5a1d-11e6-99a1-c03fd5b6f2e8', '10', '1', '', '2016-12-27 15:44:55', NULL, NULL, NULL, NULL, NULL),
('272b4e87-cc11-11e6-9d0b-02004c4f4f50', '1008', 'ทดสอบ2', '55.00', '1', '29b9ecd5-6e7e-11e6-a3fd-c03fd5b6f2e8', '', 'beefcdc0-5a1d-11e6-99a1-c03fd5b6f2e8', '10', '1', '', '2016-12-27 15:47:52', NULL, NULL, NULL, NULL, NULL);

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
  `date_create` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `date_modi` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `host_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `branch_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `device_id` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `b_foods_type`
--

INSERT INTO `b_foods_type` (`foods_type_id`, `foods_type_code`, `foods_type_name`, `active`, `remark`, `sort1`, `date_create`, `date_modi`, `host_id`, `branch_id`, `device_id`) VALUES
('29b9ecd5-6e7e-11e6-a3fd-c03fd5b6f2e8', '10', 'อาหารจานเดียว', '1', NULL, '100', NULL, NULL, NULL, NULL, NULL),
('29b9f510-6e7e-11e6-a3fd-c03fd5b6f2e8', '11', 'กับข้าว', '1', NULL, '101', NULL, NULL, NULL, NULL, NULL),
('6e8af84d-6e7e-11e6-a3fd-c03fd5b6f2e8', '12', 'อาหารอีสาน', '1', NULL, '102', NULL, NULL, NULL, NULL, NULL),
('6e8b0b40-6e7e-11e6-a3fd-c03fd5b6f2e8', '13', 'สุรา, เบียร์1', '1', 'null', '', NULL, NULL, NULL, NULL, NULL),
('cee2df68-6e7e-11e6-a3fd-c03fd5b6f2e8', '14', 'น้ำอัดลม', '1', NULL, '104', NULL, NULL, NULL, NULL, NULL),
('cee2eaae-6e7e-11e6-a3fd-c03fd5b6f2e8', '15', 'น้ำผลไม้', '1', NULL, '105', NULL, NULL, NULL, NULL, NULL),
('0275e3a8-6e7f-11e6-a3fd-c03fd5b6f2e8', '16', 'ขนมหวาน22', '1', '', '', NULL, NULL, NULL, NULL, NULL),
('0275ecff-6e7f-11e6-a3fd-c03fd5b6f2e8', '17', 'อาหารกลางโต๊ะ', '1', NULL, '107', NULL, NULL, NULL, NULL, NULL),
('923b1bc4-73dc-11e6-a181-c03fd5b6f2e8', '09', 'สุรา, เบียร์', '1', 'null11', '', '2016-09-06 09:49:46', NULL, NULL, NULL, NULL),
('5d9ccb22-74a3-11e6-b621-c03fd5b6f2e8', '10', 'น้ำอัดลม1', '1', 'null', '', '2016-09-07 09:32:48', NULL, NULL, NULL, NULL),
('97d10b22-cc08-11e6-9d0b-02004c4f4f50', '11', 'ทดสอบ', '1', '', '', '2016-12-27 14:46:35', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `b_printername`
--

CREATE TABLE `b_printername` (
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

CREATE TABLE `b_restaurant` (
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
('beefcdc0-5a1d-11e6-99a1-c03fd5b6f2e8', '10', 'ครัวมัยลาภaaaa', '1', '', NULL, NULL, '1', 'โทร 0892017028', '105 ซอยมัยลาภ ถนนรามอินทรา', 'Line id:@nakoyagarden', '', '000079', '12', '33333334444', '', '', '', '', '', '', '', NULL, NULL),
('beefd53a-5a1d-11e6-99a1-c03fd5b6f2e8', '11', 'อีสาน รสเด็ด', '1', 'ggggg', NULL, NULL, '0', '', '', '', '', NULL, NULL, '', '', '', '', '', '', '', '', NULL, NULL),
('4a6cd9a1-b603-11e6-9063-b827eb43d88e', '03', 'test', '1', 'ffff', '2016-11-29 07:13:13', NULL, '0', '', '', '', '', NULL, NULL, '', '', '', '', '', '', '', '', NULL, NULL),
('fb00933e-b617-11e6-9063-b827eb43d88e', '04', 'ทดสอบ', '1', 'dddddd', '2016-11-29 09:41:19', NULL, '0', 'c', 'a', 'd', 'b', '', NULL, '2222', '', '', '', '', '', '', '2', NULL, NULL),
('135ebc82-b6a1-11e6-9063-b827eb43d88e', '05', 'ooooo', '1', '', '2016-11-30 02:02:41', NULL, '0', '', '', '', '', '', NULL, '', '', '', '', '', '', '', '', NULL, NULL);

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
  `date_modi` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `status_use` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '0=default;1=use;',
  `status_togo` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '0=default;1=togo;2=inres',
  `date_use` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `host_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `branch_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `device_id` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `b_table`
--

INSERT INTO `b_table` (`table_id`, `area_id`, `table_code`, `table_name`, `active`, `remark`, `sort1`, `date_create`, `date_modi`, `status_use`, `status_togo`, `date_use`, `host_id`, `branch_id`, `device_id`) VALUES
('dd9f4e7d-5a1b-11e6-99a1-c03fd5b6f2e8', NULL, '100', 'โต๊ะ1', '1', '1', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL),
('3aed5439-5a1c-11e6-99a1-c03fd5b6f2e8', NULL, '101', 'โต๊ะ2', '1', NULL, NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL),
('3aed5e6c-5a1c-11e6-99a1-c03fd5b6f2e8', NULL, '103', 'โต๊ะ3', '1', NULL, NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL),
('6e7f4e6b-5a1c-11e6-99a1-c03fd5b6f2e8', '', '104', 'โต๊ะ4', '1', 'สสสสบ', 'null', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL),
('857880af-5a1c-11e6-99a1-c03fd5b6f2e8', NULL, '105', 'โต๊ะ5', '1', NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL),
('857893df-5a1c-11e6-99a1-c03fd5b6f2e8', '', '106', 'โต๊ะ61', '1', '', 'null', NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL),
('3a800881-cc11-11e6-9d0b-02004c4f4f50', '', '107', 'ทดสอบ', '1', '', '', '2016-12-27 15:48:24', NULL, NULL, NULL, NULL, NULL, NULL, NULL);

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
  `privilege` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '1=all privilege,2=order,3=order bill,4=order bill closeday',
  `remark` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `date_create` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `date_modi` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `sort1` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `host_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `branch_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `permission_void_bill` varchar(155) COLLATE utf8_bin DEFAULT NULL,
  `permission_void_closeday` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ttttt` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `b_user`
--

INSERT INTO `b_user` (`user_id`, `user_login`, `user_name`, `active`, `password1`, `privilege`, `remark`, `date_create`, `date_modi`, `sort1`, `host_id`, `branch_id`, `permission_void_bill`, `permission_void_closeday`, `ttttt`) VALUES
('447e331c-ad32-11e6-96c5-02004c4f4f50', 'admin', 'admin555', '1', '1618', '1', '', NULL, NULL, '', NULL, NULL, '1', '1', NULL),
('578d0600-c8e6-11e6-9d0b-02004c4f4f50', '02', 'pop', '1', '555980', '1', '', '2016-12-23 15:03:51', NULL, '', NULL, NULL, '1', '1', NULL);

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
  `void_user` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `table_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `res_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `area_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `device_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `amount` decimal(17,2) DEFAULT NULL,
  `discount` decimal(17,2) DEFAULT NULL,
  `service_charge` decimal(17,2) DEFAULT NULL,
  `vat` decimal(17,2) DEFAULT NULL,
  `total` decimal(17,2) DEFAULT NULL,
  `nettotal` decimal(17,2) DEFAULT NULL,
  `cash_receive` decimal(17,2) DEFAULT NULL,
  `cash_ton` decimal(17,2) DEFAULT NULL,
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

INSERT INTO `t_bill` (`bill_id`, `bill_code`, `bill_date`, `lot_id`, `active`, `remark`, `status_void`, `void_date`, `void_user`, `table_id`, `res_id`, `area_id`, `device_id`, `amount`, `discount`, `service_charge`, `vat`, `total`, `nettotal`, `cash_receive`, `cash_ton`, `date_create`, `date_modi`, `bill_user`, `status_closeday`, `closeday_id`, `host_id`, `branch_id`) VALUES
('ed259610-15a2-4e77-a686-e51b0736cbdf', '5912000077', '2016-12-26 14:05:02', NULL, '1', '', '0', NULL, NULL, 'dd9f4e7d-5a1b-11e6-99a1-c03fd5b6f2e8', '', 'a01f92e1-5926-11e6-bfd0-c03fd5b6f2e8', '', '95.00', '0.00', '0.00', '0.00', '95.00', '95.00', '100.00', '5.00', '2016-12-26 14:05:02', NULL, '', '0', '', NULL, NULL),
('71b1dd04-65a3-44dd-9a21-aef0b7e4b025', '5912000078', '2016-12-26 14:11:51', NULL, '1', '', '0', NULL, NULL, 'dd9f4e7d-5a1b-11e6-99a1-c03fd5b6f2e8', '', 'a01f92e1-5926-11e6-bfd0-c03fd5b6f2e8', '', '625.00', '0.00', '0.00', '0.00', '625.00', '625.00', '1000.00', '375.00', '2016-12-26 14:11:51', NULL, '', '0', '', NULL, NULL);

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
  `remark` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `host_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `branch_id` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `t_bill_detail`
--

INSERT INTO `t_bill_detail` (`bill_detail_id`, `bill_id`, `order_id`, `lot_id`, `status_void`, `row1`, `foods_id`, `foods_code`, `price`, `qty`, `amount`, `date_create`, `date_modi`, `active`, `remark`, `host_id`, `branch_id`) VALUES
('9f8e1282-cb39-11e6-9d0b-02004c4f4f50', 'ed259610-15a2-4e77-a686-e51b0736cbdf', '9074b994-cb39-11e6-9d0b-02004c4f4f50', NULL, '0', '1', '9d5480ab-758e-11e6-96a5-c03fd5b6f2e8', '1102', '50.00', '1.00', '50.00', '2016-12-26 14:05:02', NULL, '1', NULL, NULL, NULL),
('9f9542f4-cb39-11e6-9d0b-02004c4f4f50', 'ed259610-15a2-4e77-a686-e51b0736cbdf', '907bb2dc-cb39-11e6-9d0b-02004c4f4f50', NULL, '0', '3', '16d42722-758e-11e6-96a5-c03fd5b6f2e8', '1002', '45.00', '1.00', '45.00', '2016-12-26 14:05:02', NULL, '1', NULL, NULL, NULL),
('92e32758-cb3a-11e6-9d0b-02004c4f4f50', '71b1dd04-65a3-44dd-9a21-aef0b7e4b025', '8437f107-cb3a-11e6-9d0b-02004c4f4f50', NULL, '0', '1', '9d5480ab-758e-11e6-96a5-c03fd5b6f2e8', '1102', '50.00', '1.00', '50.00', '2016-12-26 14:11:51', NULL, '1', NULL, NULL, NULL),
('92e5f8f3-cb3a-11e6-9d0b-02004c4f4f50', '71b1dd04-65a3-44dd-9a21-aef0b7e4b025', '84594ad7-cb3a-11e6-9d0b-02004c4f4f50', NULL, '0', '2', '722a4860-758e-11e6-96a5-c03fd5b6f2e8', '1005', '45.00', '1.00', '45.00', '2016-12-26 14:11:51', NULL, '1', NULL, NULL, NULL),
('92e8f8df-cb3a-11e6-9d0b-02004c4f4f50', '71b1dd04-65a3-44dd-9a21-aef0b7e4b025', '845dd1cb-cb3a-11e6-9d0b-02004c4f4f50', NULL, '0', '3', '88225c62-758e-11e6-96a5-c03fd5b6f2e8', '1101', '50.00', '1.00', '50.00', '2016-12-26 14:11:51', NULL, '1', NULL, NULL, NULL),
('92ebadee-cb3a-11e6-9d0b-02004c4f4f50', '71b1dd04-65a3-44dd-9a21-aef0b7e4b025', '8462936f-cb3a-11e6-9d0b-02004c4f4f50', NULL, '0', '4', '74c833d6-7590-11e6-96a5-c03fd5b6f2e8', '1103', '50.00', '1.00', '50.00', '2016-12-26 14:11:51', NULL, '1', NULL, NULL, NULL),
('92eebd74-cb3a-11e6-9d0b-02004c4f4f50', '71b1dd04-65a3-44dd-9a21-aef0b7e4b025', '84673ff8-cb3a-11e6-9d0b-02004c4f4f50', NULL, '0', '5', '9d5480ab-758e-11e6-96a5-c03fd5b6f2e8', '1102', '50.00', '1.00', '50.00', '2016-12-26 14:11:51', NULL, '1', NULL, NULL, NULL),
('92f150a7-cb3a-11e6-9d0b-02004c4f4f50', '71b1dd04-65a3-44dd-9a21-aef0b7e4b025', '843b83d8-cb3a-11e6-9d0b-02004c4f4f50', NULL, '0', '6', 'cbfc636c-7593-11e6-96a5-c03fd5b6f2e8', '1104', '50.00', '1.00', '50.00', '2016-12-26 14:11:51', NULL, '1', NULL, NULL, NULL),
('92f3dfb7-cb3a-11e6-9d0b-02004c4f4f50', '71b1dd04-65a3-44dd-9a21-aef0b7e4b025', '843ec2e0-cb3a-11e6-9d0b-02004c4f4f50', NULL, '0', '7', '74c833d6-7590-11e6-96a5-c03fd5b6f2e8', '1103', '50.00', '1.00', '50.00', '2016-12-26 14:11:51', NULL, '1', NULL, NULL, NULL),
('92f66ef8-cb3a-11e6-9d0b-02004c4f4f50', '71b1dd04-65a3-44dd-9a21-aef0b7e4b025', '84423336-cb3a-11e6-9d0b-02004c4f4f50', NULL, '0', '8', '5debdec7-7590-11e6-96a5-c03fd5b6f2e8', '1006', '45.00', '1.00', '45.00', '2016-12-26 14:11:51', NULL, '1', NULL, NULL, NULL),
('92f8f17c-cb3a-11e6-9d0b-02004c4f4f50', '71b1dd04-65a3-44dd-9a21-aef0b7e4b025', '844760d2-cb3a-11e6-9d0b-02004c4f4f50', NULL, '0', '9', '722a4860-758e-11e6-96a5-c03fd5b6f2e8', '1005', '45.00', '1.00', '45.00', '2016-12-26 14:11:51', NULL, '1', NULL, NULL, NULL),
('92fc0bc6-cb3a-11e6-9d0b-02004c4f4f50', '71b1dd04-65a3-44dd-9a21-aef0b7e4b025', '844b28e0-cb3a-11e6-9d0b-02004c4f4f50', NULL, '0', '10', '88225c62-758e-11e6-96a5-c03fd5b6f2e8', '1101', '50.00', '1.00', '50.00', '2016-12-26 14:11:51', NULL, '1', NULL, NULL, NULL),
('93030319-cb3a-11e6-9d0b-02004c4f4f50', '71b1dd04-65a3-44dd-9a21-aef0b7e4b025', '844e5c57-cb3a-11e6-9d0b-02004c4f4f50', NULL, '0', '11', '88225c62-758e-11e6-96a5-c03fd5b6f2e8', '1101', '50.00', '1.00', '50.00', '2016-12-26 14:11:51', NULL, '1', NULL, NULL, NULL),
('9309e21e-cb3a-11e6-9d0b-02004c4f4f50', '71b1dd04-65a3-44dd-9a21-aef0b7e4b025', '8451d055-cb3a-11e6-9d0b-02004c4f4f50', NULL, '0', '12', '16d42722-758e-11e6-96a5-c03fd5b6f2e8', '1002', '45.00', '1.00', '45.00', '2016-12-26 14:11:51', NULL, '1', NULL, NULL, NULL),
('93127329-cb3a-11e6-9d0b-02004c4f4f50', '71b1dd04-65a3-44dd-9a21-aef0b7e4b025', '84555ab7-cb3a-11e6-9d0b-02004c4f4f50', NULL, '0', '13', '16d42722-758e-11e6-96a5-c03fd5b6f2e8', '1001', '45.00', '1.00', '45.00', '2016-12-26 14:11:51', NULL, '1', NULL, NULL, NULL),
('ab58eb56-ccce-11e6-9d0b-02004c4f4f50', '13e930c7-d296-4d35-ab4d-b2ac24d3578b', '9f89c3bb-cccd-11e6-9d0b-02004c4f4f50', NULL, '0', '1', '88225c62-758e-11e6-96a5-c03fd5b6f2e8', '1101', '50.00', '1.00', '50.00', '2016-12-28 14:24:28', NULL, '1', NULL, NULL, NULL),
('ab5cfa52-ccce-11e6-9d0b-02004c4f4f50', '13e930c7-d296-4d35-ab4d-b2ac24d3578b', '7732e6b3-cccd-11e6-9d0b-02004c4f4f50', NULL, '0', '2', '88225c62-758e-11e6-96a5-c03fd5b6f2e8', '1101', '50.00', '1.00', '50.00', '2016-12-28 14:24:28', NULL, '1', NULL, NULL, NULL),
('ab60652f-ccce-11e6-9d0b-02004c4f4f50', '13e930c7-d296-4d35-ab4d-b2ac24d3578b', 'a147a502-ccce-11e6-9d0b-02004c4f4f50', NULL, '0', '3', '88225c62-758e-11e6-96a5-c03fd5b6f2e8', '1101', '50.00', '1.00', '50.00', '2016-12-28 14:24:28', NULL, '1', NULL, NULL, NULL),
('ab63251d-ccce-11e6-9d0b-02004c4f4f50', '13e930c7-d296-4d35-ab4d-b2ac24d3578b', 'a14e0e2b-ccce-11e6-9d0b-02004c4f4f50', NULL, '0', '4', '5debdec7-7590-11e6-96a5-c03fd5b6f2e8', '1006', '45.00', '1.00', '45.00', '2016-12-28 14:24:28', NULL, '1', NULL, NULL, NULL),
('ab669847-ccce-11e6-9d0b-02004c4f4f50', '13e930c7-d296-4d35-ab4d-b2ac24d3578b', '3317a525-ccbb-11e6-9d0b-02004c4f4f50', NULL, '0', '5', '9d5480ab-758e-11e6-96a5-c03fd5b6f2e8', '1102', '50.00', '1.00', '50.00', '2016-12-28 14:24:28', NULL, '1', NULL, NULL, NULL),
('ab6dd514-ccce-11e6-9d0b-02004c4f4f50', '13e930c7-d296-4d35-ab4d-b2ac24d3578b', '332bdd80-ccbb-11e6-9d0b-02004c4f4f50', NULL, '0', '6', '296650f5-758e-11e6-96a5-c03fd5b6f2e8', '1003', '45.00', '1.00', '45.00', '2016-12-28 14:24:28', NULL, '1', NULL, NULL, NULL),
('ab70f366-ccce-11e6-9d0b-02004c4f4f50', '13e930c7-d296-4d35-ab4d-b2ac24d3578b', '3339e9b8-ccbb-11e6-9d0b-02004c4f4f50', NULL, '0', '7', '873daada-6f49-11e6-b472-c03fd5b6f2e8', '1001', '45.00', '1.00', '45.00', '2016-12-28 14:24:28', NULL, '1', NULL, NULL, NULL),
('ab739808-ccce-11e6-9d0b-02004c4f4f50', '13e930c7-d296-4d35-ab4d-b2ac24d3578b', '3510323e-ccbb-11e6-9d0b-02004c4f4f50', NULL, '0', '8', '9d5480ab-758e-11e6-96a5-c03fd5b6f2e8', '1102', '50.00', '1.00', '50.00', '2016-12-28 14:24:28', NULL, '1', NULL, NULL, NULL),
('ab7626f1-ccce-11e6-9d0b-02004c4f4f50', '13e930c7-d296-4d35-ab4d-b2ac24d3578b', '3516b15c-ccbb-11e6-9d0b-02004c4f4f50', NULL, '0', '9', '296650f5-758e-11e6-96a5-c03fd5b6f2e8', '1003', '45.00', '1.00', '45.00', '2016-12-28 14:24:28', NULL, '1', NULL, NULL, NULL),
('ab793d14-ccce-11e6-9d0b-02004c4f4f50', '13e930c7-d296-4d35-ab4d-b2ac24d3578b', '351b0785-ccbb-11e6-9d0b-02004c4f4f50', NULL, '0', '10', '873daada-6f49-11e6-b472-c03fd5b6f2e8', '1001', '45.00', '1.00', '45.00', '2016-12-28 14:24:28', NULL, '1', NULL, NULL, NULL),
('ab7caa7d-ccce-11e6-9d0b-02004c4f4f50', '13e930c7-d296-4d35-ab4d-b2ac24d3578b', '33983d3d-ccbb-11e6-9d0b-02004c4f4f50', NULL, '0', '11', '9d5480ab-758e-11e6-96a5-c03fd5b6f2e8', '1102', '50.00', '1.00', '50.00', '2016-12-28 14:24:28', NULL, '1', NULL, NULL, NULL),
('ab81ef7b-ccce-11e6-9d0b-02004c4f4f50', '13e930c7-d296-4d35-ab4d-b2ac24d3578b', '339d7927-ccbb-11e6-9d0b-02004c4f4f50', NULL, '0', '12', '296650f5-758e-11e6-96a5-c03fd5b6f2e8', '1003', '45.00', '1.00', '45.00', '2016-12-28 14:24:29', NULL, '1', NULL, NULL, NULL),
('ab876db9-ccce-11e6-9d0b-02004c4f4f50', '13e930c7-d296-4d35-ab4d-b2ac24d3578b', '33a24d8e-ccbb-11e6-9d0b-02004c4f4f50', NULL, '0', '13', '873daada-6f49-11e6-b472-c03fd5b6f2e8', '1001', '45.00', '1.00', '45.00', '2016-12-28 14:24:29', NULL, '1', NULL, NULL, NULL),
('ab8be25a-ccce-11e6-9d0b-02004c4f4f50', '13e930c7-d296-4d35-ab4d-b2ac24d3578b', '62a6176d-ccce-11e6-9d0b-02004c4f4f50', NULL, '0', '14', '88225c62-758e-11e6-96a5-c03fd5b6f2e8', '1101', '50.00', '1.00', '50.00', '2016-12-28 14:24:29', NULL, '1', NULL, NULL, NULL),
('ab90f446-ccce-11e6-9d0b-02004c4f4f50', '13e930c7-d296-4d35-ab4d-b2ac24d3578b', '2291994b-ccce-11e6-9d0b-02004c4f4f50', NULL, '0', '15', '88225c62-758e-11e6-96a5-c03fd5b6f2e8', '1101', '50.00', '1.00', '50.00', '2016-12-28 14:24:29', NULL, '1', NULL, NULL, NULL),
('ab973097-ccce-11e6-9d0b-02004c4f4f50', '13e930c7-d296-4d35-ab4d-b2ac24d3578b', '2ffb4dbf-ccbb-11e6-9d0b-02004c4f4f50', NULL, '0', '16', '9d5480ab-758e-11e6-96a5-c03fd5b6f2e8', '1102', '50.00', '1.00', '50.00', '2016-12-28 14:24:29', NULL, '1', NULL, NULL, NULL),
('ab9dcc92-ccce-11e6-9d0b-02004c4f4f50', '13e930c7-d296-4d35-ab4d-b2ac24d3578b', '302e4e81-ccbb-11e6-9d0b-02004c4f4f50', NULL, '0', '17', '296650f5-758e-11e6-96a5-c03fd5b6f2e8', '1003', '45.00', '1.00', '45.00', '2016-12-28 14:24:29', NULL, '1', NULL, NULL, NULL),
('aba1a7c7-ccce-11e6-9d0b-02004c4f4f50', '13e930c7-d296-4d35-ab4d-b2ac24d3578b', '3035e2a2-ccbb-11e6-9d0b-02004c4f4f50', NULL, '0', '18', '873daada-6f49-11e6-b472-c03fd5b6f2e8', '1001', '45.00', '1.00', '45.00', '2016-12-28 14:24:29', NULL, '1', NULL, NULL, NULL),
('aba56ee3-ccce-11e6-9d0b-02004c4f4f50', '13e930c7-d296-4d35-ab4d-b2ac24d3578b', '2d802c56-ccbb-11e6-9d0b-02004c4f4f50', NULL, '0', '19', '9d5480ab-758e-11e6-96a5-c03fd5b6f2e8', '1102', '50.00', '1.00', '50.00', '2016-12-28 14:24:29', NULL, '1', NULL, NULL, NULL),
('aba87802-ccce-11e6-9d0b-02004c4f4f50', '13e930c7-d296-4d35-ab4d-b2ac24d3578b', '2d8ff019-ccbb-11e6-9d0b-02004c4f4f50', NULL, '0', '20', '296650f5-758e-11e6-96a5-c03fd5b6f2e8', '1003', '45.00', '1.00', '45.00', '2016-12-28 14:24:29', NULL, '1', NULL, NULL, NULL),
('ababcfad-ccce-11e6-9d0b-02004c4f4f50', '13e930c7-d296-4d35-ab4d-b2ac24d3578b', '2d97a1c2-ccbb-11e6-9d0b-02004c4f4f50', NULL, '0', '21', '873daada-6f49-11e6-b472-c03fd5b6f2e8', '1001', '45.00', '1.00', '45.00', '2016-12-28 14:24:29', NULL, '1', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `t_closeday`
--

CREATE TABLE `t_closeday` (
  `closeday_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `closeday_date` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `res_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `amount` decimal(17,2) DEFAULT NULL,
  `discount` decimal(17,2) DEFAULT NULL,
  `total` decimal(17,2) DEFAULT NULL,
  `service_charge` decimal(17,2) DEFAULT NULL,
  `vat` decimal(17,2) DEFAULT NULL,
  `nettotal` decimal(17,2) DEFAULT NULL,
  `remark` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `active` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `status_void` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `void_date` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `void_user` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `cnt_bill` decimal(17,2) DEFAULT NULL,
  `bill_amount` decimal(17,2) DEFAULT NULL,
  `cnt_order` decimal(17,2) DEFAULT NULL,
  `amount_order` decimal(17,2) DEFAULT NULL,
  `closeday_user` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `cash_receive1` decimal(17,2) DEFAULT NULL,
  `cash_receive2` decimal(17,2) DEFAULT NULL,
  `cash_receive3` decimal(17,2) DEFAULT NULL,
  `cash_draw1` decimal(17,2) DEFAULT NULL,
  `cash_draw2` decimal(17,2) DEFAULT NULL,
  `cash_draw3` decimal(17,2) DEFAULT NULL,
  `cash_receive1_remark` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `cash_receive2_remark` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `cash_receive3_remark` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `cash_draw1_remark` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `cash_draw2_remark` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `cash_draw3_remark` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `host_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `branch_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `device_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `weather` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '0=default;1=sun;2='
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `t_closeday`
--

INSERT INTO `t_closeday` (`closeday_id`, `closeday_date`, `res_id`, `amount`, `discount`, `total`, `service_charge`, `vat`, `nettotal`, `remark`, `active`, `status_void`, `void_date`, `void_user`, `cnt_bill`, `bill_amount`, `cnt_order`, `amount_order`, `closeday_user`, `cash_receive1`, `cash_receive2`, `cash_receive3`, `cash_draw1`, `cash_draw2`, `cash_draw3`, `cash_receive1_remark`, `cash_receive2_remark`, `cash_receive3_remark`, `cash_draw1_remark`, `cash_draw2_remark`, `cash_draw3_remark`, `host_id`, `branch_id`, `device_id`, `weather`) VALUES
('2750bdc2-ec25-45ac-b7b9-1b3b4cc137ed', '2016-12-21 11:46:03', 'beefcdc0-5a1d-11e6-99a1-c03fd5b6f2e8', '140.00', '0.00', '140.00', '0.00', '0.00', '140.00', '', '1', '0', '', '', '3.00', '140.00', '0.00', '0.00', '1618', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '', '', '', '', '', '', NULL, NULL, NULL, NULL);

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
  `bill_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `order_user` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `status_closeday` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `closeday_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `host_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `branch_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `cnt_cust` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `t_order`
--

INSERT INTO `t_order` (`order_id`, `lot_id`, `row1`, `foods_id`, `foods_code`, `foods_name`, `order_date`, `price`, `qty`, `remark`, `table_code`, `device_id`, `res_code`, `area_code`, `status_foods_1`, `status_foods_2`, `status_foods_3`, `status_bill`, `bill_check_date`, `status_cook`, `cook_receive_date`, `cook_finish_date`, `active`, `void_date`, `status_void`, `printer_name`, `date_create`, `date_modi`, `status_to_go`, `bill_id`, `order_user`, `status_closeday`, `closeday_id`, `host_id`, `branch_id`, `cnt_cust`) VALUES
('9074b994-cb39-11e6-9d0b-02004c4f4f50', 'd8c4f567-9767-4c48-b49b-c8cd1eb27356', '1', '9d5480ab-758e-11e6-96a5-c03fd5b6f2e8', '1102', 'แกงเขียวหวาน', '2016-12-26 14:04:37', '50', '1', '', '100', NULL, '10', '1000', '', '', '', '2', '2016-12-26 14:05:02', '0', NULL, NULL, '1', NULL, '0', '', '2016-12-26 14:04:37', NULL, '1', 'ed259610-15a2-4e77-a686-e51b0736cbdf', '447e331c-ad32-11e6-96c5-02004c4f4f50', '0', '', NULL, NULL, NULL),
('90784e5d-cb39-11e6-9d0b-02004c4f4f50', 'd8c4f567-9767-4c48-b49b-c8cd1eb27356', '2', '88225c62-758e-11e6-96a5-c03fd5b6f2e8', '1101', 'แกงส้มกุ้ง', '2016-12-26 14:04:37', '50', '1', '', '100', NULL, '10', '1000', '', '', '', '2', '2016-12-26 14:05:02', '0', NULL, NULL, '1', '2016-12-26 14:05:02', '1', '', '2016-12-26 14:04:37', NULL, '1', 'ed259610-15a2-4e77-a686-e51b0736cbdf', '447e331c-ad32-11e6-96c5-02004c4f4f50', '0', '', NULL, NULL, NULL),
('907bb2dc-cb39-11e6-9d0b-02004c4f4f50', 'd8c4f567-9767-4c48-b49b-c8cd1eb27356', '3', '16d42722-758e-11e6-96a5-c03fd5b6f2e8', '1002', 'ข้าวกระเพราเนื้อ+ไข่ดาว', '2016-12-26 14:04:37', '45', '1', '', '100', NULL, '10', '1000', '', '', '', '2', '2016-12-26 14:05:02', '0', NULL, NULL, '1', NULL, '0', '', '2016-12-26 14:04:37', NULL, '1', 'ed259610-15a2-4e77-a686-e51b0736cbdf', '447e331c-ad32-11e6-96c5-02004c4f4f50', '0', '', NULL, NULL, NULL),
('8437f107-cb3a-11e6-9d0b-02004c4f4f50', 'a3ffdf96-2598-4b30-a59f-86ad5976e770', '1', '9d5480ab-758e-11e6-96a5-c03fd5b6f2e8', '1102', 'แกงเขียวหวาน', '2016-12-26 14:11:26', '50', '1', '', '100', NULL, '10', '1000', '', '', '', '2', '2016-12-26 14:11:51', '0', NULL, NULL, '1', NULL, '0', '', '2016-12-26 14:11:26', NULL, '1', '71b1dd04-65a3-44dd-9a21-aef0b7e4b025', '447e331c-ad32-11e6-96c5-02004c4f4f50', '0', '', NULL, NULL, NULL),
('843b83d8-cb3a-11e6-9d0b-02004c4f4f50', 'a3ffdf96-2598-4b30-a59f-86ad5976e770', '2', 'cbfc636c-7593-11e6-96a5-c03fd5b6f2e8', '1104', 'คะน้าปลาเค็ม', '2016-12-26 14:11:26', '50', '1', '', '100', NULL, '10', '1000', '', '', '', '2', '2016-12-26 14:11:51', '0', NULL, NULL, '1', NULL, '0', '', '2016-12-26 14:11:26', NULL, '1', '71b1dd04-65a3-44dd-9a21-aef0b7e4b025', '447e331c-ad32-11e6-96c5-02004c4f4f50', '0', '', NULL, NULL, NULL),
('843ec2e0-cb3a-11e6-9d0b-02004c4f4f50', 'a3ffdf96-2598-4b30-a59f-86ad5976e770', '3', '74c833d6-7590-11e6-96a5-c03fd5b6f2e8', '1103', 'คะน้าหมูกรอบ', '2016-12-26 14:11:26', '50', '1', '', '100', NULL, '10', '1000', '', '', '', '2', '2016-12-26 14:11:51', '0', NULL, NULL, '1', NULL, '0', '', '2016-12-26 14:11:26', NULL, '1', '71b1dd04-65a3-44dd-9a21-aef0b7e4b025', '447e331c-ad32-11e6-96c5-02004c4f4f50', '0', '', NULL, NULL, NULL),
('84423336-cb3a-11e6-9d0b-02004c4f4f50', 'a3ffdf96-2598-4b30-a59f-86ad5976e770', '4', '5debdec7-7590-11e6-96a5-c03fd5b6f2e8', '1006', 'ผัดซีอิวหมู', '2016-12-26 14:11:26', '45', '1', '', '100', NULL, '10', '1000', '', '', '', '2', '2016-12-26 14:11:51', '0', NULL, NULL, '1', NULL, '0', '', '2016-12-26 14:11:26', NULL, '1', '71b1dd04-65a3-44dd-9a21-aef0b7e4b025', '447e331c-ad32-11e6-96c5-02004c4f4f50', '0', '', NULL, NULL, NULL),
('844760d2-cb3a-11e6-9d0b-02004c4f4f50', 'a3ffdf96-2598-4b30-a59f-86ad5976e770', '5', '722a4860-758e-11e6-96a5-c03fd5b6f2e8', '1005', 'ข้าวกระเพราไก่+ไข่ดาว', '2016-12-26 14:11:26', '45', '1', '', '100', NULL, '10', '1000', '', '', '', '2', '2016-12-26 14:11:51', '0', NULL, NULL, '1', NULL, '0', '', '2016-12-26 14:11:26', NULL, '1', '71b1dd04-65a3-44dd-9a21-aef0b7e4b025', '447e331c-ad32-11e6-96c5-02004c4f4f50', '0', '', NULL, NULL, NULL),
('844b28e0-cb3a-11e6-9d0b-02004c4f4f50', 'a3ffdf96-2598-4b30-a59f-86ad5976e770', '6', '88225c62-758e-11e6-96a5-c03fd5b6f2e8', '1101', 'แกงส้มกุ้ง', '2016-12-26 14:11:26', '50', '1', '', '100', NULL, '10', '1000', '', '', '', '2', '2016-12-26 14:11:51', '0', NULL, NULL, '1', NULL, '0', '', '2016-12-26 14:11:26', NULL, '1', '71b1dd04-65a3-44dd-9a21-aef0b7e4b025', '447e331c-ad32-11e6-96c5-02004c4f4f50', '0', '', NULL, NULL, NULL),
('844e5c57-cb3a-11e6-9d0b-02004c4f4f50', 'a3ffdf96-2598-4b30-a59f-86ad5976e770', '7', '88225c62-758e-11e6-96a5-c03fd5b6f2e8', '1101', 'แกงส้มกุ้ง', '2016-12-26 14:11:26', '50', '1', '', '100', NULL, '10', '1000', '', '', '', '2', '2016-12-26 14:11:51', '0', NULL, NULL, '1', NULL, '0', '', '2016-12-26 14:11:26', NULL, '1', '71b1dd04-65a3-44dd-9a21-aef0b7e4b025', '447e331c-ad32-11e6-96c5-02004c4f4f50', '0', '', NULL, NULL, NULL),
('8451d055-cb3a-11e6-9d0b-02004c4f4f50', 'a3ffdf96-2598-4b30-a59f-86ad5976e770', '8', '16d42722-758e-11e6-96a5-c03fd5b6f2e8', '1002', 'ข้าวกระเพราเนื้อ+ไข่ดาว', '2016-12-26 14:11:26', '45', '1', '', '100', NULL, '10', '1000', '', '', '', '2', '2016-12-26 14:11:51', '0', NULL, NULL, '1', NULL, '0', '', '2016-12-26 14:11:26', NULL, '1', '71b1dd04-65a3-44dd-9a21-aef0b7e4b025', '447e331c-ad32-11e6-96c5-02004c4f4f50', '0', '', NULL, NULL, NULL),
('84555ab7-cb3a-11e6-9d0b-02004c4f4f50', 'a3ffdf96-2598-4b30-a59f-86ad5976e770', '9', '16d42722-758e-11e6-96a5-c03fd5b6f2e8', '1001', 'ข้าวกระเพราหมู ไข่ดาว', '2016-12-26 14:11:26', '45', '1', '', '100', NULL, '10', '1000', '', '', '', '2', '2016-12-26 14:11:51', '0', NULL, NULL, '1', NULL, '0', '', '2016-12-26 14:11:26', NULL, '1', '71b1dd04-65a3-44dd-9a21-aef0b7e4b025', '447e331c-ad32-11e6-96c5-02004c4f4f50', '0', '', NULL, NULL, NULL),
('84594ad7-cb3a-11e6-9d0b-02004c4f4f50', 'a3ffdf96-2598-4b30-a59f-86ad5976e770', '10', '722a4860-758e-11e6-96a5-c03fd5b6f2e8', '1005', 'ข้าวกระเพราไก่+ไข่ดาว', '2016-12-26 14:11:26', '45', '1', '', '100', NULL, '10', '1000', '', '', '', '2', '2016-12-26 14:11:51', '0', NULL, NULL, '1', NULL, '0', '', '2016-12-26 14:11:26', NULL, '1', '71b1dd04-65a3-44dd-9a21-aef0b7e4b025', '447e331c-ad32-11e6-96c5-02004c4f4f50', '0', '', NULL, NULL, NULL),
('845dd1cb-cb3a-11e6-9d0b-02004c4f4f50', 'a3ffdf96-2598-4b30-a59f-86ad5976e770', '11', '88225c62-758e-11e6-96a5-c03fd5b6f2e8', '1101', 'แกงส้มกุ้ง', '2016-12-26 14:11:26', '50', '1', '', '100', NULL, '10', '1000', '', '', '', '2', '2016-12-26 14:11:51', '0', NULL, NULL, '1', NULL, '0', '', '2016-12-26 14:11:26', NULL, '1', '71b1dd04-65a3-44dd-9a21-aef0b7e4b025', '447e331c-ad32-11e6-96c5-02004c4f4f50', '0', '', NULL, NULL, NULL),
('8462936f-cb3a-11e6-9d0b-02004c4f4f50', 'a3ffdf96-2598-4b30-a59f-86ad5976e770', '12', '74c833d6-7590-11e6-96a5-c03fd5b6f2e8', '1103', 'คะน้าหมูกรอบ', '2016-12-26 14:11:26', '50', '1', '', '100', NULL, '10', '1000', '', '', '', '2', '2016-12-26 14:11:51', '0', NULL, NULL, '1', NULL, '0', '', '2016-12-26 14:11:26', NULL, '1', '71b1dd04-65a3-44dd-9a21-aef0b7e4b025', '447e331c-ad32-11e6-96c5-02004c4f4f50', '0', '', NULL, NULL, NULL),
('84673ff8-cb3a-11e6-9d0b-02004c4f4f50', 'a3ffdf96-2598-4b30-a59f-86ad5976e770', '13', '9d5480ab-758e-11e6-96a5-c03fd5b6f2e8', '1102', 'แกงเขียวหวาน', '2016-12-26 14:11:26', '50', '1', '', '100', NULL, '10', '1000', '', '', '', '2', '2016-12-26 14:11:51', '0', NULL, NULL, '1', NULL, '0', '', '2016-12-26 14:11:26', NULL, '1', '71b1dd04-65a3-44dd-9a21-aef0b7e4b025', '447e331c-ad32-11e6-96c5-02004c4f4f50', '0', '', NULL, NULL, NULL),
('2d802c56-ccbb-11e6-9d0b-02004c4f4f50', 'f4368aae-e2b6-4292-ac81-d8b893e065e9', '1', '9d5480ab-758e-11e6-96a5-c03fd5b6f2e8', '1102', 'แกงเขียวหวาน', '2016-12-28 12:04:57', '50', '1', '', '100', NULL, '10', '1000', '', '', '', '2', '2016-12-28 14:24:29', '0', NULL, NULL, '1', NULL, '0', '', '2016-12-28 12:04:57', NULL, '1', '13e930c7-d296-4d35-ab4d-b2ac24d3578b', '447e331c-ad32-11e6-96c5-02004c4f4f50', '0', '', NULL, NULL, ''),
('2d8ff019-ccbb-11e6-9d0b-02004c4f4f50', 'f4368aae-e2b6-4292-ac81-d8b893e065e9', '2', '296650f5-758e-11e6-96a5-c03fd5b6f2e8', '1003', 'ข้าวคะน้าหมูกรอบ', '2016-12-28 12:04:57', '45', '1', '', '100', NULL, '10', '1000', '', '', '', '2', '2016-12-28 14:24:29', '0', NULL, NULL, '1', NULL, '0', '', '2016-12-28 12:04:57', NULL, '1', '13e930c7-d296-4d35-ab4d-b2ac24d3578b', '447e331c-ad32-11e6-96c5-02004c4f4f50', '0', '', NULL, NULL, ''),
('2d97a1c2-ccbb-11e6-9d0b-02004c4f4f50', 'f4368aae-e2b6-4292-ac81-d8b893e065e9', '3', '873daada-6f49-11e6-b472-c03fd5b6f2e8', '1001', 'ข้าวกระเพราหมู ไข่ดาว', '2016-12-28 12:04:57', '45', '1', '', '100', NULL, '10', '1000', '', '', '', '2', '2016-12-28 14:24:29', '0', NULL, NULL, '1', NULL, '0', '', '2016-12-28 12:04:57', NULL, '1', '13e930c7-d296-4d35-ab4d-b2ac24d3578b', '447e331c-ad32-11e6-96c5-02004c4f4f50', '0', '', NULL, NULL, ''),
('2ffb4dbf-ccbb-11e6-9d0b-02004c4f4f50', 'd602b335-588f-41cb-a3c4-2cce2d027e0b', '1', '9d5480ab-758e-11e6-96a5-c03fd5b6f2e8', '1102', 'แกงเขียวหวาน', '2016-12-28 12:05:01', '50', '1', '-', '100', NULL, '10', '1000', '', '', '', '2', '2016-12-28 14:24:29', '0', NULL, NULL, '1', NULL, '0', '', '2016-12-28 12:05:01', NULL, '1', '13e930c7-d296-4d35-ab4d-b2ac24d3578b', '447e331c-ad32-11e6-96c5-02004c4f4f50', '0', '', NULL, NULL, ''),
('302e4e81-ccbb-11e6-9d0b-02004c4f4f50', 'd602b335-588f-41cb-a3c4-2cce2d027e0b', '2', '296650f5-758e-11e6-96a5-c03fd5b6f2e8', '1003', 'ข้าวคะน้าหมูกรอบ', '2016-12-28 12:05:01', '45', '1', '-', '100', NULL, '10', '1000', '', '', '', '2', '2016-12-28 14:24:29', '0', NULL, NULL, '1', NULL, '0', '', '2016-12-28 12:05:01', NULL, '1', '13e930c7-d296-4d35-ab4d-b2ac24d3578b', '447e331c-ad32-11e6-96c5-02004c4f4f50', '0', '', NULL, NULL, ''),
('3035e2a2-ccbb-11e6-9d0b-02004c4f4f50', 'd602b335-588f-41cb-a3c4-2cce2d027e0b', '3', '873daada-6f49-11e6-b472-c03fd5b6f2e8', '1001', 'ข้าวกระเพราหมู ไข่ดาว', '2016-12-28 12:05:01', '45', '1', '-', '100', NULL, '10', '1000', '', '', '', '2', '2016-12-28 14:24:29', '0', NULL, NULL, '1', NULL, '0', '', '2016-12-28 12:05:01', NULL, '1', '13e930c7-d296-4d35-ab4d-b2ac24d3578b', '447e331c-ad32-11e6-96c5-02004c4f4f50', '0', '', NULL, NULL, ''),
('3317a525-ccbb-11e6-9d0b-02004c4f4f50', '35eed953-bf90-404a-9ea3-1f9e6ba7c482', '1', '9d5480ab-758e-11e6-96a5-c03fd5b6f2e8', '1102', 'แกงเขียวหวาน', '2016-12-28 12:05:06', '50', '1', '-', '100', NULL, '10', '1000', '', '', '', '2', '2016-12-28 14:24:28', '0', NULL, NULL, '1', NULL, '0', '', '2016-12-28 12:05:06', NULL, '1', '13e930c7-d296-4d35-ab4d-b2ac24d3578b', '447e331c-ad32-11e6-96c5-02004c4f4f50', '0', '', NULL, NULL, ''),
('332bdd80-ccbb-11e6-9d0b-02004c4f4f50', '35eed953-bf90-404a-9ea3-1f9e6ba7c482', '2', '296650f5-758e-11e6-96a5-c03fd5b6f2e8', '1003', 'ข้าวคะน้าหมูกรอบ', '2016-12-28 12:05:06', '45', '1', '-', '100', NULL, '10', '1000', '', '', '', '2', '2016-12-28 14:24:28', '0', NULL, NULL, '1', NULL, '0', '', '2016-12-28 12:05:06', NULL, '1', '13e930c7-d296-4d35-ab4d-b2ac24d3578b', '447e331c-ad32-11e6-96c5-02004c4f4f50', '0', '', NULL, NULL, ''),
('3339e9b8-ccbb-11e6-9d0b-02004c4f4f50', '35eed953-bf90-404a-9ea3-1f9e6ba7c482', '3', '873daada-6f49-11e6-b472-c03fd5b6f2e8', '1001', 'ข้าวกระเพราหมู ไข่ดาว', '2016-12-28 12:05:06', '45', '1', '-', '100', NULL, '10', '1000', '', '', '', '2', '2016-12-28 14:24:28', '0', NULL, NULL, '1', NULL, '0', '', '2016-12-28 12:05:06', NULL, '1', '13e930c7-d296-4d35-ab4d-b2ac24d3578b', '447e331c-ad32-11e6-96c5-02004c4f4f50', '0', '', NULL, NULL, ''),
('33983d3d-ccbb-11e6-9d0b-02004c4f4f50', '7e35e23b-bea7-43b3-965e-7e26d0e6789f', '1', '9d5480ab-758e-11e6-96a5-c03fd5b6f2e8', '1102', 'แกงเขียวหวาน', '2016-12-28 12:05:07', '50', '1', '-', '100', NULL, '10', '1000', '', '', '', '2', '2016-12-28 14:24:28', '0', NULL, NULL, '1', NULL, '0', '', '2016-12-28 12:05:07', NULL, '1', '13e930c7-d296-4d35-ab4d-b2ac24d3578b', '447e331c-ad32-11e6-96c5-02004c4f4f50', '0', '', NULL, NULL, ''),
('339d7927-ccbb-11e6-9d0b-02004c4f4f50', '7e35e23b-bea7-43b3-965e-7e26d0e6789f', '2', '296650f5-758e-11e6-96a5-c03fd5b6f2e8', '1003', 'ข้าวคะน้าหมูกรอบ', '2016-12-28 12:05:07', '45', '1', '-', '100', NULL, '10', '1000', '', '', '', '2', '2016-12-28 14:24:29', '0', NULL, NULL, '1', NULL, '0', '', '2016-12-28 12:05:07', NULL, '1', '13e930c7-d296-4d35-ab4d-b2ac24d3578b', '447e331c-ad32-11e6-96c5-02004c4f4f50', '0', '', NULL, NULL, ''),
('33a24d8e-ccbb-11e6-9d0b-02004c4f4f50', '7e35e23b-bea7-43b3-965e-7e26d0e6789f', '3', '873daada-6f49-11e6-b472-c03fd5b6f2e8', '1001', 'ข้าวกระเพราหมู ไข่ดาว', '2016-12-28 12:05:07', '45', '1', '-', '100', NULL, '10', '1000', '', '', '', '2', '2016-12-28 14:24:29', '0', NULL, NULL, '1', NULL, '0', '', '2016-12-28 12:05:07', NULL, '1', '13e930c7-d296-4d35-ab4d-b2ac24d3578b', '447e331c-ad32-11e6-96c5-02004c4f4f50', '0', '', NULL, NULL, ''),
('3510323e-ccbb-11e6-9d0b-02004c4f4f50', '54d2c6a7-9473-4004-843b-d47158159086', '1', '9d5480ab-758e-11e6-96a5-c03fd5b6f2e8', '1102', 'แกงเขียวหวาน', '2016-12-28 12:05:09', '50', '1', '-', '100', NULL, '10', '1000', '', '', '', '2', '2016-12-28 14:24:28', '0', NULL, NULL, '1', NULL, '0', '', '2016-12-28 12:05:09', NULL, '1', '13e930c7-d296-4d35-ab4d-b2ac24d3578b', '447e331c-ad32-11e6-96c5-02004c4f4f50', '0', '', NULL, NULL, ''),
('3516b15c-ccbb-11e6-9d0b-02004c4f4f50', '54d2c6a7-9473-4004-843b-d47158159086', '2', '296650f5-758e-11e6-96a5-c03fd5b6f2e8', '1003', 'ข้าวคะน้าหมูกรอบ', '2016-12-28 12:05:09', '45', '1', '-', '100', NULL, '10', '1000', '', '', '', '2', '2016-12-28 14:24:28', '0', NULL, NULL, '1', NULL, '0', '', '2016-12-28 12:05:09', NULL, '1', '13e930c7-d296-4d35-ab4d-b2ac24d3578b', '447e331c-ad32-11e6-96c5-02004c4f4f50', '0', '', NULL, NULL, ''),
('351b0785-ccbb-11e6-9d0b-02004c4f4f50', '54d2c6a7-9473-4004-843b-d47158159086', '3', '873daada-6f49-11e6-b472-c03fd5b6f2e8', '1001', 'ข้าวกระเพราหมู ไข่ดาว', '2016-12-28 12:05:09', '45', '1', '-', '100', NULL, '10', '1000', '', '', '', '2', '2016-12-28 14:24:28', '0', NULL, NULL, '1', NULL, '0', '', '2016-12-28 12:05:09', NULL, '1', '13e930c7-d296-4d35-ab4d-b2ac24d3578b', '447e331c-ad32-11e6-96c5-02004c4f4f50', '0', '', NULL, NULL, ''),
('7732e6b3-cccd-11e6-9d0b-02004c4f4f50', '2c546248-00bd-4ddb-b91c-ff460d2a8dd1', '1', '88225c62-758e-11e6-96a5-c03fd5b6f2e8', '1101', 'แกงส้มกุ้ง', '2016-12-28 14:15:51', '50', '1', '', '100', NULL, '10', '1000', '', '', '', '2', '2016-12-28 14:24:28', '0', NULL, NULL, '1', NULL, '0', '', '2016-12-28 14:15:51', NULL, '1', '13e930c7-d296-4d35-ab4d-b2ac24d3578b', '447e331c-ad32-11e6-96c5-02004c4f4f50', '0', '', NULL, NULL, ''),
('9f89c3bb-cccd-11e6-9d0b-02004c4f4f50', '2a1fd417-eb8b-470d-bdd6-47b17df6865e', '1', '88225c62-758e-11e6-96a5-c03fd5b6f2e8', '1101', 'แกงส้มกุ้ง', '2016-12-28 14:16:59', '50', '1', '', '100', NULL, '10', '1000', '', '', '', '2', '2016-12-28 14:24:28', '0', NULL, NULL, '1', NULL, '0', '', '2016-12-28 14:16:59', NULL, '1', '13e930c7-d296-4d35-ab4d-b2ac24d3578b', '447e331c-ad32-11e6-96c5-02004c4f4f50', '0', '', NULL, NULL, ''),
('2291994b-ccce-11e6-9d0b-02004c4f4f50', 'd0946f50-b4ff-4dbd-9eb2-3b61fecb5f1a', '1', '88225c62-758e-11e6-96a5-c03fd5b6f2e8', '1101', 'แกงส้มกุ้ง', '2016-12-28 14:20:39', '50', '1', '', '100', NULL, '10', '1000', '', '', '', '2', '2016-12-28 14:24:29', '0', NULL, NULL, '1', NULL, '0', '', '2016-12-28 14:20:39', NULL, '1', '13e930c7-d296-4d35-ab4d-b2ac24d3578b', '447e331c-ad32-11e6-96c5-02004c4f4f50', '0', '', NULL, NULL, ''),
('62a6176d-ccce-11e6-9d0b-02004c4f4f50', 'ad108994-5c46-42ae-84d3-51a1b09bdaa9', '1', '88225c62-758e-11e6-96a5-c03fd5b6f2e8', '1101', 'แกงส้มกุ้ง', '2016-12-28 14:22:26', '50', '1', '', '100', NULL, '10', '1000', '', '', '', '2', '2016-12-28 14:24:29', '0', NULL, NULL, '1', NULL, '0', '', '2016-12-28 14:22:26', NULL, '1', '13e930c7-d296-4d35-ab4d-b2ac24d3578b', '447e331c-ad32-11e6-96c5-02004c4f4f50', '0', '', NULL, NULL, ''),
('a147a502-ccce-11e6-9d0b-02004c4f4f50', '2faa347c-536a-43c9-a5c3-7b91cc94ab34', '1', '88225c62-758e-11e6-96a5-c03fd5b6f2e8', '1101', 'แกงส้มกุ้ง', '2016-12-28 14:24:11', '50', '1', '', '100', NULL, '10', '1000', '', '', '', '2', '2016-12-28 14:24:28', '0', NULL, NULL, '1', NULL, '0', '', '2016-12-28 14:24:11', NULL, '1', '13e930c7-d296-4d35-ab4d-b2ac24d3578b', '447e331c-ad32-11e6-96c5-02004c4f4f50', '0', '', NULL, NULL, '1'),
('a14e0e2b-ccce-11e6-9d0b-02004c4f4f50', '2faa347c-536a-43c9-a5c3-7b91cc94ab34', '2', '5debdec7-7590-11e6-96a5-c03fd5b6f2e8', '1006', 'ผัดซีอิวหมู', '2016-12-28 14:24:11', '45', '1', '', '100', NULL, '10', '1000', '', '', '', '2', '2016-12-28 14:24:28', '0', NULL, NULL, '1', NULL, '0', '', '2016-12-28 14:24:11', NULL, '1', '13e930c7-d296-4d35-ab4d-b2ac24d3578b', '447e331c-ad32-11e6-96c5-02004c4f4f50', '0', '', NULL, NULL, '1');

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
