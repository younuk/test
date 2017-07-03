<%@ page contentType="text/html; charset=utf-8" session="false" %>
<%@ taglib prefix="c"	 uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" 	 uri="http://egovframework.gov/ctl/ui"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=2.0, user-scalable=yes" />
	<meta name="keywords" content="충청남도, 소방" />
	<meta name="description" content="충청남도소방본부 소방공무원 인사배치 시스템에 오신것을 환영합니다." />
    <link rel="stylesheet" type="text/css" href="/css/sub.css" />
    <link rel="stylesheet" type="text/css" href="/css/popup.css" />

	<script type="text/javascript" src="/js/jquery/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="/js/jquery/jquery.validate.min.js"></script>
<script type="text/javascript" src="/js/common/common.js"></script>
    <title>충청남도소방본부 소방공무원 인사배치 시스템 팝업</title>

<script type="text/javascript">
$(document).ready(function(){
	$("#btnSave").click(function(){
		$("#login").validate({
			rules: {
				oldPassword: { required: true},
				newPassword: { required: true},
				reNewPassword: { required: true}
	        },
	        messages: {
	        	oldPassword: { required: _REQUIRED},
	        	newPassword: { required: _REQUIRED},
	        	reNewPassword: { required: _REQUIRED}
	        }
		});
		if(!$("#login").valid()) return false;

		if($("#newPassword").val() != $("#reNewPassword").val()){
			alert("비밀번호가 일치하지 않습니다.");
			return false;
		}

		if(!cmmChkPwd($("#newPassword"))){
			$("#reNewPassword").val("");
			return false;
		}

		if(!confirm(_REG_CONFIRM)) return false;

		var data = {
				loginId:$("#loginId").val()
				, password:$("#oldPassword").val()
		}
		var rtnObj = cmmCallAjax("<c:url value='/auth/checkPwd.do'/>", data);
		if(rtnObj.result =="fail"){
			alert("기존비밀번호가 일치하지 않습니다.");
			return false;
		}

		data = {
				loginId:$("#loginId").val()
				, password:$("#newPassword").val()
		}
		rtnObj = cmmCallAjax("<c:url value='/auth/changePwd.do'/>", data);
		if(rtnObj.result =="success"){
			alert("변경되었습니다. 다시로그인해주세요");
       		window.close();
		}else{
			alert(_REG_FAIL);
       		window.close();
		}
	});
});
</script>
<style type="text/css">
table.table tbody tr td{
	padding:5px;
}
</style>
</head>
<body style="width:100%;">
	<header id="header">
		<div class="accessibility">
			<a href="#container">본문 바로가기</a>
		</div>
		<h1 class="skip">충청남도소방본부 Chungnam Fire serverice Headpuarter 소방공무원 인사배치 시스템</h1>
	</header>
	<form name="login" id="login" method="post" onsubmit="return false;">
	<main id="container">
		<h2>비밀번호변경</h2>
		<div id="contents">
			<table class="table">
				<colgroup>
					<col class="w35p" />
					<col class="w65p" />
				</colgroup>
				<tbody class="text_left">
					<tr>
						<th>아이디</th>
						<td>${loginId }<input type="hidden" id="loginId" value="${loginId }"/></td>
					</tr>
					<tr>
						<th>현재비밀번호</th>
						<td><input type="password" name="oldPassword" id="oldPassword"/></td>
					</tr>
					<tr>
						<th>신규비밀번호</th>
						<td><input type="password" name="newPassword" id="newPassword"/></td>
					</tr>
					<tr>
						<th>신규비밀번호확인</th>
						<td><input type="password" name="reNewPassword" id="reNewPassword"/></td>
					</tr>
				</tbody>
			</table>
		</div>
	</main>
	</form>
	<footer id="footer" style="position:absolute;bottom:0px;left:0px;right:0px;">
		<input type="button" value="저장" class="btn" id="btnSave"/>
		<input type="button" value="닫기" class="btn" onclick="window.close();"/>
	</footer>
</body>
</html>