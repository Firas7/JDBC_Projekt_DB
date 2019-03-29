package de.hsh.dbs2.imdb.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.hsh.dbs.imdb.entities.Genre;

public class GenreFactory {

public GenreFactory() {
		
	}
	static ResultSet rs ;
	
	
	
	public Genre findById(int id) throws SQLException {
		Genre gen = new Genre();	
		String sql = "SELECT * FROM GENRE WHERE GENREID = "+id;
		rs = SQL.sqlAbfrage(sql);
		while(rs.next()) {
			gen.setGenreId(rs.getInt(1));
			gen.setGenre(rs.getString(2));
		}
		return gen;
	}
	
	public int idByGenre(String genre) throws SQLException {	
		String sql = "SELECT GENREID FROM GENRE WHERE GENRE LIKE '"+genre+"'";
		int id = 0;
		rs = SQL.sqlAbfrage(sql);
		while(rs.next()) {
			id = rs.getInt(1);
		}
		return id;
	}
	
	
	public List<Genre> getAll() throws SQLException{
		List <Genre> genres = new ArrayList<Genre>();
		String sql = "SELECT * FROM GENRE ORDER BY GENRE ASC";
		rs = SQL.sqlAbfrage(sql);
		while(rs.next()) {
			Genre gen = new Genre();
			gen.setGenreId(rs.getInt(1));
			gen.setGenre(rs.getString(2));
			genres.add(gen);
		}
		return genres;
	}
	
	public Set<String> getByMovieID(long id) throws SQLException{
		Set <String> genres = new HashSet<String>();
		String sql = "SELECT G.GENRE FROM GENRE G JOIN MOVGEN MG ON MG.GENREID = G.GENREID WHERE MG.MOVIEID = "+id;
		rs = SQL.sqlAbfrage(sql);
		while(rs.next()) {
			genres.add(rs.getString(1));
		}
		return genres;
	}
	
	
}
