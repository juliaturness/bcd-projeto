-- Tabela de Tipos Sanguíneos
CREATE TABLE IF NOT EXISTS tipo_sanguineo (
id INT PRIMARY KEY AUTO_INCREMENT,
descricao VARCHAR(10) NOT NULL
);

-- Tabela de Pessoas (Jovens)
CREATE TABLE IF NOT EXISTS pessoa (
id_pessoa INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(100) NOT NULL,
cpf VARCHAR(14) UNIQUE NOT NULL,
endereco VARCHAR(200),
telefone VARCHAR(20),
data_nascimento DATE,
genero ENUM('Masculino', 'Feminino', 'Outro'),
id_tipo_sanguineo INT,
FOREIGN KEY (id_tipo_sanguineo) REFERENCES tipo_sanguineo(id)
);

-- Tabela de Responsáveis
CREATE TABLE IF NOT EXISTS responsavel (
id_responsavel INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(100) NOT NULL,
email VARCHAR(100),
telefone VARCHAR(20)
);

-- Tabela de Vínculos
CREATE TABLE IF NOT EXISTS vinculo (
id_pessoa INT,
id_responsavel INT,
PRIMARY KEY (id_pessoa, id_responsavel),
FOREIGN KEY (id_pessoa) REFERENCES pessoa(id_pessoa),
FOREIGN KEY (id_responsavel) REFERENCES responsavel(id_responsavel)
);

-- Tabela de Problemas de Saúde
CREATE TABLE IF NOT EXISTS problemas_saude (
id_problema_saude INT PRIMARY KEY AUTO_INCREMENT,
tipo_problema VARCHAR(50),
descricao TEXT
);

-- Tabela de Dados de Saúde
CREATE TABLE IF NOT EXISTS dado_saude (
id_pessoa INT PRIMARY KEY,
id_problema_saude INT,
FOREIGN KEY (id_pessoa) REFERENCES pessoa(id_pessoa),
FOREIGN KEY (id_problema_saude) REFERENCES problemas_saude(id_problema_saude)
);

-- Tabela de Etapas de Progressão
CREATE TABLE IF NOT EXISTS etapa_progressao (
id_etapa INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(50) NOT NULL,
descricao TEXT,
ordem INT NOT NULL
);

-- Tabela de Distintivos de Progressão
CREATE TABLE IF NOT EXISTS distintivo (
id_distintivo INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(50) NOT NULL,
id_etapa INT,
FOREIGN KEY (id_etapa) REFERENCES etapa_progressao(id_etapa)
);

-- Tabela de Desafios de Distintivos
CREATE TABLE IF NOT EXISTS desafios_distintivos (
id_desafio_distintivo INT PRIMARY KEY AUTO_INCREMENT,
descricao TEXT NOT NULL,
id_distintivo INT,
FOREIGN KEY (id_distintivo) REFERENCES distintivo(id_distintivo)
);

-- Tabela de Progressão de Jovens
CREATE TABLE IF NOT EXISTS progressao_lobinho (
id_progressao INT PRIMARY KEY AUTO_INCREMENT,
id_pessoa INT,
id_etapa_atual INT,
data_inicio DATE,
data_conclusao DATE,
FOREIGN KEY (id_pessoa) REFERENCES pessoa(id_pessoa),
FOREIGN KEY (id_etapa_atual) REFERENCES etapa_progressao(id_etapa)
);

-- Tabela de Conquistas de Distintivos
CREATE TABLE IF NOT EXISTS conquista_distintivo (
id_progressao INT,
id_distintivo INT,
data_conquista DATE,
PRIMARY KEY (id_progressao, id_distintivo),
FOREIGN KEY (id_progressao) REFERENCES progressao_lobinho(id_progressao),
FOREIGN KEY (id_distintivo) REFERENCES distintivo(id_distintivo)
);

-- Tabela de Insígnias de Interesse Especial
CREATE TABLE IF NOT EXISTS insignia (
id_insignia INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(50) NOT NULL,
descricao TEXT
);

-- Tabela de Conquistas de Insígnias
CREATE TABLE IF NOT EXISTS conquista_insignia (
id_pessoa INT,
id_insignia INT,
data_conquista DATE,
PRIMARY KEY (id_pessoa, id_insignia),
FOREIGN KEY (id_pessoa) REFERENCES pessoa(id_pessoa),
FOREIGN KEY (id_insignia) REFERENCES insignia(id_insignia)
);

-- Tabela de Áreas de Conhecimento
CREATE TABLE IF NOT EXISTS area_conhecimento (
id_area_conhecimento INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(50) NOT NULL
);

-- Tabela de Especialidades
CREATE TABLE IF NOT EXISTS especialidade (
id_especialidade INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(50) NOT NULL,
id_area_conhecimento INT,
FOREIGN KEY (id_area_conhecimento) REFERENCES area_conhecimento(id_area_conhecimento)
);

-- Tabela de Conquistas de Especialidades
CREATE TABLE IF NOT EXISTS conquista_especialidade (
id_pessoa INT,
id_especialidade INT,
data_conquista DATE,
PRIMARY KEY (id_pessoa, id_especialidade),
FOREIGN KEY (id_pessoa) REFERENCES pessoa(id_pessoa),
FOREIGN KEY (id_especialidade) REFERENCES especialidade(id_especialidade)
);

-- Tabela de Acampamentos
CREATE TABLE IF NOT EXISTS acampamento (
id_acampamento INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(100) NOT NULL,
data_inicio DATE,
data_fim DATE,
tipo ENUM('Acampamento', 'Acantonamento') NOT NULL
);

-- Tabela de Participação em Acampamentos
CREATE TABLE IF NOT EXISTS participacao_acampamento (
id_pessoa INT,
id_acampamento INT,
PRIMARY KEY (id_pessoa, id_acampamento),
FOREIGN KEY (id_pessoa) REFERENCES pessoa(id_pessoa),
FOREIGN KEY (id_acampamento) REFERENCES acampamento(id_acampamento)
);

-- Inserção de dados iniciais

-- Tipos Sanguíneos
INSERT INTO tipo_sanguineo (id, descricao) VALUES
(1, 'A+'), (2, 'A−'), (3, 'B+'), (4, 'B−'),
(5, 'AB+'), (6, 'AB−'), (7, 'O+'), (8, 'O−');

-- Etapas de Progressão
INSERT INTO etapa_progressao (id_etapa, nome, descricao, ordem) VALUES
(1, 'Integrar', 'Primeira etapa do Caminho do Lobinho', 1),
(2, 'Pata Tenra', 'Segunda etapa de progressão', 2),
(3, 'Saltador', 'Terceira etapa de progressão', 3),
(4, 'Cruzeiro do Sul', 'Etapa final de progressão', 4);

-- Distintivos de Progressão
INSERT INTO distintivo (id_distintivo, nome, id_etapa) VALUES
(1, 'Lobo Pata Tenra', 2),
(2, 'Lobo Saltador', 3),
(3, 'Cruzeiro do Sul', 4);

-- Desafios dos Distintivos
INSERT INTO desafios_distintivos (id_desafio_distintivo, descricao, id_distintivo) VALUES
(1, 'Conhecer a história do lobinho', 1),
(2, 'Participar de 3 atividades', 1),
(3, 'Ajudar em uma boa ação', 2),
(4, 'Acampar 2 noites', 3);

-- Insígnias de Interesse Especial
INSERT INTO insignia (id_insignia, nome, descricao) VALUES
(1, 'Campeões da Natureza', 'Insígnia relacionada à preservação ambiental'),
(2, 'Reduzir, Reciclar, Reutilizar', 'Insígnia sobre sustentabilidade'),
(3, 'Escoteiros pela Energia Solar', 'Insígnia sobre energia renovável'),
(4, 'Luzofória', 'Insígnia sobre comunicação'),
(5, 'Ação Comunitária', 'Insígnia sobre trabalho comunitário'),
(6, 'Conselho do Sul', 'Insígnia sobre orientação'),
(7, 'Aprender', 'Insígnia sobre desenvolvimento pessoal');

-- Áreas de Conhecimento
INSERT INTO area_conhecimento (id_area_conhecimento, nome) VALUES
(1, 'Ciência e Tecnologia'),
(2, 'Cultura'),
(3, 'Desportos'),
(4, 'Meio Ambiente'),
(5, 'Serviço Comunitário');

-- Especialidades
INSERT INTO especialidade (id_especialidade, nome, id_area_conhecimento) VALUES
(1, 'Informática', 1),
(2, 'Primeiros Socorros', 1),
(3, 'Artesanato', 2),
(4, 'Natação', 3),
(5, 'Observação de Aves', 4),
(6, 'Reciclagem', 4),
(7, 'Serviço Voluntário', 5);

-- Pessoas de exemplo
INSERT INTO pessoa (id_pessoa, nome, cpf, endereco, telefone, data_nascimento, genero, id_tipo_sanguineo) VALUES
(1, 'Taylor Swift', '131.131.131-13', 'Av.Industria Da Música, 13 - Santa Catarina - SC', '(48) 99345‑2000', '2010-07-24', 'Feminino', 1),
(2, 'Betty', '111.333.131-13', 'Av. Folklore, 24 – Rio de Janeiro – RJ', '(21) 92345‑2000', '2011-05-15', 'Feminino', 2),
(3, 'Augustine', '131.333.131-13', 'Av. Folklore, 24 – Rio de Janeiro – RJ', '(21) 93456‑3000', '2012-03-10', 'Feminino', 1);

-- Progressão dos jovens
INSERT INTO progressao_lobinho (id_progressao, id_pessoa, id_etapa_atual, data_inicio) VALUES
(1, 1, 3, '2022-01-10'), -- Taylor está no estágio Saltador
(2, 2, 2, '2023-03-15'), -- Betty está no estágio Pata Tenra
(3, 3, 1, '2023-09-01'); -- Augustine está no estágio Integrar

-- Acampamentos
INSERT INTO acampamento (id_acampamento, nome, data_inicio, data_fim, tipo) VALUES
(1, 'Acampamento de Páscoa', '2023-04-10', '2023-04-12', 'Acampamento'),
(2, 'Acantonamento de Inverno', '2023-07-15', '2023-07-16', 'Acantonamento'),
(3, 'Acampamento de Verão', '2024-01-10', '2024-01-14', 'Acampamento');

-- Participação em acampamentos
INSERT INTO participacao_acampamento (id_pessoa, id_acampamento) VALUES
(1, 1), (1, 2), (1, 3), -- Taylor participou de 3 acampamentos
(2, 1), (2, 2); -- Betty participou de 2 acampamentos