package kr.co.back.board.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.back.board.dao.BoardDAO;
import kr.co.back.board.dto.BoardDTO;

@Service
public class BoardService {
	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	BoardDAO boardDAO;

	public List<BoardDTO> callList(int max) {
		return boardDAO.callList(max);
	}

}
