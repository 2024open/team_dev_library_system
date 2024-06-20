-- 各種テーブル削除
DROP TABLE IF EXISTS reservation;
DROP TABLE IF EXISTS lending_item;
DROP TABLE IF EXISTS lend_item;
DROP TABLE IF EXISTS notice;
DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS cd;
DROP TABLE IF EXISTS dvd;
DROP TABLE IF EXISTS kamishibai;
DROP TABLE IF EXISTS room;
DROP TABLE IF EXISTS status;
DROP TABLE IF EXISTS genre;
DROP TABLE IF EXISTS library;
DROP TABLE IF EXISTS category;


-- 図書館テーブル
CREATE TABLE library
(
   library_id SERIAL PRIMARY KEY,
   library_name VARCHAR(50),
   library_address VARCHAR(50),
   library_tel VARCHAR(50),
   deleted BOOLEAN DEFAULT FALSE
);

-- 貸出物ステータステーブル
CREATE TABLE status
(
   status_id SERIAL PRIMARY KEY,
   status_name VARCHAR(50),
   deleted BOOLEAN DEFAULT FALSE
);

-- アカウントテーブル
CREATE TABLE account
(
   user_id SERIAL PRIMARY KEY,
   user_name VARCHAR(50) NOT NULL,
   nickname VARCHAR(50) NOT NULL,
   email VARCHAR(100) UNIQUE,
   password VARCHAR(30) NOT NULL,
   privilege INTEGER DEFAULT 2,
   ban BOOLEAN DEFAULT FALSE,
   deleted BOOLEAN DEFAULT FALSE
);


-- お知らせテーブル
CREATE TABLE notice
(
   notice_id SERIAL PRIMARY KEY,
   library_id INTEGER REFERENCES library(library_id),
   user_id INTEGER REFERENCES account(user_id),
   title VARCHAR(50) NOT NULL,
   content TEXT NOT NULL,
   notice_date DATE DEFAULT CURRENT_DATE
);


-- カテゴリーテーブル
CREATE TABLE category
(
   category_id SERIAL PRIMARY KEY,
   category_name VARCHAR(30) NOT NULL,
   deleted BOOLEAN DEFAULT FALSE
);

-- ジャンルテーブル
CREATE TABLE genre
(
   genre_id SERIAL PRIMARY KEY,
   genre_name VARCHAR(30) NOT NULL,
   category_id INTEGER REFERENCES category(category_id),
   deleted BOOLEAN DEFAULT FALSE
);

-- 貸出物テーブル
CREATE TABLE lend_item
(
   lend_item_id SERIAL PRIMARY KEY,
   library_id INTEGER REFERENCES library(library_id),
   category_id INTEGER REFERENCES category(category_id),
   create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   update_date TIMESTAMP,
   status_id INTEGER REFERENCES status(status_id),
   any_id INTEGER NOT NULL,
   deleted BOOLEAN DEFAULT FALSE
);

-- 貸出中貸出物テーブル
CREATE TABLE lending_item
(
   lending_item_id SERIAL PRIMARY KEY,
   lend_item_id INTEGER REFERENCES lend_item(lend_item_id),
   user_id INTEGER REFERENCES account(user_id),
   return_date DATE,
   borrowed_date DATE,
   status_id INTEGER,
   deleted BOOLEAN DEFAULT FALSE
);

-- 予約テーブル
CREATE TABLE reservation
(
   reservation_id SERIAL PRIMARY KEY,
   lend_item_id INTEGER REFERENCES lend_item(lend_item_id),
   user_id INTEGER REFERENCES account(user_id),
   reservation_date DATE DEFAULT CURRENT_TIMESTAMP
);



-- 本テーブル
CREATE TABLE book
(
   book_id SERIAL PRIMARY KEY,
   title VARCHAR(400) NOT NULL,
   author VARCHAR(400) NOT NULL,
   publisher VARCHAR(50) NOT NULL,
   genre_id INTEGER REFERENCES genre(genre_id),
   deleted BOOLEAN DEFAULT FALSE
);

-- CDテーブル
CREATE TABLE cd
(
   cd_id SERIAL PRIMARY KEY,
   title VARCHAR(400) NOT NULL,
   author VARCHAR(400) NOT NULL,
   publisher VARCHAR(50) NOT NULL,
   genre_id INTEGER REFERENCES genre(genre_id),
   deleted BOOLEAN DEFAULT FALSE
);

-- DVDテーブル
CREATE TABLE dvd
(
   dvd_id SERIAL PRIMARY KEY,
   title VARCHAR(400) NOT NULL,
   author VARCHAR(400) NOT NULL,
   publisher VARCHAR(50) NOT NULL,
   genre_id INTEGER REFERENCES genre(genre_id),
   deleted BOOLEAN DEFAULT FALSE
);

-- 紙芝居テーブル
CREATE TABLE kamishibai
(
   kamishibai_id SERIAL PRIMARY KEY,
   title VARCHAR(400) NOT NULL,
   author VARCHAR(400) NOT NULL,
   publisher VARCHAR(50) NOT NULL,
   genre_id INTEGER REFERENCES genre(genre_id),
   deleted BOOLEAN DEFAULT FALSE
);


-- 貸会議室テーブル
CREATE TABLE room
(
   room_id SERIAL PRIMARY KEY,
   room_name VARCHAR(30) NOT NULL,
   deleted BOOLEAN DEFAULT FALSE
);

ALTER TABLE book OWNER TO student;
ALTER TABLE cd OWNER TO student;
ALTER TABLE dvd OWNER TO student;
ALTER TABLE kamishibai OWNER TO student;
ALTER TABLE room OWNER TO student;
ALTER TABLE status OWNER TO student;
ALTER TABLE category OWNER TO student;
ALTER TABLE genre OWNER TO student;
ALTER TABLE account OWNER TO student;
ALTER TABLE notice OWNER TO student;
ALTER TABLE reservation OWNER TO student;
ALTER TABLE library OWNER TO student;
ALTER TABLE lend_item OWNER TO student;
ALTER TABLE lending_item OWNER TO student;