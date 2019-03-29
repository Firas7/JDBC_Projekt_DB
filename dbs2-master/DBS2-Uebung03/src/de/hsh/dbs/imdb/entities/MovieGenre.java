package de.hsh.dbs.imdb.entities;

import java.sql.SQLException;

import de.hsh.dbs2.imdb.util.SQL;

public class MovieGenre {

	private long movId;
	private long genId;
	
	public MovieGenre(long mId, long gId) {
		this.movId = mId;
		this.genId = gId;
	}
	
	public long getMovieId() {
		return movId;
	}
	public void setMovieId(int movId) {
		this.movId = movId;
	}
	public long getGenreId() {
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
			
	}
	public static void delete (long id) throws SQLException {
		
		String sql = " DELETE FROM MOVGEN WHERE MOVIEID = " + id + " or GENREID = " + id ;
		
			SQL.sqlEingabe(sql);
			System.out.println(id + " wurde aus MovieGenre geloescht! ");
	}


	public static void update(long movId , long genId) throws SQLException{
		String sql = " Update movgen set GenreId = " + genId + " where movieId = " + movId;
		SQL.sqlAbfrage(sql);
		
		
	}
}
