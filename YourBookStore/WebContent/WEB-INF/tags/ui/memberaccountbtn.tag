<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ attribute name="username" rtexprvalue="true" required="true" %>
<div class="dropdown">
	<button class="btn btn-light dropdown-toggle btn-sm mx-3" type="button"
		id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
		aria-expanded="false">
		<i class="fas fa-user"></i> ${username}
	</button>
	<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
		<a class="dropdown-item" href='<spring:url value="/logout"></spring:url>'><i class="fas fa-sign-out-alt"></i>
			Logout</a>
	</div>
</div>