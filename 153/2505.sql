use m153;

drop table mitarbeiter;
drop table abteilung;

create table abteilung
(
	id int,
	name character varying (150) not null,
    beschreibung text,
    primary key (id)
);

create table mitarbeiter
(
	id int,
    abteilungsid int not null,
    vorgesetzter int,
    name character varying (150) not null,
    vorname character varying (150),
    geburtsdatum date,
    primary key (id),
    foreign key (abteilungsid) references abteilung(id),
    foreign key (vorgesetzter) references mitarbeiter(id)
);

alter table mitarbeiter add anstellungsgrad float;