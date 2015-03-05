/**
 * 
 */
package com.silyan.dustjs.i18n;

import java.io.IOException;
import java.nio.file.Paths;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Angel Cervera Claudio ( angelcervera@silyan.com )
 *
 */
public class I18nFileFinderTest {
	
	@Test
	public void addFolder() throws IOException {
		
		I18nFileFinder finder = new I18nFileFinder();
		finder.sourceFolder = Paths.get("./src/test/resources/com/silyan/dustjs/testfolders/pages/com/silyan/dustjs");
		finder.mapper = new ObjectMapper();
		
		finder.addFolder( Paths.get("./src/test/resources/com/silyan/dustjs/testfolders/pages/com/silyan/dustjs/proof/user", "i18n/client") );
		
		Assert.assertEquals(finder.getI18nIndexed().size(), 1);
		Assert.assertNotNull(finder.getI18nIndexed().get("/proof/user/i18n/client"));
		Assert.assertEquals(finder.getI18nIndexed().get("/proof/user/i18n/client").size(), 3);
		Assert.assertEquals(finder.getI18nIndexed().get("/proof/user/i18n/client").get("es").size(), 4);
		Assert.assertEquals(finder.getI18nIndexed().get("/proof/user/i18n/client").get("pt").size(), 0);
		Assert.assertEquals(finder.getI18nIndexed().get("/proof/user/i18n/client").get("en").size(), 4);
		
	}
	
	@Test
	public void I18nFileFinder() throws IOException {
		I18nFileFinder finder = new I18nFileFinder( Paths.get("./src/test/resources/com/silyan/dustjs/testfolders"), "*.tl", new ObjectMapper() );
		Assert.assertEquals(finder.getI18nIndexed().size(), 6);
	}
	
}
