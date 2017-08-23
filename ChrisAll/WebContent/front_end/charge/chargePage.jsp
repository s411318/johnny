<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.member.model.*"%>
<%request.setCharacterEncoding("UTF-8");%>  
<%response.setCharacterEncoding("UTF-8");%> 
<html>
<head>
<%@ include file="page4.file" %>
<style type="text/css">
.mm{
	padding-left:0px;
}
.credit-card-div  span {
    padding-top:10px;
        }
.credit-card-div img {
    padding-top:30px;
}
.credit-card-div .small-font {
    font-size:9px;
}
.credit-card-div .pad-adjust {
    padding-top:10px;
}
</style>
</head>
<body>

<%@ include file="/front_end/frontEndNavBar.file" %>
<%@ include file="page2.file" %>

<div class="container">
  <form action="<%=request.getContextPath() %>/chargePage" method="POST">
    <div class="row">
      
        <div class="col-md-10 text-center">
            <h2>請輸入你的信用卡資訊 </h2>
            <br />
        </div>
    </div>
    <div class="row">
        <div class="col-md-4 col-md-offset-3">
            <div class="credit-card-div">
                <div class="panel panel-default">
                    <div class="panel-heading">

                        <div class="row">
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <h5 class="text-muted"> 卡號
                                 <font color="red"> 
                                   <c:if test="${not empty errorMsgs}">
                                     <%=request.getAttribute("errorMsgs") %>
                                   </c:if>
                                 </font>
                                </h5>
                            </div>
                            <div class="col-md-3 col-sm-3 col-xs-3"  name="cardone">
                                <input type="text" class="form-control"  id="one" name="cardone" />
                            </div>
                            <div class="col-md-3 col-sm-3 col-xs-3" name="cardtwo">
                                <input type="text" class="form-control" id="two" name="cardtwo"/>
                            </div>
                            <div class="col-md-3 col-sm-3 col-xs-3" name="cardthree">
                                <input type="text" class="form-control" id="three" name="cardthree"  />
                            </div>
                            <div class="col-md-3 col-sm-3 col-xs-3" name="cardfour">
                                <input type="text" class="form-control" id="four" name="cardfour"  />
                            </div>
                        </div>
                        <div class="row ">
                            <div class="col-md-3 col-sm-3 col-xs-3" name="valimon">
                                <span class="help-block text-muted small-font" > 到期月份</span>
                                <input type="text" class="form-control" id="mon" name="valimon" placeholder="MM" />
                            </div>
                            <div class="col-md-3 col-sm-3 col-xs-3" name="valiyear">
                                <span class="help-block text-muted small-font">  到期年份</span>
                                <input type="text" class="form-control" id="year" name="valiyear" placeholder="YY" />
                            </div>
                            <div class="col-md-3 col-sm-3 col-xs-3" name="ccv">
                                <span class="help-block text-muted small-font"> CCV</span>
                                <input type="text" class="form-control" id="ccv" name="ccv" placeholder="CCV" />
                            </div>
                            <div class="col-md-3 col-sm-3 col-xs-3">
                                <img src="data:image/png;base64,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" class="img-rounded" />
                            </div>
                        </div>
                        <div class="row ">
                            <div class="col-md-12 pad-adjust" name="holder">

                                <input type="text" class="form-control" id="holder" name="holder" placeholder="請輸入持卡人姓名" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12 pad-adjust">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" checked class="text-muted"> 記住我的資訊
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="row ">
                            <div class="col-md-6 col-sm-6 col-xs-6 pad-adjust">
                            			<input type="hidden" name="action" value="payment">
                                		<input type="submit" class="btn btn-warning btn-block" value="支付" /><br>	
                                 		<input name="chargeNum" id="chargeNum" type="text" placeholder="請輸入金額"/>
                            	
                            </div>
                            <div class="col-md-6 col-sm-6 col-xs-6 pad-adjust">
                                <input type="submit" class="btn btn-danger" value="取消" />
                            </div>
                            
                        </div>
                       		<div>
                            	<input type="button" value="p" onclick="insert()">
                            </div>

                    </div>
                </div>
            </div>
            <!-- CREDIT CARD DIV END -->
        </div>
    </div>
    </form>
</div>
<script type="text/javascript">
function insert() {
    var one = document.getElementById("one");
    one.value="0001";
    var one = document.getElementById("two");
    one.value="8995";
    var one = document.getElementById("three");
    one.value="5300";
    var one = document.getElementById("four");
    one.value="7777";
    var one = document.getElementById("mon");
    one.value="12";
    var one = document.getElementById("year");
    one.value="21";
    var one = document.getElementById("ccv");
    one.value="310";
    var one = document.getElementById("holder");
    one.value="黃楷章";
    
}

</script>
<%@ include file="/front_end/frontEndButtom.file" %>
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>