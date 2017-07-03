<%@ page contentType="text/html; charset=utf-8" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<jsp:include page="../layout/header.jsp" />

<script type="text/javascript">
$(document).ready(function(){
	cmmSetMenu("sideUserMng", 1);	//set menu

	$(".tbody tr").click(function(){
		document.orgnzSearchVo.orgnzId.value = $(this).attr("id")
		document.orgnzSearchVo.action = "<c:url value='/orgnz/view.do'/>";
		document.orgnzSearchVo.submit();
	});


	$("#btnSearch").click(function(){
		cmmPage(document.orgnzSearchVo, 1);
	});

	$("#btnAdd").click(function(){
		location.href = "<c:url value='/orgnz/view.do'/>";
	});
});

function fnPage(pageNo){
	cmmPage(document.orgnzSearchVo, pageNo);
}
</script>
<main class="colgroup">
	<header class="sub_head">
		<div class="path">
			<ul class="clearfix">
				<li>홈</li>
				<li class="actived">관서관리</li>
				<li class="actived">관서목록</li>
			</ul>
		</div>
	</header>
	<article id="contents">
	<form:form commandName="orgnzSearchVo" name="orgnzSearchVo" class="search" method="post" onsubmit="return false;">

		<fieldset>
			<table class="table">
				<colgroup>
                    <col class="w20p" />
                    <col class="w30p" />
                    <col class="w20p" />
                    <col class="w30p" />
				</colgroup>
				<tbody class="text_left">
					<tr>
						<th scope="col">관서명</th>
						<td><form:select path="srchOrgnzId" items="${orgnzCombo}"/></td>
						<th scope="col">정원</th>
						<td><form:input path="srchNrmCntStart"/>~<form:input path="srchNrmCntEnd"/></td>
					</tr>
					<tr>
						<th scope="col">배치가능인원</th>
						<td><form:input path="srchNowCntStart"/>~<form:input path="srchNowCntEnd"/></td>
						<th scope="col">강제결원</th>
						<td><form:input path="srchVccCntStart"/>~<form:input path="srchVccCntEnd"/></td>
					</tr>
				</tbody>
			</table>
			<br/>
			<input type="button" value="조회" class="btn" id="btnSearch" />
			<input type="button" value="등록" class="btn" id="btnAdd"/>
			<form:hidden path="orgnzId" />
			<form:hidden path="pageIndex" />
		</fieldset>
		</form:form>

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
					<th scope="col">관서명</th>
					<th scope="col">정원</th>
					<th scope="col">강제결원</th>
					<th scope="col">배치가능인원</th>
					<th scope="col">현원</th>
				</tr>
			</thead>
			<tbody class="tbody">
                <c:set var="total01" value="0"/>
                <c:set var="total02" value="0"/>
                <c:set var="total03" value="0"/>
                <c:set var="total04" value="0"/>
				<c:forEach var="result" items="${resultList}">
					<c:set var="total01" value="${total01 + result.nrmTotal}"/>
					<c:set var="total02" value="${total02 + result.vccTotal}"/>
					<c:set var="total03" value="${total03 + result.psbTotal}"/>
					<c:set var="total04" value="${total04 + result.nowTotal}"/>
				<tr id="${result.orgnzId }">
					<td>${result.name }</td>
					<td>${result.nrmTotal }</td>
					<td>${result.vccTotal }</td>
					<td>${result.psbTotal}</td>
					<td>${result.nowTotal }</td>
				</tr>
				</c:forEach>
				<tr>
					<td>총계</td>
					<td>${total01}</td>
					<td>${total02}</td>
					<td>${total03}</td>
					<td>${total04}</td>
				</tr>
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
<jsp:include page="../layout/footer.jsp" />