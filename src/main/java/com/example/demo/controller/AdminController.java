package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Account;
import com.example.demo.entity.Category;
import com.example.demo.entity.Genre;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.GenreRepository;

@Controller
public class AdminController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
	GenreRepository genreRepository;

	@Autowired
	CategoryRepository categoryRepository;
	
    @GetMapping("/admin/lenditems")
    public String lendItem() {
        // 処理は後で書く
        return "";
    }

    @GetMapping("/admin/home")
    public String home() {
        // 処理は後で書く
        return "";
    }

    // 管理者利用者情報管理画面
    @GetMapping("/admin/accountManager")
    public String index(
            @RequestParam(value = "userName", defaultValue = "%") String userName,
            Model model) {
        List<Account> accountList;
        
        if (userName != null) {
            accountList = accountRepository.findByUserNameLike("%" + userName + "%");
        } else {
            accountList = accountRepository.findAll();
        }
        model.addAttribute("accountList", accountList);
        return "accountManager";
    }
    @PostMapping("/admin/ban")
    public String ban() {
        // 処理は後で書く
        return "accountManager";
    }
    
 // ジャンルの表示
 	@GetMapping("/admin/genre")
 	public String genre(
 			@RequestParam(value = "message",defaultValue = "") String message,
 			@RequestParam(value = "categoryId",defaultValue = "") Integer categoryId,
 			Model model) {
 		


 		Map<Integer, String> categoryMap = new HashMap<>();
 		
 		
 		List<Genre> genreList = null;
 		
 		
 		if(categoryId == null) {
 			genreList=genreRepository.findByDeletedFalseOrderByCategoryIdAsc();
 		}else {
 			genreList=genreRepository.findByDeletedFalseAndCategoryId(categoryId);
 		}
 		
 		List<Category> categoryList = categoryRepository.findAll();
 		categoryList.remove(4);
 		model.addAttribute("categoryList", categoryList);

 		// Mapに格納
 		for (Category category : categoryList) {
 			categoryMap.put(category.getCategoryId(), category.getCategoryName());
 		}

 		model.addAttribute("message", message);
 		model.addAttribute("genreList", genreList);		
 		model.addAttribute("categoryMap", categoryMap);
 		
 		return "genre";
 	}

 	
 	
 	
 	//ジャンルの追加
 	@PostMapping("/admin/genre/add")
 	public String genreAdd(
 			@RequestParam(value = "categoryId") String strCategoryId,
 			@RequestParam(value = "genreName", defaultValue = "") String genreName,
 			Model model,
 			RedirectAttributes redirectAttributes) {

 		int categoryId = Integer.parseInt(strCategoryId);

 		//    	エラーチェック
 		List<Genre> genreList = genreRepository.findByCategoryIdAndGenreName(categoryId, genreName);

 		if ( genreName.isBlank() ) {
 			redirectAttributes.addFlashAttribute("message","ジャンル名を入力してください");
 			return "redirect:/admin/genre";
 			}
 			
 		if (!genreList.isEmpty()) {
 			redirectAttributes.addFlashAttribute("message","既に存在するジャンルです");
 			return "redirect:/admin/genre";
 		}

 		//	ジャンルの初期設定
 		Boolean deleted = false;

 		// Genreオブジェクトの生成
 		Genre newGenre = new Genre(genreName, categoryId, deleted);

 		// genreテーブルへの反映（INSERT）
 		genreRepository.save(newGenre);
 		return "redirect:/admin/genre";

 	}
}
