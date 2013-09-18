/**
 * 
 */
package com.silyan.dustjs.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.silyan.dustjs.utils.JacksonUtils;

/**
 * Rendered values from a executed template.
 * 
 * @author Angel Cervera Claudio ( angelcervera@silyan.com )
 *
 */
public class PageRendered {
	
	/**
	 * Name of template to render.
	 */
	public String name;

	/**
	 * JSON configuration used to render.
	 */
	public String configuration;
	
	/**
	 * Result after render.
	 */
	public String rendered;
	
	/**
	 * Error, if any, after render.
	 */
	public String err;
	
	/**
	 * Templates used to create this PageRedered.
	 */
	public Template template;
	
	/**
	 * Translations for client side.
	 */
	protected Map<String, String> clientTranslations;
	
	/**
	 * Translations for client side.
	 */
	protected Map<String, String> serverTranslations;
	
	/**
	 * Environment for client side.
	 */
	protected Map<String, Object> clientEnvironment;
	
	/**
	 * Environment for client side.
	 */
	protected Map<String, Object> serverEnvironment;
	
	
	public PageRendered() {
		super();
	}
	
	public PageRendered(String name, String configuration) {
		super();
		this.name = name;
		this.configuration = configuration;
	}
	
	
	@SuppressWarnings("rawtypes")
	private static void generateClientTranslations(String language, String templateName, Project project, List<Map> lngTranslations) {
		Map<String, Template> templates = project.getTemplates();
		Template template = templates.get(templateName);
		
		lngTranslations.add(project.getConfig().getClientTranslations().get(language)); // Adds project translations.
		for (String referencedTemplateName : template.getConfigutation().getClientTemplates()) { // Adds templates translations.
			Template referencedTemplate = templates.get(referencedTemplateName);
			if(referencedTemplate == null) {
				throw new RuntimeException("Template ["+referencedTemplateName+"] not found.");
			}
			Map<String, String> translations = referencedTemplate.getConfigutation().getClientTranslations().get(language);
			if(translations == null) {
				throw new RuntimeException("Translations for languages ["+language+"] not found in template ["+referencedTemplateName+"].");
			}
			lngTranslations.add( translations );
		}
		Map<String, String> templateTranslations = template.getConfigutation().getClientTranslations().get(language);
		if(templateTranslations == null) {
			throw new RuntimeException("Translations for languages ["+language+"] not found in template ["+templateName+"].");
		}
		lngTranslations.add(templateTranslations); // Adds page translations.
	}
	
	@SuppressWarnings("rawtypes")
	private static void generateServerTranslations(String language, String templateName, Project project, List<Map> lngTranslations) {
		Map<String, Template> templates = project.getTemplates();
		Template template = templates.get(templateName);
		
		lngTranslations.add(project.getConfig().getServerTranslations().get(language)); // Adds project translations.
		for (String referencedTemplateName : template.getConfigutation().getServerTemplates()) { // Adds templates translations.
			Template referencedTemplate = templates.get(referencedTemplateName);
			if(referencedTemplate == null) {
				throw new RuntimeException("Template ["+referencedTemplateName+"] not found.");
			}
			Map<String, String> translations = referencedTemplate.getConfigutation().getServerTranslations().get(language);
			if(translations == null) {
				throw new RuntimeException("Translations for languages ["+language+"] not found in template ["+referencedTemplateName+"].");
			}
			lngTranslations.add( translations );
		}
		Map<String, String> templateTranslations = template.getConfigutation().getServerTranslations().get(language);
		if(templateTranslations == null) {
			throw new RuntimeException("Translations for languages ["+language+"] not found in template ["+templateName+"].");
		}
		lngTranslations.add(templateTranslations); // Adds page translations.
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static PageRendered fromTemplate(String language, String environment, String name, Project project, ObjectMapper mapper) throws JsonGenerationException, JsonMappingException, IOException {
		Map<String, Template> templates = project.getTemplates();
		Template template = templates.get(name);
		if(template==null) {
			throw new RuntimeException("Template ["+name+"] not found.");
		}
		PageRendered render = new PageRendered();
		render.setName(template.getName());
		render.setTemplate(template);
		
		// Test for all templates registered.
		for (String templateName : template.getConfigutation().getServerTemplates()) {
			if(!templates.containsKey(templateName)) {
				throw new RuntimeException("Template ["+templateName+"] not found.");
			}
		}
		for (String templateName : template.getConfigutation().getClientTemplates()) {
			if(!templates.containsKey(templateName)) {
				throw new RuntimeException("Template ["+templateName+"] not found.");
			}
		}
		
		// Generate i18n.
		// It must iterates over i18n translations in all templates used and merge them with translations in page.
		String defaultLanguage = project.getConfig().getDefaultLanguage();
		
		// Generate default translations.
		
		// Generate i18n for client side.
		List<Map> lngTranslations = new ArrayList<>();
		generateClientTranslations(defaultLanguage, name, project, lngTranslations);
		generateClientTranslations(language, name, project, lngTranslations);
		render.clientTranslations = JacksonUtils.mergeListOfMaps(lngTranslations);
		
		// Generate i18n for server side.
		lngTranslations = new ArrayList<>();
		generateServerTranslations(defaultLanguage, name, project, lngTranslations);
		generateServerTranslations(language, name, project, lngTranslations);
		render.serverTranslations = JacksonUtils.mergeListOfMaps(lngTranslations);
		
		// Generate list of parameters for client side.
		List<Map> parameters = new ArrayList<>();
		parameters.add( project.getConfig().getEnvironments().get(environment) ); // Adds project parameters.
		for (String referencedTemplateName : template.getConfigutation().getClientTemplates()) { // Adds templates parameters.
			Template referencedTemplate = templates.get(referencedTemplateName);
			Map<String, Object> environmentParameters = referencedTemplate.getConfigutation().getEnvironments().get(environment);
			if(environmentParameters == null) {
				throw new RuntimeException("Environment ["+environment+"] not found in template ["+referencedTemplateName+"].");
			}
			parameters.add( environmentParameters );
		}
		Map<String, Object> templateEnvironment = template.getConfigutation().getEnvironments().get(environment);
		if(templateEnvironment == null) {
			throw new RuntimeException("Environment ["+environment+"] not found in template ["+name+"].");
		}
		parameters.add(templateEnvironment); // Adds page parameters.
		render.clientEnvironment = JacksonUtils.mergeListOfMaps(parameters);
		
		// Generate list of parameters for server side.
		parameters = new ArrayList<>();
		parameters.add( getDefaultParameters(language) );		// Generate list of default parameters
		parameters.add( project.getConfig().getEnvironments().get(environment) ); // Adds project parameters.
		for (String referencedTemplateName : template.getConfigutation().getServerTemplates()) { // Adds templates parameters.
			Template referencedTemplate = templates.get(referencedTemplateName);
			Map<String, Object> environmentParameters = referencedTemplate.getConfigutation().getEnvironments().get(environment);
			if(environmentParameters == null) {
				throw new RuntimeException("Environment ["+environment+"] not found in template ["+referencedTemplateName+"].");
			}
			parameters.add( environmentParameters );
		}
		templateEnvironment = template.getConfigutation().getEnvironments().get(environment);
		parameters.add(templateEnvironment); // Adds page parameters.
		render.serverEnvironment = JacksonUtils.mergeListOfMaps(parameters);
		
		
		// Generate JSON json.
		Map<String, Object> json = render.getServerJSON();
		render.setConfiguration( mapper.writeValueAsString(json) );
		
		return render;
	}
	
	
	private static Map<String, Object> getDefaultParameters(String language) {
		Map<String, Object> map = new HashMap<>();
		map.put("language", language);
		return map;
	}

	protected Map<String, Object> getServerJSON() {
		Map<String, Object> retValue = new HashMap<String, Object>();
		
		retValue.put("i18n", serverTranslations);
		retValue.put("parameters", serverEnvironment);
		retValue.put("clientSide", getClientJSON());
		retValue.put("renderJSON", "");
		
		return retValue;
	}
	
	protected Map<String, Object> getClientJSON() {
		Map<String, Object> retValue = new HashMap<String, Object>();
		
		retValue.put("i18n", serverTranslations);
		retValue.put("parameters", serverEnvironment);
		retValue.put("renderJSON", "");
		
		return retValue;
	}
	
	// Setters and Getters

	/**
	 * @return the rendered
	 */
	public String getRendered() {
		return rendered;
	}

	/**
	 * @param rendered the rendered to set
	 */
	public void setRendered(String rendered) {
		this.rendered = rendered;
	}

	/**
	 * @return the configuration
	 */
	public String getConfiguration() {
		return configuration;
	}

	/**
	 * @param configuration the configuration to set
	 */
	public void setConfiguration(String configuration) {
		this.configuration = configuration;
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
	 * @return the err
	 */
	public String getErr() {
		return err;
	}

	/**
	 * @param err the err to set
	 */
	public void setErr(String err) {
		this.err = err;
	}

	/**
	 * @return the template
	 */
	public Template getTemplate() {
		return template;
	}

	/**
	 * @param template the template to set
	 */
	public void setTemplate(Template template) {
		this.template = template;
	}
	
}
