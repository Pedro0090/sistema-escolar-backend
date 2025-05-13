RENAME TABLE enedereco TO endereco;

ALTER TABLE professor MODIFY SEXO ENUM('M', 'F', 'OUTRO') NOT NULL;