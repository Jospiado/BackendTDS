CREATE TABLE usuarios (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome_de_usuario VARCHAR(50) NOT NULL,
    senha VARCHAR(100) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO usuarios (nome_de_usuario, senha) VALUES ('usuario1', 'senha1');
INSERT INTO usuarios (nome_de_usuario, senha) VALUES ('usuario2', 'senha2');