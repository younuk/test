<%@ page contentType="text/html; charset=utf-8" session="false" %>
<%@ taglib prefix="c"	 uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" 	 uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=2.0, user-scalable=yes" />
	<meta name="keywords" content="충청남도, 소방" />
	<meta name="description" content="충청남도소방본부 소방공무원 인사배치 시스템에 오신것을 환영합니다." />
    <link rel="stylesheet" type="text/css" href="/css/designation.css" />
    <script type="text/javascript" src="/js/jquery/jquery-3.1.1.min.js"></script>
    <title>충청남도소방본부 소방공무원 인사배치 시스템 팝업</title>
	<script type="text/javascript">
		window.print();
	</script>
</head>
<body>
	<header id="header">
		<div class="accessibility">
			<a href="#container">본문 바로가기</a>
		</div>
		<h1 class="skip">충청남도소방본부 Chungnam Fire serverice Headpuarter 소방공무원 인사배치 시스템</h1>
	</header>

	<c:forEach var="result" items="${list}">
	<main id="container">
		<article>
			<h2>임용장</h2>
			<strong class="command">
				<c:choose>
					<c:when test="${not empty result.prmtname and not empty result.transname}">(승진/전보)</c:when>
					<c:when test="${not empty result.newname}">${result. newname}</c:when>
					<c:when test="${not empty result.prmtname}">${result. prmtname}</c:when>
					<c:when test="${not empty result.transname}">${result. transname}</c:when>
					<c:otherwise>&nbsp;</c:otherwise>
				</c:choose>
			</strong>
			<div class="clearfix">
				<ul>
					<li>${result.exorgnzname}</li>
					<li>${result.exrankname}<strong>${result.username}</strong></li>

				</ul>
			</div>
			<p><c:choose>
					<c:when test="${not empty result.prmtname or not empty result.newname}">${result.rankname}에 임함</c:when>
					<c:otherwise>&nbsp;</c:otherwise>
			   </c:choose>
			   <br />
			   <c:choose>
					<c:when test="${not empty result.transname or not empty result.newname}">${result.orgnzname} 근무를 명함</c:when>
					<c:otherwise>&nbsp;</c:otherwise>
			   </c:choose></p>
			<time datetime="2016-07-01">${result.dtyear}년 ${result.dtmonth}월 ${result.dtday}일</time>
			<strong class="who">충청남도지사</strong>
		</article>
	</main>
	</c:forEach>
</body>
</html>