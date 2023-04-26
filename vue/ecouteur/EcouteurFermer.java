package arezzo.vue.ecouteur;

import arezzo.monde.Arezzo;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

public class EcouteurFermer implements EventHandler<WindowEvent> {
    private Arezzo arezzo ;

    public EcouteurFermer(Arezzo arezzo){
        this.arezzo = arezzo ;
    }

    @Override
    public void handle(WindowEvent windowEvent) {
        arezzo.setNbFenetre(0);
    }
}
