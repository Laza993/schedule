$(document).ready(function(){
    var userName;


    $.get("ViewAddLectureServlet", function(getAnswer){
        if(getAnswer.user == null){
            window.location.replace("Login.html");
        }
        userName = getAnswer.user.userName;
        $("#teacher").html(userName);
    })

    $("form").submit(function(){
        var group = $("#group").val();
        var from = $("#from").val();
        var to = $("#to").val();
        var classroom = $("#classroom").val();
        var subject = $("#subject").val();
        var day = $("#day option:selected").val();
        var teaching = $('input.radio:checked').val();
        var teacher = userName;

        var parameters = {
            "group" : group,
            "from" : from,
            "to" : to,
            "classroom" : classroom,
            "subject" : subject,
            "day" : day,
            "teaching" : teaching,
            "teacher" : teacher,
        }

        if( group == "" || from == "" || to == "" || classroom == "" || subject == "" || day == "" || teaching == "" || teacher == ""){
            alert("all fields are required");
            return false;
        }
        $.post("AddLectureServlet", parameters, function(answer){
            if(answer.status == "success"){
                window.location.replace("AllLectures.html");
            }else{
                alert("something went wrong" + answer.explanation)
                return false;
            }
        });
        return false;
    });

});

function backward(){return history.back()};
