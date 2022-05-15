import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import static java.lang.System.out;

import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;
import java.io.*;

import java.awt.event.KeyEvent;

public class Main {
    private static final char SEPARATOR = ';';

    public static String getFormattedXML(String xmlRaw) throws Exception { // Elemek listájának lekérdezése, kiíratása a konzolra.

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance(); //
        documentBuilderFactory.setValidating(false);
        Document document = documentBuilderFactory.newDocumentBuilder().parse(new InputSource(new StringReader(xmlRaw)));
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty("encoding", "UTF-8");
        transformer.setOutputProperty("indent", "yes");
        StringWriter stringWriter = new StringWriter();
        transformer.transform(new DOMSource(document), new StreamResult(stringWriter));
        String formatted = stringWriter.toString();

        try{
        Element rootElement = document.getDocumentElement();

        //build a list of created Users
        NodeList childNodeList = rootElement.getChildNodes();
        Node node;
        for (int i = 0; i < childNodeList.getLength(); i++) {
            node = childNodeList.item(i);

          System.out.println(node.getTextContent());

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                NodeList childNodesOfUserTag = node.getChildNodes();
                String nev = "", kor = "", nem = "", magassag = "", testsuly = "";
                for (int j = 0; j < childNodesOfUserTag.getLength(); j++) {
                    Node childNodeOfUserTag = childNodesOfUserTag.item(j);
                    if (childNodeOfUserTag.getNodeType() == Node.ELEMENT_NODE) {
                        switch (childNodeOfUserTag.getNodeName()) {
                            case "Sikeres név megadása: " -> {
                                nev = childNodeOfUserTag.getTextContent();
                                System.out.println("sikeres megadása");
                            }
                            case "Sikeres kor megadása" -> {
                                kor = childNodeOfUserTag.getTextContent();
                                System.out.println("sikeres megadása");
                            }
                            case "Sikeres nem megadása" -> {
                                nem = childNodeOfUserTag.getTextContent();
                                System.out.println("sikeres megadása");
                            }
                            case "Sikeres magasság megadása" -> {
                                magassag = childNodeOfUserTag.getTextContent();
                                System.out.println("sikeres megadása");
                            }
                            case "Sikeres testsúly megadása" -> {
                                testsuly = childNodeOfUserTag.getTextContent();
                                System.out.println("sikeres megadása");
                            }
                        }
                    }
                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
        return formatted;
    }

    public static void main(String[] args) throws Exception {

        //Menü pontok XML fájlban való rögzítése
        String unFormattedXML ="<menu><nev>Nev</nev><kor>Kor</kor><nem>Nem</nem><magassag>Magassag</magassag><testsuly>Testsuly</testsuly></menu>";
        String formattedXML = getFormattedXML(unFormattedXML);
        //System.out.println(formattedXML);

        //Writing formatted XML to a file
        FileWriter fileWriter = new FileWriter("formattedXML.xml");
        fileWriter.write(formattedXML);
        fileWriter.close(); // closing file writer stream

        //ENTER lenyomásával a felhasználó elkezdheti rögzíteni a menüpontokba az adatait...
        System.out.println("Saját adatai rögzítéséhez nyomjon 'ENTER' -t! \nMódosításukhoz használja a 'Modify' szót!\nEltávolításukhoz a 'Remove' szót!\n");
        Scanner readinput = new Scanner(System.in);

        String presskey = "";
        System.out.print(presskey);

        presskey = readinput.nextLine();
        System.out.print(presskey);

        if(presskey.equals("")) {
            newUserInfo(); //Új elem rögzítése metódus
        }
        else if(presskey == "Modify"){
            ModifyElement modify = new ModifyElement(); //Elem módosítása metódus
            System.out.println(ModifyElement.modifyUserInfo());
        }
        else if(presskey == "Remove"){
            RemoveElement remove = new RemoveElement(); //Elem törlése metódus
            remove.removeUserInfo();
        }
    }

    static void newUserInfo() throws Exception {
        Scanner scan = new Scanner(System.in);
        out.println("Név: "); //Felhasználó neve
        String nev = scan.nextLine();

        out.println("Kor: "); //Felhasználó kora
        Integer kor = scan.nextInt();

        out.println("Nem: "); //Felhasználó neme
        String nem = scan.nextLine();

        out.println("Magasság: "); //Felhasználó magassága
        Integer magassag = scan.nextInt();

        out.println("Testsúly: "); //Felhasználó testsúly
        Integer testsuly = scan.nextInt();

        System.out.println("Név:" + " " + nev + "\n" + "Kor:" + " " + kor + "\n" + "Nem:" + " " + nem + "\n" + "Magasság:" + " " + magassag + "\n" + "Testsúly:" + " " + testsuly);
        scan.close();

        try {
            File file = new File("src/main/resources/UserInfo.xml");
            PrintWriter writer = new PrintWriter(file);
            writer.write("Név:" + " " + nev + "\n" + "Kor:" + " " + kor + "\n" + "Nem:" + " " + nem + "\n" + "Magasság:" + " " + magassag + "\n" + "Testsúly:" + " " + testsuly);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}