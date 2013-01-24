/**
 * 
 */
package com.silyan.dustjs.model;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Angel Cervera Claudio ( angelcervera@silyan.com )
 *
 */
public class Project {
	
	public Configuration config;
	
	/**
	 * Layouts in the project.
	 */
	public List<Template> layouts = new ArrayList<>();
	
	/**
	 * Components in the project.
	 */
	public List<Template> components = new ArrayList<>();
	
	/**
	 * Pages in the project.
	 */
	public List<Template> pages = new ArrayList<>();
	
	/**
	 * Return map with all templates, indexed by name.
	 * 
	 * @return
	 */
	public Map<String, Template> getTemplates() {
		Map<String, Template> retValue = new HashMap<String, Template>();
		for (Template template : getComponents()) {
			retValue.put(template.getName(), template);
		}
		for (Template template : getLayouts()) {
			retValue.put(template.getName(), template);
		}
		for (Template template : getPages()) {
			retValue.put(template.getName(), template);
		}
		return retValue;
	}

	/**
	 * @return the layouts
	 */
	public List<Template> getLayouts() {
		return layouts;
	}

	/**
	 * @param layouts the layouts to set
	 */
	public void setLayouts(List<Template> layouts) {
		this.layouts = layouts;
	}

	/**
	 * @return the components
	 */
	public List<Template> getComponents() {
		return components;
	}

	/**
	 * @param components the components to set
	 */
	public void setComponents(List<Template> components) {
		this.components = components;
	}

	/**
	 * @return the pages
	 */
	public List<Template> getPages() {
		return pages;
	}

	/**
	 * @param pages the pages to set
	 */
	public void setPages(List<Template> pages) {
		this.pages = pages;
	}
	
	/**
	 * @return the config
	 */
	public Configuration getConfig() {
		return config;
	}

	/**
	 * @param config the config to set
	 */
	public void setConfig(Configuration config) {
		this.config = config;
	}

	// Factory
	public static Project fromFolder(Path rootFolder, ObjectMapper mapper) throws IOException {
		
		// Search all templates (template.tl) and process it.
		class Finder extends SimpleFileVisitor<Path> {
			
			private final PathMatcher matcher;
			private Path rootFolder;
			private List<Template> templates;
			private ObjectMapper mapper;

			public Finder(Path rootFolder, String pattern, List<Template> templates, ObjectMapper mapper) {
				matcher = FileSystems.getDefault().getPathMatcher("glob:" + pattern);
				this.templates = templates;
				this.rootFolder = rootFolder;
				this.mapper = mapper;
			}

			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
				Path name = file.getFileName();
				if (name != null && matcher.matches(name)) {
					try {
						templates.add(Template.fromFolder(rootFolder, file.getParent(), mapper));
					} catch (IOException e) {
						e.printStackTrace();
						return FileVisitResult.TERMINATE;
					}
				}
				return FileVisitResult.CONTINUE;
			}

		}
		
		Project project = new Project();
		
		// Load project config.
		project.setConfig( Configuration.fromFile(Paths.get(rootFolder.toString(), "config.json" ), mapper) );
		
		// Load templates.
		List<Template> templates = new ArrayList<>();
		Finder finder = new Finder(rootFolder, "template.tl", templates, mapper);
		Files.walkFileTree(rootFolder, finder);
		
		for (Template templateBean : templates) {
			switch (templateBean.getType()) {
			case LAYOUT:
				project.getLayouts().add(templateBean);
				break;
			case COMPONENT:
				project.getComponents().add(templateBean);
				break;
			case PAGE:
				project.getPages().add(templateBean);
				break;

			default:
				break;
			}
		}
		
		return project;
		
	}

}
