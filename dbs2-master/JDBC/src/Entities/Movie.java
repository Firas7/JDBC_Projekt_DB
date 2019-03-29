package Entities;

import java.sql.*;
import DB.SQL;

public class Movie {
	
	private int movieId;
	private String title;
	private int year;
	private char type;


	public Movie() {
		
	}
	
	public Movie(int id ,String title, int year, char type) {
		this.setId(id);
		this.setTitle(title);
		this.setYear(year);
		this.setType(type);
		
	}
	public Movie(String title, int year, char type) {
		this.setTitle(title);
		this.setYear(year);
		this.setType(type);
		
	}
	public void setId(int movId) {
		this.movieId = movId;
	}
	
	public void setTitle(String t) {
		title = t;
	}

	public void setYear(int y) {
		year = y;
	}
	
	public void setType(char type) {
		this.type = type;
	}
	
	
	public int getId() {
		return this.movieId;
	}
	
	public String getTitle() {
		return title;
	}

	public int getYear() {
		return year;
	}
	
	public char getType() {
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
				this.setId(rs.getInt(1));
			}
			System.out.println(this.getTitle() + " wurde zur Tabelle Movie hingefuegt! ");
			
		
	}
	
	/* delete a Movie */
	public void delete () throws SQLException {
		
		String sql = " DELETE FROM MOVIE WHERE movieid =" + this.getId();
		
			SQL.sqlEingabe(sql);
			MovieGenre.delete(this.getId());
			MovieCharacter.delete(this.getId());
			MovieGenre.delete(this.getId());
			System.out.println(this.getTitle() +" wurde geloescht! ");
			
	}
	
	public  void delete (int id) throws SQLException {
		this.setId(id);
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
	
	public void update(int id) throws SQLException {
		this.setId(id);
		String sql = " UPDATE MOVIE SET TITLE = "
				+" '" + id +"' "
				+"WHERE MOVIEID = " + this.getId();	
		SQL.sqlAbfrage(sql);
		System.out.println(this.getTitle()+" wurde aktualisiert! ");
	}
}
