# DoaLivros 📚

Plataforma de doação de livros desenvolvida como Prova de Conceito (PoC) para a AEP 2026.2 do curso de Engenharia de Software — Unicesumar.

## O Problema

Muitas pessoas têm livros parados em casa que não usam mais, enquanto outras não têm acesso a materiais de leitura e estudo. O DoaLivros conecta quem quer doar com quem precisa receber, promovendo o acesso à educação de forma simples e gratuita.

## ODS Relacionado

**ODS 4 — Educação de Qualidade**  
Garantir educação inclusiva e equitativa de qualidade e promover oportunidades de aprendizagem ao longo da vida para todos.

## Tecnologias

- Java 17
- Spring Boot 4.1.1
- MongoDB (NoSQL)
- JUnit 5 + Mockito (testes)
- JaCoCo (cobertura de testes)
- Swagger/OpenAPI (documentação da API)
- Maven (gerenciador de dependências)

## Pré-requisitos

- Java 17 instalado
- MongoDB instalado e rodando na porta 27017
- Maven instalado

## Como rodar o projeto

1. Clone o repositório:
```bash
git clone https://github.com/masscarello/AEP-6SEMESTRE-DOALIVROS.git
cd AEP-6SEMESTRE-DOALIVROS/DoaLivros
```

2. Suba a aplicação:
```bash
mvn spring-boot:run
```

3. Acesse a documentação da API:
http://localhost:8080/swagger-ui/index.html


## Como rodar os testes

```bash
cd DoaLivros
mvn test
```

## Como gerar o relatório de cobertura (JaCoCo)

O relatório é gerado automaticamente ao rodar os testes:

```bash
mvn test
```

Depois abra no navegador:
DoaLivros/target/site/jacoco/index.html



## Cobertura atual

- Total: **79%** ✅
- Controllers: 100%
- Services: 96%

## Estrutura do projeto

```
DoaLivros/src/
├── main/java/com/AEP/DoaLivros/
│   ├── models/           # Entidades do MongoDB
│   ├── repositories/     # Comunicação com o banco
│   ├── services/         # Regras de negócio
│   └── controllers/      # Endpoints REST
└── test/java/com/AEP/DoaLivros/
    ├── controllers/      # Testes dos controllers
    └── services/         # Testes dos services
```


## Coleções no MongoDB

- `livros` — cadastro de livros disponíveis para doação
- `usuarios` — cadastro de doadores e receptores
- `pedidos` — solicitações de doação

## Equipe

| Dev | Responsabilidade |
|-----|-----------------|
| Eduardo | Gestão de Livros |
| Matheus | Gestão de Usuários |
| Gabriel | Gestão de Pedidos |