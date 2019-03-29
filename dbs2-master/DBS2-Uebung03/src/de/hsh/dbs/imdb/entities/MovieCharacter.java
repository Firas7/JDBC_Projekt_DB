package de.hsh.dbs.imdb.entities;

import java.sql.SQLException;
import de.hsh.dbs2.imdb.util.SQL;
public class MovieCharacter {

	 	private int movCharId;
	    private String character;
	    private String alias;
	    private int position;
	    private int playerId;
	    private long movieId;
	    
		public int getId() {
			return this.movCharId;
		}
		public void setId(int movCharId) {
			this.movCharId = movCharId;
		}
		public String getCharacter() {
			return this.character;
		}
		public void setCharacter(String character) {
			this.character = character;
		}
		public String getAlias() {
			return this.alias;
		}
		public void setAlias(String alias) {
			this.alias = alias;
		}
		public int getPos() {
			return this.position;
		}
		public void setPos(int position) {
			this.position = position;
		}
		public int getPlayerId() {
			return this.playerId;
		}
		public void setPlayerId(int personId) {
			this.playerId = personId;
		}
		public long getMovieId() {
			return this.movieId;
		}
		public void setMovieId(long movieId) {
			this.movieId = movieId;
		}
		
		/* insert a movie character into the table MovieCharacter */
		public void insert() throws SQLException {
			
			String sql = "INSERT INTO MovieCharacter VALUES ( " 
				+ " seq_movieCharacter.nextval" + ","
				+ " '"+this.getCharacter() +"' ," 
				+"'"+ this.getAlias() +"' , "
				+ this.getPos() +"," 
				+ this.getMovieId() + ","
				+ this.getPlayerId() + ")";
			System.out.println(sql);
			SQL.sqlEingabe(sql);
		}
		
		public static void delete (long id) throws SQLException {
			String sql = " DELETE FROM MOVIECHARACTER WHERE movieid = " + id + " or personid = " + id ;
	
				SQL.sqlEingabe(sql);
				System.out.println(id +" wurde aus MovieCharacter geloescht! ");
		}
		
		/*
		 * Vielleicht nutzlos ?
		 */
		public void update() throws SQLException {
			String sql = "select  * from Moviecharacter where movieid = " + this.getMovieId() + " and character = '" + this.getCharacter() + "' ";
			if(!SQL.sqlAbfrage(sql).next()) {
				System.out.println(sql);
				insert();
				
			}else {
			String sql2 = "UPDATE MOVIECHARACTER SET CHARACTER = '"+this.getCharacter()+"', ALIAS = '"+this.getAlias()+"', "
					+ "POSITION ="+this.getPos()+" , MOVIEID = "+this.getMovieId()+", PERSONID = "+this.getPlayerId()+" WHERE MOVCHARID = "+ this.getId();	
			System.out.println(sql2);
			SQL.sqlEingabe(sql2);
			}
	}
	
}
