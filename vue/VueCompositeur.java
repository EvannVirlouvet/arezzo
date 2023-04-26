package arezzo.vue;

import arezzo.monde.Arezzo;
import arezzo.vue.ecouteur.EcouteurDescendre;
import arezzo.vue.ecouteur.EcouteurEffacer;
import arezzo.vue.ecouteur.EcouteurMonter;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;



public class VueCompositeur implements Observateur{
    private Arezzo arezzo ;

    private ContextMenu contextMenu ;

    private MenuItem effacer,monter,descendre ;

    @FXML
    private ListView<String> compositeur ;

    public VueCompositeur(Arezzo arezzo){
        this.arezzo = arezzo ;
        contextMenu = new ContextMenu() ;
        arezzo.ajouterObservateur(this);
        effacer = new MenuItem("Effacer");
        monter = new MenuItem("Monter d'1/2 ton");
        descendre = new MenuItem("Descendre d'1/2 ton");

        contextMenu.getItems().addAll(effacer, monter, descendre);

    }

    @Override
    public void reagir() {
        compositeur.getItems().clear();
        for (int i = 0 ; i < arezzo.getListeNote().size() ; i++){
            compositeur.getItems().add(i+1+" "+arezzo.getListeNote().get(i).getOctave()+" "+arezzo.getListeNote().get(i).getDuree()+" "+arezzo.getListeNote().get(i).traduction()) ;
        }
        compositeur.getSelectionModel()
                .setSelectionMode(SelectionMode.MULTIPLE);


    }

    public void OnContextActivate(ContextMenuEvent contextMenuEvent) {
        effacer.setOnAction(new EcouteurEffacer(compositeur.getSelectionModel().getSelectedItems(), arezzo));
        monter.setOnAction(new EcouteurMonter(compositeur.getSelectionModel().getSelectedItems(), arezzo));
        descendre.setOnAction(new EcouteurDescendre(compositeur.getSelectionModel().getSelectedItems(), arezzo));

            contextMenu.show(compositeur, contextMenuEvent.getScreenX(), contextMenuEvent.getScreenY());


    }
}
