/**
 * 
 */
	var dropDownModalite;
	var dropDownCout;
	
	
		$(document).ready(function(){
			dropDownModalite = $("#modalite_bourse");
			dropDownCout=$("#coutModalite");
		
			
			dropDownModalite.on("change", function(){
				//loadCout();
			});
			
			
			
		});
			
	function loadCout(){
			idPaiement=dropDownModalite.val();
			url = contextPath +  "loadCout/" + idPaiement;
				$.get(url, function(responseJson){
				dropDownCout.val(responseJson.cout);
			}).done(function(){
				
			}).fail(function(){
				alert('failed');
			});
		}
	
function myFunction(value){
var res;
var pourcentage=value/100;
var coutMod=document.getElementById('coutModalite').value
res=coutMod*pourcentage;
document.getElementById('bourse').value=res; 
}
		

