<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.HashMap"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="">

<head>
<%@ include file="memHead.file"%>



</head>

<body>
	<%@ include file="/front_end/frontEndNavBar.file" %>
	<div class="container-fluid">
		<div class="row">

			<div class="col-xs-12 col-sm-2 postion-left-group ">
					<%@ include file="memZoneLSide.file" %>
			</div>

			<div class="col-xs-12 col-sm-8 ">
				<div class="row">

					<h5 class="page-header text-right">目前位置:會員個人資訊</h5>


					<div class="row">

						<div class="panel panel-info">
							<div class="panel-heading">
								<h3 class="panel-title">${member.memId}</h3>
							</div>
							<div class="panel-body">
								<form method="post" action="<%=request.getContextPath() %>/front_end/member/member.do" enctype="multipart/form-data">
									<div class="row">
										<div class="col-md-3 col-lg-3 " align="center">
											<img alt="User Pic" id="memPic" src="<%=request.getContextPath() %>/DBGifReader"
												height="350px" width="250px"
												class="img-circle img-responsive"> <input type="file"
												name="memImg" id="memImg" placeholder="編輯相片" />
										</div>


										<div class=" col-md-9 col-lg-9 ">
											<table class="table table-user-information">
												<tbody>
													<tr>
														<td class="title" >暱稱</td>
														<td><input type="text" class="form-control"
															name="memSname" id="memSname" value="${member.memSname}"
															placeholder="請輸入暱稱" required/></td>
													</tr>
													<tr>
														<td class="title">姓名</td>
														<td><input type="text" class="form-control"
															name="memName" id="memName" value="${member.memName}"
															placeholder="請輸入您的姓名" required/></td>
													</tr>
													<tr>
														<td class="title">生日</td></label>
														<td><input type="text" name="memBday" id="memBday"
															value="${member.memBday}" class="form-control"
															placeholder="請輸入您的生日" required/><span id="memBdayShow"></span></td>
													</tr>
													<tr>
														<td class="title">手機</td>
														<td><input type="text" class="form-control"
															name="memPhone" id="memPhone" value="${member.memPhone}"
															placeholder="請輸入您的手機" required/><span id="memPhoneShow"></span></td>
													</tr>
													<tr>
														<td class="title">性別</td>
														<td><input type="radio" name="memGender"
															${member.memGender==0?'checked':''} value="0">男 <input
															type="radio" name="memGender"
															${member.memGender==1?'checked':''} value="1">女 <input
															type="radio" name="memGender"
															${member.memGender==2?'checked':''} value="2">不願透漏
														</td>
													</tr>
													<tr>

														<td class="title">感情</td>

														<td><input type="radio" name="memRelation"
															${member.memRelation==0?'checked':''} value="0">單身
															<input type="radio" name="memRelation"
															${member.memRelation==1?'checked':''} value="1">穩定交往
															<input type="radio" name="memRelation"
															${member.memRelation==2?'checked':''} value="2">不公開
														</td>
													</tr>
													<tr>
														<td class="title">粉絲</td>
														<td>${member.memFollowed}人</td>
													</tr>
													<tr>
														<td class="title">點數</td>
														<td>${member.memPoint}點</td>
													</tr>
													<tr>
														<td class="title">Email</td>
														<td><input type="text" class="form-control"
															name="memEmail" id="memEmail" value="${member.memEmail}"
															placeholder="請輸入您的電子信箱" required/><span id="memEmailShow"></span></td>
													</tr>
													<td>地址</td>
													<td><textarea class="form-control" id="memAddress"
															name="memAddress" placeholder="請輸入您的地址" required>${member.memAddress}</textarea><span id="memAddressShow"></span></td>
													<tr>
														<td class="title">關於我</td> </td>
														<td><textarea class="form-control" id="memSelfintro"
																name="memSelfintro" placeholder="請輸入您的自我介紹" required>${member.memSelfintro}</textarea><span id="memSelfintroShow"></span></td>
													</tr>


												</tbody>
											</table>
											<input type="hidden" name="action" value="memUpdate"> 
											<input type="submit" value="修改" class="btn btn-primary">
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


				
			</div>
		</div>
		<%@ include file="/front_end/frontEndButtom.file" %>
		<script>
		$(function(){
			
			var valids = new Array(true,true, true);
			
			
			//生日驗證 生日最大可選天數
			var today = new Date();
			var dd = today.getDate();
			var mm = today.getMonth()+1; //January is 0!
			var yyyy = today.getFullYear();
			if(dd<10){
				dd='0'+dd
			} 
			if(mm<10){
				mm='0'+mm
			} 

			today = yyyy+'-'+mm+'-'+dd;

		
// 			function isValidDate(dateString) {
// 				  var regEx = /^\d{4}-\d{2}-\d{2}$/;
// 				  if(!dateString.match(regEx))
// 				    return false;  // Invalid format
// 				  var d;
// 				  if(!((d = new Date(dateString))|0))
// 				    return false; // Invalid date (or this could be epoch)
// 				  return d.toISOString().slice(0,10) == dateString;
// 				}

					//生日驗證 生日不可以大於今天
					$("#memBday").blur(function(e){
						console.log(e.target.id);
						var nDay=yyyy+mm+dd;
						if(e.target.id=="memBday"){
							days=$("#memBday").val().split("-");
							tDay=days[0]+days[1]+days[2];
							if(tDay>nDay){
								$("#memBdayShow").html("&nbsp;&nbsp;&nbsp;&nbsp;不合格的生日").css('color','red');
								valids[0]=false;
							}
// 							else if(!isValidDate(e.target.value)){
// 								$("#memBdayShow").html("&nbsp;&nbsp;&nbsp;&nbsp;生日格式不符").css('color','red');
// 								valids[0]=false;
// 							}
							else{
								$("#memBdayShow").html("");
								valids[0]=true;
							}
						}
					});


					//手機驗證
					$("#memPhone").blur(function() {
						var phone1 = new RegExp("[09]{2}[0-9]{2}\-[0-9]{6}");
						var phone2 = new RegExp("[09]{2}[0-9]{8}");
						if (phone1.test($("#memPhone").val())||phone2.test($("#memPhone").val()))  
						{  
							$("#memPhoneShow").html("").css('color','green');
							valids[1]=true;
						}  
						else{
							$("#memPhoneShow").html("&nbsp;&nbsp;&nbsp;&nbsp;不合格的手機格式").css('color','red');
							valids[1]=false;
						} 
					});



					//信箱驗證
					$("#memEmail").blur($.fn.testMemEmail = function() {

						var memEmail = $("#memEmail").val();
						$.ajax({
							url: '<%=request.getContextPath() %>/front_end/member/RegisterExit.do',
							data: {
								memEmail:$("#memEmail").val(),
								action :"memEmail"			
							},
							success : function(responseText){
									console.log(responseText);
									if(responseText.trim() == "抱歉，Email格式不正確"){
										valids[2]=false;
										$("#memEmailShow").html(responseText).css('color','red');
									}
									else if(responseText.trim() == "抱歉，此Email已經註冊過了"){
										if($("#memEmail").val()=="${member.memEmail}"){
											console.log("this is my email");
											$("#memEmailShow").html("").css('color','green');
											valids[2]=true;
										}
										else{
											valids[2]=false;
											$("#memEmailShow").html(responseText).css('color','red');
										}
									}
									else{
										$("#memEmailShow").html("").css('color','green');
										valids[2]=true;
									}	
								}
									
						});

					});

					
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
					};
					
					
					
					//送出表單時檢察有無未填
					$('form').submit(function() {
						

						for (var i = 0; i < valids.length; i++) {
							if(!valids[i]){
								return false;
							}
						}

					});
					
					
			
		});
		</script>
		
		
	<script>

		$(function(){

	
		$("#memBday").datetimepicker({
		format: 'Y-m-d',
		 timepicker:false,
		 maxDate: '0',
		});

	 
		});


	</script>
		

</body>

</html>