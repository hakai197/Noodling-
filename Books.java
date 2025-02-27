import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Books extends DatasetReader {
    public Books(String filePath) throws IOException {
        super(filePath);
    }

    @Override
    public void processRecord(String[] info) {
        if (info.length < 3) {
            System.out.println("You forgot to add something: " + Arrays.toString(info));
        }

    }

    public String showBookInfo(String title) {
        String results = "";
        boolean found = false;
        for (String[] book : getInfo()) {
            if (book[0].equals(title)) {
                results += "Title: " + book[0] + "\n";
                results += "Author: " + book[1] + "\n";
                results += "Genre: " + book[2] + "\n";
                found = true;

            }
        }
        if (!found) {
            results += "You don't own " + title + " yet......you need to buy it.";
        }
        return results;
    }

    public void reloadData() throws IOException {
        List<String[]> records = getInfo();
        getInfo().clear();

        File file = new File(getFilePath());
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] record = line.split(" \\| ");
                if (record.length >= 7) {
                    records.add(record);
                } else {
                    System.out.println("Invalid record skipped:" + Arrays.toString(record));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading data from file: " + e.getMessage());
        }
    }
}