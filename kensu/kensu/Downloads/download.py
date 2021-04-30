import requests
import socket
import json



def download(filename):
    hostname = socket.gethostname()
    ip = socket.gethostbyname(hostname)
    url = "http://" + ip + ":8000/"
    
    
    if (filename[len(filename)-1]=="/"):
        return print("incorrect format")

    
    if (filename.find("/")!=-1) :
        url+=filename
        tab=filename.split("/")
        if tab[len(tab)-1]=="":
            tab.remove("")
        filename=tab[len(tab)-1]
    else:
        url+=filename   
    r = requests.get(url)
    fichier = filename
    if r.ok:
        with open (fichier, "w") as code:
           
            code.write(r.text)
            print("download")
       
    else:
        print("file not found ")

download(input("file name ? \n"))