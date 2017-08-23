<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%
	ProductService prodService = new ProductService();
	List<Product> prodList = prodService.getAllByType("其他");
	session.setAttribute("prodList", prodList);

%>
<html>
<head>
<link href="//netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
<%@ include file="page4.file"%>
</head>
<body>
<%@ include file="pageCount1.file"%>
	<%@ include file="/front_end/frontEndNavBar.file" %>
    <%@ include file="page2.file"%>
    
    
    
    
    </div>
    	
        <div class="row">
        <div>
                <div class="text-center">
                    <%@ include file="pageCount2.file"%>
                </div>
               
            </div>
        </div>
        <%@ include file="/front_end/frontEndButtom.file" %>
        <script src="https://code.jquery.com/jquery.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>