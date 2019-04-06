package fr.uga.devops.panda.strategy.operation;

/**
 * @author Maxime Deroissart | Defours Ronan
 * @version 1.0
 * @date 04062019
 */
public class Sum implements Operation {

    public static final Sum SUM = new Sum(); //Singleton

    private Sum() {
    }

    /**
     * This method return the sum between these two integer
     *
     * @param i1
     * @param i2
     * @return the sum of these two integer
     */

    @Override
    public int compute(int i1, int i2) {
        return i1 + i2;
    }
}
