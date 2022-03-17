package psycomp;

import java.util.ArrayList;
import java.util.Random;

public class AStarParametrique extends AStar {
	//test
	
	/**
	 * Attribut repr�sentant le nombre d'�tape d�j� r�alis�e pas l'algorithme
	 */
	protected int step;
	final static double k1=0.97 ,k2=0, ET = 0.3;
	
	public AStarParametrique(Map m, Croisement depart, Croisement arrivee) {
		super(m, depart, arrivee);
		// TODO Auto-generated constructor stub
		int step=0;
	}
	
	
	/**
	 * permet de g�n�rer un nombre al�toire suivant une loi normale
	 * @param d la moyenne de la loi normale 
	 * @param e l'�cart type de la loi normale 
	 * @return un nombre sous forme de double 
	 */
	public static double randomNormal(double d, double e) {
		// Return a random number from a normal distribution using the Box-Muller method
		
		return d+e*Math.sqrt(-2*Math.log(Math.random()))*Math.cos(2*Math.PI*Math.random());
	}
	/**
	 * Modifie la distance parcourue avec une repr�sentation de la distance parcourue obtenue � partir de l'�valuation param�trique 
	 * de l'erreur d'estimation des humains sur le papiers Thorndyke, P. W. (1981). Distance estimation from cognitive maps. Cognitive Psychology, 13(4), 526‑550. https://doi.org/10.1016/0010-0285(81)90019-0
	 */
	public double fonctionEvaluation(double distParcourue, Croisement c1, Croisement c2) {
		double rand = randomNormal(k2,ET);
		distParcourue=distParcourue*k1+rand*step;
		if(!c2.estVoisin(c1))
			throw new IllegalArgumentException();// ca risque pas d'etre bloquant si on fait le parcours en automatique?
		return distParcourue+c1.distVoisin(c2)+c2.getDistObj();
	}
	
	
	/**
	 * red�finition de la m�thode parcours afin que l'algorithme prenne en compte la modification des param�tres 
	 */
	public ArrayList<Croisement> parcours(){
		
		if(etape.estVoisin(arrivee)) {
			chemin.add(arrivee);
			step = 0;
			return chemin;
		}
		String[] s = etape.getVoisin();
		double minFonctionEval = Integer.MAX_VALUE;
		double fonctionEval;
		
		int indexMin=0;
		for(int i = 0; i<s.length;i++) {
			Croisement c = m.getCroisement(s[i]);
			fonctionEval = fonctionEvaluation(distParcourue,etape,c);
			
			if(!chemin.contains(c)) {
				if(minFonctionEval>fonctionEval) {
					minFonctionEval = fonctionEval;
					indexMin = i;
					step=step+1;
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
