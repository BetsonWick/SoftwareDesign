package ru.wa5teed.sd.aop.drawing;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import ru.wa5teed.sd.aop.profiler.MethodProfile;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class JavaFxApp extends Application {
    public static Map<Method, MethodProfile> statistics;

    public void run() {
        launch();
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Tree");
        Map<String, TreeItem<String>> nodes = new HashMap<>();
        for (Map.Entry<Method, MethodProfile> entry : statistics.entrySet()) {
            String className = entry.getKey().getDeclaringClass().getName();
            TreeItem<String> node = nodes.get(className);
            if (node == null) {
                node = new TreeItem<>(className);
            }
            node.getChildren().add(new TreeItem<>(entry.getValue().toString()));
            nodes.put(className, node);
        }
        StackPane root = new StackPane();
        root.getChildren().addAll(nodes.values().stream().map(TreeView::new).toList());
        stage.setScene(new Scene(root, 1280, 720));
        stage.show();
    }
}
