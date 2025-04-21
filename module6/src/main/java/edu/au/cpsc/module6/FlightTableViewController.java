package edu.au.cpsc.module6;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.transformation.SortedList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.DayOfWeek;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FlightTableViewController {

    @FXML private TableView<ScheduledFlight> flightTableView;
    @FXML private TableColumn<ScheduledFlight, String> flightDesignatorColumn, departureColumn, arrivalColumn, daysColumn;

    public void initialize() {
        flightDesignatorColumn.setCellValueFactory(new PropertyValueFactory<>("flightDesignator"));
        departureColumn.setCellValueFactory(new PropertyValueFactory<>("departureAirportIdent"));
        arrivalColumn.setCellValueFactory(new PropertyValueFactory<>("arrivalAirportIdent"));
        daysColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
                formatDays(cellData.getValue().getDaysOfWeek())
        ));

        flightTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> tableSelectionChanged());
    }

    public void showFlights(List<ScheduledFlight> flights) {
        SortedList<ScheduledFlight> sortedList = new SortedList<>(FXCollections.observableArrayList(flights));
        sortedList.setComparator(
                Comparator.comparing(ScheduledFlight::getFlightDesignator, Comparator.nullsLast(String::compareTo))
        );
        flightTableView.setItems(sortedList);
        sortedList.comparatorProperty().bind(flightTableView.comparatorProperty());
        flightTableView.refresh();
    }

    public void onFlightSelectionChanged(EventHandler<FlightSelectedEvent> handler) {
        flightTableView.addEventHandler(FlightSelectedEvent.FLIGHT_SELECTED, handler);
    }

    private void tableSelectionChanged() {
        ScheduledFlight selected = flightTableView.getSelectionModel().getSelectedItem();
        FlightSelectedEvent event = new FlightSelectedEvent(FlightSelectedEvent.FLIGHT_SELECTED, selected);
        flightTableView.fireEvent(event);
    }

    public void select(ScheduledFlight flight) {
        flightTableView.getSelectionModel().select(flight);
    }

    private String formatDays(Set<DayOfWeek> days) {
        return days.stream().sorted().map(day -> switch (day) {
            case MONDAY -> "M";
            case TUESDAY -> "T";
            case WEDNESDAY -> "W";
            case THURSDAY -> "R";
            case FRIDAY -> "F";
            case SATURDAY -> "S";
            case SUNDAY -> "U";
        }).collect(Collectors.joining());
    }

    public static class FlightSelectedEvent extends Event {
        public static final EventType<FlightSelectedEvent> FLIGHT_SELECTED = new EventType<>(Event.ANY, "FLIGHT_SELECTED");
        private final ScheduledFlight selectedFlight;

        public FlightSelectedEvent(EventType<? extends Event> eventType, ScheduledFlight selectedFlight) {
            super(eventType);
            this.selectedFlight = selectedFlight;
        }

        public ScheduledFlight getSelectedFlight() {
            return selectedFlight;
        }
    }
}
