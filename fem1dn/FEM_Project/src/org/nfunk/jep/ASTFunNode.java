/*****************************************************************************

@header@
@date@
@copyright@
@license@

*****************************************************************************/
/* Generated By:JJTree: Do not edit this line. ASTFunNode.java */
package org.nfunk.jep;

import org.nfunk.jep.function.*;
// rjm unneeded import
// import java.util.*;

/**
 * Function Node
 */
public class ASTFunNode extends SimpleNode {
	
	/** The function class used to evaluate the node */
	private PostfixMathCommandI pfmc;
	
	/** Name of the function */
	private String name;
	
	/** ID of the operator (if it is one) */
	private Operator opID=null;
	
	/**
	 * Creates a new ASTFunNode
	 */
	public ASTFunNode(int id) {
		super(id);
	}
	
	/**
	 * Creates a new ASTFunNode
	 */
	public ASTFunNode(Parser p, int id) {
		super(p, id);
	}
	
	/**
	 * Accept the visitor.
	 */
	public Object jjtAccept(ParserVisitor visitor, Object data) throws ParseException
	{
		return visitor.visit(this, data);
	}

	/**
	 * Sets the function for a node. A name and function class must
	 * be specified.
	 */	
	public void setFunction(String name_in, PostfixMathCommandI pfmc_in) {
		name = name_in;
		pfmc = pfmc_in;
	}
	
	/**
	 * Sets the opID, name and pfmc for this node by looking up the values
	 * in the Operators class
	 */
	public void setOperator(Operator op) {
			opID = op;
			pfmc = op.getPFMC();
			name = op.getName();
	}

	/**
	 * Returns a string containing the function name.
	 */
	public String toString() {
		return "Function \"" + name + "\"";
	}

	/**
	 * Returns the math command class associated with this node.
	 */
	public PostfixMathCommandI getPFMC() {
		return pfmc;
	}
	
	/**
	 * Returns the name of the node (operator symbol or function name).
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the id number of the operator if the node is an operator.
	 */
	public Operator getOperator() {
		return opID;
	}

	/**
	 * Returns true if node is an operator.
	 */
	public boolean isOperator() {
		return (opID != null);
	}
}
