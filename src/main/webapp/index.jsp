<%@ page contentType="text/html; charset=utf-8" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<html>
<head>
	<title>충청남도소방본부</title>

	<sec:authorize access="isAnonymous()">
		<meta http-equiv="refresh" content="0; url=/login.do" />
	</sec:authorize>

	<sec:authorize var="authenticated" access="isAuthenticated()">
		<sec:authorize access="hasRole('ROL003')">
			<meta http-equiv="refresh" content="0; url=/user/my/view.do" />
		</sec:authorize>

		<sec:authorize access="hasAnyRole('ROL001','ROL002')">
			<meta http-equiv="refresh" content="0; url=/user/list.do" />
		</sec:authorize>
	</sec:authorize>
</head>
<body>
</body>
</html>