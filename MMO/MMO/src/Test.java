import java.awt.List;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Test {
	
 
    public static void main(String[] args) {
    	Maps maps= new Maps();
    	
    	
    	maps.chargerMaps();
       	while(true) {
       		
       		
       	maps.UserChoix(Joueur.joueur1);	
    	new Serveur();
       	Serveur.EnvoieServeur(maps.Maps);
       	new Serveur();
       	Serveur.ReceptionServeur(maps.Maps);
       	
       	maps.reprendre();
       	maps.AllMonstreAttaque();
       	


       	}	
       	
    	
    }
   }
    
    


