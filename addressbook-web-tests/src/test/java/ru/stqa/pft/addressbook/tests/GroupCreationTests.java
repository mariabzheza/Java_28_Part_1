package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    @DataProvider
    // Ітератор масивів обєктів
    public Iterator<Object[]> validGroups() {
        List<Object[]> list = new ArrayList<Object[]>();
        list.add(new Object[] {new GroupData().withName("test1").withHeader("header 1").withFooter("footer 1")});
        list.add(new Object[] {new GroupData().withName("test2").withHeader("header 2").withFooter("footer 2")});
        list.add(new Object[] {new GroupData().withName("test3").withHeader("header 3").withFooter("footer 3")});
        return list.iterator();
    }

    @Test(dataProvider = "validGroups")
    public void testGroupCreation(GroupData group) {

        //GroupData group = new GroupData("test1", null, null);
        app.goTo().groupPage();
        Groups before = app.group().all(); //change .getList method to .all
        app.group().create(group);
        assertThat(app.group().getGroupCount(), equalTo(before.size() + 1));
        Groups after = app.group().all(); //change .getList method to .all

        //Comparator
        //group.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        //easiest comparator was bellow:
        group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());

        assertThat(after, equalTo(before.withAdded(group)));
    }

    @Test
    public void testBadGroupCreation() {
        app.goTo().groupPage();
        Groups before = app.group().all(); //change .getList method to .all
        //GroupData group = new GroupData("test1", null, null);
        GroupData group = new GroupData().withName("test34'");
        app.group().create(group);
        assertThat(app.group().getGroupCount(), equalTo(before.size()));
        Groups after = app.group().all(); //change .getList method to .all
        assertThat(after, equalTo(before));
    }


}
