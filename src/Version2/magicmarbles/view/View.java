package Version2.magicmarbles.view;

import javafx.scene.shape.Circle;

import javax.swing.*;
import java.awt.*;
import java.lang.invoke.VolatileCallSite;

public class View {

    JFrame window = new JFrame();
    JMenuBar menuBar = new JMenuBar();
    JMenu Itm_file = new JMenu("File");
    JMenu Itm_settings = new JMenu("Settings");
    JMenuItem SubItm_newGame = new JMenuItem("New Game");
    JMenuItem SubItm_exit = new JMenuItem("Exit");
    JMenuItem SubItm_resolution = new JMenuItem("Resolution");
    JPanel panel = new JPanel();


    public View(){
        start();
    }

    private void start()
    {
        window.setTitle("MagicMarbles");
        window.setSize(500,500);
        window.setVisible(true);
        window.setResizable(false);
        window.setLocation(100,100);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        menuBar.add(Itm_file);
        menuBar.add(Itm_settings);
        Itm_file.add(SubItm_newGame);
        Itm_file.add(SubItm_exit);
        Itm_settings.add(SubItm_resolution);
        window.setJMenuBar(menuBar);

        Container container = window.getContentPane();
        container.setLayout(new BorderLayout());

        container.add(panel,BorderLayout.NORTH);

        Graphics g = panel.getGraphics();
        g.setColor(new Color(0x00FF00));
        g.fillOval(100,100,100,100);
        g.setColor(new Color(0xFF0000));
        g.fillOval(200,200, 100,100);
        g.setColor(new Color(0x0000FF));
        g.fillOval(100,300,100,100);


    }

    public void paint(Graphics g)
    {


    }

    public static void main(String[] args) {
    View app = new View();
    }
}
