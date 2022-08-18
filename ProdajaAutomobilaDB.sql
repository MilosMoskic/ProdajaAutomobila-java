CREATE DATABASE PRODAJA_AUTOMOBILA;
USE PRODAJA_AUTOMOBILA;



CREATE TABLE MESTO(
PTT INT PRIMARY KEY NOT NULL,
NAZIV LONGTEXT NOT NULL
);



CREATE TABLE RADNIK(
ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
IME LONGTEXT NOT NULL,
PREZIME LONGTEXT NOT NULL,
POL LONGTEXT NOT NULL
);



CREATE TABLE AUTOPLAC(
ID INT PRIMARY KEY AUTO_INCREMENT,
PTT INT,
FOREIGN KEY (PTT) REFERENCES MESTO(PTT),
NAZIV LONGTEXT,
ADRESA LONGTEXT
);

create table ADMINISTRATOR(
ID INT PRIMARY KEY AUTO_INCREMENT,
IME LONGTEXT NOT NULL,
PREZIME LONGTEXT NOT NULL,
USERNAME LONGTEXT NOT NULL,
PASSWORD LONGTEXT NOT NULL,
PTT INT,
STRUKA LONGTEXT NOT NULL,

FOREIGN KEY (PTT) REFERENCES MESTO(PTT)
);



CREATE TABLE AUTOMOBIL(
ID INT PRIMARY KEY auto_increment,
NAZIV LONGTEXT NOT NULL,
MARKA LONGTEXT NOT NULL,
GODISTE INT,
CENA DOUBLE,
ID_AUTO INT,
foreign key (ID_AUTO) references AUTOPLAC(ID)
);


CREATE TABLE DELOVI(
ID INT PRIMARY KEY auto_increment,
ID_A INT NOT NULL,
ID_R INT NOT NULL,
NAZIV LONGTEXT NOT NULL,
MATERIJAL LONGTEXT NOT NULL,
CENA DOUBLE,
foreign key (ID_A) references AUTOMOBIL (ID),
foreign key (ID_R) references RADNIK(ID)
);

alter table DELOVI add foreign key (ID_A) references AUTOMOBIL (ID) on delete cascade;
alter table DELOVI add foreign key (ID_R) references RADNIK (ID) on delete cascade;
alter table AUTOMOBIL add foreign key(ID_AUTO) references AUTOPLAC(ID) on delete cascade;
alter table ADMINISTRATOR add foreign key(ptt) references MESTO(ptt) on delete cascade;
alter table AUTOPLAC add foreign key(ptt) references MESTO(ptt) on delete cascade;

insert INTO MESTO
VALUES(26300, 'Vrsac');
insert INTO MESTO
VALUES(23000, 'Zrenjanin');
insert into mesto
values(21000, 'Novi Sad');
insert into mesto
values(11000, 'Novi Beograd');
insert into mesto
values(24000, 'Subotica');