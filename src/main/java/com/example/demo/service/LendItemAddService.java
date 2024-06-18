package com.example.demo.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.demo.entity.LendItem;
import com.example.demo.entity.LendItemForm;
import com.example.demo.repository.LendItemRepository;

@Service
public class LendItemAddService {

	//サービス
	@Autowired
	LibrarianService librarianService;

	@Autowired
	CategoryService categoryService;

	//リポジトリ
	@Autowired
	LendItemRepository lendItemRepository;

	//貸出物新規登録用
	//LendItem(categoryId, anyId, statusId)
	//th:object用のLendItemFormとデータset用のカテゴリーリストとステータスリストを渡す
	public void forLendItemForm(Model model, Integer categoryId, String address) {
		LendItemForm lendItemForm = new LendItemForm();
		model.addAttribute(address, lendItemForm);
		model.addAttribute("categoryId", categoryId);

		categoryService.forCategoryDataList(model, categoryId);
		librarianService.forStatusList(model);
	}

	//貸出物新規登録処理
	public void forLendItemFormStore(Integer categoryId, Integer libraryId, LendItemForm lendItemForm) {
		LendItem lendItem = new LendItem();
		lendItem.setLibraryid(libraryId);
		lendItem.setCategoryId(categoryId);
		lendItem.setCreateDate(LocalDateTime.now());
		lendItem.setStatusId(lendItemForm.getStatusId());
		lendItem.setAnyId(lendItemForm.getAnyId());
		lendItem.setDeleted(false);
		lendItemRepository.save(lendItem);
	}
}
