-- create by firman (1302100093)
-- kritik dana saran di 08529991170
-- phpMyAdmin SQL Dump
-- version 2.11.9.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jun 13, 2012 at 03:37 PM
-- Server version: 5.0.67
-- PHP Version: 5.2.6

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- Database: `dbmahasiswa`
--

-- --------------------------------------------------------

--
-- Table structure for table `dkhs`
--

CREATE TABLE IF NOT EXISTS `dkhs` (
  `stambuk` text,
  `nama` text,
  `tahun_ajaran` text,
  `semester` text,
  `kode` text,
  `mata_kuliah` text,
  `sks` text,
  `nilai` text,
  `nilai_x_sks` text
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dkhs`
--

INSERT INTO `dkhs` (`stambuk`, `nama`, `tahun_ajaran`, `semester`, `kode`, `mata_kuliah`, `sks`, `nilai`, `nilai_x_sks`) VALUES
('1302100093', 'firman', '2010/2011', 'Semester 1', '3 KK 110', 'Logika Informatika', '3', 'B', '9'),
('1302100093', 'firman', '2010/2011', 'Semester 1', '3 KK 112', 'Sistem DIgital', '3', 'B', '9'),
('1302100093', 'firman', '2010/2011', 'Semester 1', '3 KK 114', 'Fisika Informatika', '3', 'A', '12'),
('1302100093', 'firman', '2010/2011', 'Semester 1', '1 KB 122', 'Prak . Pengantar Ilmu Komputer', '1', 'A', '4'),
('1302100093', 'firman', '2010/2011', 'Semester 1', '2 KB 121', 'Pengantar Ilmu Komputer', '2', 'A', '8'),
('1302100093', 'firman', '2010/2011', 'Semester 1', '1 KK 106', 'Prak. Algoritma Pemograman', '1', 'B', '3'),
('1302100093', 'firman', '2010/2011', 'Semester 1', '3 KK 105', 'Algoritma& Struktur Data I', '3', 'B', '9'),
('1302100093', 'firman', '2010/2011', 'Semester 1', '2 KK 111', 'Bahasa Inggris', '2', 'A', '8'),
('1302100093', 'firman', '2010/2011', 'Semester 1', '2 PK 107', 'Pancasila', '2', 'B', '6'),
('1302100093', 'firman', '2010/2011', 'Semester 1', '2 PK 101', 'Aqidah', '2', 'A', '8');

-- --------------------------------------------------------

--
-- Table structure for table `dmahasiswa`
--

CREATE TABLE IF NOT EXISTS `dmahasiswa` (
  `stambuk` int(11) NOT NULL,
  `nama` text,
  `jenis_kelamin` text,
  `tgl_lahir` text,
  `alamat` text,
  `hp` text,
  `email` text,
  `asal_daerah` text,
  `asal_sekolah` text,
  `fakultas` text,
  `jurusan` text,
  `angkatan` text,
  PRIMARY KEY  (`stambuk`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dmahasiswa`
--

INSERT INTO `dmahasiswa` (`stambuk`, `nama`, `jenis_kelamin`, `tgl_lahir`, `alamat`, `hp`, `email`, `asal_daerah`, `asal_sekolah`, `fakultas`, `jurusan`, `angkatan`) VALUES
(1302100090, 'firman', 'Laki-laki', '1992-01-29', 'BTN Gowa Lestari b4/13, somba opu, batangkaluku', '085299911570', 'firman1302100093@gmail.com', 'Bulukumba', 'SMAN 1 Bulukumba', 'Ilmu Komputer', 'Teknik Informatika', '2010');

-- --------------------------------------------------------

--
-- Table structure for table `dnilai`
--

CREATE TABLE IF NOT EXISTS `dnilai` (
  `kode_mata_kuliah` varchar(25) NOT NULL,
  `nama_mata_kuliah` text,
  `kredit` text,
  `semester` text,
  PRIMARY KEY  (`kode_mata_kuliah`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dnilai`
--

INSERT INTO `dnilai` (`kode_mata_kuliah`, `nama_mata_kuliah`, `kredit`, `semester`) VALUES
('2 PK 101', 'Aqidah', '2', 'Semester 1'),
('2 PK 107', 'Pancasila', '2', 'Semester 1'),
('2 KK 111', 'Bahasa Inggris', '2', 'Semester 1'),
('3 KK 105', 'Algoritma& Struktur Data I', '3', 'Semester 1'),
('1 KK 106', 'Prak. Algoritma Pemograman', '1', 'Semester 1'),
('2 KB 121', 'Pengantar Ilmu Komputer', '2', 'Semester 1'),
('1 KB 122', 'Prak . Pengantar Ilmu Komputer', '1', 'Semester 1'),
('3 KK 114', 'Fisika Informatika', '3', 'Semester 1'),
('3 KK 112', 'Sistem DIgital', '3', 'Semester 1'),
('3 KK 110', 'Logika Informatika', '3', 'Semester 1'),
('1 KK 208', 'Prak. Algoritm & Struktur data II', '1', 'Semester 2'),
('3 KK 2007', 'Algoritma & Struktur data II', '3', 'Semester 2'),
('2 PK 202', 'Syariah', '2', 'Semester 2'),
('3 KK 203', 'Matematika Diskrit', '3', 'Semester 2'),
('2 KB 214', 'Bahasa pemograman 1 (Delphi)', '2', 'Semester 2'),
('1 KB 215', 'Prak. Bhs. Pemograman 1 (Delphi)', '1', 'Semester 2'),
('3 KK 216', 'Kalkulus Informatika', '3', 'Semester 2'),
('2 PK 208', 'Kewarganegaraan', '2', 'Semester 2'),
('2 PK 303', 'Akhlak', '2', 'Semester 3'),
('3 KB 302', 'Basis Data 1', '3', 'Semester 3'),
('1 KB 303', 'Prak. Basis data 1', '1', 'Semester 3'),
('3 KB 310', 'Sistem Operasi', '3', 'Semester 3'),
('1 KB 311', 'Prak. Sistem Operasi', '1', 'Semester 3'),
('3 KK 309', 'Aljabar Linier & Matriks', '3', 'Semester 3'),
('2 KB 316', 'Bahasa Pemograman II (PBO)', '2', 'Semester 3'),
('1 KB 317', 'Prak. Bhs. Pemograman II (PBO)', '1', 'Semester 3'),
('3 KB 312', 'Arsitektur & Organisasi Komputer', '3', 'Semester 3'),
('3 KK 315', 'Komunikasi data ', '3', 'Semester 3'),
('2 PK 404', 'Pendidikan Agama Islam', '2', 'Semester 4'),
('3 KK 421', 'Pemograman Terstruktur', '3 ', 'Semester 4'),
('1 KK 422', 'Prak. Pemrogramana terstruktur (C)', '1', 'Semester 4'),
('3 KK 402', 'Metode Numerik', '3', 'Semester 4'),
('2 KB 418', 'Bahasa Pemograman III (java lanjut)', '2', 'Semester 4'),
('1 KK 419', 'Prak. Bahasa Pemograman III (java lanjut)', '1', 'Semester 4'),
('3 KB 401', 'Rekayasa perangkat lunak', '3 ', 'Semester 4'),
('3 KK 416', 'Teori bahasa dan automata', '3', 'Semester 4'),
('2 KB 406', 'Jaringan komputer', '2', 'Semester 4'),
('1 KB 407', 'Prak. Jaringan Komputer', '1 ', 'Semester 4'),
('2 PK 505', 'Islam Disiplin Ilmu', '2', 'Semester 5'),
('3 KB 504', 'Basis Data II', '3', 'Semester 5'),
('1 KB 505', 'Prak. Basis Data II', '1', 'Semester 5'),
('3 KK 520', 'Teknik Kompilasi', '3', 'Semester 5'),
('3 KK 517', 'Analisa perancangan sustem/UML', '3', 'Semester 5'),
('2 KB 524', 'Pemograman IV (Web i/static)', '2', 'Semester 5'),
('1 KB 525', 'Prak. PemogramanIV(web i/static', '1', 'Semester 5'),
('2 KB 533', 'Micropprecesso\r\nr & Microcontroller', '2', 'Semester 5'),
('3 KB 508', 'Jaringan Komputer Lanjut', '3', 'Semester 5'),
('1 KB 534', 'Prak. Micropros & Microcontroller', '1', 'Semester 5'),
('2 PK 606', 'Ilmu Dakwah', '2', 'Semester 6'),
('2 KK 619', 'Riset teknologi informasi', '2', 'Semester 6'),
('3 KB 620', 'Interaksi manusia & Komputer', '3', 'Semester 6'),
('3 KB 623', 'Kecerdasan buatan', '3', 'Semester 6'),
('2 KB 626', 'Pemograman V (web II/dinamis)', '2', 'Semester 6'),
('1 KB 627', 'Prak. pemograman v (web II /dinamis)', '1', 'Semester 6'),
('3 KK 604', 'Statistik', '3', 'Semester 6'),
('3 KB 735', 'Metodologi penelitian', '3', 'Semester 7'),
('3 KB 709', 'Manajemen proyek RPL', '3', 'Semester 7'),
('2 BB 702', 'Kewairausahaan ', '2', 'Semester 7'),
('2 KB 729', 'Tugas Aplikasi', '2', 'Semester 7'),
('3 KK 718', 'Keamanan sistem komputer', '3', 'Semester 7'),
('2 BB 801', 'KKN (Reguler/Professi/PPMD)', '2', 'Semester 8'),
('2 PB 801', 'PKL + Seminar Proposal', '2', 'Semester 8'),
('4 BB 803', 'Tugas Akhir (Skripsi)', '4', 'Semester 8'),
('2 KB 613', 'wirelles & mobile computing', '2', 'Mata Kuliah Pilihan'),
('2 KB 728', 'sistem pendukung keputusan', '2', 'Mata Kuliah Pilihan'),
('2 KB 732', 'unix dan linux', '2', 'Mata Kuliah Pilihan'),
('2 KB 639', 'GIS', '2', 'Mata Kuliah Pilihan'),
('2 KB 736', 'Troubleshooting', '2', 'Mata Kuliah Pilihan'),
('2 KB 631', 'pengolahan citra', '2', 'Mata Kuliah Pilihan'),
('2 KB 637', 'Robotika', '2', 'Mata Kuliah Pilihan'),
('2 KB 738', 'kapita selekta', '2', 'Mata Kuliah Pilihan'),
('2 KB 530', 'desain grafis', '2', 'Mata Kuliah Pilihan'),
('2 KB 740', 'Sistem oprasi lanjut', '2', 'Mata Kuliah Pilihan');
