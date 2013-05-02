package boa.compiler.ast.expressions;

import java.util.ArrayList;
import java.util.List;

import boa.compiler.ast.Node;
import boa.compiler.ast.Term;
import boa.compiler.visitors.AbstractVisitor;
import boa.compiler.visitors.AbstractVisitorNoArg;

/**
 * 
 * @author rdyer
 */
public class SimpleExpr extends Node {
	protected Term lhs;
	protected final List<String> ops = new ArrayList<String>();
	protected final List<Term> rhs = new ArrayList<Term>();

	public Term getLhs() {
		return lhs;
	}

	public List<String> getOps() {
		return ops;
	}

	public int getOpsSize() {
		return ops.size();
	}

	public String getOp(final int index) {
		return ops.get(index);
	}

	public void addOp(final String s) {
		ops.add(s);
	}

	public List<Term> getRhs() {
		return rhs;
	}

	public int getRhsSize() {
		return rhs.size();
	}

	public Term getRhs(final int index) {
		return rhs.get(index);
	}

	public void addRhs(final Term t) {
		t.setParent(this);
		rhs.add(t);
	}

	public SimpleExpr (final Term lhs) {
		lhs.setParent(this);
		this.lhs = lhs;
	}

	public <A> void accept(AbstractVisitor<A> v, A arg) {
		v.visit(this, arg);
	}

	public void accept(AbstractVisitorNoArg v) {
		v.visit(this);
	}
}
