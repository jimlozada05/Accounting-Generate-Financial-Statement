package table;

public class Login {
	private int login_id;
	private String username,password;
	private byte[] l_image;
	public int getLogin_id() {
		return login_id;
	}
	public void setLogin_id(int login_id) {
		this.login_id = login_id;
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
	public byte[] getL_image() {
		return l_image;
	}
	public void setL_image(byte[] l_image) {
		this.l_image = l_image;
	}
}
