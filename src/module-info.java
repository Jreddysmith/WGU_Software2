module scheduling_app {

    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    exports schedule.Controllers;
    opens schedule.Controllers;
//    exports schedule.Models;
    opens schedule.Models;
    exports schedule.Models;
    opens schedule.exceptions;
    exports schedule.exceptions;

    opens schedule;
}