$(document).ready(function(){

	// ------------- Top menu auto width and animation ---------------- //
	var width = 0;
	$('#menu tr td').each(function() {
		width += $(this).width()+1;
	});

	var padding = parseInt((($('#menu').width() - width) / $('#menu tr td a').length)/2);
	var pixLeft = ($('#menu').width() - width)-(padding*$('#menu tr td a').length*2)

	$('#menu tr td a').each(function(index) {
		if (index+1 != $('#menu tr td a').length) {
			$(this).css('padding', '0 '+(padding+2)+'px');
			$(this).css('background-position', '-' + $(this).position().left + 'px 0');
		} else {
			padding = padding + (pixLeft/2) +2;
			$(this).css('padding', '0 '+padding+'px');
			$(this).css('background-position', '-' + $(this).position().left + 'px 0');
		}
	});
	
	$('#menu tr td a').mouseover(function(){	
			$(this).stop().animate({ backgroundPosition: '-' + $(this).position().left - 129 + 'px 0'}, 2000)
		.mouseout(function(){
			$(this).stop().animate({ backgroundPosition: '-' + $(this).position().left + 'px 0'}, 2000)
		})
	});
	// ------------- END Top menu auto width and animation ---------------- //
	
});