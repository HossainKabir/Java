import java.awt.EventQueue;

import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OfficerVerificaton {

	private static JFrame frame;
	private JTextField unameField;
	private JPasswordField passwordField;
	
	private int increase=0;

	dbClass db=new dbClass();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OfficerVerificaton window = new OfficerVerificaton();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public OfficerVerificaton() {
		initialize();
		
		db.dbSuperConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		frame.setTitle("Officer Verification");
		frame.setLocationRelativeTo(null);
		
		JLabel uname = new JLabel("User Name");
		uname.setFont(new Font("Tahoma", Font.BOLD, 15));
		uname.setBounds(114, 66, 101, 29);
		frame.getContentPane().add(uname);
		
		unameField = new JTextField();
		unameField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		unameField.setBounds(225, 66, 133, 32);
		frame.getContentPane().add(unameField);
		unameField.setColumns(10);
		
		JLabel passworld = new JLabel("Password");
		passworld.setFont(new Font("Tahoma", Font.BOLD, 15));
		passworld.setBounds(114, 124, 101, 29);
		frame.getContentPane().add(passworld);
		
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				db.closeDBConnection();
				frame.dispose();
			}
		});
		cancel.setFont(new Font("Tahoma", Font.BOLD, 13));
		cancel.setBounds(269, 183, 89, 32);
		frame.getContentPane().add(cancel);
		
		JButton Login = new JButton("Login");
		Login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String name=unameField.getText();
				@SuppressWarnings("deprecation")
				String pass=passwordField.getText();
				
				if(name.equals("") || pass.equals("")){
					JOptionPane.showMessageDialog(null,"OOps......!");	
				}
				
				else{
				@SuppressWarnings("deprecation")
				String query1="SELECT userName,Password FROM Officer WHERE userName='" + unameField.getText()+"' and Password='"+passwordField.getText()+"';";
				try{
				ResultSet rs=null;	
				rs=db.ExecuteQuerySentResult(query1);
				int flag=0;
				while(rs.next())
				{
					String dbname = rs.getString("userName");
					String dbpass = rs.getString("Password");

				if(dbname.equals(name) && dbpass.equals(pass)){
						JOptionPane.showMessageDialog(null,"Verified Officer");
						frame.dispose();
						mainMenu mm=new mainMenu();
						mm.setVisible(true);
						flag=1;
					}
				}
						if(flag==0){
							increase++;
							if(increase!=3){
							JOptionPane.showMessageDialog(null,"Invalid Officer");
							}
							unameField.setText("");
							passwordField.setText("");
							if(increase==3){
							JOptionPane.showMessageDialog(null,"You Are Fake.");
							db.closeDBConnection();
							JOptionPane.showMessageDialog(null,"Connection Closed.");
							System.exit(0);
							}
						}
				
				}catch(Exception e){JOptionPane.showMessageDialog(null,e);}
				
			}
		}
		});
		Login.setFont(new Font("Tahoma", Font.BOLD, 13));
		Login.setBounds(134, 183, 89, 32);
		frame.getContentPane().add(Login);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordField.setEchoChar('$');
		passwordField.setBounds(225, 121, 133, 32);
		frame.getContentPane().add(passwordField);
	}
}
