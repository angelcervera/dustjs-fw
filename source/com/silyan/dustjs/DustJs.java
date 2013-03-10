/**
 * 
 */
package com.silyan.dustjs;

import java.io.IOException;
import java.util.List;

import javax.script.Compilable;
import javax.script.CompiledScript;
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
	
	private CompiledScript scripts;
	
	{
		try {
			dustjs =          IOUtils.toStringFromClassPath("com/silyan/dustjs/resources/1.2.0/dust-full-1.2.0.js");
			dustjs = dustjs + IOUtils.toStringFromClassPath("com/silyan/dustjs/resources/helper/1.1.1/dust-helpers-1.1.1.js");
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
        
        Compilable compiledScript = (Compilable)engine;
        scripts = compiledScript.compile(dustjs + renderjs + json2js);
        scripts.eval();

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
		String compiled = (String) engine.eval("dust.compile(\"\" + template + \"\", name)"); // FIXME: Very very very low performance !!!!
		engine.eval("delete template;");
		engine.eval("delete name;");
		
		template.setCompiled(compiled);
		System.out.println("Compiled template: " + template.getName() );
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
		System.out.println("Rendered page: " + render.getName() );
	}
	

}
