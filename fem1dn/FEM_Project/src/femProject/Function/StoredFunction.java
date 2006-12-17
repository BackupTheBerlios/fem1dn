package femProject.Function;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: zagi
 * Date: 2006-12-17
 * Time: 15:00:07
 * To change this template use File | Settings | File Templates.
 */
public class StoredFunction {

      private ArrayList<Range> ranges;

      public ArrayList<String> getFunctions() {
          return functions;
      }

      public ArrayList<Range> getRanges() {
          return ranges;
      }

      private ArrayList<String> functions;


      public StoredFunction(ArrayList<String> functions, ArrayList<Range> ranges) {
          this.functions = new ArrayList<String>();
          this.ranges = new ArrayList<Range>();

          for(int i=0; i < functions.size(); i++){
              this.functions.add(new String(functions.get(i)));
              this.ranges.add(new Range(ranges.get(i)));
          }
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
           }

           return storedFuns;
       }


}
