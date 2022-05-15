import java.io.*;
import java.nio.file.Files;
import java.util.Scanner;
import java.nio.file.StandardCopyOption;

public class RemoveElement {
    public static void main(String[] args) throws Exception {
        removeUserInfo();
    }
        static Object removeUserInfo() throws Exception {
        Scanner in = new Scanner(System.in);
        File inputFile = new File("src/main/resources/UserInfo.xml");
        File tempFile = new File("myTempUserInfor.xml");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String currentLine;

        String lineToRemove;
        System.out.println("Melyik adatot szeretné kitörölni: ");
        lineToRemove = in.next();

        while((currentLine = reader.readLine()) != null) {
            String trimmedLine = currentLine.trim();
            if(trimmedLine.startsWith(lineToRemove)) continue;
            System.out.println(trimmedLine);
            writer.write((currentLine) + System.getProperty("line.separator"));
        }
        writer.close();
        reader.close();
        Files.move(tempFile.toPath(), inputFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

            return currentLine;
        }
}