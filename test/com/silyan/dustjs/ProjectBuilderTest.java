package com.silyan.dustjs;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import javax.script.ScriptException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.silyan.dustjs.model.PageRendered;

public class ProjectBuilderTest {
	
	private ProjectBuilder projectBuilder;
	private Map<String, List<PageRendered>> renderedPages;
	
	@BeforeClass
	public void init() throws ScriptException, IOException {
		projectBuilder = new ProjectBuilder(
			Paths.get("./test/com/silyan/dustjs/testfolders")
		);
	}
	
	@Test
	public void compile() {
		try {
			
			projectBuilder.compile();
			Assert.assertEquals(
					projectBuilder.project.components.size(),
					0
			);
			Assert.assertEquals(
					projectBuilder.project.layouts.size(),
					1
			);
			Assert.assertEquals(
					projectBuilder.project.pages.size(),
					1
			);
			
		} catch ( /* IOException | */ ScriptException e) {
			Assert.fail("Unexpected error compiling templates.", e);
		}
	}
	
	@Test(dependsOnMethods={"compile"})
	public void render() {
		try {
			
			renderedPages = projectBuilder.render("test");
			Assert.assertEquals(renderedPages.size(), 3); // Three languages
			
		} catch ( ScriptException | IOException e) {
			Assert.fail("Unexpected error compiling templates.", e);
		}
	}
	
	@Test(dependsOnMethods={"render"})
	public void writeToFileSystem() {
		try {
			
			projectBuilder.writeToFileSystem(
				Paths.get("./build/test/dustjs"),
				renderedPages
			);
			
		} catch ( IOException e) {
			Assert.fail("Unexpected error writing rendered templates.", e);
		}
	}
	
	

}
