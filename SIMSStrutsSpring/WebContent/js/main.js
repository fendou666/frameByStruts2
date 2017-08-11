$(function(){
	
	var bgSelect="<select  id=\"skin\" onchange=\"changeBg()\">"+
							"<option value=\"1\">背景1</option>"+
							"<option value=\"2\">背景2</option>"+
							"<option value=\"3\">背景3</option>"+
							"<option value=\"4\">背景4</option>"+
							"<option value=\"5\">背景5</option>"+
							"<option value=\"6\">背景6</option>"+
						"</select>";
	$("#main").prepend(bgSelect)
})

function changeBg(){
	var num = $("#skin").val();
	$("#main_bg").attr("src", path+"/img/bg" + num + ".jpg")
}