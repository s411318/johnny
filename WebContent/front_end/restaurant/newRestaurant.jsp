<%@page import="com.restaurant.model.Restaurant"%>
<%@page import="com.restMember.model.RestMember"%>
<%@page import="com.restMember.model.RestMemberService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%-- <% Restaurant restaurant = (Restaurant)request.getParameter("restaurant"); %> --%>
<%-- �����ĥ� JSTL �P EL ���� --%>
<!DOCTYPE html>
<html lang="">
<head>

<%@ include file="/front_end/actFiles/restFrontCss.file" %>
<title>Title Page</title>
<style type="text/css">
.aa {
	margin-top: 20px;
}




</style>

</head>
<body>
	<%@ include file="/front_end/actFiles/restMemberNavBar2.file" %>
	
	
	
	<div class="container">
		<div class="row">
        	
    
    
    	<div class="col-sm-offset-3">
			<div class="col-sm-9">
				
				<h1 class="text-center">�s�W�����\�U</h1>

				<div class="form-horizontal">
				<c:if test="${not empty newRestErr}">
					${newRestErr}
				</c:if>
					<form method="post" action="<%=request.getContextPath()%>/restaurant/restaurantController">
							
							<input type="hidden" name="restReviewStatus" class="form-control" value="2" >
							
							<div class="form-group">
								<label class="col-sm-3 control-label">
									�\�U�W��
								</label>
								<div class="col-sm-9">
									<input type="text" name="restName" class="form-control" 
									value="" placeholder="�п�J�\�U�W��" >
									
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-3 control-label">
									�\�U�a�}
								</label>
								<div class="col-sm-9">
									<input type="text" name="restAdd" class="form-control" 
									value="" placeholder="�п�J�\�U�a�}">

								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-3 control-label">
									�\�U�q��
								</label>
								<div class="col-sm-9">
									<input type="text" name="restPhone" class="form-control" 
									value="" placeholder="�п�J�\�U�q��">
									
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-3 control-label">
									�\�U����
								</label>
								<div class="col-sm-9">
									<textarea rows="3" cols="54" name="restIntro" class="form-control" 
									 placeholder="�п�J�\�U����" style="resize:none"></textarea>
									
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-3 control-label">
									�\�U����
								</label>
								<div class="col-sm-9">
									<select name="restKind" class="form-control">
									  <option value="0">���\�U</option>
									  <option value="1">���\�U</option>
									  <option value="2">��L�\�U</option>
									</select>
								</div>
							</div>
							
							
							<input type="hidden" name="action" value="newRestaurant">
							<input class="btn btn-primary btn-lg btn-block login-button login"
								type="submit" value="�s�W���\�U">
							<div>
								<a href="<%=request.getContextPath() %>/front_end/restMember/restMemberLogin.jsp" class="btn btn-link">�^�n�J����</a> 
								<a href="<%=request.getContextPath() %>/front_end/restMember/restMemberList.jsp" class="btn btn-link">�^���U����</a>
							</div>	
								
						</form>			
					</div>	
						
						
					
				</div>
			</div>
		</div>
	</div>
<%@ include file="/front_end/frontEndButtomFixed.file" %>      
	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>