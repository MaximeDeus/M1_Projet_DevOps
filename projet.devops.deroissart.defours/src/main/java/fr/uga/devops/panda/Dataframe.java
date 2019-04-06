package fr.uga.devops.panda;


import fr.uga.devops.panda.exception.NotALabelException;
import fr.uga.devops.panda.exception.NotAnIntegeException;
import fr.uga.devops.panda.strategy.display.DisplayFirstLines;
import fr.uga.devops.panda.strategy.display.DisplayLastLines;
import fr.uga.devops.panda.strategy.display.Display_Itf;
import fr.uga.devops.panda.strategy.operation.Max;
import fr.uga.devops.panda.strategy.operation.Min;
import fr.uga.devops.panda.strategy.operation.Operation;
import fr.uga.devops.panda.strategy.operation.Sum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Main class of the project
 *
 * @author Maxime Deroissart
 * @author Ronan Defours
 * @version 1.0
 */
public class Dataframe {

    //Attribut
    /**
     * A Dataframe instance is represented by a list of dictionaries (lines)
     * Each line must have the same keys (columns) and a value.
     * <p>
     * This implementation was inspired by the official pandas librairies.
     * For more details, see method 4 in this link
     * https://www.geeksforgeeks.org/different-ways-to-create-pandas-dataframe/
     */
    List<HashMap<String, Object>> datas;

    //CONSTRUCTORS DATAFRAME

    /**
     * Méthod 1, we give a dictionary in entry
     * Each key is a column
     * The value for each key os a list of all values to put in the dataframe
     * <p>
     * Example
     * <p>
     * {
     * name:[Alain,Beatrice,Claude,Daniel],
     * position:[1,2,3,4],
     * }
     * <p>
     * Returns
     * <p>
     * name        position
     * Alain       1
     * Beatrice    2
     * Claude      3
     * Daniel      4
     */


    public Dataframe(HashMap<String, List<Object>> columns) {
        datas = new ArrayList<>();
        boolean first_column = true; //if first column, we instantiate Hashmap for each lines, otherwise we fill them
        int index = 0;
        //Browse each column
        for (String columnName : columns.keySet()) {
            //Fill column
            for (Object columnValue : columns.get(columnName)) {
                if (first_column) { //if first column
                    HashMap<String, Object> cell = new HashMap<>();
                    cell.put(columnName, columnValue);
                    datas.add(cell);
                } else { //other columns
                    datas.get(index).put(columnName, columnValue);
                    index++;
                }
            }
            first_column = false;
            index = 0;
        }
    }

    /**
     * Méthode 2, a CSV file is parsed
     * Example
     * <p>
     * {
     * name,Alain,Beatrice,Claude,Daniel
     * position,1,2,3,4
     *
     * <p>
     * Returns
     * <p>
     * name        position
     * Alain       1
     * Beatrice    2
     * Claude      3
     * Daniel      4
     */


    public void Dataframe(HashMap<String, List<Object>> columns) {
        //TODO
    }

    //DISPLAY Dataframe

    /**
     * This method display all lines of the dataframe
     */
    public void displayDataframe() {
        int index;
        String space = new String(new char[10]).replace("\0", " ");
        for (String columnsName : datas.get(0).keySet()) {
            System.out.print(columnsName + space); //Display column names
        }
        System.out.println();
        for (index = 0; index < datas.size(); index++) { //Display each lines
            HashMap<String, Object> line = datas.get(index);
            for (String columnsName : line.keySet()) {
                Object value = line.get(columnsName);
                //Compute number of spaces : (defaultValue + column name size - value size)
                space = new String(new char[10 + (columnsName.length() - value.toString().length())]).replace("\0", " ");
                System.out.print(value + space);
            }
            System.out.println();
        }
    }

    /**
     * This method display the lines depending of the strategy (first of last lines)
     *
     * @param displayStrategy the choosen strategy
     * @param nb_lines        the number of lines displayed
     */
    public void displayDataframe(Display_Itf displayStrategy, int nb_lines) {
        //TODO Ajouter try/catch pour gérer OOB
        int index;
        String space = new String(new char[10]).replace("\0", " ");
        for (String columnsName : datas.get(0).keySet()) {
            System.out.print(columnsName + space); //Display column names
        }
        System.out.println();
        List<HashMap<String, Object>> selectedLines = displayStrategy.selectLines(this.datas, nb_lines);
        for (index = 0; index < selectedLines.size(); index++) { //Display each lines
            HashMap<String, Object> lines = selectedLines.get(index);
            for (String columnsName : lines.keySet()) {
                Object value = lines.get(columnsName);
                //Compute number of spaces : (defaultValue + column name size - value size)
                space = new String(new char[10 + (columnsName.length() - value.toString().length())]).replace("\0", " ");
                System.out.print(value + space);
            }
            System.out.println();
        }
    }

    /**
     * This method is used for create Dataframe from a subset of an existing dataframe
     * <p>
     * The selection is made by interval of lines
     * <p>
     * More info see :
     * <p>
     * http://pandas.pydata.org/pandas-docs/stable/reference/api/pandas.DataFrame.iloc.html#pandas.DataFrame.iloc
     */
    public Dataframe iloc(int begin, int end) {
        //TODO Try/Catch OOB
        int index;
        HashMap<String, List<Object>> columns = new HashMap<>(); //Paramater for the constructor
        for (String columnsName : this.datas.get(0).keySet()) { //Fill all columns
            List<Object> values = new ArrayList<>(); //Contains all values of the current column
            for (index = begin; index <= end; index++) {
                values.add(this.datas.get(index).get(columnsName)); //Fill current column
            }
            columns.put(columnsName, values);
        }
        return new Dataframe(columns);
    }

    /**
     * This method is used for create Dataframe from a subset of an existing dataframe
     * <p>
     * The selection is made by column names
     * <p>
     * More info see :
     * <p>
     * http://pandas.pydata.org/pandas-docs/stable/reference/api/pandas.DataFrame.loc.html#pandas.DataFrame.iloc
     */
    public Dataframe loc(List<String> labels) {
        //TODO Vérifier que les clés existent
        int index;
        HashMap<String, List<Object>> columns = new HashMap<>(); //Paramater for the constructor
        for (String columnsName : labels) { //Fill all columns
            List<Object> values = new ArrayList<>(); //Contains all values of the current column
            for (index = 0; index < this.datas.size(); index++) {
                values.add(this.datas.get(index).get(columnsName)); //Fill current column
            }
            columns.put(columnsName, values);
        }
        return new Dataframe(columns);
    }


    /**
     * This method returns the result of the operation for this column
     *
     * @param label: column name
     * @param op     : the operation
     * @return the operation of this column
     * <p>
     * For more informations see :
     * <p>
     * http://pandas.pydata.org/pandas-docs/stable/reference/api/pandas.DataFrame.min.html
     * http://pandas.pydata.org/pandas-docs/stable/reference/api/pandas.DataFrame.max.html
     * http://pandas.pydata.org/pandas-docs/stable/reference/api/pandas.DataFrame.sum.html
     */
    public int operation(String label, Operation op) throws NotAnIntegeException, NotALabelException {
        int res;
        int current_val;
        int index;
        if (!this.datas.get(0).containsKey(label)) {
            throw new NotALabelException("this key is not defined");
        } else if (!(this.datas.get(0).get(label) instanceof Integer)) {
            throw new NotAnIntegeException("the column must contain integers");
        } else {
            res = (Integer) datas.get(0).get(label); //If we initialize with an arbitrary value, she could be wrong
            for (index = 1; index < datas.size(); index++) {
                current_val = (Integer) datas.get(index).get(label);
                res = op.compute(res, current_val);
            }
            return res;
        }
    }

    /**
     * This method returns the average value for this column
     *
     * @param label: column name
     * @return the average of this column
     */
    public float average(String label) throws NotAnIntegeException, NotALabelException {
        float accumulator = 0;
        int size = this.datas.size();
        int index;
        if (!this.datas.get(0).containsKey(label)) {
            throw new NotALabelException("this key is not defined");
        } else if (!(this.datas.get(0).get(label) instanceof Integer)) {
            throw new NotAnIntegeException("the column must contain integers");
        } else {
            for (index = 0; index < size; index++) {
                accumulator += (Integer) datas.get(index).get(label);
            }
            return accumulator / size;
        }
    }


    public static void main(String args[]) throws NotALabelException, NotAnIntegeException {
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


        System.out.println("Create dataframe");
        Dataframe dataframe = new Dataframe(columns);
        System.out.println("Display all dataframe");
        dataframe.displayDataframe();
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
    }
}
