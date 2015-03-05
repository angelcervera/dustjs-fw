/**
 * 
 */
package com.silyan.dustjs.i18n;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

import au.com.bytecode.opencsv.CSVReader;

import com.silyan.dustjs.utils.IOUtils;

/**
 * 
 * Search all templates (template.tl) and load i18n resources.
 * 
 * @author Angel Cervera Claudio ( angelcervera@silyan.com )
 *
 */
public class CSVFileFinder extends SimpleFileVisitor<Path> {

	protected PathMatcher matcher;
	protected Path sourceFolder;
	protected Map<String /* Path */, Map<String /* Language */, Map<String /* i18n key */, String /* Text */>>> i18nIndexed = new HashMap<String, Map<String,Map<String,String>>>();
	
	protected CSVFileFinder() { }
	
	public CSVFileFinder(Path sourceFolder) throws IOException {
		
		matcher = FileSystems.getDefault().getPathMatcher("glob:*.csv");
		this.sourceFolder = sourceFolder;
		
		Files.walkFileTree(sourceFolder, this);
		
	}

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
		Path name = file.getFileName();
		if (name != null && matcher.matches(name)) {
			try {
				addCSV(file);
			} catch (IOException e) {
				e.printStackTrace();
				return FileVisitResult.TERMINATE;
			}
		}
		return FileVisitResult.CONTINUE;
	}
	
	/**
	 * Add a csv to index.
	 * 
	 * @param csvPath
	 * @throws IOException
	 */
	public void addCSV(Path csvPath) throws IOException {
		i18nIndexed.put( IOUtils.getRelativePath(sourceFolder, csvPath.getParent(), false), loadCSVAsMap( csvPath ) );
	}
	
	/**
	 * 
	 * @param csvPath
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public Map<String, Map<String, String>> loadCSVAsMap(Path csvPath) throws FileNotFoundException, IOException {
		
		Map<String, Map<String, String>> index = new HashMap<>();
		
		try(CSVReader reader = new CSVReader(new FileReader(csvPath.toFile()), '\t')) {
			// Read header.
			String [] header = reader.readNext();
			for (int i = 1; i < header.length; i++) {
				String languageCode = header[i];
				index.put(languageCode, new HashMap<String,String>());
			}
			
			// Read rows
			String [] row;
		    while ((row = reader.readNext()) != null) {
		        String i18key = row[0];
		        for (int i = 1; i < row.length; i++) {
		        	if(row[i] != null && row[i].length() > 0) {
		        		index.get(header[i]).put(i18key, row[i]);
		        	}
				}
		    }
		}
		
		return index;
	}

	/**
	 * @return the i18nIndexed
	 */
	public Map<String, Map<String, Map<String, String>>> getI18nIndexed() {
		return i18nIndexed;
	}

	/**
	 * @param i18nIndexed the i18nIndexed to set
	 */
	public void setI18nIndexed(Map<String, Map<String, Map<String, String>>> i18nIndexed) {
		this.i18nIndexed = i18nIndexed;
	}
	
	
}
