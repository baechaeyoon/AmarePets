package kr.or.pets.notice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.accept.ServletPathExtensionContentNegotiationStrategy;

import kr.or.pets.notice.dao.NoticeDAO;
import kr.or.pets.notice.vo.NoticeVO;

@Service("noticeService")
@Transactional(propagation = Propagation.REQUIRED)
public class NoticeServiceImpl implements NoticeService {
	
	@Autowired
	private NoticeDAO noticeDAO;
	
	public void setNoticeDAO(NoticeDAO noticeDAO) {
		this.noticeDAO = noticeDAO;
	}

	//01. 공지사항 전체 목록
	@Override
	public Map listNotices(Map<String, Integer> pagingMap) throws Exception {
		
		List<NoticeVO> noticesList = noticeDAO.selectAllNoticesList(pagingMap);
		int totNotices = noticeDAO.selectTotNotices();
		
		Map noticesMap = new HashMap();
		noticesMap.put("noticesList", noticesList);
		noticesMap.put("totNotices", totNotices);
		
		return noticesMap;
	}
	
	//02. 공지사항 등록
	@Override
	public int addNotice(NoticeVO noticeVO) throws DataAccessException {
		return noticeDAO.insertNotice(noticeVO);
	}
	
	//03. 공지사항 상세보기
	@Override
	public NoticeVO viewNotice(int no_number) throws DataAccessException {
		return noticeDAO.viewNotice(no_number);
	}

	
	//04. 공지사항 삭제
	@Override
	public int removeNotice(int no_number) throws DataAccessException {
		return noticeDAO.removeNotice(no_number);
	}

	//05-2. 공지사항 수정
	@Override
	public int modNotice(NoticeVO noticeVO) throws DataAccessException {
		System.out.println("====service");
		return noticeDAO.updateNotice(noticeVO);
		
	}


}
