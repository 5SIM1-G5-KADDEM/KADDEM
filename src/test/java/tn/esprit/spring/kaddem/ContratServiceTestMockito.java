package tn.esprit.spring.kaddem;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Specialite;
import tn.esprit.spring.kaddem.services.ContratServiceImpl;
import tn.esprit.spring.kaddem.services.IContratService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class ContratServiceTestMockito {

    @Autowired
    private ContratServiceImpl contratService;

    @Test
    public void testAddContrat() {

        Contrat contratTest = new Contrat(
                new Date(),
                new Date(),
                Specialite.IA,
                false,
                12345);

        Contrat result = contratService.addContrat(contratTest);

        Assertions.assertNotEquals(null, result);
    }

    @Test
    public void testGetContrats() {

        List<Contrat> contratList = new ArrayList<>();

        contratList = contratService.retrieveAllContrats();

        Assertions.assertNotEquals(0, contratList.size());
    }
}
