<%@page import="com.restMember.model.RestMember"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ page import="com.restMember.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
   response.setHeader("Cache-Control","no-store"); //HTTP 1.1
   response.setHeader("Pragma","no-cache");        //HTTP 1.0
   response.setDateHeader ("Expires", 0);
 --%>
<% 
 RestMember restMember =(RestMember)session.getAttribute("restMember"); 
%>
<!DOCTYPE html>
<html lang="">
<head>
<%@ include file="/front_end/actFiles/restFrontCss.file" %>	
<title>Title Page</title>
<style type="text/css">
.pwd {
	margin-top: 20px;
}

.login {
	margin-top: 15px;
}

#memIdShow {
   color: red;
}
</style>

</head>
<body background="<%=request.getContextPath()%>/front_end/actFiles/77.jpg"  style= margin:0px;padding:0px;background-size:cover;>
<%@ include file="/front_end/actFiles/restMemberNavBar2.file" %>

	
	
	<div class="container">
		<div class="row">
        	
    
			<div class="col-sm-6 col-sm-offset-3">
				<div class="col-sm-12">
					
	
					<form class="" action="<%=request.getContextPath()%>/restMember/restMemberController"  method="post">
								<c:if test="${not empty errorMsgs}">
									<ul>
										<c:forEach var="message" items="${errorMsgs}">
										
											<li>${message.value}</li>
									</c:forEach>
									
								</ul>
									
							</c:if>	
							
							<c:if test="${not empty hasAUser}">
								${hasAUser}
							</c:if>
				
					<br><br><br><br><br><br><br><br>
					<div class="form-group">
						<label for="restMemId" class="cols-sm-2 control-label">餐廳會員帳號</label><span
							id="restMemIdShow"> 
							

						</span>
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-user fa"
									aria-hidden="true"></i></span> 
									<input type="text" class="form-control" 
									name="restMemId" id="restMemId" 
									value="<%=(restMember==null)?"":restMember.getRestMemId()%>" placeholder="請輸入帳號" required />
							</div>
						</div>
					</div>


					<div class="form-group pwd">
						<label for="memId" class="cols-sm-2 control-label">餐廳會員密碼</label><span
							id="restMemIdShow">
							
							</span>
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-user fa"
									aria-hidden="true"></i></span> <input type="password"
									class="form-control" name="restMemPsw" id="restMemPsw" value="<%=(restMember==null)?"":restMember.getRestMemPsw()%>"
									placeholder="請輸入密碼" required />
							</div>
						</div>
					</div>

					
					<input type="hidden" name="action" value="login">
					<input class="btn btn-primary btn-lg btn-block login-button login"
						type="submit" value="登錄">
					<div>
						
						<br>
						<a href="<%=request.getContextPath()%>/front_end/restMember/restMemberList.jsp" class="btn btn-primary">註冊</a>
					</div>
					
				</form>

			</div>
		</div>
	</div>
</div>
	<a href="<%=request.getContextPath()%>/front_end/index.jsp">
		<div class="text-left" id="fixedbutton-talk">
			<img src="<%=request.getContextPath()%>/front_end/actFiles/33.jpg">
		</div>
	</a>
						
<%@ include file="/front_end/frontEndButtomFixed.file" %>      
	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>