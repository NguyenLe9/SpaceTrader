import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.net.URL;
import java.io.*;

public class SpaceTraderDriver extends JFrame {

    private final JPanel contentPane;
    private JPanel welcomeScreen;
    private JPanel configScreen;
    private JPanel confirmScreen;
    private JPanel tradeScreen;
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
    private Region targetedRegion;

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
        setBounds(100, 100, 850, 650);
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
                    .getScaledInstance(850, 535, Image.SCALE_DEFAULT);
            JLabel background = new JLabel(new ImageIcon(icon1));

            //set label
            JLabel startBanner = new JLabel("WELCOME TO SPACE TRADER",
                                SwingConstants.CENTER);
            startBanner.setSize(650, 50);
            startBanner.setFont(new Font("Verdana", Font.BOLD, 30));
            startBanner.setForeground(Color.CYAN);
            startBanner.setOpaque(true);
            startBanner.setBackground(Color.BLACK);

            createLabelTimer(startBanner);

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
            createButtonTimer(startButton);

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
                formatText(skillText, false, 200, 30, Color.BLACK, Color.WHITE);
                skillText.setFont(new Font("Times New Roman", Font.PLAIN, 15));
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

            // prompt for difficulty using radio buttons
            JTextField promptDiff = new JTextField("Difficulty:");
            formatText(promptDiff, false, 500, 30);
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

            Image image = javax.imageio.ImageIO.read(
                new File("Graphics/spaceman.jpg"))
                .getScaledInstance(440, 610, Image.SCALE_DEFAULT);
            JLabel background = new JLabel(new ImageIcon(image));
            background.setOpaque(true);

            configScreen = new JPanel();
            configScreen.setLayout(new GridBagLayout());
            addWithGBC(configScreen, background,
                new int[] {0, 0, 1, 4, 0, 0});
            addWithGBC(configScreen, promptName,
                new int[] {1, 0, 1, 1, 80, 110});
            addWithGBC(configScreen, nameField,
                new int[] {2, 0, 1, 1, 0, 0});
            addWithGBC(configScreen, promptDiff,
                new int[] {1, 1, 1, 1, 0, 110});
            addWithGBC(configScreen, radioPanel,
                new int[] {2, 1, 1, 1, 0, 0});
            addWithGBC(configScreen, promptSkill,
                new int[] {1, 2, 1, 1, 0, 140});
            addWithGBC(configScreen, skillPanel,
                new int[] {2, 2, 1, 1, 0, 0});
            addWithGBC(configScreen, buttonSubmit,
                new int[] {1, 3, 2, 1, 120, 0});

            this.contentPane.add(configScreen);
            configScreen.setVisible(true);

        } catch (java.io.IOException e) {
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
                    // calculate buy and sell prices depending on player merchant skill
                    game.calculateMarketPrice();
                }
            });

            Image image = javax.imageio.ImageIO.read(
                new File("Graphics/spaceman.jpg"))
                .getScaledInstance(440, 610, Image.SCALE_DEFAULT);
            JLabel background = new JLabel(new ImageIcon(image));
            background.setOpaque(true);

            confirmScreen = new JPanel();
            confirmScreen.setLayout(new GridBagLayout());
            addWithGBC(confirmScreen, background,
                new int[] {0, 0, 1, 6, 0, 0});
            addWithGBC(confirmScreen, confirmText,
                new int[] {1, 0, 2, 1, 183, 70});
            addWithGBC(confirmScreen, confirmName,
                new int[] {1, 1, 2, 1, 0, 70});
            addWithGBC(confirmScreen, confirmDiff,
                new int[] {1, 2, 2, 1, 0, 70});
            addWithGBC(confirmScreen, confirmSkill,
                new int[] {1, 3, 2, 1, 0, 70});
            addWithGBC(confirmScreen, confirmCredit,
                new int[] {1, 4, 2, 1, 0, 70});
            addWithGBC(confirmScreen, buttonBack,
                new int[] {1, 5, 1, 1, 100, 70});
            addWithGBC(confirmScreen, buttonStart,
                new int[] {2, 5, 1, 1, 0, 0});
            this.contentPane.add(confirmScreen);
            confirmScreen.setVisible(true);

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public void setUpRegionScreen() {
        JTextField regionName = new JTextField(game.getPlayer()
            .getCurrReg().getName());
        formatText(regionName, false, 300, 30);
        JTextField regionTech = new JTextField("" + game.getPlayer()
            .getCurrReg().getTechLevel());
        formatText(regionTech, false, 300, 30);
        JTextField regionCoord = new JTextField(game.getPlayer()
            .getCurrReg().getCoord());
        formatText(regionCoord, false, 300, 30);

        JPanel subRegionInfo = new JPanel();
        subRegionInfo.setLayout(new GridLayout(0, 3));
        subRegionInfo.add(regionName);
        subRegionInfo.add(regionTech);
        subRegionInfo.add(regionCoord);

        JButton tradeButton = new JButton("Trade");
        formatButton(tradeButton, 300, 30);
        tradeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                regionScreen.setVisible(false);
                setUpTradeScreen();
            }
        });
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

        JPanel regionInfo = new JPanel();
        regionInfo.setLayout(new GridLayout(2, 1));
        regionInfo.add(subRegionInfo);
        regionInfo.add(playerAction);

        Image reg = game.getPlayer().getCurrReg().getImage().
                getScaledInstance(300, 400, Image.SCALE_DEFAULT);
        JLabel display = new JLabel(new ImageIcon(reg));
        display.setOpaque(true);

        GridBagConstraints gbc = new GridBagConstraints();
        regionScreen = new JPanel();
        regionScreen.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        regionScreen.add(display, gbc);
        gbc.gridy = 3;
        regionScreen.add(regionInfo, gbc);
        this.contentPane.add(regionScreen);
        regionScreen.setVisible(true);
    }

    public void setUpTradeScreen() {

        // aliasing so code doesn't get too cluttered
        Item[] items = game.getPlayer().getCurrReg().getMarket().getItem();
        Ship ship = game.getPlayer().getShip();
        Item[] inventory = game.getPlayer().getShip().getInventory();

        JTextField shipCapacity = new JTextField("Ship capcity: "
            + ship.getCargo() + "/" + ship.getMaxCargo());
        formatText(shipCapacity, false, 300, 30);
        JTextField playerCredit = new JTextField("Credits: "
            + game.getPlayer().getCredit());
        formatText(playerCredit, false, 300, 30);

        JPanel buyMarket = new JPanel();
        buyMarket.setLayout(new GridBagLayout());

        JTextField buyName = new JTextField("Item");
        formatText(buyName, false, 300, 30);
        addWithGBC(buyMarket, buyName, new int[] {0, 0, 1, 1, 120, 0});
        JTextField buyPrice = new JTextField("Price");
        formatText(buyPrice, false, 300, 30);
        addWithGBC(buyMarket, buyPrice, new int[] {1, 0, 1, 1, 60, 0});
        JTextField buyAmount = new JTextField("Available");
        formatText(buyAmount, false, 300, 30);
        addWithGBC(buyMarket, buyAmount, new int[] {2, 0, 1, 1, 2, 0});

        JPanel sellMarket = new JPanel();
        sellMarket.setLayout(new GridBagLayout());

        JTextField sellName = new JTextField("Item");
        formatText(sellName, false, 300, 30);
        addWithGBC(sellMarket, sellName, new int[] {0, 0, 1, 1, 120, 0});
        JTextField sellPrice = new JTextField("Price");
        formatText(sellPrice, false, 300, 30);
        addWithGBC(sellMarket, sellPrice, new int[] {1, 0, 1, 1, 60, 0});
        JTextField sellAmount = new JTextField("Available");
        formatText(sellAmount, false, 300, 30);
        addWithGBC(sellMarket, sellAmount, new int[] {2, 0, 1, 1, 2, 0});

        for (int i = 0; i < items.length; i++) {
            JTextField itemBuyName = new JTextField(items[i].getName());
            formatText(itemBuyName, false, 300, 30);
            JTextField itemBuyPrice = new JTextField("" + items[i].getPrice());
            formatText(itemBuyPrice, false, 300, 30);
            JTextField itemBuyAmount = new JTextField("" + items[i].getAmount());
            formatText(itemBuyAmount, false, 300, 30);
            JTextField itemSellName = new JTextField(inventory[i].getName());
            formatText(itemSellName, false, 300, 30);
            JTextField itemSellPrice = new JTextField("" + inventory[i].getPrice());
            formatText(itemSellPrice, false, 300, 30);
            JTextField itemSellAmount = new JTextField("" + inventory[i].getAmount());
            formatText(itemSellAmount, false, 300, 30);

            addWithGBC(buyMarket, itemBuyName, new int[] {0, i + 1, 1, 1, 0, 0});
            addWithGBC(buyMarket, itemBuyPrice, new int[] {1, i + 1, 1, 1, 0, 0});
            addWithGBC(buyMarket, itemBuyAmount, new int[] {2, i + 1, 1, 1, 0, 0});
            addWithGBC(sellMarket, itemSellName, new int[] {0, i + 1, 1, 1, 0, 0});
            addWithGBC(sellMarket, itemSellPrice, new int[] {1, i + 1, 1, 1, 0, 0});
            addWithGBC(sellMarket, itemSellAmount, new int[] {2, i + 1, 1, 1, 0, 0});

            int index = i;

            JButton buttonBuy = new JButton("Buy");
            formatButton(buttonBuy, 300, 30);
            buttonBuy.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (items[index].getPrice() <= game.getPlayer().getCredit()
                            && items[index].getAmount() > 0
                            && ship.getCargo() <= ship.getMaxCargo()) {
                        items[index].changeAmount(-1);
                        itemBuyAmount.setText("" + items[index].getAmount());
                        inventory[index].changeAmount(1);
                        itemSellAmount.setText("" + inventory[index].getAmount());
                        ship.changeCargo(1);
                        shipCapacity.setText("Ship capcity: " + ship.getCargo()
                            + "/" + ship.getMaxCargo());
                        game.getPlayer().changeCredit(-items[index].getPrice());
                        playerCredit.setText("Credits: " + game.getPlayer()
                            .getCredit());
                    }
                }
            });
            JButton buttonSell = new JButton("Sell");
            formatButton(buttonSell, 300, 30);
            buttonSell.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (inventory[index].getAmount() > 0) {
                        items[index].changeAmount(1);
                        itemBuyAmount.setText("" + items[index].getAmount());
                        inventory[index].changeAmount(-1);
                        itemSellAmount.setText("" + inventory[index].getAmount());
                        ship.changeCargo(-1);
                        shipCapacity.setText("Ship capcity: " + ship.getCargo()
                            + "/" + ship.getMaxCargo());
                        game.getPlayer().changeCredit(inventory[index].getPrice());
                        playerCredit.setText("Credits: " + game.getPlayer()
                            .getCredit());
                    }
                }
            });
            addWithGBC(buyMarket, buttonBuy, new int[] {3, i + 1, 1, 1, 0, 0});
            addWithGBC(sellMarket, buttonSell, new int[] {3, i + 1, 1, 1, 0, 0});
        }

        JButton buttonBack = new JButton("Back");
        formatButton(buttonBack, 300, 30);
        buttonBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tradeScreen.setVisible(false);
                setUpRegionScreen();
            }
        });

        JTextField buyBanner = new JTextField("Buy");
        formatText(buyBanner, false, 300, 40);
        JTextField sellBanner = new JTextField("Sell");
        formatText(sellBanner, false, 300, 40);


        tradeScreen = new JPanel();
        tradeScreen.setLayout(new GridBagLayout());
        addWithGBC(tradeScreen, buyBanner, new int[] {0, 0, 1, 1, 0, 20},
            new Insets(0, 0, 14, 0));
        addWithGBC(tradeScreen, sellBanner, new int[] {1, 0, 1, 1, 0, 0},
            new Insets(0, 0, 14, 0));
        addWithGBC(tradeScreen, buyMarket, new int[] {0, 1, 1, 1, 0, 0});
        addWithGBC(tradeScreen, sellMarket, new int[] {1, 1, 1, 1, 0, 0});
        addWithGBC(tradeScreen, shipCapacity, new int[] {0, 2, 1, 1, 0, 0},
            new Insets(14, 0, 0, 0));
        addWithGBC(tradeScreen, playerCredit, new int[] {1, 2, 1, 1, 0, 0},
            new Insets(14, 0, 0, 0));
        addWithGBC(tradeScreen, buttonBack, new int[] {0, 3, 2, 1, 0, 0});
        this.contentPane.add(tradeScreen);
        tradeScreen.setVisible(true);
    }

    public void setUpMapScreen() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel targetImage = new JLabel(new ImageIcon(game.getPlayer()
            .getCurrReg().getImage().getScaledInstance(300, 400,
            Image.SCALE_DEFAULT)));
        JTextField targetName = new JTextField("Name:\t" + game.getPlayer()
            .getCurrReg().getName());
        formatText(targetName, false, 0, 0);
        JTextField targetLoc = new JTextField("Location:\t" + game.getPlayer()
            .getCurrReg().getCoord());
        formatText(targetLoc, false, 0, 0);
        JTextField targetTech = new JTextField("Tech Level:\t" + game
            .getPlayer().getCurrReg().getTechLevel());
        formatText(targetTech, false, 0, 0);
        JTextField targetCost = new JTextField("Cost:\t0.00");
        formatText(targetCost, false, 0, 0);
        targetedRegion = game.getPlayer().getCurrReg();

        JPanel targetInfo = new JPanel();
        targetInfo.setLayout(new GridLayout(0, 1));
        targetInfo.add(targetName);
        targetInfo.add(targetLoc);
        targetInfo.add(targetTech);
        targetInfo.add(targetCost);

        JPanel mapDisplay = new JPanel();
        mapDisplay.setLayout(new GridBagLayout());

        JButton[] regionButton = new JButton[game.getUniverse()
            .getRegionList().size()];
        for (int i = 0; i < regionButton.length; i++) {
            regionButton[i] = new JButton(" ");
            formatButton(regionButton[i], 0, 0);
            regionButton[i].setLocation(regionButton[i].getX(),
                regionButton[i].getY());
            gbc.gridx = game.getUniverse().getRegionList().get(i).getX() + 200;
            gbc.gridy = game.getUniverse().getRegionList().get(i).getY() + 200;

            Region reg = game.getUniverse().getRegionList().get(i);
            regionButton[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    changeTargetDisplay(reg, targetImage, targetName, targetLoc,
                        targetTech, targetCost);
                    targetedRegion = reg;
                }
            });
            mapDisplay.add(regionButton[i], gbc);
        }

        JTextField playerLocName = new JTextField("Current Region: " + game
            .getPlayer().getCurrReg().getName());
        formatText(playerLocName, false, 0, 0);
        JTextField playerLocCoord = new JTextField("Current Location: "
            + game.getPlayer().getCurrReg().getCoord());
        formatText(playerLocCoord, false, 0, 0);
        JTextField shipHP = new JTextField("Ship HP: " + game.getPlayer()
            .getShip().getHealth() + "/" + game.getPlayer().getShip().getMaxHealth());
        formatText(shipHP, false, 0, 0);
        JTextField shipFuel = new JTextField("Ship Fuel: " + game.getPlayer()
            .getShip().getFuel() + "/" + game.getPlayer().getShip().getMaxFuel());
        formatText(shipFuel, false, 0, 0);
        JPanel subInfoPanel = new JPanel();
        subInfoPanel.setLayout(new GridLayout(2, 2));
        subInfoPanel.add(shipHP);
        subInfoPanel.add(shipFuel);
        subInfoPanel.add(playerLocName);
        subInfoPanel.add(playerLocCoord);

        JButton cancelTravel = new JButton("Back");
        formatButton(cancelTravel, 0, 0);
        cancelTravel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mapScreen.setVisible(false);
                setUpRegionScreen();
            }
        });
        JButton confirmTravel = new JButton("Travel");
        formatButton(confirmTravel, 0, 0);
        confirmTravel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (targetedRegion != null) {
                    mapScreen.setVisible(false);
                    game.getPlayer().setCurrReg(targetedRegion);
                    setUpRegionScreen();
                    // calculate buy and sell prices depending on player merchant skill
                    game.calculateMarketPrice();
                }
            }
        });

        mapScreen = new JPanel();
        mapScreen.setLayout(new GridBagLayout());
        addWithGBC(mapScreen, mapDisplay, new int[] {0, 0, 1, 2, 0, 0},
            new Insets(30, 22, 30, 22));
        addWithGBC(mapScreen, subInfoPanel, new int[] {0, 2, 1, 1, 0, 0});
        addWithGBC(mapScreen, targetImage, new int[] {1, 0, 2, 1, 0, 0});
        addWithGBC(mapScreen, targetInfo, new int[] {1, 1, 2, 1, 0, 40});
        addWithGBC(mapScreen, cancelTravel, new int[] {1, 2, 1, 1, 70, 0});
        addWithGBC(mapScreen, confirmTravel, new int[] {2, 2, 1, 1, 70, 0});

        this.contentPane.add(mapScreen);
        mapScreen.setVisible(true);
    }

    public void changeTargetDisplay(Region region, JLabel image,
            JTextField name, JTextField loc, JTextField tech, JTextField cost) {
        image.setIcon(new ImageIcon(region.getImage().getScaledInstance(300,
            400, Image.SCALE_DEFAULT)));
        name.setText("Name:\t" + region.getName());
        loc.setText("Location:\t" + region.getCoord());
        tech.setText("Tech Level:\t" + region.getTechLevel());
        cost.setText(String.format("Cost:\t%.2f", game.getCost(region)));
    }

    public void createLabelTimer(JLabel text) {
        Timer blinkingTimer = new Timer(500, new ActionListener() {
            private boolean on = false;
            public void actionPerformed(ActionEvent e) {
                if (on) {
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
            private boolean on = false;
            public void actionPerformed(ActionEvent e) {
                if (on) {
                    text.setForeground(Color.CYAN);
                } else {
                    text.setForeground(Color.WHITE);
                }
                on = !on;
            }
        });
        blinkingTimer.start();
    }

    // public void setBackground(File background) {
    //     try {
    //         final Image backgroundImage = javax.imageio.ImageIO.read(background);
    //         setContentPane(new JPanel(new BorderLayout()) {
    //             @Override public void paintComponent(Graphics g) {
    //                 g.drawImage(backgroundImage, 0, 0, null);
    //             }
    //         });
    //     } catch (java.io.IOException e) {
    //         e.printStackTrace();
    //     }
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
    public void addWithGBC(JPanel jPan, JComponent jCom, int[] val, Insets in) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = val[0];
        gbc.gridy = val[1];
        gbc.gridwidth = val[2];
        gbc.gridheight = val[3];
        gbc.ipadx = val[4];
        gbc.ipady = val[5];
        gbc.insets = in;
        gbc.fill = GridBagConstraints.BOTH;
        jPan.add(jCom, gbc);
    }
    public void addWithGBC(JPanel jPan, JComponent jCom, int[] vals) {
        addWithGBC(jPan, jCom, vals, new Insets(0, 0, 0, 0));
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