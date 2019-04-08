package fr.uga.devops.panda;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Test class of the project
 *
 * @author Maxime Deroissart
 * @author Ronan Defours
 * @version 1.0
 */
public class DataframeTest {

    private Dataframe dataframe;

    private HashMap<String, List<Object>> colonnes;

    /**
     * set up environment before each test
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        HashMap<String, List<Object>> columns = new HashMap<>();

        List<Object> names = new ArrayList<>();
        names.add("Alain");
        names.add("Beatrice");
        names.add("Claude");
        names.add("Daniel");

        List<Object> positions = new ArrayList<>();
        positions.add(1);
        positions.add(2);
        positions.add(3);
        positions.add(4);

        List<Object> solvable = new ArrayList<>();
        solvable.add(true);
        solvable.add(false);
        solvable.add(true);
        solvable.add(false);

        List<Object> sold = new ArrayList<>();
        sold.add(1500);
        sold.add(-300);
        sold.add(2800);
        sold.add(-2500);


        columns.put("sold", sold);
        columns.put("solvable", solvable);
        columns.put("position", positions);
        columns.put("name", names);

        /**
         System.out.println("Display Dataframe");
         Dataframe dataframe = new Dataframe(columns);
         dataframe.displayDataframe();

         System.out.println("Affichage Dataframe file bank.csv");
         Dataframe dataframeFile = new Dataframe("./ressources/bank.csv");
         dataframeFile.displayDataframe();


         System.out.println("Display 2 first lines");
         dataframe.displayDataframe(DisplayFirstLines.DISPLAYER, 2);
         System.out.println("Display 2 last lines");
         dataframe.displayDataframe(DisplayLastLines.DISPLAYER, 2);

         System.out.println("Create dataframe with iloc (0,1)");

         Dataframe dataframe1a2 = dataframe.iloc(0, 1);
         dataframe1a2.displayDataframe();

         System.out.println("Create dataframe with iloc (2,3)");
         Dataframe dataframe3a4 = dataframe.iloc(2, 3);
         dataframe3a4.displayDataframe();

         System.out.println("Create dataframe with loc ([sold,solvable,name])");
         List<String> selectionDataframe = new ArrayList<>();
         selectionDataframe.add("name");
         selectionDataframe.add("solvable");
         selectionDataframe.add("sold");
         Dataframe dataframeSelection = dataframe.loc(selectionDataframe);
         dataframeSelection.displayDataframe();


         System.out.println("Search min for sold");
         System.out.println(dataframe.operation("sold", Min.MIN));

         System.out.println("Search max for sold");
         System.out.println(dataframe.operation("sold", Max.MAX));

         System.out.println("Compute sum for sold");
         System.out.println(dataframe.operation("sold", Sum.SUM));

         System.out.println("Compute average for sold");
         System.out.println(dataframe.average("sold"));


         This class is used to easily test if an Exception is raised
         For more informations, see :

         http://www.arolla.fr/blog/2014/03/catch-exception-pour-tester-vos-exceptions-sur-junit/
         /
         */

    }

    @Test
    public void testBasicConstructor() {
        //TODO Vérifier que Dataframe correspond bien aux données (parcourt struct)
    }

    @Test
    public void testFileConstructor() {
        //TODO Comparer qu'il est équiv. (equals) au constructeur basique
    }

    @Test
    public void testilocOK() {
        //TODO créer dataframe et vérifier qu'il corresp à la selection
    }

    @Test
    public void testilocBeginGTEnd() {
        //TODO créer DF avec begin > end et vérifier qu'il est vide
    }

    @Test
    public void testilocbadBeginValue() {
        //TODO créer DF avec begin -1
    }

    @Test
    public void testilocbadEndValue() {
        //TODO créer DF avec end qui déclenche un OOB
    }

    @Test
    public void testlocOK() {
        //TODO créer dataframe et vérifier qu'il correspond
    }

    @Test
    public void testlocWithUnknownLabel() {
        //TODO créer dataframe avec label inconnu
    }

    @Test
    public void testOperationMin() {
        //TODO vérifier si resultat correspond
    }

    @Test
    public void testOperationMax() {
        //TODO vérifier si resultat correspond
    }

    @Test
    public void testOperationSum() {
        //TODO vérifier si resultat correspond
    }

    @Test
    public void testOperationAvg() {
        //TODO vérifier si resultat correspond
    }

    @Test
    public void testOperationWithBadType() {
        //TODO vérifier si renvoie NotAnIntegerException
    }

    @Test
    public void testOperationWithUnknownLabel() {
        //TODO vérifier si renvoie NotALabelException
    }
}