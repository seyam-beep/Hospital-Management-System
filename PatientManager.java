package hospital.service;
import hospital.model.Patient;
import hospital.exception.HospitalException;
import java.util.ArrayList;
import java.io.*;
public class PatientManager{
	private ArrayList<Patient> patients;
	public PatientManager(){
		patients=new ArrayList<>();
	}
	public void addPatient(Patient patient) throws HospitalException{
		if(findPatient(patient.getId())!=null){
			throw new HospitalException("Patient id already exists!");
		}
		patients.add(patient);
		try{
			saveToFile();
		}catch(IOException e){e.printStackTrace();}
	}
	public Patient findPatient(String id){
		for(Patient patient:patients){
			if(patient.getId().equals(id)){
				return patient;
			}
		}
		return null;
	}
	public void updatePatient(Patient updatedPatient) throws HospitalException{
			Patient existingPatient=findPatient(updatedPatient.getId());
			if(existingPatient==null){
				throw new HospitalException("Patient not found!");
			}
			existingPatient.setName(updatedPatient.getName());
			existingPatient.setAge(updatedPatient.getAge());
			existingPatient.setGender(updatedPatient.getGender());
			existingPatient.setDisease(updatedPatient.getDisease());
			existingPatient.setPhone(updatedPatient.getPhone());
			existingPatient.setDoctor(updatedPatient.getDoctor());
			existingPatient.setRoomNumber(updatedPatient.getRoomNumber());
			try{
			saveToFile();
		}catch(IOException e){e.printStackTrace();}
		}
	public void deletePatient(String id) throws HospitalException{
		Patient patient=findPatient(id);
		if(patient==null){
			throw new HospitalException("Patient not found!");
		}
		patients.remove(patient);
		try{
			saveToFile();
		}catch(IOException e){e.printStackTrace();}
	}
	public ArrayList<Patient> getPatients(){
		return patients;
	}
	public void saveToFile() throws IOException{
		BufferedWriter writer=new BufferedWriter(new FileWriter("patients.txt"));
		for(Patient patient:patients){
		writer.write(patient.getId()+"|"+patient.getName()+"|"+patient.getAge()+"|"+patient.getGender()+"|"+patient.getDisease()+"|"+
		patient.getPhone()+"|"+patient.getDoctor()+"|"+patient.getRoomNumber());
		writer.newLine();
		}
		writer.close();
	}
	public void loadFromFile() throws IOException {
    BufferedReader reader =
        new BufferedReader(new FileReader("patients.txt"));
    String line;
    while ((line = reader.readLine()) != null) {
        String[] data = line.split("\\|");
        Patient patient = new Patient(
            data[0],
            data[1],
            Integer.parseInt(data[2]),
            data[3],
            data[4],
            data[5],
            data[6],
            data[7]
        );
        patients.add(patient);
    }
    reader.close();
}
}