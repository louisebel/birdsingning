package com.birdsinging.level;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
@Api(value="LevelAPI", description="get or manage the birdsinging Level")
public class LevelController {

    @Autowired
    private LevelService levelService;

    @PostMapping("levels/")
    public ResponseEntity<LevelExposition> postLevel(){
        return new ResponseEntity<>(new LevelExposition(levelService.postLevel()), HttpStatus.CREATED);
    }

    @PatchMapping("levels/{levelId}")
    public ResponseEntity<LevelExposition> patchLevel(@RequestBody LevelExposition levelExposition, @PathVariable Long levelId){
        return new ResponseEntity<>(new LevelExposition(levelService.patchLevel(levelExposition.transformIntoNewLevel(),levelId)), HttpStatus.OK);
    }

    @GetMapping("levels/")
    public ResponseEntity<List<LevelExposition>> getLevelActivate() {
        List<LevelExposition> levelExpositions = levelService.getLevelActivate().stream()
                .map(LevelExposition::new)
                .collect(Collectors.toList());
        if(levelExpositions.isEmpty()) {
            return new ResponseEntity<>(levelExpositions, HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(levelExpositions, HttpStatus.OK);
        }
    }

    @GetMapping("admin/levels/")
    public ResponseEntity<List<LevelExposition>> getLevelForAdmin() {
        List<LevelExposition> levelExpositions = levelService.getLevel().stream()
                .map(LevelExposition::new)
                .collect(Collectors.toList());
        if(levelExpositions.isEmpty()) {
            return new ResponseEntity<>(levelExpositions, HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(levelExpositions, HttpStatus.OK);
        }
    }

}