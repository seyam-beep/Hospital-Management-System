package hospital.model;
public class Patient extends Person{
	private String disease;
	private String doctor;
	private String phone;
	private String roomNumber;
	public Patient(String id,String name,int age,String gender,String disease,String doctor,String phone,String roomNumber){
		super(id,name,age,gender);
		this.disease=disease;
		this.doctor=doctor;
		this.phone=phone;
		this.roomNumber=roomNumber;
	}
	public void setDisease(String disease){
		this.disease=disease;
	}
	public String getDisease(){
		return disease;
	}
	public void setDoctor(String doctor){
		this.doctor=doctor;
	}
	public String getDoctor(){
		return doctor;
	}
	public void setPhone(String phone){
		this.phone=phone;
	}
	public String getPhone(){
		return phone;
	}
	public void setRoomNumber(String roomNumber){
		this.roomNumber=roomNumber;
	}
	public String getRoomNumber(){
		return roomNumber;
	}
	@Override
	public String getRole(){
		return "Patient";
	}
}