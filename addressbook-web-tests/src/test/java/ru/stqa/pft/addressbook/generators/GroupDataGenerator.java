package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by maria on 12.04.2016.
 */
public class GroupDataGenerator {

    @Parameter(names = "-c", description = "Group count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    //This -d param should be or csv or xml (and it should be added into "Edit configuration" of GroupDataGenerator's).
    @Parameter(names = "-d", description = "Data Format")
    public String format;


    public static void main(String[] args) throws IOException {

        GroupDataGenerator generator = new GroupDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
        //The old version of the main method with parameters.
        //int count = Integer.parseInt(args[0]);
        //File file = new File(args[1]);
        //List<GroupData> groups = generateGroups(count);
        //save(groups, new File(file));
    }

    private void run() throws IOException {
        List<GroupData> groups = generateGroups(count);
        save(groups, new File(file));
    }

    private void save(List<GroupData> groups, File file) throws IOException {
        System.out.println(new File(".").getAbsoluteFile());
        Writer writer = new FileWriter(file);
        for (GroupData group: groups) {
            writer.write(String.format("%s;%s;%s\n", group.getName(), group.getHeader(), group.getFooter()));
        }
        writer.close();
    }

    private List<GroupData> generateGroups(int count) {
        List<GroupData> groups = new ArrayList<GroupData>();
        for (int i = 0; i < count; i++) {
            groups.add(new GroupData()
                    .withName(String.format("test %s", i))
                    .withHeader(String.format("Header %s", i))
                    .withFooter(String.format("Footer %s", i)));
        }
        return groups;
    }
}
