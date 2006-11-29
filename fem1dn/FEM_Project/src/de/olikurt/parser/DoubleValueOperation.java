/*
 * @(#)DoubleValueOperation.java	1.00 12/31/2001
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
public class DoubleValueOperation implements Operation {

  /**
   * Speichert den typ der Operation. z.b. + oder - oder sin...
   */
  protected int typ;

  public DoubleValueOperation(int typ) {
    this.typ = typ;
  }

  public Node[] devideAndCreate(String string) {
    Node[] nodes = new Node[2];
    nodes[0] = NodeFactory.newInstance(getErsterKlammernTeil(string, '('));
    nodes[1] = NodeFactory.newInstance(getErsterKlammernTeil(string, '['));
    return nodes;
  }


  public double link(Node[] nodes, Vector variables) {
    switch ( typ ) {
      case LOG:
        return Math.log(nodes[0].getValue(variables)) / Math.log(nodes[1].getValue(variables));
      case ROOT:
        if ( nodes[1].getValue(variables) == 2 ) {
          return Math.sqrt(nodes[0].getValue(variables));
        }
        else {
          return Math.pow(nodes[0].getValue(variables),1/nodes[1].getValue(variables));
        }
    }
    throw new RuntimeException("Unknown type: "+typ);
  }


  /**
   * Gibt den String zurück, der in einer Klammer steht.
   * Bsp.:<br>
   * input="sin(x+5+ln(x)) typ='(' => return ist: x+5+ln(x) <br>
   * input="root[ln(x)](sin(x)) typ='[' => return ist: ln(x) <br>
   *
   * @param typ: darf nur ( oder [ sein.
   * @param input zu parsender String.
   * @return string in einer Klammer.
   */
  private String getErsterKlammernTeil(String input, char typ) {

    int startKa=0,endeKa=0,startKea=0,endeKea=0;
    int ka = 0, kea=0;
    for ( int i = 0 ; i < input.length() ; i++) {
      if ( input.charAt(i) == '(' && kea==0 ) {
        if ( ka == 0 ) {
          startKa = i+1;
        }
        ka++;
      }
      else if ( input.charAt(i) == ')'  && kea==0 ) {
        ka--;
        if ( ka == 0 ) {
          endeKa = i;
        }
      }
      else if ( input.charAt(i) == '['  && ka==0 ) {
        if ( kea == 0 ) {
          startKea = i+1;
        }
        kea++;
      }
      else if ( input.charAt(i) == ']'  && ka==0 ) {
        kea--;
        if ( kea == 0 ) {
          endeKea = i;
        }
      }
    }
    if ( typ == '(' ) {
      return input.substring(startKa,endeKa);
    }
    else if ( typ == '[' ) {
      return input.substring(startKea,endeKea);
    }
    else {
      throw new RuntimeException("Der Klammertyp "+typ+" wird nicht unterstützt.");
    }
  }

}

