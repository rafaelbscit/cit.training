Para executar o docker com o mysql, utilizar os comandos abaixos:


###################### IMAGEM MYSQL #####################
LINK: https://hub.docker.com/_/mysql/

* CRIA UM NOVO SERVIDOR DO MYSQL COM BD, USER E SENHA
	$-> docker run -d -p 3306:3306 --name cit-mysql -e MYSQL_ROOT_PASSWORD=cit@2016 -e MYSQL_DATABASE=test_db -e MYSQL_USER=test -e MYSQL_PASSWORD=test -d mysql:latest

* EXECUTA UM COMANDO DENTRO DO CONTAINER
	$-> docker exec -i cit-mysql mysql --user=test --password=test

********************** MYSQL **************************
* EXECUTAR OS SCRIPTS: contacts/contacts-repository/src/main/resources/sql
    $-> mysql --user=root --password=cit@2016 --port=3306 --host=0.0.0.0

* ADMINISTRA O BANCO DE DADOS CONTACTSBD
    $-> mysql --user=contact --password=contact --port=3306 --host=0.0.0.0


####################### IMAGEM REDIS ####################
LINK: https://hub.docker.com/_/redis/

* CRIA UM NOVO SERVIDOR DO REDIS
	$-> docker run -p 6379:6379 --name cit-redis -d redis:latest redis-server --appendonly yes

* EXECUTA UM COMANDO DENTRO DO CONTAINER
	$-> docker exec -i cit-redis redis-cli INFO

	****************** REDIS ******************
	Dados para conectar no servidor redis:
	    - host: 0.0.0.0
	    - port: 6379