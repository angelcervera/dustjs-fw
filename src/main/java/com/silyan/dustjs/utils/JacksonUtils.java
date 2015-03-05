/**
 * 
 */
package com.silyan.dustjs.utils;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Angel Cervera Claudio ( angelcervera@silyan.com )
 *
 */
public class JacksonUtils {
	
//	/**
//	 * Merge to beans.
//	 * {@link http://stackoverflow.com/questions/9895041/merging-two-json-documents-using-jackson }
//	 * 
//	 * @param mainNode
//	 * @param updateNode
//	 * @return
//	 */
//	public static JsonNode merge(JsonNode mainNode, JsonNode updateNode) {
//
//	    Iterator<String> fieldNames = updateNode.fieldNames();
//	    while (fieldNames.hasNext()) {
//
//	        String fieldName = fieldNames.next();
//	        JsonNode jsonNode = mainNode.get(fieldName);
//	        // if field doesn't exist or is an embedded object
//	        if (jsonNode != null && jsonNode.isObject()) {
//	            merge(jsonNode, updateNode.get(fieldName));
//	        }
//	        else {
//	            if (mainNode instanceof ObjectNode) {
//	                // Overwrite field
//	                JsonNode value = updateNode.get(fieldName);
//	                ((ObjectNode) mainNode).put(fieldName, value);
//	            }
//	        }
//
//	    }
//
//	    return mainNode;
//	}
	
	/**
	 * Load files in folder as Map.
	 * Ideal for load i18n translations.
	 * 
	 * @param folder
	 * @return
	 * @throws IOException 
	 */
	public static Map<String,Map<String,String>> loadFilesAsMap(Path folder, ObjectMapper mapper) throws IOException {

		class LoadFileAsMap extends SimpleFileVisitor<Path> {

			private PathMatcher matcher;
			private ObjectMapper mapper;
			private Map<String,Map<String,String>> maps;

			public LoadFileAsMap(Map<String,Map<String,String>> maps, ObjectMapper mapper) {
				super();
				this.maps = maps;
				this.mapper = mapper;
				this.matcher = FileSystems.getDefault().getPathMatcher("glob:*.json");
			}

			/* (non-Javadoc)
			 * @see java.nio.file.SimpleFileVisitor#visitFile(java.lang.Object, java.nio.file.attribute.BasicFileAttributes)
			 */
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				Path name = file.getFileName();
				if (name != null && matcher.matches(name)) {
					try {
						@SuppressWarnings("unchecked")
						Map<String, String> translations = mapper.readValue(file.toFile(), Map.class);
						maps.put(IOUtils.getPathName(file.toString(), false), translations);
					} catch (IOException e) {
						e.printStackTrace();
						return FileVisitResult.TERMINATE;
					}
				}
				return FileVisitResult.CONTINUE;
			}

		}

		Map<String,Map<String,String>> maps = new HashMap<String, Map<String,String>>();
		Files.walkFileTree(folder, new LoadFileAsMap(maps, mapper));
		return maps;
	}
	
	/**
	 * Merge two maps.
	 * 
	 * @param defaultMap
	 * @param otherMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map mergeMaps(Map defaultMap, Map otherMap) {
		Map newMap = new HashMap<>();
		newMap.putAll(defaultMap);
		newMap.putAll(otherMap);
		return newMap;
	}
	
	/**
	 * Merge two maps with recursion if value is a Map
	 * 
	 * @param defaultMap
	 * @param otherMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map mergeMapsWithRecursion(Map defaultMap, Map otherMap) {
		Map newMap = new HashMap<>();
		newMap.putAll(otherMap);
		for (Object key : defaultMap.keySet()) {
			Object obj = defaultMap.get(key);
			if(!newMap.containsKey(key)) {
				newMap.put(key, obj);
			} else {
				if( obj instanceof Map ) {
					newMap.put(key, mergeMapsWithRecursion((Map)obj, (Map)newMap.get(key)) );
				}
			}
		}
		return newMap;
	}
	
	/**
	 * Merge list of maps.
	 * 
	 * @param maps
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map mergeListOfMaps(List<Map> maps) {
		Map newMap = new HashMap<>();
		for (Map map : maps) {
			newMap.putAll(map);
		}
		return newMap;
	}
	
	/**
	 * Update all properties in "environment" map including values in "default" map.
	 * 
	 * @param enviroment
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	public static Map<String, Map<String, Object>> mergeEnvironmentMaps(Map<String, Map<String, Object>> enviroment) {
		
		// Merge default environment with others.
		Map<String, Object> defaultEnviroment = enviroment.get("default");
		Map<String, Map<String, Object>> newEnviroment = new HashMap<>();
		newEnviroment.put("default", defaultEnviroment);
		for (String enviromentName : enviroment.keySet()) {
			if(!enviromentName.equals("default")) {
				newEnviroment.put(enviromentName, JacksonUtils.mergeMaps(defaultEnviroment, enviroment.get(enviromentName)));
			}
		}
		return newEnviroment;
	}
	

	

}
