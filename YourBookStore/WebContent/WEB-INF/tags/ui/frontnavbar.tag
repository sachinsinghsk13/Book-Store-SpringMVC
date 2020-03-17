<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<nav class="navbar navbar-expand-lg  navbar-dark bg-transparent">
	<a href='<spring:url value="/"></spring:url>' class="navbar-brand"> <img
		src='<spring:url value="/static/images/book.png"></spring:url>' alt=""
		id="navbar-logo"> <span class="h4 mx-2">Book Store</span>
	</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">
		
		</ul>
		<div class="my-2 my-lg-0 ml-auto">
			<form class="form-inline my-2 my-lg-0">
				<input class="form-control-sm mr-sm-2 rounded-pill border-0 p-2"
					type="search" placeholder="Book Title, Author.."
					aria-label="Search">
				<button class="btn btn-light my-2 btn-sm my-sm-0" type="submit">
					<i class="fas fa-search"></i> Search
				</button>
			</form>
		</div>
	</div>
</nav>