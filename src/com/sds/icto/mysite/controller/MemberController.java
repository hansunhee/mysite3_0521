package com.sds.icto.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sds.icto.mysite.domain.MemberVo;
import com.sds.icto.mysite.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String joinForm(){
		return "member/joinform";
	}
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(@ModelAttribute MemberVo vo){
		memberService.joinUser(vo);
		return "member/joinsuccess";
	}
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String loginForm(){
		return "member/loginform";
	}
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@ModelAttribute MemberVo vo, HttpSession session){
		MemberVo authMember=memberService.authUser(vo);
		if(authMember==null){
			return "redirect:/member/login?result=fail";
		}
		session.setAttribute("authMember", authMember);
		return "redirect:/index";
	}
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.removeAttribute("authMember");
		session.invalidate();
		return "redirect:/index";
	}
	@RequestMapping("/myinfo")
	public String myinfo(){
		return "member/myinfo";
	}
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(HttpSession session, @ModelAttribute MemberVo vo){
		MemberVo authMember=(MemberVo)session.getAttribute("authMember");
		if(authMember!=null){
			authMember.setName(vo.getName());
			authMember.setGender(vo.getGender());
			memberService.modifyMyinfo(authMember);
			return "redirect:/index";
		}else{
			return "redirect:/member/login";
		}
	}
}
