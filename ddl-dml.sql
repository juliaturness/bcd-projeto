-- =====================================================
-- SCRIPT COMPLETO: CRIAR TABELAS + POPULAR DADOS
-- Sistema de Registro de Progressões - Ramo Lobinho
-- =====================================================

-- Criar banco de dados se não existir
CREATE DATABASE IF NOT EXISTS bcd;
USE bcd;

-- Desabilitar verificação de chaves estrangeiras temporariamente
SET FOREIGN_KEY_CHECKS = 0;

-- Remover tabelas se existirem (para recriar)
DROP TABLE IF EXISTS NoiteAcampada;
DROP TABLE IF EXISTS DesafioInsigniaFeito;
DROP TABLE IF EXISTS DesafioEspecialidadeFeito;
DROP TABLE IF EXISTS DesafioDistintivoFeito;
DROP TABLE IF EXISTS DesafioInsignia;
DROP TABLE IF EXISTS DesafioEspecialidade;
DROP TABLE IF EXISTS DesafioDistintivo;
DROP TABLE IF EXISTS Vinculo;
DROP TABLE IF EXISTS Responsavel;
DROP TABLE IF EXISTS Dadosaude;
DROP TABLE IF EXISTS ProblemasSaude;
DROP TABLE IF EXISTS Pessoa;
DROP TABLE IF EXISTS TipoSanguineo;
DROP TABLE IF EXISTS Acampamento;
DROP TABLE IF EXISTS Insignia;
DROP TABLE IF EXISTS Especialidade;
DROP TABLE IF EXISTS AreaConhecimento;
DROP TABLE IF EXISTS Distintivo;

-- =====================================================
-- CRIAR TODAS AS TABELAS
-- =====================================================

-- 1. Tabela TipoSanguineo
CREATE TABLE TipoSanguineo (
                               idTipoSanguineo INTEGER NOT NULL AUTO_INCREMENT,
                               tipo VARCHAR(10),
                               PRIMARY KEY (idTipoSanguineo)
) ENGINE=InnoDB;

-- 2. Tabela Pessoa
CREATE TABLE Pessoa (
                        idPessoa INTEGER NOT NULL AUTO_INCREMENT,
                        nome VARCHAR(255) NOT NULL,
                        cpf VARCHAR(255) NOT NULL UNIQUE,
                        endereco VARCHAR(255) NOT NULL,
                        telefone VARCHAR(255) NOT NULL,
                        dataNascimento DATE NOT NULL,
                        genero VARCHAR(255) NOT NULL,
                        idTipoSanguineo INTEGER,
                        PRIMARY KEY (idPessoa),
                        FOREIGN KEY (idTipoSanguineo) REFERENCES TipoSanguineo(idTipoSanguineo)
) ENGINE=InnoDB;

-- 3. Tabela ProblemasSaude
CREATE TABLE ProblemasSaude (
                                idProblema INTEGER NOT NULL AUTO_INCREMENT,
                                tipoProblema VARCHAR(255) NOT NULL,
                                descricao VARCHAR(255),
                                PRIMARY KEY (idProblema)
) ENGINE=InnoDB;

-- 4. Tabela DadosSaude
CREATE TABLE Dadosaude (
                           idDadosSaude INTEGER NOT NULL AUTO_INCREMENT,
                           idPessoa INTEGER UNIQUE,
                           idProblema INTEGER,
                           PRIMARY KEY (idDadosSaude),
                           FOREIGN KEY (idPessoa) REFERENCES Pessoa(idPessoa),
                           FOREIGN KEY (idProblema) REFERENCES ProblemasSaude(idProblema)
) ENGINE=InnoDB;

-- 5. Tabela Responsavel
CREATE TABLE Responsavel (
                             idResponsavel INTEGER NOT NULL AUTO_INCREMENT,
                             pessoa_idPessoa INTEGER NOT NULL,
                             nome VARCHAR(100) NOT NULL,
                             email VARCHAR(100),
                             telefone VARCHAR(20) NOT NULL,
                             PRIMARY KEY (idResponsavel),
                             FOREIGN KEY (pessoa_idPessoa) REFERENCES Pessoa(idPessoa)
) ENGINE=InnoDB;

-- 6. Tabela Vinculo
CREATE TABLE Vinculo (
                         id_vinculo INTEGER NOT NULL AUTO_INCREMENT,
                         idPessoa INTEGER NOT NULL,
                         idResponsavel INTEGER NOT NULL,
                         PRIMARY KEY (id_vinculo),
                         FOREIGN KEY (idPessoa) REFERENCES Pessoa(idPessoa),
                         FOREIGN KEY (idResponsavel) REFERENCES Responsavel(idResponsavel)
) ENGINE=InnoDB;

-- 7. Tabela AreaConhecimento
CREATE TABLE AreaConhecimento (
                                  idAreaConhecimento INTEGER NOT NULL AUTO_INCREMENT,
                                  nome VARCHAR(50) NOT NULL,
                                  PRIMARY KEY (idAreaConhecimento)
) ENGINE=InnoDB;

-- 8. Tabela Especialidade
CREATE TABLE Especialidade (
                               idEspecialidade INTEGER NOT NULL AUTO_INCREMENT,
                               nome VARCHAR(100) NOT NULL,
                               idAreaConhecimento INTEGER NOT NULL,
                               PRIMARY KEY (idEspecialidade),
                               FOREIGN KEY (idAreaConhecimento) REFERENCES AreaConhecimento(idAreaConhecimento)
) ENGINE=InnoDB;

-- 9. Tabela Distintivo
CREATE TABLE Distintivo (
                            idDistintivo INTEGER NOT NULL AUTO_INCREMENT,
                            nome VARCHAR(100) NOT NULL,
                            PRIMARY KEY (idDistintivo)
) ENGINE=InnoDB;

-- 10. Tabela Insignia
CREATE TABLE Insignia (
                          idInsignia INTEGER NOT NULL AUTO_INCREMENT,
                          nome VARCHAR(50) NOT NULL,
                          PRIMARY KEY (idInsignia)
) ENGINE=InnoDB;

-- 11. Tabela Acampamento
CREATE TABLE Acampamento (
                             idAcampamento INTEGER NOT NULL AUTO_INCREMENT,
                             nome VARCHAR(100) NOT NULL,
                             data DATE NOT NULL,
                             PRIMARY KEY (idAcampamento)
) ENGINE=InnoDB;

-- 12. Tabela DesafioDistintivo
CREATE TABLE DesafioDistintivo (
                                   idDesafioDistintivo INTEGER NOT NULL AUTO_INCREMENT,
                                   descricao VARCHAR(500) NOT NULL,
                                   idDistintivo INTEGER,
                                   PRIMARY KEY (idDesafioDistintivo),
                                   FOREIGN KEY (idDistintivo) REFERENCES Distintivo(idDistintivo)
) ENGINE=InnoDB;

-- 13. Tabela DesafioEspecialidade
CREATE TABLE DesafioEspecialidade (
                                      idDesafioEspecialidade INTEGER NOT NULL AUTO_INCREMENT,
                                      descricao VARCHAR(500) NOT NULL,
                                      idEspecialidade INTEGER NOT NULL,
                                      PRIMARY KEY (idDesafioEspecialidade),
                                      FOREIGN KEY (idEspecialidade) REFERENCES Especialidade(idEspecialidade)
) ENGINE=InnoDB;

-- 14. Tabela DesafioInsignia
CREATE TABLE DesafioInsignia (
                                 idDesafioInsignia INTEGER NOT NULL AUTO_INCREMENT,
                                 nome VARCHAR(100) NOT NULL,
                                 idInsignia INTEGER NOT NULL,
                                 PRIMARY KEY (idDesafioInsignia),
                                 FOREIGN KEY (idInsignia) REFERENCES Insignia(idInsignia)
) ENGINE=InnoDB;

-- 15. Tabela DesafioDistintivoFeito
CREATE TABLE DesafioDistintivoFeito (
                                        idDesafioDistintivoFeito INTEGER NOT NULL AUTO_INCREMENT,
                                        dataInicio DATE NOT NULL,
                                        dataFim DATE NOT NULL,
                                        idDesafioDistintivo INTEGER NOT NULL,
                                        idPessoa INTEGER NOT NULL,
                                        PRIMARY KEY (idDesafioDistintivoFeito),
                                        FOREIGN KEY (idDesafioDistintivo) REFERENCES DesafioDistintivo(idDesafioDistintivo),
                                        FOREIGN KEY (idPessoa) REFERENCES Pessoa(idPessoa)
) ENGINE=InnoDB;

-- 16. Tabela DesafioEspecialidadeFeito
CREATE TABLE DesafioEspecialidadeFeito (
                                           idDesafioEspecialidadeFeito INTEGER NOT NULL AUTO_INCREMENT,
                                           data DATE NOT NULL,
                                           idDesafioEspecialidade INTEGER NOT NULL,
                                           idPessoa INTEGER NOT NULL,
                                           PRIMARY KEY (idDesafioEspecialidadeFeito),
                                           FOREIGN KEY (idDesafioEspecialidade) REFERENCES DesafioEspecialidade(idDesafioEspecialidade),
                                           FOREIGN KEY (idPessoa) REFERENCES Pessoa(idPessoa)
) ENGINE=InnoDB;

-- 17. Tabela DesafioInsigniaFeito
CREATE TABLE DesafioInsigniaFeito (
                                      idDesafioInsigniaFeito INTEGER NOT NULL AUTO_INCREMENT,
                                      data DATE NOT NULL,
                                      idDesafioInsignia INTEGER NOT NULL,
                                      idPessoa INTEGER NOT NULL,
                                      PRIMARY KEY (idDesafioInsigniaFeito),
                                      FOREIGN KEY (idDesafioInsignia) REFERENCES DesafioInsignia(idDesafioInsignia),
                                      FOREIGN KEY (idPessoa) REFERENCES Pessoa(idPessoa)
) ENGINE=InnoDB;

-- 18. Tabela NoiteAcampada
CREATE TABLE NoiteAcampada (
                               idNoiteAcampada INTEGER NOT NULL AUTO_INCREMENT,
                               idAcampamento INTEGER NOT NULL,
                               idPessoa INTEGER NOT NULL,
                               PRIMARY KEY (idNoiteAcampada),
                               FOREIGN KEY (idAcampamento) REFERENCES Acampamento(idAcampamento),
                               FOREIGN KEY (idPessoa) REFERENCES Pessoa(idPessoa)
) ENGINE=InnoDB;

-- Reabilitar verificação de chaves estrangeiras
SET FOREIGN_KEY_CHECKS = 1;

-- =====================================================
-- POPULAR DADOS
-- =====================================================

-- 1. TIPOS SANGUÍNEOS
INSERT INTO TipoSanguineo (tipo) VALUES
                                     ('A+'), ('A-'), ('B+'), ('B-'), ('AB+'), ('AB-'), ('O+'), ('O-');

-- 2. ÁREAS DE CONHECIMENTO
INSERT INTO AreaConhecimento (nome) VALUES
                                        ('Ciência e Tecnologia'),
                                        ('Cultura'),
                                        ('Desportos'),
                                        ('Habilidades Escoteiras'),
                                        ('Serviços');

-- 3. ESPECIALIDADES
INSERT INTO Especialidade (nome, idAreaConhecimento) VALUES
-- Ciência e Tecnologia
('Radioamadorismo', 1),
('Criptografia', 1),
('Astronomia', 1),
('Informática', 1),
('Eletrônica', 1),
('Meteorologia', 1),
-- Cultura
('Música', 2),
('Teatro', 2),
('Dança', 2),
('Literatura', 2),
('História', 2),
('Artes Plásticas', 2),
-- Desportos
('Futebol', 3),
('Natação', 3),
('Atletismo', 3),
('Ciclismo', 3),
('Escalada', 3),
('Canoagem', 3),
-- Habilidades Escoteiras
('Acampamento', 4),
('Orientação', 4),
('Pioneiria', 4),
('Observação', 4),
('Rastreamento', 4),
('Sobrevivência', 4),
-- Serviços
('Primeiros Socorros', 5),
('Animais Peçonhentos', 5),
('Proteção Ambiental', 5),
('Serviço Comunitário', 5),
('Educação Ambiental', 5),
('Voluntariado', 5);

-- 4. DISTINTIVOS DE PROGRESSÃO
INSERT INTO Distintivo (nome) VALUES
                                  ('Lobo Pata Tenra'),
                                  ('Lobo Saltador'),
                                  ('Lobo Rastreador'),
                                  ('Lobo Caçador'),
                                  ('Cruzeiro do Sul');

-- 5. INSÍGNIAS DE INTERESSE ESPECIAL
INSERT INTO Insignia (nome) VALUES
                                ('Aprender'),
                                ('Compartilhar'),
                                ('Cuidar'),
                                ('Integrar'),
                                ('Servir'),
                                ('Aventurar');

-- 6. PROBLEMAS DE SAÚDE
INSERT INTO ProblemasSaude (tipoProblema, descricao) VALUES
                                                         ('Alergia', 'Alergia alimentar - amendoim'),
                                                         ('Alergia', 'Alergia respiratória - pólen'),
                                                         ('Alergia', 'Alergia a medicamentos - penicilina'),
                                                         ('Medicamento', 'Uso contínuo de insulina - diabetes'),
                                                         ('Medicamento', 'Broncodilatador para asma'),
                                                         ('Condição', 'Asma leve controlada'),
                                                         ('Condição', 'Miopia - uso de óculos'),
                                                         ('Alergia', 'Alergia a picadas de insetos');

-- 7. PESSOAS (LOBINHOS)
INSERT INTO Pessoa (nome, cpf, endereco, telefone, dataNascimento, genero, idTipoSanguineo) VALUES
                                                                                                ('Ana Clara Silva', '123.456.789-01', 'Rua das Flores, 123 - Centro - Florianópolis - SC', '(48) 99876-5432', '2016-03-15', 'Feminino', 1),
                                                                                                ('Pedro Henrique Santos', '234.567.890-12', 'Av. Beira Mar, 456 - Trindade - Florianópolis - SC', '(48) 98765-4321', '2015-07-22', 'Masculino', 2),
                                                                                                ('Maria Eduarda Costa', '345.678.901-23', 'Rua da Lagoa, 789 - Lagoa da Conceição - Florianópolis - SC', '(48) 97654-3210', '2017-01-10', 'Feminino', 3),
                                                                                                ('João Gabriel Oliveira', '456.789.012-34', 'Rua do Mangue, 321 - Estreito - Florianópolis - SC', '(48) 96543-2109', '2016-09-05', 'Masculino', 4),
                                                                                                ('Sofia Almeida', '567.890.123-45', 'Av. das Rendeiras, 654 - Lagoa da Conceição - Florianópolis - SC', '(48) 95432-1098', '2015-12-18', 'Feminino', 5),
                                                                                                ('Lucas Ferreira', '678.901.234-56', 'Rua Bocaiúva, 987 - Centro - Florianópolis - SC', '(48) 94321-0987', '2017-04-30', 'Masculino', 6),
                                                                                                ('Isabella Rodrigues', '789.012.345-67', 'Rua Delminda Silveira, 147 - Agronômica - Florianópolis - SC', '(48) 93210-9876', '2016-11-12', 'Feminino', 7),
                                                                                                ('Matheus Lima', '890.123.456-78', 'Av. Madre Benvenuta, 258 - Santa Mônica - Florianópolis - SC', '(48) 92109-8765', '2015-08-25', 'Masculino', 8);

-- 8. DADOS DE SAÚDE
INSERT INTO Dadosaude (idPessoa, idProblema) VALUES
                                                 (1, 1), (2, 6), (3, 7), (4, 2), (5, 8), (7, 4), (8, 5);

-- 9. RESPONSÁVEIS
INSERT INTO Responsavel (pessoa_idPessoa, nome, email, telefone) VALUES
                                                                     (1, 'Carlos Silva', 'carlos.silva@email.com', '(48) 99111-2222'),
                                                                     (2, 'Mariana Santos', 'mariana.santos@email.com', '(48) 99222-3333'),
                                                                     (3, 'Roberto Costa', 'roberto.costa@email.com', '(48) 99333-4444'),
                                                                     (4, 'Fernanda Oliveira', 'fernanda.oliveira@email.com', '(48) 99444-5555'),
                                                                     (5, 'Ricardo Almeida', 'ricardo.almeida@email.com', '(48) 99555-6666'),
                                                                     (6, 'Patrícia Ferreira', 'patricia.ferreira@email.com', '(48) 99666-7777'),
                                                                     (7, 'Anderson Rodrigues', 'anderson.rodrigues@email.com', '(48) 99777-8888'),
                                                                     (8, 'Juliana Lima', 'juliana.lima@email.com', '(48) 99888-9999');

-- 10. VÍNCULOS
INSERT INTO Vinculo (idPessoa, idResponsavel) VALUES
                                                  (1, 1), (2, 2), (3, 3), (4, 4), (5, 5), (6, 6), (7, 7), (8, 8);

-- 11. ACAMPAMENTOS
INSERT INTO Acampamento (nome, data) VALUES
                                         ('Acampamento de Integração Lobinho 2024', '2024-03-15'),
                                         ('Acampamento de Primavera', '2024-09-21'),
                                         ('Acampamento de Verão', '2024-12-15'),
                                         ('Atividade Dia da Árvore', '2024-09-21'),
                                         ('Gincana Escoteira', '2024-06-10');

-- 12. DESAFIOS DOS DISTINTIVOS
INSERT INTO DesafioDistintivo (descricao, idDistintivo) VALUES
-- Lobo Pata Tenra
('Conhecer a história de Mowgli', 1),
('Saber a Oração do Escoteiro', 1),
('Conhecer a Lei do Lobinho', 1),
('Participar de 3 reuniões da alcateia', 1),
('Fazer um amigo na alcateia', 1),
-- Lobo Saltador
('Participar de 6 reuniões da alcateia', 2),
('Conhecer os símbolos escoteiros', 2),
('Participar de uma atividade externa', 2),
('Demonstrar espírito de equipe', 2),
('Ajudar um colega lobinho', 2);

-- 13. DESAFIOS DAS ESPECIALIDADES
INSERT INTO DesafioEspecialidade (descricao, idEspecialidade) VALUES
-- Radioamadorismo
('Conhecer o alfabeto fonético internacional', 1),
('Saber o que é radioamadorismo', 1),
('Conhecer as bandas de radioamador', 1),
('Identificar tipos de antenas', 1),
-- Música
('Conhecer instrumentos musicais', 7),
('Cantar música escoteira', 7),
('Tocar instrumento simples', 7),
('Participar de apresentação', 7),
-- Futebol
('Dominar a bola', 13),
('Fazer um gol', 13),
('Conhecer regras básicas', 13),
('Participar de jogo', 13);

-- 14. DESAFIOS DAS INSÍGNIAS
INSERT INTO DesafioInsignia (nome, idInsignia) VALUES
-- Aprender
('Ler 3 livros adequados à idade', 1),
('Visitar biblioteca ou museu', 1),
('Aprender algo novo e ensinar outros', 1),
('Fazer pesquisa sobre tema de interesse', 1),
-- Compartilhar
('Organizar atividade para a alcateia', 2),
('Ensinar habilidade para outro lobinho', 2),
('Compartilhar brinquedo ou material', 2),
('Ajudar colega com dificuldades', 2);

-- 15. PROGRESSÕES REALIZADAS
INSERT INTO DesafioDistintivoFeito (dataInicio, dataFim, idDesafioDistintivo, idPessoa) VALUES
-- Ana Clara completou Lobo Pata Tenra
('2024-01-15', '2024-03-15', 1, 1),
('2024-01-15', '2024-03-15', 2, 1),
('2024-01-15', '2024-03-15', 3, 1),
('2024-01-15', '2024-03-15', 4, 1),
('2024-01-15', '2024-03-15', 5, 1),
-- Pedro Henrique em progresso
('2024-02-01', '2024-04-01', 1, 2),
('2024-02-01', '2024-04-01', 2, 2),
('2024-02-01', '2024-04-01', 3, 2);

-- 16. ESPECIALIDADES CONQUISTADAS
INSERT INTO DesafioEspecialidadeFeito (data, idDesafioEspecialidade, idPessoa) VALUES
                                                                                   ('2024-04-10', 5, 1),  -- Ana Clara - Música
                                                                                   ('2024-04-20', 6, 1),
                                                                                   ('2024-05-05', 9, 2),  -- Pedro - Futebol
                                                                                   ('2024-05-15', 10, 2),
                                                                                   ('2024-06-01', 1, 3),  -- Maria Eduarda - Radioamadorismo
                                                                                   ('2024-06-10', 2, 3);

-- 17. INSÍGNIAS CONQUISTADAS
INSERT INTO DesafioInsigniaFeito (data, idDesafioInsignia, idPessoa) VALUES
                                                                         ('2024-05-01', 1, 1),  -- Ana Clara - Aprender
                                                                         ('2024-05-15', 2, 1),
                                                                         ('2024-07-01', 5, 3),  -- Maria Eduarda - Compartilhar
                                                                         ('2024-07-15', 6, 3);

-- 18. PARTICIPAÇÕES EM ACAMPAMENTOS
INSERT INTO NoiteAcampada (idAcampamento, idPessoa) VALUES
                                                        (1, 1), (1, 2), (1, 3), (1, 4),
                                                        (2, 1), (2, 3), (2, 5), (2, 6),
                                                        (3, 2), (3, 3), (3, 7), (3, 8),
                                                        (4, 1), (4, 2), (4, 3),
                                                        (5, 3), (5, 4), (5, 5), (5, 6);

-- =====================================================
-- VERIFICAÇÃO FINAL
-- =====================================================
SELECT 'TABELAS CRIADAS E DADOS INSERIDOS COM SUCESSO!' as Status;

-- Mostrar estatísticas
SELECT
    (SELECT COUNT(*) FROM Pessoa) as Total_Lobinhos,
    (SELECT COUNT(*) FROM Especialidade) as Total_Especialidades,
    (SELECT COUNT(*) FROM Distintivo) as Total_Distintivos,
    (SELECT COUNT(*) FROM Insignia) as Total_Insignias,
    (SELECT COUNT(*) FROM Acampamento) as Total_Atividades;

-- Listar todas as tabelas criadas
SHOW TABLES;
