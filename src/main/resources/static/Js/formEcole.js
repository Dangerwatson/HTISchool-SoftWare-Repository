$(document).ready(function(){
    //var firstName = document.getElementById("firstName"),
               var lastName = document.getElementById("nomEtablissement");
               phone = document.getElementById("telEcole"),
                Email = document.getElementById("emailEcole"),
                 adresse = document.getElementById("adresseEtab"),              
                 
               
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
   // validate(firstName)
    validate(lastName)
    validate(phone)
    validate(Email)
    validate(adresse)
   
    

               

    
});