package dao.MySQL;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLEntrepriseDAO implements EntrepriseDAO {
    // ... autres méthodes implémentées ...

    @Override
    public BigDecimal depensesTotalesSalaires() {
        BigDecimal totalSalaires = BigDecimal.ZERO;

        try (Connection connection = // obtenir une connexion à la base de données
             PreparedStatement statement = connection.prepareStatement(
                "SELECT SUM(s.MontantSalaire) AS TotalSalaires " +
                "FROM salaire s")) {

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    totalSalaires = resultSet.getBigDecimal("TotalSalaires");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gérer les exceptions SQL
        }

        return totalSalaires;
    }

    @Override
    public List<Entreprise> obtenirListeEntreprises() {
        List<Entreprise> entreprises = new ArrayList<>();

        try (Connection connection = // obtenir une connexion à la base de données
             PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM Entreprise")) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int idEntreprise = resultSet.getInt("IdEts");
                    String nomEntreprise = resultSet.getString("NomEts");
                    String localisationEntreprise = resultSet.getString("LocalisationEts");

                    Entreprise entreprise = new Entreprise(idEntreprise, nomEntreprise, localisationEntreprise);
                    entreprises.add(entreprise);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gérer les exceptions SQL
        }

        return entreprises;
    }
}

