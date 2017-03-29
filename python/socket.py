import socket

s = socket.socket()

hostname = 'nomedoservidor'

port = 1234

s.connect((hostname,port))

print( s.recv(1024))

command = ""

s.close()
