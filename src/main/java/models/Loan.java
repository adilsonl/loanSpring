package models;

import java.util.HashMap;
import java.util.Map;

public class Loan {
	
	private String hashID;
	private String cpfUser;
	private String status;
	private double value;
	
	public Loan(){
		super();
	}
	
	public Map<String,Object> toMap(){
		Map<String,Object> data = new HashMap<String, Object>();
		data.put("cpfUser",this.cpfUser);
		data.put("status",this.status);
		data.put("value",this.value);		
		return data;
	}
	
	public Loan(String hashID, String cpfUser, String status, double value) {
		super();
		this.hashID = hashID;
		this.cpfUser = cpfUser;
		this.status = status;
		this.value = value;
	}



	public String getHashID() {
		return hashID;
	}
	public void setHashID(String hashID) {
		this.hashID = hashID;
	}
	public String getCpfUser() {
		return cpfUser;
	}
	public void setCpfUser(String cpfUser) {
		this.cpfUser = cpfUser;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	
	
}
