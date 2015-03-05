package com.silyan.dustjs.model;

import java.io.IOException;
import java.nio.file.Paths;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.silyan.dustjs.model.Template;
import com.silyan.dustjs.utils.IOUtils;

public class TemplateTest {

	@Test
	public void fromFolder() {
		try {
			Template template = Template.fromFolder(
					Paths.get("./src/test/resources/com/silyan/dustjs/testfolders"),
					Paths.get("./src/test/resources/com/silyan/dustjs/testfolders/layouts/main"),
					new ObjectMapper()
			);
			Assert.assertEquals(template.getName(), "/layouts/main");
			Assert.assertEquals(template.getType(), Template.TemplateTypes.LAYOUT);
			
			Assert.assertEquals(template.getTemplate(), IOUtils.toStringFromFile(Paths.get("./src/test/resources/com/silyan/dustjs/testfolder_results/layout_escaped_template.tl")));
		
			// Test configuration is loaded.
			Assert.assertNotNull(template.getConfigutation());
			
			
		} catch (IOException e) {
			Assert.fail("Unexpected error building Template path.", e);
		}
	}
}
