package arezzo.vue;

import arezzo.monde.Arezzo;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class VueTitre implements Observateur{
    private Arezzo arezzo ;
    @FXML
    private Label titre ;
    public VueTitre(Arezzo arezzo){
        this.arezzo = arezzo ;
        arezzo.ajouterObservateur(this);
    }

    public void OnRenomerSelected(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Donner un Titre ");
        dialog.setHeaderText("Entrez votre Titre : ");
        dialog.setContentText("Titre : ");
        Optional<String> result = dialog.showAndWait();

        result.ifPresent(name -> {
            titre.setText(result.get());

        });



    }

    @Override
    public void reagir() {

    }

    public void OnQuitterSelected(ActionEvent actionEvent) {
        arezzo.getPartition().close();
        Platform.exit();
    }

    public void OnNouveauSelected(ActionEvent actionEvent) {
        arezzo.buttonReset();
        titre.setText("Sans Titre");
    }

    public void OnTransposerSelected(ActionEvent actionEvent) {
        arezzo.transposer("grave");
    }

    public void OnEnregistrerSelected(ActionEvent actionEvent) {
        arezzo.Enregistrer();
    }

    public void OnOuvrirSelected(ActionEvent actionEvent) {
        arezzo.ouvrir();
        titre.setText(arezzo.getTitre());
    }

    public void OnversAiguSelected(ActionEvent actionEvent) {
        arezzo.transposer("aigu");
    }
}
