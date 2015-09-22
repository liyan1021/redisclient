<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=8">
<meta http-equiv="Expires" content="0">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-control" content="no-cache">
<meta http-equiv="Cache" content="no-cache">
<body>
	<div class="table-responsive">
		<form name="serchform">
			<input type="hidden" id="db" name="db" value=0>
		</form>
		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th>#</th>
					<th>名称</th>
					<th>类型</th>
					<th>大小</th>
					<th>Encoding</th>
				</tr>
			</thead>
			<tbody id="gridData">
			</tbody>
		</table>
		<div class="pagination" id="pagination" url="../../redis/getDataList.do">
		</div>	
	</div>
	<script type="text/javascript">
		var pageNo = 1 ;  // 默认第一页
		var pageSize = 20 ; 
		$(function () {
			var selected = $('#keyTree').jstree(true).get_selected();
			var node = $('#keyTree').jstree(true).get_node (selected[0]);
			//设置隐藏域属性
			$("#db").val(node.data.index);
			 $.ajax({
				type : 'POST',
				url : "../../redis/getDataList.do",
				data:{
					db:node.data.index,
					pageSize : pageSize ,
					pageNo : pageNo
				},
				success : function(obj) {
					loadGrid(obj);
				},
				error : function(data) {
					alert("异常！");
					$("#dataPage").html(data.responseText);
				}
			});	
		});
		function loadGrid(obj){
			var obj = eval('(' + obj + ')');
			var list = obj.resultList;
			var htmlStr ="";
			$.each(list,function(index,data){
				htmlStr = htmlStr + "<tr><td>"+index+"</td><td>"+data.key+"</td><td>"+data.type+"</td><td>"+data.size+"</td><td>"+data.encoding+"</td></tr>";
			});
			if(htmlStr ==""){
				htmlStr = "<tr><td  colspan='5'>该数据库下没有任何元素</td></tr>" ;
			}
			
			$("#pagination").attr("currentPage",obj.pageNo);
			$("#pagination").attr("pageCount",obj.pageCount);
			$("#pagination").attr("pageSize",obj.pageSize);
			$("#gridData").html(htmlStr);
			$(".pagination").my_page("serchform");   //当页面分页属性设置完毕后 加载分页
		}
	</script>
</body>
</html>