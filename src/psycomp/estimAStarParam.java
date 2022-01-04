package psycomp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class estimAStarParam extends AStarParametrique{
	
	public estimAStarParam(Map m, Croisement depart, Croisement arrivee) {
		super(m, depart, arrivee);
	}
	
	/**
	 * Permet de calculer l'écart quadratique moyen entre les données stockées dans t1 et les données stockées dans t2
	 * @param t1 premiere série de données 
	 * @param t2 deuxième série de données 
	 * @return la valeure de l'EQM
	 */
	private static double EQM(ArrayList t1, ArrayList t2) {
		if(t2==null) {
			return Integer.MAX_VALUE;
		}
		double somme = 0;
		for(int i = 0; i<t1.size();i++) {
			somme += Math.pow((double)t1.get(i)-(double)t2.get(i), 2);
		}
		return Math.sqrt(somme/t1.size());
	}
	
	/**
	 * Permet d'obtenir une liste contenant les données expérimentales récoltées
	 * @param NomFichier fichier contenant les données récoltés
	 * @return Une ArrayList contenant les données la probabilité de passage pour chaque croisement 
	 * @throws IOException
	 */
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
	
	/**
	 * Permet de générer une liste contenant les données simulées par le programme
	 * @param l Résultats générés par le programme 
	 * @return Une ArrayList contenant la probabilité de passage pour chaque croisement 
	 */
	
	private static ArrayList dataProg(ArrayList<Croisement> l) {
		
		ArrayList<Double> lResu = new ArrayList<Double>();
		for(int i = 1; i<=53;i++) {
			boolean b = false;
			for(int k = 0; k<l.size();k++) {
				
				if(l.get(k).getNom().equals(i+"")) {
					
					lResu.add(i-1,1.0);
					b=true;
				}
				
			}
			if(b==false) {
				lResu.add(i-1,0.0);
			}

		}
		return lResu;
	}
	
	
	/**
	 * Modifie la distance parcourue avec une représentation de la distance parcourue obtenue Ã  partir de l'évaluation paramétrique 
	 * de l'erreur d'estimation des humains sur le papiers Thorndyke, P. W. (1981). Distance estimation from cognitive maps. Cognitive Psychology, 13(4), 526â€‘550. https://doi.org/10.1016/0010-0285(81)90019-0
	 */
	public double fonctionEvaluation(double distParcourue, Croisement c1, Croisement c2, double k2, double ecartType) {
		double rand = randomNormal(k2,ecartType);
		distParcourue=distParcourue*k1+rand*step;
		//distParcourue=distParcourue*k1+(k2+(Math.random() * ((1.84 + 1.84 ) + 1)) - 1.84)*step;
		if(!c2.estVoisin(c1))
			throw new IllegalArgumentException();// ca risque pas d'etre bloquant si on fait le parcours en automatique?
		return distParcourue+c1.distVoisin(c2)+c2.getDistObj();
	}
	
	
	
	public ArrayList<Croisement> parcours(double k2, double ecartType){
		
		if(depart.estVoisin(arrivee)) {
			chemin.add(arrivee);
			return chemin;
		}
		String[] s = depart.getVoisin();
		double minFonctionEval = Integer.MAX_VALUE;
		double fonctionEval;
		
		int indexMin=1;
		for(int i = 0; i<s.length;i++) {
			Croisement c = m.getCroisement(s[i]);
			fonctionEval = fonctionEvaluation(distParcourue,depart,c,k2,ecartType);
			
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
		this.etape = etape;
		chemin.add(etape);
		return parcours();
	}
	
	/**
	 * Méthode réalisant l'estimation paramétrique pour k2 et ET
	 * @param borneInfK2 borne inférieur de l'intervalle de recherche pour k2
	 * @param borneSupK2 borne supérieure de l'intervalle de recherche pour k2
	 * @param pasK2 pas de parcours de l'intervalle de recherche pour k2
	 * @param borneInfET borne inférieure de l'intervalle de recherche pour ET
	 * @param borneSupET borne supérieure de l'intervalle de recherche pour ET
	 * @param pasET pas de parcours de l'intervalle de recherche pour ET
	 * @return un tableau contenant les valeures estimées pour k2 et pour ET
	 * @throws IOException
	 */

	public double[] estimParam(double borneInfK2,double borneSupK2, double pasK2,double borneInfET,double borneSupET, double pasET) throws IOException {
		ArrayList t1 = dataExpe("D:\\Users\\psychologie computationel\\Psychologie-Computationel\\src\\psycomp\\dataExpe.txt");
		ArrayList t2 = new ArrayList();
		double eqmMin = Integer.MAX_VALUE;
		double[] t = new double[2];

		double k=borneInfK2;
		double p=borneInfET;

		while(k<borneSupK2){ 
			while(p<borneSupET) {
				try {
					chemin.clear();
					etape = depart;
					t2 = dataProg(parcours(k,p));
				}catch(Error e) {
					t2=null;
				}
				if(EQM(t1,t2)<eqmMin) {
					eqmMin = EQM(t1,t2);

					t[0]=k;
					t[1]=p;
				}
				p+=pasET;


			}
			k+=pasK2;
		}

		return t;
	}
	

	
	
}
