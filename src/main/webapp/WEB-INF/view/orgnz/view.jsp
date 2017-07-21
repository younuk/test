<%@ page contentType="text/html; charset=utf-8" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="../layout/header.jsp" />

<script type="text/javascript" src="/js/jquery/jquery.validate.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	cmmSetMenu("sideUserMng", 1);	//set menu
	cmmResultMsg("${result}");		//show result message

	$("input[name^=dept],input[name=special]").each(function(){
		if(!$(this).val())
			$(this).val("0");
	});

	$("#btnSave").click(function(){

		$("#orgnzVo").validate({
			rules: {
				'name': { required: true}
	        },
	        messages: {
	        	'name': { required: _REQUIRED}
	        }
		});

		if($("#orgnzVo").valid()) {
			var oldHopeYn = "${orgnzVo.applOrgnzYn}";
			var newHopeYn = ($("input[name=applOrgnzYn]").is(":checked"))? "Y": "N";
			var confirmMsg = _REG_CONFIRM;
			if(oldHopeYn != ""  && oldHopeYn != newHopeYn){
				confirmMsg += "\n\n주의 ! 희망관서등록여부가 변경되었습니다.";
			}
			if(!confirm(confirmMsg)) return false;

			$.post("<c:url value='/orgnz/add.do'/>", $("#orgnzVo").serialize(), "json")
			.done(function(data) {
				cmmAjaxRtn(data);
            });
		}
	});

	$("#btnDelete").click(function(){
		var confirmMsg = _DEL_CONFIRM;
		confirmMsg +=  "\n\n주의 ! 해당관서 구급대원정보 및 인사정도 모두 삭제 됩니다.";
		if(!confirm(confirmMsg)) return false;

		$.post("<c:url value='/orgnz/delete.do'/>", $("#orgnzVo").serialize(), "json")
		.done(function(data) {
			cmmAjaxRtn(data);
        });
	});

	$("#btnClose").click(function(){
		fnGoList();
	});

	$("#totPsb").text(sumTot("Psb"));
	$("input[name$=Nrm],input[name$=Vcc]").keyup(function() {
		var trObj = $(this).parent().parent();
		if($(trObj).hasClass("trAutoSum"))
			$(trObj).find("input:eq(2)").val(Number($(trObj).find("input:eq(0)").val()) - Number($(trObj).find("input:eq(1)").val()));

		setTot();
	});
});

function setTot(){
	$("#totNrm").text(sumTot("Nrm"));
	$("#totVcc").text(sumTot("Vcc"));
	$("#totPsb").text(sumTot("Psb"));
	//$("#totPsb").text(Number($("#totNrm").text()) - Number($("#totVcc").text()));
}

function sumTot(paramDiv){
	var tot = 0;
	$("input[id$="+paramDiv+"]").each(function(){
		tot += Number($(this).val());
	});

	return tot;
}

function fnGoList(){
	document.orgnzSearchVo.action = "<c:url value='/orgnz/list.do'/>";
	document.orgnzSearchVo.submit();
}

</script>
<main class="colgroup">
	<header class="sub_head">
		<div class="path">
			<ul class="clearfix">
				<li>홈</li>
				<li class="actived">관서관리</li>
				<li class="actived"><c:choose><c:when test="${empty orgnzVo.orgnzId}">관서등록</c:when><c:otherwise>관서상세보기</c:otherwise></c:choose></li>
			</ul>
		</div>
	</header>
	<form:form commandName="orgnzVo" name="orgnzVo" method="post" onsubmit="return false;">
	<article id="contents">
		<table class="table">
			<colgroup>
				<col class="w25p" />
				<col class="w75p" />
			</colgroup>
			<tbody>
				<tr>
					<th>관서명</th>
					<td><form:input path="name"/></td>
				</tr>
			</tbody>
		</table>
		<br/>
		<table class="table">
			<colgroup>
					<col class="w20p" />
					<col class="w20p" />
					<col class="w20p" />
					<col class="w20p" />
					<col class="w20p" />
			</colgroup>
			<thead>
				<tr>
					<th scope="col"></th>
					<th scope="col">정원</th>
					<th scope="col">강제결원</th>
					<th scope="col">배치가능인원</th>
					<th scope="col">현원</th>
				</tr>
			</thead>
			<tbody class="text_left">
				<tr class="trAutoSum">
					<th>소방사</th>
					<td><form:input path="dept1Nrm" size="7"/></td>
					<td><form:input path="dept1Vcc" size="7"/></td>
					<td><input type="text" id="dept1Psb" value="${orgnzVo.dept1Nrm - orgnzVo.dept1Vcc}" readonly="readonly" size="7"/>(auto gen)</td>
					<td>${orgnzVo.dept1Now}</td>
				</tr>
				<tr class="trAutoSum">
					<th>소방교</th>
					<td><form:input path="dept2Nrm" size="7"/></td>
					<td><form:input path="dept2Vcc" size="7"/></td>
					<td><input type="text" id="dept2Psb" value="${orgnzVo.dept2Nrm - orgnzVo.dept2Vcc}" readonly="readonly" size="7"/>(auto gen)</td>
					<td>${orgnzVo.dept2Now}</td>
				</tr>
				<tr class="trAutoSum">
					<th>소방장</th>
					<td><form:input path="dept3Nrm" size="7"/></td>
					<td><form:input path="dept3Vcc" size="7"/></td>
					<td><input type="text" id="dept3Psb" value="${orgnzVo.dept3Nrm - orgnzVo.dept3Vcc}" readonly="readonly" size="7"/>(auto gen)</td>
					<td>${orgnzVo.dept3Now}</td>
				</tr>
				<tr class="trAutoSum">
					<th>소방위</th>
					<td><form:input path="dept4Nrm" size="7"/></td>
					<td><form:input path="dept4Vcc" size="7"/></td>
					<td><input type="text" id="dept4Psb" value="${orgnzVo.dept4Nrm - orgnzVo.dept4Vcc}" readonly="readonly" size="7"/>(auto gen)</td>
					<td>${orgnzVo.dept4Now}</td>
				</tr>
				<tr class="trAutoSum">
					<th>소방경</th>
					<td><form:input path="dept5Nrm" size="7"/></td>
					<td><form:input path="dept5Vcc" size="7"/></td>
					<td><input type="text" id="dept5Psb" value="${orgnzVo.dept5Nrm - orgnzVo.dept5Vcc}" readonly="readonly" size="7"/>(auto gen)</td>
					<td>${orgnzVo.dept5Now}</td>
				</tr>
				<tr>
					<th>소방령</th>
					<td><form:input path="dept6Nrm" size="7"/></td>
					<td><form:input path="dept6Vcc" size="7"/></td>
					<td><input type="text" id="dept6Psb" value="0" readonly="readonly" size="7"/>(fix)</td>
					<td>${orgnzVo.dept6Now}</td>
				</tr>
				<tr>
					<th>소방정</th>
					<td><form:input path="dept7Nrm" size="7"/></td>
					<td><form:input path="dept7Vcc" size="7"/></td>
					<td><input type="text" id="dept7Psb" value="0" readonly="readonly" size="7"/>(fix)</td>
					<td>${orgnzVo.dept7Now}</td>
				</tr>
				<tr>
					<th>총정원</th>
					<td colspan="4" id="totNrm">${orgnzVo.nrmTotal }</td>
				</tr>
				<tr>
					<th>총강제결원</th>
					<td colspan="4" id="totVcc">${orgnzVo.vccTotal }</td>
				</tr>
				<tr>
					<th>총배치가능인원</th>
					<td colspan="4"><span id="totPsb"></span></td>
				</tr>
				<tr>
					<th>총현원</th>
					<td colspan="4">${orgnzVo.nowTotal }</td>
				</tr>
				<tr>
					<th>특수직무군정원</th>
					<td colspan="4"><form:input path="special"/></td>
				</tr>
				<tr>
					<th>희망관서등록여부</th>
					<td colspan="4"><form:checkbox path="applOrgnzYn" value="Y"/></td>
				</tr>
			</tbody>
		</table>
		<div class="btn_group text_center">
			<input type="button" value="저장" class="btn" id="btnSave"/>
			<c:if test="${not empty orgnzVo.orgnzId }">
				<input type="button" value="삭제" class="btn" id="btnDelete"/>
			</c:if>
			<input type="button" value="목록" class="btn" id="btnClose"/>
		</div>
	</article>
	<form:hidden path="orgnzId"/>
	</form:form>
</main>
<form:form commandName="orgnzSearchVo" name="orgnzSearchVo" method="post" onsubmit="return false;">
	<form:hidden path="srchOrgnzId"/>
	<form:hidden path="srchNrmCntStart"/>
	<form:hidden path="srchNrmCntEnd"/>
	<form:hidden path="srchNowCntStart"/>
	<form:hidden path="srchNowCntEnd"/>
	<form:hidden path="srchVccCntStart"/>
	<form:hidden path="srchVccCntEnd"/>
</form:form>
<%@include file="../layout/footer.jsp"%>