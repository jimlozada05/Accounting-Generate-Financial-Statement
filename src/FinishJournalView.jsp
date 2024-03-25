<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="dao.*,java.util.*,table.*,java.sql.*,connect.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML>
<html>
<head>
	<title>Accounting ISO</title>
	<meta charset="utf-8">
	
	<link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="bootstrap-4.0.0-dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="css/skel-noscript.css">
	<link rel="stylesheet" href="css/style.css">
	<link rel="stylesheet" href="css/style-wide.css">
	
	<script src="jquery/jquery-3.3.1.js"></script>
	<script src="js/jquery.min.js"></script>
	
	<script src="js/skel.min.js"></script>
	<script src="js/skel-panels.min.js"></script>
	<script src="js/init.js"></script>
	
	<!--[if lte IE 9]>
	<link rel="stylesheet" href="css/ie9.css">
	<![jimlozada and liaulanday]-->
	<!--[if lte IE 8]>
	<link rel="stylesheet" href="css/ie8.css">
	<script src="js/html5shiv.js"></script>
	<![endif]-->
	
	
	<!-- <script src="js/prefixfree.min.js"></script> -->
	
	<style>
	table{
	width:90%;
	margin:auto;
	}
	#smoltr{
	width:20%;
	}
	#sosmoltr{
	width:10%;
	}
</style>


</head>
<body style="background-color:#f5fafa">
	<%
		if(session.getAttribute("username")!=null && session.getAttribute("login_id")!=null) {
			
			int sessionLogin_id = (int) session.getAttribute("login_id");
			String sessionUsername = (String) session.getAttribute("username");
			
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
        <li><a href="#" onclick="window.location='index.jsp'" class="skel-panels-ignoreHref"><span class="icon icon-home">Intro</span></a></li>
        <li><a href="#top" onclick="window.location='#'"><span class="icon icon-th">General Journal</span></a></li>
        <li><a href="#" onclick="window.location='module.jsp'" class="skel-panels-ignoreHref"><span class="icon icon-list">Module</span></a></li>
        <li><a href="#" onclick="window.location='history.jsp'"  class="active"><span class="icon icon-bookmark">History</span></a></li>
        <li><a href="#" onclick="window.location='logout.jsp'" class="skel-panels-ignoreHref"><span class="icon icon-signout">Log Out</span></a></li>
      </ul>
    </nav>
  </div>

</div>
<div id="main">

	<%List<JournalEntry> gjList=JournalEntryDAO.getByJournalId(Integer.parseInt(request.getParameter("journal_id")));
	request.setAttribute("gjList",gjList);%>
	
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
	

	
  <section id="top" class="one" style="background-color:#f5fafa">
	 	<h1 class="display-4">General Journal #${param.journal_id}</h1>
	 	
	 	
		
	 	<table class="table table-striped table-bordered">
	 		<thead>
	 			<tr>
	 			<th>Date</th>
	 			<th>Account Title</th>
	 			<th>Debit</th>
	 			<th>Credit</th>
	 			<tr>
	 		</thead>
	 		<tbody>
	 			<c:forEach items="${gjList}" var="journal">
		 			<tr>
			 			<td><fmt:formatDate pattern="MMMM dd, yyyy" value="${journal.date}"/></td>
			 			
			 			<td>${journal.account_title.str}</td>
			 			<td>${journal.getDebit()}</td>
			 			<td>${journal.getCredit()}</td>
			 		</tr>
	 			</c:forEach>
	 		</tbody>
	 	</table>
	 	
			
		<button onclick="GoBack()"  class="btn btn-outline-dark" style="position:absolute;left:55%;padding:1% 3% 1% 3%;">Back</button>
	 	<script>
		function GoBack() {
		  window.history.back();
		}
		</script>
	 	<form method="post" action="webpages/deletegroup.jsp">
			<input type="hidden" name="journal_id" value="<%out.print(request.getParameter("journal_id"));%>">
			<button type="submit" class="btn btn-outline-danger" style="position:absolute;right:.5%;padding:1% 3% 1% 3%;">Delete Journal</button>
		</form>
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