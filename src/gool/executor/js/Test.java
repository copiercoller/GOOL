package gool.executor.js;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import gool.Settings;

public class Test {
	public static void main(String[] arg) {
		String outputDir = Settings.get("js_out_dir");
		JSCompiler js = new JSCompiler(new File(outputDir), new ArrayList<File>());
		File file = new File("home/toure/Desktop/script.js");
		List<File> classPath = new ArrayList<File>();
		try {
			System.out.println(js.run(file, classPath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
