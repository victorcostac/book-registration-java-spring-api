CREATE TABLE book (
    id VARCHAR(255) PRIMARY KEY UNIQUE NOT NULL,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    year_of_publication VARCHAR(255) NOT NULL,
    quantity INTEGER NOT NULL
);