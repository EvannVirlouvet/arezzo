package arezzo.monde;


public class Note {
    private String ton , octave, duree , racineNote ;

    private int temps ;



    public Note(String ton , String octave, String duree, int temps){
        this.ton = ton ;
        this.octave = octave ;
        this.duree = duree ;
        this.temps = temps ;

        racineNote = ton ;

    }

    public void transposerGrave(){
        String[] listeNote = new String[]{"C","^C","D","^D","E","F","^F","G","^G","A","^A","B"} ;
        String[] listeOctave = new String[]{"aigu", "medium", "grave"};
        for (int i = 0 ; i < listeNote.length ; i++){
            if (listeNote[i].equals(racineNote)){
                if( i ==  0){
                    racineNote = listeNote[11];
                    for (int j = 0 ; j < listeOctave.length ; j++) {
                        if (listeOctave[j].equals(this.octave)) {
                            if (j == 2) {
                                this.octave = listeOctave[0];
                            } else {
                                this.octave = listeOctave[j + 1];
                            }
                            j = 4 ;
                        }
                    }
                }else{
                    racineNote = listeNote[i-1];
                }
                i = 12 ;
            }
        }
        ton = racineNote ;
        creerNote();
    }

    public void transposerAigu(){
        String[] listeNote = new String[]{"C","^C","D","^D","E","F","^F","G","^G","A","^A","B"} ;
        String[] listeOctave = new String[]{"aigu", "medium", "grave"};
        for (int i = 0 ; i < listeNote.length ; i++){
            if (listeNote[i].equals(racineNote)){
                if( i ==  11){
                    racineNote = listeNote[0];
                    for (int j = 0 ; j < listeOctave.length ; j++){
                        if(listeOctave[j].equals(this.octave)){
                            if (j == 0){
                                this.octave = listeOctave[2] ;
                            }else {
                                this.octave = listeOctave[j - 1] ;
                            }
                            j=4 ;
                        }
                    }

                }else{
                    racineNote = listeNote[i + 1];
                }
                i = 12 ;
            }
        }
        ton = racineNote ;
        creerNote();
    }

    public String traduction(){
        String[] listeNote = new String[]{"C","^C","D","^D","E","F","^F","G","^G","A","^A","B","z1/2","z1","z2","z3","z4"} ;
        String vraiNote = "" ;
        String[] listeVraiNote = new String[]{"Do","#Do","Re","#Re","Mi","Fa","#Fa","Sol","#Sol","La","#La","Si","chuuut"} ;
        int position = 0;
        for(int i = 0; i < listeNote.length; i++){
            if(listeNote[i].equals(racineNote)){
                position = i ;
            }
        }
        for (int j = 0 ; j < listeVraiNote.length ; j++){
            if (j == position){
                vraiNote = listeVraiNote[j] ;
            }
            if (position> 11){
                vraiNote = listeVraiNote[12] ;
                octave = "" ;
            }
        }
        return vraiNote ;
    }

    public void effacer(){
        switch (duree){
            case ("croche"):
                ton = "z1/2" ;
                octave = "" ;
                duree = "" ;
                racineNote = "z1/2" ;
                break;
            case("blanche"):
                ton = "z2" ;
                octave = "" ;
                duree = "" ;
                racineNote = "z2" ;
                break;
            case ("ronde"):
                ton = "z4" ;
                octave = "" ;
                duree = "" ;
                racineNote = "z4" ;
                break;
            default:
                ton = "z1" ;
                octave = "" ;
                duree = "" ;
                racineNote = "z1" ;
            break;
        }


    }

    public void  creerNote(){
        switch (duree) {
            case ("ronde"):
                    switch (octave) {
                        case ("aigu"):
                            ton = ton.toLowerCase();
                            ton = ton + "4";
                            break;
                        case ("grave"):
                            ton = ton + ",4";
                            break;

                        default:
                            ton = ton + "4";
                            break;
                    }
                break;
            case ("blanche"):
                switch (octave) {
                    case ("aigu"):
                        ton = ton.toLowerCase();
                        ton = ton + "2";
                        break;
                    case ("grave"):
                        ton = ton + ",2";
                        break;
                    default:
                        ton = ton + "2";
                        break;
                }
                break;
            case("croche") :
                switch (octave){
                    case("aigu"):
                        ton = ton.toLowerCase();
                        ton = "/" + ton ;
                        break;
                    case ("grave"):
                        ton = "/"+ton+"," ;
                        break;
                    default:
                        ton = "/"+ton ;
                        break;
                }
                break;
            default:
                switch (octave) {
                    case ("aigu"):
                        ton = ton.toLowerCase();
                        break;
                    case ("grave"):
                        ton = ton + ",";
                        break;

                    default:
                        break;
                }
                break;
        }
    }

    public String getTon() {
        return ton;
    }

    public String getOctave() {
        return octave;
    }

    public String getDuree() {
        return duree;
    }

    public int getTemps() {
        return temps;
    }

    public boolean cleanSilence() {
        String[] listeNote = new String[]{"C","^C","D","^D","E","F","^F","G","^G","A","^A","B","z1/2","z1","z2","z3","z4"} ;
        for (int i = listeNote.length-1; i > 11 ; i--){
            if(listeNote[i].equals(racineNote)){
                return true ;
            }
        }
        return  false ;
    }
}
