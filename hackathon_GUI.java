package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import GUI_components.HackathonLable;
import GUI_components.Hackathon_Panel;

public class hackathon_GUI implements ActionListener
{
	/**Class Variables**/
	 static String NL = ("\n");
	private final String PYTHON_LOCATION = "C:\\Users\\Steve Liu\\Desktop\\New folder\\dist\\Backend\\Backend.exe";
	private JButton jbuPing      = null;
	private JButton jbuLookup = null;
	private JButton jbuTraceRoute         = null;
	private JButton jbuWhois      = null;
	private JButton jbuPortScan = null;
	private JButton jbuSpeedTest         = null;
	private JButton jbuExit = null;
	private JButton jbuQuitapp = null;
	private JFrame   jfrWindow           = null;
	//down part
	private Hackathon_Panel jpaFunctionArea = new Hackathon_Panel("Application");
	private Hackathon_Panel jpaUserPart = null;
	private Hackathon_Panel jpaDisplay = null;
	//ping
	private JButton jbuPingInfiniteTimes = null;
	private JButton jbuPingLimitedTimes = null;
	private HackathonLable jlaIns = null;
	private HackathonLable jlaInfinitePing = null;
	private HackathonLable jlaLimitedPing = null;
	private HackathonLable jlaexample = null;
	private JTextField jtePingAddress = null;
	private JTextField jtePingTimes = null;
	private JTextArea jteResults = null;
	private JScrollPane jsPing = new JScrollPane(jteResults);
	
	//Lookup
	private JButton jbuLookupStart = null;
	private HackathonLable jlaLookupIns = null;
	private HackathonLable jlaLookupExample = null;
	private JTextField jteIpAddress = null;
	private JTextArea jteLookupResult= null;
	private JScrollPane jsLookup = new JScrollPane(jteLookupResult);
	
	//trace route
	private JButton jbutrStart = null;
	private HackathonLable jlatrIns = null;
	private HackathonLable jlatrExample = null;
	private JTextField jtetrIpAddress = null;
	private JTextArea jtetrResult= null;
	private JScrollPane jsTraceroute = new JScrollPane(jtetrResult);
	
	//whois
	private JButton jbuwhoisStart = null;
	private HackathonLable jlawhoisIns = null;
	private HackathonLable jlawhoisExample = null;
	private JTextField jtewhoisIpAddress = null;
	private JTextArea jtewhoisResult= null;
	private JScrollPane jsWhois = new JScrollPane(jtewhoisResult);
	
	//portscan
	private JButton jbuPortScanStart = null;
	private HackathonLable jlaPortScanIns = null;
	private JTextField jtePortScanIp = null;
	private HackathonLable jlaPortScanExample = null;
	private JCheckBox jchOnlyTestCertainPorts = null;
	private JTextField jte1 = null;
	private JTextField jte2 = null;
	private HackathonLable jla1 = null;
	private JTextArea jtePortScanResult = null;
	private JScrollPane jsPortscan = new JScrollPane(jtePortScanResult);
	//speed test
	private JButton jbuSpeedTest_Start = null;
	private JProgressBar jpr_Progress = null;
	private JTextArea jteSpeedTestResult = null;
	private JScrollPane jsSpeedtest = new JScrollPane(jteSpeedTestResult);
	
	
	
	boolean isPreviousActionDone = true;

	private String Current_Panel = "ping";
	private String Current_Status = "Idle";
	
	public  hackathon_GUI()
	{
		/*** Local Variables ***/
		System.out.println("GUI starting......");
		
		Hackathon_Panel   jpaModeSelection         = null;
		Hackathon_Panel   jpaDisplayArea          = null;
		Container c = null;
		//Instantiate outer-container
		System.out.println("Instantiating outer-container.......");
		jfrWindow = new JFrame("Network Utility");
		c = jfrWindow.getContentPane();
		c.setLayout( new BorderLayout());
		setJFrameAttributes(jfrWindow);
		//Create Inner Containner
		jpaModeSelection = createModeSelection();
		//System.out.println(jpaModeSelection);
		jpaDisplayArea = createFuntionArea("ping");
		System.out.println("Adding panel......");
		//Adding panel to the frame
		c.add("North",jpaModeSelection);
		c.add("Center",jpaDisplayArea);
		System.out.println("Complete!");
		//Starting the GUI
		
		jfrWindow.setVisible(true);
		System.out.println("GUI Initialization complete!");
		
	}
	
	/**Class Helper Methods**/
	
	private void setJFrameAttributes (JFrame jfrGUI)
	{
		jfrGUI.setSize(780,800); 
		jfrGUI.setVisible(false);
		jfrGUI.setLocation(100, 100);
		System.out.println("Setting JFrame......");
	}
	
	private Hackathon_Panel createModeSelection ()
	{
		//create
		Hackathon_Panel jpaModeSelection = null;
		
		//instantiate
		jpaModeSelection = new Hackathon_Panel("Select Mode");
		
		jbuPing = new JButton("Ping");
		jbuLookup = new JButton("Lookup");
		jbuTraceRoute = new JButton("Trace Route");
		jbuWhois = new JButton("Whois");
		jbuPortScan = new JButton("Port Scan");
		jbuSpeedTest = new JButton("Speed Test");
		jbuExit = new JButton("Close Program");
		jbuQuitapp = new JButton("Quit app");
		
		//adding buttons to the panel
		jbuPing.addActionListener(this);
		jbuLookup.addActionListener(this);
		jbuTraceRoute.addActionListener(this);
		jbuWhois.addActionListener(this);
		jbuPortScan.addActionListener(this);
		jbuSpeedTest.addActionListener(this);
		jbuExit.addActionListener(this);
		jbuQuitapp.addActionListener(this);
		jpaModeSelection.add(jbuPing);
		jpaModeSelection.add(jbuLookup);
		jpaModeSelection.add(jbuTraceRoute);
		jpaModeSelection.add(jbuWhois);
		jpaModeSelection.add(jbuPortScan);
		jpaModeSelection.add(jbuSpeedTest);
		jpaModeSelection.add(jbuExit);
		jpaModeSelection.add(jbuQuitapp);
		
		System.out.println("Setting up option panel......");
		return jpaModeSelection;
	}
	
	private Hackathon_Panel createFuntionArea (String function)
	{
		
		jpaFunctionArea.removeAll();
		jpaFunctionArea.revalidate();
		try {
			createTXTfile();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		switch (function)
		{
		case "ping":
			//Create components
			jfrWindow.setSize(780,800); 

			//new
			jpaUserPart = new Hackathon_Panel("Ping");
			jpaDisplay = new Hackathon_Panel("Result");
			jlaIns = new HackathonLable("Please enter an ip address for ping.");
			jtePingAddress = new JTextField(10);
			jtePingTimes = new JTextField(5);
			jlaInfinitePing = new HackathonLable("Send Infinite Ping requests");
			jlaLimitedPing = new HackathonLable("Send this amount of Ping requests:");
			jlaexample = new HackathonLable ("For Example: 10.0.2.1");
			jbuPingInfiniteTimes = new JButton("Go");
			jbuPingLimitedTimes = new JButton("Go");
			jbuPingInfiniteTimes.addActionListener(this);
			jbuPingLimitedTimes.addActionListener(this);
			jteResults = new JTextArea(20,60);
			jsPing = new JScrollPane(jteResults);
			jsPing.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			jsPing.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			
			jpaUserPart.setLayout(new GridLayout(5,3));
			jpaFunctionArea.setLayout(new GridLayout(2,1));
			
			//add GUI components
			//1st row
			jpaUserPart.add(jlaIns);
			jpaUserPart.add(new JLabel ("   "));
			jpaUserPart.add(new JLabel ("   "));
			//2nd row
			jpaUserPart.add(jtePingAddress);
			jpaUserPart.add(jlaexample);
			jpaUserPart.add(new JLabel ("   "));
			//3rd row
			jpaUserPart.add(new JLabel ("   "));
			jpaUserPart.add(new JLabel ("   "));
			jpaUserPart.add(new JLabel ("   "));
			//4th row
			jpaUserPart.add(jlaInfinitePing);
			jpaUserPart.add(jbuPingInfiniteTimes);
			jpaUserPart.add(new JLabel ("   "));
			//5th row
			jpaUserPart.add(jlaLimitedPing);
			jpaUserPart.add(jtePingTimes);
			jpaUserPart.add(jbuPingLimitedTimes);
			
			jpaDisplay.add(jsPing);
			
			jpaFunctionArea.add(jpaUserPart);
			jpaFunctionArea.add(jpaDisplay);
			break;
		case "lookup":
			jfrWindow.setSize(780,800); 
			
			jlaLookupIns = new HackathonLable("Please Enter an url for lookup.");
			jlaLookupExample = new HackathonLable("For example: www.example.com");
			jteIpAddress = new JTextField();
			jteLookupResult = new JTextArea(20,60);
			jbuLookupStart = new JButton("Go");
			jbuLookupStart.addActionListener(this);
			jpaUserPart = new Hackathon_Panel("Lookup");
			jpaDisplay = new Hackathon_Panel("Result");
			jsLookup = new JScrollPane(jteLookupResult);
			jsLookup.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			jsLookup.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			//jpaFunctionArea = new Hackathon_Panel();
			
			jpaUserPart.setLayout(new GridLayout(4,3));
			jpaFunctionArea.setLayout(new GridLayout(2,1));
			
			//adding GUI components
			//1st row
			jpaUserPart.add(jlaLookupIns);
			jpaUserPart.add(new JLabel ("   "));
			jpaUserPart.add(new JLabel ("   "));
			//2nd row
			jpaUserPart.add(jteIpAddress);
			jpaUserPart.add(jlaLookupExample);
			jpaUserPart.add(new JLabel ("   "));
			//3nd row
			jpaUserPart.add(new JLabel ("   "));
			jpaUserPart.add(new JLabel ("   "));
			jpaUserPart.add(new JLabel ("   "));
			//4th row
			jpaUserPart.add(new JLabel ("   "));
			jpaUserPart.add(new JLabel ("   "));
			jpaUserPart.add(jbuLookupStart);
			
			jpaDisplay.add(jsLookup);
			jpaFunctionArea.add(jpaUserPart);
			jpaFunctionArea.add(jpaDisplay);
			
			break;
		case "traceroute":
			jfrWindow.setSize(780,800); 
			
			jlatrIns = new HackathonLable("Please Enter an url for trace route.");
			jlatrExample = new HackathonLable("For example: www.example.com");
			jtetrIpAddress = new JTextField();
			jtetrResult = new JTextArea(20,60);
			jbutrStart = new JButton("Go");
			jbutrStart.addActionListener(this);
			jpaUserPart = new Hackathon_Panel("Trace Route");
			jpaDisplay = new Hackathon_Panel("Result");
			jsTraceroute = new JScrollPane(jtetrResult);
			jsTraceroute.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			jsTraceroute.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			//jpaFunctionArea = new Hackathon_Panel();
			
			jpaUserPart.setLayout(new GridLayout(4,3));
			jpaFunctionArea.setLayout(new GridLayout(2,1));
			
			//adding GUI components
			//1st row
			jpaUserPart.add(jlatrIns);
			jpaUserPart.add(new JLabel ("   "));
			jpaUserPart.add(new JLabel ("   "));
			//2nd row
			jpaUserPart.add(jtetrIpAddress);
			jpaUserPart.add(jlatrExample);
			jpaUserPart.add(new JLabel ("   "));
			//3nd row
			jpaUserPart.add(new JLabel ("   "));
			jpaUserPart.add(new JLabel ("   "));
			jpaUserPart.add(new JLabel ("   "));
			//4th row
			jpaUserPart.add(new JLabel ("   "));
			jpaUserPart.add(new JLabel ("   "));
			jpaUserPart.add(jbutrStart);
			
			jpaDisplay.add(jsTraceroute);
			jpaFunctionArea.add(jpaUserPart);
			jpaFunctionArea.add(jpaDisplay);
			break;
		case "whois":
			jfrWindow.setSize(780,800); 
			
			jlawhoisIns = new HackathonLable("Please Enter an url for whois.");
			jlawhoisExample = new HackathonLable("For example: www.example.com");
			jtewhoisIpAddress = new JTextField();
			jtewhoisResult = new JTextArea(20,60);
			jbuwhoisStart = new JButton("Go");
			jbuwhoisStart.addActionListener(this);
			jpaUserPart = new Hackathon_Panel("Whois");
			jpaDisplay = new Hackathon_Panel("Result");
			jsWhois = new JScrollPane(jtewhoisResult);
			jsWhois.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			jsWhois.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			//jpaFunctionArea = new Hackathon_Panel();
			
			jpaUserPart.setLayout(new GridLayout(4,3));
			jpaFunctionArea.setLayout(new GridLayout(2,1));
			
			//adding GUI components
			//1st row
			jpaUserPart.add(jlawhoisIns);
			jpaUserPart.add(new JLabel ("   "));
			jpaUserPart.add(new JLabel ("   "));
			//2nd row
			jpaUserPart.add(jtewhoisIpAddress);
			jpaUserPart.add(jlawhoisExample);
			jpaUserPart.add(new JLabel ("   "));
			//3nd row
			jpaUserPart.add(new JLabel ("   "));
			jpaUserPart.add(new JLabel ("   "));
			jpaUserPart.add(new JLabel ("   "));
			//4th row
			jpaUserPart.add(new JLabel ("   "));
			jpaUserPart.add(new JLabel ("   "));
			jpaUserPart.add(jbuwhoisStart);
			
			jpaDisplay.add(jsWhois);
			jpaFunctionArea.add(jpaUserPart);
			jpaFunctionArea.add(jpaDisplay);
			break;
		case "portscan":
			jfrWindow.setSize(1100,800); 
			
			jlaPortScanIns = new HackathonLable("PLease enter an ip/url for opened ports.");
			jtePortScanIp = new JTextField();
			jchOnlyTestCertainPorts = new JCheckBox("Only these ports, from:");
			jchOnlyTestCertainPorts.setBackground(Color.GRAY);
			jchOnlyTestCertainPorts.setForeground(Color.WHITE);
			jlaPortScanExample = new HackathonLable("For Example: 10.0.2.1 or www.example.com");
			jte1 = new JTextField();
			jte2 = new JTextField();
			jla1 = new HackathonLable("to ");
			jbuPortScanStart = new JButton("Go");
			jbuPortScanStart.addActionListener(this);
			jpaUserPart = new Hackathon_Panel("Port Scan");
			jpaDisplay = new Hackathon_Panel("Result");
			jtePortScanResult = new JTextArea(20,95);
			jsPortscan = new JScrollPane(jtePortScanResult);
			jsPortscan.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			jsPortscan.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			
			jpaUserPart.setLayout(new GridLayout(4,4));
			jpaFunctionArea.setLayout(new GridLayout(2,1));
			
			//adding GUI components 
			//1st row
			jpaUserPart.add(jlaPortScanIns);
			jpaUserPart.add(new JLabel ("   "));
			jpaUserPart.add(new JLabel ("   "));
			jpaUserPart.add(new JLabel ("   "));
			//2nd row
			jpaUserPart.add(jtePortScanIp);
			jpaUserPart.add(jlaPortScanExample);
			jpaUserPart.add(new JLabel ("   "));
			jpaUserPart.add(new JLabel ("   "));
			//3rd row
			jpaUserPart.add(jchOnlyTestCertainPorts);
			jpaUserPart.add(jte1);
			jpaUserPart.add(jla1);
			jpaUserPart.add(jte2);
			//4th row
			jpaUserPart.add(new JLabel ("   "));
			jpaUserPart.add(new JLabel ("   "));
			jpaUserPart.add(new JLabel ("   "));
			jpaUserPart.add(jbuPortScanStart);
			
			jpaDisplay.add(jsPortscan);
			
			jpaFunctionArea.add(jpaUserPart);
			jpaFunctionArea.add(jpaDisplay);
			break;
		case "speedtest":
			jfrWindow.setSize(780,800); 
			jpaUserPart = new Hackathon_Panel("Speed Test");
			jpaDisplay = new Hackathon_Panel("Result");
			jbuSpeedTest_Start = new JButton("Start");
			jbuSpeedTest_Start.addActionListener(this);
			jpr_Progress = new JProgressBar(0);
			jteSpeedTestResult = new JTextArea(20,60);
			
			jsSpeedtest = new JScrollPane(jteSpeedTestResult);
			jsSpeedtest.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			jsSpeedtest.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			
			jpaUserPart.setLayout(new GridLayout(3,5));
			jpaFunctionArea.setLayout(new GridLayout(2,1));
			
			//1
			jpaUserPart.add(new JLabel ("   "));
			jpaUserPart.add(new JLabel ("   "));
			jpaUserPart.add(new JLabel ("   "));
			jpaUserPart.add(new JLabel ("   "));
			jpaUserPart.add(new JLabel ("   "));
			//2
			jpaUserPart.add(new JLabel ("   "));
			jpaUserPart.add(new JLabel ("   "));
			jpaUserPart.add(jbuSpeedTest_Start);
			jpaUserPart.add(new JLabel ("   "));
			jpaUserPart.add(new JLabel ("   "));
			//3
			jpaUserPart.add(new JLabel ("   "));
			jpaUserPart.add(new JLabel ("   "));
			jpaUserPart.add(jpr_Progress);
			jpaUserPart.add(new JLabel ("   "));
			jpaUserPart.add(new JLabel ("   "));
			
			jpaDisplay.add(jsSpeedtest);
			jpaFunctionArea.add(jpaUserPart);
			jpaFunctionArea.add(jpaDisplay);
			break;
		}
		return jpaFunctionArea;
	}
	
	private void UserAction(String ActionNeeded) 
	{
		if(isPreviousActionDone = true)
		{
			switch (ActionNeeded)
			{
			case "ping":
				if(!Current_Panel.equals("ping"))
				{
				createFuntionArea("ping");
				System.out.println("exc:ping");
				try {
					createTXTfile();
				} catch (FileNotFoundException | UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Current_Panel = "ping";
				}
				break;
			case "lookup":
				if(!Current_Panel.equals("lookup"))
				{
				createFuntionArea("lookup");
				System.out.println("exc:lookup");
				try {
					createTXTfile();
				} catch (FileNotFoundException | UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Current_Panel = "lookup";
				}
				break;
			case "traceroute":
				if(!Current_Panel.equals("traceroute"))
				{
				createFuntionArea("traceroute");
				System.out.println("exc:traceroute");
				try {
					createTXTfile();
				} catch (FileNotFoundException | UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Current_Panel = "traceroute";
				}
				break;
			case "whois":
				if(!Current_Panel.equals("whois"))
				createFuntionArea("whois");
				System.out.println("exc:whois");
				try {
					createTXTfile();
				} catch (FileNotFoundException | UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Current_Panel = "whois";
				break;
			case "portscan":
				if(!Current_Panel.equals("portscan"))
				{
				createFuntionArea("portscan");
				System.out.println("exc:portscan");
				try {
					createTXTfile();
				} catch (FileNotFoundException | UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Current_Panel = "portscan";
				}
				break;
			case "speedtest":
				if(!Current_Panel.equals("speedtest"))
				{
				createFuntionArea("speedtest");
				System.out.println("exc:speedtest");
				try {
					createTXTfile();
				} catch (FileNotFoundException | UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Current_Panel = "speedtest";
				}
				break;
			case "Ping_Start_Unlimited":
				Current_Status = "Ping";
				try {
					pingU();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "Ping_Start_Limited":
				Current_Status = "Ping";
				try {
					pingL();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "Lookup_Start":
				Current_Status = "Lookup";
				try {
					lookup();
				} catch (IOException | InterruptedException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				break;
			case "TraceRoute_Start":
				Current_Status = "TraceRoute";
				try {
					traceRoute();
				} catch (IOException | InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case "Whois_Start":
				Current_Status = "Whois";
				try {
					whois();
				} catch (IOException | InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "PortScan_Start":
				Current_Status = "PortScan";
				try {
					portScan();
				} catch (IOException | InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "SpeedTest_Start":
				Current_Status = "SpeedTest";
				try {
					SpeedTest();
				} catch (IOException | InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
					
		}
		else
		{
			
		}
	}
	
	private void SpeedTest() throws IOException, InterruptedException
	{
		String[] args1 = new String[] {" speedtest"}; 
		String args2 = Arrays.toString(args1);
		args2 = args2.replaceAll("[\\[\\]\\(\\)]", "");
		//Process process = new ProcessBuilder(PYTHON_LOCATION,args2).start();
		Runtime rt = Runtime.getRuntime();
		Process p = rt.exec(PYTHON_LOCATION+args2);
		System.out.println("Called Python with args" + args2);
		jteSpeedTestResult.setText(args2);
		startListenTXT(Current_Panel);
	}
	
	private void portScan() throws IOException, InterruptedException
	{
		String Address = jtePortScanIp.getText();
		if(jchOnlyTestCertainPorts.isSelected())
		{
			String port1 = " "+jte1.getText();
			String port2 = " "+jte2.getText();
			String[] args1 = new String[] { " portscan "+Address+" "+port1+" "+port2}; 
			String args2 = Arrays.toString(args1);
			args2 = args2.replaceAll("[\\[\\]\\(\\)]", "");
	//		Process process = new ProcessBuilder(PYTHON_LOCATION,args2).start();
			Runtime rt = Runtime.getRuntime();
			Process p = rt.exec(PYTHON_LOCATION+args2);
			System.out.println("Called Python with args" + args2);
			jtePortScanResult.setText(args2);
			startListenTXT(Current_Panel);
		}
		else
		{
			String[] args1 = new String[] { " portscan "+Address}; 
			String args2 = Arrays.toString(args1);
			args2 = args2.replaceAll("[\\[\\]\\(\\)]", "");
			//Process process = new ProcessBuilder(PYTHON_LOCATION,args2).start();
			Runtime rt = Runtime.getRuntime();
			Process p = rt.exec(PYTHON_LOCATION+args2);
			System.out.println("Called Python with args" + args2);
			jtePortScanResult.setText(args2);
			startListenTXT(Current_Panel);
		}
	}
	private void lookup() throws IOException, InterruptedException
	{
		String Address = jteIpAddress.getText();
		String[] args1 = new String[] { " lookup "+Address}; 
		String args2 = Arrays.toString(args1);
		args2 = args2.replaceAll("[\\[\\]\\(\\)]", "");
	//	Process process = new ProcessBuilder(PYTHON_LOCATION,args2).start();
		Runtime rt = Runtime.getRuntime();
		Process p = rt.exec(PYTHON_LOCATION+args2);
		System.out.println("Called Python with args" + args2);
		jteLookupResult.setText(args2);
		startListenTXT(Current_Panel);
	}
	
	private void traceRoute() throws IOException, InterruptedException
	{
		String Address = jtetrIpAddress.getText();
		System.out.println(Address);
		String[] args1 = new String[] { " traceroute " + Address}; 
		String args2 = Arrays.toString(args1);
		args2 = args2.replaceAll("[\\[\\]\\(\\)]", "");
		//Process process = new ProcessBuilder(PYTHON_LOCATION,args2).start();
		Runtime rt = Runtime.getRuntime();
		Process p = rt.exec(PYTHON_LOCATION+args2);
		System.out.println("Called Python with args" + args2);
		jtetrResult.setText(args2);
		startListenTXT(Current_Panel);
	}
	
	private void whois() throws IOException, InterruptedException
	{
		String Address = jtewhoisIpAddress.getText();
		String[] args1 = new String[] { " whois "+Address}; 
		String args2 = Arrays.toString(args1);
		args2 = args2.replaceAll("[\\[\\]\\(\\)]", "");
		//Process process = new ProcessBuilder(PYTHON_LOCATION,args2).start();
		Runtime rt = Runtime.getRuntime();
		Process p = rt.exec(PYTHON_LOCATION+args2);
		System.out.println("Called Python with args" + args2);
		jtewhoisResult.setText(args2);
		startListenTXT(Current_Panel);
	}
	
	private void startListenTXT(String action) throws InterruptedException, IOException
	{
		System.out.println(action);
		//int i = 0;
		StringBuilder text = new StringBuilder();
		Scanner fileIn = null;
				fileIn = new Scanner(new File("log.txt"));
				
	    while (!Current_Status.equals("Idle")) 
	    {
		      while (fileIn.hasNextLine()){
		        text.append(fileIn.nextLine() + NL);
		      //  i = 0;
		        if(action.equals("ping"))
		    	 {
		    		  jteResults.setText(text.toString());
		    		  jteResults.revalidate();
		    	 }
		      else if(action.equals("lookup"))
		    	 {
		    		  jteLookupResult.setText(text.toString());
		    	 }
		      else if(action.equals("traceroute"))
		    	 {
		    		  jtetrResult.setText(text.toString());
		    	 }
		      else if(action.equals("whois"))
		    	 {
		    		  jtewhoisResult.setText(text.toString());
		    	 }
		      else if(action.equals("portscan"))
		    	 {
		    		  jtePortScanResult.setText(text.toString());
		    	 }
		      else if(action.equals("speedtest"))
		      {
		    	  jteSpeedTestResult.setText(text.toString());
		      }
		      else
		      {
		    	//  i++;
		      }
		      }
	    	
	    	Current_Status = "Idle";  
	    
	    	  
	    	  
	     }
	}
	
	private void pingU() throws IOException, InterruptedException
	{
		String time = " 0";
		String Address = jtePingAddress.getText();
		String[] args1 = new String[] { " ping "+Address+" "+time}; 
		String args2 = Arrays.toString(args1);
		args2 = args2.replaceAll("[\\[\\]\\(\\)]", "");
	//	Process process = new ProcessBuilder(PYTHON_LOCATION,args2).start();
		Runtime rt = Runtime.getRuntime();
				Process p = rt.exec(PYTHON_LOCATION+args2);
		System.out.println("Called Python with args" + args2);
		jteResults.setText(args2);
		startListenTXT(Current_Panel);
	}
	
	private void pingL() throws IOException, InterruptedException
	{
		String Address = jtePingAddress.getText();
		String times = jtePingTimes.getText().trim();
		String[] args1 = new String[] { " ping "+Address+" "+times}; 
		String args2 = Arrays.toString(args1);
		args2 = args2.replaceAll("[\\[\\]\\(\\)]", "");
		//Process process = new ProcessBuilder(PYTHON_LOCATION,args2).start();
		Runtime rt = Runtime.getRuntime();
		Process p = rt.exec(PYTHON_LOCATION+args2);
		System.out.println("Called Python with args" + args2);
		jteResults.setText(args2);
		startListenTXT(Current_Panel);
	}
	private void createTXTfile() throws FileNotFoundException, UnsupportedEncodingException
	{
		PrintWriter writer = new PrintWriter("log.txt", "UTF-8");
		//writer.println("The first line");
		//writer.println("The second line");
		writer.close();
	}
	
	public void Exit_Program()
	{
		System.out.println("Shutting down......");
		System.exit(0);
	}
	
	public static void main(String[] args) 
	{
		new hackathon_GUI();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == jbuPing)
		{
			System.out.println("User Click Event: Ping");
			UserAction("ping");
		}
		if (e.getSource() == jbuLookup)
		{
			System.out.println("User Click Event: Lookup");
			UserAction("lookup");
		}
		if (e.getSource() == jbuTraceRoute)
		{
			System.out.println("User Click Event: Trace Route");
			UserAction("traceroute");
		}
		if (e.getSource() == jbuWhois)
		{
			System.out.println("User Click Event: Whois");
			UserAction("whois");
		}
		if (e.getSource() == jbuPortScan)
		{
			System.out.println("User Click Event: Port Scan");
			UserAction("portscan");
		}
		if (e.getSource() == jbuSpeedTest)
		{
			System.out.println("User Click Event: Speed Test");
			UserAction("speedtest");
		}
		if (e.getSource() == jbuExit)
		{
			System.out.println("User Click Event: EXIT");
			Exit_Program();
		}
		if (e.getSource() == jbuPingInfiniteTimes)
		{
			System.out.println("User Click Event: Ping_Start_Infinite_Times");
			UserAction("Ping_Start_Unlimited");
		}
		if (e.getSource() == jbuPingLimitedTimes)
		{
			System.out.println("User Click Event: Ping_Start_Limited_Times");
			UserAction("Ping_Start_Limited");
		}
		if (e.getSource() == jbuLookupStart)
		{
			System.out.println("User Click Event: Lookup_Start");
			UserAction("Lookup_Start");
		}
		if (e.getSource() == jbutrStart)
		{
			System.out.println("User Click Event: Trace Route Start");
			UserAction("TraceRoute_Start");
		}
		if (e.getSource() == jbuwhoisStart)
		{
			System.out.println("User Click Event: Whois Start");
			UserAction("Whois_Start");
		}
		if (e.getSource() == jbuPortScanStart)
		{
			System.out.println("User Click Event: Port Scan Start");
			UserAction("PortScan_Start");
		}
		if (e.getSource() == jbuQuitapp)
		{
			System.out.println("User Click Event: Quit app");
			
		}
		if (e.getSource() == jbuSpeedTest_Start)
		{
			System.out.println("User Click Event: Speed Test Start");
			UserAction("SpeedTest_Start");
		}
	}
}
