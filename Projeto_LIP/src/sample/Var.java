package sample;

public class Var {


    //======================================== Variáves ========================================

    private static int deltaTime = 1000;
    private static boolean atlz = true;
    private static boolean tmr = true;
    private static float rateSaude = 0.008f;
    private static float rateFome = 0.04f;
    private static float rateEnergia = 0.02f;
    private static float rateFelicidade = 0.03f;
    private static float rateHigiene = 0.02f;
    private static float rateSaude2 = 0;
    private static float rateFome2 = 0;
    private static float rateEnergia2 = 0;
    private static float rateFelicidade2 = 0;
    private static float rateHigiene2 = 0;

    // Imformações do Usuário

    private static String user;
    private static int pets;


    // Informações do Pet

    private static int Id;
    private static String nome;
    private static int idade;
    private static String skin;
    private static boolean Pisca = false;

    private static int qtd_Comida;
    private static int qtd_Banheiro;
    private static int qtd_Curar;
    private static int qtd_Brincar;
    private static int qtd_Luz;


    // Estatísticas
    private static float S_Saude = 0.8f;
    private static float S_Fome = 0.8f;
    private static float S_Energia = 0.8f;
    private static float S_Felicidade = 0.8f;
    private static float S_Higiene = 0.8f;
    private static boolean[] Estado = new boolean[7];



    //======================================== Setters ========================================

    public static void SetDeltaTime(int time){ deltaTime = time; }
    public static void SetAtlz(Boolean bool){ atlz = bool; }
    public static void SetTmr(Boolean bool){ tmr = bool; }
    public static void SetPisca(boolean bool){ Pisca = bool; }

    public static void SetRateSaude(float rate){ rateSaude = rate; }
    public static void SetRateFome(float rate){ rateFome = rate; }
    public static void SetRateEnergia(float rate){ rateEnergia = rate; }
    public static void SetRateFelicidade(float rate){ rateFelicidade = rate; }
    public static void SetRateHigiene(float rate){ rateHigiene = rate; }

    public static void SetRateSaude2(float rate){ rateSaude2 += rate; }
    public static void SetRateFome2(float rate){ rateFome2 += rate; }
    public static void SetRateEnergia2(float rate){ rateEnergia2 += rate; }
    public static void SetRateFelicidade2(float rate){ rateFelicidade2 += rate; }
    public static void SetRateHigiene2(float rate){ rateHigiene2 += rate; }


    // Imformações do Usuário
    public static void SetUser(String nome){ user = nome; }
    public static void SetPets(int qtd){ pets = qtd; }


    // Informações do Pet
    public static void SetId(int id){ Id = id; }
    public static void SetNome(String nick){ nome = nick; }
    public static void SetIdade(int idd){ idade = idd; }
    public static void SetSkin(String skn){ skin = skn; }


    // Estatísticas
    public static void SetS_Saude(float Saude){ S_Saude = Saude; }
    public static void SetS_Fome(float Fome){ S_Fome = Fome; }
    public static void SetS_Energia(float Energia){ S_Energia = Energia; }
    public static void SetS_Felicidade(float Felicidade){ S_Felicidade = Felicidade; }
    public static void SetS_Higiene(float Higiene){ S_Higiene = Higiene; }
    public static void SetEstado(boolean[] estado){ Estado = estado; }



    //======================================== Getters ========================================

    public static int GetDeltaTime(){ return deltaTime; }
    public static boolean GetAtlz(){ return atlz; }
    public static boolean GetTmr(){ return tmr; }
    public static float GetRateSaude(){ return rateSaude; }

    public static float GetRateFome(){ return rateFome; }
    public static float GetRateEnergia(){ return rateEnergia; }
    public static float GetRateFelicidade(){ return rateFelicidade; }
    public static float GetRateHigiene(){ return rateHigiene; }
    public static float GetRateSaude2(){ return rateSaude2; }

    public static float GetRateFome2(){ return rateFome2; }
    public static float GetRateEnergia2(){ return rateEnergia2; }
    public static float GetRateFelicidade2(){ return rateFelicidade2; }
    public static float GetRateHigiene2(){ return rateHigiene2; }

    // Imformações do Usuário

    public static String GetUser(){ return user; }
    public static int GetPets(){ return pets; }

    // Informações do Pet

    public static int GetId(){ return Id; }
    public static String GetNome(){ return nome; }
    public static int GetIdade(){ return idade; }
    public static String GetSkin(){ return skin; }
    public static boolean GetPisca(){ return Pisca; }

    // Estatísticas
    public static float GetS_Saude(){ return S_Saude; }
    public static float GetS_Fome(){ return S_Fome; }
    public static float GetS_Energia(){ return S_Energia; }
    public static float GetS_Felicidade(){ return S_Felicidade; }
    public static float GetS_Higiene(){ return S_Higiene; }
    public static boolean[] GetEstado(){ return Estado; }


}
