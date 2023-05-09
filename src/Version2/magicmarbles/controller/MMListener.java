package Version2.magicmarbles.controller;

import java.util.EventListener;

public interface MMListener extends EventListener {
    void fieldChanged(MMEvent evt);
}
