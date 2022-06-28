package sample;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class Acoes {

    //======================================== Variáveis Auxiliares ==============================

    static int i;
    static float valAux;
    static String estado = "";
    static String file = "";
    static boolean[] boolAux = new boolean[7];



    //======================================== Ações ========================================

    static void S_alterar(char s, float val) throws IOException, InterruptedException {

        boolAux = Var.GetEstado();

        switch (s) {
            case 's' -> {
                val += Var.GetS_Saude();
                if (val >= 0 && val <= 1) {
                    Var.SetS_Saude(val);

                    if (val <= 0.4){
                        boolAux[0] = true;
                        Var.SetEstado(boolAux);
                    } else {
                        boolAux[0] = false;
                        Var.SetEstado(boolAux);
                    }
                }else if (val > 1)
                    Var.SetS_Saude(1);
                else{
                    Var.SetS_Saude(0);
                    Morto();
                }
            }
            case 'f' -> {
                val += Var.GetS_Fome();
                if (val >= 0 && val <= 1) {
                    Var.SetS_Fome(val);

                    if (val <= 0.4) {
                        boolAux[1] = true;
                        Var.SetEstado(boolAux);
                    } else {
                        boolAux[1] = false;
                        Var.SetEstado(boolAux);
                    }
                }else if (val > 1)
                    Var.SetS_Fome(1);
                else{
                    Var.SetS_Fome(0);
                    Morto();
                }
            }
            case 'e' -> {
                val += Var.GetS_Energia();
                if (val >= 0 && val <= 1) {
                    Var.SetS_Energia(val);

                    if (val <= 0.4){
                        boolAux[2] = true;
                        Var.SetEstado(boolAux);
                    } else {
                        boolAux[2] = false;
                        Var.SetEstado(boolAux);
                    }
                }else if (val > 1)
                    Var.SetS_Energia(1);
                else{
                    Var.SetS_Energia(0);
                    Morto();
                }
            }
            case 'a' -> {
                val += Var.GetS_Felicidade();
                if (val >= 0 && val <= 1) {
                    Var.SetS_Felicidade(val);

                    if (val <= 0.4){
                        boolAux[3] = true;
                        Var.SetEstado(boolAux);
                    } else {
                        boolAux[3] = false;
                        Var.SetEstado(boolAux);
                    }
                }else if (val > 1) {
                    Var.SetS_Felicidade(1);
                }
                else {
                    Var.SetS_Felicidade(0);
                    Morto();
                }
            }
            case 'h' -> {
                val += Var.GetS_Higiene();
                if (val >= 0 && val <= 1) {
                    Var.SetS_Higiene(val);

                    if (val <= 0.4){
                        boolAux[4] = true;
                        Var.SetEstado(boolAux);
                    } else {
                        boolAux[4] = false;
                        Var.SetEstado(boolAux);
                    }
                }else if (val > 1)
                    Var.SetS_Higiene(1);
                else{
                    Var.SetS_Higiene(0);
                    Morto();
                }
            }
        }
    }

    static void Morto() throws IOException, InterruptedException {
        boolAux = Var.GetEstado();
        boolAux[6] = true;
        Var.SetEstado(boolAux);

        if (boolAux[5]){
            Var.SetRateEnergia2(2);
            Var.SetRateFelicidade2(0.75f);
            Var.SetRateFome2(0.75f);
            Var.SetRateSaude2(0.9f);
            Var.SetRateHigiene2(0.5f);
        }
        Var.SetRateEnergia2(-1f);
        Var.SetRateFelicidade2(-1f);
        Var.SetRateFome2(-1f);
        Var.SetRateSaude2(-1f);
        Var.SetRateHigiene2(-1f);
        Servidor.Salvar();
    }

    static void Estados(ImageView img_Pd, ImageView img_Cr, ImageView img_Cmr, ImageView img_Luz,  ImageView img_Bnc, ImageView img_Bnh, ImageView img_Bg, ImageView img_Bg2, ImageView img_Salvar) {
        boolAux = Var.GetEstado();
        if (boolAux[5]) {
            img_Bg.setVisible(false);
            img_Bg2.setVisible(true);

            img_Cr.setDisable(true);
            img_Cr.setOpacity(0.6);

            img_Cmr.setDisable(true);
            img_Cmr.setOpacity(0.65);

            img_Bnc.setDisable(true);
            img_Bnc.setOpacity(0.7);

            img_Bnh.setDisable(true);
            img_Bnh.setOpacity(0.65);
        }else if(!boolAux[6]){
            img_Bg.setVisible(true);
            img_Bg2.setVisible(false);

            img_Cr.setDisable(false);
            img_Cr.setOpacity(1);

            img_Cmr.setDisable(false);
            img_Cmr.setOpacity(1);

            img_Bnc.setDisable(false);
            img_Bnc.setOpacity(1);

            img_Bnh.setDisable(false);
            img_Bnh.setOpacity(1);
        }
        if (boolAux[6]) {
            img_Pd.setImage(new Image("file:src/sample/img/Pudim" + Var.GetSkin() + "/Morto.png"));

            img_Cr.setDisable(true);
            img_Cr.setOpacity(0.6);

            img_Cmr.setDisable(true);
            img_Cmr.setOpacity(0.65);

            img_Luz.setDisable(true);
            img_Luz.setOpacity(0.65);

            img_Bnc.setDisable(true);
            img_Bnc.setOpacity(0.7);

            img_Bnh.setDisable(true);
            img_Bnh.setOpacity(0.65);

            img_Salvar.setDisable(true);
            img_Salvar.setOpacity(0.65);
        }else {


            estado = "";
            file = "";

            for (i = 0; i < 6; i++) {
                if (i == 5)
                    if(Var.GetPisca() || boolAux[i]) estado += "1";
                    else estado += "0";
                else
                    if (boolAux[i]) estado += "1";
                    else estado += "0";
            }
            img_Pd.setImage(new Image("file:src/sample/img/Pudim" + Var.GetSkin() + "/" + estado + ".png"));
        }
    }


    static void A_Luz(ImageView img_Cr, ImageView img_Cmr, ImageView img_Bnc, ImageView img_Bnh, ImageView img_Bg, ImageView img_Bg2){

        boolAux = Var.GetEstado();

        if (!boolAux[5]){
            boolAux[5] = true;

            Var.SetEstado(boolAux);
            Var.SetRateEnergia2(-2);
            Var.SetRateFelicidade2(-0.75f);
            Var.SetRateFome2(-0.75f);
            Var.SetRateSaude2(-0.9f);
            Var.SetRateHigiene2(-0.5f);
        }
        else{
            boolAux[5] = false;

            Var.SetEstado(boolAux);
            Var.SetRateEnergia2(2);
            Var.SetRateFelicidade2(0.75f);
            Var.SetRateFome2(0.75f);
            Var.SetRateSaude2(0.9f);
            Var.SetRateHigiene2(0.5f);
        }
    }

    static void Pisca(ImageView img){}

    static void Verif(){

        boolAux = Var.GetEstado();

        if (boolAux[6]){
            Var.SetRateEnergia2(-1f);
            Var.SetRateFelicidade2(-1f);
            Var.SetRateFome2(-1f);
            Var.SetRateSaude2(-1f);
            Var.SetRateHigiene2(-1f);
        } else if (boolAux[5]){
            Var.SetRateEnergia2(-2);
            Var.SetRateFelicidade2(-0.75f);
            Var.SetRateFome2(-0.75f);
            Var.SetRateSaude2(-0.9f);
            Var.SetRateHigiene2(-0.5f);
        }
    }

    static void ErroTxt(TextField txt, String str){
        txt.setText("");
        txt.setPromptText(str);
        txt.setStyle("-fx-prompt-text-fill: #F00;" +
                "-fx-alignment: center;" +
                "-fx-background-color: #00ba98;" +
                "-fx-border-width: 2;" +
                "-fx-border-style: hidden hidden solid;" +
                "-fx-text-fill: black;");
    }

    static void LimparTxt(TextField txt, String str){
        txt.setText("");
        txt.setPromptText(str);
        txt.setStyle("-fx-prompt-text-fill: #606060;" +
                "-fx-alignment: center;" +
                "-fx-background-color: #00ba98;" +
                "-fx-border-width: 2;" +
                "-fx-border-style: hidden hidden solid;" +
                "-fx-text-fill: black;");
    }

    static void AbrirJogo(Button btn) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Acoes.class.getResource("Jogo.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Pudimagochi");
        stage.setScene(new Scene(root1));
        stage.setResizable(false);
        stage.show();

        Stage thiss = (Stage) btn.getScene().getWindow();
        thiss.close();
    }


    static void Logout(ImageView img) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Acoes.class.getResource("Login.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Pudimagochi");
        stage.setScene(new Scene(root1));
        stage.setResizable(false);
        stage.show();

        Stage thiss = (Stage) img.getScene().getWindow();
        thiss.close();
    }

    static void ProxPudi(int id, TextField txt, ImageView img) throws IOException, InterruptedException {
        Servidor.GetPet(id);

        boolAux = Var.GetEstado();

        if (boolAux[6]){
            img.setImage(new Image("file:src/sample/img/Pudim"+Var.GetSkin()+"/Morto.png"));
        }else{
            var estado = "";
            for (int i=0; i<6; i++){
                if (boolAux[i]) estado+="1";
                if (!boolAux[i]) estado+="0";
            }
            img.setImage(new Image("file:src/sample/img/Pudim"+Var.GetSkin()+"/"+estado+".png"));
        }

        txt.setText(Var.GetNome());
    }
}
