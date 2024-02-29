ALTER TABLE book
ADD CONSTRAINT fk_book_author
FOREIGN KEY (id_author) REFERENCES author(id);
