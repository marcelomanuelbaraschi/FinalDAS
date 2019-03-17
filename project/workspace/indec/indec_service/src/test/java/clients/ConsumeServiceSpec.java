package clients;
import clients.exceptions.ClientException;
import contract.SupermercadosServiceContract;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repositories.CadenaServiceRepository;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class ConsumeServiceSpec {
    protected static final Logger log = LoggerFactory.getLogger(ConsumeServiceSpec.class);
    @Test
    public void test_axis_one_health(){ //Walmart
            final Optional<SupermercadosServiceContract> maybeClient = CadenaServiceRepository.getInstance().query(1L);
            assertTrue(maybeClient.isPresent());
            try {
                final String confirmation = maybeClient.get().health("INDEC");
                assertEquals(confirmation,"OK");
            } catch (ClientException e) {
                log.error("Client Exception:{}",e.getMessage());

            }

        }
}