package kr.or.pets.protectPets.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import kr.or.pets.member.vo.MemberVO;
import kr.or.pets.protectPets.service.ProPetsService;
import kr.or.pets.protectPets.vo.ImageVO;
import kr.or.pets.protectPets.vo.ProPetsVO;
import net.sf.json.JSONObject;

@Controller("proPetsController")
@EnableAspectJAutoProxy	
public class ProPetsControllerImpl extends MultiActionController implements ProPetsController {

	private static final Logger logger = LoggerFactory.getLogger(ProPetsController.class);
	private static final String ARTICLE_IMAGE_FILE = "C:\\workspace-pets\\article_image";
	
	@Autowired
	private ProPetsService proPetsService;
	@Autowired
	private ProPetsVO proPetsVO;
	
	@Override
	@RequestMapping(value = "/protect/listBoards.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView listBoards(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String section_ = request.getParameter("section");
		String pageNum_ = request.getParameter("pageNum");
		
		int section = Integer.parseInt(((section_== null)? "1" : section_));
		int pageNum = Integer.parseInt(((pageNum_ == null)? "1" : pageNum_));
		
		Map<String, Integer> pagingMap = new HashMap<>();			/*section?????? pageNum?????? HashMap??? ??????*/
		pagingMap.put("section", section);
		pagingMap.put("pageNum", pageNum);
		
		Map boardMap = proPetsService.listBoards(pagingMap);
		boardMap.put("section", section);
		boardMap.put("pageNum", pageNum);
		
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("boardMap", boardMap);
		
		return mav;
	}

	//????????? ??? ????????????
	@RequestMapping(value = "/protect/addBoard.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity addNewArticle(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception {
		
		multipartRequest.setCharacterEncoding("UTF-8");
		
		Map articleMap = new HashMap();
		
		Enumeration enu = multipartRequest.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String)enu.nextElement();
			String value = multipartRequest.getParameter(name);
			articleMap.put(name, value);
		}
		
		//????????? ??? ????????? ????????? ?????? ???????????? ????????? ????????? ????????? Map??? ?????? ?????????.
		HttpSession session = multipartRequest.getSession();
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		String id = memberVO.getUserID();				//(????????? ??????????????????)
		articleMap.put("user_id", id);					//(????????? ??????????????????)
		
		//????????? ?????? ??????????????? ??????
		List<String> fileList = upload(multipartRequest);
		
		//(??????????????? ???????????? ??????) ????????? Map??? ?????? ?????????.
		List<ImageVO> imageFileList = new ArrayList<>();
		if (fileList != null && fileList.size() != 0) {
			for (String fileName : fileList) {
				ImageVO imageVO = new ImageVO();
				imageVO.setImageFileName(fileName);

				imageFileList.add(imageVO);
			}

			articleMap.put("ImageFileList", imageFileList);
		}
		
		// ?????? ????????? ??? ??????????????? ????????? ???????????? ????????????
		
		//alert??? ????????? ??????
	    HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.add("Content-Type", "text/html; charset=utf-8");
	    String message;
	    ResponseEntity responseEntity = null;
		String imageFileName = null;

	    try {

	     int articleNo = proPetsService.addNewArticle(articleMap);

	     if (imageFileList != null && imageFileList.size() != 0) {
	    	for (ImageVO  imageVO : imageFileList) {
	    		//2) ??? ?????? ??????????????????(temp)??? ?????? ???????????? ?????? ??? ????????? ??????
	    		imageFileName = imageVO.getImageFileName();

	    		File srcFile = new File(ARTICLE_IMAGE_FILE  +"\\"+  "temp"  +"\\"+  imageFileName);
	    		File destFile = new File(ARTICLE_IMAGE_FILE  +"\\"+ articleNo );

	    		FileUtils.moveFileToDirectory(srcFile, destFile, true);
	    	}
	     }

			  message = "<script>";
			  message += " alert('????????? ??????????????????.');";			  
			  //message += " location.href='        "+request.getContextPath()+"     /board/viewBoard.do?qa_No=qa_No                    ';    ";
			  //message += " location.href='        "+request.getContextPath()+"     /board/viewBoard.do?qa_No="
			  //		+ qa_No +   "';";
			  message += " location.href='"+multipartRequest.getContextPath()+"/protect/viewBoard.do?pro_boardNum="
				  		+ articleMap.get("pro_boardNum") +   "';";			  
			  message += " </script>";

			  responseEntity = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);	     


	    } catch (Exception e) {
	    	//???????????? ????????? ????????? ????????????
	    	if (imageFileList != null && imageFileList.size() != 0) {
	    		for (ImageVO imageVO : imageFileList) {
	    			imageFileName = imageVO.getImageFileName();

	    			File srcFile = new File(ARTICLE_IMAGE_FILE  +"\\"+  "temp"  +"\\"+  imageFileName);
	    			srcFile.delete();
	    		}
	    	}

			  message = "<script>";
			  message += " alert('????????? ??????????????????. ?????? ??????????????????.');";			  
			  //message += " location.href='        "+request.getContextPath()+"     /board/viewBoard.do?qa_No=qa_No                    ';    ";
			  //message += " location.href='        "+request.getContextPath()+"     /board/viewBoard.do?qa_No="
			  //		  + qa_No +   "';";
			  message += " location.href='"+multipartRequest.getContextPath()+"/protect/boardForm.do';";

			  message += " </script>";			  

			  responseEntity = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);

			  e.printStackTrace();
		}

		return responseEntity;
		
		
	}
	
	// ???????????? ??? ????????? ???????????????
		public List<String> upload(MultipartHttpServletRequest multipartRequest) throws Exception {
			List<String> fileList = new ArrayList<>();

			Iterator<String> fileNames = multipartRequest.getFileNames();				// ?????? ???????????? ??????
			while(fileNames.hasNext()) {
				String fileName = fileNames.next();
				MultipartFile mFile = multipartRequest.getFile(fileName);

				//?????? ?????? ??????(path) ???????????? ?????? ??????
				String originalFileName = mFile.getOriginalFilename();
				if (originalFileName != "" && originalFileName != null) {
					//db??? ??????????????? ?????????
					fileList.add(originalFileName);

					//fileSystem ??????????????? ?????????
					File file = new File(ARTICLE_IMAGE_FILE +"\\"+ fileName);
					if (mFile.getSize() != 0) {					//?????? null ??????
						if (!file.exists()) {					//???????????? ??????(????????? ??????????????????)
							file.getParentFile().mkdirs();		//????????? ???????????? ??????????????? ??????

							mFile.transferTo(new File(ARTICLE_IMAGE_FILE  +"\\"+ "temp" +"\\"+  originalFileName));			//1) ??? ?????? ??????????????????(temp)??? ????????????
						}
					}


				}



			}


			return fileList;
		}

	@RequestMapping(value = "/protect/removeBoard.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView removeBoard(@RequestParam("pro_boardNum") String pro_boardNum, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		proPetsService.removeBoard(pro_boardNum);
		ModelAndView mav = new ModelAndView("redirect:/protect/listBoards.do");
		
		return mav;
	}

	@Override
	@RequestMapping(value = "/protect/keywordSearch.do", method = RequestMethod.GET, produces = "application/text; charset=utf-8")
	public String keywordSearch(@RequestParam("keyword") String keyword, HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		if(keyword == null || keyword.equals(""))
			return null;
		
		keyword = keyword.toUpperCase();
		
		List<String> keywordList = proPetsService.keywordSearch(keyword);
		
		//?????? ?????????  JSONObject ??????
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("keyword", keywordList);
		
		String jsonInfo = jsonObject.toString();
		
		return jsonInfo;
	}

	@Override
	@RequestMapping(value = "/protect/searchBoards.do", method = RequestMethod.GET)
	public ModelAndView searchBoards(@RequestParam("searchWord") String searchWord, HttpServletRequest request, HttpServletResponse response)throws Exception {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String viewName = (String)request.getAttribute("viewName");
		List<ProPetsVO> boardsList = proPetsService.searchBoards(searchWord);
		
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("boardsList", boardsList);
		
		return mav;
	}
	
	@RequestMapping(value = "/protect/*Form.do", method = RequestMethod.GET)
	public ModelAndView form(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}
	
	@RequestMapping(value = "/protect/viewBoard.do", method = {RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView viewBoard(@RequestParam("pro_boardNum") int pro_boardNum, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		String viewName = (String)request.getAttribute("viewName");
		System.out.println("===============pro_boardNum: " + pro_boardNum);
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName(viewName);
		mav.addObject("vo", proPetsService.viewBoard(pro_boardNum));
		
		return mav;
	}
	
	

	@Override
	public ModelAndView removeBoard(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelAndView viewBoard(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
