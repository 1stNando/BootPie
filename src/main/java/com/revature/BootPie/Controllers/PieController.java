package com.revature.BootPie.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.BootPie.Exceptions.ResourceNotFoundException;
import com.revature.BootPie.Models.Pie;
import com.revature.BootPie.Services.PieService;

@Controller
@RequestMapping("pie")
public class PieController {
  
  private PieService pieService;

  @Autowired
  public PieController(PieService pieService) {
    this.pieService = pieService;
  }

  @RequestMapping(method = RequestMethod.GET)
  public @ResponseBody List<Pie> getPieList() {
    return pieService.getPieList();
  }

  // GET http://localhost:8080/pie/Apple
  @GetMapping("{pieName}")
  public @ResponseBody ResponseEntity<?> findPie(@PathVariable String pieName) {
    try {
        Pie foundPie = pieService.findPie(pieName);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(foundPie);
    } catch (ResourceNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
  // public @ResponseBody ResponseEntity<Pie> findPie(@RequestParam String pieName) {
  //     return new ResponseEntity<>(pieService.findPie(pieName), HttpStatus.ACCEPTED);

  // }


  @GetMapping("calories/{limit}")
  public @ResponseBody ResponseEntity<List<Pie>> findPiesByCalories(@PathVariable int limit) {
    List<Pie> caloriePieList = pieService.getPiesByCalories(limit);

    return ResponseEntity.status(HttpStatus.ACCEPTED).body(caloriePieList);
  }

  
  @DeleteMapping("delete/{pieName}")
  public @ResponseBody ResponseEntity<String> deletePie(@PathVariable String pieName) {
    pieService.deletePie(pieName);
    return ResponseEntity.accepted().body("Successfully deleted");
  }

  
  @PatchMapping
  public @ResponseBody ResponseEntity<String> patchPie(@RequestParam String pieName, @RequestParam(defaultValue = "0", required = false) int calories,
   @RequestParam(defaultValue = "0", required = false) int slicesAvailable) {
    
    pieService.patchPie(pieName, calories, slicesAvailable);
    return ResponseEntity.ok()
    .body("Pie successfully Patched.");
  }

  // Yes working
  @PutMapping("update")
  public @ResponseBody ResponseEntity<String> updatePie(@RequestBody Pie updatedPie) {
    pieService.updatePie(updatedPie);
    return ResponseEntity.ok()
            .body("Pie Successfully Updated.");
  }

  // Yes working
  @PostMapping("create")
  public @ResponseBody ResponseEntity<Pie> createPie(@RequestBody Pie newPie) {
    pieService.addNewPie(newPie);
    return ResponseEntity.status(HttpStatus.CREATED)
            .body(newPie);
  }
}
