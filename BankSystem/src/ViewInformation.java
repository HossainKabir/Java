import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ViewInformation extends JDialog {
	private JTextField idField;
	private JTable table;
	
	private dbClass db=new dbClass();
	private StringBuilder s=new StringBuilder();
	private int flag=0;
	private String dbID="";
	
	
	public static void main(String[] args) {
		try {
			ViewInformation dialog = new ViewInformation();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewInformation() {
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			setBounds(100,100,900,400);
			setResizable(false);
			
			JButton btnViewAllInformation = new JButton("View All Information");
			btnViewAllInformation.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try{
					String query="Select * from customer;";
					ResultSet rs=null;
					db.dbSuperConnector();
					rs=db.ExecuteQuerySentResult(query);
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
						
					}catch(Exception ex){}
				}
			});
			btnViewAllInformation.setFont(new Font("Arial Black", Font.BOLD, 13));
			btnViewAllInformation.setBounds(452, 14, 198, 30);
			panel.add(btnViewAllInformation);
			
			JLabel lblId = new JLabel("ID :");
			lblId.setFont(new Font("Arial Black", Font.BOLD, 12));
			lblId.setBounds(11, 65, 40, 23);
			panel.add(lblId);
			
			idField = new JTextField();
			idField.setBounds(44, 61, 121, 30);
			panel.add(idField);
			idField.setColumns(10);
			
			JButton btnOk = new JButton("OK");
			btnOk.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String id=idField.getText();
					
					if(idField.getText().isEmpty()){
						s.append("ID must not be Empty.\n");
					}
					
					if(!idField.getText().isEmpty()){
					if(!id.matches("\\d{4}-\\d{2}-\\d{5}")){
						s.append("ID did not Match, Check it.\n");
						flag=1;
					}
				}
					
					if(s.length()>0){
						JOptionPane.showMessageDialog(null, s.toString(),"Input Warning",JOptionPane.WARNING_MESSAGE);
						idField.setText("");
					}else{
						String Tempquery="select id from customer where id='"+id+"';";
						String query="Select * from customer where id='"+id+"'";
						
							try {
								db.dbSuperConnector();
								ResultSet rs=null;
								rs=db.ExecuteQuerySentResult(Tempquery);
								rs.beforeFirst(); 
								if(rs.next())
								dbID = rs.getString("id");
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							if(dbID.equals(id)){
								ResultSet rs=null;
								rs=db.ExecuteQuerySentResult(query);
								table.setModel(DbUtils.resultSetToTableModel(rs));
								idField.setText("");
							}else{
								JOptionPane.showMessageDialog(null,"Invalid ID.");
								idField.setText("");
							}
						//
					}
				}
			});
			btnOk.setFont(new Font("Tahoma", Font.BOLD, 12));
			btnOk.setBounds(11, 120, 70, 30);
			panel.add(btnOk);
			
			JButton btnCancel = new JButton("Cancel");
			btnCancel.setFont(new Font("Tahoma", Font.BOLD, 12));
			btnCancel.setBounds(85, 121, 80, 30);
			panel.add(btnCancel);
			
			JLabel lblSingleInformation = new JLabel("Single Information");
			lblSingleInformation.setFont(new Font("Arial Black", Font.BOLD, 13));
			lblSingleInformation.setBounds(11, 14, 170, 30);
			panel.add(lblSingleInformation);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(187, 55, 697, 284);
			panel.add(scrollPane);
			
			table = new JTable();
			scrollPane.setViewportView(table);
		}
	}


}
