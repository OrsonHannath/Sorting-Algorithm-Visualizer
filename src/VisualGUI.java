import java.awt.*;
import javax.swing.JPanel;
import javax.swing.JFrame;

enum SortType {
    Bubble,
    Selection,
    Insertion,
    Heap,
    Merge
}

public class VisualGUI {

    public static final int WIN_WIDTH = 1200;
    public static final int WIN_HEIGHT = 720;

    private JFrame window;
    private Color bgColor = Color.darkGray;

    private boolean paused = false;

    //Array to sort
    SortArray arrayToSort = null;
    int arraySize = 99999;
    int arrayMin = 0;
    int arrayMax = 999999;

    SortType sortType = SortType.Bubble;

    public VisualGUI(){

        //Set up the window using Swing JFrame
        this.window = new JFrame("Sorting Algorithm Visualizer");
        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.window.setSize(this.WIN_WIDTH, this.WIN_HEIGHT);
        this.window.setVisible(true);

        //Set the background colour of the window
        this.window.getContentPane().setBackground(this.bgColor);

        //Currently has the array size to the window width so one pixel is one element in the array
        arrayToSort = new SortArray(WIN_WIDTH, arrayMin, arrayMax, sortType);
        this.window.add(arrayToSort);
    }

    public static void main(String[] args){

        VisualGUI visualGUI = new VisualGUI();

        //Loop to make the sorting algorithm occur the whole time
        while(!visualGUI.paused){

            visualGUI.arrayToSort.repaint();
        }
    }
}
