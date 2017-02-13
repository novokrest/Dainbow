package org.oneuse.dainbow.goals;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/goals")
public class GoalController {
    private final GoalRepository goalRepository;

    @Autowired
    public GoalController(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    @GetMapping
    public List<Goal> getAllGoals() {
        return Lists.newArrayList(goalRepository.findAll());
    }

    @GetMapping("/{goalId}")
    public ResponseEntity<?> getGoal(@PathVariable long goalId) {
        return goalRepository.findById(goalId)
                .map(goal -> ResponseEntity.ok(goal))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> addGoal(@RequestBody Goal goal) {
        Goal addedGoal = goalRepository.save(goal);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(addedGoal.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{goalId}")
    public ResponseEntity<?> updateGoal(@PathVariable long goalId, @RequestBody Goal goal) {
        return goalRepository.findById(goalId).map(updatedGoal -> {
            updatedGoal.setTitle(goal.getTitle());
            goalRepository.save(updatedGoal);
            return redirect("..");
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{goalId}")
    public ResponseEntity<?> deleteGoal(@PathVariable long goalId) {
        return goalRepository.findById(goalId).map(goal -> {
            goalRepository.delete(goalId);
            return redirect("..");
        }).orElse(ResponseEntity.notFound().build());
    }

    private <T> ResponseEntity<T> redirect(String relativePath) {
        String redirectUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/" + relativePath).toUriString();
        return ResponseEntityEx.redirect(redirectUri);
    }
}
