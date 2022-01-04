package psycomp;

import java.util.ArrayList;


public class AStar {
	/**
	 * Carte sur laquelle l'algorithme A* sera d�roul�
	 */
	Map m;
	
	/**
	 * repr�sente l'�tape � laquelle est l'algorithme
	 */
	Croisement etape;
	/**
	 * repr�sente le croisement � partir duquel le participant doit tracer son chemin
	 */
	Croisement depart;
	/**
	 *repr�sente le croisement que le participant doit atteindre avec son chemin
	 */
	Croisement arrivee;
	
	/**
	 * repr�sente le chemin emprunt� par le programme 
	 */
	ArrayList<Croisement> chemin;
	/**
	 * L'attribut distParcourue repr�sente la distance effective parcourue par le programme � chaque �tape de l'algorithme
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
		etape = depart;
		this.arrivee = arrivee;
		distParcourue = 0;
		chemin = new ArrayList<Croisement>();
	}
	
	
	
	/**
	 * M�thode permettant de calculer la fonction d'�valuation repr�sentant le co�t total estim� pour aller vers l��tat final en partant de c1 
	 * et en passant par c2 
	 * @param distParcourue repr�sente la distance d�ja parcourue avant le croisement c1
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
	 * M�thode r�alisant l'algorithme A* sans retour en arri�re 
	 * @return une liste de croisement repr�sentant le chemin emptunt� par l'algorithme 
	 */
	public ArrayList<Croisement> parcours(){
		
		if(etape.estVoisin(arrivee)) {
			chemin.add(arrivee);
			return chemin;
		}
		String[] s = etape.getVoisin();
		double minFonctionEval = 1000000;
		double fonctionEval;
		int indexMin=0;
		for(int i = 0; i<s.length;i++) {
			Croisement c = m.getCroisement(s[i]);
			fonctionEval = fonctionEvaluation(distParcourue,etape,c);
			if(!chemin.contains(c)) {
			if(minFonctionEval>fonctionEval) {
				minFonctionEval = fonctionEval;
				indexMin = i;
			}
			}
		}
		Croisement etape =  m.getCroisement(s[indexMin]);
		distParcourue += etape.distVoisin(this.etape);
		this.etape = etape;
		
		chemin.add(etape);
		return parcours();
	}

}
