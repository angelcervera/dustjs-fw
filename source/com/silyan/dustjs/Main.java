/**
 * 
 */
package com.silyan.dustjs;

import java.io.IOException;
import java.nio.file.Paths;

import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
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
		
		System.out.println("Sacando lista de script engines");
		for (ScriptEngineFactory sef : new ScriptEngineManager().getEngineFactories()) {
			  System.out.println(sef);
			  System.out.append("  Engine: ")
			      .append(sef.getEngineName())
			      .append(" ")
			      .println(sef.getEngineVersion());
			  System.out.append("  Language: ")
			      .append(sef.getLanguageName())
			      .append(" ")
			      .println(sef.getLanguageVersion());
			  System.out.append("  Names: ")
			      .println(sef.getNames());
			}
		
		System.out.println("FIN Sacando lista de script engines");
		
		try {
			new ProjectBuilder( Paths.get(args[0])).process(args[2], Paths.get(args[1]));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
