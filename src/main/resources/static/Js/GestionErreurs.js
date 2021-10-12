	// Validation form Classe
	/*$(function() {
		$.validator.setDefaults({

		});
		$('#formClasse')
				.validate(
						{
							rules : {
								categoryCla : {
									required : true,
								},
								
								classeName : {
									required : true,
									maxlength : 15,
									minlength: 5,
									pattern: "[A-Za-z0-9 ]{5,15}" 
								},
							
							},
							messages : {
								categoryCla : {
									required : "Selectionner une catégorie pour cette classe",								
							},
							errorElement : 'span',
							errorPlacement : function(error, element) {
								error.addClass('invalid-feedback');
								element.closest('.form-group')
										.append(error);
							},
							highlight : function(element, errorClass,
									validClass) {
								$(element).addClass('is-invalid');
							},
							unhighlight : function(element, errorClass,
									validClass) {
								$(element).removeClass('is-invalid');
							}
						});
	});*/
	
	
		// Validation form CategorieClasse
	/*$(function() {
		$.validator.setDefaults({

		});
		$('#formCatClass')
				.validate(
						{
							rules : {
								nomCat : {
									required : true,
									maxlength : 25,
									minlength: 5,
									pattern: "[A-Za-z ]{5,25}"
								
								    
								},
							
							},
							messages : {
								nomCat : {
									required : "Ce champs ne doit pas être vide",
									maxlength:"Ce champs ne doit pas contenir plus de 25 caractère",
									minlength : "Ce champs ne doit pas inférieur a 5",
									pattern: "Invalide caractère"
									
								},
								
							},
							errorElement : 'span',
							errorPlacement : function(error, element) {
								error.addClass('invalid-feedback');
								element.closest('.form-group')
										.append(error);
							},
							highlight : function(element, errorClass,
									validClass) {
								$(element).addClass('is-invalid');
							},
							unhighlight : function(element, errorClass,
									validClass) {
								$(element).removeClass('is-invalid');
							}
						});
	});*/
	
		
		// Validation form CategorieEcole
/*	$(function() {
		$.validator.setDefaults({

		});
		$('#formCatEcole')
				.validate(
						{
							rules : {
								typeEcole : {
									required : true,
									maxlength : 25,
									minlength: 5,
									pattern: "[A-Za-z ]{5,15}"
								
								    
								},
							
							},
							messages : {
								typeEcole : {
									required : "Ce champs ne doit pas être vide",
									maxlength:"Ce champs ne doit pas contenir plus de 15 caractère",
									minlength : "Ce champs ne doit pas inférieur a 5",
									pattern: "Invalide caractère"
									
								},
								
							},
							errorElement : 'span',
							errorPlacement : function(error, element) {
								error.addClass('invalid-feedback');
								element.closest('.form-group')
										.append(error);
							},
							highlight : function(element, errorClass,
									validClass) {
								$(element).addClass('is-invalid');
							},
							unhighlight : function(element, errorClass,
									validClass) {
								$(element).removeClass('is-invalid');
							}
						});
	});*/
	
	
			// Validation form Ecole
	$(function() {
		$.validator.setDefaults({

		});
		$('#formEcole')
				.validate(
						{
							rules : {
								
								catEcole : {
									required : true,
								},
								
								depart : {
									required : true,
								},
								
									commune : {
									required : true,
								},
								
									user : {
									required : true,
								},
								
									
								
										phot : {
									required : true,
								},
								
								
							
							},
							
							
							messages : {
								catEcole : {
									required : "Selectionner une catégorie pour cette école",
								
								},
								
									depart : {
									required : "Selectionner un département pour cette école",
								
								},
								
									commune : {
									required : "Selectionner une commune pour cette école",
								
								},
								
									user : {
									required : "Ce champs est obligatoire",
								
								},
								
								
							},
							errorElement : 'span',
							errorPlacement : function(error, element) {
								error.addClass('invalid-feedback');
								element.closest('.form-group')
										.append(error);
							},
							highlight : function(element, errorClass,
									validClass) {
								$(element).addClass('is-invalid');
							},
							unhighlight : function(element, errorClass,
									validClass) {
								$(element).removeClass('is-invalid');
							}
						});
	});
	

		