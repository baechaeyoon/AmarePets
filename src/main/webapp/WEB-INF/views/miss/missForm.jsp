<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
	request.setCharacterEncoding("UTF-8");
%>          
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet" >
<link href="${pageContext.request.contextPath}/resources/css/kfonts2.css" rel="stylesheet" >
<title>게시글 작성</title>
<style type="text/css">
		.text_center {
			text-align: center;
		}
		table{
		    width: 60%;
		    margin-left: auto;
		    margin-right: auto;
		    border-collapse: collapse;
		    line-height: 10px;
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
	<script type="text/javascript">
	function backToList(obj) {
		obj.action = "${contextPath}/miss/m_listBoards.do"
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
				<td width="400"><input type="text" name="missName" /></td>
			</tr>
			<tr>
				<td width="200"><p align="right">작성자</td>
				<td width="400"><input type="text" name="userNAME" value="${member.userNAME}" disabled /></td>
			</tr>
			<tr>
				<td width="200"><p align="right">작성자 이메일</td>
				<td width="400"><input type="text" name="userEmail" value="${member.userEmail}" disabled /></td>
			</tr>
			<tr>
				<td width="200"><p align="right">작성자 주소</td>
				<td width="400"><input type="text" name="userAddress" value="${member.userAddress}" disabled /></td>
			</tr>
			<tr>
				<td width="200"><p align="right">작성자 전화번호</td>
				<td width="400"><input type="text" name="userPhoneNumber" value="${member.userPhoneNumber}" disabled /></td>
			</tr>
			<tr>
				<td width="200"><p align="right">품종</td>
				<td width="400"><input type="text" name="missKind" /></td>
			</tr>
			<tr>
				<td width="200"><p align="right">성별</td>
				<td width="400">
					<input type="radio" name="missGender" value="수컷">수컷
					<input type="radio" name="missGender" value="암컷">암컷
				</td>
			</tr>
			<tr>
				<td width="200"><p align="right">나이</td>
				<td width="400"><input type="text" name="missAge" /></td>
			</tr>
			<tr>
				<td width="200"><p align="right">실종장소</td>
				<td width="400"><input type="text" name="missPlace" /></td>
			</tr>
			<tr>
				<td width="200"><p align="right">실종일자</td>
				<td width="400"><input type="date" name="missMissdate" onblur="$('.calendar').hide();" /></td>
			</tr>
			<tr>
				<td width="200"><p align="right">특징</td>
				<td width="400"><input type="text" name="missCharacter" /></td>
			</tr>
			<tr>
				<td width="200"><p align="right">등록번호</td>
				<td width="400"><input type="text" name="missRegistNum" /></td>
			</tr>
			<tr>
				<td width="200"><p align="right">이미지대신</td>
				<td width="400"><input type="text" name="missImg" /></td>
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
				<td width="400"><input type="text" name="userID" value="${member.userID}" disabled /></td>
			</tr>
			<tr>
				<td width="200"><p>&nbsp;</p></td>
				<td width="400">
					<input type="submit" value="등록하기" />
					<input type="reset" value="다시입력" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>