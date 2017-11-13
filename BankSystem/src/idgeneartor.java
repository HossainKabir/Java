import java.util.Date;

public class idgeneartor
{
	private int serial_no=1;
	private String zero;
	 public String Genarte()
	 {
		 if(serial_no<10){
			 zero="0000";
		 }
		 else if(serial_no==10 || serial_no<100)
		 {
			 zero="000";
		 }
		 else if(serial_no==100 || serial_no<1000)
		 {
			 zero="00";
		 }
		 else if(serial_no==1000 || serial_no<10000)
		 {
			 zero="0";
		 }
		 else if(serial_no==10000 || serial_no<100000)
		 {
			 zero="";
		 }
		 else{
			zero= "Stop";
		 }
		 if(zero!="Stop")
		 {
		 
		Date cdate=new Date();
		int month=cdate.getMonth();
		if(month<=9){
			String Fmonth="0"+(cdate.getMonth()+1);
			String fst=(cdate.getYear()+1900)+"-"+(Fmonth)+"-"+zero+serial_no;
			serial_no++;
			return fst;
		}
		else
		{
		String fst=(cdate.getYear()+1900)+"-"+(cdate.getMonth()+1)+"-"+zero+serial_no;
		serial_no++;
		return fst;
		}
	}
		 else
		 {
			System.out.println("Reset Your Serial_No.");
			String fail="Failed to Genarate ID.";
			return fail;
		 }
	}
}