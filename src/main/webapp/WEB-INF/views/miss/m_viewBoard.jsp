<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	request.setCharacterEncoding("UTF-8");
%>      
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="miss" value="${missMap.miss}" />	<!-- board는 boardVO를 뜻함 -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet" >
	<link href="${pageContext.request.contextPath}/resources/css/kfonts2.css" rel="stylesheet" >
<title>상세조회 페이지</title>
<style type="text/css">
		input[type=radio] { vertical-align:middle; }
		#tr_btn_modify {
			display: none;
		}
		.tr_modEnable {
			visibility: hidden;
		}
		table{
		     width: 60%;
		    margin-left: auto;
		    margin-right: auto;
		    border-collapse: collapse;
		    line-height: 19px;
		}
		th {
		    border-top:1px solid black;
		    border-bottom:1px solid black;
		    border-collapse: collapse;
		    text-align: center;
		    padding: 13px;
		    background: rgb(221, 221, 221);
		}
		td{
			border-bottom: 1px solid gray;
			padding : 10px;
		}
	</style>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">		
	
	function fn_enable() {
		document.getElementById("qa_title_mod").disabled=false;
		document.getElementById("qa_content_mod").disabled=false;
		document.getElementById("qa_category_mod").disabled=false;
		document.getElementById("qa_secret_mod").disabled=false;
		document.getElementById("tr_btn_modify").style.display="block";
		document.getElementById("tr_btn").style.display="none";
		$(".tr_modEnable").css("visibility", "visible");
	}
	
	function backToList(obj) {
		obj.action = "${contextPath}/miss/m_listBoards.do"
		obj.submit();
	}
	function removeList(obj) {
		obj.action = "${contextPath}/miss/m_removeBoard.do?qa_No=${board.qa_No}"
		obj.submit();
	}
	
	function fn_modify_article(obj) {
		obj.action="${contextPath}/miss/m_modBoard.do";
		obj.submit();
	}
	
</script>
</head>
<body>

	<form action="${contextPath}/miss/m_addBoard.do" enctype="multipart/form-data" method="post">
		<h1 class="text_center">보호동물 등록</h1>
		<table align="center">
			<tr>
				<td width="200"><p align="right">동물 이름</td>
				<td width="400"><input type="text" name="missName" value="${miss.missName}" disabled /></td>
			</tr>
			<tr>
				<td width="200"><p align="right">작성자</td>
				<td width="400"><input type="text" name="userNAME" value="${miss.userNAME}" disabled /></td>
			</tr>
			<tr>
				<td width="200"><p align="right">작성자 이메일</td>
				<td width="400"><input type="text" name="userEmail" value="${miss.userEmail}" disabled /></td>
			</tr>
			<tr>
				<td width="200"><p align="right">작성자 주소</td>
				<td width="400"><input type="text" name="userAddress" value="${miss.userAddress}" disabled /></td>
			</tr>
			<tr>
				<td width="200"><p align="right">작성자 전화번호</td>
				<td width="400"><input type="text" name="userPhoneNumber" value="${miss.userPhoneNumber}" disabled /></td>
			</tr>
			<tr>
				<td width="200"><p align="right">품종</td>
				<td width="400"><input type="text" name="missKind" value="${miss.missKind}" disabled /></td>
			</tr>
			<tr>
				<td width="200"><p align="right">성별</td>
				<td width="400">
					<input type="text" name="missGender" value="${miss.missGender}" disabled/>
				</td>
			</tr>
			<tr>
				<td width="200"><p align="right">나이</td>
				<td width="400"><input type="text" name="missAge" value="${miss.missAge}" disabled /></td>
			</tr>
			<tr>
				<td width="200"><p align="right">실종장소</td>
				<td width="400"><input type="text" name="missPlace" value="${miss.missPlace}" disabled /></td>
			</tr>
			<tr>
				<td width="200"><p align="right">실종일자</td>
				<td width="400"><input type="text" name="missMissdate" value="${miss.missMissdate}"  disabled /></td>
			</tr>
			<tr>
				<td width="200"><p align="right">특징</td>
				<td width="400"><input type="text" name="missCharacter" value="${miss.missCharacter}" disabled /></td>
			</tr>
			<tr>
				<td width="200"><p align="right">등록번호</td>
				<td width="400"><input type="text" name="missRegistNum" value="${miss.missRegistNum}" disabled /></td>
			</tr>
			<tr>
				<td width="200"><p align="right">이미지대신</td>
				<td width="400"><input type="text" name="missImg" value="${miss.missImg}" disabled /></td>
			</tr>
			<!-- <tr>
				<td width="200"><p align="right">미리보기</td>
				<td width="400"><img alt="미리보기" src="#" width="200" height="200" id="preview0" /></td>
			</tr>
			<tr>
				<td width="200"><p align="right">사진</td>
				<td><input type="file" name="imageFileName" onchange="readURL(this, 0)" /></td><br/>
			</tr> -->
			<tr>
				<td width="200"><p align="right">아이디</td>
				<td width="400"><input type="text" name="userID" value="${miss.userID}" disabled /></td>
			</tr>
				<tr id="tr_btn_modify" align="right">
				<td colspan="2">
					<input type="button" class="btn btn-success" value="수정반영하기" onclick="fn_modify_article(frmBoard)" />
					<input type="button" class="btn btn-danger" value="취소" onclick="backToList(frmBoard)" />
				</td>
			</tr>
			
			<tr id="tr_btn">
				<td colspan="3" align="center">
				<c:if test="${member.userID == board.userID }">
					<input type="button" class="btn btn-warning" value="수정하기" onclick="fn_enable()" />
					<input type="button" class="btn btn-danger" value="삭제하기" onclick="removeList(this.form)">
					</c:if>
					<input type="button" class="btn btn-primary" value="게시글목록" onclick="backToList(this.form)">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>