<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>显示/隐藏菜单 - Powered By ${systemConfig.systemName}</title>
<meta name="Author" content="SHOP++ Team" />
<meta name="Copyright" content="SHOP++" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "../include.ftl">
<link href="${base!}/assets/admin/css/middle.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
$().ready(function() {
	$(".main").click( function() {
		var mainFrameset = window.parent.window.document.getElementById("mainFrameset");
		if(mainFrameset.cols == "130,6,*") {
			mainFrameset.cols = "0,6,*";
			$(".main").removeClass("leftArrow");
			$(".main").addClass("rightArrow");
		} else {
			mainFrameset.cols = "130,6,*";
			$(".main").removeClass("rightArrow");
			$(".main").addClass("leftArrow");
		}
	})
})
</script>
</head>
<body class="middle">
	<div class="main leftArrow"></div>
</body>
</html>