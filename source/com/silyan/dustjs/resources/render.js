function renderDustJS(renderObj) {
	dust.configuration = JSON.parse(renderObj.configuration);
	dust.render(renderObj.name, dust.configuration.renderJSON, function(err, out) {
		if(err!=undefined) {
			renderObj.err = err;
		} else {
			renderObj.rendered = out;
		}
	} );
}
