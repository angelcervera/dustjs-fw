/**
 * 
 */
package com.silyan.dustjs;

import java.io.IOException;
import java.nio.file.Paths;

import javax.script.ScriptException;

/**
 * @author Angel Cervera Claudio ( angelcervera@silyan.com )
 *
 */
public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ScriptException 
	 */
	public static void main(String[] args) {
		try {
			ProjectBuilder pb = new ProjectBuilder( Paths.get(args[0]), Paths.get(args[3]));
			pb.compile();
			pb.render(args[2], Paths.get(args[1]));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
