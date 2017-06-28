package hpe.demo.auth.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Auth {
	@Id
	@GeneratedValue
	private int id;
	@Column(nullable = false)
	private int appkey;
	@Column(nullable = false)
	private String appname;
	@Column(nullable = false)
	private String username;
	@Column(nullable = false)
	private String password;

	public Auth() {
	}

	public Auth(int appkey, String appname, String username, String password) {
		this.appkey = appkey;
		this.appname = appname;
		this.username = username;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAppkey() {
		return appkey;
	}

	public void setAppkey(int appkey) {
		this.appkey = appkey;
	}

	public String getAppname() {
		return appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
