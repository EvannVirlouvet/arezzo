package arezzo;

import arezzo.monde.Arezzo;
import arezzo.vue.VueOutils;
import arezzo.vue.VueMorceau;
import arezzo.vue.VueTitre;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override

    public void start(Stage stage) throws IOException {
        Arezzo arezzo = new Arezzo() ;
        BorderPane borderPane = new BorderPane() ;
        borderPane.setStyle("-fx-background-color : #f0f3e8;");


        FXMLLoader outils = new FXMLLoader();
        outils.setLocation(getClass().getResource("vue/VueOutils.fxml"));
        outils.setControllerFactory(iC->new VueOutils(arezzo));
        borderPane.setBottom(outils.load());

        FXMLLoader morceau = new FXMLLoader();
        morceau.setLocation(getClass().getResource("vue/VueMorceaux.fxml"));
        morceau.setControllerFactory(iC->new VueMorceau(arezzo));
        borderPane.setCenter(morceau.load());

        FXMLLoader titre = new FXMLLoader();
        titre.setLocation(getClass().getResource("vue/VueTitre.fxml"));
        titre.setControllerFactory(iC->new VueTitre(arezzo));
        borderPane.setTop(titre.load());


        Scene scene = new Scene(borderPane, 1280, 720);
        stage.setTitle("Arezzo");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}