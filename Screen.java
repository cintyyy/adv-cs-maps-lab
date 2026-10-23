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
    private MyArrayList<Song> playlist;
    private JTextArea textArea;
    private JTextField addSongF, deleteLocationF, deleteSongF;
    private JButton shuffle, addSong;
    private JButton sortSong, sortArtist, sortAlbum, deleteSongLoc, deleteSong;

    // Constructor
    public Screen() {
        setFocusable(true);
        setLayout(null);

        shuffle = new JButton("Shuffle playlist");
        shuffle.setBounds(40, 40, 150, 30);
        add(shuffle);
        shuffle.addActionListener(this);

        addSongF = new JTextField();
        addSongF.setBounds(40, 140, 380, 30);
        add(addSongF);

        addSong = new JButton("Add Song");
        addSong.setBounds(40, 180, 120, 30);
        add(addSong);
        addSong.addActionListener(this);

        sortSong = new JButton("Sort by Song");
        sortSong.setBounds(40, 270, 120, 30);
        add(sortSong);
        sortSong.addActionListener(this);

        sortArtist = new JButton("Sort by Artist");
        sortArtist.setBounds(170, 270, 120, 30);
        add(sortArtist);
        sortArtist.addActionListener(this);

        sortAlbum = new JButton("Sort by Album");
        sortAlbum.setBounds(300, 270, 120, 30);
        add(sortAlbum);
        sortAlbum.addActionListener(this);

        deleteLocationF = new JTextField();
        deleteLocationF.setBounds(40, 365, 80, 30);
        add(deleteLocationF);

        deleteSongLoc = new JButton("Delete by Location");
        deleteSongLoc.setBounds(130, 365, 160, 30);
        add(deleteSongLoc);
        deleteSongLoc.addActionListener(this);

        deleteSongF = new JTextField();
        deleteSongF.setBounds(40, 490, 250, 30);
        add(deleteSongF);

        deleteSong = new JButton("Delete Song");
        deleteSong.setBounds(300, 490, 130, 30);
        add(deleteSong);
        deleteSong.addActionListener(this);

        textArea = new JTextArea();
        textArea.setBounds(450, 80, 520, 500);
        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.PLAIN, 16));
        add(textArea);

        playlist = new MyArrayList<Song>();

        playlist.add(new Song("Blinding Lights", "The Weeknd", "After Hours"));
        playlist.add(new Song("Bad Guy", "Billie Eilish", "When We All Fall Asleep, Where Do We Go?"));
        playlist.add(new Song("Shape of You", "Ed Sheeran", "Divide"));
        playlist.add(new Song("Levitating", "Dua Lipa", "Future Nostalgia"));
        playlist.add(new Song("As It Was", "Harry Styles", "Harry's House"));

        displayPlaylist(playlist);
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