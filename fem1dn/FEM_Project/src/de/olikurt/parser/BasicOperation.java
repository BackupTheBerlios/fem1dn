/*
 * @(#)BasicOperation.java	1.00 12/31/2001
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
 * Diese Klasse deckt die Grundrechenarten und das Potenzieren ('einfachen' Mathematischen Operationen) ab.
 *
 * @author: Oliver Kurt
 * @date: 12/31/2001
 */
public class BasicOperation implements Operation {

  /**
   * Stelle an der der Operator steht. Somit die Stelle an der der String in zwei Teile zerlegt wird.
   */
  protected int pos;

  /**
   * Speichert den typ der Operation. z.b. + oder - oder sin...
   */
  protected int typ;

  protected BasicOperation(int pos, int typ) {
    this.typ = typ;
	this.pos = pos;
  }

  public Node[] devideAndCreate(String string) {
    Node[] nodes = new Node[2];
    nodes[0] = NodeFactory.newInstance(string.substring(0, pos));
    nodes[1] = NodeFactory.newInstance(string.substring(pos+1));
    return nodes;
  }


  public double link(Node[] nodes, Vector variables) {
    switch ( typ ) {
      case PLUS:
        return nodes[0].getValue(variables) + nodes[1].getValue(variables);
      case MINUS:
        return nodes[0].getValue(variables) - nodes[1].getValue(variables);
      case MULTIPLICATION:
        return nodes[0].getValue(variables) * nodes[1].getValue(variables);
      case DIVISION:
        return nodes[0].getValue(variables) / nodes[1].getValue(variables);
      case POWER:
        return Math.pow(nodes[0].getValue(variables), nodes[1].getValue(variables));
    }
    throw new RuntimeException("Unknown type: "+typ);
  }

}