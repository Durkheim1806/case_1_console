package marktplaats;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import marktplaats.view.LoginScherm;
import org.jboss.weld.environment.se.Weld;

@Slf4j
@Singleton
public class Applicatie {
    @Inject
    LoginScherm loginScherm;

    public static void main(String[] args) {

        try (SeContainer container = Weld.newInstance().initialize()) {
            Applicatie applicatie = container.select(Applicatie.class).get();
            applicatie.run();
        }
    }

    private void run() {


        this.loginScherm.start();

    }
}