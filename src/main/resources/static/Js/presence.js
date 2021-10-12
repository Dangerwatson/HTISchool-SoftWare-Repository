		$(document).ready(function(){
			//$(".presValue").change()
			$(".presValue").val("abs");
			$(".checkPresAll").on("change", function(e){
				var checkAll = e.target.checked
				if(checkAll){
					$(".presValue").val("pres");
				}else{
					$(".presValue").val("abs");
				}
				
			
			})
			
			
			$(".checkPres").on("change", function(e){
				var pre = $(this).parent().children("input.presValue").val()
				if(pre == "abs"){
					$(this).parent().children("input.presValue").val("pres");
				}else{
					$(this).parent().children("input.presValue").val("abs")
				}
			
			})
			
            
		  
		});