package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.AnyJoinGenre;

//Book, CD, DVD, KamishibaiをGenreと結合
public interface AnyJoinGenreRepository extends JpaRepository<AnyJoinGenre, Integer> {
	//カテゴリ更新用
	static String sqlBookJoinGenreAll = "SELECT book.book_id AS any_id, book.title, book.author, book.publisher, book.genre_id, book.deleted, "
			+ "genre.genre_name, genre.category_id "
			+ "FROM book "
			+ "JOIN genre ON book.genre_id = genre.genre_id "
			+ "WHERE book.deleted = false "
			+ "ORDER BY book.book_id ; ";
	static String sqlCDJoinGenreAll = "SELECT cd.cd_id AS any_id, cd.title, cd.author, cd.publisher, cd.genre_id, cd.deleted, "
			+ "genre.genre_name, genre.category_id "
			+ "FROM cd "
			+ "JOIN genre ON cd.genre_id = genre.genre_id "
			+ "WHERE cd.deleted = false "
			+ "ORDER BY cd.cd_id ; ";
	static String sqlDVDJoinGenreAll = "SELECT dvd.dvd_id AS any_id, dvd.title, dvd.author, dvd.publisher, dvd.genre_id, dvd.deleted, "
			+ "genre.genre_name, genre.category_id "
			+ "FROM dvd "
			+ "JOIN genre ON dvd.genre_id = genre.genre_id "
			+ "WHERE dvd.deleted = false "
			+ "ORDER BY dvd.dvd_id ; ";
	static String sqlKamishibaiJoinGenreAll = "SELECT kamishibai.kamishibai_id AS any_id, kamishibai.title, kamishibai.author, kamishibai.publisher, kamishibai.genre_id, kamishibai.deleted, "
			+ "genre.genre_name, genre.category_id "
			+ "FROM kamishibai "
			+ "JOIN genre ON kamishibai.genre_id = genre.genre_id "
			+ "WHERE kamishibai.deleted = false "
			+ "ORDER BY kamishibai.kamishibai_id ; ";

	//更新画面表示用
	static String sqlBookJoinGenreById = "SELECT book.book_id AS any_id, book.title, book.author, book.publisher, book.genre_id, book.deleted, "
			+ "genre.genre_name, genre.category_id "
			+ "FROM book "
			+ "JOIN genre ON book.genre_id = genre.genre_id "
			+ "WHERE book.deleted = false "
			+ "AND book.book_id = :anyId ;";
	static String sqlCDJoinGenreById = "SELECT cd.cd_id AS any_id, cd.title, cd.author, cd.publisher, cd.genre_id, cd.deleted, "
			+ "genre.genre_name, genre.category_id "
			+ "FROM cd "
			+ "JOIN genre ON cd.genre_id = genre.genre_id "
			+ "WHERE cd.deleted = false "
			+ "AND cd.cd_id = :anyId ;";
	static String sqlDVDJoinGenreById = "SELECT dvd.dvd_id AS any_id, dvd.title, dvd.author, dvd.publisher, dvd.genre_id, dvd.deleted, "
			+ "genre.genre_name, genre.category_id "
			+ "FROM dvd "
			+ "JOIN genre ON dvd.genre_id = genre.genre_id "
			+ "WHERE dvd.deleted = false "
			+ "AND dvd.dvd_id = :anyId ;";
	static String sqlKamishibaiJoinGenreById = "SELECT kamishibai.kamishibai_id AS any_id, kamishibai.title, kamishibai.author, kamishibai.publisher, kamishibai.genre_id, kamishibai.deleted, "
			+ "genre.genre_name, genre.category_id "
			+ "FROM kamishibai "
			+ "JOIN genre ON kamishibai.genre_id = genre.genre_id "
			+ "WHERE kamishibai.deleted = false "
			+ "AND kamishibai.kamishibai_id = :anyId ;";

	//カテゴリ更新用
	//更新画面表示用
	@Query(value = sqlBookJoinGenreAll, nativeQuery = true)
	List<AnyJoinGenre> sqlBookJoinGenreAll();

	@Query(value = sqlCDJoinGenreAll, nativeQuery = true)
	List<AnyJoinGenre> sqlCDJoinGenreAll();

	@Query(value = sqlDVDJoinGenreAll, nativeQuery = true)
	List<AnyJoinGenre> sqlDVDJoinGenreAll();

	@Query(value = sqlKamishibaiJoinGenreAll, nativeQuery = true)
	List<AnyJoinGenre> sqlKamishibaiJoinGenreAll();

	//更新画面表示用
	@Query(value = sqlBookJoinGenreById, nativeQuery = true)
	List<AnyJoinGenre> sqlBookJoinGenreById(@Param("anyId") Integer bookId);

	@Query(value = sqlCDJoinGenreById, nativeQuery = true)
	List<AnyJoinGenre> sqlCDJoinGenreById(@Param("anyId") Integer cdId);

	@Query(value = sqlDVDJoinGenreById, nativeQuery = true)
	List<AnyJoinGenre> sqlDVDJoinGenreById(@Param("anyId") Integer dvdId);

	@Query(value = sqlKamishibaiJoinGenreById, nativeQuery = true)
	List<AnyJoinGenre> sqlKamishibaiJoinGenreById(@Param("anyId") Integer kamishibaiId);

}
