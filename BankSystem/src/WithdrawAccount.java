import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class WithdrawAccount extends JDialog {
	
	private static final long serialVersionUID = 1L;
	protected static final String Uquery = null;
	private final JPanel contentPanel = new JPanel();
	private JTextField idField;
	private JTextField amountField;
	
	private dbClass db=new dbClass();
	private int close=0;
	private double fBalance=0.0;
	private int flag=0;
	private int gotID=0;

public static void main(String[] args) {
		try {
			WithdrawAccount dialog = new WithdrawAccount(null,true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
	public WithdrawAccount(java.awt.Frame parent1, boolean c){
		super(parent1,c);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(parent1);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setTitle("Withdraw From Account");
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("    Withdraw Window");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel.setBounds(118, 16, 208, 29);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Account ID");
		lblNewLabel_1.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblNewLabel_1.setBounds(100, 67, 125, 29);
		getContentPane().add(lblNewLabel_1);
		
		idField = new JTextField();
		idField.setBounds(239, 65, 149, 38);
		getContentPane().add(idField);
		idField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("  Amount");
		lblNewLabel_2.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblNewLabel_2.setBounds(122, 132, 101, 29);
		getContentPane().add(lblNewLabel_2);
		
		amountField = new JTextField();
		amountField.setBounds(239, 129, 149, 38);
		getContentPane().add(amountField);
		amountField.setColumns(10);
		
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
					try{
					String query="select initialBalance,accountType,id from customer where id='"+id+"';";
					db.dbSuperConnector();
					ResultSet rs=null;
					rs=db.ExecuteQuerySentResult(query);
					rs.beforeFirst(); 
					if(rs.next())
					dbID=rs.getString("id");
					dbBalance = rs.getString("initialBalance");
					dbAType = rs.getString("accountType");
					}catch(Exception e){}
					if(dbID.equals(id)){
						gotID=1;
						JOptionPane.showMessageDialog(null,"id Found");
						String tBalance=amountField.getText();
						double nBalance=Double.parseDouble(tBalance);
						double pBalance=Double.parseDouble(dbBalance);
						if(dbAType.equals("Saving")){
						fBalance=pBalance-nBalance;
						if(fBalance<=0){
							JOptionPane.showMessageDialog(null,"Your Saving Account and Your Balance is Limited.");	
							idField.setText("");
							amountField.setText("");
							flag=1;
							}
						}
						else if(dbAType.equals("Debit")){
							fBalance=pBalance-nBalance;
							if(fBalance<=0){
								JOptionPane.showMessageDialog(null,"Your Debit Account and Your Balance is Limited.");
								idField.setText("");
								amountField.setText("");
								flag=1;
								}
						}
						else{
							fBalance=pBalance-nBalance;
							if(fBalance>=-10000){
								JOptionPane.showMessageDialog(null,"Your Credit Account and Your Borrow Limit crossed.");
								idField.setText("");
								amountField.setText("");
								flag=1;
								}
							
						}
						String Uquery="";
						if(flag==0){
						Uquery="Update customer set initialBalance='"+fBalance+"'";
						db.ExecuteQuery(Uquery);
						idField.setText("");
						amountField.setText("");
						JOptionPane.showMessageDialog(null,"Balance Withdraw from Account.\n"+"Current Balance"+fBalance+"\n");
						}
					}
					if(gotID==0){
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
		btnNewButton.setFont(new Font("Arial Black", Font.BOLD, 16));
		btnNewButton.setBounds(118, 188, 125, 45);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				db.closeDBConnection();
				setVisible(false);
			}
		});
		btnNewButton_1.setFont(new Font("Arial Black", Font.BOLD, 16));
		btnNewButton_1.setBounds(270, 188, 118, 45);
		getContentPane().add(btnNewButton_1);

		
		}

}
