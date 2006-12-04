package femProject.UI;

import femProject.Function.Function;
import femProject.Drawing.DrawingPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: zagi
 * Date: 2006-12-04
 * Time: 13:26:46
 * To change this template use File | Settings | File Templates.
 */
public class ResultForm extends JFrame {
    private JTabbedPane tabbedPane1;

    private JPanel panel1;
    private DrawingPanel drawingPanel;
    private static final int WIDTH = 400,
            HEIGHT = 300;

    public ResultForm() {
        super();
        this.setContentPane(panel1);
        this.setSize(WIDTH, HEIGHT);
        tabbedPane1.addKeyListener(drawingPanel);

    }

    public void setFunction(Function fun) throws Exception {
        ArrayList<Color> colors = new ArrayList<Color>();
        colors.add(Color.RED);
        float[][] pTab = new float[2][];
        for (int i = 0; i < pTab.length; ++i) {
            pTab[i] = new float[100];
        }
        float x = (fun.getMinX() == Float.NEGATIVE_INFINITY) ? -10 : fun.getMinX();
        float endX = (fun.getMaxX() == Float.POSITIVE_INFINITY) ? 10 : fun.getMaxX();
        float diff = (endX - x) / 100;

        for (int i = 0; i < pTab[0].length; ++i) {
            x = pTab[0][i] = x + diff;
            pTab[1][i] = fun.getValue(x);
        }

        ArrayList<float[][]> l = new ArrayList<float[][]>();
        l.add(pTab);
        drawingPanel.setTab(l);
        drawingPanel.setColor(colors);
        drawingPanel.repaint();
    }
}
