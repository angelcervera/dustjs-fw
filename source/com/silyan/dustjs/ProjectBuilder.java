/**
 * 
 */
package com.silyan.dustjs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.script.ScriptException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.silyan.dustjs.model.PageRendered;
import com.silyan.dustjs.model.Project;
import com.silyan.dustjs.model.Template;

/**
 * Process folder.
 * 
 * @author Angel Cervera Claudio ( angelcervera@silyan.com )
 *
 */
public class ProjectBuilder {

	protected DustJs dustJs;
	protected Project project;
	
	protected ObjectMapper jsonMapper = new ObjectMapper();
	
	public ProjectBuilder(Path rootFolder) throws ScriptException, IOException {
		dustJs = new DustJs();
		project = Project.fromFolder(rootFolder, jsonMapper);
		compile();
	}
	
	public void process(String environment, Path targetFolder ) throws ScriptException, IOException {
		Map<String, List<PageRendered>> renderedPages = render(environment);
		writeToFileSystem(
				targetFolder,
				renderedPages
			);
	}
	
	/**
	 * Compile all templates in the project and load it.
	 * 
	 * @throws ScriptException
	 */
	protected void compile() throws ScriptException  {
		dustJs.compile( project.getComponents() );
		dustJs.compile( project.getLayouts() );
		dustJs.compile( project.getPages() );
		
		dustJs.loadSource( project.getComponents() );
		dustJs.loadSource( project.getLayouts() );
		dustJs.loadSource( project.getPages() );
	}
	
	/**
	 * With all templates compiled previously, renders statics ( pages ).
	 * 
	 * @throws ScriptException
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 */
	protected Map<String, List<PageRendered>> render(String environment) throws ScriptException, JsonGenerationException, JsonMappingException, IOException  {
		
		Map<String, List<PageRendered>> rendersByLanguage = new HashMap<>();
		
		// Each languages, one render.
		for (String lngKey : project.getConfig().getAvailablesLanguages()) {
			List<PageRendered> lstPages = new ArrayList<>();
			rendersByLanguage.put(lngKey, lstPages);
			
			// Each page, one render.
			for (Template template : project.getPages()) {
				PageRendered render = PageRendered.fromTemplate(lngKey, environment, template.getName(), project, jsonMapper );
				dustJs.render( render );
				lstPages.add(render);
			}
		}
		
		return rendersByLanguage;
	}
	
	/**
	 * Store generate code in folder.
	 * 
	 * @param targetFolder
	 * @throws IOException 
	 */
	protected void writeToFileSystem(Path targetFolder, Map<String, List<PageRendered>> rederedEnvironment) throws IOException {
		
		targetFolder = targetFolder.toAbsolutePath().normalize();
		
		// If folder not exist, create.
		if(!targetFolder.toFile().exists()) {
			Files.createDirectories(targetFolder);
		}
		
		for (String lng : rederedEnvironment.keySet()) {
			List<PageRendered> lst = rederedEnvironment.get(lng);
			String lngFolder = targetFolder.toString() + File.separator + lng;
			for (PageRendered pageRendered : lst) {
				String pagePathStr = pageRendered.getTemplate().getConfigutation().getTargetPath();
				pagePathStr = lngFolder + pagePathStr;
				
				Path filePath = Paths.get(pagePathStr);
				if(!filePath.getParent().toFile().exists()) {
					Files.createDirectories(filePath.getParent());
				}
				
				File file = filePath.toFile();
				try(
						FileWriter fw = new FileWriter(file);
				) {
					
					fw.write(pageRendered.getRendered());
					fw.flush();
					fw.close();
				}
			}
		}
	}
	
}
