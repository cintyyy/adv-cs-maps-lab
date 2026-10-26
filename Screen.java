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

        // View state information
        viewStateInfo = new JButton("View State Info");
        viewStateInfo.setBounds(200, 80, 150, 30);
        viewStateInfo.addActionListener(this);
        add(viewStateInfo);
        viewStateInfoF = new JTextField(); // asks user for state abbreviation
        viewStateInfoF.setBounds(40, 80, 150, 30);
        add(viewStateInfoF);

        // Delete state
        deleteState = new JButton("Delete State");
        deleteState.setBounds(200, 150, 150, 30);
        deleteState.addActionListener(this);
        add(deleteState);
        deleteStateF = new JTextField(); // asks user for state abbreviation
        deleteStateF.setBounds(40, 150, 150, 30);
        add(deleteStateF);

        // View all states
        viewAllStates = new JButton("View All States");
        viewAllStates.setBounds(40, 210, 150, 30);
        viewAllStates.addActionListener(this);
        add(viewAllStates);

        // Add more pictures to the state
        addPicture = new JButton("Add Picture to State");
        addPicture.setBounds(200, 150, 150, 30);
        addPicture.addActionListener(this);
        add(addPicture);
        addPictureF = new JTextField(); // asks user for picture url + state abbreviation
        addPictureF.setBounds(40, 150, 150, 30);
        add(addPictureF);

        // Add new state
        addState = new JButton("Add State");
        addState.setBounds(200, 260, 150, 30);
        addState.addActionListener(this);
        add(addState);
        addStateF = new JTextField();
        addStateF.setBounds(40, 260, 150, 30);
        add(addStateF);

        // Output area for state information
        infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.setLineWrap(true);
        infoArea.setBounds(40, 320, 330, 240);
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
            playlist.get(0).sortByAlbum(playlist);
            displayPlaylist(playlist);
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