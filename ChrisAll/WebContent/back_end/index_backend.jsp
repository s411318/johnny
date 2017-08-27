<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>Title Page</title>
<link href="<%=request.getContextPath()%>/back_end/css/bootstrap.css"
	rel="stylesheet">
<link href="<%=request.getContextPath()%>/back_end/css/nav.css"
	rel="stylesheet">
<link href="<%=request.getContextPath()%>/back_end/css/colorplan.css"
	rel="stylesheet">
<!-- Custom CSS -->
<link
	href="<%=request.getContextPath()%>/back_end/css/modern-business.css"
	rel="stylesheet">
<!-- Custom Fonts -->
<link
	href="<%=request.getContextPath()%>/back_end/font-awesome/css/font-awesome.css"
	rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/back_end/css/backend.css"
	rel="stylesheet">
<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
</head>

<body>
	<nav id="emerald-nav" class="navbar navbar-light navbar-fixed-top"
		role="navigation">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand logo" href="index.html">�d��You&amp;Me</a>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
				</ul>
				<div class="nav collapse navbar-collapse navbar-right" id="login">
					<ul class="nav navbar-nav">
						<c:if test="${emp!=null}">
							<li><a href="<%=request.getContextPath() %>/back_end/emp/EmpLogout.do">�޲z���n�X</a></li>
						</c:if>
						<c:if test="${emp==null}">
							<li><a href="<%=request.getContextPath() %>/back_end/emp/empLogin.jsp">�޲z���J</a></li>
						</c:if>
					</ul>
				</div>
				<!-- /.navbar-collapse -->
				<!-- /.container -->
	</nav>


	<div class="container-fluid">
		<div class="row">




			<div class="col-xs-12 col-sm-2 postion-left-group-b">
				<div id="menu">
					<div class="panel list-group list-color">

						<a href="#" class="list-group-item">��ݭ���</a> <a href="#"
							class="list-group-item" data-toggle="collapse" data-target="#sm1"
							data-parent="#menu">�e�ݬݪO�޲z <span
							class="glyphicon glyphicon-triangle-bottom pull-right"></span></a>
						<div id="sm1" class="sublinks collapse">
							<a href="#" class="list-group-item small"> �ۿO���޲z</a> <a href="#"
								class="list-group-item small"> �̷s�����޲z</a>
						</div>


						<a href="#" class="list-group-item" data-toggle="collapse"
							data-target="#sm2" data-parent="#menu">�|���b���޲z <span
							class="glyphicon glyphicon-triangle-bottom pull-right"></span></a>
						<div id="sm2" class="sublinks collapse">
							<a href="#" class="list-group-item small"> �@��|���޲z</a> <a href="#"
								class="list-group-item small"> �\�U�|���޲z</a>
						</div>


						<a href="#" class="list-group-item" data-toggle="collapse"
							data-target="#sm3" data-parent="#menu">�ӫ��޲z <span
							class="glyphicon glyphicon-triangle-bottom pull-right"></span></a>
						<div id="sm3" class="sublinks collapse">
							<a href="<%=request.getContextPath()%>/back_end/product/productManage.jsp" class="list-group-item small"> �ӫ~�޲z</a> 
							<a href="<%=request.getContextPath()%>/back_end/order/OrderManage.jsp" class="list-group-item small"> �q��޲z</a>
						</div>

						<a href="#" class="list-group-item" data-toggle="collapse"
							data-target="#sm4" data-parent="#menu">���|�ӶD�޲z <span
							class="glyphicon glyphicon-triangle-bottom pull-right"></span></a>
						<div id="sm4" class="sublinks collapse">
							<a href="#" class="list-group-item small"> ���|�ӫ~���|�޲z</a> <a
								href="#" class="list-group-item small"> ���|�ӫ~�ӶD�޲z</a>
						</div>



						<a href="#" class="list-group-item" data-toggle="collapse"
							data-target="#sm5" data-parent="#menu">�v���޲z <span
							class="glyphicon glyphicon-triangle-bottom pull-right"></span></a>
						<div id="sm5" class="sublinks collapse">
							<a href="#" class="list-group-item small"> �޲z���v���޲z</a> <a
								href="<%=request.getContextPath() %>/back_end/emp/empRegister.jsp" class="list-group-item small"> ���U�s�޲z��</a>
						</div>



						<a href="#" class="list-group-item">���ʼf��</a> <a href="#"
							class="list-group-item">�\�U�f��</a> <a href="#"
							class="list-group-item">�ޯ����H�޲z</a>






					</div>
				</div>
			</div>





			<div class="col-xs-12 col-sm-8">


				<h5 class="page-header text-right">�ثe��m:��ݭ���</h5>


				<div
					class="panel panel-default col-sm-offset-3 col-sm-6 text-center">
					<div class="panel-heading">
						<h3 class="panel-title">�ݳB�z�ƶ�</h3>
					</div>

					<ul class="list-group">
						<li class="list-group-item">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5
							���ݼf�֭q�� <span class="label label-info pull-right">5</span>
						</li>
						<li class="list-group-item">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1
							���ݼf���\�U <span class="label label-info pull-right">1</span>
						</li>
						<li class="list-group-item">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2
							���ݳB�z�ӶD <span class="label label-info pull-right">2</span>
						</li>
						<li class="list-group-item">0 ���ݳB�z���|</li>
						<li class="list-group-item">0 ���ݼf�֬���</li>

					</ul>







				</div>




			</div>

			<script src="https://code.jquery.com/jquery.js"></script>
			<script
				src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>

</html>