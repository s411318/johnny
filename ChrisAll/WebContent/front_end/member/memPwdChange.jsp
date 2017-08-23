<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.HashMap"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="">

<head>
<%@ include file="memHead.file"%>
<title>Title Page</title>


<script>

$(function(){
	
	$("#memNewPwd").blur(function () {
		var regex = new RegExp('.*[a-zA-Z]+.*');
		if($("#memNewPwd").val().length<6){
			$("#memPwdShow").html("&nbsp;&nbsp;&nbsp;&nbsp;�K�X���׻ݤj��6").css('color','red');
		}
		else if(!$("#memNewPwd").val().match(regex)) {
			$("#memNewPwdShow").html("&nbsp;&nbsp;&nbsp;&nbsp;�K�X���]�t�^��r").css('color','red');
		} 
		else{
			$("#memNewPwdShow").html("").css('color','green');
		}
	});

	//�T�{�K�X����
	$("#conPwd").blur(function() {
		if ($("#memNewPwd").val().trim() == $("#conPwd").val().trim()) {
			$("#conPwdShow").html("&nbsp;&nbsp;&nbsp;&nbsp;�K�X�۲�").css('color','green');
		} else {
			$("#conPwdShow").html("&nbsp;&nbsp;&nbsp;&nbsp;�K�X���۲�").css('color','red');
		}
		;
	});
	
	
});
</script>


<STYLE>
.title {
	width: 150px; /* �]�w H1 ���˦�*/
}
</STYLE>

</head>

<body>
	<%@ include file="memNavBar.file" %>
	<div class="container-fluid">
		<div class="row">

			<div class="col-xs-12 col-sm-2 postion-left-group ">
				<%@ include file="memZoneLSide.file" %>
			</div>

			<div class="col-xs-12 col-sm-8 ">
				<div class="row">

					<h5 class="page-header text-right">�ثe��m:�|���M��</h5>


					<div class="row">

						<div class="panel panel-info">
							<div class="panel-heading">
								<h3 class="panel-title">${member.memId}</h3>
							</div>
							<div class="panel-body">
								<form method="post" action="<%=request.getContextPath() %>/Update">
									<div class="row">
										<div class="col-md-3 col-lg-3 " align="center">
											<img alt="User Pic" id="memPic" src="<%=request.getContextPath() %>/DBGifReader"
												height="350px" width="250px"
												class="img-circle img-responsive"> 
										</div>


										<div class=" col-md-9 col-lg-9 ">
											<table class="table table-user-information">
												<tbody>
													<tr>
														<td class="title">�ثe���K�X</td>
														<td><input type="password" class="form-control"
															name="memPwd" id="memPwd"
															placeholder="�п�J�ثe���K�X" value='${falsePwd["memPwd"]}'/></td>
													</tr>
													<tr>
														<td class="title">�s�K�X</td>
														<td><input type="password" class="form-control"
															name="memNewPwd" id="memNewPwd"
															placeholder="�п�J�z���s�K�X" value='${falsePwd["memNewPwd"]}'/><span id="memNewPwdShow"></span></td>
													</tr>
													<tr>
														<td class="title">���s��J�s�K�X</td>
														<td><input type="password" name="conPwd"
															id="conPwd"
														    class="form-control"
															placeholder="�нT�{�z���K�X" value='${falsePwd["memNewPwd"]}'/><span id="conPwdShow"></span></td>
													</tr>
	

												</tbody>
											</table>
											<input type="hidden" name="action" value="pwdChange">
											<input type="submit" value="�ק�K�X" class="btn btn-primary">
											<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>�Эץ��H�U���~:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<c:if test="${success!=null}">
	<font color='green'>�K�X�ܧ󦨥\
	</font>
</c:if>


										</div>
									</div>
								</form>
							</div>

						</div>
					</div>
				</div>


<%@ include file="memButtom.file" %>
			</div>
		</div>
		<script src="https://code.jquery.com/jquery.js"></script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>

</html>