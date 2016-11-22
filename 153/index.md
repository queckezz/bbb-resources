
# module 153 docs

## serial key

* `PK` -> Primary key
* `NN` -> Not null
* `AI` -> Auto increment

## Data types

* http://www.w3schools.com/sql/sql_datatypes.asp

```
FLOAT(size, d)
```

A small number with a floating decimal point. The maximum number of digits may be specified in the size parameter. The maximum number of digits to the right of the decimal point is specified in the d parameter

### dates

* http://www.w3schools.com/sql/sql_dates.asp

When using the `Date`-type you have to insert date in the `(YYYY-MM-DD)` format

```sql
INSERT INTO `messnetz`.`wetter`
  (`datum`, `bewoelkung`)
  VALUES
  ('2016-05-13', 'schwach');
```

## Check constraints

### Check if a value is in a specific set of values

```sql
IF NEW.bewoelkung NOT IN ('stark', 'mittel', 'schwach', 'nicht') THEN
  SIGNAL SQLSTATE '12345' SET MESSAGE_TEXT = 'check constraint on wetter.bewoelkung failed';
END IF;
```

## Logisches Modell

![./assets/Komposition_Aggregation.png](./assets/Komposition_Aggregation.png)

In der [objektorientierten Programmierung](https://de.wikipedia.org/wiki/Objektorientierte_Programmierung) spezifiziert die **Aggregation** eine [Assoziation](https://de.wikipedia.org/wiki/Assoziation_(UML)) zwischen [Objekten](https://de.wikipedia.org/wiki/Objekt_(Programmierung)). Im Gegensatz zur [Komposition](https://de.wikipedia.org/wiki/Assoziation_(UML)#Aggregation_und_Komposition) (die ebenfalls eine „ist-Teil-von“-Assoziation beschreibt) kann das Teil-Objekt ohne das Aggregat-Objekt existieren; Es wird also nicht automatisch beim Löschen des Aggregat-Objekts mitgelöscht.

* https://de.wikipedia.org/wiki/Kardinalität_(Datenbankmodellierung)
* http://www.sqldocu.com/nine/relationship.htm
* http://stackoverflow.com/questions/762937/whats-the-difference-between-identifying-and-non-identifying-relationships

## Referentielle Integrität

Nach der RI-Regel dürfen Datensätze (über ihre [Fremdschlüssel](https://de.wikipedia.org/wiki/Fremdschl%C3%BCssel)) nur auf existierende Datensätze verweisen

Danach besteht die RI grundsätzlich aus zwei Teilen:

1. Ein neuer Datensatz mit einem Fremdschlüssel kann nur dann in einer Tabelle eingefügt werden, wenn in der referenzierten Tabelle ein Datensatz mit entsprechendem Wert im [Primärschlüssel](https://de.wikipedia.org/wiki/Schl%C3%BCssel_(Datenbank)#Prim.C3.A4rschl.C3.BCssel_und_Alternativschl.C3.BCssel) oder einem eindeutigen [Alternativschlüssel](https://de.wikipedia.org/wiki/Schl%C3%BCssel_(Datenbank)#Prim.C3.A4rschl.C3.BCssel_und_Alternativschl.C3.BCssel) existiert.
2. Eine Datensatzlöschung oder Änderung des [Schlüssels](https://de.wikipedia.org/wiki/Schl%C3%BCssel_(Datenbank)) in einem Primär-Datensatz ist nur möglich, wenn zu diesem Datensatz keine abhängigen Datensätze in Beziehung stehen.

### Constraints

* `CASCADE` will propagate the change when the parent changes. (If you delete a row, rows in constrained tables that reference that row will also be deleted, etc.)
* `SET NULL` sets the column value to NULL when a parent row goes away.
* `RESTRICT` causes the attempted DELETE of a parent row to fail.

## Business Rules

Logische Bedingungen an die DB.

|            | Kunde | Bestellung | Artikel |
| ---------- | ----- | ---------- | ------- |
| Kunde      |       | C1         |         |
| Bestellung |       |            | C2      |
| Artikel    |       |            |         |

* **C1**: Eine Bestellung muss man immer einen Kunden zugeordnet sein.
* **C2**: Eine Bestellung muss mindestens einen Artikel umfassen.