package com.silyan.dustjs.i18n;

import java.io.IOException;
import java.nio.file.Paths;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CSVFileFinderTest {
	
	@Test
	public void addFolder() throws IOException {
		
		CSVFileFinder finder = new CSVFileFinder();
		finder.sourceFolder = Paths.get("./src/test/resources/com/silyan/dustjs/csvtest/pages/com/silyan/dustjs");

		finder.addCSV( Paths.get("./src/test/resources/com/silyan/dustjs/csvtest/pages/com/silyan/dustjs", "/proof/user/i18n/server/i18n.csv") );
		
		Assert.assertEquals(finder.getI18nIndexed().size(), 1);
		Assert.assertNotNull(finder.getI18nIndexed().get("/proof/user/i18n/server"));
		Assert.assertEquals(finder.getI18nIndexed().get("/proof/user/i18n/server").size(), 3);
		Assert.assertEquals(finder.getI18nIndexed().get("/proof/user/i18n/server").get("es").size(), 5);
		Assert.assertEquals(finder.getI18nIndexed().get("/proof/user/i18n/server").get("pt").size(), 0);
		Assert.assertEquals(finder.getI18nIndexed().get("/proof/user/i18n/server").get("en").size(), 5);
		
	}
	
	@Test
	public void CSVFileFinder() throws IOException {
		CSVFileFinder finder = new CSVFileFinder( Paths.get("./src/test/resources/com/silyan/dustjs/csvtest") );
		Assert.assertEquals(finder.getI18nIndexed().size(), 6);
	}
}
