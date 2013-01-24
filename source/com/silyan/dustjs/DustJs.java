/**
 * 
 */
package com.silyan.dustjs;

import java.io.IOException;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.silyan.dustjs.model.PageRendered;
import com.silyan.dustjs.model.Template;
import com.silyan.dustjs.utils.IOUtils;

/**
 * DustJS commands.
 * Complete cicly for correct use:
 * 1.- Compile templates.
 * 2.- Load compiled templates in DustJS scope.
 * 3.- Render pages with different parameters.
 * 
 * @author Angel Cervera Claudio ( angelcervera@silyan.com )
 *
 */
public class DustJs {
	
	private static String dustjs;
	private static String renderjs;
	private static String json2js;
	
	{
		try {
			dustjs =          IOUtils.toStringFromClassPath("com/silyan/dustjs/resources/dust.js");
			dustjs = dustjs + IOUtils.toStringFromClassPath("com/silyan/dustjs/resources/compiler.js");
			dustjs = dustjs + IOUtils.toStringFromClassPath("com/silyan/dustjs/resources/parser.js");
			dustjs = dustjs + IOUtils.toStringFromClassPath("com/silyan/dustjs/resources/dust-helpers-1.1.0.js");
			dustjs = dustjs + IOUtils.toStringFromClassPath("com/silyan/dustjs/resources/dust-more-helpers.js");
			json2js = IOUtils.toStringFromClassPath("com/silyan/dustjs/resources/json2.js");
			renderjs = IOUtils.toStringFromClassPath("com/silyan/dustjs/resources/render.js");
		} catch (IOException e) {
			throw new RuntimeException("Can't load js files", e);
		}
	}
	
    private ScriptEngineManager manager;
    private ScriptEngine engine;
	
	public DustJs() throws ScriptException {
		super();
		
        manager = new ScriptEngineManager();
        engine = manager.getEngineByName("JavaScript");
        engine.eval(dustjs);
        engine.eval(json2js);
        engine.eval(renderjs);
	}

	/**
	 * Compile templates.
	 * 
	 * @param is
	 * @param os
	 */
	public void compile(List<Template> templates) throws ScriptException {
        for (Template template : templates) {
			compile(template);
		}
	}
	
	/**
	 * Compiles one template.
	 * Template compiled is stored in "compiled" property.
	 *  
	 * @param template
	 * @return
	 * @throws ScriptException
	 */
	public String compile(Template template) throws ScriptException {
		engine.put("template", template.getTemplate() );
		engine.put("name", template.getName() );
		template.setCompiled((String) engine.eval("dust.compile(\"\" + template + \"\", name)"));
		engine.eval("delete template;");
		engine.eval("delete name;");
		return template.getCompiled();
	}
	
	/**
	 * Load resources.
	 * 
	 * @param templates
	 * @throws ScriptException
	 */
	public void loadSource(List<Template> templates) throws ScriptException {
        for (Template template : templates) {
        	loadSource(template);
		}
	}
	
	/**
	 * Load template in DustJS scope to be used.
	 * 
	 * @param template
	 * @throws ScriptException
	 */
	public void loadSource(Template template) throws ScriptException {
		if(template.getCompiled() != null) {
			engine.put("compiled", template.getCompiled() );
			engine.eval("dust.loadSource(compiled);");
			engine.eval("delete compiled;");
		}
	}
	
	/**
	 * Render templates.
	 * 
	 * @param is
	 * @param os
	 */
	public void render(List<PageRendered> renders) throws ScriptException {
        for (PageRendered render : renders) {
        	render(render);
		}
	}
	
	public void render(PageRendered render) throws ScriptException {
		engine.put("renderObj", render );
		engine.eval("renderDustJS(renderObj);");
		engine.eval("delete renderObj;");
	}
	

}
