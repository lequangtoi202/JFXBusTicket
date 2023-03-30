module com.lqt.oubus {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    opens com.lqt.oubus to javafx.fxml;
    exports com.lqt.oubus;
    exports com.lqt.pojo;
    exports com.lqt.service;
    exports com.lqt.utils;
}
