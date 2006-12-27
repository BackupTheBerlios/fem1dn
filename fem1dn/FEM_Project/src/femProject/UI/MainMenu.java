package femProject.UI;

import femProject.Dirichlet.Dirichlet;
import femProject.Neumann.Neumann;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;

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
    private JButton helpButton;
    private Neumann neumann = null;
    private Dirichlet dirichlet = null;
    private JFrame frame;
    private static final int COUNT = 1000;
    private static final int WIDTH = 300,
                             HEIGHT = 380;


    private float a, b, upa, upb;
    private int n;
    private static final String PROGRAM_INFO =  "Program rozwiazuje metoda elementu skonczonego rownanie\n" +
                                                "\n -(p(x)u')'+ q(x)u'+ r(x)u = f(x)  dla xî(a;b)\n\n" +
                                                "z zastosowaniem elementow liniowych,"+
                                                "z warunkami brzegowymi: \n"+
                                                "Dirichleta u(a)=ua, u(b)=ub, lub \n" +
                                                "Neumana u'(a)=upa, u'(b)=upb.\n"+
                                                "\nAplikacja rozpoznaje funkcje:\n"+
                                                "sin(x) cos(x) tan(x)\n" +
                                                "asin(x) acos(x) atan(x)\n"+
                                                "sinh(x) cosh(x) tanh(x) asinh(x) acosh(x) atanh(x)\n"+
                                                "ln(x) log(x) exp(x) abs(x) sqrt(x)\n"+
                                                "mod(x,y) - x modulo y\n"+
                                                "x^y -  x do potêgi y\n"+
                                                "oraz sta³e pi e"+
                                                "\n\nKlawisze:" +
                                                "\n strza³ki - poruszanie siê po wykresie:"+
                                                "\n +/- przybli¿anie oddalanie wykresów"+
                                                "\n\nAutorzy:\n\tJakub ¦lepowroñski\n\tPawe³ Zawistowski";

    public MainMenu() {
        frame = this;
        setContentPane(contentPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     
        dirichlet = new Dirichlet();
        neumann = new Neumann();


        warunkiDirichletaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if (getConditions()) {

                    try {
                        if(!oldFunctions.isSelected() || dirichlet.isNotUsed())
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
                            float[][] fTab = dirichlet.getU(COUNT);
                            result.addFunction(Color.BLUE, fTab);
                            result.setFunctionLists(tab, dirichlet.getU(xi));
                            result.setError(dirichlet.error());
                        } else result.setFunctionLists(tab);

                        result.refresh();
                        result.showResults();
                        if(oldFunctions.isSelected()) lockRangeTextFields(true);
                    } catch (Exception e) {
                       // e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
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
                            float[][] u = neumann.getU(COUNT);
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
        helpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                  JOptionPane.showMessageDialog(frame,PROGRAM_INFO);                 
            }
        });
    }
    private void lockRangeTextFields(boolean val){
        this.aTextField.setEditable(!val);
        this.bTextField.setEditable(!val);
    }


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
