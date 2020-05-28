package entity;

import java.util.Map;

/**
 * @author Kamilla
 * @date 14/04-20
 * @version 1.0
 * This is a enitity class
 * */

public class Score {

    private String uid;
    private String did;
    private Map score;
    private String point;


    public Map getScore() {
        return score;
    }

    public void setScore(Map score) {
        this.score = score;
        System.out.println("Set score from map "+score);
        setPoint();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid=uid;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getPoint() {
        return point;
    }



    public void setPoint() {
        boolean boo = (boolean) score.get("point");
        if (boo == true) {
            point = "true";
        } else {
            point = "false";
        }
        System.out.println("Score "+point);
    }
}