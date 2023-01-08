package org.recursive.antlr;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.recursive.lexer.JavaLexer;
import org.recursive.parser.JavaParser;

public class Main {
	private static Set<String> unfound = new HashSet<>();
	
	public static void main(String[] args) {
		if (args.length < 3) {
			System.err.println("Usage: <file_path> <package_name> <src_path> [<src_path>...]");
			return;
		}
		
		try {
			for (int i = 0; i < args.length; i++) {
				args[i] = args[i].replace("\\", "/").replace("\"", "");
			}
			
			List<String> srcPaths = new ArrayList<>();
			for (int i = 2; i < args.length; i++) {
				srcPaths.add(args[i]);
			}
			
			Set<String> allFiles = getRelated(args[0], args[1], srcPaths);
			String outputFilePath = "/output/" + args[1] + "/";
			
			for (String fileName: allFiles) {
				CharStream input = CharStreams.fromFileName(fileName);
				JavaLexer lexer = new JavaLexer(input);
				CommonTokenStream tokens = new CommonTokenStream(lexer);
				JavaParser parser = new JavaParser(tokens);
				JavaParser.CompilationUnitContext tree = parser.compilationUnit(); // parse a compilationUnit
				
				MyListener extractor = new MyListener();
				ParseTreeWalker.DEFAULT.walk(extractor, tree);
	
				Map<String, Integer> values = extractor.getValues();
				
				System.out.println(values.toString());
				
				String dirName = System.getProperty("user.dir") + outputFilePath + args[0].substring(args[0].lastIndexOf("/"), args[0].lastIndexOf("."));
				File dir = new File(dirName);
				dir.mkdirs();
				File file = new File(dirName + "/" + pathToPackage(fileName.substring(fileName.indexOf(packageToPath(args[1])), fileName.lastIndexOf('.')) + ".txt"));
				try (BufferedWriter bf = new BufferedWriter(new FileWriter(file))) {
					for (Map.Entry<String, Integer> entry : values.entrySet()) {
						bf.write(entry.getKey() + ":" + entry.getValue());
						bf.newLine();
					}
					bf.flush();
				}
			}
			
			String unfoundDirName = System.getProperty("user.dir") + outputFilePath + args[0].substring(args[0].lastIndexOf("/"), args[0].lastIndexOf(".")) + "/unfound/";
			File unfoundDir = new File(unfoundDirName);
			unfoundDir.mkdirs();
			File unfoundFile = new File(unfoundDirName + "/" + "unfound.txt");
			try (BufferedWriter bf = new BufferedWriter(new FileWriter(unfoundFile))) {
				for (String classFile: unfound) {
					bf.write(classFile);
					bf.newLine();
				}
				bf.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static Set<String> getRelated(String fileName, String packageName, List<String> srcPaths) {
		Set<String> related = new HashSet<String>();
//		String packagePath = packageToPath(packageName);
		related.add(fileName);
		try (Scanner sc = new Scanner(new File(fileName))) {
	      while (sc.hasNextLine()) {
	        String data = sc.nextLine();
	        if (data.startsWith("import " + packageName)) {
	        	String newFile = "";
	        	boolean found = false;
	        	for (String path: srcPaths) {
	        		newFile = path + "/" + packageToPath(data.replaceFirst("import ", "").replace(";", "")) + ".java";
	        		File f = new File(newFile);
	        		if (f.exists()) {
	        			found = true;
	        			break;
	        		}
	        	}
	        	if (found) {
		        	related.addAll(getRelated(newFile, packageName, srcPaths));
	        	} else {
	        		unfound.add(data.replaceFirst("import ", "").replace(";", "") + ".java");
	        	}
	        }
	      }
	      sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return related;
	}
	
	private static String packageToPath(String packageName) {
		String path = "";
		String[] words = packageName.split("\\.");
		for (String word: words) {
			path += word + "/";
		}
		
		path = path.substring(0, path.lastIndexOf("/"));
		
		return path;
	}
	
	private static String pathToPackage(String pathName) {
		String packageName = "";
		String[] words = pathName.split("/");
		for (String word: words) {
			packageName += word + ".";
		}
		
		packageName = packageName.substring(0, packageName.lastIndexOf("."));
		
		return packageName;
	}

}
