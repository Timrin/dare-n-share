package model;

import dareSetUp.Score;

import java.io.Serializable;

/**
 * This class represents a users participation in a dare, it holds data associated to a specific user in a specific dare.
 */
public class Participant implements Serializable {
	private User user;

	private Score score;
	private Dare dare;

	public Participant(User user, Dare dare) {
		this.user = user;
		this.dare = dare;

		//Initialize score, FIXME: Score is an interface, workarounds?
	}

	public User getUser() {
		return user;
	}

	//Should a parameter be passed, or does the method determine how score should be adjusted?
	public void addToScore() {
		//Add to score, FIXME: how will adding to a score work?
		//score.addToScore();
	}

	public Score getScore() {
		return score;
	}

	public Dare getDare() {
		return dare;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setScore(Score score) {
		this.score = score;
	}

	public void setDare(Dare dare) {
		this.dare = dare;
	}
}
