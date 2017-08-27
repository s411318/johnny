<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page import="com.dateitem.model.*"%>
<%@ page import="com.restaurant.model.*" %>
<%@ page import="com.member.model.*" %>
<%@ page import="java.util.*"%>

<%@ include file="header.file"%>

<jsp:useBean id = "dSvc" scope="page" class="com.dateitem.model.DateItemService" />
<jsp:useBean id = "memSvc" scope="page" class="com.member.model.MemberService" />
<jsp:useBean id = "restSvc" scope="page" class="com.restaurant.model.RestaurantService"/>
<jsp:useBean id = "pSvc" scope="page" class="com.pet.model.PetService"/>

<%
Member member = (Member) session.getAttribute("member");
List<DateItemVO> list = dSvc.findByBuyer_future(member.getMemNo());
pageContext.setAttribute("list",list);
%>

<style>
.adj-button{
margin-right:2px;
margin-left:2px;
}

</style>

<head><title>�ʶR����</title></head>
<body bgcolor='white'>

<%@ include file="/front_end/frontEndNavBar.file"%>
<%@ include file="sidelist.file"%>

<!-- JSP�H�@�ӥ]�t��u��div�}�l, ���O������</div>�g�bfooter�̭� -->

<div class="col-xd-12 col-sm-8 col-sm-offset-1 main-page-show">

					<ul class="list-inline amos">
					<li class="text-sucess"><b><a href="<%=request.getContextPath() %>/front_end/dateitem/dateitem.do?action=list_buyer_future">�i�椤�����|</a></b></li>
					<li class="text-sucess"><b><a href="<%=request.getContextPath() %>/front_end/dateitem/dateitem.do?action=list_buyer_history">�L�h�����|���</a></b></li>
					</ul>
  
  <c:if test="${not empty itemPurchased}">
  <h1>�z�w�ʶR���\�A�s�W�@�����|!</h1>
   <c:remove var="itemPurchased" scope="request"/>
  </c:if>
  
    <c:if test="${not empty itemNotFound}">
  <h1>�ܩ�p!���|�w�g�m���@�B�Q�H�w��, �п�ܨ�L���|!</h1>
   <c:remove var="itemPurchased" scope="request"/>
  </c:if>
	
	

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
                        <td class="col-sm-6">
                        <div class="media">
                            <a class="thumbnail pull-left" > <img class="media-object" src="ImgReader?dateItemNo=${dateitem.dateItemNo}&action=dateImg" style="width: 100px; height: 100px;"> </a>
                            <div class="media-body">
                                <h4 class="media-heading"><strong>${dateitem.dateItemTitle}</strong></h4>
                                <h6 class="media-heading"><small> by </small> <a href="#"><small>${memSvc.getOneMember(dateitem.sellerNo).getMemSname()}</small></a></h6>
                                <span> &nbsp;���A: </span><span class="text-success"><strong>�Y�N�i��</strong></span>
                            </div>
                        </div></td>
                        <td class="col-sm-1 "></td>
                        <td class="col-sm-1 " style="text-align: center">
                        ${dSvc.getTimeForItem(dateitem.dateMeetingTime)}
                        </td>
                       	
                        <td class="col-sm-3  text-center"><strong>${restSvc.getOneRest(dateitem.restListNo).getRestName()}</strong></td>
                        <td class="col-sm-1 ">
			<td><a class="btn btn-warning btn-xs" data-toggle="modal" data-target="#modal-cancel" href="#">�ڭn����</a></td>
			<td><a class="btn btn-default btn-xs" data-toggle="modal" data-target="#appeal${dateitem.dateItemNo}" href="#">�ڭn�ӶD</a></td>
                        </td>
                    </tr>

<!--������modal -->

		<div id="modal-cancel" class="modal fade" role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header text-center">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">�������|�w�w</h4>
      </div>
      <div class="modal-body">
        <p></p>
        <p>�T�w�w����?</p>
      </div>
      <div class="modal-footer">
      <button type="button" class="btn btn-warning pull-right adj-button" data-dismiss="modal">�^�W�@��</button>
        <a type="button" class="btn btn-primary pull-right adj-button" href="<%=request.getContextPath() %>/front_end/dateitem/dateitem.do?action=cancel_date&dateItemNo=${dateitem.dateItemNo}&fromwho=buyer">�T�{����</a>
        
      </div>
    </div>

  </div>
  </div>
  
   <!-- ���|��modal -->

 <div id="appeal${dateitem.dateItemNo}" class="modal fade" role="dialog">
  <div class="modal-dialog">
    <div class="modal-content fortest">
      <div class="modal-header text-center">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">�ڭn�ӶD</h4>
      </div>
      <form class="form-group" action="<%=request.getContextPath() %>/back_end/apprec/dateitemapp.do" method=post enctype="multipart/form-data">
      <div class="modal-body" >
        <div class=" input-group">
        <label class="control-label input-group-addon">�ӶD���D</label>
        <input type="text" class="form-control" name="appTitle" id="appTitle${dateitem.dateItemNo}"/>
        </div>
        <div class="input-group">
        <label class="input-group-addon contol-label">�ӶD���e </label>
        <input type="text" class="form-control" name="appText" id="appText${dateitem.dateItemNo}"/>
        </div>
        <large id="desc1" class="form-text text-muted">�ФW�ǥӶD�Ϥ�</large>
        <input id="appImg${dateitem.dateItemNo}" name="appImage"  type="file" data-show-upload="false" data-show-caption="true"/>
      </div>
      <div class="modal-footer">
      <button id="" type="button" class="btn btn-warning pull-right adj-button" data-dismiss="modal">�^�W�@��</button>
       <input type="submit" id="sendRep${dateitem.dateItemNo}" class="check btn btn-primary" style="margin-right:3px;" value="�T�w�e�X"/>
       <input type="hidden" name="dateItemNo" value="${dateitem.dateItemNo}"/>
       <input type="hidden" name="action" value="insert"/>
        
      </div>
      </form>
    </div>

  </div>
  </div>
  
<!-- ================================================�������s��modal,�����bforeach��=====		 -->		
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


<%@ include file="/front_end/frontEndButtomFixed.file"%>





