package femProject.UI;

import femProject.Function.Function;
import femProject.Drawing.DrawingPanel;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.AdjustmentListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.ComponentAdapter;
import java.util.ArrayList;

import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.Spacer;

/**
 * Created by IntelliJ IDEA.
 * User: zagi
 * Date: 2006-12-04
 * Time: 13:26:46
 * To change this template use File | Settings | File Templates.
 */
public class ResultForm extends JFrame {
    private JTabbedPane tabbedPane1;

    private JFrame drawingFrame;
    private JPanel panel1;
    private DrawingPanel drawingPanel;
    private JList argList;
    private JList valList;
    private JScrollBar scrollBar1;
    private JList fvalList;
    private JList errorList;
    private JLabel errorLabel;
    private JPanel errors;
    private JScrollPane scrollPane1;
    private JScrollPane scrollPane2;
    private JScrollPane scrollPane3;
    private JScrollPane scrollPane4;
    private DefaultListModel argListModel;
    private DefaultListModel valListModel;

    private static final int WIDTH = 550,
            HEIGHT = 300;
    private DefaultListModel errListModel;
    private DefaultListModel fvalListModel;
    private float error = 0;

    private void setAllListSelections(int val) {
        valList.setSelectedIndex(val);
        valList.ensureIndexIsVisible(val);
        argList.setSelectedIndex(val);
        argList.ensureIndexIsVisible(val);
        if (fvalList.isEnabled()) {
            errorList.setSelectedIndex(val);
            errorList.ensureIndexIsVisible(val);
            fvalList.setSelectedIndex(val);
            fvalList.ensureIndexIsVisible(val);
        }

    }

    public ResultForm() {
        super();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int posX = (int) (dim.getWidth() / 2) - WIDTH / 2;

        drawingFrame = new JFrame();
        drawingFrame.setSize(WIDTH, HEIGHT);
        drawingFrame.setLocation(posX, 0);
        this.setLocation(posX, HEIGHT);
        drawingPanel = new DrawingPanel();
        drawingFrame.addKeyListener(drawingPanel);
        drawingFrame.setContentPane(drawingPanel);
        drawingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        this.setContentPane(panel1);
        this.setSize(WIDTH, HEIGHT);
        // panel1.addKeyListener(drawingPanel);
        //tabbedPane1.addKeyListener(drawingPanel);
        //this.addKeyListener(drawingPanel);
        this.setFocusable(false);


        argList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                setAllListSelections(argList.getSelectedIndex());
            }
        });
        valList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                setAllListSelections(valList.getSelectedIndex());

            }
        });
        errorList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                setAllListSelections(errorList.getSelectedIndex());

            }
        });
        scrollBar1.addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent adjustmentEvent) {
                setAllListSelections(adjustmentEvent.getValue());


            }
        });
        fvalList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                setAllListSelections(fvalList.getSelectedIndex());
            }
        });


    }

    public void addFunction(Color col, float[][] tab) throws Exception {
        drawingPanel.addFunction(tab, col);
    }

    public void showResults() {
        this.setVisible(true);
        drawingFrame.setVisible(true);
    }

    public void setFunctionLists(float[][] tab) {
        argListModel.clear();
        valListModel.clear();
        errListModel.clear();
        errorList.setEnabled(false);
        fvalListModel.clear();
        fvalList.setEnabled(false);

        for (int i = 0; i < tab[0].length; i++) {
            argListModel.addElement(tab[0][i]);
            valListModel.addElement(tab[1][i]);

        }
        scrollBar1.setMaximum(tab[0].length);
    }

    private void createUIComponents() {
        argListModel = new DefaultListModel();
        valListModel = new DefaultListModel();
        errListModel = new DefaultListModel();
        fvalListModel = new DefaultListModel();

        argList = new JList(argListModel);
        valList = new JList(valListModel);
        errorList = new JList(errListModel);
        fvalList = new JList(fvalListModel);

    }

    public void refresh() {
        drawingPanel.computeBoundary();

        drawingPanel.repaint();
    }

    public void setFunctionLists(float[][] tab, float[] fval) {
        argListModel.clear();
        valListModel.clear();
        errListModel.clear();
        fvalListModel.clear();
        errorList.setEnabled(true);
        fvalList.setEnabled(true);

        for (int i = 0; i < tab[0].length; i++) {
            argListModel.addElement(tab[0][i]);
            valListModel.addElement(tab[1][i]);
            fvalListModel.addElement(fval[i]);
            errListModel.addElement(fval[i] - tab[1][i]);

        }
        scrollBar1.setMaximum(tab[0].length);
    }

    public float getError() {
        return error;
    }

    public void setError(float error) {
        this.error = error;
        this.errorLabel.setText("Blad œredniokwadratowy wynosi =" + error);
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
        createUIComponents();
        panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        errors = new JPanel();
        errors.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(errors, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(2, 6, new Insets(0, 0, 0, 0), -1, -1));
        errors.add(panel2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setHorizontalAlignment(0);
        label1.setText("Wartoœci wêz³ów");
        panel2.add(label1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setHorizontalAlignment(0);
        label2.setText("Wartoœci przybli¿ona");
        panel2.add(label2, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        scrollPane1 = new JScrollPane();
        scrollPane1.setHorizontalScrollBarPolicy(30);
        scrollPane1.setVerticalScrollBarPolicy(21);
        panel2.add(scrollPane1, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        argList.setSelectionMode(0);
        scrollPane1.setViewportView(argList);
        scrollPane2 = new JScrollPane();
        scrollPane2.setHorizontalScrollBarPolicy(30);
        scrollPane2.setVerticalScrollBarPolicy(21);
        panel2.add(scrollPane2, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        valList.setSelectionMode(0);
        scrollPane2.setViewportView(valList);
        scrollPane3 = new JScrollPane();
        scrollPane3.setHorizontalScrollBarPolicy(30);
        scrollPane3.setVerticalScrollBarPolicy(21);
        panel2.add(scrollPane3, new GridConstraints(1, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        errorList.setSelectionMode(0);
        scrollPane3.setViewportView(errorList);
        scrollPane4 = new JScrollPane();
        scrollPane4.setHorizontalScrollBarPolicy(31);
        scrollPane4.setVerticalScrollBarPolicy(21);
        panel2.add(scrollPane4, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        fvalList.setSelectionMode(0);
        scrollPane4.setViewportView(fvalList);
        final JLabel label3 = new JLabel();
        label3.setHorizontalAlignment(0);
        label3.setText("Wartoœæ dok³adna");
        panel2.add(label3, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setHorizontalAlignment(0);
        label4.setHorizontalTextPosition(0);
        label4.setText("B³¹d");
        label4.setVerticalAlignment(0);
        panel2.add(label4, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(124, 15), null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel2.add(spacer1, new GridConstraints(1, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        scrollBar1 = new JScrollBar();
        scrollBar1.setValueIsAdjusting(false);
        panel2.add(scrollBar1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        errorLabel = new JLabel();
        errorLabel.setText("");
        errors.add(errorLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }
}
