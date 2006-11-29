/*
 * @(#)OperationFactory.java	1.00 12/31/2001
 *
 * Copyright 2001 by Oliver Kurt. All Rights Reserved.
 *
 * This software is the proprietary information of Oliver Kurt, Germany.
 * Use is subject to license terms.
 *
 */

package de.olikurt.parser;

/**
 * Zuständig für das Erstellen von Operationen.
 *
 * @author: Oliver Kurt
 * @date: 12/31/2001
 */
public class OperationFactory {

  /**
   * Diese Klasse wird niemals instanziiert.
   */
  private OperationFactory() {
  }

  /**
   * Analysiert den String string und erkennt an welche Operation der String aufgebrochen werden muss.
   *
   * @param string der eine zu evaluierende Zeichenkette (die Funktion) enthält.
   * @return Gibt ein Operation-Objekt zurück, das die 'nächste' mathematische Operation enthält. Hier werden die Prioritäten der mathematischen Funtionen überwacht.
   */
  public static Operation getNextOperation(String string) {

    int ka=0,kea=0; // das gibt die anzahl der klammern an
    int plus=-1,minus=-1,mal=-1,geteilt=-1,hoch=-1; // das gibt die position der operation an.

    // geht den gesamten string durch
    // merkt sich in den variabeln das ERSTE vorkommen des operatores, wenn er nicht in klammern() [] steht
    for (int i = 0 ; i< string.length() ; i++) {

      if (string.charAt(i)=='(') ka++;
      if (string.charAt(i)==')') ka--;

      if (string.charAt(i)=='[') kea++;
      if (string.charAt(i)==']') kea--;

      if (plus==-1     && string.charAt(i)=='+' && ka==0 && kea==0)  plus=i;
      if (minus==-1    && string.charAt(i)=='-' && ka==0 && kea==0)  minus=i;
      if (mal==-1      && string.charAt(i)=='*' && ka==0 && kea==0)  mal=i;
      if (geteilt==-1  && string.charAt(i)=='/' && ka==0 && kea==0)  geteilt=i;
      if (hoch==-1     && string.charAt(i)=='^' && ka==0 && kea==0)  hoch=i;

    }

    // die Reihenfolge der nachstehenden Anweisung ist entscheident !!!

    if (plus>-1)    return new BasicOperation(plus, Operation.PLUS);
    // das minus wird mathematisch doppeltdeutig eingesetzt.
    // a) als subtraktionszeichen
    // b) als Kurzform für *-1
    // daher muss geschaut werden, ob das minus alleine steht, oder ob es vor einem anderen operator steht
    if (minus>-1 && !(plus==minus-1||mal==minus-1||geteilt==minus-1||hoch==minus-1) )   return new BasicOperation(minus, Operation.MINUS);
    if (mal>-1)     return new BasicOperation(mal, Operation.MULTIPLICATION);
    if (geteilt>-1) return new BasicOperation(geteilt, Operation.DIVISION);
    if (hoch>-1)    return new BasicOperation(hoch, Operation.POWER);

    if ( string.startsWith("ln")   ) return new PostfixOperation(Operation.LN);
    if ( string.startsWith("exp")  ) return new PostfixOperation(Operation.EXP);
    if ( string.startsWith("sin")  ) return new PostfixOperation(Operation.SIN);
    if ( string.startsWith("cos")  ) return new PostfixOperation(Operation.COS);
    if ( string.startsWith("tan")  ) return new PostfixOperation(Operation.TAN);
    if ( string.startsWith("asin") ) return new PostfixOperation(Operation.ASIN);
    if ( string.startsWith("acos") ) return new PostfixOperation(Operation.ACOS);
    if ( string.startsWith("atan") ) return new PostfixOperation(Operation.ATAN);

    if ( string.startsWith("log")  ) return new DoubleValueOperation(Operation.LOG);
    if ( string.startsWith("root") ) return new DoubleValueOperation(Operation.ROOT);

    throw new RuntimeException("Unable to parse string: "+string);
  }

}