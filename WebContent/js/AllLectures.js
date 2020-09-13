$(document).ready(function(){

    var mainTable = $("table.mainTable");
    var headingTrow = $("tr.mainTable");

    $("form.searchForm").submit(function(){
        var day = $("#days option:selected").val();
        if(day == "All"){
            window.location.replace("AllLectures.html")
        }
        var parameters = {
            "day" : day
        }
        $.post("SearchByDayServlet", parameters, function myPostFunction(postAnswer){
            var userRolePost;
            console.log(postAnswer);

            if(postAnswer.status === "success"){
                mainTable.find("tr:gt(0)").remove();
                userRolePost = postAnswer.loggedUser.role;
                if(userRolePost == "teacher"){
                    headingTrow.append(
                        "<th>Action</th>"
                    )
                    for(var it in postAnswer.lectures){
                        var itLecture = postAnswer.lectures[it];                
                        mainTable.append("<tr>" + 
                        "                    <td>" + itLecture.day + "</td>" + 
                        "                    <td>"+ itLecture.group +"</td>" + 
                        "                    <td>"+ itLecture.from +"</td>" + 
                        "                    <td>"+ itLecture.to +"</td>" + 
                        "                    <td>"+ itLecture.classroom +"</td>" + 
                        "                    <td>"+ itLecture.teaching.name +"</td>" + 
                        "                    <td>"+ itLecture.subject +"</td>" + 
                        "                    <td>"+ itLecture.teacher +"</td>"+
                        "                    <td><a href='EditLecture.html?viewId="+ itLecture.id +"'>Edit</a></td>" +
                        "</tr>"
                        )
                    }
                }else{
                    for(var it in postAnswer.lectures){
                        var itLecture = postAnswer.lectures[it];                
                        mainTable.append("<tr>" + 
                        "                    <td>" + itLecture.day + "</td>" + 
                        "                    <td>"+ itLecture.group +"</td>" + 
                        "                    <td>"+ itLecture.from +"</td>" + 
                        "                    <td>"+ itLecture.to +"</td>" + 
                        "                    <td>"+ itLecture.classroom +"</td>" + 
                        "                    <td>"+ itLecture.teaching.name +"</td>" + 
                        "                    <td>"+ itLecture.subject +"</td>" + 
                        "                    <td>"+ itLecture.teacher +"</td>"+
                        "</tr>"
                        )
                    }
                }    
                $("#theoryClassNum").html(postAnswer.theoryCounter);
                $("#practiceClassNum").html(postAnswer.practiceCounter);
            }else{
                if(postAnswer.loggedUser == null){
                    window.location.replace("Login.html")
                }
                alert("failed to fetch lectures!")
            }
        })
        return false;
    })

    
    
    $.get("AllLecturesServlet", function(answer){
        console.log(answer);
        var userRole;

        if(answer.status == "success"){
            mainTable.find("tr:gt(0)").remove();
            userRole = answer.loggedUser.role;

            if(userRole == "teacher"){
                headingTrow.append(
                    "<th>Action</th>"
                )
                for(var it in answer.lectures){
                    var itLecture = answer.lectures[it];                
                    mainTable.append("<tr>" + 
                    "                    <td>" + itLecture.day + "</td>" + 
                    "                    <td>"+ itLecture.group +"</td>" + 
                    "                    <td>"+ itLecture.from +"</td>" + 
                    "                    <td>"+ itLecture.to +"</td>" + 
                    "                    <td>"+ itLecture.classroom +"</td>" + 
                    "                    <td>"+ itLecture.teaching.name +"</td>" + 
                    "                    <td>"+ itLecture.subject +"</td>" + 
                    "                    <td>"+ itLecture.teacher +"</td>"+
                    "                    <td><a href='EditLecture.html?viewId="+ itLecture.id +"'>Edit</a></td>" +
                    "</tr>"
                    )
                }
            }else{
                for(var it in answer.lectures){
                    var itLecture = answer.lectures[it];                
                    mainTable.append("<tr>" + 
                    "                    <td>" + itLecture.day + "</td>" + 
                    "                    <td>"+ itLecture.group +"</td>" + 
                    "                    <td>"+ itLecture.from +"</td>" + 
                    "                    <td>"+ itLecture.to +"</td>" + 
                    "                    <td>"+ itLecture.classroom +"</td>" + 
                    "                    <td>"+ itLecture.teaching.name +"</td>" + 
                    "                    <td>"+ itLecture.subject +"</td>" + 
                    "                    <td>"+ itLecture.teacher +"</td>"+
                    "</tr>"
                    )
                }
            }
            


            $("#theoryClassNum").html(answer.theoryCounter);
            $("#practiceClassNum").html(answer.practiceCounter);
        }else{
            if(answer.loggedUser == null){
                window.location.replace("Login.html")
            }
            alert("failed to fetch lectures")
        }

        
    });
    $("button.backward").click(function(){
        return history.back();
    });
});

function confirmDeletion(){
    return confirm("are you really want to delete lecture?");
}



