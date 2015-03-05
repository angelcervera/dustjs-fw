<!DOCTYPE html>{~n}
<html>{~n}
<head>{~n}
{~s}{~s}{~s}<meta charset="UTF-8" />{~n}
{~s}{~s}{~s}<title>Example DustJS web - {@confParam key="applicationVersion"/}</title>{~n}
{~s}{~s}{~s}<link rel="shortcut icon" href="{@confParam key="contextpath"/}/favicon.ico" type="image/vnd.microsoft.icon" />{~n}
{~s}{~s}{~s}<link rel="stylesheet" type="text/css" media="all" href="{@confParam key="contextpath"/}/styles.css" />{~n}
{+headCSSContent/}{~n}
{+headJSContent/}{~n}
</head>{~n}
<body>{~n}
{~s}{~s}{~s}<header>{~n}
{+header/}{~n}
{~s}{~s}{~s}</header>{~n}
{~s}{~s}{~s}{~n}
{~s}{~s}{~s}<div class="layout-content" >{~n}
{+pageContent/}{~n}
{~s}{~s}{~s}</div>{~n}
{~n}
{~s}{~s}{~s}<div id="hidden-contents" style='display:none'>{~n}
{+hiddenContent/}{~n}
{~s}{~s}{~s}</div>{~n}
{~s}{~s}{~s}{~n}
{~s}{~s}{~s}<footer>{~n}
{+footerContent}Footer / Example{/footerContent}{~n}
{~s}{~s}{~s}</footer>{~n}
{~n}
{~s}{~s}{~s}<script type="text/javascript">{~n}
{~s}{~s}{~s}var contextpath = '{@confParam key="contextpath"/}';{~n}
{~s}{~s}{~s}</script>{~n}
{~s}{~s}{~s}<script type="text/javascript" src="{@confParam key="contextpath"/}/example.js"></script>{~n}
{+footerJSContent/}{~n}
</body>{~n}
</html>