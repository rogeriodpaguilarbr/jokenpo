Item: Configuração
=======================

Para que a aplicação execute corretamente, é necessário:

1 - clonar este repositório em algum diretório da máquina de testes ou realizar o download do arquivo zip 
    utilizando o link zip da página e depois do término do download descompactar o arquivo em algum diretório 
    na máquina de testes;

2 - instalar o jdk versão 1.8;

3 - instalar o maven (http://maven.apache.org/users/index.html);

	OBS: a instalação dos produtos anteriores requer a configuração de variáveis de ambiente
	para que os executáveis sejam encontrados. Os detalhes sobre estas configurações podem ser
	encontrados nos sites dos produtos e não serão detalhados neste documento.
	A variável de ambiente JAVA_HOME deve ser configurada para apontar para o diretório de instalação do jdk e
	a variável PATH deve ser configurada para apontar para os diretórios bin de instalação do jdk e do maven.

	O site a seguir ensina como instalar o maven no windows (fiz o desenvolvimento e testes no ubuntu):

	http://maven3tutorial.blogspot.com.br/2011/02/instalando-o-maven-no-seu-computador.html


4 - ler o Item:Funcionamento para configurar os caminhos necessários para a apliação funcionar corretamente;


Item: Como utilizar o programa
=======================

1 - após instalar os programas necessários (Item: Configuração), é necessário certificar-se de que o computador
    possua conexão com a internet, pois na primeira execução o maven irá realizar o download dos artefatos
    necessários para execução. Também é necessário certificar-se que a porta 8080 não esteja sendo utilizada
    por algum outro processo, pois esta porta será utilizada pelo servidor. Caso exista algum outro processo
    utilizando esta porta, é necessário terminá-lo antes de iniciar o servidor;

2 - abrir o terminal e posicionar no diretório onde encontra-se o arquivo pom.xml;

3 - rodar o comando mvn clean install (caso ocorra algum problema, será necessário verificar a instalação dos produtos
    e configurações de variáveis de ambiente). Este item pode demorar , pois o maven executará o download de diversas dependências.
    É necessário aguardar no terminal o término da execução deste comando antes de executar o passo 4;

4 - executar no terminal o comando mvn spring-boot:run

    Aguarde aparecer no terminal a mensagem abaixo antes de ir ao passo 5:
	
    OBS: Na primeira vez que executar o passo 4 ocorrerá uma demora pois mais artefatos serão copiados da internet.
    Após executar a primeira vez, a execução será bem mais rápida pois todos os artefatos necessários já
    estarão no repositório local. Aguarde aparecer algo parecido com:
    
 :: Spring Boot ::        (v2.3.0.RELEASE)
(JVM running for <algum número>)
    

5 - abrir o browser e navegar na url 

	http://localhost:8080/jogador/listar
	
6 - Deverá aparecer uma lista no formato json contendo 10 jogadores que a aplicação cadastra
    automatiamente quando é executada, algo parecido com (requisição http tipo GET):
    	
    [{"nome":"Teste Nome 0","id":"f1db17e9-586a-41cd-9b5c-ce4bb0c0a961"},{"nome":"Teste Nome    1","id":"88c0aea0-93d8-4dc8-99b0-da242ba53968"},{"nome":"Teste Nome 2","id":"0ebc1256-c419-4c85-bd2c-40a26a6367bc"},{"nome":"Teste Nome 3","id":"c5c4cf8d-f9ed-4a9d-8a2a-b550b8598feb"},{"nome":"Teste Nome 4","id":"c17011c3-4aed-4784-bbb6-c6525f2866ca"},{"nome":"Teste Nome 5","id":"85ae5aca-33d7-4857-bc0f-6e1d6b764157"},{"nome":"Teste Nome 6","id":"0a898652-1c84-4d7a-9824-2b97cb89b95e"},{"nome":"Teste Nome 7","id":"bec4da28-f016-49d3-8d95-0795fdd3a368"},{"nome":"Teste Nome 8","id":"1ca87c71-774a-477d-a58d-d1c52b308cbc"},{"nome":"Teste Nome 9","id":"4b628fd6-69ac-4eab-a245- c64943035e4a"}]    	


Cada jogador possui um nome e um id gerado automatiamente


7 - para remover um jogador, utilizar a url (requisição http tipo GET):

	http://localhost:8080/jogador/remover/{id}
	
	substituindo {id} pelo uuid de algum jogador cadastrado.
	
	No exemplo acima, chamando http://localhost:8080/jogador/remover/1ca87c71-774a-477d-a58d-d1c52b308cbc
	o jogador "Teste Nome 8" removido e, chamando a url anterior, não existirá mais na lista. Será retornado um
	dado no formato json com os dados do jogador excluído.
	
    [{"nome":"Teste Nome 0","id":"f1db17e9-586a-41cd-9b5c-ce4bb0c0a961"},{"nome":"Teste Nome 1","id":"88c0aea0-93d8-4dc8-99b0-da242ba53968"},{"nome":"Teste Nome 2","id":"0ebc1256-c419-4c85-bd2c-40a26a6367bc"},{"nome":"Teste Nome 3","id":"c5c4cf8d-f9ed-4a9d-8a2a-b550b8598feb"},{"nome":"Teste Nome 4","id":"c17011c3-4aed-4784-bbb6-c6525f2866ca"},{"nome":"Teste Nome 5","id":"85ae5aca-33d7-4857-bc0f-6e1d6b764157"},{"nome":"Teste Nome 6","id":"0a898652-1c84-4d7a-9824-2b97cb89b95e"},{"nome":"Teste Nome 7","id":"bec4da28-f016-49d3-8d95-0795fdd3a368"},{"nome":"Teste Nome 9","id":"4b628fd6-69ac-4eab-a245-c64943035e4a"}]	

8 - para consultar um jogador, utilizar a url (requisição http tipo GET):

	http://localhost:8080/jogador/consultar/{id}
	
	substituindo {id} pelo uuid de algum jogador cadastrado.
	
	No exemplo acima, chamando http://localhost:8080/jogador/consultar/0ebc1256-c419-4c85-bd2c-40a26a6367bc, os dados do jogador "Teste 3" serão retornados no formato
	json, mas ele continua existindo, basta chamar a url que lista os jogadores do item 6.
	
9 - Para adicionar um jogador, utilizar a url http://localhost:8080/jogador/consultar/adicionar (requisição http tipo POST)
	e colocar no corpo da mensagem o nome do jogador. Para testar isso, utilizei o programa SoapUI (figura adicionarJogador.png na pasta 		doc):
	
	Será retornado no corpo da requisição http os dados do novo jogador no formato json com um id gerado aleatoriamente. No exemplo:
	
	HTTP/1.1 200 
	Content-Type=application/json
	Transfer-Encoding=chunked
	Date=Sat, 20 Jun 2020 17:09:25 GMT
	Keep-Alive=timeout=60
	Connection=keep-alive
	
	{"nome":"Chuck Norris","id":"3a601ff2-0448-4ff5-bc48-dcf5a82811af"}	

10 - Para realizar uma jogada, utilizar a URL (requisição http tipo GET):

	http://localhost:8080/jogada/adicionar/{id}/{tipodejogada}	
	
	substituindo id pelo id do jogador e tipo de jogada por: TESOURA ou PAPEL ou PEDRA ou LAGARTO ou SPOCK

	No exemplo:
	
		http://localhost:8080/jogada/adicionar/88c0aea0-93d8-4dc8-99b0-da242ba53968/PAPEL
		
	irá adicionar a jogada ao jogador escolhido e retornará uma lista contendo as jogadas atuais do jogo
	no formato json:
	
	[{"jogador":{"nome":"Teste Nome 1","id":"88c0aea0-93d8-4dc8-99b0-da242ba53968"},"tipoDeJogada":{"tipoDeJogadaEnum":"PAPEL"}}]	

		http://localhost:8080/jogada/adicionar/85ae5aca-33d7-4857-bc0f-6e1d6b764157/PEDRA

	irá adicionar outra jogada e retornar a lista atualizada:
	
	[{"jogador":{"nome":"Teste Nome 1","id":"88c0aea0-93d8-4dc8-99b0-da242ba53968"},"tipoDeJogada":{"tipoDeJogadaEnum":"PAPEL"}},{"jogador":{"nome":"Teste Nome 5","id":"85ae5aca-33d7-4857-bc0f-6e1d6b764157"},"tipoDeJogada":{"tipoDeJogadaEnum":"PEDRA"}}]	
	
	
11 - Após cadastrar usuários e jogadas, para verificar o jogador vencedor, utilizar a seguinte URL:

	http://localhost:8080/jogada/verificarvencedor

	ela irá retornar o jogador vencedor no formato JSON. No exemplo acima:
	
	{"jogador":{"nome":"Teste Nome 5","id":"85ae5aca-33d7-4857-bc0f-6e1d6b764157"},"tipoDeJogada":{"tipoDeJogadaEnum":"PAPEL"}}	
	

Item: Observações 
===================================

1 - Como não deveria utilizar banco de dados, utilizei um mapa que fica em memória e é sincronizado
para acessos simultâneos.Esta implementação pode ser troccada por outra que implemente a interface
GerenciadorJogadoresInterface;

2 - As jogadas também foram sincronizadas porém utilizando um semáforo;

3 - O javadoc (documentação da api) está na pasta doc, arquivo index.html;

4 - os testes da api e de algumas jogadas estão no junit, não coloquei a implementação 
    das combinações de jogadas por falta de tempo;
   