package psycomp;

public class Rue {
	/**Classe Rue representant une distance entre deux intersections
	 * @author Y.Cheriti et C.Conrad
	 * @
	 */
	Croisement debut,fin;
	
	double longueur;
	double poids;
	
	public Rue(Croisement debut,Croisement fin,double longueur,double poids){
		this.debut=debut;//Croisement de début d'une rue
		this.fin=fin;//Croisement de fin d'une rue
		this.longueur=longueur;//Longueur entre le début et la fin d'une rue
		this.poids=poids;//valence de la rue en question
		
	}

	public Croisement getDebut() {
		return debut;
	}

	public void setDebut(Croisement debut) {
		this.debut = debut;
	}

	public Croisement getFin() {
		return fin;
	}

	public void setFin(Croisement fin) {
		this.fin = fin;
	}

	public double getLongueur() {
		return longueur;
	}

	public void setLongueur(double longueur) {
		this.longueur = longueur;
	}

	public double getPoids() {
		return poids;
	}

	public void setPoids(double poids) {
		this.poids = poids;
	}
	
	/**
	 * Verifie si la rue b et this sont connectées entre elles
	 * @param b
	 */
	public boolean connecte(Rue b) {
		if(b.debut==this.debut || b.debut==this.fin || b.fin==this.debut || b.fin==this.fin)
		return true;
		else return false;
		
	}
}
