package kr.co.back.member.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.back.member.dao.MemberDAO;

@Service
public class MemberService {
	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	MemberDAO memberDAO;

	public Boolean checkId(String id) {
		String checkId = memberDAO.checkId(id);
		return checkId == null;
	}

	public boolean join(Map<String, String> userJoinInfo) {
		try {
			return memberDAO.join(userJoinInfo);
		} catch (Exception e) {
			return false;
		}
	}

	public String login(String id, String pw) {
		String loginid = memberDAO.login(id, pw);
		return loginid;
	}
}
