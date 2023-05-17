package Version2.magicmarbles.view;

import Version2.magicmarbles.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class MMPanel extends JPanel {

    private int diameter;
    private int TODO_width;
    private int TODO_height;
    private MMGameModel model;

    public MMPanel(int radius, MMGameModel model) {
        super();
        this.model = model;
        this.TODO_height = model.getHeight();
        this.TODO_width = model.getWidth();
        diameter = radius * 2;
        setPlayground();
        this.addMouseListener(new MMController()); 
    }

    public void setPlayground() {

        setPreferredSize(new Dimension(TODO_width * diameter, TODO_height * diameter));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        for (int row = 0; row < TODO_height; row++) {
        	for (int col = 0; col < TODO_width; col++) {
                Color c = Color.BLACK;

                if(model.getFieldState(row,col) == MMFieldState.BLUE) c = Color.BLUE;
                else if (model.getFieldState(row,col) == MMFieldState.RED) c = Color.RED;
                else if (model.getFieldState(row,col) == MMFieldState.GREEN) c = Color.GREEN;



                g.setColor(c);
                int[] pixelPos = PixelHelper.convertIndexToPixel(col, row);
                g.fillOval(pixelPos[0], pixelPos[1], diameter, diameter);
            }
        }
    }

    private final MMGameListener GameListener = new MMGameListener() {
        @Override
        public void FieldChanged(MMGameEvent e) {

            repaint();
        }
    };

    class MMController extends MouseAdapter {
 
        @Override
        public void mouseClicked(MouseEvent e) {
            int[] tmp = PixelHelper.convertPixelToIndex(e.getX(), e.getY());
            model.select(tmp[1], tmp[0]);
        }
    }
}
