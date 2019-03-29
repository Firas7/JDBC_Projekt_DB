select * from movie;
select * from person;
select * from genre;
select * from movgen;
select * from MovieCharacter;

/* Testen des Loeschen !*/

insert into movie values (200  , 'Film zu loeschen' , 2018, 'S');
delete from movie where movieid = 170;
SELECT * FROM MovieCharacter WHERE MOVIEID = 200;


SELECT GENRE FROM GENRE ORDER BY GENRE ;
SELECT MC.CHARACTER, P.NAME FROM MOVIECHARACTER MC JOIN PERSON P ON MC.PERSONID = P.PERSONID WHERE MC.MOVIEID = 103;

insert into person values (250,'Firas Shmit', 'M');
delete from person where personid = 169;
insert into genre values (500,'GENRENAME');
delete from genre where genreid = 142;

insert into movgen values (200,110);
delete from  movgen where MOVIEID = 200 ;
delete from  movgen where GENREID= 500;


insert into MovieCharacter Values (500 ,'Firas Shmit', null, 3 , 200 , 250);
insert into MovieCharacter Values (100 ,'Firas Shmit', null, 3 , 200 , 250);

delete from  MovieCharacter where movieid = 166 or personid = 250;
update movie set title  = 'Die tolle Komodie'  where movieid = 167;

SELECT G.GENRE,M.MOVIEID, M.TITLE, M.YEAR from Movie M JOIN MOVgen MG ON MG.MOVIEID = M.MOVIEID 
JOIN GENRE G ON MG.GENREID = G.GENREID WHERE M.MOVIEID = 146; 

update movie m join GENRE g on m.movieid = g.movieid set title = 'Update durhcgefuehrt! ' where m.movieid = 200;
select * from movgen;