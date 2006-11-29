package femProject.Function;
import de.olikurt.parser.Variable;
import de.olikurt.parser.Function;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;

/**
 * Created by IntelliJ IDEA.
 * User: zagi
 * Date: 2006-11-13
 * Time: 17:03:51
 * To change this template use File | Settings | File Templates.
 */
public class FunctionInterface {
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
    private JButton editButton;
    private JTextField rangeBegTextField;
    private DefaultListModel functionListModel;
    private DefaultListModel rangeListModel;


    public FunctionInterface() {
        leftBrBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if (leftBrBtn.getText().compareTo("(") == 0) leftBrBtn.setText("[");
                else leftBrBtn.setText("(");

            }
        });
        rightBrBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if (rightBrBtn.getText().compareTo(")") == 0) rightBrBtn.setText("]");
                else rightBrBtn.setText(")");
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
                if (checkFunction()) {
                    String f = functionTextField.getText(),
                            a = rangeBegTextField.getText(),
                            b = rangeEndTextField.getText();

                    functionListModel.addElement(f);
                    rangeListModel.addElement(leftBrBtn.getText() + a + ";" + b + rightBrBtn.getText());


                }
            }

        });

        
        functionList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                rangeList.setSelectedIndex(functionList.getSelectedIndex());
            }
        });
        rangeList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                functionList.setSelectedIndex(rangeList.getSelectedIndex());
            }
        });

       
    }

    private boolean checkFunction() {
        return true;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("FunctionInterface");
        FunctionInterface fi = new FunctionInterface();

        frame.setContentPane(fi.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        frame.setVisible(true);
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
        finishButton.setText("Zako�cz");
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
        addButton.setText("Dodaj/Zmie�");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel3.add(addButton, gbc);
        editButton = new JButton();
        editButton.setText("Edytuj");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        panel3.add(editButton, gbc);
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
}
