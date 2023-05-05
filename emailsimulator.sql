-- phpMyAdmin SQL Dump
-- version 5.3.0-dev+20221113.0eded7bb43
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 05, 2023 at 04:25 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `emailsimulator`
--

-- --------------------------------------------------------

--
-- Table structure for table `accounts`
--

CREATE emailsimulator;
USE emailsimulator;

CREATE TABLE `accounts` (
  `email` varchar(128) NOT NULL,
  `name` varchar(128) NOT NULL,
  `phoneNum` varchar(128) NOT NULL,
  `password` varchar(128) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `accounts`
--

INSERT INTO `accounts` (`email`, `name`, `phoneNum`, `password`) VALUES
('caophat@email.com', 'Cao Duc Hai', '1234567891', '12345679'),
('nguyena@email.com', 'Nguyen Van A', '1234567891', '12345678'),
('quocthai@email.com', 'Vo Pham Quoc Thai', '1234567878', '12345678'),
('quoctuan@email.com', 'Van Tuan Quoc', '1234567890', '12345678'),
('thaihoc@email.com', 'Nguyen Thai Hoc', '1234567890', '12345678');

-- --------------------------------------------------------

--
-- Table structure for table `emails`
--

CREATE TABLE `emails` (
  `sender` varchar(128) NOT NULL,
  `receiver` varchar(128) NOT NULL,
  `subject` varchar(128) NOT NULL,
  `mainBody` varchar(1024) NOT NULL,
  `dateSent` varchar(16) NOT NULL,
  `isNew` tinyint(1) NOT NULL,
  `mailID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `emails`
--

INSERT INTO `emails` (`sender`, `receiver`, `subject`, `mainBody`, `dateSent`, `isNew`, `mailID`) VALUES
('test1@socketmail.gr', '', '', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"></body></html>', '19/12/2022 15:02', 1, 29),
('test1@socketmail.gr', 'test2@socketmail,gr', '', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"></body></html>', '19/12/2022 15:03', 1, 30),
('test1@socketmail.gr', 'test2@socketmail.gr', '', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"></body></html>', '19/12/2022 15:05', 1, 31),
('test1@socketmail.gr', 'test2@socketmail.gr', '123123', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><span style=\"font-family: &quot;&quot;;\">asd wef acc sdc</span></p></body></html>', '19/12/2022 17:42', 1, 32),
('test3@socketmail.gr', 'test2@socketmai;l.gr', 'hi', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><span style=\"font-family: &quot;&quot;;\">124 daf qef ac asd&nbsp;</span></p></body></html>', '19/12/2022 18:35', 1, 33),
('test3@socketmail.gr', 'test2@socketmail.gr', 'hi', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><span style=\"font-family: &quot;&quot;;\">124 daf qef ac asd&nbsp;</span></p></body></html>', '19/12/2022 18:35', 1, 34),
('test3@socketmail.gr', 'test2@socketmail.gr', 'hello there', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><span style=\"font-family: &quot;&quot;;\">add me</span></p></body></html>', '19/12/2022 19:24', 1, 35),
('test3@socketmail.gr', 'test3@socketmail.gr', 'hi', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><span style=\"font-family: &quot;&quot;;\">asdasdasdasdasdasdasd</span></p></body></html>', '19/12/2022 19:26', 1, 36),
('test2@socketmail.gr', 'test2@socketmail.gr', 'asdasd', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><span style=\"font-family: &quot;&quot;;\">asdafd asd asd as&nbsp;</span></p></body></html>', '19/12/2022 19:31', 1, 37),
('test2@socketmail.gr', 'test2@socketmail.gr', 'asdasd', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><span style=\"font-family: &quot;&quot;;\">adsad</span></p></body></html>', '19/12/2022 19:36', 1, 38),
('test2@socketmail.gr', 'test2@socketmail.gr', 'asdasd', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><span style=\"font-family: &quot;&quot;;\">adasd</span></p></body></html>', '19/12/2022 19:38', 1, 39),
('test2@socketmail.gr', 'test2@socketmail.gr', 'asdasd', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><span style=\"font-family: &quot;&quot;;\">sadasdsad</span></p></body></html>', '19/12/2022 19:44', 1, 40),
('test2@socketmail.gr', 'test2@socketmail.gr', 'asdasd', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><span style=\"font-family: &quot;&quot;;\">1231221ewad as</span></p></body></html>', '19/12/2022 19:52', 1, 41),
('test2@socketmail.gr', 'test2@socketmail.gr', 'asdasd', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><span style=\"font-family: &quot;&quot;;\">asdasd</span></p></body></html>', '19/12/2022 19:58', 1, 42),
('test2@socketmail.gr', 'test2@socketmail.gr', 'asdsad', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><span style=\"font-family: &quot;&quot;;\">asdasd</span></p></body></html>', '19/12/2022 20:00', 1, 43),
('test2@socketmail.gr', 'test2@socketmail.gr', 'asdas', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><span style=\"font-family: &quot;&quot;;\">dasdasdsa</span></p></body></html>', '19/12/2022 20:01', 1, 44),
('test2@socketmail.gr', 'test2@socketmail.gr', 'asdas', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><span style=\"font-family: &quot;&quot;;\">asdasd</span></p></body></html>', '19/12/2022 20:03', 1, 45),
('test2@socketmail.gr', 'test2@socketmail.gr', 'asdasd', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><span style=\"font-family: &quot;&quot;;\">asdasd</span></p></body></html>', '19/12/2022 20:04', 1, 46),
('test2@socketmail.gr', 'test2@socketmail.gr', 'asdasd', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><span style=\"font-family: &quot;&quot;;\">asdasd</span></p></body></html>', '19/12/2022 20:05', 1, 47),
('test2@socketmail.gr', 'test2@socketmail.gr', 'asdas', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><span style=\"font-family: &quot;&quot;;\">dasdasda</span></p></body></html>', '19/12/2022 20:06', 1, 48),
('test2@socketmail.gr', 'test2@socketmatil.gr', 'passasdas', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><span style=\"font-family: &quot;&quot;;\">asdasdas</span></p></body></html>', '19/12/2022 20:09', 1, 49),
('test2@socketmail.gr', 'test2@socketmail.gr', 'passasdas', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><span style=\"font-family: &quot;&quot;;\">asdasdas</span></p></body></html>', '19/12/2022 20:09', 1, 50),
('test2@socketmail.gr', 'test2@socketmail.gr', 'asd', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><span style=\"font-family: &quot;&quot;;\">asdasd</span></p></body></html>', '19/12/2022 20:12', 1, 51),
('test2@socketmail.gr', 'test2@socketmail.gr', 'asdsa', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><span style=\"font-family: &quot;&quot;;\">sadasdasd</span></p></body></html>', '19/12/2022 20:16', 1, 52),
('test2@socketmail.gr', 'test2@socketmail,gr', 'asdasd', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><span style=\"font-family: &quot;&quot;;\">asdasd</span></p></body></html>', '19/12/2022 20:19', 1, 53),
('test2@socketmail.gr', 'test2@socketmail.gr', 'asdasd', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><span style=\"font-family: &quot;&quot;;\">asdasd</span></p></body></html>', '19/12/2022 20:20', 1, 54),
('test2@socketmail.gr', 'test2@socketmail,gr', 'asdasd', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><span style=\"font-family: &quot;&quot;;\">hi</span></p></body></html>', '19/12/2022 20:22', 1, 55),
('test2@socketmail.gr', 'test2@socketmail.gr', 'asdasd', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><span style=\"font-family: &quot;&quot;;\">hi</span></p></body></html>', '19/12/2022 20:22', 1, 56),
('test2@socketmail.gr', 'test2@socketmail.gr', 'asdas', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><span style=\"font-family: &quot;&quot;;\">dasdasd</span></p></body></html>', '19/12/2022 20:23', 1, 57),
('test2@socketmail.gr', 'test2@socketmail.gr', 'asdas', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><span style=\"font-family: &quot;&quot;;\">dasdasd</span></p></body></html>', '19/12/2022 20:24', 1, 58),
('test2@socketmail.gr', 'test2@socketmail.gr', 'adas', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><span style=\"font-family: &quot;&quot;;\">dasdasd</span></p></body></html>', '19/12/2022 20:26', 1, 59),
('test2@socketmail.gr', 'test2@socketmail.gr', 'asdsd', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><span style=\"font-family: &quot;&quot;;\">ada</span></p></body></html>', '19/12/2022 20:31', 1, 60),
('test2@socketmail.gr', 'test2@socketmail.gr', 'asdasd', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><span style=\"font-family: &quot;&quot;;\">asdasd</span></p></body></html>', '19/12/2022 20:33', 1, 61),
('test2@socketmail.gr', 'test2@socketmail.gr', 'this is in label \"hello mate\"', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><span style=\"font-family: &quot;&quot;;\">asdadsa</span></p></body></html>', '19/12/2022 20:45', 1, 62),
('test2@socketmail.gr', 'test2@socketmail.gr', 'this is in hi label', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><span style=\"font-family: &quot;&quot;;\">asda</span></p></body></html>', '19/12/2022 20:47', 1, 63),
('test2@socketmail.gr', 'test2@socketmail.gr', 'adasdasda', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><span style=\"font-family: &quot;&quot;;\">asdadasdasda</span></p></body></html>', '19/12/2022 20:49', 1, 64),
('test2@socketmail.gr', 'test2@socketmail.gr', 'hi', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><span style=\"font-family: &quot;&quot;;\">adasdasda</span></p></body></html>', '19/12/2022 20:49', 1, 65),
('test2@socketmail.gr', 'test2@socketmail.gr', 'asdad', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><span style=\"font-family: &quot;&quot;;\">asdasdasdas</span></p></body></html>', '19/12/2022 20:51', 1, 66),
('test2@socketmail.gr', 'test3@socketmail.gr', 'asdasd', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><span style=\"font-family: &quot;&quot;;\">adasdasdasdad</span></p></body></html>', '19/12/2022 20:55', 1, 67),
('test2@socketmail.gr', 'test3@socketmail.gr', 'test 1', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><span style=\"font-family: &quot;&quot;;\">1241 cv swdf szc zc</span></p></body></html>', '19/12/2022 20:56', 1, 68),
('test2@socketmail.gr', 'test3@socketmail.gr', 'xczxc aswd 2154', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><span style=\"font-family: &quot;&quot;;\">asd xc x</span></p></body></html>', '19/12/2022 20:56', 1, 69),
('test2@socketmail.gr', 'test2@socketmail.gr', 'asdas', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><span style=\"font-family: &quot;&quot;;\">dasdas</span></p></body></html>', '19/12/2022 20:59', 1, 70),
('test2@socketmail.gr', 'test2@socketmail.gr', 'asdasd', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><span style=\"font-family: &quot;&quot;;\">asdasd</span></p></body></html>', '19/12/2022 21:01', 1, 71),
('test2@socketmail.gr', 'test2@socketmail.gr', 'asd124 1qwd ', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><span style=\"font-family: &quot;&quot;;\">asc 31e dc ad f</span></p></body></html>', '19/12/2022 21:02', 1, 72),
('test2@socketmail.gr', 'test2@socketmail.gr', 'asdasd ', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><span style=\"font-family: &quot;&quot;;\">hello there how you doin</span></p></body></html>', '19/12/2022 21:04', 1, 73),
('test2@socketmail.gr', 'test2@socketmail.gr', 'new one 1', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><span style=\"font-family: &quot;&quot;;\">asdasd</span></p></body></html>', '19/12/2022 21:06', 1, 74),
('test2@socketmail.gr', 'test2@socketmail.gr', 'new one 2', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><span style=\"font-family: &quot;&quot;;\">asdsad</span></p></body></html>', '19/12/2022 21:06', 1, 75),
('test2@socketmail.gr', 'test2@socketmail.gr', 'test 1', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><span style=\"font-family: &quot;&quot;;\">hello&nbsp;</span></p></body></html>', '19/12/2022 21:15', 1, 76),
('test2@socketmail.gr', 'test2@socketmail.gr', 'test 2', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><span style=\"font-family: &quot;&quot;;\">hi</span></p></body></html>', '19/12/2022 21:15', 1, 77),
('test2@socketmail.gr', 'test2@socketmail.gr', 'test 3', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><span style=\"font-family: &quot;&quot;;\">asdasd</span></p></body></html>', '19/12/2022 21:15', 1, 78),
('test2@socketmail.gr', 'test2@socketmail.gr', 'test 4', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><span style=\"font-family: &quot;&quot;;\">asdasdasd</span></p></body></html>', '19/12/2022 21:15', 1, 79),
('caophat@email.com', 'vana@email.com', 'asdas', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><span style=\"font-family: &quot;&quot;;\">dasdad</span></p></body></html>', '19/12/2022 21:39', 1, 80),
('caophat@email.com', 'hi@email.com', 'asda', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><span style=\"font-family: &quot;&quot;;\">asdasd</span></p></body></html>', '19/12/2022 21:39', 1, 81),
('caophat@email.com', 'nguyena@email.com', 'xin chao', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><span style=\"font-family: &quot;&quot;;\">xin chao day la</span></p><p style=\"text-align: center;\"><span style=\"font-size: xx-large; font-family: &quot;Agency FB&quot;; font-weight: bold; font-style: italic;\">&nbsp;mail</span><span style=\"font-size: xx-large; font-family: &quot;Arial Rounded MT Bold&quot;;\">&nbsp;</span></p><h4><span style=\"font-family: &quot;&quot;; font-style: italic; font-weight: bold; text-decoration: underline line-through;\">thu</span></h4></body></html>', '19/12/2022 21:47', 1, 82),
('quocthai@email.com', 'quocthai@email.com', 'asdasd', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><span style=\"font-family: &quot;&quot;;\">adsasda</span></p></body></html>', '19/12/2022 21:51', 1, 83),
('nguyena@email.com', 'caophat@email.com', 'asd', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><span style=\"font-family: &quot;&quot;;\">asdasda</span></p></body></html>', '19/12/2022 21:53', 1, 84);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `accounts`
--
ALTER TABLE `accounts`
  ADD PRIMARY KEY (`email`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `emails`
--
ALTER TABLE `emails`
  ADD PRIMARY KEY (`mailID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `emails`
--
ALTER TABLE `emails`
  MODIFY `mailID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=85;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
