/*
 * @(#)Node.java	1.00 12/31/2001
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
 * Definiert einen Knoten.
 * <p>
 * Jeder Knoten muss seine Wert, in Abhängigkeit der Variablen, zurückgeben können.
 *
 * @author: Oliver Kurt
 * @date: 12/31/2001
 */
public interface Node {

  /**
   * Gibt den Wert in Abhängigkeit der Variablen variables zurück.
   *
   * @param variables veränderliche Variablen.
   * @return Wert den der Knoten nach Evaluierung der variables hat.
   */
  double getValue(Vector variables);

}