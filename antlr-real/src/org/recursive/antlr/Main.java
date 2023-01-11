package org.recursive.antlr;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
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
	private static List<String> sources = new ArrayList<>();
	private static List<String> considered = new ArrayList<>();
	
	public static void main(String[] args) {
		if (args.length < 3) {
			System.err.println("Usage: <file_path> <package_name> <src_path>");
			return;
		}
		
		try {
			for (int i = 0; i < args.length; i++) {
				args[i] = args[i].replace("\\", "/").replace("\"", "");
			}
            
            listf(args[2], sources);
            
            for (int i = 0; i < sources.size(); i++) {
            	sources.set(i, sources.get(i).replaceAll("\\\\", "/"));
            }
			
			Set<String> allFiles = getRelated(args[0], args[1], sources);
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
		
		System.out.println("Finished processing: " + args[0]);
	}
	
	private static Set<String> getRelated(String fileName, String packageName, List<String> sources) {
		Set<String> related = new HashSet<String>();
//		String packagePath = packageToPath(packageName);
		related.add(fileName);
		try (Scanner sc = new Scanner(new File(fileName))) {
	      while (sc.hasNextLine()) {
	        String data = sc.nextLine();
	        if (data.startsWith("import " + packageName)) {
	        	if (!considered.contains(data)) {
	        		considered.add(data);
		        	String newFile = "";
		        	boolean found = false;
		        	//find the file path of of the left most file and write it to a file
		        	String search = packageToPath(data.replaceFirst("import ", "").replace(";", "")) + ".java";
		            try {
		                for (String src: sources) {
		                	if (src.contains(search)) {
		                		newFile = src;
		                		found = true;
		                	}
		                }
		            } catch (Exception e) {
		                e.printStackTrace();
		            }
                
		        	if (found) {
			        	related.addAll(getRelated(newFile, packageName, sources));
		        	} else {
		        		unfound.add(data.replaceFirst("import ", "").replace(";", "") + ".java");
		        	}
	        	}
	        }
	      }
	      sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return related;
	}
	
	public static void listf(String directoryName, List<String> files) {
	    File directory = new File(directoryName);

	    // Get all files from a directory.
	    File[] fList = directory.listFiles();
	    if(fList != null)
	        for (File file : fList) {      
	            if (file.isFile()) {
	                files.add(file.getAbsolutePath());
	            } else if (file.isDirectory()) {
	                listf(file.getAbsolutePath(), files);
	            }
	        }
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
