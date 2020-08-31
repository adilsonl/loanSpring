package models;


public class User {
	
	private String name;
	private String email;
	private String password;
	private int age;
	private String cpf;
	
	public User() {
		super();
	};
	
	public User(String name, String email, String password, int age, String cpf, String hashID) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.age = age;
		this.cpf = cpf;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	
}
