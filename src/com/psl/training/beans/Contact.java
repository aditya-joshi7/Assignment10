package com.psl.training.beans;

import java.io.Serializable;
import java.util.List;

public class Contact implements Serializable,Comparable<Contact> {
 
	private int contactID;
	private String contactName;
	private String emailAddress;
	private List<String> contactNumber;
	
	public Contact() {
		
		
		
	}
	
	
	
	
	@Override
	public String toString() {
		return "Contact [contactID=" + contactID + ", contactName=" + contactName + ", emailAddress=" + emailAddress
				+ ", contactNumber=" + contactNumber + "]";
	}



	public Contact(int contactID, String contactName, String emailAddress, List<String> contactNumber) {
		super();
		this.contactID = contactID;
		this.contactName = contactName;
		this.emailAddress = emailAddress;
		this.contactNumber = contactNumber;
	}
	
	
	public int getContactID() {
		return contactID;
	}
	public void setContactID(int contactID) {
		this.contactID = contactID;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public List<String> getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(List<String> contactNumber) {
		this.contactNumber = contactNumber;
	}



	@Override
	public int compareTo(Contact o) {
		
		if(this.contactID<o.getMovieId()) {
			return 1;
		}
		return 0;
	}



	private int getMovieId() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

	
}
