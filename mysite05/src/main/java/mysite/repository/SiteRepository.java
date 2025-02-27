package mysite.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import mysite.vo.SiteVo;

@Repository
public class SiteRepository {
	private final SqlSession sqlSession;
	
	public SiteRepository(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public SiteVo find(Long id) {
		return sqlSession.selectOne("site.find-site", id);
	}

	public void update(SiteVo vo) {
		sqlSession.update("site.update-site", vo);
	}

}
