package com.sds.icto.mysite.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sds.icto.mysite.domain.BoardVo;
import com.sds.icto.mysite.repository.BoardDao;


@Service
public class BoardService {
	@Autowired
	BoardDao boardDao;
	
	public void addBoard(BoardVo vo){
		boardDao.insert(vo);
	}
	public void removeBoard(Long no){
		boardDao.delete(no);
	}
	public void modifyBoard(BoardVo vo){
		boardDao.update(vo);
	}
	public void modifyBoardViewcnt(BoardVo vo){
		boardDao.updateViewcnt(vo);
	}
	public BoardVo getBoard(Long no){
		BoardVo vo=boardDao.selectOne(no);
		return vo;
	}
	public List<BoardVo> getBoardList(){
		List<BoardVo> list=new ArrayList<BoardVo>();
		list=boardDao.selectList();
		return list;
	
	}
	public List<BoardVo> searchBoardList(String keyword){
		List<BoardVo> list=new ArrayList<BoardVo>();
		list=boardDao.selectByTitle(keyword);
		return list;
	}
}
