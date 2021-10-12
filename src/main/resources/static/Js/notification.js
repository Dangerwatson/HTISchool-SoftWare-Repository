
$(document).ready(function(){
	//var = 
	function loadMessage(){
		
		url = contextPath + "HTISchool";
		
			$.get(url, function(responseJson){
						
			//use a data-id attribute in order to cal.... cout en fonction de type bourse
			console.log("messgedhw "+responseJson)
			
						
		}).done(function(){
			//alert('done');
		}).fail(function(){
			alert('failed');
		});
	}
	loadMessage()
	$("#counter").text($("#message-bloc .message").length)
})