import java.io.Serializable;

public class Objet implements Serializable {

private String nomPotion;
private String Type;

public Objet(String nomPotion,String Type){
this.nomPotion=nomPotion;
this.Type=Type;
}


public void heal(Personnage p) {
	Integer vie = p.getVie();
	if(vie<75){
	p.AjoutStat(0, 0, 0, 25);
	}
	else {
	p.AjoutStat(0, 0, 0, 100-vie);
	}
	System.out.println("Vous avez utilise une potion de soin vos points de vie sont a "+p.getVie());
}	

public void potionForce(Personnage p) {
	p.AjoutStat(3, 0, 0, 0);
	
	System.out.println("Vous avez utilise une potion Force+ vos points de force sont a "+p.getForce());
}	
public void potionResistance(Personnage p) {
	p.AjoutStat(0, 0, 3, 0);
	
	System.out.println("Vous avez utilise une potion Resistance+ vos points de resistance sont a "+p.getResistance());
}	

public void potionAdresse(Personnage p) {
	p.AjoutStat(0, 3, 0, 0);
	
	System.out.println("Vous avez utilise une potion Adresse+ vos points d'adresse sont a "+p.getAdresse());
}	

public String getnomObjet() {
	return nomPotion;
}

public String getType() {
	return Type;
}
}