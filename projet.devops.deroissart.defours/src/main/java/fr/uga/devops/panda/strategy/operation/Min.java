package fr.uga.devops.panda.strategy.operation;

/**
 * @author Maxime Deroissart | Defours Ronan
 * @version 1.0
 */
public class Min implements Operation {

    public static final Min MIN = new Min(); //Singleton

    private Min() {
    }

    /**
     * This method return the minimum between these two integer
     *
     * @param i1 the first integer
     * @param i2 the second integer
     * @return the minimum between these two integer
     */
    @Override
    public int compute(int i1, int i2) {
        return (i1 < i2) ? i1 : i2;
    }
}
