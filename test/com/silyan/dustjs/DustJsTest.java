package com.silyan.dustjs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.script.ScriptException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.silyan.dustjs.model.PageRendered;
import com.silyan.dustjs.model.Template;
import com.silyan.dustjs.utils.IOUtils;

public class DustJsTest {
	
	private DustJs dustjs;
	
	@BeforeClass
	public void init() throws ScriptException {
		dustjs = new DustJs();
	}
	
	@Test
	public void compile() {
		List<Template> templates = new ArrayList<>();
		
		try {
			
			for (int i = 1; i < 5; i++) {
				TemplateModelTest test = new TemplateModelTest("test/com/silyan/dustjs/test"+i+"/test", IOUtils.toStringFromClassPath("com/silyan/dustjs/test"+i+"/test.tl"));
				test.compiledExpected = IOUtils.toStringFromClassPath("com/silyan/dustjs/test"+i+"/test.compiled" );
				templates.add( test );
			}
			
			dustjs.compile(templates);
			for (Template templateBean : templates) {
				((TemplateModelTest)templateBean).testCompiled();
			}
			
			dustjs.loadSource(templates);
			
		} catch (ScriptException e) {
			Assert.fail("Error compiling", e);
		} catch (IOException e) {
			Assert.fail("Error loading templates for testing", e);
		}
	}
	
	@Test(dependsOnMethods={"compile"})
	public void render() {
		List<PageRendered> renders = new ArrayList<>();
		
		try {
			
			for (int i = 1; i < 3; i++) {
				RenderModelTest test = new RenderModelTest(
					"test/com/silyan/dustjs/test"+i+"/test",
					IOUtils.toStringFromClassPath("com/silyan/dustjs/test"+i+"/test.json" )
				);
				test.renderedExpected = IOUtils.toStringFromClassPath("com/silyan/dustjs/test"+i+"/test.rendered" );
				renders.add( test );
			}
			
			dustjs.render(renders);
			for (PageRendered render : renders) {
				((RenderModelTest)render).testRendered();
			}
			
		} catch (ScriptException e) {
			Assert.fail("Error compiling", e);
		} catch (IOException e) {
			Assert.fail("Error loading templates for testing", e);
		}
	}
	
	public class TemplateModelTest extends Template {
		
		public String compiledExpected;
		
		public TemplateModelTest() {
			super();
		}

		public TemplateModelTest(String name, String template) {
			super(name, template);
		}

		public void testCompiled() {
			Assert.assertEquals(compiled, compiledExpected);
		}
		
	}
	
	public class RenderModelTest extends PageRendered {
		
		public String renderedExpected;

		public RenderModelTest() {
			super();
		}

		public RenderModelTest(String name, String json) {
			super(name, json);
		}

		public void testRendered() {
			Assert.assertEquals(rendered, renderedExpected);
		}
		
	}
}
