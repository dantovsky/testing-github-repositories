# Trabalho Prático da Disciplina de `Testes de Software`
Universidade de Aveiro - ESTGA

Professores: Joaquim Ferreira / André Campos  
Aluno: Dante Marinho

Este trabalho consiste na implementação de um processo de testes de integração, onde teve foco na API do GitHub, v3.

## Como correr o programa

Pré-requisitos:
- Personal access token (obter no setting do GitHub)

Aconselha-se utlizar uma conta de testes do GitHub, uma vez que a execução dos testes irá gerar novos repositório, assim como removê-los.

Deverá adicionar o token em `src/main/java/api/data/Common.java`

Em `src/test/java/api/generic/ReposCallTests.java` poderá inserir um nome de username e o nome de um repositório, para a execução de alguns testes.
- public static final String USERNAME = "username";
- public static final String REPO_NAME = "repository-name";
