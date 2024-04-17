package kr.co.back.board.dao;

import java.util.List;

import kr.co.back.board.dto.BoardDTO;

public interface BoardDAO {

	List<BoardDTO> callList(int max);

}
