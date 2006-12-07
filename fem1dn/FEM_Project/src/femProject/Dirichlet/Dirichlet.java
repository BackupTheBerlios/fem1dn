package femProject.Dirichlet;

import femProject.Function.Function;
import femProject.Function.FunctionInterface;

import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: zagi
 * Date: 2006-12-07
 * Time: 18:20:48
 * To change this template use File | Settings | File Templates.
 */
public class Dirichlet {
    Function p,q,r,f,pp;

    public Dirichlet() {
        FunctionInterface functionInterface = new FunctionInterface();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        int     posX = (int) (dim.getWidth() / 2) - functionInterface.getWidth() / 2,
                posY = (int) (dim.getHeight() / 2) - functionInterface.getHeight() / 2;
        functionInterface.setLocation(posX,posY);

        functionInterface.setFunctionName("p");
        functionInterface.setVisible(true);
        p = functionInterface.getFunction();

        functionInterface.setFunctionName("pp");
        functionInterface.setVisible(true);
        pp = functionInterface.getFunction();

        functionInterface.setFunctionName("q");
        functionInterface.setVisible(true);
        q = functionInterface.getFunction();

        functionInterface.setFunctionName("r");
        functionInterface.setVisible(true);
        r = functionInterface.getFunction();

        functionInterface.setFunctionName("f");
        functionInterface.setVisible(true);
        f = functionInterface.getFunction();

    }
}
