<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String action = request.getParameter("userPermission");
		action="0";
		request.getRequestDispatcher("LoginServlet?userPermission="+action).forward(request, response);
		//request.getRequestDispatcher(request.getContextPath() +"/Z6Admin/Admin.jsp").forward(request, response);
	%>
</body>
</html>