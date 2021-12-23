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
		Reader r = new FileReader("D:\\Users\\psychologie computationel\\Psychologie-Computationel\\src\\psycomp\\croisements.txt");
		BufferedReader data = new BufferedReader(r);
		Map m = new Map(data);
		Croisement depart = m.getCroisement("1");
		Croisement arrivee = m.getCroisement("47");
		AStar a = new AStar(m,depart,arrivee);
		ArrayList<Croisement> chemin =a.parcours();
		BufferedWriter fichierChemin =new BufferedWriter(new FileWriter("chemin")); 
		String s = "";
		for(int i = 0; i<chemin.size();i++) {
			s = s + '\n' + chemin.get(i).getNom();
		}
		fichierChemin.write(s);
		fichierChemin.close();
		
	}

}
