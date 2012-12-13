jdbc-ftw
========

Ok, so here's the deal. <b>You have to use SQL</b> (I know it's 2013 and it may sound outdated). Your previously JPA/Hibernate projects sucks and the DBA hates you.

This time you want all the SQL files well-organized, OUTSIDE your Java code, so the DBA can quickly run the file 
inside his SQL tool, improve it, and commit it back to you.

This time you'll face multiple optmized SQL queries for retrieving different combinations of columns, joins, sometimes
aliasing the projection, etc. Forget the same boring and unhuman Hibernate generated queries. 

You want a more flexible ResultSet. If the (stupid Oracle) driver gives you a BigDecimal every time you wanted an Integer, 
why just don't converted it?!

ORM tools does one thing very well. They provide a single point of configuration. I want that in my JDBC, using a nice DSL.
I don't want to spend hours mapping columns to do a standard INSERT/UPDATE/DELETE.

More to come.

---
Eduardo Rebola
@eduardorebola
