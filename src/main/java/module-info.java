module com.app.datatransfer {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.graphics;
    requires java.net.http;

    requires jsch;

    opens com.app.datatransfer to javafx.fxml;
    exports com.app.datatransfer;
}