import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Insets;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class mainMenu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private dbClass db=new dbClass();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainMenu frame = new mainMenu();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public mainMenu() {
		
		
		setTitle("Bank MainMenu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setMargin(new Insets(3, 3, 3, 3));
		menuBar.setToolTipText("File");
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				db.closeDBConnection();
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenuItem mntmNothing = new JMenuItem("Nothing");
		mnEdit.add(mntmNothing);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(mnHelp,"System Under Constracion.");
			}
		});
		mnHelp.add(mntmAbout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton AddButton = new JButton(" Add Account");
		AddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddAccount ad=new AddAccount(null,true);
				ad.setVisible(true);
			}
		});
		AddButton.setFont(new Font("Arial Black", Font.BOLD, 13));
		AddButton.setBounds(37, 47, 167, 23);
		contentPane.add(AddButton);
		
		JButton DepositButton = new JButton("Deposit");
		DepositButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DepositAccount da=new DepositAccount(null,true);
				da.setVisible(true);
			}
		});
		DepositButton.setFont(new Font("Arial Black", Font.BOLD, 13));
		DepositButton.setBounds(37, 81, 167, 23);
		contentPane.add(DepositButton);
		
		JButton WithDrawButton = new JButton("Withdraw");
		WithDrawButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WithdrawAccount wa=new WithdrawAccount(null,true);
				wa.setVisible(true);
			}
		});
		WithDrawButton.setFont(new Font("Arial Black", Font.BOLD, 13));
		WithDrawButton.setBounds(37, 116, 167, 23);
		contentPane.add(WithDrawButton);
		
		JButton btnNewButton = new JButton("View Information");
		btnNewButton.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnNewButton.setBounds(37, 150, 167, 23);
		contentPane.add(btnNewButton);
	}
}
