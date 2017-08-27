<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<html lang="en">

<head>
<%@ include file="memHead.file"%>
<title>�d��You&amp;Me</title>

<script>
	$(function() {

		//�Ӥ��W�ǹw��
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
		}

		$("#autoAddPet").click(function() {
			$("#petName").val("�樭��");
		});

	});
</script>


</head>

<body>
	<!-- Navigation -->
	<%@ include file="memNavBar.file"%>
	<!-- Header Carousel -->


	<div class="container frontend">

		<div class="row main">

			<div class="col-sm-6 col-sm-offset-3">

				<form class="" action="<%=request.getContextPath() %>/petUpdate" method="post"
					enctype="multipart/form-data">



					<div id="petDiv">

						<div align="center">
							<Img src="<%=request.getContextPath() %>/front_end/images/logo.png" height="250px" width="100%" />
						</div>


						<div class="form-group">
							<label for="petName" class="cols-sm-2 control-label">�d���m�W</label><span
								id="petNameShow"></span>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i
										class="fa fa-lock fa-lg" aria-hidden="true"></i></span> <input
										type="text" class="form-control" name="petName" id="petName"
										placeholder="�п�J�z���d���m�W" />
								</div>
							</div>
						</div>


						<div class="row">
							<div class="col-sm-6 form-group">
								<label for="petKind" class="control-label">�d������</label><br>
								<label class="radio-inline"><input type="radio"
									name="petKind" id="petOrNot_0" value="��" checked="true">��</label>
								<label class="radio-inline"><input type="radio"
									name="petKind" id="petOrNot_1" value="��">��</label> <label
									class="radio-inline"><input type="radio" name="petKind"
									id="petOrNot_2" value="��L">��L</label>
							</div>


							<div class="col-sm-6 form-group">
								<label for="petGender" class="control-label">�d���ʧO</label><br>
								<label class="radio-inline"><input type="radio"
									name="petGender" value="0" checked="true">�k</label> <label
									class="radio-inline"><input type="radio"
									name="petGender" value="1">�k</label>
							</div>

						</div>


						<div class="form-group">
							<label for="petImg" class="control-label">�d���Ӥ�</label><span
								id="petImgShow"></span><br> <input type="file"
								name="petImg" id="petImg" /> <img
								src="https://api.fnkr.net/testimg/350x200/00CED1/FFF/?text=img+placeholder"
								height="200px" width="150px" id="petPic" style="margin-top: 5px"><br>

						</div>

					</div>

					<input type="hidden" name="action" value="petRegister"> <input
						class="btn btn-primary btn-lg btn-block login-button"
						type="submit" value="���U">

					<c:if test="${not empty errorMsgs}">
						<font color="red">
							<ul>
								<c:forEach var="message" items="${errorMsgs}">
									<li>${message}</li>
								</c:forEach>
							</ul>
						</font>
					</c:if>


				</form>

				<button type="button" class="btn btn-info" id="autoAddPet"
					style="margin-top: 10px">�s�W�d��</button>

			</div>
		</div>


		<!-- MODAL -->
		<div class="modal fade" id="logout" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h3 class="modal-title" id="exampleModalLabel">�O�_�T�w�n�X?</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-footer">
						<span>

							<form action="<%=request.getContextPath() %>/Update" method="post">
								<input type="hidden" name="action" value="logout">
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">�_</button>
								<input type="submit" class="btn btn-primary" value="�O">
							</form>
						</span>
					</div>
				</div>
			</div>
		</div>

		<!-- Footer -->
		<footer>
			<div class="row">
				<div class="col-sm-12">
					<div class="col-sm-3">
						<p>Copyright �d��You&amp;Me 2017</p>
					</div>
					<div class="col-sm-3">
						<p>����ڭ�</p>
					</div>
				</div>
			</div>
		</footer>
	</div>
	
	<!-- /.container -->
	<!-- jQuery -->
	<script src="https://code.jquery.com/jquery.js"></script>
	<!-- Bootstrap Core JavaScript -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<!-- Script to Activate the Carousel -->

</body>

</html>