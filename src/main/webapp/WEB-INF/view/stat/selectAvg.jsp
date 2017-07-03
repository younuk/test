<%@ page contentType="text/html; charset=utf-8" session="false" %>
<%@ taglib prefix="c"	 uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../layout/header.jsp" />

<script type="text/javascript">
$(document).ready(function(){
	cmmSetMenu("sideStatMng", 3);	//set menu

	$("#btnSearch").click(function(){
		document.statSearchForm.action = "<c:url value='/stat/selectAvg.do' />";
		document.statSearchForm.submit();
	});

	$("#btnExcel").click(function(){
		document.statSearchForm.action = "<c:url value='/stat/avgFileDown.do' />";
		document.statSearchForm.submit();
	});
});
</script>
<main class="colgroup">
	<header class="sub_head">
		<div class="path">
			<ul class="clearfix">
				<li>홈</li>
				<li class="actived">통계</li>
				<li class="actived">계급별 평균 지수점수</li>
			</ul>
		</div>
	</header>
	<article id="contents">
		<form name="statSearchForm" class="search" method="post" onsubmit="return false;">
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
                        <th scope="col">인사차수</th>
                        <td><select name="srchDegree">
                        		<c:forEach var="result" items="${degreeCombo}">
                        			<option value="${result.cateCodeId}" <c:if test="${srchInfo.srchDegree eq result.cateCodeId}">selected</c:if>>${result.codeName}</option>
                        		</c:forEach>
                        	</select>
                        </td>
                        <th scope="col">소방관서</th>
                        <td><select name="srchOrgnz">
                        		<option value="">선택</option>
                        		<c:forEach var="result" items="${orgnzCombo}">
                        			<option value="${result.orgnzId}" <c:if test="${srchInfo.srchOrgnz eq result.orgnzId}">selected</c:if>>${result.name}</option>
                        		</c:forEach>
                        	</select>
                        </td>
                    </tr>
                </tbody>
            </table>
			<br/>
			<input type="button" value="조회" class="btn" id="btnSearch" />
			<input type="button" value="엑셀내려받기" class="btn" id="btnExcel"/>
		</fieldset>
		</form>

		<table class="table">
			<colgroup>
					<col />
					<col style="width:33%" />
					<col style="width:33%" />
					<col style="width:33%" />
			</colgroup>
			<thead>
				<tr>
					<th scope="col">계급명</th>
					<th scope="col">인사차수</th>
					<th scope="col">평균지수점수</th>
				</tr>
			</thead>
			<tbody class="tbody">
				<c:forEach var="result" items="${resultList}">
				<tr>
					<td>${result.codename}</td>
					<td>${result.degree}</td>
					<td>${result.disstsflevel}</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</article>
</main>
<%@include file="../layout/footer.jsp"%>