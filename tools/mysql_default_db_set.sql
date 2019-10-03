CREATE SCHEMA `db1974` DEFAULT CHARACTER SET utf16 COLLATE utf8_unicode_ci ;

CREATE TABLE `db1974`.`pacjenci` (
  `idpacjenci` INT NOT NULL,
  `nazwisko` VARCHAR(45) NULL,
  `imie` VARCHAR(45) NULL,
  `pesel` VARCHAR(11) NULL,
  `telefon` VARCHAR(45) NULL,
  `adres` VARCHAR(45) NULL,
  `plec` VARCHAR(45) NULL,
  `lok` VARCHAR(45) NULL,
  PRIMARY KEY (`idpacjenci`));
  
ALTER TABLE `db1974`.`pacjenci` 
CHANGE COLUMN `idpacjenci` `idpacjenci` INT(11) NOT NULL AUTO_INCREMENT ,
ADD UNIQUE INDEX `idpacjenci_UNIQUE` (`idpacjenci` ASC);

CREATE TABLE `db1974`.`personel` (
  `idpersonel` INT NOT NULL,
  `nazwisko_imie` VARCHAR(90) NULL,
  `imie` VARCHAR(45) NULL,
  `adres` VARCHAR(45) NULL,
  `telefon` VARCHAR(45) NULL,
  PRIMARY KEY (`idpersonel`));
  
ALTER TABLE `db1974`.`personel` 
CHANGE COLUMN `idpersonel` `idpersonel` INT(11) NOT NULL AUTO_INCREMENT ,
ADD UNIQUE INDEX `idpersonel_UNIQUE` (`idpersonel` ASC);


CREATE TABLE `db1974`.`komorka` (
  `idkomorka` INT NOT NULL,
  `nazwa` VARCHAR(45) NULL,
  `numer` VARCHAR(45) NULL,
  `punkty` INT NULL,
  `adres` VARCHAR(45) NULL,
  `pln` INT NULL,
  `typ` INT NULL,
  PRIMARY KEY (`idkomorka`));
  
ALTER TABLE `db1974`.`komorka` 
CHANGE COLUMN `idkomorka` `idkomorka` INT(11) NOT NULL AUTO_INCREMENT ,
ADD UNIQUE INDEX `idkomorka_UNIQUE` (`idkomorka` ASC);


CREATE TABLE `db1974`.`grafik` (
  `idgrafik` INT NOT NULL,
  `idpersonel` INT NOT NULL,
  `idkomorka` INT NOT NULL,
  `punkty` INT NULL,
  `czas` INT NULL,
  `pon_co` VARCHAR(10) NULL,
  `pon_od` VARCHAR(10) NULL,
  `pon_do` VARCHAR(10) NULL,
  `pon_h` VARCHAR(10) NULL,
  `wto_co` VARCHAR(10) NULL,
  `wto_od` VARCHAR(10) NULL,
  `wto_do` VARCHAR(10) NULL,
  `wto_h` VARCHAR(10) NULL,
  `sro_co` VARCHAR(10) NULL,
  `sro_od` VARCHAR(10) NULL,
  `sro_do` VARCHAR(10) NULL,
  `sro_h` VARCHAR(10) NULL,
  `czw_co` VARCHAR(10) NULL,
  `czw_od` VARCHAR(10) NULL,
  `czw_do` VARCHAR(10) NULL,
  `czw_h` VARCHAR(10) NULL,
  `pia_co` VARCHAR(10) NULL,
  `pia_od` VARCHAR(10) NULL,
  `pia_do` VARCHAR(10) NULL,
  `pia_h` VARCHAR(10) NULL,
  `sob_co` VARCHAR(10) NULL,
  `sob_od` VARCHAR(10) NULL,
  `sob_do` VARCHAR(10) NULL,
  `sob_h` VARCHAR(10) NULL,
  `nie_co` VARCHAR(10) NULL,
  `nie_od` VARCHAR(10) NULL,
  `nie_do` VARCHAR(10) NULL,
  `nie_h` VARCHAR(10) NULL,
  PRIMARY KEY (`idgrafik`));
  
ALTER TABLE `db1974`.`grafik` 
CHANGE COLUMN `idgrafik` `idgrafik` INT(11) NOT NULL AUTO_INCREMENT ,
ADD UNIQUE INDEX `idgrafik_UNIQUE` (`idgrafik` ASC);

CREATE TABLE `db1974`.`wizyta` (
  `idwizyta` INT NOT NULL,
  `nazwa` VARCHAR(45) NULL,
  `czas` VARCHAR(45) NULL,
  `punkty` INT NULL,
  `typ` VARCHAR(45) NULL,
  PRIMARY KEY (`idwizyta`));
  
ALTER TABLE `db1974`.`wizyta` 
CHANGE COLUMN `idwizyta` `idwizyta` INT(11) NOT NULL AUTO_INCREMENT ,
ADD UNIQUE INDEX `idwizyta_UNIQUE` (`idwizyta` ASC);

CREATE TABLE `db1974`.`kalendarz` (
  `idkalendarz` INT NOT NULL,
  `idpersonel` INT NOT NULL,
  `idkomorka` INT NOT NULL,
  `idpacjenci` INT NOT NULL,
  `idwizyta` INT NOT NULL,
  `data` VARCHAR(45) NULL,
  `godzina` VARCHAR(45) NULL,
  `godzina_od` VARCHAR(10) NULL,
  `godzina_do` VARCHAR(10) NULL,
  `pierwsza` VARCHAR(10) NULL,
  `status` VARCHAR(10) NULL,
  `odwolana` VARCHAR(10) NULL,
  `uwagi` VARCHAR(90) NULL,
  PRIMARY KEY (`idkalendarz`));
  
ALTER TABLE `db1974`.`kalendarz` 
CHANGE COLUMN `idkalendarz` `idkalendarz` INT(11) NOT NULL AUTO_INCREMENT ,
ADD UNIQUE INDEX `idkalendarz_UNIQUE` (`idkalendarz` ASC);


CREATE TABLE `db1974`.`users` (
  `idusers` INT NOT NULL,
  `nazwa` VARCHAR(45) NULL,
  `haslo` VARCHAR(45) NULL,
  `uprawnienia` VARCHAR(45) NULL,
  PRIMARY KEY (`idusers`));
  ALTER TABLE `db1974`.`users` 
CHANGE COLUMN `idusers` `idusers` INT(11) NOT NULL AUTO_INCREMENT ,
ADD UNIQUE INDEX `idusers_UNIQUE` (`idusers` ASC);