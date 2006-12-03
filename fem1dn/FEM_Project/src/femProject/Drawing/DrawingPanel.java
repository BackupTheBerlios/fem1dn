package femProject.Drawing;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: Jakub
 * Date: 2006-12-01
 * Time: 22:25:52
 * To change this template use File | Settings | File Templates.
 */
public class DrawingPanel extends JPanel implements KeyListener {
    private float[][] tab;

    public DrawingPanel() {
        this.tab = new float[0][0];
        this.addKeyListener(this);
    }

    public DrawingPanel(float[][] tab) {
        this.addKeyListener(this);
        this.tab = tab;
    }

    public float[][] getTab() {
        return tab;
    }

    public void setTab(float[][] tab) {
        this.tab = tab;
        this.repaint();
    }

    public void keyTyped(KeyEvent keyEvent) {
        keyTyped(keyEvent);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public void keyPressed(KeyEvent keyEvent) {
        keyPressed(keyEvent);    //To change body of overridden methods use File | Settings | File Templates.
    }


    public void keyReleased(KeyEvent keyEvent) {
        keyReleased(keyEvent);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.black);
        g.drawLine(this.getWidth()/2,0,this.getWidth()/2,this.getHeight());
        g.drawLine(0,this.getHeight()/2,this.getWidth(),this.getHeight()/2);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("DrawingPanel");
        DrawingPanel fi = new DrawingPanel();

        frame.setContentPane(new DrawingPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setBounds(50, 50, 500, 500);

        frame.setVisible(true);
    }
}
