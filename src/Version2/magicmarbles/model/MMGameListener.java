package Version2.magicmarbles.model;

import java.util.EventListener;

public interface MMGameListener extends EventListener {
    void FieldChanged(MMGameEvent evt);
}
