$(document).ready(function(){
	
	var loggedUserId;
	document.querySelector("#addPerson").addEventListener('submit', addPerson);
        
        function addPerson(e){
        	e.preventDefault();
        	title = $('#title').val();
		    field = $('#field').val();
		    position = $('#position').val();
		    expirience = $('#expirience').val();
		    suitable = $('#suitable').val();
		    place = $('#place').val();
		    description = $('#description').val();
		    
		    
		    $.ajax({
        		url : "/jobs/add",
        		method : "POST",
        		data : {
        			title : title,
        			field :field,
        			position : position,
        			expirience : expirience,
        			suitable : suitable,
        			place : place,
        			description : description
        		},
        		
        		success : function(data){
        			if(data.includes ("Error:")){
        				alert(data);
        			}else{
        				postJobs(title,field,position,expirience,suitable,place,description,data);
        			}        			
        		},
        		fail: function(){
        			alert("Ерор");
        		}   
        	});
        }
	
	
	    function deleteJob(cloneMe, id){
        	$.ajax({
        		url: "/jobs/delete",
        		method: "DELETE",
        		data: {
        			id: id
        			},
       			complete : function(data){
       				
       				switch(data.status){
       				
       				case 200:
       					cloneMe.remove();
       					break;
       					
       				case 404:       					
       					alert("The Job Offer Was Not Found!");
       					cloneMe.remove();       					
       					break;       				
       				}       				
       			}
        	});
        }
	
    function postJobs(title,field,position,expirience,suitable,place,description,jobId, userId){
                var cloneMe = $('#cloneMe').clone();

                cloneMe.find('.jobs-id').text(userId);
                cloneMe.find('.title-display').text(title);
                cloneMe.find('.field-display').text(field);
                cloneMe.find('.position-display').text(position);
                cloneMe.find('.expirience-display').text(expirience);
                cloneMe.find('.suitable-display').text(suitable);
                cloneMe.find('.location-display').text(place);
                cloneMe.find('.description-display').text(description);
                 
               	   cloneMe.find('.remove-post').click(function(){
                          deleteJob(cloneMe, jobId);
                      });
               
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
                alert("Comments failed to load!");
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
   
        function getLoggedUser(){
        	$.ajax({
        		url: "/loggedUser",
        		method : "GET",
        		complete : function(data){
        			switch(data.status){
        			case 200:
        				loggedUserId = data.responseJSON;
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

    
    getLoggedUser();
    getAllJobs();
});


