package femProject.UI;

import femProject.Function.FunctionInterface;
import femProject.Dirichlet.Dirichlet;
import femProject.Neumann.Neumann;

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
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private ResultForm resultForm;

    public MainMenu() {
        setContentPane(contentPane);
        resultForm = new ResultForm();


        warunkiDirichletaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Dirichlet dirichlet = new Dirichlet();
                //dirichlet
            }
        });

        warunkiNeumannaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Neumann neumann = new Neumann();
                //dirichlet
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
}
