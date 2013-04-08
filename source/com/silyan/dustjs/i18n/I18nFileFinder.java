/**
 * 
 */
package com.silyan.dustjs.i18n;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.silyan.dustjs.utils.IOUtils;
import com.silyan.dustjs.utils.JacksonUtils;

/**
 * 
 * Search all templates (template.tl) and load i18n resources.
 * 
 * @author Angel Cervera Claudio ( angelcervera@silyan.com )
 *
 */
public class I18nFileFinder extends SimpleFileVisitor<Path> {

	protected PathMatcher matcher;
	protected Path sourceFolder;
	protected Map<String /* Path */, Map<String /* Language */, Map<String /* i18n key */, String /* Text */>>> i18nIndexed = new HashMap<String, Map<String,Map<String,String>>>();
	protected ObjectMapper mapper;
	
	protected I18nFileFinder() { }
	
	public I18nFileFinder(
			Path sourceFolder,
			String pattern,
			ObjectMapper mapper ) throws IOException {
		
		matcher = FileSystems.getDefault().getPathMatcher("glob:" + pattern);
		this.sourceFolder = sourceFolder;
		this.mapper = mapper;
		
		// Add defaults translations.
		addFolder(Paths.get(sourceFolder.toString(), "i18n/client"));
		addFolder(Paths.get(sourceFolder.toString(), "i18n/server"));
		
		Files.walkFileTree(sourceFolder, this);
		
	}

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
		Path name = file.getFileName();
		if (name != null && matcher.matches(name)) {
			try {
				// Add translations of template.
				addFolder(Paths.get(file.getParent().toString(), "i18n/client"));
				addFolder(Paths.get(file.getParent().toString(), "i18n/server"));
			} catch (IOException e) {
				e.printStackTrace();
				return FileVisitResult.TERMINATE;
			}
		}
		return FileVisitResult.CONTINUE;
	}
	
	/**
	 * Add a folder to index.
	 * 
	 * @param folder
	 * @param subfolder
	 * @throws IOException
	 */
	public void addFolder(Path i18nPath) throws IOException {
		File i18nFolder = i18nPath.toFile();
		if(i18nFolder.exists() && i18nFolder.isDirectory()) {
			// store i18n translations by languages.
			i18nIndexed.put( IOUtils.getRelativePath(sourceFolder, i18nPath, false), JacksonUtils.loadFilesAsMap( i18nPath, mapper) );
		}
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
