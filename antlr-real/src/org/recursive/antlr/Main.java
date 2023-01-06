package org.recursive.antlr;

import java.io.File;
import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.recursive.lexer.JavaLexer;
import org.recursive.parser.JavaParser;
import org.recursive.parser.JavaParserListener;

public class Main {

	public static void main(String[] args) {
		try {
			CharStream input = CharStreams.fromFileName("C:/Users/jared/Documents/Datathon/ICPE/Results/libraries/Sources/kafka-2.7.0/jmh-benchmarks/src/main/java/org/apache/kafka/jmh/record/RecordBatchIterationBenchmark.java");
			JavaLexer lexer = new JavaLexer(input);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			JavaParser parser = new JavaParser(tokens);
			JavaParser.CompilationUnitContext tree = parser.compilationUnit(); // parse a compilationUnit
			
			MyListener extractor = new MyListener(parser);
			ParseTreeWalker.DEFAULT.walk(extractor, tree);

			System.out.println("Methods: " + extractor.getMethods());
			System.out.println("Catches: " + extractor.getCatches());
			System.out.println("Fors: " + extractor.getFors());
			System.out.println("EFors: " + extractor.getEFors());
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
