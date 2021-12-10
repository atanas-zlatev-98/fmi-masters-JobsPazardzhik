$(document).ready(function(){
	
	var getLoggedUserId;
	
    function postJobs(title,field,position,expirience,suitable,place,description,jobId, userId){
                var cloneMe = $('#cloneMe').clone();

                cloneMe.find('.title-display').text(title);
                cloneMe.find('.field-display').text(field);
                cloneMe.find('.position-display').text(position);
                cloneMe.find('.expirience-display').text(expirience);
                cloneMe.find('.suitable-display').text(suitable);
                cloneMe.find('.location-display').text(place);
                cloneMe.find('.description-display').text(description);
              
               
                $('#jobs-list').prepend(cloneMe);

               
                
                cloneMe.show();
        }

    function getAllJobs(){
        $.ajax({
            url: "/jobs/all",
            method: "GET",
            success: function(data){
                data.forEach( function (jobs){
                    postJobs(jobs.title,jobs.field,jobs.position,jobs.expirience,jobs.suitable,jobs.place,jobs.description, jobs.id, jobs.user.id);
                });        			
            },
            fail: function(){
                alert("Failed to load offers!");
            }        		
        });
    }
    
    
    
    function getLoggedUser(){
        $.ajax({
            url: "/loggedUser",
            method : "GET",
            complete : function(data){
                switch(data.status){
                case 200:
                    getLoggedUserId = data.responseJSON;
                    break;
                    
                case 401:
                    window.location.href = "index.html";
                    break;
                }
            }, fail : function(){
                window.location.href = "home.html";
            }
        });
    }
    
    $('#logout').on('click', function(){
        
        $.ajax({
            url: "logout",
            method: "POST",
            complete: function(data){
                if(data.status == 418){
                    alert(data);
                }
                
                window.location.href = "index.html";
            }        		
        });
        
    });       
    

    
    getLoggedUser();
    getAllJobs();
});


