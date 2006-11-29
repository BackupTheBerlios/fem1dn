/*
 * @(#)Operation.java	1.00 12/31/2001
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
 * Ist für eine(!) math. Operation zuständig.
 *
 * @author: Oliver Kurt
 * @date: 12/31/2001
 */
public interface Operation {

  /**
   * Konstanten Definitionen für die unterstützen Operatoren.
   */

  int PLUS = 1;
  int MINUS = 2;
  int MULTIPLICATION = 3;
  int DIVISION = 4;
  int POWER = 5;

  int LN = 6;
  int EXP = 7;
  int SIN = 8;
  int COS = 9;
  int TAN = 10;
  int ASIN = 11;
  int ACOS = 12;
  int ATAN = 13;

  int ROOT = 14;
  int LOG = 15;


  /**
   * Zerteilt den String string in weitere Nodes und gibt diese zurück.
   *
   * @param string zu parsender String.
   * @return Kindknoten der Operation. 1 oder 2.
   */
  Node[] devideAndCreate(String string);


  /**
   * Wertet die Knoten nodes anhand der akutellen Operation und den Werten in variables aus.
   *
   * @param nodes Kindknoten.
   * @param variables Wertezuweisungen für die Variablen.
   * @return Wert der Kindknoten.
   */
  double link(Node[] nodes, Vector variables);


}