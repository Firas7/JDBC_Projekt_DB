import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DB.SQL;
import Entities.Movie;

public class MovieFactory {

	
	public MovieFactory() {
		
	}
	static ResultSet rs ;
	public Movie findById(long id) throws SQLException {
		Movie mov= null;		
		String sql = " Select * from Movie where Movieid = " + id;
		rs = SQL.sqlAbfrage(sql);
		while(rs.next()) {
			mov = new Movie(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4).charAt(0));
		}
		return mov;
	}
	
	public ArrayList<Movie> findByTitle(String title) throws SQLException{
		ArrayList <Movie> movies = new ArrayList<Movie>();
		String sql = "Select * from Movie where title like '%" + title + "%' ";
		rs = SQL.sqlAbfrage(sql);
		while(rs.next()) {
			Movie mov = new Movie(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4).charAt(0));
			movies.add(mov);
		}
		
		return movies;
	}
	
	
}
