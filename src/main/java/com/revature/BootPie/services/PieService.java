package com.revature.BootPie.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.BootPie.Exceptions.ResourceNotFoundException;
import com.revature.BootPie.Models.Pie;
import com.revature.BootPie.Repositories.PieRepository;

// CRUD implementation
@Service
public class PieService {
  
  // private List<Pie> pieList = new ArrayList<>();

  // {
  //   Pie pie1 = new Pie("Cherry", 800, 6);
  //   Pie pie2 = new Pie("Apple", 700, 3);
  //   Pie pie3 = new Pie("BootPie", 100000, 8);

  //   pieList.add(pie1);
  //   pieList.add(pie2);
  //   pieList.add(pie3);
  // }

  // JpaRepository/CrudRepository
  private PieRepository pieRepository;

  @Autowired
  public PieService(PieRepository pieRepository) {
    this.pieRepository = pieRepository;
  }

  public List<Pie> getPieList() { return (List<Pie>) pieRepository.findAll();}
  

  
  public Pie findPie(String pieName) throws ResourceNotFoundException {
      return pieRepository.findById(pieName)
              .orElseThrow(() -> new ResourceNotFoundException(pieName + " was not found. Please check name or try another."));
    
  }

  // Todo: Create custom query
  public List<Pie> getPiesByCalories(int limit) throws ResourceNotFoundException {
    
    List<Pie> caloriePieList = pieRepository.findByCaloriesLessThan(limit);

    
    if(caloriePieList.isEmpty()) throw new ResourceNotFoundException("No pies exist with calories equal to or lower than " + limit);

    return caloriePieList;
  }

  public void deletePie(String pieName) {
    pieRepository.deleteById(pieName);
  }

  public void patchPie(String pieName, int calories, int slicesAvailable) throws ResourceNotFoundException {

    Pie pie = pieRepository.findById(pieName)
          .orElseThrow(() -> new ResourceNotFoundException(pieName + " was not found. Please check name and try another."));

        if(calories > 0) pie.setCalories(calories);
        if(slicesAvailable > 0) pie.setSlicesAvailable(slicesAvailable);

        pieRepository.save(pie);
    }

  public void updatePie(Pie updatedPie) throws ResourceNotFoundException {
      Pie pie = pieRepository.findById(updatedPie.getPieName())
              .orElseThrow(() -> new ResourceNotFoundException(updatedPie.getPieName() + " was not found. Please check name and try another."));

      pie.setCalories(updatedPie.getCalories());
      pie.setSlicesAvailable(updatedPie.getSlicesAvailable());

        pieRepository.save(pie);
  }

  public void addNewPie(Pie newPie) {
    pieRepository.save(newPie);
  }
}