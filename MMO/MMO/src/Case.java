import java.io.Serializable;

public class Case  implements Serializable{
	

	private Personnage perso;

public Case(Personnage perso) {

	this.perso=perso;

}	
	
public void Ajout(Personnage perso ) {
	this.perso=perso;

}
public void AjoutPerso(Personnage perso) {
	perso.setPosition(Integer.parseInt(this.perso.getPseudo()));
	this.perso=perso;
}
public void retirerPerso() {
	int position = this.perso.getPosition();
	this.perso=new Personnage(""+position,"case vide", 0, 0, 0, 0);
	this.perso.setPosition(position);
}


public Personnage getPerso() {
	return perso;
}


}
