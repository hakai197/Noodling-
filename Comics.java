import java.io.IOException;

public class Comics extends DatasetReader{
    public Comics(String filePath) throws IOException {
        super(filePath);
    }

    @Override
    public void processRecord(String[] record) {

    }
}
