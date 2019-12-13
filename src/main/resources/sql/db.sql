create database test_db;

CREATE TABLE `realm` (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) NOT NULL,
  `encrypted_data` varchar(32) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci