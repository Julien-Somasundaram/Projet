import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Maps implements Serializable {

ArrayList<Case> Maps=new ArrayList<Case>();


public void Mapsvide() {
	
	for(int i=0;i<440;i++) {
	Maps.add(new Case(new Personnage(""+i, "case vide", 0, 0, 0, 0)));
	Maps.get(i).getPerso().setPosition(i);
	}
	for(int i=0;i<Maps.size();i++) {
		if(i%22==0) {
		Maps.get(i).Ajout(Murs.murs);
		}
		if((i+1)%22==0) {
			Maps.get(i).Ajout(Murs.murs);
			}
		if(i<22) {
			Maps.get(i).Ajout(Murs.murs);
			}
		if(i>Maps.size()-22) {
			Maps.get(i).Ajout(Murs.murs);
			}
	}
	
}
public void ajoutmur(ArrayList<Integer> tab2) {
	for(int i =0;i<tab2.size();i++) {
		Maps.get(tab2.get(i)).Ajout(Murs.murs);
	}
}


public void deplacement(Personnage p, Case c) {
	int position = p.getPosition();
	c.AjoutPerso(p);
	Maps.set(position,(new Case(new Personnage(""+position, "case vide", 0, 0, 0, 0))));
}

public void AfficherMaps() {
	ArrayList<String> tab = new ArrayList<String>(); 
	String maps ="";
	for(int i1=0;i1<(int)Maps.size();i1++) {
		if(Maps.get(i1).getPerso().getType().contentEquals("case vide")) {
			tab.add(" ");
		}
		else if(Maps.get(i1).getPerso().getType().contentEquals("murs")) {
			
		
		tab.add("#");		
		}
		else if(Maps.get(i1).getPerso().getType().contentEquals("monstre")) {
		tab.add(Maps.get(i1).getPerso().getPseudo().substring(0, 1).toLowerCase());
		}
		else if(Maps.get(i1).getPerso().getType().contentEquals("joueur1")||Maps.get(i1).getPerso().getType().contentEquals("joueur2")) {
		tab.add(Maps.get(i1).getPerso().getType().substring(6, 7));
		}		
	}
	for(int i=0;i<(int)tab.size();i++) {
		if(i%22==0) {
			maps+=("\n");
		}
		maps+=(tab.get(i));
	}
	maps+=("\n");
	System.out.println( maps);
	}


/*
public void MapsversFichier() {
	try {

	PrintWriter printWriter = new PrintWriter ("game.txt");
	printWriter.println (AfficherMaps());
    printWriter.close (); 
	} 
	catch (IOException e) {
		System.err.println(e);
	}
}
*/



public void sauver() {
	try {
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File("Maps")));
		for (int i = 0; i <Maps.size(); i++) {	
        bw.write(Maps.get(i).getPerso().getPseudo());
        bw.write("/");
        bw.write(Maps.get(i).getPerso().getType());
        bw.write("/");
        bw.write(""+Maps.get(i).getPerso().getForce());
        bw.write("/");
        bw.write(""+Maps.get(i).getPerso().getAdresse());
        bw.write("/");
        bw.write(""+Maps.get(i).getPerso().getResistance());
        bw.write("/");
        bw.write(""+Maps.get(i).getPerso().getVie());
        bw.write("\r\n");
		}
		bw.close();
	} catch (IOException e) {
		System.err.println(e);
	}
}
public void charger() {
	try {
		Maps.clear();
		BufferedReader br = Files.newBufferedReader(Paths.get("Maps"));
		String ligne;
				ligne = br.readLine();
		while (ligne != null) {
			String[] Liste;
			Liste=(ligne.split("/"));
			Maps.add(new Case(new Personnage(Liste[0], Liste[1],Integer.parseInt(Liste[2]),Integer.parseInt(Liste[3]),Integer.parseInt(Liste[4]),Integer.parseInt(Liste[5]))));
			ligne = br.readLine();
		}

		br.close();
	} catch (IOException e) {
		System.err.println(e);
	}
}
public void sauverMaps() {
	try {
	
		FileOutputStream fos = new FileOutputStream("Maps.txt");
		 ObjectOutputStream oos = new ObjectOutputStream(fos);
         oos.writeObject(Maps);
         oos.close();
         fos.close();
		
	
	} catch (IOException e) {
		System.err.println(e);
	}
}

@SuppressWarnings("unchecked")
public void chargerMaps() {
	Maps.clear();
	try
    {
		
		 FileInputStream fis = new FileInputStream("Maps.txt");
	      ObjectInputStream ois = new ObjectInputStream(fis);
		 Maps=(ArrayList<Case>) ois.readObject();
		
		 ois.close();
  
    }
    catch (Exception e)
    {
        e.printStackTrace(); }
	Joueur.joueur1=Maps.get(CherchePerso(Joueur.joueur1)).getPerso();
  	Joueur.joueur2=Maps.get(CherchePerso(Joueur.joueur2)).getPerso();    
    
}
public void reprendre() {

Maps.clear();
try
{
	
	 FileInputStream fis = new FileInputStream("nouvelleMaps.txt");
      ObjectInputStream ois = new ObjectInputStream(fis);
	 Maps=(ArrayList<Case>) ois.readObject();
	
	 ois.close();

}
catch (Exception e)
{
    e.printStackTrace(); }
Joueur.joueur1=Maps.get(CherchePerso(Joueur.joueur1)).getPerso();
	Joueur.joueur2=Maps.get(CherchePerso(Joueur.joueur2)).getPerso();
}
public void newMaps() {
	try
    {
		Maps.clear();
		 FileInputStream fis = new FileInputStream("newMaps.txt");
	      ObjectInputStream ois = new ObjectInputStream(fis);
		 Maps=(ArrayList<Case>) ois.readObject();
		
		 ois.close();
  
    }
    catch (Exception e)
    {
        e.printStackTrace(); }
	Joueur.joueur1=Maps.get(CherchePerso(Joueur.joueur1)).getPerso();
   	Joueur.joueur2=Maps.get(CherchePerso(Joueur.joueur2)).getPerso();
   	System.out.println("Pour le joueur 1\n******************************************");
   	Joueur.joueur1.ChangeName();
   	InitStats(Joueur.joueur1);
   	System.out.println("\n******************************************\nPour le joueur 2\n\n******************************************");
   	Joueur.joueur2.ChangeName();
   	InitStats(Joueur.joueur2);
}

public ArrayList<String> getCaseDispo(Case Case){
	ArrayList<String> CaseDispo=new ArrayList<String>();
	if((Maps.get(Case.getPerso().getPosition()+1).getPerso().getType()).contentEquals("case vide")){
		CaseDispo.add("Droite");
	
	}
	if((Maps.get(Case.getPerso().getPosition()-1).getPerso().getType()).contentEquals("case vide")){
		CaseDispo.add("Gauche");

	}
	if((Maps.get(Case.getPerso().getPosition()-22).getPerso().getType()).contentEquals("case vide")){
		CaseDispo.add("Haut");
	}
	if((Maps.get(Case.getPerso().getPosition()+22).getPerso().getType()).contentEquals("case vide")){
		CaseDispo.add("Bas");
	}
	return CaseDispo;
	}

public void Attaque( Personnage p1 ,equipement Emplacement,Case c2) {
	if (Maps.get(p1.getPosition()).getPerso().Agilite()>c2.getPerso().Agilite()) {
		int att =Maps.get(p1.getPosition()).getPerso().getAttaque(Emplacement);
		int deff = c2.getPerso().getDefense();
		if(att>deff) {
			int degat = (att-deff);
			if(degat>c2.getPerso().getVie()) {
				degat=c2.getPerso().getVie();
			}
			c2.getPerso().AjoutStat(0, 0, 0, -degat);
			if(Emplacement.getnomObjet().contentEquals("rien")) {
				System.out.println("Attaque reussie "+c2.getPerso().getPseudo()+" a perdu "+degat+ " point de vie,il lui reste "+c2.getPerso().getVie()+" point de vie." );

			}
			else {
			System.out.println("Attaque avec " +Emplacement.getnomObjet()+" reussie "+c2.getPerso().getPseudo()+" a perdu "+degat+ " point de vie,il lui reste "+c2.getPerso().getVie()+" point de vie." );
			}
			if(c2.getPerso().getVie()==0) {
				System.out.println(c2.getPerso().getPseudo()+" est mort");
				p1.drop(c2.getPerso());
				p1.GainExp(c2.getPerso().getNiv());
				c2.retirerPerso();
				
			}
		}
		else {
			System.out.println("Attaque echoue");
		}
	
	}
	else{
	System.out.println(c2.getPerso().getPseudo()+ " a esquive");
	
		}
	}

public void UserMove(Personnage p) {
	ArrayList<String> Case = getCaseDispo(Maps.get(p.getPosition()));
	System.out.println("Deplacement Disponible :");

	for(int i=0;i<Case.size();i++) {
		System.out.println(i+1+" "+Case.get(i));
	}
	int Choix = -1;
	while(Choix>Case.size()+1||Choix<0 ){
		System.out.println("Saisir une valeur entre 1 et "+(Case.size()) +" :" );
		Choix=Integer.parseInt(new Scanner(System.in).nextLine());
	}
	
		
	if(Case.get(Choix-1).contentEquals("Droite")) {
		Choix=1;
	}
	else if(Case.get(Choix-1).contentEquals("Gauche")) {
		Choix=-1;
	}
	else if(Case.get(Choix-1).contentEquals("Haut")) {
		Choix=-22;
	}
	else if (Case.get(Choix-1).contentEquals("Bas")) {
		Choix=+22;
	}
	
	deplacement(p,Maps.get(p.getPosition()+Choix) );
	p.setPA(p.getPA() - 2);
	p.refreshTemps();
	
}

public ArrayList<String> getCaseMonstre(Case Case){
	ArrayList<String> CaseDispo=new ArrayList<String>();
	if((Maps.get(Case.getPerso().getPosition()+1).getPerso().getType()).contentEquals("monstre")){
		CaseDispo.add("A droite");		
	}
	if((Maps.get(Case.getPerso().getPosition()-1).getPerso().getType()).contentEquals("monstre")){
		CaseDispo.add("A gauche");
	}
	if((Maps.get(Case.getPerso().getPosition()-22).getPerso().getType()).contentEquals("monstre")){
		CaseDispo.add("Devant");
	}
	if((Maps.get(Case.getPerso().getPosition()+22).getPerso().getType()).contentEquals("monstre")){
		CaseDispo.add("Derriere");
	}
	return CaseDispo;
	}

public void UserAttaque(Personnage p) {
	ArrayList<String> Case = getCaseMonstre(Maps.get(p.getPosition()));
	System.out.println("Attaque disponible :");

	for(int i=0;i<Case.size();i++) {
		System.out.println(i+1+" "+Case.get(i));
	}
	
	if(Case.size()==0 ) {
		System.out.println("Pas d'ennemie a porte");
	}
	
	else{
		

	int Choix = -1;
	
	while(Choix>Case.size()+1||Choix<0 ){
		System.out.println("Saisir une valeur entre 1 et "+(Case.size()) +" :" );
		Choix=Integer.parseInt(new Scanner(System.in).nextLine());
	}
	
		
	if(Case.get(Choix-1).contentEquals("A droite")) {
		Choix=1;
	}
	else if(Case.get(Choix-1).contentEquals("A gauche")) {
		Choix=-1;
	}
	else if(Case.get(Choix-1).contentEquals("Devant")) {
		Choix=-22;
	}
	else if (Case.get(Choix-1).contentEquals("Derriere")) {
		Choix=+22;
	}
	
	for(int i=0;i<2;i++) {
	}
	int Choix2 = -1;
	
	System.out.println("Avec quelle main voulez-vous attaquer l'ennemie ?");
	System.out.println("Saisir 1 pour la main droite et 2 pour la main gauche :");
	
	while(Choix2>2+1||Choix2<0 ){
		System.out.println("Saisir une valeur entre 1 et 2 :" );
		Choix2=Integer.parseInt(new Scanner(System.in).nextLine());
	}
	equipement Emplacement = null;
	if (Choix2==1) {
		Emplacement=p.MainDroite;
	}
	else if(Choix2==2) {
		Emplacement=p.MainGauche;
	}
	
	Attaque(p,Emplacement,Maps.get(p.getPosition()+Choix));
	p.setPA(p.getPA() - 3);
	p.refreshTemps();
	}
}

public void UserChoix(Personnage p){
	int Choix=-1;
	while(Choix!=6) {	
	p.refreshPa();
	System.out.println("Vos points d'action : "+p.getPA());
	System.out.println("Tour de "+p.getPseudo()+"\nChoix Disponible :\n\n1 Voir les caracteristiques du personnage\n2 Attaque (3PA)\n3 Deplacement (2PA)\n4 Equipement (1PA)\n5 Potion (Variable)\n6 Finir et garder les PA restants\n7 Ouvrir le menu (met fin au tour)");
	while(Choix>7||Choix<1 ){
		System.out.println("-----------------------------------------------------------");	
		System.out.println("Saisir une valeur entre 1 et 7 :" );
		Choix=Integer.parseInt(new Scanner(System.in).nextLine());
	}
	
	if(Choix==1) {
		
		p.VoirStats();
		AfficherMaps();
		Choix=-1;
	}
	else if(Choix==2) {
		if(p.getPA()>=3) {
				UserAttaque(p);
				AfficherMaps();
		
		}
		else {
			System.out.println("Vous n'avez pas assez de point d'action");	
		}
		Choix=-1;
	}
	else if(Choix==3) {
		if(p.getPA()>=2) {
			UserMove(p);
			AfficherMaps();
		}
		else {
			System.out.println("Vous n'avez pas assez de point d'action");	
		}
		Choix=-1;
	}
	else if(Choix==4) {
		p.Equipement();
		AfficherMaps();
		Choix=-1;
	}
	else if(Choix==5) {
		potion(p);
		AfficherMaps();
		Choix=-1;
		
	}
	else if(Choix==7) {
		UserMenu();
		
		Choix=-1;
		break;
		
	}
	}
	
}

public void UserMenu() {
	int Choix=-1;
	while(Choix!=4) {	
	System.out.println("\nChoix Disponible :\n\n1 Nouvelle partie\n2 Charger la derniere partie\n3 Sauvegarder la partie actuelle\n4 Retour\n5 Quitter le super jeu");
	while(Choix>5||Choix<1 ){
		System.out.println("-----------------------------------------------------------");	
		System.out.println("Saisir une valeur entre 1 et 4 :" );
		Choix=Integer.parseInt(new Scanner(System.in).nextLine());
	}
	if(Choix==1) {
		newMaps();
		Choix=4;

		}
	else if(Choix==2) {
		chargerMaps();
		Choix=4;
	
		}
	else if(Choix==3) {
		sauverMaps();
		System.out.println("Votre partie a ete sauvegarder");
		
		Choix=4;
		}
	else if(Choix==5) {
		System.exit(0);	

		}
	
	}
	
	
}




public ArrayList<String> getCaseJoueur(Case Case){
	ArrayList<String> CaseDispo=new ArrayList<String>();
	if((Maps.get(Case.getPerso().getPosition()+1).getPerso().getType()).contentEquals("joueur1")||(Maps.get(Case.getPerso().getPosition()+1).getPerso().getType()).contentEquals("joueur2")){
		CaseDispo.add("A droite");		
	}
	if((Maps.get(Case.getPerso().getPosition()-1).getPerso().getType()).contentEquals("joueur1")||(Maps.get(Case.getPerso().getPosition()-1).getPerso().getType()).contentEquals("joueur2")){
		CaseDispo.add("A gauche");
	}
	if((Maps.get(Case.getPerso().getPosition()-22).getPerso().getType()).contentEquals("joueur1")||(Maps.get(Case.getPerso().getPosition()-22).getPerso().getType()).contentEquals("joueur2")){
		CaseDispo.add("Devant");
	}
	if((Maps.get(Case.getPerso().getPosition()+22).getPerso().getType()).contentEquals("joueur1")||(Maps.get(Case.getPerso().getPosition()+22).getPerso().getType()).contentEquals("joueur2")){
		CaseDispo.add("Derriere");
	}
	return CaseDispo;
	}
public void MonstreAttaque(Personnage p) {
	ArrayList<String> Case = getCaseJoueur(Maps.get(p.getPosition()));
	if(Case.size()!=0) {
		int Choix = 0;
		if(Case.get(0).contentEquals("A droite")) {
			Choix=1;
		}
		else if(Case.get(0).contentEquals("A gauche")) {
			Choix=-1;
		}
		else if(Case.get(0).contentEquals("Devant")) {
			Choix=-22;
		}
		else if (Case.get(0).contentEquals("Derriere")) {
			Choix=+22;
		}
		System.out.println("Attaque de "+p.getPseudo());
		Attaque(p, p.MainDroite, Maps.get(p.getPosition()+Choix));
		
		
	}
}

public void AllMonstreAttaque() {
	for(int i=0;i<Maps.size();i++) {
		if(Maps.get(i).getPerso().getType().contentEquals("monstre")) {
			MonstreAttaque(Maps.get(i).getPerso());
		}
	}
}
public void explosion(Personnage p){
	ArrayList<String> Case = getCaseMonstre(Maps.get(p.getPosition()));
	System.out.println("Cible disponible :");

	for(int i=0;i<Case.size();i++) {
		System.out.println(i+1+" "+Case.get(i));
	}
	
	if(Case.size()==0 ) {
		System.out.println("Pas d'ennemie a porte");
	}
	
	else{
		int Choix = -1;
		
		while(Choix>Case.size()+1||Choix<0 ){
			System.out.println("Saisir une valeur entre 1 et "+(Case.size()) +" :" );
			Choix=Integer.parseInt(new Scanner(System.in).nextLine());
		}
		
			
		if(Case.get(Choix-1).contentEquals("A droite")) {
			Choix=1;
		}
		else if(Case.get(Choix-1).contentEquals("A gauche")) {
			Choix=-1;
		}
		else if(Case.get(Choix-1).contentEquals("Devant")) {
			Choix=-22;
		}
		else if (Case.get(Choix-1).contentEquals("Derriere")) {
			Choix=+22;
		}
		Integer vie = Maps.get(p.getPosition()+Choix).getPerso().getVie();
		if(vie>20){
			Maps.get(p.getPosition()+Choix).getPerso().AjoutStat(0, 0, 0,-20);
			System.out.println(Maps.get(p.getPosition()+Choix).getPerso().getPseudo()+" a perdu 20 point de vie il lui reste "+Maps.get(p.getPosition()+Choix).getPerso().getVie()+" point de vie");
		}
		else {
			Maps.get(p.getPosition()+Choix).getPerso().AjoutStat(0, 0, 0, -vie);
			System.out.println(Maps.get(p.getPosition()+Choix).getPerso().getPseudo()+" est mort suite a l'explosion");
			p.drop(Maps.get(p.getPosition()+Choix).getPerso());
			p.GainExp(Maps.get(p.getPosition()+Choix).getPerso().getNiv());
			Maps.get(p.getPosition()+Choix).retirerPerso();
		}
		
		
	}
	
	
}

public void Corona(Personnage p){
	ArrayList<String> Case = getCaseMonstre(Maps.get(p.getPosition()));
	System.out.println("Cible disponible :");

	for(int i=0;i<Case.size();i++) {
		System.out.println(i+1+" "+Case.get(i));
	}
	
	if(Case.size()==0 ) {
		System.out.println("Pas d'ennemie a porte");
	}
	
	else{
		int Choix = -1;
		
		while(Choix>Case.size()+1||Choix<0 ){
			System.out.println("Saisir une valeur entre 1 et "+(Case.size()) +" :" );
			Choix=Integer.parseInt(new Scanner(System.in).nextLine());
		}
		
			
		if(Case.get(Choix-1).contentEquals("A droite")) {
			Choix=1;
		}
		else if(Case.get(Choix-1).contentEquals("A gauche")) {
			Choix=-1;
		}
		else if(Case.get(Choix-1).contentEquals("Devant")) {
			Choix=-22;
		}
		else if (Case.get(Choix-1).contentEquals("Derriere")) {
			Choix=+22;
		}
			Maps.get(p.getPosition()+Choix).getPerso().AjoutStat(0, 0, -Maps.get(p.getPosition()+Choix).getPerso().getResistance()*30/100, 0);
			System.out.println("Ayez confiance en nous la defense de l'ennemie a bien ete reduite !");
		
		
	}
	
	
}

public void potion(Personnage p) {
	p.listObjet();
	if(p.ListObjet.size()!=0) {	
		int Choix = -1;
		while(Choix>p.ListObjet.size()||Choix<0 ){
			System.out.println("Entrer une valeur entre 1 et "+p.ListObjet.size()+" pour utiliser la potion , entrez 0 pour quitter");
			Choix=Integer.parseInt(new Scanner(System.in).nextLine());
		}
		if(Choix!=0) {
			
			System.out.println("Entrez 1 pour utiliser la potion,2 pour quitter");
			int Choix2 = -1;
			while(Choix2>2||Choix2<1 ){
				System.out.println("Entrer une valeur entre 1 et 2");
				Choix2=Integer.parseInt(new Scanner(System.in).nextLine());
		}
			if(Choix2==1) {
				if(p.ListObjet.get(Choix-1).getType().contentEquals("soin")) {
					if(p.PA>2) {
					p.ListObjet.get(Choix-1).heal(p);
					p.PA-= 2;
					p.refreshTemps();
					}
					else {
						System.out.println("Vous n'avez pas assez de point d'action");
						}
					
				}		
				else if(p.ListObjet.get(Choix-1).getType().contentEquals("explosion")) {
					if(p.PA>3) {
					explosion(p);
					p.setPA(p.getPA() - 3);
					p.refreshTemps();
					}
					else {
					System.out.println("Vous n'avez pas assez de point d'action");
					}
				}
				else if(p.ListObjet.get(Choix-1).getType().contentEquals("force")) {
					if(p.PA>1) {
					p.ListObjet.get(Choix-1).potionForce(p);
					p.setPA(p.getPA() - 1);
					p.refreshTemps();
					}
					else {
						System.out.println("Vous n'avez pas assez de point d'action");
						}
				}
				else if(p.ListObjet.get(Choix-1).getType().contentEquals("corona")) {
					if(p.PA>3) {
					Corona(p);
					p.setPA(p.getPA() - 3);
					p.refreshTemps();
					}
					else {
						System.out.println("Vous n'avez pas assez de point d'action");
						}
				}
				else if(p.ListObjet.get(Choix-1).getType().contentEquals("resistance")) {
					if(p.PA>1) {
					p.ListObjet.get(Choix-1).potionResistance(p);
					p.setPA(p.getPA() - 1);
					p.refreshTemps();
					}
					else {
						System.out.println("Vous n'avez pas assez de point d'action");
						}
				}
				else if(p.ListObjet.get(Choix-1).getType().contentEquals("adresse")) {
					if(p.PA>1) {
					p.ListObjet.get(Choix-1).potionAdresse(p);
					p.setPA(p.getPA() - 1);
					p.refreshTemps();
					}
					else {
						System.out.println("Vous n'avez pas assez de point d'action");
						}
				}
				p.ListObjet.remove(Choix-1);
	
			}
	}
	}
}
public void InitStats(Personnage p) {

		for(int i=0;i<6;i++) {
			System.out.println("Distribuer vos points" );
			System.out.println("Veuillez choisir la stats a augmenter, les stats augmenteront par 3" );
			System.out.println("1 Force\n2 Adresse\n3 Resistance");
			int Choix = -1;
			while(Choix>4||Choix<0 ){
				System.out.println("Saisir une valeur entre 1 et 3 :" );
				Choix=Integer.parseInt(new Scanner(System.in).nextLine());
			}
			if(Choix==1) {
				p.AjoutStat(3, 0, 0, 0);
				System.out.println("Votre force actuelle est de :"+ p.getForce());
			}
			else if(Choix==2) {
				p.AjoutStat(0, 3, 0, 0);
				System.out.println("Votre adresse actuelle est de :"+ p.getAdresse());
			}
			else if(Choix==3) {
				p.AjoutStat(0, 0, 3, 0);
				System.out.println("Votre resistance actuelle est de :"+ p.getResistance());
			}
			System.out.println("------------------------------------------------");
		}
	
}

public int CherchePerso(Personnage p) {
	int position=0;
	for(int i=0;i<440;i++) {
		if(Maps.get(i).getPerso().getType().contentEquals(p.getType())){
			position=Maps.get(i).getPerso().getPosition();
		}
		}
	return position;

}

public boolean gameover() {
	if(Joueur.joueur1.getVie()==0 && Joueur.joueur2.getVie()==0) {
	return false;
}
	return true;
}

public void CyclePlay() {
	while(gameover()){
		UserChoix(Joueur.joueur1);
		UserChoix(Joueur.joueur2);
		AllMonstreAttaque();
		if(Maps.get(23).getPerso().getType().contentEquals("case vide")) {
			System.out.println("Vous avez reussi a sauver le monde enfin juste a stoper l'infection felicitation");
			System.exit(0);;
		}
	}
	System.out.println("Game Over");
}
}
	






