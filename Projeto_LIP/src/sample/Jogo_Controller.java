package sample;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Jogo_Controller implements Initializable {

    int pd = 0;
    int bg = 0;
    boolean criar = false;
    boolean[] boolAux = new boolean[7];
    static int[] pets = new int[Var.GetPets()+20];

    public static void SetPets(int i, int id){ pets[i] = id;}

    //======================================== Criar ========================================
    @FXML private ImageView img_Logout;

    @FXML private AnchorPane acp_Criar;
    @FXML private TextField txt_CRIAR_Nome;

    @FXML private Label lbl_Titulo;
    @FXML private ImageView img_CRIAR_Pudi;

    @FXML private ImageView img_STE_Pudi;
    @FXML private ImageView img_STD_Pudi;

    @FXML private ImageView img_STE_BG;
    @FXML private ImageView img_STD_BG;

    @FXML private Button btn_CRIAR_Criar;
    @FXML private Button btn_CRIAR_Iniciar;


    //======================================== Pudimagotchi ========================================
    //---------------------------------------- Estatísticas ----------------------------------------

    @FXML private AnchorPane acp_Pudimagotchi;
    @FXML private Label lbl_Pudimagotchi;

    @FXML private ImageView img_Pudi;

    @FXML private ImageView img_Bg;
    @FXML private ImageView img_Bg2;

    @FXML private ImageView img_Bg3;
    @FXML private ImageView img_S_Saude;

    @FXML private ProgressBar pgb_S_Saude;
    @FXML private ImageView img_S_Fome;

    @FXML private ProgressBar pgb_S_Fome;
    @FXML private ImageView img_S_Energia;

    @FXML private ProgressBar pgb_S_Energia;
    @FXML private ImageView img_S_brincar;

    @FXML private ProgressBar pgb_S_Felicidade;
    @FXML private ImageView img_S_Banheiro;

    @FXML private  ProgressBar pgb_S_Higiene;

    //---------------------------------------- Botões ----------------------------------------
    @FXML private ImageView img_A_Curar;
    @FXML private ImageView img_A_Comida;
    @FXML private ImageView img_A_Luz;
    @FXML private ImageView img_A_Brincar;
    @FXML private ImageView img_A_Banheiro;
    @FXML private ImageView img_Sair;

    @FXML private ImageView img_Salvar;

    //======================================== Funções ========================================

    //---------------------------------------- Criar ----------------------------------------
    @FXML void Logout(ActionEvent event) throws IOException {
        Acoes.Logout(img_Logout);
    }

    @FXML void CRIAR_Criar(ActionEvent event) throws IOException, InterruptedException {
        if (criar == false){
            criar = true;
            pd = 0;
            bg = 0;
            txt_CRIAR_Nome.setText("");
            txt_CRIAR_Nome.setEditable(true);
            btn_CRIAR_Criar.setText("Voltar");
            lbl_Titulo.setText("Crie um Pet Novo");
            img_CRIAR_Pudi.setImage(new Image("file:src/sample/img/Pudim0/000000.png"));
        } else if (Var.GetPets() > 0){
            criar = false;
            pd = 0;
            bg = 0;
            txt_CRIAR_Nome.setEditable(false);
            btn_CRIAR_Criar.setText("Criar Novo");
            lbl_Titulo.setText("Escolha seu Pet");
            Acoes.ProxPudi(pets[0], txt_CRIAR_Nome, img_CRIAR_Pudi);
        }


    }

    @FXML void CRIAR_Iniciar(ActionEvent event) throws IOException, InterruptedException {
        if(criar){
            Var.SetPets(Var.GetPets()+1);
            Var.SetNome(txt_CRIAR_Nome.getText());
            Var.SetSkin(String.valueOf(pd));
            Var.SetS_Saude(0.8f);
            Var.SetS_Fome(0.8f);
            Var.SetS_Energia(0.8f);
            Var.SetS_Felicidade(0.8f);
            Var.SetS_Higiene(0.8f);
            boolean[] bool = {false, false, false, false, false, false, false};
            Var.SetEstado(bool);

            Servidor.NovoPet();
            Var.SetId(pets[Var.GetPets()-1]);

            acp_Criar.setVisible(false);
            acp_Pudimagotchi.setVisible(true);


        } else{
            acp_Criar.setVisible(false);
            acp_Pudimagotchi.setVisible(true);
        }

        lbl_Pudimagotchi.setText(Var.GetNome());

        Acoes.Verif();

        Var.SetTmr(true);
        Var.SetAtlz(true);

        inicializar();
    }

    @FXML void STE_Pudi(MouseEvent event) throws IOException, InterruptedException {
        if (criar){
            pd--;
            if(pd < 0) pd=1;
            img_CRIAR_Pudi.setImage(new Image("file:src/sample/img/Pudim" + pd + "/000000.png"));
        } else{
            pd--;
            if(pd < 0) pd=Var.GetPets()-1;
            Acoes.ProxPudi(pets[pd], txt_CRIAR_Nome, img_CRIAR_Pudi);
        }
    }

    @FXML void STD_Pudi(MouseEvent event) throws IOException, InterruptedException {
        if (criar){
            pd++;
            if(pd > 1) pd=0;
            img_CRIAR_Pudi.setImage(new Image("file:src/sample/img/Pudim" + pd + "/000000.png"));
            System.out.println("file:src/sample/img/Pudim" + pd + "/000000.png");
        } else{
            pd++;
            if(pd == Var.GetPets()) pd=0;
            Acoes.ProxPudi(pets[pd], txt_CRIAR_Nome, img_CRIAR_Pudi);
        }
    }

    @FXML void STE_BG(MouseEvent event) {
        bg--;
        if(bg < 0) bg=7;
        img_Bg.setImage(new Image("file:src/sample/img/Background/BG" + bg + ".jpg"));
    }

    @FXML void STD_BG(MouseEvent event) {
        bg++;
        if(bg > 7) bg=0;
        img_Bg.setImage(new Image("file:src/sample/img/Background/BG" + bg + ".jpg"));
    }

    //---------------------------------------- Jogo ----------------------------------------
    @FXML void A_Banheiro(MouseEvent event) throws IOException, InterruptedException {
        Acoes.S_alterar('h', 0.22f);
        Acoes.S_alterar('e', -0.01f);
        Acoes.S_alterar('f', -0.01f);
    }

    @FXML void A_Brincar(MouseEvent event) throws IOException, InterruptedException {
        Acoes.S_alterar('a', 0.17f);
        Acoes.S_alterar('s', 0.02f);
        Acoes.S_alterar('e', -0.04f);
        Acoes.S_alterar('h', -0.06f);
        Acoes.S_alterar('f', -0.03f);
    }
    @FXML void A_Comida(MouseEvent event) throws IOException, InterruptedException {
        Acoes.S_alterar('f', 0.24f);
        Acoes.S_alterar('s', 0.02f);
        Acoes.S_alterar('e', 0.02f);
        Acoes.S_alterar('h', -0.02f);

    }
    @FXML void A_Curar(MouseEvent event) throws IOException, InterruptedException {
        Acoes.S_alterar('s', 0.2f);
    }

    @FXML void A_Luz(MouseEvent event) {
        Acoes.A_Luz(img_A_Curar, img_A_Comida, img_A_Brincar, img_A_Banheiro, img_Bg2, img_Bg3);
    }

    @FXML void Sair(MouseEvent event) throws IOException, InterruptedException {
        Var.SetAtlz(false);
        Var.SetTmr(false);

        Servidor.Salvar();

        criar = false;
        pd = 0;
        bg = 0;
        txt_CRIAR_Nome.setEditable(false);
        btn_CRIAR_Criar.setText("Criar Novo");
        lbl_Titulo.setText("Escolha seu Pet");
        Acoes.ProxPudi(pets[0], txt_CRIAR_Nome, img_CRIAR_Pudi);

        acp_Criar.setVisible(true);
        acp_Pudimagotchi.setVisible(false);

        boolAux = Var.GetEstado();

        if (boolAux[6]){
            Var.SetRateEnergia2(1f);
            Var.SetRateFelicidade2(1f);
            Var.SetRateFome2(1f);
            Var.SetRateSaude2(1f);
            Var.SetRateHigiene2(1f);
        } else if (boolAux[5]){
            Var.SetRateEnergia2(2);
            Var.SetRateFelicidade2(0.75f);
            Var.SetRateFome2(0.75f);
            Var.SetRateSaude2(0.9f);
            Var.SetRateHigiene2(0.5f);
        }

        img_Bg.setVisible(true);
        img_Bg2.setVisible(false);

        img_A_Curar.setDisable(false);
        img_A_Curar.setOpacity(1);

        img_A_Comida.setDisable(false);
        img_A_Comida.setOpacity(1);

        img_A_Luz.setDisable(false);
        img_A_Luz.setOpacity(1);

        img_A_Brincar.setDisable(false);
        img_A_Brincar.setOpacity(1);

        img_A_Banheiro.setDisable(false);
        img_A_Banheiro.setOpacity(1);

        img_Salvar.setDisable(false);
        img_Salvar.setOpacity(1);
    }

    @FXML void Salvar(MouseEvent event) throws IOException, InterruptedException {
        Servidor.Salvar();
    }



    //======================================== Animação ========================================
    //---------------------------------------- Criar ----------------------------------------
    @FXML void Aumentar_Logout(MouseEvent event) { Animacoes.anmcAumentarIMG(img_Logout); }

    @FXML void Aumentar_STE_Pudi(MouseEvent event) { Animacoes.anmcAumentarIMG(img_STE_Pudi); }

    @FXML void Aumentar_STD_Pudi(MouseEvent event) { Animacoes.anmcAumentarIMG(img_STD_Pudi); }

    @FXML void Aumentar_STE_BG(MouseEvent event) { Animacoes.anmcAumentarIMG(img_STE_BG); }

    @FXML void Aumentar_STD_BG(MouseEvent event) { Animacoes.anmcAumentarIMG(img_STD_BG); }

    @FXML void Aumentar_Criar(MouseEvent event) { Animacoes.anmcAumentarBTN(btn_CRIAR_Criar); }

    @FXML void Aumentar_Iniciar(MouseEvent event) { Animacoes.anmcAumentarBTN(btn_CRIAR_Iniciar); }

    @FXML void Diminuir_Logout(MouseEvent event) { Animacoes.anmcDiminuirIMG(img_Logout); }

    @FXML void Diminuir_STE_Pudi(MouseEvent event) { Animacoes.anmcDiminuirIMG(img_STE_Pudi); }

    @FXML void Diminuir_STD_Pudi(MouseEvent event) { Animacoes.anmcDiminuirIMG(img_STD_Pudi); }

    @FXML void Diminuir_STE_BG(MouseEvent event) { Animacoes.anmcDiminuirIMG(img_STE_BG); }

    @FXML void Diminuir_STD_BG(MouseEvent event) { Animacoes.anmcDiminuirIMG(img_STD_BG); }

    @FXML void Diminuir_Criar(MouseEvent event) { Animacoes.anmcDiminuirBTN(btn_CRIAR_Criar); }

    @FXML void Diminuir_Iniciar(MouseEvent event) { Animacoes.anmcDiminuirBTN(btn_CRIAR_Iniciar); }

    @FXML void Pressionar_Logout(MouseEvent event) { Animacoes.anmcPresionarIMG(img_Logout); }

    @FXML void Pressionar_STE_Pudi(MouseEvent event) { Animacoes.anmcPresionarIMG(img_STE_Pudi); }

    @FXML void Pressionar_STD_Pudi(MouseEvent event) { Animacoes.anmcPresionarIMG(img_STD_Pudi); }

    @FXML void Pressionar_STE_BG(MouseEvent event) { Animacoes.anmcPresionarIMG(img_STE_BG); }

    @FXML void Pressionar_STD_BG(MouseEvent event) { Animacoes.anmcPresionarIMG(img_STD_BG); }

    @FXML void Pressionar_Criar(MouseEvent event) { Animacoes.anmcPresionarBTN(btn_CRIAR_Criar); }

    @FXML void Pressionar_Iniciar(MouseEvent event) { Animacoes.anmcPresionarBTN(btn_CRIAR_Iniciar); }

    @FXML void Soltar_Logout(MouseEvent event) { Animacoes.anmcSoltarIMG(img_Logout); }

    @FXML void Soltar_STE_Pudi(MouseEvent event) { Animacoes.anmcSoltarIMG(img_STE_Pudi); }

    @FXML void Soltar_STD_Pudi(MouseEvent event) { Animacoes.anmcSoltarIMG(img_STD_Pudi); }

    @FXML void Soltar_STE_BG(MouseEvent event) { Animacoes.anmcSoltarIMG(img_STE_BG); }

    @FXML void Soltar_STD_BG(MouseEvent event) { Animacoes.anmcSoltarIMG(img_STD_BG); }

    @FXML void Soltar_Criar(MouseEvent event) { Animacoes.anmcSoltarBTN(btn_CRIAR_Criar); }

    @FXML void Soltar_Iniciar(MouseEvent event) { Animacoes.anmcSoltarBTN(btn_CRIAR_Iniciar); }


    //---------------------------------------- Jogo ----------------------------------------

    @FXML void Aumentar_cr(MouseEvent event) { Animacoes.anmcAumentarIMG(img_A_Curar); }

    @FXML void Aumentar_cmd(MouseEvent event) { Animacoes.anmcAumentarIMG(img_A_Comida); }

    @FXML void Aumentar_luz(MouseEvent event) { Animacoes.anmcAumentarIMG(img_A_Luz); }

    @FXML void Aumentar_bnc(MouseEvent event) { Animacoes.anmcAumentarIMG(img_A_Brincar); }

    @FXML void Aumentar_bnh(MouseEvent event) { Animacoes.anmcAumentarIMG(img_A_Banheiro); }

    @FXML void Aumentar_Sair(MouseEvent event) { Animacoes.anmcAumentarIMG(img_Sair); }

    @FXML void Aumentar_Salvar(MouseEvent event) { Animacoes.anmcAumentarIMG(img_Salvar); }

    @FXML void Diminuir_cr(MouseEvent event) { Animacoes.anmcDiminuirIMG(img_A_Curar); }

    @FXML void Diminuir_cmd(MouseEvent event) { Animacoes.anmcDiminuirIMG(img_A_Comida); }

    @FXML void Diminuir_luz(MouseEvent event) { Animacoes.anmcDiminuirIMG(img_A_Luz); }

    @FXML void Diminuir_bnc(MouseEvent event) { Animacoes.anmcDiminuirIMG(img_A_Brincar); }

    @FXML void Diminuir_bnh(MouseEvent event) { Animacoes.anmcDiminuirIMG(img_A_Banheiro); }

    @FXML void Diminuir_Sair(MouseEvent event) { Animacoes.anmcDiminuirIMG(img_Sair); }

    @FXML void Diminuir_Salvar(MouseEvent event) { Animacoes.anmcDiminuirIMG(img_Salvar); }

    @FXML void Presionar_cr(MouseEvent event) { Animacoes.anmcPresionarIMG(img_A_Curar); }

    @FXML void Presionar_cmd(MouseEvent event) { Animacoes.anmcPresionarIMG(img_A_Comida); }

    @FXML void Presionar_luz(MouseEvent event) { Animacoes.anmcPresionarIMG(img_A_Luz); }

    @FXML void Presionar_bnc(MouseEvent event) { Animacoes.anmcPresionarIMG(img_A_Brincar); }

    @FXML void Presionar_bnh(MouseEvent event) { Animacoes.anmcPresionarIMG(img_A_Banheiro); }

    @FXML void Presionar_Sair(MouseEvent event) { Animacoes.anmcPresionarIMG(img_Sair); }

    @FXML void Presionar_Salvar(MouseEvent event) { Animacoes.anmcPresionarIMG(img_Salvar); }

    @FXML void Soltar_cr(MouseEvent event) { Animacoes.anmcSoltarIMG(img_A_Curar); }

    @FXML void Soltar_cmd(MouseEvent event) { Animacoes.anmcSoltarIMG(img_A_Comida); }

    @FXML void Soltar_luz(MouseEvent event) { Animacoes.anmcSoltarIMG(img_A_Luz); }

    @FXML void Soltar_bnc(MouseEvent event) { Animacoes.anmcSoltarIMG(img_A_Brincar); }

    @FXML void Soltar_bnh(MouseEvent event) { Animacoes.anmcSoltarIMG(img_A_Banheiro); }

    @FXML void Soltar_Sair(MouseEvent event) { Animacoes.anmcSoltarIMG(img_Sair); }

    @FXML void Soltar_Salvar(MouseEvent event) { Animacoes.anmcSoltarIMG(img_Salvar); }



    //======================================== Atualização ========================================
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        try {
            if (Var.GetPets() > 0) {
                Servidor.GetPets();
                Acoes.ProxPudi(pets[0], txt_CRIAR_Nome, img_CRIAR_Pudi);
                lbl_Titulo.setText("Escolha seu Pet");
            }else {
                CRIAR_Criar((ActionEvent) null);
                lbl_Titulo.setText("Crie um Pet Novo");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void inicializar() {
        try {
            Thread.sleep(500);

            Tarefas.Timer();
            atualiza();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void atualiza() throws InterruptedException {
        Service<Void> servico = new Service() {
            protected Task createTask() {
                return new Task() {
                    protected Void call() throws Exception {

                        Thread.sleep(100);

                        while (Var.GetAtlz()) {
                            atualiza_pgb();
                            Thread.sleep( 100);
                        }
                        return null;
                    }
                };
            }
        };
        servico.restart();
    }


    void atualiza_pgb(){
        pgb_S_Higiene.setProgress(Var.GetS_Higiene());
        pgb_S_Fome.setProgress(Var.GetS_Fome());
        pgb_S_Energia.setProgress(Var.GetS_Energia());
        pgb_S_Felicidade.setProgress(Var.GetS_Felicidade());
        pgb_S_Saude.setProgress(Var.GetS_Saude());
        Acoes.Estados(img_Pudi, img_A_Curar, img_A_Comida, img_A_Luz, img_A_Brincar, img_A_Banheiro, img_Bg2, img_Bg3, img_Salvar);
    }

}
