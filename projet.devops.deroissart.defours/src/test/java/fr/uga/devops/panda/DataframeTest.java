package fr.uga.devops.panda;

import fr.uga.devops.panda.exception.NotALabelException;
import fr.uga.devops.panda.exception.NotAnIntegeException;
import fr.uga.devops.panda.strategy.operation.Max;
import fr.uga.devops.panda.strategy.operation.Min;
import fr.uga.devops.panda.strategy.operation.Sum;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Test class of the project
 *
 * @author Maxime Deroissart
 * @author Ronan Defours
 * @version 1.0
 */
public class DataframeTest {

    private Dataframe dataframe;
    private HashMap<String, List<Object>> columns;

    /**
     * set up environment before each test
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        columns = new HashMap<>();

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


        dataframe = new Dataframe(columns);
        Dataframe dataframeFile = new Dataframe("./ressources/bank.csv");


        Dataframe dataframe1a2 = dataframe.iloc(0, 1);
        Dataframe dataframe3a4 = dataframe.iloc(2, 3);

        List<String> selectionDataframe = new ArrayList<>();
        selectionDataframe.add("name");
        selectionDataframe.add("solvable");
        selectionDataframe.add("sold");
        Dataframe dataframeSelection = dataframe.loc(selectionDataframe);
        dataframeSelection.displayDataframe();

/*
         This class is used to easily test if an Exception is raised
         For more informations, see :

         http://www.arolla.fr/blog/2014/03/catch-exception-pour-tester-vos-exceptions-sur-junit/
         /
         */

    }

    @Test
    public void testBasicConstructor() {
        int indexLine;
        int nbLineDataframe = dataframe.datas.size();
        Set<String> labels = dataframe.datas.get(0).keySet();
        for (String label : labels) {
            for (indexLine = 0; indexLine < nbLineDataframe; indexLine++) {
                Assert.assertEquals( //compare each value in the dataframe with the source value
                        columns.get(label).get(indexLine), //expected value
                        dataframe.datas.get(indexLine).get(label));
            }
        }
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
    public void testOperationMin() throws NotALabelException, NotAnIntegeException {
        int res = dataframe.operation("sold", Min.MIN);
        Assert.assertEquals(-2500, res);
    }

    @Test
    public void testOperationMax() throws NotALabelException, NotAnIntegeException {
        int res = dataframe.operation("sold", Max.MAX);
        Assert.assertEquals(2800, res);
    }

    @Test
    public void testOperationSum() throws NotALabelException, NotAnIntegeException {
        int res = dataframe.operation("sold", Sum.SUM);
        Assert.assertEquals(1500, res);
    }

    @Test
    public void testOperationAvg() throws NotALabelException, NotAnIntegeException {
        float res = dataframe.average("sold");
        Assert.assertEquals(375.0, res, 0);
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