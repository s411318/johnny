<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page import="com.dateitem.model.*"%>
<%@ page import="com.restaurant.model.*" %>
<%@ page import="com.member.model.*" %>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat" %>

<%@ include file="header.file"%>

<jsp:useBean id = "dSvc" scope="page" class="com.dateitem.model.DateItemService" />
<jsp:useBean id = "memSvc" scope="page" class="com.member.model.MemberService" />
<jsp:useBean id = "restSvc" scope="page" class="com.restaurant.model.RestaurantService"/>
<jsp:useBean id = "pSvc" scope="page" class="com.pet.model.PetService"/>

<%
Member member = (Member) session.getAttribute("member");
List<DateItemVO> list = dSvc.findByBuyer_history(member.getMemNo());
pageContext.setAttribute("list",list);
%>




<head><title>�ʶR����</title></head>
<body bgcolor='white'>

<%@ include file="/front_end/frontEndNavBar.file"%>
<%@ include file="sidelist.file"%>

<!-- JSP�H�@�ӥ]�t��u��div�}�l, ���O������</div>�g�bfooter�̭� -->

<div class="col-xd-12 col-sm-8 col-sm-offset-1 main-page-show">

  <c:if test="${not empty itemCanceled}">
  <h1>�z�w�����@�����|�A�g�J�z������!</h1>
   <c:remove var="itemCanceled" scope="request"/>
  </c:if>

					<ul class="list-inline amos">
					<li class="text-sucess"><b><a href="<%=request.getContextPath() %>/front_end/dateitem/dateitem.do?action=list_buyer_future">�i�椤�����|</a></b></li>
					<li class="text-sucess"><b><a href="<%=request.getContextPath() %>/front_end/dateitem/dateitem.do?action=list_buyer_history">�L�h�����|���</a></b></li>
					</ul>
  
     <div class="row">
        <div class="col-sm-12">
            <table class="table table-hover">
                <thead>
                    <tr>
                    	
                        <th>���|</th>
                        <th></th>
                        <th>�ɶ�</th>
                        <th class="text-center">�\�U</th>
                        <th class="text-center"></th>
                        <th></th>
                    </tr>
                </thead>
				<tobdy>
	
<c:forEach var="dateitem" items="${list}" >

<tr>
                        <td class="col-sm-5">
                        <div class="media">
                            <a class="thumbnail pull-left" > <img class="media-object" src="ImgReader?dateItemNo=${dateitem.dateItemNo}&action=dateImg" style="width: 100px; height: 100px;"> </a>
                            <div class="media-body">
                                <h4 class="media-heading"><strong>${dateitem.dateItemTitle}</strong></h4>
                                <h6 class="media-heading"><small> by </small> <a href="#"><small>${memSvc.getOneMember(dateitem.sellerNo).getMemSname()}</small></a></h6>
                            </div>
                        </div></td>
                        <td class="col-sm-1 "></td>
                        <td class="col-sm-1 " style="text-align: center">
                        ${dSvc.getTimeForItem(dateitem.dateMeetingTime)}
                        </td>
                       	
                        <td class="col-sm-3  text-center"><strong>${restSvc.getOneRest(dateitem.restListNo).getRestName()}</strong></td>
                        <td class="col-sm-2 ">
							<c:if test="${dateitem.dateItemStatus==3}">
  							<span class="text-success"><small>�������</small></span>
							</c:if>
							<c:if test="${dateitem.dateItemStatus==2}">
  							<span class="text-warning"><small>�������</small></span>
							</c:if>
                        
                    </tr>
 </c:forEach>

                   <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td class="text-center">�@�����|</td>
                        <td class="text-left"><%=list.size()%>��</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>

<%@ include file="/front_end/frontEndButtomFixed.file"%>





