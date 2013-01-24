package com.silyan.dustjs.model;

import java.io.IOException;
import java.nio.file.Paths;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.silyan.dustjs.model.Project;

public class ProjectTest {

	@Test
	public void fromFolder() {
		try {
			Project project = Project.fromFolder(
					Paths.get("./test/com/silyan/dustjs/testfolders"),
					new ObjectMapper()
			);
			Assert.assertEquals(project.getLayouts().size(), 1);
		} catch (IOException e) {
			Assert.fail("Unexpected error building Project from folder.", e);
		}
	}
}
