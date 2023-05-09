package Version2.magicmarbles.view;

import javafx.scene.shape.Circle;

import javax.swing.*;
import java.awt.*;
import java.lang.invoke.VolatileCallSite;

public class View extends JFrame {

    //JFrame window = new JFrame();
    JMenuBar menuBar = new JMenuBar();
    JMenu Itm_file = new JMenu("File");
    JMenu Itm_settings = new JMenu("Settings");
    JMenuItem SubItm_newGame = new JMenuItem("New Game");
    JMenuItem SubItm_exit = new JMenuItem("Exit");
    JMenuItem SubItm_resolution = new JMenuItem("Resolution");
    JPanel game_field = new JPanel();
    JPanel score_panel = new JPanel();
    JLabel score = new JLabel();


    public View(){
        start();
    }

    private void start()
    {
        setTitle("MagicMarbles");
        setSize(300,300);
        setVisible(true);
        setResizable(false);
        setLocation(100,100);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        score_panel.setBackground(Color.LIGHT_GRAY);
        game_field.setBackground(Color.BLACK);
        score.setText("Hello World!");


        menuBar.add(Itm_file);
        menuBar.add(Itm_settings);
        Itm_file.add(SubItm_newGame);
        Itm_file.add(SubItm_exit);
        Itm_settings.add(SubItm_resolution);
        setJMenuBar(menuBar);

        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        container.add(score_panel,BorderLayout.SOUTH);
        container.add(game_field,BorderLayout.CENTER);
        score_panel.add(score);

        setBackground(Color.BLACK);


    }

    public void paint(Graphics g)
    {
        super.paint(g);

        for (int i = 0; i < 5; i++) {
            for (int i1 = 0; i1 < 7; i1++) {
                g.setColor(new Color(0xFF0000));
                g.fillOval((10+(i1*40)),(60+(i*40)),40,40);
            }
        }

        /*
        g.setColor(Color.RED);
        g.fillOval(10,60,40,40);
        g.setColor(Color.GREEN);
        g.fillOval(50,60, 40,40);
        g.setColor(Color.BLUE);
        g.fillOval(90,60,40,40);
        */




    }

    public static void main(String[] args) {
    View app = new View();
    }
}
