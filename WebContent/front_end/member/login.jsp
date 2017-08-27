<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ page import="com.member.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	Member member = (Member) request.getAttribute("member");
%>
<html lang="en">

<head>

<%@ include file="memHead.file"%>

</head>

<body>

	<!-- Navigation -->
	<%@ include file="/front_end/frontEndNavBar.file"%>



	<div class="container frontend">

		<div class="row">
			<div class="col-sm-6 col-sm-offset-3">

				<div align="center">
					<Img src="<%=request.getContextPath() %>/front_end/images/logo.png" height="250px" width="400px" />
				</div>

				<form class="" 	action="<%=request.getContextPath() %>/front_end/member/member.do"  method="post">


					<div class="form-group">
						<label for="memId" class="cols-sm-2 control-label">�b��</label><span
							id="memIdShow"> <c:if test="${not empty errorMsgs}">
								<font color="red">&nbsp;&nbsp;�b���K�X���~</font>
							</c:if>

						</span>
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-user fa"
									aria-hidden="true"></i></span> <input type="text" class="form-control"
									name="memId" id="memId"
									value="<%=(member == null) ? "" : member.getMemId()%>"
									placeholder="�п�J�b��" required />
							</div>
						</div>
					</div>


					<div class="form-group pwd">
						<label for="memId" class="cols-sm-2 control-label">�K�X</label><span
							id="memIdShow"></span>
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-user fa"
									aria-hidden="true"></i></span> <input type="password"
									class="form-control" name="memPwd" id="memPwd"
									value="<%=(member == null) ? "" : member.getMemPwd()%>"
									placeholder="�п�J�K�X" required />
							</div>
						</div>
					</div>


					<input class="btn btn-primary btn-lg btn-block login-button login"
						type="submit" value="�n��">
					<div>
						<a href="<%=request.getContextPath() %>/front_end/member/forgetPwd.jsp" class="btn btn-link">�ѰO�K�X</a> <a href="<%=request.getContextPath() %>/front_end/member/register.jsp"
							class="btn btn-link">���U</a>
					</div>
					<input type="hidden" name="action" value="login">
				</form>
				
				<button id="loginbtn1" class="btn btn-xs btn-default">���d</button>
				<button id="loginbtn2" class="btn btn-xs btn-default">�S�d</button>
				<button id="loginbtn3" class="btn btn-xs btn-default">�ĤT</button>

			</div>
		</div>

	</div>
		<%@ include file="/front_end/frontEndButtomFixed.file"%>
	<!-- /.container -->
	<!-- jQuery -->
	<script src="<%=request.getContextPath()%>/front_end/js/jquery.js"></script>
	<!-- Bootstrap Core JavaScript -->
	<script
		src="<%=request.getContextPath()%>/front_end/js/bootstrap.min.js"></script>
	<!-- Script to Activate the Carousel -->


</body>

</html>

<script>
$(document).ready(function(){
	$("#loginbtn1").click(function(e){
		e.preventDefault();
		$("#memId").val("petym0");
		$("#memPwd").val("p123456");
	});
	
	$("#loginbtn2").click(function(e){
		e.preventDefault();
		$("#memId").val("petym1");
		$("#memPwd").val("p123456");
	});
	
	$("#loginbtn3").click(function(e){
		e.preventDefault();
		$("#memId").val("petym20");
		$("#memPwd").val("p123456");
	});
});
</script>