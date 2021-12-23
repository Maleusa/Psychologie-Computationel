package psycomp;

import java.util.Arrays;

/**
 * Classe croisement représentant l'intersection de plusieurs rues, un croisement est représenté par une lettre et un nombre
 * @author Y.Cheriti et C.Conrad
 *
 */
public class Croisement {
	/**
	 * nom du croisement
	 */
	String nom;
	/**
	 * Tableau contenant l'ensemble des noms des croisements voisin à this.
	 */
	String[] voisin;
	/**
	 * Tableau contenant les disances de this à ses voisins.
	 * La distance stocké à l'indice i dans distVoisin est la distance séparant this au croisement stocké à l'indice i dans voisin
	 */
	double[] distVoisin;
	/**
	 * Représente la distance à vol d'oiseau entre this et l'arrivée
	 */
	double distObj;
	
	/**
	 * Constructeur permettant d'initialisé l'ensemble des attributs d'un croisement
	 * @param nom
	 * @param voisin
	 * @param distVoisin
	 * @param distObj
	 */
	public Croisement(String nom, String[] voisin, double[] distVoisin, double distObj){
		this.nom = nom;
		this.voisin = voisin;
		this.distVoisin = distVoisin;
		this.distObj = distObj;
	}
	/**
	 * Permet de récupérer le nom de this
	 * @return l'attribut nom de this
	 */
	public String getNom() {
		return nom;
	}
	/*public void setNom(String nom) {
		this.nom = nom;
	}*/
	/**
	 * Permet de récupérer l'ensemble des voisins de this
	 * @return l'attribut voisin de this
	 */
	public String[] getVoisin() {
		return voisin;
	}
	/**
	 * redéfinition de la méthode equals 
	 * @param c le croisement que l'on veut comprarer à this
	 * @return true si c à le même nom de this et false sinon.
	 */
	public boolean equals(Croisement c) {
		return (this.nom.equals(c.nom));
	}
	
	/**
	 * Permet de determiner si c est un voisin de this
	 * @param c
	 * @return true si c est un voisin de this et false sinon
	 */
	public boolean estVoisin(Croisement c) {
		for(int i = 0; i<voisin.length;i++) {
			if(voisin[i].equals(c.getNom()))
				return true;
		}
		return false;
	}
	
	/**
	 * Permet de récupérer la distance séparant this à l'arrivée 
	 * @return l'attribut distObj de this
	 */
	public double getDistObj() {
		return distObj;
	}
	
	/**
	 * Permet de récupérer la distance séparant this à c 
	 * Lève une IllegaleArgumentException si c n'est pas un voisin de this
	 * @param c
	 * @return la distance sous forme de double
	 */
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
	
	/**
	 * redifnition de la méthode toString
	 */
	public String toString() {
		return nom +" "+ Arrays.toString(voisin)+" "+Arrays.toString(distVoisin)+" "+distObj;
	}
}
