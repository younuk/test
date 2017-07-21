<%@ page contentType="text/html; charset=utf-8" session="false" %>
<%@ taglib prefix="c"	 uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="../layout/header.jsp" />

<script type="text/javascript">
$(document).ready(function(){
	cmmSetMenu("sideUserMng", 0);	//set menu

	// 조회 설정
	if($("#stayLevel").val()){
		$("#stayYn1").prop("checked", true);
	}else{
		$("#spanStay").hide();
	}

	$("#btnClose").click(function(){
		fnGoList();
	});

	$("select[name^=hopeOrgnz]").change(function(n){
		//이후 select options 삭제
		var idx = $("select[name^=hopeOrgnz]").index(this);
		$("select[name^=hopeOrgnz]:gt("+idx+")").children("option").remove();

		var options = $(this).children("option").clone();

		var tgtSel = $("select#hopeOrgnz"+(Number(idx)+1));
		$(tgtSel).append(options);
		$(tgtSel).children("option[value="+$(this).val()+"]").remove();

		return false;
	});

	$("#stayYn1").change(function(){
		if($(this).is(":checked")){
			$("#spanStay").show();
			makeStayOptions()
		}else
			$("#spanStay").hide()

		return false;
	});

	$("#stay5Yn1").change(function(){
		if($(this).is(":checked"))
			$("#spanHope").hide();
		else
			$("#spanHope").show();
		return false;
	});
});
function makeStayOptions(){
	var nowOrgnz = $("#orgnzId").val();
	var idx;
	$("select[name^=hopeOrgnz]").each(function(i, e){
		if(nowOrgnz == $(this).val()){
			idx = i;
			return false;
		}
	});

	$("#stayLevel").children("option").remove();
	var totIdx = $("select[name^=hopeOrgnz]").length;
	for(var i=idx+2; i<=totIdx; i++){
		$("#stayLevel").append("<option value='"+i+"'>"+i+" 순위</option>")
	}
}

function fnGoList(){
	document.psnnlSearchVo.action = "<c:url value='/psnnl/list.do'/>";
	document.psnnlSearchVo.submit();
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
	<form:form commandName="psnnlVo" name="psnnlVo" method="post" onsubmit="return false;">
	<article id="contents">
		<table class="table">
			<colgroup>
                    <col class="w20p" />
                    <col class="w80p" />
			</colgroup>
			<tbody>
				<tr>
					<th scope="col">이름</th>
					<td>${psnnlVo.name }</td>
				</tr>
			</tbody>
		</table>

		<table class="table">
			<colgroup>
				<col class="w20p" />
				<col class="w30p" />
				<col class="w20p" />
				<col class="w30p" />
			</colgroup>
			<tbody class="text_left">
				<tr>
					<th>인사구분</th>
					<td colspan=3><c:out value="${psnnlVo.divCodeName }"/></td>
				</tr>
				<tr>
					<th>상태</th>
					<td colspan=3><c:out value="${psnnlVo.statCodeName }"/></td>
				</tr>
				<tr>
					<th>인사일시</th>
	`				<td colspan=3><c:out value="${psnnlVo.startDt }"/> - <c:out value="${psnnlVo.endDt}"/></td>
				</tr>
				<tr>
					<th>발령관서명</th>
					<td colspan=3><c:out value="${psnnlVo.orgnzName}"/></td>
				</tr>
				<tr>
					<th>희망관서순위</th>
					<td colspan=3><c:out value="${psnnlVo.orgnzRank}"/></td>
				</tr>
				<tr>
					<th>불만족지수</th>
					<td colspan=3><c:out value="${psnnlVo.disstsfLevel}"/></td>
				</tr>
				<tr>
					<th>대상자구분</th>
					<td><c:out value="${psnnlVo.targetCodeName}"/></td>
					<th>승진임용구분</th>
					<td><c:out value="${psnnlVo.prmtCodeName}"/></td>
				</tr>
				<tr>
					<c:choose>
						<c:when test="${empty  psnnlVo.stay5YearStartDt}">
							<th>5년유예여부</th>
							<td>아니오 </td>
						</c:when>
						<c:otherwise>
							<th>5년유예여부 (기간)</th>
							<td>예 (<c:out value="${psnnlVo.stay5YearStartDt}"/>)</td>
						</c:otherwise>
					</c:choose>
					<th>동점자우선대상자여부</th>
					<td><c:out value="${psnnlVo.tiePriorityYn}"/></td>
				</tr>
				<c:if test="${psnnlVo.divCodeName == '정기' }">
					<tr>
						<th>희망관서</th>
						<td colspan=3><c:forEach var="result" items="${orgnzCombo}" varStatus="status">

								<c:if test="${not status.last}">
								${status.index +1} 순위 :  <c:out value="${psnnlVo.hopeOrgnz[status.index].orgnzName}"/>
								<br/>
								</c:if>
							</c:forEach>
						</td>
					</tr>
					<tr>
						<th>현관서 잔류 우선 여부</th>
						<td colspan=3><c:out value="${psnnlVo.stayYn}"/><c:if test="${psnnlVo.stayYn eq 'Y'}">(${psnnlVo.stayLevel}순위)</c:if></td>
					</tr>
				</c:if>
			</tbody>
		</table>
		<div class="btn_group text_center">
			<input type="button" value="목록" class="btn" id="btnClose"/>
		</div>
	</article>
	<!-- 현 기관 -->
	<form:hidden path="psnnlId"/>
	<form:hidden path="orgnzId"/>
	<form:hidden path="userId"/>
	<form:hidden path="psnnlBatchId"/>
	<form:hidden path="name"/>
	</form:form>
</main>

<form:form commandName="psnnlSearchVo" name="psnnlSearchVo" method="post" onsubmit="return false;">
	<form:hidden path="divCodeId"/>
	<form:hidden path="srchStartDt"/>
	<form:hidden path="srchEndDt"/>
	<form:hidden path="srchOrgnzId"/>
</form:form>

<jsp:include page="../layout/footer.jsp" />