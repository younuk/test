<%@ page contentType="text/html; charset=utf-8" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="../layout/header.jsp" />

<script type="text/javascript" src="/js/jquery/jquery.validate.min.js"></script>
<script type="text/javascript" src="/js/jquery/jquery.ui.datepicker.validation.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	cmmSetMenu("sideUserMng", 2);	//set menu
	//cmmResultMsg("${result}");		//show result message

	//set calander
	$(".calendar").datepicker(datepickerOption);


	var statCodeVal = "${psnnlBatchVo.statCodeId}";
	if(statCodeVal != "" && statCodeVal != "PST001"){
		$("#psnnlBatchVo input").attr("readonly", true);
	}
	$("#btnSave").click(function(){

		//1. check 희망관서입력완료여부
		$("#statCodeId").val($(':radio[name="statCodeIdTemp"]:checked').parent().attr("id"));

		if($("#statCodeId").val() == "PST004" || $("#statCodeId").val() == "PST005"){
			var rtnObj = cmmCallAjax("/batch/checkHopeIn.do", {psnnlBatchId:$("#psnnlBatchId").val()});
			if(rtnObj != null && rtnObj.result =="fail"){
				alert("희망관서입력이 완료되지 않았습니다.");
				return false;
			}
		}

		$("#psnnlBatchVo").validate({
			rules: {
				degree: { required: true, number: true},
				divCodeId: { required: true},
				inStartDt: { required: true, dpDate:true},
				inEndDt: { required: true,  min:$("#inStartDt").val()},
				dt: { required: true}
	        },
	        messages: {
				degree: { required: _REQUIRED, number: _ONLYNUMBER},
				divCodeId: { required: _REQUIRED},
				inStartDt: { required: _REQUIRED, dpDate:_REQUIRED},
				inEndDt: { required: _REQUIRED, min:"시작일보다 커야함"},
				dt: { required: _REQUIRED}
	        }
		});

		if($("#psnnlBatchVo").valid()) {
			if(!confirm(_REG_CONFIRM)) return false;
			document.psnnlBatchVo.action = "<c:url value='/batch/add.do'/>";
			document.psnnlBatchVo.submit();
		}
	});

	$("#btnDelete").click(function(){
		if(!confirm(_DEL_CONFIRM)) return false;

		document.psnnlBatchVo.action = "<c:url value='/batch/delete.do'/>";
		document.psnnlBatchVo.submit();
	});

	$("#btnClose").click(function(){
		location.href = "<c:url value='/batch/list.do'/>";
	});

	$("#dt").change(function(n){
		console.log($(this).val());
		var dtVal = $(this).val();
		if(dtVal != "" && dtVal.length == 10){
			$("#spanDegreeheader").text(dtVal.substring(0, 4)+"-");
		}else{
			$("#spanDegreeheader").text("");
		}
	});

	$(".divState").each(function(){
		if($(this).attr("id") == statCodeVal){
			$(this).removeClass("notAllow").addClass("nowState");
			$(this).append("<input type='radio' name='statCodeIdTemp'/>");
			$(this).next().removeClass("notAllow").addClass("nextState");
			$(this).next().append("<input type='radio' name='statCodeIdTemp' checked='checked'/>");


			if(statCodeVal == "PST005"){
				$("div#PST002").text("인사대기자 재선정");
				$("div#PST002").removeClass("notAllow").addClass("nextState");
				$("div#PST002").append("<input type='radio' name='statCodeIdTemp'/>");
			}
			return false;
		}
	});
});


</script>
<style>
.divState{
	width:150px;
	margin : 10px;
	background-color: #b0bdc5;
    color: #fff;
    text-align: center;
}
.notAllow{
	background-color: #b0bdc5;
}
.nowState{
	background-color: #3b85c4;
}
.nextState{
	background-color: #c42a2a;
}
.btnGuide{
    font-size: 6px;
    padding: 3px;
    cursor:not-allowed;
}

</style>
<main class="colgroup">
	<header class="sub_head">
		<div class="path">
			<ul class="clearfix">
				<li>홈</li>
				<li class="actived">인사배치관리</li>
				<li class="actived"><c:choose><c:when test="${empty psnnlBatchVo.psnnlBatchId}">인사배치 등록</c:when><c:otherwise>인사배치 상세보기</c:otherwise></c:choose></li>
			</ul>
		</div>
	</header>
	<form:form commandName="psnnlBatchVo" name="psnnlBatchVo" method="post" onsubmit="return false;">
	<article id="contents">
		<table class="table">
			<colgroup>
					<col class="w25p" />
					<col class="w75p" />
			</colgroup>
			<tbody class="text_left">
				<tr>
					<th>인사차수</th>
					<td><c:set var="degreeHeader" />
						<c:set var="degreeInt" />
						<c:if test="${not empty psnnlBatchVo.degree }">
							<c:set var="degreeHeader">${fn:substring(psnnlBatchVo.degree,0,4) } -</c:set>
							<c:set var="degreeInt">${fn:substring(psnnlBatchVo.degree, 5, fn:length(psnnlBatchVo.degree)) }</c:set>
						</c:if>
						<span id="spanDegreeheader">${degreeHeader }</span> <form:input path="degree" value="${degreeInt }" size="5"/>
					</td>
				</tr>
				<tr>
					<th>인사구분</th>
					<td><c:choose>
							<c:when test="${psnnlBatchVo.divCodeId =='PDV002'}">수시</c:when>
							<c:otherwise>정기</c:otherwise>
						</c:choose></td>
				</tr>
				<tr>
					<th>희망관서입력대기기간</th>
					<td><form:input path="inStartDt" class="calendar"/><label id="inStartDt-error" class="error" for="inStartDt"></label>- <form:input path="inEndDt" class="calendar"/><label id="inEndDt-error" class="error" for="inEndDt"></label>
					</td>
				</tr>
				<tr>
					<th>인사일시</th>
					<td><form:input path="dt" class="calendar"/><label id="dt-error" class="error" for="dt"></label></td>
				</tr>
				<tr>
					<th>인사대상자수</th>
					<td>${psnnlBatchVo.targetNum}</td>
				</tr>
				<tr>
					<th>상태</th>
					<td>
						<c:choose>
							<c:when test="${psnnlBatchVo.statCodeId != 'PST006' }">
								<div style="width:480px;">
								&nbsp;&nbsp;&nbsp;&nbsp;
								<div class="btn divState notAllow" id="PST001">저장 </div>
								▷▷<div class="btn divState notAllow" id="PST002">인사대기자선정 </div>
								▷▷<div class="btn divState notAllow" id="PST003">희망관서입력대기</div>

								▷▷<div class="btn divState notAllow" id="PST004">희망관서입력완료</div>
								▷▷<div class="btn divState notAllow" id="PST005">배치실행</div>
								▷▷<div class="btn divState notAllow" id="PST006">배치적용</div>
								</div>

								<br/>
								※ guide: <button class="btn type8 btnGuide">현재 배치 상태</button> &nbsp;<button class="btn type5 btnGuide">다음 배치 상태</button>
							</c:when>
							<c:otherwise>배치적용</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</tbody>
		</table>
		<div class="btn_group text_center">
			<c:if test="${psnnlBatchVo.statCodeId != 'PST006' }">
				<input type="button" value="저장" class="btn" id="btnSave"/>
				<c:if test="${not empty psnnlBatchVo.psnnlBatchId }">
					<input type="button" value="삭제" class="btn" id="btnDelete"/>
				</c:if>
			</c:if>
			<input type="button" value="목록" class="btn" id="btnClose"/>
		</div>
		<div>※ [인사대기자선정] 상태 이후 상태 외 정보 수정 불가</div>
	</article>
	<form:hidden path="statCodeId"/>
	<form:hidden path="divCodeId"/>
	<form:hidden path="psnnlBatchId"/>
	</form:form>
</main>
<%@include file="../layout/footer.jsp"%>