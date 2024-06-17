package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Genre;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
	static String sqlGenreListId = "SELECT * "
			+ "FROM genre "
			+ "WHERE category_id = :categoryId "
			+ "AND deleted = false "
			+ "ORDER BY genre_id ; ";

	List<Genre> findByCategoryId(Integer categoryId);

	//	ジャンル追加時のエラーチェック用
	List<Genre> findByCategoryIdAndGenreName(Integer categoryId, String genreName);

	//	ジャンル一覧表示
	List<Genre> findByDeletedFalseOrderByCategoryIdAsc();

	//	カテゴリーごとのジャンル表示
	List<Genre> findByDeletedFalseAndCategoryId(Integer categoryId);

	//	削除されたジャンル一覧表示
	List<Genre> findByDeletedTrueOrderByCategoryIdAsc();

	//カテゴリデータ編集用createdBy藤上
	@Query(value = sqlGenreListId, nativeQuery = true)
	List<Genre> sqlGenreListId(@Param("categoryId") Integer categoryId);

}
