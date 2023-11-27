create database biblioteca;
use biblioteca;
create table livros(
id int not null auto_increment primary key,
titulo varchar(100),
autor varchar(100),
editora varchar(100)
);

insert into livros(titulo, autor, editora) values ("A Menina que Roubava Livros", "Markus Zusak", "Intriseca");

select * from livros;

drop database biblioteca;