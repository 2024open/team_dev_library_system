package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.demo.entity.Account;
import com.example.demo.entity.LendItem;
import com.example.demo.entity.LendingItem;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.LendItemRepository;
import com.example.demo.repository.LendingItemRepository;

@Service
public class LendProcessService {

	//サービス
	@Autowired
	LibrarianService librarianService;

	//リポジトリ
	@Autowired
	AccountRepository accountRepository;

	@Autowired
	LendItemRepository lendItemRepository;

	@Autowired
	LendingItemRepository lendingItemRepository;

	public String forLendProcess(Model model, Integer libraryId, Integer lendItemId, String title, String email) {
		LendItem updateItem = lendItemRepository.findById(lendItemId).get();

		if (updateItem.getStatusId() == 1) {
			//アカウント探し
			List<Account> tmpList = accountRepository.findByEmail(email);
			Account lenderAccount = new Account();
			if (tmpList.size() == 1) {
				lenderAccount = tmpList.get(0);
			} else {
				String errorMsg = "メールアドレスが間違っています";
				model.addAttribute("errorMsg", errorMsg);
				librarianService.forLendProcessIdSearch(lendItemId, libraryId, model);
				librarianService.forCategoryList(model);
				librarianService.forLibraryId(model, libraryId);
				return "librarianLendProcess";
			}

			//貸出物テーブルのステータス変更
			//貸出中テーブルに追加
			forLendExecute(updateItem, lenderAccount);

			model.addAttribute("msg", "貸出");
			model.addAttribute("lendItem", updateItem);
			model.addAttribute("title", title);
			librarianService.forLibraryId(model, libraryId);
			return "librarianLendProcessExecuted";

		} else if (updateItem.getStatusId() == 2) {

			//返却処理
			forReturnExecute(updateItem, lendItemId);

			model.addAttribute("msg", "返却");
			model.addAttribute("lendItem", updateItem);
			model.addAttribute("title", title);
			librarianService.forLibraryId(model, libraryId);
			return "librarianLendProcessExecuted";

		} else {
			String errorMsg = "貸出不可";

			model.addAttribute("errorMsg", errorMsg);
			model.addAttribute("lendItem", updateItem);
			model.addAttribute("title", title);
			librarianService.forLibraryId(model, libraryId);
			return "librarianLendProcessExecuted";
		}

	}

	//貸出処理
	//テーブル変更系
	public void forLendExecute(LendItem updateItem, Account lenderAccount) {
		//貸出物テーブルのステータス変更
		updateItem.setStatusId(2);
		updateItem = lendItemRepository.save(updateItem);

		//貸出中テーブルに追加
		LendingItem lendingItem = new LendingItem();
		lendingItem.setLendItemId(updateItem.getLendItemId());
		lendingItem.setUserId(lenderAccount.getUserId());
		lendingItem.setStatusId(updateItem.getStatusId());
		lendingItem.setBorrowedDate(LocalDate.now());
		lendingItem.setReturnDate(null); //返却日はnull
		lendingItemRepository.save(lendingItem);
	}

	//返却処理
	//テーブル変更系
	public void forReturnExecute(LendItem updateItem, Integer lendItemId) {
		//貸出物テーブルのステータス変更
		updateItem.setStatusId(1);
		updateItem = lendItemRepository.save(updateItem);

		//貸出中テーブルの更新
		List<LendingItem> returnItemList = lendingItemRepository.findByLendItemId(lendItemId);
		LendingItem returnItem = returnItemList.get(0);
		returnItem.setReturnDate(LocalDate.now());
		returnItem.setStatusId(1);
		lendingItemRepository.save(returnItem);
	}
}
