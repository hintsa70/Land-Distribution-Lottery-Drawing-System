-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 18, 2017 at 11:42 PM
-- Server version: 5.7.14
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `houselotory`
--

-- --------------------------------------------------------

--
-- Table structure for table `assigned`
--

CREATE TABLE `assigned` (
  `id` int(11) NOT NULL,
  `Fname` varchar(100) NOT NULL,
  `Mname` varchar(100) NOT NULL,
  `Lname` varchar(100) NOT NULL,
  `Interest` varchar(100) NOT NULL,
  `Block` int(100) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `assigned`
--

INSERT INTO `assigned` (`id`, `Fname`, `Mname`, `Lname`, `Interest`, `Block`) VALUES
(1, 'ድዩግ', 'ህብግ', 'ህጅድ', 'ስትድዮ', 4),
(2, 'ጅክ', 'ድፍን', 'ብቭይ', 'ስትድዮ', 1),
(3, 'ብቭ', 'ፍቭብ', 'ኡድስ', 'ስትድዮ', 5),
(4, 'ግፍን', 'ጽችዝ', 'ፍችቭ', 'ስትድዮ', 2),
(5, 'ድህቩ', 'ድስፍ', 'ብቪኡ', 'ስትድዮ', 3),
(6, 'ጅክ', 'ድፍን', 'ብቭይ', 'ስትድዮ', 2),
(7, 'ግፍን', 'ጽችዝ', 'ፍችቭ', 'ስትድዮ', 3),
(8, 'ድዩግ', 'ህብግ', 'ህጅድ', 'ስትድዮ', 1),
(9, 'ድህቩ', 'ድስፍ', 'ብቪኡ', 'ስትድዮ', 4),
(10, 'ብቭ', 'ፍቭብ', 'ኡድስ', 'ስትድዮ', 5),
(11, 'ጅክ', 'ድፍን', 'ብቭይ', 'ስትድዮ', 5),
(12, 'ግፍን', 'ጽችዝ', 'ፍችቭ', 'ስትድዮ', 4),
(13, 'ብቭ', 'ፍቭብ', 'ኡድስ', 'ስትድዮ', 2),
(14, 'ድዩግ', 'ህብግ', 'ህጅድ', 'ስትድዮ', 3),
(15, 'ድህቩ', 'ድስፍ', 'ብቪኡ', 'ስትድዮ', 1),
(16, 'ጅህ', 'ብጉይ', 'ግቡይ', '1-ዓራት', 1),
(17, 'ስድግድ', 'ርግረት', 'ርግትር', '1-ዓራት', 2),
(18, 'ፈፍ', 'ህይትጅ', 'ኣስድስፍ', '2-ዓራት', 2),
(19, 'ጅብግ', 'ጅህግብ', 'ግህቢ', '2-ዓራት', 4),
(20, 'ግይ', 'ቭጉ', 'ግቩ', '2-ዓራት', 3),
(21, 'ቩጅቭ', 'ዩግቭህግ', 'ግጉግ', '2-ዓራት', 1);

-- --------------------------------------------------------

--
-- Table structure for table `cond`
--

CREATE TABLE `cond` (
  `id` int(11) NOT NULL,
  `cond_cod` int(100) NOT NULL,
  `n_studio` int(100) NOT NULL,
  `n_one` int(100) NOT NULL,
  `n_two` int(100) NOT NULL,
  `n_three` int(100) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cond`
--

INSERT INTO `cond` (`id`, `cond_cod`, `n_studio`, `n_one`, `n_two`, `n_three`) VALUES
(1, 21, 3, 1, 1, 3),
(2, 10, 2, 1, 3, 1);

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `cus_id` int(11) NOT NULL,
  `Fname` varchar(100) NOT NULL,
  `Mname` varchar(100) NOT NULL,
  `Lname` varchar(100) NOT NULL,
  `Address` varchar(100) NOT NULL,
  `Interest` varchar(100) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`cus_id`, `Fname`, `Mname`, `Lname`, `Address`, `Interest`) VALUES
(1, 'ቴድሮስ', 'ሃይሉ', 'ገብረሩፋኤል', 'መቀለ', '3-ዓራት'),
(3, 'ሃጎስ', 'ገለሰ', 'ይብራህ', 'ዓዲ-ግራት', '3-ዓራት'),
(4, 'ሃመር', 'ሳሊሕ', 'ዓሊ', 'ሑመራ', 'ስትድዮ'),
(5, 'ፍጔር', 'ህድብፈ', 'ግፌር', 'ግፉኤር', 'ስትድዮ'),
(7, 'ሰለሙን ', 'ሃጎስ', 'በርሀ', 'ምዕራብ', '2-ዓራት'),
(8, 'ህንፃ', 'ህንፃ', 'ህንፃ', 'መቀለ', '2-ዓራት'),
(9, 'ኪዳኔ', 'ኪዳኔ', 'ኪዳኔ', 'ውቅሮ', '2-ዓራት'),
(10, 'ተስፋይ', 'ለኣከ', 'ስዩም', 'መቐለ', '1-ዓራት'),
(11, 'ኪሮስ', 'ሓጎስ', 'ሓየሎም', 'ውቕሮ', '1-ዓራት'),
(12, 'ኣማኑኤል', 'ኪሮስ', 'ወ/ኤል', 'ፈረስ-ማይ', '1-ዓራት'),
(13, 'ዘሚካኤል', 'ፍስሃ', 'ገ/መስቀል', 'ሸራሮ', '2-ዓራት'),
(14, 'ብርሃነ', 'ገብሩ', 'ሓረጎት', 'መቀለ', '2-ዓራት'),
(16, 'ዘሚካኤል', 'ገ/መስቀል', 'ሓጎስ', 'ውቅሮ', '1-ዓራት'),
(17, 'ተጠምቀ', 'ፍስሃ', 'ሓጎስ', 'ዓድዋ', 'ስትድዮ'),
(18, 'ተጠምቐ', 'ሓዱሽ', 'ሃደራ', 'ዓድዋ', '1-ዓራት'),
(19, 'ቴዲ', 'ቴዲ', 'ቴዲ', 'ቴዲ', 'ስትድዮ'),
(20, 'ጮማ', 'ጮማ', 'ጮማ', 'ጮማ', '1-ዓራት'),
(21, 'ዛኪ', 'ጮማ', 'ጮማ', 'ጮማ', 'ስትድዮ'),
(22, 'ንቢኦኡግ', 'ፍኖፍብ', 'ፍብንጎኢፍቦፍ', 'ድብፍኖኢፍግብ', '1-ዓራት'),
(23, 'ቭፍዱ', 'ፍድግፍርድ', 'ግርት', 'ቭርፈር', '1-ዓራት'),
(25, 'ፈፍ', 'ህይትጅ', 'ኣስድስፍ', 'ፍብህግንጅ', '2-ዓራት'),
(26, 'ድግፍርግግ', 'ግህጅይጅ', 'ኲህምጅህ', 'ፍግብግህንህጅ', '3-ዓራት'),
(27, 'ግፍንህግን', 'ጽችዝቭጽፍብ', 'ፍችቭብግጽፍንብግፍ', 'ቭጽግፍን', 'ስትድዮ'),
(28, 'ድዩግቭፍዲኡቭግ', 'ህጅፍብግዪረ', 'ህጅድፍቢይድፍ', 'ጅድፍብቪይፍደ', 'ስትድዮ'),
(29, 'ጅክህቭህዱፕፊ', 'ድፍንቭብግፍድፒይ', 'ብቭይዱፊብቪ', 'ንብቪስፉቭ', 'ስትድዮ'),
(30, 'ቭቢኡፕፍድህቩ', 'ክብቩኢድስፍ', 'ንብችቪኡድፍህብቪኡ', 'ንክፍብቪስብቭፍ', 'ስትድዮ'),
(31, 'ብቪኡፍድህብቭ', 'ቭቢዱፍቭብ', 'ድፍንክክብቮኡድስ', 'ንፍድብኑኦኢህደፍብ', 'ስትድዮ'),
(32, 'ንህግፎህቦግ', 'ፍህጒ', 'ፍግብኊ', 'ህብግ', '3-ዓራት'),
(33, 'ፊህግ', 'ጅብግህ', 'ፍግጅ', 'ህግ', '3-ዓራት'),
(34, 'ጊኡፍህ', 'ሂቭ', 'ሂይ', 'ህጉይ', '3-ዓራት'),
(35, 'ብግፍይ', 'ቭፍቭት', 'ቭግጉ', 'ህቭጅህ', '3-ዓራት'),
(36, 'ሁህግ', 'ህቡኢ', 'ሂኡሂኡ', 'ጅሂኡ', '3-ዓራት'),
(37, 'ቩጅቭ', 'ዩግቭህግ', 'ግጉግ', 'ህጅብቭጉ', '2-ዓራት'),
(38, 'ግይ', 'ቭጉ', 'ግቩ', 'ቩህጅ', '2-ዓራት'),
(39, 'ጅብግ', 'ጅህግብ', 'ግህቢ', 'ጅሂኡ', '2-ዓራት'),
(40, 'ብህ', 'ዩጉይ', 'ህብግ', 'ህጉህጅ', '2-ዓራት'),
(41, 'ቭብህ', 'ቭግቭ', 'ቭጅህግቭ', 'ቭህቭግህ', '1-ዓራት'),
(42, 'ንህኑ', 'ኊህጅክ', 'ጅክህጅ', 'ጅክጅጅ', '1-ዓራት'),
(43, 'ግህይፊኡ', 'ህብህ', 'ህጅህጅህ', 'ህህጅ', '1-ዓራት'),
(44, 'ህጒፍህ', 'ኡኢይግይግ', 'ህጁይ', 'ህጉይ', '1-ዓራት'),
(45, 'ጅህ', 'ብጉይ', 'ግቡይ', 'ጉዩይ', '1-ዓራት');

-- --------------------------------------------------------

--
-- Table structure for table `cus_cond`
--

CREATE TABLE `cus_cond` (
  `id` int(11) NOT NULL,
  `Fname` varchar(100) CHARACTER SET utf8 NOT NULL,
  `Mname` varchar(100) CHARACTER SET utf8 NOT NULL,
  `Lname` varchar(100) CHARACTER SET utf8 NOT NULL,
  `Address` varchar(100) CHARACTER SET utf8 NOT NULL,
  `Interest` varchar(100) CHARACTER SET utf8 NOT NULL,
  `Block` varchar(100) CHARACTER SET utf8 NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `one_alga`
--

CREATE TABLE `one_alga` (
  `cus_id` int(11) NOT NULL,
  `Fname` varchar(100) NOT NULL,
  `Mname` varchar(100) NOT NULL,
  `Lname` varchar(100) NOT NULL,
  `Address` varchar(100) NOT NULL,
  `Interest` varchar(100) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `one_alga`
--

INSERT INTO `one_alga` (`cus_id`, `Fname`, `Mname`, `Lname`, `Address`, `Interest`) VALUES
(1, 'ስድግድ', 'ርግረት', 'ርግትር', 'ርህትር', '1-ዓራት'),
(2, 'ቭብህ', 'ቭግቭ', 'ቭጅህግቭ', 'ቭህቭግህ', '1-ዓራት'),
(3, 'ንህኑ', 'ኊህጅክ', 'ጅክህጅ', 'ጅክጅጅ', '1-ዓራት'),
(4, 'ግህይፊኡ', 'ህብህ', 'ህጅህጅህ', 'ህህጅ', '1-ዓራት'),
(5, 'ህጒፍህ', 'ኡኢይግይግ', 'ህጁይ', 'ህጉይ', '1-ዓራት'),
(6, 'ጅህ', 'ብጉይ', 'ግቡይ', 'ጉዩይ', '1-ዓራት');

-- --------------------------------------------------------

--
-- Table structure for table `stu_alga`
--

CREATE TABLE `stu_alga` (
  `cus_id` int(11) NOT NULL,
  `Fname` varchar(100) NOT NULL,
  `Mname` varchar(100) NOT NULL,
  `Lname` varchar(100) NOT NULL,
  `Address` varchar(100) NOT NULL,
  `Interest` varchar(100) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `stu_alga`
--

INSERT INTO `stu_alga` (`cus_id`, `Fname`, `Mname`, `Lname`, `Address`, `Interest`) VALUES
(1, 'ግፍን', 'ጽችዝ', 'ፍችቭ', 'ቭጽግ', 'ስትድዮ'),
(2, 'ድዩግ', 'ህብግ', 'ህጅድ', 'ጅድፍ', 'ስትድዮ'),
(3, 'ጅክ', 'ድፍን', 'ብቭይ', 'ንብቪ', 'ስትድዮ'),
(4, 'ድህቩ', 'ድስፍ', 'ብቪኡ', 'ብቭፍ', 'ስትድዮ'),
(5, 'ብቭ', 'ፍቭብ', 'ኡድስ', 'ህደፍብ', 'ስትድዮ');

-- --------------------------------------------------------

--
-- Table structure for table `three_alga`
--

CREATE TABLE `three_alga` (
  `cus_id` int(11) NOT NULL,
  `Fname` varchar(100) NOT NULL,
  `Mname` varchar(100) NOT NULL,
  `Lname` varchar(100) NOT NULL,
  `Address` varchar(100) NOT NULL,
  `Interest` varchar(100) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `three_alga`
--

INSERT INTO `three_alga` (`cus_id`, `Fname`, `Mname`, `Lname`, `Address`, `Interest`) VALUES
(1, 'ድግፍርግግ', 'ግህጅይጅ', 'ኲህምጅህ', 'ፍግብግህንህጅ', '3-ዓራት'),
(2, 'ንህግፎህቦግ', 'ፍህጒ', 'ፍግብኊ', 'ህብግ', '3-ዓራት'),
(3, 'ፊህግ', 'ጅብግህ', 'ፍግጅ', 'ህግ', '3-ዓራት'),
(4, 'ጊኡፍህ', 'ሂቭ', 'ሂይ', 'ህጉይ', '3-ዓራት'),
(5, 'ብግፍይ', 'ቭፍቭት', 'ቭግጉ', 'ህቭጅህ', '3-ዓራት'),
(6, 'ሁህግ', 'ህቡኢ', 'ሂኡሂኡ', 'ጅሂኡ', '3-ዓራት');

-- --------------------------------------------------------

--
-- Table structure for table `two_alga`
--

CREATE TABLE `two_alga` (
  `cus_id` int(11) NOT NULL,
  `Fname` varchar(100) NOT NULL,
  `Mname` varchar(100) NOT NULL,
  `Lname` varchar(100) NOT NULL,
  `Address` varchar(100) NOT NULL,
  `Interest` varchar(100) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `two_alga`
--

INSERT INTO `two_alga` (`cus_id`, `Fname`, `Mname`, `Lname`, `Address`, `Interest`) VALUES
(1, 'ፈፍ', 'ህይትጅ', 'ኣስድስፍ', 'ፍብህግንጅ', '2-ዓራት'),
(2, 'ቩጅቭ', 'ዩግቭህግ', 'ግጉግ', 'ህጅብቭጉ', '2-ዓራት'),
(3, 'ግይ', 'ቭጉ', 'ግቩ', 'ቩህጅ', '2-ዓራት'),
(4, 'ጅብግ', 'ጅህግብ', 'ግህቢ', 'ጅሂኡ', '2-ዓራት'),
(5, 'ብህ', 'ዩጉይ', 'ህብግ', 'ህጉህጅ', '2-ዓራት');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `assigned`
--
ALTER TABLE `assigned`
  ADD UNIQUE KEY `id` (`id`);

--
-- Indexes for table `cond`
--
ALTER TABLE `cond`
  ADD UNIQUE KEY `id` (`id`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`cus_id`);

--
-- Indexes for table `cus_cond`
--
ALTER TABLE `cus_cond`
  ADD UNIQUE KEY `id` (`id`);

--
-- Indexes for table `one_alga`
--
ALTER TABLE `one_alga`
  ADD UNIQUE KEY `cus_id` (`cus_id`);

--
-- Indexes for table `stu_alga`
--
ALTER TABLE `stu_alga`
  ADD PRIMARY KEY (`cus_id`);

--
-- Indexes for table `three_alga`
--
ALTER TABLE `three_alga`
  ADD UNIQUE KEY `cus_id` (`cus_id`);

--
-- Indexes for table `two_alga`
--
ALTER TABLE `two_alga`
  ADD UNIQUE KEY `cus_id` (`cus_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `assigned`
--
ALTER TABLE `assigned`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
--
-- AUTO_INCREMENT for table `cond`
--
ALTER TABLE `cond`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `cus_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;
--
-- AUTO_INCREMENT for table `cus_cond`
--
ALTER TABLE `cus_cond`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `one_alga`
--
ALTER TABLE `one_alga`
  MODIFY `cus_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `stu_alga`
--
ALTER TABLE `stu_alga`
  MODIFY `cus_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `three_alga`
--
ALTER TABLE `three_alga`
  MODIFY `cus_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `two_alga`
--
ALTER TABLE `two_alga`
  MODIFY `cus_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
