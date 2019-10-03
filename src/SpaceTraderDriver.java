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
    private JPanel mapScreen;
    private JPanel regionScreen;
    private Game game;
    // these variables will be kept for use in initializing the game
    private String name;
    private int credit;
    private int skillPoints = 0;
    private String[] skillName = {"Pilot", "Fighter", "Merchant", "Engineer"};
    private int[] skillDis = new int[4];
    private String difficulty;

    // Launch the application.
    public static void main(final String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SpaceTraderDriver spacetrade = new SpaceTraderDriver();
                    spacetrade.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Create the frame.
    public SpaceTraderDriver() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setBounds(100, 100, 650, 665);
        contentPane = new JPanel();
        setContentPane(contentPane);
        setBackground(Color.BLUE);

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
            JLabel background = new JLabel(new ImageIcon(icon1));

            //set label
            JLabel startBanner = new JLabel("WELCOME TO SPACE TRADER",
                                SwingConstants.CENTER);
            startBanner.setSize(650, 50);
            startBanner.setFont(new Font("Verdana", Font.BOLD, 30));
            startBanner.setForeground(Color.CYAN);
            startBanner.setOpaque(true);
            startBanner.setBackground(Color.BLACK);

            // createLabelTimer(startBanner);

            //set StartButton
            JButton startButton = new JButton("START");
            startButton.setSize(650, 50);
            startButton.setFont(new Font("Verdana", Font.BOLD, 25));
            startButton.setForeground(Color.CYAN);
            startButton.setOpaque(true);
            startButton.setBackground(Color.BLACK);
            startButton.setBorder(BorderFactory.createBevelBorder(0));

            startButton.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    welcomeScreen.setVisible(false);
                    // contentPane.remove(welcomeScreen);
                    setUpConfigScreen();
                }
            });
            // createButtonTimer(startButton);

            //add components
            welcomeScreen.add(startBanner, BorderLayout.NORTH);
            welcomeScreen.add(startButton, BorderLayout.SOUTH);
            welcomeScreen.add(background, BorderLayout.CENTER);


            this.contentPane.add(welcomeScreen);
        } catch (java.net.MalformedURLException e) {
            e.printStackTrace();
        }
    }
    public void setUpConfigScreen() {
        try {
            configScreen = new JPanel();
            configScreen.setLayout(new GridLayout(0, 2));

            // prompt for name
            JTextField promptName = new JTextField("Name:");
            formatText(promptName, false, 500, 30);
            JTextField nameField = new JTextField("Spock");
            formatText(nameField, true, 500, 30);
            JPanel subNamePanel = new JPanel();
            subNamePanel.setLayout(new GridLayout(1, 2));
            subNamePanel.add(promptName);
            subNamePanel.add(nameField);

            // prompt for skill distribution
            JTextField promptSkill = new JTextField("Select Difficulty");
            formatText(promptSkill, false, 450, 30);
            // panel for distributing skill point
            JPanel skillPanel = new JPanel();
            skillPanel.setLayout(new GridLayout(4, 0));

            JTextField pilotPoint = new JTextField("0");
            JTextField fighterPoint = new JTextField("0");
            JTextField merchantPoint = new JTextField("0");
            JTextField engineerPoint = new JTextField("0");
            for (int i = 0; i < skillDis.length; i++) {
                JTextField skillText = new JTextField(skillName[i]);
                JButton minus = new JButton("-");
                int skillIndex = i;
                minus.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (skillDis[skillIndex] > 0) {
                            skillDis[skillIndex]--;
                            displayPoints(promptSkill, pilotPoint, fighterPoint,
                                merchantPoint, engineerPoint);
                        }
                    }
                });
                JButton plus = new JButton("+");
                plus.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (skillDis[0] + skillDis[1] + skillDis[2]
                                + skillDis[3] < skillPoints) {
                            skillDis[skillIndex]++;
                            displayPoints(promptSkill, pilotPoint, fighterPoint,
                                merchantPoint, engineerPoint);
                        }
                    }
                });
                skillPanel.add(skillText);
                skillPanel.add(minus);
                if (i == 0) {
                    skillPanel.add(pilotPoint);
                } else if (i == 1) {
                    skillPanel.add(fighterPoint);
                } else if (i == 2) {
                    skillPanel.add(merchantPoint);
                } else {
                    skillPanel.add(engineerPoint);
                }
                skillPanel.add(plus);
            }

            JPanel subSkillPanel = new JPanel();
            subSkillPanel.setLayout(new GridLayout(1, 2));
            subSkillPanel.add(promptSkill);
            subSkillPanel.add(skillPanel);

            // prompt for difficulty
            JTextField promptDiff = new JTextField("Difficulty:");
            formatText(promptDiff, false, 500, 30);
            // set up radio buttons for selecting difficulty
            JRadioButton[] diff = {new JRadioButton("Easy"),
                new JRadioButton("Normal"), new JRadioButton("Hard")};
            ButtonGroup diffGroup = new ButtonGroup();
            JPanel radioPanel = new JPanel(new GridLayout(1, 0));
            for (int i = 0; i < diff.length; i++) {
                diffGroup.add(diff[i]);
                radioPanel.add(diff[i]);
                int allocSkill;
                if (i == 0) {
                    allocSkill = 16;
                } else if (i == 1) {
                    allocSkill = 12;
                } else {
                    allocSkill = 8;
                }
                diff[i].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        skillPoints = allocSkill;
                        resetPoints(promptSkill, pilotPoint, fighterPoint,
                            merchantPoint, engineerPoint);
                    }
                });
            }
            JPanel subDiffPanel = new JPanel();
            subDiffPanel.setLayout(new GridLayout(1, 2));
            subDiffPanel.add(promptDiff);
            subDiffPanel.add(radioPanel);

            // submit info about name, difficulty, and skills selected
            JButton buttonSubmit = new JButton("Submit");
            formatButton(buttonSubmit, 300, 30);
            buttonSubmit.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    storeName(nameField.getText());
                    String difficulty;
                    if (diff[0].isSelected()) {
                        difficulty = "Easy";
                    } else if (diff[2].isSelected()) {
                        difficulty = "Hard";
                    } else {
                        difficulty = "Normal";
                    }
                    storeDifficulty(difficulty);
                    if (diff[0].isSelected() || diff[1].isSelected()
                            || diff[2].isSelected()) {
                        configScreen.setVisible(false);
                        // contentPane.remove(configScreen);
                        setUpConfirmationScreen();
                    }
                }
            });

            URL configLink = new URL("https://bit.ly/2kWR8je");
            Image icon1 = new ImageIcon(configLink).getImage()
                .getScaledInstance(750, 500, Image.SCALE_DEFAULT);
            JLabel background = new JLabel(new ImageIcon(icon1));
            background.setOpaque(true);

            JLabel configScreenInput = new JLabel();
            configScreenInput.setLayout(new GridLayout(4, 1));

            configScreenInput.add(subNamePanel);
            configScreenInput.add(subDiffPanel);
            configScreenInput.add(subSkillPanel);
            configScreenInput.add(buttonSubmit);
            configScreenInput.setBackground(Color.BLACK);

            configScreen.add(background);
            configScreen.add(configScreenInput);
            this.contentPane.add(configScreen);
            configScreen.setVisible(true);

        } catch (java.net.MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void setUpConfirmationScreen() {
        try {
            confirmScreen = new JPanel();
            confirmScreen.setLayout(new GridLayout(1, 2));

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
            JTextField confirmSkill = new JTextField("Pilot: " + skillDis[0]
                    + "   Fighter: " + skillDis[1]
                    + "   Merchant: " + skillDis[2]
                    + "   Engineer: " + skillDis[3]);
            formatText(confirmSkill, false, 500, 30);

            JPanel subConfPanel = new JPanel();
            subConfPanel.setLayout(new GridLayout(1, 3));
            subConfPanel.add(confirmName);
            subConfPanel.add(confirmDiff);
            subConfPanel.add(confirmCredit);

            JButton buttonBack = new JButton("Back");
            formatButton(buttonBack, 500, 30);
            buttonBack.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    confirmScreen.setVisible(false);
                    // contentPane.remove(confirmScreen);
                    configScreen.setVisible(true);
                }
            });
            JButton buttonStart = new JButton("Start Game");
            formatButton(buttonStart, 500, 30);
            buttonStart.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    confirmScreen.setVisible(false);
                    // contentPane.remove(confirmScreen);
                    game = new Game(skillDis, credit, difficulty);
                    // setUpMapScreen();
                    setUpRegionScreen();
                }
            });

            JPanel subOptionPanel = new JPanel();
            subOptionPanel.setLayout(new GridLayout(1, 2));
            subOptionPanel.add(buttonBack);
            subOptionPanel.add(buttonStart);

            URL configLink = new URL("https://bit.ly/2kWR8je");
            Image icon1 = new ImageIcon(configLink).getImage()
                    .getScaledInstance(750, 500, Image.SCALE_DEFAULT);
            JLabel background = new JLabel(new ImageIcon(icon1));
            background.setOpaque(true);

            JPanel confirmScreenInput = new JPanel();
            confirmScreenInput.setLayout(new GridLayout(4, 0));

            confirmScreenInput.add(confirmText);
            confirmScreenInput.add(subConfPanel);
            confirmScreenInput.add(confirmSkill);
            confirmScreenInput.add(subOptionPanel);
            confirmScreenInput.setBackground(Color.BLACK);

            confirmScreen.add(background);
            confirmScreen.add(confirmScreenInput);
            this.contentPane.add(confirmScreen);
            confirmScreen.setVisible(true);

        } catch (java.net.MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void setUpRegionScreen() {
        // try {
            regionScreen = new JPanel();
            regionScreen.setLayout(new GridLayout(2, 3));

            JTextField regionName = new JTextField(this.game.getPlayer()
                .getCurrReg().getName());
            formatText(regionName, false, 300, 30);
            JTextField regionTech = new JTextField("" + this.game.getPlayer()
                .getCurrReg().getTechLevel());
            formatText(regionTech, false, 300, 30);
            JTextField regionCoord = new JTextField(this.game.getPlayer()
                .getCurrReg().getCoord());
            formatText(regionCoord, false, 300, 30);

            JPanel regionInfo = new JPanel();
            regionInfo.setLayout(new GridLayout(0, 3));
            regionInfo.add(regionName);
            regionInfo.add(regionTech);
            regionInfo.add(regionCoord);

            JButton tradeButton = new JButton("Trade");
            formatButton(tradeButton, 300, 30);
            //=================================================================
            // implement functionality for the trade button
            //=================================================================
            JButton travelButton = new JButton("Travel");
            formatButton(travelButton, 300, 30);
            travelButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    regionScreen.setVisible(false);
                    setUpMapScreen();
                }
            });

            JPanel playerAction = new JPanel();
            playerAction.setLayout(new GridLayout(0, 2));
            playerAction.add(tradeButton);
            playerAction.add(travelButton);
            //=================================================================
            // Add a picture to each region, would probably need an array to
            // store the picture and we can pull them up as necessary. Once the
            // image is add, uncomment the try catch in this method
            //=================================================================

            // URL configLink = new URL("INSERT URL HERE");
            // Image icon = new ImageIcon(configLink).getImage()
            //         .getScaledInstance(IMAGEX, IMAGEY, Image.SCALE_DEFAULT);
            // JLabel regionDisplay = new JLabel(new ImageIcon(icon));
            // regionDisplay.setOpaque(true);
            // regionScreen.add(regionDisplay);
            regionScreen.add(regionInfo);
            regionScreen.add(playerAction);
            this.contentPane.add(regionScreen);
            regionScreen.setVisible(true);

        // } catch (java.net.MalformedURLException e) {
        //     e.printStackTrace();
        // }
    }

    public void setUpMapScreen() {
        mapScreen = new JPanel();
        // still restrained to a grid based layout unfortunately, but we can
        // freely place the regions in relation to each other on a grid
        mapScreen.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JButton[] regionButton = new JButton[this.game.getUniverse()
            .getRegionList().size()];
        for (int i = 0; i < regionButton.length; i++) {
            regionButton[i] = new JButton(this.game.getUniverse()
                .getRegionList().get(i).getName());
            formatButton(regionButton[i], 300, 30);
            regionButton[i].setLocation(regionButton[i].getX(),
                regionButton[i].getY());
            // System.out.println(regionButton[i].getX() + "   "
            //    + regionButton[i].getY());
            gbc.gridx = this.game.getUniverse().getRegionList().get(i).getX()
                + 400;
            gbc.gridy = this.game.getUniverse().getRegionList().get(i).getY()
                + 400;

            Region reg = game.getUniverse().getRegionList().get(i);
            regionButton[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    mapScreen.setVisible(false);
                    // contentPane.remove(mapScreen);
                    displayRegion(reg);
                }
            });
            mapScreen.add(regionButton[i], gbc);
        }

        this.contentPane.add(mapScreen);
        mapScreen.setVisible(true);
    }

    public void displayRegion(Region region) {
        JPanel regionScreen = new JPanel();
        regionScreen.setLayout(new GridLayout(0, 1));

        JTextField regionName = new JTextField(region.getName());
        formatText(regionName, false, 300, 30);
        JTextField techLevel = new JTextField("Tech Level: "
            + region.getTechLevel());
        formatText(techLevel, false, 300, 30);
        JTextField coordinate = new JTextField("Coordinates: (" + region.getX()
            + ", " + region.getY() + ")");
        formatText(coordinate, false, 300, 30);
        // JTextField travelCost = new JTextField(region.getTravelCost());
        JTextField travelCost = new JTextField("Cost: Very Costly");
        formatText(travelCost, false, 300, 30);
        JTextField distance = new JTextField("Distance: "
            + this.game.getDistance(region));
        formatText(distance, false, 300, 30);

        JButton backButton = new JButton("Back");
        formatButton(backButton, 300, 30);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                regionScreen.setVisible(false);
                setUpMapScreen();
            }
        });
        JButton travelButton = new JButton("Travel");
        formatButton(travelButton, 300, 30);
        travelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                regionScreen.setVisible(false);
                //=============================================================
                // add things to do in each region. Current code only
                // supports traveling from point A to point B and then
                // immediately displaying the mapScreen again
                //=============================================================

                //=============================================================
                // add code that subtracts fuel from the player's
                // Ship and set the new screen to display the region
                //=============================================================

                game.getPlayer().setCurrReg(region);
                setUpRegionScreen();
            }
        });

        JPanel subRegionPanel = new JPanel();
        subRegionPanel.setLayout(new GridLayout(0, 2));
        subRegionPanel.add(backButton);
        subRegionPanel.add(travelButton);

        regionScreen.add(regionName);
        regionScreen.add(techLevel);
        regionScreen.add(coordinate);
        regionScreen.add(travelCost);
        regionScreen.add(distance);
        regionScreen.add(subRegionPanel);
        regionScreen.setBackground(Color.BLACK);
        this.contentPane.add(regionScreen);
        regionScreen.setVisible(true);
    }

    // public void createLabelTimer(JLabel text) {
    //     Timer blinkingTimer = new Timer(500, new ActionListener() {
    //         boolean on = false;
    //         public void actionPerformed(ActionEvent e) {
    //             if (on) {
    //                 text.setForeground(Color.CYAN);
    //             } else {
    //                 text.setForeground(Color.WHITE);
    //             }
    //             on = !on;
    //         }
    //     });
    //     blinkingTimer.start();
    // }
    // public void createButtonTimer(JButton text) {
    //     Timer blinkingTimer = new Timer(500, new ActionListener() {
    //         boolean on = false;
    //         public void actionPerformed(ActionEvent e) {
    //             if (on) {
    //                 text.setForeground(Color.CYAN);
    //             } else {
    //                 text.setForeground(Color.WHITE);
    //             }
    //             on = !on;
    //         }
    //     });
    //     blinkingTimer.start();
    // }

    public void formatButton(JButton b, int x, int y) {
        b.setSize(x, y);
        b.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        b.setForeground(Color.CYAN);
        b.setBackground(Color.BLACK);
    }
    public void formatText(JTextField t, boolean editable, int x, int y) {
        t.setSize(x, y);
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
        for (int i = 0; i < skillDis.length; i++) {
            skillDis[i] = 0;
        }
        pilot.setText("0");
        fighter.setText("0");
        merchant.setText("0");
        engineer.setText("0");
    }
    public void displayPoints(JTextField prompt, JTextField pilot,
            JTextField fighter, JTextField merchant, JTextField engineer) {
        pilot.setText("" + skillDis[0]);
        fighter.setText("" + skillDis[1]);
        merchant.setText("" + skillDis[2]);
        engineer.setText("" + skillDis[3]);
        prompt.setText("Skill Points: " + (skillPoints - skillDis[0]
            - skillDis[1] - skillDis[2] - skillDis[3]));
    }
    // public Game startGame(int pPoint, int mPoint, int ePoint,
    //           int fPoint, int credit, String difficulty) {
    //     return new Game(pPoint, mPoint, ePoint, fPoint, credit, difficulty);
    // }
}