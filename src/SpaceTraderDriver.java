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
    private JPanel confirmTravelScreen;
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
                    setUpConfigScreen();
                }
            });
            // createButtonTimer(startButton);

            welcomeScreen = new JPanel();
            welcomeScreen.setLayout(new BorderLayout());
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
                formatText(skillText, false, 300, 30, Color.BLACK, Color.WHITE);
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
                // calculate skill points allowed, might have to change magic 16
                int allocSkill = 16 - i * 4;
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

            configScreen = new JPanel();
            configScreen.setLayout(new GridLayout(0, 2));
            configScreen.add(background);
            configScreen.add(configScreenInput);

            // GridBagConstraints gbc = new GridBagConstraints();
            // configScreen = new JPanel();
            // configScreen.setLayout(new GridBagLayout());
            // gbc.fill = GridBagConstraints.HORIZONTAL;
            // gbc.gridx = 0;
            // gbc.gridy = 0;
            // configScreen.add(background, gbc);
            // gbc.gridx = 0;
            // gbc.gridy = 1;
            // gbc.anchor = GridBagConstraints.PAGE_END;
            // configScreen.add(configScreenInput, gbc);
            this.contentPane.add(configScreen);
            configScreen.setVisible(true);

        } catch (java.net.MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void setUpConfirmationScreen() {
        try {
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
                    configScreen.setVisible(true);
                }
            });
            JButton buttonStart = new JButton("Start Game");
            formatButton(buttonStart, 500, 30);
            buttonStart.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    confirmScreen.setVisible(false);
                    game = new Game(skillDis, credit, difficulty);
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

            confirmScreen = new JPanel();
            confirmScreen.setLayout(new GridLayout(1, 2));
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
            JTextField regionName = new JTextField(game.getPlayer()
                .getCurrReg().getName());
            formatText(regionName, false, 300, 30);
            JTextField regionTech = new JTextField("" + game.getPlayer()
                .getCurrReg().getTechLevel());
            formatText(regionTech, false, 300, 30);
            JTextField regionCoord = new JTextField(game.getPlayer()
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

            regionScreen = new JPanel();
            regionScreen.setLayout(new GridLayout(2, 3));
            // regionScreen.add(regionDisplay);
            URL region0 = new URL("https://i.imgur.com/uyBgo1S.jpg");
            URL region1 = new URL("https://i.imgur.com/M8xsqLO.jpg");
            URL region2 = new URL("https://i.imgur.com/t2XMP9V.jpg");
            URL region3 = new URL("https://i.imgur.com/WNamirI.jpg");
            URL region4 = new URL("https://i.imgur.com/T55viT0.jpg");
            URL region5 = new URL("https://i.imgur.com/E8Rtkbb.jpg");
            URL region6 = new URL("https://i.imgur.com/Lo54og6.jpg");
            URL region7 = new URL("https://i.imgur.com/dtWtKTq.jpg");
            URL region8 = new URL("https://i.imgur.com/ipCacCK.jpg");
            URL region9 = new URL("https://i.imgur.com/01x9hFu.jpg");

            URL[] images = new URL[10];
            images[0] = region0;
            images[1] = region1;
            images[2] = region2;
            images[3] = region3;
            images[4] = region4;
            images[5] = region5;
            images[6] = region6;
            images[7] = region7;
            images[8] = region8;
            images[9] = region9;

            int regionIndex = this.game.getPlayer()
                .getCurrReg().getIndex();
            Image reg = new ImageIcon(images[regionIndex]).getImage().
                    getScaledInstance(300, 400, Image.SCALE_DEFAULT);
            
            JLabel display = new JLabel(new ImageIcon(reg));
            display.setOpaque(true);
            
            regionScreen.add(display);
            regionScreen.add(regionInfo);
            regionScreen.add(playerAction);
            this.contentPane.add(regionScreen);
            regionScreen.setVisible(true);

        // } catch (java.net.MalformedURLException e) {
        //     e.printStackTrace();
        // }
    }

    public void setUpMapScreen() {

        GridBagConstraints gbc = new GridBagConstraints();
        // still restrained to a grid based layout unfortunately, but we can
        // freely place the regions in relation to each other on a grid
        JPanel mapDisplay = new JPanel();
        mapDisplay.setLayout(new GridBagLayout());

        JButton[] regionButton = new JButton[game.getUniverse()
            .getRegionList().size()];
        for (int i = 0; i < regionButton.length; i++) {
            regionButton[i] = new JButton(game.getUniverse()
                .getRegionList().get(i).getName());
            formatButton(regionButton[i], 20, 20);
            regionButton[i].setLocation(regionButton[i].getX(),
                regionButton[i].getY());
            gbc.gridx = game.getUniverse().getRegionList().get(i).getX() + 200;
            gbc.gridy = game.getUniverse().getRegionList().get(i).getY() + 200;

            Region reg = game.getUniverse().getRegionList().get(i);
            regionButton[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    mapScreen.setVisible(false);
                    confirmTravelScreen(reg);
                }
            });
            mapDisplay.add(regionButton[i], gbc);
        }

        JTextField playerLocName = new JTextField("Current Location: " + game
            .getPlayer().getCurrReg().getName());
        formatText(playerLocName, false, 300, 30);
        JTextField playerLocCoord = new JTextField("Current Coordinates: "
            + game.getPlayer().getCurrReg().getCoord());
        formatText(playerLocCoord, false, 300, 30);
        JPanel subInfoPanel = new JPanel();
        subInfoPanel.setLayout(new GridLayout(2, 0));
        subInfoPanel.add(playerLocName);
        subInfoPanel.add(playerLocCoord);

        JButton cancelTravel = new JButton("Back");
        formatButton(cancelTravel, 300, 30);
        cancelTravel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mapScreen.setVisible(false);
                setUpRegionScreen();
            }
        });

        JPanel infoAndOption = new JPanel();
        infoAndOption.setLayout(new GridLayout(1, 0));
        infoAndOption.add(subInfoPanel);
        infoAndOption.add(cancelTravel);

        mapScreen = new JPanel();
        mapScreen.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mapScreen.add(mapDisplay, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.PAGE_END;
        gbc.insets = new Insets(50, 0, 0, 0);
        mapScreen.add(infoAndOption, gbc);

        this.contentPane.add(mapScreen);
        mapScreen.setVisible(true);
    }

    public void confirmTravelScreen(Region region) {
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
            + game.getDistance(region));
        formatText(distance, false, 300, 30);

        JButton backButton = new JButton("Back");
        formatButton(backButton, 300, 30);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                confirmTravelScreen.setVisible(false);
                setUpMapScreen();
            }
        });
        JButton travelButton = new JButton("Travel");
        formatButton(travelButton, 300, 30);
        travelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                confirmTravelScreen.setVisible(false);
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

        confirmTravelScreen = new JPanel();
        confirmTravelScreen.setLayout(new GridLayout(0, 1));
        confirmTravelScreen.add(regionName);
        confirmTravelScreen.add(techLevel);
        confirmTravelScreen.add(coordinate);
        confirmTravelScreen.add(travelCost);
        confirmTravelScreen.add(distance);
        confirmTravelScreen.add(subRegionPanel);
        confirmTravelScreen.setBackground(Color.BLACK);
        this.contentPane.add(confirmTravelScreen);
        confirmTravelScreen.setVisible(true);
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

    public void formatButton(JButton b, int x, int y, Color fore, Color back) {
        b.setSize(x, y);
        b.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        b.setForeground(fore);
        b.setBackground(back);
    }
    public void formatButton(JButton b, int x, int y) {
        formatButton(b, x, y, Color.CYAN, Color.BLACK);
    }
    public void formatText(JTextField t, boolean editable, int x, int y,
            Color fore, Color back) {
        t.setSize(x, y);
        t.setEditable(editable);
        t.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        t.setForeground(fore);
        t.setBackground(back);
    }
    public void formatText(JTextField t, boolean editable, int x, int y) {
        formatText(t, editable, x, y, Color.CYAN, Color.BLACK);
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