package com.jakduk.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jakduk.model.db.Encyclopedia;
import com.jakduk.model.db.FootballClubOrigin;
import com.jakduk.model.web.BoardCategoryWrite;
import com.jakduk.model.web.FootballClubWrite;
import com.jakduk.service.AdminService;

/**
 * @author <a href="mailto:phjang1983@daum.net">Jang,Pyohwan</a>
 * @company  : http://jakduk.com
 * @date     : 2014. 5. 1.
 * @desc     :
 */

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@RequestMapping
	public String root() {
		
		return "redirect:/admin/settings";
	}
	
	@RequestMapping("/settings")
	public String root(Model model,
			@RequestParam(required = false) String message,
			@RequestParam(required = false) String open) {
		
		model.addAttribute("message", message);
		
		if (open != null) {
			model.addAttribute("open", open);
		}
		
		return "admin/admin";
	}
	
	@RequestMapping(value = "/init")
	public String loginFailure() {

		String message = adminService.initData();
		
		return "redirect:/admin?message=" + message;
	}
	
	@RequestMapping(value = "/encyclopedia/write", method = RequestMethod.GET)
	public String encyclopediaWrite(Model model) {
		model.addAttribute("encyclopedia", new Encyclopedia());
		
		return "admin/encyclopediaWrite";
	}
	
	@RequestMapping(value = "/encyclopedia/write/{seq}", method = RequestMethod.GET)
	public String encyclopediaWrite(@PathVariable int seq, Model model,
			@RequestParam(required = true) String lang) {
		
		adminService.getEncyclopedia(model, seq, lang);
		
		return "admin/encyclopediaWrite";
	}
	
	@RequestMapping(value = "/encyclopedia/write", method = RequestMethod.POST)
	public String encyclopediaWriteSubmit(@Valid Encyclopedia encyclopedia, BindingResult result) {
		
		if (result.hasErrors()) {
			logger.debug("result=" + result);
			return "encyclopedia/write";
		}
		
		adminService.encyclopediaWrite(encyclopedia);
		
		return "redirect:/admin/settings?open=encyclopedia";
	}
	
	@RequestMapping(value = "/footballclub/write", method = RequestMethod.GET)
	public String footballClubWrite(Model model) {
		
		adminService.getFootballClubWrite(model);
		
		return "admin/footballClubWrite";
	}
	
	@RequestMapping(value = "/footballclub/write/{id}", method = RequestMethod.GET)
	public String footballClubWrite(@PathVariable String id, Model model) {
		
		adminService.getFootballClubWrite(model, id);
		
		return "admin/footballClubWrite";
	}
	
	@RequestMapping(value = "/footballclub/write", method = RequestMethod.POST)
	public String footballClubWrite(@Valid FootballClubWrite footballClubWrite, BindingResult result) {
		
		if (result.hasErrors()) {
			logger.debug("result=" + result);
			return "admin/footballClubWrite";
		}
		
		adminService.footballClubWrite(footballClubWrite);

		return "redirect:/admin/settings?open=fc";
	}
	
	@RequestMapping(value = "/footballclub/origin/write", method = RequestMethod.GET)
	public String footballClubOriginWrite(Model model) {
		model.addAttribute("footballClubOrigin", new FootballClubOrigin());
		
		return "admin/footballClubOriginWrite";
	}
	
	@RequestMapping(value = "/footballclub/origin/write/{id}", method = RequestMethod.GET)
	public String footballClubOriginWrite(@PathVariable String id, Model model) {
		
		adminService.getFootballClubOrigin(model, id);
		
		return "admin/footballClubOriginWrite";
	}
	
	@RequestMapping(value = "/footballclub/origin/write", method = RequestMethod.POST)
	public String footballClubOriginWrite(@Valid FootballClubOrigin footballClubOrigin, BindingResult result) {
		
		if (result.hasErrors()) {
			logger.debug("result=" + result);
			return "admin/footballClubOriginWrite";
		}
		
		adminService.footballClubOriginWrite(footballClubOrigin);

		return "redirect:/admin/settings?open=fcOrigin";
	}
	
	@RequestMapping(value = "/board/category/write", method = RequestMethod.GET)
	public String boardCategoryWrite(Model model) {
		model.addAttribute("boardCategoryWrite", new BoardCategoryWrite());
		
		return "admin/boardCategoryWrite";
	}
	
	@RequestMapping(value = "/board/category/write", method = RequestMethod.POST)
	public String boardCategoryWrite(@Valid BoardCategoryWrite boardCategoryWtite, BindingResult result) {
		
		if (result.hasErrors()) {
			logger.debug("result=" + result);
			return "admin/boardCategoryWrite";
		}
		
		adminService.boardCategoryWrite(boardCategoryWtite);

		return "redirect:/admin";
	}
	
	@RequestMapping(value = "/encyclopedia", method = RequestMethod.GET)
	public void encyclopedia(Model model) {
		
		adminService.getEncyclopediaList(model);
	}
	
	@RequestMapping(value = "/footballclub/origin", method = RequestMethod.GET)
	public void footballClubOrigin(Model model) {
		
		adminService.getFootballClubOriginList(model);
	}
	
	@RequestMapping(value = "/footballclub", method = RequestMethod.GET)
	public void footballClub(Model model) {
		
		adminService.getFootballClubList(model);
	}

}
