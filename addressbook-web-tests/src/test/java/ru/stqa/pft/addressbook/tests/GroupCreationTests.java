package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.goTo().groupPage();
        Groups before = app.group().all(); //change .getList method to .all
        //GroupData group = new GroupData("test1", null, null);
        GroupData group = new GroupData().withName("test1");
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
