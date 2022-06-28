package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Login_Controller {

    boolean terms = false;


    //======================================== Login ========================================
    @FXML private AnchorPane acp_Login;
    @FXML private TextField txt_LOG_User;
    @FXML private PasswordField txt_LOG_Senha;
    @FXML private Button btn_LOG_Entrar;
    @FXML private Button btn_LOG_Cadastrar;
    @FXML private Label lbl_LOG_Esqc;


    //======================================== cadastro ========================================
    @FXML private AnchorPane acp_Cadastro;
    @FXML private TextField txt_CAD_Nome;
    @FXML private TextField txt_CAD_User;
    @FXML private PasswordField txt_CAD_Senha;
    @FXML private PasswordField txt_CAD_SenhaConfirma;
    @FXML private CheckBox chkb_CAD_Termos;
    @FXML private Label lbl_CAD_VerTermos;
    @FXML private ImageView img_CAD_Termos;
    @FXML private Button btn_CAD_Salvar;
    @FXML private Button btn_CAD_Voltar;


    //======================================== Botões ========================================
    //---------------------------------------- Login ----------------------------------------
    @FXML void LOG_Esqc(MouseEvent event) {

    }


    @FXML void LOG_Entrar(ActionEvent event) throws IOException, InterruptedException {

        if (txt_LOG_User.getText().equals("") && txt_LOG_User.getText().equals("")) {

            if (txt_LOG_User.getText().equals(""))
                Acoes.ErroTxt(txt_LOG_User, "Usuário em branco");
            if (txt_LOG_Senha.getText().equals(""))
                Acoes.ErroTxt(txt_LOG_Senha, "Senha em branco");
        }else {
            if (!Servidor.Logar(txt_LOG_User.getText(), txt_LOG_Senha.getText(), btn_LOG_Entrar)) {
                Acoes.ErroTxt(txt_LOG_User, "Usuário errado");
                Acoes.ErroTxt(txt_LOG_Senha, "Senha errado");
            } else
                Acoes.ErroTxt(txt_LOG_Senha, "Senha errado");
        }
    }
    @FXML void LOG_Cadastrar(ActionEvent event) {
        acp_Login.setVisible(false);
        acp_Cadastro.setVisible(true);

        Acoes.LimparTxt(txt_LOG_User, "Usuário");
        Acoes.LimparTxt(txt_LOG_Senha, "Senha");
    }


    //---------------------------------------- Cadastro ----------------------------------------
    @FXML void CAD_VerTermos(MouseEvent event) {
        if (terms == false) {
            img_CAD_Termos.setVisible(true);
            terms = true;
            lbl_CAD_VerTermos.setText("Fechar Termos de Uso e Privacidade.");
        }else {
            img_CAD_Termos.setVisible(false);
            terms = false;
            lbl_CAD_VerTermos.setText("Abrir Termos de Uso e Privacidade.");
        }
    }

    @FXML void CAD_Salvar(ActionEvent event) throws IOException, InterruptedException {
        if(!chkb_CAD_Termos.isSelected()){
            chkb_CAD_Termos.setStyle("-fx-text-fill: #59422b");
        } else{
            String senha = txt_CAD_Senha.getText();
            if (senha.equals(txt_CAD_SenhaConfirma.getText())) {
                String user = txt_CAD_User.getText();
                String nome = txt_CAD_Nome.getText();
    
                if (Servidor.Cadastro(user, nome, senha)) {
                    CAD_Voltar(event);
                } else {
                    Acoes.ErroTxt(txt_CAD_User, "Ususario já Existente");
                }
            } else {
                Acoes.ErroTxt(txt_CAD_Senha, "Senhas Diferentes");
                Acoes.ErroTxt(txt_CAD_SenhaConfirma, "Senhas Diferentes");
            }
        }
    }

    @FXML void CAD_Voltar(ActionEvent event) {
        acp_Cadastro.setVisible(false);
        acp_Login.setVisible(true);

        Acoes.LimparTxt(txt_CAD_Nome, "Nome");
        Acoes.LimparTxt(txt_CAD_User, "Usuário");
        Acoes.LimparTxt(txt_CAD_Senha, "Senha");
        Acoes.LimparTxt(txt_CAD_SenhaConfirma, "Confirmar Senha");
        chkb_CAD_Termos.setSelected(false);
    }


    //======================================== Animações ========================================
    //---------------------------------------- Login ----------------------------------------
    @FXML void Aumentar_Entrar(MouseEvent event) { Animacoes.anmcAumentarBTN(btn_LOG_Entrar); }

    @FXML void Aumentar_Cadastrar(MouseEvent event) { Animacoes.anmcAumentarBTN(btn_LOG_Cadastrar); }

    @FXML void Diminuir_Entrar(MouseEvent event) { Animacoes.anmcDiminuirBTN(btn_LOG_Entrar); }

    @FXML void Diminuir_Cadastrar(MouseEvent event) { Animacoes.anmcDiminuirBTN(btn_LOG_Cadastrar); }

    @FXML void Pressionar_Entrar(MouseEvent event) { Animacoes.anmcPresionarBTN(btn_LOG_Entrar); }

    @FXML void Pressionar_Cadastrar(MouseEvent event) { Animacoes.anmcPresionarBTN(btn_LOG_Cadastrar); }

    @FXML void Soltar_Entrar(MouseEvent event) { Animacoes.anmcSoltarBTN(btn_LOG_Entrar); }

    @FXML void Soltar_Cadastrar(MouseEvent event) { Animacoes.anmcSoltarBTN(btn_LOG_Cadastrar); }

    @FXML void Acende_Esqc(MouseEvent event) { Animacoes.anmcAcende(lbl_LOG_Esqc); }

    @FXML void Apaga_Esqc(MouseEvent event) {Animacoes.anmcApaga(lbl_LOG_Esqc); }



    //---------------------------------------- Cadastro ----------------------------------------
    @FXML void Aumentar_Salvar(MouseEvent event) { Animacoes.anmcAumentarBTN(btn_CAD_Salvar); }

    @FXML void Aumentar_Voltar(MouseEvent event) { Animacoes.anmcAumentarBTN(btn_CAD_Voltar); }

    @FXML void Diminuir_Salvar(MouseEvent event) { Animacoes.anmcDiminuirBTN(btn_CAD_Salvar); }

    @FXML void Diminuir_Voltar(MouseEvent event) { Animacoes.anmcDiminuirBTN(btn_CAD_Voltar); }

    @FXML void Pressionar_Salvar(MouseEvent event) { Animacoes.anmcPresionarBTN(btn_CAD_Salvar); }

    @FXML void Pressionar_Voltar(MouseEvent event) {Animacoes.anmcPresionarBTN(btn_CAD_Voltar); }

    @FXML void Soltar_Salvar(MouseEvent event) { Animacoes.anmcSoltarBTN(btn_CAD_Salvar); }

    @FXML void Soltar_Voltar(MouseEvent event) { Animacoes.anmcSoltarBTN(btn_CAD_Voltar); }

    @FXML void Acende_VerTermos(MouseEvent event) { Animacoes.anmcAcende(lbl_CAD_VerTermos); }

    @FXML void Apaga_VerTermos(MouseEvent event) { Animacoes.anmcApaga(lbl_CAD_VerTermos); }

}
