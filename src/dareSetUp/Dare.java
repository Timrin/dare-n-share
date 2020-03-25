package dareSetUp;
/**
 * Should maybe be an abstract class, rather than an interface. todo if so.
 * Every dare has a start and a stop.
 * */

public interface Dare {
    // int ID; // hvorfor skal den ha en verdi?

    boolean start();
    boolean stop();
    User getInstigator();
    User getChallenged();


}
