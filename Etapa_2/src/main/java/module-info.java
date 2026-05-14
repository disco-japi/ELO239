module cl.utfsm.elo.eloteltag {
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires transitive java.desktop;

    opens cl.utfsm.elo.eloteltag to javafx.fxml;

    exports cl.utfsm.elo.eloteltag;
}
