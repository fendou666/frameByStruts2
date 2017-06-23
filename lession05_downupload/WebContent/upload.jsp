<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Struts2文件上传示例</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>

<body>
	<h3>Struts2文件上传示例</h3>
	<hr />

	<s:form action="fileUpload.action" method="post"
		enctype="multipart/form-data">
		<s:file type="file" name="up" style="width: 500px"></s:file>
		<s:fielderror name="up"></s:fielderror>
		<br />
		<s:submit value="开始上传" />
		<s:token />
	</s:form>

	<h3>Struts2批量文件上传示例</h3>
	<hr />

	<s:form action="batchFileUpload.action" method="post"
		enctype="multipart/form-data">
		<s:fielderror name="up"></s:fielderror>
		<br />
		<s:file type="file" name="up" style="width: 500px"></s:file>
		<s:file type="file" name="up" style="width: 500px"></s:file>
		<s:file type="file" name="up" style="width: 500px"></s:file>
		<s:submit value="开始上传" />
		<s:token />
	</s:form>

</body>
</html>
