package kr.co.back.member.dao;

import java.util.Map;

public interface MemberDAO {

	String checkId(String id);

	boolean join(Map<String, String> userJoinInfo);

	String login(String id, String pw);

}
