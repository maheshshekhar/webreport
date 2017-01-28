<script type="text/javascript">
    function start_upload()
    {
        var fup = document.getElementById('file_id');
	document.write(fup);
        var fileName = fup.value;
        var ext = fileName.substring(fileName.lastIndexOf('.') + 1);

    if(ext =="XLS" || ext=="xls")
    {
        return true;
    }
    else
    {
        alert("Upload Excel Files only");
        return false;
    }
    }
</script>
