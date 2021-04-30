import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Joueur1 {
	static ServerSocket serveurSocket  ;
	static Socket clientSocket ;
	static ObjectInputStream in;
	static ObjectOutputStream out;
	
public static void  ReceptionJoueur1(ArrayList<Case> maps) {	
	try {
		  
		 
		  maps=(ArrayList<Case>) in.readObject();
			
		  
		
	  }
	 catch (Exception e)
    {
        e.printStackTrace(); }
	try {
		
		FileOutputStream fos = new FileOutputStream("nouvelleMaps.txt");
		 ObjectOutputStream oos = new ObjectOutputStream(fos);
         oos.writeObject(maps);
         oos.close();
         fos.close();
		
	
	} catch (IOException e) {
		System.err.println(e);
	}
	
}
public static void EnvoieJoueur1(ArrayList<Case> maps) {

try {
  
	  out.writeObject(maps);
	  out.flush();
	 
}
catch(IOException e) {
	  e.printStackTrace();
}
}
	
	public Joueur1() {
		try {
			clientSocket=new Socket(InetAddress.getLocalHost().getHostAddress(),4240);	
			in = new ObjectInputStream(clientSocket.getInputStream());
			out = new ObjectOutputStream(clientSocket.getOutputStream());
		  
		}
		  catch(IOException e) {
			  e.printStackTrace();
		  }
	}
		
public static void main(String[] args) {		
		new Joueur1();
		Maps maps=new Maps();
		
		ReceptionJoueur1(maps.Maps);
		maps.reprendre();

		maps.AfficherMaps();
		maps.UserChoix(Joueur.joueur1);
		new Joueur1();
		EnvoieJoueur1(maps.Maps);
		

		
	}
}

