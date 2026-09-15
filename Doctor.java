package hospital.model;
public class Doctor extends Person{
	private String specialization;
	public Doctor(String id,String name,int age,String gender,String specialization){
		super(id,name,age,gender);
		this.specialization=specialization;
	}
	public void setSpecialization(String specialization){
		this.specialization=specialization;
	}
	public String getSpecialization(){
		return specialization;
	}
	@Override
	public String getRole(){
		return "Doctor";
	}
}
