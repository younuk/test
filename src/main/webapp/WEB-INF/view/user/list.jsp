<%@ page contentType="text/html; charset=utf-8" session="false" %>
<%@ taglib prefix="c"	 uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" 	 uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<jsp:include page="../layout/header.jsp" />

<script type="text/javascript">
$(document).ready(function(){
	cmmSetMenu("sideUserMng", 0);	//set menu

	$(".calendar").datepicker(datepickerOption);

	$("#userSearchVo input").keypress(function(e) {
        if (e.keyCode == 13){
        	cmmPage(document.userSearchVo, 1);
        }
    });

	$(".tbody tr td").click(function(){
		if($(this).index() < 7){
			document.userSearchVo.userId.value = $(this).parent().attr("id")
			document.userSearchVo.action = "<c:url value='/user/view.do'/>";
			document.userSearchVo.submit();
		}
	});

	$("#btnSearch").click(function(){
		cmmPage(document.userSearchVo, 1);
	});

	$("#btnAdd").click(function(){
		var runBatchInfo = cmmChkInProgress();
		if(!runBatchInfo){
			location.href = "<c:url value='/user/view.do'/>";
		}else{
			alert("배치프로세스중인 인사배치가 존재합니다. 사용자를 등록할 수 없습니다.");
			return false;
		}
	});

	$("#btnPrintAll").click(function(){
		var userIdArr = "";
		$(".tbody tr").each(function(idx){
			if(idx > 0) userIdArr += ",";
			userIdArr += $(this).attr("id");
		});
		var url = "<c:url value='/user/selectPopJang.do'/>?div=all&userId="+userIdArr;
		window.open(url, "btnPrintjang", cmmPopup(900, 900));
	});

	$("#excelFileTemp").change(function(){
		console.log("test");
		var formData = new FormData();
        formData.append("excelFile", $("input[name=excelFile]")[0].files[0]);

		$.ajax({
            url: "<c:url value='/user/excelUploadAjax'/>",
            processData: false,
            contentType: false,
            data: formData,
            type: 'POST',
            success: function(data){
				alert(data.result+"건 등록완료 했습니다.");
				location.reload();
            }, error:function(data, test){
				alert("시스템 오류가 발생하였습니다. \n관리자에게 문의하세요.");
				location.reload();
            }
        });
	});
});

function checkInProgress(){
	var runBatchInfo;
	$.ajax({
		url : "<c:url value='/batch/checkInProgress.do'/>"
		, type: "POST"
		, async : false
		, dataType: "json"
       	, success : function(data) {
       		runBatchInfo = data.result;
      	}
    });
	return runBatchInfo;
}

function fnPage(pageNo){
	cmmPage(document.userSearchVo, pageNo);
}

function fnPrintPage(param){
	var url = "<c:url value='/user/selectPopJang.do'/>?userId="+param;
	window.open(url, "btnPrintjang", cmmPopup(900, 900));
}
</script>
<main class="colgroup">
	<header class="sub_head">
		<div class="path">
			<ul class="clearfix">
				<li>홈</li>
				<li class="actived">사용자관리</li>
				<li class="actived">사용자목록</li>
			</ul>
		</div>
	</header>
	<article id="contents">
		<form:form commandName="userSearchVo" name="userSearchVo" class="search" method="post" onsubmit="return false;">
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
						<th scope="col">소속</th>
						<td><form:select path="orgnzId" items="${orgnzCombo}">
						    </form:select></td>
						<th scope="col">이름</th>
						<td><form:input path="name" class="w85p"/></td>
					</tr>
					<tr>
						<th scope="col">ID</th>
						<td><form:input path="loginId" class="w85p"/></td>
						<th scope="col">인사대상여부</th>
						<td><form:select path="targetYn">
								<form:option value="">선택</form:option>
								<form:option value="Y">Y</form:option>
								<form:option value="N">N</form:option>
							</form:select></td>
					</tr>
					<tr>
						<th scope="col">계급</th>
						<td><form:select path="rankCodeId" items="${rankCombo}"/></td>
						<th scope="col">특수직무</th>
						<td><form:select path="specialDutyCodeId" items="${spcDutyCombo}"/></td>
					</tr>
				</tbody>
			</table>
			<br/>
			<input type="button" value="조회" class="btn" id="btnSearch" />
			<input type="button" value="등록 " class="btn" id="btnAdd"/>
			<input type="button" value="임용장일괄출력" class="btn" id="btnPrintAll" />
			<form:hidden path="userId"/>
			<form:hidden path="pageIndex" />
		</fieldset>
		</form:form>

		<table class="table">
			<caption>환자발생상황에 대한 표 - 환자태그ID, 성명, 성별, 나이, 1차 분류결과, 2차 분류결과, 이송예정병원, 최종이송병원 순으로 내용을 제공하고 있습니다.</caption>
			<colgroup>
				<col style="width:12%;" />
				<col style="width:9%;" />
				<col style="width:12%;" />
				<col style="width:13%;" />
				<col style="width:13%;" />
				<col style="width:10%;" />
				<col style="width:8%;" />
				<col style="width:14%;" />
				<col style="width:9%;" />
			</colgroup>
			<thead>
				<tr>
					<th scope="col">ID</th>
					<th scope="col">이름</th>
					<th scope="col">생년월일</th>
					<th scope="col">소속/계급</th>
					<th scope="col">최초임용일/<br>현계급임용일</th>
					<th scope="col">특수직무</th>
					<th scope="col">5년유예여부</th>
					<th scope="col">인사대상여부/<br>희망관서입력여부</th>
					<th scope="col">임용장출력</th>
				</tr>
			</thead>
			<tbody class="tbody">
				<c:forEach var="result" items="${resultList}">
				<tr id="${result.userId }">
					<td>${result.loginId }</td>
					<td>${result.name}</td>
					<td>${result.birthDt }</td>
					<td>${result.orgnzName }/${result.rankCodeName }</td>
					<td>${result.initialAppmnt}/<br>${result.rankAppmnt}</td>
					<td>${result.specialDutyCodeName }</td>
					<td>${result.stay5YearStartDt }</td>
					<td><c:if test="${empty result.psnnlBatchId }">N</c:if><c:if test="${not empty result.psnnlBatchId }">Y/${result.tiePriorityYn }</c:if></td>
					<td><input type="button" value="출력" class="btn type8" id="btnPrintjang" onclick="fnPrintPage('${result.userId }')"/></td>
				</tr>
				</c:forEach>

				<c:if test="${paginationInfo.totalRecordCount == 0}">
					<tr>
						<td colspan="9">No Result</td>
					</tr>
				</c:if>
			</tbody>
		</table>

		<c:if test="${paginationInfo.totalRecordCount != 0}">
		<div class="pagination">
			<ui:pagination paginationInfo = "${paginationInfo}" type="image" jsFunction="fnPage" />
		</div>
		</c:if>

		<br/>
		<table class="table">
           <colgroup>
               <col class="w20p" />
               <col class="w40p" />
               <col class="w40p" />
           </colgroup>
           <tbody class="text_left">
               <tr>
                   <th scope="col">사용자일괄등록</th>
                   <td><a href="/user/fileDown.do">일괄등록서식다운로드</a></td>
                   <td><input id="excelFileTemp" type="file" name="excelFile" accept=".xls,.xlsx" class="input-file" size="10" ></td>
               </tr>
           </tbody>
       </table>
	</article>
</main>
<jsp:include page="../layout/footer.jsp" />