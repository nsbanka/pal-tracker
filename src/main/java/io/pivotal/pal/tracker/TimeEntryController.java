package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
@RestController
public class TimeEntryController {
    private  TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }
    @PostMapping("/time-entries")
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry timeEntry = timeEntryRepository.create(timeEntryToCreate);
        ResponseEntity response = new ResponseEntity(timeEntry, HttpStatus.CREATED);
        return response;
    }
    @GetMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {
        TimeEntry timeEntry = timeEntryRepository.find(timeEntryId);
        ResponseEntity response = null;
        if (timeEntry == null)
        {response = new ResponseEntity(timeEntry, HttpStatus.NOT_FOUND);}
        else
        {response = new ResponseEntity(timeEntry, HttpStatus.OK);}

        return response;
    }
    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> timeEntryList = timeEntryRepository.list();
        ResponseEntity response = new ResponseEntity(timeEntryList, HttpStatus.OK);
        return response;
    }
    @PutMapping("/time-entries/{timeEntryId}")
    public ResponseEntity update(@PathVariable long timeEntryId, @RequestBody TimeEntry expected) {
        TimeEntry timeEntry = timeEntryRepository.update(timeEntryId, expected);
        ResponseEntity response = null;
        if (timeEntry == null)
        {response = new ResponseEntity(timeEntry, HttpStatus.NOT_FOUND);}
        else
        {response = new ResponseEntity(timeEntry, HttpStatus.OK);}

        return response;
    }
    @DeleteMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> delete(@PathVariable long timeEntryId) {
        timeEntryRepository.delete(timeEntryId);
        ResponseEntity response = null;
        TimeEntry timeEntry = timeEntryRepository.find(timeEntryId);
        if (timeEntry == null)
        {response = new ResponseEntity(timeEntry, HttpStatus.NO_CONTENT);}
        else
        {response = new ResponseEntity(timeEntry, HttpStatus.PARTIAL_CONTENT);}

        return response;
    }
}
