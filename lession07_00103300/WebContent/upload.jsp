<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<s:actionmessage/>
	<s:form action="fileupload.action" enctype="multipart/form-data">
		<s:textfield label="照片描述" name="desc"></s:textfield>
		<s:file label="文件1" name="file1"></s:file>
		<s:submit value="上传"></s:submit>
	</s:form>

</body>
</html>