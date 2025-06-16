package com.revature.BootPie.Services;

import java.util.ArrayList;
import java.util.List;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.BootPie.Exceptions.ResourceNotFoundException;
import com.revature.BootPie.Models.Consumer;
import com.revature.BootPie.Repositories.ConsumerRepository;

@Service
public class ConsumerService {
  
  private ConsumerRepository consumerRepository;

  private PieService pieService;

  @Autowired 
  public ConsumerService(PieService pieService, ConsumerRepository consumerRepository) {
    this.pieService = pieService;
    this.consumerRepository = consumerRepository;
  }

  

  public void register(Consumer newConsumer) {consumerRepository.save(newConsumer);}

  public void order(String username, String pieName) {
    Consumer consumer = consumerRepository.findById(username).orElseThrow(() -> new ResourceNotFoundException("Consumer " + username + " not found."));
    consumer.setLastPie((pieService.findPie(pieName)));
    consumerRepository.save(consumer);
  }

  // Custom query for username/password 
  public void login(String username, String password) throws AuthenticationException {
    consumerRepository.findByUsernameAndPassword((username), password);
  }
  //   public void login(String username, String password) throws AuthenticationException {
  //     for(Consumer consumer:consumerList) {
  //       if(consumer.getUsername().equals(username) && consumer.getPassword().equals(password)) {
  //         return;
  //       } 
  //     }
  //     throw new ResourceNotFoundException("Check username and password credentials as they are invalid.");
  //   }


}
