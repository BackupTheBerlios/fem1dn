package femProject.UI;

import femProject.Function.FunctionInterface;
import femProject.Dirichlet.Dirichlet;
import femProject.Neumann.Neumann;
import femProject.Drawing.DrawingPanel;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.util.ArrayList;

public class MainMenu extends JFrame {
    private JPanel contentPane;
    private JButton warunkiDirichletaButton;
    private JButton warunkiNeumannaButton;
    private JButton koniecButton;
    private JTextField aTextField;
    private JTextField bTextField;
    private JTextField nTextField;
    private JTextField upaTextField;
    private JTextField upbTextField;
    private ResultForm resultForm;

    private float a,b,upa,upb;
    private int n;

    public MainMenu() {
        setContentPane(contentPane);
        resultForm = new ResultForm();


        warunkiDirichletaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if(getConditions()){
                    Dirichlet dirichlet = new Dirichlet(a,b,n,upa,upb);
                    dirichlet.start();
                    float[] xi = dirichlet.getXi();
                    float[] ax = dirichlet.getAx();
                    float[][] tab = new float[2][];
                    tab[0] = new float[n+1];
                    tab[1] = new float[n+1];
                    for(int i=0;i<=n;++i){
                        tab[0][i] = xi[i];
                        tab[1][i] = ax[i];
                    }
                    JFrame frame = new JFrame("DrawingPanel");
                    DrawingPanel fi = new DrawingPanel();
                    frame.addKeyListener(fi);
                    frame.setContentPane(fi);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.pack();
                    frame.setBounds(50, 50, 500, 500);
                    frame.setVisible(true);
                    fi.addFunction(tab, Color.green);
                    fi.computeBoundary();
                    fi.repaint();
                }
            }
        });

        warunkiNeumannaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if(getConditions()){
                    Neumann neumann = new Neumann(a,b,n,upa,upb);
                    neumann.start();
                    float[] xi = neumann.getXi();
                    float[] ax = neumann.getAx();
                    float[][] tab = new float[2][];
                    tab[0] = new float[n+1];
                    tab[1] = new float[n+1];
                    for(int i=0;i<=n;++i){
                        tab[0][i] = xi[i];
                        tab[1][i] = ax[i];
                    }
                    JFrame frame = new JFrame("DrawingPanel");
                    DrawingPanel fi = new DrawingPanel();
                    frame.addKeyListener(fi);
                    frame.setContentPane(fi);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.pack();
                    frame.setBounds(50, 50, 500, 500);
                    frame.setVisible(true);
                        fi.addFunction(tab, Color.green);
                        fi.computeBoundary();
                        fi.repaint();
                            }

                        }
                });
        koniecButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
    }

    private static final int WIDTH = 320,
            HEIGHT = 280;

    public static void main(String[] args) {
        MainMenu dialog = new MainMenu();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        int posX = (int) (dim.getWidth() / 2) - WIDTH / 2,
                posY = (int) (dim.getHeight() / 2) - HEIGHT / 2;

        dialog.setSize(WIDTH, HEIGHT);
        dialog.setLocation(posX, posY);

        dialog.setVisible(true);
    }

    private boolean getConditions(){
        try{
            a = Float.parseFloat(this.aTextField.getText());
            b = Float.parseFloat(this.bTextField.getText());
            upa = Float.parseFloat(this.upaTextField.getText());
            upb = Float.parseFloat(this.upbTextField.getText());
            n = Integer.parseInt(this.nTextField.getText());
            if(n>0 && n<=1000)
                return true;
            else
                return false;
        }   catch(Exception ex){
            return false;
        }
    }
}
