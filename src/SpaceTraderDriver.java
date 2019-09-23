import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.net.URL;

public class SpaceTraderDriver extends JFrame {

   private final JPanel contentPane;
   private JPanel welcomeScreen;
   private JPanel configScreen;
   private JPanel confirmScreen;
   private JLabel startBanner;
   private String name;
   private String difficulty;
   private JPanel skillField;
   private int credit;
   private int skillPoints = 0;
   private int pPoint = 0, fPoint = 0, mPoint = 0, ePoint = 0;

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
       setExtendedState(JFrame.MAXIMIZED_BOTH);
       setBounds(100, 100, 650, 665);
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
           Image icon1 = new ImageIcon(welcomeLink).getImage()
                   .getScaledInstance(650, 550, Image.SCALE_DEFAULT);
           JLabel background = new JLabel( new ImageIcon(icon1));

           //set label
           startBanner = new JLabel("WELCOME TO SPACE TRADER",SwingConstants.CENTER);
           startBanner.setSize(650,50);
           startBanner.setFont(new Font("Verdana", Font.BOLD, 30));
           startBanner.setForeground(Color.CYAN);
           startBanner.setOpaque(true);
           startBanner.setBackground(Color.black);

           createLabelTimer(startBanner);

           //set StartButton
           JButton startButton = new JButton("START");
           startButton.setSize(650,50);
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
           welcomeScreen.add(startBanner,BorderLayout.NORTH);
           welcomeScreen.add(startButton,BorderLayout.SOUTH);
           welcomeScreen.add(background, BorderLayout.CENTER);


           this.contentPane.add(welcomeScreen);
       } catch (java.net.MalformedURLException e) {
           e.printStackTrace();
       }
   }
   public void setUpConfigScreen() {
       try {
           configScreen = new JPanel();
           configScreen.setLayout(new GridLayout(4,1));

           // prompt for name
           JTextField promptName = new JTextField("Name:");
           setUpPromptName(promptName);

           JTextField nameField = new JTextField("Spock");
           setUpNameField(nameField);

           JPanel subNamePanel = new JPanel();
           subNamePanel.setLayout(new GridLayout(1,2));
           subNamePanel.add(promptName);
           subNamePanel.add(nameField);

           // prompt for difficulty
           JTextField promptDiff = new JTextField("Difficulty:");
           setUpPromptDiff(promptDiff);

           // set up radio buttons for selecting difficulty
           JRadioButton easyButton = new JRadioButton("Easy");
           JRadioButton normalButton = new JRadioButton("Normal");
           JRadioButton hardButton = new JRadioButton("Hard");
           ButtonGroup diffGroup = new ButtonGroup();
           diffGroup.add(easyButton);
           diffGroup.add(normalButton);
           diffGroup.add(hardButton);
           JPanel radioPanel = new JPanel(new GridLayout(1,0));
           radioPanel.add(easyButton);
           radioPanel.add(normalButton);
           radioPanel.add(hardButton);
           JPanel subDiffPanel = new JPanel();
           subDiffPanel.setLayout(new GridLayout(1,2));
           subDiffPanel.add(promptDiff);
           subDiffPanel.add(radioPanel);

           // prompt for skill distribution
           JTextField promptSkill = new JTextField("Select Difficulty");
           setUpPromptSkill(promptSkill);

           // panel for distribution skill point
           skillField = new JPanel();
           skillField.setLayout(new GridLayout(4,0));
           JTextField pilotText = new JTextField("Pilot");
           JTextField pilotPoint = new JTextField("0");
           JButton pilotMinus = new JButton("-");
           JButton pilotPlus = new JButton("+");
           JTextField fighterText = new JTextField("Fighter");
           JTextField fighterPoint = new JTextField("0");
           JButton fighterMinus = new JButton("-");
           JButton fighterPlus = new JButton("+");
           JTextField merchantText = new JTextField("Merchant");
           JTextField merchantPoint = new JTextField("0");
           JButton merchantMinus = new JButton("-");
           JButton merchantPlus = new JButton("+");
           JTextField engineerText = new JTextField("Engineer");
           JTextField engineerPoint = new JTextField("0");
           JButton engineerMinus = new JButton("-");
           JButton engineerPlus = new JButton("+");
           JPanel subSkillPanel = new JPanel();
           subSkillPanel.setLayout(new GridLayout(1,2));
           subSkillPanel.add(promptSkill);
           subSkillPanel.add(skillField);

           // button actions for every single skill related buttons
           easyButton.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                   skillPoints = 16;
                   promptSkill.setText("Skill Points:" + skillPoints);
                   pPoint = 0;
                   pilotPoint.setText("0");
                   fPoint = 0;
                   fighterPoint.setText("0");
                   mPoint = 0;
                   merchantPoint.setText("0");
                   ePoint = 0;
                   engineerPoint.setText("0");
               }
           });
           normalButton.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                   skillPoints = 12;
                   promptSkill.setText("Skill Points:" + skillPoints);
                   pPoint = 0;
                   pilotPoint.setText("0");
                   fPoint = 0;
                   fighterPoint.setText("0");
                   mPoint = 0;
                   merchantPoint.setText("0");
                   ePoint = 0;
                   engineerPoint.setText("0");
               }
           });
           hardButton.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                   skillPoints = 8;
                   promptSkill.setText("Skill Points: " + skillPoints);
                   pPoint = 0;
                   pilotPoint.setText("0");
                   fPoint = 0;
                   fighterPoint.setText("0");
                   mPoint = 0;
                   merchantPoint.setText("0");
                   ePoint = 0;
                   engineerPoint.setText("0");
               }
           });
           pilotMinus.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                   if (pPoint > 0) {
                       pPoint--;
                       pilotPoint.setText("" + pPoint);
                       promptSkill.setText("Skill Points: " + (skillPoints
                               - pPoint - fPoint - mPoint - ePoint));
                   }
               }
           });
           pilotPlus.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                   if (pPoint + fPoint + mPoint + ePoint < skillPoints) {
                       pPoint++;
                       pilotPoint.setText("" + pPoint);
                       promptSkill.setText("Skill Points: " + (skillPoints
                               - pPoint - fPoint - mPoint - ePoint));
                   }
               }
           });
           fighterMinus.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                   if (fPoint > 0) {
                       fPoint--;
                       fighterPoint.setText("" + fPoint);
                       promptSkill.setText("Skill Points: " + (skillPoints
                               - pPoint - fPoint - mPoint - ePoint));
                   }
               }
           });
           fighterPlus.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                   if (pPoint + fPoint + mPoint + ePoint < skillPoints) {
                       fPoint++;
                       fighterPoint.setText("" + fPoint);
                       promptSkill.setText("Skill Points: " + (skillPoints
                               - pPoint - fPoint - mPoint - ePoint));
                   }
               }
           });
           merchantMinus.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                   if (mPoint > 0) {
                       mPoint--;
                       merchantPoint.setText("" + mPoint);
                       promptSkill.setText("Skill Points: " + (skillPoints
                               - pPoint - fPoint - mPoint - ePoint));
                   }
               }
           });
           merchantPlus.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                   if (pPoint + fPoint + mPoint + ePoint < skillPoints) {
                       mPoint++;
                       merchantPoint.setText("" + mPoint);
                       promptSkill.setText("Skill Points: " + (skillPoints
                               - pPoint - fPoint - mPoint - ePoint));
                   }
               }
           });
           engineerMinus.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                   if (ePoint > 0) {
                       ePoint--;
                       engineerPoint.setText("" + ePoint);
                       promptSkill.setText("Skill Points: " + (skillPoints
                               - pPoint - fPoint - mPoint - ePoint));
                   }
               }
           });
           engineerPlus.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                   if (pPoint + fPoint + mPoint + ePoint < skillPoints) {
                       ePoint++;
                       engineerPoint.setText("" + ePoint);
                       promptSkill.setText("Skill Points: " + (skillPoints
                               - pPoint - fPoint - mPoint - ePoint));
                   }
               }
           });

           skillField.add(pilotText);
           skillField.add(pilotMinus);
           skillField.add(pilotPoint);
           skillField.add(pilotPlus);
           skillField.add(fighterText);
           skillField.add(fighterMinus);
           skillField.add(fighterPoint);
           skillField.add(fighterPlus);
           skillField.add(merchantText);
           skillField.add(merchantMinus);
           skillField.add(merchantPoint);
           skillField.add(merchantPlus);
           skillField.add(engineerText);
           skillField.add(engineerMinus);
           skillField.add(engineerPoint);
           skillField.add(engineerPlus);

           // submit info about name, difficulty, and skills selected
           JButton submitButton = new JButton("Submit");
           setUpSubmitButton(submitButton);
           submitButton.addActionListener(new ActionListener() {
               public void actionPerformed(final ActionEvent e) {
                   String name = nameField.getText();
                   storeName(name);
                   String difficulty;
                   if (easyButton.isSelected()) {
                       difficulty = "Easy";
                   } else if (hardButton.isSelected()) {
                       difficulty = "Hard";
                   } else {
                       difficulty = "Normal";
                   }
                   storeDifficulty(difficulty);
                   if (easyButton.isSelected() || normalButton.isSelected()
                           || hardButton.isSelected()) {
                       configScreen.setVisible(false);
                       setUpConfirmationScreen();
                   }
               }
           });

           URL configLink = new URL("https://bit.ly/2kWR8je");
           Image icon1 = new ImageIcon(configLink).getImage()
                   .getScaledInstance(750,500, Image.SCALE_DEFAULT);
           JLabel background = new JLabel( new ImageIcon(icon1));
           background.setOpaque(true);

           configScreen.add(subNamePanel);
           configScreen.add(subDiffPanel);
           configScreen.add(subSkillPanel);
           configScreen.add(submitButton);
           configScreen.setBackground(Color.black);
           this.contentPane.add(background,BorderLayout.SOUTH);
           this.contentPane.add(configScreen);
           configScreen.setVisible(true);

       } catch (java.net.MalformedURLException e) {
           e.printStackTrace();
       }
   }

   public void setUpSubmitButton(JButton submitButton) {
       submitButton.setSize(300,30);
       submitButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
       submitButton.setForeground(Color.CYAN);
       submitButton.setBackground(Color.BLACK);
   }
   public void setUpPromptSkill(JTextField promptSkill) {
       promptSkill.setSize(450,30);
       promptSkill.setBackground(Color.BLACK);
       promptSkill.setFont(new Font("Times New Roman", Font.ITALIC, 20));
       promptSkill.setForeground(Color.CYAN);
   }
   public void setUpPromptName(JTextField promptName) {
       promptName.setSize(500,30);
       promptName.setBackground(Color.BLACK);
       promptName.setFont(new Font("Times New Roman", Font.ITALIC, 20));
       promptName.setForeground(Color.CYAN);
   }
   public void setUpNameField(JTextField nameField) {
       nameField.setSize(500,30);
       nameField.setEditable(true);
       nameField.setBackground(Color.BLACK);
       nameField.setFont(new Font("Times New Roman", Font.ITALIC, 20));
       nameField.setForeground(Color.CYAN);
   }
   public void setUpPromptDiff(JTextField promptDiff) {
       promptDiff.setSize(500,30);
       promptDiff.setBackground(Color.BLACK);
       promptDiff.setFont(new Font("Times New Roman", Font.ITALIC, 20));
       promptDiff.setForeground(Color.CYAN);
   }
   public void setUpConfirmationScreen() {
       try {
           confirmScreen = new JPanel();
           confirmScreen.setLayout(new GridLayout(4,0));

           // print out all relevant info
           JTextField confirmText
                   = new JTextField("Please confirm your entry");
           confirmText.setBackground(Color.BLACK);
           confirmText.setFont(new Font("Times New Roman", Font.ITALIC, 20));
           confirmText.setForeground(Color.CYAN);
           JTextField confirmName = new JTextField("Name: " + name);
           confirmName.setBackground(Color.BLACK);
           confirmName.setFont(new Font("Times New Roman", Font.ITALIC, 20));
           confirmName.setForeground(Color.CYAN);
           JTextField confirmCredit = new JTextField("Credits: " + credit);
           confirmCredit.setBackground(Color.BLACK);
           confirmCredit.setFont(new Font("Times New Roman", Font.ITALIC, 20));
           confirmCredit.setForeground(Color.CYAN);
           JTextField confirmDiff = new JTextField("Difficulty: "
                   + difficulty);
           confirmDiff.setBackground(Color.BLACK);
           confirmDiff.setFont(new Font("Times New Roman", Font.ITALIC, 20));
           confirmDiff.setForeground(Color.CYAN);
           // using space bars instead of \t because \t can cause some weird
           // spacing if used with words of differing length
           JTextField confirmSkill = new JTextField("Pilot: " + pPoint
                   + "   Fighter: " + fPoint
                   + "   Merchant: " + mPoint
                   + "   Engineer: " + ePoint);
           confirmSkill.setBackground(Color.BLACK);
           confirmSkill.setFont(new Font("Times New Roman", Font.ITALIC, 20));
           confirmSkill.setForeground(Color.CYAN);

           JPanel subConfPanel = new JPanel();
           subConfPanel.setLayout(new GridLayout(1,3));
           subConfPanel.add(confirmName);
           subConfPanel.add(confirmDiff);
           subConfPanel.add(confirmCredit);

           JButton returnToConfig = new JButton("Back");
           returnToConfig.setBackground(Color.BLACK);
           returnToConfig.setFont(new Font("Times New Roman", Font.ITALIC, 20));
           returnToConfig.setForeground(Color.CYAN);
           JButton startGame = new JButton("Start Game");
           startGame.setBackground(Color.BLACK);
           startGame.setFont(new Font("Times New Roman", Font.ITALIC, 20));
           startGame.setForeground(Color.CYAN);

           // TODO: add functionality to the two option buttons

           JPanel subOptionPanel = new JPanel();
           subOptionPanel.setLayout(new GridLayout(1,2));
           subOptionPanel.add(returnToConfig);
           subOptionPanel.add(startGame);

           URL configLink = new URL("https://bit.ly/2kWR8je");
           Image icon1 = new ImageIcon(configLink).getImage()
                   .getScaledInstance(750,500, Image.SCALE_DEFAULT);
           JLabel background = new JLabel( new ImageIcon(icon1));
           background.setOpaque(true);

           confirmScreen.add(confirmText);
           confirmScreen.add(subConfPanel);
           confirmScreen.add(confirmSkill);
           confirmScreen.add(subOptionPanel);
           confirmScreen.setBackground(Color.black);
           this.contentPane.add(confirmScreen);
           confirmScreen.setVisible(true);

       } catch (java.net.MalformedURLException e) {
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

   public void storeName(String name) {
       this.name = name;
   }
   public void storeDifficulty(String difficulty) {
       this.difficulty = difficulty;
       if (difficulty.equals("Easy")) {
           this.credit = 1000;
       } else if (difficulty.equals("Normal")) {
           this.credit = 500;
       } else {
           this.credit = 100;
       }
   }

}