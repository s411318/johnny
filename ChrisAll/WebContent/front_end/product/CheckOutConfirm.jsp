<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.member.model.*"%>
<%request.setCharacterEncoding("UTF-8");%>  
<%response.setCharacterEncoding("UTF-8");%> 



<html>
<head>
<%@ include file="page4.file" %>
</head>
<body>
<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	Member mem=(Member)session.getAttribute("member");

%>
<%@ include file="/front_end/frontEndNavBar.file" %>
<%@ include file="page2.file" %>


<div class="container">
      <h2>確認你的訂單資訊</h2>
      <form class="col-sm-8" action="<%=request.getContextPath()%>/OrderInsert" method="POST">
        <div class="form-group">
          <label for="name">name:</label>
          <input type="text" class="form-control" id="name" value="<%= mem.getMemName()%>">
        </div>
        <div class="form-group">
          <label for="addr">Address:</label>
          <input type="text" class="form-control" id="addr" value="<%= mem.getMemAddress()%>">
        </div>
        <div class="form-group">
          <label for="tel">Telephone:</label>
          <input type="text" class="form-control" id="tel" value="<%= mem.getMemPhone()%>">
        </div>
        <div class="form-group">
          <label for="tel">TotalPrice:</label>
          <input type="text" class="form-control" id="tel" value="<%= session.getAttribute("amount")%>">
        </div>
        <button type="submit" class="btn btn-default">確定付款</button>
        
      </form>
    </div>



<%@ include file="/front_end/frontEndButtom.file" %>
        <script src="https://code.jquery.com/jquery.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>
</html>