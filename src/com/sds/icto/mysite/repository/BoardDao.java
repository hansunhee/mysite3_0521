package com.sds.icto.mysite.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.sds.icto.mysite.domain.BoardVo;

@Repository
public class BoardDao {
	@Autowired
	SqlMapClientTemplate sqlMapClientTemplate;
	
	public void insert(BoardVo vo){
		sqlMapClientTemplate.insert("board.insert", vo);
	}
	public void delete(Long no){
		sqlMapClientTemplate.delete("board.delete", no);
	}
	public void update(BoardVo vo){
		sqlMapClientTemplate.update("board.update", vo);
	}
	public void updateViewcnt(BoardVo vo){
		sqlMapClientTemplate.update("board.updateViewcnt", vo);
	}
	public BoardVo selectOne(Long no){
		BoardVo vo=(BoardVo)sqlMapClientTemplate.queryForObject("board.selectOne", no);
		return vo;
	}
	@SuppressWarnings("unchecked")
	public List<BoardVo> selectList(){
		List<BoardVo> list=new ArrayList<BoardVo>();
		list=sqlMapClientTemplate.queryForList("board.selectList");
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<BoardVo> selectByTitle(String keyword){
		List<BoardVo> list=new ArrayList<BoardVo>();
		list=sqlMapClientTemplate.queryForList("board.selectByTitle", keyword);
		return list;
	}
}
