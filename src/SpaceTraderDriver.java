import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.net.URL;

public class SpaceTraderDriver extends JFrame {

    private final JPanel contentPane;
    private JPanel welcomeScreen;
    private JPanel configScreen;
    private JLabel text;
    private String name;
    private String level;
    /**
     * Launch the application.
     */
    public static void main(final String[] args) {
        EventQueue.invokeLater(new Runnable() {
		public void run() {
		    try {
			SpaceTraderDriver frame = new SpaceTraderDriver();
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
    public SpaceTraderDriver() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 750, 750);
        contentPane = new JPanel();
        setContentPane(contentPane);

        //call setUpWelcomeScreen
        setUpWelcomeScreen();

    }

    public void setUpWelcomeScreen() {
        try {
            welcomeScreen = new JPanel();
            welcomeScreen.setLayout(new BorderLayout());
            //set background
            URL welcomeLink = new URL("https://bit.ly/2m6jU0U");
            Image icon1 = new ImageIcon(welcomeLink).getImage().getScaledInstance(750,650, Image.SCALE_DEFAULT);
            JLabel background = new JLabel( new ImageIcon(icon1));

            //set label
            text = new JLabel("WELCOME TO SPACE TRADER",SwingConstants.CENTER);
            text.setFont(new Font("Verdana", Font.BOLD, 30));
            text.setForeground(Color.CYAN);
            text.setOpaque(true);
            text.setBackground(Color.black);

            createLabelTimer(text);

            //set StartButton
            JButton startButton = new JButton("START");
            startButton.setFont(new Font("Verdana", Font.BOLD, 25));
            startButton.setForeground(Color.CYAN);
            startButton.setOpaque(true);
            startButton.setBackground(Color.black);
            startButton.setBorder(BorderFactory.createBevelBorder(0));

            startButton.addActionListener(new ActionListener() {
		    public void actionPerformed(final ActionEvent e) {
			welcomeScreen.setVisible(false);
			setUpConfigScreen();
		    }
		});
            createButtonTimer(startButton);

            //add components
            welcomeScreen.add(text,BorderLayout.NORTH);
            welcomeScreen.add(startButton,BorderLayout.SOUTH);
            welcomeScreen.add(background, BorderLayout.CENTER);


            this.contentPane.add(welcomeScreen);
        }
        catch (java.net.MalformedURLException e) {
            e.printStackTrace();
        }
    }
    public void createLabelTimer(JLabel text) {
	Timer blinkingTimer = new Timer(500, new ActionListener() {
		boolean on = false;
		public void actionPerformed(ActionEvent e) {
		    if (on == true) {
			text.setForeground(Color.CYAN);
		    } else {
			text.setForeground(Color.WHITE);
		    }
		    on = !on;
		}
	    });
        blinkingTimer.start();
    }
    public void createButtonTimer(JButton text) {
        Timer blinkingTimer = new Timer(500, new ActionListener() {
		boolean on = false;
		public void actionPerformed(ActionEvent e) {
		    if (on == true) {
			text.setForeground(Color.CYAN);
		    } else {
			text.setForeground(Color.WHITE);
		    }
		    on = !on;
		}
	    });
        blinkingTimer.start();
    }
    public void setUpConfigScreen() {
        try {
            configScreen = new JPanel();
            configScreen.setLayout(new GridBagLayout());


            //set background
            JTextField textField = new JTextField("Enter character name here");
            textField.setSize(300,30);
            textField.setEditable(true);
            textField.setBackground(Color.BLACK);
            textField.setFont(new Font("Times New Roman", Font.ITALIC, 20));
            textField.setForeground(Color.CYAN);

            //set button
            JButton submitButton = new JButton("Submit");
            submitButton.setSize(300,30);
            submitButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
            submitButton.setForeground(Color.CYAN);
            submitButton.setBackground(Color.BLACK);

            submitButton.addActionListener(new ActionListener() {
		    public void actionPerformed(final ActionEvent e) {
			String name = textField.getText();
			storeName(name);
		    }
		});

            //set background
            JTextField textField2 = new JTextField("Level: Easy, Medium or Hard");
            textField2.setSize(450,30);
            textField2.setEditable(true);
            textField2.setBackground(Color.BLACK);
            textField2.setFont(new Font("Times New Roman", Font.ITALIC, 20));
            textField2.setForeground(Color.CYAN);

            //set button
            JButton submitButton2 = new JButton("Submit");
            submitButton2.setSize(300,30);
            submitButton2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
            submitButton2.setForeground(Color.CYAN);
            submitButton2.setBackground(Color.BLACK);

            submitButton.addActionListener(new ActionListener() {
		    public void actionPerformed(final ActionEvent e) {
			String level = textField2.getText();
			storeLevel(level);
		    }
		});


            URL configLink = new URL("https://bit.ly/2kWR8je");
            Image icon1 = new ImageIcon(configLink).getImage().getScaledInstance(750,500, Image.SCALE_DEFAULT);
            JLabel background = new JLabel( new ImageIcon(icon1));
            background.setOpaque(true);

            configScreen.add(textField);
            configScreen.add(submitButton);
            configScreen.add(textField2);
            configScreen.add(submitButton2);
            configScreen.setBackground(Color.black);
            this.contentPane.add(background,BorderLayout.SOUTH);
            this.contentPane.add(configScreen);
            configScreen.setVisible(true);

        }
        catch (java.net.MalformedURLException e) {
            e.printStackTrace();
        }
    }
    public void storeName(String name) {
        this.name = name;
    }
    public void storeLevel(String level) {
        this.level = level;
    }

}