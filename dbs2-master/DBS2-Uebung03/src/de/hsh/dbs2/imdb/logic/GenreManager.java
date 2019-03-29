package de.hsh.dbs2.imdb.logic;

import java.util.ArrayList;
import java.util.List;

import de.hsh.dbs.imdb.entities.Genre;
import de.hsh.dbs2.imdb.util.GenreFactory;

public class GenreManager {

	/**
	 * Ermittelt eine vollstaendige Liste aller in der Datenbank abgelegten Genres
	 * Die Genres werden alphabetisch sortiert zurueckgeliefert.
	 * @return Alle Genre-Namen als String-Liste
	 * @throws Exception
	 */
	public List<String> getGenres() throws Exception {
		
		GenreFactory gf = new GenreFactory();
		List<Genre> fullGenreArray = gf.getAll();
		List<String> genres = new ArrayList<String>();
		
		for(Genre g : fullGenreArray) {
			genres.add(g.getGenre());
		}
		
		return genres;
	}

}
