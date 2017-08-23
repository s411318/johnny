<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.HashMap"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="">

<head>
<%@ include file="memHead.file"%>
<script>
	//�Ӥ��W�ǹw��
	$(function() {

		$("#memImg").change(function() {
			readURL(this);
		});

		function readURL(input) {

			if (input.files && input.files[0]) {
				var reader = new FileReader();
				reader.onload = function(e) {
					$('#memPic').attr('src', e.target.result);
				}
				reader.readAsDataURL(input.files[0]);
			}
		}
		;

	});
</script>


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

					<h5 class="page-header text-right">�ثe��m:�|���ӤH��T</h5>


					<div class="row">

						<div class="panel panel-info">
							<div class="panel-heading">
								<h3 class="panel-title">${member.memId}</h3>
							</div>
							<div class="panel-body">
								<form method="post" action="<%=request.getContextPath() %>/Update" enctype="multipart/form-data">
									<div class="row">
										<div class="col-md-3 col-lg-3 " align="center">
											<img alt="User Pic" id="memPic" src="<%=request.getContextPath() %>/DBGifReader"
												height="350px" width="250px"
												class="img-circle img-responsive"> <input type="file"
												name="memImg" id="memImg" placeholder="�s��ۤ�" />
										</div>


										<div class=" col-md-9 col-lg-9 ">
											<table class="table table-user-information">
												<tbody>
													<tr>
														<td class="title">�ʺ�</td>
														<td><input type="text" class="form-control"
															name="memSname" id="memSname" value="${member.memSname}"
															placeholder="�п�J�ʺ�" /></td>
													</tr>
													<tr>
														<td class="title">�m�W</td>
														<td><input type="text" class="form-control"
															name="memName" id="memName" value="${member.memName}"
															placeholder="�п�J�z���m�W" /></td>
													</tr>
													<tr>
														<td class="title">�ͤ�</td>
														<td><input type="date" name="memBday"
															min="1910-01-01" max='2000-13-13' id="memBday"
															value="${member.memBday}" class="form-control"
															placeholder="Confirm your Password" /></td>
													</tr>
													<tr>
														<td class="title">���</td>
														<td><input type="text" class="form-control"
															name="memPhone" id="memPhone" value="${member.memPhone}"
															placeholder="�п�J�z�����" /></td>
													</tr>
													<tr>
														<td class="title">�ʧO</td>
														<td><input type="radio" name="memGender"
															${member.memGender==0?'checked':''} value="0">�k <input
															type="radio" name="memGender"
															${member.memGender==1?'checked':''} value="1">�k <input
															type="radio" name="memGender"
															${member.memGender==2?'checked':''} value="2">���@�z�|
														</td>
													</tr>
													<tr>

														<td class="title">�P��</td>

														<td><input type="radio" name="memRelation"
															${member.memRelation==0?'checked':''} value="0">�樭
															<input type="radio" name="memRelation"
															${member.memRelation==1?'checked':''} value="1">í�w�橹
															<input type="radio" name="memRelation"
															${member.memRelation==2?'checked':''} value="2">�����}
														</td>
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
														<td><input type="text" class="form-control"
															name="memEmail" id="memEmail" value="${member.memEmail}"
															placeholder="�п�J�z���q�l�H�c" /></td>
													</tr>
													<td>�a�}</td>
													<td><textarea class="form-control" id="memAddress"
															name="memAddress" placeholder="�п�J�z���a�}">${member.memAddress}</textarea></td>
													<tr>
														<td class="title">�����</td>
														<td><textarea class="form-control" id="memAddress"
																name="memSelfintro" placeholder="�п�J�z���a�}">${member.memSelfintro}</textarea></td>
													</tr>


												</tbody>
											</table>
											<input type="hidden" name="action" value="memUpdate"> 
											<input type="submit" value="�ק�" class="btn btn-primary">
											<c:if test="${not empty errorMsgs}">
												<font color="red">
													<ul>
														<c:forEach var="message" items="${errorMsgs}">
															<li>${message}</li>
														</c:forEach>
													</ul>
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