INSERT INTO `db1974`.`pacjenci` (`idpacjenci`, `nazwisko`, `imie`, `pesel`, `telefon`, `adres`, `plec`, `lok`) VALUES ('2', 'NOWACZYK', 'ROBERT', '33344414231', '667888212', 'POZNAŃ', 'MĘŻCZYZNA', 'MIASTO');
INSERT INTO `db1974`.`pacjenci` (`idpacjenci`, `nazwisko`, `imie`, `pesel`, `telefon`, `adres`, `plec`, `lok`) VALUES ('3', 'GARBARCZYK', 'RENATA', '77612312348', '554003921', 'ZIELONA GÓRA - KONWALIOWA 4', 'KOBIETA', 'MIASTO');
INSERT INTO `db1974`.`pacjenci` (`idpacjenci`, `nazwisko`, `imie`, `pesel`, `telefon`, `adres`, `plec`, `lok`) VALUES ('4', 'TRACZ', 'KACPER', '12344461274', '550998432', 'NOWA SÓL', 'MĘŻCZYZNA', 'MIASTO');
INSERT INTO `db1974`.`pacjenci` (`idpacjenci`, `nazwisko`, `imie`, `pesel`, `telefon`, `adres`, `plec`, `lok`) VALUES ('5', 'STANEK', 'JULIA', '12372141272', '312312348', 'PRZYLEP', 'KOBIETA', 'WIEŚ');
INSERT INTO `db1974`.`pacjenci` (`idpacjenci`, `nazwisko`, `imie`, `pesel`, `telefon`, `adres`, `plec`, `lok`) VALUES ('6', 'KOWAL', 'KRYSTIAN', '12324214871', '311231231', 'POMORSKO', 'MĘŻCZYZNA', 'WIEŚ');

INSERT INTO `db1974`.`wizyta` (`idwizyta`, `nazwa`, `czas`, `punkty`, `typ`) VALUES ('1', 'DOMOWA', '30', '5', '1');
INSERT INTO `db1974`.`wizyta` (`idwizyta`, `nazwa`, `czas`, `punkty`, `typ`) VALUES ('2', 'GRUPOWA', '45', '10', '2');
INSERT INTO `db1974`.`wizyta` (`idwizyta`, `nazwa`, `czas`, `punkty`, `typ`) VALUES ('3', 'SUPERWIZJA', '15', '15', '3');
INSERT INTO `db1974`.`wizyta` (`idwizyta`, `nazwa`, `czas`, `punkty`, `typ`) VALUES ('4', 'WSTĘPNA', '15', '20', '4');
INSERT INTO `db1974`.`wizyta` (`idwizyta`, `nazwa`, `czas`, `punkty`, `typ`) VALUES ('5', 'TESTOWA', '25', '25', '1');
INSERT INTO `db1974`.`wizyta` (`idwizyta`, `nazwa`, `czas`, `punkty`, `typ`) VALUES ('6', 'TESTOWA', '25', '25', '2');
INSERT INTO `db1974`.`wizyta` (`idwizyta`, `nazwa`, `czas`, `punkty`, `typ`) VALUES ('7', 'TESTOWA', '25', '25', '3');
INSERT INTO `db1974`.`wizyta` (`idwizyta`, `nazwa`, `czas`, `punkty`, `typ`) VALUES ('8', 'TESTOWA', '25', '25', '4');

INSERT INTO `db1974`.`personel` (`idpersonel`, `nazwisko_imie`, `adres`, `telefon`) VALUES ('1', 'PSYCHOLOG 1', 'ZG1', '609234211');
INSERT INTO `db1974`.`personel` (`idpersonel`, `nazwisko_imie`, `adres`, `telefon`) VALUES ('2', 'PSYCHOLOG 2', 'ZG2', '609431231');
INSERT INTO `db1974`.`personel` (`idpersonel`, `nazwisko_imie`, `adres`, `telefon`) VALUES ('3', 'PSYCHOLOG 3', 'ZG3', '609234234');
INSERT INTO `db1974`.`personel` (`idpersonel`, `nazwisko_imie`, `adres`, `telefon`) VALUES ('4', 'PSYCHOLOG 4', 'ZG4', '609432432');
INSERT INTO `db1974`.`personel` (`idpersonel`, `nazwisko_imie`, `adres`, `telefon`) VALUES ('5', 'PSYCHIATRA 1', 'ZG5', '609345345');
INSERT INTO `db1974`.`personel` (`idpersonel`, `nazwisko_imie`, `adres`, `telefon`) VALUES ('6', 'PSYCHIATRA 2', 'ZG6', '609543534');
INSERT INTO `db1974`.`personel` (`idpersonel`, `nazwisko_imie`, `adres`, `telefon`) VALUES ('7', 'PSYCHIATRA 3', 'ZG7', '609456456');
INSERT INTO `db1974`.`personel` (`idpersonel`, `nazwisko_imie`, `adres`, `telefon`) VALUES ('8', 'PSYCHIATRA 4', 'ZG8', '609654654');

INSERT INTO `db1974`.`komorka` (`idkomorka`, `nazwa`, `numer`, `punkty`, `adres`, `pln`, `typ`) VALUES ('1', 'PORADNIA', '001', '5000', 'ZG1', '5', '1');
INSERT INTO `db1974`.`komorka` (`idkomorka`, `nazwa`, `numer`, `punkty`, `adres`, `pln`, `typ`) VALUES ('2', 'SZPITAL', '002', '3000', 'ZG2', '10', '1');
INSERT INTO `db1974`.`komorka` (`idkomorka`, `nazwa`, `numer`, `punkty`, `adres`, `pln`, `typ`) VALUES ('3', 'PORADNIA DZ', '003', '4000', 'ZG3', '8', '2');
INSERT INTO `db1974`.`komorka` (`idkomorka`, `nazwa`, `numer`, `punkty`, `adres`, `pln`, `typ`) VALUES ('4', 'SZPITAL DZ', '004', '6000', 'ZG4', '10', '2');
INSERT INTO `db1974`.`komorka` (`idkomorka`, `nazwa`, `numer`, `punkty`, `adres`, `pln`, `typ`) VALUES ('5', 'ZLŚ', '005', '1250', 'ZG5', '20', '4');
INSERT INTO `db1974`.`komorka` (`idkomorka`, `nazwa`, `numer`, `punkty`, `adres`, `pln`, `typ`) VALUES ('6', 'PSYCHOLOG', '006', '4000', 'ZG6', '15', '3');
