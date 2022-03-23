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

        arrayToSort = new SortArray(arraySize, arrayMin, arrayMax);
    }

    public SortType DetermineSortType(){

        return this.sortType;
    }

    public void BubbleSort(){


    }

    public void SelectionSort(){


    }

    public void InsertionSort(){


    }

    public void HeapSort(){


    }

    public void MergeSort(){


    }

    public static void main(String[] args){

        VisualGUI visualGUI = new VisualGUI();

        while(!visualGUI.paused){

            SortType sortType = visualGUI.DetermineSortType();

            //Run the correct sort algorithm that has been requested
            switch (sortType){
                case Bubble:
                    visualGUI.BubbleSort();
                    break;

                case Selection:
                    visualGUI.SelectionSort();
                    break;

                case Insertion:
                    visualGUI.InsertionSort();
                    break;

                case Heap:
                    visualGUI.HeapSort();
                    break;

                case Merge:
                    visualGUI.MergeSort();
                    break;

                default:
                    break;
            }
        }
    }
}
