package org.oneuse.dainbow.goals;

public class GoalFactory {
    public static Goal createForTest(long id) {
        return new Goal(id, String.format("Test Goal #%d", id));
    }
}
