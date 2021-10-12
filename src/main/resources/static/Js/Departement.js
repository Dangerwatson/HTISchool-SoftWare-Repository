	var btnLoad;
	var dropDownDept;
	var dropDownCommune;
		$(document).ready(function(){
			btnLoad = $("#btnLoadDept");
			dropDownDept = $("#departement1");
			dropDownCommune=$("#commune");
			
			btnLoad.click(function(){
				loadDepartements();
			});
			dropDownDept.on("change", function(){
				loadCommune();
			});
		});
		
		function loadDepartements(){
			url = contextPath + "loadDept";
			
			$.get(url, function(responseJson){
				dropDownDept.empty();
				$("<option>").text("Select Departement").appendTo(dropDownDept);
				$.each(responseJson, function(index, depart){
					$("<option>").val(depart.id).text(depart.name).appendTo(dropDownDept);
				});
			}).done(function(){
				
			}).fail(function(){
				
			});
		}
		
	function loadCommune(){
			deptId=dropDownDept.val();
			url = contextPath + "loadCommuneByDept/" + deptId;
			
				$.get(url, function(responseJson){
				dropDownCommune.empty();
				$("<option>").text("Select Commune").appendTo(dropDownCommune);
				$.each(responseJson, function(index, commune){
					$("<option>").val(commune.id).text(commune.name).appendTo(dropDownCommune);
				});
			}).done(function(){
				
			}).fail(function(){
				alert('failed');
			});
		}