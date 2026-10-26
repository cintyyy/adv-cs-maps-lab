import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Font;

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

        textArea = new JTextArea();
        textArea.setBounds(450, 80, 520, 500);
        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.PLAIN, 16));
        add(textArea);

        stateMap = new MyHashMap<State, StateInfo>();
        stateMap.put(new State("California", "CA"), new StateInfo("Sacramento", 39));
        stateMap.put(new State("Washington", "WA"), new StateInfo("Olympia", 8));
        stateMap.put(new State("New York", "NY"), new StateInfo("Albany", 20));
    }

    public void deleteState(/*idk what goes here */){

        //ask user for state abbreviation, then delete the state
        stateMap.remove();
    }

    public void actionPerformed(ActionEvent e) {
        // Delete a state
        if (e.getSource() == deleteState) {
        }

        // View state information
        else if (e.getSource() == viewStateInfo) {
        }

        // View all states (key values only)
        else if (e.getSource() == viewAllStates) {
            String states = "";
            DLList<State> stateList = stateMap.keySet().toDLList();
            for (int i = 0; i < stateList.size(); i++){
                states += stateList.get(i).getAbb() + "\n";
            }
            textArea.setText(states);
        }

        else if (e.getSource() == addPicture) {
            addPictureF.getText();
        }

        // Add a new state
        else if (e.getSource() == addState) {
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1000, 600);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Title
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("PLAYLIST MANAGER", 40, 35);

        // Add song
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("ADD A SONG", 40, 110);

        g.setFont(new Font("Arial", Font.PLAIN, 14));
        g.drawString("Format: Song, Artist, Album, Location", 40, 130);

        // Sort playlist
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("SORT PLAYLIST", 40, 245);

        // Delete by location
        g.drawString("DELETE BY LOCATION", 40, 340);

        g.setFont(new Font("Arial", Font.PLAIN, 14));
        g.drawString("Enter 1 for the first song", 40, 355);

        // Delete by song information
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("DELETE BY SONG INFORMATION", 40, 450);

        g.setFont(new Font("Arial", Font.PLAIN, 14));
        g.drawString("Format: Song, Artist, Album", 40, 465);

        // Current playlist
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("CURRENT PLAYLIST", 450, 55);
    }
}