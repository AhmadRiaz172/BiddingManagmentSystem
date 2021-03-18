-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 31, 2020 at 04:17 PM
-- Server version: 10.4.13-MariaDB
-- PHP Version: 7.4.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `biddera`
--

-- --------------------------------------------------------

--
-- Table structure for table `admins`
--

CREATE TABLE `admins` (
  `username` varchar(25) NOT NULL,
  `password` varchar(25) NOT NULL,
  `id` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admins`
--

INSERT INTO `admins` (`username`, `password`, `id`) VALUES
('admin1', '1234', 1);

-- --------------------------------------------------------

--
-- Table structure for table `all_accounts`
--

CREATE TABLE `all_accounts` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `address` varchar(150) NOT NULL,
  `cardnumber` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `all_accounts`
--

INSERT INTO `all_accounts` (`id`, `username`, `email`, `address`, `cardnumber`, `password`) VALUES
(3, 'Ahmad Riaz', 'ahmadriaz1999@gmail.com', 'house 135,street 4, block A, PWD society, Islamabad', '1234567891', '12345678'),
(4, 'Hassan Mehboob', 'bob@gmail.com', 'Bahria town, phase 8, Islamabad', '1234567891', 'thisismypass'),
(5, 'Hassan Mehboob', 'bob@gmail.com', 'Bahria town, phase 8, Islamabad', '1234567891', 'thisispass'),
(6, 'user1', 'user1@gmail.com', 'user1 address', '12345678', '12345'),
(7, 'user2', 'user2@gmail.com', 'user2 address', '1234567891', '12345'),
(8, 'user3', 'user3@gmail.com', 'user3 address', '1234567891', '12345');

-- --------------------------------------------------------

--
-- Table structure for table `all_products`
--

CREATE TABLE `all_products` (
  `name` varchar(50) NOT NULL,
  `category` int(11) NOT NULL,
  `description` varchar(200) NOT NULL,
  `image` varchar(200) NOT NULL,
  `minbid` int(11) NOT NULL,
  `datetime` varchar(50) NOT NULL,
  `id` int(11) NOT NULL,
  `uid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `all_products`
--

INSERT INTO `all_products` (`name`, `category`, `description`, `image`, `minbid`, `datetime`, `id`, `uid`) VALUES
('Cricket Bat', 10, 'This is cricket bat used by Babar Azam', 'Screenshot (55).png', 1000, '2020-12-23T11:27', 1, 3),
('Ball', 10, 'This is ball used by Saeed Ajmal in England toure', 'A-sample-bid-gradient.png', 1200, '2020-12-25T01:16', 2, 3),
('Watch', 8, 'This is 18th century watch used by royal family.', 'IMG_4932.JPG', 50000, '2020-12-11T18:02', 3, 3),
('Hat', 7, 'This is hat used by Ladi Diana.', 'IMG_4932.JPG', 10000, '2020-12-18T18:28', 4, 6);

-- --------------------------------------------------------

--
-- Table structure for table `bids`
--

CREATE TABLE `bids` (
  `id` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  `productid` int(11) NOT NULL,
  `bidamount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `bids`
--

INSERT INTO `bids` (`id`, `userid`, `productid`, `bidamount`) VALUES
(1, 3, 1, 50000),
(2, 3, 2, 50000),
(5, 4, 1, 99999),
(6, 3, 3, 55000),
(8, 6, 3, 60000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admins`
--
ALTER TABLE `admins`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `all_accounts`
--
ALTER TABLE `all_accounts`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `all_products`
--
ALTER TABLE `all_products`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bids`
--
ALTER TABLE `bids`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admins`
--
ALTER TABLE `admins`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `all_accounts`
--
ALTER TABLE `all_accounts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `all_products`
--
ALTER TABLE `all_products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `bids`
--
ALTER TABLE `bids`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
