package tn.esprit.spring.kaddem;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;
import tn.esprit.spring.kaddem.services.UniversiteServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class SprintBootWithUnitTestMockito {

@Mock
    UniversiteRepository universiteRepository;

@InjectMocks
    UniversiteServiceImpl universiteService;

    Universite universite = Universite.builder().nomUniv("Esprit").build();
    List<Universite> list= new ArrayList<Universite>() {
        {
            add(Universite.builder().nomUniv("Esprit").build());
            add(Universite.builder().nomUniv("ISETB").build());
        }
    };

    @Test
    public void testAddUniversite() {
        // Arrange
        Universite universite = new Universite("Esprit");
        when(universiteRepository.save(any(Universite.class))).thenReturn(universite);

        // Act
        Universite result = universiteService.addUniversite(universite);

        // Assert
        assertNotNull(result);
        assertEquals("Esprit", result.getNomUniv());
        verify(universiteRepository, times(1)).save(universite);
    }

    @Test
    public void testRetrieveUniversite() {
        // Arrange
        Integer id = 1;
        Universite universite = new Universite(id, "Esprit");
        when(universiteRepository.findById(id)).thenReturn(Optional.of(universite));

        // Act
        Universite result = universiteService.retrieveUniversite(id);

        // Assert
        assertNotNull(result);
        assertEquals(id, result.getIdUniv());
        assertEquals("Esprit", result.getNomUniv());
        verify(universiteRepository, times(1)).findById(id);
    }

    @Test
    public void testRetrieveAllUniversites() {
        // Arrange
        when(universiteRepository.findAll()).thenReturn(getMockUniversites());

        // Act
        List<Universite> result = universiteService.retrieveAllUniversites();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(universiteRepository, times(1)).findAll();
    }

    // Helper method to create mock Universite objects
    private List<Universite> getMockUniversites() {
        List<Universite> universites = new ArrayList<>();
        universites.add(new Universite(1, "Esprit"));
        universites.add(new Universite(2, "ISETB"));
        return universites;
    }


}
