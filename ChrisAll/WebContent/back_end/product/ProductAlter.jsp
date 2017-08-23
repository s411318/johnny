<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.member.model.*"%>

<html>
<head>
<%@ include file="page1.file" %>
<script src="<%=request.getContextPath() %>/back_end/product/filejs/js/fileinput.js"></script>
<script src="<%=request.getContextPath() %>/back_end/product/filejs/themes/fa/theme.js"></script>
<link href="<%=request.getContextPath() %>/back_end/product/filejs/css/fileinput.min.css" media="all" rel="stylesheet" type="text/css" />
<script src="<%=request.getContextPath() %>/back_end/product/filejs/js/plugins/piexif.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/back_end/product/filejs/js/plugins/sortable.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/back_end/product/filejs/js/plugins/purify.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/back_end/product/filejs/js/fileinput.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/back_end/product/filejs/themes/fa/theme.js"></script>
<script src="<%=request.getContextPath() %>/back_end/product/filejs/js/locales/<lang>.js"></script>
<style type="text/css">
	.mm{
		margin-top:4cm;
		
	}
</style>

</head>
<body>
<%@ include file="/back_end/backEndNavBar.file"%>
<%@ include file="/back_end/backEndLSide.file"%>
<%
int proNo = Integer.valueOf(request.getParameter("proNo"));
ProductService prodDao = new ProductService();
Product prod = (Product)prodDao.getOneProduct(proNo);

%>

<div class="row col-xs-10 col-sm-10 ">
<form action="<%=request.getContextPath() %>/ProductAlter" method="POST" enctype="multipart/form-data" name="form">
<table class="table table-hover mm" style="background-color:#CCEEFF;">
<tr><td>商品編號<br><input type="text" name="proNo" value="<%=proNo%>" readonly></td></tr>
<tr><td>商品名稱<br><input type="text" size="25" name="proName" value="<%=prod.getProdName() %>"></td></tr>
<tr><td>商品描述<br><textarea rows="4" cols="25" name="proDesc" ><%=prod.getProdDescpt() %></textarea>
</td></tr>
<tr><td>商品價格<br><input type="text" name="proPrice" value="<%=prod.getProdPrice() %>"></td></tr>
<tr><td>商品類別<br><input type="text" name="proType" value="<%=prod.getProdType() %>"></td></tr>
<tr><td>商品圖片<input id="input-fa" name="prodimg" type="file" class="file-loading"></td></tr>
<tr><td><div style="padding-left:8cm;">
<input class="btn btn-info btn-pressure btn-sensitive" type="submit" value="送出修改" >

<button class="btn btn-info btn-pressure btn-sensitive" href="<%=request.getContextPath() %>/back_end/product/productManage.jsp">
回商品頁面
</button>

</div>
<input type="hidden" name="action" value="alter">
</td></tr>

</table>
</form>
</div>




</body>
<script>
$("#input-fa").fileinput({
    theme: "fa",
    uploadUrl: "/file-upload-batch/2"
});
</script>
<!-- /.container -->
	<!-- jQuery -->
	<script src="<%=request.getContextPath()%>/front_end/js/jquery.js"></script>
	<!-- Bootstrap Core JavaScript -->
	<script
		src="<%=request.getContextPath()%>/front_end/js/bootstrap.min.js"></script>
	<!-- Script to Activate the Carousel -->

</html>