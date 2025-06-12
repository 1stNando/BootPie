package com.revature.BootPie.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.revature.BootPie.models.Pie;

@Service
public class PieService {
  
  private List<Pie> pieList = new ArrayList<>();

  {
    Pie pie1 = new Pie("Cherry", 800, 6);
    Pie pie2 = new Pie("Apple", 700, 3);
    Pie pie3 = new Pie("BootPie", 100000, 8);

    pieList.add(pie1);
    pieList.add(pie2);
    pieList.add(pie3);
  }

  public Pie getRandomPie() {
    int randomInt = new Random().nextInt(pieList.size());

    return pieList.get(randomInt);
  }
}