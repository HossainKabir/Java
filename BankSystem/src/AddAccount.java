import java.awt.*;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class AddAccount extends JDialog {
	private static final long serialVersionUID = 1L;
	private JTextField firstnameField;
	private JTextField lastnameField;
	private JTextField dobField;
	private JTextField nomineeField;
	private JTextField initialbalanceField;
	private static AddAccount dialog;
	
	private String balance="";
	private double verify=0.0;
	
	dbClass db=new dbClass();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
		 dialog = new AddAccount(null,true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public class IDgeneartor
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
	
	@SuppressWarnings("unchecked")
	public AddAccount(java.awt.Frame object, boolean b) {
		super (object,b);
		setFont(new Font("Arial", Font.BOLD, 15));
		setTitle("Account Information");
		
		setBounds(100, 100, 530, 361);
		setLocationRelativeTo(object);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{255, 275, 0};
		gridBagLayout.rowHeights = new int[]{42, 42, 42, 42, 42, 42, 45, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel FirstName = new JLabel("First Name ");
		FirstName.setFont(new Font("Arial Black", Font.BOLD, 18));
		FirstName.setLabelFor(this);
		GridBagConstraints gbc_FirstName = new GridBagConstraints();
		gbc_FirstName.anchor = GridBagConstraints.WEST;
		gbc_FirstName.insets = new Insets(0, 0, 5, 5);
		gbc_FirstName.gridx = 0;
		gbc_FirstName.gridy = 0;
		getContentPane().add(FirstName, gbc_FirstName);
		
		firstnameField = new JTextField();
		firstnameField.setFont(new Font("Arial", Font.BOLD, 15));
		GridBagConstraints gbc_firstnameField = new GridBagConstraints();
		gbc_firstnameField.insets = new Insets(0, 0, 5, 0);
		gbc_firstnameField.fill = GridBagConstraints.BOTH;
		gbc_firstnameField.gridx = 1;
		gbc_firstnameField.gridy = 0;
		getContentPane().add(firstnameField, gbc_firstnameField);
		firstnameField.setColumns(10);
		
		JLabel LastName = new JLabel("Last Name");
		LastName.setFont(new Font("Arial Black", Font.BOLD, 18));
		GridBagConstraints gbc_LastName = new GridBagConstraints();
		gbc_LastName.anchor = GridBagConstraints.WEST;
		gbc_LastName.insets = new Insets(0, 0, 5, 5);
		gbc_LastName.gridx = 0;
		gbc_LastName.gridy = 1;
		getContentPane().add(LastName, gbc_LastName);
		
		lastnameField = new JTextField();
		lastnameField.setFont(new Font("Arial", Font.BOLD, 15));
		GridBagConstraints gbc_lastnameField = new GridBagConstraints();
		gbc_lastnameField.insets = new Insets(0, 0, 5, 0);
		gbc_lastnameField.fill = GridBagConstraints.BOTH;
		gbc_lastnameField.gridx = 1;
		gbc_lastnameField.gridy = 1;
		getContentPane().add(lastnameField, gbc_lastnameField);
		lastnameField.setColumns(10);
		
		JLabel DOB = new JLabel("Date of Birth");
		DOB.setFont(new Font("Arial Black", Font.PLAIN, 18));
		GridBagConstraints gbc_DOB = new GridBagConstraints();
		gbc_DOB.anchor = GridBagConstraints.WEST;
		gbc_DOB.insets = new Insets(0, 0, 5, 5);
		gbc_DOB.gridx = 0;
		gbc_DOB.gridy = 2;
		getContentPane().add(DOB, gbc_DOB);
		
		dobField = new JTextField();
		dobField.setFont(new Font("Arial", Font.BOLD, 15));
		GridBagConstraints gbc_dobField = new GridBagConstraints();
		gbc_dobField.insets = new Insets(0, 0, 5, 0);
		gbc_dobField.fill = GridBagConstraints.BOTH;
		gbc_dobField.gridx = 1;
		gbc_dobField.gridy = 2;
		getContentPane().add(dobField, gbc_dobField);
		dobField.setColumns(10);
		
		JLabel Nominee = new JLabel("Nominee");
		Nominee.setFont(new Font("Arial Black", Font.BOLD, 18));
		GridBagConstraints gbc_Nominee = new GridBagConstraints();
		gbc_Nominee.anchor = GridBagConstraints.WEST;
		gbc_Nominee.insets = new Insets(0, 0, 5, 5);
		gbc_Nominee.gridx = 0;
		gbc_Nominee.gridy = 3;
		getContentPane().add(Nominee, gbc_Nominee);
		
		nomineeField = new JTextField();
		nomineeField.setFont(new Font("Arial", Font.BOLD, 15));
		GridBagConstraints gbc_nomineeField = new GridBagConstraints();
		gbc_nomineeField.insets = new Insets(0, 0, 5, 0);
		gbc_nomineeField.fill = GridBagConstraints.BOTH;
		gbc_nomineeField.gridx = 1;
		gbc_nomineeField.gridy = 3;
		getContentPane().add(nomineeField, gbc_nomineeField);
		nomineeField.setColumns(10);
		
		JLabel AccountType = new JLabel("Account Type");
		AccountType.setFont(new Font("Arial Black", Font.BOLD, 18));
		GridBagConstraints gbc_AccountType = new GridBagConstraints();
		gbc_AccountType.anchor = GridBagConstraints.WEST;
		gbc_AccountType.insets = new Insets(0, 0, 5, 5);
		gbc_AccountType.gridx = 0;
		gbc_AccountType.gridy = 4;
		getContentPane().add(AccountType, gbc_AccountType);
		
		JComboBox accounttypeField = new JComboBox();
		accounttypeField.setFont(new Font("Arial Black", Font.PLAIN, 14));
		accounttypeField.setModel(new DefaultComboBoxModel<Object>(new String[] {"Saving", "Debit", "Credit"}));
		GridBagConstraints gbc_accounttypeField = new GridBagConstraints();
		gbc_accounttypeField.insets = new Insets(0, 0, 5, 0);
		gbc_accounttypeField.fill = GridBagConstraints.BOTH;
		gbc_accounttypeField.gridx = 1;
		gbc_accounttypeField.gridy = 4;
		getContentPane().add(accounttypeField, gbc_accounttypeField);
		
		JLabel InitialBalance = new JLabel("Initial Balance");
		InitialBalance.setFont(new Font("Arial Black", Font.BOLD, 18));
		GridBagConstraints gbc_InitialBalance = new GridBagConstraints();
		gbc_InitialBalance.anchor = GridBagConstraints.WEST;
		gbc_InitialBalance.insets = new Insets(0, 0, 5, 5);
		gbc_InitialBalance.gridx = 0;
		gbc_InitialBalance.gridy = 5;
		getContentPane().add(InitialBalance, gbc_InitialBalance);
		
		JButton confirm = new JButton("Confirm");
		confirm.setFont(new Font("Arial Black", Font.PLAIN, 18));
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fname=firstnameField.getText();
				String lname=lastnameField.getText();
				String dob=dobField.getText();
				String nominee=nomineeField.getText();
				String atype=accounttypeField.getSelectedItem().toString();
				StringBuilder sb=new StringBuilder();
				int flag=0;
				if(firstnameField.getText().isEmpty()){
					sb.append("First Name Should not Empty.\n");
				}
				if(lastnameField.getText().isEmpty()){
					sb.append("Last Name Should not Empty.\n");
				}
				if(!dobField.getText().matches("(\\d{2}-?){2}\\d{4}")){
					sb.append("Date of Birth Must be 31-12-1900 Format.\n");
				}
				if(nomineeField.getText().isEmpty()){
					sb.append("Nominee Name Should not Empty.\n");
				}
				try{
					balance=initialbalanceField.getText();
					verify=Double.parseDouble(balance);
					}catch(Exception ex){
						flag=1;
						sb.append("Initial Balance must be added in Number.\n");}
				
				if(verify <=0.0 || verify<=1000.0){
					if(flag==0)
					sb.append("You Have to Increase Your Balance.\n");
				}
				if(sb.length()>0){
					JOptionPane.showMessageDialog(null, sb.toString(),"Input Warning",JOptionPane.WARNING_MESSAGE);
				}else{
					IDgeneartor widg=new IDgeneartor();
					String wid=widg.Genarte();
					
					String query="INSERT INTO customer(fName,lName,DOB,nominee,accountType,id,initialBalance) VALUES('"+fname+"','"+lname+"','"+dob+"','"+nominee+"','"+atype+"','"+wid+"','"+verify+"');";
					try{
					db.dbSuperConnector();
					db.ExecuteQuery(query);
					}catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}
					JOptionPane.showMessageDialog(null,fname+"\n"+lname+"\n"+dob+"\n"+nominee+"\n"+atype+"\n"+wid+"\n"+verify+"\n" ,"Inserted Data in Account",JOptionPane.WARNING_MESSAGE);
					firstnameField.setText("");
					lastnameField.setText("");
					dobField.setText("");
					nomineeField.setText("");
					initialbalanceField.setText("");
				}
				
				
			}
		});
		
		initialbalanceField = new JTextField();
		initialbalanceField.setFont(new Font("Arial", Font.BOLD, 15));
		GridBagConstraints gbc_initialbalanceField = new GridBagConstraints();
		gbc_initialbalanceField.insets = new Insets(0, 0, 5, 0);
		gbc_initialbalanceField.fill = GridBagConstraints.BOTH;
		gbc_initialbalanceField.gridx = 1;
		gbc_initialbalanceField.gridy = 5;
		getContentPane().add(initialbalanceField, gbc_initialbalanceField);
		initialbalanceField.setColumns(10);
		GridBagConstraints gbc_confirm = new GridBagConstraints();
		gbc_confirm.fill = GridBagConstraints.BOTH;
		gbc_confirm.insets = new Insets(0, 0, 0, 5);
		gbc_confirm.gridx = 0;
		gbc_confirm.gridy = 6;
		getContentPane().add(confirm, gbc_confirm);
		
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 setVisible(false);
				
			}

			
		});
		cancel.setFont(new Font("Arial Black", Font.BOLD, 18));
		GridBagConstraints gbc_cancel = new GridBagConstraints();
		gbc_cancel.fill = GridBagConstraints.BOTH;
		gbc_cancel.gridx = 1;
		gbc_cancel.gridy = 6;
		getContentPane().add(cancel, gbc_cancel);
	}

}
