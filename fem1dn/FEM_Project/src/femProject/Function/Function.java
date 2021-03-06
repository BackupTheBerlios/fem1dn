package femProject.Function;

import java.util.Vector;
import java.util.Collections;
import java.util.ArrayList;

import org.nfunk.jep.JEP;
import org.nfunk.jep.Variable;
import org.nfunk.jep.ParseException;
import org.nfunk.jep.SymbolTable;
import org.lsmp.djep.rpe.RpCommandList;
import org.lsmp.djep.rpe.RpEval;
import org.lsmp.djep.xjep.XJep;

/**
 * Created by IntelliJ IDEA.
 * User: zagi
 * Date: 2006-12-04
 * Time: 14:12:39
 * To change this template use File | Settings | File Templates.
 */
public class Function {
    private Range[] ranges;
    private  RpCommandList[] functions;
    private int rangeNum, symTabMaxSize, symTabSize;
    private float minX, maxX;
    private JEP jep;
    private RpEval rpe;
    private final int POINTS_NUMBER = 10;
    private static final float EPSILON = 0.00001f;


    private void initJep() throws ParseException {
          jep = new XJep();
          jep.addStandardFunctions();
          jep.addStandardConstants();
          symTabMaxSize = jep.getSymbolTable().size() + 1; //pocz�tkowa liczba zapisanych sta�ych + 1 niewiadoma

          //jep.addComplex();
          jep.setAllowUndeclared(true);
          jep.setImplicitMul(true);
          jep.setAllowAssignment(true);
          rpe = new RpEval(jep);         
    }
    private void initTabs(int size){
        this.functions = new RpCommandList[size];
        this.ranges = new Range[size];
        this.rangeNum = size;
        minX = Float.POSITIVE_INFINITY;
        maxX = Float.NEGATIVE_INFINITY;
    }
    public Function(StoredFunction textFunction) throws Exception {
         if (textFunction != null) {
            initJep();
            initTabs(textFunction.getSize());
                        
            textFunction.initEnum();
            int i=0;
            while(textFunction.nextPoint()){
                parseFunction(i,textFunction.currentFunction(),textFunction.currentRange());
                i++;               
            }
            symTabSize = jep.getSymbolTable().size();
        } else throw new Exception("Invalid argument");
        
    }

    private void parseFunction(int ind, String fun, Range rng) throws Exception{
    //    fun = fun.replaceAll("pi",String.valueOf(Math.PI));
        org.nfunk.jep.Node node = jep.parse(fun);        
        this.functions[ind] = rpe.compile(node);
        SymbolTable sym = jep.getSymbolTable();
        if(sym.size() > symTabMaxSize) throw new Exception("Zbyt wiele niewiadomych");
        this.ranges[ind] = rng;
        if (ranges[ind].getBeg() < minX)
            minX = ranges[ind].getBeg();
        if (ranges[ind].getEnd() > maxX)
            maxX = ranges[ind].getEnd();        
    }

    public float getMinX() {
        return minX;
    }

    public float getMaxX() {
        return maxX;
    }


    public Range[] getRanges() {
        return ranges;
    }

    public Range[] getRanges(float a, float b){
        int ra=0, rb=ranges.length-1;
        for(int i=0;i<ranges.length;++i){
            if(ranges[i].isInRange(a))
                ra=i;
            if(ranges[i].isInRange(b))
                rb=i;
        }
        //ra>=rb
        Range[] r= new Range[ra-rb+1];
        for(int i=0;i<r.length;++i)
            r[i]=ranges[ra -i];
        return r;
    }

    public Function(Range[] ranges, String[] functions) throws Exception {
        if (ranges != null && functions != null && ranges.length > 0
                && ranges.length == functions.length) {
            initJep();
            initTabs(functions.length);

            for (int i = 0; i < ranges.length; i++) {
                parseFunction(i,functions[i],ranges[i]);
            }
            symTabSize = jep.getSymbolTable().size();
        } else throw new Exception("Invalid argument");
    }

    public float getValue(float x) throws Exception {
        int i = 0;
        while (i < rangeNum) {
            if (ranges[i].isInRange(x)) break;
            else i++;
        }

        if (i < rangeNum) {
            try{
                if(symTabSize == symTabMaxSize){ //tzn ze jest niewiadoma                 
                    Variable v = jep.getVar("x");
                    int ref = rpe.getVarRef(v);
                    rpe.setVarValue(ref,x);
                }
            }catch(Exception e){
                e.printStackTrace();
            }            
            return (float) rpe.evaluate(functions[i]);
        } else throw new Exception("Invalid argument");
    }
    private float apprValue(float x) throws Exception {
        int i = 0;
        while (i < rangeNum) {
            if (ranges[i].isInRange(x)) break;
            else i++;
        }
        if(i>=rangeNum){
            i=0;
            while (i < rangeNum) {
                if (ranges[i].begin == x){
                    x+=EPSILON;
                    break;
                }else if(ranges[i].end == x){
                    x-=EPSILON;
                    break;
                }
            else i++;
        }
            
        }

        if (i < rangeNum) {
            try{
                if(symTabSize == symTabMaxSize) //tzn ze jest niewiadoma
                    rpe.setVarValue(0,x);
            }catch(Exception e){};
            return (float) rpe.evaluate(functions[i]);
        } else throw new Exception("Invalid argument");

    }

    public void getValues(float[][] xyTab) throws Exception {
        for (int i = 0; i < xyTab[0].length; i++) {
            xyTab[1][i] = this.getValue(xyTab[0][i]);
        }
    }
  



}
