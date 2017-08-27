<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page import="com.dateitem.model.*"%>
<%@ page import="com.restaurant.model.*" %>
<%@ page import="com.member.model.*" %>
<%@ page import="java.util.*"%>

<style>
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
.chatlist{ 
 text-overflow: ellipsis; 
 overflow: hidden; 
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
	int memNo= member.getMemNo();
	List<DateItemVO> list = dSvc.getAllItems();
    pageContext.setAttribute("list",list);
%>





<head><title>���|����</title></head>
<body bgcolor='white'>



<%@ include file="nav.file"%>
<%@ include file="sidelist.file"%>

<%-- <%@ include file="page3.file"%>	 --%>

<!-- JSP�H�@�ӥ]�t��u��div�}�l, ���O������</div>�g�bfooter�̭� -->


<%
try{
List<DateItemVO> chatList = dSvc.getAllForChats(memNo); 
pageContext.setAttribute("chatList",chatList);
}catch(Exception e ){};%>

<c:if test="${not empty chatlist}">
<div class="popup-box chat-popup" id="qnimate">
              <div class="popup-head">
                <div id="chat-title" class="popup-head-left pull-left"><img id="otherpic" src=""></div>
                      <div class="popup-head-right pull-right">

                        <div class="btn-group dropdown">
                        			
                                      <button class="chat-header-button dropdown-toggle" data-toggle="dropdown" type="button">
                                       <i class="glyphicon glyphicon-cog"></i> </button>
                                      <ul role="menu" class="dropdown-menu pull-right">
                                                     
                                      <c:forEach var="chat" items="${chatList}">
                                      <li><a class="chatlist" onclick="changeroom(${chat.dateItemNo})">${chat.dateItemTitle}</a></li>
                                      <li class="divider"></li>
                                      </c:forEach>
                                      </ul>
                        </div>
                        
                        <button data-widget="remove" id="removeClass" class="chat-header-button pull-right" type="button"><i class="glyphicon glyphicon-arrow-left"></i></button>
                        
                      </div>
              </div>


<!-- =============
============= -->

            <div id="scroll-area" class="popup-messages">
            
            
            
            <div id="append" class="direct-chat-messages">
 <!-- =============
============= -->                   
                    
                    <div class="direct-chat-msg doted-border">
                      <div class="direct-chat-info clearfix">
                        <span class="direct-chat-name-left ">PetYM���ɴ���</span>
                      </div>
                     
                      <div class="direct-chat-text direct-chat-text-left chat-right">
                        	���|�ɽЪ`�N�ۨ��w���C�Y�J��o�ƬG�L�k����аO�o����������|!���|�����e�аȥ����yQRC��������A���z���Ӵr�֪����|!
                      </div>
                      <div class="direct-chat-info clearfix">
                        <span class="direct-chat-timestamp "></span>
                      </div>
                      
                    </div>

                   
                    </div>
 
 <!-- =============
============= -->                   
                    

                
             </div>

<!-- =============
============= -->

            <div class="popup-messages-footer" id="footer">
            <textarea id="status_message" placeholder="�п�J�T��" rows="10" cols="40" name="message"></textarea>
            <div class="btn-footer">
            <button class="bg_none" onclick="heart()"><i class="glyphicon glyphicon-heart"></i> </button>
            <button class="bg_none" onclick="pulldown()"><i class="glyphicon glyphicon-hand-down"></i> </button>
<!--             <button class="bg_none"><i class="glyphicon glyphicon-camera"></i> </button> -->
<!--             <button class="bg_none"><i class="glyphicon glyphicon-paperclip"></i> </button> -->
            
            </div>
            </div>
      </div>
  
                    <div class="" id="fixedbutton-talk">
                        <button id="addClass" class="button btn-lg btn-primary active">
                            �}�Ҳ�ѫ�<span id="badge" class="badge">${msgSvc.unread(member.memNo)>0 ? msgSvc.unread(member.memNo) : null}</span>
                        </button>
          				</div>

        		  
        		
        		<input type="hidden" id="lastestItemNo" value="${dSvc.getLastestDateItemNo(
        		  dSvc.getAllForChats(member.memNo))}"/>

        		  
        		    
        		    
<!--         		�T�{�b��ܥ\�त�ۤv�O�R���٬O���, �����H�O��     -->
				<% Integer otherUserNo;				
					Integer me=member.getMemNo();
					Integer seller=(dSvc.getLastestDateItem(
			        		  dSvc.getAllForChats(member.getMemNo()))).getSellerNo();
					Integer buyer=(dSvc.getLastestDateItem(
			        		  dSvc.getAllForChats(member.getMemNo()))).getBuyerNo();
					if(me.equals(seller)){
						otherUserNo=buyer;
					}else{
						otherUserNo=seller;
					}
					String otherUserName = memSvc.getOneMember(otherUserNo).getMemSname();
				%>
				<input type="hidden" id="otherUserNo" value="<%=otherUserNo%>"/>
				<input type="hidden" id="otherUserName" value="<%=otherUserName%>"/>
				<input type="hidden" id="userNo" value="${member.memNo}"/>
        		<input type="hidden" id="userName" value="${member.memSname}"/>
                    

				</c:if>
<script>

var MyPoint = "/MyEchoServer/"+$('#userNo').val()+"/"+$('#lastestItemNo').val();
var host = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf('/', 1));
var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
function pulldown() {
	$("#scroll-area").animate({ scrollTop: $('#scroll-area')[0].scrollHeight }, 1000);
}

function heart() {
	$('#status_message').val('<3');
	if (true){
	sendMessage();
	}
}




$(document).ready(function(){
	
	$('#status_message').on('keyup', function(e)  {
	    if (e.which == 13) {	    	
	    	sendMessage();	   
	    }
	});
		
	$("#addClass").click(function openwindow(){
	          $('#qnimate').addClass('popup-box-on');
	          $("#addClass").css('visibility', 'hidden');
;
	          var userName=$('#userName').val();
	          var userNo=$('#userNo').val();
			  var dateItemNo=$('#lastestItemNo').val();
			  var otherUserNo=$('#otherUserNo').val();
			  var otherUserName=$('#otherUserName').val();
			  var towhom='<img id="otherpic">���b�� '+otherUserName+' �i����';
			  
			  $('#chat-title').html(towhom);
			  $("#otherpic").attr('src','ImgReader?sellerNo='+otherUserNo+'&action=memImg');
	          
	          
	      	var statusOutput = document.getElementById("statusOutput");
	      	var webSocket;
	      	connect();
	});
	
	function connect() {
		// �إ� websocket ����
		webSocket = new WebSocket(endPointURL);
		
		webSocket.onopen = function(event) {
			//�bonopen�̧�Ҧ����v�T��Ū�i��
			//���J���v���
			}
		webSocket.onmessage = function(event) {
 			if ((event.data).valueOf() == new String("�����wŪ").valueOf()){
			
 				$('.read').html("�wŪ");
 			}
			var jsonObj = JSON.parse(event.data);
			if (jsonObj.history==new String("1").valueOf()){
				
				if (jsonObj.userNo==userNo.value){
					//�걵����r��
					var contentString='<div class="direct-chat-msg doted-border"><div class="direct-chat-info clearfix"><span class="direct-chat-name-left ">�A�� : </span></div><img src="ImgReader?sellerNo='+jsonObj.userNo+'&action=memImg" class="direct-chat-img-left"><div class="direct-chat-text direct-chat-text-left chat-right">'+(jsonObj.message== new String("<3").valueOf() ? '<img src="../images/heart-icon.png">' : jsonObj.message)+'</div><div class="direct-chat-info clearfix"><span class="direct-chat-timestamp ">'+jsonObj.time+' &nbsp; &nbsp; &nbsp; <b><large style="color:blue;" class="read"><style color="red"></large></b></style></span></div></div>';
					//�N�r��append��e���W
					$('#append').append(contentString);
				}else{
					var contentStr='<div class="direct-chat-msg doted-border"><div class="direct-chat-info clearfix"><span class="direct-chat-name-right ">'+jsonObj.userName+ '��:</span></div><img src="ImgReader?sellerNo='+jsonObj.userNo+'&action=memImg" class="direct-chat-img-right"><div class="direct-chat-text direct-chat-text-right chat-left">'+(jsonObj.message== new String("<3").valueOf() ? '<img src="../images/heart-icon.png">' : jsonObj.message)+'</div><div class="direct-chat-info clearfix"><span class="direct-chat-timestamp ">'+jsonObj.time+'</span></div></div>';
					$('#append').append(contentStr);
				}
			}else if (jsonObj.userNo==userNo.value){
				//�걵��html
				var contentString='<div class="direct-chat-msg doted-border"><div class="direct-chat-info clearfix"><span class="direct-chat-name-left ">�A�� : </span></div><img src="ImgReader?sellerNo='+jsonObj.userNo+'&action=memImg" class="direct-chat-img-left"><div class="direct-chat-text direct-chat-text-left chat-right">'+(jsonObj.message== new String("<3").valueOf() ? '<img src="../images/heart-icon.png">' : jsonObj.message)+'</div><div class="direct-chat-info clearfix"><span class="direct-chat-timestamp ">'+jsonObj.time+' &nbsp; &nbsp; &nbsp; <b><large style="color:blue;" class="read"><style color="red"></large></b></style></span></div></div>';
				//�N�r��append��e���W
				$('#append').append(contentString);
				//�V�U���ʨ�̩��U
				$("#scroll-area").animate({ scrollTop: $('#scroll-area')[0].scrollHeight }, 1000);
			}else{
				var contentStr='<div class="direct-chat-msg doted-border"><div class="direct-chat-info clearfix"><span class="direct-chat-name-right ">'+jsonObj.userName+ '��:</span></div><img src="ImgReader?sellerNo='+jsonObj.userNo+'&action=memImg" class="direct-chat-img-right"><div class="direct-chat-text direct-chat-text-right chat-left">'+(jsonObj.message== new String("<3").valueOf() ? '<img src="../images/heart-icon.png">' : jsonObj.message)+'</div><div class="direct-chat-info clearfix"><span class="direct-chat-timestamp ">'+jsonObj.time+'</span></div></div>';
				$('#append').append(contentStr);
				//�V�U���ʨ�̩��U
				$("#scroll-area").animate({ scrollTop: $('#scroll-area')[0].scrollHeight }, 1000);
			}
	        	      
		};
		webSocket.onclose = function(event) {
			alert("�w���u");
			$('#badge').html('');
		};
	}
		            
	          
	            $("#removeClass").click(function () {
	          $('#qnimate').removeClass('popup-box-on');
	          $("#addClass").css('visibility', 'visible');
	          webSocket.close();
	            });
	            
	            
	            
	        	function sendMessage() {
	        		var time= new Date().toLocaleString();
	        		var message=$("#status_message").val().trim();
	    	        var jsonObj = {"userNo" : $('#userNo').val() ,"userName" : $('#userName').val() , "message" : message , "time":time, "theOtherUserNo":otherUserNo.value};
	    	        if (message.length>0){
	    	        webSocket.send(JSON.stringify(jsonObj));
	    	        $('#status_message').val('');
	        	    }
	        	}
	        	    
	            
	        	
	        	
	            }); 
	            
window.onbeforeunload = function(){
	   webSocket.close();
	}
  	           
</script>