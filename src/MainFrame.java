import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

//TODO: create homepage layout
public class MainFrame {

	final int MAX = 10000; //max amount of people
	JButton button;
	static BufferedReader in; 
	static BufferedWriter out;
	static int numOfUsers;
	String fullName;
    String[] name, emails, passwords, grades, points;
	static String fileName = "accounts.txt";
	static Student curUser;
    public MainFrame() throws IOException {
    	//read in students
    	in = new BufferedReader(new FileReader(fileName));
//    	out = new BufferedWriter(new FileWriter(fileName));
    	name = new String[2*MAX];
    	emails = new String[MAX];
    	passwords = new String[MAX];
    	grades = new String[MAX];
    	points = new String[MAX];
    	name = in.readLine().split(" ");
    	emails = in.readLine().split(" ");
    	passwords = in.readLine().split(" ");
    	grades = in.readLine().split(" ");
    	points = in.readLine().split(" ");
    	numOfUsers = points.length;
    	for(int i = 0, n = 0; i < numOfUsers; i++) {
    		fullName = name[n] + " " + name[n+1]; n+=2;
    		Student.getStudents().add(new Student(fullName, emails[i], passwords[i], Integer.parseInt(grades[i]), Integer.parseInt(points[i])));
    	}
    	in.close();
   
        //setup the frame
        JFrame frame = new JFrame("Home Page");
        Main.setMainFrame(frame);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(1280, 720);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setBackground(Color.white); //get this to work

        //titlebar
        JPanel titlebar = new JPanel();
        titlebar.setBackground(Color.decode("#76BEE8"));
        titlebar.setLayout(new BorderLayout());
        titlebar.setBorder(new EmptyBorder(10, 10, 10, 80));
        titlebar.setBounds(0, 0, 1280, 60);
        titlebar.add(Common.getImage("logo_small.png"), BorderLayout.EAST);
        frame.add(titlebar);

        //sidebar
        JPanel sidebar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        sidebar.setBorder( new EmptyBorder(15, 15, 15, 15));
        sidebar.setBackground(Color.decode("#3E3F40"));
        sidebar.setBounds(0, 60, 300, 720);
        JLabel stats = new JLabel("Quick Stats");
        stats.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 23));
        stats.setForeground(Color.white);
        sidebar.add(stats);
        frame.add(sidebar);

        //section that presents all the events + event info
        JLabel title1 = new JLabel("Current Events");
        title1.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));
        title1.setForeground(Color.gray);
        title1.setBounds(325, 70, 150, 50);
        frame.add(title1);

        //TO DO: get the scroll thing to stay at the top when first running the program
        JPanel allEvents = new JPanel();
        allEvents.setLayout(new BoxLayout(allEvents, BoxLayout.Y_AXIS));
        allEvents.setBackground(Color.white);
        allEvents.setBorder(new EmptyBorder(15, 15, 15, 15));
        JScrollPane eventsPane = new JScrollPane(allEvents, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        eventsPane.setBounds(320, 110, 490, 555);
        eventsPane.getVerticalScrollBar().setUnitIncrement(15);
        
        //gets information from each event in the events list and adds it all to a single panel
        List<Event> eventsList = new ArrayList<Event>(); //use Event.getEvents(); after testing

        //events used for testing
        eventsList.add(new Event ("Cross Country", "Come to cross-country! Where you meet other runners and get some exercise while you're at it! Students of all levels are welcome!", "March 6, 2023", "12:30PM", 10));
        eventsList.add(new Event ("Volleyball", "BUMP! SMASH! VOLLEY! You know what we're talking about! Come tryout for volleyball and represent our school!", "March 9, 2023", "3:30PM", 20));
        eventsList.add(new Event ("Soccer", "Do you think you have the ability to be the Messi of our school? Join and find out!", "March 7, 2023", "3:30PM", 15));
		eventsList.add(new Event ("Tchoukball", "Tchouk what?! Are you new to this sport? If so, come on down, and learn the art of tchouk-ing the ball! No experience necessary!", "March 13, 2023", "12:30PM", 10));
        eventsList.add(new Event ("Badminton", "Oh! and that goes for a six! Come down and play in our school's monthly intramural! Be ready to practice your dropshots!", "March 13, 2023", "3:30PM", 20));
        eventsList.add(new Event ("Jump rope for heart", "Come outside during lunch and do some skipping to fund heart disease research!", "March 15, 2023", "12:30PM", 15));		
        eventsList.add(new Event ("Model UN Conference", "That motion is passed delegates! Come down to the library and represent your country well, at our demo model UN conference. Experience recommended!", "March 14, 2023", "12:30PM", 10));
        eventsList.add(new Event ("Chess Tournament", "Bishop to E5! Come unleash your inner Magnus!", "March 16, 2023", "12:30PM", 20));
        eventsList.add(new Event ("Entrepreneurship Festival", "Do you think you have the capabilities of a snake oil salesman? Come pitch your product and get rich!", "March 17, 2023", "3:30PM", 15));
		eventsList.add(new Event ("Science Fair", "Do you think you have the solution to climate change or aging! Present your invention here!", "March 20, 2023", "12:30PM", 10));
        eventsList.add(new Event ("Yearbook Committee Design Voting", "Come and appreciate the multitude of fine artwork done by your fellow peers!", "March 21, 2023", "3:30PM", 20));
        eventsList.add(new Event ("Knitting Club", "Knitting isn't only for old people, it's for the young anew, everyone welcome!", "March 22, 2023", "12:30PM", 15));

        for (int i = 0; i < eventsList.size(); i++) {
            //creates a mini panel to hold all the info about a specific event
            JPanel eventInfoPane = new JPanel();
            eventInfoPane.setLayout(new BoxLayout(eventInfoPane, BoxLayout.Y_AXIS));
            eventInfoPane.setBackground(Color.white);
            eventInfoPane.setBorder(new EmptyBorder(5, 5, 5, 5));
            eventInfoPane.setBorder(BorderFactory.createLineBorder(Color.gray));
            
            eventInfoPane.setMaximumSize(new Dimension(480, 200));

            //holds event points
            JTextField eventPointsInfo = new JTextField("WORTH " + eventsList.get(i).getPoints() + " POINTS!"); //gets the points the event is worth
            eventPointsInfo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 17));
            eventPointsInfo.setBorder(javax.swing.BorderFactory.createEmptyBorder());
            eventPointsInfo.setEditable(false);
            eventPointsInfo.setBackground(Color.decode("#76BEE8"));
			eventPointsInfo.setForeground(Color.white);
            eventPointsInfo.setBorder(new EmptyBorder(8, 5, 5, 0));

            //holds event name
            JTextField eventNameInfo = new JTextField(eventsList.get(i).getName()); //gets the event name
            eventNameInfo.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
            eventNameInfo.setBorder(javax.swing.BorderFactory.createEmptyBorder());
            eventNameInfo.setEditable(false);
            eventNameInfo.setBackground(Color.white);
            eventNameInfo.setBorder(new EmptyBorder(8, 5, 0, 0));
            
            //holds the event description
            JTextArea eventDescInfo = new JTextArea(eventsList.get(i).getDesc());
            eventDescInfo.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
            eventDescInfo.setForeground(Color.gray);
            eventDescInfo.setEditable(false);
            eventDescInfo.setLineWrap(true);
            eventDescInfo.setWrapStyleWord(true);
            eventDescInfo.setBorder(new EmptyBorder(0, 5, 3, 0));

            //holds the event date, and time
			JTextField eventSetting = new JTextField(eventsList.get(i).getDate() //gets the event date
                                                    + "  @ " + eventsList.get(i).getTime()); //gets the event time
            eventSetting.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
            eventSetting.setBorder(javax.swing.BorderFactory.createEmptyBorder());
            eventSetting.setEditable(false);
            eventSetting.setBackground(Color.white);
            eventSetting.setBorder(new EmptyBorder(5, 5, 3, 0));

            //register in an event
            JButton register = new JButton("Register"); //TO DO: create actionListener
            register.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
            register.setBackground(Color.decode("#76BEE8"));
            register.setOpaque(true);
            JTextPane registerPane = new JTextPane();
            registerPane.insertComponent(register);
            registerPane.setBackground(Color.white);
            registerPane.setBorder(new EmptyBorder(0, 5, 8, 0));

            eventInfoPane.add(eventPointsInfo);
			eventInfoPane.add(Box.createRigidArea(new Dimension(0, 5)));
			eventInfoPane.add(eventNameInfo);
            eventInfoPane.add(Box.createRigidArea(new Dimension(0, 5)));
            eventInfoPane.add(eventDescInfo);
            eventInfoPane.add(Box.createRigidArea(new Dimension(0, 6)));
            eventInfoPane.add(eventSetting);
            eventInfoPane.add(Box.createRigidArea(new Dimension(0, 6)));
            eventInfoPane.add(registerPane);
            eventInfoPane.setAlignmentX(Component.LEFT_ALIGNMENT);
            allEvents.add(eventInfoPane);
            allEvents.setAlignmentX(Component.LEFT_ALIGNMENT);
            allEvents.add(Box.createRigidArea(new Dimension(0, 20)));
        };
        frame.add(eventsPane);

        //leaderboard panel that shows an updated leaderboard everytime the page is opened
        JLabel title2 = new JLabel("Current Leaderboard");
        title2. setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));
        title2. setForeground(Color.gray);
        title2.setBounds(825, 70, 200, 50);
        frame.add(title2);

        JPanel leaderboard = new JPanel();
        leaderboard.setLayout(new BoxLayout(leaderboard, BoxLayout.Y_AXIS));
        leaderboard.setBackground(Color.decode("#F66845"));
        leaderboard.setBounds(825, 110, 420, 250);
        leaderboard.setBorder( new EmptyBorder(15, 15, 15, 15));

        //students for testing
        Student.addNineStudent(new Student("Sophie Tester", "sophie@student.ca", "testing", 9, 10));
        Student.addTenStudent(new Student("Liam Tester", "liam@student.ca", "testing", 10, 10));
        Student.addElevenStudent(new Student("Neo Tester", "neo@student.ca", "testing", 11, 30));
        Student.addTwelveStudent(new Student("Dan Tester", "dan@student.ca", "testing", 12, 10));

        JTextArea nineWinner = new JTextArea("Grade 9: " + PointSystem.nineWinners().get(0).getName());
        for (int i = 1; i < PointSystem.nineWinners().size(); i++) {
            if (PointSystem.nineWinners().size() == 1) {
                continue;
            } else {
                nineWinner.setText(nineWinner.getText() + ", " + (PointSystem.nineWinners().get(i)).getName());
            } 
        }
        nineWinner.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        nineWinner.setBackground(Color.decode("#F66845"));
        nineWinner.setForeground(Color.white);
        nineWinner.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        nineWinner.setEditable(false);
        nineWinner.setLineWrap(true);
        nineWinner.setWrapStyleWord(true);
        leaderboard.add(nineWinner);

        JTextArea tenWinner = new JTextArea("Grade 10: " + PointSystem.tenWinners().get(0).getName());
        for (int i = 1; i < PointSystem.tenWinners().size(); i++) {
            if (PointSystem.tenWinners().size() == 1) {
                continue;
            } else {
                tenWinner.setText(tenWinner.getText() + ", " + (PointSystem.tenWinners().get(i)).getName());
            } 
        }
        tenWinner.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        tenWinner.setBackground(Color.decode("#F66845"));
        tenWinner.setForeground(Color.white);
        tenWinner.setEditable(false);
        tenWinner.setLineWrap(true);
        tenWinner.setWrapStyleWord(true);
        tenWinner.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        leaderboard.add(tenWinner);

        JTextArea elevenWinner = new JTextArea("Grade 11: " + PointSystem.elevenWinners().get(0).getName());
        for (int i = 1; i < PointSystem.elevenWinners().size(); i++) {
            if (PointSystem.elevenWinners().size() == 1) {
                continue;
            } else {
                elevenWinner.setText(elevenWinner.getText() + ", " + (PointSystem.elevenWinners().get(i)).getName());
            } 
        }
        elevenWinner.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        elevenWinner.setBackground(Color.decode("#F66845"));
        elevenWinner.setForeground(Color.white);
        elevenWinner.setEditable(false);
        elevenWinner.setLineWrap(true);
        elevenWinner.setWrapStyleWord(true);
        elevenWinner.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        leaderboard.add(elevenWinner);

        JTextArea twelveWinner = new JTextArea("Grade 12: " + PointSystem.twelveWinners().get(0).getName());
        for (int i = 1; i < PointSystem.twelveWinners().size(); i++) {
            if (PointSystem.twelveWinners().size() == 1) {
                continue;
            } else {
                twelveWinner.setText(twelveWinner.getText() + ", " + (PointSystem.twelveWinners().get(i)).getName());
            } 
        }
        twelveWinner.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        twelveWinner.setBackground(Color.decode("#F66845"));
        twelveWinner.setForeground(Color.white);
        twelveWinner.setEditable(false);
        twelveWinner.setLineWrap(true);
        twelveWinner.setWrapStyleWord(true);
        twelveWinner.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        leaderboard.add(twelveWinner);

        JTextArea randomWinner = new JTextArea("Random Winners: " + PointSystem.randomWinners().get(0).getName());
        for (int i = 1; i < PointSystem.randomWinners().size(); i++) {
            if (PointSystem.randomWinners().size() == 1) {
                continue;
            } else {
                randomWinner.setText(randomWinner.getText() + ", " + (PointSystem.randomWinners().get(i)).getName());
            } 
        }
        randomWinner.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        randomWinner.setBackground(Color.decode("#F66845"));
        randomWinner.setForeground(Color.white);
        randomWinner.setEditable(false);
        randomWinner.setLineWrap(true);
        randomWinner.setWrapStyleWord(true);
        randomWinner.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        leaderboard.add(randomWinner);

        frame.add(leaderboard);
//        System.out.printf("Current user: " + curUser.getName() + "\n");


        //leaderboard panel that shows the final winners at the end of the quarter or from the previous quarter
        JLabel title3 = new JLabel("Quarterly Leaderboard");
        title3. setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));
        title3. setForeground(Color.gray);
        title3.setBounds(825, 375, 300, 50);
        frame.add(title3);

        JPanel leaderboardFinal = new JPanel();
        leaderboardFinal.setLayout(new BoxLayout(leaderboardFinal, BoxLayout.Y_AXIS));
        leaderboardFinal.setBackground(Color.decode("#F66845"));
        leaderboardFinal.setBounds(825, 415, 420, 250); 
        leaderboardFinal.setBorder( new EmptyBorder(15, 15, 15, 15));

        JTextField winnersFinal = new JTextField("Winners");
        winnersFinal.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        winnersFinal.setBackground(Color.decode("#F66845"));
        winnersFinal.setForeground(Color.white);
        winnersFinal.setBorder(javax.swing.BorderFactory.createEmptyBorder());

        leaderboardFinal.add(winnersFinal);
        frame.add(leaderboardFinal);

        /*
        //only generate winners for this leaderboard if it is the end of the quarter
        if () {

        } else {
            JLabel() msg = new JLabel("No one winners have been selected yet. Check again later");
            msg.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
            leaderboardFinal.add(msg);
        }
        */
        
        
        for(Student s : Student.getStudents()) {
        	System.out.println(s.getName());
        }
        frame.setVisible(true);
    }
    protected static void saveUser() throws IOException {
    	
		out = new BufferedWriter(new FileWriter(fileName));
		
		for(Student s : Student.getStudents()) {
			out.write(s.getName() + " ");
		}
		out.newLine();
		for(Student s : Student.getStudents()) {
			out.write(s.getEmail() + " ");
		}
		out.newLine();
		for(Student s : Student.getStudents()) {
			out.write(s.getPassword() + " ");
		}
		out.newLine();
		for(Student s : Student.getStudents()) {
			out.write(s.getGrade() + " ");
		}
		out.newLine();
		for(Student s : Student.getStudents()) {
			out.write(s.getPoints() + " ");
		}
		out.newLine();
		out.close();
    	
    }
}
