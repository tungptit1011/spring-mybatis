-- Phòng ban
INSERT INTO `mst_group` (`group_id`, `group_name`) VALUES
(1, 'Phòng phát triển số 1'),
(2, 'Phòng phát triển số 2'),
(3, 'Phòng phát triển số 3'),
(4, 'Phòng phát triển số 4'),
(5, 'Phòng phát triển số 5');

-- Trình độ tiếng nhật
INSERT INTO `mst_japanse` (`code_level`, `name_level`) VALUES
('N1', 'Trình độ tiếng nhật cấp 1'),
('N2', 'Trình độ tiếng nhật cấp 2'),
('N3', 'Trình độ tiếng nhật cấp 3'),
('N4', 'Trình độ tiếng nhật cấp 4'),
('N5', 'Trình độ tiếng nhật cấp 5'),
('N7', 'Trình độ tiếng nhật cấp 6');


INSERT INTO `tbl_user` (`user_id`, `group_id`, `login_name`, `password`, `full_name`, `full_name_kana`, `email`, `tel`, `birthday`, `rule`) VALUES
 (1, 1, 'tung1', '$2a$10$4/hBXdZBkjP3WEQ09lJeNeAIOSUv4PeL7grRFNRAAH46uiHSFoYGW', 'Nguyễn Thị Mai \\%Hương', 'Hương Kana', 'huong@gmail.com', '123456789', '1983-07-08', 'ROLE_ADMIN'),
 (2, 1, 'hieudt', '6efa2190956f362117b0b7d66d8bb15911a8b333', 'Đoàn Trọng %Hiếu', '%Hiếu Kana', 'hieu@gmail.com', '987456123', '1983-08-08', 'ROLE_ADMIN'),
 (4, 2, 'dungdv', '6efa2190956f362117b0b7d66d8bb15911a8b333', 'Đỗ Văn _Dũng', 'Dũng Kana', 'dung@gmail.com', '789123456', '1983-10-08', 'ROLE_ADMIN'),
 (5, 3, 'phuongnv', '6efa2190956f362117b0b7d66d8bb15911a8b333', 'Nguyễn Việt Phương aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa', 'Phương Kana', 'phuong@gmail.com', '456987213', '1983-11-08', 'ROLE_ADMIN'),
 (6, 1, 'dung', '6efa2190956f362117b0b7d66d8bb15911a8b333', 'Nguyễn Thị Mai ;Hương', 'Hương Kana', 'huong@gmail.com', '123456789', '1983-07-08', 'ROLE_ADMIN'),
 (7, 1, 'hieudt', '6efa2190956f362117b0b7d66d8bb15911a8b333', 'Đoàn; Trọng Hiếu', 'Hiếu Kana', 'hieu@gmail.com', '987456123', '1983-08-08', 'ROLE_ADMIN'),
 (8, 2, 'longth', '6efa2190956f362117b0b7d66d8bb15911a8b333', 'Trần Hoàng /Long', 'Long Kana', 'long@gmail.com', '654123789', '1983-09-08', 'ROLE_ADMIN'),
 (9, 2, 'dungdv', '6efa2190956f362117b0b7d66d8bb15911a8b333', 'Đỗ Văn /Dũng', 'Dũng Kana', 'dung@gmail.com', '789123456', '1983-10-08', 'ROLE_ADMIN'),
 (10, 3, 'phuongnv', '6efa2190956f362117b0b7d66d8bb15911a8b333', 'Nguyễn Việt \\Phương', 'Phương Kana', 'phuong@gmail.com', '456987213', '1983-11-08', 'ROLE_ADMIN'),
 (11, 1, 'hoa', '6efa2190956f362117b0b7d66d8bb15911a8b333', 'Nguyễn Thị Mai \\Hương', 'Hương \\Kana', 'huong@gmail.com', '123456789', '1983-07-08', 'ROLE_ADMIN'),
 (13, 2, 'longth', '6efa2190956f362117b0b7d66d8bb15911a8b333', 'Trần Hoàng Long', 'Long Kana', 'long@gmail.com', '654123789', '1983-09-08', 'ROLE_ADMIN'),
 (14, 2, 'dungdv', '6efa2190956f362117b0b7d66d8bb15911a8b333', 'Đỗ Văn Dũng', 'Dũng Kana', 'dung@gmail.com', '789123456', '1983-10-08', 'ROLE_ADMIN'),
 (15, 3, 'phuongnv', '6efa2190956f362117b0b7d66d8bb15911a8b333', 'Nguyễn Việt Phương', 'Phương Kana', 'phuong@gmail.com', '456987213', '1983-11-08', 'ROLE_ADMIN'),
 (16, 1, 'ntmhuong', '6efa2190956f362117b0b7d66d8bb15911a8b333', 'Nguyễn Thị Mai Hương', 'Hương Kana', 'huong@gmail.com', '123456789', '1983-07-08', 'ROLE_ADMIN'),
 (17, 1, 'hieudt', '6efa2190956f362117b0b7d66d8bb15911a8b333', 'Đoàn Trọng Hiếu', 'Hiếu Kana', 'hieu@gmail.com', '987456123', '1983-08-08', 'ROLE_ADMIN'),
 (18, 2, 'longth', '6efa2190956f362117b0b7d66d8bb15911a8b333', 'Trần Hoàng Long', 'Long Kana', 'long@gmail.com', '654123789', '1983-09-08', 'ROLE_ADMIN'),
 (19, 2, 'dungdv', '6efa2190956f362117b0b7d66d8bb15911a8b333', 'Đỗ Văn Dũng', 'Dũng Kana', 'dung@gmail.com', '789123456', '1983-10-08', 'ROLE_ADMIN'),
 (20, 3, 'phuongnv', '6efa2190956f362117b0b7d66d8bb15911a8b333', 'Nguyễn Việt Phương', 'Phương Kana', 'phuong@gmail.com', '456987213', '1983-11-08', 'ROLE_ADMIN'),
 (21, 1, 'ha', '6efa2190956f362117b0b7d66d8bb15911a8b333', 'Nguyễn Thị Mai ;Hương', 'Hương Kana', 'huong@gmail.com', '123456789', '1983-07-08', 'ROLE_ADMIN'),
 (22, 1, 'hieudt', '6efa2190956f362117b0b7d66d8bb15911a8b333', 'Đoàn Trọng Hiếu', 'Hiếu Kana', 'hieu@gmail.com', '987456123', '1983-08-08', 'ROLE_ADMIN'),
 (23, 2, 'longth', '6efa2190956f362117b0b7d66d8bb15911a8b333', 'Trần Hoàng Long', 'Long Kana', 'long@gmail.com', '654123789', '1983-09-08', 'ROLE_ADMIN'),
 (24, 2, 'dungdv', '6efa2190956f362117b0b7d66d8bb15911a8b333', 'Đỗ Văn Dũng', 'Dũng Kana', 'dung@gmail.com', '789123456', '1983-10-08', 'ROLE_ADMIN'),
 (25, 3, 'phuongnv', '6efa2190956f362117b0b7d66d8bb15911a8b333', 'Nguyễn Việt Phương', 'Phương Kana', 'phuong@gmail.com', '456987213', '1983-11-08', 'ROLE_ADMIN'),
 (26, 1, 'ntmhuong', '6efa2190956f362117b0b7d66d8bb15911a8b333', 'Nguyễn Thị Mai Hương', 'Hương Kana', 'huong@gmail.com', '123456789', '1983-07-08', 'ROLE_ADMIN'),
 (27, 1, 'hieudt', '6efa2190956f362117b0b7d66d8bb15911a8b333', 'Đoàn Trọng Hiếu', 'Hiếu Kana', 'hieu@gmail.com', '987456123', '1983-08-08', 'ROLE_ADMIN'),
 (28, 2, 'longth', '6efa2190956f362117b0b7d66d8bb15911a8b333', 'Trần %Hoàng Long', 'Long Kana', 'long@gmail.com', '654123789', '1983-09-08', 'ROLE_ADMIN'),
 (29, 2, 'dungdv', '6efa2190956f362117b0b7d66d8bb15911a8b333', 'Đỗ Văn ;Dũng', 'Dũng Kana', 'dung@gmail.com', '789123456', '1983-10-08', 'ROLE_ADMIN'),
 (30, 3, 'phuongnv', '6efa2190956f362117b0b7d66d8bb15911a8b333', 'Nguyễn Việt Phương', 'Phương Kana', 'phuong@gmail.com', '456987213', '1983-11-08', 'ROLE_ADMIN'),
 (32, 1, 'hieudt', '6efa2190956f362117b0b7d66d8bb15911a8b333', 'Đoàn Trọng Hiếu', 'Hiếu Kana', 'hieu@gmail.com', '987456123', '1983-08-08', 'ROLE_ADMIN'),
 (33, 2, 'longth', '6efa2190956f362117b0b7d66d8bb15911a8b333', 'Trần Hoàng Long', 'Long Kana', 'long@gmail.com', '654123789', '1983-09-08', 'ROLE_ADMIN');



 INSERT INTO `tbl_detail_user_japan` (`detail_user_japan_id`, `user_id`, `code_level`, `start_date`, `end_date`, `total`) VALUES
 (2, 4, 'N1', '2005-05-20', '2006-05-20', 90),
 (3, 5, 'N5', '2006-05-20', '2007-05-20', 100),
 (4, 6, 'N2', '2004-06-10', '2008-10-11', 110),
 (5, 7, 'N3', '2006-05-17', '2021-01-19', 120),
 (6, 8, 'N4', '2005-02-08', '2020-01-20', 150),
 (7, 9, 'N5', '2007-08-09', '2021-01-30', 200),
 (8, 10, 'N1', '2008-01-19', '2020-10-11', 250),
 (9, 11, 'N2', '2009-02-10', '2020-11-08', 100),
 (11, 2, 'N1', '2005-01-08', '2005-01-08', 20),
 (26, 4, 'N2', '2005-08-09', '2006-09-09', 22);
