package com.silyan.dustjs.utils;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonUtilsTest {
	
	private ObjectMapper mapper = new ObjectMapper();

	@Test
	public void loadFilesAsMap() {
		
		try {
			Map<String,Map<String,String>> maps = JacksonUtils.loadFilesAsMap(Paths.get("test/com/silyan/dustjs/testfolders/layouts/main/i18n/client"), mapper);
		
			Assert.assertEquals(maps.size(), 2);
			Assert.assertNotNull(maps.get("es"));
			Assert.assertNotNull(maps.get("en"));
			
			Map<String, String> lngEs = maps.get("es");
			Assert.assertNotNull(lngEs.get("example"));
			Assert.assertEquals(lngEs.get("example"), "Ejemplo");
		
			Map<String, String> lngEn = maps.get("en");
			Assert.assertNotNull(lngEn.get("example"));
			Assert.assertEquals(lngEn.get("example"), "Example");
		
		} catch(Exception e) {
			Assert.fail("Unexpected error in loadFilesAsMap.", e);
		}
		
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void mergeMaps() {
		
		try {
			
			Map<String, String> map1 = new HashMap<String, String>();
			map1.put("param1", "value1");
			map1.put("param2", "value2");
			map1.put("param3", "value3");
			map1.put("param4", "value4");
			map1.put("param5", "value5");
			
			Map<String, String> map2 = new HashMap<String, String>();
			map2.put("param1", "value12");
			map2.put("param6", "value6");

			Map<String, String> ret = (Map<String, String>)JacksonUtils.mergeMaps(map1, map2);
			
			Assert.assertNotNull(ret.get("param1"));
			Assert.assertNotNull(ret.get("param2"));
			Assert.assertNotNull(ret.get("param3"));
			Assert.assertNotNull(ret.get("param4"));
			Assert.assertNotNull(ret.get("param5"));
			Assert.assertNotNull(ret.get("param6"));
			
			Assert.assertEquals(ret.get("param1"), "value12");
			Assert.assertEquals(ret.get("param2"), "value2");
			Assert.assertEquals(ret.get("param6"), "value6");
			
		
		} catch(Exception e) {
			Assert.fail("Unexpected error in mergeMaps.", e);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void mergeMapsWithRecursion() {
		
		try {
			
			Map<String, Object> map12 = new HashMap<String, Object>();
			map12.put("param1", "value1");
			map12.put("param2", "value2");
			map12.put("param3", "value3");
			map12.put("param4", "value4");
			map12.put("param5", "value5");
			
			Map<String, Object> map22 = new HashMap<String, Object>();
			map22.put("param1", "value12");
			map22.put("param6", "value6");
			
			
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("param1", "value1");
			map1.put("param2", "value2");
			map1.put("param3", "value3");
			map1.put("param4", "value4");
			map1.put("param5", "value5");
			map1.put("recursive1", map12);
			map1.put("recursive2", map12);
			
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("param1", "value12");
			map2.put("param6", "value6");
			map2.put("recursive1", map22);

			Map<String, Object> ret = (Map<String, Object>)JacksonUtils.mergeMapsWithRecursion(map1, map2);
			
			Assert.assertNotNull(ret.get("param1"));
			Assert.assertNotNull(ret.get("param2"));
			Assert.assertNotNull(ret.get("param3"));
			Assert.assertNotNull(ret.get("param4"));
			Assert.assertNotNull(ret.get("param5"));
			Assert.assertNotNull(ret.get("param6"));
			
			Assert.assertEquals(ret.get("param1"), "value12");
			Assert.assertEquals(ret.get("param2"), "value2");
			Assert.assertEquals(ret.get("param6"), "value6");
			
			
			// Test recursion.
			Assert.assertNotNull(ret.get("recursive1"));
			Assert.assertNotNull(ret.get("recursive2"));
			Assert.assertEquals(((Map<String,String>)ret.get("recursive1")).get("param1"), "value12");
			Assert.assertEquals(((Map<String,String>)ret.get("recursive1")).get("param2"), "value2");
			Assert.assertEquals(((Map<String,String>)ret.get("recursive1")).get("param6"), "value6");
			Assert.assertNotNull(((Map<String,String>)ret.get("recursive2")).get("param1"));
			Assert.assertNull(((Map<String,String>)ret.get("recursive2")).get("param6"));
			
		
		} catch(Exception e) {
			Assert.fail("Unexpected error in mergeMaps.", e);
		}
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void mergeListOfMaps() {
		
		try {
			
			Map<String, String> map1 = new HashMap<String, String>();
			map1.put("param1", "value1");
			map1.put("param2", "value2");
			map1.put("param3", "value3");
			map1.put("param4", "value4");
			map1.put("param5", "value5");
			
			Map<String, String> map2 = new HashMap<String, String>();
			map2.put("param1", "value12");
			map2.put("param6", "value6");
			
			List<Map> lstMaps = new ArrayList<>();
			lstMaps.add(map1);
			lstMaps.add(map2);
			

			Map<String, String> ret = (Map<String, String>)JacksonUtils.mergeListOfMaps(lstMaps);
			
			Assert.assertNotNull(ret.get("param1"));
			Assert.assertNotNull(ret.get("param2"));
			Assert.assertNotNull(ret.get("param3"));
			Assert.assertNotNull(ret.get("param4"));
			Assert.assertNotNull(ret.get("param5"));
			Assert.assertNotNull(ret.get("param6"));
			
			Assert.assertEquals(ret.get("param1"), "value12");
			Assert.assertEquals(ret.get("param2"), "value2");
			Assert.assertEquals(ret.get("param6"), "value6");
			
		
		} catch(Exception e) {
			Assert.fail("Unexpected error in merge maps.", e);
		}
		
	}
	
	@Test
	public void mergeEnvironmentMaps() {
		
		try {
			
			Map<String, Object> defaultProperties = new HashMap<String, Object>();
			defaultProperties.put("param1", "value1");
			defaultProperties.put("param2", "value2");
			defaultProperties.put("param3", "value3");
			defaultProperties.put("param4", "value4");
			defaultProperties.put("param5", "value5");
			
			Map<String, Object> env1 = new HashMap<String, Object>();
			env1.put("param1", "value12");
			env1.put("param6", "value6");
			
			Map<String, Map<String, Object>> environments = new HashMap<>();
			environments.put("default", defaultProperties);
			environments.put("env1", env1);
			
			Map<String, Map<String, Object>> ret = JacksonUtils.mergeEnvironmentMaps(environments);
			
			defaultProperties = ret.get("default");
			env1 = ret.get("env1");
			
			Assert.assertEquals(ret.size(), 2);
			Assert.assertEquals(defaultProperties.size(), 5);
			Assert.assertEquals(env1.size(), 6);
			
			Assert.assertNotNull(env1.get("param1"));
			Assert.assertNotNull(env1.get("param2"));
			Assert.assertNotNull(env1.get("param3"));
			Assert.assertNotNull(env1.get("param4"));
			Assert.assertNotNull(env1.get("param5"));
			Assert.assertNotNull(env1.get("param6"));
			
			Assert.assertEquals(env1.get("param1"), "value12");
			Assert.assertEquals(env1.get("param2"), "value2");
			Assert.assertEquals(env1.get("param6"), "value6");
			
		
		} catch(Exception e) {
			Assert.fail("Unexpected error in mergeEnvironmentMaps.", e);
		}
		
	}

//	@Test
//	public void merge() {
//		throw new RuntimeException("Test not implemented");
//	}
}
