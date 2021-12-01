package psycomp;

public class AStar {
	/**
	 * Carte sur laquelle l'algorithme A* sera déroulé 
	 */
	Map m;
	
	/**
	 * L'attribut départ représente le croisement à partir duquel le participant doit tracé son chemin
	 * L'attribut arrivee représente le croisement que le participant doit atteindre avec son chemin
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
	 * methode permettant de calculer la fonction d'évalution représentant le coût total estimé pour aller vers l’´état final en partant de c1 
	 *et en passant par c2 
	 * @param distParcourue représente la distance déja parcourue avant le croisement c1
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
