<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<html lang="en">

<%@ page import="net.tanesha.recaptcha.ReCaptcha"%>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory"%>

<head>


<%@ include file="memHead.file"%>
<%@ include file="memHead.file"%>
<title>Ãdª«You&amp;humphreyLiang chrisyen8341 Me</title>
</head>

</script>
<script src="https://www.google.com/recaptcha/api.js" async defer></script>
<body>
	<!-- Navigation -->
	<%@ include file="/front_end/frontEndNavBar.file"%>


	<div class="container frontend">


		<div class="row main">

			<div class="col-sm-6 col-sm-offset-3">

				<script src="https://www.google.com/recaptcha/api.js" async defer></script>

				<form action="<%=request.getContextPath()%>/ReCaptChar" method="POST">
					Username: <input type="text" name="user"> <br>
					Password: <input type="password" name="pwd"> <br>
					<div class="g-recaptcha"
						data-sitekey="6Lcybi0UAAAAANlN7w-Z9PnA0K-oUb-d_vcB7eBA">
					</div>
					<br/> <input type="submit" value="Submit">
				</form>



			</div>
		</div>


	</div>

	<%@ include file="/front_end/frontEndButtom.file"%>










	<Script>
		onload();
	</script>

	<!-- /.container -->
	<!-- jQuery -->
	<script src="<%=request.getContextPath()%>/front_end/js/jquery.js"></script>
	<!-- Bootstrap Core JavaScript -->
	<script
		src="<%=request.getContextPath()%>/front_end/js/bootstrap.min.js"></script>
	<!-- Script to Activate the Carousel -->

</body>

</html>