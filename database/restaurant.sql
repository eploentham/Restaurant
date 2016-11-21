-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Aug 11, 2016 at 08:36 AM
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
  `active` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `foods_type_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `remark` varchar(255) COLLATE utf8_bin DEFAULT NULL
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
  `remark` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `b_restaurant`
--

CREATE TABLE `b_restaurant` (
  `res_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `res_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `active` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `remark` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='ชื่อร้านอาหาร';

--
-- Dumping data for table `b_restaurant`
--

INSERT INTO `b_restaurant` (`res_id`, `res_name`, `active`, `remark`) VALUES
('beefcdc0-5a1d-11e6-99a1-c03fd5b6f2e8', 'ครัวมัยลาภ', '1', NULL),
('beefd53a-5a1d-11e6-99a1-c03fd5b6f2e8', 'อีสาน รสเด็ด', '1', NULL);

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
('dd9f4e7d-5a1b-11e6-99a1-c03fd5b6f2e8', NULL, '1000', 'โต๊ะ1', '1', '1', NULL),
('3aed5439-5a1c-11e6-99a1-c03fd5b6f2e8', NULL, '1000', 'โต๊ะ2', '1', NULL, NULL),
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
  `status_void` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `t_order`
--

INSERT INTO `t_order` (`order_id`, `lot_id`, `foods_id`, `foods_code`, `foods_name`, `order_date`, `price`, `qty`, `remark`, `table_code`, `device_id`, `res_code`, `area_code`, `status_foods_1`, `status_foods_2`, `status_foods_3`, `status_bill`, `bill_check_date`, `status_cook`, `cook_receive_date`, `cook_finish_date`, `active`, `void_date`, `status_void`) VALUES
('cb3d68a1-d67c-45b0-990a-30c71dcc8933', 'd8904fcf-ea16-4f27-a613-f10310a278e2', NULL, '100', NULL, '2016-08-05 11:45:37', '1', '1', NULL, 'โต๊ะ1', NULL, '10', 'ในร้าน', '', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('8ccb3f68-7097-4a25-87db-a9b00f874a77', 'd8904fcf-ea16-4f27-a613-f10310a278e2', NULL, '101', NULL, '2016-08-05 11:45:37', '1', '2', NULL, 'โต๊ะ1', NULL, '10', 'ในร้าน', '', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('276854b3-e314-42e9-8172-534a5c08ff69', '32454c4f-c643-4baa-8949-b417646611a7', NULL, '100', NULL, '2016-08-05 16:18:33', '1', '1', NULL, 'โต๊ะ1', NULL, '10', 'ในร้าน', '', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('08b80bcd-a498-4bc4-bd8b-825694ac5db2', '32454c4f-c643-4baa-8949-b417646611a7', NULL, '101', NULL, '2016-08-05 16:18:33', '1', '2', NULL, 'โต๊ะ1', NULL, '10', 'ในร้าน', '', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('83c6fde3-2b5a-4521-9d0a-f4ec8a0cd7b0', 'a9a5ecb6-5b03-48b7-abdf-b7352da50829', NULL, '100', NULL, '2016-08-05 16:31:20', '1', '1', NULL, 'โต๊ะ1', NULL, '10', 'ในร้าน', '', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('a72234d9-7fcc-4f55-b624-4d55977e8fce', 'a9a5ecb6-5b03-48b7-abdf-b7352da50829', NULL, '101', NULL, '2016-08-05 16:31:20', '1', '2', NULL, 'โต๊ะ1', NULL, '10', 'ในร้าน', '', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('10fa72ee-8810-43d4-9ff9-6f1292808875', '99c4eef1-30a7-4f56-8eef-a520392038d6', NULL, '100', NULL, '2016-08-05 16:34:10', '1', '1', NULL, 'โต๊ะ1', NULL, '10', 'ในร้าน', '', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('8640fda9-56e0-41ae-bb63-fb1868ecd175', '99c4eef1-30a7-4f56-8eef-a520392038d6', NULL, '101', NULL, '2016-08-05 16:34:10', '1', '2', NULL, 'โต๊ะ1', NULL, '10', 'ในร้าน', '', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('62f49182-043b-4230-b457-8025b2b8d95f', '3fc8aead-f671-48f9-a834-d70560937832', NULL, '100', NULL, '2016-08-05 16:35:00', '1', '1', NULL, 'โต๊ะ1', NULL, '10', 'ในร้าน', '', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('4eaedb08-82f9-4967-bc12-b6a69312ad7a', '3fc8aead-f671-48f9-a834-d70560937832', NULL, '101', NULL, '2016-08-05 16:35:00', '1', '2', NULL, 'โต๊ะ1', NULL, '10', 'ในร้าน', '', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('30feee76-b5d7-4d75-adf8-23bdcb874670', '768aae7a-c96c-44f8-aa4e-43792a275be3', NULL, '100', NULL, '2016-08-05 16:36:27', '1', '1', NULL, 'โต๊ะ1', NULL, '10', 'ในร้าน', '2131427424', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('fbc6ea03-d1e6-434b-8cce-1c8af355fa61', '768aae7a-c96c-44f8-aa4e-43792a275be3', NULL, '101', NULL, '2016-08-05 16:36:27', '1', '2', NULL, 'โต๊ะ1', NULL, '10', 'ในร้าน', '2131427430', '2131427432', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('ce01652c-c4fa-417b-963a-029843496536', '768aae7a-c96c-44f8-aa4e-43792a275be3', NULL, '102', NULL, '2016-08-05 16:36:27', '1', '3', NULL, 'โต๊ะ1', NULL, '10', 'ในร้าน', '2131427439', '2131427442', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('dea282f6-de5d-4c34-92c6-e5386b70ff3b', '768aae7a-c96c-44f8-aa4e-43792a275be3', NULL, '103', NULL, '2016-08-05 16:36:27', '1', '4', NULL, 'โต๊ะ1', NULL, '10', 'ในร้าน', '2131427447', '2131427451', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('3dcf9cad-7d4d-4052-9a26-7f783ae5630b', 'cd35e99a-5fad-4609-862b-d3079608376d', NULL, '100', NULL, '2016-08-05 16:47:46', '1', '1', NULL, 'โต๊ะ1', NULL, '10', 'ในร้าน', 'หมู', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('ac81d117-1340-414d-b967-19aaddddfdfb', 'cd35e99a-5fad-4609-862b-d3079608376d', NULL, '101', NULL, '2016-08-05 16:47:46', '1', '2', NULL, 'โต๊ะ1', NULL, '10', 'ในร้าน', 'เนื้อ', 'ขม', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('40962d18-e43c-4be9-9cb6-6266299af50e', 'cd35e99a-5fad-4609-862b-d3079608376d', NULL, '102', NULL, '2016-08-05 16:47:46', '1', '3', NULL, 'โต๊ะ1', NULL, '10', 'ในร้าน', '2131427438', '2131427442', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

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
