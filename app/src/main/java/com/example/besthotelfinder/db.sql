CREATE DATABASE besthotel;

CREATE TABLE Hotel (
  id bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT primary KEY,
  nome varchar(255) NOT NULL,
  classificacao int(20)  NOT NULL,
  precoDiaSemanaRegular double(5,2) NOT NULL,
  precoDiaSemanaReward double(5,2) NOT NULL,
  precoFimSemanaRegular double(5,2) NOT NULL,
  precoFimSemanaReward double(5,2) NOT NULL

  );