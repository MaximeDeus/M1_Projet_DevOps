package fr.uga.devops.panda;


import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import fr.uga.devops.panda.exception.BadValueException;
import fr.uga.devops.panda.exception.NotALabelException;
import fr.uga.devops.panda.exception.NotAnIntegerException;
import fr.uga.devops.panda.strategy.display.Display_Itf;
import fr.uga.devops.panda.strategy.operation.Operation;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

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
     *
     * @param columns the dictionary
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
                    HashMap<String, Object> line = new HashMap<>();
                    line.put(columnName, columnValue);
                    datas.add(line);
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
     * Méthode 2, a CSV file is parsed using OpenCSV library
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
     * @param pathName the path of the csv file (relative path = projet.devops.deroissart.defours)
     * @throws IOException if file error
     */

    public Dataframe(String pathName) throws IOException {
        datas = new ArrayList<>();
        FileReader filereader = new FileReader(pathName);
        CSVReader csvReader = new CSVReaderBuilder(filereader).build(); //Parser CSV
        String[] labels = csvReader.readNext();
        List<String[]> lines = csvReader.readAll();
        int indexLine = 0;
        int indexColumn = 0;
        for (String[] row : lines) {
            datas.add(new HashMap<String, Object>());
            for (String cell : row) {
                datas.get(indexLine).put(labels[indexColumn], cell); //Fill datas
                indexColumn++;
            }
            indexColumn = 0;
            indexLine++;
        }
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
     * @throws BadValueException if incorrect number of line
     */
    public void displayDataframe(Display_Itf displayStrategy, int nb_lines) throws BadValueException {
        if (nb_lines < 1 || nb_lines > datas.size()) {
            throw new BadValueException("bad value");
        }
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
     * @param begin begin of interval
     * @param end end of interval
     * @throws BadValueException if incorrect interval
     *
     * @return a Dataframe
     */
    public Dataframe iloc(int begin, int end) throws BadValueException {
        if (begin > end || begin < 0 || datas.size() <= end) {
            throw new BadValueException("begin must be lesser than end");
        }

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
     * @param labels list of columns names
     * @throws NotALabelException if column doesn't exist
     * @return a Dataframe
     */
    public Dataframe loc(List<String> labels) throws NotALabelException {

        for (String label : labels) {
            if (!datas.get(0).containsKey(label)) {
                throw new NotALabelException("Unknown column");
            }
        }

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
     * @throws NotALabelException if column doesn't exist
     * @throws NotAnIntegerException if values of the column are not integer
     * @return the operation of this column
     * <p>
     * For more informations see :
     * <p>
     * http://pandas.pydata.org/pandas-docs/stable/reference/api/pandas.DataFrame.min.html
     * http://pandas.pydata.org/pandas-docs/stable/reference/api/pandas.DataFrame.max.html
     * http://pandas.pydata.org/pandas-docs/stable/reference/api/pandas.DataFrame.sum.html
     */
    public int operation(String label, Operation op) throws NotAnIntegerException, NotALabelException {
        int res;
        int current_val;
        int index;
        if (!this.datas.get(0).containsKey(label)) {
            throw new NotALabelException("this key is not defined");
        } else if (!(this.datas.get(0).get(label) instanceof Integer)) {
            throw new NotAnIntegerException("the column must contain integers");
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
     * @throws NotALabelException if column doesn't exist
     * @throws NotAnIntegerException if values of the column are not integer
     * @return the average of this column
     */
    public float average(String label) throws NotAnIntegerException, NotALabelException {
        float accumulator = 0;
        int size = this.datas.size();
        int index;
        if (!this.datas.get(0).containsKey(label)) {
            throw new NotALabelException("this key is not defined");
        } else if (!(this.datas.get(0).get(label) instanceof Integer)) {
            throw new NotAnIntegerException("the column must contain integers");
        } else {
            for (index = 0; index < size; index++) {
                accumulator += (Integer) datas.get(index).get(label);
            }
            return accumulator / size;
        }
    }
}
