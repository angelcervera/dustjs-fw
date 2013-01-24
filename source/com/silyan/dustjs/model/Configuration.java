/**
 * 
 */
package com.silyan.dustjs.model;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.silyan.dustjs.utils.IOUtils;
import com.silyan.dustjs.utils.JacksonUtils;

/**
 * @author Angel Cervera Claudio ( angelcervera@silyan.com )
 *
 */
public class Configuration {
	
	/**
	 * Default language, if any.
	 */
	public String defaultLanguage;
	
	/**
	 * escape EOL.
	 */
	public boolean escapeEOL = false;
	
	/**
	 * escape TAB.
	 */
	public boolean escapeTAB = false;
	
	/**
	 * Full path and name of the file generated.
	 * Useful for pages.
	 * 
	 */
	public String targetPath;
	
	/**
	 * Available languages.
	 */
	public List<String> availablesLanguages;
	
	/**
	 * Translations for template, when render is in client side.
	 */
	public Map<String, Map<String, String>> clientTranslations;
	
	/**
	 * Translations for template, when render is in server side.
	 * This Map include clientTranslations too.
	 */
	public Map<String, Map<String, String>> serverTranslations;
	
	/**
	 * Parameters for different environments.
	 */
	public Map<String, Map<String, Object>> environments = new HashMap<>();
	
	/**
	 * Templates used in this templates. Only necessary for server side.
	 */
	public List<String> serverTemplates = new ArrayList<>();
	
	/**
	 * Templates used in this templates. Only necessary for client side.
	 */
	public List<String> clientTemplates = new ArrayList<>();
	
	/**
	 * JSON for server.
	 */
	public String serverJSON;
	
	/**
	 * JSON for client.
	 */
	public String clientJSON;
	
	/**
	 * @return the serverJSON
	 */
	public String getServerJSON() {
		return serverJSON;
	}

	/**
	 * @param serverJSON the serverJSON to set
	 */
	public void setServerJSON(String serverJSON) {
		this.serverJSON = serverJSON;
	}

	/**
	 * @return the clientJSON
	 */
	public String getClientJSON() {
		return clientJSON;
	}

	/**
	 * @param clientJSON the clientJSON to set
	 */
	public void setClientJSON(String clientJSON) {
		this.clientJSON = clientJSON;
	}

	/**
	 * @return the escapeEOL
	 */
	public boolean isEscapeEOL() {
		return escapeEOL;
	}

	/**
	 * @param escapeEOL the escapeEOL to set
	 */
	public void setEscapeEOL(boolean escapeEOL) {
		this.escapeEOL = escapeEOL;
	}
	
	/**
	 * @return the escapeTAB
	 */
	public boolean isEscapeTAB() {
		return escapeTAB;
	}

	/**
	 * @param escapeTAB the escapeTAB to set
	 */
	public void setEscapeTAB(boolean escapeTAB) {
		this.escapeTAB = escapeTAB;
	}

	/**
	 * @return the targetPath
	 */
	public String getTargetPath() {
		return targetPath;
	}

	/**
	 * @param targetPath the targetPath to set
	 */
	public void setTargetPath(String targetPath) {
		this.targetPath = targetPath;
	}

	/**
	 * @return the defaultLanguage
	 */
	public String getDefaultLanguage() {
		return defaultLanguage;
	}

	/**
	 * @param defaultLanguage the defaultLanguage to set
	 */
	public void setDefaultLanguage(String defaultLanguage) {
		this.defaultLanguage = defaultLanguage;
	}
	
	/**
	 * @return the availablesLanguages
	 */
	public List<String> getAvailablesLanguages() {
		return availablesLanguages;
	}

	/**
	 * @param availablesLanguages the availablesLanguages to set
	 */
	public void setAvailablesLanguages(List<String> availablesLanguages) {
		this.availablesLanguages = availablesLanguages;
	}

	/**
	 * @return the environment
	 */
	public Map<String, Map<String, Object>> getEnvironments() {
		return environments;
	}

	/**
	 * @param environment the environment to set
	 */
	public void setEnvironments(Map<String, Map<String, Object>> environments) {
		this.environments = environments;
	}
	
	/**
	 * @return the serverTemplates
	 */
	public List<String> getServerTemplates() {
		return serverTemplates;
	}

	/**
	 * @param serverTemplates the serverTemplates to set
	 */
	public void setServerTemplates(List<String> serverTemplates) {
		this.serverTemplates = serverTemplates;
	}

	/**
	 * @return the clientTemplates
	 */
	public List<String> getClientTemplates() {
		return clientTemplates;
	}

	/**
	 * @param clientTemplates the clientTemplates to set
	 */
	public void setClientTemplates(List<String> clientTemplates) {
		this.clientTemplates = clientTemplates;
	}

	/**
	 * @return the clientTranslations
	 */
	public Map<String, Map<String, String>> getClientTranslations() {
		return clientTranslations;
	}

	/**
	 * @param clientTranslations the clientTranslations to set
	 */
	public void setClientTranslations(
			Map<String, Map<String, String>> clientTranslations) {
		this.clientTranslations = clientTranslations;
	}

	/**
	 * @return the serverTranslations
	 */
	public Map<String, Map<String, String>> getServerTranslations() {
		return serverTranslations;
	}

	/**
	 * @param serverTranslations the serverTranslations to set
	 */
	public void setServerTranslations(Map<String, Map<String, String>> serverTranslations) {
		this.serverTranslations = serverTranslations;
	}

	/**
	 * Factory.
	 * 
	 * @param file
	 * @param mapper
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static Configuration fromFile(Path file, ObjectMapper mapper) throws JsonParseException, JsonMappingException, IOException {
		Configuration config = mapper.readValue(file.toFile(), Configuration.class);
		
		// Load i18n translations.
		Map<String, Map<String, String>> clientTranslations = JacksonUtils.loadFilesAsMap( Paths.get(file.getParent().toString(), "i18n/client" ), mapper);
		config.setClientTranslations(clientTranslations);
		
		// If there are a default language, it's merged with new translations.
		if(config.getDefaultLanguage() != null && clientTranslations.get(config.getDefaultLanguage()) != null) {
			Map<String, String> defaultLng = clientTranslations.get(config.getDefaultLanguage());
			for (String lng : clientTranslations.keySet()) {
				Map<String, String> language = clientTranslations.get(lng);
				if(!lng.endsWith(lng)) {
					clientTranslations.put(lng, (Map<String,String>)JacksonUtils.mergeMaps(defaultLng, language));
				}
			}
		}
		
		// Server translations contain all client translations too.
		Map<String, Map<String, String>> serverTranslations = JacksonUtils.loadFilesAsMap( Paths.get(file.getParent().toString(), "i18n/server" ), mapper);
		serverTranslations = JacksonUtils.mergeMapsWithRecursion(clientTranslations, serverTranslations);
		config.setServerTranslations(serverTranslations); 
		
		// If there are a default language, it's merged with new translations.
		if(config.getDefaultLanguage() != null && serverTranslations.get(config.getDefaultLanguage()) != null) {
			Map<String, String> defaultLng = serverTranslations.get(config.getDefaultLanguage());
			for (String lng : serverTranslations.keySet()) {
				Map<String, String> language = serverTranslations.get(lng);
				if(!lng.endsWith(lng)) {
					serverTranslations.put(lng, (Map<String,String>)JacksonUtils.mergeMaps(defaultLng, language));
				}
			}
		}
		
		// Calculate available languages.
		config.setAvailablesLanguages(new ArrayList<String>(serverTranslations.keySet()));
		
		// Merge default environment with others.
		config.setEnvironments( JacksonUtils.mergeEnvironmentMaps(config.getEnvironments()) );
		
		// Load json files.
		Path serverJsonPath = Paths.get(file.getParent().toString(), "server.json" );
		if(serverJsonPath.toFile().exists()) {
			config.setServerJSON(IOUtils.toStringFromFile(serverJsonPath));
		} else {
			config.setServerJSON("{}");
		}
		Path clientJsonPath = Paths.get(file.getParent().toString(), "client.json" );
		if(serverJsonPath.toFile().exists()) {
			config.setClientJSON(IOUtils.toStringFromFile(clientJsonPath));
		} else {
			config.setClientJSON("{}");
		}
		
		return config;
	}
	
	
}
