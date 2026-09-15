import hospital.gui.HospitalGUI;
import javax.swing.SwingUtilities;
public class Start{
		public static void main(String[] args){
			SwingUtilities.invokeLater(()->{HospitalGUI gui=new HospitalGUI();
			gui.setVisible(true);});
		}
	}