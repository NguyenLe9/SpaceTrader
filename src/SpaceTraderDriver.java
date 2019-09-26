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
    private String name = "Spock";
    private String difficulty;
    private JPanel skillPanel;
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
            startBanner = new JLabel("WELCOME TO SPACE TRADER",
                              SwingConstants.CENTER);
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
            formatText(promptName, false, 500, 30);

            JTextField nameField = new JTextField(name);
            formatText(nameField, true, 500, 30);

            JPanel subNamePanel = new JPanel();
            subNamePanel.setLayout(new GridLayout(1,2));
            subNamePanel.add(promptName);
            subNamePanel.add(nameField);

            // prompt for difficulty
            JTextField promptDiff = new JTextField("Difficulty:");
            formatText(promptDiff, false, 500, 30);

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
            formatText(promptSkill, false, 450, 30);

            // panel for distribution skill point
            skillPanel = new JPanel();
            skillPanel.setLayout(new GridLayout(4,0));
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
            subSkillPanel.add(skillPanel);

            // button actions for every single skill related buttons
            easyButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    skillPoints = 16;
                    resetPoints(promptSkill, pilotPoint, fighterPoint,
                      merchantPoint, engineerPoint);
                }
            });
            normalButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    skillPoints = 12;
                    resetPoints(promptSkill, pilotPoint, fighterPoint,
                      merchantPoint, engineerPoint);
                }
            });
            hardButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    skillPoints = 8;
                    resetPoints(promptSkill, pilotPoint, fighterPoint,
                      merchantPoint, engineerPoint);
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

            skillPanel.add(pilotText);
            skillPanel.add(pilotMinus);
            skillPanel.add(pilotPoint);
            skillPanel.add(pilotPlus);
            skillPanel.add(fighterText);
            skillPanel.add(fighterMinus);
            skillPanel.add(fighterPoint);
            skillPanel.add(fighterPlus);
            skillPanel.add(merchantText);
            skillPanel.add(merchantMinus);
            skillPanel.add(merchantPoint);
            skillPanel.add(merchantPlus);
            skillPanel.add(engineerText);
            skillPanel.add(engineerMinus);
            skillPanel.add(engineerPoint);
            skillPanel.add(engineerPlus);

            // submit info about name, difficulty, and skills selected
            JButton buttonSubmit = new JButton("Submit");
            formatButton(buttonSubmit, 300, 30);
            buttonSubmit.addActionListener(new ActionListener() {
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
            configScreen.add(buttonSubmit);
            configScreen.setBackground(Color.black);
            this.contentPane.add(background,BorderLayout.SOUTH);
            this.contentPane.add(configScreen);
            configScreen.setVisible(true);

        } catch (java.net.MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void setUpConfirmationScreen() {
        try {
            confirmScreen = new JPanel();
            confirmScreen.setLayout(new GridLayout(4,0));

            // print out all relevant info
            JTextField confirmText
                    = new JTextField("Please confirm your entry");
            formatText(confirmText, false, 500, 30);
            JTextField confirmName = new JTextField("Name: " + name);
            formatText(confirmName, false, 500, 30);
            JTextField confirmCredit = new JTextField("Credits: " + credit);
            formatText(confirmCredit, false, 500, 30);
            JTextField confirmDiff = new JTextField("Difficulty: "
                    + difficulty);
            formatText(confirmDiff, false, 500, 30);
            // using space bars instead of \t because \t can cause some weird
            // spacing if used with words of differing length
            JTextField confirmSkill = new JTextField("Pilot: " + pPoint
                    + "   Fighter: " + fPoint
                    + "   Merchant: " + mPoint
                    + "   Engineer: " + ePoint);
            formatText(confirmSkill, false, 500, 30);

            JPanel subConfPanel = new JPanel();
            subConfPanel.setLayout(new GridLayout(1,3));
            subConfPanel.add(confirmName);
            subConfPanel.add(confirmDiff);
            subConfPanel.add(confirmCredit);

            JButton buttonBack = new JButton("Back");
            formatButton(buttonBack, 500, 30);
            buttonBack.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    confirmScreen.setVisible(false);
                    configScreen.setVisible(true);
                }
            });
            JButton buttonStart = new JButton("Start Game");
            formatButton(buttonStart, 500, 30);

            //=================================================================
            // TODO: add functionality to the start option button
            //=================================================================

            JPanel subOptionPanel = new JPanel();
            subOptionPanel.setLayout(new GridLayout(1,2));
            subOptionPanel.add(buttonBack);
            subOptionPanel.add(buttonStart);

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

    public void formatButton(JButton b, int x, int y) {
        b.setSize(x,y);
        b.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        b.setForeground(Color.CYAN);
        b.setBackground(Color.BLACK);
    }
    public void formatText(JTextField t, boolean editable, int x, int y) {
        t.setSize(x,y);
        t.setEditable(editable);
        t.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        t.setForeground(Color.CYAN);
        t.setBackground(Color.BLACK);
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
    public void resetPoints(JTextField prompt, JTextField pilot,
      JTextField fighter, JTextField merchant, JTextField engineer) {
        prompt.setText("Skill Points:" + skillPoints);
        pPoint = 0;
        pilot.setText("0");
        fPoint = 0;
        fighter.setText("0");
        mPoint = 0;
        merchant.setText("0");
        ePoint = 0;
        engineer.setText("0");
    }

}