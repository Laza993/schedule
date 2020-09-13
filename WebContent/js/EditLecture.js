$(document).ready(function(){


    var url = new URL(window.location.href);
    const attrName = "viewId";
    var param = url.searchParams.get(attrName);
    var parameters = {"viewId" : param};


    var groupField = $("input[name=group]");
    var fromField = $("input[name=from]");
    var toField = $("input[name=to]");
    var classroomField = $("input[name=classroom]");
    var subjectField = $("input[name=subject]");
    var teacherField = $("input[name=teacher]");


    $.get("ViewLectureServlet", parameters, function(answer){
        console.log(answer);
        if(answer.status == "success"){
            $("#group").val(answer.lecture.group);
            fromField.val(answer.lecture.from);
            toField.val(answer.lecture.to);
            classroomField.val(answer.lecture.classroom);
            subjectField.val(answer.lecture.subject);
            teacherField.val(answer.lecture.teacher);
            $("#dayDiv select").val(answer.lecture.day);
            var radioValue = answer.lecture.teaching.name;
            $('#' + radioValue).prop('checked',true);
        }else if(answer.loggedUser == null){
            window.location.replace("Login.html");
        }else{
            alert("failed to fetch lecture");
            window.location.replace("AllLecture.html");
        }

    });

    $("form").submit(function(){
        var group = groupField.val();
        var from = fromField.val();
        var to = toField.val();
        var clasroom = classroomField.val();
        var subject = subjectField.val();
        var teacher = teacherField.val();
        var day = $("#dayDiv option:selected").val();
        var ediTid = param;
        var teaching = $("input.radio:checked").val();

        var postParams = {
            "group" : group,
            "from" : from,
            "to" : to,
            "clasroom" : clasroom,
            "subject" : subject,
            "day" : day,
            "teaching" : teaching,
            "teacher" : teacher,
            "ediTid" : ediTid
        }
        console.log(postParams);

        if( group == "" || from == "" || to == "" || clasroom == "" || subject == "" || day == "" || teaching == "" || teacher == ""){
            alert("all fields are required");
            return false;
        }

        $.post("EditLectureServlet", postParams, function(postAnswer){
            if(postAnswer.status == "success"){
                window.location.replace("AllLectures.html");
            }else{
                if(postAnswer.loggedUser == null){
                    window.location.replace("Login.html");
                }else{
                    alert("failed to update Lecture: " + postAnswer.explanation);
                    return false;
                }
            }
        });
        return false;
    })



// validation
    var notValidGroupField = $("p.notValidGroup");
    var notValidFromField = $("p.notValidFrom");
    var notValidToField = $("p.notValidTo");
    var notValidClassroomField = $("p.notValidClassroom");
    var notValidSubjectField = $("p.notValidSubject");

    notValidGroupField.hide();
    notValidFromField.hide();
    notValidToField.hide();
    notValidClassroomField.hide();
    notValidSubjectField.hide();

    groupField.blur(function(){
        console.log("blur");
        if(groupField.val() == ""){
            groupField.addClass("invalid");
            groupField.addClass("emptyField");
            notValidGroupField.slideDown(1500);
        }
    });

    groupField.focus(function(){
        console.log("focus");
        groupField.removeClass("invalid");
        groupField.removeClass("emptyField");
        notValidGroupField.slideUp(1500);
    });

    fromField.blur(function(){
        console.log("blur");
        if(fromField.val() == ""){
            fromField.addClass("invalid");
            fromField.addClass("emptyField");
            notValidFromField.slideDown(1500);
        }
    });

    fromField.focus(function(){
        console.log("focus");
        fromField.removeClass("invalid");
        fromField.removeClass("emptyField");
        notValidFromField.slideUp(1500);
    });

    toField.blur(function(){
        console.log("blur");
        if(toField.val() == ""){
            toField.addClass("invalid");
            toField.addClass("emptyField");
            notValidToField.slideDown(1500);
        }
    });

    toField.focus(function(){
        console.log("focus");
        toField.removeClass("invalid");
        toField.removeClass("emptyField");
        notValidToField.slideUp(1500);
    });
    

    classroomField.blur(function(){
        console.log("blur");
        if(classroomField.val() == ""){
            classroomField.addClass("invalid");
            classroomField.addClass("emptyField");
            notValidClassroomField.slideDown(1500);
        }
    });

    classroomField.focus(function(){
        console.log("focus");
        classroomField.removeClass("invalid");
        classroomField.removeClass("emptyField");
        notValidClassroomField.slideUp(1500);
    });
    

    subjectField.blur(function(){
        console.log("blur");
        if(subjectField.val() == ""){
            subjectField.addClass("invalid");
            subjectField.addClass("emptyField");
            notValidSubjectField.slideDown(1500);
        }
    });

    subjectField.focus(function(){
        console.log("focus");
        subjectField.removeClass("invalid");
        subjectField.removeClass("emptyField");
        notValidSubjectField.slideUp(1500);
    });

    teacherField.click(function(){
        teacherField.attr("disabled", true);
    })

    // other methods

    $("button.backward").click(function(){
        return history.back();
    });

});