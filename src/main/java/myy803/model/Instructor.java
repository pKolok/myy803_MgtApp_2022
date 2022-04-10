package myy803.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "instructors")
public class Instructor {
	
	@Id
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	public Instructor() {}
	
	public Instructor(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() { return username; }

	public void setUsername(String username) { this.username = username; }

	public String getPassword() { return password; }

	public void setPassword(String password) { this.password = password; }
	
}
