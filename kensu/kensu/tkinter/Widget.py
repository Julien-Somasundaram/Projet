
# import lib
from tkinter import *
import tkinter as tk 
from tkinter import ttk 
import time

def _quit():
    """quitter"""
    window.quit() 
    window.destroy() 

# def de qui change le contenu du label en haut
statu = False
def cliquer():
    global statu
    if statu == False:
        statu = True
        var.set(time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()))
    else:
        statu = False
        var.set(time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()))
        
# creation fenetre
window = tk.Tk()
 
# nomer fenetre
window.title('Infomation Bar')
 
# taille fenetre
window.geometry('500x300')  

# change var en String
var = tk.StringVar() 
# title dans fenetre
lab = tk.Label(window, textvariable=var, bg='white', font=('Arial', 12), width=30, height=1)
 
# placer au millieu
lab.pack(fill=X) 

# creation systeme onglet
nb=ttk.Notebook(window, height = "200" , width = "500")

modif, perf, avanc, autre = ttk.Frame(nb),ttk.Frame(nb),ttk.Frame(nb),ttk.Frame(nb)

modif.pack()
nb.add(modif, text="Modification")
nb.pack(fill=X,padx=5)
bouton = tk.Button(modif, text='Refresh', font=('Arial', 12), width=10, height=1, command=cliquer)
bouton.pack(side=tk.BOTTOM)

perf.pack()
nb.add(perf, text="Performance")
canvas= Canvas(perf,height=window.winfo_height(), width= window.winfo_width())
nb.pack(fill=X,padx=5)
bouton = tk.Button(perf, text='Refresh', font=('Arial', 12), width=10, height=1, command=cliquer)
bouton.pack(side=tk.BOTTOM)
#canvas.create_window(ANCHOR='center',height=window.winfo_height(), width=window.winfo_width())

avanc.pack()
nb.add(avanc, text="Avancement du proget")
nb.pack(fill=X,padx=5)
bouton = tk.Button(avanc, text='Refresh', font=('Arial', 12), width=10, height=1, command=cliquer)
bouton.pack(side=tk.BOTTOM)

autre.pack()
nb.add(autre, text="Autre")
nb.pack(fill=X,padx=5)
bouton = tk.Button(autre, text='Refresh', font=('Arial', 12), width=10, height=1, command=cliquer)
bouton.pack(side=tk.BOTTOM)


"""
#cree les onglets et ajouter dans Notebook
for fm in ['Modification', 'Performance','Avancement du proget','autre']:
    ong = tk.Frame(nb)
    label1=tk.Label(ong, text = "Alex ton micro lag")
    label1.pack()
    nb.add(ong,text = fm)
    nb.pack() # centrer
"""


    

bouton = tk.Button(master=window, text='Quitter', font=('Arial', 12), width=10, height=1, command=(_quit))
bouton.pack(side=tk.BOTTOM)
# rafraichir a chaque click
window.mainloop()
# sans mainloop la fenetre est en mode freeze