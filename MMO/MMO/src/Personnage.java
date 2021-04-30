
import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Personnage implements Serializable {
	Random alea=new Random();
	private String Pseudo;
	private String Type;
	private int force;
	private int adresse;
	private int resistance;
	private int vie;
	equipement MainDroite=new equipement("rien", "arme", 0, 0, 0);
	equipement MainGauche=new equipement("rien", "arme", 0, 0, 0);
	equipement Armure=new equipement("rien", "armure", 0, 0, 0);
	ArrayList<equipement> Listequipement= new ArrayList<equipement>();
	ArrayList<Objet> ListObjet= new ArrayList<Objet>();
	private int position;
	int exp=100;
	int niv=exp/100;
	int PA=12;

	LocalTime temps=null;

	
public Personnage(String Pseudo,String Type,int force,int adresse,int resistance,int vie){
	this.Pseudo=Pseudo;
	this.Type=Type;
	this.force=force;
	this.adresse=adresse;
	this.resistance=resistance;
	this.vie=vie;
	
}
public Personnage(int position) {
this.setPosition(position);	
}

public Personnage() {
	this.exp=0;
	this.niv=1;
	this.PA=12;
	
}
public String getPseudo() {
 return this.Pseudo;
}
public int getForce() {
	 return this.force;
}
public int getAdresse() {
	 return this.adresse;
}
public int getResistance() {
	 return this.resistance;
}
public int getVie() {
	return this.vie;
}
public void AjoutStat(int force, int adresse, int resistance, int vie) {
	this.force+=force;
	this.adresse+=adresse;
	this.resistance+=resistance;
	this.vie+=vie;
}
@SuppressWarnings("resource")
public void ChangeName(){
	System.out.print("Saisir un nouveau nom :");
	this.Pseudo=new Scanner(System.in).nextLine();
}


public int getAttaque(equipement Emplacement){
	
	int de= (force/3);
	int Total=0;
	for(int i=0;i<de;i++) {
	Total+=de*alea.nextInt(6)+1;
	}
	return Total+ force%3 + Emplacement.getAttaque();
	
}

public int getDefense(){
	
	int de= (resistance/3);
	int Total=0;
	for(int i=0;i<de;i++) {
	Total+=de*alea.nextInt(6)+1;
	}
	return Total+ resistance%3+Armure.getDefense()+MainDroite.getDefense()+MainGauche.getDefense();
	
}

public int Agilite() {
	int de= (adresse/3);
	int Total=0;
	for(int i=0;i<de;i++) {
	Total+=de*alea.nextInt(6)+1;
	}
	return Total- (adresse%3+Armure.getPoid()+MainDroite.getPoid()+MainGauche.getPoid());
}
public String getType() {
	return Type;
}
public void equipementMD(equipement Arme) {
	this.MainDroite=Arme;
}
public void equipementMG( equipement Arme) {
	this.MainGauche=Arme;
}
public void equipementArmure(equipement Armure) {
	this.Armure=Armure;
}
public void desequipementMD() {
	this.MainDroite=new equipement("rien", "arme", 0, 0, 0);
}
public void desequipementMG() {
	this.MainGauche=new equipement("rien","arme", 0,0, 0);
}
public void desequipementArm() {
	this.Armure=new equipement("rien","arme", 0,0, 0);
}

public void Ajouterequipement(equipement equipement){
	this.Listequipement.add(equipement);
	
}
public void Retirerequipement(equipement equipement){
	this.Listequipement.remove(equipement);
	
}
public void listequipement() {
	if(this.Listequipement.size()==0) {
		System.out.println("Vous n'avez pas d'equipement");
	}
	else {
		for(int i=0;i<this.Listequipement.size();i++) {
			System.out.println(i+1+" "+this.Listequipement.get(i).getnomObjet());
		}
		
	}
}
	
public void AjouterObjet(Objet Objet){
	this.ListObjet.add(Objet);		
}
public void RetirerObjet(Objet Objet){
	this.ListObjet.remove(Objet);		
}



public void listObjet() {
	if(this.ListObjet.size()==0) {
		System.out.println("Vous n'avez pas d'Objet");
	}
	else {
	for(int i=0;i<this.ListObjet.size();i++) {
	System.out.println(i+1+" "+this.ListObjet.get(i).getnomObjet());
	}
	}
}

public void drop(Personnage p){
	ArrayList<equipement> equip = p.Listequipement;
	ArrayList<Objet> objet = p.ListObjet;
	for(int i=0;i<equip.size();i++) {
		this.Listequipement.add(equip.get(i));
		System.out.println(this.getPseudo()+" a obtenu "+equip.get(i).getnomObjet()+" sur le cadavre de "+p.getPseudo());
		p.Retirerequipement(equip.get(i));
		
		}
	for(int i=0;i<objet.size();i++) {
		this.ListObjet.add(objet.get(i));
		System.out.println(this.getPseudo()+" a obtenu "+objet.get(i).getnomObjet()+" sur le cadavre de "+p.getPseudo());
		p.RetirerObjet(objet.get(i));
		
		}	
	}
public void GainExp(int Niv) {
	int Oldniv = niv;
	
	if(niv!=100) {		
	this.exp+=((Niv/this.niv)*50);
	this.niv=this.exp/100;
	}
	if(Oldniv!=this.niv) {
		for(;Oldniv<(this.niv);Oldniv++) {
			System.out.println("Vous etes passe niv "+(Oldniv+1	)+" ,veuillez choisir la stats a augmenter" );
			System.out.println("1 Force\n2 Adresse\n3 Resistance");
			int Choix = -1;
			while(Choix>4||Choix<0 ){
				System.out.println("Saisir une valeur entre 1 et 3 :" );
				Choix=Integer.parseInt(new Scanner(System.in).nextLine());
			}
			if(Choix==1) {
				this.AjoutStat(2, 0, 0, 0);
				System.out.println("Votre force actuelle est de :"+ getForce());
			}
			else if(Choix==2) {
				this.AjoutStat(0, 2, 0, 0);
				System.out.println("Votre adresse actuelle est de :"+ getAdresse());
			}
			else if(Choix==3) {
				this.AjoutStat(0, 0, 2, 0);
				System.out.println("Votre resistance actuelle est de :"+ getResistance());
			}
		}
	}
}


public void VoirStats() {
	System.out.println("Vos carateristique:\n- force "+this.getForce()+"\n- adresse "+this.getAdresse()+"\n- resistance "+this.getResistance()+"\n -vie "+this.getVie()+"\n -lvl "+this.niv);
	System.out.println("-----------------------------------------------------------");
	System.out.println("Vos equipement:\nMain droite :"+this.MainDroite.getnomObjet()+"\nMain gauche :"+this.MainGauche.getnomObjet()+"\nArmure :"+this.Armure.getnomObjet());
}
public void Mort() {
	
}
public void Equipement() {

	this.listequipement();
	if(this.Listequipement.size()!=0) {	
		int Choix = -1;
		while(Choix>this.Listequipement.size()||Choix<0 ){
			System.out.println("Entrer une valeur entre 1 et "+this.Listequipement.size()+" pour equiper un item (1PA) , entrez 0 pour desequiper un item");
			Choix=Integer.parseInt(new Scanner(System.in).nextLine());
		}
		if(Choix!=0) {
			if(this.getPA()>1) {
			System.out.println("Entrez 1 pour equiper,2 pour quitter");
			int Choix2 = -1;
			while(Choix2>2||Choix2<1 ){
				System.out.println("Entrer une valeur entre 1 et 2");
				Choix2=Integer.parseInt(new Scanner(System.in).nextLine());
			}
		if(Choix2==1) {

			if(this.Listequipement.get(Choix-1).getType().contentEquals("armure")) {
					this.equipementArmure(this.Listequipement.get(Choix-1));
					System.out.println("Vous avez equipe "+this.Listequipement.get(Choix-1).getnomObjet());
					this.Retirerequipement(this.Listequipement.get(Choix-1));
					this.PA-=1;
				
				
			}
			else if(this.Listequipement.get(Choix-1).getType().contentEquals("arme")) {
				int Choix3 = -1;
				while(Choix3>2||Choix3<1 ){
					System.out.println("Entrer 1 pour equiper "+this.Listequipement.get(Choix-1).getnomObjet()+" sur votre main droite et 2 pour l'equiper sur votre main gauche");
					Choix3=Integer.parseInt(new Scanner(System.in).nextLine());
				}
				if(Choix3==1) {
						this.equipementMD(this.Listequipement.get(Choix-1));
						System.out.println("Vous avez equipe "+this.Listequipement.get(Choix-1).getnomObjet());
						this.Retirerequipement(this.Listequipement.get(Choix-1));
						
						this.PA-=1;
				}
				else if(Choix3==2) {
					this.equipementMG(this.Listequipement.get(Choix-1));
					System.out.println("Vous avez equipe "+this.Listequipement.get(Choix-1).getnomObjet());
					this.Retirerequipement(this.Listequipement.get(Choix-1));						
					}
			}
			
			
		}
		
			}
			else {
				System.out.println("Vous n'avez pas assez de point d'action");	
			}
		}
		else if(Choix==0) {
			System.out.println("Entrez 1 pour desequiper votre armure,2 pour votre main droite et 3 pour votre main gauche");
			int Choix4 = -1;
			while(Choix4>3||Choix4<1 ){
				System.out.println("Entrer une valeur entre 1 et 3");
				Choix4=Integer.parseInt(new Scanner(System.in).nextLine());
			}	
			if(Choix4==1) {
				if(this.Armure.getnomObjet().contentEquals("rien")) {
					System.out.println("Vous n'avez pas equipe d'armure");
				}
				else {
					this.Ajouterequipement(this.Armure);
					System.out.println("Vous avez enleve "+this.Armure.getnomObjet());
					this.desequipementArm();
				}
			}
			else if(Choix4==2) {
				if(this.MainDroite.getnomObjet().contentEquals("rien")) {
					System.out.println("Vous n'avez pas equipe d'arme");
				}
				else {
					this.Ajouterequipement(this.MainDroite);
					System.out.println("Vous avez enleve "+this.MainDroite.getnomObjet());
					this.desequipementMD();
				}
			}
			else if(Choix4==3) {
				if(this.MainGauche.getnomObjet().contentEquals("rien")) {
					System.out.println("Vous n'avez pas equipe d'arme");
				}
				else {	
					this.Ajouterequipement(this.MainGauche);
					System.out.println("Vous avez enleve "+this.MainGauche.getnomObjet());
					this.desequipementMG();
				}
			
			}
			
			
		}
	}
	}
public void refreshTemps() {
	temps=LocalTime.now();
}
public void refreshPa() {
	if(getPA()<12) {
		if(LocalTime.now().getSecond()-temps.getSecond()>0.1) {
			setPA(getPA() + 1);
			refreshTemps();
			}
		}
	}
public int getPosition() {
	return position;
}
public void setPosition(int position) {
	this.position = position;
}
public int getExp() {
	return exp;
}
public void setExp(int exp) {
	this.exp = exp;
}
public int getNiv() {
	return niv;
}
public void setNiv(int niv) {
	this.niv = niv;
}
public int getPA() {
	return PA;
}
public void setPA(int pA) {
	PA = pA;
}

}

	






