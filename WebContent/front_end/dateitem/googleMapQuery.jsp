<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ page import="com.dateitem.model.*"%>
<%@ page import="com.restaurant.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>

<%@ include file="header.file"%>
<script
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCAUbYcDBdfK_UjTWa9G6FSe3EfERMpEZQ">
	
</script>
<!--     <script src="https://maps.googleapis.com/maps/api/js?sensor=false" type="text/javascript"></script> -->




<head>
<title>���|����</title>
<link href="<%=request.getContextPath() %>/front_end/css/modern-business.css" rel="stylesheet" type="text/css">
<style type="text/css">
#map_canvas {
	width: 100%;
	height: 50%;
}

select {
  border: 1px solid #333;
  display: inline-block;
  border-radius: 5px;
  width: 200px;
  color: #ccc;
  height: 30px;
  line-height: 30px;
  
}



    #custom-search-form {
        margin:0;
        margin-top: 5px;
        padding: 0;
    }
 
    #custom-search-form .search-query {
        padding-right: 3px;
        padding-right: 4px \9;
        padding-left: 3px;
        padding-left: 4px \9;
        /* IE7-8 doesn't have border-radius, so don't indent the padding */
 
        margin-bottom: 0;
        -webkit-border-radius: 3px;
        -moz-border-radius: 3px;
        border-radius: 3px;
    }
 
    #custom-search-form button {
        border: 0;
        background: none;
        /** belows styles are working good */
        padding: 2px 5px;
        margin-top: 2px;
        position: relative;
        left: -28px;
        /* IE7-8 doesn't have border-radius, so don't indent the padding */
        margin-bottom: 0;
        -webkit-border-radius: 3px;
        -moz-border-radius: 3px;
        border-radius: 3px;
    }
 
    .search-query:focus + button {
        z-index: 3;   
    }



</style>
</head>
<body bgcolor='white'>



	<%@ include file="/front_end/frontEndNavBar.file"%>
	<%@ include file="sidelist.file"%>

	<%-- <%@ include file="page3.file"%>	 --%>

	<!-- JSP�H�@�ӥ]�t��u��div�}�l, ���O������</div>�g�bfooter�̭� -->

	<div class="col-xd-12 col-sm-10  main-page-show">
		<div class="col-sm-10 col-sm-offset-1">
			<span>
				<br>
				<Form action="<%=request.getContextPath()%>/front_end/dateitem/dateitem.do" method="post">
					<input type="hidden" name="action" value="googleMapQuery"> 
					<label for="dateMeetingTime">�]�w���|�ɶ�</label>
  					<input  type="text" id="dateMeetingTime" name="dateMeetingTime" placeholder="�I�ڿ�ܤ��">
  					<input  type="submit" value="�d��">

					<!--   <button class="btn btn-lg btn-warning glyphicon glyphicon-search"> </button> -->
				</Form>

			</span>




			<div id="map_canvas"></div>





		</div>



		<footer>
			<div class="row navbar-fixed-bottom">
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



	
	
	
	
	
	
	<script>

 function initializeMaps() {
	 console.log(typeof window.XMLSerializer==null);

 	var options = {
 		zoom:10,
 		center:{lat: 25.042419,lng:121.541808}
 	}

 	var map = new google.maps.Map(document.getElementById('map_canvas'), options);

	// var bounds = new google.maps.LatLngBounds();



}




initializeMaps();

</script>
	
	
	<script>

$(function(){

	
	$("#dateMeetingTime").datetimepicker({
		format: 'Y-m-d',
		 timepicker:false,		 
		 mindate:0,
	});

	 
});


</script>
	
	
	
	
	

</body>

</html>

