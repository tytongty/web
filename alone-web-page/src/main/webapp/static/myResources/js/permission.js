$(function(){
		$.ajax({
			type:"post",
			url:'../permission/getRole',
			dataType:"json",
			success:function(data){
				if(data){
					$.each(data,function(i,n){
						$("#roleId").append("<option value='"+n.roleId+"'>"+n.roleName+"</option>")
					})
				}
			}
		});
		
	});
	  var zNodes ;
	 $("#searchId").click(function(){
		//通过ajax请求获取zNode节点
		$.ajax({
			type:"POST",
			data:{
				roleId:$("#roleId").val()
			},
			async:false,
			url:"../permission/getNodeList",
			dataType:"json",
			success:function(data){
				//$.fn.zTree.init($("#treeDemo"),setting,data);
				zNodes=data;
			}
		   
		});
		var setting = {
			view: {
				dblClickExpand: false,
				showLine: false,
				selectedMulti: false
			},
			data: {
				simpleData: {
					enable:true,
					idKey: "id",
					pIdKey: "parentId",
					rootPId: ""
				}
			},
			check: {
				enable: true,
				chkStyle: "checkbox",
				chkboxType: { "Y": "p", "N": "s" }
			}
			
		};
		$(document).ready(function(){
			var t = $("#treeDemo");
			t = $.fn.zTree.init(t, setting, zNodes);
		});
		 
	});
	 
	  $("#permissionEdit").click(function(){
		  var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		  var nodes = treeObj.getCheckedNodes(true);
		  var  param ={
				  children:[]
		  }
		  $.each(nodes,function(i,n){
			  var tree = new Object();
			if(n.parentId !=""){
				tree.id = n.id
				tree.parentId = n.parentId
				tree.name = n.name
				tree.checked = n.checked
				param.children.push(tree)
			}
		  })
		  var nodeTree = JSON.stringify(param.children)
			 $.ajax({
				 type:"post",
				 url:"../permission/edit",
				 data:{
					 nodeTree:nodeTree,
					 roleId:$("#roleId").val()
				 },
				 dataType:"json",
				 success:function(data){
					 layer.alert(data.msg)
				 }
			 })
		
		
	  })