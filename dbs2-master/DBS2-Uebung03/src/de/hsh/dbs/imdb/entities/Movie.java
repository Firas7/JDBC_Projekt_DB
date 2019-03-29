package de.hsh.dbs.imdb.entities;

import java.sql.*;
import de.hsh.dbs2.imdb.util.SQL;
public class Movie {
	
	private Long movieId = null;
	private String title;
	private int year;
	private String type;


	public Movie() {
		
	}
	
	public Movie(long id ,String title, int year, String type) {
		this.setId(id);
		this.setTitle(title);
		this.setYear(year);
		this.setType(type);
		
	}
	public Movie(String title, int year, String type) {
		this.setTitle(title);
		this.setYear(year);
		this.setType(type);
		
	}
	public void setId(Long movId) {
		this.movieId = movId;
	}
	
	public void setTitle(String t) {
		title = t;
	}

	public void setYear(int y) {
		year = y;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	
	public Long getId() {
		return this.movieId;
	}
	
	public String getTitle() {
		return title;
	}

	public int getYear() {
		return year;
	}
	
	public String getType() {
		return this.type;
	}
	
	
	public void printMovie() {
	
		System.out.println("Title: "+ title + " Year:"+year+" Genre:");
	}
	
	
	/* insert a new Movie */ 
	public void insert() throws SQLException {
	
		
			String sql = "INSERT INTO MOVIE " + " VALUES ( "
					+"seq_movie.nextval, "
					+ " '" + this.getTitle() + "', "
					+ this.getYear() + ","
					+" '" + this.getType() + "'"
					+ ")" ;
			
			SQL.sqlEingabe(sql);
			ResultSet rs = SQL.sqlAbfrage("SELECT seq_movie.currval FROM DUAL");
			if(rs.next()) {
				this.setId(Long.valueOf(rs.getInt(1)));
			}
			System.out.println(this.getTitle() + " wurde zur Tabelle Movie hingefuegt! ");
	}
	
	/* delete a Movie */
	public void delete () throws SQLException {
		
		String sql = " DELETE FROM MOVIE WHERE movieid =" + this.getId();
		
			SQL.sqlEingabe(sql);
			MovieGenre.delete(this.getId());
			MovieCharacter.delete(this.getId());
			System.out.println(this.getTitle() +" wurde geloescht! ");
			
	}
	
	public  void delete (int id) throws SQLException {
		this.setId(Long.valueOf(id));
		delete();
		
	}
	
	public void update() throws SQLException {
		
			String sql = " UPDATE MOVIE SET TITLE = "
					+" '" + this.title +"' "
					+", year = " + this.getYear() 
					+", type = '" + this.getType() + "' "
					+"WHERE MOVIEID = " + this.getId();	
			System.out.println(sql);
			SQL.sqlEingabe(sql);
			
			System.out.println(this.getTitle()+" wurde aktualisiert! ");
		
	}
	
	public void update(long id) throws SQLException {
		this.setId(Long.valueOf(id));
		String sql = " UPDATE MOVIE SET TITLE = "
				+" '" + id +"' "
				+"WHERE MOVIEID = " + this.getId();	
		SQL.sqlAbfrage(sql);
		System.out.println(this.getTitle()+" wurde aktualisiert! ");
	}
}
