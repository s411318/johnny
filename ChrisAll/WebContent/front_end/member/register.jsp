<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<html lang="en">

<head>


<%@ include file="memHead.file"%>
<%@ include file="registerTest.file" %>
<title>�d��You&amp;Me</title>

</head>

<body>
	<!-- Navigation -->
	<%@ include file="memNavBar.file"%>


	<div class="container frontend">


						<div class="row main">

					<div class="col-sm-6 col-sm-offset-3">

						<form class="" action="<%=request.getContextPath() %>/Update" method="post" enctype="multipart/form-data">

							<div class="row">

								<div class="col-sm-6 form-group">
									<label for="memId" class="cols-sm-2 control-label">�b��</label><span id="memIdShow"></span>
									<div class="cols-sm-10">
										<div class="input-group">
											<span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
											<input type="text" class="form-control" name="memId" id="memId"  placeholder="�п�J�b��"/>
										</div>
									</div>
								</div>

								<div class="col-sm-6 form-group">
									<label for="memSname" class="cols-sm-2 control-label">�ʺ�</label><span id="memSnameShow"></span>
									<div class="cols-sm-10">
										<div class="input-group">
											<span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
											<input type="text" class="form-control" name="memSname" id="memSname"  placeholder="�п�J�ʺ�"/>
										</div>
									</div>
								</div>
							</div>

							
							<div class="row">
								<div class="col-sm-6 form-group">
									<label for="memPwd" class="cols-sm-2 control-label">�K�X</label><span id="memPwdShow"></span>
									<div class="cols-sm-10">
										<div class="input-group">
											<span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>
											<input type="password" class="form-control" name="memPwd" id="memPwd"  placeholder="�ݥ]�t�^��r�B���פj��6"/>
										</div>
									</div>
								</div>

								<div class="col-sm-6 form-group">
									<label for="conpwd" class="cols-sm-2 control-label">�T�{�K�X</label><span id="conPwdShow"></span>
									<div class="cols-sm-10">
										<div class="input-group">
											<span class="input-group-addon"><i class="fa fa-users fa" aria-hidden="true"></i></span>
											<input type="password" class="form-control" name="conpwd" id="conPwd"  placeholder="�ЦA����J�K�X"/>
										</div>
									</div>
								</div>
							</div>


							<div class="row">
								<div class="col-sm-6 form-group">
									<label for="memName" class="cols-sm-2 control-label">�m�W</label><span id="memNameShow"></span>
									<div class="cols-sm-10">
										<div class="input-group">
											<span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
											<input type="text" class="form-control" name="memName" id="memName"  placeholder="�п�J�z���m�W"/>
										</div>
									</div>
								</div>



								<div class="col-sm-6 form-group">
									<label for="memIdNo" class="cols-sm-2 control-label">�����Ҧr��</label><span id="memIdNoShow"></span>
									<div class="cols-sm-10">
										<div class="input-group">
											<span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
											<input type="text" class="form-control" name="memIdNo" id="memIdNo"  placeholder="�п�J�����Ҧr��"/>
										</div>
									</div>
								</div>
							</div>




							<div class="row">
								<div class="col-sm-6 form-group">
									<label for="memBday" class="cols-sm-2 control-label">�ͤ�</label><span id="memBdayShow"></span>
									<div class="cols-sm-10">
										<div class="input-group">
											<span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
											<input type="date" name="memBday" min="1910-01-01"  max='2000-13-13' id="memBday" class="form-control" placeholder="Confirm your Password"/>
										</div>
									</div>
								</div>


								<div class="col-sm-6 form-group">
									<label for="memPhone" class="cols-sm-2 control-label">���</label><span id="memPhoneShow"></span>
									<div class="cols-sm-10">
										<div class="input-group">
											<span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
											<input type="text" class="form-control" name="memPhone" id="memPhone"  placeholder="�п�J�z�����"/>
										</div>
									</div>
								</div>
							</div>


							<div class="form-group">
								<label for="memGender" class="control-label">�ʧO</label><br>
								<label class="radio-inline"><input type="radio" name="memGender" checked="true" value="0">�k</label>
								<label class="radio-inline"><input type="radio" name="memGender" value="1">�k</label>
								<label class="radio-inline"><input type="radio" name="memGender" value="2">�ĤT��</label>
							</div>


							<div class="form-group">
								<label for="memAddress" class="cols-sm-2 control-label">�a�}</label><span id="memAddressShow"></span>
								<div class="cols-sm-10">
									<div class="input-group">
										<span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
										<textarea class="form-control" id="memAddress" name="memAddress"  placeholder="�п�J�z���a�}"></textarea>
									</div>
								</div>
							</div>


							<div class="form-group">
								<label for="memEmail" class="cols-sm-2 control-label">�q�l�H�c</label><span id="memEmailShow"></span>
								<div class="cols-sm-10">
									<div class="input-group">
										<span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
										<input type="text" class="form-control" name="memEmail" id="memEmail"  placeholder="�п�J�z���q�l�H�c"/>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-sm-6 form-group">
									<label for="memImg" class="control-label ">�Ӥ�</label><span id="memImgShow"></span> <br>
									<input  type="file"  name="memImg" id="memImg" placeholder="½��¿é¤J±zªº¹q¤l«H½c"/>
									<img src="https://api.fnkr.net/testimg/350x200/00CED1/FFF/?text=img+placeholder" height="200px" width="150px" id="memPic" style="margin-top: 5px "><br>		
								</div>

								<div class="col-sm-6 form-group">
									<label for="petOrNot" class="control-label">���L�i�d��</label><br>
									<label class="radio-inline"><input type="radio" name="petOrNot" id="petOrNot_0" value="0" checked="true">�L</label>
									<label class="radio-inline"><input type="radio" name="petOrNot" id="petOrNot_1" value="1">��</label>
								</div>
							</div>

							<div id="petDiv" style="display:none;">
								<div class="form-group">
									<label for="petName" class="cols-sm-2 control-label">�d���m�W</label><span id="petNameShow"></span>
									<div class="cols-sm-10">
										<div class="input-group">
											<span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
											<input type="text" class="form-control" name="petName" id="petName"  placeholder="�п�J�z���d���m�W"/>
										</div>
									</div>
								</div>


								<div class="row">
									<div class="col-sm-6 form-group">
										<label for="petKind" class="control-label">�d������</label><br>
										<label class="radio-inline"><input type="radio" name="petKind" id="petOrNot_0" value="��" checked="true">��</label>
										<label class="radio-inline"><input type="radio" name="petKind" id="petOrNot_1" value="��">��</label>
										<label class="radio-inline"><input type="radio" name="petKind" id="petOrNot_2" value="��L">��L</label>
									</div>


									<div class="col-sm-6 form-group">
										<label for="petGender" class="control-label">�d���ʧO</label><br>
										<label class="radio-inline"><input type="radio" name="petGender" value="0" checked="true">�k</label>
										<label class="radio-inline"><input type="radio" name="petGender" value="1">�k</label>
									</div>

								</div>


								<div class="form-group">
									<label for="petImg" class="control-label">�d���Ӥ�</label><span id="petImgShow"></span><br>
									<input type="file"  name="petImg" id="petImg" />
									<img src="https://api.fnkr.net/testimg/350x200/00CED1/FFF/?text=img+placeholder" height="200px" width="150px" id="petPic" style="margin-top: 5px "><br>

								</div>

							</div>

							<input type="hidden" name="action" value="register">
							<input class="btn btn-primary btn-lg btn-block login-button" type="submit" value="���U">

						</form>
						
						<button type="button" class="btn btn-info" id="autoAddMem" style="margin-top: 10px">�s�W�@��|��</button>
						<button type="button" class="btn btn-info" id="autoAddMemPet" style="margin-top: 10px">�s�W�d���|��</button>

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
	<script src="<%=request.getContextPath() %>/front_end/js/jquery.js"></script>
	<!-- Bootstrap Core JavaScript -->
	<script src="<%=request.getContextPath() %>/front_end/js/bootstrap.min.js"></script>
	<!-- Script to Activate the Carousel -->

</body>

</html>