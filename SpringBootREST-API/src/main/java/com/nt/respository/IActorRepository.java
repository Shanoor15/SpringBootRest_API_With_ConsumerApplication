package com.nt.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.nt.entity.ActorEntity;



public interface IActorRepository extends JpaRepository<ActorEntity, Integer> {

	@Query("from ActorEntity where remunaration>=:start and remunaration<=:end order by aname asc")
	public List<ActorEntity> showActorByRemuneration(double start,double end);
	
	@Query("delete from ActorEntity where category in(:cat1,:cat2,:cat3)")
	@Modifying  //this query is not a SELECT, it’s changing data (like UPDATE or DELETE)
    @Transactional   
	public int deleteActorsByCategory(String cat1,String cat2,String cat3);
}
