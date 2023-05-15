package marktplaats.controller;

import marktplaats.model.Advertentie;
import marktplaats.model.Bieding;
import marktplaats.model.BiedingDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BiedingControllerTest {

    @Mock
    private BiedingDAO biedingDAO;

    @InjectMocks
    private BiedingController target;

    @Test
    void alsInsertBodLagerOfGelijkAanBestaandeBiedingenDanException() {
        // given
        Advertentie advertentieFiets1 = Advertentie.builder().id(1).build();
        Bieding bodFiets10 = Bieding.builder().advertentie(advertentieFiets1).bedragBieding(BigDecimal.valueOf(10)).build();
        Bieding bodFiets20 = Bieding.builder().advertentie(advertentieFiets1).bedragBieding(BigDecimal.valueOf(20)).build();
        Bieding bodFiets30 = Bieding.builder().advertentie(advertentieFiets1).bedragBieding(BigDecimal.valueOf(30)).build();

        when(biedingDAO.vindBiedingenPerAdvertentie(1)).thenReturn(List.of(bodFiets10, bodFiets20, bodFiets30));

        //then
        Assertions.assertThrows(IllegalArgumentException.class, /* when */() -> target.insert(bodFiets10));

    }

}