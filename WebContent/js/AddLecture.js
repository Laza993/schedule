function checkValues(){
    var group = document.getElementById("group").value;
    var from = document.getElementById("from").value;
    var to = document.getElementById("to").value;
    var classroom = document.getElementById("classroom").value;
    var subject = document.getElementById("subject").value;
   
    if( group == "" || from == "" || to == "" || classroom == "" || subject == ""){
        alert("all fields are required");
        return false;
    }
    return confirm("confirm entery");
}


function backward(){
    return history.back();
}
