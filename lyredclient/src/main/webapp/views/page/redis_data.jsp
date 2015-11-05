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
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="gridData">
			</tbody>
		</table>
		<div class="pagination" id="pagination" url="../../redis/getDataList.do">
		</div>
		<!-- 查看详细dialog -->
		<div class="modal fade" id="detailDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">详情</h4>
					</div>
					<form>
						<div class="modal-body">
	
							<div class="form-group">
								<label for="exampleInputEmail1">key</label> 
								<input class="form-control" id="keyName" readonly="readonly">
							</div>
							<div class="form-group">
								<label for="exampleInputPassword1">value</label> 
								<input class="form-control" id="valueName" >
							</div>
							<div class="form-group">
								<label for="exampleInputPassword1">ttl</label> 
								<input class="form-control" id="ttlName" >
							</div>
	
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
							<button type="submit" class="btn btn-primary" onclick="updateDetail()">确定</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		var pageNo = 1 ;  // 默认第一页
		var pageSize = 20 ; 
		$(function () {
			initPage();	
		});
		function loadGrid(obj){
			var obj = eval('(' + obj + ')');
			var list = obj.resultList;
			var htmlStr ="";
			$.each(list,function(index,data){
				
				htmlStr = htmlStr + "<tr><td>" + index + "</td><td><a href='#' onclick=openDetail('"+data.key+"')>" + data.key
								+ "</a></td><td>" + data.type + "</td><td>" + data.size
								+ "</td><td>" + data.encoding + "</td><td><a href='#' onclick=deleteKey('"+data.key+"')>删除</a></td></tr>";
			});
			if (htmlStr == "") {
				htmlStr = "<tr><td colspan='6'>该数据库下没有任何元素</td></tr>";
			}

			$("#pagination").attr("currentPage", obj.pageNo);
			$("#pagination").attr("pageCount", obj.pageCount);
			$("#pagination").attr("pageSize", obj.pageSize);
			$("#gridData").html(htmlStr);
			$(".pagination").my_page("serchform"); //当页面分页属性设置完毕后 加载分页
		}
		function initPage(){
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
		}
		function openDetail(key){
			 $.ajax({
					type : 'POST',
					url : "../../redis/getDetail.do",
					data:{
						key:key
					},
					success : function(obj) {
						var obj = eval('(' + obj + ')');
						$('#keyName').val(key);		
						$('#valueName').val(obj.value);
						$('#ttlName').val(obj.ttl);
						$('#detailDialog').modal();
					},
					error : function(data) {
						alert("异常！");
						$("#dataPage").html(data.responseText);
					}
				});	
		}
		function updateDetail(){
			alert('保存');
		}
		function deleteKey(key){
			$.ajax({
				type : 'POST',
				url : "../../redis/deleteKey.do",
				data:{
					key:key
				},
				success : function(obj) {
					alert('删除');	
					initPage();
				},
				error : function(data) {
					alert("异常！");
					$("#dataPage").html(data.responseText);
				}
			});	
		}
	</script>
</body>
</html>