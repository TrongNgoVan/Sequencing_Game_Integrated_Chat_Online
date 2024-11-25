-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 25, 2024 lúc 05:44 AM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `game_sort_chat`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `matchs`
--

CREATE TABLE `matchs` (
  `id_match` varchar(100) NOT NULL,
  `user1` varchar(100) NOT NULL,
  `user2` varchar(100) NOT NULL,
  `user_win` varchar(100) NOT NULL,
  `score_win` float NOT NULL,
  `time_begin` datetime NOT NULL,
  `score_lose` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `matchs`
--

INSERT INTO `matchs` (`id_match`, `user1`, `user2`, `user_win`, `score_win`, `time_begin`, `score_lose`) VALUES
('16GLw', 'vungocson', 'ngovantrong', 'DRAW', 0.5, '2024-11-03 15:57:45', 0.5),
('2HSgd', 'ngovantrong', 'vungocson', 'DRAW', 0.5, '2024-11-03 15:59:38', 0.5),
('33Z5U', 'ngovantrong', 'nguyennhuthieu', 'ngovantrong', 1, '2024-11-04 07:54:17', 0),
('4ggpQ', 'vungocson', 'ngovantrong', 'DRAW', 0.5, '2024-11-23 20:54:21', 0.5),
('5uNoe', 'vungocson', 'ngovantrong', 'DRAW', 0.5, '2024-10-29 15:29:16', 0.5),
('64kiU', 'vungocson', 'ngovantrong', 'DRAW', 0.5, '2024-11-03 17:18:13', 0.5),
('6ikCl', 'ngovantrong', 'vungocson', 'ngovantrong', 1, '2024-11-04 03:14:30', 0),
('6jmPE', 'vungocson', 'ngovantrong', 'DRAW', 0.5, '2024-11-23 20:39:08', 0.5),
('8P732', 'vungocson', 'ngovantrong', 'DRAW', 0.5, '2024-11-23 21:58:48', 0.5),
('9hWpy', 'ngovantrong', 'vungocson', 'ngovantrong', 1, '2024-11-04 09:46:26', 0),
('A4XAy', 'nguyenhoanghai', 'ngovantrong', 'DRAW', 0.5, '2024-10-31 09:37:44', 0.5),
('AMZH8', 'nguyenvietquang', 'ngovantrong', 'DRAW', 0.5, '2024-11-03 22:23:59', 0.5),
('B12ql', 'vungocson', 'nguyennhuthieu', 'vungocson', 1, '2024-11-04 07:37:29', 0),
('b7uSQ', 'ngovantrong', 'vungocson', 'DRAW', 0.5, '2024-11-23 17:33:06', 0.5),
('BjEjn', 'vungocson', 'ngovantrong', 'DRAW', 0.5, '2024-11-23 21:01:21', 0.5),
('bpytI', 'ngovantrong', 'vungocson', 'vungocson', 1, '2024-10-29 11:08:42', 0),
('C5Mzb', 'vungocson', 'ngovantrong', 'vungocson', 1, '2024-11-25 09:25:48', 0),
('cTIPH', 'ngovantrong', 'vungocson', 'ngovantrong', 1, '2024-11-23 21:23:28', 0),
('cwYYT', 'ngovantrong', 'vungocson', 'DRAW', 0.5, '2024-11-23 17:32:12', 0.5),
('d9Ukm', 'vungocson', 'ngovantrong', 'vungocson', 1, '2024-10-29 15:27:56', 0),
('DdObs', 'vungocson', 'ngovantrong', 'DRAW', 0.5, '2024-11-14 18:48:46', 0.5),
('DG0PH', 'nguyennhuthieu', 'vungocson', 'DRAW', 0.5, '2024-11-25 09:09:02', 0.5),
('djrIj', 'ngovantrong', 'vungocson', 'DRAW', 0.5, '2024-11-24 12:19:46', 0.5),
('dYqbn', 'ngovantrong', 'vungocson', 'ngovantrong', 1, '2024-10-29 13:45:08', 0),
('fkuNW', 'ngovantrong', 'vungocson', 'ngovantrong', 1, '2024-11-04 09:47:14', 0),
('Fumot', 'ngovantrong', 'vungocson', 'DRAW', 0.5, '2024-11-25 09:40:29', 0.5),
('fwSQ2', 'vungocson', 'ngovantrong', 'DRAW', 0.5, '2024-11-12 15:29:37', 0.5),
('fZpN4', 'ngovantrong', 'vungocson', 'ngovantrong', 1, '2024-11-25 09:13:27', 0),
('G7jJ4', 'ngovantrong', 'vungocson', 'ngovantrong', 1, '2024-10-31 04:08:17', 0),
('gdrxF', 'Ngọ Văn Trọng', 'Vũ Ngọc Sơn', 'DRAW', 0.5, '2024-11-24 12:17:21', 0.5),
('gnwpA', 'Ngọ Văn Trọng', 'Nguyễn Viết Quang', 'Ngọ Văn Trọng', 1, '2024-11-03 22:51:23', 0),
('HNqg4', 'Ngọ Văn Trọng', 'Vũ Ngọc Sơn', 'DRAW', 0.5, '2024-11-03 20:58:56', 0.5),
('HxFMp', 'Ngọ Văn Trọng', 'Vũ Ngọc Sơn', 'DRAW', 0.5, '2024-11-24 12:18:19', 0.5),
('hzqwD', 'nguyennhuthieu', 'ngovantrong', 'nguyennhuthieu', 1, '2024-11-25 09:20:59', 0),
('i9Pjs', 'Nguyễn Như Thiệu', 'Ngọ Văn Trọng', 'Nguyễn Như Thiệu', 1, '2024-11-03 22:39:05', 0),
('IN2Sg', 'Vũ Ngọc Sơn', 'Ngọ Văn Trọng', 'DRAW', 0.5, '2024-11-23 20:48:08', 0.5),
('itcx3', 'Vũ Ngọc Sơn', 'Ngọ Văn Trọng', 'DRAW', 0.5, '2024-11-23 20:34:22', 0.5),
('IVcgl', 'Nguyễn Như Thiệu', 'Ngọ Văn Trọng', 'Nguyễn Như Thiệu', 1, '2024-11-04 00:01:02', 0),
('J9VtT', 'Nguyễn Viết Quang', 'Ngọ Văn Trọng', 'DRAW', 0.5, '2024-11-03 22:29:06', 0.5),
('JwrEX', 'Ngọ Văn Trọng', 'Vũ Ngọc Sơn', 'Vũ Ngọc Sơn', 1, '2024-11-04 07:57:21', 0),
('KHpJB', 'Ngọ Văn Trọng', 'Vũ Ngọc Sơn', 'DRAW', 0.5, '2024-11-04 08:03:16', 0.5),
('llTnP', 'ngovantrong', 'vungocson', 'DRAW', 0.5, '2024-11-25 08:41:23', 0.5),
('lRZ9j', 'Vũ Ngọc Sơn', 'Ngọ Văn Trọng', 'DRAW', 0.5, '2024-11-23 21:05:09', 0.5),
('lzi90', 'Vũ Ngọc Sơn', 'Ngọ Văn Trọng', 'DRAW', 0.5, '2024-11-23 22:04:41', 0.5),
('MkoEz', 'Vũ Ngọc Sơn', 'Ngọ Văn Trọng', 'DRAW', 0.5, '2024-11-23 17:10:50', 0.5),
('mp0Ac', 'Vũ Ngọc Sơn', 'Ngọ Văn Trọng', 'Vũ Ngọc Sơn', 1, '2024-11-24 12:22:13', 0),
('NBUu4', 'Vũ Ngọc Sơn', 'Ngọ Văn Trọng', 'Vũ Ngọc Sơn', 1, '2024-11-04 10:01:06', 0),
('nFrsA', 'ngovantrong', 'vungocson', 'ngovantrong', 1, '2024-11-25 09:12:39', 0),
('NJpTi', 'Vũ Ngọc Sơn', 'Ngọ Văn Trọng', 'Vũ Ngọc Sơn', 1, '2024-11-24 12:23:10', 0),
('NvVsb', 'Vũ Ngọc Sơn', 'Ngọ Văn Trọng', 'DRAW', 0.5, '2024-11-23 21:22:03', 0.5),
('ODDsd', 'Nguyễn Như Thiệu', 'Ngọ Văn Trọng', 'Nguyễn Như Thiệu', 1, '2024-11-03 21:03:39', 0),
('pCNS6', 'Ngọ Văn Trọng', 'Nguyễn Hoàng Hải', 'Ngọ Văn Trọng', 1, '2024-11-03 22:54:57', 0),
('QcEDM', 'Vũ Ngọc Sơn', 'Ngọ Văn Trọng', 'DRAW', 0.5, '2024-11-23 15:28:47', 0.5),
('QO7tz', 'Nguyễn Viết Quang', 'Ngọ Văn Trọng', 'DRAW', 0.5, '2024-11-03 21:52:05', 0.5),
('RaWD4', 'Ngọ Văn Trọng', 'Vũ Ngọc Sơn', 'DRAW', 0.5, '2024-11-23 21:34:06', 0.5),
('RnlYk', 'Nguyễn Hoàng Hải', 'Ngọ Văn Trọng', 'DRAW', 0.5, '2024-10-31 09:39:17', 0.5),
('rqgMD', 'Ngọ Văn Trọng', 'Vũ Ngọc Sơn', 'Ngọ Văn Trọng', 1, '2024-11-24 12:19:04', 0),
('ScGCi', 'Vũ Ngọc Sơn', 'Ngọ Văn Trọng', 'Vũ Ngọc Sơn', 1, '2024-10-31 04:02:18', 0),
('ShDqm', 'Nguyễn Hoàng Hải', 'Ngọ Văn Trọng', 'DRAW', 0.5, '2024-11-03 09:13:17', 0.5),
('ShVLc', 'Ngọ Văn Trọng', 'Vũ Ngọc Sơn', 'DRAW', 0.5, '2024-11-23 17:41:40', 0.5),
('SN8oA', 'Ngọ Văn Trọng', 'Vũ Ngọc Sơn', 'DRAW', 0.5, '2024-11-23 20:49:34', 0.5),
('sOhez', 'Vũ Ngọc Sơn', 'Ngọ Văn Trọng', 'Vũ Ngọc Sơn', 1, '2024-11-23 21:44:47', 0),
('SUUJZ', 'Vũ Ngọc Sơn', 'Ngọ Văn Trọng', 'DRAW', 0.5, '2024-11-12 10:50:54', 0.5),
('SVRyC', 'vungocson', 'ngovantrong', 'vungocson', 1, '2024-11-25 09:41:38', 0),
('Tbf2X', 'Vũ Ngọc Sơn', 'Ngọ Văn Trọng', 'DRAW', 0.5, '2024-11-03 19:31:28', 0.5),
('UDaNZ', 'Ngọ Văn Trọng', 'Nguyễn Như Thiệu', 'DRAW', 0.5, '2024-11-03 22:47:36', 0.5),
('ujc0g', 'vungocson', 'nguyennhuthieu', 'DRAW', 0.5, '2024-11-25 09:18:08', 0.5),
('uUQ1j', 'Nguyễn Như Thiệu', 'Ngọ Văn Trọng', 'Nguyễn Như Thiệu', 1, '2024-11-04 07:45:51', 0),
('vmRiz', 'ngovantrong', 'vungocson', 'DRAW', 0.5, '2024-11-25 09:16:55', 0.5),
('vUg52', 'Ngọ Văn Trọng', 'Nguyễn Như Thiệu', 'DRAW', 0.5, '2024-11-03 22:31:45', 0.5),
('W2eFT', 'Vũ Ngọc Sơn', 'Ngọ Văn Trọng', 'Vũ Ngọc Sơn', 1, '2024-11-04 08:09:07', 0),
('wMRAP', 'Ngọ Văn Trọng', 'Nguyễn Hoàng Hải', 'Ngọ Văn Trọng', 1, '2024-11-04 03:28:09', 0),
('X9fbz', 'Vũ Ngọc Sơn', 'Ngọ Văn Trọng', 'Vũ Ngọc Sơn', 1, '2024-11-04 09:45:19', 0),
('YI3cq', 'Ngọ Văn Trọng', 'Vũ Ngọc Sơn', 'DRAW', 0.5, '2024-11-24 12:14:42', 0.5),
('yK8Lh', 'Vũ Ngọc Sơn', 'Ngọ Văn Trọng', 'DRAW', 0.5, '2024-11-03 18:57:53', 0.5),
('YOf3O', 'Vũ Ngọc Sơn', 'Ngọ Văn Trọng', 'Vũ Ngọc Sơn', 1, '2024-11-23 17:30:43', 0),
('yWGKD', 'Nguyễn Như Thiệu', 'Ngọ Văn Trọng', 'Nguyễn Như Thiệu', 1, '2024-11-03 22:41:38', 0),
('z8MJY', 'Vũ Ngọc Sơn', 'Ngọ Văn Trọng', 'Vũ Ngọc Sơn', 1, '2024-11-23 21:42:02', 0),
('zL6kX', 'Vũ Ngọc Sơn', 'Ngọ Văn Trọng', 'Vũ Ngọc Sơn', 1, '2024-11-04 03:13:04', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users`
--

CREATE TABLE `users` (
  `userId` int(11) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `score` float NOT NULL,
  `win` int(11) NOT NULL,
  `draw` int(11) NOT NULL,
  `lose` int(11) NOT NULL,
  `avgTime` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `users`
--

INSERT INTO `users` (`userId`, `username`, `password`, `score`, `win`, `draw`, `lose`, `avgTime`) VALUES
(1, 'ngovantrong', '123', 106, 51, 110, 46, 9.38696),
(2, 'nguyennhuthieu', '123', 25.5, 15, 21, 21, 14.5836),
(3, 'nguyenvietquang', '123', 5, 1, 8, 1, 0),
(4, 'vungocson', '123', 73, 36, 74, 34, 13.3277),
(6, 'truongvinhtien', '1234', 2.5, 0, 5, 3, 0),
(7, 'nguyenmanhhung', '1234', 0.5, 0, 1, 0, 0),
(8, 'nguyenmanhquy', '123', 0.5, 0, 1, 0, 0),
(9, 'ngyenbahoanghuynh', '123', 1.5, 1, 1, 0, 0),
(10, 'nguyenhoanghai', '123', 4, 1, 6, 3, 0),
(11, 't', '123', 0, 0, 0, 0, 0),
(12, 'a', '123', 0, 0, 0, 0, 0);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `matchs`
--
ALTER TABLE `matchs`
  ADD PRIMARY KEY (`id_match`);

--
-- Chỉ mục cho bảng `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`userId`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `users`
--
ALTER TABLE `users`
  MODIFY `userId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
