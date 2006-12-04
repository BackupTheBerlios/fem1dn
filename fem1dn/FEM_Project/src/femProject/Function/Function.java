package femProject.Function;

import de.olikurt.parser.Variable;

import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: zagi
 * Date: 2006-12-04
 * Time: 14:12:39
 * To change this template use File | Settings | File Templates.
 */
public class Function {
    private Range[] ranges;
    private de.olikurt.parser.Function[] functions;
    private int rangeNum;
    private Variable var;
    private Vector vect;
    private float minX, maxX;


    public float getMinX() {
        return minX;
    }

    public float getMaxX() {
        return maxX;
    }

    public Function(Range[] ranges, String[] functions) throws Exception {
        if (ranges != null && functions != null && ranges.length > 0
                && ranges.length == functions.length) {
            this.functions = new de.olikurt.parser.Function[ranges.length];
            this.rangeNum = ranges.length;
            this.ranges = ranges;
            minX = ranges[0].getBeg();
            maxX = ranges[0].getEnd();

            this.var = new Variable('x');
            this.vect = new Vector();
            vect.add(var);
            for (int i = 0; i < ranges.length; i++) {
                this.functions[i] = new de.olikurt.parser.Function(functions[i]);
                if (ranges[i].getBeg() < minX)
                    minX = ranges[i].getBeg();
                if (ranges[i].getEnd() > maxX)
                    maxX = ranges[i].getEnd();
            }
        } else throw new Exception("Invalid argument");
    }

    public float getValue(float x) throws Exception {
        int i = 0;
        while (i < rangeNum) {
            if (ranges[i].isInRange(x)) break;
            else i++;
        }

        if (i < rangeNum) {
            this.var.setValue(x);
            return (float) functions[i].calculate(this.vect);
        } else throw new Exception("Invalid argument");
    }

    public void getValues(float[][] xyTab) throws Exception {
        for (int i = 0; i < xyTab[0].length; i++) {
            xyTab[1][i] = this.getValue(xyTab[0][i]);
        }

    }
}
