<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<style>
#banner{
	height:70%;
}

/* �̲צp�G�n���e���վ㪽���վ�o��CSS */
</style>


<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<title>PetYM</title>
<!-- Bootstrap Core CSS -->
<link href="css/main.css" rel="stylesheet">
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/nav.css" rel="stylesheet">

<!-- Custom CSS -->
<!-- Custom Fonts -->
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<!-- <link href="css/frontend.css" rel="stylesheet" type="text/css"> -->


<style>


#logo{
width:2em;
margin-right:0.25em;
 margin-bottom:0.5em; 
}

.qa{
	margin-top: 5em;
	position:relative;
/* 	right:1em; */
}

#startdating{
	margin-top:1em;
}

 #main{ 
 	position:relative;
 	left:0.3em; 
 	


</style>

</head>




<html>

	<body class="landing">
		<div id="page-wrapper">
		
	
<%@ include file="frontEndNavBar.file"%>

			<!-- Header -->
<!-- 		<nav style="background:#032814;" class="navbar navbar-fixed-top  role="navigation" id="nav"> -->
			
<!-- 				<div class="navbar-header"> -->
<!-- 					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#top-navbar-1"> -->
<!-- 						<span class="sr-only">PetYM</span> -->
<!-- 						<span class="icon-bar"></span> -->
<!-- 						<span class="icon-bar"></span> -->
<!-- 						<span class="icon-bar"></span> -->
<!-- 					</button> -->
<!-- 					<a class="navbar-brand" href="index.html">PetYM</a> -->



<!-- 				</div> -->
<!-- 				Collect the nav links, forms, and other content for toggling -->
<!-- 				<div class="collapse navbar-collapse" id="top-navbar-1"> -->
<!-- 					<ul class="nav navbar-nav navbar-right"> -->


<!--   <!--  ================================== -->         
<%-- 						<li><a href="<%=request.getContextPath() %>/front_end/dateitem/select_page.jsp">���|</a></li> --%>
<%-- 						<li><a href="<%=request.getContextPath() %>/front_end/product/BrowseProduct.jsp">�ӫ�</a></li> --%>
<%-- 						<li><a href="<%=request.getContextPath()%>/front_end/activityFront/activityIndex.jsp">����</a></li> --%>
<%-- 						<li><a href="<%=request.getContextPath()%>/front_end/diary/diary.jsp">�d����x</a></li> --%>
<%-- 						<li><a href="<%=request.getContextPath() %>/front_end/product/Cart.jsp">�ʪ���</a></li> --%>
<%-- 						<li><a href="<%=request.getContextPath()%>/front_end/letter/index_letter.jsp">�����H</a></li> --%>
<%-- 						<li><a href="<%=request.getContextPath()%>/front_end/letter/index_letter.jsp">�|���M��</a></li> --%>
<%-- 						<c:if test="${member==null}"> --%>
<%-- 						<li><a href="<%=request.getContextPath() %>/front_end/member/login.jsp">�n�J</a></li> --%>
<%-- 						</c:if> --%>
<%-- 						<c:if test="${member!=null}"> --%>
<!-- 						<li> -->
<!-- 						<li><a href="#" data-toggle="modal" data-target="#logout">�n�X</a></li> -->
<%-- 						</c:if> --%>

<!-- 						========================= -->
<!-- 				<li id="selectsearch"><form class="navbar-form navbar-search" role="search"> -->
<!--                 <div class="input-group"> -->
        
<!--                     <input type="text" class="form-control"> -->
                
<!--                     <div class="input-group-btn" > -->
<!--                         <button type="button" class="btn btn-search btn-basic"> -->
<!--                             <span class="glyphicon glyphicon-search"></span> -->
<!--                             <span class="label-icon ">Search</span> -->
<!--                         </button> -->
<!--                         <button type="button" class="btn btn-basic dropdown-toggle" data-toggle="dropdown" id="searchgroup"> -->
<!--                             <span class="caret"></span> -->
<!--                         </button> -->
<!--                         <ul  class="dropdown-menu pull-right" role="menu"> -->
<!--                             <li> -->
<!--                                 <a href="#"> -->
<!--                                     <span class="glyphicon glyphicon-user spancontent"></span> -->
<!--                                     <span class="label-icon spancontent">Search By User</span> -->
<!--                                 </a> -->
<!--                             </li> -->
<!--                             <li> -->
<!--                                 <a href="#"> -->
<!--                                 <span class="glyphicon glyphicon-book spancontent"></span> -->
<!--                                 <span class="label-icon spancontent">Search By Organization</span> -->
<!--                                 </a> -->
<!--                             </li> -->
<!--                         </ul> -->
<!--                     </div> -->
<!--                 </div>   -->
<!--             </form>  </li> -->
<!-- 					</ul> -->
<!-- 					</ul> -->
<!-- 				</div> -->
			
<!-- 		</nav> -->

			<!-- Banner -->
				<section id="banner">
					
				</section>

			<!-- Main -->
				<section id="main" class="container" >

					<section class="box special">

						<header class="major">
							<h2><img src="images/logo-sp.png" id="logo">Pet YM 
							<br />
							�J�����쪺�H�M�d��</h2>
							<p>�L�קA���w���O���٬O��<br />
							���W�[�J�ڭ̡A�}�l�A���d�����|</p><br>
							<a href="<%=request.getContextPath() %>/front_end/dateitem/select_page.jsp" id="startdating" class="btn btn-info" >�}�l���|</a>
						
						</header>

						<span class="image featured"><img src="images/pic01.jpg" alt="" /></span>
					</section>

					<section class="box special features">
					
						<div class="features-row">

							<section>
								<a class="icon major fa-paw accent2 fourarea" href="<%=request.getContextPath() %>/front_end/dateitem/select_page.jsp"></a>
								<h3>���|</h3>
								<h3>Dating With Pets</h3>
								<p>�ݮi�A����Ͱ�, �Ԫ�H�P�H���Z�� <BR> �Φa�Ϸj�M�A�X�A�����|, �b�諸�ɶ��諸�a�I, ��짹�������| </p>
							</section>
							<section>
								<a class="icon major fa-shopping-cart accent3 fourarea" href="<%=request.getContextPath() %>/front_end/product/BrowseProduct.jsp"></a>
								<h3>�d���ӫ�</h3>
								<h3>Shopping Area</h3>
								<p>�̿W�S���ӫ~, �̿W�a������, �ɴ����w <BR>
								�C�P��s�[�W�ӫ~, ���A�a���d���j�H�ʶR�M�ݰӫ~, �v�t�쩲</p>
							</section>
						</div>
						<div class="features-row">
							<section>
								<a class="icon major fa-users accent4 fourarea" href="<%=request.getContextPath()%>/front_end/activityFront/activityIndex.jsp"></a>
								<h3>�\�U����</h3>
								<h3>Group Activities</h3>
								<p>��Party��party, ��y��ͪ����x <BR>
								 �ֶi�Ӭݳ̷s�o�_������, �̴Ϊ���������</p>
							</section>
							<section>
								<a class="icon major fa-book accent5 fourarea" href="<%=request.getContextPath()%>/front_end/diary/diary.jsp"></a>
								<h3>�d����x</h3>
								<h3>Pets' Diary</h3>
								<p> �l���d�����P , ���ɥͬ��I�w<br>
								�L�h���A�ڨӤ��ΰѻP, ���ӵ��藍���L</p>
							</section>
						</div>
					</section>

					<div class="row">
						<div class="6u 12u(narrower) qa">

							<section class="box special">
								<span class="image featured"><img src="images/rest.jpg" alt="" /></span>
								<h3>��s�\�U���</h3>
								<h3>Restaurant Owners' Club</h3>
								<p>�A�O�d���\�U�D�H��? �֨ӵo�_�@�Ǭ��ʧa</p>
								<ul class="actions">
									<li><a href="<%=request.getContextPath() %>/front_end/restMember/restMemberLogin.jsp" class="button alt btn btn-info">Learn More</a></li>
								</ul>
							</section>

						</div>
						<div class="6u 12u(narrower) qa">

							<section class="box special">
								<span class="image featured"><img src="images/qa.jpg" alt="" /></span>
								<h3>����QA</h3>
								<h3>Frequent Questions and Answers</h3>
								<p>��������ϥΫ��n, �A�Q���D������b�o�o��ѵ�</p>
								<ul class="actions">
									<li><a href="<%=request.getContextPath() %>/front_end/about_us/faq/faq_user.jsp" class="button alt btn btn-info">Learn More</a></li>
								</ul>
							</section>

						</div>
					</div>

				</section>

			<!-- CTA -->
<!-- 				<section id="cta">

					<h2>Sign up for beta access</h2>
					<p>Blandit varius ut praesent nascetur eu penatibus nisi risus faucibus nunc.</p> -->

<!-- 					<form>
						<div class="row uniform 50%">
							<div class="8u 12u(mobilep)">
								<input type="email" name="email" id="email" placeholder="Email Address" />
							</div>
							<div class="4u 12u(mobilep)">
								<input type="submit" value="Sign Up" class="fit" />
							</div>
						</div>
					</form> -->

<!-- 				</section> -->

<%@ include file="/front_end/frontEndButtom.file"%>

		</div>


			
			<script src="js/bootstrap.js"></script>

	</body>
</html>


