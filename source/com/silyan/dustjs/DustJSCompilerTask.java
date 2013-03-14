/**
 * 
 */
package com.silyan.dustjs;

import java.io.IOException;

import javax.script.ScriptException;

import com.silyan.dustjs.model.Template;

/**
 * @author Angel Cervera Claudio ( angelcervera@silyan.com )
 *
 */
public class DustJSCompilerTask implements Runnable {
	
	private DustJs dustJs;
	private Template template;
	
	public DustJSCompilerTask(DustJs dustJs, Template template) {
		this.dustJs = dustJs;
		this.template = template;
	}

	@Override
	public void run() {
		try {
			dustJs.compile( template );
		} catch (ScriptException | IOException e) {
			e.printStackTrace();
		}
	}

	

}
