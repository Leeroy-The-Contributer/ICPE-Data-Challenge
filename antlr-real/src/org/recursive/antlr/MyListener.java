package org.recursive.antlr;

import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.recursive.parser.JavaParser;
import org.recursive.parser.JavaParserBaseListener;

public class MyListener extends JavaParserBaseListener {
	private JavaParser parser;
	private int methods, catches, fors, efors = 0;
	
	public MyListener() {
		super();
	}
	
	public MyListener(JavaParser parser) {
		super();
		this.parser = parser;
	}
	
    public void print(RuleContext ctx) {
        explore(ctx, 0);
    }
    
    private void explore(RuleContext ctx, int indentation) {
        String ruleName = JavaParser.ruleNames[ctx.getRuleIndex()];
        for (int i=0;i<indentation;i++) {
            System.out.print("  ");
        }
        System.out.println(ruleName);
        for (int i=0;i<ctx.getChildCount();i++) {
            ParseTree element = ctx.getChild(i);
            if (element instanceof RuleContext) {
                explore((RuleContext)element, indentation + 1);
            }
        }
    }
    
    public int nums = 0;
	public boolean execExitS = false;

	@Override
	public void exitMethodDeclaration(JavaParser.MethodDeclarationContext ctx) {
		methods++;
	}

	@Override
	public void exitCatchClause(JavaParser.CatchClauseContext ctx) {
		catches++;
	}

	@Override
	public void exitForControl(JavaParser.ForControlContext ctx) {
		fors++;
	}

	@Override
	public void exitEnhancedForControl(JavaParser.EnhancedForControlContext ctx) {
		efors++;
	}
	
	public int getMethods() {
		return methods;
	}

	public int getCatches() {
		return catches;
	}

	public int getFors() {
		return fors;
	}
	
	public int getEFors() {
		return efors;
	}
}
