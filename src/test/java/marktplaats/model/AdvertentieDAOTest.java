package marktplaats.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdvertentieDAOTest {

    @Mock
    private EntityManager emMock;

    @Mock
    private EntityTransaction tMock;

    @InjectMocks
    private AdvertentieDAO target;

    @Test
    void whenSelectWithValidIDThenPersonIsReturned() {
        // given
        when(emMock.getTransaction()).thenReturn(tMock);


    }

}