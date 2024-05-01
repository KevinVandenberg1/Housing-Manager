// You can ignore this class, this class was me just trying to mess around with Swing and figure out how it works
// I used ChatGPT mainly to help me figure out how to do various things using Swing
// Currently, as it stands this does not work.
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
public class TestClassSwing {
    static ArrayList<Housing> houses = new ArrayList<Housing>();
    static ArrayList<JButton> buttons = new ArrayList<JButton>();
    static JTextArea console = new JTextArea(10, 30);
    static JTextField input = new JTextField(40);
    static int inputState = 0;

    public static JPanel panel;
    public static void main(String[] args) {
        JFrame frame = new JFrame("Housing Manager");
        Dimension userScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int)userScreenSize.getWidth();
        int screenHeight = (int) userScreenSize.getHeight();
        Border border = BorderFactory.createLineBorder(Color.black, 5);
        panel = new JPanel(new GridBagLayout());

        // Setting up gridBag
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5,5,5,5);


        // Adding components to the frame
        setUpDesign(panel, gbc);
        // Setting up the frame
        frame.add(panel);
        frame.setSize((int)(screenWidth * 0.9),(int)(screenHeight * 0.9));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);





        frame.setVisible(true);


    }
    public static void setUpDesign(JPanel panel, GridBagConstraints gbc) {
        // Adds a label for console
        JLabel label1 = new JLabel("Output");
        gbc.gridx = 2;
        gbc.gridy = 29;
        panel.add(label1, gbc);
        // Adds the console
        console = new JTextArea(10, 30);
        console.setEditable(false);
        JScrollPane scroll = new JScrollPane(console);
        gbc.gridx = 2;
        gbc.gridy = 30;
        panel.add(scroll, gbc);
        // Adds a label for console
        JLabel label2 = new JLabel("Input");
        gbc.gridx = 2;
        gbc.gridy = 9;
        panel.add(label2, gbc);
        // Adds the input console
        JTextField input = new JTextField(40);
        gbc.gridx = 2;
        gbc.gridy = 10;
        panel.add(input, gbc);

        input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = input.getText();
                console.append(s + "\n");
                input.setText("");
            }
        });



        addButtons();
        for (JButton i : buttons) {
            gbc.gridx = gbc.gridx +1;
            gbc.gridy =5;
            panel.add(i, gbc);
        }
        

        String menuText = String.format("MENU: %n%s %n%s %n%s %n%s %n%s %n",
        "   \"A\": Add a new house to the housing manager",
        "   \"V\": View one of the houses stored in the housing manager",
        "   \"I\": Add an infraction to one of the residents",
        "   \"R\": Remove an infraction from one of the residents",
        "   \"Q\": Quit the housing manager");
        console.setText(menuText);
    }
    private static void addButtons() {
        JButton quit = new JButton("Quit");
        JButton view = new JButton("View");
        JButton add = new JButton("Add New");
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitProgram();
            }
        });
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                input.requestFocus();
                addNewHouse();
                input.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String s = input.getText();
                        console.append(s + "\n");
                        input.setText("");
                        processUserInput(s);
                    }
                });
                processUserInput("None");
            }
        });
        view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewHouses();
            }
        });
        buttons.add(add);
        buttons.add(view);
        buttons.add(quit);

    }

    private static void processUserInput(String userInput) {
        userInput = userInput.trim();
        String address = "";
        int rooms = 0;
        double bedrooms = 0;
        double bathrooms = 0;
        int capacity = 0;
        boolean studio = false;
        switch(inputState) {
            case 0: {
                console.append("Input the address: \n");
                inputState++;
                break;
            }
            case 1: {
                address = userInput;
                console.append("Input the number of rooms: \n");
                inputState++;
                break;
            }
            case 2: {
                try {
                    rooms = Integer.parseInt(userInput);
                    console.append("Input the number of bedrooms: \n");
                    inputState++;
                } catch (Exception e) {
                    System.out.println("Input an integer for the number of rooms");
                }
            break;
            }
            case 3: {
                try {
                    bedrooms = Double.parseDouble(userInput);
                    console.append("Input the number of bathrooms: \n");
                    inputState++;
                } catch (Exception e) {
                    System.out.println("Input a double for the number of bedrooms");
                }
            break;
            }
            case 4: {
                try {
                    bathrooms = Double.parseDouble(userInput);
                    console.append("Input the human capacity of the building: \n");
                    inputState++;
                } catch (Exception e) {
                    System.out.println("Input a number for the number of bedrooms in the building");
                }
            break;
            }
            case 5: {
                try {
                    capacity = Integer.parseInt(userInput);
                    console.append("Does this building have a studio? \n");
                    inputState++;
                } catch (Exception e) {
                    System.out.println("Input a number for the human capacity of the building");
                }
            break;
            }
            case 6: {
                try {
                    userInput = userInput.toLowerCase();
                    if (userInput.startsWith("y")) {
                        studio = true;
                    } else if (userInput.startsWith("n")) {
                        studio = false;
                    } else {
                        throw new ArithmeticException("Invalid input");
                    }
                    inputState++;
                } catch (Exception e) {
                    System.out.println("Input a number for the number of bedrooms in the building");
                }
                break;
            }
        }
    }
    private static void addNewHouse() {
        ActionListener[] listeners = input.getActionListeners();
        for (ActionListener list : listeners) {
            input.removeActionListener(list);
        }
        hideButtons();
    }
    private static void viewHouses() {
        hideButtons();
    }
    private static void exitProgram() {
        System.exit(0);
    }
    private static void hideButtons() {
        for (JButton button :buttons) {
            button.setVisible(false);
        }
    }
    private void showButtons() {
        for (JButton button :buttons) {
            button.setVisible(true);
        }
    }

}