

public class PNG {

static Personnage Enfant=new Personnage("Enfant perdu","monstre", 3, 3, 3, 10);
static Personnage Vieux=new Personnage("Ancetre","monstre",6, 2, 4, 15);
static Personnage DJRaoult=new Personnage("DJ Raoult","monstre",11,50,30,100);
static Personnage Zombie=new Personnage("Zombie","monstre", 6, 4, 10, 30);
static Personnage Singe=new Personnage("Singe infecte","monstre", 9, 15, 4, 20);
static Personnage Licorne=new Personnage("Licorne qui demande son chemin","monstre", 15, 16, 8, 30);
static Personnage Paysans=new Personnage("Paysans","monstre", 10, 9, 6, 20);
static Personnage Teletubbies=new Personnage("Teletubbies infecte","monstre", 16, 14, 13, 35);
static Personnage Raiponce=new Personnage("Raiponce","monstre", 12, 12, 10, 25);
static Personnage Chloroquine=new Personnage("Chloroquine le BOSS","monstre", 20, 23, 20, 100);


public PNG() {
	Enfant.setNiv(5);
	Vieux.setNiv(10);
	Zombie.setNiv(20);
	Paysans.setNiv(25);
	Singe.setNiv(30);
	Raiponce.setNiv(35);
	Licorne.setNiv(40);
	Teletubbies.setNiv(50);
	DJRaoult.setNiv(60);
	Chloroquine.setNiv(80);
}

}

