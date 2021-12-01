package psycomp;

import java.util.Arrays;

/**
 * Classe croisement représentant l'intersection de plusieurs rues, un croisement est représenté par une lettre et un nombre
 * @author Y.Cheriti et C.Conrad
 *
 */
public class Croisement {
	String nom;
	String[] voisin;
	double[] distVoisin;
	double distObj;
	
	public Croisement(String nom, String[] voisin, double[] distVoisin, double distObj){
		this.nom = nom;
		this.voisin = voisin;
		this.distVoisin = distVoisin;
		this.distObj = distObj;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public boolean equals(Croisement c) {
		return (this.nom.equals(c.nom));
	}
	public boolean estVoisin(Croisement c) {
		for(int i = 0; i<voisin.length;i++) {
			if(voisin[i].equals(c.getNom()))
				return true;
		}
		return false;
	}
	
	public double getDistObj() {
		return distObj;
	}
	
	public double distVoisin(Croisement c) {
		double dist = 0;
		if(!this.estVoisin(c))
			throw new IllegalArgumentException();
		for(int i = 0; i<voisin.length;i++) {
			if(c.getNom().equals(voisin[i])){
				dist = distVoisin[i];
			}
		}
		return dist;
	}
	
	
	public String toString() {
		return nom +" "+ Arrays.toString(voisin)+" "+Arrays.toString(distVoisin)+" "+distObj;
	}
}
