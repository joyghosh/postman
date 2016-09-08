<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Postman template page</title>
		<script type="text/javascript">
			if(window.File && window.FileReader && window.FileList && window.Blob){
				document.getElementById("filePicker").addEventListener('change', handleFileSelect, false);
			}else{
				document.write("File api not supported!");
			}
		</script>
	</head>
<body>
	<form method="POST" action="rest/producer/enqueue" id="emailForm">
		<input type="hidden" value="bruce.wayne@acne.com" name="from"/>
		<div>
			<label>To:</label>
			<input type="text" name="to">
		</div>
		<div>
			<label>Cc:</label>
			<input type="text" name="cc">
		</div>
		<div>
			<label>Bcc:</label>
			<input type="text" name="bcc">
		</div>
		<div>
			<label>subject:</label>
			<input type="text" name="subject">
		</div>
		<div>
			<label>Content:</label>
			<textarea rows="10" cols="70" name="content" form="emailForm"></textarea>
		</div>
		<div>
			<label>attachments:</label>
			<input type="file" id="filePicker">
		</div>
		<input type="submit" value="Send"/>
	</form>
</body>
</html>
