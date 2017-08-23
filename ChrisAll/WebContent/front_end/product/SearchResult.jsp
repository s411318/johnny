<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>


<html>
<head>
<link href="//netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
<%@ include file="page4.file"%>
<style type="text/css">
.cc{
	position:fixed;
}
</style>
</head>
<body>
<%@ include file="/front_end/frontEndNavBar.file" %>
<%@ include file="page2.file"%>
	<c:forEach var="searchProd" items="${searchProd}">
		<c:if test="${searchProd.prodState == 0}">
		<div class="adj">

			<div class="block col-sm-3 col-sm-offset-1 item-margin">
				<div class="product">
					<img
						src="http://localhost:8081/ChrisAll/ProductImage?prodno=${searchProd.prodNo}" class="img-responsive" alt="Cinque Terre">
					<div class="buttons">
						<form action="<%=request.getContextPath()%>/ShoppingCar.do" method="POST">
							<a class="buy btn-large" href="#">
							<i class="icon-shopping-cart"></i>
							<input type="submit" class="btn btn-primary" value="放入購物車"></a> 
							<input type="hidden" name="name" value="${searchProd.prodName}"> 
							<input type="hidden" name="price" value="${searchProd.prodPrice}">
							<input type="hidden" name="prodno" value="${searchProd.prodNo}">
							<input type="hidden" name="action" value="add">
						</form>

						<form action="<%=request.getContextPath()%>/ShoppingDetail.do" method="POST">
							<a class="preview btn-large"><i class="icon-eye-open"></i><input type="submit" class="btn btn-primary" value="商品明細"></a> 
							<input type="hidden" name="prodno" value="${searchProd.prodNo}">
						</form>
					</div>
				</div>
				<div class="info">
					<h4>${searchProd.prodName}</h4>
					 
					<span class="price">${searchProd.prodPrice}</span>
					<form action="<%=request.getContextPath()%>/ShoppingDetail.do"
						method="POST">
						<a class="btn pull-right" href="#"><i
							class="icon-shopping-cart"></i><input type="submit"
							class="btn btn-primary" value="馬上購買"></a> <input
							type="hidden" name="prodno" value="${searchProd.prodNo}">
					</form>
				</div>
				
			</div>
		</div>
		</c:if>
	
	</c:forEach>
	 <%@ include file="/front_end/frontEndButtom.file" %>
        <script src="https://code.jquery.com/jquery.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>