package model;

import dareSetUp.Dare;

import dareSetUp.Score;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class represents a user and holds data about a user, and it's associations with other users and dares.
 */
public class User implements Serializable {
	//The ID should be determined by a User controller class
	private int ID; //FIXME: Should this be an int, string might be more suitable?

	private String name;
	private ImageIcon profilePicture;
	private String email;

	private ArrayList<User> friends;
	private ArrayList<Dare> dares;
	private HashMap<User, Score> savedScore; //FIXME: what is this for? Should the key really ba a user object, wouldn't every key be the same or is the key user the opponent. Should probably be a participant object or maybe dare.

	public User(){}//todo remove me

	public User(String name){}//todo remove me

	public User(String name, ImageIcon profilePicture, String email) {
		this.name = name;
		this.profilePicture = profilePicture;
		this.email = email;

		friends = new ArrayList<User>();
		dares = new ArrayList<Dare>();
		savedScore = new HashMap<User, Score>();
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public ImageIcon getProfilePicture() {
		return profilePicture;
	}

	public String getEmail() {
		return email;
	}

	public void addFriend(User newFriend) {
		if (newFriend != null && !friends.contains(newFriend)) {
			friends.add(newFriend);
		}
	}

	public void removeFriend(User friendToRemove) {
		friends.remove(friendToRemove);
	}

	public ArrayList<User> getFriends() {
		return new ArrayList<>(friends);
	}

	public void addDare(Dare newDare) {
		if (newDare != null && !dares.contains(newDare)) {
			dares.add(newDare);
		}
	}

	public ArrayList<Dare> getDares() {
		return new ArrayList<>(dares);
	}


}
