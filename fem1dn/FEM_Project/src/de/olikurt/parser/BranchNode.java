/*
 * @(#)BranchNode.java	1.00 12/31/2001
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
public class BranchNode implements Node {

  private Node[] nodes;

  private Operation operation;

  protected BranchNode(String creationString) {

    // nächste zu trennende stelle und deren typ suchen
    operation = OperationFactory.getNextOperation(creationString);

    // trennen und
    nodes = operation.devideAndCreate(creationString);

  }

  public double getValue(Vector variables) {
    return operation.link(nodes, variables);
  }

}