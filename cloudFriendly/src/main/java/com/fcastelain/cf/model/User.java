package main.java.com.fcastelain.cf.model;

public class User {

	// list of variables
	private long id;
	private String name;
	private String mail;
	private String password;

	// list of getters and setters
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() { return this.password; }

	// private the constructor
	private User() { super(); }

	// checkl if the password is good
	public boolean check(String password) {
		return password.equals(this.password);
	}

	// Using builder pattern
	public static Builder getBuilder() {
		return new Builder();
	}

	// the builder of the class
	public static class Builder {
		private User user = new User();

		public Builder() {
			super();
		}

		public Builder id(long id) {
			user.id = id;
			return this;
		}

		public Builder name(String name) {
			user.name = name;
			return this;
		}

		public Builder mail(String mail) {
			user.mail = mail;
			return this;
		}

		public Builder password(String password) {
			user.password = password;
			return this;
		}

		public User build() {
			return user;
		}
	}
	
}
