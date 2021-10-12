$(document).ready(function(){
    
               
     //hide blockUpdate
    $(".blockUpdate").hide();
    
    $(".checkUpdate").on("change",function(e){
    	var name = $(this).attr("data-name")
    	$(this).parent().siblings(".nameTd").children("input").val(name)
    	//check if inputs are hide
    	if($(this).parent().siblings(".nameTd").children("input").css("display") == "none"){
    		$(this).parent().siblings(".nameTd").children("span").hide()
    		$(this).parent().siblings(".nameTd").children("input").show();
    	}else{
    		$(this).parent().siblings(".nameTd").children("input").hide();
    		$(this).parent().siblings(".nameTd").children("span").show()
    	}
    });
    
  
});



