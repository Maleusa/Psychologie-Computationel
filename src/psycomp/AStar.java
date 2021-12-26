package psycomp;

import java.util.ArrayList;


public class AStar {
	/**
	 * Carte sur laquelle l'algorithme A* sera dï¿½roulï¿½ 
	 */
	Map m;
	
	/**
	 * represente le croisement à partir duquel le participant doit tracé son chemin
	 */
	Croisement depart;
	
	/**
	 *represente le croisement que le participant doit atteindre avec son chemin
	 */
	Croisement arrivee;
	
	/**
	 * represente le chemin emprunté par le porgramme 
	 */
	ArrayList<Croisement> chemin;
	/**
	 * L'attribut distParcourue represente la distance effective parcourue par le programme à chaque étape de l'algorithme
	 */
	double distParcourue;
	
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
		distParcourue = 0;
		chemin = new ArrayList<Croisement>();
	}
	
	
	
	/**
	 * Méthode permettant de calculer la fonction d'évalution représentant le coût total estimé pour aller vers l’´état final en partant de c1 
	 * et en passant par c2 
	 * @param distParcourue représente la distance déja parcourue avant le croisement c1
	 * @param depart 
	 * @param arrive
	 * @return
	 */
	public double fonctionEvaluation(double distParcourue, Croisement c1, Croisement c2) {
		if(!c2.estVoisin(c1))
			throw new IllegalArgumentException();// ca risque pas d'etre bloquant si on fait le parcours en automatique?
		return distParcourue+c1.distVoisin(c2)+c2.getDistObj();
	}
	
	/**
	 * Méthode réalisant l'algorithme A* sans retour en arrière 
	 * @return une liste de croisement représentant le chemin emptunté par l'algorithme 
	 */
	public ArrayList<Croisement> parcours(){
	
		if(depart.estVoisin(arrivee)) {
			chemin.add(arrivee);
			return chemin;
		}
		String[] s = depart.getVoisin();
		double minFonctionEval = 1000000;
		double fonctionEval;
		int indexMin=0;
		for(int i = 0; i<s.length;i++) {
			Croisement c = m.getCroisement(s[i]);
			fonctionEval = fonctionEvaluation(distParcourue,depart,c);
			if(!chemin.contains(c)) {
			if(minFonctionEval>fonctionEval) {
				minFonctionEval = fonctionEval;
				indexMin = i;
			}
			}
		}
		Croisement etape =  m.getCroisement(s[indexMin]);
		distParcourue += etape.distVoisin(depart);
		this.depart = etape;
		chemin.add(etape);
		return parcours();
	}

}
