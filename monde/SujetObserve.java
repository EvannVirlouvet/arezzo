package arezzo.monde;

import arezzo.vue.Observateur;

import java.util.ArrayList;

public class SujetObserve implements Observateur {
    private ArrayList<Observateur> obs = new ArrayList<>(10);

    public void ajouterObservateur(Observateur v){
        this.obs.add(v) ;
    }

    public void notifierObservateur(){
        for (Observateur v : this.obs) v.reagir() ;
    }


    @Override
    public void reagir() {

    }
}
