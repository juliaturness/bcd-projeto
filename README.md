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
%% Entidades Principais
    PESSOA ||--o{ JOVEM : "é"
    PESSOA ||--o{ RESPONSAVEL : "é"
    JOVEM ||--|| LOBO : "é"

%% Relacionamentos de Dados Pessoais
    JOVEM ||--o{ CONTATO : "tem"
    JOVEM ||--|| DADO_SAUDE : "possui"
    JOVEM ||--o{ RESPONSAVEL_JOVEM : "tem"
    RESPONSAVEL_JOVEM }|--|| RESPONSAVEL : "vincula"

%% Progressão e Atividades
    LOBO ||--o{ PROGRESSAO : "conquista"
    PROGRESSAO }|--|| DISTINTIVO : "recebe"
    LOBO ||--o{ ATIVIDADE_PARTICIPACAO : "participa"
    ATIVIDADE_PARTICIPACAO }|--|| ATIVIDADE : "realiza"

%% Especialidades e Insígnias
    LOBO ||--o{ ESPECIALIDADE_JOVEM : "desenvolve"
    ESPECIALIDADE_JOVEM }|--|| ESPECIALIDADE : "conquista"
    ESPECIALIDADE ||--o{ REQUISITO_ESPECIALIDADE : "exige"
    LOBO ||--o{ INSIGNIA_JOVEM : "obtém"
    INSIGNIA_JOVEM }|--|| INSIGNIA : "recebe"
    INSIGNIA ||--o{ REQUISITO_INSIGNIA : "requer"

%% Requisitos e Níveis
    ESPECIALIDADE_JOVEM ||--|| NIVEL_ESPECIALIDADE : "atinge"

    PESSOA {
        int id_pessoa PK
        varchar nome
        date data_nascimento
        varchar endereco
        char genero
        varchar cpf
        int telefone
    }

    JOVEM {
        int id_jovem PK
        int id_pessoa FK
        date data_ingresso
        varchar status
    }

    RESPONSAVEL {
        int id_responsavel PK
        int id_pessoa FK
        varchar parentesco
    }

    LOBO {
        int id_lobo PK
        int id_jovem FK
        varchar patrulha
        date data_promocao_proximo_nivel
    }

    CONTATO {
        int id_contato PK
        int id_pessoa FK
        varchar tipo
        varchar valor
        boolean principal
    }

    DADO_SAUDE {
        int id_dado_saude PK
        int id_jovem FK
        varchar tipo_sanguineo
        text alergias
        text medicamentos
        text restricoes
        text observacoes
    }

    RESPONSAVEL_JOVEM {
        int id_responsavel_jovem PK
        int id_jovem FK
        int id_responsavel FK
    }

    ESPECIALIDADE_JOVEM {
        int id_especialidade_jovem PK
        int id_lobo FK
        int id_especialidade FK
        date data_inicio
        date data_conclusao
        int nivel
        int requisitos_completos
    }

    NIVEL_ESPECIALIDADE {
        int id_nivel PK
        varchar nome
        text criterio_conquista
    }

    ESPECIALIDADE {
        int id_especialidade PK
        int id_area FK
        varchar nome
        text descricao
        int total_requisitos
    }

    REQUISITO_ESPECIALIDADE {
        int id_requisito PK
        int id_especialidade FK
        text descricao
        int etapa
    }

    ATIVIDADE_PARTICIPACAO {
        int id_participacao PK
        int id_lobo FK
        int id_atividade FK
        boolean presenca
        varchar avaliacao
        text observacoes
    }

    ATIVIDADE {
        int id_atividade PK
        varchar nome
        text descricao
        date data
        varchar local
        varchar tipo
    }

    PROGRESSAO {
        int id_progressao PK
        int id_lobo FK
        int id_distintivo FK
        date data_conquista
        text observacoes
        boolean aprovado_velho_lobo
    }

    DISTINTIVO {
        int id_distintivo PK
        varchar nome
        text descricao
        int ordem_progressao
    }

    INSIGNIA_JOVEM {
        int id_insignia_jovem PK
        int id_lobo FK
        int id_insignia FK
        date data_conquista
        text observacoes
    }

    INSIGNIA {
        int id_insignia PK
        varchar nome
        text descricao
        int total_requisitos
        varchar tipo
    }

    REQUISITO_INSIGNIA {
        int id_requisito PK
        int id_insignia FK
        text descricao
        int etapa
    }
```

**Aluna:** Júlia Manuela Turnes
**Curso:** Análise e Desenvolvimento de Sistemas – IFSC
