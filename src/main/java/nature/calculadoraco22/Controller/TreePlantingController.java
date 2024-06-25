package nature.calculadoraco22.Controller;

import nature.calculadoraco22.Dto.TreePlantingDto;
import nature.calculadoraco22.Dto.TreePlantingTotalsDto;
import nature.calculadoraco22.Model.TreePlanting;
import nature.calculadoraco22.Service.TreePlantingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/tree-plantings")
public class TreePlantingController {

    private final TreePlantingService treePlantingService;

    public TreePlantingController(TreePlantingService treePlantingService) {
        this.treePlantingService = treePlantingService;
    }

    @PostMapping
    public ResponseEntity<TreePlanting> addTreePlanting(
            @PathVariable Long userId,
            @RequestBody TreePlantingDto treePlantingDto) {
        TreePlanting createdTreePlanting = treePlantingService.addTreePlanting(userId, treePlantingDto);
        return new ResponseEntity<>(createdTreePlanting, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TreePlanting>> getAllTreePlantings(@PathVariable Long userId) {
        List<TreePlanting> treePlantings = treePlantingService.getAllTreePlantings(userId);
        return new ResponseEntity<>(treePlantings, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TreePlanting> getTreePlantingById(
            @PathVariable Long userId,
            @PathVariable Long id) {
        TreePlanting treePlanting = treePlantingService.getTreePlantingById(userId, id);
        if (treePlanting != null) {
            return new ResponseEntity<>(treePlanting, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTreePlanting(
            @PathVariable Long userId,
            @PathVariable Long id) {
        treePlantingService.deleteTreePlanting(userId, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/totals")
    public ResponseEntity<TreePlantingTotalsDto> getTreePlantingTotals(@PathVariable Long userId) {
        TreePlantingTotalsDto totalsDto = treePlantingService.getTreePlantingTotals(userId);
        return new ResponseEntity<>(totalsDto, HttpStatus.OK);
    }
}
