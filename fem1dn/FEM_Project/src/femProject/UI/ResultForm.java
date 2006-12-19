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
    private JPanel errors;
    private JList argList;
    private JList valList;
    private JScrollBar scrollBar1;
    private JScrollPane scrollPane1;
    private JScrollPane scrollPane2;
    private JList fvalList;
    private JList errorList;
    private JScrollPane scrollPane3;
    private JScrollPane scrollPane4;
    private DefaultListModel argListModel;
    private DefaultListModel valListModel;
    
    private static final int WIDTH = 500,
            HEIGHT = 300;
    private DefaultListModel errListModel;
    private DefaultListModel fvalListModel;

    private void setAllListSelections(int val){
        valList.setSelectedIndex(val);
        valList.ensureIndexIsVisible(val);
        argList.setSelectedIndex(val);
        argList.ensureIndexIsVisible(val);
        if(fvalList.isEnabled()){
            errorList.setSelectedIndex(val);
            errorList.ensureIndexIsVisible(val);
            fvalList.setSelectedIndex(val);
            fvalList.ensureIndexIsVisible(val);
        }

    }
    public ResultForm() {
        super();
        this.setContentPane(panel1);
        this.setSize(WIDTH, HEIGHT);
        panel1.addKeyListener(drawingPanel);
        tabbedPane1.addKeyListener(drawingPanel);
        this.addKeyListener(drawingPanel);


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
        drawingPanel.addFunction(tab,col);       
    }
    public void setFunctionLists(float[][] tab){
        argListModel.clear();
        valListModel.clear();
        errListModel.clear();
        errorList.setEnabled(false);
        fvalListModel.clear();
        fvalList.setEnabled(false);

        for(int i=0; i < tab[0].length; i++){
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

    public void setFunctionLists(float[][] tab, float[] fval){
        argListModel.clear();
        valListModel.clear();
        errListModel.clear();
        fvalListModel.clear();
        errorList.setEnabled(true);        
        fvalList.setEnabled(true);

        for(int i=0; i < tab[0].length; i++){
            argListModel.addElement(tab[0][i]);
            valListModel.addElement(tab[1][i]);
            fvalListModel.addElement(fval[i]);
            errListModel.addElement(fval[i]-tab[1][i]);

        }
        scrollBar1.setMaximum(tab[0].length);
    }

    
}
