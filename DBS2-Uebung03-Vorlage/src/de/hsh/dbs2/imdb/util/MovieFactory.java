package de.hsh.dbs2.imdb.util;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.hsh.dbs.imdb.entities.*;

public class MovieFactory {

	
	public MovieFactory() {
		
	}
	static ResultSet rs ;
	public Movie findById(long id) throws SQLException {
		Movie mov= null;		
		String sql = " Select * from Movie where Movieid = " + id;
		System.out.println("Da");
		rs = SQL.sqlAbfrage(sql);
		while(rs.next()) {
			mov = new Movie(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4));
		}
		return mov;
	}
	
	public List<Movie> findByTitle(String title) throws SQLException{
		List <Movie> movies = new ArrayList<Movie>();
		String sql = "Select * from Movie where title like '%" + title + "%' ";
		rs = SQL.sqlAbfrage(sql);
		while(rs.next()) {
			Movie mov = new Movie(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4));
			movies.add(mov);
			
		}
		return movies;
	}
	
	
}
