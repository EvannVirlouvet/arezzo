package arezzo.monde;

import arezzo.exceptions.TempsIncorrectException;
import com.google.gson.reflect.TypeToken;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.parboiled.common.StringUtils;
import partition.Partition;
import com.google.gson.* ;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Arezzo extends SujetObserve {
    private Partition partition ;
    private String note = "" ;

    private String titre ;

    private int nbFenetre;
    private int compteurTemps ;
    private String octaveNote, dureeNote;

    private ArrayList<Note> listeNote = new ArrayList<>() ;

    public Arezzo()  {
        Synthesizer synthesizer = null;
        try {
            synthesizer = MidiSystem.getSynthesizer();
        } catch (MidiUnavailableException e) {
            throw new RuntimeException(e);
        }
        partition = new Partition(synthesizer);
        compteurTemps = 0 ;
        dureeNote = "noire" ;
        octaveNote = "medium" ;
        nbFenetre = 0 ;
    }

    public void boutonClavier(String letter) {
        String son = "";
        switch (dureeNote) {
            case ("ronde"):
                if (compteurTemps%8 == 0) {
                    listeNote.add(new Note(letter, octaveNote,dureeNote,8)) ;
                    listeNote.get(listeNote.size()-1).creerNote();
                    son = listeNote.get(listeNote.size()-1).getTon() ;
                    compteurTemps = compteurTemps + 8;
                }else {
                    throw new TempsIncorrectException("Vous ajouté une ronde alors que vous avez deja mis une note . Essayer croche, noire ou blanche") ;
                }
                break;
            case ("blanche"):
                if((compteurTemps+3)%8 != 0 ) {
                    if((compteurTemps+2)%8 != 0) {
                        if ((compteurTemps+1)%8 != 0) {
                            listeNote.add(new Note(letter, octaveNote,dureeNote,4)) ;
                            listeNote.get(listeNote.size()-1).creerNote();
                            son = listeNote.get(listeNote.size()-1).getTon() ;
                            compteurTemps = compteurTemps + 4;
                        }else {
                            throw new TempsIncorrectException("Vous ajouté une Blanche alors qu'il ne reste qu'1 temps. Essayer croche") ;
                        }
                    }else {
                        throw new TempsIncorrectException("Vous ajouté une Blanche alors qu'il ne reste que 2 temps. Essayer croche ou noire") ;
                    }
                }else {
                    throw new TempsIncorrectException("Vous ajouté une Blanche alors qu'il ne reste que 3 temps. Essayer croche ou noire") ;
                }
                break;
            case("croche") :

                listeNote.add(new Note(letter, octaveNote,dureeNote,1)) ;
                listeNote.get(listeNote.size()-1).creerNote();
                son = listeNote.get(listeNote.size()-1).getTon() ;
                compteurTemps = compteurTemps + 1;

                break;
            default:
                if ((compteurTemps+1)%8 != 0 ) {
                    listeNote.add(new Note(letter, octaveNote,dureeNote,2)) ;
                    listeNote.get(listeNote.size()-1).creerNote();
                    son = listeNote.get(listeNote.size()-1).getTon() ;
                    compteurTemps = compteurTemps + 2;
                }else {
                    throw new TempsIncorrectException("Vous ajouté un Noire alors qu'il ne reste qu'1 temps. Essayer croche") ;
                }
                break;
        }

        note = note + listeNote.get(listeNote.size()-1).getTon() ;
        if(compteurTemps%8 ==  0){
            note = note + "|" ;
        }
        partition.setMelodie(note) ;
        switch (octaveNote){
            case ("aigu"):
                partition.setCouleurs(Color.GREEN, listeNote.size()-1);
                break ;
            case ("grave"):
                partition.setCouleurs(Color.RED, listeNote.size()-1);
                break;
        }
        partition.play(son);


        notifierObservateur();
    }



    public void boutonSilence(){
        switch (dureeNote) {
            case ("ronde"):
                if (compteurTemps%8 == 0) {
                    listeNote.add(new Note("z4", octaveNote,dureeNote,8)) ;
                    compteurTemps = compteurTemps + 8;
                }else {
                    throw new TempsIncorrectException("Vous ajouté ronde alors que vous avez deja mis une note . Essayer croche, noire ou blanche") ;
                }
                break;
            case ("blanche"):
                if((compteurTemps+3)%8 != 0 ) {
                    if((compteurTemps+2)%8 != 0) {
                        if ((compteurTemps+1)%8 != 0) {
                            listeNote.add(new Note("z2", octaveNote,dureeNote,4)) ;
                            compteurTemps = compteurTemps + 4;
                        }else {
                            throw new TempsIncorrectException("Vous ajouté une blanche alors qu'il ne reste qu'1 temps. Essayer croche") ;
                        }
                    }else {
                        throw new TempsIncorrectException("Vous ajouté une blanche alors qu'il ne reste que 2 temps. Essayer croche ou noire") ;
                    }
                }else {
                    throw new TempsIncorrectException("Vous ajouté une blanche alors qu'il ne reste que 3 temps. Essayer croche ou noire") ;
                }
                break;
            case("croche") :

                listeNote.add(new Note("z1/2", octaveNote,dureeNote,1)) ;
                compteurTemps = compteurTemps + 1;

                break;
            default:
                if ((compteurTemps+1)%8 != 0 ) {
                    listeNote.add(new Note("z1",octaveNote,dureeNote,2)) ;
                    compteurTemps = compteurTemps + 2 ;

                }else {
                    throw new TempsIncorrectException("Vous ajouté un Noire alors qu'il ne reste qu'1 temps. Essayer croche") ;
                }
                break;
        }
        note = note + listeNote.get(listeNote.size()-1).getTon() ;
        if(compteurTemps%8 ==  0){
            note = note + "|" ;
        }

        partition.setMelodie(note) ;
        notifierObservateur();
    }

    public void testSilence(){
        for (int i = listeNote.size() ; i > 0 ; i--){
            if(listeNote.get(i-1).cleanSilence()){
                listeNote.remove(i-1) ;
            }else {
                i = 0 ;
            }
        }
        this.note = "" ;
        compteurTemps = 0 ;
        for (int i = 0; i < listeNote.size(); i++) {
            this.note = this.note + listeNote.get(i).getTon();
            compteurTemps = compteurTemps + listeNote.get(i).getTemps();
            if (compteurTemps % 8 == 0) {
                this.note = this.note + "|";
            }
        }
        partition.setMelodie(note);
        notifierObservateur();
    }



    public void jouerMusique(){
        testSilence();
        partition.play(note);
    }



    public void setDuree(String duree){
        dureeNote = duree ;
    }

    public void setOctave(String octave){
        octaveNote = octave ;
    }

    public void buttonReset(){
        listeNote.clear();
        note = "" ;
        compteurTemps = 0 ;
        partition.setMelodie(note);
        notifierObservateur();
    }

    public void setInstrument(String instrument){ partition.setInstrument(instrument); }
    public void setVolume(double volume){
        partition.setVolume(volume);
        System.out.println(volume);
    }

    public void setTempo(double tempo){
        int temp = (int) tempo ;
        partition.setTempo(temp);
    }

    public Partition getPartition() {
        return partition;
    }

    public void effacer(Note note){
        note.effacer();
        this.note = "" ;
        compteurTemps = 0 ;
        for (int i = 0; i < listeNote.size(); i++) {
            this.note = this.note + listeNote.get(i).getTon();
            compteurTemps = compteurTemps + listeNote.get(i).getTemps();
            if (compteurTemps % 8 == 0) {
                this.note = this.note + "|";
            }
        }

        partition.setMelodie(this.note);
        notifierObservateur();



    }

    public void transposer(String transposer){
        note = "" ;
        compteurTemps = 0 ;
        if (transposer.equals("grave")) {
            for (int i = 0; i < listeNote.size(); i++) {
                listeNote.get(i).transposerGrave();
                note = note + listeNote.get(i).getTon();
                compteurTemps = compteurTemps + listeNote.get(i).getTemps();
                if (compteurTemps % 8 == 0) {
                    note = note + "|";
                }
            }
        }else if(transposer.equals("aigu")) {
            for (int i = 0; i < listeNote.size(); i++) {
                listeNote.get(i).transposerAigu();
                note = note + listeNote.get(i).getTon();
                compteurTemps = compteurTemps + listeNote.get(i).getTemps();
                if (compteurTemps % 8 == 0) {
                    note = note + "|";
                }
            }
        }
        partition.setMelodie(this.note);
        notifierObservateur();

    }

    public void Enregistrer() {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create() ;

        JFileChooser jFileChooser = new JFileChooser() ;
        int fileChoose = jFileChooser.showDialog(jFileChooser,"Enregistrer") ;
        if(fileChoose == JFileChooser.APPROVE_OPTION){
            File file = new File(jFileChooser.getSelectedFile().getAbsoluteFile()+".json") ;

            try{
                if(!file.exists()){
                    Writer writer = new FileWriter(file) ;
                    gson.toJson(listeNote,writer) ;
                    writer.close();
                }

            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void ouvrir() {
        FileChooser fileChooser = new FileChooser() ;
        File file = fileChooser.showOpenDialog(new Stage()) ;
        titre = file.getName() ;
        titre = titre.substring(0,titre.length()-5);
        String JSON_PATH = file.getAbsolutePath();
        try{
            listeNote.clear();
            Reader reader = new FileReader(JSON_PATH) ;
            listeNote = new Gson().fromJson(reader, new TypeToken<List<Note>>() {}.getType());


        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        note= "" ;
        for (int i = 0 ; i < listeNote.size() ; i++){
            note = note + listeNote.get(i).getTon() ;
            compteurTemps = compteurTemps + listeNote.get(i).getTemps() ;
            if(compteurTemps%8 == 0){
                note = note + "|" ;
            }
        }
        partition.setMelodie(note);

        notifierObservateur();

    }

    public ArrayList<Note> getListeNote() {
        return listeNote;
    }

    public void transposerNote(Note note, String transposer) {
        this.note = "" ;
        compteurTemps = 0 ;
        if (transposer.equals("grave")) {
            note.transposerGrave();
            for (int i = 0; i < listeNote.size(); i++) {
                this.note = this.note + listeNote.get(i).getTon();
                compteurTemps = compteurTemps + listeNote.get(i).getTemps();
                if (compteurTemps % 8 == 0) {
                    this.note = this.note + "|";
                }
            }
        }else if(transposer.equals("aigu")) {
            note.transposerAigu();
            for (int i = 0; i < listeNote.size(); i++) {
                this.note = this.note + listeNote.get(i).getTon();
                compteurTemps = compteurTemps + listeNote.get(i).getTemps();
                if (compteurTemps % 8 == 0) {
                    this.note = this.note + "|";
                }
            }
        }
        partition.setMelodie(this.note);
        notifierObservateur();
    }

    public int getNbFenetre() {
        return nbFenetre;
    }

    public void setNbFenetre(int nbFenetre) {
        this.nbFenetre = nbFenetre;
    }

    public void fenetreExiste() {
        if(listeNote.size() != 0){
            notifierObservateur();
        }
    }

    public String getTitre() {
        return titre;
    }
}
