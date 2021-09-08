package com.app.datatransfer.animations;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Shake {
    private TranslateTransition transition;

    public Shake(Node node) {
        transition = new TranslateTransition(Duration.millis(300), node);
        transition.setFromX(0f);
        transition.setByX(-20f);
        transition.setCycleCount(2);
        transition.setAutoReverse(true);
    }

    public void play() {
        transition.playFromStart();
    }
}
