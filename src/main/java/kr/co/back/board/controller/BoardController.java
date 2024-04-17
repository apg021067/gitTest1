package kr.co.back.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.back.board.dto.BoardDTO;
import kr.co.back.board.service.BoardService;

@Controller
public class BoardController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	BoardService boardService;

	@RequestMapping(value = "/list.go")
	public String listGo(HttpSession session) {
		logger.info("/list.go Req");
		
		String page = "redirect:/";
		
		if (session.getAttribute("loginId") != null) {
			page = "list";
		}
		
		return page;
	}

	@RequestMapping(value = "/callList.ajax")
	@ResponseBody
	public Map<String, Object> callList(String max) {
		logger.info("/callList.ajax | max = {}",max);
		
		Map<String, Object> result = new HashMap<String, Object>();
		List<BoardDTO> boardList = boardService.callList(Integer.parseInt(max));
		logger.info("/callList.ajax | list = {}",boardList);
		result.put("list", boardList);
		logger.info("/callList.ajax | map = {}",result);
		
		return result;
	}
	
}
