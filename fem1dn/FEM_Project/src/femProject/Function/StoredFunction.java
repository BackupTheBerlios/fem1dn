package femProject.Function;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by IntelliJ IDEA.
 * User: zagi
 * Date: 2006-12-17
 * Time: 15:00:07
 * To change this template use File | Settings | File Templates.
 */
public class StoredFunction {

      private ArrayList<FunctionPoint> points;
      private int enumPos;

      public StoredFunction(ArrayList<String> functions, ArrayList<Range> ranges) {
         points = new ArrayList<FunctionPoint>();

          for(int i=0; i < functions.size(); i++){
              this.points.add(new FunctionPoint(functions.get(i),ranges.get(i)));              
          }
      }

    public StoredFunction() {
        points = new ArrayList<FunctionPoint>();
    }

    public static ArrayList<StoredFunction> getStoredFunctions(String type){
           ArrayList<String> funs = new ArrayList<String>();
           ArrayList<Range> rngs = new ArrayList<Range>();
           ArrayList<StoredFunction> storedFuns = new ArrayList<StoredFunction>();
           rngs.add(new Range(0.0f,1.0f,true,true));

           if(type.equalsIgnoreCase("p")){
               funs.add("1");
               storedFuns.add(new StoredFunction(funs,rngs));

               funs.clear();
               funs.add("x+1");               
               storedFuns.add(new StoredFunction(funs,rngs));
           }else if(type.equalsIgnoreCase("pp")){
               funs.add("0");
               storedFuns.add(new StoredFunction(funs,rngs));

               funs.clear();
               funs.add("1");
               storedFuns.add(new StoredFunction(funs,rngs));
           }else if(type.equalsIgnoreCase("q")){
               funs.add("0");
               storedFuns.add(new StoredFunction(funs,rngs));

               funs.clear();
               funs.add("0.5");               
               storedFuns.add(new StoredFunction(funs,rngs));               
           }else if(type.equalsIgnoreCase("r")){
               funs.add("0");               
               storedFuns.add(new StoredFunction(funs,rngs));

               funs.clear();
               funs.add("2");               
               storedFuns.add(new StoredFunction(funs,rngs));

               funs.clear();
               funs.add("1");               
               storedFuns.add(new StoredFunction(funs,rngs));
           }else if(type.equalsIgnoreCase("u")){
               funs.add("x*(x-1)");
               storedFuns.add(new StoredFunction(funs,rngs));

               funs.clear();
               funs.add("sin(3.14*x)");
               storedFuns.add(new StoredFunction(funs,rngs));

            }else if(type.equalsIgnoreCase("f")){
               funs.add("-2");
               storedFuns.add(new StoredFunction(funs,rngs));

               funs.clear();
               funs.add("2*x^2-5*x-1.5");
               storedFuns.add(new StoredFunction(funs,rngs));
           }

           return storedFuns;
       }

    public void remove(int indx) {
        points.remove(indx);
    }

    public Range getRanges(int indx) {
        return points.get(indx).range;
    }

    public String getFuction(int indx) {
        return points.get(indx).function;
    }

    public void add(String fun, Range range) {
        points.add(new FunctionPoint(fun,range));
        Collections.sort(points);        
    }

    public boolean isRangeOk() {
        Range curr,prev;

        for(int i=points.size()-1; i>0; i--){
            curr = points.get(i).range;
            prev = points.get(i-1).range;
            if(curr.end != prev.begin || (!curr.inclEnd && !prev.inclBeg) ||
                curr.begin >= curr.end )
                return false;
        }
        return true;

    }


    private class FunctionPoint implements Comparable{
        String function;
        Range range;

        public FunctionPoint(String s, Range range) {
            this.function = new String(s);
            this.range = new Range(range); 
        }

        public int compareTo(Object o) {
            FunctionPoint fp = (FunctionPoint)o;            
            return (int)(fp.range.begin - this.range.begin);

        }
    }
    public void initEnum(){
        enumPos = -1;
        
    }
    public boolean nextPoint(){
        enumPos++;
        return enumPos<points.size();
    }
    public String currentFunction(){
        return points.get(enumPos).function;
    }
    public Range currentRange(){
        return points.get(enumPos).range;
    }
    public int getSize(){
        return points.size();
    }


}
