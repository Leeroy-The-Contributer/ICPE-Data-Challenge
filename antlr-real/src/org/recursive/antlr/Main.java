package org.recursive.antlr;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.recursive.lexer.JavaLexer;
import org.recursive.parser.JavaParser;

public class Main {
	final static String outputFilePath = "/output/test.txt";
	public static void main(String[] args) {
		try {
			CharStream input = CharStreams.fromFileName("/sources/kafka-2.7.0/jmh-benchmarks/src/main/java/org/apache/kafka/jmh/record/RecordBatchIterationBenchmark.java");
			JavaLexer lexer = new JavaLexer(input);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			JavaParser parser = new JavaParser(tokens);
			JavaParser.CompilationUnitContext tree = parser.compilationUnit(); // parse a compilationUnit
			
			MyListener extractor = new MyListener();
			ParseTreeWalker.DEFAULT.walk(extractor, tree);

			Map<String, Integer> values = extractor.getValues();
			
			System.out.println(values.toString());
			
			File file = new File(outputFilePath);
			
			try (BufferedWriter bf = new BufferedWriter(new FileWriter(file))) {
				for (Map.Entry<String, Integer> entry : values.entrySet()) {
					bf.write(entry.getKey() + ":" + entry.getValue());
					bf.newLine();
				}
				bf.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	public static void main(String[] args) throws IOException {
//        ParserFacade parserFacade = new ParserFacade();
//        AstPrinter astPrinter = new AstPrinter();
//        astPrinter.print(parserFacade.parse(new File("examples/simple.py")));
//    }

}
