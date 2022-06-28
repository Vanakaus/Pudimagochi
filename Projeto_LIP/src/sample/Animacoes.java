package sample;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class Animacoes {

    static boolean in = false, pres = false;

    //======================================== Animações ========================================
    static void anmcAumentarIMG(ImageView img){
        img.setScaleX(1.1);
        img.setScaleY(1.1);
        in = true;
    }
    static void anmcDiminuirIMG(ImageView img){
        if (pres == false) {
            img.setScaleX(1);
            img.setScaleY(1);
        }
        in = false;
    }

    static void anmcAumentarBTN(Button btn){
        btn.setScaleX(1.1);
        btn.setScaleY(1.1);
        in = true;
    }
    static void anmcDiminuirBTN(Button btn){
        if (pres == false) {
            btn.setScaleX(1);
            btn.setScaleY(1);
        }
        in = false;
    }

    public static void anmcPresionarIMG(ImageView img) {
        img.setScaleX(1.05);
        img.setScaleY(1.05);
        pres = true;
    }
    public static void anmcSoltarIMG(ImageView img) {
        if (in == false){
            img.setScaleX(1);
            img.setScaleY(1);
        } else {
            img.setScaleX(1.1);
            img.setScaleY(1.1);
        }
        pres = false;
    }

    public static void anmcPresionarBTN(Button btn) {
        btn.setScaleX(1.05);
        btn.setScaleY(1.05);
        pres = true;
    }
    public static void anmcSoltarBTN(Button btn) {
        if (in == false){
            btn.setScaleX(1);
            btn.setScaleY(1);
        } else{
            btn.setScaleX(1.1);
            btn.setScaleY(1.1);
        }
        pres = false;
    }

    public static void anmcAcende(Label lbl) {
        lbl.setStyle("-fx-text-fill: #59422b");
    }
    public static void anmcApaga(Label lbl) {
        lbl.setStyle("-fx-text-fill: #000");
    }
}
