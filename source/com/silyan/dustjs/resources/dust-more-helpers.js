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
	
	var contextPathI18N = function( chunk, context, bodies, params ) {
		var contextpath, language;
		params = params || {};
		if(dust.configuration == undefined || dust.configuration.parameters == undefined ) {
			return chunk.write("dust.configuration.parameters not initialised.");
		}
		contextpath = dust.configuration.parameters['contextpath'];
		if(contextpath == undefined) {
			contextpath = "not value for configuration parameter [contextpath]";
		}
		language = dust.configuration.parameters['language'];
		if(language == undefined) {
			language = "not value for configuration parameter [language]";
		}
		return chunk.write(contextpath + '/' + language);
	};
	dust.helpers.contextPathI18N = contextPathI18N;
	
	var ifEnvironment  = function( chunk, context, bodies, params ){
		var body = bodies.block;
		if( params && params.env) {
			if( params.env.split(',').indexOf(dust.configuration.parameters['environment']) != -1 ) {
				if(body) {
					return chunk.render( bodies.block, context );
				}
				else {
					_console.log( "Missing body block in the ifEnvironment helper!" );
					return chunk;
				}
			}
		}
		// no condition
		else {
			_console.log( "No environment list given in the ifEnvironment helper!" );
		}
		return chunk;
	};
	dust.helpers.ifEnvironment = ifEnvironment;


})(dust);
