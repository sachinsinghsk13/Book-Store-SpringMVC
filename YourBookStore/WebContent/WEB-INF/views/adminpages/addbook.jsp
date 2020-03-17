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

<body>
	<!-- Navigation Bar -->
	<div class="container-fluid" id="main">
		<ui:adminnavbar />
	</div>
	<!-- Add Book Form -->
	<div class="container my-4 shadow bg-light">
		<div class="row">
			<div class="col-lg-8 offset-lg-2 p-3 col-md-10 offset-md-1">
				<form action='<spring:url value="/admin/add-book"></spring:url>' method="POST" enctype="multipart/form-data">
					<p class="text-center h2 py-3">New Book</p>
					<div class="form-row">
						<div class="col-sm">
							<div class="form-group">
								<label for="">Title:</label> <input type="text"
									class="form-control" placeholder="Title" name="title">
							</div>
						</div>
						<div class="col-sm">
							<div class="form-group">
								<label for="">Author:</label> <input type="text"
									class="form-control" placeholder="Author" name="author">
							</div>
						</div>
					</div>
					<div class="form-row">
						<div class="col-sm">
							<div class="form-group">
								<label for="">Category:</label> <select name="categoryid" id=""
									class="form-control">
									<c:forEach var="cat" items="${categories}">
										<option value="${cat.id}">${cat.title}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="col">
							<div class="form-group">
								<label for="">Publish Year:</label> <select name="year" id=""
									class="form-control">
									<c:forEach begin="1995" end="2020" var="y">
										<option value="${y}">${y}</option>
									
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="col">
							<div class="form-group">
								<label for="">Pages:</label> <input type="text"
									class="form-control" placeholder="Pages" name="pages">
							</div>
						</div>
					</div>
					<div class="form-row">
						<div class="col">
							<div class="form-group">
								<label for="">Language:</label> <select name="lang" id=""
									class="form-control">
									<option value="HINDI">Hindi</option>
									<option value="ENGLISH">English</option>
								</select>
							</div>
						</div>
						<div class="col">
							<div class="form-group">
								<label for="">ISBN:</label> <input type="text"
									class="form-control" placeholder="ISBN" name="isbn">
							</div>
						</div>
					</div>
					<div class="form-row">
						<div class="col">
							<div class="form-group">
								<div class="custom-file">
									<input type="file" class="custom-file-input" id="customFile" name="thumb">
									<label class="custom-file-label" for="customFile">Book
										Thumbnail</label>
								</div>
							</div>
						</div>
						<div class="col">
							<div class="form-group">
								<div class="custom-file">
									<input type="file" class="custom-file-input" id="customFile" name="pdf">
									<label class="custom-file-label" for="customFile">PDF
										File</label>
								</div>
							</div>
						</div>
					</div>
					<div class="form-row">
						<div class="col">
							<div class="form-group">
								<label for="">Description:</label>
								<textarea name="description" id="" cols="30" rows="4" class="form-control"
									style="resize: none;" placeholder="Description"></textarea>
							</div>
						</div>
					</div>
					<div class="form-row">
						<div class="col">
							<div class="form-group">
								<input type="submit" class="btn btn-primary mx-2"
									value="Add Book"> <a href="" class="btn btn-secondary">Cancel</a>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>

</html>