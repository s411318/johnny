<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page import="com.dateitem.model.*"%>
<%@ page import="com.restaurant.model.*" %>
<%@ page import="com.member.model.*" %>
<%@ page import="java.util.*"%>

<style>

@font-face {
    font-family: DJB;
    src: url(../fonts/DJB.ttf);
    font-weight: normal;
    font-style: normal;
}

#fixedbutton-talk {
  position: fixed;
  bottom: 3%;
  right: 5%;
}

blockquote>p {
position:relative;
left:1rem;
padding-right:4rem;
}
.fortest button{
margin-left:1%;
float:right;
}
.fortest .a{
margin-left:1%;
float:right;
}
.btn-circle {
  display:block;
  height: 20px;
  width: 20px;
  border-radius: 50%;
  border: 1px solid grey;
  
}

.syotimer{
	font-family: 'DJB';
	padding:3px;
    text-align: center;
 	right:10px;
    margin: 0px auto 0;
    padding: 0 0 10px;
 
/*     border-bottom: 2px solid #80a3ca; */
}
.syotimer .syotimer-cell{
    display: inline-block;
    margin: 0 5px;
 
    width: 100px;
/*      background: url(../images/timer.png) no-repeat 0 0;  */
	background-color:#6fffd5;

}
.syotimer .syotimer-cell .syotimer-cell_value{
    font-size: 50px;
    color: #80a3ca;
 	font-family: 'DJB';
    height: 100px;
    line-height: 100px;
 
    margin: 0 0 5px;
}
.syotimer .syotimer-cell {
    font-family: 'DJB';
    font-size: 20px;
    text-transform: uppercase;
}





.select-style {
    padding: 0;
    margin: 0;
    border: 1px solid #ccc;
    width: 200px;
    border-radius: 3px;
    overflow: hidden;
    background-color: #fff;

    background: #fff url("http://www.scottgood.com/jsg/blog.nsf/images/arrowdown.gif") no-repeat 90% 50%;
}

.select-style select {
    padding: 5px 8px;
    width: 130%;
    border: none;
    box-shadow: none;
    background-color: transparent;
    background-image: none;
    -webkit-appearance: none;
       -moz-appearance: none;
            appearance: none;
}

.select-style select:focus {
    outline: none;
}




</style>

<%@ include file="header.file"%>




<jsp:useBean id = "dSvc" scope="page" class="com.dateitem.model.DateItemService" />
<jsp:useBean id = "memSvc" scope="page" class="com.member.model.MemberService" />
<jsp:useBean id = "restSvc" scope="page" class="com.restaurant.model.RestaurantService"/>
<jsp:useBean id = "pSvc" scope="page" class="com.pet.model.PetService"/>
<jsp:useBean id = "msgSvc" scope="page" class="com.msg.model.MsgService"/>
<%
	Member member = (Member) session.getAttribute("member");
	try{
	int memNo= member.getMemNo();
	}catch(Exception e){		};
    List<DateItemVO> list = dSvc.getAllWithOutImg();
    pageContext.setAttribute("list",list);
%>





<head><title>���|����</title>
<%-- <link href="<%=request.getContextPath() %>/front_end/css/modern-business.css" rel="stylesheet" type="text/css"> --%>
</head>
<body bgcolor='white'>



<%@ include file="/front_end/frontEndNavBar.file"%>
<%@ include file="sidelist.file"%>

<%-- <%@ include file="page3.file"%>	 --%>

<!-- JSP�H�@�ӥ]�t��u��div�}�l, ���O������</div>�g�bfooter�̭� -->

<div class="col-xd-12 col-sm-10  main-page-show">
<div class="row">

			<div class="col-xd-12 col-sm-3"> 
			<a href="#" class="list-group-item"
				data-toggle="collapse" data-target="#search" data-parent="#menu">���|�ӫ~�d��
					<span class="glyphicon glyphicon-triangle-bottom pull-right"></span>
			</a>
				<div id="search" class="sublinks collapse">
					<a href="#" class="list-group-item small" data-toggle="modal"
						data-target="#searchSpec">�H����j�M </a>		
					<a class="list-group-item small"
						href='<%=request.getContextPath()%>/front_end/dateitem/googleMapQuery.jsp'>�H�a�Ϸj�M</a>
				</div>
			</div>

			<div class="col-xd-12 col-sm-2"> 
				<button class="btn btn-lg btn-default" id="button1" value="showppl">�H�D�H��ܬ��|</button>
			</div>


	


			<!-- �ƦX�d��Modal content-->
			<div class="modal fade" id="searchSpec" role="dialog">
				<div class="modal-dialog">

					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">�d�߬��|�ӫ~</h4>
						</div>
						<div class="modal-body">

							<Form
								action="<%=request.getContextPath()%>/front_end/dateitem/dateitem.do"
								method="post">
								<table>
									<input type="hidden" name="action"
										value="listDItems_ByCompositeQuery">

									<tr>
										<td class="title">���|���</td>
										<td><input type="text" id="dateMeetingTime"
											name="dateMeetingTime"></td>
									</tr>

									<tr>
										<td class="title">�|���ʺ�</td>
										<td><input type="text" id="memSname"
											name="memSname"></td>
									</tr>
									
									<tr>
										<td class="title">�d���m�W</td>
										<td><input type="text" id="petName"
											name="petName"></td>
									</tr>
									

									<tr>
										<td class="title">�|���ʧO:</td>
										<td>
											<div class="select-style">
												<select class="filter" name="memGender">
													<option value="" disabled selected>�п�ܥD�H�ʧO</option>
													<option value="">�ҥi</option>
													<option value="0">�k</option>
													<option value="1">�k</option>
													<option value="2">���@�z�S</option>
												</select>
											</div>
										</td>
									</tr>

									<tr>
										<td class="title">�d���ʧO</td>
										<td>
											<div class="select-style">
												<select class="filter" name="petKind">
													<option value="" disabled selected>�п���d��</option>
													<option value="">�ҥi</option>
													<option value="��">��</option>
													<option value="��">��</option>
													<option value="��L">��L</option>
												</select>
											</div>
										</td>
									</tr>

								</table>

								<input type="submit" value="�d��" class="btn btn-primary">

							</Form>
						</div>


					</div>

				</div>
			</div>


 </div>
 
<%@ include file="pages/page1.file" %> 
  <div> 
  <c:forEach var="dateitem" items="${list}" varStatus="s" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
          <c:if test="${s.index%3==0}"> 
			<div class="row">
			</c:if>
          
          <div class="col-sm-4 ">
            <div class="bg-color">
            <div class="card hovercard">
                <div class="cardheader" style="background-image:url('ImgReader?dateItemNo=${dateitem.dateItemNo}&action=dateImg');">

                <input  type="hidden" value="${dateitem.sellerNo}">
                <input class="no1" type="hidden" value="${dateitem.dateItemNo}">

                </div>
                <div class="avatar">
                	 <input  type="hidden" value="${dateitem.sellerNo}">
                    <img class="img1" src="ImgReader?sellerNo=${dateitem.sellerNo}&action=memImg">
                    <input  type="hidden" value="${dateitem.dateItemNo}">
  
                </div>
                <div class="info">
                    <div class="title dateDes">
                        <a class="dateDes" target="_blank" href="">${dateitem.dateItemTitle}</a>
                    </div>
                    <div class="desc">${memSvc.getOneMember(dateitem.sellerNo).getMemSname()}</div>
                    <div class="desc">${dSvc.getTimeForItem(dateitem.dateMeetingTime)}</div>
                    <div class="desc">${dateitem.dateItemLocate}</div>
                  
                </div>
                <div class="bottom">
                    <a class="btn btn-info"  data-toggle="modal" data-target="#modal-detail${dateitem.dateItemNo}" href="">
                        �Ա�
                    </a>
                </div>
                </div>
            </div>

        </div>
 
<!--  �ӫ~���Ӫ����� -->

<div id="modal-detail${dateitem.dateItemNo}" class="modal fade" role="dialog">
  <div class="modal-dialog modal-lg">


    <!-- Modal content-->
    <div class="modal-content">
    <div id="simple-timer${dateItem.dateItemNo}" class="simple-timer"  style="color:black;" ></div>
    <input type="hidden" class="stime" value="${dateitem.dateMeetingTime}"/>
    
      <div class="modal-header">
  
  <div class ="main-container">

<div class=" highlight">
 <a href="" 
type="button" class = "btn btn-xs btn-basic pull-left btn-circle" data-toggle="modal" data-target="#rep${dateitem.dateItemNo}" >���|</a>

<h1><b>${dateitem.dateItemTitle}</b></h1>

	<div class="row">
  
  
  
		<div id="cc">
        <img id="memphoto-detail${dateitem.dateItemNo}"src ="ImgReader?sellerNo=${dateitem.sellerNo}&action=memImg" />
      
        </div>
        <ul>
        
        
            <li> <h2><a href="" style="color:white;">
            ${memSvc.getOneMember(dateitem.sellerNo).getMemSname()} && ${pSvc.getOnePet(dateitem.petNo).getPetName()}
            </a></h2></li>
            <li>�\�U:${restSvc.getOneRest(dateitem.restListNo).getRestName()} - �Ҧb�a:<a href="" class="btn btn-xs btn-basic">�ݦa��</a></li>
            <li>${dSvc.getTimeForItem(dateitem.dateMeetingTime)}</li>
            <li>�ѥ[�H�ƤW��:${dateitem.dateItemPeople}�H  - 
            	�d���D�H���:
            	<c:if test="${dateitem.hasMate==true}">��</c:if>
            	<c:if test="${dateitem.hasMate==false}">�S��</c:if>           	
            	</li>
            <li><h3 class="cost"><span class="glyphicon glyphicon-usd"></span> ${dateitem.dateItemPrice} </h3></li>
           
        </ul>
        
        <div class="row">
        <blockquote >
		<p >${dateitem.dateItemText}</p>
<!-- 		<footer> -->
<%-- 		 <cite>�H��:${dateitem.dateItemViewer}</cite> --%>
<!-- 		</footer> -->
		</blockquote>
        
        </div>
        </div>        
        <button class = "btn btn-warning" data-dismiss="modal">�^�W�@��</button>
        
<!--         //����ۤv����R�ۤv�����| -->
		<c:if test="${ member.getMemNo()!=null}">
       <c:if test="${dateitem.sellerNo!=member.getMemNo()}" > 
      
       <a href="" 
       type="button" onclick="goajax('${dateitem.dateItemNo}')" data-toggle="modal" data-target="#confirm${dateitem.dateItemNo}" class="check btn btn-primary">�w��</a> 
       </c:if>
       </c:if>
       <input type="hidden" value="${dateItem.dateItemPrice}"/>
        
    </div>
    
    <BR>
	</div>
	
	 
</div>
</div>
      </div>
    </div>

<!-- �T�{�I�ڻP�ˬd�x��modal -->

 <div id="confirm${dateitem.dateItemNo}" class="modal fade" role="dialog">
  <div class="modal-dialog">
    <div class="modal-content fortest">
      <div class="modal-header text-center">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">�w�����|</h4>
      </div>
      <div class="modal-body" >
        <p></p>
        <p id="textshow${dateitem.dateItemNo}">����$${dateitem.dateItemPrice}, �T�w�w�������|?</p>
      </div>
      <div class="modal-footer">
      <button id="" type="button" class="btn btn-warning" data-dismiss="modal">�^�W�@��</button>
       <a href="<%=request.getContextPath() %>/front_end/dateitem/dateitem.do?action=buy_date&dateItemNo=${dateitem.dateItemNo}" 
       type="button" id="checkPrice${dateitem.dateItemNo}" class="check btn btn-primary">�T�w���|</a>
       <input type="hidden" value="${dateItem.dateItemPrice}"/>
        
      </div>
    </div>

  </div>
  </div>
 
 <!-- ���|��modal -->

 <div id="rep${dateitem.dateItemNo}" class="modal fade" role="dialog">
  <div class="modal-dialog">
    <div class="modal-content fortest">
      <div class="modal-header text-center">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">���|</h4>
      </div>
      <form action="<%=request.getContextPath() %>/back_end/apprec/dateitemrep.do" method="post" >
      <div class="modal-body" >
        <p></p>
        
		
        <input type="text" class="form-control" name="repText" id="repText${dateitem.dateItemNo}"/>
        <p>�п�J���|��] </p>
      </div>
      <div class="modal-footer">
      <button id="" type="button" class="btn btn-warning" data-dismiss="modal">�^�W�@��</button>
       <input type="submit" id="sendRep${dateitem.dateItemNo}" class="check btn btn-primary" value="�T�w�e�X">
       <input type="hidden" name="dateItemNo" value="${dateitem.dateItemNo}"/>
       
        <input type="hidden" name="whichPage" value="<%=whichPage %>"/>
        <input type="hidden" name="action" value="insert"/>
      </div>
      </form>
    </div>

  </div>
  </div>
 
 
 			<c:if test="${s.index%3==2}"> 
				</div>
			 </c:if>
 
 </c:forEach>

 </div>       

<%@ include file="pages/pageLatest.file" %> 

 </div>  
    

 
<%@ include file="/front_end/frontEndButtom.file"%>
<%@ include file="chat.file"%>


</div> 

<script>
<!--�ˬd�x�� -->
function goajax(dateItemNo){
// 		alert(dateItemNo);
	    $.ajax({ 
	        url:  'dateitem.do?action=checkCharge',
	        type: 'POST',
	        data: { dateItemNo:dateItemNo,
			},
	        success: function(response){
// 	        	alert('success');
	        	if(response.length<4){
// 	        	alert('enough');					
	        	}else{
	        		
// 	 =================�l�B����,����s�אּ�x��=====================
// 	        		alert('not enough');
  	        		$('#textshow'+dateItemNo).html('<h1>�l�B�������x��<h1>');	
  	        		$('#confirm'+dateItemNo).modal('show');
  	        		$('#checkPrice'+dateItemNo).text('�e���x��');
  	        		$('#checkPrice'+dateItemNo).attr("href", "<%=request.getContextPath() %>/front_end/charge/chargePage.jsp?from=dateitem")
	        	}
	        },
	        error: function(){
	            alert("error");
	        }  
	    });  	
	};
$(document).ready(function(){

	$('.simple-timer').each(function(){
		var timeStr=$(this).next('input').val().trim();
		
		var yearStr = parseInt(timeStr.substring(0,4));
	
		var monthStr = parseInt(timeStr.substring(5,7));
		
		var dayStr = parseInt(timeStr.substring(8,10));
		
		var hourStr = parseInt(timeStr.substring(11,13));
		var minuteStr= parseInt(timeStr.substring(14,16));
		$(this).syotimer({
		    year: yearStr,
		    month: monthStr,
		    day: dayStr,
		    hour: hourStr,
		    minute: minuteStr,
		    effectType: 'opacity',
		    lang: 'eng'
		});		
	});



// 	$('#simple-timer').syotimer({
// 	    year: 2017,
// 	    month: 8,
// 	    day: 31,
// 	    hour: 20,
// 	    minute: 48,
// 	    effectType: 'opacity',
// 	});		
	
	
	
	
		var but1 = $('#button1');
		but1.click(function() {
			if(this.value == 'showppl'){
			but1.val('showpet');
			but1.html('�H�d����ܬ��|')
			
		
			$.ajax({
				url : 'dateitem.do',
				data : {
					action : this.value,
				},
				type : 'POST',
				error : function(xhr) {
					alert('Ajax request �o�Ϳ��~');
				},
				success : function(result) {
// 					alert('sucess1');
					$('.cardheader').each(function(index){
// 						$(this).attr('style', "background-image: url("'ImgReader?sellerNo='+$(this).next('input').val()+'&action=memImg'")");
						$(this).css('background-image','url("ImgReader?sellerNo='+$(this).find('input').val()+'&action=memImg")');
					});
					$('.img1').each(function(index){
						$(this).attr('src','ImgReader?dateItemNo='+$(this).next('input').val()+'&action=dateImg');
					});
// 					$('.avatar').find('img').attr('src','ImgReader?dateItemNo=${dateitem.dateItemNo}&action=dateImg');
				}
			});
			this.value = "showpet";
			
			}else if(this.value == 'showpet'){
				but1.val('showppl');
				but1.html('�H�D�H��ܬ��|')
				$.ajax({
					url : 'dateitem.do',
					data : {
						action : this.value,
					},
					type : 'POST',
					error : function(xhr) {
						alert('Ajax request �o�Ϳ��~');
					},
					success : function(result) {
// 						alert('sucess2');
						$('.cardheader').each(function(index){
//	 						$(this).attr('style', "background-image: url("'ImgReader?sellerNo='+$(this).next('input').val()+'&action=memImg'")");
							$(this).css('background-image','url("ImgReader?dateItemNo='+$(this).find('.no1').val()+'&action=dateImg")');
						});
						$('.img1').each(function(index){
							$(this).attr('src','ImgReader?sellerNo='+$(this).prev('input').val()+'&action=memImg');
						});
//	 					$('.avatar').find('img').attr('src','ImgReader?dateItemNo=${dateitem.dateItemNo}&action=dateImg');
					}
				});
				this.value = "showppl";
			} 
			
			
			
			
		});
});


</script>
		<script>
			$(function() {

				$("#dateMeetingTime").datetimepicker({
					format : 'Y-m-d',
					timepicker : false,
					mindate : 0,
				});

			});
		</script>
</body>

</html>
