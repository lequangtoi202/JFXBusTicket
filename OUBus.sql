-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: bus_ticket
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ben_xe`
--

DROP TABLE IF EXISTS `ben_xe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ben_xe` (
  `Ma_Ben` int NOT NULL AUTO_INCREMENT,
  `Ten_Ben_Xe` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`Ma_Ben`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `chuyen_xe`
--

DROP TABLE IF EXISTS `chuyen_xe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chuyen_xe` (
  `Ma_Chuyen_Xe` int NOT NULL AUTO_INCREMENT,
  `Ten_Chuyen_Xe` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `Thoi_gian_di` datetime DEFAULT NULL,
  `Ma_Tuyen_Xe` int NOT NULL,
  `Ma_tai_xe` int NOT NULL,
  `is_updated` bit(1) NOT NULL,
  PRIMARY KEY (`Ma_Chuyen_Xe`),
  KEY `Ma_tuyen_xe_idx` (`Ma_Tuyen_Xe`),
  KEY `Ma_tai_xe_idx` (`Ma_tai_xe`),
  CONSTRAINT `Ma_tai_xe` FOREIGN KEY (`Ma_tai_xe`) REFERENCES `tai_xe` (`Ma_tx`),
  CONSTRAINT `Ma_tuyen_xe` FOREIGN KEY (`Ma_Tuyen_Xe`) REFERENCES `tuyen_xe` (`Ma_Tuyen_Xe`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ghe`
--

DROP TABLE IF EXISTS `ghe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ghe` (
  `Ma_ghe` int NOT NULL AUTO_INCREMENT,
  `So_ghe` varchar(3) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Trang_thai` enum('Empty','Selected') COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Ma_xe` int DEFAULT NULL,
  PRIMARY KEY (`Ma_ghe`),
  KEY `fk_ma_xe_idx` (`Ma_xe`),
  CONSTRAINT `fk_ma_xe` FOREIGN KEY (`Ma_xe`) REFERENCES `xe` (`Ma_xe`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=183 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `khach_hang`
--

DROP TABLE IF EXISTS `khach_hang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `khach_hang` (
  `Ma_KH` int NOT NULL AUTO_INCREMENT,
  `Ten_KH` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `NgaySinh` datetime DEFAULT NULL,
  `GioiTinh` bit(1) DEFAULT NULL,
  `DienThoai` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `DiaChi` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `CCCD` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  PRIMARY KEY (`Ma_KH`)
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `loai_xe`
--

DROP TABLE IF EXISTS `loai_xe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loai_xe` (
  `Ma_LoaiXe` int NOT NULL AUTO_INCREMENT,
  `Ten_LoaiXe` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  PRIMARY KEY (`Ma_LoaiXe`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `nhan_vien`
--

DROP TABLE IF EXISTS `nhan_vien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhan_vien` (
  `Ma_NV` int NOT NULL AUTO_INCREMENT,
  `Ten_NV` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `NgaySinh_NV` datetime DEFAULT NULL,
  `GioiTinh_NV` bit(1) DEFAULT NULL,
  `DiaChi_NV` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `CCCD_NV` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `DienThoai` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `Email` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `Ma_User` int NOT NULL,
  PRIMARY KEY (`Ma_NV`,`Ma_User`),
  KEY `fk_nhanvien_user1_idx` (`Ma_User`),
  CONSTRAINT `fk_nhanvien_user1` FOREIGN KEY (`Ma_User`) REFERENCES `user` (`Ma_User`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tai_xe`
--

DROP TABLE IF EXISTS `tai_xe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tai_xe` (
  `Ma_tx` int NOT NULL AUTO_INCREMENT,
  `Ten_tx` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `GioiTinh_tx` bit(1) DEFAULT NULL,
  `NgaySinh_tx` datetime DEFAULT NULL,
  `DiaChi_tx` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `DienThoai_tx` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `CCCD` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `Email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  PRIMARY KEY (`Ma_tx`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tuyen_xe`
--

DROP TABLE IF EXISTS `tuyen_xe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tuyen_xe` (
  `Ma_Tuyen_Xe` int NOT NULL AUTO_INCREMENT,
  `Ten_Tuyen_Xe` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `BangGia` float DEFAULT NULL,
  `Ma_ben_di` int NOT NULL,
  `Ma_ben_den` int NOT NULL,
  PRIMARY KEY (`Ma_Tuyen_Xe`),
  KEY `fk_ben_di_idx` (`Ma_ben_di`),
  KEY `fk_ben_den_idx` (`Ma_ben_den`),
  CONSTRAINT `fk_ben_den` FOREIGN KEY (`Ma_ben_den`) REFERENCES `ben_xe` (`Ma_Ben`) ON DELETE CASCADE,
  CONSTRAINT `fk_ben_di` FOREIGN KEY (`Ma_ben_di`) REFERENCES `ben_xe` (`Ma_Ben`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `Ma_User` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`Ma_User`),
  KEY `fk_user_role1_idx` (`role_id`),
  CONSTRAINT `fk_user_role1` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ve_xe`
--

DROP TABLE IF EXISTS `ve_xe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ve_xe` (
  `Ma_Ve_Xe` int NOT NULL AUTO_INCREMENT,
  `Gio_ban` datetime DEFAULT NULL,
  `Trang_thai` enum('Done','Canceled','Booked','Retrieved') COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `Ma_nv` int NOT NULL,
  `Ma_kh` int NOT NULL,
  `Ma_chuyen_xe` int NOT NULL,
  `Ma_ghe` int NOT NULL,
  PRIMARY KEY (`Ma_Ve_Xe`),
  KEY `Ma_chuyen_xe_idx` (`Ma_chuyen_xe`),
  KEY `Ma_kh_idx` (`Ma_kh`),
  KEY `MaKH_idx` (`Ma_kh`),
  KEY `MaGhe_idx` (`Ma_ghe`),
  KEY `MaNV_idx` (`Ma_nv`),
  CONSTRAINT `Ma_chuyen_xe` FOREIGN KEY (`Ma_chuyen_xe`) REFERENCES `chuyen_xe` (`Ma_Chuyen_Xe`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `MaGhe` FOREIGN KEY (`Ma_ghe`) REFERENCES `ghe` (`Ma_ghe`) ON DELETE CASCADE,
  CONSTRAINT `MaKH` FOREIGN KEY (`Ma_kh`) REFERENCES `khach_hang` (`Ma_KH`),
  CONSTRAINT `MaNV` FOREIGN KEY (`Ma_nv`) REFERENCES `nhan_vien` (`Ma_NV`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `xe`
--

DROP TABLE IF EXISTS `xe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xe` (
  `Ma_xe` int NOT NULL AUTO_INCREMENT,
  `Ten_xe` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `BienSo_xe` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `SoGhe_xe` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `Ma_Loai_xe` int NOT NULL,
  PRIMARY KEY (`Ma_xe`),
  KEY `Ma_loaixe_idx` (`Ma_Loai_xe`),
  CONSTRAINT `Ma_Loai_xe` FOREIGN KEY (`Ma_Loai_xe`) REFERENCES `loai_xe` (`Ma_LoaiXe`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-17  9:46:20
