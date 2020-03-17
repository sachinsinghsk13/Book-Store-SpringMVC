<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<div class="dropdown">
	<button class="btn btn-light dropdown-toggle btn-sm mx-3" type="button"
		id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
		aria-expanded="false">
		<i class="fas fa-user"></i> Guest
	</button>
	<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
		<a class="dropdown-item" href='<spring:url value="/admin-login"></spring:url>'> <i class="fas fa-user-cog"></i>
			Login as Admin
		</a> <a class="dropdown-item" href='<spring:url value="/login"></spring:url>'><i class="fas fa-sign-in-alt"></i>
			Login</a>
		<div class="dropdown-divider"></div>
		<a class="dropdown-item" href='<spring:url value="/signup"></spring:url>'><i class="fas fa-edit"></i>
			Register</a>
	</div>
</div>
