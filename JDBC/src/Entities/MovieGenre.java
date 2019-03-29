package Entities;

import java.sql.SQLException;

import DB.SQL;

public class MovieGenre {

	private int movId;
	private int genId;
	
	
	
	public int getMovieId() {
		return movId;
	}
	public void setMovieId(int movId) {
		this.movId = movId;
	}
	public int getGenreId() {
		return genId;
	}
	public void setGenreId(int genId) {
		this.genId = genId;
	}
	
	
	/* insert a movie and a genre together */
	public void insert() throws SQLException {
		
		String sql = "INSERT INTO MOVGEN " + " VALUES ("
		+ this.getMovieId() +"," 
		+ this.getGenreId() + ")";
		
		SQL.sqlEingabe(sql);
		System.out.println("Ein neuer Datensatz wurde zur Tabelle MovGen hingefuegt!");
			
	}
	public static void delete (long id) throws SQLException {
		
		String sql = " DELETE FROM MOVGEN WHERE MOVIEID = " + id + " or GENREID = " + id ;
		
			SQL.sqlEingabe(sql);
			System.out.println(id + " wurde aus MovieGenre geloescht! ");
			
			
	}

}
