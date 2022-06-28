package sample;

import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Servidor {

    //======================================== "Instancia" ========================================
    private static HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();


    //======================================== "Formatação" ========================================
    private static HttpRequest.BodyPublisher buildFormDataFromMap(Map<Object, Object> data) {
        var builder = new StringBuilder();
        builder.append("{");
        for (Map.Entry<Object, Object> entry : data.entrySet()) {
            if (builder.length() > 1) {
                builder.append(",\n");
            }
            builder.append("\"");
            builder.append(URLEncoder.encode(entry.getKey().toString(), StandardCharsets.UTF_8));
            builder.append("\":\"");
            builder.append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8));
            builder.append("\"");
        }
        builder.append("}");
        System.out.println(builder.toString());
        return HttpRequest.BodyPublishers.ofString(builder.toString());
    }


    //======================================== "Funções" ========================================
    static boolean Cadastro(String user, String nome, String senha) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:3000/users/"+user))
                .setHeader("User-Agent", "Java 11 HttpClient Bot")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        String res = response.body();

        if (!res.equals("")) {
            return false;
        } else {

            Map<Object, Object> data = new HashMap<>();
            data.put("usuario", user);
            data.put("nome", nome);
            data.put("senha", senha);
            data.put("pets", "0");

            request = HttpRequest.newBuilder()
                    .POST(buildFormDataFromMap(data))
                    .uri(URI.create("http://localhost:3000/users"))
                    .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                    .header("Content-Type", "application/json")
                    .build();

            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());


            return true;
        }
    }


    static boolean Logar(String usuario, String senha, Button btn) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:3000/users/"+usuario))
                .setHeader("User-Agent", "Java 11 HttpClient Bot")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        String res = response.body();

        if (res.equals("")) {
            return false;
        } else {


            res = res.substring(1, res.length() - 1);
            System.out.println(res);

            var partes = res.split(",");

            var us = partes[0].split(":");
            var nm = partes[1].split(":");
            var snh = partes[2].split(":");
            var pt = partes[3].split(":");

            var user = us[1].substring(1, us[1].length()-1);
            var nome = nm[1].substring(1, nm[1].length()-1);
            var senhaa = snh[1].substring(1, snh[1].length()-1);
            var pets = pt[1];


            if (senha.equals(senhaa)){
                Var.SetUser(user);
                Var.SetNome(nome);
                Var.SetPets(Integer.parseInt(pets));

                Acoes.AbrirJogo(btn);
            } else
                return true;
        }

        return true;
    }

    public static void GetPets() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:3000/pets/"+Var.GetUser()))
                .setHeader("User-Agent", "Java 11 HttpClient Bot")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // print status code
//            System.out.println(response.statusCode());

        // print response body
//        System.out.println(response.body());
        String res = response.body();

//        System.out.println(res);

        res = res.substring(1, res.length() - 2);
        System.out.println(res);

        if (Var.GetPets() > 1){
            var partes = res.split("},");

            for (int i = 0; i < Var.GetPets(); i++){
                var parte = partes[i].split(",");
                var idd = parte[0].split(":");
                var id = Integer.parseInt(idd[1]);
                Jogo_Controller.SetPets(i, id);
            }

        }else {
            var parte = res.split(",");
            var idd = parte[0].split(":");
            var id = Integer.parseInt(idd[1]);
            Jogo_Controller.SetPets(0, id);




//            System.out.println(partes[i]);
//            System.out.println(parte[0]);
//            System.out.println(idd[1]);
//            System.out.println(id);
        }



    }


    public static void GetPet(int iddd) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:3000/pet/"+iddd))
                .setHeader("User-Agent", "Java 11 HttpClient Bot")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // print status code
//            System.out.println(response.statusCode());

        // print response body
        String res = response.body();
        res = res.substring(1, res.length() - 1);

        var partes = res.split(",");

        var idd = partes[0].split(":");
        var nm = partes[1].split(":");
        var skn = partes[2].split(":");
        var sd = partes[3].split(":");
        var fm = partes[4].split(":");
        var eng = partes[5].split(":");
        var flcd = partes[6].split(":");
        var hgn = partes[7].split(":");
        var est = partes[8].split(":");

        var id = idd[1];
        var nome = nm[1].substring(1, nm[1].length()-1);
        var skin = skn[1].substring(1, skn[1].length()-1);
        var saude = sd[1].substring(1, sd[1].length()-1);
        var fome = fm[1].substring(1, fm[1].length()-1);
        var energia = eng[1].substring(1, eng[1].length()-1);
        var felicidade = flcd[1].substring(1, flcd[1].length()-1);
        var higiene = hgn[1].substring(1, hgn[1].length()-1);
        var estados = est[1].substring(1, est[1].length()-1);

        boolean[] estado = new boolean[7];
        for (int i = 0; i < 7; i++) {
            if(estados.charAt(i) == '0') estado[i] = false;
            if(estados.charAt(i) == '1') estado[i] = true;
        }

//        System.out.println(id);
//        System.out.println(nome);
//        System.out.println(skin);
//        System.out.println(saude);
//        System.out.println(fome);
//        System.out.println(energia);
//        System.out.println(felicidade);
//        System.out.println(higiene);


        Var.SetId(Integer.parseInt(id));
        Var.SetNome(nome);
        Var.SetSkin(skin);
        Var.SetS_Saude(Float.parseFloat(saude));
        Var.SetS_Fome(Float.parseFloat(fome));
        Var.SetS_Energia(Float.parseFloat(energia));
        Var.SetS_Felicidade(Float.parseFloat(felicidade));
        Var.SetS_Higiene(Float.parseFloat(higiene));
        Var.SetEstado(estado);




    }

    static void NovoPet() throws IOException, InterruptedException {

        Map<Object, Object> data = new HashMap<>();
            data.put("nome", Var.GetNome());
            data.put("skin", Var.GetSkin());
            data.put("saude", "0.8");
            data.put("fome", "0.8");
            data.put("energia", "0.8");
            data.put("felicidade", "0.8");
            data.put("higiene", "0.8");
            data.put("estados", "0000000");
            data.put("dono", Var.GetUser());

        HttpRequest request = HttpRequest.newBuilder()
                .POST(buildFormDataFromMap(data))
                .uri(URI.create("http://localhost:3000/pet"))
                .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());


        update();
        GetPets();

    }

    private static void update() throws IOException, InterruptedException {

        Map<Object, Object> data = new HashMap<>();
        data.put("usuario", Var.GetUser());
        data.put("pets", Var.GetPets());

        HttpRequest request = HttpRequest.newBuilder()
                .POST(buildFormDataFromMap(data))
                .uri(URI.create("http://localhost:3000/updateUser"))
                .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

    }

    public static void Salvar() throws IOException, InterruptedException {


        boolean[] estadoss = new boolean[7];
        estadoss = Var.GetEstado();
        String estados = "";

        for (int i = 0; i < 7; i++) {
            if (estadoss[i]) estados += "1";
            if (!estadoss[i]) estados += "0";
        }

//        System.out.println("depois do loop");


        Map<Object, Object> data = new HashMap<>();
        data.put("id", Var.GetId());
        data.put("saude", Var.GetS_Saude());
        data.put("fome", Var.GetS_Fome());
        data.put("energia", Var.GetS_Energia());
        data.put("felicidade", Var.GetS_Felicidade());
        data.put("higiene", Var.GetS_Higiene());
        data.put("estados", estados.toString());

        HttpRequest request = HttpRequest.newBuilder()
                .POST(buildFormDataFromMap(data))
                .uri(URI.create("http://localhost:3000/update"))
                .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());


    }
}