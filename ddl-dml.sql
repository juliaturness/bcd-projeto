SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

DROP DATABASE sofiadb;
CREATE DATABASE IF NOT EXISTS sofiadb;
USE sofiadb;

CREATE TABLE IF NOT EXISTS sofiadb.TipoSanguineo (
                                                     idTipoSanguineo INT NOT NULL AUTO_INCREMENT,
                                                     tipo VARCHAR(45) NOT NULL,

                                                     PRIMARY KEY (idTipoSanguineo)
);

CREATE TABLE IF NOT EXISTS sofiadb.Jovem (
                                             idJovem INT NOT NULL AUTO_INCREMENT,
                                             nome VARCHAR(255) NOT NULL,
                                             dataNascimento DATETIME NOT NULL,
                                             telefone VARCHAR(45) NOT NULL,
                                             email VARCHAR(255) NOT NULL,
                                             idTipoSanguineo INT NOT NULL,

                                             PRIMARY KEY (idJovem),
                                             INDEX fk_Jovem_TipoSanguineo_idx (idTipoSanguineo),

                                             CONSTRAINT fk_Jovem_TipoSanguineo FOREIGN KEY (idTipoSanguineo)
                                                 REFERENCES sofiadb.TipoSanguineo (idTipoSanguineo)
                                                 ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS sofiadb.Responsavel (
                                                   idResponsavel INT NOT NULL AUTO_INCREMENT,
                                                   nome VARCHAR(255) NOT NULL,
                                                   telefone VARCHAR(45) NOT NULL,
                                                   email VARCHAR(255) NOT NULL,

                                                   PRIMARY KEY (idResponsavel)
);

CREATE TABLE IF NOT EXISTS sofiadb.Vinculo (
                                               idVinculo INT NOT NULL AUTO_INCREMENT,
                                               idJovem INT NOT NULL,
                                               idResponsavel INT NOT NULL,

                                               PRIMARY KEY (id),
                                               INDEX idx_idResponsavel (idResponsavel),
                                               INDEX idx_idJovem (idJovem),

                                               CONSTRAINT fk_Vinculo_Jovem FOREIGN KEY (idJovem)
                                                   REFERENCES sofiadb.Jovem (idJovem)
                                                   ON DELETE CASCADE ON UPDATE CASCADE,

                                               CONSTRAINT fk_Vinculo_Responsavel FOREIGN KEY (idResponsavel)
                                                   REFERENCES sofiadb.Responsavel (idResponsavel)
                                                   ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS sofiadb.ProblemaSaude (
                                                     idProblemaSaude INT NOT NULL AUTO_INCREMENT,
                                                     tipoProblema VARCHAR(255) NOT NULL,
                                                     descricao VARCHAR(255),

                                                     PRIMARY KEY (idProblemaSaude)
);

CREATE TABLE IF NOT EXISTS sofiadb.Saude (
                                             id INT NOT NULL AUTO_INCREMENT,
                                             idJovem INT NOT NULL,
                                             idProblemaSaude INT NOT NULL,

                                             PRIMARY KEY (id),
                                             INDEX idx_idJovem (idJovem),
                                             INDEX idx_idProblemaSaude (idProblemaSaude),

                                             CONSTRAINT fk_Saude_Jovem FOREIGN KEY (idJovem)
                                                 REFERENCES sofiadb.Jovem (idJovem)
                                                 ON DELETE CASCADE ON UPDATE CASCADE,

                                             CONSTRAINT fk_Saude_ProblemaSaude FOREIGN KEY (idProblemaSaude)
                                                 REFERENCES sofiadb.ProblemaSaude (idProblemaSaude)
                                                 ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS sofiadb.Insignia (
                                                idInsignia INT NOT NULL AUTO_INCREMENT,
                                                nome VARCHAR(255) NOT NULL,

                                                PRIMARY KEY (idInsignia)
);

CREATE TABLE IF NOT EXISTS sofiadb.Desafio (
                                               idDesafio INT NOT NULL AUTO_INCREMENT,
                                               nome VARCHAR(255) NOT NULL,
                                               idInsignia INT,

                                               PRIMARY KEY (idDesafio),
                                               INDEX idx_idInsignia (idInsignia),

                                               CONSTRAINT fk_Desafio_Insignia FOREIGN KEY (idInsignia)
                                                   REFERENCES sofiadb.Insignia (idInsignia)
                                                   ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS sofiadb.DesafioInsignia (
                                                       idDesafioInsignia INT NOT NULL AUTO_INCREMENT,
                                                       nome VARCHAR(255) NOT NULL,
                                                       idInsignia INT NOT NULL,

                                                       PRIMARY KEY (idDesafioInsignia),
                                                       INDEX fk_DesafioInsignia_Insignia_idx (idInsignia),

                                                       CONSTRAINT fk_DesafioInsignia_Insignia FOREIGN KEY (idInsignia)
                                                           REFERENCES sofiadb.Insignia (idInsignia)
                                                           ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS sofiadb.DesafioInsigniaFeita (
                                                            id INT NOT NULL AUTO_INCREMENT,
                                                            idDesafioInsignia INT NOT NULL,
                                                            idJovem INT NOT NULL,
                                                            data DATETIME NOT NULL,

                                                            PRIMARY KEY (id),
                                                            INDEX idx_idDesafioInsignia (idDesafioInsignia),
                                                            INDEX idx_idJovem (idJovem),

                                                            CONSTRAINT fk_DesafioInsigniaFeita_DesafioInsignia FOREIGN KEY (idDesafioInsignia)
                                                                REFERENCES sofiadb.DesafioInsignia (idDesafioInsignia)
                                                                ON DELETE NO ACTION ON UPDATE NO ACTION,

                                                            CONSTRAINT fk_DesafioInsigniaFeita_Jovem FOREIGN KEY (idJovem)
                                                                REFERENCES sofiadb.Jovem (idJovem)
                                                                ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS sofiadb.AreaConhecimento (
                                                        idAreaConhecimento INT NOT NULL AUTO_INCREMENT,
                                                        nome VARCHAR(255) NOT NULL,

                                                        PRIMARY KEY (idAreaConhecimento)
);

CREATE TABLE IF NOT EXISTS sofiadb.Especialidade (
                                                     idEspecialidade INT NOT NULL AUTO_INCREMENT,
                                                     nome VARCHAR(255) NOT NULL,
                                                     idAreaConhecimento INT,

                                                     PRIMARY KEY (idEspecialidade),
                                                     INDEX idx_idAreaConhecimento (idAreaConhecimento),

                                                     CONSTRAINT fk_Especialidade_AreaConhecimento FOREIGN KEY (idAreaConhecimento)
                                                         REFERENCES sofiadb.AreaConhecimento (idAreaConhecimento)
                                                         ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS sofiadb.DesafioEspecialidade (
                                                            idDesafioEspecialidade INT NOT NULL AUTO_INCREMENT,
                                                            nome VARCHAR(255) NOT NULL,
                                                            idEspecialidade INT,

                                                            PRIMARY KEY (idDesafioEspecialidade),
                                                            INDEX idx_idEspecialidade (idEspecialidade),

                                                            CONSTRAINT fk_DesafioEspecialidade_Especialidade FOREIGN KEY (idEspecialidade)
                                                                REFERENCES sofiadb.Especialidade (idEspecialidade)
                                                                ON DELETE SET NULL ON UPDATE CASCADE
)
;
CREATE TABLE IF NOT EXISTS sofiadb.DesafioEspecialidadeFeita (
                                                                 id INT NOT NULL AUTO_INCREMENT,
                                                                 idDesafioEspecialidade INT NOT NULL,
                                                                 idJovem INT NOT NULL,
                                                                 data DATETIME NOT NULL,

                                                                 PRIMARY KEY (id),
                                                                 INDEX idx_idDesafioEspecialidade (idDesafioEspecialidade),
                                                                 INDEX idx_idJovem (idJovem),

                                                                 CONSTRAINT fk_DesafioEspecialidadeFeita_DesafioEspecialidade FOREIGN KEY (idDesafioEspecialidade)
                                                                     REFERENCES sofiadb.DesafioEspecialidade (idDesafioEspecialidade)
                                                                     ON DELETE NO ACTION ON UPDATE CASCADE,

                                                                 CONSTRAINT fk_DesafioEspecialidadeFeita_Jovem FOREIGN KEY (idJovem)
                                                                     REFERENCES sofiadb.Jovem (idJovem)
                                                                     ON DELETE NO ACTION ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS sofiadb.Acampamento (
                                                   idAcampamento INT NOT NULL AUTO_INCREMENT,
                                                   nome VARCHAR(255) NOT NULL,
                                                   data DATETIME NOT NULL,

                                                   PRIMARY KEY (idAcampamento)
);

CREATE TABLE IF NOT EXISTS sofiadb.NoiteAcampada (
                                                     id INT NOT NULL AUTO_INCREMENT,
                                                     idAcampamento INT NOT NULL,
                                                     idJovem INT NOT NULL,

                                                     PRIMARY KEY (id),
                                                     INDEX idx_idAcampamento (idAcampamento),
                                                     INDEX idx_idJovem (idJovem),

                                                     CONSTRAINT fk_NoiteAcampada_Acampamento FOREIGN KEY (idAcampamento)
                                                         REFERENCES sofiadb.Acampamento (idAcampamento)
                                                         ON DELETE CASCADE ON UPDATE CASCADE,

                                                     CONSTRAINT fk_NoiteAcampada_Jovem FOREIGN KEY (idJovem)
                                                         REFERENCES sofiadb.Jovem (idJovem)
                                                         ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS sofiadb.Distintivo (
                                                  idDistintivo INT NOT NULL AUTO_INCREMENT,
                                                  nome VARCHAR(45) NOT NULL,

                                                  PRIMARY KEY (idDistintivo)
);

CREATE TABLE IF NOT EXISTS sofiadb.DesafioDistintivo (
                                                         idDesafioDistintivo INT NOT NULL AUTO_INCREMENT,
                                                         descricao VARCHAR(45) NOT NULL,
                                                         idDistintivo INT,

                                                         PRIMARY KEY (idDesafioDistintivo),
                                                         INDEX idx_idDistintivo (idDistintivo),

                                                         CONSTRAINT fk_DesafioDistintivo_Distintivo FOREIGN KEY (idDistintivo)
                                                             REFERENCES sofiadb.Distintivo (idDistintivo)
                                                             ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS sofiadb.DesafioDistintivoFeita (
                                                              id INT NOT NULL AUTO_INCREMENT,
                                                              idDesafioDistintivo INT NOT NULL,
                                                              idJovem INT NOT NULL,
                                                              dataInicio DATETIME NOT NULL,
                                                              dataFim DATETIME NOT NULL,

                                                              PRIMARY KEY (id),
                                                              INDEX idx_idDesafioDistintivo (idDesafioDistintivo),
                                                              INDEX idx_idJovem (idJovem),

                                                              CONSTRAINT fk_DesafioDistintivoFeita_DesafioDistintivo FOREIGN KEY (idDesafioDistintivo)
                                                                  REFERENCES sofiadb.DesafioDistintivo (idDesafioDistintivo)
                                                                  ON DELETE NO ACTION ON UPDATE CASCADE,

                                                              CONSTRAINT fk_DesafioDistintivoFeita_Jovem FOREIGN KEY (idJovem)
                                                                  REFERENCES sofiadb.Jovem (idJovem)
                                                                  ON DELETE NO ACTION ON UPDATE CASCADE
);

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- POVOAMENTO --------------------------------------------------------------------------------------------

INSERT INTO TipoSanguineo (tipo) VALUES ('A+'), ('A-'), ('B+'), ('B-'), ('AB+'), ('AB-'), ('O+'), ('O-');

INSERT INTO Responsavel (nome, telefone, email) VALUES
                                                    ('Carla Mendes', '(48) 99999-1111', 'carla.mendes@email.com'),
                                                    ('Rafael Oliveira', '(48) 98888-2222', 'rafael.oliveira@email.com'),
                                                    ('Joana Pereira', '(48) 97777-3333', 'joana.pereira@email.com');

INSERT INTO Pessoa (nome, dataNascimento, telefone, email, idTipoSanguineo) VALUES
                                                                               ('Lucas Mendes', '2015-08-12', '(48) 98888-0001', 'lucas@email.com', 1),
                                                                               ('Lara Mendes', '2013-05-22', '(48) 98888-0002', 'lara@email.com', 2),
                                                                               ('Pedro Oliveira', '2014-11-03', '(48) 98888-0003', 'pedro@email.com', 3),
                                                                               ('Marina Oliveira', '2015-01-17', '(48) 98888-0004', 'marina@email.com', 4),
                                                                               ('Igor Oliveira', '2016-09-10', '(48) 98888-0005', 'igor@email.com', 5),
                                                                               ('Ana Pereira', '2014-06-28', '(48) 98888-0006', 'ana@email.com', 6),
                                                                               ('Felipe Pereira', '2013-12-15', '(48) 98888-0007', 'felipe@email.com', 7),
                                                                               ('Julia Pereira', '2016-03-09', '(48) 98888-0008', 'julia@email.com', 8);

-- Carla é responsável por Lucas e Lara
INSERT INTO Vinculo (idJovem, idResponsavel) VALUES
                                                 (1, 1), (2, 1);

-- Rafael é responsável por Pedro, Marina e Igor
INSERT INTO Vinculo (idJovem, idResponsavel) VALUES
                                                 (3, 2), (4, 2), (5, 2);

-- Joana é responsável por Ana, Felipe e Julia
INSERT INTO Vinculo (idJovem, idResponsavel) VALUES
                                                 (6, 3), (7, 3), (8, 3);

-- ÁREA DE CONHECIMENTO
INSERT INTO AreaConhecimento (nome) VALUES
                                        ('Ciência e Tecnologia'), ('Cultura'), ('Desporto'), ('Habilidade Escoteira'), ('Serviço');

-- ESPECIALIDADE
INSERT INTO Especialidade (nome, idAreaConhecimento) VALUES
                                                         ('Radioamadorismo', 1), ('Criptografia', 1),
                                                         ('História Regional', 2), ('Xadrez', 2),
                                                         ('Futebol', 3), ('Atletismo', 3),
                                                         ('Nó e Amarra', 4), ('Orientação', 4),
                                                         ('Primeiro Socorro', 5), ('Cuidado com Animal', 5);

-- DESAFIO DE ESPECIALIDADE (um por especialidade)
INSERT INTO DesafioEspecialidade (nome, idEspecialidade) VALUES
                                                             ('Desafio Radioamadorismo', 1),
                                                             ('Desafio Criptografia', 2),
                                                             ('Desafio História Regional', 3),
                                                             ('Desafio Xadrez', 4),
                                                             ('Desafio Futebol', 5),
                                                             ('Desafio Atletismo', 6),
                                                             ('Desafio Nó e Amarra', 7),
                                                             ('Desafio Orientação', 8),
                                                             ('Desafio Primeiro Socorro', 9),
                                                             ('Desafio Cuidado com Animal', 10);

-- INSÍGNIA DE INTERESSE ESPECIAL
INSERT INTO Insignia (nome) VALUES
                                ('Aprender'), ('Servir'), ('Liderar');

-- DESAFIO RELACIONADO A INSÍGNIA
INSERT INTO DesafioInsignia (nome, idInsignia) VALUES
                                                   ('Desafio de Aprender - Leitura', 1),
                                                   ('Desafio de Servir - Comunidade', 2),
                                                   ('Desafio de Liderar - Patrulha', 3);

-- DISTINTIVO DE PROGRESSÃO
INSERT INTO Distintivo (nome) VALUES
                                  ('Lobo Pata Tenra'), ('Lobo Saltador'), ('Lobo Rastreador'), ('Lobo Caçador'), ('Cruzeiro do Sul');

-- DESAFIO PARA DISTINTIVO
INSERT INTO DesafioDistintivo (descricao, idDistintivo) VALUES
                                                            ('Conhecer a alcateia', 1),
                                                            ('Participar de um jogo simbólico', 2),
                                                            ('Realizar um acampamento', 3),
                                                            ('Concluir três especialidades', 4),
                                                            ('Concluir requisito final', 5);

-- ACAMPAMENTO
INSERT INTO Acampamento (nome, data) VALUES
                                         ('Acampamento da Lua', '2025-04-20'),
                                         ('Trilha do Lobinho', '2025-06-10');

-- NOITE ACAMPADA
INSERT INTO NoiteAcampada (idAcampamento, idJovem) VALUES
                                                       (1, 1), (1, 2), (1, 3), (2, 4), (2, 5), (2, 6), (2, 7), (2, 8);

-- DESAFIO DE ESPECIALIDADE FEITA POR JOVEM
INSERT INTO DesafioEspecialidadeFeita (idDesafioEspecialidade, idJovem, data) VALUES
                                                                                  (1, 1, '2025-05-01'),
                                                                                  (2, 2, '2025-05-02'),
                                                                                  (3, 3, '2025-05-03'),
                                                                                  (4, 4, '2025-05-04'),
                                                                                  (5, 5, '2025-05-05'),
                                                                                  (6, 6, '2025-05-06'),
                                                                                  (7, 7, '2025-05-07'),
                                                                                  (8, 8, '2025-05-08'),
                                                                                  (9, 1, '2025-05-09'),
                                                                                  (10, 2, '2025-05-10');

-- DESAFIO DE INSÍGNIA FEITA POR JOVEM
INSERT INTO DesafioInsigniaFeita (idDesafioInsignia, idJovem, data) VALUES
                                                                        (1, 1, '2025-05-11'),
                                                                        (2, 2, '2025-05-12'),
                                                                        (3, 3, '2025-05-13'),
                                                                        (1, 4, '2025-05-14'),
                                                                        (2, 5, '2025-05-15');

-- DESAFIO DE DISTINTIVO FEITO POR JOVEM
INSERT INTO DesafioDistintivoFeita (idDesafioDistintivo, idJovem, dataInicio, dataFim) VALUES
                                                                                           (1, 1, '2025-04-01', '2025-04-15'),
                                                                                           (2, 2, '2025-04-05', '2025-04-20'),
                                                                                           (3, 3, '2025-04-10', '2025-04-25'),
                                                                                           (4, 4, '2025-04-15', '2025-04-30'),
                                                                                           (5, 5, '2025-05-01', '2025-05-15');



-- 1. Inserir novo lobinho (jovem)
INSERT INTO Jovem (nome, dataNascimento, telefone, email, idTipoSanguineo)
VALUES ('Sofia Alves Toreti', '2015-02-09', '(47) 99454-3482', 'sofia.a2005@aluno.ifsc.edu.br', 1);

-- 2. Vincular responsável (vamos usar um responsável existente, ex: idResponsavel = 1)
INSERT INTO Vinculo (idJovem, idResponsavel)
VALUES (9, 1);

-- 3. Inserir 1 insígnia feita para esse lobinho (ex: idDesafioInsignia = 1)
INSERT INTO DesafioInsigniaFeita (idDesafioInsignia, idJovem, data)
VALUES (1, 9, NOW());

-- 4. Inserir pelo menos 5 especialidades feitas em pelo menos 3 áreas diferentes

-- Vamos pegar 5 especialidades distintas (IDs 1 a 5, de áreas diferentes)
INSERT INTO DesafioEspecialidadeFeita (idDesafioEspecialidade, idJovem, data) VALUES
                                                                                  (1, 9, NOW()),
                                                                                  (2, 9, NOW()),
                                                                                  (3, 9, NOW()),
                                                                                  (4, 9, NOW()),
                                                                                  (5, 9, NOW());

-- 5. Dar o distintivo Lobo Caçador ao lobinho (idDesafioDistintivo relacionado ao Lobo Caçador = 4)
INSERT INTO DesafioDistintivoFeita (idDesafioDistintivo, idJovem, dataInicio, dataFim)
VALUES (4, 9, NOW(), DATE_ADD(NOW(), INTERVAL 15 DAY));

-- 6. Dar o distintivo Cruzeiro do Sul ao lobinho (idDesafioDistintivo = 5)
INSERT INTO DesafioDistintivoFeita (idDesafioDistintivo, idJovem, dataInicio, dataFim)
VALUES (5, 9, NOW(), DATE_ADD(NOW(), INTERVAL 15 DAY));




SELECT j.idJovem, j.nome FROM Jovem j

-- Verifica se o jovem tem o distintivo "Lobo Caçador" (idDistintivo = 4)
                                  INNER JOIN DesafioDistintivoFeita ddf_lc ON ddf_lc.idJovem = j.idJovem
                                  INNER JOIN DesafioDistintivo dd_lc ON dd_lc.idDesafioDistintivo = ddf_lc.idDesafioDistintivo
                                  INNER JOIN Distintivo dist_lc ON dist_lc.idDistintivo = dd_lc.idDistintivo
    AND dist_lc.nome = 'Lobo Caçador'

-- Verifica se o jovem tem o distintivo "Cruzeiro do Sul"
                                  INNER JOIN DesafioDistintivoFeita ddf_cs ON ddf_cs.idJovem = j.idJovem
                                  INNER JOIN DesafioDistintivo dd_cs ON dd_cs.idDesafioDistintivo = ddf_cs.idDesafioDistintivo
                                  INNER JOIN Distintivo dist_cs ON dist_cs.idDistintivo = dd_cs.idDistintivo
    AND dist_cs.nome = 'Cruzeiro do Sul'

-- Verifica se tem pelo menos 1 insígnia feita
                                  INNER JOIN DesafioInsigniaFeita dif ON dif.idJovem = j.idJovem

-- Verifica especialidades feitas (pelo menos 5) em pelo menos 3 áreas de conhecimento distintas
                                  INNER JOIN DesafioEspecialidadeFeita def ON def.idJovem = j.idJovem
                                  INNER JOIN DesafioEspecialidade de ON de.idDesafioEspecialidade = def.idDesafioEspecialidade
                                  INNER JOIN Especialidade e ON e.idEspecialidade = de.idEspecialidade
                                  INNER JOIN AreaConhecimento ac ON ac.idAreaConhecimento = e.idAreaConhecimento

GROUP BY j.idJovem, j.nome

HAVING
    COUNT(DISTINCT def.idDesafioEspecialidade) >= 5 -- pelo menos 5 especialidades feitas
   AND COUNT(DISTINCT ac.idAreaConhecimento) >= 3 -- pelo menos 3 áreas de conhecimento diferentes
   AND COUNT(DISTINCT dif.idDesafioInsignia) >= 1 -- pelo menos 1 insígnia feita
;