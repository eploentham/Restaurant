-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Aug 30, 2016 at 09:53 AM
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
  `sort1` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `b_area`
--

INSERT INTO `b_area` (`area_id`, `area_code`, `area_name`, `active`, `remark`, `sort1`) VALUES
('a01f92e1-5926-11e6-bfd0-c03fd5b6f2e8', '1000', 'ในร้าน', '1', NULL, '100'),
('a01fa080-5926-11e6-bfd0-c03fd5b6f2e8', '1001', 'ฟุตบาท', '1', NULL, '101'),
('c00d1782-5926-11e6-bfd0-c03fd5b6f2e8', '1002', 'ในสวน', '1', NULL, '102');

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
  `sort1` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `b_foods_type`
--

INSERT INTO `b_foods_type` (`foods_type_id`, `foods_type_code`, `foods_type_name`, `active`, `remark`, `sort1`) VALUES
('29b9ecd5-6e7e-11e6-a3fd-c03fd5b6f2e8', '10', 'อาหารจานเดียว', '1', NULL, '100'),
('29b9f510-6e7e-11e6-a3fd-c03fd5b6f2e8', '11', 'กับข้าว', '1', NULL, '101'),
('6e8af84d-6e7e-11e6-a3fd-c03fd5b6f2e8', '12', 'อาหารอีสาน', '1', NULL, '102'),
('6e8b0b40-6e7e-11e6-a3fd-c03fd5b6f2e8', '13', 'สุรา, เบียร์', '1', NULL, '103'),
('cee2df68-6e7e-11e6-a3fd-c03fd5b6f2e8', '14', 'น้ำอัดลม', '1', NULL, '104'),
('cee2eaae-6e7e-11e6-a3fd-c03fd5b6f2e8', '15', 'น้ำผลไม้', '1', NULL, '105'),
('0275e3a8-6e7f-11e6-a3fd-c03fd5b6f2e8', '16', 'ขนมหวาน', '1', NULL, '106'),
('0275ecff-6e7f-11e6-a3fd-c03fd5b6f2e8', '17', 'อาหารกลางโต๊ะ', '1', NULL, '107');

-- --------------------------------------------------------

--
-- Table structure for table `b_printername`
--

CREATE TABLE `b_printername` (
  `printer_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `printer_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `active` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `printer_ip` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `b_printername`
--

INSERT INTO `b_printername` (`printer_id`, `printer_name`, `active`, `printer_ip`) VALUES
('92da874f-6e84-11e6-a3fd-c03fd5b6f2e8', 'T88', '1', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `b_restaurant`
--

CREATE TABLE `b_restaurant` (
  `res_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `res_code` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `res_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `active` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `remark` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='ชื่อร้านอาหาร';

--
-- Dumping data for table `b_restaurant`
--

INSERT INTO `b_restaurant` (`res_id`, `res_code`, `res_name`, `active`, `remark`) VALUES
('beefcdc0-5a1d-11e6-99a1-c03fd5b6f2e8', '10', 'ครัวมัยลาภ', '1', NULL),
('beefd53a-5a1d-11e6-99a1-c03fd5b6f2e8', '11', 'อีสาน รสเด็ด', '1', NULL);

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
  `sort1` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `b_table`
--

INSERT INTO `b_table` (`table_id`, `area_id`, `table_code`, `table_name`, `active`, `remark`, `sort1`) VALUES
('dd9f4e7d-5a1b-11e6-99a1-c03fd5b6f2e8', NULL, '100', 'โต๊ะ1', '1', '1', NULL),
('3aed5439-5a1c-11e6-99a1-c03fd5b6f2e8', NULL, '101', 'โต๊ะ2', '1', NULL, NULL),
('3aed5e6c-5a1c-11e6-99a1-c03fd5b6f2e8', NULL, '1000', 'โต๊ะ3', '1', NULL, NULL),
('6e7f4e6b-5a1c-11e6-99a1-c03fd5b6f2e8', NULL, '1000', 'โต๊ะ4', '1', NULL, NULL),
('857880af-5a1c-11e6-99a1-c03fd5b6f2e8', NULL, '1000', 'โต๊ะ5', '1', NULL, NULL),
('857893df-5a1c-11e6-99a1-c03fd5b6f2e8', NULL, '1000', 'โต๊ะ6', '1', NULL, NULL);

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
  `date_modi` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

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
-- Indexes for table `t_order`
--
ALTER TABLE `t_order`
  ADD PRIMARY KEY (`order_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
