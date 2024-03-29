package list.listreader;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class UserController {
    private ListRegister listRegister = ListRegister.getRegister();
    @FXML
    private TableView<List> tableView;
    @FXML
    private TableColumn<List, String> contents;
    @FXML
    private TableColumn<List, Boolean> check;
    @FXML
    private TableColumn<List, CheckBox> checkBox;
    @FXML
    private Button openFile;
    @FXML
    public Button refreshButton;
    @FXML
    public Button searchButton;
    @FXML
    private TextField searchField;
    private List list;

    ObservableList<List> observableList = FXCollections.observableArrayList(listRegister.getList());

    public void init(List list) {
        this.list = list;
        //gridPane.add(directoryChooser,4,0);
        contents.setCellValueFactory(new PropertyValueFactory<>("contentsProperty"));
        check.setCellValueFactory(new PropertyValueFactory<>("checkProperty"));
        checkBox.setCellValueFactory(new PropertyValueFactory<>("checkBox"));
        tableView.setItems(observableList);
        tableView.refresh();
        initBinding();
    }

    private void initBinding() {
        for (int i = 0; 10 < i; i++) {
            checkBox.getCellObservableValue(i).getValue().selectedProperty().bindBidirectional(list.getCheckBoxProperty().selectedProperty());
        }
    }

    private void updateModel(List newList) {
        newList.setContentsProperty(newList.getContentsProperty());
        newList.setCheckProperty(newList.getCheckProperty());
    }

    /**
     * Once the Filter-Button is clicked, when a number is entered with an operator selected, only the qualifying years will be shown.
     */
    @FXML
    protected void onFilterContents(ActionEvent event) {
        //assertTrue(src.matches("(?i).*" + dest + ".*"));
        System.out.println("onFilterContents method active");
        contents.getCellValueFactory();
        observableList = FXCollections.observableArrayList(listRegister.getList());
        tableView.setItems(observableList);

        String contents = searchField.textProperty().getValue();

        // same - probably easier to read ig
        observableList.removeIf(entry -> !entry.getContentsProperty().toLowerCase().contains(contents.toLowerCase()));

        for (int i = 0; i < observableList.size(); i++) {
            /*if (!(observableList.get(i).getContentsProperty().contains(contents))) {
                observableList.remove(i);
                i--;
            }

             */
            if (!(observableList.get(i).getContentsProperty().toLowerCase().contains(contents.toLowerCase()))) {
                //observableList.remove(i);
                i--;
            }
        }
        tableView.refresh();
    }

    /**
     * When the Refresh-Button is clicked, the register gets loaded and all filters are reset.
     */
    @FXML
    protected void onRefreshButton(ActionEvent event) {
        listRegister.loadRegister();
        contents.getCellValueFactory();
        check.getCellValueFactory();
        ObservableList<List> observableList = FXCollections.observableArrayList(listRegister.getList());
        tableView.setItems(observableList);
    }

    @FXML
    protected void onSave(ActionEvent event) throws IOException {
        //Sets the first boolean in the Check Column to false when saved
        System.out.println("Save clicked ..." + event.getSource());
        ObservableList<List> observableList = FXCollections.observableArrayList(listRegister.getList());
        tableView.setItems(observableList);

        for (int i = 0; 10 < i; i++) {
            checkBox.getCellObservableValue(i).getValue().selectedProperty().bindBidirectional(list.getCheckBoxProperty().selectedProperty());
        }
        CsvManager.setData(tableView.getSelectionModel().getFocusedIndex(), false, listRegister.getList());

        //File configFile = new File(App.MODEL_FILE_PATH);
        //App.JSON_MAPPER.writerWithDefaultPrettyPrinter().writeValue(configFile, list);

        onRefreshButton(event);
        tableView.refresh();
    }

    @FXML
    protected void onOpenFile() {
        // do not forget the mechanic
        // lg elftausend
    }
}
