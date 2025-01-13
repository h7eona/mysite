package mysite.service;

import org.springframework.stereotype.Service;

import mysite.repository.SiteRepository;
import mysite.vo.SiteVo;

@Service
public class SiteService {
	private final SiteRepository siteRepository;
	
	public SiteService(SiteRepository siteRepository) {
		this.siteRepository = siteRepository;
	}
	
	public SiteVo getSite(Long id) {
		return siteRepository.find(id);
	}
	
	public void updateSite(SiteVo vo) {
		siteRepository.update(vo);
	}
}
