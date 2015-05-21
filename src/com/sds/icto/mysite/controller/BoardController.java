package com.sds.icto.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sds.icto.mysite.domain.BoardVo;
import com.sds.icto.mysite.domain.MemberVo;
import com.sds.icto.mysite.service.BoardService;
import com.sds.icto.mysite.service.MemberService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@Autowired
	MemberService memberService;
	
	
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String insertform(){
		return "board/insertform";
	}

	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(@ModelAttribute BoardVo vo, @RequestParam String password, HttpSession session){
		MemberVo authMember=(MemberVo)session.getAttribute("authMember");
		authMember.setPassword(password);
		MemberVo member=memberService.authUser(authMember);
		if(member==null){
			return "board/insertformerror";
		}else{
			BoardVo board=new BoardVo(vo.getTitle(), vo.getContent(), authMember.getNo(), authMember.getName());
			boardService.addBoard(board);
			return "redirect:/board/list";
		}
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(@ModelAttribute BoardVo vo){
		BoardVo board=boardService.getBoard(vo.getNo());
		board.setTitle(vo.getTitle());
		board.setContent(vo.getContent());
		boardService.modifyBoard(vo);
		return "redirect:/board/list";
	}

	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String delete(@RequestParam long no){
		boardService.removeBoard(no);
		return "redirect:/board/list";
	}

	@RequestMapping(value="/view", method=RequestMethod.GET)
	public String view(@RequestParam long no, Model model, HttpSession session){
		BoardVo vo=new BoardVo();
		System.out.println("no: "+no);
		vo.setNo(no);
		boardService.modifyBoardViewcnt(vo);
		BoardVo board=boardService.getBoard(no);		
		System.out.println("board: "+board);
		model.addAttribute("vo", board);
		MemberVo authMember=(MemberVo)session.getAttribute("authMember");
		if(authMember.getNo()==board.getMemberno()){
			return "board/updateform";
		}else{
			return "board/view";
		}
	}
	
	@RequestMapping(value="/list")
	public String list(Model model){
		List<BoardVo> list=boardService.getBoardList();
		model.addAttribute("list", list);
		return "board/list";
	}
	
	@RequestMapping(value="/search", method=RequestMethod.POST)
	public String search(@RequestParam("search") String keyword, Model model){
		List<BoardVo> list=boardService.searchBoardList(keyword);
		model.addAttribute("list", list);
		return "board/list";
	}
}
