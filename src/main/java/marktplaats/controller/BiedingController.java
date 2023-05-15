package marktplaats.controller;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import marktplaats.model.Bieding;
import marktplaats.model.BiedingDAO;

import java.util.List;

@Slf4j
@Singleton
public class BiedingController {

    @Inject
    private BiedingDAO biedingDAO;

    public void insert(Bieding bieding) {

        if (bieding.getBieder().getId() == bieding.getAdvertentie().getAanbieder().getId()) {
            throw new IllegalArgumentException("Je kan niet bieden op je eigen advertentie.");
        }

        List<Bieding> lijstBiedingenPerAdvertentie = biedingDAO.vindBiedingenPerAdvertentie(bieding.getAdvertentie().getId());
        for (int i = 0; i < lijstBiedingenPerAdvertentie.size(); i++) {
            if (lijstBiedingenPerAdvertentie.get(i).getBedragBieding().compareTo(bieding.getBedragBieding()) >= 0) {
                throw new IllegalArgumentException("Het bod dient hoger te zijn dan bestaande biedingen.");
            }
        }
        biedingDAO.insert(bieding);
    }
}
