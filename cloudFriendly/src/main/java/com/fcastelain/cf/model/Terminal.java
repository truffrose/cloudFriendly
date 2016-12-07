package com.fcastelain.cf.model;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Terminal {

	// list of variables
	public static final String ROOT_PATH = "/home/fcastelain/cloudFriendly/data/";
	private User currentUser;
	private Path path;

	// list of constructors
	public Terminal() {
		this(null, null);
	}
	public Terminal(Path path) {
		this(null, path);
	}
	public Terminal(User user, Path path) {
		super();
		this.currentUser = user;
		this.path = path;
	}

	// getters and setters
	public Path getPath() {
		return this.path;
	}
	public Path getFullPath() { return Paths.get(Terminal.ROOT_PATH + this.currentUser.getId() + "/" + this.getPath()); }
	public void setPath(Path path) {
		this.path = path;
	}
	public void setPath(String path) {
		this.path = Paths.get(path).normalize();
	}
	public User getCurrentUser() {
		return currentUser;
	}
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
    public void addToPath(String str) {
		this.path = Paths.get( this.getPath() + "/" + str).normalize();
    }
}
