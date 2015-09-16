<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="utf-8">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>template</title>

<!-- Bootstrap core CSS -->
<link href="../../css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="../../css/dashboard.css" rel="stylesheet">
<link href="../../css/style.min.css" rel="stylesheet">

</head>
<body>
	<!-- 导航栏 -->
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">redisClient</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#">搜索</a></li>
					<li><a href="#" data-toggle="modal" data-target="#connDialog">创建连接</a></li>
					<!-- 
					<li><a href="#">按钮3</a></li>
					<li><a href="#">按钮4</a></li> -->
				</ul>
				<form class="navbar-form navbar-right">
					<input type="text" class="form-control" placeholder="Search...">
				</form>
			</div>
		</div>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<div id="keyTree">
				</div>
				<a href="#" onclick="redisDataPage()">asdasd</a>
			</div>
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main" id="dataPage">
			
		</div>
		</div>
	</div>





	<!-- Modal -->
	<div class="modal fade" id="connDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">创建连接 </h4>
				</div>
				<form>
					<div class="modal-body">

						<div class="form-group">
							<label for="exampleInputEmail1">Email address</label> <input
								type="email" class="form-control" id="exampleInputEmail1"
								placeholder="Email">
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1">Password</label> <input
								type="password" class="form-control" id="exampleInputPassword1"
								placeholder="Password">
						</div>
						<div class="form-group">
							<label for="exampleInputFile">File input</label> <input
								type="file" id="exampleInputFile">
							<p class="help-block">Example block-level help text here.</p>
						</div>
						<div class="checkbox">
							<label> <input type="checkbox"> Check me out
							</label>
						</div>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="submit" class="btn btn-primary">确定</button>
					</div>
				</form>
			</div>
		</div>
	</div>




	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="../../js/jquery-1.11.3.min.js"></script>
	<script src="../../js/bootstrap.min.js"></script>
	<script src="../../js/jstree.min.js"></script>
	<script type="text/javascript">

		$('#keyTree').jstree({ 'core' : {
		    'data' : [
		       { "id" : "ajson1", "parent" : "#", "text" : "连接1" },
		       { "id" : "ajson2", "parent" : "#", "text" : "连接2" },
		       { "id" : "ajson3", "parent" : "ajson2", "text" : "db0" },
		       { "id" : "ajson4", "parent" : "ajson2", "text" : "db1" },
		       { "id" : "ajson5", "parent" : "ajson2", "text" : "db2" },
		       { "id" : "ajson6", "parent" : "ajson2", "text" : "db3" },
		       { "id" : "ajson7", "parent" : "ajson2", "text" : "db4" },
		       { "id" : "ajson8", "parent" : "ajson2", "text" : "db5" },
		       { "id" : "ajson9", "parent" : "ajson2", "text" : "db6" },
		       { "id" : "ajson10", "parent" : "ajson2", "text" : "db7" },
		       { "id" : "ajson11", "parent" : "ajson2", "text" : "db8" },
		       { "id" : "ajson12", "parent" : "ajson2", "text" : "db9" },
		    ]
		} });
		function redisDataPage(){

			$.ajax({
				type : 'POST',
				url : "../../RedisClientAction",
				success :function(data){
					console.dir(data);
					$("#dataPage").html(data);
				},
				error : function(data) {  
				   console.dir(data);
				   alert("异常！");  
				   $("#dataPage").html(data.responseText);
				}  
			});
		}
	</script>
</body>
</html>
