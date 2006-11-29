/*
 * @(#)PostfixOperation.java	1.00 12/31/2001
 *
 * Copyright 2001 by Oliver Kurt. All Rights Reserved.
 *
 * This software is the proprietary information of Oliver Kurt, Germany.
 * Use is subject to license terms.
 *
 */

package de.olikurt.parser;

import java.util.Vector;

/**
 *
 *
 * @author: Oliver Kurt
 * @date: 12/31/2001
 */
public class PostfixOperation implements Operation {

  /**
   * Speichert den typ der Operation. z.b. + oder - oder sin...
   */
  protected int typ;

  public PostfixOperation(int typ) {
    this.typ = typ;
  }

  public Node[] devideAndCreate(String string) {
    Node[] nodes = new Node[1];
    switch ( typ ) {
      case LN:
        nodes[0] = NodeFactory.newInstance(string.substring(3, string.length()-1));
        break;
      case EXP:
      case SIN:
      case COS:
      case TAN:
        nodes[0] = NodeFactory.newInstance(string.substring(4, string.length()-1));
        break;
      case ASIN:
      case ACOS:
      case ATAN:
        nodes[0] = NodeFactory.newInstance(string.substring(5, string.length()-1));
        break;
    }
    return nodes;
  }


  public double link(Node[] nodes, Vector variables) {
    switch ( typ ) {
      case LN:
        return Math.log(nodes[0].getValue(variables));
      case EXP:
        return Math.exp(nodes[0].getValue(variables));
      case SIN:
        return Math.sin(nodes[0].getValue(variables));
      case COS:
        return Math.cos(nodes[0].getValue(variables));
      case TAN:
        return Math.tan(nodes[0].getValue(variables));
      case ASIN:
        return Math.asin(nodes[0].getValue(variables));
      case ACOS:
        return Math.acos(nodes[0].getValue(variables));
      case ATAN:
        return Math.atan(nodes[0].getValue(variables));
    }
    throw new RuntimeException("Unknown type: "+typ);
  }

}