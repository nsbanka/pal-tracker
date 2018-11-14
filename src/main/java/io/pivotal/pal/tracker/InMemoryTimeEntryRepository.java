package io.pivotal.pal.tracker;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{

   private  HashMap<Integer, TimeEntry> timeEntryMap = new HashMap<Integer, TimeEntry>();

    public TimeEntry create(TimeEntry timeEntry) {
        int i = timeEntryMap.size()+1;
        //if (timeEntryMap.size() > 0) { i = timeEntryMap.size()+1;}
        TimeEntry rtimeEntry = new TimeEntry(i, timeEntry.getProjectId(), timeEntry.getUserId(), LocalDate.parse(timeEntry.getDate().toString()), timeEntry.getHours());
        timeEntryMap.put(i, rtimeEntry);
        return rtimeEntry;

    }

    public TimeEntry find(long id) {
        final TimeEntry[] rTimeEntry = new TimeEntry[1];
        timeEntryMap.forEach((k, v) -> {
            if (id ==v.getId()) {
               rTimeEntry[0] = v;
            }
        });
        return rTimeEntry[0];
    }


    public TimeEntry update(long id, TimeEntry timeEntry) {

        final int[] i = new int[1];
        timeEntryMap.forEach((k, v) -> {
            if (id == v.getId()) {
                i[0] = k;
            }
        });
        timeEntryMap.remove(i[0]);
        timeEntryMap.put(i[0], new TimeEntry(id, timeEntry.getProjectId(),timeEntry.getUserId(),timeEntry.getDate(),timeEntry.getHours()));
        return timeEntryMap.get(i[0]);
    }


    public void delete(long id) {
        final int[] i = new int[1];
        timeEntryMap.forEach((k, v) -> {
            if (id ==v.getId()) {
                i[0] = k;
            }
        });
        timeEntryMap.remove(i[0]);
    }


    public List<TimeEntry> list() {
        List<TimeEntry> list = new ArrayList(timeEntryMap.values());
        return list;
    }
}
