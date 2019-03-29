package de.hsh.dbs.imdb.entities;

public class PersonCharacter {
	private Person p = null;
	private MovieCharacter mc = null;
	
	public PersonCharacter() {
		
	}

	public PersonCharacter(Person p, MovieCharacter mc) {
		this.p = p;
		this.mc = mc;
		
	}
	
	public Person getP() {
		return p;
	}

	public void setP(Person p) {
		this.p = p;
	}

	public MovieCharacter getMc() {
		return mc;
	}

	public void setMc(MovieCharacter mc) {
		this.mc = mc;
	}
	
	
}
