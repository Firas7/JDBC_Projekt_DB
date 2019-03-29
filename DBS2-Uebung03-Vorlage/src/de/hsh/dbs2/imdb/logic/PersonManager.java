package de.hsh.dbs2.imdb.logic;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import de.hsh.dbs2.imdb.util.SQL;

public class PersonManager {

	/**
	 * Liefert eine Liste aller Personen, deren Name den Suchstring enthaelt.
	 * @param text Suchstring
	 * @return Liste mit passenden Personennamen, die in der Datenbank eingetragen sind.
	 * @throws Exception
	 */
	public List<String> getPersonList(String text) throws Exception {
		
		String sql = " ";
		ArrayList<String> person = new ArrayList<String>();
		ResultSet rs = SQL.sqlAbfrage(sql);
		while(rs.next()) {
			person.add(rs.getString(0));
		}
		return person;
	}

}
