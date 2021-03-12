package com.psl.training;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.psl.training.beans.Contact;
import com.psl.training.exception.ContactNotFoundException;
import com.psl.training.services.ContactServices;

public class Tester {

	 
public static void main(String args[]) throws IOException {
	    ContactServices sc=new ContactServices();
	 	List<Contact> c=new ArrayList<>();
	 	
	 	
		String fileContact="C:\\Users\\hp\\eclipse-workspace\\Assignment10\\Contact.txt";
		sc.readContactsFromFile(c,fileContact);
		System.out.println(c);
		
		String fileS="C:\\Users\\hp\\eclipse-workspace\\Assignment10\\Serial.txt";
		//sc.serializeContactDetails(c,fileS);
		//sc.deserializeContact(fileS);
		
		
		Contact co=new Contact(104,"Aman","aman@gmail.com",null);
		sc.addContact(co, c);
		
		System.out.println(c);
		
		Contact co1=new Contact(105,"Aman","aman@gmail.com",null);
		/**try {
			sc.removeContact(co1, c);
		} catch (ContactNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		
		try {
			Set<Contact> s=sc.populateContactFromDb();
			c.addAll(s);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//System.out.println(c);
		
		
		try {
			List<Contact> l=sc.SearchContactByNumber("1",c);
			System.out.println(l);
		} catch (ContactNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sc.addContactNumber(104,"73627", c);
		
		sc.sortContactsByName(c);
		
		System.out.println(c);
		
		
	}
	
	
}
