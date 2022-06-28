package sample;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class Tarefas {

    public static void Timer() throws InterruptedException {
        Service<Void> servico = new Service() {
            protected Task createTask() {
                return new Task() {
                    protected Void call() throws Exception {

                        Thread.sleep(100);

                        var clock = 0;

                        while (Var.GetTmr()) {

                            if(clock%3 == 0) Var.SetPisca(true);
                            else Var.SetPisca(false);

                            if(clock%2 == 0) Servidor.Salvar();

                            Acoes.S_alterar('s', -(Var.GetRateSaude() + (Var.GetRateSaude() * Var.GetRateSaude2()) ) );
                            Acoes.S_alterar('f', -(Var.GetRateFome() + (Var.GetRateFome() * Var.GetRateFome2()) ) );
                            Acoes.S_alterar('a', -(Var.GetRateFelicidade() + (Var.GetRateFelicidade() * Var.GetRateFelicidade2()) ) );
                            Acoes.S_alterar('e', -(Var.GetRateEnergia() + (Var.GetRateEnergia() * Var.GetRateEnergia2()) ) );
                            Acoes.S_alterar('h', -(Var.GetRateHigiene() + (Var.GetRateHigiene() * Var.GetRateHigiene2()) ) );

                            clock++;

                            Thread.sleep(Var.GetDeltaTime());
                        }
                        return null;
                    }
                };
            }
        };
        servico.restart();
    }


}
