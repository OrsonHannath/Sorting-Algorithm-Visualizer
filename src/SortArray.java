import javax.swing.*;
import java.awt.*;

public class SortArray extends JPanel {

    //The elements array
    int[] elements;
    boolean sortingComplete = false;
    SortType sortType = null;

    int barWidth = 0;
    double heightScaleFactor = 0;

    //Bubble sort
    int currSortPos = 0;
    int sortedElements = 0;

    public SortArray(int _size, int _min, int _max, SortType _sortType){

        //Set the array to the size passed in
        this.elements = new int[_size];
        this.sortType = _sortType;

        barWidth = _size / VisualGUI.WIN_WIDTH;
        heightScaleFactor = _max / VisualGUI.WIN_HEIGHT;

        for(int i = 0; i < _size; i++){

            //Generate a random number between min and max value and apply that to the ith element
            this.elements[i] = (int) Math.floor(Math.random() * (_max - _min + 1) + _min);
        }
    }

    public void BubbleSort(){

        if(currSortPos + 1 < elements.length && !sortingComplete) {
             if(this.elements[currSortPos] > this.elements[currSortPos+1]){

                 int temp = this.elements[currSortPos];
                 this.elements[currSortPos] = this.elements[currSortPos+1];
                 this.elements[currSortPos+1] = temp;
                 sortedElements++;
             }

             currSortPos++;
        }else{

            if(sortedElements == 0){

                System.out.println("Bubble Sort Complete");
                sortingComplete = true;
            }else{

                currSortPos = 0;
                sortedElements = 0;
            }
        }
    }

    public void SelectionSort(){


    }

    public void InsertionSort(){


    }

    public void HeapSort(){


    }

    public void MergeSort(){


    }

    //Paint the current array and then do one step of the sort
    @Override
    public void paint(Graphics g){

        Graphics2D graphics2D = (Graphics2D) g;
        super.paintComponent(graphics2D);

        for(int i = 0; i < this.elements.length; i++){

            //System.out.println("x: " + i + ", y: " + VisualGUI.WIN_HEIGHT + ", width: " + barWidth + ", height: " + (int) (this.elements[i] / heightScaleFactor));
            graphics2D.setColor(Color.pink);
            graphics2D.fillRect(i, VisualGUI.WIN_HEIGHT, barWidth, (int) -(this.elements[i] / heightScaleFactor));
        }

        //Run the correct sort algorithm that has been requested
        switch (sortType){
            case Bubble:
                BubbleSort();
                break;

            case Selection:
                SelectionSort();
                break;

            case Insertion:
                InsertionSort();
                break;

            case Heap:
                HeapSort();
                break;

            case Merge:
                MergeSort();
                break;

            default:
                break;
        }
    }
}
