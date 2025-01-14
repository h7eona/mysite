package mysite.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import mysite.repository.BoardRepository;
import mysite.vo.BoardVo;
import mysite.vo.UserVo;

@Service
public class BoardService {
	private BoardRepository boardRepository;
	
	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}
	
	public void addContents(BoardVo vo, UserVo authUser) {
		boardRepository.insert(vo, authUser);
	}
	
	public BoardVo getContents(Long id) {
		BoardVo vo = boardRepository.findById(id);
		boardRepository.increaseHit(vo);
		return vo;
	}
	
	public BoardVo getContents(Long id, Long userId) {
		return null;
	}
	
	public void updateContents(BoardVo vo) {
		boardRepository.update(vo);
	}
	
	public void deleteContents(Long id, Long userId) {
		BoardVo vo = boardRepository.findById(id);
		if(vo.getUserId() == userId) {
			boardRepository.deleteById(id);
		}
	}
	
	public Map<String, Object> getContentsList(int firstPage, int curPage, int lastPage, String keyword){
		// view의 pagination을 위한 데이터 값 계산
		
		Map<String, Integer> pageInfo = null;
		if(curPage == 1) {
			pageInfo = Map.of("firstPage", 1, "curPage", 1, "lastPage", 5);
		} else {
			pageInfo = Map.of("firstPage", firstPage, "curPage", curPage, "lastPage", lastPage);
		}
		
		List<BoardVo> list = boardRepository.findAll();
		return Map.of("pageInfo", pageInfo, "list", list);
	}
}
