import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;

public class Message {
	public Message(String texte){
		try {
			File file = new File("Message.txt");
			PrintWriter writer = null;
			if ( file.exists() && !file.isDirectory() ) {
			    writer = new PrintWriter(new FileOutputStream(new File("Message.txt"), true));
			}
			else {
			    writer = new PrintWriter("Message.txt");
			}
			writer.println(LocalTime.now().getHour()+":"+LocalTime.now().getMinute()+"\n"+texte);
			writer.close();
			} 
		catch (IOException e) {
			System.err.println(e);
			}
	}

}
