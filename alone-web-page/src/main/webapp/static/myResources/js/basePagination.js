//基本分页js
var basePage={
		intiPage:function(param){
			$.ajax({
				type:"post",
				url:param.url,
				dataType:"html",
				data:param.data,
				success:function(data){
					$("#"+param.tableList).empty();
					$("#"+param.tableList).html(data);
					basePage.pageLayer(param);
				}
			})
		},
		pageLayer:function(param){
			laypage({
				cont:param.pagination,
				pages:param.pageTotal,
				curr: param.data.currentPageNumber || 1,
				skin: '#00AA91',
				groups:3,
				skip:true,
				jump:function(obj,first){
					if(!first){
						param.data.currentPageNumber=obj.curr
						basePage.intiPage(param)
					}
				}
			})
		}
}