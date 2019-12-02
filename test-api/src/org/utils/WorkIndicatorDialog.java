package org.utils;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public final class WorkIndicatorDialog<P, R> {

    private Task<R> taskWorker;

    private final ProgressIndicator progressIndicator = new ProgressIndicator(ProgressIndicator.INDETERMINATE_PROGRESS);
    private final Stage dialog = new Stage();
    private final Label label = new Label();
    private final Label subLabel = new Label();
    private final BorderPane mainPane = new BorderPane();
    private final Scene scene = new Scene(mainPane, 570, 160, Color.WHITE);
    private final VBox vbox = new VBox();

    /** Placing a listener on this list allows to get notified BY the result when the task has finished. */
    public ObservableList<R> resultNotificationList = FXCollections.observableArrayList();

    public R resultValue;

    public WorkIndicatorDialog(Window owner, String label) {
        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.initStyle(StageStyle.TRANSPARENT);
        dialog.initOwner(owner);
        dialog.setResizable(false);
        this.label.setText(label);
        this.label.setWrapText(true);
        this.subLabel.setWrapText(true);
    }

    public void addTaskEndNotification(Consumer<R> c) {
        resultNotificationList.addListener((ListChangeListener<? super R>) n -> {
            resultNotificationList.clear();
            c.accept(resultValue);
        });
    }

    public void execute(P parameter, BiFunction<P, Consumer<String>, R> func) {
        setupDialog();
        setupWorkerThread(parameter, func);
    }

    private void setupDialog() {
        vbox.setSpacing(15);
        vbox.setAlignment(Pos.CENTER);
        vbox.setMinSize(530, 120);
        vbox.getChildren().addAll(label, subLabel, progressIndicator);

        BorderPane dropShadowPane = new BorderPane();
        dropShadowPane.getStyleClass().add("content");

        dropShadowPane.setCenter(vbox);
        mainPane.setCenter(dropShadowPane);

        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add(getClass().getResource("workIndicator.css").toExternalForm());
        dialog.setScene(scene);

        dialog.show();
    }

    private void setupWorkerThread(P parameter, BiFunction<P, Consumer<String>, R> func) {

        taskWorker = new Task<R>() {
            @Override
            public R call() {
                long start = System.currentTimeMillis();
                R result = func.apply(parameter, this::updateMessage);
                // ensure the UI has some time to print our status
                if(System.currentTimeMillis() - start < 1000) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                return result;
            }
        };

        EventHandler<WorkerStateEvent> eh = event -> {
            progressIndicator.progressProperty().unbind();
            dialog.close();
            try {
                resultValue = taskWorker.get();
                resultNotificationList.add(resultValue);
            } catch (ExecutionException e) {
                throw Optional.of(e)
                        .map(Throwable::getCause)
                        .filter(RuntimeException.class::isInstance)
                        .map(RuntimeException.class::cast)
                        .orElseThrow(() -> new RuntimeException(e.getCause()));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        taskWorker.setOnSucceeded(eh);
        taskWorker.setOnFailed(eh);
        taskWorker.messageProperty().addListener((observable, oldValue, newValue) -> subLabel.setText(newValue));

        new Thread(taskWorker).start();
    }

    public R getResultValue() {
        return resultValue;
    }
}