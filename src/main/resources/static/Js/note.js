$(document).ready(function(){
		//alert("ok");
			//$("td.1").hide();
			//var thMatiereCount = document.querySelectorAll("#matiere .mat").length;
			var trCount = document.querySelectorAll("tbody tr");
			var thList = document.querySelectorAll("thead th.matiere").length;
			var thCoeMat = document.querySelectorAll("thead th.matiere");
			var SumCoe = 0;
			for(let i = 0 ; i < thCoeMat.length; i++){
				SumCoe += Number.parseFloat(thCoeMat[i].getAttribute("data-coe"))
			}
			//console.log(SumCoe)
			$("#thMatiere").attr("colspan",thList)
			//console.log($("#thMatiere").attr("colspan"))
			
			
			//var trCount = $("tbody tr");
			//var rangTrSum = 1;
			//var rangSum = trCount.length;
			//var firstIdTr = trCount[0].getAttribute("data-idP")
			//console.log(firstIdTr)
			//var firstIdTr = trCount[0].getAttribute("data-idP");
			
				
			/*for(var i=0; i<tds.length; i++){				
				console.log("Id : " + tds[i])
				//var tdr = 
			}*/
			
			//console.log(document.querySelectorAll("#" + String(firstIdTr)))
			//var tdList = document.querySelectorAll("#" + firstIdTr+" .td-note");
			//console.log(trCount.length)
			//console.log(trCount.length * 2)
			
			/*$("tbody tr").each(function(){
				$(this).attr("data-idP")
			})*/
			//var sumTr = Number.parseFloat(thList.length)
			//console.log("th + "+ thList)
			
			for(var i=0; i<trCount.length; i++){
				var idTr = trCount[i].getAttribute("data-idP");
			
				for(let j = 0; j < thList; j++){
					$("#"+idTr +" td.tdPrenom").after('<td class="tt ">0</td>')
					
				}
			}
		
			
		for(var i=0; i<trCount.length; i++){
				var idTr = trCount[i].getAttribute("data-idP");
				
				//console.log("Id tr: " + idTr)
				//var tds = $("#" + firstIdTr + " span")
				//console.log($("#"+idTr +" span").text())
				//console.log($("#"+idTr +" td.total").before("<td>0</td>"))
				$("#"+idTr +" span").parent().hide()
				//let spanText = $("#"+idTr +" span."+idTr).text()
				//console.log("span:"+spanText)
				//$("#"+idTr +" span").text("0")
				//console.log("SPAN "+$("#"+idTr +" span").text())
					
				$("#"+idTr +" span."+idTr).parent().show()
				
				var thSpa = $("#"+idTr +" td."+idTr);
				//var tdNotes = $("#"+idTr +" td."+idTr);
				var newTdNote="";
				
				for(let i = 0; i < thCoeMat.length; i++){
					let idMat = thCoeMat[i].getAttribute('data-idMat');
					for(let j = 0; j < thSpa.length; j++){
						//console.log("MAT ID td : "+thSpa[j].getAttribute('data-idM'))
						if(idMat == thSpa[j].getAttribute("data-idM")){
							let note = thSpa[j].getAttribute('data-note');
							//console.log("MAT ID td : "+thSpa[j].getAttribute("data-idM") +" - " + thSpa[j].innerHTML)
							newTdNote +='<td data-id="'+idTr+'" data-note="'+note+'" class="'+idTr+' note"><span data-td="'+ idTr+'" data-note="'+note+'" class="'+idTr+'">'+note+'</span></td>'
						}
					}					
				}
				console.log("NEW TD : "+thSpa[0]);
				//if(thSpa[0].getAttribute('data-note') != null){
					$("#"+idTr+" td."+idTr).remove()
					$("#"+idTr+" td.tdPrenom").after(newTdNote);
				//}
				
				
				var thSpans = $("#"+idTr +" span."+idTr).length;
				
				
				//console.log(thSpans)
				if(thSpans > 0){
					console.log($("#"+idTr +" td.tt").remove())
					for(let j = 0; j < thList-thSpans; j++){
						
						$("#"+idTr +" td.total").before('<td class="t '+idTr+'" ><span  class="'+idTr+'">0</span></td>')
						//$(".test").hide();
						//console.log($("#"+idTr +" span."+idTr+":last-child").parent())
						//$("#"+idTr +" span."+idTr+":last-child").parent().after('<td class="t">0</td>')
						
					}
				}
				//console.log("span In Th :"+thList);
				//console.log("span In Tr :"+thSpans);
				
				/*for(var j=0; j< tds.length; j++){
					var spanId =  tds[i].getAttribute("data-td")
					console.log("tr:" +idTr+" spanId : "+ spanId)
					console.log($("."+spanId).text())
					
					if(idTr != spanId){
						console.log($("."+spanId).text())
					}
				}*/
				//var tdr = 
				//var noteList = $("#"+idTr +" .note");
				var thSpa = $("#"+idTr +" td."+idTr);
				
				//console.log($("#OK").html())
				var tot = 0;
				//$(".total").text(thSpa.length);
				for(let i=0; i<thSpa.length; i++){
					tot +=Number.parseFloat(thSpa[i].textContent);
					//console.log("COE : "+ Number.parseFloat(thCoeMat[i].getAttribute("data-coe"))/100)
				}
				
				console.log("total :" + tot +" sumCoe :"+SumCoe);
				var moy = ((tot*10)/SumCoe).toFixed(2)
				
				var mention ="";
				if(moy >= 5.00)
					mention = "Reussi";
				else if(moy >= 4 && moy < 5)
					mention = "Ajourne";
				else
					mention = "elimine";
				
				$("#"+ idTr +" .total input").val(tot)
				$("#"+ idTr +" .moy input").val(moy)
				$("#"+ idTr +" .mention input").val(mention)
			}
			
			/*for(var i = 0; i<trCount.length; i++){
				var idTr = trCount[i].getAttribute("data-id");
				console.log("Id : "+idTr)
				console.log("rang "+ rangTrSum)
				for(var j = rangTrSum; j <= (trCount.length * 2) - rangSum; j++){
					console.log("ok" + j)
					rangTrSum++;					
				}
					
				rangTrSum--;
			}*/
		})
		
	