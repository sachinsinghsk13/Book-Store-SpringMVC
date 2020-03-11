<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib tagdir="/WEB-INF/tags/import" prefix="import"%>
<%@ taglib tagdir="/WEB-INF/tags/ui" prefix="ui"%>
<%@ taglib tagdir="/WEB-INF/tags/forms" prefix="forms"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Book Store</title>

<import:bootstrap4 />
<link rel="shortcut icon" type="image/png"
	href='<spring:url value="/static/images/book.png"></spring:url>' />
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
										<form action="">
											<h5 class="m-2">Administrator Login</h5>
											<!-- Email -->
											<div class="input-group mb-3">
												<div class="input-group-prepend">
													<div class="input-group-text">
														<i class="fas fa-at"></i>
													</div>
												</div>
												<input type="text" class="form-control"
													placeholder="Admin ID">

											</div>
											<div class="input-group mb-3">
												<div class="input-group-prepend">
													<div class="input-group-text">
														<i class="fas fa-lock"></i>
													</div>
												</div>
												<input type="text" class="form-control"
													placeholder="Admin Password">

											</div>
											<div class="form-group">
												<input type="submit" class="btn btn-danger shadow-sm mx-2"
													value="Login as Administrator" placeholder="Email">
											</div>

										</form>
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