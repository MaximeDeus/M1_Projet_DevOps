package fr.uga.devops.panda.strategy.operation;

/**
 * @author Maxime Deroissart | Defours Ronan
 * @version 1.0
 * @date 04062019
 */
public class Max implements Operation {

    public static final Max MAX = new Max(); //Singleton

    private Max() {
    }

    /**
     * This method return the maximum between these two integer
     *
     * @param i1
     * @param i2
     * @return the maximum between these two integer
     */
    @Override
    public int compute(int i1, int i2) {
        return (i1 > i2) ? i1 : i2;
    }
}
