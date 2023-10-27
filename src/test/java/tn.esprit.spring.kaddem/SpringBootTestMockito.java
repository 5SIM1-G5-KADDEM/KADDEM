package tn.esprit.spring.kaddem;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;
import tn.esprit.spring.kaddem.services.UniversiteServiceImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;
public class SpringBootTestMockito {
    @InjectMocks
    private UniversiteServiceImpl universiteService;

    @Mock
    private UniversiteRepository universiteRepository;

    @Mock
    private DepartementRepository departementRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRetrieveAllUniversites() {
        List<Universite> universites = new ArrayList<>();
        universites.add(new Universite(1, "Universite1"));
        universites.add(new Universite(2, "Universite2"));

        when(universiteRepository.findAll()).thenReturn(universites);
        List<Universite> result = universiteService.retrieveAllUniversites();

        assertEquals(2, result.size());

    }

    @Test
    public void testAddUniversite() {
        Universite universiteToAdd = new Universite(3, "NouvelleUniversite");

        when(universiteRepository.save(any(Universite.class))).thenReturn(universiteToAdd);

        Universite result = universiteService.addUniversite(universiteToAdd);

        assertEquals(universiteToAdd, result);
    }

}


