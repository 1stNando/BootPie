package com.revature.BootPie.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.BootPie.Models.Pie;

@Repository
public interface PieRepository extends CrudRepository<Pie, String> {
  
}
