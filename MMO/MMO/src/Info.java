import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class Info {
	public Info(String fichier,String texte){
		try {
			File file = new File(fichier);
			PrintWriter writer = null;
			if ( file.exists() && !file.isDirectory() ) {
			    writer = new PrintWriter(new FileOutputStream(new File(fichier), true));
			}
			else {
			    writer = new PrintWriter(fichier);
			}
			writer.println(texte);
			writer.close();
			} 
		catch (IOException e) {
			System.err.println(e);
			}
	}

}
