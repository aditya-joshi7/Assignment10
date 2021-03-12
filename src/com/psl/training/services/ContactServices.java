package com.psl.training.services;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.psl.training.DBConnectionUtil;
import com.psl.training.beans.Contact;

import com.psl.training.exception.ContactNotFoundException;



public class ContactServices {

	
	public class SortContactByName implements Comparator<Contact>{

		@Override
		public int compare(Contact o1, Contact o2) {
			return  o1.getContactName().compareTo(o2.getContactName());
			
			
		}
		
		
		
		
	}
	
	
	
	public void addContact(Contact contact,List<Contact> contacts) {
		
		contacts.add(contact);
		
	}
	
	
  public  void removeContact(Contact contact, List<Contact> contacts) throws ContactNotFoundException{
	  
	  boolean status =contacts.remove(contact);
	  
	  if(!status) {
		  throw new ContactNotFoundException();
	  }
	
  }
  
  public Contact searchContactByName(String name, List<Contact> contact) throws ContactNotFoundException{
	
	  boolean found=false;
	  for(Contact c:contact) {
		  if(c.getContactName().equals(name)) {
			  found=true;
			  return c;
		  }
		  
	  }
	  
	  if(!found) {
		  throw new ContactNotFoundException();
	  }
	  
	  return null;
  }
  
  
  
  public List<Contact> SearchContactByNumber(String number, List<Contact> contact) throws ContactNotFoundException{
	  List<Contact> cList=new ArrayList<>();
	  
	  
	  for(Contact c:contact) {
		  List<String> numberList=c.getContactNumber();
		  if(numberList==null) {
			  continue;
		  }
		  
		  for(String s:numberList) {
			  if(s.contains(number)) {
				  if(cList.contains(c)) {
					 continue;
				  }
				  else {
					  cList.add(c);
					  
				  }  
				  
			  }  
			  
		  }
		    
	  }
	  
	  
	  if(cList.size()==0) {
		  throw new ContactNotFoundException();
		  
	  }
	  
	  return cList;
  }
  
  
  
  
 public void addContactNumber(int contactId, String contactNo, List<Contact> contacts) {
	 
	 for(Contact c:contacts) {
		 
		 
		 if(contactId==c.getContactID()) {
			 if(c.getContactNumber()==null) {
				 List<String> arr=new ArrayList<>();
				 arr.add(contactNo);
				 c.setContactNumber(arr);
				 
			 }
			 else {
				 c.getContactNumber().add(contactNo);
				 
			 }
			 
			
			 
		 }
		 
		 
	 }
	 
	 
 }
  
  
 public void sortContactsByName(List<Contact> contacts) {
	 
	 Collections.sort(contacts,new SortContactByName());
	 
	 
 }
 
 
public  void readContactsFromFile(List<Contact> contacts, String fileName) throws IOException {
	FileReader fr=new FileReader(fileName);
	BufferedReader br=new BufferedReader(fr);   
	//sample file
	//1,Rohan,rohan@gmail.com,1234565
	String line =br.readLine();
	
	while(line!=null){
		
		String []details=line.split(",");
		if(details.length==1) {
			break;
		}
		
		int id=Integer.parseInt(details[0]);
		String name=details[1];
		String email=details[2];
		List<String> temp=new ArrayList<>();
		for(int i=3;i<details.length;i++) {
			temp.add(details[i]);
		}
		
		Contact c=new Contact(id,name,email,temp);
		
		contacts.add(c);
		
		
		
		line=br.readLine();
		
		
	}
	
	
	
}

public void serializeContactDetails(List<Contact> contacts , String fileName) {
	
	try
    {
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(contacts);
        oos.close();
        fos.close();
    } 
    catch (IOException ioe) 
    {
        ioe.printStackTrace();
    }
	
	
}


public   List<Contact> deserializeContact(String filename){
	
	List<Contact> mList;
	try
    {
        FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream ois = new ObjectInputStream(fis);

        mList = (ArrayList) ois.readObject();

        ois.close();
        fis.close();
    } 
    catch (IOException ioe) 
    {
        ioe.printStackTrace();
        return null;
    } 
    catch (ClassNotFoundException c) 
    {
        System.out.println("Class not found");
       c.printStackTrace();
        return null;
    }
	
	
	System.out.println(mList);
     
	
	return mList;
	
	
	
}

public Set<Contact> populateContactFromDb() throws SQLException{
	
	Set<Contact> s=new HashSet<>();
	
	Connection cn=DBConnectionUtil.getConnection();
	Statement smt=cn.createStatement();
	
	String q="Select * from contact_tbl";
	ResultSet rs=smt.executeQuery(q);
	
	if(rs.next()){ 
		do{
			
			int id=Integer.parseInt(rs.getString(1));
			String name=rs.getString(2);
			String email=rs.getString(3);
			String contact[] = null;
			List<String> ls=new ArrayList<>();
			if(!(rs.getString(4)==null)) {
				contact=rs.getString(4).split(",");
				
				for(int i=0;i<contact.length;i++) {
					ls.add(contact[i]);
				}
			}
			
			
			
			
			
			Contact c=new Contact(id,name,email,ls);
			s.add(c);
			
		//System.out.println(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4));
		}while(rs.next());
	}
	else{
		System.out.println("Record Not Found...");
	}
	cn.close();
	
	
	
	
	return s;
}


boolean addContacts(List<Contact> existingContact,Set<Contact> newContacts) {
	
	boolean status=existingContact.addAll(newContacts);
	return status;
}



 
	
}
