<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>
<%@ page import="com.lettertype.model.*"%>
<%@ page import="java.util.*"%>


<%
	Emp emp = (Emp) session.getAttribute("emp");
	pageContext.setAttribute("emp", emp);
%>

<jsp:useBean id="ltrTypeSvc" scope="page" class="com.lettertype.model.LetterTypeService"/>



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


				<h5 class="page-header text-right">�ثe��m:�����H����</h5>


				<div class="panel panel-default  text-center">
					<div class="panel-heading">
						<h3 class="panel-title">�޲z�����H��</h3>						
					</div>
					<div class="panel-body text-left" style="padding-bottom:0px;">
						<a href='#modal-id' data-toggle="modal" class="btn btn-primary">�s�W����</a>
							<div class="modal fade" id="modal-id">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
											<div class="modal-title"><textarea cols="70" id="insname" placeholder="�����W��......">����</textarea></div>
										</div>
										<div class="modal-body">
											<textarea cols="80" rows="5" id="instext" placeholder="�s�W����......">�A�n</textarea>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-primary" data-dismiss="modal" id="checkval" >�e�X�s�W</button>
										</div>
									</div>
								</div>
							</div>					
					</div>
					<div class="panel-body">
					
						<table border='1' bordercolor='#CCCCFF' width='780' id="typetab">
							<tr >
								<th class="text-center">�����s��</th>
								<th class="text-center">�����W��</th>
								<th class="text-center">��������</th>
								<th class="text-center">���s</th>
							</tr>
						<c:forEach var="ltrtype" items="${ltrTypeSvc.getAll()}">
							<tr >
								<td>${ltrtype.letterTypeNo}</td>	
								<td>${ltrtype.letterTypeName}</td>	
								<td>${ltrtype.letterTypeText}</td>	
								<td><input type="button" value="�ק�" class="btn btn-info" onclick="update(this);" ></td>
							</tr>
						</c:forEach>
							<tr id="insert"></tr>
						</table>
					</div>
				</div>
			</div>

			<script src="https://code.jquery.com/jquery.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
			
			<script type="text/javascript">
			$('#checkval').click(function(){
				if($("#insname").val()==""){
					alert("�W�٤���ť�");
				}else if($('#instext').val()==""){
					alert("���夣��ť�");
				}else{
					insert();
				}
			});
			function insert(){
								
				$.ajax({
					url:"<%=request.getContextPath()%>/back_end/letter/letterType.do",
					data:{
						action : 'insert',
						lettertypename: $('#insname').val(),
						lettertypetext: $('#instext').val()						
					},
					type:'POST',
					dataType : 'text',
					error : function(xhr) {
						alert('Ajax request �o�Ϳ��~');
					},
					success: function(data) {
						var obj = JSON.parse(data);//�নjson�榡				
						$('<td>').appendTo($('#insert')).text(obj.curr);
						$('<td>').appendTo($('#insert')).text($("#insname").val());
						$('<td>').appendTo($('#insert')).text($("#instext").val());
						$('<td>').appendTo($('#insert')).attr("id","newbtn");
						$('<input>').attr({"type":"button","value":"�ק�","class":"btn btn-info","onclick":"update(this);"}).appendTo($('#newbtn'));						
						$('#insert').removeAttr('id');
						$('#newbtn').removeAttr('id');
						$('<tr>').appendTo($('#typetab')).attr("id","insert");						
					}
				});			
			}
			
			function update(e){
				var modupda = $(e).parent(); //td
				//�@���u��ק�@��
				modupda.parent().prevAll().children().find('input').attr({"class":"btn disabled","onclick":""});
				modupda.parent().nextAll().children().find('input').attr({"class":"btn disabled","onclick":""});
		
				var name = modupda.prev().prev().text();
				var txt = modupda.prev().text();
				if($(e).val()=='�ק�'){					
					//type Name ���ק�
					modupda.prev().prev().text("");
					$('<textarea>').text(name).attr("id","modname").appendTo(modupda.prev().prev());
							
					//type Text ���ק�
					modupda.prev().text("");
					$('<textarea>').text(txt).attr("id","modtext").appendTo(modupda.prev());
					
					$(e).val("�T�w");
					$(e).attr("class","btn btn-success");
					$('<input>').attr({"type":"button","value":"����","onclick":"cancal(this);","class":"btn btn-warning"}).appendTo(modupda);
					
				}else if($(e).val()=='�T�w'){
					$.ajax({
						url:"<%=request.getContextPath()%>/back_end/letter/letterType.do",
						data:{
							action : 'update',
							lettertypeno : modupda.prev().prev().prev().text(),
							lettertypename: $('#modname').val(),
							lettertypetext: $('#modtext').val()						
						},
						type : 'POST',
						error : function(xhr) {
							alert('Ajax request �o�Ϳ��~');
							},
						success : function(result) {
							modupda.prev().prev().text($('#modname').val());
							modupda.prev().text($('#modtext').val());
							$('#modname').remove();
							$('#modtext').remove();
							$(e).val('�ק�');
							$(e).attr("class","btn btn-info");
							$(e).next().remove();	
							modupda.parent().prevAll().children().find('input').attr({"class":"btn btn-info","onclick":"update(this);"});
							modupda.parent().nextAll().children().find('input').attr({"class":"btn btn-info","onclick":"update(this);"});
						}
					});			
				}	
			}
			function cancal(e){
				var modupda = $(e).parent(); //td
				modupda.prev().prev().text($('#modname').text());
				modupda.prev().text($('#modtext').text());
				$('#modname').remove();
				$('#modtext').remove();
				$(e).prev().val('�ק�');	
				$(e).prev().attr("class","btn btn-info");
				$(e).remove();	
				
				modupda.parent().prevAll().children().find('input').attr({"class":"btn btn-info","onclick":"update(this);"});
				modupda.parent().nextAll().children().find('input').attr({"class":"btn btn-info","onclick":"update(this);"});
			}
			
			</script>
</body>

</html>