package scripts.template.graphics;

import java.net.URL;
import java.util.ResourceBundle;

import org.tribot.api.General;

import com.allatori.annotations.DoNotRename;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import scripts.template.graphics.GUI;

@DoNotRename
public class GUIController implements Initializable {

	private GUI gui;

	// Inits
	@FXML @DoNotRename private Button startScriptButton;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		//Put anything that you need the GUI to do when it is loaded.
	}
	
	// All events go here

	
	@FXML @DoNotRename public void startScript() {
		General.println("Enjoy Jerm's Template");
		gui.close();
	}
	
	public void setGUI(GUI gui) {
        this.gui = gui;
    }

    public GUI getGUI() {
        return this.gui;
    }
}
