package com.rrdm.todo;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.io.IOException;

public class CardListCell extends ListCell<Todo> {

    static TodoDAO todoDAO;

    @Override
    protected void updateItem(Todo item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setGraphic(null);
        } else {
            try {
                // Load FXML for the card
                FXMLLoader loader = new FXMLLoader(getClass().getResource("card-view.fxml"));
                AnchorPane card = loader.load();

                // Get the card controller and set the data
                CardController controller = loader.getController();
                controller.setText(item);

                switch (item.getPriority()) {
                    case HIGH ->
                            card.setBackground(new Background(new BackgroundFill(Color.LIGHTPINK, CornerRadii.EMPTY, null)));
                    case MED ->
                            card.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, null)));
                    default ->
                            card.setBackground(new Background(new BackgroundFill(Color.LIGHTSALMON, CornerRadii.EMPTY, null)));
                }

                setGraphic(card); // display the card in the cell
            } catch (IOException e) {
                e.printStackTrace();
                setGraphic(null);
            }
        }
    }
}
