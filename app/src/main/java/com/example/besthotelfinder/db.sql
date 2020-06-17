CREATE DATABASE besthotel;

CREATE TABLE Hotel (
  id bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT primary KEY,
  nome varchar(255) ,
  classificacao int(20)  ,
  precoDiaSemanaRegular double(5,2),
  precoDiaSemanaReward double(5,2) ,
  precoFimSemanaRegular double(5,2),
  precoFimSemanaReward double(5,2)

  );