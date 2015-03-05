package com.silyan.dustjs.model;

import java.nio.file.Paths;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PageRenderedTest {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void fromTemplate() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			Project project = Project.fromFolder(
					Paths.get("./src/test/resources/com/silyan/dustjs/testfolders"),
					mapper
			);
			
			PageRendered pageRendered = PageRendered.fromTemplate("es", "production", "/pages/com/silyan/dustjs/proof/user", project, new ObjectMapper() );
			
			// Test translations.
			Assert.assertEquals(pageRendered.clientTranslations.get("example"), "Ejemplo"); // In page
			Assert.assertEquals(pageRendered.serverTranslations.get("copyright"), "2007-2012 Silyan Software SL. Todos los derechos reservados."); // In templates used
			
			// Test environment parameters.
			Assert.assertEquals(pageRendered.clientEnvironment.get("contextpath"), ""); // In project config
			Assert.assertTrue((boolean)pageRendered.clientEnvironment.get("compress")); // In project config
			
			// Test default parameters, so must be in all environments.
			Map<String, Object> clientEnvironment = pageRendered.clientEnvironment;
			Assert.assertEquals(clientEnvironment.get("applicationVersion"), "4.0_APP"); // In project config
			Assert.assertEquals(clientEnvironment.get("pageVersion"), "4.0_PAGE"); // In project page
			Assert.assertNull(clientEnvironment.get("layoutVersion")); // In project layout
			Assert.assertEquals(pageRendered.serverEnvironment.get("applicationVersion"), "4.0_APP"); // In project config
			Assert.assertEquals(pageRendered.serverEnvironment.get("pageVersion"), "4.0_PAGE"); // In project page
			Assert.assertEquals(pageRendered.serverEnvironment.get("layoutVersion"), "4.0_LAYOUT"); // In project layout
			
			// Test generated JSON.
			Map<String, Object> json = mapper.readValue(pageRendered.getConfiguration(), Map.class);
			Assert.assertNotNull( json.get("i18n") );
			Assert.assertNotNull( json.get("parameters") );
			Assert.assertNotNull( json.get("clientSide") );
			Assert.assertNotNull( ((Map)json.get("clientSide")).get("i18n") );
			Assert.assertNotNull( ((Map)json.get("clientSide")).get("parameters") );
			Assert.assertNull( ((Map)json.get("clientSide")).get("clientSide") );
			
		} catch(Exception e) {
			Assert.fail("Unexpected error building Project from folder.", e);
		}
	}
}
