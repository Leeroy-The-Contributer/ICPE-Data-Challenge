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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

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
			
			// Here you do magic
			Map<String, Integer> all_values = initValues();
			
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
						all_values.put(entry.getKey(), all_values.get(entry.getKey()) + entry.getValue());
						bf.write(entry.getKey() + ":" + entry.getValue());
						bf.newLine();
					}
					bf.flush();
				}
				
			}
			String final_out = System.getProperty("user.dir") + "/output/" + pathToPackage(args[0].substring(args[0].indexOf(packageToPath(args[1])), args[0].lastIndexOf('.'))) + "_everything.txt";
			File final_out_file = new File(final_out);
			try (BufferedWriter bf = new BufferedWriter(new FileWriter(final_out_file))) {
				for (Map.Entry<String, Integer> entry : all_values.entrySet()) {
					bf.write(entry.getKey() + ":" + entry.getValue());
					bf.newLine();
				}
				bf.flush();
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
	
	private static Map<String, Integer> initValues() {
		Map<String, Integer> values = new TreeMap<String, Integer>();
		values.putIfAbsent("CompilationUnit", 0);
		values.putIfAbsent("PackageDeclaration", 0);
		values.putIfAbsent("ImportDeclaration", 0);
		values.putIfAbsent("TypeDeclaration", 0);
		values.putIfAbsent("Modifier", 0);
		values.putIfAbsent("ClassOrInterfaceModifier", 0);
		values.putIfAbsent("VariableModifier", 0);
		values.putIfAbsent("ClassDeclaration", 0);
		values.putIfAbsent("TypeParameters", 0);
		values.putIfAbsent("TypeParameter", 0);
		values.putIfAbsent("TypeBound", 0);
		values.putIfAbsent("EnumDeclaration", 0);
		values.putIfAbsent("EnumConstants", 0);
		values.putIfAbsent("EnumConstant", 0);
		values.putIfAbsent("EnumBodyDeclarations", 0);
		values.putIfAbsent("InterfaceDeclaration", 0);
		values.putIfAbsent("ClassBody", 0);
		values.putIfAbsent("InterfaceBody", 0);
		values.putIfAbsent("ClassBodyDeclaration", 0);
		values.putIfAbsent("MemberDeclaration", 0);
		values.putIfAbsent("MethodBody", 0);
		values.putIfAbsent("TypeTypeOrVoid", 0);
		values.putIfAbsent("GenericMethodDeclaration", 0);
		values.putIfAbsent("GenericConstructorDeclaration", 0);
		values.putIfAbsent("ConstructorDeclaration", 0);
		values.putIfAbsent("CompactConstructorDeclaration", 0);
		values.putIfAbsent("FieldDeclaration", 0);
		values.putIfAbsent("InterfaceBodyDeclaration", 0);
		values.putIfAbsent("InterfaceMemberDeclaration", 0);
		values.putIfAbsent("ConstDeclaration", 0);
		values.putIfAbsent("ConstantDeclarator", 0);
		values.putIfAbsent("InterfaceMethodDeclaration", 0);
		values.putIfAbsent("InterfaceMethodModifier", 0);
		values.putIfAbsent("GenericInterfaceMethodDeclaration", 0);
		values.putIfAbsent("InterfaceCommonBodyDeclaration", 0);
		values.putIfAbsent("VariableDeclarators", 0);
		values.putIfAbsent("VariableDeclarator", 0);
		values.putIfAbsent("VariableDeclaratorId", 0);
		values.putIfAbsent("VariableInitializer", 0);
		values.putIfAbsent("ArrayInitializer", 0);
		values.putIfAbsent("ClassOrInterfaceType", 0);
		values.putIfAbsent("TypeArgument", 0);
		values.putIfAbsent("QualifiedNameList", 0);
		values.putIfAbsent("FormalParameters", 0);
		values.putIfAbsent("ReceiverParameter", 0);
		values.putIfAbsent("FormalParameterList", 0);
		values.putIfAbsent("FormalParameter", 0);
		values.putIfAbsent("LastFormalParameter", 0);
		values.putIfAbsent("LambdaLVTIList", 0);
		values.putIfAbsent("LambdaLVTIParameter", 0);
		values.putIfAbsent("QualifiedName", 0);
		values.putIfAbsent("Literal", 0);
		values.putIfAbsent("IntegerLiteral", 0);
		values.putIfAbsent("FloatLiteral", 0);
		values.putIfAbsent("AltAnnotationQualifiedName", 0);
		values.putIfAbsent("Annotation", 0);
		values.putIfAbsent("ElementValuePairs", 0);
		values.putIfAbsent("ElementValuePair", 0);
		values.putIfAbsent("ElementValue", 0);
		values.putIfAbsent("ElementValueArrayInitializer", 0);		
		values.putIfAbsent("AnnotationTypeDeclaration", 0);
		values.putIfAbsent("AnnotationTypeBody", 0);
		values.putIfAbsent("AnnotationTypeElementDeclaration", 0);
		values.putIfAbsent("AnnotationTypeElementRest", 0);
		values.putIfAbsent("AnnotationMethodOrConstantRest", 0);
		values.putIfAbsent("AnnotationMethodRest", 0);
		values.putIfAbsent("AnnotationConstantRest", 0);
		values.putIfAbsent("DefaultValue", 0);		
		values.putIfAbsent("ModuleDeclaration", 0);
		values.putIfAbsent("ModuleBody", 0);
		values.putIfAbsent("ModuleDirective", 0);
		values.putIfAbsent("RequiresModifier", 0);
		values.putIfAbsent("RecordDeclaration", 0);
		values.putIfAbsent("RecordHeader", 0);
		values.putIfAbsent("RecordComponentList", 0);
		values.putIfAbsent("RecordComponent", 0);
		values.putIfAbsent("RecordBody", 0);
		values.putIfAbsent("Block", 0);
		values.putIfAbsent("BlockStatement", 0);
		values.putIfAbsent("LocalVariableDeclaration", 0);
		values.putIfAbsent("Identifier", 0);
		values.putIfAbsent("TypeIdentifier", 0);
		values.putIfAbsent("LocalTypeDeclaration", 0);
		values.putIfAbsent("Statement", 0);
		values.putIfAbsent("CatchType", 0);
		values.putIfAbsent("FinallyBlock", 0);		
		values.putIfAbsent("ResourceSpecification", 0);
		values.putIfAbsent("Resources", 0);
		values.putIfAbsent("Resource", 0);
		values.putIfAbsent("SwitchBlockStatementGroup", 0);		
		values.putIfAbsent("SwitchLabel", 0);
		values.putIfAbsent("ForInit", 0);
		values.putIfAbsent("ParExpression", 0);
		values.putIfAbsent("ExpressionList", 0);
		values.putIfAbsent("MethodCall", 0);
		values.putIfAbsent("Expression", 0);
		values.putIfAbsent("Pattern", 0);
		values.putIfAbsent("LambdaExpression", 0);
		values.putIfAbsent("LambdaParameters", 0);
		values.putIfAbsent("LambdaBody", 0);
		values.putIfAbsent("Primary", 0);
		values.putIfAbsent("SwitchExpression", 0);
		values.putIfAbsent("SwitchLabeledRule", 0);
		values.putIfAbsent("GuardedPattern", 0);
		values.putIfAbsent("SwitchRuleOutcome", 0);
		values.putIfAbsent("ClassType", 0);
		values.putIfAbsent("Creator", 0);
		values.putIfAbsent("CreatedName", 0);
		values.putIfAbsent("InnerCreator", 0);
		values.putIfAbsent("ArrayCreatorRest", 0);
		values.putIfAbsent("ClassCreatorRest", 0);
		values.putIfAbsent("ExplicitGenericInvocation", 0);
		values.putIfAbsent("TypeArgumentsOrDiamond", 0);
		values.putIfAbsent("NonWildcardTypeArgumentsOrDiamond", 0);
		values.putIfAbsent("NonWildcardTypeArguments", 0);
		values.putIfAbsent("TypeList", 0);
		values.putIfAbsent("TypeType", 0);
		values.putIfAbsent("PrimitiveType", 0);
		values.putIfAbsent("TypeArguments", 0);
		values.putIfAbsent("SuperSuffix", 0);
		values.putIfAbsent("ExplicitGenericInvocationSuffix", 0);
		values.putIfAbsent("Arguments", 0);
		values.putIfAbsent("EveryRule", 0);
		values.putIfAbsent("MethodDeclaration", 0);
		values.putIfAbsent("CatchClause", 0);
		values.putIfAbsent("ForControl", 0);
		values.putIfAbsent("EnhancedForControl", 0);
		
		return values;
	}

}
