package edu.au.cpsc.module3;

import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.web.WebView;

public class AirportController {

    @FXML private TextField airportIdField;
    @FXML private TextField iataCodeField;
    @FXML private TextField typeField;
    @FXML private TextField nameField;
    @FXML private TextField elevationField;
    @FXML private TextField countryField;
    @FXML private TextField regionField;
    @FXML private TextField municipalityField;
    @FXML private TextField localCodeField;

    @FXML private Button searchButton;

    @FXML private Label nameLabel;
    @FXML private Label locationLabel;
    @FXML private Label elevationLabel;
    @FXML private Label coordinatesLabel;

    @FXML private WebView weatherWebView;

    private List<Airport> airports;

    @FXML
    public void initialize() {
        try {
            airports = Airport.readAll();
        } catch (Exception e) {
            System.out.println("Error reading airports: " + e.getMessage());
            e.printStackTrace();
        }

        searchButton.setOnAction(e -> handleSearch());
    }

    @FXML
    public void handleSearch() {
        String iataCode = iataCodeField.getText().trim();
        String id = airportIdField.getText().trim();

        Airport found = null;

        // First try IATA, prioritizing US entries
        if (!iataCode.isEmpty()) {
            found = airports.stream()
                    .filter(a -> iataCode.equalsIgnoreCase(a.getIataCode()))
                    .filter(a -> "US".equalsIgnoreCase(a.getIsoCountry())) // prioritize US match
                    .findFirst()
                    .orElse(null);
        }

        // Then try Ident if needed
        if (found == null && !id.isEmpty()) {
            found = airports.stream()
                    .filter(a -> id.equalsIgnoreCase(a.getIdent()))
                    .findFirst()
                    .orElse(null);
        }

        if (found != null) {
            updateAirportInfo(found);
        } else {
            nameLabel.setText("No airport found.");
            locationLabel.setText("");
            elevationLabel.setText("");
            coordinatesLabel.setText("");
            weatherWebView.getEngine().load("about:blank");
        }
    }

    private void updateAirportInfo(Airport airport) {
        System.out.println("🛬 Found airport: " + airport);

        // Update result display labels
        nameLabel.setText("Name: " + airport.getName());
        locationLabel.setText("Location: " + airport.getMunicipality() + ", " +
                airport.getIsoRegion() + ", " + airport.getIsoCountry());
        elevationLabel.setText("Elevation: " + airport.getElevationFt() + " ft");
        coordinatesLabel.setText("Coordinates: " + airport.getLatitude() + ", " + airport.getLongitude());

        // Update form text fields
        typeField.setText(airport.getType());
        nameField.setText(airport.getName());
        elevationField.setText(airport.getElevationFt() != null ? String.valueOf(airport.getElevationFt()) : "");
        countryField.setText(airport.getIsoCountry());
        regionField.setText(airport.getIsoRegion());
        municipalityField.setText(airport.getMunicipality());
        localCodeField.setText(airport.getLocalCode());
        iataCodeField.setText(airport.getIataCode());

        // Update the map
        updateWeatherView(airport.getLatitude(), airport.getLongitude());
    }
    private void updateWeatherView(double latitude, double longitude) {
        String url = String.format(
                "https://embed.windy.com/embed2.html?lat=%.6f&lon=%.6f&zoom=10&level=surface" +
                        "&overlay=wind&menu=true&message=true&marker=true&calendar=now&pressure=true" +
                        "&type=map&location=coordinates&detail=true&detailLat=%.6f&detailLon=%.6f" +
                        "&metricWind=default&metricTemp=default&radarRange=-1",
                latitude, longitude, latitude, longitude
        );

        // Fake a real browser to unlock full Windy experience
        weatherWebView.getEngine().setUserAgent(
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 " +
                        "(KHTML, like Gecko) Chrome/118.0.5993.89 Safari/537.36"
        );

        weatherWebView.getEngine().load(url);
        System.out.println("🌍 Loading enhanced Windy map at " + url);
    }

}
