package clientServer;
	import java.awt.BorderLayout;
	import java.awt.EventQueue;
	import javax.swing.JFrame;
	import javax.swing.JPanel;
	import javax.swing.border.EmptyBorder;

	//import clueless.BoardGame;

	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.io.BufferedReader;
	import java.io.IOException;
	import java.io.InputStreamReader;
	import java.io.PrintStream;
	import java.io.PrintWriter;
	import java.net.Socket;
	import java.text.SimpleDateFormat;
	import java.util.Date;
	import java.util.Scanner;
	import javax.swing.JLabel;
	import javax.swing.JOptionPane;
	import javax.swing.SwingConstants;
	import javax.swing.SwingUtilities;
	import javax.swing.UIManager;
	import java.awt.Font;
	import java.awt.GridBagLayout;
	import java.awt.GridLayout;

	import javax.swing.JButton;
	import javax.swing.JComboBox;
	import javax.swing.JTextArea;
	import javax.swing.JScrollPane;
	import java.awt.Color;
	import java.awt.FlowLayout;
	import javax.swing.JTextField;

	public class ClientUI extends JFrame implements ActionListener {	

		// Socket Related
		private static Socket clientSocket;
		private static int PORT;
		private PrintWriter out;

		// JFrame related
		private JPanel contentPane;
		private JTextArea txtAreaLogs;
		private JButton btnStart;
		private JPanel panelNorth;
		private JLabel lblChatClient;
		private JPanel panelNorthSouth;
		private JPanel panelNorthWest;
		private JPanel pannelCollection;
		private JPanel movePanel;
		private JButton suggBtn;
		private JButton moveBtn;
		private JButton accusationBtn;
		private JLabel lblPort;
		private JLabel lblName;
		private JLabel lblNames;
		private JPanel panelSouth;
		private JButton btnSend;
		private JTextField txtMessage;
		private JTextField txtNickname;
		private JTextField txtPort;
		private String clientName;
		
		//private static BoardGame game;
		private int numPlayers = 0;

		/**
		 * Launch the application.
		 */
		public static void main(String[] args) {
			//game = new BoardGame();
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						ClientUI frame = new ClientUI();
						UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
						SwingUtilities.updateComponentTreeUI(frame);
						//Logs
						System.setOut(new PrintStream(new TextAreaOutput(frame.txtAreaLogs)));
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}

		/**
		 * Create the frame.
		 */
		public ClientUI() {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(200, 200, 570, 400);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(10,10,10,10)); //(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(new BorderLayout(0, 0));

			panelNorth = new JPanel();
			contentPane.add(panelNorth, BorderLayout.NORTH);
			panelNorth.setLayout(new BorderLayout(0, 0));

			lblChatClient = new JLabel("PLAYER");
			lblChatClient.setHorizontalAlignment(SwingConstants.CENTER);
			lblChatClient.setFont(new Font("Tahoma", Font.PLAIN, 20));
			panelNorth.add(lblChatClient, BorderLayout.NORTH);

			panelNorthSouth = new JPanel();
			panelNorth.add(panelNorthSouth, BorderLayout.SOUTH);
			panelNorthSouth.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));		
			
			lblName = new JLabel("Nickname");
			panelNorthSouth.add(lblName);
			
			JLabel lbl = new JLabel("Select one");
		    lbl.setVisible(true);
	    //--------------------------------------
		//ADDED for TEST 
		    //pannelCollection = new JPanel(new GridLayout(10, 1, 10, 5));
			panelNorthWest = new JPanel(new GridLayout(2,0));//(10, 1, 10, 5)); //new JPanel();
			panelNorth.add(panelNorthWest, BorderLayout.WEST);
			panelNorthWest.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
			lblName = new JLabel("Suggestions");
			panelNorthWest.add(lblName);
			String[] choices = { "CHOICE 1","CHOICE 2", "CHOICE 3","CHOICE 4","CHOICE 5","CHOICE 6"};

		    final JComboBox<String> cb = new JComboBox<String>(choices);
		    cb.setVisible(true);
		    panelNorthWest.add(cb);
		    suggBtn = new JButton("OK");
		    panelNorthWest.add(suggBtn);
		    suggBtn.addActionListener(this);
		    
		    panelNorth.add(panelNorthWest, BorderLayout.WEST);
		    
		    movePanel = new JPanel(new GridLayout(2,0));
		    moveBtn = new JButton("Move");
		    //panelNorth.add(moveBtn);
		    accusationBtn = new JButton("Accusation");
		    movePanel.add(moveBtn);
		    movePanel.add(accusationBtn);
		    moveBtn.addActionListener(this);
		    accusationBtn.addActionListener(this);
		    panelNorth.add(movePanel);
		    //pannelCollection.add(movePanel);
		    //panelNorth.add(pannelCollection, BorderLayout.WEST);// panelNorthWest, BorderLayout.WEST);
		    
		    
		    
		    
	//---------------------------------------
		    
			txtNickname = new JTextField();
			txtNickname.setColumns(10);
			panelNorthSouth.add(txtNickname);

			lblPort = new JLabel("Port");
			panelNorthSouth.add(lblPort);

			txtPort = new JTextField();
			panelNorthSouth.add(txtPort);
			txtPort.setColumns(10);

			btnStart = new JButton("START");
			panelNorthSouth.add(btnStart);
			btnStart.addActionListener(this);
			btnStart.setFont(new Font("Tahoma", Font.PLAIN, 12));

			JScrollPane scrollPane = new JScrollPane();
			contentPane.add(scrollPane, BorderLayout.CENTER);

			txtAreaLogs = new JTextArea();
			txtAreaLogs.setBackground(Color.BLACK);
			txtAreaLogs.setForeground(Color.WHITE);
			txtAreaLogs.setLineWrap(true);
			scrollPane.setViewportView(txtAreaLogs);

			panelSouth = new JPanel();
			FlowLayout fl_panelSouth = (FlowLayout) panelSouth.getLayout();
			fl_panelSouth.setAlignment(FlowLayout.RIGHT);
			contentPane.add(panelSouth, BorderLayout.SOUTH);

			txtMessage = new JTextField();
			panelSouth.add(txtMessage);
			txtMessage.setColumns(50);

			btnSend = new JButton("SEND");
			btnSend.addActionListener(this);
			btnSend.setFont(new Font("Tahoma", Font.PLAIN, 12));
			panelSouth.add(btnSend);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnStart) {
				if(btnStart.getText().equals("START")) {
					btnStart.setText("STOP");
					start();
				}else {
					btnStart.setText("START");
					stop();
				}
			}else if(e.getSource() == btnSend) {
				String message = txtMessage.getText().trim();
				if(!message.isEmpty()) {
					out.println(message);
					txtMessage.setText("");
				}
			}else if (e.getSource() == suggBtn) {
				String suggMsg = JOptionPane.showInputDialog("Enter Suggestion");
				if(!suggMsg.isEmpty()) {
					out.println(suggMsg);
					//txtMessage.setText("");
				}
				//----------------------------
				//Handle suggestion here
			} else if (e.getSource() == moveBtn) {
				String location = JOptionPane.showInputDialog("Enter Location");
			} else if (e.getSource() == accusationBtn) {
				//do something
			}
			
			//Refresh UI
			refreshUIComponents();
		}

		public void refreshUIComponents() {

		}

		public void start() {
			try {
				PORT = Integer.parseInt(txtPort.getText().trim());
				clientName = txtNickname.getText().trim();
				clientSocket = new Socket("localhost", PORT);
				out = new PrintWriter(clientSocket.getOutputStream(), true);
				new Thread(new Listener()).start();
				//send name
				out.println(clientName);
			} catch (Exception err) {
				addToLogs("[ERROR] "+err.getLocalizedMessage());
			}
		}

		public void stop(){
			if(!clientSocket.isClosed()) {
				try {
					clientSocket.close();
				} catch (IOException e1) {}
			}
		}

		public static void addToLogs(String message) {
			System.out.printf("%s %s\n", ServerUI.formatter.format(new Date()), message);
		}

		private static class Listener implements Runnable {
			private BufferedReader in;
			@Override
			public void run() {
				try {
					in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
					String read;
					for(;;) {
						read = in.readLine();
						if (read != null && !(read.isEmpty())) addToLogs(read);
					}
				} catch (IOException e) {
					return;
				}
			}

		}
	}