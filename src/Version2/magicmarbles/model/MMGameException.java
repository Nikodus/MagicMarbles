package Version2.magicmarbles.model;

import javax.swing.*;

public class MMGameException {
    public MMGameException()
    {
        JOptionPane.showMessageDialog(null,"Invalid Move","Error", JOptionPane.ERROR_MESSAGE);
    }
}
