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
    ('Taylor Swift', '131.131.131-13', 'Av. Industria Da Música, 13 - Nashville - TN', '(48) 99345-2000', '2010-07-24', 'Feminino', 1),
    ('Betty', '111.333.131-13', 'Av. Folklore, 24 – Mystic Falls – RJ', '(21) 92345-2000', '2011-05-15', 'Feminino', 2),
    ('Augustine', '131.333.131-13', 'Rua Cardigan, 17 – Folklore Village – RJ', '(21) 93456-3000', '2012-03-10', 'Feminino', 1),
    ('James', '514.514.514-14', 'Cardigan Street, 89 - Teenage Triangle - SP', '(11) 95555-5555', '2013-08-12', 'Masculino', 8),
    ('Inez', '615.615.615-15', 'Reputation Avenue, 1989 - Big City - DF', '(61) 96666-6666', '2014-11-22', 'Feminino', 1),
    ('Dorothea', '716.716.716-16', 'Willow Lane, 22 - Small Town - RS', '(51) 97777-7777', '2015-06-30', 'Feminino', 2),
    ('Ronan', '817.817.817-17', 'Forever Winter Street, 4 - Sad Beautiful - SC', '(48) 98888-8888', '2016-09-04', 'Masculino', 3),
    ('Abigail', '918.918.918-18', 'Fifteen Avenue, 15 - High School - PR', '(41) 99999-9999', '2017-12-13', 'Feminino', 4),
    ('Romeo', '019.019.019-19', 'Love Story Boulevard, 2008 - Fairytale - MG', '(31) 90000-0000', '2018-02-14', 'Masculino', 5),
    ('Cassandra', '113.113.113-13', 'The Tortured Poets Department - Prophecy City', '(11) 91111-1111', '2016-04-19', 'Feminino', 6);



    -- 9. RESPONSÁVEIS (TEMÁTICA TAYLOR SWIFT)
    INSERT INTO Responsavel (pessoa_idPessoa, nome, email, telefone) VALUES
    (1, 'Andrea Swift', 'andrea.swift@email.com', '(48) 98888-8888'),
    (2, 'Rebekah Harkness', 'rebekah.folklore@email.com', '(21) 97777-7777'),
    (3, 'Joni Mitchell', 'joni.cardigan@email.com', '(21) 96666-6666'),
    (4, 'William Bowery', 'william.folklore@email.com', '(11) 95555-5555'),
    (5, 'Jack Antonoff', 'jack.producer@email.com', '(61) 94444-4444'),
    (6, 'Aaron Dessner', 'aaron.national@email.com', '(51) 93333-3333'),
    (7, 'Liz Rose', 'liz.songwriter@email.com', '(48) 92222-2222'),
    (8, 'Colbie Caillat', 'colbie.breathe@email.com', '(41) 91111-1111'),
    (9, 'Tim McGraw', 'tim.country@email.com', '(31) 90000-0000'),
    (10, 'Stevie Nicks', 'stevie.prophecy@email.com', '(11) 99999-9999');

    -- 10. VÍNCULOS
    INSERT INTO Vinculo (idPessoa, idResponsavel) VALUES
    (1, 1), (2, 2), (3, 3), (4, 4), (5, 5), (6, 6), (7, 7), (8, 8), (9, 9), (10, 10);

    -- 11. ACAMPAMENTOS TEMÁTICOS
    INSERT INTO Acampamento (nome, data) VALUES
    ('Acampamento Fearless', '2024-01-15'),
    ('Acampamento Speak Now', '2024-03-25'),
    ('Acampamento Red', '2024-05-20'),
    ('Acampamento 1989', '2024-07-13'),
    ('Acampamento Reputation', '2024-08-17'),
    ('Acampamento Lover', '2024-08-23'),
    ('Acampamento Folklore', '2024-07-24'),
    ('Acampamento Evermore', '2024-12-11'),
    ('Acampamento Midnights', '2024-10-21'),
    ('Acampamento TTPD', '2024-04-19');
    -- 8. DADOS DE SAÚDE
    INSERT INTO Dadosaude (idPessoa, idProblema) VALUES
    (1, 1), (2, 6), (3, 7), (4, 2), (5, 8), (7, 4), (8, 5);

    -- 9. RESPONSÁVEIS

    -- 10. VÍNCULOS
    INSERT INTO Vinculo (idPessoa, idResponsavel) VALUES
    (1, 1), (2, 2), (3, 3), (4, 4), (5, 5), (6, 6), (7, 7), (8, 8);

    -- 11. ACAMPAMENTOS

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
