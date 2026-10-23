public class TwoWayLogAdapter implements ModernConsole, LegacyPrinter {

    @Override
    public void logMessage(String tag, String body){
        System.out.println("[" + tag + "] " + body);
    }

    @Override
    public void printLine(String combinedText){
        if (combinedText.startsWith("[") && combinedText.contains("]")) {
            int end = combinedText.indexOf("]");
            String tag = combinedText.substring(1, end);
            String body = combinedText.substring(end + 1).trim();

            logMessage(tag, body);
        } else {
            System.out.println(combinedText);
        }
    }

    public static void main(String[] args){
        String studentId = "230103256";

        System.out.println("Student ID:" + studentId);

        TwoWayLogAdapter adapter = new TwoWayLogAdapter();

        adapter.logMessage(studentId, "Modern system log");

        adapter.printLine("[" + studentId + "] Legacy system log");
    }
}