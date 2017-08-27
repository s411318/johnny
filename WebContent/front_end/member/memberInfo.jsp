<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.HashMap"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="">

<head>

<%@ include file="memHead.file"%>
<style>
</style>


</head>

<body>
	<%@ include file="/front_end/frontEndNavBar.file"%>
	<div class="container-fluid">
		<div class="row">

			<div class="col-xs-12 col-sm-2 postion-left-group ">
				<%@ include file="memZoneLSide.file"%>
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
								<div class="row">
									<div class="col-md-3 col-lg-3 " align="center">
										<img alt="User Pic" id="memImg" src="<%=request.getContextPath()%>/DBGifReader" height="350px" width="250px"
											class="img-circle img-responsive">
<!-- 										<video  autoplay loop muted width="150px" height="200px"> -->
<%-- 											<source src="<%=request.getContextPath()%>/DBGifReader" type="video/mp4"> --%>
<!-- 										</video> -->
									</div>


									<div class=" col-md-9 col-lg-9 ">
										<table class="table table-user-information">
											<tbody>
												<tr>
													<td class="title">�ʺ�</td>
													<td>${member.memSname}</td>
												</tr>
												<tr>
													<td class="title">�m�W</td>
													<td>${member.memName}</td>
												</tr>
												<tr>
													<td class="title">�ͤ�</td>
													<td>${member.memBday}</td>
												</tr>
												<tr>
													<td class="title">���</td>
													<td>${member.memPhone}</td>
												</tr>
												<tr>
													<td class="title">�ʧO</td>
													<%
														String memGender = String.valueOf(member.getMemGender());
														HashMap mGender = (HashMap) application.getAttribute("mGender");
													%>
													<td><%=mGender.get(memGender)%></td>
												</tr>
												<tr>

													<td class="title">�P��</td>
													<%
														String memRelation = String.valueOf(member.getMemRelation());
														HashMap mRelation = (HashMap) application.getAttribute("mRelation");
													%>
													<td><%=mRelation.get(memRelation)%></td>
												</tr>
												<tr>
													<td class="title">����</td>
													<td>${member.memFollowed}�H</td>
												</tr>
												<tr>
													<td class="title">�I��</td>
													<td>${member.memPoint}�I</td>
												</tr>
												<tr>
													<td class="title">Email</td>
													<td>${member.memEmail}</td>
												</tr>
												<td class="title">�a�}</td>
												<td>${member.memAddress}</td>
												<tr>
													<td class="title">�����</td>
													<td>${member.memSelfintro}</td>
												</tr>


											</tbody>
										</table>

										<a href="memberInfoUpdate.jsp" class="btn btn-primary">�s��ӤH��T</a>
									</div>
								</div>
							</div>

						</div>
					</div>
				</div>

			</div>
			
		</div>
		<%@ include file="/front_end/frontEndButtomFixed.file"%>
		<script src="https://code.jquery.com/jquery.js"></script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script>
			$(document).ready(function(e) {
				$('.search-panel .dropdown-menu').find('a').click(function(e) {
					e.preventDefault();
					var param = $(this).attr("href").replace("#", "");
					var concept = $(this).text();
					$('.search-panel span#search_concept').text(concept);
					$('.input-group #search_param').val(param);
				});
			});
		</script>
</body>

</html>