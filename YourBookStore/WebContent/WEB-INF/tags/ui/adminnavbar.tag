<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags/ui" prefix="ui"%>

<nav class="navbar navbar-expand-lg text-light navbar-dark sticky-top">
	<a href='<spring:url value="/"></spring:url>' class="navbar-brand"> <img src='<spring:url value="/static/images/book.png"></spring:url>' alt=""
		id="navbar-logo"> <span class="h5 mx-2">Book Store</span>
	</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active"><a class="nav-link" href='<spring:url value="/home"></spring:url>'>All IT Books
			</a></li>
			<li class="nav-item"><a class="nav-link" href='<spring:url value="/admin/add-book"></spring:url>'>Categories</a>
			</li>

		</ul>
		<div class="my-2 my-lg-0 ml-auto">
			<form class="form-inline my-2 my-lg-0">
			
				<c:choose>
					<c:when test="${sessionScope.SESSION_USER_ACCOUNT.userRole eq 'ADMIN'}">
						<ui:adminaccountbtn username="${sessionScope.SESSION_USER_ACCOUNT.user.name}"/>					
					</c:when>
					<c:when test="${sessionScope.SESSION_USER_ACCOUNT.userRole eq 'MEMBER'}">
						<ui:memberaccountbtn username="${sessionScope.SESSION_USER_ACCOUNT.user.name}"/>
					</c:when>
					<c:otherwise>
						<ui:userbtn/>
					</c:otherwise>
				</c:choose>
			
				<input class="form-control-sm mr-sm-2 rounded-pill border-0 p-2"
					type="search" placeholder="Book Title, Author.."
					aria-label="Search">
			<!-- 	<button class="btn btn-light my-2 btn-sm my-sm-0" type="submit"><i class="fas fa-search"></i> Search</button> -->

			</form>

		</div>
	</div>
</nav>