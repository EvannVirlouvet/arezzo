package arezzo.vue;

import arezzo.exceptions.TempsIncorrectException;
import arezzo.monde.Arezzo;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import java.util.Timer;
import java.util.TimerTask;


public class VueOutils implements Observateur{
    private Arezzo arezzo ;

    private int click ;

    @FXML
    private Slider volume ;
    @FXML
    private Slider tempo ;
    @FXML
    private Button dodo, re, la, mi, fa,sol ,si, doNoire,laNoire, faNoire,reNoire,solNoire;

    @FXML
    private RadioButton aigu, grave, medium ;


    public VueOutils(Arezzo arezzo) {
        this.arezzo = arezzo ;
        arezzo.ajouterObservateur(this);
        dodo = new Button() ;
        re = new Button() ;
        mi = new Button() ;
        fa = new Button() ;
        sol = new Button() ;
        la = new Button() ;
        si = new Button() ;
        doNoire = new Button() ;
        reNoire = new Button() ;
        laNoire = new Button() ;
        faNoire = new Button() ;
        solNoire = new Button() ;
        click = 0 ;
    }
    public void onDoButtonClick(ActionEvent actionEvent) {
        try{
            arezzo.boutonClavier("C");
        }catch (TempsIncorrectException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(e.getMessage());
            alert.setHeaderText("Impossible d'ajouter ce temps");
            alert.show();
            PauseTransition pauseTransition = new PauseTransition(Duration.millis(3000));
            pauseTransition.setOnFinished( event -> alert.close());
            pauseTransition.play();
        }
    }
    public void onPLayButtonClick(ActionEvent actionEvent) {
        arezzo.jouerMusique();
    }

    public void onReButtonClick(ActionEvent actionEvent) {
        try{
            arezzo.boutonClavier("D");
        }catch (TempsIncorrectException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(e.getMessage());
            alert.setHeaderText("Impossible d'ajouter ce temps");
            alert.show();
            PauseTransition pauseTransition = new PauseTransition(Duration.millis(3000));
            pauseTransition.setOnFinished( event -> alert.close());
            pauseTransition.play();
        }
    }

    public void animation(Button e, String octave) {
        if(octave.equals("aigu")){
            click= 1 ;
        }
        if(octave.equals("grave")){
            click = 3 ;
        }
        if(octave.equals("medium")){
            click = 2 ;
        }
        Duration duration = Duration.seconds(2) ;
        TranslateTransition translateTransition = new TranslateTransition(duration,e) ;
        translateTransition.setByX(500);
        translateTransition.setAutoReverse(true); ;
        translateTransition.setCycleCount(2);
        translateTransition.play();
        TimerAnimation timerAnimation = new TimerAnimation(e, octave) ;
        Timer timer = new Timer() ;
        timer.schedule(timerAnimation,2000);

    }

    public class TimerAnimation extends TimerTask {
        private Button b;
        private String octave;

        public TimerAnimation(Button b, String octave) {
            this.b = b;
            this.octave = octave;
        }


        public void run() {
            if (octave.equals("aigu")) {
                b.setStyle("-fx-background-color : #27e127;-fx-border-color : black ;");
            } else if (octave.equals("grave")) {
                b.setStyle("-fx-background-color : #ec3535;-fx-border-color : black;");
            } else {
                if (octave.equals("medium")) {
                    b.setStyle("-fx-background-color : white;-fx-border-color : grey ;");
                }
            }
            Delai delai = new Delai() ;
            Timer timer = new Timer() ;
            timer.schedule(delai, 2000);
        }
    }

    public class Delai extends TimerTask{

        @Override
        public void run() {
            click = 0 ;
        }
    }



    public void reagir(){ }

    public void onMiButtonClick(ActionEvent actionEvent) {
        try{
            arezzo.boutonClavier("E");
        }catch (TempsIncorrectException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(e.getMessage());
            alert.setHeaderText("Impossible d'ajouter ce temps");
            alert.show();
            PauseTransition pauseTransition = new PauseTransition(Duration.millis(3000));
            pauseTransition.setOnFinished( event -> alert.close());
            pauseTransition.play();
        }
    }

    public void onFaButtonClick(ActionEvent actionEvent) {
        try{
            arezzo.boutonClavier("F");
        }catch (TempsIncorrectException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(e.getMessage());
            alert.setHeaderText("Impossible d'ajouter ce temps");
            alert.show();
            PauseTransition pauseTransition = new PauseTransition(Duration.millis(3000));
            pauseTransition.setOnFinished( event -> alert.close());
            pauseTransition.play();
        }
    }

    public void onSolButtonClick(ActionEvent actionEvent) {
        try{
            arezzo.boutonClavier("G");
        }catch (TempsIncorrectException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(e.getMessage());
            alert.setHeaderText("Impossible d'ajouter ce temps");
            alert.show();
            PauseTransition pauseTransition = new PauseTransition(Duration.millis(3000));
            pauseTransition.setOnFinished( event -> alert.close());
            pauseTransition.play();
        }
    }

    public void onLaButtonClick(ActionEvent actionEvent) {
        try{
            arezzo.boutonClavier("A");
        }catch (TempsIncorrectException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(e.getMessage());
            alert.setHeaderText("Impossible d'ajouter ce temps");
            alert.show();
            PauseTransition pauseTransition = new PauseTransition(Duration.millis(3000));
            pauseTransition.setOnFinished( event -> alert.close());
            pauseTransition.play();
        }
    }

    public void onSiButtonClick(ActionEvent actionEvent) {
        try{
            arezzo.boutonClavier("B");
        }catch (TempsIncorrectException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(e.getMessage());
            alert.setHeaderText("Impossible d'ajouter ce temps");
            alert.show();
            PauseTransition pauseTransition = new PauseTransition(Duration.millis(3000));
            pauseTransition.setOnFinished( event -> alert.close());
            pauseTransition.play();
        }
    }

    public void onSilenceButtonClick(ActionEvent actionEvent) {
        try{
            arezzo.boutonSilence();
        }catch (TempsIncorrectException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(e.getMessage());
            alert.setHeaderText("Impossible d'ajouter ce temps");
            alert.show();
            PauseTransition pauseTransition = new PauseTransition(Duration.millis(3000));
            pauseTransition.setOnFinished( event -> alert.close());
            pauseTransition.play();
        }
    }

    public void OnCrocheSelected(ActionEvent actionEvent) { arezzo.setDuree("croche"); }


    public void OnRondeSelected(ActionEvent actionEvent) { arezzo.setDuree("ronde"); }

    public void OnBlancheSelected(ActionEvent actionEvent) { arezzo.setDuree("blanche"); }

    public void OnGraveSelected(ActionEvent actionEvent){
        if(click == 0){
            arezzo.setOctave("grave");
            animation(dodo,"grave");
            animation(re,"grave");
            animation(mi,"grave");
            animation(fa,"grave");
            animation(sol,"grave");
            animation(la,"grave");
            animation(si,"grave");
            animation(doNoire,"changePas");
            animation(laNoire,"changePas");
            animation(solNoire,"changePas");
            animation(reNoire,"changePas");
            animation(faNoire,"changePas");
        }else {
            if (click == 1){
                aigu.setSelected(true);
                grave.setSelected(false);
            }
            if(click == 2){
                medium.setSelected(true);
                grave.setSelected(false);
            }
        }
    }

    public void OnMediumSelected(ActionEvent actionEvent){
        if(click == 0){
            arezzo.setOctave("medium");
            animation(dodo,"medium");
            animation(re,"medium");
            animation(mi,"medium");
            animation(fa,"medium");
            animation(sol,"medium");
            animation(la,"medium");
            animation(si,"medium");
            animation(doNoire,"changePas");
            animation(laNoire,"changePas");
            animation(solNoire,"changePas");
            animation(reNoire,"changePas");
            animation(faNoire,"changePas");
        }else{
            if(click == 1){
                aigu.setSelected(true);
                medium.setSelected(false);
            }if(click == 3){
                grave.setSelected(true);
                medium.setSelected(false);
            }
        }
    }

    public void OnAiguSelected(ActionEvent actionEvent) {
        if(click == 0){
            arezzo.setOctave("aigu");
            animation(dodo,"aigu");
            animation(re,"aigu");
            animation(mi,"aigu");
            animation(fa,"aigu");
            animation(sol,"aigu");
            animation(la,"aigu");
            animation(si,"aigu");
            animation(doNoire,"changePas");
            animation(laNoire,"changePas");
            animation(solNoire,"changePas");
            animation(reNoire,"changePas");
            animation(faNoire,"changePas");
        }else{
            if (click == 3 ){
                grave.setSelected(true);
                aigu.setSelected(false);
            }
            if(click == 2){
                medium.setSelected(true);
                aigu.setSelected(false);
            }
        }
    }

    public void OnNoireSelected(ActionEvent actionEvent) { arezzo.setDuree("noire");}

    public void OnPianoSelected(ActionEvent actionEvent) { arezzo.setInstrument("Piano");}

    public void OnGuitareSelected(ActionEvent actionEvent) { arezzo.setInstrument("Guitare");}

    public void OnTrompetteSelected(ActionEvent actionEvent) { arezzo.setInstrument("Trompette"); }

    public void OnSaxophoneSelected(ActionEvent actionEvent) { arezzo.setInstrument("Saxophone"); ;}

    public void OnVolumeReleased(MouseEvent mouseEvent) { arezzo.setVolume(this.volume.getValue());}

    public void OnTempoReleased(MouseEvent mouseEvent) { arezzo.setTempo(this.tempo.getValue());
    }

    public void onDoNoireButtonClick(ActionEvent actionEvent) {
        try{
            arezzo.boutonClavier("^C");
        }catch (TempsIncorrectException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(e.getMessage());
            alert.setHeaderText("Impossible d'ajouter ce temps");
            alert.show();
            PauseTransition pauseTransition = new PauseTransition(Duration.millis(3000));
            pauseTransition.setOnFinished( event -> alert.close());
            pauseTransition.play();
        }
    }

    public void onReNoireButtonClick(ActionEvent actionEvent) {
        try{
            arezzo.boutonClavier("^D");
        }catch (TempsIncorrectException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(e.getMessage());
            alert.setHeaderText("Impossible d'ajouter ce temps");
            alert.show();
            PauseTransition pauseTransition = new PauseTransition(Duration.millis(3000));
            pauseTransition.setOnFinished( event -> alert.close());
            pauseTransition.play();
        }
    }

    public void onFaNoireButtonClick(ActionEvent actionEvent) {
        try{
            arezzo.boutonClavier("^E");
        }catch (TempsIncorrectException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(e.getMessage());
            alert.setHeaderText("Impossible d'ajouter ce temps");
            alert.show();
            PauseTransition pauseTransition = new PauseTransition(Duration.millis(3000));
            pauseTransition.setOnFinished( event -> alert.close());
            pauseTransition.play();
        }
    }

    public void onSolNoireButtonClick(ActionEvent actionEvent) {
        try{
            arezzo.boutonClavier("^G");
        }catch (TempsIncorrectException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(e.getMessage());
            alert.setHeaderText("Impossible d'ajouter ce temps");
            alert.show();
            PauseTransition pauseTransition = new PauseTransition(Duration.millis(3000));
            pauseTransition.setOnFinished( event -> alert.close());
            pauseTransition.play();
        }
    }

    public void onLaNoireButtonClick(ActionEvent actionEvent) {
        try{
            arezzo.boutonClavier("^A");
        }catch (TempsIncorrectException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(e.getMessage());
            alert.setHeaderText("Impossible d'ajouter ce temps");
            alert.show();
            PauseTransition pauseTransition = new PauseTransition(Duration.millis(3000));
            pauseTransition.setOnFinished( event -> alert.close());
            pauseTransition.play();
        }
    }


}
