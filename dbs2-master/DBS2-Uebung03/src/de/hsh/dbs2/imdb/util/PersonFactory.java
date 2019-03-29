package de.hsh.dbs2.imdb.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.hsh.dbs.imdb.entities.MovieCharacter;
import de.hsh.dbs.imdb.entities.Person;
import de.hsh.dbs.imdb.entities.PersonCharacter;

public class PersonFactory {

	public PersonFactory() {

	}

	static ResultSet rs;

	public Person findById(long id) throws SQLException {
		Person pers = new Person();
		String sql = " Select * from Person where Personid = " + id;
		rs = SQL.sqlAbfrage(sql);
		while (rs.next()) {
			pers.setPersonId(rs.getInt(1));
			pers.setName(rs.getString(2));
			pers.setSex(rs.getString(3).charAt(0));
		}
		return pers;
	}

	public List<Person> findByName(String name) throws SQLException {
		List<Person> personen = new ArrayList<Person>();
		String sql = "Select * from Person where upper(name) like upper('%" + name + "%')";
		rs = SQL.sqlAbfrage(sql);
		while (rs.next()) {
			Person pers = new Person();
			pers.setPersonId(rs.getInt(1));
			pers.setName(rs.getString(2));
			pers.setSex(rs.getString(3).charAt(0));
			personen.add(pers);
		}
		return personen;
	}
	
	
	
	public List<PersonCharacter> personCharacterByMovieID(long id) throws SQLException {

		List<PersonCharacter> pc = new ArrayList<PersonCharacter>();
		String sql = "select mc.movCharId, mc.character, mc.alias, mc.position, mc.personid, mc.movieid, p.PERSONID, p.NAME, p.SEX "
				+ "from Person p join moviecharacter mc on p.PERSONID = mc.PERSONID where mc.MOVIEID = " + id; 
		rs = SQL.sqlAbfrage(sql);
		
		while(rs.next()) {
			MovieCharacter mc = new MovieCharacter();
			Person p = new Person();
			
			mc.setId(rs.getInt(1));
			mc.setCharacter(rs.getString(2));
			mc.setAlias(rs.getString(3));
			mc.setPos(rs.getInt(4));
			mc.setPlayerId(rs.getInt(5));
			mc.setMovieId(rs.getInt(6));
			
			p.setPersonId(rs.getInt(7));
			p.setName(rs.getString(8));
			p.setSex(rs.getString(9).charAt(0));
			
			pc.add(new PersonCharacter(p,mc));
		}
		
		return pc;
	}
	/*
	 * False, wenn Person nicht in Datenbank, True, wenn vorhanden
	 */
	public boolean checkPerson(String name) throws SQLException {
		String sql = "Select name from person where name like '" + name + "'";
		return SQL.sqlAbfrage(sql).next();
	}
	
	public int findIdByName(String name) throws SQLException {
		String sql = "Select personID from person where name like '" + name + "'";
		int id = 0;
		ResultSet rs = SQL.sqlAbfrage(sql);
		while(rs.next()) {
			id = rs.getInt(1);
		}
		return id;
	}
	
	public void printAll() throws SQLException {
		// TODO Auto-generated method stub
		String sql = "Select * from Person";
		ResultSet rs = SQL.sqlAbfrage(sql);
		while(rs.next()) {
			System.out.println(rs.getInt(1)+":"+rs.getString(2));
		}
	}
}
