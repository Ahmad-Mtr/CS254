`ProductsTable.sql`
```sql
CREATE TABLE products (
    id INT AUTO_INCREMENT PRIMARY KEY,  
    name VARCHAR(100) NOT NULL, 
    price decimal(10, 2) NOT NUll,  
    quantity int not null
);
```

`UsersTable.sql`
```sql
CREATE TABLE Users (
    user_name varchar(20) PRIMARY KEY,
    password VARCHAR(100),
    full_name VARCHAR(100),
    email varchar(50),
    gender varchar(20),
    major varchar(50),
    skills varchar(200)
);

CREATE PROCEDURE `checkUserName`(in userName varchar(20), out count int)
BEGIN
	select count(*) as count into count from users where user_name= userName;
END
```