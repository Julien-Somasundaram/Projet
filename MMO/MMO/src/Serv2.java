import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;



public class Serv2 {
 
   public static void main(String[] test) {
	   Maps maps=new Maps();
	   maps.chargerMaps();
  
     final ServerSocket serveurSocket  ;
     final Socket clientSocket ;
     final ObjectInputStream in;
     final ObjectOutputStream out;
     try {
       serveurSocket = new ServerSocket(5000);
       clientSocket = serveurSocket.accept();
       out = new ObjectOutputStream(clientSocket.getOutputStream());
       in = new ObjectInputStream(clientSocket.getInputStream());
       Thread envoi= new Thread(new Runnable() {
       
          @Override
          public void run() {
        	  try {
				out.writeObject(maps);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  try {
				out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
         
             
          }
       });
       System.out.println("ok");
       envoi.start();
   
       Thread recevoir= new Thread(new Runnable() {
    	   ArrayList<Case> maps2=new ArrayList<Case>();
          @Override
          public void run() {
        	  try {
				maps2=(ArrayList<Case>) in.readObject();
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
         }
      });
     // recevoir.start();
      }catch (IOException e) {
         e.printStackTrace();
      }
     
   }
}