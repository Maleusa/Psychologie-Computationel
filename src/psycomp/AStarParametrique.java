package psycomp;

import java.util.ArrayList;

public class AStarParametrique extends AStar {

	protected int step;
	final static double k1=0.97 ,k2=7.8;
	public AStarParametrique(Map m, Croisement depart, Croisement arrivee) {
		super(m, depart, arrivee);
		// TODO Auto-generated constructor stub
		int step=0;
	}
	/**
	 * Modifie la distance parcourue avec une représentation de la distance parcourue obtenue à partir de l'évaluation parametrique 
	 * de l'erreur d'estimation des humains sur le papiers Thorndyke, P. W. (1981). Distance estimation from cognitive maps. Cognitive Psychology, 13(4), 526‑550. https://doi.org/10.1016/0010-0285(81)90019-0
	 */
	public double fonctionEvaluation(double distParcourue, Croisement c1, Croisement c2) {
		distParcourue=distParcourue*k1+(k2+(Math.random() * ((1.84 + 1.84 ) + 1)) - 1.84)*step;
		if(!c2.estVoisin(c1))
			throw new IllegalArgumentException();// ca risque pas d'etre bloquant si on fait le parcours en automatique?
		return distParcourue+c1.distVoisin(c2)+c2.getDistObj();
	}
	
	
	public ArrayList<Croisement> parcours(){
		System.out.println("depart = "+ depart);
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
				step=step+1;
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
