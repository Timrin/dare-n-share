package Converter;

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

    public Map getScore() {
        return score;
    }

    public void setScore(Map score) {
        this.score = score;
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

}