package femProject.Drawing;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Jakub
 * Date: 2006-12-01
 * Time: 22:25:52
 * To change this template use File | Settings | File Templates.
 */
public class DrawingPanel extends JPanel implements KeyListener {
    private List<float[][]> tab;
    private List<Color> color;
    private float xMin = -1, xMax = 1, yMin = -1, yMax = 1;
    private final float  EPS = 0.01f;

    public DrawingPanel() {
        this.addKeyListener(this);
        this.color = new ArrayList<Color>();
        this.tab = new ArrayList<float[][]>();
    }

    public DrawingPanel(List<float[][]> tab, List<Color> color) {
        this.addKeyListener(this);
        this.tab = tab;
        this.color = color;
    }


     public float getxMin() {
        return xMin;
    }

    public void setxMin(float xMin) {
        this.xMin = xMin - EPS;
    }

    public float getxMax() {
        return xMax;
    }

    public void setxMax(float xMax) {
        this.xMax = xMax + EPS;
    }

    public float getyMin() {
        return yMin;
    }

    public void setyMin(float yMin) {
        this.yMin = yMin - EPS;
    }

    public float getyMax() {
        return yMax;
    }

    public void setyMax(float yMax) {
        this.yMax = yMax + EPS;
    }

    public List<float[][]> getTab() {
        return tab;
    }

    public void setTab(List<float[][]> tab) {
        this.tab = tab;
    }

    public List<Color> getColor() {
        return color;
    }

    public void setColor(List<Color> color) {
        this.color = color;
    }

    public void keyTyped(KeyEvent keyEvent) {
        //keyTyped(keyEvent);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public void keyPressed(KeyEvent keyEvent) {
        //keyPressed(keyEvent);    //To change body of overridden methods use File | Settings | File Templates.
        float xStep = (xMax - xMin) / this.tab.get(0)[0].length, yStep = (yMax - yMin) / this.tab.get(0)[0].length;
        switch (keyEvent.getKeyCode()) {
            case 107: //+
                xMax -= xStep;
                xMin += xStep;
                yMax -= yStep;
                yMin += yStep;
                break;
            case 109: //-
                xMax += xStep;
                xMin -= xStep;
                yMax += yStep;
                yMin -= yStep;
                break;
            case 37:  //lewa strzalka
                xMax += xStep;
                xMin += xStep;
                break;
            case 38:  //strzalka do gory
                yMax -= yStep;
                yMin -= yStep;
                break;
            case 39:  //prawa strzalka
                xMax -= xStep;
                xMin -= xStep;
                break;
            case 40: //strzalka w dol
                yMax += yStep;
                yMin += yStep;
                break;
        }
        this.repaint();
    }


    public void keyReleased(KeyEvent keyEvent) {
        //keyReleased(keyEvent);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public void computeBoundary() {
        xMin = Float.POSITIVE_INFINITY;
        xMax = Float.NEGATIVE_INFINITY;
        yMin = Float.POSITIVE_INFINITY;
        yMax = Float.NEGATIVE_INFINITY;
        for (float[][] aTab : this.tab) {
            for (int i = 0; i < aTab[0].length; ++i) {
                if (xMin > aTab[0][i]) {
                    this.setxMin(aTab[0][i]);
                }
                if (xMax < aTab[0][i]) {
                    this.setxMax(aTab[0][i]);
                }
                if (yMin > aTab[1][i]) {
                    this.setyMin(aTab[1][i]);
                }
                if (yMax < aTab[1][i]) {
                    this.setyMax(aTab[1][i]);
                }
            }
        }
    }

    public void paint(Graphics g) {
        super.paint(g);    //To change body of overridden methods use File | Settings | File Templates.
        int x0 = -(int) (this.getWidth() * xMin / (xMax - xMin)),
                y0 = this.getHeight() + (int) (this.getHeight() * yMin / (yMax - yMin));

        g.setColor(Color.white);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.black);
        //OY
        g.drawLine(x0, 0, x0, this.getHeight());
        //OX
        g.drawLine(0, y0, this.getWidth(), y0);

        for (int k = 0; k < this.tab.size(); ++k) {
            if (k < this.color.size())
                g.setColor(this.color.get(k));
            else
                g.setColor(Color.red);
            float[][] aTab = this.tab.get(k);
            for (int i = 0; i < aTab[0].length - 1; ++i) {
                g.drawLine((int) (this.getWidth() * (aTab[0][i] - xMin) / (xMax - xMin)),
                        this.getHeight() + (int) (this.getHeight() * (yMin - aTab[1][i]) / (yMax - yMin)),
                        (int) (this.getWidth() * (aTab[0][i + 1] - xMin) / (xMax - xMin)),
                         this.getHeight() + (int) (this.getHeight() * (yMin - aTab[1][i + 1]) / (yMax - yMin)));
            }
        }
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("DrawingPanel");
        DrawingPanel fi = new DrawingPanel();
        frame.addKeyListener(fi);
        frame.setContentPane(fi);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setBounds(50, 50, 500, 500);
        frame.setVisible(true);
        fi.test();
    }

    public void test() {
        float[][] pTab = new float[2][];
        for (int i = 0; i < pTab.length; ++i) {
            pTab[i] = new float[100];
        }
        float x=-3.14f;
        for(int i=0;i<pTab[0].length;++i){
            x = pTab[0][i] = x + 6.28f/pTab[0].length;
            pTab[1][i] = (float)Math.sin(x);
        }
        this.color = new ArrayList<Color>();
        color.add(Color.red);
        this.tab = new ArrayList<float[][]>();
        this.tab.add(pTab);

        float[][] aTab = new float[2][];
        for(int i=0;i<aTab.length;++i){
            aTab[i] = new float[1000];
        }

        x=-1;
        for(int i=0;i<aTab[0].length;++i){
            x = aTab[0][i] = x + 6f/aTab[0].length;
            if(i<aTab[0].length/4){
                aTab[1][i] = -3f;
            }
            else if(i>aTab[0].length/4){
                aTab[1][i] = 1f;
            }
            else {
                aTab[1][i] = -3f;
                ++i;
                aTab[0][i] = x;
                aTab[1][i] = 1f;
            }
        }
        this.color.add(Color.green);
        this.tab.add(aTab);
        this.computeBoundary();
        this.repaint();
    }
}
