<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../basicPage/basic.jsp" %>
</head>
<body>
<%@ include file="../basicPage/nav.jsp" %>
	<div class="page-container">
		<table class="table">
	     <tr>
		<td width="200" class="va-t"><ul id="treeDemo" class="ztree"></ul></td>
		<td class="va-t"><iframe ID="testIframe" Name="testIframe" FRAMEBORDER=0 SCROLLING=AUTO width=100%  height=390px SRC="product-category-add.html"></iframe></td>
	   </tr>
    </table>
	</div>
	

<script type="text/javascript">
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
				pIdKey: "pId",
				rootPId: ""
			}
		},
		check: {
			enable: true,
			chkStyle: "checkbox",
			chkboxType: { "Y": "ps", "N": "ps" }
		},
		callback: {
			beforeClick: function(treeId, treeNode) {
				var zTree = $.fn.zTree.getZTreeObj("tree");
				if (treeNode.isParent) {
					zTree.expandNode(treeNode);
					return false;
				} else {
					demoIframe.attr("src",treeNode.file + ".html");
					return true;
				}
			}
		}
	};

	var zNodes =[
		{ id:1, pId:0, name:"一级分类", open:true},
		{ id:11, pId:1, name:"二级分类"},
		{ id:111, pId:11, name:"三级分类"},
		{ id:112, pId:11, name:"三级分类"},
		{ id:113, pId:11, name:"三级分类"},
		{ id:114, pId:11, name:"三级分类"},
		{ id:115, pId:11, name:"三级分类"},
		{ id:12, pId:1, name:"二级分类 1-2"},
		{ id:121, pId:12, name:"三级分类 1-2-1"},
		{ id:122, pId:12, name:"三级分类 1-2-2"},
	];
			
	var code;
			
	function showCode(str) {
		if (!code) code = $("#code");
		code.empty();
		code.append("<li>"+str+"</li>");
	}
			
	$(document).ready(function(){
		var t = $("#treeDemo");
		t = $.fn.zTree.init(t, setting, zNodes);
		demoIframe = $("#testIframe");
		//demoIframe.on("load", loadReady);
		var zTree = $.fn.zTree.getZTreeObj("tree");
		//zTree.selectNode(zTree.getNodeByParam("id",'11'));
	});
</script>

