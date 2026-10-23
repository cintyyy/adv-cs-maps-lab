import javax.swing.JFrame;

// Runner that can be used with JPanel Graphics
public class Runner {
	public static void main( String args[] ) {
        // Create the frame object. Give it a title appropriate to the application
        JFrame frame = new JFrame("MyArrayList Lab");

        //Create the JPanel object and add it to the frame
        Screen canvas = new Screen();

        frame.add(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
