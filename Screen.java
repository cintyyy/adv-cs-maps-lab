import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import java.awt.Color;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Screen extends JPanel implements ActionListener {
    // Instance variables
    private MyHashMap<State,StateInfo> stateMap;
    private JTextArea textArea;
    private JButton viewStateInfo, deleteState, viewAllStates, addState, addPicture;
    private JTextField viewStateInfoF, deleteStateF, addStateF, addPictureF; 
    private JTextArea infoArea;
    private DLList<BufferedImage> pictures;

    // Constructor
    public Screen() {
        setFocusable(true);
        setLayout(null);

        pictures = new DLList<BufferedImage>();

        // View state information (label at y=80)
        viewStateInfoF = new JTextField();
        viewStateInfoF.setBounds(40, 88, 210, 30);
        add(viewStateInfoF);
        viewStateInfo = new JButton("View State Info");
        viewStateInfo.setBounds(260, 88, 150, 30);
        viewStateInfo.addActionListener(this);
        add(viewStateInfo);

        // Delete state (label at y=150)
        deleteStateF = new JTextField();
        deleteStateF.setBounds(40, 158, 210, 30);
        add(deleteStateF);
        deleteState = new JButton("Delete State");
        deleteState.setBounds(260, 158, 150, 30);
        deleteState.addActionListener(this);
        add(deleteState);

        // Add picture to state (label at y=220)
        addPictureF = new JTextField();
        addPictureF.setBounds(40, 228, 210, 30);
        add(addPictureF);
        addPicture = new JButton("Add Picture");
        addPicture.setBounds(260, 228, 150, 30);
        addPicture.addActionListener(this);
        add(addPicture);

        // Add new state (label at y=290)
        addStateF = new JTextField();
        addStateF.setBounds(40, 298, 210, 30);
        add(addStateF);
        addState = new JButton("Add State");
        addState.setBounds(260, 298, 150, 30);
        addState.addActionListener(this);
        add(addState);

        // View all states
        viewAllStates = new JButton("View All States");
        viewAllStates.setBounds(40, 352, 370, 30);
        viewAllStates.addActionListener(this);
        add(viewAllStates);

        // Output area (label at y=408)
        infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.setLineWrap(true);
        infoArea.setWrapStyleWord(true);
        infoArea.setFont(new Font("Arial", Font.PLAIN, 14));
        infoArea.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(Color.GRAY),
        BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        infoArea.setBounds(40, 415, 370, 150);
        add(infoArea);

        // 3 states with 2 pictures each
        stateMap = new MyHashMap<State, StateInfo>();
        StateInfo ca = new StateInfo("Sacramento", 39);
        ca.addImage("https://i.natgeofe.com/k/5af79b71-007d-46f8-8efe-bf37a504195b/california-golden-gate-bridge_4x3.jpg");
        ca.addImage("https://m.media-amazon.com/images/I/71XxdIPdqaL._AC_UF894,1000_QL80_.jpg");
        stateMap.put(new State("California", "CA"), ca);

        StateInfo wa = new StateInfo("Olympia", 8);
        wa.addImage("https://media.q4launch.website/uploads/sites/93/2020/05/Beautiful-Winter-Mountains-Snow-Forest-1024x683.jpg");
        wa.addImage("https://www.visittheusa.com/wp-content/uploads/2025/09/WA_Washington_State-of-Washington-Tourism_0623_1-Seattle-Skyline_web72DPI_2000px.jpg");
        stateMap.put(new State("Washington", "WA"), wa);

        StateInfo ny = new StateInfo("Albany", 20);
        ny.addImage("https://media.istockphoto.com/id/521714583/photo/new-york-city-midtown-with-empire-state-building-at-sunset.jpg?s=612x612&w=0&k=20&c=paLoZfZnaZSfaBK_DxLls_Ii0hD3r2PBKSlS6M1QxVU=");
        ny.addImage("https://res.cloudinary.com/dtljonz0f/image/upload/c_auto,ar_4:3,w_3840,g_auto/f_auto/q_auto/v1/new-york-city-central-park?_a=BAVHyiGg0");
        stateMap.put(new State("New York", "NY"), ny);

    }


    public void actionPerformed(ActionEvent e) {
        // Delete a state
        if (e.getSource() == deleteState) {
        }

        // View state information
        else if (e.getSource() == viewStateInfo) {
            String abb = viewStateInfoF.getText().trim().toUpperCase();
            pictures.clear();
            if (abb.length() != 2) {
                infoArea.setText("Please enter a 2-letter abbreviation.");
            } else {
                StateInfo info = stateMap.get(new State("", abb));
                if (info == null) {
                    infoArea.setText("State not found.");
                } else {
                    infoArea.setText(info.toString());
                    DLList<String> urls = info.getImages();
                    for (int i = 0; i < urls.size(); i++) {
                        try {
                            BufferedImage img = ImageIO.read(new java.net.URL(urls.get(i)));
                            if (img != null) {
                                pictures.add(img);
                            }
                        } catch (Exception ex) {
                            infoArea.append("\nCould not load picture " + (i + 1));
                        }
                    }
                }
            }
        }

        // View all states (key values only)
        else if (e.getSource() == viewAllStates) {
            String states = "";
            DLList<State> stateList = stateMap.keySet().toDLList();
            for (int i = 0; i < stateList.size(); i++){
                states += stateList.get(i).toString() + "\n";
            }
            infoArea.setText(states);
        }

        else if (e.getSource() == addPicture) {
            String text = addPictureF.getText();

            int comma = text.indexOf(", ");

            String url = text.substring(0, comma);
            String abbr = text.substring(comma + 2);

            DLList<State> stateList = stateMap.keySet().toDLList();
            for (int i = 0; i < stateList.size(); i++) {
                if (stateList.get(i).getAbb().equals(abbr)) {
                    stateMap.get(new State(stateList.get(i).getName(), abbr)).addImage(url);
                }
            }
            
            addPictureF.setText("");
        }

        // Add a new state
        else if (e.getSource() == addState) {
            String text = addStateF.getText();
            // (name, abbreviation, capital, and population)
            int comma = text.indexOf(", ");
            int comma2 = text.indexOf(", ", comma + 1);
            int comma3 = text.indexOf(", ", comma2 + 1);

            String name = text.substring(0, comma);
            String abbr = text.substring(comma + 2, comma2);
            String capital = text.substring(comma2 + 2, comma3);
            int population = Integer.parseInt(text.substring(comma3 + 2));

            stateMap.put(new State(name, abbr), new StateInfo(capital, population));

            System.out.println(name + " " + abbr + " " + capital + " " + population);
        }
        repaint(); 
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1000, 600);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setFont(new Font("Arial", Font.BOLD, 22));
        g.drawString("STATES DATABASE", 40, 40);

        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString("VIEW STATE INFO", 40, 80);
        g.drawString("DELETE STATE", 40, 150);
        g.drawString("ADD PICTURE TO STATE", 40, 220);
        g.drawString("ADD STATE", 40, 290);
        g.drawString("INFORMATION", 40, 408);
        g.drawString("PICTURES", 440, 40);

        g.setFont(new Font("Arial", Font.PLAIN, 12));
        g.drawString("Enter abbreviation (e.g. CA)", 160, 80);
        g.drawString("Enter abbreviation (e.g. CA)", 160, 150);
        g.drawString("Format: URL, abbreviation", 200, 220);
        g.drawString("Format: Name, Abbreviation, Capital, Population", 110, 290);

        for (int i = 0; i < pictures.size() && i < 4; i++) {
            int x = 440 + (i % 2) * 280;
            int y = 55 + (i / 2) * 210;
            g.drawImage(pictures.get(i), x, y, 260, 195, null);
        }
    }
}