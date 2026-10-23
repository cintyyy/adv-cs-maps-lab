import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Screen extends JPanel implements ActionListener {
    // Instance variables
    private MyHashMap<State,StateInfo> stateMap;
    private JButton viewStateInfo, deleteState, viewAllStates, addState;

    // Constructor
    public Screen() {
        setFocusable(true);
        setLayout(null);

        viewStateInfo = new JButton("View State Info");
        viewStateInfo.setBounds(40, 40, 150, 30);
        add(viewStateInfo);
        viewStateInfo.addActionListener(this);

        deleteState = new JButton("Delete State");
        deleteState.setBounds(40, 40, 150, 30);
        add(deleteState);
        deleteState.addActionListener(this);

        viewAllStates = new JButton("View All States");
        viewAllStates.setBounds(40, 180, 120, 30);
        add(viewAllStates);
        viewAllStates.addActionListener(this);

        addState = new JButton("Add State");
        addState.setBounds(170, 270, 120, 30);
        add(addState);
        addState.addActionListener(this);

        stateMap = new MyHashMap<State, StateInfo>();
        stateMap.put(new State("California", "CA"), new StateInfo("Sacramento", 39));
        stateMap.put(new State("Washington", "WA"), new StateInfo("Olympia", 8));
        stateMap.put(new State("New York", "NY"), new StateInfo("Albany", 20));
    }

    public void displayPlaylist(MyArrayList<Song> playlist) {
        String songs = "";

        for (int i = 0; i < playlist.size(); i++) {
            songs += (i + 1) + ". " + playlist.get(i).toString() + "\n";
        }

        textArea.setText(songs);
    }


    public void deleteState(/*idk what goes here */){

        //ask user for state abbreviation, then delete the state
        stateMap.remove();
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == deleteState) {
            playlist.get(0).sortBySong(playlist);
            displayPlaylist(playlist);
        }

        // Sort by artist
        else if (e.getSource() == viewStateInfo) {
            playlist.get(0).sortByArtist(playlist);
            displayPlaylist(playlist);
        }

        // Sort by album
        else if (e.getSource() == viewAllStates) {
            playlist.get(0).sortByAlbum(playlist);
            displayPlaylist(playlist);
        }

        // Delete by location
        else if (e.getSource() == addState) {
            int index = Integer.parseInt(deleteLocationF.getText());
            playlist.get(0).deleteSong(playlist, index);
            deleteLocationF.setText("");
            displayPlaylist(playlist);
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