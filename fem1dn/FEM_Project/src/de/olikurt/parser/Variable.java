/*
 * @(#)Variable.java	1.00 12/31/2001
 *
 * Copyright 2001 by Oliver Kurt. All Rights Reserved.
 *
 * This software is the proprietary information of Oliver Kurt, Germany.
 * Use is subject to license terms.
 *
 */

package de.olikurt.parser;

/**
 *
 *
 * @author: Oliver Kurt
 * @date: 12/31/2001
 */
public class Variable {

  private char c;
  private double value;

  public Variable(char c) {
    this.c = c;
  }

  public void setValue(double value) {
    this.value = value;
  }

  public double getValue() {
    return value;
  }

  public char getName() {
    return c;
  }

}