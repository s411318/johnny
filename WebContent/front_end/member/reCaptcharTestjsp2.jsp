<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<html lang="en">



<head>


<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<link href="<%=request.getContextPath() %>/front_end/css/bootstrap.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/front_end/css/nav.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/front_end/css/colorplan.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/front_end/css/frontend.css" rel="stylesheet" type="text/css">
<!-- Custom CSS -->
<link href="<%=request.getContextPath() %>/front_end/css/modern-business.css" rel="stylesheet">
<!-- Custom Fonts -->
<link href="<%=request.getContextPath() %>/front_end/font-awesome/css/font-awesome.css" rel="stylesheet"
	type="text/css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<!--[if lt IE 9]>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
            <![endif]-->
            </script>
<title>Ãdª«You&amp;Me</title>


</head>



<body>
	<!-- Navigation -->
	<%@ include file="/front_end/frontEndNavBar.file"%>


	<div class="container frontend">


		<div class="row main">

			<div class="col-sm-6 col-sm-offset-3">


				<form id="test" action="<%=request.getContextPath()%>/ReCaptChar2"
					method="POST">
					Username: <input type="text" name="user"> <br>
					Password: <input type="password" name="pwd"> <br> <br />
					<div id='recaptcha' class="g-recaptcha"
						data-sitekey="6LeBbC0UAAAAAEd3C3R3zbSpsfxg2A7zZarw2mZT" data-callback="onSubmit"
						data-size="invisible"></div>
					<button id='sub'>submit</button>
				</form>



			</div>
		</div>


	</div>

	<%@ include file="/front_end/frontEndButtom.file"%>






<script>
  function onSubmit(token) {
		document.getElementById('test').submit();;
  }

  function validate(event) {
    
	  event.preventDefault();
	  grecaptcha.execute();
  }

  function onload() {
    var element = document.getElementById('sub');
    element.onclick = validate;
  }
</script>
<script src="https://www.google.com/recaptcha/api.js" async defer></script>



<script>onload();</script>


	<!-- /.container -->
	<!-- jQuery -->
	<script src="<%=request.getContextPath()%>/front_end/js/jquery.js"></script>
	<!-- Bootstrap Core JavaScript -->
	<script
		src="<%=request.getContextPath()%>/front_end/js/bootstrap.min.js"></script>
	<!-- Script to Activate the Carousel -->

</body>

</html>