import java.io.Serializable;
import java.util.Random;

public class equipement implements Serializable{
	
	private String nomObjet;
	private int  Attaque;
	private int Defense;
	private int poid;
	private String Type;
	Random alea=new Random();
	
public equipement(String nomObjet,String Type, int Attaque, int Defense,int Poid) {
	this.nomObjet=nomObjet;
	this.Attaque=Attaque;
	this.Defense=Defense;
	this.poid=Poid;
	this.Type=Type;
	}


public int getAttaque() {
	int de= (Attaque/3);
	int Total=0;
	for(int i=0;i<de;i++) {
	Total+=de*alea.nextInt(6)+1;
	}
	return Total+ Attaque%3;
}

public int getDefense() {
	int de= (Defense/3);
	int Total=0;
	for(int i=0;i<de;i++) {
	Total+=de*alea.nextInt(6)+1;
	}
	return Total+ Defense%3;
}

public int getPoid() {
	int de= (poid/3);
	int Total=0;
	for(int i=0;i<de;i++) {
	Total+=de*alea.nextInt(6)+1;
	}
	return Total+ poid%3;
	
}

public String getnomObjet() {
	return nomObjet;
}

public String getType() {
	return Type;
}
}
