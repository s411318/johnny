<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ page import="com.member.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	Member member = (Member) request.getAttribute("member");
	String success=(String)request.getAttribute("success");
	pageContext.setAttribute("success",success);
%>
<html lang="en">

<head>

<%@ include file="memHead.file"%>
<style>
.forget {
	margin-top: 20px;
}
</style>


</head>

<body>

	<!-- Navigation -->
	<%@ include file="/front_end/frontEndNavBar.file"%>



	<div class="container frontend">

		<div class="row">
			<div class="col-sm-6 col-sm-offset-3">

				<div align="center">
					<Img src="<%=request.getContextPath()%>/front_end/images/logo.png"
						height="250px" width="400px" />
				</div>

				<form class="" action="<%=request.getContextPath()%>/front_end/member/member.do"
					method="post">

					<div class="row">
						<div class="form-group">
							<label for="memId" class="cols-sm-2 control-label">��J�z���q�l�H�c</label><span
								id="memEmail"> <c:if test="${not empty errorMsgs}">
									<font color="red">&nbsp;&nbsp;�q�l�H�c���~</font>
								</c:if>

							</span>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-user fa"
										aria-hidden="true"></i></span> <input type="text"
										class="form-control" name="memEmail" id="memEmail"
										placeholder="�п�J�z���q�l�H�c" required />
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="cols-sm-10">
							<input
								class="btn btn-primary btn-lg btn-block login-button forget"
								type="submit" value="�e�X">
						</div>
					</div>
					<font color="red">${success}</font>

					<input type="hidden" name="action" value="forgetPwd">

				</form>

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