import java.awt.BorderLayout;
import java.awt.*;
import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class DepositAccount extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField idField;
	private JTextField amountField;
	
	private dbClass db=new dbClass();
	private int close=0;
	
	public static void main(String[] args) {
		try {
			DepositAccount dialog1 = new DepositAccount(null,true);
			dialog1.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog1.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public DepositAccount(java.awt.Frame parent, boolean a){
		super(parent,a);
		setTitle("Deposit on Account");
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(parent);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel accountID = new JLabel("Account ID");
		accountID.setFont(new Font("Arial Black", Font.BOLD, 16));
		accountID.setBounds(105, 62, 107, 25);
		contentPanel.add(accountID);
		
		idField = new JTextField();
		idField.setBounds(232, 59, 142, 32);
		contentPanel.add(idField);
		idField.setColumns(10);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblAmount.setBounds(136, 116, 84, 32);
		contentPanel.add(lblAmount);
		
		amountField = new JTextField();
		amountField.setBounds(235, 116, 139, 32);
		contentPanel.add(amountField);
		amountField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("    Deposit Window");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel.setBounds(133, 11, 191, 32);
		contentPanel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Confirm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StringBuilder s=new StringBuilder();
				
				String Samount=amountField.getText();
				String id=idField.getText();
				double Damount=Double.parseDouble(Samount);
				int flag1=0,flag2=0;
				if(idField.getText().isEmpty()){
					s.append("ID must not be Empty.\n");
					flag1=1;
				}
				if(amountField.getText().isEmpty()){
					s.append("Amount must not be Empty.\n");
					flag2=1;
				}
				if(flag1==0){
				if(!id.matches("\\d{4}-\\d{2}-\\d{5}")){
					s.append("ID did not Match, Check it.\n");
				}
				}
				if(flag2==0){
				if(Damount<500){
					s.append("Please,Increase your Deposit Amount.\n");	
				}
				}
				if(s.length()>0){
					JOptionPane.showMessageDialog(null, s.toString(),"Input Warning",JOptionPane.WARNING_MESSAGE);
				}
				else{
					String dbBalance=""; 
					String dbAType="";
					String dbID="";
					String query="select initialBalance,accountType,id from customer where id='"+id+"';";
					db.dbSuperConnector();
					ResultSet rs=null;
					rs=db.ExecuteQuerySentResult(query);
					
					try{
					rs.beforeFirst(); 
					if(rs.next())
					dbID=rs.getString("id");
					dbBalance = rs.getString("initialBalance");
					dbAType = rs.getString("accountType");
					}catch(Exception e){}
					if(dbID.equals(id)){
						JOptionPane.showMessageDialog(null,"id Found");
						String tBalance=amountField.getText();
						double nBalance=Double.parseDouble(tBalance);
						double pBalance=Double.parseDouble(dbBalance);
						double fBalance=nBalance+pBalance;
						String Uquery="Update customer set initialBalance='"+fBalance+"'";
						db.ExecuteQuery(Uquery);
						idField.setText("");
						amountField.setText("");
						JOptionPane.showMessageDialog(null,"Balance Deposited.\n"+"Current Balance"+fBalance+"\n");
						}else{
							
							idField.setText("");
							amountField.setText("");
							close++;
							if(close!=3){
							JOptionPane.showMessageDialog(null,"id not Found.");
							}
							if(close==3){
								JOptionPane.showMessageDialog(null,"You must have to Know your ID Correcly.");
								db.closeDBConnection();
								setVisible(false);	
							}
						}
					}
				}
		});
		btnNewButton.setFont(new Font("Arial Black", Font.BOLD, 17));
		btnNewButton.setBounds(112, 182, 117, 43);
		contentPanel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				db.closeDBConnection();
				setVisible(false);
			}
		});
		btnNewButton_1.setFont(new Font("Arial Black", Font.BOLD, 16));
		btnNewButton_1.setBounds(251, 182, 123, 43);
		contentPanel.add(btnNewButton_1);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
	}
}
