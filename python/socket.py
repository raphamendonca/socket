import socket

sok = socket.socket()

hostname = 'nomedoservidor'
port = 1234

sok.connect((hostname,port))
print( sok.recv(1024))
#comando = ""

sok.close()
