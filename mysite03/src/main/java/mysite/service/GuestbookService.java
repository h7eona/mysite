package mysite.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mysite.repository.GuestbookLogRepository;
import mysite.repository.GuestbookRepository;
import mysite.vo.GuestbookVo;

@Service
public class GuestbookService {
	private GuestbookRepository guestbookRepository;
	private GuestbookLogRepository guestbookLogRepository;
	
	public GuestbookService(GuestbookRepository guestbookRepository, GuestbookLogRepository guestbookLogRepository) {
		this.guestbookRepository = guestbookRepository;
		this.guestbookLogRepository = guestbookLogRepository;
	}
	
	public List<GuestbookVo> getContentsList() {
		return guestbookRepository.findAll();
	}
	
	@Transactional
	public void deleteContents(Long id, String password) {
		guestbookRepository.deleteByIdAndPassword(id, password);
	}
	
	@Transactional
	public void addContents(GuestbookVo vo) {
		guestbookRepository.insert(vo);
	}
}
