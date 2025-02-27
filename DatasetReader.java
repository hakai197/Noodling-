import java.io.*;
import java.util.*;



public abstract class DatasetReader {
    private List<String[]> info;
    private String filePath;

    public DatasetReader(String filePath) throws IOException {
        this.filePath = filePath;
        this.info = new ArrayList<>();
        readDataset();
    }
    public List<String[]> getInfo() {
        return info;
    }

    public void setInfo(List<String[]> info) {
        this.info = info;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }


    private void readDataset() throws IOException {
        info.clear();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                info.add(scanner.nextLine().split(" \\| "));
            }
        }
    }



    public void addRecord(String record) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
            writer.println(record);
            info.add(record.split(" \\| "));
            System.out.println("Record added: " + record);
        } catch (IOException e) {
            System.err.println("Error writing to dataset: " + e.getMessage());
        }
    }

    public abstract void processRecord(String[] record);
}