package application;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.input.MouseEvent;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class guiController {
	@FXML
	private CheckBox checkID;
	@FXML
	private CheckBox checkName;
	@FXML
	private CheckBox checkAge;
	@FXML
	private CheckBox checkGender;
	@FXML
	private CheckBox checkClass;
	@FXML
	private CheckBox checkDepartment;
	@FXML
	private CheckBox checkAdress;
	@FXML
	private TextField ID;
	@FXML
	private TextField Name;
	@FXML
	private TextField infAge;
	@FXML
	private TextField Gender;
	@FXML
	private TextField Class;
	@FXML
	private TextField Department;
	@FXML
	private TextField Address;
	@FXML
	private TextField supAge;
	@FXML
	private Button inqButton;
	@FXML
	private TextField statement;
	@FXML
	private TableView allResult;
	@FXML
	private TableColumn csid;
	@FXML
	private TableColumn csname;
	@FXML
	private TableColumn csage;
	@FXML
	private TableColumn cssex;
	@FXML
	private TableColumn cclass;
	@FXML
	private TableColumn cdepartment;
	@FXML
	private TableColumn caddress;
	
	ObservableList<student> list = FXCollections.observableArrayList(); 
	
	private String strID = "";
	private String strName = "";
	private String strInfAge = "";
	private String strSupAge = "";
	private String strGender = "";
	private String strClass = "";
	private String strDepartment = "";
	private String strAdress = "";
	
	private int[] flag = new int[7];
	private String stat = "";

	// Event Listener on Button[#inqButton].onAction
	@FXML
	public void inquireAction(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#inqButton].onMouseClicked
	@FXML
	public void clickInquire(MouseEvent event) {
		// TODO Autogenerated
		System.out.println("Click Button!");
		//System.out.println(checkID.isSelected());
		//System.out.println(checkName.indeterminateProperty());
		//System.out.println(ID.getText());
		stat = "";
		stat = stat + "select * from student where";
		boolean tmp = false;

		if (checkID.isSelected()) {
			strID = ID.getText();
			if (strID.indexOf("%") == -1) {
				stat = stat + " (sid='" + strID + "')";
			} else {
				stat = stat + " (sid like '" + strID + "')";
			}
			tmp = true;
		} 
		
		if (checkName.isSelected()) {
			strName = Name.getText();
			if (tmp == true) {
				System.out.println("666");
				stat = stat + " and";
			} else {
				tmp = true;
			}
			if (strName.indexOf("%") == -1) {
				stat = stat + " (sname='" + strName + "')";
			} else {
				stat = stat + " (sname like '" + strName + "')";
			}
		}
		
		if (checkAge.isSelected()) {
			strInfAge = infAge.getText();
			strSupAge = supAge.getText();
			if (tmp == true) {
				stat = stat + " and";
			} else {
				tmp = true;
			}
			stat = stat + " (sage>=" + strInfAge + ") and (sage<=" + strSupAge + ")";
		}
		
		if (checkGender.isSelected()) {
			strGender = Gender.getText();
			if (tmp == true) {
				stat = stat + " and";
			} else {
				tmp = true;
			}
			stat = stat + " (ssex='" + strGender + "')";
		} 
		
		if (checkClass.isSelected()) {
			strClass = Class.getText();
			if (tmp == true) {
				stat = stat + " and";
			} else {
				tmp = true;
			}
			stat = stat + " (sclass='" + strClass + "')";
		}
		if (checkDepartment.isSelected()) {
			strDepartment = Department.getText();
			if (tmp == true) {
				stat = stat + " and";
			} else {
				tmp = true;
			}
			stat = stat + " (department='" + strDepartment + "')";
		} 
		if (checkAdress.isSelected()) {
			strAdress = Address.getText();
			if (tmp == true) {
				stat = stat + " and";
			} else {
				tmp = true;
			}
			stat = stat + " (address='" + strAdress + "')";
		} 
		
		
		if (tmp == true) {
			statement.setText(stat);
			
			studentService ss = new studentService();
			ArrayList<student> result = ss.searchStudent(stat);
			
			//allResult.getItems().clear();
			showData(result);
		} else {
			statement.setText("You didn't choose anything!");
		}
		
		
	}
	
	private void configTableView() {
		csid.setCellValueFactory(new PropertyValueFactory("sid"));
		csname.setCellValueFactory(new PropertyValueFactory("sname"));
		csage.setCellValueFactory(new PropertyValueFactory("sage"));
		cssex.setCellValueFactory(new PropertyValueFactory("ssex"));
		cclass.setCellValueFactory(new PropertyValueFactory("sclass"));
		cdepartment.setCellValueFactory(new PropertyValueFactory("department"));
		caddress.setCellValueFactory(new PropertyValueFactory("address"));
	}
	
	protected void showData(ArrayList<student> a) {
		allResult.getItems().clear();
		configTableView();
		for (student x:a) {
			list.add(x);
		}
		allResult.setItems(list);
	}
}
