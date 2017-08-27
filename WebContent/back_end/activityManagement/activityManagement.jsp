<%@page import="com.activity.model.Activity"%>
<%@page import="com.activity.model.ActivityService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>
<%@ page import="java.util.*"%>


<%
	ActivityService activityService = new ActivityService();
	List<Activity> activityBackList = (request.getAttribute("activityBackList")==null)?activityService.getAll():(List<Activity>)request.getAttribute("activityBackList");
	pageContext.setAttribute("activityBackList", activityBackList);
	
	
	
%>
<!DOCTYPE html>
<html lang="">
<head>
    <%@ include file="/back_end/actFiles/actBackCss.file" %>
</head>
<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
	<script>
	$(document).ready(function(){
		$("tr:even").css("background-color","white");
		$("tr:odd").css("background-color","#D9FFFF");
		
	})
	</script>
	<style type="text/css">
		.actTable{
			margin-top: 50px;
		}
		th{
			text-align: center;
			font-size: 16px;
			background: #40E0D0;
		}
		td{
			text-align: center;
		}
		
		
</style>
<body>
    <%@ include file="/back_end/backEndNavBar.file" %>
	<%@ include file="/back_end/backEndLSide.file" %>
	
	    	<div class="container-fluid">
				<div class="row">
					<div class="col-xs-12 col-sm-10">
					<form method="post" action="<%=request.getContextPath()%>/activity/activityController" >
						<div class="form-group row ">
							<div class="col-xs-2">
								���ʽs��:<input class="form-control" type="text" name="actNo" value="">
							</div>
							<div class="col-xs-2">	
								���ʦW��:<input class="form-control" type="text" name="actName" value="">
							</div>	
							<div class="col-xs-2">	
								���ʤ��:<input class="form-control" type="text" name="actDate" value="">
							</div>
							<div class="col-xs-2">	
								���ʺI����:<input class="form-control" type="text" name="actFDate" value="">
							</div>
								<br>
								
							<input type="submit" class="btn btn-primary text-rigth" value="�j�M"> 
							
							<a href="<%=request.getContextPath()%>/back_end/activityManagement/activityManagement.jsp">
								<input type="button" class="btn btn-primary text-rigth" value="��������">
							</a>
							
							
							
							<input type="hidden" name="action" value="CompositeQueryBackAct">	
						</div>		
					</form>
						<table class="table table-hover table-striped table-condensed table-bordered actTable">
						<thead>
							<tr>
								<th>���ʬۤ�</th>
								<th>���ʽs��</th>
								<th>���ʦW��</th>
								<th>���ʤ��</th>
								<th>�I����</th>
								<th>�H�ƤW��</th>
								<th>�H�ƤU��</th>
								<th>��������</th>
								<th>���ʪ��A</th>
								<th>�f�֬���</th>
							</tr>
						</thead>
							<c:forEach var="activityBack" items="${activityBackList}" >
								<form method="post" action="<%=request.getContextPath()%>/activity/activityController">
									<tr>
										<td>
											<div class="imgimg">
												<img src="<%=request.getContextPath()%>/activity/DBGifReader5?actNo=${activityBack.actNo}" 
													class="img-rounded" style="width:100px;height:100px;"   />                          
											</div>
										</td>
										
										<td><br><br>${activityBack.actNo}</td>
										<td><br><br>${activityBack.actName}</td>
										<td><br><br>${activityBack.actDate}</td>
										<td><br><br>${activityBack.actFDate}</td>
										<td><br><br>${activityBack.actULimit}</td>
										<td><br><br>${activityBack.actLLimit}</td>
											<c:if test="${activityBack.actKind=='0'}">
												<td><br><br>��</td>
											</c:if>
											<c:if test="${activityBack.actKind=='1'}">
												<td><br><br>��</td>
											</c:if>
											<c:if test="${activityBack.actKind=='2'}">
												<td><br><br>��L</td>
											</c:if>
										<td><br><br>
											<select name="actStatus">    
											  <option value="0" ${(activityBack.actStatus=='0')?'selected':''} >�o�_����</option>
											  <option value="1" ${(activityBack.actStatus=='1')?'selected':''} >�f�֥��q�L</option>
											  <option value="2" ${(activityBack.actStatus=='2')?'selected':''} >�f�ֳq�L</option>
											  <option value="3" ${(activityBack.actStatus=='3')?'selected':''} >�\�U��������</option>
											</select>
										</td>
										<td><br><br>
											<input type="hidden" name="actNo" value="${activityBack.actNo}">
											<input type="hidden" name="action" value="actManagement">
											<input type="submit" class="btn btn-primary" value="�ק�">
										</td>
									</tr>
								</form>	
							</c:forEach>
								
							
						
						</table>
					</div>
				</div>
			</div> 
  		
	
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>

</html>