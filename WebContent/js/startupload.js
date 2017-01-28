
    function start_upload()
    {
        var fup = document.getElementById('file_id');
	
	var lblError = document.getElementById('error');
	
        var fileName = fup.value;
        var ext = fileName.substring(fileName.lastIndexOf('.') + 1);

    if(ext =="XLS" || ext=="xls")
    {
	document.getElementById("hide").innerHTML = "";
	document.getElementById("error").innerHTML = "";
	document.getElementById("show").innerHTML = "File Upload in Progress... Please wait until the page is Re-Directed to Dashboard !";
        return true;
    }
    else
    {
       // alert("Upload Excel Files only");
	document.getElementById("error").innerHTML = "Please Upload Appropriate Excel File!";

       // return false;
    }
    
    function upload_alert(){
    	alert("File upload in progress!");
    }
	
   }

