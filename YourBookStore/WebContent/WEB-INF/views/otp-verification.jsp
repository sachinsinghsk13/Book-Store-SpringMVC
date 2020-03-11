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
                                            <h3 class="m-3">Verify OTP</h3>
											<p class="m-3">Enter OTP sent to <strong>sachinsingh@gmail.com</strong></p>
                                            <!-- Email -->
                                            <div class="input-group mb-3">
                                                <div class="input-group-prepend">
                                                    <div class="input-group-text"><i class="fas fa-lock"></i></div>
                                                </div>
                                                <input type="text" class="form-control" placeholder="OTP">

                                            </div>
                                            <div class="form-group">
                                                <input type="submit" class="btn btn-primary shadow-sm mx-2" value="Submit"
                                                    placeholder="Email">
                                                <a href="#" class="btn btn-light">Resend OTP</a>
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