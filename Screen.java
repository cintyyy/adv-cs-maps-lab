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
        stateMap.put(new State(), new StateInfo());
    }

    public void displayPlaylist(MyArrayList<Song> playlist) {
        String songs = "";

        for (int i = 0; i < playlist.size(); i++) {
            songs += (i + 1) + ". " + playlist.get(i).toString() + "\n";
        }

        textArea.setText(songs);
    }

    public void actionPerformed(ActionEvent e) {
        // Shuffle
        if (e.getSource() == shuffle) {
            playlist.get(0).shuffle(playlist);
            displayPlaylist(playlist);
        }

        // Add song
        else if (e.getSource() == addSong) {
            String addition = addSongF.getText();

            int comma1 = addition.indexOf(", ");
            int comma2 = addition.indexOf(", ", comma1 + 1);
            int comma3 = addition.indexOf(", ", comma2 + 1);

            String songName = addition.substring(0, comma1);
            String artist = addition.substring(comma1 + 2, comma2);
            String album = addition.substring(comma2 + 2, comma3);
            int location = Integer.parseInt(addition.substring(comma3 + 2));

            playlist.add(location-1, new Song(songName, artist, album));
            addSongF.setText("");

            displayPlaylist(playlist);
        }

        // Sort by song
        else if (e.getSource() == sortSong) {
            playlist.get(0).sortBySong(playlist);
            displayPlaylist(playlist);
        }

        // Sort by artist
        else if (e.getSource() == sortArtist) {
            playlist.get(0).sortByArtist(playlist);
            displayPlaylist(playlist);
        }

        // Sort by album
        else if (e.getSource() == sortAlbum) {
            playlist.get(0).sortByAlbum(playlist);
            displayPlaylist(playlist);
        }

        // Delete by location
        else if (e.getSource() == deleteSongLoc) {
            int index = Integer.parseInt(deleteLocationF.getText());
            playlist.get(0).deleteSong(playlist, index);
            deleteLocationF.setText("");
            displayPlaylist(playlist);
        }

        // Delete by song information
        else if (e.getSource() == deleteSong) {
            String info = deleteSongF.getText();
            int comma1 = info.indexOf(", ");
            int comma2 = info.indexOf(", ", comma1 + 1);

            String songName = info.substring(0, comma1);
            String artist = info.substring(comma1 + 2, comma2);
            String album = info.substring(comma2 + 2);

            playlist.get(0).deleteSong(playlist, songName, artist, album);

            deleteSongF.setText("");

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