package fr.uga.devops.panda;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Classe principale du projet
 *
 * @author Maxime Deroissart
 * @author Ronan Defours
 * @version 1.0
 */
public class Dataframe {

    //Attribut
    /**
     * Un dataframe est ici représenté par une liste de dictionnaires (ligne)
     * Chaque dictionnaire possède toutes les colonnes (clés)
     * Chacune est associée à la valeur de sa ligne
     */
    List<HashMap<String, Object>> datas;

    //CONSTRUCTEURS DATAFRAME

    /**
     * Méthode 1, on fournit en entrée un dictionnaire
     * Chaque clé correspond à une colonne
     * Une colonne contient une liste de valeurs
     * <p>
     * Exemple
     * <p>
     * {
     * nom:[Alain,Beatrice,Claude,Daniel],
     * position:[1,2,3,4],
     * libelle:["premier","deuxième","troisième","quatrième"]
     * }
     * <p>
     * Renvoie
     * <p>
     * nom         position   libelle
     * Alain       1          premier
     * Beatrice    2          deuxième
     * Claude      3          troisième
     * Daniel      4          quatrième
     */


    public Dataframe(HashMap<String, List<Object>> colonnes) {
        datas = new ArrayList<>();
        boolean premiere_colonne = true; //Si première colonne, on instancie une Hashmap pour chaque ligne, sinon on les complète
        int index = 0;
        // On parcourt chaque colonne
        for (String nomColonne : colonnes.keySet()) {
            //Ajout pour chaque ligne de la valeur associée à cette colonne
            for (Object valeurColonne : colonnes.get(nomColonne)) {
                if (premiere_colonne) {
                    HashMap<String, Object> cellule = new HashMap<>();
                    cellule.put(nomColonne, valeurColonne);
                    datas.add(cellule);
                } else {
                    datas.get(index).put(nomColonne, valeurColonne);
                    index++;
                }
            }
            premiere_colonne = false;
            index = 0;
        }
    }

    //AFFICHAGE Dataframe

    public void displayAllDataframe() {
        int index;
        String space = new String(new char[10]).replace("\0", " ");
        for (String nomColonne : datas.get(0).keySet()) {
            System.out.print(nomColonne + space); //Affichage des noms des colonnes
        }
        System.out.println();
        for (index = 0; index < datas.size(); index++) { //Parcourt de chaque ligne
            HashMap<String, Object> ligne = datas.get(index);
            for (String nomColonne : ligne.keySet()) {
                Object valeur = ligne.get(nomColonne);
                //Calcul affichage nb d'espace : (valeurParDefaut + taille nomColonne - taille valeur)
                space = new String(new char[10 + (nomColonne.length() - valeur.toString().length())]).replace("\0", " ");
                System.out.print(valeur + space); //Affichage des valeurs de cette ligne
            }
            System.out.println();
        }
    }

    public void displayFirstLinesDataframe() {

    }

    public void displayLastLinesDataframe() {

    }

    public static void main(String args[]) {
        HashMap<String, List<Object>> colonnes = new HashMap<>();

        List<Object> noms = new ArrayList<>();
        noms.add("Alain");
        noms.add("Beatrice");
        noms.add("Claude");
        noms.add("Daniel");

        List<Object> positions = new ArrayList<>();
        positions.add(1);
        positions.add(2);
        positions.add(3);
        positions.add(4);

        List<Object> presences = new ArrayList<>();
        presences.add(true);
        presences.add(false);
        presences.add(true);
        presences.add(false);

        colonnes.put("nom", noms);
        colonnes.put("position", positions);
        colonnes.put("present", presences);

        System.out.println("creation dataframe");
        Dataframe dataframe = new Dataframe(colonnes);
        System.out.println("affichage dataframe");
        dataframe.displayAllDataframe();
    }

}
