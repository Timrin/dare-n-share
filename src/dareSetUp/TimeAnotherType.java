package dareSetUp;
/**
 * TimeAnotherType expects another type of score. The score is not a boolean, and the user can achieve more points.
 * It is a time dare
 * @see TimeDare
 * */

public class TimeAnotherType extends TimeDare {
    private AnotherTypeOfScore anotherTypeOfScore;

   public TimeAnotherType(){
       super();
   }

    @Override
    public User getInstigator() {
        return null;
    }

    @Override
    public User getChallenged() {
        return null;
    }
}
