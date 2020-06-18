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

INSERT INTO `hotel` (`id`, `nome`, `classificacao`, `precoDiaSemanaRegular`, `precoDiaSemanaReward`, `precoFimSemanaRegular`, `precoFimSemanaReward`) VALUES (NULL, 'Plaza', '3', '110', '80', '90', '80');
INSERT INTO `hotel` (`id`, `nome`, `classificacao`, `precoDiaSemanaRegular`, `precoDiaSemanaReward`, `precoFimSemanaRegular`, `precoFimSemanaReward`) VALUES (NULL, 'Hilton', '4', '160', '110', '60', '50');
INSERT INTO `hotel` (`id`, `nome`, `classificacao`, `precoDiaSemanaRegular`, `precoDiaSemanaReward`, `precoFimSemanaRegular`, `precoFimSemanaReward`) VALUES (NULL, 'Continental', '5', '220', '100', '150', '40');