package femProject.UI;

import femProject.Dirichlet.Dirichlet;
import femProject.Neumann.Neumann;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;

import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.Spacer;

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
    private static final String PROGRAM_INFO = "Program rozwiazuje metod¹ elementu skoñczonego równanie\n" +
            "\n -(p(x)u')'+ q(x)u'+ r(x)u = f(x)  dla x zawartego  w (a;b)\n\n" +
            "z zastosowaniem elementów liniowych," +
            "z warunkami brzegowymi: \n" +
            "Dirichleta u(a)=ua, u(b)=ub, lub \n" +
            "Neumana u'(a)=upa, u'(b)=upb.\n" +
            "\nAplikacja rozpoznaje funkcje:\n" +
            "sin(x) cos(x) tan(x)\n" +
            "asin(x) acos(x) atan(x)\n" +
            "sinh(x) cosh(x) tanh(x) asinh(x) acosh(x) atanh(x)\n" +
            "ln(x) log(x) exp(x) abs(x) sqrt(x)\n" +
            "mod(x,y) - x modulo y\n" +
            "x^y -  x do potêgi y\n" +
            "oraz sta³e pi e" +
            "\n\nKlawisze:" +
            "\n strza³ki - poruszanie siê po wykresie:" +
            "\n +/- przybli¿anie oddalanie wykresów" +
            "\n\nAutorzy:\n\tJakub Œlepowroñski\n\tPawel‚ Zawistowski";

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
                        if (!oldFunctions.isSelected() || dirichlet.isNotUsed())
                            dirichlet.setConditions(a, b, n, upa, upb, errorCheckBox.isSelected());
                        else {
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
                        if (oldFunctions.isSelected()) lockRangeTextFields(true);
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
                        if (oldFunctions.isSelected()) lockRangeTextFields(true);
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
                if (!oldFunctions.isSelected()) {
                    lockRangeTextFields(false);
                }
            }
        });
        helpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(frame, PROGRAM_INFO);
            }
        });
    }

    private void lockRangeTextFields(boolean val) {
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
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection HardCodedStringLiteral
     */
    private void $$$setupUI$$$() {
        contentPane = new JPanel();
        contentPane.setLayout(new GridLayoutManager(1, 1, new Insets(10, 10, 10, 10), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(14, 1, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        warunkiDirichletaButton = new JButton();
        warunkiDirichletaButton.setText("Warunki Dirichleta");
        panel1.add(warunkiDirichletaButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(200, 25), null, 0, false));
        warunkiNeumannaButton = new JButton();
        warunkiNeumannaButton.setText("Warunki Neumanna");
        panel1.add(warunkiNeumannaButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(200, 25), null, 0, false));
        koniecButton = new JButton();
        koniecButton.setText("Koniec");
        panel1.add(koniecButton, new GridConstraints(13, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(200, 25), null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, new Dimension(200, 11), null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel1.add(spacer2, new GridConstraints(10, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(200, 14), null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(5, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel2, new GridConstraints(3, 0, 5, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 129), null, 0, false));
        aTextField = new JTextField();
        aTextField.setText("0");
        panel2.add(aTextField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(34, 21), null, 0, false));
        bTextField = new JTextField();
        bTextField.setText("1");
        panel2.add(bTextField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(34, 21), null, 0, false));
        nTextField = new JTextField();
        nTextField.setText("10");
        panel2.add(nTextField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(34, 21), null, 0, false));
        upaTextField = new JTextField();
        panel2.add(upaTextField, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(34, 21), null, 0, false));
        upbTextField = new JTextField();
        panel2.add(upbTextField, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(34, 21), null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("a=");
        panel2.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("b=");
        panel2.add(label2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("n=");
        panel2.add(label3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("war. brzeg. a=");
        panel2.add(label4, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("war. brzeg. b=");
        panel2.add(label5, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        errorCheckBox = new JCheckBox();
        errorCheckBox.setText("Funkcja dok³adna i b³êdy");
        panel1.add(errorCheckBox, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        oldFunctions = new JCheckBox();
        oldFunctions.setSelected(true);
        oldFunctions.setText("Uwzglêdniaj wczeœniejsze funkcje");
        panel1.add(oldFunctions, new GridConstraints(9, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        helpButton = new JButton();
        helpButton.setText("Pomoc");
        panel1.add(helpButton, new GridConstraints(11, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panel1.add(spacer3, new GridConstraints(12, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
    }

    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }
}
