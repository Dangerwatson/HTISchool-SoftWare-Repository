var btnLoad;
	var modalitePaiemnt;
	var coutModalite;
	var el;

	
		$(document).ready(function(){
			
			btnLoad = $("#btnLoadDept");
			modalitePaiemnt = $("#modalite");
			coutModalite=$("#cout");
			el=$("#elev");
			
			//
			var bourseBloc = $("#bourseOption").html();
			modalitePaiemnt.on("change", function(){
				//
				$("#bourseOption").html(bourseBloc)
				//
				$("#qte").val("");
				$("#bal").val("")
				//remove a disabled attribute when user select..
				$("#bourseOption .typeB").removeAttr("disabled")
				
				//loadCout();
				 
				 //event in order to ..
				 $("#bourseOption .typeB").change(function(e){					
					 //
					 $("#qte").val("");
					 $("#bal").val("")
					let cout = Number(coutModalite.val());
					var coutBourse = e.target.getAttribute("data-cout");
						coutModalite.val(cout - coutBourse) 
					
				})
			});
			
		});
		

	
		
function myFunctionPaiement(value){
var res;
var coutMod=document.getElementById('cout').value
res=coutMod-value;
document.getElementById('bal').value=res;
		}
		
		function myFunctionBal(value){
var resBal;
var ancBal=document.getElementById('balAncien').value
resBal=ancBal-value;
document.getElementById('balNouv').value=resBal;
		}
		




//contrainte pour paiement mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm
$(document).ready(function(){    
               var qteVers = document.getElementById("qte");
              
                        
    //hide all errors div
    $(".invalid-feedback").hide();
                
     //Function in order to check input classes
     function checkClasses(inputElement){
        if(!inputElement.checkValidity())
        {
            inputElement.classList.remove("is-valid");
            inputElement.classList.add("is-invalid");
            inputElement.nextElementSibling.innerHTML = inputElement.validationMessage;
            inputElement.nextElementSibling.style.display = "block";
        }else{
            inputElement.classList.remove("is-invalid")
            inputElement.classList.add("is-valid");
            inputElement.nextElementSibling.style.display = "none";
        }
    }

    //Function in order to valide input with two events blur and keyup
    function validate(inputElement){
        if(!inputElement.getAttribute("readonly")){
            inputElement.addEventListener("blur", function(){
                checkClasses(inputElement);
            })
        }
        inputElement.addEventListener("keyup", function(){
            checkClasses(inputElement);
        });
    }
    
    //Call validate function in order to check each user form  input   
    //validate(qteVers)
    
  //second validatiooioo
    function checkVers(val) {
	  var str = val;
	  var patt = new RegExp("^[0-9]+[,.]?[0-9]+$");
	  return patt.test(str);
	}
    $("#qte").on("input",function(e){
				let qte = Number(e.target.value);
				let cout = Number($("#cout").val());
				
				$(".btn").attr("disabled",true)
				
				$(".invalid-feedback").hide()
				$("#qte").removeClass("is-invalid");
				$("#qte").removeClass("is-valid");
				
				if(checkVers(qte)){
					$(".invalid-feedback").hide()
					$("#qte").removeClass("is-invalid");
					$("#qte").addClass("is-valid");
				}else{
					$(".invalid-feedback").show()
					$("#qte").removeClass("is-valid");
					$("#qte").addClass("is-invalid");
				}
				//
				if(cout > 0){
					if(qte > cout){					
						$("#qte").removeClass("is-valid");
						$(".invalid-feedback").show()
						//document.querySelector(".invalid-feedback").style.display = "block";
						$("#qte").addClass("is-invalid");
						$(".btn").attr("disabled",true)
					}else{
					$(".btn").attr("disabled",false)
					}
				}
			});


      
});