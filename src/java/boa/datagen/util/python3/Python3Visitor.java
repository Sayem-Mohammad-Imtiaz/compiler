package boa.datagen.util.python3;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;

import com.hp.hpl.jena.graph.query.ExpressionSet;

import boa.datagen.util.python3.*;
import boa.datagen.util.python3.Python3Parser.And_exprContext;
import boa.datagen.util.python3.Python3Parser.And_testContext;
import boa.datagen.util.python3.Python3Parser.AnnassignContext;
import boa.datagen.util.python3.Python3Parser.ArglistContext;
import boa.datagen.util.python3.Python3Parser.ArgumentContext;
import boa.datagen.util.python3.Python3Parser.Arith_exprContext;
import boa.datagen.util.python3.Python3Parser.Assert_stmtContext;
import boa.datagen.util.python3.Python3Parser.AssignContext;
import boa.datagen.util.python3.Python3Parser.Async_funcdefContext;
import boa.datagen.util.python3.Python3Parser.Async_stmtContext;
import boa.datagen.util.python3.Python3Parser.AtomContext;
import boa.datagen.util.python3.Python3Parser.Atom_exprContext;
import boa.datagen.util.python3.Python3Parser.AugassignContext;
import boa.datagen.util.python3.Python3Parser.Break_stmtContext;
import boa.datagen.util.python3.Python3Parser.CalldefContext;
import boa.datagen.util.python3.Python3Parser.ClassdefContext;
import boa.datagen.util.python3.Python3Parser.Comp_forContext;
import boa.datagen.util.python3.Python3Parser.Comp_ifContext;
import boa.datagen.util.python3.Python3Parser.Comp_iterContext;
import boa.datagen.util.python3.Python3Parser.Comp_opContext;
import boa.datagen.util.python3.Python3Parser.ComparisonContext;
import boa.datagen.util.python3.Python3Parser.Compound_stmtContext;
import boa.datagen.util.python3.Python3Parser.Continue_stmtContext;
import boa.datagen.util.python3.Python3Parser.DecoratedContext;
import boa.datagen.util.python3.Python3Parser.DecoratorContext;
import boa.datagen.util.python3.Python3Parser.DecoratorsContext;
import boa.datagen.util.python3.Python3Parser.Del_stmtContext;
import boa.datagen.util.python3.Python3Parser.DictorsetmakerContext;
import boa.datagen.util.python3.Python3Parser.Dotted_as_nameContext;
import boa.datagen.util.python3.Python3Parser.Dotted_as_namesContext;
import boa.datagen.util.python3.Python3Parser.Dotted_nameContext;
import boa.datagen.util.python3.Python3Parser.Encoding_declContext;
import boa.datagen.util.python3.Python3Parser.Eval_inputContext;
import boa.datagen.util.python3.Python3Parser.Except_clauseContext;
import boa.datagen.util.python3.Python3Parser.ExprContext;
import boa.datagen.util.python3.Python3Parser.Expr_stmtContext;
import boa.datagen.util.python3.Python3Parser.ExprlistContext;
import boa.datagen.util.python3.Python3Parser.FactorContext;
import boa.datagen.util.python3.Python3Parser.File_inputContext;
import boa.datagen.util.python3.Python3Parser.Flow_stmtContext;
import boa.datagen.util.python3.Python3Parser.For_stmtContext;
import boa.datagen.util.python3.Python3Parser.FuncdefContext;
import boa.datagen.util.python3.Python3Parser.Global_stmtContext;
import boa.datagen.util.python3.Python3Parser.If_stmtContext;
import boa.datagen.util.python3.Python3Parser.Import_as_nameContext;
import boa.datagen.util.python3.Python3Parser.Import_as_namesContext;
import boa.datagen.util.python3.Python3Parser.Import_fromContext;
import boa.datagen.util.python3.Python3Parser.Import_nameContext;
import boa.datagen.util.python3.Python3Parser.Import_stmtContext;
import boa.datagen.util.python3.Python3Parser.LambdefContext;
import boa.datagen.util.python3.Python3Parser.Lambdef_nocondContext;
import boa.datagen.util.python3.Python3Parser.MinusContext;
import boa.datagen.util.python3.Python3Parser.Nonlocal_stmtContext;
import boa.datagen.util.python3.Python3Parser.Not_testContext;
import boa.datagen.util.python3.Python3Parser.Or_testContext;
import boa.datagen.util.python3.Python3Parser.ParametersContext;
import boa.datagen.util.python3.Python3Parser.Pass_stmtContext;
import boa.datagen.util.python3.Python3Parser.PlusContext;
import boa.datagen.util.python3.Python3Parser.PluseqContext;
import boa.datagen.util.python3.Python3Parser.PowerContext;
import boa.datagen.util.python3.Python3Parser.Raise_stmtContext;
import boa.datagen.util.python3.Python3Parser.Return_stmtContext;
import boa.datagen.util.python3.Python3Parser.Shift_exprContext;
import boa.datagen.util.python3.Python3Parser.Simple_stmtContext;
import boa.datagen.util.python3.Python3Parser.Single_inputContext;
import boa.datagen.util.python3.Python3Parser.SliceopContext;
import boa.datagen.util.python3.Python3Parser.Small_stmtContext;
import boa.datagen.util.python3.Python3Parser.Star_exprContext;
import boa.datagen.util.python3.Python3Parser.StmtContext;
import boa.datagen.util.python3.Python3Parser.SubscriptContext;
import boa.datagen.util.python3.Python3Parser.SubscriptlistContext;
import boa.datagen.util.python3.Python3Parser.SuiteContext;
import boa.datagen.util.python3.Python3Parser.TermContext;
import boa.datagen.util.python3.Python3Parser.TestContext;
import boa.datagen.util.python3.Python3Parser.Test_nocondContext;
import boa.datagen.util.python3.Python3Parser.TestlistContext;
import boa.datagen.util.python3.Python3Parser.Testlist_compContext;
import boa.datagen.util.python3.Python3Parser.Testlist_star_exprContext;
import boa.datagen.util.python3.Python3Parser.TfpdefContext;
import boa.datagen.util.python3.Python3Parser.TrailerContext;
import boa.datagen.util.python3.Python3Parser.Try_stmtContext;
import boa.datagen.util.python3.Python3Parser.TypedargslistContext;
import boa.datagen.util.python3.Python3Parser.VarargslistContext;
import boa.datagen.util.python3.Python3Parser.VfpdefContext;
import boa.datagen.util.python3.Python3Parser.While_stmtContext;
import boa.datagen.util.python3.Python3Parser.With_itemContext;
import boa.datagen.util.python3.Python3Parser.With_stmtContext;
import boa.datagen.util.python3.Python3Parser.Xor_exprContext;
import boa.datagen.util.python3.Python3Parser.Yield_argContext;
import boa.datagen.util.python3.Python3Parser.Yield_exprContext;
import boa.datagen.util.python3.Python3Parser.Yield_stmtContext;
import boa.types.Ast.Declaration;
import boa.types.Ast.Expression;
import boa.types.Ast.Expression.ExpressionKind;
import boa.types.Ast.Method;
import boa.types.Ast.Namespace;
import boa.types.Ast.PositionInfo;
import boa.types.Ast.Statement;
import boa.types.Ast.Statement.StatementKind;
import boa.types.Ast.TypeKind;
import boa.types.Ast.Variable;
public class Python3Visitor implements Python3Listener{
	Python3Parser parser;
	Python3Lexer lexer;
	
	private String src = null;
	private String pkg = "";
	public static final int PY2 = 1, PY3 = 2;
	
	private PositionInfo.Builder pos = null;
	private Namespace.Builder b = Namespace.newBuilder();
	private List<boa.types.Ast.Comment> comments = new ArrayList<boa.types.Ast.Comment>();
	//private List<String> imports = new ArrayList<String>();
	//private Stack<boa.types.Ast.Expression> expressions = new Stack<boa.types.Ast.Expression>();
	protected Stack<List<boa.types.Ast.Variable>> fields = new Stack<List<boa.types.Ast.Variable>>();
	//private Stack<List<boa.types.Ast.Method>> methods = new Stack<List<boa.types.Ast.Method>>();
	//private Stack<List<boa.types.Ast.Statement>> statements = new Stack<List<boa.types.Ast.Statement>>();
	private Stack<Method.Builder> methods = new Stack<Method.Builder>();
	private Stack<Statement.Builder> statements = new Stack<Statement.Builder>();
	private Stack<Expression.Builder> expressions = new Stack<Expression.Builder>();
	private Stack<String> atoms = new Stack<String>();
	private Stack<String> atomEx = new Stack<String>();
	private Stack<String> imports = new Stack<String>();
	protected int astLevel = PY3;
	
	public boolean isPython3 = true;
	
	public int getAstLevel() {
		return astLevel;
	}

	public void setAstLevel(int astLevel) {
		this.astLevel = astLevel;
	}

	public Namespace getNamespaces() {
		return b.build();
	}
	
	public List<boa.types.Ast.Comment> getComments() {
		return comments;
	}

	public List<String> getImports() {
		return imports;
	}
		
	public Python3Visitor(String src) {
		this.src = src;
	}
	
	public Python3Visitor() {
	}
	
	private static String readFile(File file, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(file.toPath());
        return new String(encoded, encoding);
    }

    public Python3Parser parsefile(File file) throws IOException {
        String code = readFile(file, Charset.forName("UTF-8"));
        return parse(code);
    }
    
    public Python3Parser parse(String code) {
    	lexer = new Python3Lexer(new ANTLRInputStream(code));

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        parser = new Python3Parser(tokens);

        return parser;
    }
    
	public void visit(String source) {
		//System.out.println("-------\n" +source + "\n-------");
        parser = parse(source);
		try {
			ParseTreeWalker.DEFAULT.walk(this, parser.file_input());
		}
		catch(Exception e) {
			System.out.println("Error");
			e.printStackTrace();
		}
	}
	
	public void visit(String path, String source) {
		pkg = path;
        parser = parse(source);
		try {
			ParseTreeWalker.DEFAULT.walk(this, parser.file_input());
		}
		catch(Exception e) {
			System.out.println("Error");
			e.printStackTrace();
		}
	}
	
	public void visit(File file) {
		try {
			parser = parsefile(file);
			ParseTreeWalker.DEFAULT.walk(this, parser.file_input());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void enterFile_input(File_inputContext ctx) {
		b.setName(pkg);
	}

	@Override
	public void exitFile_input(File_inputContext ctx) {
		
	}
	
	Declaration.Builder db;
	@Override
	public void enterClassdef(ClassdefContext ctx) {
		if(ctx.NAME() == null) 
			return;
		db = Declaration.newBuilder();
		db.setName(ctx.NAME().getText());
		db.setKind(TypeKind.CLASS);
	}

	@Override
	public void exitClassdef(ClassdefContext ctx) {
		if(db != null)
			b.addDeclarations(db.build());
		db = null;
	}
	
	@Override
	public void enterFuncdef(FuncdefContext ctx) {	
		if(ctx.NAME() == null) 
			return;
		Method.Builder mb = Method.newBuilder();
		mb.setName(ctx.NAME().getText());
		methods.push(mb);
	}  
	
	@Override
	public void exitFuncdef(FuncdefContext ctx) {
		if(methods.isEmpty())
			return;
		
		Method.Builder mbi = methods.pop();
		if(!statements.isEmpty()) {
			statements.peek().addMethods(mbi.build());
		}
		else {
			if(db != null)
				db.addMethods(mbi.build());
			else
				b.addMethods(mbi.build());
		}
		
	}	

	Variable.Builder vb;
	@Override
	public void enterParameters(ParametersContext ctx) {
		vb = Variable.newBuilder();	
	}

	@Override
	public void exitParameters(ParametersContext ctx) {
		vb = null;
	}

	@Override
	public void enterTypedargslist(TypedargslistContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitTypedargslist(TypedargslistContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterTfpdef(TfpdefContext ctx) {
		if(vb != null) {
			vb = Variable.newBuilder();
			vb.setName(ctx.NAME().getText());
			if(!methods.isEmpty())
				methods.peek().addArguments(vb.build());
		}
	}

	@Override
	public void exitTfpdef(TfpdefContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterVarargslist(VarargslistContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitVarargslist(VarargslistContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterVfpdef(VfpdefContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitVfpdef(VfpdefContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterStmt(StmtContext ctx) {
		// TODO Auto-generated method stub	
		
	}

	@Override
	public void exitStmt(StmtContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterSimple_stmt(Simple_stmtContext ctx) {
		
		
	}

	@Override
	public void exitSimple_stmt(Simple_stmtContext ctx) {
		
		
	}

	@Override
	public void enterSmall_stmt(Small_stmtContext ctx) {

		
	}

	@Override
	public void exitSmall_stmt(Small_stmtContext ctx) {
		
	}

	@Override
	public void enterExpr_stmt(Expr_stmtContext ctx) {
		Statement.Builder sb = Statement.newBuilder();
		sb.setKind(Statement.StatementKind.EXPRESSION);
		//sb.addNames(ctx.getText()); // For testing purpose
		System.out.println("@@  " + ctx.getText());
		statements.push(sb);
	}

	@Override
	public void exitExpr_stmt(Expr_stmtContext ctx) {
		if(isAssign) {
			exitExpression();
			isAssign = false;
		}
		exitStatement();
		
	}
	
	boolean isAssign = false;
	@Override
	public void enterAssign(AssignContext ctx) {	
		isAssign = true;
		
		Expression.Builder eb = Expression.newBuilder();
		
		eb.setKind(ExpressionKind.ASSIGN);
		Expression.Builder ebl = Expression.newBuilder();
		ebl.setKind(ExpressionKind.VARACCESS);
		//System.out.println(atomEx.peek());
		ebl.setVariable(atomEx.pop());
		
		eb.addExpressions(ebl.build());
		expressions.push(eb);
		
		
//		System.out.println("## " + ctx.getParent().getText());
//		System.out.println("$$ " + atoms.pop());
		
	}

	@Override
	public void exitAssign(AssignContext ctx) {
//		exitExpression();
//		isAssign = false;
	}

	@Override
	public void enterAnnassign(AnnassignContext ctx) {

	}
	
	@Override
	public void exitAnnassign(AnnassignContext ctx) {
		
	}
	
	@Override
	public void enterAugassign(AugassignContext ctx) {
		//		Expression.Builder eb = Expression.newBuilder();
		//		eb.setKind(ExpressionKind.ASSIGN);
		//		eb.setVariable(ctx.getText());
		//		expressions.push(eb);
	}

	@Override
	public void exitAugassign(AugassignContext ctx) {
		//exitExpression();
	}

	private void exitExpression() {
		if(expressions.isEmpty())
			return;
		Expression.Builder current = expressions.pop();
		if(!expressions.isEmpty()) {
			 expressions.peek().addExpressions(current.build());
		}
		else {
			if(!statements.isEmpty()) {
				Statement.Builder sb = statements.peek();
				sb.addExpressions(current.build());
			}
			else
				b.addExpressions(current.build());
		}
	}

	@Override
	public void enterDel_stmt(Del_stmtContext ctx) {
		Expression.Builder eb = Expression.newBuilder();
		eb.setKind(ExpressionKind.DELETE);
		eb.setVariable(ctx.getText());
		expressions.push(eb);
	}

	@Override
	public void exitDel_stmt(Del_stmtContext ctx) {
		exitExpression();
	}

	@Override
	public void enterPass_stmt(Pass_stmtContext ctx) {
		Statement.Builder sb = Statement.newBuilder();
		sb.setKind(Statement.StatementKind.PASS);
		statements.push(sb);
		
	}

	@Override
	public void exitPass_stmt(Pass_stmtContext ctx) {
		exitStatement();
	}

	@Override
	public void enterFlow_stmt(Flow_stmtContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitFlow_stmt(Flow_stmtContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterBreak_stmt(Break_stmtContext ctx) {
		Statement.Builder sb = Statement.newBuilder();
		sb.setKind(Statement.StatementKind.BREAK);
		statements.push(sb);
	}

	@Override
	public void exitBreak_stmt(Break_stmtContext ctx) {
		exitStatement();
	}

	private void exitStatement() {
		if(statements.empty()) {
			return;
		}
		Statement.Builder current = statements.pop();
		if(!statements.isEmpty()) {
			statements.peek().addStatements(current.build());
		}
		else {
			if (!methods.isEmpty())
				methods.peek().addStatements(current.build());
			else if (db != null) 
				db.addStatements(current.build());
			else 
				b.addStatements(current.build());
		}
	}

	@Override
	public void enterContinue_stmt(Continue_stmtContext ctx) {
		Statement.Builder sb = Statement.newBuilder();
		sb.setKind(Statement.StatementKind.CONTINUE);
		statements.push(sb);
	}

	@Override
	public void exitContinue_stmt(Continue_stmtContext ctx) {
		exitStatement();
	}

	@Override
	public void enterReturn_stmt(Return_stmtContext ctx) {
		Statement.Builder sb = Statement.newBuilder();
		sb.setKind(Statement.StatementKind.RETURN);
		statements.push(sb);
	}

	@Override
	public void exitReturn_stmt(Return_stmtContext ctx) {
		exitStatement();
	}

	@Override
	public void enterYield_stmt(Yield_stmtContext ctx) {
		
	}

	@Override
	public void exitYield_stmt(Yield_stmtContext ctx) {
		
	}

	@Override
	public void enterRaise_stmt(Raise_stmtContext ctx) {
		Statement.Builder sb = Statement.newBuilder();
		sb.setKind(Statement.StatementKind.BREAK);
		statements.push(sb);
	}

	@Override
	public void exitRaise_stmt(Raise_stmtContext ctx) {
		exitStatement();
	}

	@Override
	public void enterImport_stmt(Import_stmtContext ctx) {
		
	}

	@Override
	public void exitImport_stmt(Import_stmtContext ctx) {
		
	}

	@Override
	public void enterImport_name(Import_nameContext ctx) {
		//imports.push(ctx.stop.getText());
	}

	@Override
	public void exitImport_name(Import_nameContext ctx) {
		if(!imports.isEmpty()) {
			String i = imports.pop();
			b.addImports(i);
		}
	}

	@Override
	public void enterImport_from(Import_fromContext ctx) {
		String[] parts = new String[2];
		try {
			parts = ctx.getText().substring(4).split("import", 2);
		} catch (Exception e) {
			System.out.println("Problem Parsing Import-From Statment");
			return;
		}
		
		if(!(parts.length > 1)) 
			return;
		if(parts[1].endsWith("as" + ctx.getStop().getText())) {
			String i = null;
			try {
				i = parts[1].split("as" + ctx.getStop().getText())[0] + " AS " + ctx.getStop().getText() + " FROM " + parts[0];
			} catch (Exception e) {
				System.out.println("Continuing Import-From Statment with AS.");
			}
			if(i != null)
				imports.push(i);
			else
				imports.push(parts[1] + " FROM " + parts[0]);
		}
		else
			imports.push(parts[1] + " FROM " + parts[0]);
		
	}

	@Override
	public void exitImport_from(Import_fromContext ctx) {
		if(!imports.isEmpty()) {
			String i = imports.pop();
			b.addImports(i);
		}
	}

	@Override
	public void enterImport_as_name(Import_as_nameContext ctx) {
		if(ctx.getText().equals(ctx.getStart().getText() + "as" + ctx.getStop().getText())) {
			if(!imports.isEmpty() && imports.peek().contains(ctx.getText()))
				imports.push(imports.pop().replace(ctx.getText(), ctx.getStart().getText() + " AS " + ctx.getStop().getText()));
		}	
	}

	@Override
	public void exitImport_as_name(Import_as_nameContext ctx) {
		
	}

	@Override
	public void enterDotted_as_name(Dotted_as_nameContext ctx) {
		if(ctx.getText().endsWith("as" + ctx.getStop().getText())) { // equals(ctx.getStart().getText() + "as" + ctx.getStop().getText())) {
			if(!imports.isEmpty() && imports.peek().equals(ctx.getStop().getText()))
				imports.pop();
			String i = null;
			try {
				i = ctx.getText().split("as" + ctx.getStop().getText())[0] + " AS " + ctx.getStop().getText();//ctx.getStart().getText() + " AS " + ctx.getStop().getText();
			} catch (Exception e) {
				System.out.println("Continuing Import-AS");
			}
			if(i != null)
				imports.push(i);
			else
				imports.push(ctx.getText());
		}
		else {
			imports.push(ctx.getText());
		}
	}

	@Override
	public void exitDotted_as_name(Dotted_as_nameContext ctx) {
		if(!imports.isEmpty()) {
			String i = imports.pop();
			b.addImports(i);
		}
	}

	@Override
	public void enterImport_as_names(Import_as_namesContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitImport_as_names(Import_as_namesContext ctx) {
		// TODO Auto-generated method stub
	}

	@Override
	public void enterDotted_as_names(Dotted_as_namesContext ctx) {
		// TODO Auto-generated method stub
	}

	@Override
	public void exitDotted_as_names(Dotted_as_namesContext ctx) {
		// TODO Auto-generated method stub
	}

	@Override
	public void enterDotted_name(Dotted_nameContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitDotted_name(Dotted_nameContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterGlobal_stmt(Global_stmtContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitGlobal_stmt(Global_stmtContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterNonlocal_stmt(Nonlocal_stmtContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitNonlocal_stmt(Nonlocal_stmtContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterAssert_stmt(Assert_stmtContext ctx) {
		Statement.Builder sb = Statement.newBuilder();
		sb.setKind(Statement.StatementKind.ASSERT);
		statements.push(sb);
	}

	@Override
	public void exitAssert_stmt(Assert_stmtContext ctx) {
		exitStatement();
	}

	@Override
	public void enterCompound_stmt(Compound_stmtContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitCompound_stmt(Compound_stmtContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterAsync_stmt(Async_stmtContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitAsync_stmt(Async_stmtContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterIf_stmt(If_stmtContext ctx) {
		Statement.Builder sb = Statement.newBuilder();
		sb.setKind(Statement.StatementKind.IF);
		statements.push(sb);
	}

	@Override
	public void exitIf_stmt(If_stmtContext ctx) {
		exitStatement();
	}

	@Override
	public void enterWhile_stmt(While_stmtContext ctx) {
		Statement.Builder sb = Statement.newBuilder();
		sb.setKind(Statement.StatementKind.WHILE);
		statements.push(sb);
	}

	@Override
	public void exitWhile_stmt(While_stmtContext ctx) {
		exitStatement();
	}

	@Override
	public void enterFor_stmt(For_stmtContext ctx) {
		Statement.Builder sb = Statement.newBuilder();
		sb.setKind(Statement.StatementKind.FOR);
		statements.push(sb);
	}

	@Override
	public void exitFor_stmt(For_stmtContext ctx) {
		exitStatement();		
	}

	@Override
	public void enterTry_stmt(Try_stmtContext ctx) {
		Statement.Builder sb = Statement.newBuilder();
		sb.setKind(Statement.StatementKind.TRY);
		statements.push(sb);
	}

	@Override
	public void exitTry_stmt(Try_stmtContext ctx) {
		exitStatement();
	}

	@Override
	public void enterWith_stmt(With_stmtContext ctx) {
		Statement.Builder sb = Statement.newBuilder();
		sb.setKind(Statement.StatementKind.WITH);
		statements.push(sb);
	}

	@Override
	public void exitWith_stmt(With_stmtContext ctx) {
		exitStatement();
	}

	@Override
	public void enterWith_item(With_itemContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitWith_item(With_itemContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterExcept_clause(Except_clauseContext ctx) {
		Statement.Builder sb = Statement.newBuilder();
		sb.setKind(Statement.StatementKind.CATCH);
		statements.push(sb);
		
	}

	@Override
	public void exitExcept_clause(Except_clauseContext ctx) {
		exitStatement();
	}

	@Override
	public void enterSuite(SuiteContext ctx) {
		Statement.Builder sb = Statement.newBuilder();
		sb.setKind(Statement.StatementKind.BLOCK);
		statements.push(sb);
	}

	@Override
	public void exitSuite(SuiteContext ctx) {
		if(statements.empty()) {
			return;
		}
		Statement.Builder current = statements.pop();
		
		if(ctx.getParent().start.getText().equals("def") && !methods.isEmpty()) {
			methods.peek().addStatements(current.build());
		}
		else if (!statements.isEmpty()) {
			statements.peek().addStatements(current.build());
		}
		else {
			if (db != null) 
				db.addStatements(current.build());
			else 
				b.addStatements(current.build());
		}
	}

	@Override
	public void enterTest(TestContext ctx) {
//		if(!statements.isEmpty()) {
//			if(statements.peek().getKind() == StatementKind.IF) {
//				System.out.println(ctx.getText() + " conditions" + ctx.start.getText() +" "+ ctx.stop.getText());
//			}
//		}
		
		
//		if(statements.peek().getKind() == StatementKind.IF) {
//			Expression.Builder eb = Expression.newBuilder();
//			eb.setKind(ExpressionKind.LOGICAL_AND);
//			eb.setVariable(ctx.getText());
//			eb.setLiteral(ctx.start.getText());
//			eb.setMethod(ctx.getTokens(ctx.).toString());
//			expressions.push(eb);
//		}
	}
	
	@Override
	public void exitTest(TestContext ctx) {
//		if(!expressions.isEmpty()) {
//			Expression.Builder current = expressions.pop();
//			if(!expressions.isEmpty()) {
//				expressions.peek().addExpressions(current.build());
//			}
//			else {
//				Statement.Builder sb = statements.peek();
//				sb.addConditions(current.build());
//			}
//		}
	}

	@Override
	public void enterTest_nocond(Test_nocondContext ctx) {
		
	}

	@Override
	public void exitTest_nocond(Test_nocondContext ctx) {
				
	}

	@Override
	public void enterLambdef(LambdefContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitLambdef(LambdefContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterLambdef_nocond(Lambdef_nocondContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitLambdef_nocond(Lambdef_nocondContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterOr_test(Or_testContext ctx) {
	}

	@Override
	public void exitOr_test(Or_testContext ctx) {

	}

	@Override
	public void enterAnd_test(And_testContext ctx) {
		//System.out.println("AND TEST "+ ctx.getText());
	}

	@Override
	public void exitAnd_test(And_testContext ctx) {
		
	}

	@Override
	public void enterNot_test(Not_testContext ctx) {
		
	}

	@Override
	public void exitNot_test(Not_testContext ctx) {
		

	}

	@Override
	public void enterComparison(ComparisonContext ctx) {
		//System.out.println("Comparison " + ctx.getText());
//		if(statements.peek().getKind() == StatementKind.IF) {
//			Expression.Builder eb = Expression.newBuilder();
//			eb.setKind(ExpressionKind.OTHER);
//			eb.setVariable(ctx.getText());
//			expressions.push(eb);
//		}
		
		
	}
	
	@Override
	public void exitComparison(ComparisonContext ctx) {
//		if(!expressions.isEmpty()) {
//			Expression.Builder current = expressions.pop();
//			if(!expressions.isEmpty()) {
//				expressions.peek().addExpressions(current.build());
//			}
//			else {
//				Statement.Builder sb = statements.peek();
//				sb.addConditions(current.build());
//			}
//		}
		
	}

	@Override
	public void enterComp_op(Comp_opContext ctx) {
		/*
		String op = ctx.getText();
		if(statements.peek().getKind() == StatementKind.IF) {
			Expression.Builder eb = Expression.newBuilder();
			eb.setKind(ExpressionKind.OP_ADD);
			if(op.equals("+"))
			eb.setVariable(ctx.getText());
			expressions.push(eb);
		}
		*/
	}

	@Override
	public void exitComp_op(Comp_opContext ctx) {
		/*
		if(!expressions.isEmpty()) {
			Expression.Builder current = expressions.pop();
			if(!expressions.isEmpty()) {
				expressions.peek().addExpressions(current.build());
			}
		}
		*/
	}

	@Override
	public void enterStar_expr(Star_exprContext ctx) {
		// TODO Auto-generated method stub
		System.out.println(ctx.getText());
	}

	@Override
	public void exitStar_expr(Star_exprContext ctx) {
		// TODO Auto-generated method stub
		
	}	

	@Override
	public void enterExpr(ExprContext ctx) {
		//System.out.println("## " + ctx.getText());
		
//		Expression.Builder eb = Expression.newBuilder();
//		eb.setKind(ExpressionKind.OTHER);
//		eb.setVariable(ctx.getText());
//		expressions.push(eb);		
	}

	@Override
	public void exitExpr(ExprContext ctx) {
//		if(!expressions.isEmpty()) {
//			Expression.Builder current = expressions.pop();
//			Expression.Builder parent;
//			
//			if(!expressions.isEmpty()) {
//				parent = expressions.peek();
//				parent.addExpressions(current.build());
//			}
//			else {
//				if(!statements.isEmpty()) {
//					Statement.Builder sb = statements.peek();
//					sb.addExpressions(current.build());
//				}
//			}
//		}
		
	}

	@Override
	public void enterXor_expr(Xor_exprContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitXor_expr(Xor_exprContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterAnd_expr(And_exprContext ctx) {
		//System.out.println("AND EXPR " + ctx.getText());
//		if(statements.peek().getKind() == StatementKind.IF) {
//			Expression.Builder eb = Expression.newBuilder();		
//			
//			eb.setKind(ExpressionKind.LOGICAL_AND);
//			eb.setLiteral(ctx.getText());
//			expressions.push(eb);
//		}	
		
	}

	@Override
	public void exitAnd_expr(And_exprContext ctx) {
//		if(statements.peek().getKind() == StatementKind.IF) {
//			Expression.Builder current = expressions.pop();
//			if(!expressions.isEmpty()) {
//				expressions.peek().addExpressions(current.build());
//			}
//			else {
//				Statement.Builder sb = statements.peek();
//				sb.addConditions(current.build());
//			}
//		}
		
	}

	@Override
	public void enterShift_expr(Shift_exprContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitShift_expr(Shift_exprContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterArith_expr(Arith_exprContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitArith_expr(Arith_exprContext ctx) {
//		if(needToExitEx)
//			exitExpression();
		for(int i = 0; i < exitEx; i++) 
			exitExpression();
		isArith = false;
		
	}

	@Override
	public void enterTerm(TermContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitTerm(TermContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterFactor(FactorContext ctx) {
		// TODO Auto-generated method stub
		//System.out.println("factorstart");
	}

	@Override
	public void exitFactor(FactorContext ctx) {
		// TODO Auto-generated method stub
		//System.out.println("factorend");
		
	}  
	
	boolean isArith = false; 
	@Override
	public void enterPlus(PlusContext ctx) {
		
		if (expressions.isEmpty()) 
			return;
		
		
		if(!isArith) {
			Expression.Builder parentex = expressions.pop();
			Expression.Builder eb = Expression.newBuilder();
			eb.setKind(ExpressionKind.OP_ADD);
			eb.addExpressions(parentex.build());
			expressions.push(eb);
			
			isArith = true;
		}
		
		else {
			Expression.Builder parentex = expressions.pop();
			if(expressions.peek().getKind() == ExpressionKind.OP_ADD) {
				expressions.peek().addExpressions(parentex.build());
				exitEx--;
			}
			else {
				Expression.Builder eb = Expression.newBuilder();
				eb.setKind(ExpressionKind.OP_ADD);
				eb.addExpressions(parentex.build());
				expressions.push(eb);
			}
		}
			
		
			
				
		
	}

	@Override
	public void exitPlus(PlusContext ctx) {
		// TODO Auto-generated method stub
	}

	@Override
	public void enterMinus(MinusContext ctx) {
		if (expressions.isEmpty()) 
			return;
		
		
		if(!isArith) {
			Expression.Builder parentex = expressions.pop();
			Expression.Builder eb = Expression.newBuilder();
			eb.setKind(ExpressionKind.OP_SUB);
			eb.addExpressions(parentex.build());
			expressions.push(eb);
			
			isArith = true;
		}
		
		else {
			Expression.Builder parentex = expressions.pop();
			if(expressions.peek().getKind() == ExpressionKind.OP_SUB) {
				expressions.peek().addExpressions(parentex.build());
				exitEx--;
			}
			else {
				Expression.Builder eb = Expression.newBuilder();
				eb.setKind(ExpressionKind.OP_SUB);
				eb.addExpressions(parentex.build());
				expressions.push(eb);
			}
		}
		
	}

	@Override
	public void exitMinus(MinusContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterPluseq(PluseqContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitPluseq(PluseqContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterPower(PowerContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitPower(PowerContext ctx) {
		// TODO Auto-generated method stub
		
	}
	
	int atom_ex = 0;

	int exitEx = 0;
	@Override
	public void enterAtom_expr(Atom_exprContext ctx) {
		//System.out.println("## " + ctx.getText());
		atomEx.push(ctx.getText());
		
		
		
		if(isAssign && !isMethodArg) {
			
			if(!atomEx.isEmpty() && isLiteral(atomEx.peek())) {
				Expression.Builder ebr = Expression.newBuilder();
				String rex = atomEx.pop();
				ebr.setKind(ExpressionKind.LITERAL);
				ebr.setLiteral(rex);
				expressions.push(ebr);
				exitEx ++;

				
			}
			
			else {
				if(!atomEx.isEmpty() && isVar(atomEx.peek())) {	
					Expression.Builder ebr = Expression.newBuilder();
					String rex = atomEx.pop();
					ebr.setKind(ExpressionKind.VARACCESS);
					ebr.setVariable(rex);
					expressions.push(ebr);
					exitEx ++;

					
				}
			}

	
			
		}
	
	}
	
	

	
	
	@Override
	public void exitAtom_expr(Atom_exprContext ctx) {		

	}
	 
	@Override
	public void enterAtom(AtomContext ctx) {
		//System.out.println("%% " + ctx.getText());
		
		atoms.push(ctx.getText());

		
	}

	@Override
	public void exitAtom(AtomContext ctx) {
		
	}

	@Override
	public void enterTestlist_comp(Testlist_compContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitTestlist_comp(Testlist_compContext ctx) {
		// TODO Auto-generated method stub
		
	}

	boolean trailerMethodCall = false;
	boolean isMethodCall = false;
	@Override
	public void enterTrailer(TrailerContext ctx) {
		
		if(ctx.getText().startsWith(".")) {
			trailerMethodCall = true;
			atoms.push(ctx.getText().substring(1));
		}
		else if(ctx.getText().equals("()")) {
			trailerMethodCall = false;
			Expression.Builder eb = Expression.newBuilder();
			eb.setKind(ExpressionKind.METHODCALL);
			
			
			if(!atoms.isEmpty())
				eb.setMethod(atoms.pop());
			else
				eb.setVariable("Method name missing!");
			//System.out.println("M1 " + eb.getMethod());
			expressions.push(eb);
			exitExpression();
		}
	}
	

	@Override
	public void exitTrailer(TrailerContext ctx) {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public void enterSubscriptlist(SubscriptlistContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitSubscriptlist(SubscriptlistContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterSubscript(SubscriptContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitSubscript(SubscriptContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterSliceop(SliceopContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitSliceop(SliceopContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterExprlist(ExprlistContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitExprlist(ExprlistContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterTestlist(TestlistContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitTestlist(TestlistContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterDictorsetmaker(DictorsetmakerContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitDictorsetmaker(DictorsetmakerContext ctx) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void enterArglist(ArglistContext ctx) {		
		Expression.Builder eb = Expression.newBuilder();
		eb.setKind(ExpressionKind.METHODCALL);
		if(!atoms.isEmpty()) {
			eb.setMethod(atoms.pop());
		}
		else {
			eb.setVariable("Method name missing!");
		}
		//System.out.println("M2 " + eb.getMethod());
		
		if(trailerMethodCall) {
			String fullEx = atomEx.pop();
			String trailer = fullEx.substring(0, fullEx.indexOf(eb.getMethod()));
			trailer = trailer.substring(0, trailer.length() - 1);
						
			Expression.Builder eb2 = Expression.newBuilder();
			eb2.setKind(ExpressionKind.VARACCESS);
			eb2.setVariable(trailer);
			eb.addExpressions(eb2);
		}		
		
		expressions.push(eb);
		
	}

	@Override
	public void exitArglist(ArglistContext ctx) {
		exitExpression();
		trailerMethodCall = false;
	}
	
	boolean isMethodArg = false;
	@Override
	public void enterArgument(ArgumentContext ctx) {
		isMethodArg = true;
		Expression.Builder eb = Expression.newBuilder();
		if(isLiteral(ctx.getText())) {
			eb.setKind(ExpressionKind.LITERAL);
			eb.setLiteral(ctx.getText());
		}
		else { // need some work to identify the assignment expression like 'x = 5' and another method call
			eb.setKind(ExpressionKind.VARACCESS);
			eb.setVariable(ctx.getText());
		}
		
		if(!expressions.isEmpty()) {
			expressions.peek().addMethodArgs(eb.build());
		}
	}

	@Override
	public void exitArgument(ArgumentContext ctx) {
		isMethodArg = false;
	}
	
	public boolean isLiteral(String text) {
		boolean isLiteral = text.startsWith("\"");
		if(!isLiteral) {
			try {
				Double.parseDouble(text);
				isLiteral =  true;
			}
			catch (Exception e) {
				isLiteral = false;
			}
		}
		return isLiteral;
	}
	
	public boolean isVar(String s) { 
		if (s == null || s.length() == 0)
		    return false;
		
		if(isLiteral(s) || !s.matches("^[^\\d\\W]\\w*\\Z"))
			return false;
		
		return true;		
	}

	@Override
	public void enterComp_iter(Comp_iterContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitComp_iter(Comp_iterContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterComp_for(Comp_forContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitComp_for(Comp_forContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterComp_if(Comp_ifContext ctx) {
		
	}

	@Override
	public void exitComp_if(Comp_ifContext ctx) {
		
	}

	@Override
	public void enterEncoding_decl(Encoding_declContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitEncoding_decl(Encoding_declContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterYield_expr(Yield_exprContext ctx) {
		Expression.Builder eb = Expression.newBuilder();
		eb.setKind(ExpressionKind.YIELD);
		eb.setVariable(ctx.getText());
		expressions.push(eb);
		
	}

	@Override
	public void exitYield_expr(Yield_exprContext ctx) {
		exitExpression();
	}

	@Override
	public void enterYield_arg(Yield_argContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitYield_arg(Yield_argContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterCalldef(CalldefContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitCalldef(CalldefContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterEveryRule(ParserRuleContext arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitEveryRule(ParserRuleContext arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitErrorNode(ErrorNode arg0) {
		isPython3 = false;
		return;
	}

	@Override
	public void visitTerminal(TerminalNode arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void enterSingle_input(Single_inputContext ctx) {
		// TODO Auto-generated method stub
	}

	@Override
	public void exitSingle_input(Single_inputContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterEval_input(Eval_inputContext ctx) {
		// TODO Auto-generated method stub
	}

	@Override
	public void exitEval_input(Eval_inputContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterDecorator(DecoratorContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitDecorator(DecoratorContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterDecorators(DecoratorsContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitDecorators(DecoratorsContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterDecorated(DecoratedContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitDecorated(DecoratedContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterAsync_funcdef(Async_funcdefContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitAsync_funcdef(Async_funcdefContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterTestlist_star_expr(Testlist_star_exprContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitTestlist_star_expr(Testlist_star_exprContext ctx) {
		// TODO Auto-generated method stub
		
	}


}
