module com.rrdm.todo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.graphics;
    requires java.sql;


    opens com.rrdm.todo to javafx.fxml;
    exports com.rrdm.todo;
}