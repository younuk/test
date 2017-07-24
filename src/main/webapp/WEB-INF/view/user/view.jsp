<%@ page contentType="text/html; charset=utf-8" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="../layout/header.jsp" />

<script type="text/javascript" src="/js/jquery/jquery.validate.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	cmmSetMenu("sideUserMng", 0);	//set menu
	cmmResultMsg("${result}");		//show result message

	//2017.06.11 수정 기능 제어
	if(!$("#psnnlBatchId").val()){
		$(".userInfo input").attr("readonly", true);
		$(".userInfo select").attr("disabled", true);
		//$(".calendar").datepicker("disable");
		$("#btnSave").hide();
	}

   $("#btnHistory").click(function(){
        document.userVo.action = "<c:url value='/psnnl/list.do'/>";
        document.userVo.submit();
    });

	var loginId = "${userVo.loginId}";
	$("#btnSave").click(function(){
		alert("test");
		var dupYn = false;
		if(loginId != $("#loginId").val()){
			var data = cmmCallAjax("<c:url value='/user/checkLoginId.do'/>", {loginId:$("#loginId").val(), userId:$("#userId").val()});
			if(data.result != "success"){
       			if(data.result == "fail"){
	       			alert("ID중복");
	       			return  false;
	       		}else{
	       			alert("error");
					return false;
	       		}
       		}
		}

		$("#userVo").validate({
			rules: {
				name: { required: true},
				orgnzId: { required: true},
				rankCodeId: { required: true},
				birthDt: { required: true},
				initialAppmnt: { required: true},
				rankAppmnt: { required: true},
				orgnzAppmnt: { required: true},
				ppmntBatch: { required: true},
				loginId: { required: true},
				authCodeId: { required: true}
	        },
	        messages: {
				name: { required: _REQUIRED},
				orgnzId: { required: _REQUIRED},
				rankCodeId: { required: _REQUIRED},
				birthDt: { required: _REQUIRED},
				initialAppmnt: { required: _REQUIRED},
				rankAppmnt: { required: _REQUIRED},
				orgnzAppmnt: { required: _REQUIRED},
				ppmntBatch: { required: _REQUIRED},
				loginId: { required: _REQUIRED},
				authCodeId: { required: _REQUIRED}
	        },
	        submitHandler: function (frm) {},
	        success: function (e) {}
		});

		if($("#userVo").valid()) {
			$("#divHiddenInput").remove();
			$(".divDuty2F").each(function(n){
				$(this).find("input:eq(0)").attr("name", "dutyPeriods["+n+"].startDt");
				$(this).find("input:eq(1)").attr("name", "dutyPeriods["+n+"].endDt");
			});

			$(".divAbs2F").each(function(n){
				$(this).find("input:eq(0)").attr("name", "absences["+n+"].startDt");
				$(this).find("input:eq(1)").attr("name", "absences["+n+"].endDt");
			});
			if(!confirm(_REG_CONFIRM)) return false;

			//document.userVo.action = "<c:url value='/user/add.do'/>";
			//document.userVo.submit();

			$.post("<c:url value='/user/add.do'/>", $("#userVo").serialize(), "json")
			.done(function(data) {
				cmmAjaxRtn(data);
            });
		}
	});

	$("#btnDelete").click(function(){
		var confirmMsg = _DEL_CONFIRM;
		confirmMsg +=  "\n\n주의 ! 해당구급대원정보 및 인사정도 모두 삭제 됩니다.";
		if(!confirm(confirmMsg)) return false;

		$.post("<c:url value='/user/delete.do'/>", $("#userVo").serialize(), "json")
		.done(function(data) {
			cmmAjaxRtn(data);
        });
	});

	$("#btnClose").click(function(){
		fnGoList();
	});

	$("#btnPwdReset").click(function(){
		if(!confirm("비밀번호를 초기화 하시겠습니까?")) return false;
		var data = cmmCallAjax("<c:url value='/user/changePwd.do'/>", {userId:"${userVo.userId}"});
		if(data.result == "success"){
			alert(_REG_SUCCESS);
   			return true;
		}else{
			alert(_REG_FAIL);
   			return false;
		}
	});

	$("#btnDemand").click(function(){
		window.open("<c:url value='/psnnl/popDemand.do'/>"+"?userId="+$("#userId").val(), "popDemandPopup", cmmPopup(550, 320));
	});
	<sec:authorize access="hasAnyRole('ROL002')">
		$("#authCodeId").attr("disabled", true);
	</sec:authorize>
});

function fnGoList(){
	document.userSearchVo.action = "<c:url value='/user/list.do'/>";
	document.userSearchVo.submit();
}

</script>
<c:set var="auth"><sec:authentication property="principal.userAuth"/></c:set>
<main class="colgroup">
	<header class="sub_head">
		<div class="path">
			<ul class="clearfix">
				<li>홈</li>
				<li class="actived">사용자관리</li>
				<li class="actived"><c:choose><c:when test="${empty userVo.userId}">사용자등록</c:when><c:otherwise>사용자상세보기</c:otherwise></c:choose></li>
			</ul>
		</div>
	</header>
	<form:form commandName="userVo" name="userVo" method="post" onsubmit="return false;">
	<article id="contents">
		<table class="table userInfo">
			<colgroup>
				<col class="w20p" />
				<col class="w30p" />
				<col class="w20p" />
				<col class="w30p" />
			</colgroup>
			<tbody class="text_left">
				<tr>
					<th>이름</th>
					<td colspan="3"><form:input path="name"/></td>
				</tr>
				<tr>
					<th>소속</th>
					<td colspan="3"><form:select path="orgnzId" items="${orgnzCombo}"/></td>
				</tr>
				<tr>
					<th>계급</th>
	`				<td colspan="3"><form:select path="rankCodeId" items="${rankCombo}"/></td>
				</tr>
				<tr>
					<th>생년월일</th>
					<td colspan="3"><form:input path="birthDt" class="calendar"/><label id="birthDt-error" class="error" for="birthDt"></label></td>
				</tr>
				<tr>
					<th>최초임용일</th>
					<td colspan="3"><form:input path="initialAppmnt" class="calendar"/><label id="initialAppmnt-error" class="error" for="initialAppmnt"></label></td>
				</tr>
				<tr>
					<th>현계급임용일</th>
					<td colspan="3"><form:input path="rankAppmnt" class="calendar"/><label id="rankAppmnt-error" class="error" for="rankAppmnt"></label></td>
				</tr>
				<tr>
					<th>현기관임용일</th>
					<td colspan="3"><form:input path="orgnzAppmnt" class="calendar"/><label id="orgnzAppmnt-error" class="error" for="orgnzAppmnt"></label></td>
				</tr>
				<tr>
					<th>최종인사일</th>
					<td colspan="3"><form:input path="ppmntBatch" class="calendar"/><label id="ppmntBatch-error" class="error" for="ppmntBatch"></label></td>
				</tr>
				<tr>
					<th>특수직무/자격증</th>
					<td colspan="3"><form:select path="specialDutyCodeId" items="${spcDutyCombo}"/></td>
				</tr>
				<tr>
					<th>로그인 ID</th>
					<td><form:input path="loginId"/><label for="loginId"></label></td>
					<th>권한</th>
					<td><form:select path="authCodeId" items="${authCodeId}"/><label for="authCodeId"></label></td>
				</tr>
			</tbody>
		</table>

		<div id="divHiddenInput" style="display:none">
			<div class="divAbs2F">
				<input type="text" name="absences.startDt" size="11" readonly="readonly"/> - <input type="text" name="absences.endDt" size="11" readonly="readonly"/>
				<input type="button" value="-" class="btn btn_small2 btnMinus"/>
			</div>
			<div class="divDuty2F">
				<input type="text" name="dutyPeriods.startDt" size="11" readonly="readonly"/> - <input type="text" name="dutyPeriods.endDt" size="11" readonly="readonly"/>
				<input type="button" value="-" class="btn btn_small2 btnMinus"/>
			</div>
		</div>

		<div class="btn_group">
			<div class="text_left" style="width:50%; float:left;">
				<input type="button" value="비밀번호 초기화" class="btn" id="btnPwdReset"/>
			</div>
			<div class="text_right" style="width:50%; float:left;">
				<input type="button" value="인사이력조회" class="btn" id="btnHistory"/>
				<c:if test="${auth eq 'ROL001' and empty runBatchInfo.psnnlBatchId}">
					<input type="button" value="수시인사등록" class="btn" id="btnDemand"/>
				</c:if>
			</div>
		</div>

		<br/>
		<br/>
		<c:if test="${runBatchInfo.runYn eq 'N' }">
			<table class="table">
				<colgroup>
					<col class="w25p" />
					<col class="w25p" />
					<col class="w75p" />
				</colgroup>
				<tbody class="text_left">
					<tr>
						<th rowspan="3">인사정보 등록</th>
						<th>동점자우선대상자여부</th>
						<td><form:checkbox path="tiePriorityYn" value="Y"/></td>
					</tr>
					<c:choose>
						<c:when test="${auth eq 'ROL001'}">
							<tr>
								<th>승진임용구분</th>
								<td><form:select path="prmtCodeId" items="${prmtCombo }"/></td>
							</tr>
							<tr>
								<th>대상자구분</th>
								<td><form:select path="targetCodeId" items="${targetCombo }"/></td>
							</tr>
						</c:when>
						<c:otherwise>
							<form:hidden path="prmtCodeId"/>
							<form:hidden path="targetCodeId"/>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</c:if>
		<div class="btn_group text_center">
			<c:if test="${runBatchInfo.runYn != 'Y' }">
				<input type="button" value="저장" class="btn" id="btnSave"/>
				<c:if test="${not empty userVo.userId }">
					<input type="button" value="삭제" class="btn" id="btnDelete"/>
				</c:if>
			</c:if>
			<!-- <input type="button" value="저장" class="btn" id="btnSave"/> -->
			<input type="button" value="목록" class="btn" id="btnClose"/>
		</div>

	</article>
	<form:hidden path="userId"/>
	<form:hidden path="psnnlBatchId" value="${runBatchInfo.psnnlBatchId}"/>
	</form:form>
</main>

<form:form commandName="userSearchVo" name="userSearchVo" method="post" onsubmit="return false;">
	<form:hidden path="orgnzId"/>
	<form:hidden path="name"/>
	<form:hidden path="loginId"/>
	<form:hidden path="targetYn"/>
	<form:hidden path="rankCodeId"/>
	<form:hidden path="specialDutyCodeId"/>
	<form:hidden path="srchStartDt"/>
	<form:hidden path="srchEndDt"/>
</form:form>
<%@include file="../layout/footer.jsp"%>