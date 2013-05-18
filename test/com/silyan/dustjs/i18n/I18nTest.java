package com.silyan.dustjs.i18n;

import java.io.IOException;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class I18nTest {

	@Test
	public void fromTemplatesInFolder() throws IOException {
		I18n i18n = new I18n();
		i18n.fromTemplatesInFolder(Paths.get("./test/com/silyan/dustjs/testfolders"), Paths.get("./build/test/i18nCSV"), new ObjectMapper());
	}
	
	@Test
	public void fromCSVsInFolder() throws IOException {
		I18n i18n = new I18n();
		i18n.fromCSVsInFolder(Paths.get("./test/com/silyan/dustjs/csvtest"), Paths.get("./build/test/i18nJSON"));
	}
}
