
Query with the primary key is a lot faster. This is probably because the id is unique where the ``

```sql
select * from benchmarktable where id = 2000;
/* -> 0.00s */

select * from benchmarktable where number = 2000;
/* -> 0.22s */
```

After the index `create-index.sql` on column `number` applied:

```sql
select * from benchmarktable where number = 2000;
/* -> 0.00s */
```