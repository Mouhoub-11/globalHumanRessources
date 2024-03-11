package dao.MySQL;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLEmployeDAO implements EmployeDAO {
    // ... autres méthodes implémentées ...

    @Override
    public List<Employe> listeEmployesAvecSalaireMoyenParEntreprise() {
        List<Employe> employes = new ArrayList<>();

        try (Connection connection = // obtenir une connexion à la base de données
             PreparedStatement statement = connection.prepareStatement(
                "SELECT e.*, en.NomEts, AVG(e.SalaireEmploye) AS SalaireMoyen " +
                "FROM Employe e " +
                "JOIN Entreprise en ON e.IdEts = en.IdEts " +
                "GROUP BY e.IdEmploye, en.IdEts")) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int idEmploye = resultSet.getInt("IdEmploye");
                    String nomEmploye = resultSet.getString("NomEmploye");
                    String prenomEmploye = resultSet.getString("PrenomEmploye");
                    double salaireEmploye = resultSet.getDouble("SalaireEmploye");
                    int ageEmploye = resultSet.getInt("AgeEmploye");
                    String telEmploye = resultSet.getString("TelEmploye");
                    int idEntreprise = resultSet.getInt("IdEts");
                    String nomEntreprise = resultSet.getString("NomEts");
                    double salaireMoyen = resultSet.getDouble("SalaireMoyen");

                    Employe employe = new Employe(idEmploye, nomEmploye, prenomEmploye, salaireEmploye, ageEmploye, telEmploye);
                    employe.setIdEntreprise(idEntreprise);
                    employe.setNomEntreprise(nomEntreprise);
                    employe.setSalaireMoyenEntreprise(salaireMoyen);

                    employes.add(employe);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gérer les exceptions SQL
        }

        return employes;
    }
}
