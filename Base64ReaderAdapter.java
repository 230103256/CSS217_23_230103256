import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64ReaderAdapter extends Reader {
    private final Reader decodedReader;

    public Base64ReaderAdapter(InputStream inputStream) {
        if (inputStream == null) {
            throw new IllegalArgumentException("inputStream must not be null");
        }

        InputStream decodedStream = Base64.getDecoder().wrap(inputStream);
        this.decodedReader = new InputStreamReader(
                decodedStream, StandardCharsets.UTF_8);
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        return decodedReader.read(cbuf, off, len);
    }

    @Override
    public void close() throws IOException {
        decodedReader.close();
    }

    public static void main(String[] args) throws IOException {
        // Replace this placeholder with your actual Student ID.
        String studentId = "YOUR_STUDENT_ID";

        System.out.println("Student ID: " + studentId);

        byte[] encodedBytes = Base64.getEncoder()
                .encode(studentId.getBytes(StandardCharsets.UTF_8));

        try (InputStream input = new ByteArrayInputStream(encodedBytes);
             BufferedReader reader = new BufferedReader(
                     new Base64ReaderAdapter(input))) {

            String decoded = reader.readLine();

            if (!studentId.equals(decoded)) {
                throw new AssertionError(
                        "Decoded ID does not match Student ID: " + decoded);
            }

            assert studentId.equals(decoded) : "Decoded ID mismatch";
            System.out.println("Decoded Student ID: " + decoded);
            System.out.println("Verification: PASSED");
        }
    }
}
