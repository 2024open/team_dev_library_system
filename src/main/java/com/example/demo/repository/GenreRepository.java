package com.example.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Genre;

public interface GenreRepository extends JpaRepository<Genre, Integer> {


	List<Genre> findByCategoryId(Integer categoryId);
	
//	ジャンル追加時のエラーチェック用
	List<Genre> findByCategoryIdAndGenreName(Integer categoryId,String genreName);
	
//	ジャンル一覧表示用（デリートフラグfalse、カテゴリー昇順）
	List<Genre> findByDeletedFalseOrderByCategoryIdAsc();
	
//	カテゴリーごとのジャンル表示用(デリートフラグfalse)
	List<Genre> findByDeletedFalseAndCategoryId(Integer categoryId);

}
