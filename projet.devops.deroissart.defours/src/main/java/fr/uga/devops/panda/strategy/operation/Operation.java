package fr.uga.devops.panda.strategy.operation;

/**
 * This interface is used to define Datragrame strategy i.e when
 * we want to make some computation on a column (min, max, sum, etc.)
 * <p>
 * In this version, these features can only return numeral
 * other version could make it more generic
 *
 * @author Maxime Deroissart | Defours Ronan
 * @version 1.0
 */
public interface Operation {
    /**
     * This method return the operation between these two integer
     *
     * @param i1 the first integer
     * @param i2 the second integer
     * @return the result of the operation between these two integer
     */
    int compute(int i1, int i2);
}
