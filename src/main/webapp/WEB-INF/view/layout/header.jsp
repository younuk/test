<%@ page contentType="text/html; charset=utf-8" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=2.0, user-scalable=yes" />
	<meta name="keywords" content="충청남도, 소방" />
	<meta name="description" content="충청남도소방본부 소방공무원 인사배치 시스템에 오신것을 환영합니다." />

    <link rel="stylesheet" type="text/css" href="/css/sub.css?v=1.7" />
	<link rel="stylesheet" href="/js/jquery/jquery-ui.min.css"/>

	<script type="text/javascript" src="/js/jquery/jquery-3.1.1.min.js"></script>
	<script type="text/javascript" src="/js/jquery/jquery-ui.min.js"></script>
    <!-- <script type="text/javascript" src="/js/jquery/jquery.easing.1.3.js"></script> -->
	<script type="text/javascript" src="/js/common/common.js?v=1.42"></script>

    <title>충청남도소방본부 소방공무원 인사배치 시스템</title>

<script type="text/javascript">
$(document).ready(function(){
	$(".calendar").datepicker(datepickerOption);
	$(".calendar").attr('size',11);
});
</script>
<style>
.selMenu{
background-color: #0030ae;
}
</style>
</head>

<body id="sub">
	<div id="wrapper">
		<header id="header">
			<div class="accessibility">
				<a href="#container">본문 바로가기</a>
			</div>
			<div class="logo">
				<h1 class="wrap">
					<a href="/"><img src="/images/common/logo.gif" alt="충청남도소방본부 Chungnam Fire serverice Headpuarter " />소방공무원 인사배치 시스템 </a>
				</h1>
			</div>
			<div class="gnb">
				<div class="wrap clearfix">
					<ul class="clearfix">
						<li><a href="/admin/logout">로그아웃</a></li>
					</ul>
				</div>
			</div>
			<nav class="lnb">
				<h2 class="skip">주메뉴</h2>
				<div class="wrap">
					<ul class="depth1_menu clearfix">
						<sec:authorize var="authenticated" access="isAuthenticated()">
						<li class="depth1_list" id="sideUser">
							<a href="<c:url value='/user/my/view.do'/>" class="depth1_anchor">개인인사관리</a>
						</li>
						</sec:authorize>
						<sec:authorize access="hasAnyRole('ROL001','ROL002')">
						<li class="depth1_list" id="sideUserMng">
							<a href="<c:url value='/user/list.do'/>" class="depth1_anchor">관리자인사관리</a>
						</li>
						</sec:authorize>
						<sec:authorize access="hasAnyRole('ROL001')">
						<li class="depth1_list" id="sideStatMng">
							<a href="<c:url value='/stat/selectApply.do'/>" class="depth1_anchor">통계</a>
						</li>
						</sec:authorize>
					</ul>
				</div>
			</nav>
		</header>

		<div id="container">
			<div class="wrap clearfix">
				<div class="side" id="sideUser" style="display:none;">
					<nav class="side_menu">
						<h2 class="skip">좌측메뉴</h2>
						<ul class="depth1_menu">
							<li class="depth1_list"><a href="<c:url value='/user/my/view.do'/>" class="depth1_anchor">개인정보관리</a></li>
							<li class="depth1_list"><a href="<c:url value='/psnnl/my/list.do'/>" class="depth1_anchor">개인인사관리</a></li>
						</ul>
					</nav>
				</div>

				<div class="side" id="sideUserMng" style="display:none;">
					<nav class="side_menu">
						<h2 class="skip">좌측메뉴</h2>
						<ul class="depth1_menu">
							<li class="depth1_list"><a href="<c:url value='/user/list.do'/>" class="depth1_anchor">사용자관리</a></li>
							<sec:authorize access="hasAnyRole('ROL001')">
								<li class="depth1_list"><a href="<c:url value='/orgnz/list.do'/>" class="depth1_anchor">관서관리</a></li>
								<li class="depth1_list"><a href="<c:url value='/batch/list.do'/>" class="depth1_anchor">인사배치관리</a></li>
							</sec:authorize>
						</ul>
					</nav>
				</div>

				<div class="side" id="sideStatMng" style="display:none;">
					<nav class="side_menu">
						<h2 class="skip">좌측메뉴</h2>
						<ul class="depth1_menu">
							<li class="depth1_list"><a href="<c:url value='/stat/selectApply.do'/>" class="depth1_anchor">계급별,관서별,희망지별 현황</a></li>
							<li class="depth1_list"><a href="<c:url value='/stat/selectIn.do'/>" class="depth1_anchor">소방서별 전입 기준 지수</a></li>
							<li class="depth1_list"><a href="<c:url value='/stat/selectInOut.do'/>" class="depth1_anchor">관서별 전출입 인원 현황</a></li>
							<li class="depth1_list"><a href="<c:url value='/stat/selectAvg.do'/>" class="depth1_anchor">계급별 평균 지수점수</a></li>
						</ul>
					</nav>
				</div>

