<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.member.model.*"%>
<%request.setCharacterEncoding("UTF-8");%>  
<%response.setCharacterEncoding("UTF-8");%> 
<%Member mem = (Member)session.getAttribute("member"); %>
<html>
<head>
<head>
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<%@ include file="page4.file" %>		
		<link rel="stylesheet" type="text/css" href="css/default.css" />
		<!-- custom demo style -->
		<link rel="stylesheet" type="text/css" href="css/demo.css" />
		<link rel="stylesheet" href="css/card.css">
		<style>
        .demo-container {
            width: 350px;
            margin: 50px auto;
        }

        form {
            margin: 0px;
        }
        input {
            width: 200px;
            margin: 10px auto;
            display: block;
        }

    	</style>
	</head>
	<body>
	<%@ include file="/front_end/frontEndNavBar.file" %>
<%@ include file="page2.file" %>
		
		<div class="container">
			<!-- Top Navigation -->
<!-- 			<font size="8" style="margin-left:0px;font-family:DFKai-sb;">PETYM儲值頁面</font> -->
	<div class="row"  id="show">
		
        <div class="col-md-4 col-md-offset-3">
			<div class="main clearfix" style="padding-top:0px;">
			   
				<div class="demo-container">
			        <div class="card-wrapper"></div>

			        <div class="form-container active" >
			            <form action="<%=request.getContextPath() %>/chargePage" method="POST">
			            		 <font color="red"> 
                                   <c:if test="${not empty errorMsgs}">
                                     <%=request.getAttribute("errorMsgs") %>
                                   </c:if>
                                 </font>
			               	 卡號<br><input id="num" placeholder="Card number" class="btn btn-default" style="margin-left:0px;width:350px;" type="text" name="number" required><br>
			                                                         姓名<br><input id="name" placeholder="Full name"  class="btn btn-default" style="margin-left:0px;width:350px;" type="text" name="name" required><br>
			                                                        有效期限<br><input id="date" placeholder="MM/YY" class="btn btn-default" style="margin-left:0px;width:350px;" type="text" name="expiry" required><br>
			                CVC碼<br><input id="cvc" placeholder="CVC" class="btn btn-default" style="margin-left:0px;width:350px;" type="text" name="cvc" required>
			               <div class="row">
			 				  <div class="col-md-6 col-sm-6 col-xs-6 pad-adjust" style="margin-left:0cm;margin-top:5px;width:350px;">
                  			  <input type="hidden" name="action" value="payment"> 
                  		                    儲值金額<br><input name="chargeNum" class="btn btn-default" style="width:350px;" id="chargeNum" type="text" name="chargeNum" required placeholder="請輸入金額"/>
                  			  <br>
                  			  <input type="submit" class="btn btn-warning btn-block" style="width:350px;" value="支付" />
                  			  <input type="button" value="" style="margin-left:0cm;width:1px;"onclick="insert()">
             				  </div>
             				</div>
             				
			            </form>
			            
			            <input type="hidden" value="<%=mem.getMemName() %>" id="memname"> 
			        </div>
			    </div>
			</div>
		</div>
	</div>
			 
			
			
		</div>
		 
		<!-- /container -->
		<script src="js/jquery.min.js"></script>
	    <script src="js/card.js"></script>
	    <script>
	        $('.active form').card({
	            container: $('.card-wrapper')
	        })
	    </script>
	    <script type="text/javascript">
	    function insert() {
	        var one = document.getElementById("name");
	        var memname = document.getElementById("memname");
	        one.value=memname.value; 
	    }
</script>
	<%@ include file="/front_end/frontEndButtom.file" %>	
</body>
</html>