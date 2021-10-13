package psycomp;
/**
 * Classe croisement représentant l'intersection de plusieurs rues, un croisement est représenté par une lettre et un nombre
 * @author Y.Cheriti et C.Conrad
 *
 */
public class Croisement {
	String nom;
	public Croisement(String nom){
		this.nom=nom;
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
}
