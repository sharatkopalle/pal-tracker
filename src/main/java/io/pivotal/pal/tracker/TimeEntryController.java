package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class TimeEntryController {
    private TimeEntryRepository timeEntryRepository;
    public TimeEntryController(final TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping("/time-entries")
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry newEntry = timeEntryRepository.create(timeEntryToCreate);
        return new ResponseEntity<TimeEntry>(newEntry, HttpStatus.CREATED);
    }

    @GetMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable("id") long timeEntryId) {
        TimeEntry result = timeEntryRepository.find(timeEntryId);
        if (result != null){
            return new ResponseEntity<TimeEntry>(result, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<TimeEntry>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> resultList = timeEntryRepository.list();
        return new ResponseEntity<List<TimeEntry>>(resultList, HttpStatus.OK);
    }
    @PutMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable("id") long timeEntryId, @RequestBody TimeEntry timeEntryToUpdate) {
        TimeEntry updatedEntry = timeEntryRepository.update(timeEntryId, timeEntryToUpdate);
        if (updatedEntry != null){
            return new ResponseEntity<TimeEntry>(updatedEntry, HttpStatus.OK);
        }
        else{
//return error
            return new ResponseEntity<TimeEntry>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/time-entries/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long timeEntryId) {
        timeEntryRepository.delete(timeEntryId);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}