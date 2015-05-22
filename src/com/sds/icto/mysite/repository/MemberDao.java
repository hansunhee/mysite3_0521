package com.sds.icto.mysite.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.sds.icto.mysite.domain.MemberVo;

@Repository
public class MemberDao {
	@Autowired
	SqlMapClientTemplate sqlMapClientTemplate;

	public void insert(MemberVo vo){
		sqlMapClientTemplate.insert("member.insert", vo);
	}	
	public MemberVo getMember(MemberVo vo){
		MemberVo member=null;
		member=(MemberVo)sqlMapClientTemplate.queryForObject("member.selectByEmailPassword", vo);
		return member;
	}
	public void update(MemberVo vo){
		sqlMapClientTemplate.update("member.update", vo);
	}
	@SuppressWarnings("unchecked")
	public List<MemberVo> selectList(){
		List<MemberVo> list=new ArrayList<MemberVo>();
		list=sqlMapClientTemplate.queryForList("member.selectList");
		return list;
	}
	public MemberVo selectByEmail(String email){
		MemberVo vo=null;
		vo=(MemberVo)sqlMapClientTemplate.queryForObject("member.selectByEmail", email);
		return vo;
	}
}
