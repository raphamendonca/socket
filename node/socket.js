/* Exemplo de socket com nodejs
Exemplo obtido na internet, assim que recuperar o site eu adiciono aqui a referencia
*/

var net = require('net');

var hostname = '127.0.0.1';
var portNumber = 1234;
var comando = 'OLá';

var client = new net.Socket();
client.connect(portNumber, hostname, function() {
	console.log('Connected');
	
});

var i = 0;
client.on('data', function(data) {
	client.write(comando);
	console.log('Received: ' + data);
	
	i++;
	if(i===2){
		console.log('----');
		client.destroy(); 
	}
});

client.on('close', function() {
	console.log('Connection closed');
});
