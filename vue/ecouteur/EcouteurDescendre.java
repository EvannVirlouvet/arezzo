package arezzo.vue.ecouteur;

import arezzo.monde.Arezzo;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class EcouteurDescendre implements EventHandler<ActionEvent> {

    private ObservableList<String> note ;
    private Arezzo arezzo ;

    public EcouteurDescendre(ObservableList<String> note, Arezzo arezzo){
        this.note = note ;
        this.arezzo = arezzo ;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        for(int i = 0; i< arezzo.getListeNote().size() ; i++ ){
            String rechercheNote = i+1+" "+arezzo.getListeNote().get(i).getOctave()+" "+arezzo.getListeNote().get(i).getDuree()+" "+arezzo.getListeNote().get(i).traduction() ;
            for (int j = 0 ;j < note.size() ; j++) {
                if (rechercheNote.equals(note.get(j))) {
                    arezzo.transposerNote(arezzo.getListeNote().get(i), "grave");
                }
            }
        }
    }
}
