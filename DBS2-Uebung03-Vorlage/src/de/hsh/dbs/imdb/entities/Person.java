package de.hsh.dbs.imdb.entities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import de.hsh.dbs2.imdb.util.*;

public class Person {

	private int personId;
	private String personName;
	private char sex;
	
	
	
	public Person() {
	
	}
	
	
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	public String getName() {
		return this.personName;
	}
	public void setName(String personName) {
		this.personName = personName;
	}
	public char getSex() {
		return this.sex;
	}
	public void setSex(char sex) {
		this.sex = sex;
	}
	
	public int getId() {
		return this.personId;
	}
	
	/* insert a new person */
	public void insert() throws SQLException {
		
		String sql = "INSERT INTO PERSON " + "VALUES (" 
		+ "seq_person.nextval,"
		+" '"+this.getName()+"' ,"
		+ " '"+this.getSex()+"' )";
		
	
			
			SQL.sqlAbfrage(sql);
			ResultSet rs = SQL.sqlAbfrage("SELECT seq_person.currval FROM DUAL");
			if(rs.next()) {
				this.setPersonId(rs.getInt(1)); 
			}
			
			System.out.println(this.getName() + " wurde zur Tabelle Person hingefuegt! ");	
	}
	
	/* delete person 
	 * wurde static gemacht, um zu testen!
	 * */
	public void delete () {
		
		String sql = " DELETE FROM PERSON WHERE Name = '" + this.personName + "' ";
		
		try {
			Connection conn = DBConnection.getConnection();
			Statement stmt = conn.createStatement();
			MovieCharacter.delete(this.getId());		
			stmt.execute(sql);
			System.out.println(this.getName() + " wurde geloescht! ");
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public void update() {
			
			
			try {
				Connection conn = DBConnection.getConnection();
				Statement stmt = conn.createStatement();
				String sql = " UPDATE PERSON SET NAME= "
						+" '" + this.getName() +"' "
						+"WHERE PERSONID = " + this.getId();	
				stmt.execute(sql);
				
			}catch(SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
	
}
