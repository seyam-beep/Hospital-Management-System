package hospital.gui;

import hospital.model.Patient;
import hospital.exception.HospitalException;
import hospital.service.PatientManager;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class HospitalGUI extends JFrame {
	private PatientManager manager;

	// --- CLASS-LEVEL FIELD DECLARATIONS ---
	private JTextField idField;
	private JTextField nameField;
	private JTextField ageField;
	private JTextField diseaseField;
	private JTextField phoneField;
	private JTextField doctorField;
	private JTextField roomField;
	private JComboBox<String> genderBox;
	private JTable patientTable;
	private DefaultTableModel tableModel;

	// --- BUTTONS DECLARED AT CLASS LEVEL SO CONSTRUCTOR CAN SEE THEM ---
	private JButton insertButton;
	private JButton getButton;
	private JButton updateButton;
	private JButton deleteButton;
	private JButton clearButton;

	public HospitalGUI(){
		manager = new PatientManager();
		setTitle("Hospital Management System");
		setSize(900, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		createGUI();

		// --- BUTTON ACTION LISTENERS INSIDE THE CONSTRUCTOR ---
		insertButton.addActionListener(e -> {
			try {
				String id = idField.getText().trim();
				String name = nameField.getText().trim();
				int age = Integer.parseInt(ageField.getText().trim());
				String gender = (String) genderBox.getSelectedItem();
				String disease = diseaseField.getText().trim();
				String phone = phoneField.getText().trim();
				String doctor = doctorField.getText().trim();
				String room = roomField.getText().trim();

				Patient patient = new Patient(
					id, name, age, gender,
					disease, doctor, phone, room
				);

				manager.addPatient(patient);

				JOptionPane.showMessageDialog(this, "Patient added successfully!");

				refreshTable();
				clearFields();

			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(this, "Age must be a valid number!");
			} catch (HospitalException ex) {
				JOptionPane.showMessageDialog(this, ex.getMessage());
			}
		});

		getButton.addActionListener(e -> {
			String id = idField.getText().trim();
			Patient patient = manager.findPatient(id);

			if (patient == null) {
				JOptionPane.showMessageDialog(this, "Patient not found!");
				return;
			}

			nameField.setText(patient.getName());
			ageField.setText(String.valueOf(patient.getAge()));
			genderBox.setSelectedItem(patient.getGender());
			diseaseField.setText(patient.getDisease());
			phoneField.setText(patient.getPhone());
			doctorField.setText(patient.getDoctor());
			roomField.setText(patient.getRoomNumber());
		});

		updateButton.addActionListener(e -> {
			try {
				String id = idField.getText().trim();
				String name = nameField.getText().trim();
				int age = Integer.parseInt(ageField.getText().trim());
				String gender = (String) genderBox.getSelectedItem();
				String disease = diseaseField.getText().trim();
				String phone = phoneField.getText().trim();
				String doctor = doctorField.getText().trim();
				String room = roomField.getText().trim();

				Patient updatedPatient = new Patient(
					id, name, age, gender,
					disease, doctor, phone, room
				);

				manager.updatePatient(updatedPatient);

				JOptionPane.showMessageDialog(this, "Patient updated successfully!");
				refreshTable();

			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(this, "Age must be a valid number!");
			} catch (HospitalException ex) {
				JOptionPane.showMessageDialog(this, ex.getMessage());
			}
		});

		deleteButton.addActionListener(e -> {
			String id = idField.getText().trim();

			try {
				manager.deletePatient(id);

				JOptionPane.showMessageDialog(this, "Patient deleted successfully!");
				clearFields();
				refreshTable();

			} catch (HospitalException ex) {
				JOptionPane.showMessageDialog(this, ex.getMessage());
			}
		});

		clearButton.addActionListener(e -> {
			clearFields();
		});
	}

	private void createGUI(){
		JPanel formPanel = new JPanel(new GridLayout(8,2,5,5));
		
		idField = new JTextField();
		nameField = new JTextField();
		ageField = new JTextField();
		diseaseField = new JTextField();
		phoneField = new JTextField();
		doctorField = new JTextField();
		roomField = new JTextField();
		genderBox = new JComboBox<>(new String[]{"Male", "Female", "Other"});
		
		// Form panel components
		formPanel.add(new JLabel("Patient ID:"));
		formPanel.add(idField);
		formPanel.add(new JLabel("Name:"));
		formPanel.add(nameField);
		formPanel.add(new JLabel("Age:"));
		formPanel.add(ageField);
		formPanel.add(new JLabel("Gender:"));
		formPanel.add(genderBox);
		formPanel.add(new JLabel("Disease:"));
		formPanel.add(diseaseField);
		formPanel.add(new JLabel("Phone:"));
		formPanel.add(phoneField);
		formPanel.add(new JLabel("Doctor:"));
		formPanel.add(doctorField);
		formPanel.add(new JLabel("Room Number:"));
		formPanel.add(roomField);
		
		// Button panel
		JPanel buttonPanel = new JPanel();
		insertButton = new JButton("Insert");
		buttonPanel.add(insertButton);
		getButton = new JButton("Get");
		buttonPanel.add(getButton);
		updateButton = new JButton("Update");
		buttonPanel.add(updateButton);
		deleteButton = new JButton("Delete");
		buttonPanel.add(deleteButton);
		clearButton = new JButton("Clear");
		buttonPanel.add(clearButton);
		
		// Table setup
		String[] columns = {"ID", "Name", "Age", "Gender", "Disease", "Phone", "Doctor", "Room"};
		tableModel = new DefaultTableModel(columns, 0);
		patientTable = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(patientTable);
		
		// Top section layout
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.add(formPanel, BorderLayout.CENTER);
		topPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		// Main window layout
		setLayout(new BorderLayout());
		add(topPanel, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);
	}

	// --- PROPERLY PLACED HELPER METHODS ---
	private void clearFields() {
		idField.setText("");
		nameField.setText("");
		ageField.setText("");
		diseaseField.setText("");
		phoneField.setText("");
		doctorField.setText("");
		roomField.setText("");
		genderBox.setSelectedIndex(0);
	}

	private void refreshTable() {
		tableModel.setRowCount(0); // Clear existing rows
		List<Patient> patients = manager.getPatients();
		if (patients != null) {
			for (Patient p : patients) {
				Object[] row = {
					p.getId(),
					p.getName(),
					p.getAge(),
					p.getGender(),
					p.getDisease(),
					p.getPhone(),
					p.getDoctor(),
					p.getRoomNumber()
				};
				tableModel.addRow(row);
			}
		}
	}
}