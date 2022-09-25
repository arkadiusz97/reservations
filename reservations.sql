-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 23 Wrz 2022, 21:30
-- Wersja serwera: 10.4.24-MariaDB
-- Wersja PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `reservations`
--

-- --------------------------------------------------------

--
-- Zastąpiona struktura widoku `detailedreservations`
-- (Zobacz poniżej rzeczywisty widok)
--
CREATE TABLE `detailedreservations` (
`id` int(11)
,`tenantName` text
,`lessorName` text
,`lessorSurname` text
,`tenantSurname` text
,`objectName` text
,`firstDay` datetime
,`lastDay` datetime
);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `lessors`
--

CREATE TABLE `lessors` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL,
  `surname` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `lessors`
--

INSERT INTO `lessors` (`id`, `name`, `surname`) VALUES
(1, 'Karolina', 'Wiśniewska'),
(2, 'Anna', 'Wójcik');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `objectsforrent`
--

CREATE TABLE `objectsforrent` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL,
  `unitPrice` int(11) NOT NULL,
  `surface` int(11) NOT NULL,
  `description` text NOT NULL,
  `lessorId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `objectsforrent`
--

INSERT INTO `objectsforrent` (`id`, `name`, `unitPrice`, `surface`, `description`, `lessorId`) VALUES
(1, 'apartment', 5, 50, 'Medium-size apartment.', 1),
(2, 'office', 10, 30, 'Office for a few people.', 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `reservations`
--

CREATE TABLE `reservations` (
  `id` int(11) NOT NULL,
  `tenantId` int(11) NOT NULL,
  `objectForRentId` int(11) NOT NULL,
  `firstDay` datetime NOT NULL,
  `lastDay` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `reservations`
--

INSERT INTO `reservations` (`id`, `tenantId`, `objectForRentId`, `firstDay`, `lastDay`) VALUES
(1, 1, 2, '2022-09-23 00:00:00', '2022-09-24 00:00:00'),
(2, 2, 1, '2022-09-26 00:00:00', '2022-09-29 00:00:00');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `tenants`
--

CREATE TABLE `tenants` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL,
  `surname` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `tenants`
--

INSERT INTO `tenants` (`id`, `name`, `surname`) VALUES
(1, 'Jan', 'Kowalski'),
(2, 'Adam', 'Nowak');

-- --------------------------------------------------------

--
-- Struktura widoku `detailedreservations`
--
DROP TABLE IF EXISTS `detailedreservations`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `detailedreservations`  AS SELECT `id` AS `id`, `tenants`.`name` AS `tenantName`, `lessors`.`name` AS `lessorName`, `lessors`.`surname` AS `lessorSurname`, `tenants`.`surname` AS `tenantSurname`, `objectsforrent`.`name` AS `objectName`, `firstDay` AS `firstDay`, `lastDay` AS `lastDay` FROM (((`reservations` join `tenants` on(`tenantId` = `tenants`.`id`)) join `objectsforrent` on(`objectForRentId` = `objectsforrent`.`id`)) join `lessors` on(`objectsforrent`.`lessorId` = `lessors`.`id`))  ;

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `lessors`
--
ALTER TABLE `lessors`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `objectsforrent`
--
ALTER TABLE `objectsforrent`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `reservations`
--
ALTER TABLE `reservations`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `tenants`
--
ALTER TABLE `tenants`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT dla zrzuconych tabel
--

--
-- AUTO_INCREMENT dla tabeli `lessors`
--
ALTER TABLE `lessors`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT dla tabeli `objectsforrent`
--
ALTER TABLE `objectsforrent`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT dla tabeli `reservations`
--
ALTER TABLE `reservations`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT dla tabeli `tenants`
--
ALTER TABLE `tenants`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
