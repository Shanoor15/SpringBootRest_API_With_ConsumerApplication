package com.nt.service;

import java.util.List;

import com.nt.VO.ActorVO;

public interface IActorService {
	
	public String registerActor(ActorVO vo);
	public List<ActorVO> showAllActors();
	public ActorVO showActorById(int id);
	public List<ActorVO> showActorByRemuneration(double start,double end);
	public String updateActorRemunaration(int id,double percent);
	public String updateActor(ActorVO vo);
	public String removeActorById(int id);
	public String removeActorsBycategory(String cat1,String cat2,String cat3);
}
