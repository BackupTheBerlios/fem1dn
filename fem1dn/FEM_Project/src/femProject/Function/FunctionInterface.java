package femProject.Function;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: zagi
 * Date: 2006-11-13
 * Time: 17:03:51
 * To change this template use File | Settings | File Templates.
 */
public class FunctionInterface extends JDialog {
    private static String INFINITY_SYMBOL = "\u221E";
    private JPanel panel1;
    private JTextField functionTextField;
    private JButton leftBrBtn;
    private JButton rightBrBtn;
    private JTextField rangeEndTextField;
    private JButton leftInfBtn;
    private JButton rightInfBtn;
    private JList functionList;
    private JList rangeList;
    private JButton finishButton;
    private JButton addButton;
    private JButton deleteButton;
    private JTextField rangeBegTextField;
    private JTextArea textArea1;
    private JLabel functionName;
    private JButton prevButton;
    private JButton nextButton;
    private DefaultListModel functionListModel;
    private DefaultListModel rangeListModel;
    private femProject.Function.Function function;
    private StoredFunction textFunction;
    private boolean inclBeg, inclEnd;
    private Vector vect;
    private ArrayList<StoredFunction> storedFunctions;
    private int currStoredFunctionIndx;
    private JDialog dial;


    private static final int WIDTH = 450,
                                HEIGHT = 300;

   
    public FunctionInterface() {

        textFunction = new StoredFunction();
        inclBeg = false;
        inclEnd = false;
        this.setModal(true);
        this.setContentPane(panel1);
        this.setSize(WIDTH, HEIGHT);
        this.setUndecorated(false);
        storedFunctions = new ArrayList<StoredFunction>();
        currStoredFunctionIndx = 0;
        dial = this;


        leftBrBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if (leftBrBtn.getText().compareTo("(") == 0) {
                    leftBrBtn.setText("[");
                    inclBeg = true;
                } else {
                    leftBrBtn.setText("(");
                    inclBeg = false;
                }

            }
        });
        rightBrBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if (rightBrBtn.getText().compareTo(")") == 0) {
                    rightBrBtn.setText("]");
                    inclEnd = true;
                } else {
                    rightBrBtn.setText(")");
                    inclEnd = false;
                }
            }
        });
        leftInfBtn.setText("-" + INFINITY_SYMBOL);
        rightInfBtn.setText("+" + INFINITY_SYMBOL);
        leftInfBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                rangeBegTextField.setText("-" + INFINITY_SYMBOL);

            }
        });
        rightInfBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                rangeEndTextField.setText("+" + INFINITY_SYMBOL);

            }
        });
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                String f = functionTextField.getText(),
                        a = rangeBegTextField.getText(),
                        b = rangeEndTextField.getText();
                if (!checkFunction(f, a, b))
                    JOptionPane.showMessageDialog(dial,"B³êdna funkcja");

            }

        });
        functionList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                rangeList.setSelectedIndex(functionList.getSelectedIndex());
                fillTextFields();
            }
        });
        rangeList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                functionList.setSelectedIndex(rangeList.getSelectedIndex());
                fillTextFields();
            }
        });
        finishButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {               
                if(textFunction.isRangeOk()){
                    try {                  
                        function = new femProject.Function.Function(textFunction);
                         setVisible(false);
                    } catch (Exception e) {

                    }

                }else{
                    JOptionPane.showMessageDialog(dial,"Funkcja nie jest poprawnie okre¶lona na przedziale.");
                }


            }
        });
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                int indx = rangeList.getSelectedIndex();
                if (indx >= 0 && indx <= textFunction.getSize()) {
                    textFunction.remove(indx);
                    rangeListModel.removeElementAt(indx);
                    functionListModel.removeElementAt(indx);
                    resetLists();
                }
            }
        });
        prevButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if(storedFunctions.size() > 0){
                    currStoredFunctionIndx = ((storedFunctions.size()+currStoredFunctionIndx-1)%storedFunctions.size());
                    textFunction = storedFunctions.get(currStoredFunctionIndx);
                    resetLists();
                }

            }
        });
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if(storedFunctions.size() > 0){
                    currStoredFunctionIndx = (currStoredFunctionIndx+1)%storedFunctions.size();
                    textFunction = storedFunctions.get(currStoredFunctionIndx);
                    resetLists();
                }

            }
        });
    }
    public void setFunctionName(String name){
        this.functionName.setText(name);
        this.setStoredFunctions(StoredFunction.getStoredFunctions(name));
    }
    private void fillTextFields() {
        int indx = functionList.getSelectedIndex();
        if (indx >= 0 && indx < textFunction.getSize()) {
            String fun = textFunction.getFuction(indx);
            Range rng = textFunction.getRanges(indx);
            functionTextField.setText(fun);
            String beg = rng.getBeg() == Float.NEGATIVE_INFINITY ?
                    "-" + INFINITY_SYMBOL : String.valueOf(rng.getBeg());
            String end = rng.getEnd() == Float.POSITIVE_INFINITY ?
                    "+" + INFINITY_SYMBOL : String.valueOf(rng.getEnd());
            rangeBegTextField.setText(beg);
            rangeEndTextField.setText(end);
        }
    }

    public femProject.Function.Function getFunction(){
        return function;
    }

    private void resetLists(){
        functionListModel.clear();
        rangeListModel.clear();

        for(int i=0; i < textFunction.getSize(); i++){
            functionListModel.addElement(textFunction.getFuction(i));
            rangeListModel.addElement(textFunction.getRanges(i).toString());
        }

    
    }
    private boolean checkFunction(String fun, String a, String b) {
        try {
            if (a.length() < 1 || b.length() < 1 || fun.length() < 1) return false;

            float beg, end, val;
            if (a.compareTo("-" + INFINITY_SYMBOL) == 0) beg = Float.NEGATIVE_INFINITY;
            else beg = Float.valueOf(a);

            if (b.compareTo("+" + INFINITY_SYMBOL) == 0) end = Float.POSITIVE_INFINITY;
            else end = Float.valueOf(b);


            if (end < beg || (end == beg && (!inclBeg || !inclEnd))) return false;

            if (beg != Float.NEGATIVE_INFINITY) {
                if (end != Float.POSITIVE_INFINITY)
                    val = (end - beg) / 2;
                else val = beg + 1;
            } else if (end != Float.POSITIVE_INFINITY) val = end - 1;
            else val = 0;


            org.nfunk.jep.JEP myParser = new org.nfunk.jep.JEP();
            myParser.addStandardFunctions();
            myParser.addStandardConstants();

            myParser.addVariable("x", val);
            myParser.parseExpression(fun);
            myParser.getValue();
            
            Range range = new Range(beg, end, inclBeg, inclEnd);
            /*Range currRange;
            for (int i = 0; i < textFunction.getSize(); i++) {
                currRange = textFunction.getRanges(i);
                if (range.intersects(currRange)) return false;
            } */
            textFunction.add(fun,range);
            resetLists();

        } catch (Exception ex) {
            return false;
        }
        return true;
    }


    private void createUIComponents() {
        functionListModel = new DefaultListModel();
        rangeListModel = new DefaultListModel();
        functionList = new JList(functionListModel);
        rangeList = new JList(rangeListModel);

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
        panel1.setLayout(new GridBagLayout());
        final JLabel label1 = new JLabel();
        label1.setText("f(x) =");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.EAST;
        panel1.add(label1, gbc);
        final JLabel label2 = new JLabel();
        label2.setText("   dla x  ");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.EAST;
        panel1.add(label2, gbc);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        panel1.add(panel2, gbc);
        leftBrBtn = new JButton();
        leftBrBtn.setText("(");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel2.add(leftBrBtn, gbc);
        rightBrBtn = new JButton();
        rightBrBtn.setText(") ");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel2.add(rightBrBtn, gbc);
        rangeBegTextField = new JTextField();
        rangeBegTextField.setHorizontalAlignment(0);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel2.add(rangeBegTextField, gbc);
        rangeEndTextField = new JTextField();
        rangeEndTextField.setHorizontalAlignment(0);
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel2.add(rangeEndTextField, gbc);
        rightInfBtn = new JButton();
        rightInfBtn.setText("N ");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        panel2.add(rightInfBtn, gbc);
        leftInfBtn = new JButton();
        leftInfBtn.setText("N");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel2.add(leftInfBtn, gbc);
        finishButton = new JButton();
        finishButton.setText("Zakoñcz");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.NORTH;
        panel1.add(finishButton, gbc);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(panel3, gbc);
        addButton = new JButton();
        addButton.setText("Dodaj/Zmieñ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel3.add(addButton, gbc);
        deleteButton = new JButton();
        deleteButton.setText("Edytuj");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        panel3.add(deleteButton, gbc);
        final JPanel spacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel3.add(spacer1, gbc);
        final JScrollPane scrollPane1 = new JScrollPane();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        panel1.add(scrollPane1, gbc);
        functionList.setSelectionMode(0);
        scrollPane1.setViewportView(functionList);
        final JScrollPane scrollPane2 = new JScrollPane();
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        panel1.add(scrollPane2, gbc);
        rangeList.setSelectionMode(0);
        scrollPane2.setViewportView(rangeList);
        functionTextField = new JTextField();
        functionTextField.setColumns(0);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(functionTextField, gbc);
    }

    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

    public void setStoredFunctions(ArrayList<StoredFunction> storedFunctions) {
        this.storedFunctions = storedFunctions;
    }
}
