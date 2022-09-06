# SpringFileAPi
## The way how to deal with database and api in Spring connecting files

![image](https://user-images.githubusercontent.com/95692103/188710796-db55155d-1a1f-4a0e-bd62-9651225d46b6.png)

## This project includes

:white_check_mark: thymeleaf

:white_check_mark: liquibase

:white_check_mark: h2 database

## What is liquibase

`Liquibase allows you to specify the database change you want using SQL or several different database-agnostic formats, including XML, YAML, and JSON. Developers can abstract the database code to make it extremely easy to push out changes to different database types.`

### This is an instance how you can make it.

*First of all create master.xml file which will be the main file containing other changes made in thanks to liquibase*

```
<?xml version="1.0" encoding="UTF-8"?>
<databaseChangeLog
        xmlns="http://www.liquibase.org/xml/ns/dbchangelog"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog
                      https://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-4.9.xsd">
<include file="0001_createTable.xml" relativeToChangelogFile="true"/>
</databaseChangeLog>
```

*The main file declared in application.properties or application.yml by using*

```
spring:
  liquibase:
    change-log: classpath:db/master.xml
```

*And here some example of file included in master*

```
<?xml version="1.0" encoding="UTF-8"?>
<databaseChangeLog
        xmlns="http://www.liquibase.org/xml/ns/dbchangelog"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog
                      https://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-4.9.xsd">
    <changeSet id="0001" author="Marcin">
        <createTable tableName="docs">
            <column name="id" type="varchar(200)">
                <constraints nullable="false" primaryKey="true"/>
            </column>
            <column name="doc_type" type="varchar(200)"/>
            <column name="data" type="blob"/>
        </createTable>
    </changeSet>
</databaseChangeLog>
```

**Now h2 database will not be created automatically ! Liquibase will made it for you.**

### :space_invader: `Feel free to use` :wink:
