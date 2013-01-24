(function(dust){
	
	var i18n = function( chunk, context, bodies, params ) {
		var key, value;
		params = params || {};
		key = params.key;
		if(dust.configuration == undefined || dust.configuration.i18n == undefined) {
			return chunk.write("dust.configuration.i18n not initialised [" + key + "].");
		}
		value = dust.configuration.i18n[key];
		if(value == undefined) {
			value = "not value for [" + key + "]";
		}
		return chunk.write(value);
	};
	dust.helpers.i18n = i18n;
	
	var confParam = function( chunk, context, bodies, params ) {
		var key, value;
		params = params || {};
		key = params.key;
		if(dust.configuration == undefined || dust.configuration.parameters == undefined ) {
			return chunk.write("dust.configuration.parameters not initialised [" + key + "].");
		}
		value = dust.configuration.parameters[key];
		if(value == undefined) {
			value = "not value for configuration parameter [" + key + "]";
		}
		return chunk.write(value);
	};
	dust.helpers.confParam = confParam;

})(dust);
