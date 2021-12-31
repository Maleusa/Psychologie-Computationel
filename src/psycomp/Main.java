package psycomp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;





public class Main {

	public static void main(String[] args) throws IOException {
		//Lecture du fichier contenants les informations necessaires ï¿½ la construction de l'entiï¿½retï¿½ des croisements contenus dans la carte
		Reader r = new FileReader("D:\\Users\\psychologie computationel\\Psychologie-Computationel\\src\\psycomp\\croisements.txt");
		BufferedReader data = new BufferedReader(r);
		//Crï¿½ation de la carte sur laquelle A* sera dï¿½roulï¿½
		Map m = new Map(data);

		//Dï¿½finition du croisement de dï¿½part du trajet
		Croisement depart = m.getCroisement("1");

		//Dï¿½finition du croisement d'arrivï¿½e du trajet 
		Croisement arrivee = m.getCroisement("47");


		////////////////////////
		//estimation des parametres k2 et ET
		/////////////////////////
	
		estimAStarParam e = new estimAStarParam(m,depart,arrivee);
		double[] t = e.estimParam(0, 1, 0.001, 0, 1, 0.001);
		
		System.out.println(Arrays.toString(t));

		/////////////////////
		//A* 
		////////////////////

		//Dï¿½roulement de l'algorithme et stockage des noms des croisements empruntï¿½ par le programme
		AStar a = new AStar(m,depart,arrivee);
		ArrayList<Croisement> chemin =a.parcours();
		BufferedWriter fichierChemin =new BufferedWriter(new FileWriter("chemin")); 
		String s = "1";
		for(int i = 0; i<chemin.size();i++) {
			s = s + '\n' + chemin.get(i).getNom();
		}
		fichierChemin.write(s);
		fichierChemin.close();

		///////////////////////
		//A* paramétrique
		/////////////////////

		//Dï¿½roulement de l'algorithme et stockage des noms des croisements empruntï¿½ par le programme
		BufferedWriter fichierCheminParam = new BufferedWriter(new FileWriter("cheminParam")); 

		for(int k = 0; k<100; k++) {
			AStarParametrique a2 = new AStarParametrique(m,depart,arrivee);
			ArrayList cheminParam =  a2.parcours();
			String s2 = "1";
			for(int i = 0; i<cheminParam.size();i++) {
				s2 = s2 + " " +((Croisement) cheminParam.get(i)).getNom();
			}
			fichierCheminParam.write(s2+'\n');
		}

		AStarParametrique a2 = new AStarParametrique(m,depart,arrivee);
		a2.parcours();

		fichierCheminParam.close();


	}

}
