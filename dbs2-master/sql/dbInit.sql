----------------------
-- Tabellen löschen --
----------------------

-- Tabelle "MovGen" löschen
Drop Table MovGen;

-- Tabelle "MovieCharacter" löschen
Drop Table MovieCharacter;
-- Auto Increment MovieCharacterID Sequence löschen
Drop Sequence seq_movieCharacter;

-- Tabelle "Genre" löschen
Drop Table Genre;
-- Auto Increment GenreID Sequence löschen
Drop Sequence seq_genre;

-- Tabelle "Movie" löschen
Drop Table Movie;
-- Auto Increment MovieID Sequence löschen
Drop Sequence seq_movie;

-- Tabelle "Person" löschen
Drop Table Person;
-- Auto Increment PersonID Sequence löschen
Drop Sequence seq_person;


----------------------
-- Tabellen anlegen --
----------------------


-- Tabelle "Genre" erstellen
Create Table Genre(
    GenreID INTEGER,
    Genre VARCHAR(100),
    CONSTRAINT pk_Genre_GenreID PRIMARY KEY (GenreID),
    CONSTRAINT uk_Genre_Genre UNIQUE (Genre)
);

-- AUTO INCREMENT Sequenz für GenreID
CREATE SEQUENCE seq_genre MINVALUE 100 START WITH 100 INCREMENT BY 1;
    
    
-- Tabelle "Movie" erstellen
Create Table Movie (
    MovieID INTEGER,
    Title VARCHAR(100),
    Year INTEGER,
    Type CHAR,
    CONSTRAINT pk_Movie_MovieID PRIMARY KEY (MovieID)
);

-- AUTO INCREMENT Sequenz für MovieID
CREATE SEQUENCE seq_movie MINVALUE 100 START WITH 100 INCREMENT BY 1;



-- Tabelle "MovGen" erstellen
-- Stellt Beziehung zwischen Movie und Genre her
Create Table MovGen (
    MovieID INTEGER,
    GenreID INTEGER,
    CONSTRAINT pk_MovGen_ID PRIMARY KEY (MovieID, GenreID),
    CONSTRAINT fk_MovGen_GenreID FOREIGN KEY (GenreID) REFERENCES Genre(GenreID),
    CONSTRAINT fk_MovGen_MovieID FOREIGN KEY (MovieID) REFERENCES Movie(MovieID)
);

-- Tabelle "Person" erstellen 
Create Table Person(
    PersonID INTEGER,
    Name VARCHAR(100),
    Sex CHAR,
    CONSTRAINT pk_Person_PersonID PRIMARY KEY (PersonID),
    CONSTRAINT uk_Person_Name UNIQUE (Name)
);
-- AUTO INCREMENT Sequenz für PersonID
CREATE SEQUENCE seq_person MINVALUE 100 START WITH 100 INCREMENT BY 1;

-- Tabelle "MovieCharacter" erstellen
Create Table MovieCharacter( 
    MovCharID INTEGER,
    Character VARCHAR(100),
    Alias VARCHAR(100),
    Position INTEGER,
    MovieID INTEGER,            -- Beziehung zu Movie
    PersonID INTEGER,           -- Beziehung zu Person
    CONSTRAINT pk_MovieCharacter_MovCharID PRIMARY KEY (MovCharID),
    CONSTRAINT fk_MovieCharacter_MovieID FOREIGN KEY (MovieID) REFERENCES Movie(MovieID),
    CONSTRAINT fk_MovieCharacter_PersonID FOREIGN KEY (PersonID) REFERENCES Person(PersonID)
);
-- AUTO INCREMENT Sequenz für MovCharID
CREATE SEQUENCE seq_movieCharacter MINVALUE 100 START WITH 100 INCREMENT BY 1;




----------------------
-- Daten hinzufügen --
----------------------

-----------------------
-- Genres hinzufügen --
-----------------------

INSERT INTO Genre VALUES (seq_genre.nextval, 'Abenteuer');      -- 100
INSERT INTO Genre VALUES (seq_genre.nextval, 'Action');         -- 101
INSERT INTO Genre VALUES (seq_genre.nextval, 'Drama');          -- 102
INSERT INTO Genre VALUES (seq_genre.nextval, 'Erotik');         -- 103
INSERT INTO Genre VALUES (seq_genre.nextval, 'Fantasy');        -- 104
INSERT INTO Genre VALUES (seq_genre.nextval, 'Horror');         -- 105
INSERT INTO Genre VALUES (seq_genre.nextval, 'Krimi');          -- 106
INSERT INTO Genre VALUES (seq_genre.nextval, 'Komödie');        -- 107
INSERT INTO Genre VALUES (seq_genre.nextval, 'Musik');          -- 108
INSERT INTO Genre VALUES (seq_genre.nextval, 'Science-Fiction');-- 109
INSERT INTO Genre VALUES (seq_genre.nextval, 'Sport');          -- 110
INSERT INTO Genre VALUES (seq_genre.nextval, 'Thriller');       -- 111
INSERT INTO Genre VALUES (seq_genre.nextval, 'Western');        -- 112

-- Ausgabe zum prüfen der Daten
Select * from Genre;

---------------------------------
-- Filme und Genres hinzufügen --
---------------------------------

INSERT INTO Movie VALUES (seq_movie.nextval, 'Aufbruch zum Mond',2018, 'C');
INSERT INTO MovGen VALUES (seq_movie.currval, 100);

INSERT INTO Movie VALUES (seq_movie.nextval, 'Star Wars - Krieg der Sterne',1977, 'C');
INSERT INTO MovGen VALUES (seq_movie.currval, 109);

INSERT INTO Movie VALUES (seq_movie.nextval, 'Harry Potter und der Stein der Weisen',1997, 'C');
INSERT INTO MovGen VALUES (seq_movie.currval, 104);

INSERT INTO Movie VALUES (seq_movie.nextval, 'Die Simpsons der Film',2018, 'C');
INSERT INTO MovGen VALUES (seq_movie.currval, 107);

INSERT INTO Movie VALUES (seq_movie.nextval, 'Spider-Man',2002, 'C');
INSERT INTO MovGen VALUES (seq_movie.currval, 101);
INSERT INTO MovGen VALUES (seq_movie.currval, 104);

INSERT INTO Movie VALUES (seq_movie.nextval, 'Adams Äpfel',2005, 'C');
INSERT INTO MovGen VALUES (seq_movie.currval, 102);
INSERT INTO MovGen VALUES (seq_movie.currval, 106);

INSERT INTO Movie VALUES (seq_movie.nextval, 'Der Spongebob Schwammkopf Film',2004, 'C');
INSERT INTO MovGen VALUES (seq_movie.currval, 100);
INSERT INTO MovGen VALUES (seq_movie.currval, 104);

-- Ausgabe zum prüfen der Daten
select * from Movie m join MovGen mg on (m.MovieID = mg.MovieID)
                        join Genre g on (g.GenreID = mg.GenreID);
    
                        
-------------------------                        
-- Personen hinzufügen --
-------------------------

Insert INTO Person VALUES (seq_person.nextval, 'Albert Tross', 'm');        -- 100
Insert INTO Person VALUES (seq_person.nextval, 'Anna Gramm', 'w');          -- 101
Insert INTO Person VALUES (seq_person.nextval, 'Heinz Ellmamnn', 'm');      -- 102
Insert INTO Person VALUES (seq_person.nextval, 'Paul Ahner', 'm');          -- 103
Insert INTO Person VALUES (seq_person.nextval, 'Marta Phal', 'w');          -- 104
Insert INTO Person VALUES (seq_person.nextval, 'Mario Nese', 'm');          -- 105
Insert INTO Person VALUES (seq_person.nextval, 'Claire Grube', 'w');        -- 106
Insert INTO Person VALUES (seq_person.nextval, 'Marga Milch', 'w');         -- 107
Insert INTO Person VALUES (seq_person.nextval, 'Johannis Bär', 'm');        -- 108

select * from Person;



---------------------------
-- Charaktere hinzufügen --
---------------------------

INSERT INTO MovieCharacter VALUES (seq_movieCharacter.nextval, 'Ben Müller', '',1,100,100);
INSERT INTO MovieCharacter VALUES (seq_movieCharacter.nextval, 'Sabrina Meyer', '',2,100,101);

INSERT INTO MovieCharacter VALUES (seq_movieCharacter.nextval, 'Han Solo', '',1,101,102);
INSERT INTO MovieCharacter VALUES (seq_movieCharacter.nextval, 'Luke Skywalker', '',2,101,103);

INSERT INTO MovieCharacter VALUES (seq_movieCharacter.nextval, 'Harry Potter', '',1,102,100);
INSERT INTO MovieCharacter VALUES (seq_movieCharacter.nextval, 'Hermine', '',2,102,106);

INSERT INTO MovieCharacter VALUES (seq_movieCharacter.nextval, 'Bart Simpson', 'el Barto',1,103,108);
INSERT INTO MovieCharacter VALUES (seq_movieCharacter.nextval, 'Marge Simpson', '',2,103,107);

INSERT INTO MovieCharacter VALUES (seq_movieCharacter.nextval, 'Adam White', '',1,104,105);
INSERT INTO MovieCharacter VALUES (seq_movieCharacter.nextval, 'Sybille Greenwood', '',2,104,107);

INSERT INTO MovieCharacter VALUES (seq_movieCharacter.nextval, 'Spongebob Schwammkopf', '',1,105,103);
INSERT INTO MovieCharacter VALUES (seq_movieCharacter.nextval, 'Patrick Star', '',2,105,105);
 
Select * from MovieCharacter; 
 
--------------------
-- Alles Ausgeben -- 
--------------------

-- Ausgabe zum prüfen der Daten
select m.movieid, m.title, m.year, g.genre, mc.character, mc.alias from Movie m join MovGen mg on (m.MovieID = mg.MovieID)
                        join Genre g on (g.GenreID = mg.GenreID)
                        join MovieCharacter mc on (m.MovieID = mc.MovieID)
                        join Person p on (mc.PersonID = p.personID);
