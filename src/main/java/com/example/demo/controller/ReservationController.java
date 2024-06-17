package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Category;
import com.example.demo.entity.Genre;
import com.example.demo.entity.LendItem;
import com.example.demo.entity.LendItemDetail;
import com.example.demo.entity.LendItemStatus;
import com.example.demo.entity.LendItemRoomDetail;
import com.example.demo.entity.Reservation;
import com.example.demo.entity.ReservationDetail;
import com.example.demo.entity.ReservationRoomDetail;
import com.example.demo.entity.Status;
import com.example.demo.model.SuperUser;
import com.example.demo.repository.ALLUserLendDetailRepoitory;
import com.example.demo.repository.ALLUserLendListRepoitory;
import com.example.demo.repository.ALLUserLendListStatusRepoitory;
import com.example.demo.repository.ALLUserLendRoomDetailRepoitory;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.GenreRepository;
import com.example.demo.repository.LendItemSaveRepository;
import com.example.demo.repository.ReservationDetailRepository;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.ReservationRoomDetailRepository;
import com.example.demo.repository.StatusRepository;

@Controller
public class ReservationController {
	@Autowired
	AccountRepository accountRepository;

	@Autowired
	ALLUserLendListRepoitory allUserLendListrepository;

	@Autowired
	ALLUserLendDetailRepoitory alluserLendDetailRepoitory;

	@Autowired
	ReservationRepository reservationRepoitory;

	@Autowired
	LendItemSaveRepository lendItemSaveRepository;

	@Autowired
	GenreRepository genreRepository;

	@Autowired
	StatusRepository statusRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	ReservationDetailRepository reservationDetailRepository;
	
	@Autowired
	ReservationRoomDetailRepository reservationRoomDetailRepository;

	@Autowired
	ALLUserLendRoomDetailRepoitory allUserLendRoomDetailRepoitory;

	@Autowired
	ALLUserLendListStatusRepoitory allUserLendListStatusRepoitory ;

	@Autowired
	SuperUser superUser;

	//予約一覧
	@GetMapping({ "/reservation" })
	public String index(@RequestParam(value = "category", defaultValue = "") Integer category,
			Model model) {

		Integer userId = superUser.getUserId();
		String titleMsg ="";

		if (category == null) {
			titleMsg = "本予約一覧";
			model.addAttribute("titleMsg", titleMsg);
			List<ReservationDetail> reservationBook = reservationDetailRepository.sqlReservationBookLendJoin(userId);
			model.addAttribute("reservation", reservationBook);
		} else if (category == 1) {
			titleMsg = "本予約一覧";
			model.addAttribute("titleMsg", titleMsg);
			List<ReservationDetail> reservationBook = reservationDetailRepository.sqlReservationBookLendJoin(userId);
			model.addAttribute("reservation", reservationBook);
		} else if (category == 2) {
			titleMsg = "CD予約一覧";
			model.addAttribute("titleMsg", titleMsg);
			List<ReservationDetail> reservationCD = reservationDetailRepository.sqlReservationCDLendJoin(userId);
			model.addAttribute("reservation", reservationCD);
		} else if (category == 3) {
			titleMsg = "DVD予約一覧";
			model.addAttribute("titleMsg", titleMsg);
			List<ReservationDetail> reservationDVD = reservationDetailRepository.sqlReservationDVDLendJoin(userId);
			model.addAttribute("reservation", reservationDVD);
		} else if (category == 4) {
			titleMsg = "紙芝居予約一覧";
			model.addAttribute("titleMsg", titleMsg);
			List<ReservationDetail> reservationKamishibai = reservationDetailRepository
					.sqlReservationKamishibaiLendJoin(userId);
			model.addAttribute("reservation", reservationKamishibai);
		} else if (category == 5) {
			titleMsg = "貸会議室予約一覧";
			model.addAttribute("titleMsg", titleMsg);
			List<ReservationRoomDetail> reservationRoom = reservationRoomDetailRepository
					.sqlReservationRoomLendJoin(userId);
			model.addAttribute("reservation", reservationRoom);
		}

		
		model.addAttribute("titleMsg",titleMsg);
		model.addAttribute("category",category);
		
		Map<Integer, String> categoryMap = new HashMap<>();

		List<Category> categoryMapList = categoryRepository.findAll();

		//Mapで格納
		for (Category categoryList : categoryMapList) {

			categoryMap.put(categoryList.getCategoryId(), categoryList.getCategoryName());

		}

		model.addAttribute("categoryMap", categoryMap);


		return "reservationList";
	}

	//予約
	@PostMapping({ "/reservation/add" })
	public String reserve(
			@RequestParam(value = "reservation", defaultValue = "") Integer reservation,
			@RequestParam(value = "category", defaultValue = "") Integer category,
			Model model) {
		LendItemDetail lendItemDetail = null;

		LendItemRoomDetail lendItemRoomDetail = null;
		
		if (category == null) {
			lendItemDetail = alluserLendDetailRepoitory.sqlALLUserBookLendJoinDetail(reservation).get(0);
		} else if (category == 1) {
			lendItemDetail = alluserLendDetailRepoitory.sqlALLUserBookLendJoinDetail(reservation).get(0);
		} else if (category == 2) {
			lendItemDetail = alluserLendDetailRepoitory.sqlALLUserCDLendJoinDetail(reservation).get(0);
		} else if (category == 3) {
			lendItemDetail = alluserLendDetailRepoitory.sqlALLUserDVDLendJoinDetail(reservation).get(0);
		} else if (category == 4) {
			lendItemDetail = alluserLendDetailRepoitory.sqlALLUserKamishibaiLendJoinDetail(reservation).get(0);
		} else if (category == 5) {
			lendItemRoomDetail = allUserLendRoomDetailRepoitory.sqlALLUserRoomLendJoinDetail(reservation).get(0);
		}

		LendItem lenditem = lendItemSaveRepository.findByLendItemId(reservation);

		Integer userId = superUser.getUserId();

		Integer status = 3;

		if (lenditem.getStatusId() == 1) {
			List<ReservationDetail> reservationbook = reservationDetailRepository.sqlReservationBookLendJoin(userId);
			List<ReservationDetail> reservationcd = reservationDetailRepository.sqlReservationCDLendJoin(userId);
			List<ReservationDetail> reservationdvd = reservationDetailRepository.sqlReservationDVDLendJoin(userId);
			List<ReservationDetail> reservationkamishibai = reservationDetailRepository.sqlReservationKamishibaiLendJoin(userId);
			List<ReservationDetail> reservationerr = reservationDetailRepository.sqlReservationLendJoin(userId);
			if (reservationerr.size() < 5 && reservationkamishibai.size() <5 && reservationdvd.size()<5 && reservationcd.size()<5 && reservationbook.size()<5) {
				if (lendItemRoomDetail == null) {
					Reservation reserv = new Reservation(lendItemDetail.getLendItemId(), userId);

					LendItem lenditemsave = new LendItem(reservation, lenditem.getLibraryid(), lenditem.getCategoryId(),
							localdatetime, status, lenditem.getAnyId());

					lendItemSaveRepository.save(lenditemsave);

				LendItem lenditemsave = new LendItem(reservation, lenditem.getLibraryid(), lenditem.getCategoryId(),
						LocalDateTime.now(), status, lenditem.getAnyId());
					reservationRepoitory.save(reserv);
					return "redirect:/lendItems";
				} else {
					Reservation reservRoom = new Reservation(lendItemRoomDetail.getLendItemId(), userId);

					LendItem lenditemsave = new LendItem(reservation, lenditem.getLibraryid(), lenditem.getCategoryId(),
							localdatetime, status, lenditem.getAnyId());

					lendItemSaveRepository.save(lenditemsave);

					reservationRepoitory.save(reservRoom);
					return "redirect:/lendItems";
				}
			} else {
				Map<Integer, String> genreMap = new HashMap<>();

				List<Genre> genreMapList = genreRepository.findAll();

				//Mapで格納
				for (Genre genre : genreMapList) {

					genreMap.put(genre.getGenreId(), genre.getGenreName());

				}

				Map<Integer, String> categoryMap = new HashMap<>();

				List<Category> categoryMapList = categoryRepository.findAll();

				//Mapで格納
				for (Category categoryList : categoryMapList) {

					categoryMap.put(categoryList.getCategoryId(), categoryList.getCategoryName());

				}

				Map<Integer, String> statusMap = new HashMap<>();

				List<Status> statusMapList = statusRepository.findAll();

				//Mapで格納
				for (Status statusM : statusMapList) {

					statusMap.put(statusM.getStatusId(), statusM.getStatusName());

				}

				model.addAttribute("categoryMap", categoryMap);
				model.addAttribute("genreMap", genreMap);
				model.addAttribute("statusMap", statusMap);

				String msg = "予約上限です";
				model.addAttribute("msg", msg);
				model.addAttribute("lendItemDetail", lendItemDetail);
				return "lendItemDetail";
			}
		} else {

			Map<Integer, String> genreMap = new HashMap<>();

			List<Genre> genreMapList = genreRepository.findAll();

			//Mapで格納
			for (Genre genre : genreMapList) {

				genreMap.put(genre.getGenreId(), genre.getGenreName());

			}

			Map<Integer, String> categoryMap = new HashMap<>();

			List<Category> categoryMapList = categoryRepository.findAll();

			//Mapで格納
			for (Category categoryList : categoryMapList) {

				categoryMap.put(categoryList.getCategoryId(), categoryList.getCategoryName());

			}

			Map<Integer, String> statusMap = new HashMap<>();

			List<Status> statusMapList = statusRepository.findAll();

			//Mapで格納
			for (Status statusM : statusMapList) {

				statusMap.put(statusM.getStatusId(), statusM.getStatusName());

			}

			model.addAttribute("categoryMap", categoryMap);
			model.addAttribute("genreMap", genreMap);
			model.addAttribute("statusMap", statusMap);
			String msg = "この貸出物は予約できません";
			model.addAttribute("msg", msg);
			model.addAttribute("lendItemDetail", lendItemDetail);
			return "lendItemDetail";
		}

	}
	
	@PostMapping({ "/reservation/{Id}/delete" })
	public String delete(
			@PathVariable("Id") Integer id,
			Model model) {
		
		LendItemStatus itemId = allUserLendListStatusRepoitory.sqlALLUserLendItemStatus(id);
		
		LendItem lenditem = lendItemSaveRepository.findByLendItemId(itemId.getLendItemId());
		
		LendItem lenditemsave = new LendItem(itemId.getLendItemId(), lenditem.getLibraryid(), lenditem.getCategoryId(),
				LocalDateTime.now(), 1, lenditem.getAnyId());
		
		lendItemSaveRepository.save(lenditemsave);
		
		reservationRepoitory.deleteById(id);
		
		return "redirect:/reservation";
	}

}
