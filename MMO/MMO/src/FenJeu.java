
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JLabel;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
public class FenJeu extends JFrame {

	private JPanel contentPane;
	private JTextField txtChoix;
	private int choix;
	private JButton btnValider;
	private static final int VALIDER = 0;
	private JTextField textField;
	private JTextField txtForce;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		new FenJeu();
	}
	*/
	public int getChoix() {
		return choix; 
	}
	
	public void setChoix(int choix) {
		 this.choix = choix;
	}
	

	/**
	 * Create the frame.
	 */
	public FenJeu() {
		super("RPG game");
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 894, 671);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelInfo = new JPanel();
		panelInfo.setBounds(592, 0, 286, 632);
		contentPane.add(panelInfo);
		panelInfo.setLayout(null);
		
		JLabel info = new JLabel("Information");
		info .setBackground(Color.LIGHT_GRAY);
		info .setBounds(10, 11, 256, 547);
		info.setBorder(BorderFactory.createEtchedBorder());
		panelInfo.add(info);
		panelInfo.setBorder(BorderFactory.createEtchedBorder());
		
		txtChoix = new JTextField();
		txtChoix.setBounds(25, 586, 117, 20);
		panelInfo.add(txtChoix);
		txtChoix.setColumns(10);
		
		btnValider = new JButton("Valider");
		btnValider.setBounds(158, 585, 89, 23);
		panelInfo.add(btnValider);
		
		JPanel panelMaps = new JPanel();
		panelMaps.setBounds(247, 0, 346, 632);
		contentPane.add(panelMaps);
		panelMaps.setLayout(null);
		
		JLabel maps = new JLabel(lireFichier("game.txt"));
		maps.setBounds(74, 0, 262, 600);
		panelMaps.add(maps);
		
		panelMaps.setBorder(BorderFactory.createEtchedBorder());
		
		JPanel panelState = new JPanel();
		panelState.setBounds(10, 0, 237, 632);
		contentPane.add(panelState);
		panelState.setLayout(null);
		
		JLabel Inventaire = new JLabel("Inventaire:");
		Inventaire.setFont(new Font("Tahoma", Font.BOLD, 12));
		Inventaire.setBounds(10, 454, 217, 27);
		panelState.add(Inventaire);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(10, 479, 217, 121);
		panelState.add(textField);
		textField.setColumns(10);
		
		JLabel lblPointAction = new JLabel("Point d'action:");
		lblPointAction.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPointAction.setBounds(10, 11, 217, 27);
		panelState.add(lblPointAction);
		
		JLabel lblAttaque = new JLabel("Attaque:");
		lblAttaque.setBounds(7, 88, 105, 27);
		panelState.add(lblAttaque);
		
		JLabel lblDefense = new JLabel("D\u00E9fense:");
		lblDefense.setBounds(122, 88, 105, 27);
		panelState.add(lblDefense);
		
		JLabel lblEsquive = new JLabel("Esquive:");
		lblEsquive.setBounds(7, 126, 105, 27);
		panelState.add(lblEsquive);
		
		JLabel lblForce = new JLabel("Force:");
		lblForce.setBounds(10, 202, 79, 27);
		panelState.add(lblForce);
		
		JLabel lblAdresse = new JLabel("Adresse:");
		lblAdresse.setBounds(10, 253, 79, 27);
		panelState.add(lblAdresse);
		
		JLabel lblResistance = new JLabel("R\u00E9sistance:");
		lblResistance.setBounds(10, 300, 79, 27);
		panelState.add(lblResistance);
		
		txtForce = new JTextField();
		txtForce.setEditable(false);
		txtForce.setBounds(109, 205, 86, 20);
		panelState.add(txtForce);
		txtForce.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(109, 256, 86, 20);
		panelState.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBounds(109, 303, 86, 20);
		panelState.add(textField_2);
		textField_2.setColumns(10);
		panelState.setBorder(BorderFactory.createEtchedBorder());
	}
	
	public String lireFichier(String adresse){
    	String phrases = "";
        String ligne;
        try
        {
            BufferedReader br =new BufferedReader(new FileReader(adresse));
            while((ligne = br.readLine()) != null) {
            	ligne = ligne.trim().replaceAll(" ","_/").replaceAll("#","#/").replaceAll("p","p/").replaceAll("a","a/").replaceAll("C","C/").replaceAll("L","L/").replaceAll("1","1/").replaceAll("2","2/");
                phrases += ligne+ "<br/>"; 	
            }
            br.close();
        }
        catch(Exception e){
            System.out.print("Erreur de lecture "+e);
        }
        return "<html>"+phrases+"</html>"; 
        
    }
	
	public void initEcouteurs() {
		this.btnValider.addActionListener(new EcouteurBoutons(VALIDER));
		/*this.btnSauve.addActionListener(new EcouteurBoutons(SAUVEGARDER));
		this.btnCharger.addActionListener(new EcouteurBoutons(CHARGER));
		this.btnQuitter.addActionListener(new EcouteurBoutons(QUITTER));*/
	}
	
	class EcouteurBoutons implements ActionListener {
		private int zone;
 
		public EcouteurBoutons(int z) {
			this.zone = z;
		}	
	
	public void actionPerformed(ActionEvent e) {
			switch (zone) {
				case VALIDER: {	
					setChoix(Integer.parseInt(txtChoix.getText()));
					break;
				}
		}
		}
	}
	
}
