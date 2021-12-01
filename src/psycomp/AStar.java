package psycomp;

public class AStar {
	/**
	 * Carte sur laquelle l'algorithme A* sera d�roul� 
	 */
	Map m;
	
	/**
	 * L'attribut d�part repr�sente le croisement � partir duquel le participant doit trac� son chemin
	 * L'attribut arrivee repr�sente le croisement que le participant doit atteindre avec son chemin
	 */
	Croisement depart, arrivee;
	
	/**
	 * 
	 * @param m
	 * @param depart
	 * @param arrivee
	 */
	public AStar(Map m, Croisement depart, Croisement arrivee) {
		this.m = m;
		this.depart = depart;
		this.arrivee = arrivee;
	}
	
	/**
	 * methode permettant de calculer la fonction d'�valution repr�sentant le co�t total estim� pour aller vers l���tat final en partant de c1 
	 *et en passant par c2 
	 * @param distParcourue repr�sente la distance d�ja parcourue avant le croisement c1
	 * @param depart 
	 * @param arrive
	 * @return
	 */
	public double fonctionEvaluation(double distParcourue, Croisement c1, Croisement c2) {
		if(!depart.estVoisin(arrive))
			throw new IllegalArgumentException();
		return distParcourue+depart.distVoisin(arrive)+arrive.getDistObj();
	}

}
