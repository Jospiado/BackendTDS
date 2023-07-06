CREATE TABLE categoria (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE fornecedor (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cnpj VARCHAR(14) NOT NULL,
    endereco VARCHAR(255) NOT NULL,
    telefone VARCHAR(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE produto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    preco DECIMAL(10,2) NOT NULL,
    estoque INT NOT NULL,
    categoria_id INT,
    fornecedor_id INT,
    FOREIGN KEY (categoria_id) REFERENCES categoria(id),
    FOREIGN KEY (fornecedor_id) REFERENCES fornecedor(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO categoria (nome) VALUES ('Eletrônicos');
INSERT INTO categoria (nome) VALUES ('Informática');

INSERT INTO fornecedor (nome, cnpj, endereco, telefone) 
VALUES ('Fornecedor A', '11111111111111', 'Endereço A', '11111111');
INSERT INTO fornecedor (nome, cnpj, endereco, telefone) 
VALUES ('Fornecedor B', '22222222222222', 'Endereço B', '22222222');
INSERT INTO fornecedor (nome, cnpj, endereco, telefone) 
VALUES ('Fornecedor C', '33333333333333', 'Endereço C', '33333333');

INSERT INTO produto (nome, descricao, preco, estoque, categoria_id, fornecedor_id) 
VALUES ('Celular', 'Smartphone avançado', 1500.00, 10, 1, 1);
INSERT INTO produto (nome, descricao, preco, estoque, categoria_id, fornecedor_id) 
VALUES ('Notebook', 'Computador portátil', 2500.00, 5, 2, 2);
INSERT INTO produto (nome, descricao, preco, estoque, categoria_id, fornecedor_id) 
VALUES ('Fones de ouvido', 'Fones sem fio', 100.00, 20, 1, 1);
INSERT INTO produto (nome, descricao, preco, estoque, categoria_id, fornecedor_id) 
VALUES ('Televisor', 'TV LED 55 polegadas', 2000.00, 8, 1, 3);
INSERT INTO produto (nome, descricao, preco, estoque, categoria_id, fornecedor_id) 
VALUES ('Câmera', 'Câmera digital 20MP', 500.00, 15, 1, 1);
INSERT INTO produto (nome, descricao, preco, estoque, categoria_id, fornecedor_id) 
VALUES ('Impressora', 'Impressora a jato de tinta', 300.00, 12, 2, 2);
INSERT INTO produto (nome, descricao, preco, estoque, categoria_id, fornecedor_id) 
VALUES ('Mouse', 'Mouse sem fio ergonômico', 50.00, 30, 2, 2);
INSERT INTO produto (nome, descricao, preco, estoque, categoria_id, fornecedor_id) 
VALUES ('Teclado', 'Teclado mecânico RGB', 100.00, 25, 2, 3);
