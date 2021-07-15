# `Testes de Software` » Trabalho Prático
Universidade de Aveiro - ESTGA

Professores: Joaquim Ferreira / André Campos  
Aluno: Dante Marinho

![Testes de software](online-test.png)

Este trabalho consiste na implementação de um processo de testes de integração, onde teve foco na API do GitHub, v3, tendo sido escolhida a reference `Repositories` para a implementação dos testes automáticos.

## Como correr o programa

Pré-requisitos:
- Personal access token (obter no setting do GitHub)
- Username da conta do GitHub associada ao token

Aconselha-se utlizar uma conta de testes do GitHub, uma vez que a execução dos testes irá gerar novos repositório, assim como removê-los.

O token e o username deverão ser adicionados em `src/main/java/api/data/Common.java`. São as seguintes variáveis estáticas:

- TOKEN: no formato “token ghp_xptoXxxxxxXxxXxxXXxxxXxxxXXxx”
- REPO_USERNAME: username do repositório Git sob o qual incidirão os testes