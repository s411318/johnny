<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.pet.model.*"%>
<%@ page import="java.util.HashMap"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>




<html lang="">

<head>
<%@ include file="memHead.file"%>
<title>�d��You&amp;Me</title>
<script>
	//�Ӥ��W�ǹw��
	$(function() {

		$("#petImg").change(function() {
			readURL(this);
		});

		function readURL(input) {

			if (input.files && input.files[0]) {
				var reader = new FileReader();
				reader.onload = function(e) {
					$('#petPic').attr('src', e.target.result);
				}
				reader.readAsDataURL(input.files[0]);
			}
		};

	});
</script>

<STYLE>
.title {
	width: 120px; /* �]�w H1 ���˦�*/
}
</STYLE>

</head>

<body>
	<%@ include file="/front_end/frontEndNavBar.file"%>

	<div class="container-fluid">
		<div class="row">

			<div class="col-xs-12 col-sm-2 postion-left-group ">
				<%@ include file="memZoneLSide.file"%>
			</div>

			<%
				
			Integer petOrd=0;
			boolean petOedIsNull=false;
			try{
				petOrd = Integer.parseInt(request.getParameter("petOrd"));
			}
			catch(Exception e){
				petOrd=0;
				petOedIsNull=true;
			}
			if(!petOedIsNull){	
			 Pet pet = list.get(petOrd);
				pageContext.setAttribute("pet", pet);
			}
			%>


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
									<form method="post" action="<%=request.getContextPath() %>/front_end/pet/pet.do" enctype="multipart/form-data">
										<div class="col-md-3 col-lg-3 " align="center">
											<img alt="User Pic" id="petPic"
												src="<%=request.getContextPath() %>/PetImgReader?petNo=${pet.petNo}" height="350px"
												width="250px" class="img-circle img-responsive"> <input
												type="file" name="petImg" id="petImg" placeholder="�s��ۤ�" />
										</div>


										<div class=" col-md-9 col-lg-9 ">
											<table class="table table-user-information">
												<tbody>
													<tr>
														<td class="title">�d���m�W</td>
														<td><input type="text" class="form-control"
															name="petName" id="petName" value="${pet.petName}"
															placeholder="�п�J�ʺ�" required/></td>
													</tr>
													<tr>
														<td class="title">�d������</td>
														<td>${pet.petKind}</td>
													</tr>
													<tr>
														<td class="title">�d���ʧO</td>
														<td>${pet.petGender==o?'�k':'�k'}</td>
													</tr>

													<tr>
														<td class="title">�d���~��</td>
														<td><input type="text" class="form-control"
															name="petSpecies" id="petSpecies"
															value="${pet.petSpecies}" placeholder="�п�J�z���d���~��" required /></td>
													</tr>

													<tr>
														<td class="title">�d���ͤ�</td>
														<td><input type="text" name="petBday" id="petBday"
															value="${pet.petBday}" class="form-control"
															placeholder="�п�J�z���d���ͤ�" required/></td>
													</tr>


													<tr>
														<td class="title">�d������</td>
														<td><textarea class="form-control" id="petIntro"
																name="petIntro" placeholder="�п�J�z���d������" required>${pet.petIntro}</textarea></td>
													</tr>


												</tbody>

											</table>
											<input type="hidden" name="action" value="petUpdate">
											<input type="hidden" name="petNo" value="${pet.petNo}">
											<input type="submit" value="�ק�" class="btn btn-primary">
											
											<!-- �U��|���η| �I�smodal-->
											<button type="button" class="btn btn-danger"
												data-toggle="modal" data-target="#petDisableModal">����</button>
											
											
											
<!-- 		Disable MODAL                                       -->
<!-- 		<div  class="modal fade" id="petDisableModal" tabindex="-1" role="dialog" -->
<!-- 			aria-labelledby="exampleModalLabel" aria-hidden="true"> -->
<!-- 			<div class="modal-dialog" role="document"> -->
<!-- 				<div class="modal-content"> -->
<!-- 					<div class="modal-header"> -->
<!-- 						<h3 class="modal-title" id="exampleModalLabel">�O�_�T�w����?</h3> -->
<!-- 						<button type="button" class="close" data-dismiss="modal" -->
<!-- 							aria-label="Close"> -->
<!-- 							<span aria-hidden="true">&times;</span> -->
<!-- 						</button> -->
<!-- 					</div> -->
<!-- 					<div class="modal-footer"> -->
<!-- 						<span> -->

<%-- 							<form action="<%=request.getContextPath() %>/front_end/pet/pet.do" method="post"> --%>
<!-- 								<input type="hidden" name="action" value="petDisable"> -->
<%-- 								<input type="hidden" name="petNo" value="${pet.petNo}"> --%>
<!-- 								<input type="submit" class="btn btn-primary" value="�O"> -->
<!-- 								<button type="button" class="btn btn-secondary" -->
<!-- 									data-dismiss="modal">�_</button> -->
					
<!-- 							</form> -->
<!-- 						</span> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 		</div> -->
											
											
		
		
				
											
											
											
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
									</form>
									
									
												<!-- Disable MODAL -->
		<div class="modal fade" id="petDisableModal" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h3 class="modal-title" id="exampleModalLabel">�O�_�T�w����?</h3>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
						</button>
					</div>
					<div class="modal-footer">
						<span>

							<Form action="<%=request.getContextPath() %>/front_end/pet/pet.do" method="post">
								<input type="hidden" name="action" value="petDisable">
								<input type="hidden" name="petNo" value="${pet.petNo}">
								<input type="submit" class="btn btn-primary" value="�O">
								<button type="button" class="btn btn-warning" data-dismiss="modal">�_</button>
							</Form>
						</span>
					</div>
				</div>
			</div>
		</div>									
							
									
									
								</div>


							</div>


						</div>
					</div>
				</div>


		
		
				
			</div>
		</div>
		<%@ include file="/front_end/frontEndButtom.file"%>
		
			<script>

		$(function(){

	
		$("#petBday").datetimepicker({
		format: 'Y-m-d',
		 timepicker:false,
		 maxDate: '0',
		});

	 
		});


	</script>

</body>

</html>