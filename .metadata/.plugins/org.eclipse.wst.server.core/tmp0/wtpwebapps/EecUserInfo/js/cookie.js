function setCookie(key, value, expriedays){
	if (expriedays==null) {
		var time = new Date();
		time.setDate(time.getDate() + 1);
		expriedays = time.toUTCString();
	}
	
	document.cookie = key+"=" + escape(value) + ";expires=" + expriedays;
}

function getCookie(key){
	var cookieStr = document.cookie;
	var ret = "";
	if(cookieStr.length !=0){
		var keyStart = cookieStr.indexOf(key);
		if(keyStart!=-1){
			var end = cookieStr.indexOf(";", keyStart);
			if(end==-1){
				end = cookieStr.length;
			}
			ret = cookieStr.substring(keyStart + key.length + 1, end);
		}
	}
	return ret;
}
