package tn.esprit.spring.kaddem;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Specialite;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.services.ContratServiceImpl;
import tn.esprit.spring.kaddem.services.IContratService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class ContratServiceTestMockito {



    @InjectMocks
    private ContratServiceImpl contratService;

    @Mock
    private ContratRepository contratRepository;


    @Test
    public void testUpdateContrat() {
        Contrat contratToUpdate = new Contrat();
        when(contratRepository.save(contratToUpdate)).thenReturn(contratToUpdate);

        Contrat updatedContrat = contratService.updateContrat(contratToUpdate);

        verify(contratRepository, times(1)).save(contratToUpdate);
        assertEquals(contratToUpdate, updatedContrat);
    }

    @Test
    public void testRemoveContrat() {
        Integer idContratToRemove = 1;
        Contrat contratToRemove = new Contrat();
        when(contratRepository.findById(idContratToRemove)).thenReturn(Optional.of(contratToRemove));

        contratService.removeContrat(idContratToRemove);

        verify(contratRepository, times(1)).delete(contratToRemove);
    }

    @Test
    public void testAddContrat() {
        Contrat contratTest = new Contrat(
                new Date(),
                new Date(),
                Specialite.IA,
                false,
                12345);

        // Utiliser Mockito pour simuler le comportement du contratRepository
        when(contratRepository.save(contratTest)).thenReturn(contratTest);

        // Appeler la méthode que vous voulez tester
        Contrat result = contratService.addContrat(contratTest);

        // Vérifier le résultat
        assertEquals(contratTest, result);
    }

    @Test
    public void testGetContrats() {
        List<Contrat> contratList = new ArrayList<>();

        // Créer une liste factice de contrats pour simuler le comportement du contratRepository
        List<Contrat> fakeContratList = new ArrayList<>();
        fakeContratList.add(new Contrat());
        fakeContratList.add(new Contrat());
        fakeContratList.add(new Contrat());

        // Utiliser Mockito pour simuler le comportement du contratRepository
        when(contratRepository.findAll()).thenReturn(fakeContratList);

        // Appeler la méthode que vous voulez tester
        contratList = contratService.retrieveAllContrats();

        // Vérifier le résultat
        assertEquals(fakeContratList.size(), contratList.size());
    }

    @Test
    public void testRetrieveContratById() {
        // Créer un contrat factice pour le résultat attendu
        Contrat expectedContrat = new Contrat();
        expectedContrat.setIdContrat(1); // Assurez-vous que l'ID du contrat factice correspond à l'ID attendu

        // Utiliser Mockito pour simuler le comportement du contratRepository
        when(contratRepository.findById(1)).thenReturn(Optional.of(expectedContrat));

        // Appeler la méthode que vous voulez tester
        Contrat result = contratService.retrieveContrat(1);

        // Vérifier le résultat
        assertEquals(expectedContrat, result);
    }
}
