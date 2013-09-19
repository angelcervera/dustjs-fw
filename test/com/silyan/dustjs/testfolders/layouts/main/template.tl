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
	<script type="text/javascript" src="{@confParam key="contextpath"/}/{@confParam key="language"/}/example.js"></script>
	<script type="text/javascript" src="{@contextPathI18N/}/example3.js"></script>
	{@ifEnvironment env="test,design"}
	<!-- ENVIROMENT test or desing -->
	{/ifEnvironment}
{+footerJSContent/}
</body>
</html>