package dao;

import java.math.BigDecimal;
import java.util.List;

public interface EntrepriseDAO {
    BigDecimal depensesTotalesSalaires();
    List<Entreprise> obtenirListeEntreprises();
    // Autres méthodes spécifiques à la gestion des entreprises
}
