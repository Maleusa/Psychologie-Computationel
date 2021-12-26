package psycomp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class estimAStarParam extends AStarParametrique{

	protected int step;
	private double k1=0.97 ,k2=7.8, ecartType = 0;
	
	public estimAStarParam(Map m, Croisement depart, Croisement arrivee) {
		super(m, depart, arrivee);
	}
	
	private static double EQM(ArrayList t1, ArrayList t2) {
		double somme = 0;
		for(int i = 0; i<t1.size();i++) {
			somme += Math.pow((double)t1.get(i)-(double)t2.get(i), 2);
		}
		return Math.sqrt(somme/t1.size());
	}
	
	private static ArrayList dataExpe(String NomFichier) throws IOException {
		ArrayList<Double> l = new ArrayList<Double>();
		BufferedReader f = new BufferedReader(new FileReader(NomFichier));
		String s = f.readLine();
		while(s!=null){
			l.add(Double.parseDouble(s));
			s = f.readLine();
		}
		f.close();
		return l;
	}
	
	private static ArrayList dataProg(ArrayList<Croisements> l) {
		ArrayList<Double> lResu = new ArrayList<Double>();
		
	}
	
	
	/**
	 * Modifie la distance parcourue avec une représentation de la distance parcourue obtenue à partir de l'évaluation parametrique 
	 * de l'erreur d'estimation des humains sur le papiers Thorndyke, P. W. (1981). Distance estimation from cognitive maps. Cognitive Psychology, 13(4), 526‑550. https://doi.org/10.1016/0010-0285(81)90019-0
	 */
	public double fonctionEvaluation(double distParcourue, Croisement c1, Croisement c2, double k) {
		double rand = randomNormal(7.8,1.54);
		distParcourue=distParcourue*k+rand*step;
		//distParcourue=distParcourue*k1+(k2+(Math.random() * ((1.84 + 1.84 ) + 1)) - 1.84)*step;
		if(!c2.estVoisin(c1))
			throw new IllegalArgumentException();// ca risque pas d'etre bloquant si on fait le parcours en automatique?
		return distParcourue+c1.distVoisin(c2)+c2.getDistObj();
	}

	


	
}
