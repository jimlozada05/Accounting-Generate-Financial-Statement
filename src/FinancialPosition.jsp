<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="dao.*,java.util.*,table.*,java.sql.*,connect.*,util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Accounting ISO</title>
<meta charset="utf-8">
<link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600" rel="stylesheet" type="text/css">
<!--[if lte IE 8]>
<script src="js/html5shiv.js"></script>
<![endif]-->
<link rel="stylesheet" href="bootstrap-4.0.0-dist/css/bootstrap.min.css">
<script src="jquery/jquery-3.3.1.js"></script>
<script src="js/jquery.min.js"></script>

<script src="js/skel.min.js"></script>
<script src="js/skel-panels.min.js"></script>
<script src="js/init.js"></script>

<link rel="stylesheet" href="css/skel-noscript.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/style-wide.css">

<!--[if lte IE 9]>
<link rel="stylesheet" href="css/ie9.css">
<![endif]-->
<!--[if lte IE 8]>
<link rel="stylesheet" href="css/ie8.css">
<![endif]-->

	<style>
	a{
	text-decoration: none;
	}

	</style>


</head>
<body>
	<%
	if(session.getAttribute("username")!=null && session.getAttribute("login_id")!=null) {
	%>
	<%
	int sessionLogin_id = (int) session.getAttribute("login_id");
	String sessionUsername = (String) session.getAttribute("username");
	%>
	<%
	List<Login> list1=LoginDAO.getARecord(sessionLogin_id);
	request.setAttribute("list1",list1);
	%>

<div id="header" class="skel-panels-fixed">
  <div class="top">
<c:forEach items="${list1}" var="log">
    <div id="logo"> <span class="image avatar60"><img src="webpages/getloginimage.jsp?login_id=${log.getLogin_id()}" alt="No Pic" style="height:70px;width:70px;"></span>
      <h1 id="title">${log.getUsername()}</h1>
      <span class="byline"><a href="#" data-toggle="modal" data-target="#EditProfileModal">Edit Profile</a></span>
      <span class="byline"><a href="#" data-toggle="modal" data-target="#EditProfilePictureModal">Edit Picture</a></span>
      </div>
</c:forEach>
    <nav id="nav">
      <ul>
        <li><a href="#" onclick="window.location='index.jsp'"><span class="icon icon-home">Intro</span></a></li>
        <li><a href="#" onclick="window.location='generaljournalhome.jsp'"><span class="icon icon-th">General Journal</span></a></li>
        <li><a href="#"  class="active"><span class="icon icon-list">Module</span></a></li>
        <li><a href="#"  onclick="window.location='history.jsp'"><span class="icon icon-bookmark">History</span></a></li>
        <li><a href="#" onclick="window.location='logout.jsp'" class="skel-panels-ignoreHref"><span class="icon icon-signout">Log Out</span></a></li>
      </ul>
    </nav>
  </div>

</div>
<div id="main">

		<div class="modal fade" id="EditProfileModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content">
					<div class="modal-header">
			        <h5 class="modal-title" id="exampleModalLongTitle">Edit Profile</h5>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
					</div>
					<c:forEach items="${list1}" var="log">
			      		<div class="modal-body">
			        		<jsp:include page="webpages/editloginform.jsp?login_id=${log.getLogin_id()}"></jsp:include>
			      		</div>
			      	</c:forEach>
				</div>
			</div>
		</div>
		
		<div class="modal fade" id="EditProfilePictureModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content">
					<div class="modal-header">
			        <h5 class="modal-title" id="exampleModalLongTitle">Edit Profile</h5>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
					</div>
					<c:forEach items="${list1}" var="log">
			      		<div class="modal-body">
			        		<jsp:include page="webpages/EditLoginImageForm.jsp?login_id=${log.getLogin_id()}"></jsp:include>
			      		</div>
			      	</c:forEach>
				</div>
			</div>
		</div>
		
  <section id="top" class="one">
<%
 		List<GeneralLedger> list = GeneralLedgerDAO.getARecordByJournal(Integer.parseInt(request.getParameter("journal_id")));
  		request.setAttribute("list",list);
  		
  		FinancialPosition fp = FinancialPositionDAO.getOneRecordByJournal(Integer.parseInt(request.getParameter("journal_id")));
  		request.setAttribute("fp",fp);
  		
  		OwnerEquity oe = OwnerEquityDAO.getOneRecordByJournal(Integer.parseInt(request.getParameter("journal_id")));
  		request.setAttribute("oe",oe);
  		
  		List<AssetTitle> assetTitle = Arrays.asList(AssetTitle.values());
  		request.setAttribute("assetTitle", assetTitle);
  		
  		List<LiabilityTitle> liabilityTitle = Arrays.asList(LiabilityTitle.values());
  		request.setAttribute("liabilityTitle", liabilityTitle);
%>	
  	
 
  	
  	<h2>Statement of Financial Position</h2>
  	<br>
  	<h3>Assets</h3>
  	<table class="table table-bordered">
  		<thead>
  		<tr>
  			<td>Title</td>
  			<td>Amount</td>
  		</tr>
  		</thead>

  		<tbody>
  		<c:forEach items="${list}" var="list">
	  		<tr>
	  		<c:forEach var="assetTitle" items="${assetTitle}">
	  			<c:if test="${list.getAccount_title() == assetTitle.toString() && (list.getAccount_title() != 'ACCUMULATED_DEPRECIATION')}">
			  		<td>${list.getAccount_title().str}</td>
			  		<c:if test="${list.getTotal() >= 0 }"><td>${list.getTotal()}</td></c:if>
			  		<c:if test="${list.getTotal() < 0 }"><td>${list.getTotal() * -1}</td></c:if>
		  		</c:if>
	  		</c:forEach>
	  		</tr>
  		</c:forEach>
  		<tr><td colspan="2">-</td></tr>
  		<c:forEach items="${list}" var="list">
  			<tr>
	  				<c:if test="${list.getAccount_title() == 'ACCUMULATED_DEPRECIATION'}">
		  			<td>${list.getAccount_title().str}</td>
		  			<c:if test="${list.getTotal() >= 0 }"><td>${list.getTotal()}</td></c:if>
		  			<c:if test="${list.getTotal() < 0 }"><td>${list.getTotal() * -1}</td></c:if>
		  			</c:if>
  			</tr>
  		</c:forEach>
  		<tr><td colspan="2">=</td></tr>
  		<tr>
  			<td>Total Asset</td>
  			<td><%=fp.getTotal_asset()%></td>
  		</tr>
  		</tbody>
  	</table>
  	
  	<h3>Liabilities</h3>
  	<table class="table table-bordered">
  		<thead>
  		<tr>
  			<td>Title</td>
  			<td>Amount</td>
  		</tr>
  		</thead>

  		<tbody>
  		<c:forEach items="${list}" var="list">
	  		<tr>
	  		<c:forEach var="liabilityTitle" items="${liabilityTitle}">
	  			<c:if test="${list.getAccount_title() == liabilityTitle.toString()}">
			  		<td>${list.getAccount_title().str}</td>
			  		<c:if test="${list.getTotal() >= 0 }"><td>${list.getTotal()}</td></c:if>
			  		<c:if test="${list.getTotal() < 0 }"><td>${list.getTotal() * -1}</td></c:if>
		  		</c:if>
	  		</c:forEach>
	  		</tr>
  		</c:forEach>
  		<tr><td colspan="2"> + </td></tr>
  		
  		<tr>
  			<td>Ending Capital</td>
  			<td><%=oe.getEnd_capital()%></td>
  		</tr>
  		
  		<tr><td colspan="2"> = </td></tr>
  		<tr>
  			<td>Total Asset</td>
  			<td><%=fp.getTotal_liability()%></td>
  		</tr>
  		</tbody>
  	</table>
  	
  	
		<button onclick="GoBack()" class="btn btn-secondary" style="position:absolute;right:5%;padding-left:5%;padding-right:5%;text-align:center;">Back</button>
 		<script>
		function GoBack() {
		  window.history.back();
		}
		</script>
  </section>
    </div>
	<%
	}
	else
	response.sendRedirect("login.jsp");
	%>
	 <script src='js/popmotion.min.js'></script>
	 <script src="bootstrap-4.0.0-dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>