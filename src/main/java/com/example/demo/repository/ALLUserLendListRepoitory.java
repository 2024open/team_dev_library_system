package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.AllUserLendList;

public interface ALLUserLendListRepoitory extends JpaRepository<AllUserLendList, Integer> {

	//本一覧表示
	static String sqlALLUserLendItemJoinBook = "SELECT lend_item.lend_item_id,lend_item.library_id, book.genre_id, book.title, book.author, lend_item.status_id,lend_item.category_id "
			+ "FROM lend_item "
			+ "INNER JOIN book "
			+ "ON lend_item.any_id = book.book_id "
			+ "WHERE lend_item.category_id=1 AND lend_item.library_id=1 "
			+ "order by lend_item.lend_item_id asc;";

	@Query(value = sqlALLUserLendItemJoinBook, nativeQuery = true)
	List<AllUserLendList> sqlALLUserBookLendJoin();

	//本一覧表示
	static String sqlALLUserLendItemJoinBookSearch = "SELECT lend_item.lend_item_id,lend_item.library_id, book.genre_id, book.title, book.author, lend_item.status_id,lend_item.category_id "
			+ "FROM lend_item "
			+ "INNER JOIN book "
			+ "ON lend_item.any_id = book.book_id "
			+ "WHERE lend_item.category_id=1 AND lend_item.library_id=1 and book.title LIKE :search "
			+ "order by lend_item.lend_item_id asc;";

	@Query(value = sqlALLUserLendItemJoinBookSearch, nativeQuery = true)
	List<AllUserLendList> sqlALLUserBookLendJoinSearch(@Param("search") String search);
	

	//本一覧表示貸出可
	static String sqlALLUserLendItemJoinBookP = "SELECT lend_item.lend_item_id,lend_item.library_id, book.genre_id, book.title, book.author, lend_item.status_id,lend_item.category_id "
			+ "FROM lend_item "
			+ "INNER JOIN book "
			+ "ON lend_item.any_id = book.book_id "
			+ "WHERE lend_item.category_id=1 AND lend_item.library_id=1 AND lend_item.status_id=1 "
			+ "order by lend_item.lend_item_id asc;";

	@Query(value = sqlALLUserLendItemJoinBookP, nativeQuery = true)
	List<AllUserLendList> sqlALLUserBookLendJoinP();

	//本一覧表示貸出不可
	static String sqlALLUserLendItemJoinBookI = "SELECT lend_item.lend_item_id,lend_item.library_id, book.genre_id, book.title, book.author, lend_item.status_id,lend_item.category_id "
			+ "FROM lend_item "
			+ "INNER JOIN book "
			+ "ON lend_item.any_id = book.book_id "
			+ "WHERE lend_item.category_id=1 AND lend_item.library_id=1 AND lend_item.status_id not in(1) "
			+ "order by lend_item.lend_item_id asc;";

	@Query(value = sqlALLUserLendItemJoinBookI, nativeQuery = true)
	List<AllUserLendList> sqlALLUserBookLendJoinI();

	//本一覧表示貸出可 検索
	static String sqlALLUserLendItemJoinBookPsearch = "SELECT lend_item.lend_item_id,lend_item.library_id, book.genre_id, book.title, book.author, lend_item.status_id,lend_item.category_id "
			+ "FROM lend_item "
			+ "INNER JOIN book "
			+ "ON lend_item.any_id = book.book_id "
			+ "WHERE lend_item.category_id=1 AND lend_item.library_id=1 AND lend_item.status_id=1 and book.title LIKE :search "
			+ "order by lend_item.lend_item_id asc;";

	@Query(value = sqlALLUserLendItemJoinBookPsearch, nativeQuery = true)
	List<AllUserLendList> sqlALLUserBookLendJoinPsearch(@Param("search") String search);
	
	

	//本一覧表示貸出不可 検索
	static String sqlALLUserLendItemJoinBookIsearch = "SELECT lend_item.lend_item_id,lend_item.library_id, book.genre_id, book.title, book.author, lend_item.status_id,lend_item.category_id "
			+ "FROM lend_item "
			+ "INNER JOIN book "
			+ "ON lend_item.any_id = book.book_id "
			+ "WHERE lend_item.category_id=1 AND lend_item.library_id=1 AND lend_item.status_id not in(1) and book.title LIKE :search "
			+ "order by lend_item.lend_item_id asc;";

	@Query(value = sqlALLUserLendItemJoinBookIsearch, nativeQuery = true)
	List<AllUserLendList> sqlALLUserBookLendJoinIsearch(@Param("search") String search);

	//CD一覧表示
	static String sqlALLUserLendItemJoinCD = "SELECT lend_item.lend_item_id, lend_item.library_id,cd.genre_id, cd.title, cd.author, lend_item.status_id "
			+ "FROM lend_item "
			+ "INNER JOIN cd "
			+ "ON lend_item.any_id = cd.cd_id "
			+ "WHERE lend_item.category_id=2 AND lend_item.library_id=1 "
			+ "order by lend_item.lend_item_id asc;";

	@Query(value = sqlALLUserLendItemJoinCD, nativeQuery = true)
	List<AllUserLendList> sqlALLUserCDLendJoin();

	//CD一覧検索
	static String sqlALLUserLendItemJoinCDSearch = "SELECT lend_item.lend_item_id, lend_item.library_id,cd.genre_id, cd.title, cd.author, lend_item.status_id "
			+ "FROM lend_item "
			+ "INNER JOIN cd "
			+ "ON lend_item.any_id = cd.cd_id "
			+ "WHERE lend_item.category_id=2 AND lend_item.library_id=1 and cd.title LIKE :search "
			+ "order by lend_item.lend_item_id asc;";

	@Query(value = sqlALLUserLendItemJoinCDSearch, nativeQuery = true)
	List<AllUserLendList> sqlALLUserCDLendJoinSearch(@Param("search") String search);

	//CD貸出可
	static String sqlALLUserLendItemJoinCDP = "SELECT lend_item.lend_item_id, lend_item.library_id,cd.genre_id, cd.title, cd.author, lend_item.status_id "
			+ "FROM lend_item "
			+ "INNER JOIN cd "
			+ "ON lend_item.any_id = cd.cd_id "
			+ "WHERE lend_item.category_id=2 AND lend_item.library_id=1 AND lend_item.status_id=1"
			+ "order by lend_item.lend_item_id asc;";

	@Query(value = sqlALLUserLendItemJoinCDP, nativeQuery = true)
	List<AllUserLendList> sqlALLUserCDLendJoinP();

	//CD貸出不可
	static String sqlALLUserLendItemJoinCDI = "SELECT lend_item.lend_item_id, lend_item.library_id,cd.genre_id, cd.title, cd.author, lend_item.status_id "
			+ "FROM lend_item "
			+ "INNER JOIN cd "
			+ "ON lend_item.any_id = cd.cd_id "
			+ "WHERE lend_item.category_id=2 AND lend_item.library_id=1 AND lend_item.status_id not in(1) "
			+ "order by lend_item.lend_item_id asc;";

	@Query(value = sqlALLUserLendItemJoinCDI, nativeQuery = true)
	List<AllUserLendList> sqlALLUserCDLendJoinI();

	//CD貸出可・検索
	static String sqlALLUserLendItemJoinCDPsearch = "SELECT lend_item.lend_item_id, lend_item.library_id,cd.genre_id, cd.title, cd.author, lend_item.status_id "
			+ "FROM lend_item "
			+ "INNER JOIN cd "
			+ "ON lend_item.any_id = cd.cd_id "
			+ "WHERE lend_item.category_id=2 AND lend_item.library_id=1 AND lend_item.status_id=1 and cd.title LIKE :search "
			+ "order by lend_item.lend_item_id asc;";

	@Query(value = sqlALLUserLendItemJoinCDPsearch, nativeQuery = true)
	List<AllUserLendList> sqlALLUserCDLendJoinPsearch(@Param("search") String search);
	

	//CD貸出不可・検索
	static String sqlALLUserLendItemJoinCDIsearch = "SELECT lend_item.lend_item_id, lend_item.library_id,cd.genre_id, cd.title, cd.author, lend_item.status_id "
			+ "FROM lend_item "
			+ "INNER JOIN cd "
			+ "ON lend_item.any_id = cd.cd_id "
			+ "WHERE lend_item.category_id=2 AND lend_item.library_id=1 AND lend_item.status_id not in(1) and cd.title LIKE :search"
			+ "order by lend_item.lend_item_id asc;";

	@Query(value = sqlALLUserLendItemJoinCDIsearch, nativeQuery = true)
	List<AllUserLendList> sqlALLUserCDLendJoinIsearch(@Param("search") String search);

	//DVD一覧表示
	static String sqlALLUserLendItemJoinDVD = "SELECT lend_item.lend_item_id,lend_item.library_id, dvd.genre_id, dvd.title, dvd.author, lend_item.status_id "
			+ "FROM lend_item "
			+ "INNER JOIN dvd "
			+ "ON lend_item.any_id = dvd.dvd_id "
			+ "WHERE lend_item.category_id=3 AND lend_item.library_id=1 "
			+ "order by lend_item.lend_item_id asc;";

	@Query(value = sqlALLUserLendItemJoinDVD, nativeQuery = true)
	List<AllUserLendList> sqlALLUserDVDLendJoin();

	//DVD検索
	static String sqlALLUserLendItemJoinDVDSearch = "SELECT lend_item.lend_item_id,lend_item.library_id, dvd.genre_id, dvd.title, dvd.author, lend_item.status_id "
			+ "FROM lend_item "
			+ "INNER JOIN dvd "
			+ "ON lend_item.any_id = dvd.dvd_id "
			+ "WHERE lend_item.category_id=3 AND lend_item.library_id=1 and dvd.title LIKE :search "
			+ "order by lend_item.lend_item_id asc;";

	@Query(value = sqlALLUserLendItemJoinDVDSearch, nativeQuery = true)
	List<AllUserLendList> sqlALLUserDVDLendJoinSearch(@Param("search") String search);

	//DVD貸出可
	static String sqlALLUserLendItemJoinDVDP = "SELECT lend_item.lend_item_id,lend_item.library_id, dvd.genre_id, dvd.title, dvd.author, lend_item.status_id "
			+ "FROM lend_item "
			+ "INNER JOIN dvd "
			+ "ON lend_item.any_id = dvd.dvd_id "
			+ "WHERE lend_item.category_id=3 AND lend_item.library_id=1 AND lend_item.status_id=1 "
			+ "order by lend_item.lend_item_id asc;";

	@Query(value = sqlALLUserLendItemJoinDVDP, nativeQuery = true)
	List<AllUserLendList> sqlALLUserDVDLendJoinP();

	//DVD貸出不可
	static String sqlALLUserLendItemJoinDVDI = "SELECT lend_item.lend_item_id,lend_item.library_id, dvd.genre_id, dvd.title, dvd.author, lend_item.status_id "
			+ "FROM lend_item "
			+ "INNER JOIN dvd "
			+ "ON lend_item.any_id = dvd.dvd_id "
			+ "WHERE lend_item.category_id=3 AND lend_item.library_id=1 AND lend_item.status_id not in(1) "
			+ "order by lend_item.lend_item_id asc;";

	@Query(value = sqlALLUserLendItemJoinDVDI, nativeQuery = true)
	List<AllUserLendList> sqlALLUserDVDLendJoinI();
	
	
	//DVD貸出可・検索
	static String sqlALLUserLendItemJoinDVDPSearch = "SELECT lend_item.lend_item_id,lend_item.library_id, dvd.genre_id, dvd.title, dvd.author, lend_item.status_id "
			+ "FROM lend_item "
			+ "INNER JOIN dvd "
			+ "ON lend_item.any_id = dvd.dvd_id "
			+ "WHERE lend_item.category_id=3 AND lend_item.library_id=1 AND lend_item.status_id=1 and dvd.title LIKE :search "
			+ "order by lend_item.lend_item_id asc;";

	@Query(value = sqlALLUserLendItemJoinDVDPSearch, nativeQuery = true)
	List<AllUserLendList> sqlALLUserDVDLendJoinPsearch(@Param("search") String search);
	
	
	//DVD貸出不可・検索
	static String sqlALLUserLendItemJoinDVDISearch = "SELECT lend_item.lend_item_id,lend_item.library_id, dvd.genre_id, dvd.title, dvd.author, lend_item.status_id "
			+ "FROM lend_item "
			+ "INNER JOIN dvd "
			+ "ON lend_item.any_id = dvd.dvd_id "
			+ "WHERE lend_item.category_id=3 AND lend_item.library_id=1 AND lend_item.status_id not in(1) and dvd.title LIKE :search "
			+ "order by lend_item.lend_item_id asc;";

	@Query(value = sqlALLUserLendItemJoinDVDISearch, nativeQuery = true)
	List<AllUserLendList> sqlALLUserDVDLendJoinIsearch(@Param("search") String search);
	
	
	
	
	//紙芝居一覧表示
	static String sqlALLUserLendItemJoinKamishibai = "SELECT lend_item.lend_item_id,lend_item.library_id, kamishibai.genre_id, kamishibai.title, kamishibai.author, lend_item.status_id "
			+ "FROM lend_item "
			+ "INNER JOIN kamishibai "
			+ "ON lend_item.any_id = kamishibai.kamishibai_id "
			+ "WHERE lend_item.category_id=4  AND lend_item.library_id=1 "
			+ "order by lend_item.lend_item_id asc;";

	@Query(value = sqlALLUserLendItemJoinKamishibai, nativeQuery = true)
	List<AllUserLendList> sqlALLUserKamishibaiLendJoin();
	
	//紙芝居検索
	static String sqlALLUserLendItemJoinKamishibaiSearch = "SELECT lend_item.lend_item_id,lend_item.library_id, kamishibai.genre_id, kamishibai.title, kamishibai.author, lend_item.status_id "
			+ "FROM lend_item "
			+ "INNER JOIN kamishibai "
			+ "ON lend_item.any_id = kamishibai.kamishibai_id "
			+ "WHERE lend_item.category_id=4  AND lend_item.library_id=1 and kamishibai.title LIKE :search "
			+ "order by lend_item.lend_item_id asc;";

	@Query(value = sqlALLUserLendItemJoinKamishibaiSearch, nativeQuery = true)
	List<AllUserLendList> sqlALLUserKamishibaiLendJoinSearch(@Param("search") String search);
	
	

	
	//紙芝居貸出可
	static String sqlALLUserLendItemJoinKamishibaiP = "SELECT lend_item.lend_item_id,lend_item.library_id, kamishibai.genre_id, kamishibai.title, kamishibai.author, lend_item.status_id "
			+ "FROM lend_item "
			+ "INNER JOIN kamishibai "
			+ "ON lend_item.any_id = kamishibai.kamishibai_id "
			+ "WHERE lend_item.category_id=4  AND lend_item.library_id=1 AND lend_item.status_id=1 "
			+ "order by lend_item.lend_item_id asc;";

	@Query(value = sqlALLUserLendItemJoinKamishibaiP, nativeQuery = true)
	List<AllUserLendList> sqlALLUserKamishibaiLendJoinP();
	
	//紙芝居貸出不可
	static String sqlALLUserLendItemJoinKamishibaiI = "SELECT lend_item.lend_item_id,lend_item.library_id, kamishibai.genre_id, kamishibai.title, kamishibai.author, lend_item.status_id "
			+ "FROM lend_item "
			+ "INNER JOIN kamishibai "
			+ "ON lend_item.any_id = kamishibai.kamishibai_id "
			+ "WHERE lend_item.category_id=4  AND lend_item.library_id=1 AND lend_item.status_id not in(1) "
			+ "order by lend_item.lend_item_id asc;";

	@Query(value = sqlALLUserLendItemJoinKamishibaiI, nativeQuery = true)
	List<AllUserLendList> sqlALLUserKamishibaiLendJoinI();
	
	
	//紙芝居貸出可・検索
	static String sqlALLUserLendItemJoinKamishibaiPsearch = "SELECT lend_item.lend_item_id,lend_item.library_id, kamishibai.genre_id, kamishibai.title, kamishibai.author, lend_item.status_id "
			+ "FROM lend_item "
			+ "INNER JOIN kamishibai "
			+ "ON lend_item.any_id = kamishibai.kamishibai_id "
			+ "WHERE lend_item.category_id=4  AND lend_item.library_id=1 AND lend_item.status_id=1 and kamishibai.title LIKE :search "
			+ "order by lend_item.lend_item_id asc;";

	@Query(value = sqlALLUserLendItemJoinKamishibaiPsearch, nativeQuery = true)
	List<AllUserLendList> sqlALLUserKamishibaiLendJoinPsearch(@Param("search") String search);
	
	//紙芝居貸出不可・検索
	static String sqlALLUserLendItemJoinKamishibaiIsearch = "SELECT lend_item.lend_item_id,lend_item.library_id, kamishibai.genre_id, kamishibai.title, kamishibai.author, lend_item.status_id "
			+ "FROM lend_item "
			+ "INNER JOIN kamishibai "
			+ "ON lend_item.any_id = kamishibai.kamishibai_id "
			+ "WHERE lend_item.category_id=4  AND lend_item.library_id=1 AND lend_item.status_id not in(1) and kamishibai.title LIKE :search "
			+ "order by lend_item.lend_item_id asc;";

	@Query(value = sqlALLUserLendItemJoinKamishibaiIsearch, nativeQuery = true)
	List<AllUserLendList> sqlALLUserKamishibaiLendJoinIsearch(@Param("search") String search);
}
