<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>
<%@ page import="java.util.*"%>


<%
	Emp emp = (Emp) session.getAttribute("emp");
	pageContext.setAttribute("emp", emp);
%>


<jsp:useBean id="dateitem" scope="page" class="com.dateitem.model.DateItemService"/>
<jsp:useBean id="dateitemapp" scope="page" class="com.dateitemapp.model.DateItemAppService"/>
<jsp:useBean id="appreprec" scope="page" class="com.appreprec.model.AppRepRecService"/>



<!DOCTYPE html>
<html lang="">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>Title Page</title>
<link href="<%=request.getContextPath()%>/back_end/css/bootstrap.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/back_end/css/nav.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/back_end/css/colorplan.css" rel="stylesheet">
<!-- Custom CSS -->
<link href="<%=request.getContextPath()%>/back_end/css/modern-business.css" rel="stylesheet">
<!-- Custom Fonts -->
<link href="<%=request.getContextPath()%>/back_end/font-awesome/css/font-awesome.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/back_end/css/backend.css" rel="stylesheet">
</head>

<body>

	<%@ include file="/back_end/backEndNavBar.file"%> 
	<%@ include file="/back_end/backEndLSide.file"%> 

			<div class="col-xs-12 col-sm-8">


				<h5 class="page-header text-right">目前位置:申訴處理首頁</h5>


				<div class="panel panel-default  text-center">
					<div class="panel-heading">
						<h3 class="panel-title">管理約會商品申訴區</h3>						
					</div>
					<div class="panel-body text-left" style="padding-bottom:0px;">
							
					</div>
					<div class="panel-body">
					
						<table class="table table-condensed" >
							<tr >
								<th class="text-center">申訴人</th>
								<th class="text-center">被申訴約會商品</th>
								<th class="text-center">申訴標題</th>
								<th class="text-center">申訴內文</th>
								<th class="text-center">申訴日</th>
								<th class="text-center">被申訴人</th>
								<th class="text-center">被申訴近一個月次數</th>
								<th class="text-center">處理狀況</th>
								
							</tr>
						<c:forEach var="appeal" items="${dateitemapp.getAll()}">
							<tr >
								<td>${appeal.memNo }</td>	
								<td><input type="button" value="${appeal.dateItemNo }" class="btn btn-default"  ></td>	
								<td>${appeal.appTitle }</td>	
								<td>
									<a href='#${appeal.appNo }' data-toggle="modal" class="btn btn-primary" >申訴詳情</a>	
								</td>	
								<td>${appeal.appDate }</td>	
								<td>${dateitem.findByPK(appeal.dateItemNo).sellerNo }</td>	
								<td>${appreprec.findOneMonthTimes(dateitem.findByPK(appeal.dateItemNo).sellerNo) }</td>	
								<td>
									<c:if test="${appeal.appState==0}" var="outcome">
										<input type="hidden" value="${appeal.appNo}">
										<input type="button" value="Pass" class="btn btn-info"  onclick="pass(this);">
										<input type="button" value="Deny" class="btn btn-danger" onclick="deny(this);" >
									</c:if>
									<c:if test="${outcome==false}">
										<img src="<%=request.getContextPath()%>/back_end/images/checked.jpg" style='height:40px;width:50px;'></img>
									</c:if>
								</td>									
							</tr>
							
							<div class="modal fade" id="${appeal.appNo }">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
											<div class="modal-title text-left">${appeal.appTitle }</div>
										</div>
										<div class="modal-body text-left">
											${appeal.appText }
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-primary" data-dismiss="modal" >確定</button>
										</div>
									</div>
								</div>
							</div>			
							
						</c:forEach>		
						</table>
						
					</div>
				</div>
			</div>

			<script src="https://code.jquery.com/jquery.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
			
			<script type="text/javascript">
			function pass(e){
				var appNo = $(e).prev().val();
				var app = $(e).parent();
				$.ajax({
					url:"<%=request.getContextPath()%>/back_end/apprec/dateitemapp.do",
					data:{
						action : 'updatepass',
						appno : appNo					
					},
					type : 'POST',
					error : function(xhr) {
						alert('Ajax request 發生錯誤');
						},
					success : function(result) {
						$(e).parent().prev().text(parseInt($(e).parent().prev().text())+1);
						$(e).parent().empty();
						$('<img>').attr('src',"<%=request.getContextPath()%>/back_end/images/checked.jpg").css({"height":"40px","width":"50px"}).appendTo(app);
						
					}
				});	
			}
			function deny(e){
				var appNo = $(e).prev().prev().val();
				var app = $(e).parent();
				$.ajax({
					url:"<%=request.getContextPath()%>/back_end/apprec/dateitemapp.do",
					data:{
						action : 'updatedeny',
						appno : appNo					
					},
					type : 'POST',
					error : function(xhr) {
						alert('Ajax request 發生錯誤');
						},
					success : function(result) {
						
						$(e).parent().empty();
						$('<img>').attr('src',"<%=request.getContextPath()%>/back_end/images/checked.jpg").css({"height":"40px","width":"50px"}).appendTo(app);
						
					}
				});	
			}
			
			
			
			</script>
</body>

</html>