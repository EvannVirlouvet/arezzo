package arezzo.vue.ecouteur;

import arezzo.monde.Arezzo;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class EcouteurEffacer implements EventHandler<ActionEvent> {

    private Arezzo arezzo ;

    private ObservableList<String> note ;

    public EcouteurEffacer(ObservableList<String> note, Arezzo arezzo){
        this.note = note ;
        this.arezzo = arezzo ;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        for(int i = 0; i< arezzo.getListeNote().size() ; i++ ){
            String rechercheNote = i+1+" "+arezzo.getListeNote().get(i).getOctave()+" "+arezzo.getListeNote().get(i).getDuree()+" "+arezzo.getListeNote().get(i).traduction() ;
            for (int j = 0 ;j < note.size() ; j++) {
                if (rechercheNote.equals(note.get(j))) {
                    arezzo.effacer(arezzo.getListeNote().get(i));
                }
            }
        }
    }
}
