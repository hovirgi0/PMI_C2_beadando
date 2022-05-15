import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

import static java.lang.System.out;

public class ModifyElement {
    public static void main(String[] args) throws Exception {
        modifyUserInfo();
    }
    static String modifyUserInfo() throws Exception {
        Scanner in = new Scanner(System.in);
        File inputFile = new File("src/main/resources/UserInfo.xml");
        File tempFile = new File("myTempUserInfo.xml");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String currentLine;

        String lineToRemove;
        System.out.println("Melyik adatot szeretné módosítani: ");
        lineToRemove = in.next();

        while((currentLine = reader.readLine()) != null) {
            String trimmedLine = currentLine.trim();
            if(trimmedLine.startsWith(lineToRemove)) continue;
            System.out.println(trimmedLine);
            writer.write((currentLine) + System.getProperty("line.separator"));

            Scanner scan = new Scanner(System.in);
            out.println("Új adat: ");
            String elem = scan.nextLine();

            if(elem == "Név: "){

            }
        }
        writer.close();
        reader.close();
        Files.move(tempFile.toPath(), inputFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        return currentLine;
    }
}