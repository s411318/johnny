<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.order.model.*"%>
<%@ page import="com.member.model.*"%>

<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	
	Member mem = (Member)session.getAttribute("member");
	if(mem == null){
		RequestDispatcher rd = request.getRequestDispatcher("/front_end/member/login.jsp");
		rd.forward(request, response);
		return;}
	OrdService Ord = new OrdService();
	List<Ord> OrdFk = Ord.getOneOrdByFk(mem.getMemNo());
	List<Ord> OrdAll = Ord.getAll();
	session.setAttribute("OrdAll", OrdAll);
	session.setAttribute("OrdFk", OrdFk);
%>

<html>
<head>
<%@ include file="page4.file"%>
</head>
<body>
	<%@ include file="/front_end/frontEndNavBar.file" %>
	<%@ include file="page2.file"%>
	<form action="<%=request.getContextPath()%>/OrdCancel" method="POST">
	<table class="table table-hover" width="200px">
		<tr>
			<th>訂單編號</th>
			<th>訂單日期</th>
			<th>訂單運費</th>
			<th>訂單金額</th>
			<th>會員姓名</th>
			<th>訂單狀態</th>
			<th></th>
		</tr>
				<c:forEach var="ordAll" items="${OrdFk}">
			<tr>
				<td><span>${ordAll.ordNo}</span></td>
				<td><span>${ordAll.ordDate}</span></td>
				<td><span>${ordAll.ordShip}</span></td>
				<td><span>${ordAll.ordTotal}</span></td>
				<td><span>${ordAll.conName}</span></td>
				
				<c:if test="${ordAll.ordStatus ==0}">
				<td><span>未出貨</span></td>
				<td><input type="submit" value="取消訂單"></td>
				<input type="hidden" name="ordNo" value="${ordAll.ordNo}">
				</c:if>
				<c:if test="${ordAll.ordStatus ==1}">
				<td><span>未出貨</span></td>
				<td><input type="submit" value="取消訂單"></td>
				<input type="hidden" name="ordNo" value="${ordAll.ordNo}">
				</c:if>
				<c:if test="${ordAll.ordStatus ==2}">
				<td><span>已出貨</span></td>
				<td></td>
				</c:if>
				<c:if test="${ordAll.ordStatus ==3}">
				<td><span>已結案</span></td>
				<td></td>
				</c:if>
				<c:if test="${ordAll.ordStatus ==4}">
				<td><span>訂單取消</span></td>
				<td></td>
				</c:if>
			</tr>
				</c:forEach>	
	</table>
	</form>
	  <%@ include file="/front_end/frontEndButtomFixed.file" %>
        <script src="https://code.jquery.com/jquery.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>