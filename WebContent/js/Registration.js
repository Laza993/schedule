$(document).ready(function(){
    var usernameField = $("input[name=username1]");
    var password1Field = $("input[name=password1]");
    var password2Field = $("input[name=password2]");

    const regex = "^[a-zA-Z0-9]+$";

    $("form").submit(function(){
        var username = usernameField.val();
        var passsword1 = password1Field.val();
        var passsword2 = password2Field.val();
        var role = $("#roles option:selected").text();
        

        if(username == "" || passsword1 == "" || passsword2 == ""){
            alert("all fields are required!")
            return false;
        }
        if(passsword1 !== passsword2){
            alert("password mismatch!")
            password1Field.val("");
            password1Field.next().text("Please fill in password!");
            password1Field.next().css("background", "red");
            password2Field.val("");
            password2Field.next().text("Please fill in password!");
            password2Field.next().css("background", "red");
            return false;
        }
        var parameters = {
            "username1" : username,
            "password1" : passsword1,
            "password2" : passsword2,
            "role" : role
        }

        $.post("RegisterServlet", parameters, function(answer){
            if(answer.status == "success"){
                window.location.replace("Login.html");
            }else{
                alert("Registration failed! " + answer.explanation)
                return false;
            }
        });
        return false;
    });


    usernameField.blur(function(){
        var blurValues = usernameField.val();
        if(blurValues == ""){
            usernameField.next().text("Please fill in username!");
        }else{
            usernameField.next().text("");
        }
    });

    password1Field.blur(function(){
        var blurpassword1 = password1Field.val();
        if(blurpassword1 == ""){
            password1Field.next().text("Please fill in password!");
            password1Field.next().css("background", "red");
        }else if(blurpassword1.length < 8){
            password1Field.next().text("too short 8 characters required!");
            password1Field.next().css("background", "red");
        }else if(blurpassword1.match(regex) == null){
            password1Field.val("");
            alert("Wrong format, password should contain alphanumeric characters only!");
            password1Field.next().text("Please fill in password!");
            password1Field.next().css("background", "red");
        }else{
            password1Field.next().text("");
        }
    });

    password2Field.blur(function(){
        var blurpassword2 = password2Field.val();
        if(blurpassword2 == ""){
            password2Field.next().text("Please fill in password!");
            password2Field.next().css("background", "red");
        }else if(blurpassword2.length < 8){
            password1Field.next().text("too short 8 characters required!");
            password1Field.next().css("background", "red");
        }else if(blurpassword2.match(regex) == null){
            password2Field.val("");
            alert("Wrong format, password should contain alphanumeric characters only!");
            password2Field.next().text("Please fill in password!");
            password2Field.next().css("background", "red");
        }else{
            password2Field.next().text("");
        }
    });
});

