-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 21 Mar 2018, 21:52
-- Wersja serwera: 10.1.31-MariaDB
-- Wersja PHP: 7.2.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `mkl_database`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `karta_czlonkowsta`
--

CREATE TABLE `karta_czlonkowsta` (
  `id_karty` int(16) NOT NULL,
  `id_klienta` int(4) NOT NULL,
  `liczba_punktow` int(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `klient`
--

CREATE TABLE `klient` (
  `id_klienta` int(4) NOT NULL,
  `imie_klienta` varchar(125) NOT NULL,
  `nazwisko_klienta` varchar(125) NOT NULL,
  `kod_pocztowy_klienta` varchar(6) NOT NULL,
  `miejscowosc_klienta` varchar(125) NOT NULL,
  `adres__klienta` varchar(125) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `pensje_pracownikow`
--

CREATE TABLE `pensje_pracownikow` (
  `id_pensja_pracownika` int(4) NOT NULL,
  `id_pracownika` int(4) NOT NULL,
  `miesiac_pracy` varchar(55) NOT NULL,
  `godziny_miesiac` float NOT NULL,
  `wartosc_transakcji` float NOT NULL,
  `laczna_wyplata` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `placowka`
--

CREATE TABLE `placowka` (
  `id_placowki` int(4) NOT NULL,
  `telefon_placowki` int(4) NOT NULL,
  `kod_pocztowy_placowki` varchar(6) NOT NULL,
  `miejscowosc_placowki` varchar(255) NOT NULL,
  `adres_placowki` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `placowka_produkt`
--

CREATE TABLE `placowka_produkt` (
  `id_placowka_produkt` int(4) NOT NULL,
  `id_placowki` int(4) NOT NULL,
  `id_produktu` int(4) NOT NULL,
  `ilosc_produktow` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `pracownik`
--

CREATE TABLE `pracownik` (
  `id_pracownika` int(4) NOT NULL,
  `imie_pracownika` varchar(125) NOT NULL,
  `nazwisko_pracownika` varchar(125) NOT NULL,
  `pesel_pracownika` int(11) NOT NULL,
  `telefon_pracownika` int(12) NOT NULL,
  `login` varchar(25) NOT NULL,
  `haslo` varchar(25) NOT NULL,
  `rola` varchar(50) NOT NULL,
  `id_placowki` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `produkty`
--

CREATE TABLE `produkty` (
  `id_produktu` int(4) NOT NULL,
  `nazwa_produktu` varchar(125) NOT NULL,
  `cena_produktu` float NOT NULL,
  `opis_produktu` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `reklamacja`
--

CREATE TABLE `reklamacja` (
  `id_reklamacji` int(4) NOT NULL,
  `id_placowki` int(4) NOT NULL,
  `id_produktu` int(4) NOT NULL,
  `id_transakcji` int(4) NOT NULL,
  `opis` varchar(255) NOT NULL,
  `stan` varchar(125) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `transakcja`
--

CREATE TABLE `transakcja` (
  `id_transakcji` int(4) NOT NULL,
  `id_pracownika` int(4) NOT NULL,
  `id_klienta` int(4) NOT NULL,
  `data` date NOT NULL,
  `calkowity_koszt` int(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `transakcja_produkty`
--

CREATE TABLE `transakcja_produkty` (
  `id_transakcja_produkty` int(4) NOT NULL,
  `id_produktu` int(4) NOT NULL,
  `id_transakcji` int(4) NOT NULL,
  `ilosc_produktow` float NOT NULL,
  `cena_transakcji` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `wiadomosci`
--

CREATE TABLE `wiadomosci` (
  `id_wiadomosci` int(4) NOT NULL,
  `id_pracownika_nadawcy` int(4) NOT NULL,
  `id_pracownika_odbiorca` int(4) NOT NULL,
  `temat_wiadomosci` varchar(250) NOT NULL,
  `tresc_wiadomosci` varchar(1000) NOT NULL,
  `status_wiadomosci` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `zamowienia_produktu`
--

CREATE TABLE `zamowienia_produktu` (
  `id_zmowienia` int(4) NOT NULL,
  `id_produktu` int(4) NOT NULL,
  `data_zamowienia` date DEFAULT NULL,
  `ilosc` int(4) NOT NULL,
  `status_zamowienia` varchar(55) NOT NULL,
  `cena_produktu` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `karta_czlonkowsta`
--
ALTER TABLE `karta_czlonkowsta`
  ADD PRIMARY KEY (`id_karty`),
  ADD KEY `klient_karta` (`id_klienta`);

--
-- Indeksy dla tabeli `klient`
--
ALTER TABLE `klient`
  ADD PRIMARY KEY (`id_klienta`);

--
-- Indeksy dla tabeli `pensje_pracownikow`
--
ALTER TABLE `pensje_pracownikow`
  ADD PRIMARY KEY (`id_pensja_pracownika`),
  ADD KEY `pensje_pracownikow` (`id_pracownika`);

--
-- Indeksy dla tabeli `placowka`
--
ALTER TABLE `placowka`
  ADD PRIMARY KEY (`id_placowki`);

--
-- Indeksy dla tabeli `placowka_produkt`
--
ALTER TABLE `placowka_produkt`
  ADD PRIMARY KEY (`id_placowka_produkt`),
  ADD KEY `wdw_placowka` (`id_placowki`),
  ADD KEY `wdw_produkt` (`id_produktu`);

--
-- Indeksy dla tabeli `pracownik`
--
ALTER TABLE `pracownik`
  ADD PRIMARY KEY (`id_pracownika`),
  ADD KEY `pracownik_placowka` (`id_placowki`);

--
-- Indeksy dla tabeli `produkty`
--
ALTER TABLE `produkty`
  ADD PRIMARY KEY (`id_produktu`);

--
-- Indeksy dla tabeli `reklamacja`
--
ALTER TABLE `reklamacja`
  ADD PRIMARY KEY (`id_reklamacji`),
  ADD KEY `reklamacja_produkty` (`id_produktu`),
  ADD KEY `reklamacja_placowka` (`id_placowki`),
  ADD KEY `reklamacja_transakcja` (`id_transakcji`);

--
-- Indeksy dla tabeli `transakcja`
--
ALTER TABLE `transakcja`
  ADD PRIMARY KEY (`id_transakcji`),
  ADD KEY `transakcja_klienta` (`id_klienta`),
  ADD KEY `transakcja_pracownik` (`id_pracownika`);

--
-- Indeksy dla tabeli `transakcja_produkty`
--
ALTER TABLE `transakcja_produkty`
  ADD PRIMARY KEY (`id_transakcja_produkty`),
  ADD KEY `wdw_produkty` (`id_produktu`),
  ADD KEY `wdw_transakcja` (`id_transakcji`);

--
-- Indeksy dla tabeli `wiadomosci`
--
ALTER TABLE `wiadomosci`
  ADD PRIMARY KEY (`id_wiadomosci`);

--
-- Indeksy dla tabeli `zamowienia_produktu`
--
ALTER TABLE `zamowienia_produktu`
  ADD PRIMARY KEY (`id_zmowienia`),
  ADD KEY `zamowienie_produkt` (`id_produktu`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `klient`
--
ALTER TABLE `klient`
  MODIFY `id_klienta` int(4) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `pensje_pracownikow`
--
ALTER TABLE `pensje_pracownikow`
  MODIFY `id_pensja_pracownika` int(4) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `placowka`
--
ALTER TABLE `placowka`
  MODIFY `id_placowki` int(4) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `placowka_produkt`
--
ALTER TABLE `placowka_produkt`
  MODIFY `id_placowka_produkt` int(4) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `pracownik`
--
ALTER TABLE `pracownik`
  MODIFY `id_pracownika` int(4) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `produkty`
--
ALTER TABLE `produkty`
  MODIFY `id_produktu` int(4) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `reklamacja`
--
ALTER TABLE `reklamacja`
  MODIFY `id_reklamacji` int(4) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `transakcja`
--
ALTER TABLE `transakcja`
  MODIFY `id_transakcji` int(4) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `transakcja_produkty`
--
ALTER TABLE `transakcja_produkty`
  MODIFY `id_transakcja_produkty` int(4) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `wiadomosci`
--
ALTER TABLE `wiadomosci`
  MODIFY `id_wiadomosci` int(4) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `zamowienia_produktu`
--
ALTER TABLE `zamowienia_produktu`
  MODIFY `id_zmowienia` int(4) NOT NULL AUTO_INCREMENT;

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `karta_czlonkowsta`
--
ALTER TABLE `karta_czlonkowsta`
  ADD CONSTRAINT `klient_karta` FOREIGN KEY (`id_klienta`) REFERENCES `klient` (`id_klienta`);

--
-- Ograniczenia dla tabeli `pensje_pracownikow`
--
ALTER TABLE `pensje_pracownikow`
  ADD CONSTRAINT `pensje_pracownikow` FOREIGN KEY (`id_pracownika`) REFERENCES `pracownik` (`id_pracownika`);

--
-- Ograniczenia dla tabeli `placowka_produkt`
--
ALTER TABLE `placowka_produkt`
  ADD CONSTRAINT `wdw_placowka` FOREIGN KEY (`id_placowki`) REFERENCES `placowka` (`id_placowki`),
  ADD CONSTRAINT `wdw_produkt` FOREIGN KEY (`id_produktu`) REFERENCES `produkty` (`id_produktu`);

--
-- Ograniczenia dla tabeli `pracownik`
--
ALTER TABLE `pracownik`
  ADD CONSTRAINT `pracownik_placowka` FOREIGN KEY (`id_placowki`) REFERENCES `placowka` (`id_placowki`);

--
-- Ograniczenia dla tabeli `reklamacja`
--
ALTER TABLE `reklamacja`
  ADD CONSTRAINT `reklamacja_placowka` FOREIGN KEY (`id_placowki`) REFERENCES `placowka` (`id_placowki`),
  ADD CONSTRAINT `reklamacja_produkty` FOREIGN KEY (`id_produktu`) REFERENCES `produkty` (`id_produktu`),
  ADD CONSTRAINT `reklamacja_transakcja` FOREIGN KEY (`id_transakcji`) REFERENCES `transakcja` (`id_transakcji`);

--
-- Ograniczenia dla tabeli `transakcja`
--
ALTER TABLE `transakcja`
  ADD CONSTRAINT `transakcja_klienta` FOREIGN KEY (`id_klienta`) REFERENCES `klient` (`id_klienta`),
  ADD CONSTRAINT `transakcja_pracownik` FOREIGN KEY (`id_pracownika`) REFERENCES `pracownik` (`id_pracownika`);

--
-- Ograniczenia dla tabeli `transakcja_produkty`
--
ALTER TABLE `transakcja_produkty`
  ADD CONSTRAINT `wdw_produkty` FOREIGN KEY (`id_produktu`) REFERENCES `produkty` (`id_produktu`),
  ADD CONSTRAINT `wdw_transakcja` FOREIGN KEY (`id_transakcji`) REFERENCES `transakcja` (`id_transakcji`);

--
-- Ograniczenia dla tabeli `zamowienia_produktu`
--
ALTER TABLE `zamowienia_produktu`
  ADD CONSTRAINT `zamowienie_produkt` FOREIGN KEY (`id_produktu`) REFERENCES `produkty` (`id_produktu`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
