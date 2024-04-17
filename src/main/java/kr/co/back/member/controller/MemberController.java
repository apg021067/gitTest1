package kr.co.back.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.back.member.service.MemberService;

@Controller
public class MemberController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	MemberService memberService;

	@RequestMapping(value = "/")
	public String home() {
		logger.info("/ Req");
		// **************************************************
		return "login";
	}

	@RequestMapping(value = "/join.go")
	public String joinGo() {
		logger.info("/join.go Req");
		return "joinForm";
	}

	@RequestMapping(value = "/checkDuplicateID.ajax", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Boolean> checkDuplicateID(String id) {
		logger.info("/checkDuplicateID.ajax Req | id = " + id);
		Map<String, Boolean> checkIdResult = new HashMap<String, Boolean>();
		checkIdResult.put("result", memberService.checkId(id));
		logger.info(memberService.checkId(id).toString());

		return checkIdResult;
	}

	@RequestMapping(value = "/join.do", method = RequestMethod.POST)
	public String join(@RequestParam Map<String, String> userJoinInfo, Model model) {
		logger.info("/join.do Req");
		String page = "joinForm";

		if (memberService.join(userJoinInfo)) {
			page = "login";
			logger.info("성공");
			model.addAttribute("msg", "회원가입 성공");
		} else {
			model.addAttribute("msg", "회원가입 실패");
			logger.info("실패");
		}

		return page;
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String login(String id, String pw, HttpSession session, Model model) {
		logger.info("/login.do Req|| id = {} / pw = {}", id, pw);
		String page = "login";
		String loginId = memberService.login(id, pw);
		logger.info(loginId);
		if (loginId != null) {
			page = "redirect:/list.go";
			session.setAttribute("loginId", loginId);
		} else {
			model.addAttribute("msg", "로그인실패");
		}
		return page;
	}
	

}