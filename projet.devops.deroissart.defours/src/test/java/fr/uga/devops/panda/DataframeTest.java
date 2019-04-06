package fr.uga.devops.panda;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Classe de test du projet
 *
 * @author Maxime Deroissart
 * @author Ronan Defours
 * @version 1.0
 */
public class DataframeTest {

    private Dataframe dataframe;

    private HashMap<String, List<Object>> colonnes;

    /**
     * Création de l'environnement de test avant le lancement de chaque test
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        colonnes = new HashMap<>();

        List<Object> noms = new ArrayList<>();
        noms.add("Alain");
        noms.add("Beatrice");
        noms.add("Claude");
        noms.add("Daniel");

        colonnes.put("nom", noms);


        //* {position:[1,2,3,4]}
        //* {libelle:["premier","deuxième","troisième","quatrième"]}


    }

    @Test
    public void testConstructorOneWithOneColumn() {
        dataframe = new Dataframe(colonnes);
    }


    /**
     * This class is used to easily test if an Exception is raised
     * For more informations, see :
     *
     * http://www.arolla.fr/blog/2014/03/catch-exception-pour-tester-vos-exceptions-sur-junit/
     */
}