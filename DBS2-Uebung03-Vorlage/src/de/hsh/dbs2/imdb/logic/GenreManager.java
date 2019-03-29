package de.hsh.dbs2.imdb.logic;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import de.hsh.dbs2.imdb.util.SQL;

public class GenreManager {

	/**
	 * Ermittelt eine vollstaendige Liste aller in der Datenbank abgelegten Genres
	 * Die Genres werden alphabetisch sortiert zurueckgeliefert.
	 * @return Alle Genre-Namen als String-Liste
	 * @throws Exception
	 */
	public List<String> getGenres() throws Exception {
		
		ArrayList<String> genre = new ArrayList<String>();
		String sql = " SELECT GENRE FROM GENRE ORDER BY GENRE ";
		ResultSet rs = SQL.sqlAbfrage(sql);
		while(rs.next()) {
			genre.add(rs.getString(0));
		}
		return genre;
	}

}
