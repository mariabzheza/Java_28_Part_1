package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by maria on 13.04.2016.
 */
public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Group count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    public static void main(String[] args) throws IOException {

        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();

        //The old version of the main method with parameters.
        /*int count = Integer.parseInt(args[0]);
        File file = new File(args[1]);
        List<ContactData> contacts = generateContacts(count);
        save(contacts, file);*/
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        save(contacts, new File(file));
    }

    private void save(List<ContactData> contacts, File file) throws IOException {
        System.out.println(new File(".").getAbsoluteFile());
        Writer writer = new FileWriter(file);
        for (ContactData contact: contacts) {
            //writer.write(String.format("%s;%s;%s\n", group.getName(), group.getHeader(), group.getFooter()));
            writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n",
                    contact.getFirstName(), contact.getLastName(),
                    contact.getNickName(), contact.getCompany(), contact.getTitle(), contact.getHomeAddress(),
                    contact.getHomePhone(), contact.getMobile(), contact.getWorkPhone(),contact.getFax(),
                    contact.getEmail(), contact.getEmail2(), contact.getEmail3(), contact.getHomePage(),
                    contact.getAddress2(), contact.getPhone2(), contact.getNotes(), contact.getGroup()
                    /* if need add photo, please uncomment the next line
                    and add ";%s" into line with String.format(...\n) */
                    //, contact.getPhoto()
            ));
        }
        writer.close();
    }

    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++) {
            //File photo = new File("src/test/resources/stru1.png");
            contacts.add(new ContactData()
                    .withFirstName(String.format("FirstN %s", i))
                    .withLastName(String.format("LastN %s", i))
                    .withNickName(String.format("Nick %s", i))
                    .withTitle(String.format("Title %s", i))
                    .withCompany(String.format("Company %s", i))
                    .withHomeAddress((String.format("HomeAddr %s", i)))
                    .withAddress2((String.format("2-Address %s", i)))
                    .withHomePhone((String.format("HomePhone %s", i)))
                    .withMobile((String.format("Mobile %s", i)))
                    .withWorkPhone((String.format("WorkPhone %s", i)))
                    .withPhone2((String.format("2-Phone %s", i)))
                    .withFax((String.format("Fax %s", i)))
                    .withEmail((String.format("1-Email %s", i)))
                    .withEmail2((String.format("2-Email %s", i)))
                    .withEmail3((String.format("3-Email %s", i)))
                    .withHomePage((String.format("HomePage %s", i)))
                    .withNotes((String.format("Notes %s", i)))
                    .withGroup("test1")
                    //.withPhoto(photo)
            );
        }
        return contacts;
    }
}
