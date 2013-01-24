<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<title>Example DustJS web - {@confParam key="applicationVersion"/}</title>
	<link rel="shortcut icon" href="{@confParam key="contextpath"/}/favicon.ico" type="image/vnd.microsoft.icon" />
	<link rel="stylesheet" type="text/css" media="all" href="{@confParam key="contextpath"/}/styles.css" />
{+headCSSContent/}
{+headJSContent/}
</head>
<body>
	<header>
{+header/}
	</header>
	
	<div class="layout-content" >
{+pageContent/}
	</div>

	<div id="hidden-contents" style='display:none'>
{+hiddenContent/}
	</div>
	
	<footer>
{+footerContent}Footer / Example{/footerContent}
	</footer>

	<script type="text/javascript">
	var contextpath = '{@confParam key="contextpath"/}';
	</script>
	<script type="text/javascript" src="{@confParam key="contextpath"/}/example.js"></script>
{+footerJSContent/}
</body>
</html>