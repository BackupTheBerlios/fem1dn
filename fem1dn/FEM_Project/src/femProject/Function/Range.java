package femProject.Function;

/**
 * Created by IntelliJ IDEA.
 * User: zagi
 * Date: 2006-12-04
 * Time: 14:44:21
 * To change this template use File | Settings | File Templates.
 */
public class Range {
    float begin;
    boolean inclBeg;
    float end;
    boolean inclEnd;
    private static String INFINITY_SYMBOL = "\u221E";

    public Range(float beg, float end, boolean inclBeg, boolean inclEnd) {
        this.begin = beg;
        this.end = end;
        this.inclBeg = inclBeg;
        this.inclEnd = inclEnd;
    }

    public Range(Range range) {
        this.begin = range.begin;
        this.end = range.end;
        this.inclBeg = range.inclBeg;
        this.inclEnd = range.inclEnd;
    }

    public boolean isInRange(float x) {
        boolean endOk = false;
        if (x < end)
            endOk = true;
        else if (inclEnd && x == end) endOk = true;

        if (endOk) {
            if (x > begin) return true;
            else if (inclBeg && x == begin) return true;
        }
        return false;
    }

    public float getBeg() {
        return begin;
    }

    public float getEnd() {
        return end;
    }


    public boolean intersects(Range r) {
        if ((this.begin < r.begin && r.begin < this.end) ||
                (this.begin < r.end && r.end < this.end) ||
                (r.begin < this.begin && this.begin < r.end) ||
                (r.begin < this.end && this.end < r.end) ||
                (this.inclBeg && ((r.inclBeg && this.begin == r.begin) || (r.inclEnd && r.end == this.begin))) ||
                (this.inclEnd && ((r.inclBeg && this.end == r.begin) || (r.inclEnd && r.end == this.end))) ||
                (this.end == r.end && this.begin == r.begin))
            return true;
        else return false;


    }
    
    public String toString(){
       String beg = begin == Float.NEGATIVE_INFINITY ?
                    "-" + INFINITY_SYMBOL : String.valueOf(begin);
       String ends = end == Float.POSITIVE_INFINITY ?
                    "+" + INFINITY_SYMBOL : String.valueOf(end);
        String s = (inclBeg?"[":"(") + beg + " ; " + ends + (inclEnd?"]":")");
        return s;                            
    }
}
