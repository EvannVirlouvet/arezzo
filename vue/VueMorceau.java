package arezzo.vue;

import arezzo.monde.Arezzo;
import arezzo.vue.ecouteur.EcouteurFermer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;


public class VueMorceau implements Observateur{

    private Arezzo arezzo;
    @FXML
    private ImageView image;

    public VueMorceau(Arezzo arezzo){
        this.arezzo = arezzo ;
        arezzo.ajouterObservateur(this);
    }

    public void reagir(){

        this.image.setImage(arezzo.getPartition().getImage());

    }


    public void OnlesNotesSelected(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage() ;
        if (arezzo.getNbFenetre() < 1){
            BorderPane borderPane =new BorderPane();

            FXMLLoader note = new FXMLLoader();
            note.setLocation(getClass().getResource("VueCompositeur.fxml"));
            note.setControllerFactory(iC->new VueCompositeur(arezzo));
            borderPane.setCenter(note.load());

            Scene scene = new Scene(borderPane, 200, 400);
            stage.setTitle("Les notes");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            arezzo.setNbFenetre(arezzo.getNbFenetre()+1);
        }
        stage.setOnCloseRequest(new EcouteurFermer(arezzo));
        arezzo.fenetreExiste() ;
    }

    public void agrandir(){

    }
}
