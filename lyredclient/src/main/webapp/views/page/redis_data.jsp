<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="utf-8">
<body>
	<div class="table-responsive">
		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th>#</th>
					<th>名称</th>
					<th>类型</th>
					<th>大小</th>
					<th>Header</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>1,001</td>
					<td>Lorem</td>
					<td>ipsum</td>
					<td>dolor</td>
					<td>sit</td>
				</tr>
				<tr>
					<td>1,002</td>
					<td>amet</td>
					<td>consectetur</td>
					<td>adipiscing</td>
					<td>elit</td>
				</tr>
				<tr>
					<td>1,003</td>
					<td>Integer</td>
					<td>nec</td>
					<td>odio</td>
					<td>Praesent</td>
				</tr>
				<tr>
					<td>1,003</td>
					<td>libero</td>
					<td>Sed</td>
					<td>cursus</td>
					<td>ante</td>
				</tr>
				<tr>
					<td>1,004</td>
					<td>dapibus</td>
					<td>diam</td>
					<td>Sed</td>
					<td>nisi</td>
				</tr>
				<tr>
					<td>1,005</td>
					<td>Nulla</td>
					<td>quis</td>
					<td>sem</td>
					<td>at</td>
				</tr>
				<tr>
					<td>1,006</td>
					<td>nibh</td>
					<td>elementum</td>
					<td>imperdiet</td>
					<td>Duis</td>
				</tr>
				<tr>
					<td>1,007</td>
					<td>sagittis</td>
					<td>ipsum</td>
					<td>Praesent</td>
					<td>mauris</td>
				</tr>
				<tr>
					<td>1,008</td>
					<td>Fusce</td>
					<td>nec</td>
					<td>tellus</td>
					<td>sed</td>
				</tr>
				<tr>
					<td>1,009</td>
					<td>augue</td>
					<td>semper</td>
					<td>porta</td>
					<td>Mauris</td>
				</tr>
				<tr>
					<td>1,010</td>
					<td>massa</td>
					<td>Vestibulum</td>
					<td>lacinia</td>
					<td>arcu</td>
				</tr>
			</tbody>
		</table>
		<nav>
		<ul class="pagination">
			<li><a href="#" aria-label="Previous"> <span
					aria-hidden="true">&laquo;</span>
			</a></li>
			<li><a href="#">1</a></li>
			<li><a href="#">2</a></li>
			<li><a href="#">3</a></li>
			<li><a href="#">4</a></li>
			<li><a href="#">5</a></li>
			<li><a href="#" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
			</a></li>
		</ul>
		</nav>
	</div>
	<script type="text/javascript">
		$(function () {
			
			var selected = $('#keyTree').jstree(true).get_selected();
			var node = $('#keyTree').jstree(true).get_node (selected[0]);
			
			 $.ajax({
				type : 'POST',
				url : "../../redis/getDataList.do",
				data:{
					db:node.data.index
				},
				success : function(data) {
					$("#dataPage").html(data);
				},
				error : function(data) {
					alert("异常！");
					$("#dataPage").html(data.responseText);
				}
			});
		});
	</script>
</body>
</html>