<%@page import="com.restMember.model.RestMemberService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.restaurant.model.*"%>
<%-- �����ĥ� JSTL �P EL ���� --%>

<%
    RestaurantService restaurantSvc = new RestaurantService();
	Restaurant restaurant = new Restaurant();
    List<Restaurant> list = restaurantSvc.getAll();
    session.setAttribute("list",list);  
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="/front_end/actFiles/restFrontCss.file" %>
<head>
<title>�\�U�@����</title>
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
		
		
</style>

<body>
<%@ include file="/front_end/actFiles/restMemberNavBar2.file" %>
<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12 col-sm-offset-1 col-sm-10">
					<div class="col-xs-12 col-sm-12"><br>
					<h1 class=" page-header text-left">�����߿ߤ͵��\�U</h1>
					
 				<!-- 					�ƦX�d��                                                                        -->
					<form method="post" action="<%=request.getContextPath()%>/restaurant/restaurantController" >
						<div class="form-group row">
							<div class="col-xs-3">
								�\�U�W��:<input class="form-control" type="text" name="restName" value="">
							</div>
							<div class="col-xs-3">	
								�\�U�a�}:<input class="form-control" type="text" name="restAdd" value="">
							</div>	
							<div class="col-xs-3">	
								�\�U�q��:<input class="form-control" type="text" name="restPhone" value="">
							</div>
								<br>
								
							<input type="submit" class="btn btn-primary text-rigth" value="�j�M"> 
							
							<a href="<%=request.getContextPath()%>/front_end/restMember/restMemberList.jsp">
								<input type="button" class="btn btn-primary text-rigth" value="�����\�U">
							</a>
							
							<a href="<%=request.getContextPath() %>/front_end/restaurant/newRestaurant.jsp">
								<input type="button" class="btn btn-primary" value="�s�W�\�U">
							</a>
							
							<input type="hidden" name="action" value="CompositeQuery">	
						</div>		
					</form>
					
					
					<table class="table table-striped">
						<tr>
							
						<th width="150px" >&nbsp;&nbsp;&nbsp;&nbsp;�\�U�W��</th>
						<th>�\�U�a�}</th>
						<th>�\�U�q��</th>
						
						<th>�\�U����</th>
						<th>�f�֪��A</th>
						<th></th>
						
					</tr>
					
					<c:forEach var="restaurant" items="${list}">
						<tr align='center' valign='middle'>
							
							<td>&nbsp;&nbsp;&nbsp;&nbsp;${restaurant.restName}</td>
							<td >${restaurant.restAdd}</td>
							<td>${restaurant.restPhone}</td>
							
							<td>
						
									<c:if test="${restaurant.restKind=='0'}">
										<input type="hidden" name="restKind" class="form-control" value="0" >���\�U
									</c:if>
									<c:if test="${restaurant.restKind=='1'}">
										<input type="hidden" name="restKind" class="form-control" value="1" >���\�U
									</c:if>
									<c:if test="${restaurant.restKind=='2'}">
										<input type="hidden" name="restKind" class="form-control" value="2" >��L�\�U
									</c:if>
												
							</td>
							<td>
									<c:if test="${restaurant.restReviewStatus=='0'}">
										<input type="hidden" name="restReviewStatus" class="form-control" value="0" >�w�f�ֳq�L
									</c:if>
									<c:if test="${restaurant.restReviewStatus=='1'}">
										<input type="hidden" name="restReviewStatus" class="form-control" value="1" >�f�֥��q�L
									</c:if>
									<c:if test="${restaurant.restReviewStatus=='2'}">
										<input type="hidden" name="restReviewStatus" class="form-control" value="2" >�|���f��
									</c:if>
									<c:if test="${restaurant.restReviewStatus=='3'}">
										<input type="hidden" name="restReviewStatus" class="form-control" value="2" >�w���U���\
									</c:if>
							</td>
							<td>
							<c:if test="${restaurant.restReviewStatus=='0'}">
								<FORM METHOD="post" ACTION="<%=request.getContextPath() %>/front_end/restMember/restMemberRegister.jsp">
							     <input type="submit" class="btn btn-primary" value="���U">
							     <input type="hidden" name="restNo" value="${restaurant.restNo}">
							     <input type="hidden" name="restName" value="${restaurant.restName}">
							     <input type="hidden" name="restAdd" value="${restaurant.restAdd}">
							     <input type="hidden" name="restPhone" value="${restaurant.restPhone}">
							     <input type="hidden" name="restIntro" value="${restaurant.restIntro}">
							     <input type="hidden" name="restKind" value="${restaurant.restKind}">
							     <input type="hidden" name="restReviewStatus" value="${restaurant.restReviewStatus}">
							     <input type="hidden" name="restLongtitude" class="form-control" value="<%=restaurant.getRestLongtitude() %>" >
								 <input type="hidden" name="restLatitude" class="form-control" value="<%=restaurant.getRestLatitude() %>" >
								 <input type="hidden" name="restLocate" class="form-control" value="<%=restaurant.getRestLocate() %>" >
							 </FORM>
							</c:if>
							
							<c:if test="${restaurant.restReviewStatus=='1'}">
								<input type="submit" class="btn btn-danger" value="�f�֥��q�L"  disabled>
							</c:if>
							<c:if test="${restaurant.restReviewStatus=='2'}">
								<input type="submit" class="btn btn-danger" value="���f��"  disabled>
							</c:if>
							<c:if test="${restaurant.restReviewStatus=='3'}">
								<input type="submit" class="btn btn-success" value="�w���U"  disabled>
							</c:if>
							  
							</td>
						</tr>
					</c:forEach>
				</table>
				
				
				
			</div>
		</div>
	</div>	
</div>
<%@ include file="/front_end/frontEndButtomFixed.file" %>      
	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
