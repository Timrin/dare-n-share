package dareSetUp;

import model.User;

import java.io.Serializable;

/**
 * This class should maybe be abstract? todo if so.
 * Goal dare is a dare, which the user wins if he/she is first to finish/achieve the goal of the dare.
 * Implements Dare, because every dare has a start and a stop.
 */

public class GoalDare extends Dare  {
    private Score score;
    private User user;

    public GoalDare(User user,User user1){
        super(user1,user);

    }



}
