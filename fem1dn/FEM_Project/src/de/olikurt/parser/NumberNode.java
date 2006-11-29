/*
 * @(#)NumberNode.java	1.00 12/31/2001
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
 * Dieser Knoten beinhaltet nur eine Dezimalzahl.
 * <p>
 * Da er keine Kindknoten mehr beinhalten kann, ist er ein Blatt des Baumes.
 *
 * @author: Oliver Kurt
 * @date: 12/31/2001
 */
public class NumberNode implements Node {

  /**
   * Wert des Knotens.
   */
  private double value;

  /**
   * Konstruktor.
   * <p>
   * Wird nur von Node aufgerufen.
   */
  protected NumberNode(double value) {
    this.value = value;
  }


  public double getValue(Vector variables) {
    return value;
  }
}