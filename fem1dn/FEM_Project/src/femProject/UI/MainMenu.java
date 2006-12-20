package femProject.UI;

import femProject.Function.FunctionInterface;
import femProject.Dirichlet.Dirichlet;
import femProject.Neumann.Neumann;
import femProject.Drawing.DrawingPanel;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
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
    private JCheckBox errorCheckBox;
    private JCheckBox oldFunctions;   
    private Neumann neumann = null;
    private Dirichlet dirichlet = null;

    private float a, b, upa, upb;
    private int n;

    public MainMenu() {
        setContentPane(contentPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     
        dirichlet = new Dirichlet();
        neumann = new Neumann();


        warunkiDirichletaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if (getConditions()) {

                    try {
                        if(!oldFunctions.isSelected() || neumann.isNotUsed())
                            dirichlet.setConditions(a, b, n, upa, upb, errorCheckBox.isSelected());
                        else{
                            dirichlet.setA(a);
                            dirichlet.setB(b);
                            dirichlet.setUa(upa);
                            dirichlet.setUb(upb);
                            dirichlet.setN(n);

                        }
                        dirichlet.start();

                        float[] xi = dirichlet.getX();
                        float[] ax = dirichlet.getY();
                        float[][] tab = new float[2][];
                        tab[0] = new float[n + 1];
                        tab[1] = new float[n + 1];
                        for (int i = 0; i <= n; ++i) {
                            tab[0][i] = xi[i];
                            tab[1][i] = ax[i];
                        }
                        ResultForm result = new ResultForm();
                        result.addFunction(Color.green, tab);

                        if (errorCheckBox.isSelected()) {
                            float[][] fTab = new float[2][];
                            fTab[0] = new float[n + 1];
                            fTab[1] = new float[n + 1];
                            for (int i = 0; i <= n; ++i) {
                                fTab[0][i] = xi[i];
                                fTab[1][i] = dirichlet.getU(xi[i]);
                            }
                            result.addFunction(Color.BLUE, fTab);
                            result.setFunctionLists(tab, fTab[1]);
                            result.setError(dirichlet.error());
                        } else result.setFunctionLists(tab);

                        result.refresh();
                        result.showResults();
                        if(oldFunctions.isSelected()) lockRangeTextFields(true);
                    } catch (Exception e) {
                      //  e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }


                }
            }
        });

        warunkiNeumannaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if (getConditions()) {
                    try {
                        if (!oldFunctions.isSelected() || neumann.isNotUsed())
                            neumann.setConditions(a, b, n, upa, upb, errorCheckBox.isSelected());                            
                        else {                            
                            neumann.setA(a);
                            neumann.setB(b);
                            neumann.setUpa(upa);
                            neumann.setUpb(upb);
                            neumann.setN(n);
                        }
                        neumann.start();
                        float[] xi = neumann.getX();
                        float[] yi = neumann.getY();
                        float[][] tab = new float[2][];
                        tab[0] = new float[n + 1];
                        tab[1] = new float[n + 1];
                        for (int i = 0; i <= n; ++i) {
                            tab[0][i] = xi[i];
                            tab[1][i] = yi[i];
                        }
/*                      ResultForm result = new ResultForm();
                        JFrame frame = new JFrame("DrawingPanel");
                        DrawingPanel fi = new DrawingPanel();
                        frame.addKeyListener(fi);
                        frame.setContentPane(fi);
                        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.pack();
                        frame.setBounds(50, 50, 500, 500);
                        frame.setVisible(true);
                        fi.addFunction(tab, Color.green);
                        fi.addFunction(u, Color.red);
                        fi.computeBoundary();
                        fi.repaint();
*/
                        ResultForm result = new ResultForm();

                        result.addFunction(Color.green, tab);

                        if (errorCheckBox.isSelected()) {
                            int count = 1000;
                            float[][] u = neumann.getU(count);
                            result.addFunction(Color.BLUE, u);
                            result.setFunctionLists(tab, neumann.getU(n + 1)[1]);
                            result.setError(neumann.error());
                        } else result.setFunctionLists(tab);

                        result.refresh();
                        result.showResults();
                        if(oldFunctions.isSelected()) lockRangeTextFields(true);
                    } catch (Exception e) {
                      //  e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }

            }
        });
        koniecButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        errorCheckBox.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent changeEvent) {
                if(!oldFunctions.isSelected()){
                    lockRangeTextFields(false);
                }
            }
        });
    }
    private void lockRangeTextFields(boolean val){
        this.aTextField.setEditable(!val);
        this.bTextField.setEditable(!val);
    }

    private static final int WIDTH = 300,
            HEIGHT = 340;

    public static void main(String[] args) {
        MainMenu dialog = new MainMenu();
        dialog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        int posX = (int) (dim.getWidth() / 2) - WIDTH / 2,
                posY = (int) (dim.getHeight() / 2) - HEIGHT / 2;

        dialog.setSize(WIDTH, HEIGHT);
        dialog.setLocation(posX, posY);

        dialog.setVisible(true);
    }

    private boolean getConditions() {
        try {
            a = Float.parseFloat(this.aTextField.getText());
            b = Float.parseFloat(this.bTextField.getText());
            upa = Float.parseFloat(this.upaTextField.getText());
            upb = Float.parseFloat(this.upbTextField.getText());
            n = Integer.parseInt(this.nTextField.getText());
            if (n > 0 && n < 1000)
                return true;
            else
                return false;
        } catch (Exception ex) {
            return false;
        }
    }
}
