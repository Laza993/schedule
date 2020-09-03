$(document).ready(function(){

    var linkLoggedTeacher = $("a.teacher");
    var linkLoggedStudent = $("a.student");
    var linkUnlogged = $("a.unlogged");
    var userName = $("p.userName");

    $.get("ScheduleServlet", function(answers){
        if(answers.logged){
            if(answers.user.role == "teacher"){
                linkLoggedTeacher.show();
                linkLoggedStudent.hide();
                linkUnlogged.hide();
                userName.text(answers.user.userName);
            }else{
                linkLoggedStudent.show();
                linkLoggedTeacher.hide();
                linkUnlogged.hide();
                userName.text(answers.user.userName);
            }

        }else{
            linkUnlogged.show();
            linkLoggedStudent.hide();
            linkLoggedTeacher.hide();
        }
    });
});