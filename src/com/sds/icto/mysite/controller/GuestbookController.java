package com.sds.icto.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sds.icto.mysite.domain.GuestbookVo;
import com.sds.icto.mysite.service.GuestbookService;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {
	@Autowired
	GuestbookService guestbookService;
	
	@RequestMapping("/list")
	public String index(Model model){
		List<GuestbookVo> list=guestbookService.selectList();
		model.addAttribute("list", list);
		return "guestbook/list";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(@ModelAttribute GuestbookVo vo){
		guestbookService.add(vo);
		return "redirect:/guestbook/list";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String deleteform(@RequestParam String no, Model model){
		model.addAttribute("no", no);
		return "guestbook/deleteform";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(@RequestParam Long no, @RequestParam String password, Model model){
		GuestbookVo vo=guestbookService.selectOne(no);
		if(!vo.getPassword().equals(password)){
			model.addAttribute("msg", "비밀번호가 틀렸습니다.");
			model.addAttribute("no", no);
			return "guestbook/deleteform";
		}
		guestbookService.delete(no);			
		return "redirect:/guestbook/list";
	}
}
