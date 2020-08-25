$(document).ready(function(){

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

    var groupField = $("input[name=group]");

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

    var fromField = $("input[name=from]");

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



    var toField = $("input[name=to]");

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

    var classroomField = $("input[name=classroom]");

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
    
    var subjectField = $("input[name=subject]");

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

    var teacherField = $("input[name=teacher]");
    teacherField.click(function(){
        teacherField.attr("disabled", true);
    })
});