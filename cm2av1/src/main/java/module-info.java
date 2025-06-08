module com.prototype.cm2a {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;
    requires org.postgresql.jdbc;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens com.prototype.cm2a to javafx.fxml;
    opens com.prototype.cm2a.controllers.principalwindows to javafx.fxml;
    opens com.prototype.cm2a.controllers.history to javafx.fxml;
    opens com.prototype.cm2a.controllers.pacient to javafx.fxml;
    opens com.prototype.cm2a.controllers.user to javafx.fxml;
    opens com.prototype.cm2a.controllers.billing to javafx.fxml;
    opens com.prototype.cm2a.controllers.medicine to javafx.fxml;
    opens com.prototype.cm2a.controllers.parameter to javafx.fxml;
    opens com.prototype.cm2a.database to javafx.fxml;
    opens com.prototype.cm2a.components to javafx.fxml;
    opens com.prototype.cm2a.utils to javafx.fxml;
    opens com.prototype.cm2a.models to javafx.base, javafx.controls;
    exports com.prototype.cm2a;
}