$(document).ready(function() {

	$("#admin").click(function() {
		$('#pages').empty();
		$('#result').empty();
		$('#pages').load('view/admin.jsp');
	});

	$("#search").click(function() {
		$('#pages').empty();
		$('#result').empty();
		$('#pages').load('view/search.jsp');
	});

	$("#aboutlogin").click(function() {
		$('#pages').empty();
		$('#result').empty();
		$('#pages').load('view/aboutlogin.jsp');
	});

	$("#deals").click(function() {
		$('#pages').empty();
		$('#result').empty();
		$('#pages').load('view/deals.jsp');
	});

	$("#contact").click(function() {
		$('#pages').empty();
		$('#result').empty();
		$('#pages').load('view/contact.jsp');
	});

	$("#updateprofile").click(function() {
		$('#pages').empty();
		$('#result').empty();
		$('#pages').load('view/updateprofile.jsp');
	});

	$("#start").click(function() {
		$('#pages').empty();
		$('#result').empty();
		$('#pages').load('view/login.jsp');
	});

	$("#about").click(function() {
		$('#pages').empty();
		$('#result').empty();
		$('#pages').load('view/about.jsp');
	});

	$("#register").click(function() {
		$('#pages').empty();
		$('#result').empty();
		$('#pages').load('view/register.jsp');
	});

	$("#adminlogin").click(function() {
		$('#pages').empty();
		$('#result').empty();
		$('#pages').load('view/login.jsp');
	});

	$("#addflight").click(function() {
		$('#pages').empty();
		$('#result').empty();
		$('#pages').load('view/addflight.jsp');
	});

	$("#deleteflight").click(function() {
		$('#pages').empty();
		$('#result').empty();
		$('#pages').load('view/deleteflight.jsp');
	});

	$("#updateflight").click(function() {
		$('#pages').empty();
		$('#result').empty();
		$('#pages').load('view/updateflight.jsp');
	});

	$("#adddeal").click(function() {
		$('#pages').empty();
		$('#result').empty();
		$('#pages').load('view/xmlupload.jsp');
	});

	$("#updatedeal").click(function() {
		$('#pages').empty();
		$('#result').empty();
		$('#pages').load('view/xmlupload.jsp');
	});

	$("#deletedeal").click(function() {
		$('#pages').empty();
		$('#result').empty();
		$('#pages').load('view/xmlupload.jsp');
	});

	$("#schedule").click(function() {
		$('#pages').empty();
		$('#result').empty();
		$('#pages').load('view/csvupload.jsp');
	});

	$("#showschedule").click(function() {
		$('#pages').empty();
		$('#result').empty();
		$('#pages').load('view/showschedule.jsp');
	});

	$("#forgetpassword").click(function() {
		$('#pages').empty();
		$('#result').empty();
		$('#pages').load('view/forgetpassword.jsp');
	});

});
