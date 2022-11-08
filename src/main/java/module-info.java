module com.example.soutenancevinyle {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires htmlunit;
    requires mysql.connector.java;
    requires org.apache.commons.lang3;
    requires java.desktop;
    requires java.sql;

    requires sib.api.v3.sdk;

    opens com.example.soutenancevinyle to javafx.fxml;
    exports com.example.soutenancevinyle;
}