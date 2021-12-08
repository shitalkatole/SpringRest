<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h1>employee list</h1>
 
 <form action="search">
        <br> <br> &nbsp Search Employee:<br> &nbsp<input
            type="text" name="name" placeholder="Search Employee"> &nbsp<input
            type="submit" value="search">
	<div align="center">
		<table>
		   <tr>
			<th>Name</th>
			<th>ProjectDetails</th>
			<th>Mail Id</th>
			<th>phone number</th>
           </tr>
			<c:forEach items="${employeeList}" var="emp">
				<tr>
					<td>${emp.name}</td>
					<td>${emp.project}</td>
					<td>${emp.mailId}</td>
					<td>${emp.phoneNo}</td>
					<td><a href="updateEmployee?employeeId=${emp.employeeId}">edit</a>
					<td><a href="deleteEmployee/${emp.employeeId}">delete</a>
		    </c:forEach>
		        
		</table>
	</div>
</body>
</html>