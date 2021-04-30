import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
/*
 * www.codeurjava.com
 */
public class Client {

   public static void main(String[] args) {
	   Maps maps=new Maps();
	   
      final Socket clientSocket;
      final ObjectInputStream in;
      final ObjectOutputStream out;
      final Scanner sc = new Scanner(System.in);//pour lire à partir du clavier
     
      try {
         
         clientSocket = new Socket("127.0.0.1",5000);
   
         //flux pour envoyer
         out = new ObjectOutputStream(clientSocket.getOutputStream());
         in = new ObjectInputStream(clientSocket.getInputStream());
   
         Thread envoyer = new Thread(new Runnable() {
       
              @Override
              public void run() {
              
                	
               	try {
					out.writeObject(maps.Maps);
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
         
   
        Thread recevoir = new Thread(new Runnable() {
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
        recevoir.start();
       
      } catch (IOException e) {
           e.printStackTrace();
      }
  }
}