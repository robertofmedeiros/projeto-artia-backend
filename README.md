# Projeto Cadastro de Projetos (Backend)
Projeto de exemplo de backend para manutenção de projetos.
## Tecnologia
* java 11
* spring boot 2.5.2
* jpa
* lombok
* mysql 8.0.25
* sprinfox
* docker
## Instalaçao
Para instalçao do banco de dados da aplicação no docker, deve er executado os comandos abaixo.
Todas as configurações do banco de dados e status da aplicação será feita automaticamente.
* realizar o package do maven no intellij
* executar docker-compose up -d --build como root

Comando deve ser executado no mesmo diretório do arquivo docker-compose.yml.

Tabelas serão criadas automaticamente quando o backend for iniciado.

Caso já possua um banco de dados instalado, pode ser realizado o apontamento no arquivo
application.yml. Arquivo está na pasta resorce do projeto.
## Aplicação
Foi criado um backend para insersão e manutenção de projetos e atividades (CRUD), a comunicação
é feita utilizando APIs rest com json. Todos o métodos foram documentados utilizando o padrão
swagger.
### Swagger
Acessando a url abaixo é possivel visualizar todos as rotas da aplicação, bem como realizar
chamadas manuais para testes.
* http://localhost:8081/swagger-ui.html

Obs: Aplicação deve estar iniciada

### Base de dados
Para teste local a aplicação irão a usar os parametros do arquico application.yml (default),
já dentro do docker a aplicação será iniciada utilizando a arquivo application-docker.yml.
O perfil docker sobreescreve o endereço do datasource do perfil default, demais parametros serão
usadas do application.yml
   