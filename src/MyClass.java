import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MyClass {
    public static void main(String[] args) throws InterruptedException {
        ArrayDeque<Item> new_queue = FileReaderToArrayDeque("src/mass_spec.csv");
        while(!new_queue.isEmpty()){
            for(int i = 0; i < new_queue.size(); i++){
                System.out.println(Arrays.toString(new String[]{new_queue.pop().getData()[i]}));
            }
        }
    }

    public static ArrayDeque<Item> FileReaderToArrayDeque(String filename) {
        ArrayDeque<Item> items= new ArrayDeque<>();
        try {
            List<String[]> dataList = new ArrayList();

            File myFile = new File(filename);
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] splitted = data.split("\t");
                dataList.add(splitted);
            }
            myReader.close();

            for (String[] row : dataList) {
                items.add(new Item(row));
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return items;
    }

    static class Item{
        private final String[] dataList;

        Item(String[] dataList){
            this.dataList = dataList;
        }

        public String[] getData()
        {
            return dataList;
        }
    }
}
