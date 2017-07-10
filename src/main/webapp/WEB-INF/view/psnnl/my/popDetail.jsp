<%@ page contentType="text/html; charset=utf-8" session="false" %>
<%@ taglib prefix="c"	 uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" 	 uri="http://egovframework.gov/ctl/ui"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=2.0, user-scalable=yes" />
	<meta name="keywords" content="충청남도, 소방" />
	<meta name="description" content="충청남도소방본부 소방공무원 인사배치 시스템에 오신것을 환영합니다." />
    <link rel="stylesheet" type="text/css" href="/css/sub.css" />
    <link rel="stylesheet" type="text/css" href="/css/popup.css" />

	<script type="text/javascript" src="/js/jquery/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="/js/jquery/jquery.easing.1.3.js"></script>
    <title>충청남도소방본부 소방공무원 인사배치 시스템 팝업</title>
    <style type="text/css">
    table.table tbody tr td{
    	padding:5px;
    }
    </style>
</head>
<body style="width:100%;">
	<header id="header">
		<div class="accessibility">
			<a href="#container">본문 바로가기</a>
		</div>
		<h1 class="skip">충청남도소방본부 Chungnam Fire serverice Headpuarter 소방공무원 인사배치 시스템</h1>
	</header>
	<main id="container">
		<h2>본인이 탈락한 상위 희망지 지수 커트라인</h2>
		<div id="contents" style="min-height: 433px;">
			<table class="table">
					<colgroup>
						<col class="w25p" />
						<col class="w25p" />
						<col class="w25p" />
						<col class="w25p" />
					</colgroup>
					<thead>
						<tr>
							<th scope="col">희망순위</th>
							<th scope="col">관서명</th>
							<th scope="col">커트라인</th>
							<th scope="col">마감순위</th>
						</tr>
					</thead>
					<tbody class="tbody popTable">
						<c:forEach var="result" items="${resultList}">
						<tr>
							<td>${result.level } 희망지</td>
							<td>${result.orgnzName}</td>
							<td>${result.passScore}</td>
							<td>${result.orgnzRank}순위 마감</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
		</div>
	</main>
	<footer id="footer" style="bottom:0px;left:0px;right:0px;">
		<input type="button" value="닫기" class="btn" onclick="window.close();"/>
	</footer>
</body>
</html>