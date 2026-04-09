package game.ui;

import game.engine.GameContext;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * JavaFX entry: same {@link GameContext} loop as CLI with {@link JavaFxPlayerCommandSource}.
 */
public class BossAssassinatorApp extends Application {

    @Override
    public void start(Stage stage) {
        TextArea log = new TextArea();
        log.setEditable(false);
        log.setWrapText(true);
        log.getStyleClass().add("log-area");

        FlowPane actionBar = new FlowPane();
        actionBar.setPadding(new Insets(8));
        actionBar.getStyleClass().add("action-bar");
        actionBar.setHgap(8);
        actionBar.setVgap(8);

        Label header = new Label("Boss Assassinator RPG");
        header.getStyleClass().add("title-label");
        Label tagline = new Label("Same session loop as the CLI — play from the log and action bar.");
        tagline.getStyleClass().add("subtitle-label");
        tagline.setWrapText(true);

        VBox top = new VBox(4, header, tagline);
        top.setPadding(new Insets(12, 16, 10, 16));
        top.getStyleClass().add("header-box");

        Label logCaption = new Label("Session log");
        logCaption.getStyleClass().add("panel-caption");
        VBox logStack = new VBox(8, logCaption, log);
        VBox.setVgrow(log, Priority.ALWAYS);
        logStack.setFillWidth(true);

        BorderPane root = new BorderPane();
        root.setTop(top);
        BorderPane.setMargin(logStack, new Insets(0, 16, 8, 16));
        root.setCenter(logStack);
        root.setBottom(actionBar);
        root.getStyleClass().add("root-pane");

        Scene scene = new Scene(root, 820, 600);
        var css = getClass().getResource("/game/ui/theme.css");
        if (css != null) {
            scene.getStylesheets().add(css.toExternalForm());
        }

        JavaFxPlayerCommandSource commands = new JavaFxPlayerCommandSource(actionBar);
        GameContext game = new GameContext();
        game.setInteractive(true);
        game.setPlayerCommands(commands);

        PrintStream originalOut = System.out;
        PrintStream guiOut = new PrintStream(new TextAreaOutputStream(log), true, StandardCharsets.UTF_8);
        System.setOut(guiOut);

        Thread gameThread = new Thread(() -> {
            try {
                game.startGame();
            } finally {
                System.setOut(originalOut);
                Platform.runLater(() -> {
                    actionBar.getChildren().clear();
                    Label done = new Label("Run finished.");
                    done.getStyleClass().add("subtitle-label");
                    actionBar.getChildren().add(done);
                });
            }
        }, "boss-game-loop");
        gameThread.setDaemon(true);
        gameThread.start();

        stage.setTitle("Boss Assassinator RPG");
        stage.setMinWidth(640);
        stage.setMinHeight(480);
        stage.setScene(scene);
        stage.setOnCloseRequest(e -> gameThread.interrupt());
        stage.show();
    }

    /**
     * Buffers stdout until a line break, then appends on the FX thread.
     */
    private static final class TextAreaOutputStream extends OutputStream {

        private final StringBuilder buf = new StringBuilder();
        private final TextArea log;

        TextAreaOutputStream(TextArea log) {
            this.log = log;
        }

        @Override
        public synchronized void write(int b) {
            if (b == '\n') {
                flushBuffer();
            } else if (b != '\r') {
                buf.append((char) b);
            }
        }

        @Override
        public synchronized void flush() {
            if (buf.length() > 0) {
                flushBuffer();
            }
        }

        private void flushBuffer() {
            String line = buf.toString();
            buf.setLength(0);
            if (!line.isEmpty()) {
                Platform.runLater(() -> {
                    log.appendText(line + "\n");
                    log.setScrollTop(Double.MAX_VALUE);
                });
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
