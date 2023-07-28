package ui;

import util.Globals;

import javax.swing.*;

public class MainWindow extends JFrame {
    private JPanel mainPanel;
    private JPanel gamePanel;
    private JPanel menuPanel;
    private JButton button1;
    private JButton button2;

    public static void main(String[] args) {
        MainWindow window = new MainWindow("Prueba");
    }

    public MainWindow(String title) {

        super(title);
        this.add(mainPanel);
        this.menuPanel.setSize(Globals.MENU_DIMENSION);
        this.menuPanel.setPreferredSize(Globals.MENU_DIMENSION);
        this.menuPanel.setMinimumSize(Globals.MENU_DIMENSION);
        this.menuPanel.setMaximumSize(Globals.MENU_DIMENSION);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void createUIComponents() {
        gamePanel = new GamePanel(this);
    }
}
