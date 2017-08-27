<%@page import="com.actDetial.model.ActDetialService"%>
<%@page import="com.actDetial.model.ActDetial"%>
<%@page import="com.member.model.Member"%>
<%@page import="com.restaurant.model.RestaurantService"%>
<%@page import="com.restaurant.model.Restaurant"%>
<%@page import="com.restMember.model.RestMemberService"%>
<%@page import="com.restMember.model.RestMember"%>
<%@page import="com.activity.model.Activity"%>
<%@page import="com.activity.model.ActivityService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	ActivityService activityService = new ActivityService();
	List<Activity> activityFrontList = activityService.getAllNorth();
	request.setAttribute("activityFrontList", activityFrontList);

	RestMemberService restMemberService = new RestMemberService();
	RestaurantService restaurantService = new RestaurantService();
	
	RestMember restMember = null;
	Restaurant restaurant = null;
	
	
	
	
%>
<!DOCTYPE html>
<html lang="">

<head>
    <%@ include file="/front_end/actFiles/actFrontCss.file" %>
</head>

<body>
	<%@ include file="/front_end/actFiles/overflow.file" %>
    <%@ include file="/front_end/frontEndNavBar.file" %>
    <div class="container-fluid">
        <div class="row">
            <%@ include file="/front_end/actFiles/actFrontList.file" %>
            
            

            <div class="panel-group col-sm-8">
			<h1 class=" page-header text-left">�_���\�U����</h1>

			<%@ include file="/front_end/actFiles/activityAll.file" %>
	

		
			</div>
		</div>
	</div>
</div>
     <%@ include file="/front_end/frontEndButtomFixed.file" %>       
            <script src="https://code.jquery.com/jquery.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
            <script src="http://code.jquery.com/jquery-latest.min.js"></script>
</body>

</html>