package de.hsh.dbs2.imdb.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import de.hsh.dbs2.imdb.util.GenreFactory;
import de.hsh.dbs2.imdb.util.MovieFactory;
import de.hsh.dbs2.imdb.util.PersonFactory;
import de.hsh.dbs2.imdb.util.SQL;
import de.hsh.dbs.imdb.entities.Movie;
import de.hsh.dbs.imdb.entities.MovieCharacter;
import de.hsh.dbs.imdb.entities.MovieGenre;
import de.hsh.dbs.imdb.entities.Person;
import de.hsh.dbs.imdb.entities.PersonCharacter;
import de.hsh.dbs2.imdb.logic.dto.CharacterDTO;
import de.hsh.dbs2.imdb.logic.dto.MovieDTO;

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
		
		MovieFactory mf = new MovieFactory();
		GenreFactory gf = new GenreFactory();
		PersonFactory pf = new PersonFactory();
		List<Movie> list = mf.findByTitle(search);
		List<MovieDTO> movieList = new ArrayList<MovieDTO>();
		
			if(search.matches("[0-9]+")) {
			movieList.add(getMovie(Long.parseLong(search)));
			return movieList;
		}else {
			
		for(int i = 0 ; i< list.size();i++) {
			MovieDTO mov = new MovieDTO();
			mov.setId(list.get(i).getId());
			mov.setTitle(list.get(i).getTitle());
			mov.setType(list.get(i).getType());
			mov.setYear(list.get(i).getYear());
			
			mov.setGenres(gf.getByMovieID(mov.getId()));
			
			List<CharacterDTO> characters = new ArrayList<CharacterDTO>();
			List<PersonCharacter> pc = new ArrayList<PersonCharacter>();
			pc = pf.personCharacterByMovieID(mov.getId());
			for(PersonCharacter p : pc) {
				CharacterDTO charDTO = new CharacterDTO();
				charDTO.setCharacter(p.getMc().getCharacter());
				charDTO.setAlias(p.getMc().getAlias());
				charDTO.setPlayer(p.getP().getName());
				characters.add(charDTO);
			}
			
			mov.setCharacters(characters);			
			movieList.add(mov);
		}
		
		return movieList;
		}
	}

	/*
	 * Speichert die uebergebene Version des Films neu in der Datenbank oder aktualisiert den
	 * existierenden Film.
	 * Dazu werden die Daten des Films selbst (Titel, Jahr, Typ) beruecksichtigt,
	 * aber auch alle Genres, die dem Film zugeordnet sind und die Liste der Charaktere
	 * auf den neuen Stand gebracht.
	 * @param movie Film-Objekt mit Genres und Charakteren.
	 * @throws Exception
	 */
	public void insertUpdateMovie(MovieDTO movieDTO) throws Exception {
		
		List<CharacterDTO> charList = movieDTO.getCharacters();
		Set<String> genHash = movieDTO.getGenres();
		
		Movie mov = new Movie();
		mov.setId(movieDTO.getId());
		mov.setTitle(movieDTO.getTitle());
		mov.setType(movieDTO.getType());
		mov.setYear(movieDTO.getYear());
		mov.insert();
		
		
		
		//MovieGenre movGen = new MovieGenre(mov.getId(), genHash.toString().);
		
		/* movie, genre, Person, movgen, MovieCharacter.
		 * 
		 * mov.insert();
		 * 
		 * 
		 * */
		GenreFactory gf = new GenreFactory();
		PersonFactory pf = new PersonFactory();

		ArrayList<MovieGenre> movGenList = new ArrayList<MovieGenre>();
		ArrayList<MovieCharacter> movCharList = new ArrayList<MovieCharacter>();
		
				
		
		
		
		/*
		 * Kann das überhaupt funktionieren?
		 * Was passiert überhaupt, wenn der Name einer fremden Person ins Feld getippt wird?
		 */
		for(CharacterDTO c : charList) {
			if(!pf.checkPerson(c.getPlayer())) {
				Person person = new Person(0,c.getPlayer(),'d'); // Wenn Person nicht vorhanden, füge sie zur Datenbank hinzu
				person.insert();
			}
			
			MovieCharacter movchar = new MovieCharacter();
			movchar.setCharacter(c.getCharacter());
			movchar.setAlias(c.getAlias());
			movchar.setPos(0);
			movchar.setPlayerId(pf.findIdByName(c.getPlayer()));
			movchar.setMovieId(mov.getId());
			movCharList.add(movchar);
		}
		
		for(String s : genHash) {
			movGenList.add(new MovieGenre(mov.getId(),gf.idByGenre(s)));
		}
		
		
		if(movieDTO.getId() == null) {
			//insert
			for(MovieGenre mg : movGenList) mg.insert();
			for(MovieCharacter mc : movCharList) mc.insert();
			
		} else {
			//update
			mov.update();
			//for(MovieGenre mg : movGenList) mg.update(mov.getId()); // wie funktioniert hier das update? wird ein Genre entfernt -> löschen, 
															//wird eins hinzugefügt -> hinzufügen. UPDATE wird nicht funktionieren
			for(MovieCharacter mc : movCharList) mc.update();
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
		String sql = " DELETE FROM MOVIE WHERE movieid = " + movieId;
		
		/* delete all Movies and Genre from table MovGen */
		String sql1 = " DELETE FROM MOVGEN WHERE MOVIEID = " + movieId ;
		SQL.sqlEingabe(sql1);
		
		/* delete all Character from this MovieID */
		String sql2 = " DELETE FROM MOVIECHARACTER WHERE movieid = " + movieId ;
		SQL.sqlEingabe(sql2);
		
		
		/* at the end delete this MovieID */
		SQL.sqlEingabe(sql);
	}

	
	public MovieDTO getMovie(long movieId) throws Exception {
		// TODO Auto-generated method stub
		
		PersonFactory pf = new PersonFactory();
		GenreFactory gf = new GenreFactory();
		MovieFactory mf = new MovieFactory();
		MovieDTO movie = new MovieDTO();
		Movie mov = mf.findById(movieId);
		
		movie.setId(mov.getId());
		movie.setTitle(mov.getTitle());
		movie.setType(mov.getType());
		movie.setYear(mov.getYear());
	
		System.out.println(movie.getTitle());
		movie.setGenres(gf.getByMovieID(movie.getId()));
		
		List<CharacterDTO> characters = new ArrayList<CharacterDTO>();
		List<PersonCharacter> pc = new ArrayList<PersonCharacter>();
		pc = pf.personCharacterByMovieID(movie.getId());
		for(PersonCharacter p : pc) {
			CharacterDTO charDTO = new CharacterDTO();
			charDTO.setCharacter(p.getMc().getCharacter());
			charDTO.setAlias(p.getMc().getAlias());
			charDTO.setPlayer(p.getP().getName());
			characters.add(charDTO);
		}
		movie.setCharacters(characters);
		
		return movie;
	}
	
	
	public static void printMovieDTO(MovieDTO mDTO) {

		Set<String> genres = mDTO.getGenres();
		List<CharacterDTO> characters = mDTO.getCharacters();
		System.out.println(" ===== Character Start =====");
		System.out.println(mDTO.getId());
		System.out.println(mDTO.getTitle());
		System.out.println(mDTO.getType());
		System.out.println(mDTO.getYear());
		for(String s : genres) System.out.println(s);
		for(CharacterDTO c : characters)
		System.out.println(c.getCharacter()+" : "+c.getAlias()+" : "+c.getPlayer());
		System.out.println(" ===== Character End =====");
		
	}

}
