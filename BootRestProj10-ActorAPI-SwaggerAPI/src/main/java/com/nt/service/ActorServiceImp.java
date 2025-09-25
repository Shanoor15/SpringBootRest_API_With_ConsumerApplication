package com.nt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.VO.ActorVO;
import com.nt.entity.ActorEntity;
import com.nt.exception.ActorNotFoundException;
import com.nt.respository.IActorRepository;

@Service
public class ActorServiceImp implements IActorService {
	
	@Autowired
	private IActorRepository actorRepo;

	@Override
	public String registerActor(ActorVO vo) {
		//Convert VO class obj to ActorEntity
				ActorEntity entity=new ActorEntity();
				BeanUtils.copyProperties(vo, entity);
				entity.setCreatedBy(System.getProperty("user.name"));
				//save object
				int idVal=actorRepo.save(entity).getAid();
				return " Actor Object is saved with this Id:: "+idVal;
	}

	@Override
	public List<ActorVO> showAllActors() {
		List<ActorEntity> listEntities=actorRepo.findAll();
		List<ActorVO> listVO=new ArrayList();
		//copy listEntities to listVO
		listEntities.forEach(entity->{
			ActorVO vo=new ActorVO();
			BeanUtils.copyProperties(entity, vo);
			listVO.add(vo);
		});
		return listVO;
	}

	@Override
	public ActorVO showActorById(int id) {
		ActorEntity entity=actorRepo.findById(id).orElseThrow(()->new ActorNotFoundException("invalid id"));
		try {
			Thread.sleep(60000);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		//convert entity to vo
		ActorVO vo=new ActorVO();
		BeanUtils.copyProperties(entity, vo);
		return vo;
	}

	@Override
	public List<ActorVO> showActorByRemuneration(double start, double end) {
		List<ActorEntity> listEntities=actorRepo.showActorByRemuneration(start, end);
		//copy listEntities to listVO
		List<ActorVO> listVO=new ArrayList<>();
		listEntities.forEach(entity->{
			ActorVO vo=new ActorVO();
			BeanUtils.copyProperties(entity, vo);
			listVO.add(vo);
		});
		return listVO;
	}

	@Override
	public String updateActorRemunaration(int id, double percent) {
		ActorEntity entity=actorRepo.findById(id).orElseThrow(()->new ActorNotFoundException("invalide id"));
		//update Remunaration
		entity.setRemunaration(entity.getRemunaration()+entity.getRemunaration()*percent/100.0);
		//save the obj
		actorRepo.save(entity);
		return id+" Actor is Updated";
	}

	@Override
	public String updateActor(ActorVO vo) {
		ActorEntity entity=actorRepo.findById(vo.getAid()).orElseThrow(()->new ActorNotFoundException("invalide id"));
		BeanUtils.copyProperties(vo, entity);
		entity.setUpdatedBy(System.getProperty("user.name"));
		actorRepo.save(entity);
		return vo.getAid()+" Actor is update";
	}

	@Override
	public String removeActorById(int id) {
		ActorEntity entity=actorRepo.findById(id).orElseThrow(()->new ActorNotFoundException("invalide id"));
		actorRepo.deleteById(id);
		return id+" Actor is Deleted";
	}

	@Override
	public String removeActorsBycategory(String cat1, String cat2, String cat3) {
		int count=actorRepo.deleteActorsByCategory(cat1, cat2, cat3);
		return count==0?"Actor not Found":count+"no.of Actors are delete";
	}

	

	

	
}
