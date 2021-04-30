import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Serveur {

static ServerSocket serveurSocket  ;
static Socket clientSocket ;
static ObjectInputStream in;
static ObjectOutputStream out;

public static void EnvoieServeur(ArrayList<Case> maps) {	
	 
			try {
				out.writeObject(maps);
				out.flush();
				out.close();
				serveurSocket.close();
				clientSocket.close();		  
			  }
			  catch(IOException e) {
				  e.printStackTrace();
			  }
			  close();
			  
			  System.out.println("Serveur ferme");
	 }
	 

public static void close(){
 
 }   

public static void ReceptionServeur(ArrayList<Case> maps) {
	try {
		  
		 
		  maps=(ArrayList<Case>) in.readObject();
			
		  in.close();
		  serveurSocket.close();
		clientSocket.close();
		
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
	  close();
	  
	  System.out.println("Serveur ferme");

}	  

public Serveur(){
	
	try {
	System.out.println("Serveur ouvert");
	  serveurSocket=new ServerSocket(4232);
	  serveurSocket.setSoTimeout(100000);
	  clientSocket=serveurSocket.accept();	
	  out = new ObjectOutputStream(clientSocket.getOutputStream());
	  in = new ObjectInputStream(clientSocket.getInputStream());

}catch (IOException e) {
    e.printStackTrace();
 }

 


}
}
