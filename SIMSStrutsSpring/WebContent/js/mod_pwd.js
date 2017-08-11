function hideMesg(){
	document.getElementById("mesg").innerText = "";
}

function checkEmpty(){
	var old_pwd = document.getElementById("old_pwd");
	var new_pwd = document.getElementById("new_pwd");
	var con_pwd = document.getElementById("con_pwd");
	
	if(old_pwd == null || new_pwd == null || con_pwd == null 
		|| old_pwd.value == "" || new_pwd.value == "" || con_pwd.value == "" ){
		document.getElementById("mesg").innerText = "密码不能为空------";
		return false;
	}
}
