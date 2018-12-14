import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SpeakerViewController implements Initializable {

    @FXML
    private ComboBox<Speaker> speakerComboBox;

    @FXML
    private Label productLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private ImageView imageView;

    @FXML
    private BarChart<?,?> barChart;

    @FXML
    private NumberAxis numberAxis;

    @FXML
    private CategoryAxis categoryAxis;

    private XYChart.Series sales;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        speakerComboBox.setPromptText("Select a speaker...");

        try {
            speakerComboBox.getItems().addAll(DBConnect.getSpeakers());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        speakerComboBox.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> updateView());

        sales = new XYChart.Series();
        sales.setName("Units Sold");

        barChart.setTitle("Sales for 2018");
    }

    public void updateView()
    {
        Speaker speaker = speakerComboBox.getValue();
        productLabel.setText(speaker.getProductName());
        priceLabel.setText(String.format("$%.2f",speaker.getPrice()));
        descriptionLabel.setText(speaker.getDescription());
        imageView.setImage(speaker.getImage());
        try {
            updateGraph(speaker.getSpeakerID());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateGraph(int speakerID) throws SQLException {
        barChart.getData().clear();
        barChart.setAnimated(true);
        barChart.getData().addAll(DBConnect.getSalesSeries(speakerID));
    }


}
