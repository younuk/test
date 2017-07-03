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
	<link rel="stylesheet" href="/js/jquery/jquery-ui.min.css"/>

	<script type="text/javascript" src="/js/jquery/jquery-3.1.1.min.js"></script>
	<script type="text/javascript" src="/js/jquery/jquery-ui.min.js"></script>
	<script type="text/javascript" src="/js/common/common.js?v=1.1"></script>
<script type="text/javascript" src="/js/jquery/jquery.validate.min.js"></script>
    <title>충청남도소방본부 소방공무원 인사배치 시스템 팝업</title>

<script type="text/javascript">
$(document).ready(function(){
	$(".calendar").datepicker(datepickerOption);
	$(".calendar").attr('size',11);

	$("#targetCodeId option").each(function(){
		console.log($(this).val())
		if($(this).val() == 'TGD002' || $(this).val() == 'TGD003')
			$(this).remove();
	});

	$("#btnSave").click(function(){
		$("#userVo").validate({
			rules: {
				targetCodeName: { required: true},
				startDt: { required: true},
				orgnzId: { required: true}
	        },
	        messages: {
	        	targetCodeName: { required: _REQUIRED},
	        	startDt: { required: _REQUIRED},
	        	orgnzId: { required: _REQUIRED}
	        }
		});

		if($("#userVo").valid()) {
			if(!confirm(_REG_CONFIRM)) return false;

			var rtnObj = cmmCallAjax("<c:url value='/psnnl/addDemand.do'/>", $("#userVo").serialize());
			if(rtnObj.result =="success"){
				alert("저장성공");
           		window.close();
			}else{
       			alert("저장실패");
       			return false;
       		}
		}
	});
});

</script>
</head>
<body style="width:100%;">
	<header id="header">
		<div class="accessibility">
			<a href="#container">본문 바로가기</a>
		</div>
		<h1 class="skip">충청남도소방본부 Chungnam Fire serverice Headpuarter 소방공무원 인사배치 시스템</h1>
	</header>
	<form:form commandName="userVo" name="userVo" method="post" onsubmit="return false;">
	<main id="container">
		<h2>수시인사 등록</h2>
		<div id="contents">
			<table class="table">
				<colgroup>
					<col class="w25p" />
					<col class="w25p" />
					<col class="w25p" />
					<col class="w25p" />
				</colgroup>
				<tbody class="text_left">
					<tr>
						<th>승진임용구분</th>
						<td><form:select path="prmtCodeId" items="${prmtCombo }"/></td>
						<th>대상자구분</th>
						<td><form:select path="targetCodeId" items="${targetCombo }"/></td>
					</tr>
					<tr>
						<th>임용일</th>
						<td colspan="3"><form:input path="ppmntBatch" class="calendar"/></td>
					</tr>
					<tr>
						<th>근무지</th>
						<td colspan="3"><form:select path="orgnzId" items="${orgnzCombo}"/></td>
					</tr>
					<tr>
				</tbody>
			</table>
		</div>
	</main>
	<form:hidden path="userId"/>
	</form:form>
	<footer id="footer" style="position:absolute;bottom:0px;width:92%;">
		<input type="button" value="저장" class="btn" id="btnSave"/>
		<input type="button" value="닫기" class="btn" onclick="window.close();"/>
	</footer>
</body>
</html>