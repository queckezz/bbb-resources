
use m153;

/* a1 */

drop table mitarbeiter;
drop table abteilung;

create table abteilung
(
  id int auto_increment,
  name varchar (150) not null,
  beschreibung text,
  primary key (id)
);

create table mitarbeiter
(
  id int auto_increment,
  abteilungsid int not null,
  vorgesetzter int,
  name varchar (150) not null,
  vorname varchar (150),
  geburtsdatum date,
  primary key (id),
  foreign key (abteilungsid) references abteilung(id),
  foreign key (vorgesetzter) references mitarbeiter(id)
);

/* a2 */

alter table mitarbeiter add anstellungsgrad float;

/* a3 */

insert into abteilung (name, beschreibung) values
  ('CEO', 'Beschreibung CEO'),
  ('Support', 'Beschreibung Support'),
  ('Admin', 'Beschreibung Admin'),
  ('Customer Care', 'Beschreibung Customer Care'),
  ('Logistics', 'Beschreibung Logistics');

insert into mitarbeiter (abteilungsid, vorgesetzter, name, vorname, geburtsdatum, anstellungsgrad) values
  (1, null, 'Frei', 'Petra', '20100301', 1.0),
  (2, 1, 'Meier', 'Hans', '20100301', 0.8),
  (3, 1, 'Rudin', 'Verana', '20100301', 0.4),
  (2, 2, 'Meier', 'Fritz', '20100301', 0.9),
  (4, 2, 'Franke', 'Heidi', '20100301', 1.0),
  (2, 4, 'Ginster', 'Matthias', '20100301', 1.0),
  (4, 4, 'Wisele', 'Peter', '20100301', 0.6),
  (5, 4, 'Ernie', 'Carl', '20100301', 0.5);

/* a4 */

update mitarbeiter set name='Röllin' where name='Rudin' and vorname='Verana';

/* a5 */

delete from mitarbeiter where id = 8;

/* a6 */

select * from mitarbeiter
  where anstellungsgrad > 0.7
  order by anstellungsgrad desc;
