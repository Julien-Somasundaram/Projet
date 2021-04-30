# -*- coding: utf-8 -*-
"""
Created on Sat Jan 23 21:40:56 2021

@author: somas
"""
import socket

hostname = socket.gethostname()
ip = socket.gethostbyname(hostname)
print(ip)