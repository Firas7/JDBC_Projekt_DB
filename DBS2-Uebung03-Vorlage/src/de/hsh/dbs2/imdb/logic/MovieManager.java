package de.hsh.dbs2.imdb.logic;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import de.hsh.dbs2.imdb.util.MovieFactory;
import de.hsh.dbs2.imdb.util.SQL;
import de.hsh.dbs.imdb.entities.Movie;
import de.hsh.dbs2.imdb.logic.dto.CharacterDTO;
import de.hsh.dbs2.imdb.logic.dto.MovieDTO;
import de.hsh.dbs2.imdb.util.MovieFactory;

public class MovieManager {

	/**
	 * Ermittelt alle Filme, deren Filmtitel den Suchstring enthaelt.
	 * Wenn der String leer ist, sollen alle Filme zurueckgegeben werden.
	 * Der Suchstring soll ohne Ruecksicht auf Gross/Kleinschreibung verarbeitet werden.
	 * @param search Suchstring. 
	 * @return Liste aller passenden Filme als MovieDTO
	 * @throws Exception
	 */
	public List<MovieDTO> getMovieList(String search) throws Exception {
		// TODO
		
		MovieFactory movies = new MovieFactory();
		List<Movie> list = movies.findByTitle(search);
		List<MovieDTO> movieList = new ArrayList<MovieDTO>();
		
		for(int i = 0 ; i< list.size();i++) {
			MovieDTO mov = new MovieDTO();
			mov.setId(list.get(i).getId());
			mov.setTitle(list.get(i).getTitle());
			mov.setType(list.get(i).getType());
			mov.setYear(list.get(i).getYear());
			movieList.add(mov);
		}
		
		return movieList;
	}

	/**
	 * Speichert die uebergebene Version des Films neu in der Datenbank oder aktualisiert den
	 * existierenden Film.
	 * Dazu werden die Daten des Films selbst (Titel, Jahr, Typ) beruecksichtigt,
	 * aber auch alle Genres, die dem Film zugeordnet sind und die Liste der Charaktere
	 * auf den neuen Stand gebracht.
	 * @param movie Film-Objekt mit Genres und Charakteren.
	 * @throws Exception
	 */
	public void insertUpdateMovie(MovieDTO movieDTO) throws Exception {
		// TODO
		
		ResultSet rs = null;
		
		if(SQL.sqlAbfrage("Select movieid from movie where movieid = " + movieDTO.getId()).next()) {
			
			
		}else {
			
		
		}
		
	}

	
	
	/**
	 * Loescht einen Film aus der Datenbank. Es werden auch alle abhaengigen Objekte geloescht,
	 * d.h. alle Charaktere und alle Genre-Zuordnungen.
	 * @param movie
	 * @throws Exception
	 */
	public void deleteMovie(long movieId) throws Exception {
		// TODO Auto-generated method stub
		String sql = " DELETE FROM MOVIE WHERE movieid =" + movieId;
		
		/* delete all Movies and Genre from table MovGen */
		String sql1 = " DELETE FROM MOVGEN WHERE MOVIEID = " + movieId ;
		SQL.sqlEingabe(sql1);
		
		/* delete all Character from this MovieID */
		String sql2 = " DELETE FROM MOVIECHARACTER WHERE movieid = " + movieId ;
		SQL.sqlEingabe(sql2);
		
		
		/* at the end delete this MovieID */
		SQL.sqlEingabe(sql);
		System.out.println(movieId+" wurde geloescht! ");
	}

	
	public MovieDTO getMovie(long movieId) throws Exception {
		// TODO Auto-generated method stub
		
		MovieFactory movFac = new MovieFactory();
		Movie mov = movFac.findById(movieId);
		MovieDTO movie = new MovieDTO();
		System.out.println(mov.getTitle());
		movie.setId(mov.getId());
		movie.setTitle(mov.getTitle());
		movie.setType(mov.getType());
		movie.setYear(mov.getYear());
	
		return movie;
	}

}
