$(document).ready( function() {
	$('#loginBtn').bind('click', function() { login.login('#userid', '#userpw', REQUEST_URI); });
	
/*	$('#loginForm').submit(function(event) {
		var userid = $('#userid').val();
		$.cookie('remember-me-userid', userid, { expires: 3650 }); //SETS IN DAYS (10 YEARS)
	});
	if($.cookie('remember-me-userid')) {
		$('#userid').val($.cookie('remember-me-userid'));
		$('#remember').attr('checked', true);
	}*/
});

var login = {
	login: function(userid, passwd, returl) {
		var userid = encodeURIComponent($(userid).val());
		var passwd = encodeURIComponent($(passwd).val());
		var returl = encodeURIComponent(returl);

		$('#loginForm').submit();
	}
};