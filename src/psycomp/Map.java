package psycomp;
import java.io.BufferedReader;
import java.io.IOException;


public class Map {
	/**
	 * attribut contenant l'ensemble des croisements contenus dans la carte 
	 */
	Croisement[] arbre;
	
	/**
	 * Permet de construire l'attribut arbre à partir d'un fichier contenant les différentes informations neccesaires à la création de tous les croisements de la carte
	 * @throws IOException
	 */
	public Map(BufferedReader data) throws IOException {
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
	
	/**
	 * 
	 * @param s
	 * @return Le croisement de la carte ayant le nom s 
	 */
	public Croisement getCroisement(String s) {
		Croisement c = null;
		for(int i = 0; i<arbre.length;i++) {
			if(arbre[i].getNom().equals(s)) {
				c = arbre[i];
				break;
			}
		}
		if(c==null)
			throw new IllegalArgumentException();
		return c;
	}
	

	

}
