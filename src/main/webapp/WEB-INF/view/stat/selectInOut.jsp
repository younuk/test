<%@ page contentType="text/html; charset=utf-8" session="false" %>
<%@ taglib prefix="c"	 uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../layout/header.jsp" />

<script type="text/javascript">
$(document).ready(function(){
	cmmSetMenu("sideStatMng", 2);	//set menu

	$("#statSearchForm select").change(function() {
		document.statSearchForm.action = "<c:url value='/stat/selectInOut.do' />";
		document.statSearchForm.submit();
    });

	$("#btnSearch").click(function(){
		document.statSearchForm.action = "<c:url value='/stat/selectInOut.do' />";
		document.statSearchForm.submit();
	});

	$("#btnExcel").click(function(){
		document.statSearchForm.action = "<c:url value='/stat/inOutFileDown.do' />";
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
				<li class="actived">관서별 전출입 인원 현황</li>
			</ul>
		</div>
	</header>
	<article id="contents">
		<form name="statSearchForm" id="statSearchForm" class="search" method="post" onsubmit="return false;">
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
                    <tr>
                        <th scope="col">계급</th>
                        <td><select name="srchRank">
                        		<option value="">선택</option>
                        		<c:forEach var="result" items="${rankCombo}">
                        			<option value="${result.codeId}" <c:if test="${srchInfo.srchRank eq result.codeId}">selected</c:if>>${result.codeName}</option>
                        		</c:forEach>
                        	</select>
                        </td>
                        <th scope="col">특수직무</th>
                        <td><select name="srchSpecial">
                        		<option value="">선택</option>
                        		<c:forEach var="result" items="${spcDutyCombo}">
                        			<option value="${result.codeId}" <c:if test="${srchInfo.srchSpecial eq result.codeId}">selected</c:if>>${result.codeName}</option>
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
					<col style="width:6%" />
					<col style="width:6%" />
					<col style="width:6%" />
					<col style="width:6%" />
					<col style="width:6%" />
					<col style="width:6%" />
					<col style="width:6%" />
					<col style="width:6%" />
					<col style="width:6%" />
					<col style="width:6%" />
					<col style="width:6%" />
					<col style="width:6%" />
					<col style="width:6%" />
					<col style="width:6%" />
					<col style="width:6%" />
					<col style="width:6%"/>
			</colgroup>
			<thead>
				<tr>
					<th scope="col" rowspan="2">관서명</th>
					<th scope="col" rowspan="2">정원</th>
					<th scope="col" rowspan="2">현원</th>
					<th scope="col" colspan="8">전입</th>
					<th scope="col" colspan="8">전출</th>
				</tr>
				<tr>
					<th scope="col">소계</th>
					<th scope="col">소방사</th>
					<th scope="col">소방교</th>
					<th scope="col">소방장</th>
					<th scope="col">소방위</th>
					<th scope="col">소방경</th>
					<th scope="col">소방령</th>
					<th scope="col">소방정</th>
					<th scope="col">소계</th>
					<th scope="col">소방사</th>
					<th scope="col">소방교</th>
					<th scope="col">소방장</th>
					<th scope="col">소방위</th>
					<th scope="col">소방경</th>
					<th scope="col">소방령</th>
					<th scope="col">소방정</th>
				</tr>
			</thead>
			<tbody class="tbody">
                <c:set var="total01" value="0"/>
                <c:set var="total02" value="0"/>
                <c:set var="total03" value="0"/>
                <c:set var="total04" value="0"/>
                <c:set var="total05" value="0"/>
                <c:set var="total06" value="0"/>
                <c:set var="total07" value="0"/>
                <c:set var="total08" value="0"/>
                <c:set var="total09" value="0"/>
                <c:set var="total10" value="0"/>
                <c:set var="total11" value="0"/>
                <c:set var="total12" value="0"/>
                <c:set var="total13" value="0"/>
                <c:set var="total14" value="0"/>
                <c:set var="total15" value="0"/>
                <c:set var="total16" value="0"/>
                <c:set var="total17" value="0"/>
                <c:set var="total18" value="0"/>
				<c:forEach var="result" items="${resultList}">
					<c:set var="total01" value="${total01 + result.nrmTotal}"/>
					<c:set var="total02" value="${total02 + result.nowTotal}"/>
					<c:set var="total03" value="${total03 + result.sumrnk}"/>
					<c:set var="total04" value="${total04 + result.rnk001}"/>
					<c:set var="total05" value="${total05 + result.rnk002}"/>
					<c:set var="total06" value="${total06 + result.rnk003}"/>
					<c:set var="total07" value="${total07 + result.rnk004}"/>
					<c:set var="total08" value="${total08 + result.rnk005}"/>
					<c:set var="total09" value="${total09 + result.rnk006}"/>
					<c:set var="total10" value="${total10 + result.rnk007}"/>
					<c:set var="total11" value="${total11 + result.sumoutrnk}"/>
					<c:set var="total12" value="${total12 + result.outrnk001}"/>
					<c:set var="total13" value="${total13 + result.outrnk002}"/>
					<c:set var="total14" value="${total14 + result.outrnk003}"/>
					<c:set var="total15" value="${total15 + result.outrnk004}"/>
					<c:set var="total16" value="${total16 + result.outrnk005}"/>
					<c:set var="total19" value="${total17 + result.outrnk006}"/>
					<c:set var="total10" value="${total18 + result.outrnk007}"/>
				<tr>
					<td>${result.name}</td>
					<td>${result.nrmTotal}</td>
					<td>${result.nowTotal}</td>
					<td>${result.sumrnk}</td>
					<td>${result.rnk001}</td>
					<td>${result.rnk002}</td>
					<td>${result.rnk003}</td>
					<td>${result.rnk004}</td>
					<td>${result.rnk005}</td>
					<td>${result.rnk006}</td>
					<td>${result.rnk007}</td>
					<td>${result.sumoutrnk}</td>
					<td>${result.outrnk001}</td>
					<td>${result.outrnk002}</td>
					<td>${result.outrnk003}</td>
					<td>${result.outrnk004}</td>
					<td>${result.outrnk005}</td>
					<td>${result.outrnk006}</td>
					<td>${result.outrnk007}</td>
				</tr>
				</c:forEach>
				<tr style="background-color: #ebebeb;">
					<td>총계</td>
					<td>${total01}</td>
					<td>${total02}</td>
					<td>${total03}</td>
					<td>${total04}</td>
					<td>${total05}</td>
					<td>${total06}</td>
					<td>${total07}</td>
					<td>${total08}</td>
					<td>${total09}</td>
					<td>${total10}</td>
					<td>${total11}</td>
					<td>${total12}</td>
					<td>${total13}</td>
					<td>${total14}</td>
					<td>${total15}</td>
					<td>${total16}</td>
					<td>${total17}</td>
					<td>${total18}</td>
				</tr>
			</tbody>
		</table>

	</article>
</main>
<%@include file="../layout/footer.jsp"%>