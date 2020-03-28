<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/import" prefix="import"%>
<%@ taglib tagdir="/WEB-INF/tags/ui" prefix="ui"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Book Store</title>

<import:bootstrap4 />
<link rel="shortcut icon" type="image/png" href='<spring:url value="/static/images/book.png"></spring:url>'/>
<link rel="stylesheet"
	href='<spring:url value="/static/css/style.css"></spring:url>'>
</head>

<body>
	<div class="container-fluid" id="main">
		<ui:frontnavbar />
		<div class="row text-light">
			<div class="col">
				<div class="container">
					<div class="row">
						<div class="col-lg-6 p-3">
							<ui:welcome-text />
						</div>
						<div
							class="col-lg-5 offset-lg-1 p-3 d-flex justify-content-center">
							<div class="container">
								<div class="row">
									<div class="col">
										<form action='<spring:url value="/login"></spring:url>' method="POST">
											<h3 class="m-3">Login</h3>
											<c:if test="${loginError ne null }">
												<h5>${loginError}</h5>
											</c:if>
											<!-- Email -->
											<div class="input-group mb-3">
												<div class="input-group-prepend">
													<div class="input-group-text">
														<i class="fas fa-envelope"></i>
													</div>
												</div>
												<input type="text" class="form-control" placeholder="Email" name="email">

											</div>
											<!-- PASSWORD -->
											<div class="input-group mb-3">
												<div class="input-group-prepend">
													<div class="input-group-text">
														<i class="fas fa-lock"></i>
													</div>
												</div>
												<input type="text" class="form-control"
													placeholder="Password" name="password">
											</div>

											<div class="form-group">
												<input type="submit" class="btn btn-primary shadow-sm"
													value="Login" placeholder="Email">
											</div>
											<div class="form-group">
												<a href="#" class="btn btn-link text-light">Forget Your
													Password?</a>
											</div>
											<hr>
											<p>Not have an account?</p>
											<div class="form-group">
												<a href='<spring:url value="/signup"></spring:url>' class="btn btn-success">Create Account</a>
											</div>
										</form>
									</div>
								</div>
								<div class="row my-3">
									<div class="col">
										<a href='<spring:url value="/"></spring:url>' class="btn btn-danger btn-block">Visit Book
											Store </a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Add Cards -->
	<ui:cards />

	<!-- Add Footer -->
	<ui:footer />
</body>

</html>