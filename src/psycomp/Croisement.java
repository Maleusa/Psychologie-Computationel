package psycomp;

import java.util.Arrays;

/**
 * Classe croisement repr�sentant l'intersection de plusieurs rues, un croisement est repr�sent� par une lettre et un nombre
 * @author Y.Cheriti et C.Conrad
 *
 */
public class Croisement {
	/**
	 * nom du croisement
	 */
	String nom;
	/**
	 * Tableau contenant l'ensemble des noms des croisements voisin � this.
	 */
	String[] voisin;
	/**
	 * Tableau contenant les disances de this � ses voisins.
	 * La distance stock� � l'indice i dans distVoisin est la distance s�parant this au croisement stock� � l'indice i dans voisin
	 */
	double[] distVoisin;
	/**
	 * Repr�sente la distance � vol d'oiseau entre this et l'arriv�e
	 */
	double distObj;
	
	/**
	 * Constructeur permettant d'initialis� l'ensemble des attributs d'un croisement
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
	 * Permet de r�cup�rer le nom de this
	 * @return l'attribut nom de this
	 */
	public String getNom() {
		return nom;
	}
	/*public void setNom(String nom) {
		this.nom = nom;
	}*/
	/**
	 * Permet de r�cup�rer l'ensemble des voisins de this
	 * @return l'attribut voisin de this
	 */
	public String[] getVoisin() {
		return voisin;
	}
	/**
	 * red�finition de la m�thode equals 
	 * @param c le croisement que l'on veut comprarer � this
	 * @return true si c � le m�me nom de this et false sinon.
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
	 * Permet de r�cup�rer la distance s�parant this � l'arriv�e 
	 * @return l'attribut distObj de this
	 */
	public double getDistObj() {
		return distObj;
	}
	
	/**
	 * Permet de r�cup�rer la distance s�parant this � c 
	 * L�ve une IllegaleArgumentException si c n'est pas un voisin de this
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
	 * redifnition de la m�thode toString
	 */
	public String toString() {
		return nom +" "+ Arrays.toString(voisin)+" "+Arrays.toString(distVoisin)+" "+distObj;
	}
}
