package com.silyan.dustjs.model;

import java.nio.file.Paths;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ConfigurationTest {

	@Test
	public void fromFile() {
		try {
			Configuration projectConfig = Configuration.fromFile(
					Paths.get("./src/test/resources/com/silyan/dustjs/testfolders/config.json"),
					new ObjectMapper()
			);
			
			// Test escape EOL.
			Assert.assertTrue(projectConfig.isEscapeEOL());
			
			// Test translations.
			Assert.assertEquals(projectConfig.getDefaultLanguage(), "en");
			
			// Client i18n
			Assert.assertEquals(projectConfig.getClientTranslations().size(), 3);
			Assert.assertNotNull(projectConfig.getClientTranslations().get("es"));
			Assert.assertNotNull(projectConfig.getClientTranslations().get("en"));
			Assert.assertNull(projectConfig.getClientTranslations().get("de"));
			
			Map<String, String> lng = projectConfig.getClientTranslations().get("es");
			Assert.assertEquals(lng.get("language"),"Español");
			Assert.assertEquals(lng.get("clientText"),"Texto en el cliente");
			Assert.assertNull(lng.get("serverText"));
			
			// Server i18n
			Assert.assertEquals(projectConfig.getServerTranslations().size(), 3);
			Assert.assertNotNull(projectConfig.getServerTranslations().get("es"));
			Assert.assertNotNull(projectConfig.getServerTranslations().get("en"));
			Assert.assertNull(projectConfig.getServerTranslations().get("de"));
			
			lng = projectConfig.getServerTranslations().get("es");
			Assert.assertEquals(lng.get("language"),"Español");
			Assert.assertEquals(lng.get("serverText"),"Texto en el servidor");
			Assert.assertEquals(lng.get("clientText"),"Texto en el cliente"); // Server translations include client translations too.
			
			// Test list of available languages.
			Assert.assertEquals(projectConfig.getAvailablesLanguages().size(), 3);
			Assert.assertTrue(projectConfig.getAvailablesLanguages().contains("es"));
			Assert.assertTrue(projectConfig.getAvailablesLanguages().contains("en"));
			Assert.assertTrue(projectConfig.getAvailablesLanguages().contains("pt"));
			
			// Test enviroments.
			Assert.assertEquals(projectConfig.getEnvironments().size(), 4);
			Assert.assertNotNull(projectConfig.getEnvironments().get("default"));
			Map<String, Object> defaultParameters = projectConfig.getEnvironments().get("default");
			Assert.assertEquals(defaultParameters.get("applicationVersion"), "4.0_APP");
			
			Assert.assertNotNull(projectConfig.getEnvironments().get("development"));
			Map<String, Object> developmentParameters = projectConfig.getEnvironments().get("development");
			Assert.assertEquals(developmentParameters.get("contextpath"), "/dust-js-proof/");
			Assert.assertEquals(developmentParameters.get("applicationVersion"), "4.0_APP");
			
			
			
			// Test list of templates used.
			
		} catch (Exception e) {
			Assert.fail("Unexpected error building ProjectConfig from file.", e);
		}
	}
}
