<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="mytags" prefix="mtags"%>
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
<title>${book.title}</title>
</head>

<body>
	<!-- Navigation Bar -->
	<div class="container-fluid" id="main">
		<ui:adminnavbar />
	</div>
	<div class="container-fluid my-3">
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
				<div class="container">
					<div class="row">
						<div class="offset-lg-1 col-lg-10">
							<h3>${book.title}</h3>
							<hr>
						</div>
					</div>
					<div class="row">
						<div
							class="offset-lg-1 col-lg-3 col-md-3 offset-md-1 col-sm-3 col-2">
							<img
								src='<spring:url value="/thumbs?id=${book.thumbFileName}"></spring:url>'
								alt="" width="240px" height="360px">
						</div>
						<div
							class="offset-lg-1 col-lg-6 col-md-6 offset-md-1 col-sm-6 offset-sm-3 col-6 offset-4">
							<table class="table table-borderless">
								<tbody>
									<tr>
										<th scope="row">Author:</th>
										<td>${book.author}</td>
									</tr>
									<tr>
										<th scope="row">ISBN-10:</th>
										<td>${book.isbn}</td>
									</tr>
									<tr>
										<th scope="row">Year:</th>
										<td>${book.year}</td>
									</tr>
									<tr>
										<th scope="row">Pages:</th>
										<td>${book.pages}</td>
									</tr>
									<tr>
										<th scope="row">Language:</th>
										<td>${book.language}</td>
									</tr>
									<tr>
										<th scope="row">Category:</th>
										<td><a href='<spring:url value="/category/${book.category.title}"></spring:url>' class="btn-link">${book.category.title}</a></td>
									</tr>
									<tr>
										<th scope="row">File Size:</th>
										<td><mtags:filesize size="${book.pdfFileSize}" /></td>
									</tr>
									<tr>
										<th scope="row">File Format:</th>
										<td>PDF</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<div class="row">
						<div class="offset-lg-1 col-lg-10">
							<hr>
							<h5>Book Description:</h5>
							<p class="text-dark">${book.description }</p>
						</div>
					</div>
					<div class="row">
						<div class="offset-lg-1 col-lg-10">
							<hr>
							<c:if
								test="${sessionScope.SESSION_USER_ACCOUNT.authenticated and sessionScope.SESSION_USER_ACCOUNT.userRole eq 'ADMIN'}">
								<a
									href='<spring:url value="/books/pdf?id=${book.pdfFileName}"></spring:url>'
									class="btn btn-primary btn-sm"><i class="fas fa-edit"></i>
									Edit Book Details</a>
								<a
									href='<spring:url value="/books/pdf?id=${book.pdfFileName}"></spring:url>'
									class="btn btn-danger btn-sm"><i class="fas fa-trash"></i>
									Remove Book</a>
							</c:if>
							<a
								href='<spring:url value="/books/pdf?id=${book.pdfFileName}"></spring:url>'
								class="btn btn-info float-right btn-sm"><i
								class="fas fa-download"></i> Download</a>
						</div>
					</div>

					<div class="row">
						<div class="offset-lg-1 col-lg-10">
							<hr>
							<h5>Comments:</h5>
							<c:choose>
								<c:when test="${fn:length(book.comments) gt 0 }">
									<c:forEach var="comment" items="${book.comments}">
										<div class="border-bottom p-3">
											<strong>${comment.postedBy.name}</strong> <span
												class="text-muted text-indent"><fmt:formatDate
													value="${comment.postedOn}" pattern="MMM dd, yyyy hh:mm a" /></span>
											<p class="text-dark">${comment.content}</p>
										</div>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<div class="p-3">
										<h6>There Is No Comments..</h6>
									</div>

								</c:otherwise>
							</c:choose>
							<c:if test="${sessionScope.SESSION_USER_ACCOUNT.authenticated}">
								<div class="border-bottom p-3">
									<strong class="m-2">Add a comment</strong>
									<form
										action='<spring:url value="/book/${book.id}/comments"></spring:url>'
										method="post">
										<div class="form-row">
											<div class="col">
												<div class="form-group">
													<textarea name="comment" cols="30" rows="2"
														class="form-control" placeholder="Write Something..."></textarea>
												</div>
											</div>
										</div>
										<div class="form-row">
											<div class="col">
												<div class="form-group">
													<input type="submit" value="Post" class="btn btn-success">
												</div>
											</div>
										</div>
									</form>
								</div>
							</c:if>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>
	<ui:footer />
</body>

</html>