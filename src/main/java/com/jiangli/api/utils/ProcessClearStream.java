package com.jiangli.api.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class ProcessClearStream extends Thread {
	private InputStream inputStream;
	private String type;
	protected final Log log = LogFactory.getLog(getClass());

	public ProcessClearStream(InputStream inputStream, String type) {
		this.inputStream = inputStream;
		this.type = type;
	}

	public void run() {
		try {
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream);
			BufferedReader br = new BufferedReader(inputStreamReader);
			// 打印信息
			String line = null;
			while ((line = br.readLine()) != null) {
				log.debug(type + ">" + line);
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
