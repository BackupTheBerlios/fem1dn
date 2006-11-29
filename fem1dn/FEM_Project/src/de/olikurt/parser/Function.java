/*
 * @(#)Function.java	1.00 12/31/2001
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
 * Hauptklasse des Parser-Packages.
 * <p>
 *    Variable v = new Variable('x');<br>
 *    v.setValue(9);<br>
 *    Vector vec = new Vector();<br>
 *    vec.addElement(v);<br>
 *<br>
 *    Function f = new Function("root[2](x)");<br>
 *    double res = f.calculate(vec);<br>
 *
 *
 * @author: Oliver Kurt
 * @date: 12/31/2001
 */
public class Function {

  /** Wurzel des Funktionsbaumes. */
  private Node root;

  /**
   * Wird der Konstruktor ohne Parameter verwendet, dann muss über die Methode
   * initialize(String) die Klasse initialisiert werden, bevor calculate aufgerufen werden darf.
   */
  public Function() {
  }

  /**
   * Konstruktor der die Klasse vollständig konstruiert. Anschließend kann direkt calcluate aufgerufen werden.
   */
  public Function(String stringToParse) {
    initialize(stringToParse);
  }

  /**
   * Wertet den Funktionsbaum aus, dabei werden alle Variablen mit den Werten ausgewertet die in variables übergeben wurden.
   * Für jede im Funktionsbaum verwendete Variable, müss eine Instanz von Variable im Vector übergeben werden.
   *
   * @param variables Vector von {@link Variable} Objekten.
   * @return Funktionswert.
   */
  public double calculate(Vector variables) {
    return root.getValue(variables);
  }

  /**
   * Initialisiert den Funktionsbaum, d.h. erstellt anhand des übergebenen Strings einen auswertbaren Funktionsgraphen.
   *
   * @param stringToParse String der zu parsen ist.
   */
  public void initialize(String stringToParse) {
    root = NodeFactory.newInstance(stringToParse);
  }

  static public void main(String args[]) {
    Variable v = new Variable('x');
    v.setValue(4);
    Vector vec = new Vector();
    vec.addElement(v);

    Function f = new Function("((x^2-3))*((x-5))+5");
    double res = f.calculate(vec);
    System.out.println(res);

  }

}