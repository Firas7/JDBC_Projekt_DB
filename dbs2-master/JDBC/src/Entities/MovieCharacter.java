package Entities;

import java.sql.SQLException;
import DB.SQL;

public class MovieCharacter {

	 	private int movCharId;
	    private String character;
	    private String alias;
	    private int position;
	    private int playerId;
	    private int movieId;
	    
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
		public int getMovieId() {
			return this.movieId;
		}
		public void setMovieId(int movieId) {
			this.movieId = movieId;
		}
		
		/* insert a movie character into the table MovieCharacter */
		public void insert() throws SQLException {
			
			String sql = "INSERT INTO MovieCharacter VALUES ( " 
				+ " seq_movieCharacter.nextval" + ","
				+ " '"+this.getCharacter() +"' ," 
				+ this.getAlias() +", "
				+ this.getPos() +"," 
				+ this.getMovieId() + ","
				+ this.getPlayerId() + ")";
			SQL.sqlEingabe(sql);
		}
		
		public static void delete (int id) throws SQLException {
			String sql = " DELETE FROM MOVIECHARACTER WHERE movieid = " + id + " or personid = " + id ;
	
				SQL.sqlEingabe(sql);
				System.out.println(id +" wurde aus MovieCharacter geloescht! ");
		}

}
