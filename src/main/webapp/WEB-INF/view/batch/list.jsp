<%@ page contentType="text/html; charset=utf-8" session="false" %>
<%@ taglib prefix="c"	 uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" 	 uri="http://egovframework.gov/ctl/ui"%>
<jsp:include page="../layout/header.jsp" />

<script type="text/javascript">
$(document).ready(function(){
	cmmSetMenu("sideUserMng", 2);	//set menu

	$(".calendar").datepicker(datepickerOption);

	$("#psnnlSearchVo input").keypress(function(e) {
        if (e.keyCode == 13){
        	cmmPage(document.psnnlSearchVo, 1);
        }
    });
	$("#psnnlSearchVo select").change(function() {
		cmmPage(document.psnnlSearchVo, 1);
    });

	$(".tbody tr").click(function(){
		document.psnnlSearchVo.psnnlBatchId.value = $(this).attr("id")
		document.psnnlSearchVo.action = "<c:url value='/batch/view.do'/>";
		document.psnnlSearchVo.submit();
	});

	$("#btnSearch").click(function(){
		cmmPage(document.psnnlSearchVo, 1);
	});

	$("#btnAdd").click(function(){
		var runBatchInfo = cmmChkInProgress();
		if(!runBatchInfo){
			location.href = "<c:url value='/batch/view.do'/>";
		}else{
			alert("배치프로세스중인 인사배치가 존재합니다. 새로운 인사배치를 등록 할 수 없습니다.");
			return false;
		}
	});
});


function fnPage(pageNo){
	cmmPage(document.psnnlSearchVo, pageNo);
}
</script>
<main class="colgroup">
	<header class="sub_head">
		<div class="path">
			<ul class="clearfix">
				<li>홈</li>
				<li class="actived">인사배치관리</li>
			</ul>
		</div>
	</header>
	<article id="contents">
		<form:form commandName="psnnlSearchVo" name="psnnlSearchVo" class="search" method="post" onsubmit="return false;">
		<fieldset>
			<table class="table">
                <colgroup>
                    <col class="w25p" />
                    <col class="w35p" />
                    <col class="w20p" />
                    <col class="w25p" />
                </colgroup>
                <tbody class="text_left">
                    <tr>
                        <th scope="col">인사일시</th>
                        <td><form:input path="srchStartDt" class="calendar"/> - <form:input path="srchEndDt" class="calendar"/></td>
                        <th scope="col">상태</th>
                        <td colspan="3"><form:select path="srchState" items="${srchStateCombo}"/></td>
                    </tr>
                </tbody>
            </table>
			<br/>
			<input type="button" value="조회" class="btn" id="btnSearch" />
			<input type="button" value="등록" class="btn" id="btnAdd"/>
			<form:hidden path="psnnlBatchId"/>
			<form:hidden path="pageIndex" />
		</fieldset>
		</form:form>

		<table class="table">
			<colgroup>
					<col class="w20p" />
					<col class="w30p" />
					<col class="w20p" />
					<col class="w15p" />
					<col class="w15p" />
			</colgroup>
			<thead>
				<tr>
					<th scope="col">인사차수</th>
					<th scope="col">상태</th>
					<th scope="col">인사일시</th>
					<th scope="col">인사대상자수</th>
					<th scope="col">실제발령자수</th>
				</tr>
			</thead>
			<tbody class="tbody">
				<c:forEach var="result" items="${resultList}">
				<tr id="${result.psnnlBatchId }">
					<td>${result.degree }</td>
					<td><c:forEach var="codeResult" items="${srchStateCombo}">
							<c:if test="${codeResult.key eq result.statCodeId}">${codeResult.value}</c:if>
						</c:forEach></td>
					<td>${result.dt }</td>
					<td>${result.targetNum }</td>
					<td>${result.psnnlNum}</td>
				</tr>
				</c:forEach>

				<c:if test="${paginationInfo.totalRecordCount == 0}">
					<tr>
						<td colspan="5">No Result</td>
					</tr>
				</c:if>
			</tbody>
		</table>

		<c:if test="${paginationInfo.totalRecordCount != 0}">
		<div class="pagination">
			<ui:pagination paginationInfo = "${paginationInfo}" type="image" jsFunction="fnPage" />
		</div>
		</c:if>
	</article>
</main>
<%@include file="../layout/footer.jsp"%>