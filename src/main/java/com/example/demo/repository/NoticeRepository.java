package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Notice;

public interface NoticeRepository extends JpaRepository<Notice,Integer>{

	public List<Notice> findByNoticeIdAndLibraryId(Integer noticeId,Integer libraryid);

}
