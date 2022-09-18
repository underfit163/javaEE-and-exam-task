import java.io.IOException;
import java.sql.*;

public class Main {

    public static void selectMus(Connection conn) {
        try (Statement stat = conn.createStatement()) {
            ResultSet rs = stat.executeQuery("SELECT * FROM Musician");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + ", " + rs.getString(2));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        ConnectBD connectBD = new ConnectBD();
        try (Connection connection = connectBD.getConnection()) {
            Statement stat = connection.createStatement();
            ResultSet rs = stat.executeQuery("Select a.name_album, c.name_composition, minc.d as duration " +
                    "From (Select c.id_album, min(c.duration_composition) as d " +
                    "from Composition as c " +
                    "Where c.duration_composition>5 " +
                    "Group by c.id_album) as minc, Album as a, Composition as c " +
                    "Where minc.d = c.duration_composition and minc.id_album=a.id_album and minc.id_album=c.id_album;");
            ResultSetMetaData rsmd = rs.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                System.out.print(rsmd.getColumnLabel(i) + " ");
            }
            System.out.println();
            while (rs.next()) {
                System.out.println(rs.getString(1) + ",       " + rs.getString(2) + ",      " + rs.getInt(3));
            }

            PreparedStatement ps;
            System.out.println("Все исполнители:");
            selectMus(connection);
            String s1 = "INSERT INTO Musician VALUES (?, ?)";
            ps = connection.prepareStatement(s1);
            ps.setInt(1, 1111111111);
            ps.setString(2, "ALEXANDER");
            ps.executeUpdate();
            System.out.println("Исполнители после добавления:");
            selectMus(connection);

            String s2 = "UPDATE Musician SET name_musician=? WHERE name_musician=?";
            ps = connection.prepareStatement(s2);
            ps.setString(1, "GENA");
            ps.setString(2, "ALEXANDER");
            ps.executeUpdate();
            System.out.println("Исполнители после изменения:");
            selectMus(connection);

            String s3 = "Delete from Musician Where name_musician=?";
            ps = connection.prepareStatement(s3);
            ps.setString(1, "GENA");
            ps.executeUpdate();
            System.out.println("Исполнители после удаления:");
            selectMus(connection);
        } catch (IOException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
