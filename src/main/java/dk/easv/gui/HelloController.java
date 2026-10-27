package dk.easv.gui;

import dk.easv.bll.FileManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;

public class HelloController {
    @FXML
    private TextField txtFilePath;

    @FXML
    private Button btnCreateFile;

    @FXML
    private Label lblFileExistance;

    @FXML
    private Button btnCheckFileExistance;

    @FXML
    private TextField txtContentToFile;

    @FXML
    private Button btnAddContent;

    @FXML
    private Button btnDeleteFile;

    @FXML
    private Button btnReadContent;

    @FXML
    private Label lblFileContent;

    @FXML
    private Label lblErrorMessage;

    private final FileManager mFileManager = new FileManager();

    public void onCreateFile(ActionEvent actionEvent)
    {
        if(!txtFilePath.getText().isEmpty())
        {
            mFileManager.createNewFile(txtFilePath.getText());
            btnCheckFileExistance.fire();
        }
    }

    public void onFileExistanceCheck(ActionEvent actionEvent)
    {
        if(mFileManager.getFileExtension(txtFilePath.getText()).equals("txt") || mFileManager.getFileExtension(txtFilePath.getText()).equals("rtf")) {
            if (!txtFilePath.getText().isEmpty())
            {
                boolean exists = mFileManager.fileExists(txtFilePath.getText());

                if (exists)
                {
                    lblFileExistance.setText("File exists!");
                    btnAddContent.setDisable(false);
                    btnDeleteFile.setDisable(false);
                    btnReadContent.setDisable(false);
                    txtContentToFile.setDisable(false);
                    btnCreateFile.setDisable(true);
                }
                else
                {
                    lblFileExistance.setText("File does not exist!");
                    btnCreateFile.setDisable(false);
                    btnAddContent.setDisable(true);
                    btnDeleteFile.setDisable(true);
                    btnReadContent.setDisable(true);
                    txtContentToFile.setDisable(true);
                }
            }
        }
        else
        {
            txtFilePath.setText("");
            btnCreateFile.setDisable(true);
            btnAddContent.setDisable(true);
            btnDeleteFile.setDisable(true);
            btnReadContent.setDisable(true);
            txtContentToFile.setDisable(true);
            lblErrorMessage.setText("File extension not supported. .txt and .rtf are the only supported file types!");
            lblErrorMessage.setVisible(true);
        }
    }

    public void onAddContent(ActionEvent actionEvent)
    {
        if(!txtFilePath.getText().isEmpty())
        {
            if(mFileManager.fileExists(mFileManager.getFilePath(txtFilePath.getText())))
            {
                mFileManager.writeStringToFile(txtFilePath.getText(), txtContentToFile.getText());
            }
        }
    }

    public void onDeleteFile(ActionEvent actionEvent)
    {
        if(!txtFilePath.getText().isEmpty())
        {
            if(mFileManager.getFileExtension(txtFilePath.getText()).equals("txt") || mFileManager.getFileExtension(txtFilePath.getText()).equals("rtf"))
            {
                if (mFileManager.fileExists(mFileManager.getFilePath(txtFilePath.getText())))
                {
                    mFileManager.deleteFile(txtFilePath.getText());
                    btnCheckFileExistance.fire();
                }
            }
        }
    }

    public void onBrowseFiles(ActionEvent actionEvent)
    {
        final FileChooser fileChooser = new FileChooser();
        final FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Text Files", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(null);
        mFileManager.setLoadedFile(file);
        if(file != null)
        {
            boolean exists = mFileManager.fileExists(mFileManager.getFilePath());

            if (exists)
            {
                txtFilePath.setText(mFileManager.getFilePath());
            }
        }
    }

    public void onReadContent(ActionEvent actionEvent)
    {
        String content = mFileManager.readFileContent(txtFilePath.getText());
        lblFileContent.setText(content);
    }
}
