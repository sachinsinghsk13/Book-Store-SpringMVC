<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags/import" prefix="import"%>
<%@ taglib tagdir="/WEB-INF/tags/ui" prefix="ui"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<import:bootstrap4 />
<link rel="shortcut icon" type="image/png"
	href='<spring:url value="/static/images/book.png"></spring:url>' />
<link rel="stylesheet"
	href='<spring:url value="/static/css/style.css"></spring:url>'>
<title>Book Store</title>
</head>

<body class="">

	<div class="container-fluid" id="main">
		<ui:adminnavbar />
	</div>
	<!-- Navigation Bar -->
	<div class="container-fluid my-3 min-vh-100">
		<div class="row">
			<div class="col-lg-3 col-md-4 col-sm-5 d-none d-md-block">
				<ul class="list-group">
					<li class="list-group-item text-center">Books Categories</li>
					<c:forEach var="cat" items="${categories}">
						<li class="list-group-item"><a
							href='<spring:url value="/category/${cat.title}"></spring:url>'
							class="text-info">${cat.title}</a></li>

					</c:forEach>

				</ul>
			</div>
			<div class="col-lg-9 col-md-8 col-sm-12">
				<div class="container-fluid">

					<c:forEach var="book" items="${books}">
						<ui:book id="${book.id}" description="${book.description}"
							thumb="${book.thumbFileName}" title="${book.title}"
							author="${book.author}"></ui:book>
					</c:forEach>
					<div class="row p-2">
						<div class="col">
							<ui:pagination currentPage="${pagination.current }"
								totalPages="${pagination.totolPages}" baseurl="/home" />
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
	<ui:footer />
</body>

</html>