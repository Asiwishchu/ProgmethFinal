package gui;

import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SideBar {
    public VBox initializeSidebar(){
        double topMargin = 10;
        double rightMargin = 20;
        double bottomMargin = 30;
        double leftMargin = 40;



        VBox sideBarDiv = new VBox();
        sideBarDiv.setId("sideBarDiv");
        sideBarDiv.getStylesheets().add(getClass().getResource("/side_bar.css").toExternalForm());
        Insets margins = new Insets(topMargin, rightMargin, bottomMargin, leftMargin);
        VBox.setMargin(sideBarDiv, margins);

        Rectangle rect1 = new Rectangle(250, 50, Color.BLUE);
        Rectangle rect2 = new Rectangle(250, 50, Color.GREEN);
        Rectangle rect3 = new Rectangle(250, 50, Color.RED);

        sideBarDiv.getChildren().addAll(rect1,rect2,rect3);

        return sideBarDiv;
    }
}
