
import java.awt.*;
import javax.swing.*;
import java.io.*;

 
public class Jeu extends JFrame {
 
    public Jeu() {
        super("Julien's Aventure :3");
        this.initComposants();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.centrer(0.4);  
      }
    
    private void initComposants() {
		JPanel panPrincipal = new JPanel();
		this.add(panPrincipal);
		panPrincipal.setLayout(new BorderLayout());
		panPrincipal.add(buildPanelMapsChat(),BorderLayout.CENTER);
		panPrincipal.add(buildPanelInfo(),BorderLayout.EAST);
		
	}
    
    public JPanel buildPanelMapsChat() {
		JPanel pan = new JPanel();
		JPanel pan2 = new JPanel();
		JPanel pan3 = new JPanel();
		JPanel maps = new JPanel();
		JLabel label= new JLabel(lireFichier("game.txt"));
		JLabel info = new JLabel(lireFichier("Message.txt"));
		maps.add(label);
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(info);
		scroll.setPreferredSize(new Dimension(700,100));
		JLabel chat = new JLabel("123");
		JTextField message = new JTextField();
		message.setPreferredSize(new Dimension (700,20));
		JButton envoyer = new JButton("Envoyer");
		pan.setLayout(new BorderLayout());
		pan2.setLayout(new BorderLayout());
		pan2.add(chat,BorderLayout.CENTER);
		pan2.add(pan3,BorderLayout.SOUTH);
		pan3.add(message);
		pan3.add(envoyer);
		pan2.add(scroll,BorderLayout.NORTH);
		pan.add(maps,BorderLayout.NORTH);
		pan.add(pan2,BorderLayout.SOUTH);
		pan.setBorder(BorderFactory.createEtchedBorder());
		pan2.setBorder(BorderFactory.createEtchedBorder());
		pan3.setBorder(BorderFactory.createEtchedBorder());
		return pan;
	}
    
    public JPanel buildPanelInfo() {
    	
		JPanel pan = new JPanel();
		JPanel pan2 = new JPanel();
		JLabel info = new JLabel(lireFichier("info.txt"));
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(info);
		scroll.setPreferredSize(new Dimension(400,930));
		JTextField choix = new JTextField();
		choix.setPreferredSize(new Dimension (100,20));
		JButton valider = new JButton("Valider");
		pan2.add(choix);
		pan2.add(valider);
		pan.setLayout(new BorderLayout());
		pan.add(scroll,BorderLayout.NORTH);	
		pan.add(pan2,BorderLayout.SOUTH);
		pan.setBorder(BorderFactory.createEtchedBorder());
		pan2.setBorder(BorderFactory.createEtchedBorder());
		return pan;
	}
    
    public void centrer(double d) {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		int largeur = (int) (d * dim.width);
		int longueur = (int) (d * dim.height);
		this.setBounds((dim.width - largeur) / 2, (dim.height - longueur) / 2, largeur, longueur);
	}
    
    public static String lireFichier(String adresse){
    	String phrases = "";
        String ligne;
        try
        {
            BufferedReader br =new BufferedReader(new FileReader(adresse));
            while((ligne = br.readLine()) != null) {
            	ligne = ligne.trim().replaceAll(" ","_");
                phrases += ligne+ "<br/>"; 	
            }
            br.close();
        }
        catch(Exception e){
            System.out.print("Erreur de lecture "+e);
        }
        return "<html>"+phrases+"</html>"; 
        
    }
    

    
    public static void main(String[] args) {
    	
		new Jeu();
	}
}