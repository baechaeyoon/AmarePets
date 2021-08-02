package kr.or.pets.missPets.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import kr.or.pets.missPets.vo.MissVO;


@Repository("missDAO")
public class MissDAOImpl implements MissDAO {
	private static final Logger logger = LoggerFactory.getLogger(MissDAOImpl.class);

	@Autowired
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public List<MissVO> selectAllBoardList(Map<String, Integer> pagingMap) {
		List<MissVO> m_boardsList = sqlSession.selectList("mapper.miss.selectAllBoardList", pagingMap);
		return m_boardsList;
	}

	@Override
	public int selectTotBoards() {
		int totBoards = sqlSession.selectOne("mapper.miss.selectTotBoards");
		return totBoards;
	}

	@Override
	public int deleteBoard(int miss_boardNum) {
		int result = sqlSession.delete("mapper.miss.deleteBoard", miss_boardNum);
		return result;
	}

	@Override
	public int insertBoard(Map missMap) {
		int miss_boardNum = selectNewMiss_boardNum();
		missMap.put("miss_boardNum", miss_boardNum);
		sqlSession.insert("mapper.miss.insertBoard", missMap);
		return miss_boardNum;
	}
	
	public int selectNewMiss_boardNum() throws DataAccessException {
		return sqlSession.selectOne("mapper.miss.selectNewMiss_boardNum");
	}

	@Override
	public MissVO viewBoard(int miss_boardNum) {
		return sqlSession.selectOne("mapper.miss.viewBoard", miss_boardNum);
	}

	@Override
	public void updateBoard(Map<String, Object> missMap) {
		sqlSession.update("mapper.miss.updateBoard", missMap);
		
	}

	@Override
	public List<String> selectKeywordSearch(String keyword) {
		List<String> list = sqlSession.selectList("mapper.miss.selectKeywordSearch", keyword);
		
		return list;
	}

	@Override
	public List<MissVO> selectBoardsBySearchWord(String searchWord) {
		ArrayList<MissVO> list = (ArrayList)sqlSession.selectList("mapper.miss.selectBoardsBySearchWord", searchWord);
		
		return list;
	}
}
