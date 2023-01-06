package org.recursive.antlr;

import java.util.Map;
import java.util.TreeMap;

import org.antlr.v4.runtime.ParserRuleContext;
import org.recursive.parser.JavaParser;
import org.recursive.parser.JavaParser.*;
import org.recursive.parser.JavaParserBaseListener;

public class MyListener extends JavaParserBaseListener {
	private Map<String, Integer> values = new TreeMap<String, Integer>();

	public MyListener() {
		super();
		init();
	}
	
	private void init() {
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
	}

	@Override
	public void exitCompilationUnit(CompilationUnitContext ctx) {
		values.put("CompilationUnit", values.get("CompilationUnit") + 1);
	}

	@Override
	public void exitPackageDeclaration(PackageDeclarationContext ctx) {
		values.put("PackageDeclaration", values.get("PackageDeclaration") + 1);
	}

	@Override
	public void exitImportDeclaration(ImportDeclarationContext ctx) {
		values.put("ImportDeclaration", values.get("ImportDeclaration") + 1);
	}

	@Override
	public void exitTypeDeclaration(TypeDeclarationContext ctx) {
		values.put("TypeDeclaration", values.get("TypeDeclaration") + 1);
	}

	@Override
	public void exitModifier(ModifierContext ctx) {
		values.put("Modifier", values.get("Modifier") + 1);
	}

	@Override
	public void exitClassOrInterfaceModifier(ClassOrInterfaceModifierContext ctx) {
		values.put("ClassOrInterfaceModifier", values.get("ClassOrInterfaceModifier") + 1);
	}

	@Override
	public void exitVariableModifier(VariableModifierContext ctx) {
		values.put("VariableModifier", values.get("VariableModifier") + 1);
	}

	@Override
	public void exitClassDeclaration(ClassDeclarationContext ctx) {
		values.put("ClassDeclaration", values.get("ClassDeclaration") + 1);
	}

	@Override
	public void exitTypeParameters(TypeParametersContext ctx) {
		values.put("TypeParameters", values.get("TypeParameters") + 1);
	}

	@Override
	public void exitTypeParameter(TypeParameterContext ctx) {
		values.put("TypeParameter", values.get("TypeParameter") + 1);
	}

	@Override
	public void exitTypeBound(TypeBoundContext ctx) {
		values.put("TypeBound", values.get("TypeBound") + 1);
	}

	@Override
	public void exitEnumDeclaration(EnumDeclarationContext ctx) {
		values.put("EnumDeclaration", values.get("EnumDeclaration") + 1);
	}

	@Override
	public void exitEnumConstants(EnumConstantsContext ctx) {
		values.put("EnumConstants", values.get("EnumConstants") + 1);
	}

	@Override
	public void exitEnumConstant(EnumConstantContext ctx) {
		values.put("EnumConstant", values.get("EnumConstant") + 1);
	}

	@Override
	public void exitEnumBodyDeclarations(EnumBodyDeclarationsContext ctx) {
		values.put("EnumBodyDeclarations", values.get("EnumBodyDeclarations") + 1);
	}

	@Override
	public void exitInterfaceDeclaration(InterfaceDeclarationContext ctx) {
		values.put("InterfaceDeclaration", values.get("InterfaceDeclaration") + 1);
	}

	@Override
	public void exitClassBody(ClassBodyContext ctx) {
		values.put("ClassBody", values.get("ClassBody") + 1);
	}

	@Override
	public void exitInterfaceBody(InterfaceBodyContext ctx) {
		values.put("InterfaceBody", values.get("InterfaceBody") + 1);
	}

	@Override
	public void exitClassBodyDeclaration(ClassBodyDeclarationContext ctx) {
		values.put("ClassBodyDeclaration", values.get("ClassBodyDeclaration") + 1);
	}

	@Override
	public void exitMemberDeclaration(MemberDeclarationContext ctx) {
		values.put("MemberDeclaration", values.get("MemberDeclaration") + 1);
	}

	@Override
	public void exitMethodBody(MethodBodyContext ctx) {
		values.put("MethodBody", values.get("MethodBody") + 1);
	}

	@Override
	public void exitTypeTypeOrVoid(TypeTypeOrVoidContext ctx) {
		values.put("TypeTypeOrVoid", values.get("TypeTypeOrVoid") + 1);
	}

	@Override
	public void exitGenericMethodDeclaration(GenericMethodDeclarationContext ctx) {
		values.put("GenericMethodDeclaration", values.get("GenericMethodDeclaration") + 1);
	}

	@Override
	public void exitGenericConstructorDeclaration(GenericConstructorDeclarationContext ctx) {
		values.put("GenericConstructorDeclaration", values.get("GenericConstructorDeclaration") + 1);
	}

	@Override
	public void exitConstructorDeclaration(ConstructorDeclarationContext ctx) {
		values.put("ConstructorDeclaration", values.get("ConstructorDeclaration") + 1);
	}

	@Override
	public void exitCompactConstructorDeclaration(CompactConstructorDeclarationContext ctx) {
		values.put("CompactConstructorDeclaration", values.get("CompactConstructorDeclaration") + 1);
	}

	@Override
	public void exitFieldDeclaration(FieldDeclarationContext ctx) {
		values.put("FieldDeclaration", values.get("FieldDeclaration") + 1);
	}

	@Override
	public void exitInterfaceBodyDeclaration(InterfaceBodyDeclarationContext ctx) {
		values.put("InterfaceBodyDeclaration", values.get("InterfaceBodyDeclaration") + 1);
	}

	@Override
	public void exitInterfaceMemberDeclaration(InterfaceMemberDeclarationContext ctx) {
		values.put("InterfaceMemberDeclaration", values.get("InterfaceMemberDeclaration") + 1);
	}

	@Override
	public void exitConstDeclaration(ConstDeclarationContext ctx) {
		values.put("ConstDeclaration", values.get("ConstDeclaration") + 1);
	}

	@Override
	public void exitConstantDeclarator(ConstantDeclaratorContext ctx) {
		values.put("ConstantDeclarator", values.get("ConstantDeclarator") + 1);
	}

	@Override
	public void exitInterfaceMethodDeclaration(InterfaceMethodDeclarationContext ctx) {
		values.put("InterfaceMethodDeclaration", values.get("InterfaceMethodDeclaration") + 1);
	}

	@Override
	public void exitInterfaceMethodModifier(InterfaceMethodModifierContext ctx) {
		values.put("InterfaceMethodModifier", values.get("InterfaceMethodModifier") + 1);
	}

	@Override
	public void exitGenericInterfaceMethodDeclaration(GenericInterfaceMethodDeclarationContext ctx) {
		values.put("GenericInterfaceMethodDeclaration", values.get("GenericInterfaceMethodDeclaration") + 1);
	}

	@Override
	public void exitInterfaceCommonBodyDeclaration(InterfaceCommonBodyDeclarationContext ctx) {
		values.put("InterfaceCommonBodyDeclaration", values.get("InterfaceCommonBodyDeclaration") + 1);
	}

	@Override
	public void exitVariableDeclarators(VariableDeclaratorsContext ctx) {
		values.put("VariableDeclarators", values.get("VariableDeclarators") + 1);
	}

	@Override
	public void exitVariableDeclarator(VariableDeclaratorContext ctx) {
		values.put("VariableDeclarator", values.get("VariableDeclarator") + 1);
	}

	@Override
	public void exitVariableDeclaratorId(VariableDeclaratorIdContext ctx) {
		values.put("VariableDeclaratorId", values.get("VariableDeclaratorId") + 1);
	}

	@Override
	public void exitVariableInitializer(VariableInitializerContext ctx) {
		values.put("VariableInitializer", values.get("VariableInitializer") + 1);
	}

	@Override
	public void exitArrayInitializer(ArrayInitializerContext ctx) {
		values.put("ArrayInitializer", values.get("ArrayInitializer") + 1);
	}

	@Override
	public void exitClassOrInterfaceType(ClassOrInterfaceTypeContext ctx) {
		values.put("ClassOrInterfaceType", values.get("ClassOrInterfaceType") + 1);
	}

	@Override
	public void exitTypeArgument(TypeArgumentContext ctx) {
		values.put("TypeArgument", values.get("TypeArgument") + 1);
	}

	@Override
	public void exitQualifiedNameList(QualifiedNameListContext ctx) {
		values.put("QualifiedNameList", values.get("QualifiedNameList") + 1);
	}

	@Override
	public void exitFormalParameters(FormalParametersContext ctx) {
		values.put("FormalParameters", values.get("FormalParameters") + 1);
	}

	@Override
	public void exitReceiverParameter(ReceiverParameterContext ctx) {
		values.put("ReceiverParameter", values.get("ReceiverParameter") + 1);
	}

	@Override
	public void exitFormalParameterList(FormalParameterListContext ctx) {
		values.put("FormalParameterList", values.get("FormalParameterList") + 1);
	}

	@Override
	public void exitFormalParameter(FormalParameterContext ctx) {
		values.put("FormalParameter", values.get("FormalParameter") + 1);
	}

	@Override
	public void exitLastFormalParameter(LastFormalParameterContext ctx) {
		values.put("LastFormalParameter", values.get("LastFormalParameter") + 1);
	}

	@Override
	public void exitLambdaLVTIList(LambdaLVTIListContext ctx) {
		values.put("LambdaLVTIList", values.get("LambdaLVTIList") + 1);
	}

	@Override
	public void exitLambdaLVTIParameter(LambdaLVTIParameterContext ctx) {
		values.put("LambdaLVTIParameter", values.get("LambdaLVTIParameter") + 1);
	}

	@Override
	public void exitQualifiedName(QualifiedNameContext ctx) {
		values.put("QualifiedName", values.get("QualifiedName") + 1);
	}

	@Override
	public void exitLiteral(LiteralContext ctx) {
		values.put("Literal", values.get("Literal") + 1);
	}

	@Override
	public void exitIntegerLiteral(IntegerLiteralContext ctx) {
		values.put("IntegerLiteral", values.get("IntegerLiteral") + 1);
	}

	@Override
	public void exitFloatLiteral(FloatLiteralContext ctx) {
		values.put("FloatLiteral", values.get("FloatLiteral") + 1);
	}

	@Override
	public void exitAltAnnotationQualifiedName(AltAnnotationQualifiedNameContext ctx) {
		values.put("AltAnnotationQualifiedName", values.get("AltAnnotationQualifiedName") + 1);
	}

	@Override
	public void exitAnnotation(AnnotationContext ctx) {
		values.put("Annotation", values.get("Annotation") + 1);
	}

	@Override
	public void exitElementValuePairs(ElementValuePairsContext ctx) {
		values.put("ElementValuePairs", values.get("ElementValuePairs") + 1);
	}

	@Override
	public void exitElementValuePair(ElementValuePairContext ctx) {
		values.put("ElementValuePair", values.get("ElementValuePair") + 1);
	}

	@Override
	public void exitElementValue(ElementValueContext ctx) {
		values.put("ElementValue", values.get("ElementValue") + 1);
	}

	@Override
	public void exitElementValueArrayInitializer(ElementValueArrayInitializerContext ctx) {
		values.put("ElementValueArrayInitializer", values.get("ElementValueArrayInitializer") + 1);
	}

	@Override
	public void exitAnnotationTypeDeclaration(AnnotationTypeDeclarationContext ctx) {
		values.put("AnnotationTypeDeclaration", values.get("AnnotationTypeDeclaration") + 1);
	}

	@Override
	public void exitAnnotationTypeBody(AnnotationTypeBodyContext ctx) {
		values.put("AnnotationTypeBody", values.get("AnnotationTypeBody") + 1);
	}

	@Override
	public void exitAnnotationTypeElementDeclaration(AnnotationTypeElementDeclarationContext ctx) {
		values.put("AnnotationTypeElementDeclaration", values.get("AnnotationTypeElementDeclaration") + 1);
	}

	@Override
	public void exitAnnotationTypeElementRest(AnnotationTypeElementRestContext ctx) {
		values.put("AnnotationTypeElementRest", values.get("AnnotationTypeElementRest") + 1);
	}

	@Override
	public void exitAnnotationMethodOrConstantRest(AnnotationMethodOrConstantRestContext ctx) {
		values.put("AnnotationMethodOrConstantRest", values.get("AnnotationMethodOrConstantRest") + 1);
	}

	@Override
	public void exitAnnotationMethodRest(AnnotationMethodRestContext ctx) {
		values.put("AnnotationMethodRest", values.get("AnnotationMethodRest") + 1);
	}

	@Override
	public void exitAnnotationConstantRest(AnnotationConstantRestContext ctx) {
		values.put("AnnotationConstantRest", values.get("AnnotationConstantRest") + 1);
	}

	@Override
	public void exitDefaultValue(DefaultValueContext ctx) {
		values.put("DefaultValue", values.get("DefaultValue") + 1);
	}

	@Override
	public void exitModuleDeclaration(ModuleDeclarationContext ctx) {
		values.put("ModuleDeclaration", values.get("ModuleDeclaration") + 1);
	}

	@Override
	public void exitModuleBody(ModuleBodyContext ctx) {
		values.put("ModuleBody", values.get("ModuleBody") + 1);
	}

	@Override
	public void exitModuleDirective(ModuleDirectiveContext ctx) {
		values.put("ModuleDirective", values.get("ModuleDirective") + 1);
	}

	@Override
	public void exitRequiresModifier(RequiresModifierContext ctx) {
		values.put("RequiresModifier", values.get("RequiresModifier") + 1);
	}

	@Override
	public void exitRecordDeclaration(RecordDeclarationContext ctx) {
		values.put("RecordDeclaration", values.get("RecordDeclaration") + 1);
	}

	@Override
	public void exitRecordHeader(RecordHeaderContext ctx) {
		values.put("RecordHeader", values.get("RecordHeader") + 1);
	}

	@Override
	public void exitRecordComponentList(RecordComponentListContext ctx) {
		values.put("RecordComponentList", values.get("RecordComponentList") + 1);
	}

	@Override
	public void exitRecordComponent(RecordComponentContext ctx) {
		values.put("RecordComponent", values.get("RecordComponent") + 1);
	}

	@Override
	public void exitRecordBody(RecordBodyContext ctx) {
		values.put("RecordBody", values.get("RecordBody") + 1);
	}

	@Override
	public void exitBlock(BlockContext ctx) {
		values.put("Block", values.get("Block") + 1);
	}

	@Override
	public void exitBlockStatement(BlockStatementContext ctx) {
		values.put("BlockStatement", values.get("BlockStatement") + 1);
	}

	@Override
	public void exitLocalVariableDeclaration(LocalVariableDeclarationContext ctx) {
		values.put("LocalVariableDeclaration", values.get("LocalVariableDeclaration") + 1);
	}

	@Override
	public void exitIdentifier(IdentifierContext ctx) {
		values.put("Identifier", values.get("Identifier") + 1);
	}

	@Override
	public void exitTypeIdentifier(TypeIdentifierContext ctx) {
		values.put("TypeIdentifier", values.get("TypeIdentifier") + 1);
	}

	@Override
	public void exitLocalTypeDeclaration(LocalTypeDeclarationContext ctx) {
		values.put("LocalTypeDeclaration", values.get("LocalTypeDeclaration") + 1);
	}

	@Override
	public void exitStatement(StatementContext ctx) {
		values.put("Statement", values.get("Statement") + 1);
	}

	@Override
	public void exitCatchType(CatchTypeContext ctx) {
		values.put("CatchType", values.get("CatchType") + 1);
	}

	@Override
	public void exitFinallyBlock(FinallyBlockContext ctx) {
		values.put("FinallyBlock", values.get("FinallyBlock") + 1);
	}

	@Override
	public void exitResourceSpecification(ResourceSpecificationContext ctx) {
		values.put("ResourceSpecification", values.get("ResourceSpecification") + 1);
	}

	@Override
	public void exitResources(ResourcesContext ctx) {
		values.put("Resources", values.get("Resources") + 1);
	}

	@Override
	public void exitResource(ResourceContext ctx) {
		values.put("Resource", values.get("Resource") + 1);
	}

	@Override
	public void exitSwitchBlockStatementGroup(SwitchBlockStatementGroupContext ctx) {
		values.put("SwitchBlockStatementGroup", values.get("SwitchBlockStatementGroup") + 1);
	}

	@Override
	public void exitSwitchLabel(SwitchLabelContext ctx) {
		values.put("SwitchLabel", values.get("SwitchLabel") + 1);
	}

	@Override
	public void exitForInit(ForInitContext ctx) {
		values.put("ForInit", values.get("ForInit") + 1);
	}

	@Override
	public void exitParExpression(ParExpressionContext ctx) {
		values.put("ParExpression", values.get("ParExpression") + 1);
	}

	@Override
	public void exitExpressionList(ExpressionListContext ctx) {
		values.put("ExpressionList", values.get("ExpressionList") + 1);
	}

	@Override
	public void exitMethodCall(MethodCallContext ctx) {
		values.put("MethodCall", values.get("MethodCall") + 1);
	}

	@Override
	public void exitExpression(ExpressionContext ctx) {
		values.put("Expression", values.get("Expression") + 1);
	}

	@Override
	public void exitPattern(PatternContext ctx) {
		values.put("Pattern", values.get("Pattern") + 1);
	}

	@Override
	public void exitLambdaExpression(LambdaExpressionContext ctx) {
		values.put("LambdaExpression", values.get("LambdaExpression") + 1);
	}

	@Override
	public void exitLambdaParameters(LambdaParametersContext ctx) {
		values.put("LambdaParameters", values.get("LambdaParameters") + 1);
	}

	@Override
	public void exitLambdaBody(LambdaBodyContext ctx) {
		values.put("LambdaBody", values.get("LambdaBody") + 1);
	}

	@Override
	public void exitPrimary(PrimaryContext ctx) {
		values.put("Primary", values.get("Primary") + 1);
	}

	@Override
	public void exitSwitchExpression(SwitchExpressionContext ctx) {
		values.put("SwitchExpression", values.get("SwitchExpression") + 1);
	}

	@Override
	public void exitSwitchLabeledRule(SwitchLabeledRuleContext ctx) {
		values.put("SwitchLabeledRule", values.get("SwitchLabeledRule") + 1);
	}

	@Override
	public void exitGuardedPattern(GuardedPatternContext ctx) {
		values.put("GuardedPattern", values.get("GuardedPattern") + 1);
	}

	@Override
	public void exitSwitchRuleOutcome(SwitchRuleOutcomeContext ctx) {
		values.put("SwitchRuleOutcome", values.get("SwitchRuleOutcome") + 1);
	}

	@Override
	public void exitClassType(ClassTypeContext ctx) {
		values.put("ClassType", values.get("ClassType") + 1);
	}

	@Override
	public void exitCreator(CreatorContext ctx) {
		values.put("Creator", values.get("Creator") + 1);
	}

	@Override
	public void exitCreatedName(CreatedNameContext ctx) {
		values.put("CreatedName", values.get("CreatedName") + 1);
	}

	@Override
	public void exitInnerCreator(InnerCreatorContext ctx) {
		values.put("InnerCreator", values.get("InnerCreator") + 1);
	}

	@Override
	public void exitArrayCreatorRest(ArrayCreatorRestContext ctx) {
		values.put("ArrayCreatorRest", values.get("ArrayCreatorRest") + 1);
	}

	@Override
	public void exitClassCreatorRest(ClassCreatorRestContext ctx) {
		values.put("ClassCreatorRest", values.get("ClassCreatorRest") + 1);
	}

	@Override
	public void exitExplicitGenericInvocation(ExplicitGenericInvocationContext ctx) {
		values.put("ExplicitGenericInvocation", values.get("ExplicitGenericInvocation") + 1);
	}

	@Override
	public void exitTypeArgumentsOrDiamond(TypeArgumentsOrDiamondContext ctx) {
		values.put("TypeArgumentsOrDiamond", values.get("TypeArgumentsOrDiamond") + 1);
	}

	@Override
	public void exitNonWildcardTypeArgumentsOrDiamond(NonWildcardTypeArgumentsOrDiamondContext ctx) {
		values.put("NonWildcardTypeArgumentsOrDiamond", values.get("NonWildcardTypeArgumentsOrDiamond") + 1);
	}

	@Override
	public void exitNonWildcardTypeArguments(NonWildcardTypeArgumentsContext ctx) {
		values.put("NonWildcardTypeArguments", values.get("NonWildcardTypeArguments") + 1);
	}

	@Override
	public void exitTypeList(TypeListContext ctx) {
		values.put("TypeList", values.get("TypeList") + 1);
	}

	@Override
	public void exitTypeType(TypeTypeContext ctx) {
		values.put("TypeType", values.get("TypeType") + 1);
	}

	@Override
	public void exitPrimitiveType(PrimitiveTypeContext ctx) {
		values.put("PrimitiveType", values.get("PrimitiveType") + 1);
	}

	@Override
	public void exitTypeArguments(TypeArgumentsContext ctx) {
		values.put("TypeArguments", values.get("TypeArguments") + 1);
	}

	@Override
	public void exitSuperSuffix(SuperSuffixContext ctx) {
		values.put("SuperSuffix", values.get("SuperSuffix") + 1);
	}

	@Override
	public void exitExplicitGenericInvocationSuffix(ExplicitGenericInvocationSuffixContext ctx) {
		values.put("ExplicitGenericInvocationSuffix", values.get("ExplicitGenericInvocationSuffix") + 1);
	}

	@Override
	public void exitArguments(ArgumentsContext ctx) {
		values.put("Arguments", values.get("Arguments") + 1);
	}

	@Override
	public void exitEveryRule(ParserRuleContext ctx) {
		values.put("EveryRule", values.get("EveryRule") + 1);
	}

	@Override
	public void exitMethodDeclaration(JavaParser.MethodDeclarationContext ctx) {
		values.put("MethodDeclaration", values.get("MethodDeclaration") + 1);
	}

	@Override
	public void exitCatchClause(JavaParser.CatchClauseContext ctx) {
		values.put("CatchClause", values.get("CatchClause") + 1);
	}

	@Override
	public void exitForControl(JavaParser.ForControlContext ctx) {
		values.put("ForControl", values.get("ForControl") + 1);
	}

	@Override
	public void exitEnhancedForControl(JavaParser.EnhancedForControlContext ctx) {
		values.put("EnhancedForControl", values.get("EnhancedForControl") + 1);
	}

	public Map<String, Integer> getValues() {
		return values;
	}
}
