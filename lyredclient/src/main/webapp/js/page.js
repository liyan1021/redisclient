$(function () {
	$.fn.extend({
		"my_page" : function(form) {
			var $this = this;
			//定义分页结构体
			var pageinfo = {
				url : $(this).attr("url"),
				currentPage : $(this).attr("currentPage") * 1, // 当前页码
				pageCount : $(this).attr("pageCount") * 1,	  // 总页码
				pageSize : $(this).attr("pageSize")	  // 每页显示条数
			};

			if (pageinfo.pageCount < 2){
				return false;
			}
			//初始起始页数、结束页数
			var start = 0, end = 10;
			if (pageinfo.currentPage >= 10){
				start = pageinfo.currentPage - 5;
			}

			if (pageinfo.pageCount > pageinfo.currentPage + 5){
				end = pageinfo.currentPage + 5;
			}else{
				end = pageinfo.pageCount;
			}
			var html = [];
			html.push("<ul class='pagination'>");
			if (pageinfo.currentPage != 1){
				//如果不是第一页则有前一页
				html.push("<li class='page_prev'><a>前一页</a></li>");
			}
			if (pageinfo.pageCount > 10 && pageinfo.currentPage > 9){
				html.push("<li class='nomal'><a>1</a></li>");
			}
			for (var i = start; i < end; i++) {
				if ((i + 1) == pageinfo.currentPage){
					html.push("<li class='active'><a>" + (i + 1)+ "</a></li>");
				}else{
					html.push("<li class='nomal'><a>" + (i + 1)+ "</a></li>");
				}
			}
			if (pageinfo.pageCount > 10 && pageinfo.currentPage < pageinfo.pageCount - 4){
				html.push("<li class='nomal'><a>" + pageinfo.pageCount	+ "</a></li>");
			}
			if (pageinfo.currentPage != pageinfo.pageCount){
				html.push("<li class='page_next'><a>后一页</a></li>");
			}
			html.push("</ul>");

			$this.html(html);
			//绑定数据处理函数
			$this.find(".nomal a").bind("click", function() {
				redirectTo($(this).html());
			});
			$this.find(".page_prev a").bind("click", function() {
				redirectTo(pageinfo.currentPage - 1);
			});
			$this.find(".page_next a").bind("click", function() {
				redirectTo(pageinfo.currentPage + 1);
			});

			function redirectTo(pageNo) {

				var url = pageinfo.url;
				var pageSize= pageinfo.pageSize ; 
//				if (url.indexOf("?") == -1){
//					url += "?";
//				}else{
//					url += "&";
//				}
//				url += "page=" + page;
//				$("form[name='" + form + "']").attr("action", url);
//				$("form[name='" + form + "']").submit();
				var data = {};
			    var param = $("form[name='" + form + "']").serializeArray();
			    $.each(param, function() {
			    	data[this.name] = this.value;
			    });
			    data.pageSize = pageSize == null ? 10 : pageSize ;
			    data.pageNo = pageNo ;
			    
			    console.dir(JSON.stringify(data));
				$.ajax({
						type : 'POST',
						url : url,
						data:data,
						success : function(obj) {
							loadGrid(obj);
						},
						error : function(data) {
							alert("异常！");
							$("#dataPage").html(data.responseText);
						}
				 });
			}
			return $this;
		}
	});

});