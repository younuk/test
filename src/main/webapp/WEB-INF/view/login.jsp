<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<sec:authorize var="authenticated" access="isAuthenticated()" />
<c:choose>
    <c:when test="${authenticated}">
        <meta http-equiv="refresh" content="0; url=/" />
    </c:when>
    <c:otherwise>
<!DOCTYPE html>
<html lang="ko">
<head>
 <meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=2.0, user-scalable=yes" />
	<meta name="keywords" content="충청남도, 소방" />
	<meta name="description" content="충청남도소방본부 소방공무원 인사배치 시스템에 오신것을 환영합니다." />
    <link rel="stylesheet" type="text/css" href="/css/main.css" />
	<script type="text/javascript" src="/js/jquery/jquery-3.1.1.min.js"></script>
	<script type="text/javascript" src="/js/jquery/jquery.cookie.js"></script>
	<script type="text/javascript" src="/js/common/common.js"></script>

<script type="text/javascript">
$(document).ready(function(){
    if($.cookie('remember-me-userid')) {
		$('#userid').val($.cookie('remember-me-userid'));
		$('#remember').attr('checked', true);

		 $("#userpw").focus();
	}else{
		 $("#userid").focus();
	}
});



function fnSign(){
    if(!checkValidation($("#userid"), "아이디")) return false;
    if(!checkValidation($("#userpw"), "비밀번호")) return false;

    var checkbox = $('#remember');
    if(checkbox.is(':checked')){
    	$.cookie('remember-me-userid', $('#userid').val(), { expires: 3650 }); //SETS IN DAYS (10 YEARS)
    } else {
    	$.removeCookie('remember-me-userid');
    }


    $.ajax({
        url : "<c:url value='/admin/j_spring_security_check'/>"
        , data: $("#loginForm").serialize()
        , type: 'POST'
        , dataType : 'json'
   }).done(function(body) {
        if(body.response.error){
            var message = body.response.message;
            message = message.replace(/<br>/gi, "\n");
            alert(message);

            $("#userpw").val("");
            $("#userpw").focus();
        }else{
			if(body.response.pwChgYn == "N"){
				alert("비밀번호를 변경하세요.");
				var url = "<c:url value='/auth/changePasswordPage.do'/>"
					 + "?loginId="+$("#userid").val();
				 $("#userpw").val("");
				window.open(url, "popPassword", cmmPopup(450, 350));
			}else{
				var url = body.response.url;
	        	window.location = body.response.url;
			}

        }
    });

    return false;
}

function checkValidation(obj, msg){
    if(isNull($(obj).val())){
        alert(msg+"을/를 입력하세요.");
        $(obj).focus();
        return false;
    }else{
        return true;
    }
}
function fnOpenPwdChange(){
	var title  = "pwdChange";
    window.open("", title, makePopupOpt(718, 267));

    document.popForm.userid.value = $("#userid").val();
    document.popForm.target = title;
    document.popForm.submit();

    return false;
}
function fnAfterPwdSave(){
	alert("다시 로그인 해 주세요");
	location.reload();
}
</script>

</head>
<body id="main">
	<div id="wrapper">
		<header id="header">
			<div class="accessibility">
				<a href="#container">본문 바로가기</a>
			</div>
			<div class="wrap">
				<h1 class="logo"><a href="login.html"><img src="/images/common/logo.gif" alt="충청남도소방본부 Chungnam Fire serverice Headpuarter " />소방공무원 인사배치 시스템</a></h1>
			</div>
		</header>
		<main id="container">
			<div class="wrap">
				<section class="login">
					<h2 class="skip">로그인</h2>
					<form name="loginForm" id="loginForm" method="post" onsubmit="return fnSign();">
						<fieldset>
							<legend>로그인 폼</legend>
							<div class="user_id">
								<label for="">아이디</label>
								<input type="text" name="userid" id="userid" value="noelapp"/>
							</div>
							<div class="user_pw">
								<label for="">비밀번호</label>
								<input type="password" name="userpw" id="userpw" value="1111"/>
							</div>
							<input type="submit" value="로그인"/>
							<div class="save_id">
								<input type="checkbox" id="remember" />
								<label for="remember">아이디 저장</label>
							</div>
						</fieldset>
					</form>
				</section>
			</div>
		</main>
		<footer id="footer">
			<div class="wrap">
				<div class="footer_info">
					<p>Copyright © 2017 충청남도소방본부. All Rights Reserved.</p>
				</div>
			</div>
		</footer>
	</div>
</body>
</html>
    </c:otherwise>
</c:choose>

