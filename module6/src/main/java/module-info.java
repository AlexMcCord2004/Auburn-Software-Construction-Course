module edu.au.cpsc.module6 {
    requires javafx.controls;
    requires javafx.fxml;

    opens edu.au.cpsc.module6 to javafx.fxml, javafx.graphics;
    opens edu.au.cpsc.part1 to javafx.fxml, javafx.graphics;

    exports edu.au.cpsc.module6;
}
