import socket
import subprocess
import struct
import json
import os

share_dir = os.getcwd() + '\Client'
gd_server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
gd_server.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
gd_server.bind(('127.0.0.1', 8000))
gd_server.listen(5)
while True :
    conn, client_addr = gd_server.accept()
    print('##########################################################################')
    print('                        Connection successfull                            ')
    print('                     Waiting for Client\'s request                        ')
    print('##########################################################################')
    while True:
        try:

            res = conn.recv(8096)
            if not res: break
            
 
            cmds = res.decode('utf-8').split()
            filename = cmds[1]


            header_dic = {
                'filename': filename,
                'file_size':os.path.getsize(r'%s\%s'%(share_dir, filename))
            }

            header_json = json.dumps(header_dic)
            header_bytes = header_json.encode('utf-8')


            conn.send(struct.pack('i',len(header_bytes)))


            conn.send(header_bytes)


            with open('%s/%s'%(share_dir, filename),'rb') as f:
                for line in f:
                    conn.send(line)
        except ConnectionResetError:
            break
    
    conn.close()
gd_server.close()
