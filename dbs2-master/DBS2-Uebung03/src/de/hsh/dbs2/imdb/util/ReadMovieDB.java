package de.hsh.dbs2.imdb.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import de.hsh.dbs.imdb.entities.*;

public class ReadMovieDB {

	private static String movieID;
	private static String movieTitle;
	private static String sql;
	private static ArrayList<Movie> movieList = null;


	public static boolean checkID(String id) throws SQLException {
		
		return SQL.sqlAbfrage("select movieid from movie where movieid = " + id).next(); 
	}
	private static void idProblem() throws SQLException {
		setSql("select movieID, Title from Movie");
		ResultSet rs = SQL.sqlAbfrage(sql);
		System.out.println();
		System.out.println(" ID | Film");
		System.out.println("-----------------------------------------------");

		try {

			/*
			 * Aufbau des ResultSet s "rs": eine oder mehrere Zeilen mit folgenden Spalten:
			 * movieID | TITLE (1) (2)
			 */
			while (rs.next()) {
				System.out.println(rs.getString(1) + " | " + rs.getString(2));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void movieAusgabe() throws SQLException {
		for (int i = 0; i < movieList.size(); i++) {
			ArrayList<String> genre = new ArrayList<String>();
			ArrayList<String> darsteller = new ArrayList<String>();

			try {

				setSql("select g.genre from MOVGEN MG JOIN GENRE G on MG.GENREID = G.GENREID WHERE MG.MOVIEID = "
						+ movieList.get(i).getId());
				ResultSet rsGenre = SQL.sqlAbfrage(getSql());

				setSql("SELECT MC.CHARACTER, P.NAME FROM MOVIECHARACTER MC JOIN PERSON P ON MC.PERSONID = P.PERSONID WHERE MC.MOVIEID = "
						+ movieList.get(i).getId());
				ResultSet rsDarsteller = SQL.sqlAbfrage(getSql());
				
				if (movieList.size() == 0 || movieList == null) { // prüfe, ob das ResultSet leer ist
					System.out.println();
					System.out.println("Movie ungültig. Bitte eine der folgenden MovieID's angeben: "); // Meldung
																										// ausgeben,
																										// dass ID
																										// ungültig ist
					idProblem(); // wenn movieID nicht vorhanden, führe idProblem() aus, um mögliche Filme
									// anzuzeigen
				} else {

					rsGenre.isBeforeFirst();
					rsDarsteller.isBeforeFirst();

					while (rsGenre.next()) { // Zeilenweises durchgehen des ResultSets "rs"
						genre.add(rsGenre.getString(1));
					}

					while (rsDarsteller.next()) {
						darsteller.add(rsDarsteller.getString(1) + ": " + rsDarsteller.getString(2));
					}
	

					/*
					 * Ausgabe der Kinofilm Daten nach vorgegebenen Format:
					 * 
					 * Kinofilm: Filmtitel (Year) Genre: ... | ... | .. Darsteller: Character: Name
					 */

					System.out.println();
					System.out.println("Kinofilm: " + movieList.get(i).getTitle() + " (" + movieList.get(i).getYear() + ")");
					System.out.print("Genre: ");
					if (genre.size() > 0)
						System.out.print(genre.get(0));

					for (int j = 1; j < genre.size(); j++) {
						System.out.print(" | " + genre.get(j));
					}
					System.out.println();
					System.out.println("Darsteller:");
					for (int j = 0; j < darsteller.size(); j++) {
						System.out.println("  " + darsteller.get(j));
					}
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e);
			}
		}
	}

	public static String getMovieID() {
		return movieID;
	}

	public static void setMovieID(String movieID) {
		ReadMovieDB.movieID = movieID;
	}

	public static String getMovieTitle() {
		return movieTitle;
	}

	public static void setMovieTitle(String movieTitle) {
		ReadMovieDB.movieTitle = movieTitle;
	}

	public static String getSql() {
		return sql;
	}

	public static void setSql(String sql) {
		ReadMovieDB.sql = sql;
	}

}
