CREATE TABLE movies (
	id SERIAL NOT NULL,
	name varchar(100) NOT NULL,
	genre varchar(200) NOT NULL,
	year int NOT NULL,
	director varchar(100),
	runtime varchar(20) NOT NULL,
	imdb_rating real NOT NULL,
	PRIMARY KEY (id)
);