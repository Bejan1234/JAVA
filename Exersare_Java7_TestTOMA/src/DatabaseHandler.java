import java.sql.*;

public class DatabaseHandler {

    private static final String URL = "jdbc:sqlite:airplanes.db";

    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS airplanes(\n" + "id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + " kerosene REAL NOT NULL,\n" + " maintenance TEXT NOT NULL,\n" + " insurance TEXT NOT NULL,\n"
                + " flightCrewCost REAL NOT NULL,\n" + " airplaneType TEXT NOT NULL\n" + ");";
        try (Connection connection = DriverManager.getConnection(URL)) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void insertAirplane(Airplane airplane) {
        String sql = "INSERT INTO airplanes(kerosene, maintenance, insurance, flightCrewCost, airplaneType) VALUES(?,?,?,?,?)";

        try (Connection connection = DriverManager.getConnection(URL)) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setFloat(1, airplane.getKerosene());
            preparedStatement.setString(2, airplane.getMaintenance());
            preparedStatement.setString(3, airplane.getInsurance());
            preparedStatement.setFloat(4, airplane.getFlightCrewCost());
            preparedStatement.setString(5, airplane.getAirplaneType().name()); //parsam cu name() din enum in string!!!
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void selectAll() {
        String sql = "SELECT id, kerosene, maintenance, insurance, flightCrewCost, airplaneType FROM airplanes";
        try (Connection connection = DriverManager.getConnection(URL)) {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + "\t" + resultSet.getFloat("kerosene") + "\t"
                        + resultSet.getString("maintenance") + "\t" + resultSet.getString("insurance") + "\t" +
                        resultSet.getFloat("flightCrewCost") + "\t" + resultSet.getString("airplaneType"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteAll() {
        String deleteSql = "DELETE FROM airplanes";
        String resetSql = "DELETE FROM sqlite_sequence WHERE name = 'airplanes'";
        try (Connection connection = DriverManager.getConnection(URL)) {

            PreparedStatement deleteStmt = connection.prepareStatement(deleteSql);
            deleteStmt.executeUpdate();

            //resetare id
            PreparedStatement resetStmt = connection.prepareStatement(resetSql);
            resetStmt.executeUpdate();

            System.out.println("Toate înregistrările din tabela airplanes au fost șterse și ID-ul a fost resetat!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void updateAirplaneByType(AirplaneType airplaneType, Airplane updatedAirplane) {
        String sql = "UPDATE airplanes SET kerosene = ?, maintenance = ?, insurance = ?, flightCrewCost = ? WHERE airplaneType = ?";
        try (Connection connection = DriverManager.getConnection(URL)) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setFloat(1, updatedAirplane.getKerosene());
            preparedStatement.setString(2, updatedAirplane.getMaintenance());
            preparedStatement.setString(3, updatedAirplane.getInsurance());
            preparedStatement.setFloat(4, updatedAirplane.getFlightCrewCost());
            preparedStatement.setString(5, airplaneType.name());
            preparedStatement.executeUpdate();
            System.out.println("Records updated for airplaneType: " + airplaneType.name());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void deleteAirplaneByType(AirplaneType airplaneType) {
        String sql = "DELETE FROM airplanes WHERE airplaneType = ?";
        try (Connection connection = DriverManager.getConnection(URL)) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, airplaneType.name());
            preparedStatement.executeUpdate();
            System.out.println("Records deleted for airplaneType: " + airplaneType.name());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
