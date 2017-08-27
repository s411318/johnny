<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.restMember.model.RestMember"%>
<%@page import="java.util.*"%>
<%@page import="com.activity.model.ActivityService"%>
<%@ page import="com.activity.model.Activity"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%
	if(session.getAttribute("restMember")==null){
		request.getRequestDispatcher("/front_end/restMember/restMemberLogin.jsp").forward(request, response);
	}
	RestMember restMember = (RestMember)session.getAttribute("restMember");
	Activity activity = (Activity)request.getAttribute("activity");
	
// 	session.setAttribute("activity", activity);
	
	ActivityService activityService = new ActivityService();
	List<Activity> activitiyList = activityService.getAllById(restMember.getRestMemId());
	request.setAttribute("activitiyList", activitiyList);
	
			
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
	String actDateMin  = simpleDateFormat.format(new Date());
%>
<!DOCTYPE html>
<html lang="">

<head>

    <script src="https://code.jquery.com/jquery.js"></script> 	
    <!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
	
	 
	
    <style type="text/css">
			.btn123{
				margin-top: 10px;
			}
			
	</style>  
</head>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<body>

    <%@ include file="/front_end/actFiles/restMemberNavBar.file" %>
	<%@ include file="/front_end/actFiles/overflow.file" %>
	
	
    <div class="container-fluid">
        <div class="row">
        	<%@ include file="/front_end/actFiles/restMemberList.file" %>
        <div class="col-xs-12 col-sm-8">       
   
    <div class="panel-group col-sm-offset-2 col-sm-8">
    
    
		<h5 class=" page-header text-right">�ثe��m:�޲z�ڪ�����</h5>
		
<!--      �ثe�^�a(���ƥ�)varStatus="activitiyE"  ���j step="1"   �q0�}�l${activitiyE.index}    �q1�}�l${activitiyE.count} -->
		
			<c:forEach var="activity" items="${activitiyList}" varStatus="activitiyE" step="1">
			 <div class="panel panel-default">
		       <div class="panel-heading">
		       	
				
						<div class="row">
							<div class="col-xs-12 col-sm-6">
								<h3 class="panel-title">���ʦW��:${activity.actName}</h3>
								<c:if test="${not empty updateErr }">
									${updateErr}
								</c:if>
							</div>
							<div class="col-xs-12 col-sm-6 text-right ">
								
									<footer>
										<c:if test="${activity.actStatus=='0'}"><font color="red">���ʵo�_�ݼf��</font></c:if>
										<c:if test="${activity.actStatus=='1'}"><font color="red">�f�֥��q�L</font></c:if>
										<c:if test="${activity.actStatus=='2'}"><font color="green">�f�ֳq�L</font></c:if>	
										<c:if test="${activity.actStatus=='3'}"><font color="red">�\�U��������</font></c:if>
									</footer>
								
							</div>
						</div>
					
		       </div>
		       
			       <div class="col-xs-12 col-sm-4">
						<img src="<%=request.getContextPath() %>/activity/DBGifReader5?actNo=${activity.actNo}" class="img-responsive" >
					</div>
				
					
					
					<div class="col-xs-12 col-sm-8 ">
						<textarea class="overflow form-control" rows="8" cols="40"  style="resize:none;border:0px;background-color:white" readonly>${activity.actContent}</textarea>
						
					</div>
					
					
					
				
			<form method="post" action="<%=request.getContextPath()%>/activity/activityController" enctype="multipart/form-data">
			
		       <div class="row">
					<div class="col-xs-12 col-sm-12">
						<div class="panel-body text-right" >
		         			<a href='#${activity.actNo}' data-toggle="modal" class="btn btn-primary">���ʸԱ�</a>
								<div class="modal fade" id="${activity.actNo }">
									<div class="modal-dialog modal-lg">
										<div class="modal-content">
											
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title text-center">���ʸԱ�</h4>
					</div>
			
				<div class="modal-body">
					<div class="container">
						<div class="row">
							<div class="col-sm-3">
									
											<script>
												$(function() {
													
													$("#actDate${activitiyE.index}").datetimepicker({
													format: 'Y-m-d',
													 minDate:'+1970/01/02',
													 allowTimes:[
											'11:00','11:30', '12:00', '12:30','13:00', '13:30','14:00','14:30', '15:00', '15:30', '16:00', '16:30', '17:00', '17:30', '18:00', '18:30', '19:00',
													  '19:30', '20:00', '21:00', '22:00', '23:00'
													 ],
													 defaultDate: '17/09/01',
											// 		 step: 30 
													timepicker:false,
													             });
													             
													             
													 $("#actFDate${activitiyE.index}").datetimepicker({
													format: 'Y-m-d',
													 minDate:'+1970/01/02',
													 allowTimes:[
											'11:00','11:30', '12:00', '12:30','13:00', '13:30','14:00','14:30', '15:00', '15:30', '16:00', '16:30', '17:00', '17:30', '18:00', '18:30', '19:00',
													  '19:30', '20:00', '21:00', '22:00', '23:00'
													 ],
													 defaultDate: '17/09/01',
											// 		 step: 30 
														timepicker:false,
													             });            	
													
													
										       });
											</script> 
									
											
											<script>
					
							
												var openFile${activitiyE.index} = function(e) {
												    var input = e.target;		
												    var reader = new FileReader();		    
												    reader.onload = function(){
													      var dateURL = reader.result;	      
														  var output = document.getElementById('outputIMG${activitiyE.index}');			    
														  output.src = dateURL;			    
												    };		    
												    reader.readAsDataURL(input.files[0]);		   
												};
												
											</script>
											
											<input type="hidden" name="actNo" value="${activity.actNo}">
											<input type="hidden" name="restMemId" value="${restMember.restMemId }">
											<input type="hidden" name="actStatus" value="${activity.actStatus }">
									
											<div class="form-group">
												<div class="col-xs-12 col-sm-12">
													<img src="<%=request.getContextPath()%>/activity/DBGifReader5?actNo=${activity.actNo}" 
													class="img-circle" id="outputIMG${activitiyE.index}" height="200px" width="100%" style="margin-top:50px;">
													
													<input type="file" name="actInitImg" class="form-control"
													 onchange="openFile${activitiyE.index}(event)">
													
												</div>
											</div>
							</div>	
								
							<div class="col-sm-6">		
											<div class="form-group">
												<label class="col-sm-3 control-label">
													���ʦW��
												</label>
												<div class="col-sm-9">
													<input type="text" name="actName" class="form-control" value="${activity.actName }">	
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-sm-3 control-label">
													���ʤ��e
												</label>
												<div class="col-sm-9">
													<textarea name="actContent" rows="3" cols="47" class="overflow form-control" style="resize:none">${activity.actContent }</textarea>	
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-sm-3 control-label">
													���ʤ��
												</label>
												<div class="col-sm-9">
													<input type="text" name="actDate" id="actDate${activitiyE.index}" class="form-control" value="${activity.actDate }"
															min="<%=actDateMin %>">	
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-sm-3 control-label">
													���W�I����
												</label>
												<div class="col-sm-9">
													<input type="text" name="actFDate" id="actFDate${activitiyE.index}" class="form-control" value="${activity.actFDate}"
													   min="<%=actDateMin %>">	
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-sm-3 control-label">
													�H�ƤW��
												</label>
												<div class="col-sm-9">
													<input type="text" name="actULimit" class="form-control" value="${activity.actULimit }">	
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-sm-3 control-label">
													�H�ƤU��
												</label>
												<div class="col-sm-9">
													<input type="text" name="actLLimit" class="form-control" value="${activity.actLLimit }">	
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-sm-3 control-label">
													��������
												</label>
												<div class="col-sm-9">
													<select id="testModal" name="actKind" class="form-control">
														
															<option value="0" ${(activity.actKind=="0")?'selected':''}>��</option>
									  						<option value="1" ${(activity.actKind=="1")?'selected':''}>��</option>
									  						<option value="2" ${(activity.actKind=="2")?'selected':''}>��L</option>
														
													</select>	
												</div>
											</div>
											
											<div class="form-group" id="aKindModal" style='display:'>
												<label class="col-sm-3 control-label">
													��L�d������
												</label>
												<div class="col-sm-9">
													<input type="text" name="actAnotherKind" class="form-control" 
													value="${activity.actAnotherKind}">	
												</div>
											</div>

											
											<input type="hidden" name="action" value="updateActivity">
									</div>
								</div>		
							</div>
						</div>
								
				
			<div class="modal-footer">
				<button type="submit" class="btn btn-primary">�����</button>
				<button type="button" class="btn btn-warning" data-dismiss="modal">����</button>
				
			</div>
												
						</div>
					</div>
				</div>	         			
		   </div>
		</div>	
	
				
				
				
			</form>	
			
			
				
		     </div>
		     
			</c:forEach>
        
		       </div>
		     </div>
        </div>
    </div>



    
<%@ include file="/front_end/frontEndButtomFixed.file" %>        
   
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    
</body>

</html>