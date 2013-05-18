/**
 * 
 */
package com.silyan.dustjs;

import java.io.IOException;
import java.nio.file.Paths;

import javax.script.ScriptException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.silyan.dustjs.i18n.I18n;

/**
 * @author Angel Cervera Claudio ( angelcervera@silyan.com )
 *
 */
public class Main {

	/**
	 * 
	 * arguments:
	 * 0 - action: render, i18nExport, i18nImport
	 * 
	 * For render action:
	 * 1 -
	 * 2 -
	 * 3 -
	 * 4 -
	 *    
	 * For i18nExport action:
	 * 1 - source folder.
	 * 2 - target folder.
	 *    
	 * For i18nImport action:
	 * 1 - source folder.
	 * 2 - target folder.
	 * 
	 * @param args
	 * @throws IOException 
	 * @throws ScriptException 
	 */
	public static void main(String[] args) {
		try {
			
			if("render".equals(args[0])) {
				ProjectBuilder pb = new ProjectBuilder( Paths.get(args[1]), Paths.get(args[4]));
				pb.compile();
				pb.render(args[3], Paths.get(args[2]));
			} else {
				if("i18nExport".equals(args[0])) {
					new I18n().fromTemplatesInFolder(Paths.get(args[1]), Paths.get(args[2]), new ObjectMapper());
				} else {
					if("i18nImport".equals(args[0])) {
						new I18n().fromCSVsInFolder(Paths.get(args[1]), Paths.get(args[2]));
					} else {
						throw new Exception("Unknow action.");
					}
				}
			}
			

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
