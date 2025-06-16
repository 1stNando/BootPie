package com.revature.BootPie.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.BootPie.Models.Consumer;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, String> {

  Optional<Consumer> findByUsernameAndPassword(String username, String password);
}
