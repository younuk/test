<%@ page contentType="text/html; charset=utf-8" session="false" %>
<%@ taglib prefix="c"	 uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:include page="../../layout/header.jsp" />

<script type="text/javascript">
$(document).ready(function(){
	cmmSetMenu("sideUser", 1);		//set menu
	cmmResultMsg("${result}");		//show result message

	$("#btnSave").click(function(){
		if(!$("#stayYn1").is(":checked")){
			$("#stayLevel").remove();
		}else{
			var nowOrgnz = $("#orgnzId").val();
			var idx;
			$("select[name^=hopeOrgnz]").each(function(i, e){
				if(nowOrgnz == $(this).val()){
					idx = i+1;
					return false;
				}
			});

			if(idx-2 < $("#stayLevel").val()){
				alert("희망관서 우선순위를 다시 선택하세요");
				makeStayOptions();
				return false;
			}

		}

		var validYn = true;
		$("select[name^=hopeOrgnz]").each(function(){
			if(!$(this).val()){
				alert("모든 희망관서를 입력하세요.");
				validYn = false;
				return false;
			}
		});
		if(!validYn || !confirm(_REG_CONFIRM)){	return false;	}

		document.psnnlVo.action = "<c:url value='/psnnl/my/add.do'/>";
		document.psnnlVo.submit();
	});

	$("#btnClose").click(function(){
		location.href = "<c:url value='/psnnl/my/list.do'/>";
	});

	$("select[name^=hopeOrgnz]").change(function(n){
		//이후 select options 삭제
		var idx = $("select[name^=hopeOrgnz]").index(this);
		$("select[name^=hopeOrgnz]:gt("+idx+")").children("option").remove();

		var selVal = $(this).val();
		if(selVal){
			var tgtSel = $("select#hopeOrgnz"+(Number(idx)+1));

			$(tgtSel).append($(this).children("option").clone());
			$(tgtSel).children("option[value="+selVal+"]").remove();
			$(tgtSel).val("");


			$("#stayYn1").attr("disabled", false);
			$("#spanStay").show();
			if(selVal == $("#orgnzId").val()){
				makeStayOptions();
			}
		}
		return false;
	});

	if("${psnnlVo.divCodeName }" == '정기' && "${psnnlVo.statCodeId}" =="PST003"){
		//처음에 .. 희망관서 입력 설정
		var orgnzCnt = Number("${fn:length(orgnzCombo)}");
		for(var idx = 0; idx < orgnzCnt-1; idx++){
			var temp = $("#hopeOrgnz"+idx).attr("selmnl");
			console.log(temp );
			$("#hopeOrgnz"+idx).val(temp);
			$("#hopeOrgnz"+idx).trigger("change");
		}
	}


	$("#stay5Yn1").change(function(){
		console.log($(this).is(":checked"));
		if($(this).is(":checked"))
			$("#spanHope").hide();
		else
			$("#spanHope").show();
		return false;
	});


	$("#stayYn1").change(function(){
		if($(this).is(":checked")){
			$("#spanStay").show();
			makeStayOptions();
		}else
			$("#spanStay").hide()

		return false;
	});

	var stayLevel = "${psnnlVo.stayLevel}";
	if(stayLevel != ''){
		$("#stayYn1").prop("checked", true);
		$("#stayYn1").change();
		$("#stayLevel").val(stayLevel);
	}else{
		$("#spanStay").hide();
	}

});
function makeStayOptions(){
	var nowOrgnz = $("#orgnzId").val();
	var idx;
	$("select[name^=hopeOrgnz]").each(function(i, e){
		if(nowOrgnz == $(this).val()){
			idx = i+1;
			return false;
		}
	});
	if(idx-2 <=0){
		$("#stayYn1").prop("checked", false);
		$("#stayLevel").val("");
		$("#spanStay").hide();
		$("#stayYn1").attr("disabled", true);
	}

	$("#stayLevel").children("option").remove();
	var totIdx = $("select[name^=hopeOrgnz]").length;
	for(var i=1; i<= (idx-2); i++){
		$("#stayLevel").append("<option value='"+i+"'>"+i+" 순위</option>")
	}
}

</script>

<c:set var="hopeRegYn" value="${psnnlVo.statCodeId eq 'PST003'}"/>
<main class="colgroup">
	<header class="sub_head">
		<div class="path">
			<ul class="clearfix">
				<li>홈</li>
				<li class="actived">개인인사관리</li>
				<li class="actived">개인인사관리 상세보기</li>
			</ul>
		</div>
	</header>
	<form:form commandName="psnnlVo" name="psnnlVo" method="post" onsubmit="return false;">
	<article id="contents">
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
	`				<td colspan=3><c:out value="${psnnlVo.startDt }"/></td>
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
					<td><c:out value="${psnnlVo.targetCodeName }"/></td>
					<th>승진임용구분</th>
					<td><c:out value="${psnnlVo.prmtCodeName}"/></td>
				</tr>
				<tr>
					<c:choose>
						<c:when test="${empty  psnnlVo.stay5YearStartDt}">

							<th>5년유예여부</th>
							<td>아니오</td>
						</c:when>
						<c:otherwise>
							<th>5년유예여부 (기간)</th>
							<td>예 (<c:out value="${psnnlVo.stay5YearStartDt}"/>)</td>
						</c:otherwise>
					</c:choose>
					<th>동점자우선대상자여부</th>
					<td><c:choose>
							<c:when test="${psnnlVo.tiePriorityYn == 'Y'}">예</c:when>
							<c:otherwise>아니오</c:otherwise>
						</c:choose>
					</td>
				</tr>

				<c:if test="${psnnlVo.divCodeName == '정기' }">
				<tr>
					<th>희망관서</th>
					<td colspan=3><c:if test="${hopeRegYn}">
							<span id="spanHope">
								<c:forEach var="result" items="${orgnzCombo}" varStatus="status">
									<c:if test="${not status.last}">
										<c:set var="selName">hopeOrgnz[${status.index}].orgnzId</c:set>
										${status.index +1} 순위 :
										<select name="${selName}" id="hopeOrgnz${status.index}" selmnl="${psnnlVo.hopeOrgnz[status.index].orgnzId}">
											<c:if test="${status.first}">
												<c:forEach var="options" items="${orgnzCombo}">
													<option value="${options.key }">${options.value }</option>
												</c:forEach>
											</c:if>
										</select>
										<br/>
									</c:if>
								</c:forEach>
								<br/>
								현관서잔류 우선선택여부  <form:checkbox path="stayYn" value="Y"/>
								<span id="spanStay">
									<br/>희망관서 우선선택순위
									<form:select path="stayLevel">
										<option value="">선택</option>
										<c:forEach var="options" items="${orgnzCombo}" varStatus="status">
											<c:set var="idx">${status.index}</c:set>
											<form:option value="${idx}">${idx} 순위 </form:option>
										</c:forEach>
									</form:select>
								</span>
							</span>
						</c:if>
						<c:if test="${!hopeRegYn }">
							<c:forEach var="result" items="${orgnzCombo}" varStatus="status">
								<c:if test="${not status.last}">
									${status.index +1} 순위 :  <c:out value="${psnnlVo.hopeOrgnz[status.index].orgnzName}"/>
									<br/>
								</c:if>
						</c:forEach>
						</c:if>
					</td>
				</tr>
				</c:if>
			</tbody>
		</table>
		<div class="btn_group text_center">
			<c:if test="${hopeRegYn}"><input type="button" value="저장" class="btn" id="btnSave"/></c:if>
			<input type="button" value="목록" class="btn" id="btnClose"/>
		</div>
	</article>
	<!-- 현 기관 -->
	<form:hidden path="psnnlId"/>
	<form:hidden path="orgnzId"/>
	<form:hidden path="userId"/>
	<form:hidden path="psnnlBatchId"/>
	<form:hidden path="startDt"/>
	</form:form>
</main>
<jsp:include page="../../layout/footer.jsp" />