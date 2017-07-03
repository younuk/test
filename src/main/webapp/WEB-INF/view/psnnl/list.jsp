<%@ page contentType="text/html; charset=utf-8" session="false" %>
<%@ taglib prefix="c"	 uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" 	 uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="sec"  uri="http://www.springframework.org/security/tags" %>

<jsp:include page="../layout/header.jsp" />

<script type="text/javascript">
$(document).ready(function(){
	cmmSetMenu("sideUserMng", 0);	//set menu

	$(".calendar").datepicker(datepickerOption);

	$("#psnnlSearchVo input").keypress(function(e) {
        if (e.keyCode == 13){
        	cmmPage(document.psnnlSearchVo, 1);
        }
    });
	$("#psnnlSearchVo select").change(function() {
		cmmPage(document.psnnlSearchVo, 1);
    });

	$(".tbody tr td").click(function(){
		if($(this).index() < 7){
			document.psnnlSearchVo.psnnlId.value = $(this).parent().attr("id");
			document.psnnlSearchVo.action = "<c:url value='/psnnl/view.do'/>";
			document.psnnlSearchVo.submit();
		}
	});

	$("#btnSearch").click(function(){
		cmmPage(document.psnnlSearchVo, 1);
	});

	$("#btnDetail").click(function(){
		var url = "<c:url value='/psnnl/my/popDetail.do'/>"
				 + "?psnnlBatchId="+$(this).parent().parent().attr("pbi")
				 + "&userId="+$(this).parent().parent().attr("uid");
		window.open(url, "popDetailPopup", cmmPopup(450, 600));
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
				<li class="actived">사용자관리</li>
				<li class="actived">인사이력조회</li>
			</ul>
		</div>
	</header>
	<article id="contents">
		<form:form commandName="psnnlSearchVo" name="psnnlSearchVo" class="search" method="post" onsubmit="return false;">
		<fieldset>
			<table class="table">
				<colgroup>
                     <col class="w20p" />
                     <col class="w80p" />
				</colgroup>
				<tbody>
					<tr>
						<th scope="col">이름</th>
						<td>${psnnlSearchVo.name }</td>
					</tr>
				</tbody>
			</table>
			<br/>
			<table class="table">
                 <colgroup>
                     <col class="w20p" />
                     <col class="w30p" />
                     <col class="w20p" />
                     <col class="w30p" />
                 </colgroup>
                 <tbody class="text_left">
                     <tr>
                         <th scope="col">인사구분</th>
                         <td><form:select path="divCodeId" items="${psnnlDivCombo}"/></td>
                         <th scope="col">인사일시</th>
                         <td><form:input path="srchStartDt" class="calendar"/> - <form:input path="srchEndDt" class="calendar"/></td>
                     </tr>
                     <tr>
                         <th scope="col">발령관서명</th>
                         <td colspan="3"><form:select path="srchOrgnzId" items="${orgnzCombo}">
                             </form:select></td>
                     </tr>
                 </tbody>
             </table>
			<br/>
			<input type="button" value="조회" class="btn" id="btnSearch" />
			<form:hidden path="psnnlId"/>
			<form:hidden path="pageIndex" />
		</fieldset>
		</form:form>

		<table class="table">
			<colgroup>
				<col style="width:10%;" />
				<col style="width:10%;" />
				<col style="width:15%;" />
				<col style="width:10%;" />
				<col style="width:15%;" />
				<col style="width:10%;" />
				<col style="width:15%;" />
				<col style="width:15%;" />
			</colgroup>
			<thead>
				<tr>
					<th scope="col">인사구분</th>
					<th scope="col">상태</th>
					<th scope="col">인사일시</th>
					<th scope="col">희망관서입력여부</th>
					<th scope="col">발령관서명</th>
					<th scope="col">희망관서순위</th>
					<th scope="col">불만족지수</th>
					<th scope="col">탈락관서순위내합격선</th>
				</tr>
			</thead>
			<tbody class="tbody">
				<c:forEach var="result" items="${resultList}">
				<tr id="${result.psnnlId }" uid="${result.userId }" pbi="${result.psnnlBatchId }">
					<td>${result.divCodeName }</td>
					<td>${result.statCodeName}</td>
					<td>${result.startDt }</td>
					<td>${result.tiePriorityYn }</td>

					<sec:authorize access="hasAnyRole('ROL001')">
						<td>${result.orgnzName }</td>
						<td>${result.orgnzRank }</td>
					</sec:authorize>
					<sec:authorize access="hasAnyRole('ROL002')">
						<c:choose>
							<c:when test="${result.statCodeId eq 'PST006'}">
								<td>${result.orgnzName }</td>
								<td>${result.orgnzRank }</td>
							</c:when>
							<c:otherwise>
								<td></td>
								<td></td>
							</c:otherwise>
						</c:choose>
					</sec:authorize>

					<td>${result.disstsfLevel }</td>
					<td><c:if test="${result.statCodeId eq 'PST006' and result.divCodeName eq '정기'}"><input type="button" value="상세" class="btn type8" id="btnDetail" /></c:if></td>
				</tr>
				</c:forEach>

				<c:if test="${paginationInfo.totalRecordCount == 0}">
					<tr>
						<td colspan="8">No Result</td>
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