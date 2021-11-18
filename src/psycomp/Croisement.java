package psycomp;
/**
 * Classe croisement représentant l'intersection de plusieurs rues, un croisement est représenté par une lettre et un nombre
 * @author Y.Cheriti et C.Conrad
 *
 */
public class Croisement {
	String nom;
	Croisement[] voisin;
	double[] distVoisin;
	double distObj;
	
	public Croisement(String nom, Croisement[] voisin, double[] distVoisin, double distObj){
		this.nom = nom;
		this.voisin = voisin;
		this.distVoisin = distVoisin;
		this.distObj = distObj;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(char[] nom) {
		this.nom = nom;
	}
	public boolean equals(Croisement c) {
		return (this.nom.equals(c.nom));
	}
}
