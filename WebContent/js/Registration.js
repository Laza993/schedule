const regex = "^[a-zA-Z0-9]+$";


function fillIn(username1, password1, password2){
    var usr = username1.value;
    var pass1 = password1.value;
    var pass2 = password2.value;
    
    if(usr == "" || pass1 == "" || pass2 == ""){
        alert("all fields are required!")
        return false;
    }
    if(pass1.match(regex) == null || pass2.match(regex) == null){
        alert("password should contain alphanumeric characters")
        return false;
    }
    if(pass1 != pass2){
        alert("password missmatch")
        return false;
    }
    return true;
}


function emptyField(field){
    var values = field.value;
    if(values == ""){
        field.parentNode.nextSibling.innerHTML = "Please fill in all required fields!";
    }
}
function filledField(filled){
        filled.parentNode.nextSibling.innerHTML = "";
}