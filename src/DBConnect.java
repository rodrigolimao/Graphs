import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;

import java.sql.*;
import java.util.ArrayList;

public class DBConnect {

    private static String userName = "student";
    private static String password = "student";


    public static ArrayList<Speaker> getSpeakers() throws SQLException {
        ArrayList<Speaker> speakers = new ArrayList<>();
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try{
            //1. connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/COMP1011T2S1?useSSL=false",
                    userName, password);

            //2.  Create a statement object
            statement = conn.createStatement();

            //3.  create and execute the query
            resultSet = statement.executeQuery("SELECT * FROM speakers");

            //4.  loop over the results and add to the ArrayList
            while (resultSet.next())
            {
                Speaker newBook = new Speaker(
                            resultSet.getInt("speakerID"),
                            resultSet.getString("productName"),
                            resultSet.getString("description"),
                            resultSet.getDouble("price"),
                        new Image(resultSet.getString("image")));
                speakers.add(newBook);
            }
        }
        catch (SQLException e)
        {
            System.err.println(e);
        }
        finally {
            if (conn != null)
                conn.close();
            if (statement != null)
                statement.close();
            if (resultSet != null)
                resultSet.close();
        }
        return speakers;
    }



    public static XYChart.Series getSalesSeries(int speakerID) throws SQLException {

        XYChart.Series salesSeries = new XYChart.Series();
        Connection conn = null;
        PreparedStatement ps= null;
        ResultSet resultSet = null;

        try{
            //1. connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/COMP1011T2S1?useSSL=false",
                    userName, password);

            //2.  Create a statement object
            String sql = "SELECT * FROM sales WHERE speakerID = ?";

            ps = conn.prepareStatement(sql);

            ps.setInt(1,speakerID);

            //3.  create and execute the query
            resultSet = ps.executeQuery();

            //4.  loop over the results and add to the ArrayList
            while (resultSet.next())
            {
                salesSeries.getData().add(new XYChart.Data(resultSet.getString("monthSold"),
                                                            resultSet.getInt("unitsSold")));
            }
        }
        catch (SQLException e)
        {
            System.err.println(e);
        }
        finally {
            if (conn != null)
                conn.close();
            if (ps != null)
                ps.close();
            if (resultSet != null)
                resultSet.close();
        }
        return salesSeries;
    }
}
