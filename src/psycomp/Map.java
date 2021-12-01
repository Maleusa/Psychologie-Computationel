package psycomp;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;

public class Map {
	Croisement[] arbre;
	
	public Map() throws IOException {
		
		Reader r = new FileReader("D:\\Users\\psychologie computationel\\Psychologie-Computationel\\src\\psycomp\\croisements.txt");
		BufferedReader data = new BufferedReader(r);
		String ligne = null;
		int k = 0;
		Croisement[] arbre = new Croisement[53];
		while((ligne = data.readLine())!=null) {
			String nom = ligne.substring(0, ligne.indexOf(" "));
			String voisin = ligne.substring(ligne.indexOf("[")+1, ligne.indexOf("]")+1);
			String distVoisin = ligne.substring(ligne.lastIndexOf("[")+1,ligne.lastIndexOf("]")+1);
			double distBut =Float.parseFloat(ligne.substring(ligne.lastIndexOf("]")+2,ligne.length()));
			
			int nbrVoisin=1;
			for(int i = 0;i<voisin.length();i++) {
				if(voisin.charAt(i)==',')
					nbrVoisin++;
			}
			
			String[] listeVoisin = new String[nbrVoisin];
			double[] listeDistVoisin = new double[nbrVoisin];
			for(int i = 0; i<listeVoisin.length-1;i++) {
				listeVoisin[i] = voisin.substring(0,voisin.indexOf(","));
				listeDistVoisin[i] = Float.parseFloat(distVoisin.substring(0,distVoisin.indexOf(",")));
				voisin = voisin.substring(voisin.indexOf(",")+1);
				distVoisin = distVoisin.substring(distVoisin.indexOf(",")+1);
			}
			listeVoisin[listeVoisin.length-1]=voisin.substring(0,voisin.indexOf("]"));
			listeDistVoisin[listeDistVoisin.length-1]=Float.parseFloat(distVoisin.substring(0,distVoisin.indexOf("]")));
			arbre[k]=new Croisement(nom,listeVoisin,listeDistVoisin,distBut);
			k++;
			
		}
		this.arbre=arbre;
		data.close();
		
	}
	
	
	
	public static void main(String[] args) throws IOException {
		Map m = new Map();
		System.out.println(m.arbre[52]);
	}
	

}
