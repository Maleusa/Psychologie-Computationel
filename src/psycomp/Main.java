package psycomp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;


public class Main {

	public static void main(String[] args) throws IOException {
		//Lecture du fichier contenants les informations necessaires � la construction de l'enti�ret� des croisements contenus dans la carte
		Reader r = new FileReader("D:\\Users\\psychologie computationel\\Psychologie-Computationel\\src\\psycomp\\croisements.txt");
		BufferedReader data = new BufferedReader(r);
		//Cr�ation de la carte sur laquelle A* sera d�roul�
		Map m = new Map(data);
		
		//D�finition du croisement de d�part du trajet
		Croisement depart = m.getCroisement("1");
		
		//D�finition du croisement d'arriv�e du trajet 
		Croisement arrivee = m.getCroisement("47");
		
		//D�roulement de l'algorithme et stockage des noms des croisements emprunt� par le programme
		AStarParametrique a = new AStarParametrique(m,depart,arrivee);
		ArrayList<Croisement> chemin =a.parcours();
		BufferedWriter fichierChemin =new BufferedWriter(new FileWriter("chemin")); 
		String s = "1";
		for(int i = 0; i<chemin.size();i++) {
			s = s + '\n' + chemin.get(i).getNom();
		}
		fichierChemin.write(s);
		fichierChemin.close();
		
	}

}
