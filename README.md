# Modelagem do Banco de Dados – Registro de Progressões no Movimento Escoteiro (Ramo Lobinho)

Este arquivo contém o diagrama Entidade-Relacionamento (ER) do banco de dados para o projeto de registro de progressões no ramo Lobinho, 
conforme solicitado na primeira entrega da disciplina de Banco de Dados.

O diagrama está modelado em terceira forma normal (3FN) e contempla as entidades principais, seus atributos e os relacionamentos entre eles, incluindo:

- Dados biográficos dos jovens (Lobinhos) e responsáveis
- Contatos e dados de saúde dos jovens
- Participação em atividades
- Especialidades, requisitos e níveis
- Distintivos, progressões e insígnias

## Diagrama ER

```mermaid
erDiagram

%% RELACIONAMENTOS

    PESSOA ||--o{ VINCULO : "possui"
    RESPONSAVEL ||--o{ VINCULO : "responsável por"
    PESSOA ||--o{ DADO_SAUDE : "tem"
    PROBLEMAS_SAUDE ||--|| DADO_SAUDE : "é"
    TIPO_SANGUINEO ||--o{ PESSOA : "tipo de"

    PESSOA ||--o{ DESAFIOS_DISTINTIVOS_FEITOS : "realiza"
    DESAFIOS_DISTINTIVOS ||--o{ DESAFIOS_DISTINTIVOS_FEITOS : "pertence"
    DISTINTIVOS ||--o{ DESAFIOS_DISTINTIVOS : "compõe"

    PESSOA ||--o{ NOITES_ACAMPADAS : "acampa"
    ACAMPAMENTOS ||--o{ NOITES_ACAMPADAS : "contém"

    PESSOA ||--o{ DESAFIOS_INSIGNIA_FEITOS : "realiza"
    DESAFIOS_INSIGNIA ||--o{ DESAFIOS_INSIGNIA_FEITOS : "pertence"
    INSIGNIAS ||--o{ DESAFIOS_INSIGNIA : "compõe"

    PESSOA ||--o{ DESAFIO_ESPECIALIDADE : "executa"
    DESAFIOS_ESPECIALIDADEFEITO ||--o{ DESAFIO_ESPECIALIDADE : "é"
    ESPECIALIDADE ||--o{ DESAFIOS_ESPECIALIDADE : "tem"
    AREA_CONHECIMENTO ||--o{ ESPECIALIDADE : "pertence"

%% ENTIDADES

    PESSOA {
        int id_pessoa PK
        varchar nome
        date data_nascimento
        varchar telefone
        varchar cpf
        int id_tipo_sanguineo FK
    }

    VINCULO {
        int id_pessoa FK
        int id_responsavel FK
    }

    RESPONSAVEL {
        int id_responsavel PK
        int id_pessoa FK
        varchar nome
        varchar email
        varchar telefone
    }

    TIPO_SANGUINEO {
        int id_tipo_sanguineo PK
        varchar tipo
    }

    DADO_SAUDE {
        int id_pessoa FK
        int id_problema_saude FK
    }

    PROBLEMAS_SAUDE {
        int id_problema_saude PK
        varchar tipo_problema
        varchar descricao
    }

    DISTINTIVOS {
        int id_distintivo PK
        varchar nome
    }

    DESAFIOS_DISTINTIVOS {
        int id_desafio_distintivo PK
        varchar descricao
        int id_distintivo FK
    }

    DESAFIOS_DISTINTIVOS_FEITOS {
        int id_desafio_distintivo FK
        int id_pessoa FK
        date data_inicio
        date data_fim
    }

    ACAMPAMENTOS {
        int id_acampamento PK
        varchar nome
        date data
    }

    NOITES_ACAMPADAS {
        int id_acampamento FK
        int id_pessoa FK
    }

    INSIGNIAS {
        int id_insignia PK
        varchar nome
    }

    DESAFIOS_INSIGNIA {
        int id_desafio_insignia PK
        varchar nome
        int id_insignia FK
    }

    DESAFIOS_INSIGNIA_FEITOS {
        int id_desafio_insignia FK
        int id_pessoa FK
        date data
    }

    AREA_CONHECIMENTO {
        int id_area_conhecimento PK
        varchar nome
    }

    ESPECIALIDADE {
        int id_especialidade PK
        varchar nome
        int id_area_conhecimento FK
    }

    DESAFIOS_ESPECIALIDADE {
        int id_desafio_especialidade PK
        varchar descricao
        int id_especialidade FK
    }

    DESAFIO_ESPECIALIDADE {
        int id_desafio_especialidade FK
        int id_pessoa FK
        date data
    }

```

**Aluna:** Júlia Manuela Turnes
**Curso:** Análise e Desenvolvimento de Sistemas – IFSC
