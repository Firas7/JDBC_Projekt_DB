package Test;

import java.sql.SQLException;

import DB.DBConnection;
import Entities.Movie;

public class TestInsert {
	
	public static void main(String[] args) throws SQLException {
		testInsert();
	}
	public static void testInsert() throws SQLException {
		boolean ok = false;
		
		try {
				/*Person person = new Person();
				person.setName("Kasadaarl Tester");
				person.setSex('M');
				person.insert();
				System.out.println("Id der Person : "+person.getId()); */
			
			
		
				Movie movie = new Movie();
				movie.setTitle("Dise tolle Komoedie");
				movie.setYear(2012);
				movie.setType('C');
				movie.insert();
				movie.delete();
				System.out.println("Id der Movie : "+movie.getId());
				
				/*MovieCharacter chr = new MovieCharacter();
				chr.setCharacter("Haupstrrsoldle");
				chr.setAlias(null);
				chr.setPos(1);
				chr.setMovieId(movie.getId());
				chr.setPlayerId(person.getId()); // 140
			
				chr.insert();
		
		
				Genre genre = new Genre();
				genre.setGenre("Uandklar");
				genre.insert();
		
		
				MovieGenre movieGenre = new MovieGenre();
				movieGenre.setGenreId(genre.getId());
				movieGenre.setMovieId(movie.getId());
				movieGenre.insert();*/
				DBConnection.getConnection().commit();
				ok = true;
		} finally {
		if (!ok)
			DBConnection.getConnection().rollback();
		}
}

}