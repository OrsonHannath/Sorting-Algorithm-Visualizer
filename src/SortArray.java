public class SortArray {

    //The elements array
    int[] elements;

    public SortArray(int _size, int _min, int _max){

        //Set the array to the size passed in
        this.elements = new int[_size];

        for(int i = 0; i < _size; i++){

            //Generate a random number between min and max value and apply that to the ith element
            this.elements[i] = (int) Math.floor(Math.random() * (_max - _min + 1) + _min);
        }
    }
}
