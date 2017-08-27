<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.dateitem.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.restaurant.model.*" %>
<%@ page isELIgnored="false" %>
<%@ page import="java.util.List"%>
<%@ page import="com.member.model.*"%>

<%@ include file="header.file"%>

<%
DateItemVO dateItemVO = (DateItemVO) request.getAttribute("dateItemVO");
Long now = System.currentTimeMillis();
Long candatetimemin = now + 3600000;
Long candatetimemax = candatetimemin +5184000000L;
SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:00");
SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
Timestamp timemin = new Timestamp(candatetimemin);
Timestamp timemax = new Timestamp(candatetimemax);
String tmin = sdf1.format(timemin);
String tmax = sdf2.format(timemax);
RestaurantService restService =new RestaurantService();
List<Restaurant> rests= restService.getAll();
pageContext.setAttribute("rests", rests);
%>

<style>
    #map{
      height:600px;
      width:100%;
    }
    
    btn{
    margin-top:1rem;
    margin-bottom:1rem;
    max-height:20px;
    radius:15%;
    }
    

  </style>


<!-- =======�ˬd�W�[�ɶ��O�_����========= -->
<jsp:useBean id = "dSvc" scope="page" class="com.dateitem.model.DateItemService" />     
<jsp:useBean id = "memSvc" scope="page" class="com.member.model.MemberService" />


<%
Member member = (Member) session.getAttribute("member");
List<DateItemVO> list = dSvc.findBySeller_history(member.getMemNo());
pageContext.setAttribute("list",list);

%>


<head>
<title>���|�ӫ~�W�[</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>



<%@ include file="/front_end/frontEndNavBar.file"%>
<%@ include file="sidelist.file"%>





<div class="col-md-offset-1 col-md-8 main-page-show">

<h4><font color=pink><b>*</b></font>�ж�g�Ҧ����</h4>
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>�Эץ��H�U���~:
	<ul class="unstyled">
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>	
	</font>
</c:if>


<form id="formadd" action="dateitem.do" method=post enctype="multipart/form-data">
<table class="table">

<div class="row">


<form>
<div class=" col-sm-5">
  <div class="form-group">
    <label for="lb1">����d���Ϥ�</label>
    <input id="lb1" type="file" class="file" name="dateItemImg" data-show-upload="false" data-show-caption="true">
	<input type="hidden" name="action" value="insert" >
    <small id="desc1" class="form-text text-muted">�п�ܭn�X�u���|���d���Ӥ�</small>
  </div>
  
  <br>
  <div class="form-group">
    <label for="lb2">����d��</label>
    <select class="form-control selectpicker" size="1" name="petNo"  id="lb2">
			<c:forEach var="pet" items="${myPetList}">
				<option value="${pet.petNo}" ${(pet.petNo==dateItemVO.petNo)? 'selected':'' } >${pet.petName}
			</c:forEach>
		</select>
  </div>
  
   <br>
  <div class="form-group">
  <label for="restListNo">����\�U</label>
  <jsp:useBean id="restSvc" scope="page" class="com.restaurant.model.RestaurantService" />
   <a class="btn btn-default btn-xs" data-toggle="modal" data-target="#googleMap" href="#">
       �a���s��</a>
  <a class="btn btn-default btn-xs" href="">�s�W�\�U</a>
  <select id="restListNo" class="form-control" size="1" name="restListNo">
			<c:forEach var="rest" items="${restSvc.all}">
				<option value="${rest.restNo}" ${(rest.restNo==dateItemVO.restListNo)? 'selected':'' }>${rest.restName}
			</c:forEach>
			
		</select> 
  </div>
   <br>
  
  <div class="form-group">
    <label for="lb4">�п�J���|����</label>
<input id="qprice" class="form-control" type="TEXT" name="dateItemPrice" size="45"
			value="<%= (dateItemVO==null)? "" : dateItemVO.getDateItemPrice()%>" />
  </div>

   <button class="btn btn-xs btn-default" id="date1">1</button>
  <button class="btn btn-xs btn-default" id="date2">2</button>
  <button class="btn btn-xs btn-default" id="date3">3</button>
  
    </div>
 <div class="col-sm-offset-1 col-sm-5"> 
  
  <div class="form-group">
    <label for="lb5">�п�J���|���D</label>
<input id="qtitle" class="form-control" maxlength="30" type="TEXT" name="dateItemTitle" size="45"
			value="<%= (dateItemVO==null)? "" : dateItemVO.getDateItemTitle()%>" />
  </div>
 
<br>
  <div class="form-group">
    <label for="exampleInputFile">���|���e�y�z</label>
    <input id="qtext" class="form-control" type="text" name="dateItemText" size="45"
			value="<%= (dateItemVO==null)? "" : dateItemVO.getDateItemText()%>" />
    <small id="fileHelp" class="form-text text-muted">�V�R�ꪺ���e�V�e�����|���\��.</small>
  </div>

  
   <div class="form-group">
    <label for="exampleInputFile">�]�w���|�ɶ�</label>
  <input class="form-control" type="text" id="dp" name="time"> </td></tr>
      <small id="fileHelp" class="form-text text-muted">�I���W���}�l���</small>
  </div>	
   <br>
  <div class="form-group">
    <label for="exampleInputFile">�R��ѻP�H��</label>
  	<select name="dateItemPeople" class="form-control selectpicker">
  	<option value="1" selected>1�H</option>
 	 <option value="2">2�H</option>
	</select>
	</div>
	 <br>
	  <div class="form-group">
    <label for="exampleInputFile">���ͤH�P��</label>
    
    	<select name="hasMate" class="form-control selectpicker">
  	<option value="false" selected>�S��</option>
 	 <option value="true">��</option>
	</select> </div>
	<br>
<br>
                    <a id="godo"class="btn btn-info"  href="">
                        �W�[�ӫ~
                    </a>

</div>	
	



<!-- �T�{�ɶ����� -->

		<div id="tryonsale" class="modal fade" role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header text-center">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">�W�[�ӫ~</h4>
      </div>
      <div class="modal-body" >
        <p></p>
        <h2><p id="textshow">���@�����|, �Ь۹j�|�p�ɥH�W</p></h2>
      </div>
      <div class="modal-footer">
        <button id="fortest" type="button" class="btn btn-warning" data-dismiss="modal">�^�W�@��</button>
      </div>
    </div>

  </div>
  </div>


</form>	


</div>






								<!-- Add photo MODAL -->
								<div class="modal fade" id="googleMap" tabindex="-1"
									role="dialog" aria-labelledby="exampleModalLabel"
									aria-hidden="true">
									<div class="modal-dialog modal-lg" role="document">
										<div class="modal-content">


											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal">&times;</button>
												<h4 class="modal-title">
													<b>�s���\�U</b>
												</h4>
											</div>


  <p> ��J���d�ߪ��a�}<input id="address" type="text" size="50" value="">   <input class="btn-info" type="button" value="�d��" onClick="codeAddress()"> </p>
  <div id="map"></div>



										</div>
									</div>
								</div>




</div>
<BR>
<BR>
<BR>



 <script>
 $(document).ready(function(){
	$("#date1").click(function(e){
		e.preventDefault();
		$("#restListNo").val("7017");
		$("#qprice").val("300");
		$("#qtitle").val("���l�O���|�������ͻH�ͻH�O���b�ѤW�����l");
		$("#qtext").val("�H�ͦp���ɯP�S�p�����e�A�p���t�W�S�p���͵��A���]���o�ˡA�C���C����o�W�S�C�ڰO�o���ǷŬX�a��ڷL�����y�աF�ڰO�o���ǲ`��籡�������F�ڰO�o���ǥ��ߥ��N���֩�K�K�������h���A���Ǭ��n�ɥ��C�s�T�O�@�󥲭n���ơA�]���s�T�A�ڭ̤F�ѤH�@���L�`�A��O����o�����ַP�쭿�[�ñ��C");
	});
	
	$("#date2").click(function(e){
		e.preventDefault();
		$("#restListNo").val("7090");
		$("#qprice").val("300");
		$("#qtitle").val("���ɭԧA�H�����O���֪��_�I");
		$("#qtext").val("�ڬO�ѪŨ����@�����A������v�b�A���i�ߡX�X�A�����Y���A��L���w�ߡX�X�b�����������F�ܼv�C�A�ڬ۳{�b�©]�����W�A�A���A���A�ڦ��ڪ��A��V�F�A�O�o�]�n�A�̦n�A�ѱ��A�b�o��|�ɤ��񪺥��G�I");
	});
	
	$("#date3").click(function(e){
		e.preventDefault();
		$("#restListNo").val("7050");
		$("#qprice").val("300");
		$("#qtitle").val("�ѤW����b�ѤW��");
		$("#qtext").val("�ڬO�ѪŨ����@�����A������v�b�A���i�ߡX�X�A�����Y���A��L���w�ߡX�X�b�����������F�ܼv�C�A�ڬ۳{�b�©]�����W�A�A���A���A�ڦ��ڪ��A��V�F�A�O�o�]�n�A�̦n�A�ѱ��A�b�o��|�ɤ��񪺥��G�I");
	});
 });
//  ����ajax�ˬd�O�_�����Ƥ��,�����ܸ�������,�S�����ܰecontroller�i��������
 
 $(document).ready(function(){
	 $('#godo').on('click', function(e) {
		    e.preventDefault();
		    $.ajax({ 
		        url:  'dateitem.do?action=checkTime',
		        type: 'POST',
		        data: { time:$('#dp').val(), 
				},
		        success: function(count){
		       
		        	if(parseInt(count)>0){
		        	 $('#tryonsale').modal('show');
	        	$('#textshow').text($('#dp').val()+'�o�ɬq�w�g�����|�F,�Ь۹j�|�p�ɥH�W');
		        	}else{		        		
		        		$('#formadd').submit();
		        	}
		        },
		        error: function(){
		            alert("error");
		        }  
		    });  	    
	});    
 }); 


 
 		

	 $("#dp").datetimepicker({
		 
// 	 	timepicker:false,
		 format: 'Y-m-d H:i',
		 minDate:
			 '+1970/01/02',
// 		 allowTimes:[
// '11:00','11:30', '12:00', '12:30','13:00', '13:30','14:00','14:30', '15:00', '15:30', '16:00', '16:30', '17:00', '17:30', '18:00', '18:30', '19:00',
// 		  '19:30', '20:00', '21:00', '22:00', '23:00'
// 		 ],
		 defaultDate: '17/09/01',
//allowTimes �Ϊ�step��@�ӥ�
		 step: 30 
		             });

	  
 
  var geocoder;
  var map, popup;
  var markers;
  
  $(function(){
	  
	  $("#googleMap").on("shown.bs.modal", function () {
		    google.maps.event.trigger(map, "resize");
		    console.log("qwe");
		});
  })
  
  
  function initMap(){
      // Map options
      var options = {
        zoom:15,
        center:{lat: 25.042419,lng:121.541808}
      }

      // New map
      map = new google.maps.Map(document.getElementById('map'), options);
      geocoder = new google.maps.Geocoder();
      popup = new google.maps.InfoWindow();

//       // Listen for click on map
//       google.maps.event.addListener(map, 'click', function(event){
//         // Add marker
//         addMarker({coords:event.latLng});
//       });


      

	          markers = new Array();

				<c:forEach var="rest" items="${rests}">
	        	marker=new Object();
				marker.coords={lat:${rest.getRestLatitude()},lng:${rest.getRestLongtitude()}};
 				marker.iconImage='https://developers.google.com/maps/documentation/javascript/examples/full/images/beachflag.png';
 				marker.content=
 				"<H1>${rest.restName}</H1>"
 				+"<H2>${rest.restPhone}</H2>"
 				+"<H3>${rest.restAdd}</H3>"
 				+"<input type=\"button\" value=\"��ܦ��\�U\" onClick=\"chooseRest(${rest.restNo})\">" ;
				markers.push(marker);
			</c:forEach>
      
      
      
      
      // Loop through markers
      for(var i = 0;i < markers.length;i++){
        // Add marker
        addMarker(markers[i]);
      }

      // Add Marker Function
      function addMarker(props){
        var marker = new google.maps.Marker({
          position:props.coords,
          map:map,
          //icon:props.iconImage
        });

        // Check for customicon
        if(props.iconImage){
          // Set icon image
          marker.setIcon(props.iconImage);
        }

        // Check content
        if(props.content){
          var infoWindow = new google.maps.InfoWindow({
            content:props.content
          });

          marker.addListener('click', function(){
            infoWindow.open(map, marker);
          });
        }
      }
      
     
    }
    
    
    
    
    function codeAddress() {
  	    var address = document.getElementById("address").value;
  	    geocoder.geocode( { 'address': address}, function(results, status) {
  	      if (status == google.maps.GeocoderStatus.OK) {
  	        map.setCenter(results[0].geometry.location);

  			document.getElementById("lat").value=results[0].geometry.location.lat();
  			document.getElementById("lng").value=results[0].geometry.location.lng();
  	        var marker = new google.maps.Marker({
  	            map: map,
  	            position: results[0].geometry.location
  	        });


  	      } else {
  	        alert("����, ��]: " + status);
  	      }
  	    });
  	  }


    
    
  	  function chooseRest(restNo) {
  		  $('#restListNo').val(restNo);
  		$('#googleMap').modal('hide');
	  };
  	  
  	
  	  
  </script>
  <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCAUbYcDBdfK_UjTWa9G6FSe3EfERMpEZQ&callback=initMap">
    </script>


<%@ include file="/front_end/frontEndButtom.file"%>

