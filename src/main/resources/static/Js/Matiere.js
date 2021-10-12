/**
 * 
 */

/**
 * 
 */
	
	var dropDownClasse;
	var jours1;
	var jours2;
	var jours3;
	var jours4;
	var jours5;
		$(document).ready(function(){
			dropDownClasse = $("#dropDownClasse");
			jours1 = $("#jours1");
			jours2=$("#jours2");
			jours3 = $("#jours3");
			jours4=$("#jours4");
			jours5 = $("#jours5");
			
			
			dropDownClasse.on("change", function(){
				loadMatiere();
			});
		});
		
		
	function loadMatiere(){
			idClasse=dropDownClasse.val();
			url = contextPath + "loadMatiereByClasse/" + idClasse;
			
				$.get(url, function(responseJson){
					jours1.empty();
					jours2.empty();
					jours3.empty();
					jours4.empty();
					jours5.empty();
						$("<option>").text("Select matière").appendTo(jours1);
							$("<option>").text("Select matière").appendTo(jours2);
								$("<option>").text("Select matière").appendTo(jours3);
									$("<option>").text("Select matière").appendTo(jours4);
										$("<option>").text("Select matière").appendTo(jours5);
				$.each(responseJson, function(index, jours){
					$("<option>").val(jours.nomMatiere).text(jours.nomMatiere).appendTo(jours1);
					$("<option>").val(jours.nomMatiere).text(jours.nomMatiere).appendTo(jours2);
					$("<option>").val(jours.nomMatiere).text(jours.nomMatiere).appendTo(jours3);
					$("<option>").val(jours.nomMatiere).text(jours.nomMatiere).appendTo(jours4);
					$("<option>").val(jours.nomMatiere).text(jours.nomMatiere).appendTo(jours5);
				});
			}).done(function(){
				
			}).fail(function(){
				alert('failed');
			});
		}