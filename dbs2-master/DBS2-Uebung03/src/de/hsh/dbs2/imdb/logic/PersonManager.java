package de.hsh.dbs2.imdb.logic;

import java.util.ArrayList;
import java.util.List;

import de.hsh.dbs.imdb.entities.Person;
import de.hsh.dbs2.imdb.util.PersonFactory;

public class PersonManager {

	/**
	 * Liefert eine Liste aller Personen, deren Name den Suchstring enthaelt.
	 * @param text Suchstring
	 * @return Liste mit passenden Personennamen, die in der Datenbank eingetragen sind.
	 * @throws Exception
	 */
	public List<String> getPersonList(String text) throws Exception {
		
		PersonFactory pf = new PersonFactory();
		List<Person> fullPersonArray = pf.findByName(text);
		List<String> person = new ArrayList<String>();
		
		for(Person p : fullPersonArray) {
			person.add(p.getName());
		}
		
		return person;
	}

}
