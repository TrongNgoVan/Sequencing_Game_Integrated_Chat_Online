-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 14, 2024 lúc 01:09 PM
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
('16GLw', 'Vũ Ngọc Sơn', 'Ngọ Văn Trọng', 'DRAW', 0.5, '2024-11-03 15:57:45', 0.5),
('2HSgd', 'Ngọ Văn Trọng', 'Vũ Ngọc Sơn', 'DRAW', 0.5, '2024-11-03 15:59:38', 0.5),
('33Z5U', 'Ngọ Văn Trọng', 'Nguyễn Như Thiệu', 'Ngọ Văn Trọng', 1, '2024-11-04 07:54:17', 0),
('5uNoe', 'Vũ Ngọc Sơn', 'Ngọ Văn Trọng', 'DRAW', 0.5, '2024-10-29 15:29:16', 0.5),
('64kiU', 'Vũ Ngọc Sơn', 'Ngọ Văn Trọng', 'DRAW', 0.5, '2024-11-03 17:18:13', 0.5),
('6ikCl', 'Ngọ Văn Trọng', 'Vũ Ngọc Sơn', 'Ngọ Văn Trọng', 1, '2024-11-04 03:14:30', 0),
('9hWpy', 'Ngọ Văn Trọng', 'Vũ Ngọc Sơn', 'Ngọ Văn Trọng', 1, '2024-11-04 09:46:26', 0),
('A4XAy', 'Nguyễn Hoàng Hải', 'Ngọ Văn Trọng', 'DRAW', 0.5, '2024-10-31 09:37:44', 0.5),
('AMZH8', 'Nguyễn Viết Quang', 'Ngọ Văn Trọng', 'DRAW', 0.5, '2024-11-03 22:23:59', 0.5),
('B12ql', 'Vũ Ngọc Sơn', 'Nguyễn Như Thiệu', 'Vũ Ngọc Sơn', 1, '2024-11-04 07:37:29', 0),
('bpytI', 'Ngọ Văn Trọng', 'Vũ Ngọc Sơn', 'Vũ Ngọc Sơn', 1, '2024-10-29 11:08:42', 0),
('d9Ukm', 'Vũ Ngọc Sơn', 'Ngọ Văn Trọng', 'Vũ Ngọc Sơn', 1, '2024-10-29 15:27:56', 0),
('DdObs', 'Vũ Ngọc Sơn', 'Ngọ Văn Trọng', 'DRAW', 0.5, '2024-11-14 18:48:46', 0.5),
('dYqbn', 'Ngọ Văn Trọng', 'Vũ Ngọc Sơn', 'Ngọ Văn Trọng', 1, '2024-10-29 13:45:08', 0),
('fkuNW', 'Ngọ Văn Trọng', 'Vũ Ngọc Sơn', 'Ngọ Văn Trọng', 1, '2024-11-04 09:47:14', 0),
('fwSQ2', 'Vũ Ngọc Sơn', 'Ngọ Văn Trọng', 'DRAW', 0.5, '2024-11-12 15:29:37', 0.5),
('G7jJ4', 'Ngọ Văn Trọng', 'Vũ Ngọc Sơn', 'Ngọ Văn Trọng', 1, '2024-10-31 04:08:17', 0),
('gnwpA', 'Ngọ Văn Trọng', 'Nguyễn Viết Quang', 'Ngọ Văn Trọng', 1, '2024-11-03 22:51:23', 0),
('HNqg4', 'Ngọ Văn Trọng', 'Vũ Ngọc Sơn', 'DRAW', 0.5, '2024-11-03 20:58:56', 0.5),
('i9Pjs', 'Nguyễn Như Thiệu', 'Ngọ Văn Trọng', 'Nguyễn Như Thiệu', 1, '2024-11-03 22:39:05', 0),
('IVcgl', 'Nguyễn Như Thiệu', 'Ngọ Văn Trọng', 'Nguyễn Như Thiệu', 1, '2024-11-04 00:01:02', 0),
('J9VtT', 'Nguyễn Viết Quang', 'Ngọ Văn Trọng', 'DRAW', 0.5, '2024-11-03 22:29:06', 0.5),
('JwrEX', 'Ngọ Văn Trọng', 'Vũ Ngọc Sơn', 'Vũ Ngọc Sơn', 1, '2024-11-04 07:57:21', 0),
('KHpJB', 'Ngọ Văn Trọng', 'Vũ Ngọc Sơn', 'DRAW', 0.5, '2024-11-04 08:03:16', 0.5),
('NBUu4', 'Vũ Ngọc Sơn', 'Ngọ Văn Trọng', 'Vũ Ngọc Sơn', 1, '2024-11-04 10:01:06', 0),
('ODDsd', 'Nguyễn Như Thiệu', 'Ngọ Văn Trọng', 'Nguyễn Như Thiệu', 1, '2024-11-03 21:03:39', 0),
('pCNS6', 'Ngọ Văn Trọng', 'Nguyễn Hoàng Hải', 'Ngọ Văn Trọng', 1, '2024-11-03 22:54:57', 0),
('QO7tz', 'Nguyễn Viết Quang', 'Ngọ Văn Trọng', 'DRAW', 0.5, '2024-11-03 21:52:05', 0.5),
('RnlYk', 'Nguyễn Hoàng Hải', 'Ngọ Văn Trọng', 'DRAW', 0.5, '2024-10-31 09:39:17', 0.5),
('ScGCi', 'Vũ Ngọc Sơn', 'Ngọ Văn Trọng', 'Vũ Ngọc Sơn', 1, '2024-10-31 04:02:18', 0),
('ShDqm', 'Nguyễn Hoàng Hải', 'Ngọ Văn Trọng', 'DRAW', 0.5, '2024-11-03 09:13:17', 0.5),
('SUUJZ', 'Vũ Ngọc Sơn', 'Ngọ Văn Trọng', 'DRAW', 0.5, '2024-11-12 10:50:54', 0.5),
('Tbf2X', 'Vũ Ngọc Sơn', 'Ngọ Văn Trọng', 'DRAW', 0.5, '2024-11-03 19:31:28', 0.5),
('UDaNZ', 'Ngọ Văn Trọng', 'Nguyễn Như Thiệu', 'DRAW', 0.5, '2024-11-03 22:47:36', 0.5),
('uUQ1j', 'Nguyễn Như Thiệu', 'Ngọ Văn Trọng', 'Nguyễn Như Thiệu', 1, '2024-11-04 07:45:51', 0),
('vUg52', 'Ngọ Văn Trọng', 'Nguyễn Như Thiệu', 'DRAW', 0.5, '2024-11-03 22:31:45', 0.5),
('W2eFT', 'Vũ Ngọc Sơn', 'Ngọ Văn Trọng', 'Vũ Ngọc Sơn', 1, '2024-11-04 08:09:07', 0),
('wMRAP', 'Ngọ Văn Trọng', 'Nguyễn Hoàng Hải', 'Ngọ Văn Trọng', 1, '2024-11-04 03:28:09', 0),
('X9fbz', 'Vũ Ngọc Sơn', 'Ngọ Văn Trọng', 'Vũ Ngọc Sơn', 1, '2024-11-04 09:45:19', 0),
('yK8Lh', 'Vũ Ngọc Sơn', 'Ngọ Văn Trọng', 'DRAW', 0.5, '2024-11-03 18:57:53', 0.5),
('yWGKD', 'Nguyễn Như Thiệu', 'Ngọ Văn Trọng', 'Nguyễn Như Thiệu', 1, '2024-11-03 22:41:38', 0),
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
(1, 'Ngọ Văn Trọng', '123', 65, 31, 68, 34, 9.59047),
(2, 'Nguyễn Như Thiệu', '123', 23, 14, 18, 21, 14.4184),
(3, 'Nguyễn Viết Quang', '123', 5, 1, 8, 1, 0),
(4, 'Vũ Ngọc Sơn', '123', 39.5, 25, 29, 14, 12.6885),
(6, 'Trương Vĩnh Tiến', '1234', 2.5, 0, 5, 3, 0),
(7, 'Nguyễn Mạnh Hùng', '1234', 0.5, 0, 1, 0, 0),
(8, 'Nguyễn Mạnh Quý', '123', 0.5, 0, 1, 0, 0),
(9, 'Nguyễn Bá Hoàng Huynh', '123', 1.5, 1, 1, 0, 0),
(10, 'Nguyễn Hoàng Hải', '123', 4, 1, 6, 3, 0),
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
