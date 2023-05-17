package Version2.magicmarbles.model;

import java.util.ArrayList;
import java.util.List;

public class MMGameModel {

    private MMGameImpl Game;
    private final List<MMGameListener> listeners = new ArrayList<>();

    public MMGameModel(int width, int height)
    {

        this.Game = new MMGameImpl(width,height);
    }

    public void reset(int width, int height)
    {
        this.Game = new MMGameImpl(width,height);
        Game.setGamePoints(0);
        fieldChangeEvent();
    }

    public MMGame getGame() {
        return Game;
    }

    public int getHeight(){
        return Game.getHeight();
    }

    public int getWidth(){
        return Game.getWidth();
    }

    public MMFieldState getFieldState(int row, int col)
    {
        return Game.getFieldState(row,col);
    }

    public void setFieldState(int row, int col, MMFieldState state){
        Game.setFieldState(row,col,state);
        fieldChangeEvent();
    }

    public void addMMGameListener(MMGameListener listener) {
        listeners.add(listener);
    }

    public void removeMMGameListener(MMGameListener listener) {
        listeners.remove(listener);
    }
    public void select(int row, int col)
    {
        try {
            Game.select(row, col);
            fieldChangeEvent();
        }catch (MMException e) {
            new MMGameException();
        }
    }

    public int getScore(){
        return Game.getGamePoints();
    }
    public MMState getGameState()
    {
        return Game.getGameState();
    }

    private void fieldChangeEvent() {
        MMGameEvent evt = new MMGameEvent(this);
        for (MMGameListener l : listeners) {
            l.FieldChanged(evt);
        }
    }

}
