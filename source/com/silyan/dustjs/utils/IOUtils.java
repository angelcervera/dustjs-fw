/**
 * 
 */
package com.silyan.dustjs.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * @author Angel Cervera Claudio ( angelcervera@silyan.com )
 *
 */
public class IOUtils {

	public static void copy(final ReadableByteChannel src, final WritableByteChannel dest) throws IOException {
		final ByteBuffer buffer = ByteBuffer.allocateDirect(16 * 1024);
		while (src.read(buffer) != -1) {
			buffer.flip();
			dest.write(buffer);
			buffer.compact();
		}
		buffer.flip();
		while (buffer.hasRemaining()) {
			dest.write(buffer);
		}
	}
	
	public static void copy(final InputStream input, final OutputStream output) throws IOException {
		ReadableByteChannel inputChannel = Channels.newChannel(input);
		WritableByteChannel outputChannel = Channels.newChannel(output);
		
		copy(inputChannel, outputChannel);
		output.flush();
	}
	
	public static String toStringFromInputStream(final InputStream is) {
		try(Scanner sc =  new Scanner( is )) {
			return sc.useDelimiter("\\A").next();
		}
	}
	
	public static String toStringFromClassPath(String path) throws IOException {
		String retValue = "";
		try(
				InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
		) {
			if(is == null) {
				throw new IOException("Not found " + path + " in classpath");
			}
			retValue = IOUtils.toStringFromInputStream(is);
		}
		return retValue;
	}
	
	public static String toStringFromFile(Path path) throws IOException {
		return toStringFromInputStream(Files.newInputStream(path));
	}
	
	/**
	 * Return path relative to base.
	 * 
	 * @param base
	 * @param file
	 * @param normalize
	 * @return
	 */
	public static String getRelativePath(Path base, Path file, boolean normalize) {
		if(normalize) {
			base = base.normalize().toAbsolutePath();
			file = file.normalize().toAbsolutePath();
		}
		String basePath = base.toString();
		String filePath = file.toString();
		if(filePath.indexOf(basePath) != 0) {
			throw new RuntimeException(file.toString() + " is not in folder " + base.toString());
		}
		
		int baseLength = basePath.length();
		String relativePath = filePath.substring(baseLength);
		return relativePath;
	}
	
	/**
	 * Return last part of path, name file o name folder.
	 * 
	 * @param path
	 * @param includeExt if false, exclude extension.
	 * @return
	 */
	public static String getPathName(String path, boolean includeExt) {
        if (path == null) {
            return null;
        }
        int index = path.lastIndexOf(File.separator);
        String name = path.substring(index + 1);
        
        if(!includeExt) {
            index = name.lastIndexOf('.');
            if(index>0) {
            	return name.substring(0, index);
            } else {
            	return name;
            }
        }
        
        return name;
	}
	
}
