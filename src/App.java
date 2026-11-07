import controller.GpaController;
import javax.swing.SwingUtilities;
import model.GpaModel;
import view.GpaView;

public class App {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GpaModel model = GpaModel.getInstance();
            GpaView view = new GpaView();

            new GpaController(model, view);

            view.setVisible(true);
        });
    }
}

