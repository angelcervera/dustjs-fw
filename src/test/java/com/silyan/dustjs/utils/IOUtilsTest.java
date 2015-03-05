package com.silyan.dustjs.utils;

import java.nio.file.Paths;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.silyan.dustjs.utils.IOUtils;

public class IOUtilsTest {

  @Test
  public void getRelativePath() {
		try {
			String relativePath = IOUtils.getRelativePath(Paths.get("/anyfolder"), Paths.get("/anyfolder/otherfolder/file.ext"), true);
			Assert.assertEquals(
					relativePath,
					"/otherfolder/file.ext"
			);
		} catch (Exception e) {
			Assert.fail("Unexpected error obtain relative path.", e);
		}
		
		try {
			IOUtils.getRelativePath(Paths.get("/anyfolder"), Paths.get("/otherRootFolder/otherfolder/file.ext"), true);
			Assert.fail("getRelativePath must throws a RuntimeException.");
		} catch (Exception e) {

		}
  }
  
  @Test
  public void getName() {
		try {
			String name = IOUtils.getPathName("/anyfolder/otherfolder/file.ext", true);
			Assert.assertEquals(
					name,
					"file.ext"
			);
			
			name = IOUtils.getPathName("/file.ext", true);
			Assert.assertEquals(
					name,
					"file.ext"
			);
			
			name = IOUtils.getPathName("file.ext", true);
			Assert.assertEquals(
					name,
					"file.ext"
			);
			
			name = IOUtils.getPathName("/anyfolder/otherfolder", true);
			Assert.assertEquals(
					name,
					"otherfolder"
			);
			
			name = IOUtils.getPathName("/anyfolder", true);
			Assert.assertEquals(
					name,
					"anyfolder"
			);
			
			name = IOUtils.getPathName("/anyRelativefolder", true);
			Assert.assertEquals(
					name,
					"anyRelativefolder"
			);
			
			name = IOUtils.getPathName("/anyfolder/otherfolder/file.ext", false);
			Assert.assertEquals(
					name,
					"file"
			);
			
			name = IOUtils.getPathName("/file.ext", false);
			Assert.assertEquals(
					name,
					"file"
			);
			
			name = IOUtils.getPathName("file.ext", false);
			Assert.assertEquals(
					name,
					"file"
			);
			
			name = IOUtils.getPathName("/anyfolder/otherfolder", false);
			Assert.assertEquals(
					name,
					"otherfolder"
			);
			
			name = IOUtils.getPathName("/anyfolder", false);
			Assert.assertEquals(
					name,
					"anyfolder"
			);
			
			name = IOUtils.getPathName("/anyRelativefolder", false);
			Assert.assertEquals(
					name,
					"anyRelativefolder"
			);
			
		} catch (Exception e) {
			Assert.fail("Unexpected error obtain path name.", e);
		}
		
		try {
			IOUtils.getRelativePath(Paths.get("/anyfolder"), Paths.get("/otherRootFolder/otherfolder/file.ext"), true);
			Assert.fail("getRelativePath must throws a RuntimeException.");
		} catch (Exception e) {

		}
  }
  
}
