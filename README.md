# Project Manager API

Uma API para **gerenciamento de projetos**, permitindo controlar projetos, membros e tarefas de forma organizada e eficiente.

## Funcionalidades

### Projetos
- Criar, listar, atualizar e remover projetos.
- Acompanhar o progresso geral de cada projeto.

### Membros
- Adicionar e gerenciar membros de cada projeto.
- Associar membros a tarefas específicas.

### Tarefas
- Criar, listar, atualizar e remover tarefas.
- Atribuir tarefas a membros.
- Definir status e prioridades das tarefas.

## Tecnologias
- **Backend:** Java, Spring Boot
- **Banco de dados:** MySQL / PostgreSQL (ou outro de sua escolha)
- **Documentação da API:** Swagger / OpenAPI
- **Controle de versão:** Git e GitHub

## Estrutura da API

Exemplo de endpoints:

| Recurso                        | Método | Descrição                          |
|--------------------------------|--------|------------------------------------|
| `/projects`                     | GET    | Listar todos os projetos           |
| `/projects`                     | POST   | Criar novo projeto                 |
| `/projects/{id}`                | PUT    | Atualizar projeto                  |
| `/projects/{id}`                | DELETE | Remover projeto                    |
| `/members`                      | GET    | Listar todos os membros            |
| `/members`                      | POST   | Adicionar novo membro              |
| `/tasks`                        | GET    | Listar todas as tarefas            |
| `/tasks`                        | POST   | Criar nova tarefa                  |
| `/projects/{id}/tasks`          | GET    | Listar tarefas de um projeto       |
| `/members/{id}/tasks`           | GET    | Listar tarefas de um membro        |

### Exemplos de JSON

**Criar um projeto:**
```json
{
  "name": "Projeto X",
  "description": "Descrição do projeto X",
  "deadline": "2025-12-31"
}
```
**Criar um membro:**
```json
{
  "name": "João Silva",
  "email": "joao@email.com"
}
```
**Criar uma tarefa:**
```json
{
  "title": "Desenvolver API",
  "description": "Implementar endpoints do backend",
  "status": "PENDING",
  "priority": "HIGH",
  "projectId": 1,
  "memberId": 2
}
```
## Como rodar o projeto
1. Clone o repositorio:
```json
git clone https://github.com/seu-usuario/project-manager-api.git
cd project-manager-api
```
2. Configure o banco de dados no application.properties ou application.yml.
3. Rode a aplicação com:
```json
./mvnw spring-boot:run
```
### Contato

Caso queira trocar ideias ou sugerir melhorias:  
[LinkedIn](https://www.linkedin.com/in/pauloflau/) 

## Contribuição
Contribuições são bem-vindas!

Faça um fork do projeto.

1. Crie uma branch para sua feature: git checkout -b minha-feature.
2. Faça commit das mudanças: git commit -m "Minha nova feature".
3. Envie para o repositório remoto: git push origin minha-feature.
4. Abra um Pull Request.

## Licença

Este projeto está sob a licença MIT.
