import javax.swing.*;
import java.awt.*;

public class SortArray extends JPanel {

    //The elements array
    int[] elements;
    boolean sortingComplete = false;
    SortType sortType = null;

    int barWidth = 0;
    double heightScaleFactor = 0;

    //Bubble Sort
    int currSortPos = 0;
    int sortedElements = 0;

    //Selection Sort
    int selectionSortPos = 0;

    //Insertion Sort
    int insertionElementsSorted = 0;

    //Merge Sort
    int mergeSortPos = 0;

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

        for (int i = 0; i < elements.length; i++) {
            if (currSortPos + 1 < elements.length && !sortingComplete) {
                if (this.elements[currSortPos] > this.elements[currSortPos + 1]) {

                    int temp = this.elements[currSortPos];
                    this.elements[currSortPos] = this.elements[currSortPos + 1];
                    this.elements[currSortPos + 1] = temp;
                    sortedElements++;
                }

                currSortPos++;
            } else {

                if (sortedElements == 0) {

                    System.out.println("Bubble Sort Complete");
                    sortingComplete = true;
                } else {

                    currSortPos = 0;
                    sortedElements = 0;
                }
            }
        }
    }

    public void SelectionSort(){

        //One Step = Go over the array find the smallest value and place it at the start of the duplicate array plus n
        int currSmallest = Integer.MAX_VALUE;
        int currSmallestPos = 0;

        if(selectionSortPos < elements.length && !sortingComplete) {
            for (int i = 0; i < elements.length; i++) {

                //Make sure we are not checking the values we have already positioned
                if (i >= selectionSortPos) {
                    //If the number checked is smaller than current smallest set currSmallest to the ith element that is being checked
                    if (elements[i] < currSmallest) {
                        currSmallest = elements[i];
                        currSmallestPos = i;
                    }
                }
            }

            //Switch the smallest element in the array back to the selectionSortPos
            int switchVal = elements[selectionSortPos];
            elements[selectionSortPos] = currSmallest;
            elements[currSmallestPos] = switchVal;
            selectionSortPos++;
        }else{

            System.out.println("Selection Sort Complete");
            sortingComplete = true;
        }
    }

    public void InsertionSort(){

        //EXAMINE ITEM AND Compare to the item on the left
        //Insert the item into the correct position in the array by checking over the elements before it untill it is in the correct spot
        if(!sortingComplete) {

            insertionElementsSorted = 0;

            for (int i = 1; i < elements.length; i++) {

                if (elements[i] < elements[i - 1]) {

                    int tempElementInt = elements[i - 1];

                    elements[i - 1] = elements[i];
                    elements[i] = tempElementInt;
                    insertionElementsSorted++;
                }
            }

            if(insertionElementsSorted == 0){
                sortingComplete = true;
                System.out.println("Insertion Sort Complete");
            }
        }
    }

    public void MergeSort(){

        if(!sortingComplete){

            sortingComplete = true;
            MergeSort_sort(this.elements, 0, this.elements.length - 1);
        }
    }

    public void MergeSort_merge(int inArray[], int startPos, int midPos, int endPos){

        //Find the size of each array
        int leftArrSize = midPos - startPos + 1;
        int rightArrSize = endPos - midPos;

        //System.out.println(leftArrSize);
        //System.out.println(rightArrSize);

        //Temporary arrays
        int tempLeftArr[] = new int[leftArrSize];
        int tempRightArr[] = new int[rightArrSize];

        //Populate the temp arrays
        for(int i = 0; i < leftArrSize; ++i){
            tempLeftArr[i] = inArray[startPos + i];
        }
        for(int i = 0; i < rightArrSize; ++i){
            tempRightArr[i] = inArray[midPos + 1 + i];
        }

        //Merge the temp arrays
        int leftIndex = 0, rightIndex = 0, mergedIndex = startPos;

        //Iterate through all elements in LeftArray and RightArray
        while(leftIndex < leftArrSize && rightIndex < rightArrSize){

            //Check if the leftArray at index is less than the rightArray at index
            if(tempLeftArr[leftIndex] <= tempRightArr[rightIndex]){

                //Set the final array value at index to the value from the leftArray at index
                inArray[mergedIndex] = tempLeftArr[leftIndex];
                leftIndex++;
            }else{

                //Set the final array value at index to the value from the rightArray at index
                inArray[mergedIndex] = tempRightArr[rightIndex];
                rightIndex++;
            }

            mergedIndex++;
        }

        //Check for any remaining elements in tempLeftArray and tempRightArray
        while(leftIndex < leftArrSize) {

            inArray[mergedIndex] = tempLeftArr[leftIndex];
            leftIndex++;
            mergedIndex++;
        }

        while(rightIndex < rightArrSize) {

            inArray[mergedIndex] = tempRightArr[rightIndex];
            rightIndex++;
            mergedIndex++;
        }

        //System.out.println("Elements: " + this.elements[0]);
        //System.out.println("inArray: " + inArray[0]);

        //int min = Integer.MAX_VALUE;
        //for(int x = 0; x < this.elements.length; x++){

            //if(elements[x] < min){
                //min = elements[x];
            //}
        //}

        //System.out.println("Min in Elements: " + min );
    }

    public void MergeSort_sort(int inArray[], int startPos, int endPos){

        if(startPos < endPos) {

            //System.out.println("Start Pos: " + startPos);
            //System.out.println("End Pos: " + endPos);

            //Find array mid-point
            int midPos = startPos + (endPos - startPos) / 2;

            //Sort each of the halves
            MergeSort_sort(inArray, startPos, midPos); //Left Half
            MergeSort_sort(inArray, midPos + 1, endPos); //Right half

            //Merge the halves
            MergeSort_merge(inArray, startPos, midPos, endPos);

            //Everytime sort is called also call paint
            Graphics g = this.getGraphics();
            paint(g);
        }
    }

    //Paint the current array and then do one step of the sort
    @Override
    public void paint(Graphics g){

        Graphics2D graphics2D = (Graphics2D) g;
        super.paintComponent(graphics2D);

        //int min = Integer.MAX_VALUE;
        //for(int x = 0; x < this.elements.length; x++){

        //if(elements[x] < min){
        //min = elements[x];
        //}
        //}

        //System.out.println("Min in Elements: " + min );

        for(int i = 0; i < this.elements.length; i++){

            //System.out.println("x: " + i + ", y: " + VisualGUI.WIN_HEIGHT + ", width: " + barWidth + ", height: " + (int) (this.elements[i] / heightScaleFactor));
            graphics2D.setColor(Color.pink);
            graphics2D.fillRect(i, VisualGUI.WIN_HEIGHT, barWidth, (int) -(this.elements[i] / heightScaleFactor));
        }

        //Run the correct sort algorithm that has been requested
        if(!sortingComplete) {
            switch (sortType) {
                case Bubble:
                    BubbleSort();
                    break;

                case Selection:
                    SelectionSort();
                    break;

                case Insertion:
                    InsertionSort();
                    break;

                case Merge:
                    MergeSort();
                    break;

                default:
                    break;
            }
        }
    }
}
