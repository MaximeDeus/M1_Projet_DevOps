package fr.uga.devops.panda;

import fr.uga.devops.panda.exception.BadValueException;
import fr.uga.devops.panda.exception.NotALabelException;
import fr.uga.devops.panda.exception.NotAnIntegeException;
import fr.uga.devops.panda.strategy.display.DisplayFirstLines;
import fr.uga.devops.panda.strategy.display.DisplayLastLines;
import fr.uga.devops.panda.strategy.operation.Max;
import fr.uga.devops.panda.strategy.operation.Min;
import fr.uga.devops.panda.strategy.operation.Sum;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;

/**
 * Test class of the project
 *
 * @author Maxime Deroissart
 * @author Ronan Defours
 * @version 1.0
 * <p>
 * The google library catch-excception is used to
 * easily test if an Exception is raised
 * For more informations, see :
 * http://www.arolla.fr/blog/2014/03/catch-exception-pour-tester-vos-exceptions-sur-junit/
 */

public class DataframeTest {

    private Dataframe dataframe; //bank.csv
    private Dataframe dataframeFile; //bank.csv
    private Dataframe dataframe1To1; //1st line of dataframe
    private Dataframe dataframe1To2;
    private Dataframe dataframe3To4;
    private static HashMap<String, List<Object>> columns;
    private Dataframe dataframeLoc; //dataframe without column 'position'

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
        dataframeFile = new Dataframe("./ressources/bank.csv");

        dataframe1To1 = dataframe.iloc(0, 0);
        dataframe1To2 = dataframe.iloc(0, 1);
        dataframe3To4 = dataframe.iloc(2, 3);

        List<String> selectionDataframe = new ArrayList<>();
        selectionDataframe.add("name");
        selectionDataframe.add("solvable");
        selectionDataframe.add("sold");
        dataframeLoc = dataframe.loc(selectionDataframe);


    }

    /**
     * This class method compare each values in a dataframe with the source
     *
     * @param df
     */
    public static void testConstructor(Dataframe df, int begin, int end) {
        Set<String> labels = df.datas.get(0).keySet();
        int indexLine = begin;
        int indexDataframe;
        for (String label : labels) {
            for (indexDataframe = 0; indexDataframe <= (end - begin); indexDataframe++) {
                Assert.assertEquals( //compare each value in the dataframe with the source value
                        columns.get(label).get(indexLine).toString(), //expected value
                        df.datas.get(indexDataframe).get(label).toString()); //compare string value (example : bank.csv)
                indexLine++;
            }
            indexLine = begin;
        }
    }

    @Test
    public void testBasicConstructor() {
        testConstructor(dataframe, 0, dataframe.datas.size() - 1);
    }

    @Test
    public void testFileConstructor() {
        testConstructor(dataframeFile, 0, dataframe.datas.size() - 1);
    }

    @Test
    public void testilocOK() {
        testConstructor(dataframe1To1, 0, 0);
        testConstructor(dataframe1To2, 0, 1);
        testConstructor(dataframe3To4, 2, 3);
    }


    @Test
    public void testilocBeginGTEnd() throws BadValueException {
        catchException(dataframe).iloc(1, 0); //Catch the exception raised by executed code
        assert caughtException() instanceof BadValueException;
    }

    @Test
    public void testilocOOBBegin() throws BadValueException {
        catchException(dataframe).iloc(-1, 0);
        assert caughtException() instanceof BadValueException;
    }

    @Test
    public void testilocOOBEnd() throws BadValueException {
        catchException(dataframe).iloc(0, dataframe.datas.size());
        assert caughtException() instanceof BadValueException;
    }

    @Test
    public void testlocOK() {
        testConstructor(dataframeLoc, 0, dataframeLoc.datas.size() - 1);
    }

    @Test
    public void testlocWithUnknownLabel() throws NotALabelException {
        ArrayList<String> containerUnknownColumn = new ArrayList<String>();
        String[] unknownColumn = {"sold", "position", "solvable", "octogone"};
        containerUnknownColumn.addAll(Arrays.asList(unknownColumn));
        catchException(dataframe).loc(containerUnknownColumn);
        assert caughtException() instanceof NotALabelException;
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
    public void testOperationOK() throws NotALabelException, NotAnIntegeException {
        catchException(dataframe).operation("sold", Max.MAX);
        Assert.assertNull(caughtException());
    }

    @Test
    public void testOperationWithBadType() throws NotALabelException, NotAnIntegeException {
        catchException(dataframe).operation("name", Sum.SUM);
        assert caughtException() instanceof NotAnIntegeException;
    }


    @Test
    public void testOperationWithBadLabel() throws NotALabelException, NotAnIntegeException {
        catchException(dataframe).operation("mononoke", Max.MAX);
        assert caughtException() instanceof NotALabelException;
    }

    @Test
    public void testAverageOK() throws NotALabelException, NotAnIntegeException {
        catchException(dataframe).average("sold");
        Assert.assertNull(caughtException());
    }

    @Test
    public void testAverageWithBadType() throws NotALabelException, NotAnIntegeException {
        catchException(dataframe).average("name");
        assert caughtException() instanceof NotAnIntegeException;
    }

    @Test
    public void testAverageWithBadLabel() throws NotALabelException, NotAnIntegeException {
        catchException(dataframe).average("RIP_Notre_Dame");
        assert caughtException() instanceof NotALabelException;
    }

    @Test
    public void testDisplayDataframeOK() {
        catchException(dataframe).displayDataframe();
        Assert.assertNull(caughtException());
    }

    @Test
    public void testDisplayDataframeWithStrategyFirstLineOK() throws BadValueException {
        catchException(dataframe).displayDataframe(DisplayFirstLines.DISPLAYER, 1);
        Assert.assertNull(caughtException());
    }

    @Test
    public void testDisplayDataframeFirstLineWith0Line() throws BadValueException {
        catchException(dataframe).displayDataframe(DisplayFirstLines.DISPLAYER, 0);
        assert caughtException() instanceof BadValueException;
    }

    @Test
    public void testDisplayDataframeFirstLineWithTooMuchLine() throws BadValueException {
        catchException(dataframe).displayDataframe(DisplayFirstLines.DISPLAYER, dataframe.datas.size() + 1);
        assert caughtException() instanceof BadValueException;
    }

    @Test
    public void testDisplayDataframeWithStrategyLastLineOK() throws BadValueException {
        catchException(dataframe).displayDataframe(DisplayLastLines.DISPLAYER, 1);
        Assert.assertNull(caughtException());
    }

    @Test
    public void testDisplayDataframeLastLineWith0Line() throws BadValueException {
        catchException(dataframe).displayDataframe(DisplayLastLines.DISPLAYER, 0);
        assert caughtException() instanceof BadValueException;
    }

    @Test
    public void testDisplayDataframeLastLineWithTooMuchLine() throws BadValueException {
        catchException(dataframe).displayDataframe(DisplayLastLines.DISPLAYER, dataframe.datas.size() + 1);
        assert caughtException() instanceof BadValueException;
    }
}