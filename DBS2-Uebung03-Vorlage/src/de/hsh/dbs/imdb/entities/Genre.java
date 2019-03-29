package de.hsh.dbs.imdb.entities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import de.hsh.dbs2.imdb.util.*;

public class Genre {

	private int genreId;
	private String genre;
	
	public Genre() {
		
	}

	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	
	public int getId() {
		return this.genreId;
	}
	public String getGenre() {
		return this.genre;
	}
	
	/* insert a new Genre*/
	public void insert() throws SQLException {
		
		String sql = "INSERT INTO GENRE" + " VALUES ("
		+"seq_genre.nextval,"
		+" '" + this.getGenre() + "' )";
		
	SQL.sqlAbfrage(sql);
			System.out.println(this.getGenre() + " wurde zur Tabelle Genre hingefuegt!");
			ResultSet rs = SQL.sqlAbfrage("SELECT seq_genre.currval FROM DUAL");
			
			if(rs.next()) {
				this.setGenreId(rs.getInt(1)); 
			}
	}
	
	/* delete a Genre */
	public void delete () {
		
		String sql = " DELETE FROM GENRE WHERE genre = '" + this.getGenre() + "' ";
		
		try {
			Connection conn = DBConnection.getConnection();
			Statement stmt = conn.createStatement();
			MovieGenre.delete(this.getId());
			stmt.execute(sql);
			System.out.println(this.getGenre()+ " wurde geloescht! ");
			
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public void update() {
		
		
		try {
			Connection conn = DBConnection.getConnection();
			Statement stmt = conn.createStatement();
			String sql = "UPDATE GENRE SET GENRE = "
					+ "' " + this.getGenre() + "' "
					+ "WHERE GENREID = " + this.getId() ;	
			stmt.execute(sql);
			
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	
}
