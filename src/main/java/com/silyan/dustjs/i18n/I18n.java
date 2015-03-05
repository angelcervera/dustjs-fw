/**
 * 
 */
package com.silyan.dustjs.i18n;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import au.com.bytecode.opencsv.CSVWriter;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Angel Cervera Claudio ( angelcervera@silyan.com )
 *
 */
public class I18n {
	
	/**
	 * From templates in folder to a CSV files in other folder structure.
	 * 
	 * @param rootFolder
	 * @param targetFolder
	 * @param mapper
	 * @throws IOException
	 */
	public void fromTemplatesInFolder(Path sourceFolder, Path targetFolder, ObjectMapper mapper) throws IOException {
		
		// Get I18N Map.
		Map<String, Map<String, Map<String, String>>> i18nIndexed = new I18nFileFinder(sourceFolder, "template.tl", mapper).getI18nIndexed();
		
		// Store csv in targetFolder.
		for (String template : i18nIndexed.keySet()) {
			Map<String, Map<String, String>> languages = i18nIndexed.get(template);
			
			// Create a static ordered list of languages.
			List<String> languageList = new ArrayList<>(languages.keySet());
			Collections.sort(languageList);
			
			// Create ordered list of keys.
			Set<String> i18nKeySet = new HashSet<>();
			for (String language : languages.keySet()) {
				i18nKeySet.addAll(languages.get(language).keySet());
			}
			List<String> i18nKeyList = new ArrayList<>(i18nKeySet);
			Collections.sort(i18nKeyList);
			
			// Generate CSV file.
			int columns = languageList.size() +1;
			
			File i18nFile = Paths.get(targetFolder.toString(), template, "i18n.csv").toFile();
			i18nFile.getParentFile().mkdirs();
			try( CSVWriter writer = new CSVWriter(new FileWriter(i18nFile), '\t') ) {
				String[] row = new String[columns];
				
				// Generate header.
				int columnCount = 0;
				row[columnCount] = "key";
				for (String lang : languageList) {
					columnCount++;
					row[columnCount] = lang;
				}
				writer.writeNext(row);
				
				// Generate files.
				for (String key : i18nKeyList) {
					columnCount = 0;
					row[columnCount] = key;
					for (String lang : languageList) {
						columnCount++;
						row[columnCount] = languages.get(lang).get(key);
					}
					writer.writeNext(row);
				}
			}
			
		}
		
	}
	
	/**
	 * From csv in folders to templates structure.
	 * 
	 * @param sourceFolder
	 * @param targetFolder
	 * @param mapper
	 * @throws IOException
	 */
	public void fromCSVsInFolder(Path sourceFolder, Path targetFolder) throws IOException {
		
		
		JsonFactory jsonFactory = new JsonFactory();
		
		// mapper.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true); // FIXME: Doesn't work for Maps :(
		
		// Read CSVs.
		Map<String, Map<String, Map<String, String>>> i18nIndexed = new CSVFileFinder( sourceFolder ).getI18nIndexed();
		
		// Store in JSON.
		for (String template : i18nIndexed.keySet()) {
			Map<String, Map<String,String>> languages = i18nIndexed.get(template);
			Path targetFolderPath = Paths.get(targetFolder.toString(), template);
			File targetFolderFile = targetFolderPath.toFile();
			targetFolderFile.mkdirs();
			
			for (String lng : languages.keySet()) {
				Path filePath = Paths.get(targetFolderPath.toString(), lng + ".json" );
				try(FileWriter writer = new FileWriter(filePath.toFile())) {
					
					// Language values map.
					Map<String,String> mapValues = languages.get(lng);
					
					// Order keys.
					List<String> keys = new ArrayList<>(mapValues.keySet());
					Collections.sort(keys);
					
					// Wrtie json
					JsonGenerator jg = jsonFactory.createJsonGenerator(writer);
					jg.useDefaultPrettyPrinter();
					jg.writeStartObject();
					for (String key : keys) {
						jg.writeStringField(key, mapValues.get(key));
					}
					jg.writeEndObject();
					jg.close();
					
				}
			}
			
		}
	}

}
