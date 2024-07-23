package com.practice.springboot_mongodb_CRUD;


import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HostelRepository extends MongoRepository<Hostel,Integer> 
{

	Optional<Hostel> findByName(String name);

	Optional<Hostel> findByRoomno(int roomno);

}
