package com.sds.icto.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sds.icto.mysite.domain.MemberVo;
import com.sds.icto.mysite.repository.MemberDao;

@Service
public class MemberService {
	
	@Autowired
	MemberDao memberDao;

	public void joinUser(MemberVo vo) {
		memberDao.insert(vo);
	}

	public MemberVo authUser(MemberVo vo) {
		return memberDao.getMember(vo);
	}
	
	public void modifyMyinfo(MemberVo vo){
		memberDao.update(vo);
	}
	public List<MemberVo> getList(){
		return memberDao.selectList();
	}
	public MemberVo getByEmail(String email){
		return memberDao.selectByEmail(email);
	}
}
