/**
 * 
 */
package com.silyan.dustjs.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.silyan.dustjs.utils.IOUtils;


/**
 * Template information.
 * 
 * @author Angel Cervera Claudio ( angelcervera@silyan.com )
 *
 */
public class Template {

	public String name;
	
	public enum TemplateTypes {
		LAYOUT, PAGE, COMPONENT
	}
	
	public TemplateTypes type;
	
	public String template, compiled;
	
	public Configuration configutation;
	
	public Template() {
		
	}
	
	public Template(String name, String template) {
		super();
		this.name = name;
		this.template = template;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the template
	 */
	public String getTemplate() {
		return template;
	}

	/**
	 * @param template the template to set
	 */
	public void setTemplate(String template) {
		this.template = template;
	}

	/**
	 * @return the compiled
	 */
	public String getCompiled() {
		return compiled;
	}

	/**
	 * @param compiled the compiled to set
	 */
	public void setCompiled(String compiled) {
		this.compiled = compiled;
	}
	
	/**
	 * @return the type
	 */
	public TemplateTypes getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(TemplateTypes type) {
		this.type = type;
	}
	
	/**
	 * @return the configutation
	 */
	public Configuration getConfigutation() {
		return configutation;
	}

	/**
	 * @param configutation the configutation to set
	 */
	public void setConfigutation(Configuration configutation) {
		this.configutation = configutation;
	}

	
	
	
	
	
	/**
	 * Factory.
	 * Build a template from a folder.
	 * 
	 * @param root Root path with layouts, components and pages.
	 * @param folder Folder to process.
	 * @return
	 */
	public static Template fromFolder(Path root, Path folder, ObjectMapper mapper) throws IOException {
		root = root.toAbsolutePath().normalize();
		folder = folder.toAbsolutePath().normalize();
		
		Template template = new Template();
		
		// Calculate name of template
		String name = IOUtils.getRelativePath(root, folder, false);
		if(name.indexOf(File.separatorChar) != 0) {
			name = File.separatorChar + name;
		}
		template.setName(name);
		
		// Calculate type.
		String type = Paths.get(name).getName(0).toString();
		if(type.equals("layouts")) {
			template.setType(TemplateTypes.LAYOUT);
		} else {
			if(type.equals("components")) {
				template.setType(TemplateTypes.COMPONENT);
			} else {
				if(type.equals("pages")) {
					template.setType(TemplateTypes.PAGE);
				} else {
					throw new RuntimeException("Type unknow: " + type);
				}
			}
		}
		
		// Load template configuration
		Path configPath = Paths.get(folder.toString(), "config.json");
		template.setConfigutation( Configuration.fromFile(configPath, mapper) );
		
		// Load template file.
		Path templatePath = Paths.get(folder.toString(), "template.tl");
		template.setTemplate( IOUtils.toStringFromFile(templatePath) );
		
		// Replace CR.
		if(template.getConfigutation().isEscapeEOL()) {
			template.setTemplate( template.getTemplate().replace("\n", "{~n}\n") );
		}
		
		// Replace TAB.
		if(template.getConfigutation().isEscapeTAB()) {
			template.setTemplate( template.getTemplate().replace("\t", "{~s}{~s}{~s}") );
		}
		
		
		System.out.println("Created new template: " + template.getName() );
		
		
		return template;
	}

}
