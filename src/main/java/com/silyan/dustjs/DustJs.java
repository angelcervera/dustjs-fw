/**
 * 
 */
package com.silyan.dustjs;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
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
	
	private Path cacheFolder;
	
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
	
	public DustJs(Path cacheFolder) throws ScriptException {
		super();
		
		this.cacheFolder = cacheFolder;
		this.cacheFolder.toFile().mkdirs();
		
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
	 * @throws IOException 
	 */
	public void compile(List<Template> templates) throws ScriptException, IOException {
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
	public String compile(Template template) throws ScriptException, IOException {
		long initMMS = System.currentTimeMillis();
		
		Path cachePath = Paths.get(cacheFolder.toFile().toString(), template.getName(), "__compiled.cache");
		if(cachePath.toFile().exists() && cachePath.toFile().lastModified() >= template.getLastModification()) {
			System.out.println("Using cache for template: " + template.getName() );
			template.setCompiled(IOUtils.toStringFromFile(cachePath));
		} else {
			System.out.println("Compiling template: " + template.getName() );
			long id = Thread.currentThread().getId();
			String templateVariable = "template_" + id;
			String nameVariable = "name_" + id;
			engine.put(templateVariable, template.getTemplate() );
			engine.put(nameVariable, template.getName() );
			String compiled = (String) engine.eval("dust.compile(\"\" + "+templateVariable+" + \"\", "+nameVariable+")"); // FIXME: Very very very low performance !!!!
			engine.eval("delete "+templateVariable+";");
			engine.eval("delete "+nameVariable+";");
			template.setCompiled(compiled);
			cachePath.getParent().toFile().mkdirs();
			IOUtils.toFileFromString(compiled, cachePath);
		}
		
		System.out.println("Compiled template: " + template.getName() + " in  " + (System.currentTimeMillis()-initMMS) + " mms" );
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
