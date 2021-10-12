/**
 * 
 */
//UpdateClasse
	 	$('document').ready(function() {
			
			$('.table #btnEdit').on('click', function(event) {
				event.preventDefault();
				var href = $(this).attr('href');
				$.get(href, function(c, status) {
					$('#idClasseEdit').val(c.idClasse);
					$('#codeClasseEdit').val(c.codeClasse);
					$('#ddlCatEdit').val(c.categoryCla.idCat);
					$('#classeEdit').val(c.classeName)
				});
				$('#updateModal').modal();
			}); 
			
			
					//delClasse
			$('.table #deleteButton').on('click', function(event) {
				event.preventDefault();
				var href = $(this).attr('href');
				$('#deleteModal #delRef1').attr('href', href);
				$('#deleteModal').modal();

			}); 
			
		  //delEcole
			$('.table #deleteButtonEcole').on('click', function(event) {
				event.preventDefault();
				var href =contextPath+ $(this).attr('href');
				$('#deleteModalEcole #delRefEcole').attr('href', href);
				$('#deleteModalEcole').modal();

			});
			
		

	
						//editMatiere
	 		/*$('.table #editMatiere').on('click', function(event) {
				event.preventDefault();
				var href = $(this).attr('href');
				$.get(href, function(m, status) {
					$('#idMatiereEdit').val(m.idMatiere);
					$('#codeMatiereEdit').val(m.codeMatiere);
					$('#ddlMatEdit').val(m.cateMat.idCatMatiere);
					$('#matiereEdit').val(m.nomMatiere);
					$('#coeffEdit').val(m.coefficient);
					$('#ddlClasseEdit').val(m.classe.idClasse);
				});
				$('#editModalMatiere').modal();
			});*/
		}); 
	