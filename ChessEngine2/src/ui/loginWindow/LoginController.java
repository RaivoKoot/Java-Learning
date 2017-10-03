package ui.loginWindow;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import ui.Main;

public class LoginController implements Initializable {

	@FXML
	JFXTextField usernameField;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	public void loginButtonClicked() {
		checkIfLoginValid(usernameField);
	}

	public void checkIfLoginValid(JFXTextField usernameField) {
		String enteredName = usernameField.getText();
		
		if (!enteredName.isEmpty()) {
			Main.launchChessboardWindow();
			Stage thisStage = (Stage) usernameField.getScene().getWindow();

			thisStage.close();
		}
	}

}
