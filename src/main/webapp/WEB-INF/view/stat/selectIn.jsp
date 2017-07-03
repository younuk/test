<%@ page contentType="text/html; charset=utf-8" session="false" %>
<%@ taglib prefix="c"	 uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../layout/header.jsp" />

<script type="text/javascript">
$(document).ready(function(){
	cmmSetMenu("sideStatMng", 1);	//set menu

	$("#btnSearch").click(function(){
		document.statSearchForm.action = "<c:url value='/stat/selectIn.do' />";
		document.statSearchForm.submit();
	});

	$("#btnExcel").click(function(){
		document.statSearchForm.action = "<c:url value='/stat/inFileDown.do' />";
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
				<li class="actived">소방서별 전입 기준 지수</li>
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
					<th scope="col">관서명</th>
					<th scope="col">1희망지</th>
					<th scope="col">2희망지</th>
					<th scope="col">3희망지</th>
					<th scope="col">4희망지</th>
					<th scope="col">5희망지</th>
					<th scope="col">6희망지</th>
					<th scope="col">7희망지</th>
					<th scope="col">8희망지</th>
					<th scope="col">9희망지</th>
					<th scope="col">10희망지</th>
					<th scope="col">11희망지</th>
					<th scope="col">12희망지</th>
					<th scope="col">13희망지</th>
					<th scope="col">14희망지</th>
					<th scope="col">15희망지</th>
					<th scope="col">16희망지</th>
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
				<c:forEach var="result" items="${resultList}">
					<c:set var="total01" value="${total01 + result.o1}"/>
					<c:set var="total02" value="${total02 + result.o2}"/>
					<c:set var="total03" value="${total03 + result.o3}"/>
					<c:set var="total04" value="${total04 + result.o4}"/>
					<c:set var="total05" value="${total05 + result.o5}"/>
					<c:set var="total06" value="${total06 + result.o6}"/>
					<c:set var="total07" value="${total07 + result.o7}"/>
					<c:set var="total08" value="${total08 + result.o8}"/>
					<c:set var="total09" value="${total09 + result.o9}"/>
					<c:set var="total10" value="${total10 + result.o10}"/>
					<c:set var="total11" value="${total11 + result.o11}"/>
					<c:set var="total12" value="${total12 + result.o12}"/>
					<c:set var="total13" value="${total13 + result.o13}"/>
					<c:set var="total14" value="${total14 + result.o14}"/>
					<c:set var="total15" value="${total15 + result.o15}"/>
					<c:set var="total16" value="${total16 + result.o16}"/>
				<tr>
					<td>${result.name}</td>
					<td>${result.o1}</td>
					<td>${result.o2}</td>
					<td>${result.o3}</td>
					<td>${result.o4}</td>
					<td>${result.o5}</td>
					<td>${result.o6}</td>
					<td>${result.o7}</td>
					<td>${result.o8}</td>
					<td>${result.o9}</td>
					<td>${result.o10}</td>
					<td>${result.o11}</td>
					<td>${result.o12}</td>
					<td>${result.o13}</td>
					<td>${result.o14}</td>
					<td>${result.o15}</td>
					<td>${result.o16}</td>
				</tr>
				</c:forEach>
				<tr>
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
				</tr>
			</tbody>
		</table>

	</article>
</main>
<%@include file="../layout/footer.jsp"%>