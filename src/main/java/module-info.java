module com.example.soutenancevinyle {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires htmlunit;
    requires org.apache.commons.lang3;
    opens com.example.soutenancevinyle to javafx.fxml;
    exports com.example.soutenancevinyle;
}