<%@ page contentType="text/html; charset=utf-8" session="false" %>
<%@ taglib prefix="c"	 uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" 	 uri="http://egovframework.gov/ctl/ui"%>
<jsp:include page="../../layout/header.jsp" />

<script type="text/javascript">
$(document).ready(function(){
	cmmSetMenu("sideUser", 0);	//set menu

	$("#btnStay5Clear").click(function(){
		if(!confirm("5년 유예여부를 해제하시겠습니까? \n\n주의!!재설정 할 수 없습니다.")) return false;

		var data = cmmCallAjax("<c:url value='/user/changeStay5.do'/>", "");
		if(data.result == "success"){
   			alert(_REG_SUCCESS);
   			location.reload();
   		}else{
   			alert(_REG_FAIL);
   		}
	});
	$("#btnPwdReset").click(function(){
		if($("#password1").val() != $("#password2").val()){
			alert("비밀번호가 일치하지 않습니다.");
			return false;
		}
		if(!cmmChkPwd($("#password1")))	return false;

		var data = cmmCallAjax("<c:url value='/user/changePwd.do'/>", {password:$("#password1").val()});
		if(data.result == "success"){
   			alert(_REG_SUCCESS);
   			$("#password1,#password2").val("");
   		}else{
   			alert(_REG_FAIL);
   		}
	});
});
</script>
<main class="colgroup">
	<header class="sub_head">
		<div class="path">
			<ul class="clearfix">
				<li>홈</li>
				<li class="actived">개인인사관리</li>
				<li class="actived">개인정보관리</li>
			</ul>
		</div>
	</header>
	<article id="contents">
		<table class="table">
			<colgroup>
				<col class="w25p" />
				<col class="w75p" />
			</colgroup>
			<tbody class="text_left">
				<tr>
					<th>이름</th>
					<td><c:out value="${userVo.name }"/></td>
				</tr>
				<tr>
					<th>소속</th>
					<td><c:out value="${userVo.orgnzName }"/></td>
				</tr>
				<tr>
					<th>계급</th>
	`				<td><c:out value="${userVo.rankCodeName }"/></td>
				</tr>
				<tr>
					<th>생년월일</th>
					<td><c:out value="${userVo.birthDt}"/></td>
				</tr>
				<tr>
					<th>최초임용일</th>
					<td><c:out value="${userVo.initialAppmnt}"/></td>
				</tr>
				<tr>
					<th>현계급임용일</th>
					<td><c:out value="${userVo.rankAppmnt}"/></td>
				</tr>
				<tr>
					<th>현기관임용일</th>
					<td><c:out value="${userVo.orgnzAppmnt}"/></td>
				</tr>
				<tr>
					<th>최종인사일</th>
					<td><c:out value="${userVo.ppmntBatch}"/></td>
				</tr>
				<tr>
					<th>특수직무/자격증</th>
					<td><c:out value="${userVo.specialDutyCodeName}"/></td>
				</tr>
				<c:choose>
					<c:when test="${empty userVo.stay5YearStartDt}">
						<tr>
							<th>5년유예여부</th>
							<td>아니오  </td>
						</tr>
					</c:when>
					<c:otherwise>
						<tr>
							<th>5년유예여부 (기간)</th>
							<td>예 (<c:out value="${userVo.stay5YearStartDt}"/>)
							 <c:if test="${runBatchInfo.runYn eq 'N' }">
							 &nbsp;&nbsp;<input type="button" value="해제" class="btn type8" id="btnStay5Clear" />
							 </c:if></td>
						</tr>
						<tr>
							<th>인사대상포함예정시기</th>
							<td><c:out value="${userVo.nextPsnnlDt}"/></td>
						</tr>
					</c:otherwise>
				</c:choose>


				<%--
				<tr>
					<th>동점자우선대상자여부</th>
					<td><c:choose>
							<c:when test="${userVo.tiePriorityYn eq 'Y'}">해당</c:when>
							<c:otherwise>해당 없음</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<th>당해계급휴직일</th>
					<td><c:forEach var="result" items="${userVo.absences}" varStatus="status">
					       <c:out value="${result.startDt}"/> - <c:out value="${result.endDt }"/>
					       <c:if test="${not status.last}"><br/></c:if>
					    </c:forEach>
					</td>
				</tr>
				<tr>
					<th>본부 및 학교 근무기간</th>
					<td><c:forEach var="result" items="${userVo.dutyPeriods}" varStatus="status">
					       <c:out value="${result.startDt}"/> - <c:out value="${result.endDt }"/>
                                 <c:if test="${not status.last}"><br/></c:if>
                              </c:forEach>
                          </td>
				</tr>
				 --%>
				<tr>
					<th>비밀번호변경</th>
					<td>입력:<input type="password" id="password1"/>
						&nbsp;&nbsp;재입력:<input type="password" id="password2"/>
						&nbsp;&nbsp;<input type="button" value="변경" class="btn type8" id="btnPwdReset" />
                    </td>
				</tr>
			</tbody>
		</table>
	</article>
</main>

<jsp:include page="../../layout/footer.jsp" />