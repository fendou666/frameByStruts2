var timecut = setInterval("timeCut()", 1000);
var time = 3;
function timeCut() {
	time--;
	document.getElementById("time").innerText = time;
	if (time <= 0) {
		window.clearInterval(timecut);
		var href = document.getElementById("link").href;
		location.href = href;
	}
}