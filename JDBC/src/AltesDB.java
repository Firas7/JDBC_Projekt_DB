
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;

import DB.DBConnection;
import DB.SQL;


public class AltesDB {

	private static int columns;
	private static ResultSet rs = null;
	private static String sql;
	private static Connection conn = DBConnection.getConnection();
	private static String movieID = "";
	
	
/*
 * Die Main-Methode prüft, ob ein Argument übergeben wurde oder nicht und
 * ruft entsprechende Methoden zur SQL-Abfrage/Ausgabe auf.
 * Anschließend wird die Verbindung zur Datenbank beendet.
 */
	public static void main(String[] args) throws SQLException {

		if(args.length == 0) {								// prüfe, ob ein Kommandozeilenparameter übergeben wurde
			idProblem();										// Aufruf der Methode idProblem()
			
		} else {
			setMovieID(args[0]);							// speichern des Kommandozeilenparameters in movieID;
			idVorhanden();									// Aufruf der Methode idVorhanden()
			
		}
		connectionClose();									// Beendet die Verbindung zur Datenbank		
	}

	
	/*
	 * Schließt die Connection "conn" und beendet somit die Verbindung zur Datenbank
	 */
	private static void connectionClose() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e); // Ausgabe Fehlermeldung
		}
	}
	
	
	
	/*
	 * Festlegen der SQL Abfrage, um movieID und Title aus der Tabelle Movie auszulesen.
	 * Tabellarische Ausgabe aller MovieId's und Filme aus einem ResultSet
	 */
	private static void idVorhanden() throws SQLException {
		LinkedHashSet<String> genre = new LinkedHashSet<String>();			// benötigt, um duplikate von genres zu entfernen
		LinkedHashSet<String> darsteller = new LinkedHashSet<String>();		// benötigt, um duplikate von darstellern zu entfernen
		String kinofilm = "";												// benötigt für die Ausgabe des Filmtitels und Jahr
		
		setSql("SELECT g.GENRE, m.TITLE, m.YEAR, mc.CHARACTER, p.NAME from Movie m JOIN MOVgen MG ON MG.MOVIEID = M.MOVIEID right JOIN GENRE G ON MG.GENREID = G.GENREID\n"
					+ "				right JOIN MOVIECHARACTER MC ON M.MOVIEID = MC.MOVIEID JOIN PERSON P ON MC.PERSONID = P.PERSONID\n"
					+ "				WHERE M.MOVIEID = " + movieID);	// SQL Abfrage, um Genre, Title, Year, Character und Name eines Films zu bekommen
		
		// SQL Abfrage ausführen und Ergebnis im ResultSet speichern
		 rs = SQL.sqlAbfrage(getSql());
		try {
		
			if(!rs.next()) {							// prüfe, ob das ResultSet leer ist
				System.out.println();
				System.out.println("MovieID ungültig. Bitte eine der folgenden MovieID's angeben: "); // Meldung ausgeben, dass ID ungültig ist
				idProblem();							// wenn movieID nicht vorhanden, führe idProblem() aus, um mögliche Filme anzuzeigen
			} else {
				rs.isBeforeFirst();						// Durch die if-Abfrage springt der Cursor des ResultSets einen weiter und muss zurück auf die erste Stelle
				while (rs.next()) {						// Zeilenweises durchgehen des ResultSets "rs"
					/*
					 * Aufbau des ResultSets "rs":
					 * eine oder mehrere Zeilen mit folgenden Spalten:
					 * GENRE | TITLE | YEAR | CHARACTER | NAME
					 *  (1)     (2)     (3)      (4)      (5)
					 */
					genre.add(rs.getString(1));											// speichern und entfernen der Duplikate der Genres in "genre"
					kinofilm = (rs.getString(2) + " (" + rs.getString(3) + ")");		// Speichern des Filmtitels und des Jahrs in "kinolfilm"
					darsteller.add(rs.getString(4) + ": " + rs.getString(5));			// speichern und entfernen der Duplikate der Darsteller in "darsteller"
				}
				
				/*
				 * Zur besseres Ausgabe, werden die LinkedHashSets in ArrayLists übertragen
				 * Grund: Es ist nicht möglich auf einzelne, bestimmte Elemente des HashSets zuzugreifen.
				 * Bedeutet: HashSet.get(2) existiert nicht, ArrayList.get(2) existiert.
				 */
				ArrayList<String> genreArrayList = new ArrayList<String>();
					for (String s : genre) {
						genreArrayList.add(s);
					}

				ArrayList<String> darstellerArrayList = new ArrayList<String>();
					for (String s : darsteller) {
						darstellerArrayList.add(s);
					}

							/*
							 * Ausgabe der Kinofilm Daten nach vorgegebenen Format:
							 * 
							 * Kinofilm: Filmtitel (Year)
							 * Genre: ... | ... | ..
							 * Darsteller:
							 *   Character: Name
							 */
							System.out.println();								// erzeugt Leerzeile
							System.out.println("Kinofilme: " + kinofilm);		// Ausgabe: Kinofilm: Filmtitel (Year)
							System.out.print("Genre: " + genreArrayList.get(0)); // Ausgabe des ersten Genres, vermeidung des Zaunpfahlproblems

							for (int i = 1; i < genreArrayList.size(); i++) {		// Ausgabe weiterer Genres, solange es weitere Einträge in	
								System.out.print(" | " + genreArrayList.get(i)); 	// der ArrayList "genreArrayList" gibt
							}
							System.out.println();								// erzeugt Leerzeile
							System.out.println("Darsteller:");					// Ausgabe: Darsteller:
							for (int i = 0; i < darstellerArrayList.size(); i++) {	// Ausgabe der Darsteller, solange es
								System.out.println("  " + darstellerArrayList.get(i));	// Darsteller in der "darstellerArrayList" gibt
							}			
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e); // Ausgabe Fehlermeldung
		}
	}
	
	
	
	
	/*
	 * Festlegen der SQL Abfrage, um movieID und Title aus der Tabelle Movie auszulesen.
	 * Tabellarische Ausgabe aller MovieId's und Filme aus einem ResultSet
	 */
	
	private static void idProblem() {
		setSql("select movieID, Title from Movie");		// SQL Abfrage für MovieID und Title eines Films speichern
		
		System.out.println();
		System.out.println(" ID | Film");				// 3 Zeilen Ausgabe: Tabellenkopf
		System.out.println("-----------------------------------------------");
		
		try {
			
			/*
			 * Aufbau des ResultSet s "rs":
			 * eine oder mehrere Zeilen mit folgenden Spalten:
			 * movieID | TITLE
			 *   (1)      (2) 
			 */
			while(rs.next()) {												// Solange es Daten im Resultset gibt, 
				System.out.println(rs.getString(1)+" | "+rs.getString(2)); 	// gib Zeilenweise Spalte 1 und 2 aus
			}
								
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	
	
	
	
	/*
	 * Getters und Setters
	 */	

	public static int getColumns() {
		return columns;
	}

	public static void setColumns(int columns) {
		AltesDB.columns = columns;
	}

	public static ResultSet getRs() {
		return rs;
	}

	public static void setRs(ResultSet ergebnis) {
		AltesDB.rs = ergebnis;
	}

	public static String getSql() {
		return sql;
	}

	public static void setSql(String sql) {
		AltesDB.sql = sql;
	}

	public static Connection getConn() {
		return conn;
	}

	public static void setConn(Connection conn) {
		AltesDB.conn = conn;
	}

	public static String getMovieID() {
		return movieID;
	}

	public static void setMovieID(String movieID) {
		AltesDB.movieID = movieID;
	}
	
}


