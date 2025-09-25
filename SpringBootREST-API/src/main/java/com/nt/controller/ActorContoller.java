package com.nt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.VO.ActorVO;
import com.nt.service.IActorService;

@RestController
@RequestMapping("/actor-api")
public class ActorContoller {

	@Autowired
	private IActorService actorSer;
	
	
	@PostMapping("/save")
	public ResponseEntity<String> saveActor(@RequestBody ActorVO vo){
		String msg=actorSer.registerActor(vo);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<ActorVO>> showAllActors(){
		List<ActorVO> listVO=actorSer.showAllActors();
		return new ResponseEntity<List<ActorVO>>(listVO,HttpStatus.OK);
		}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<ActorVO> showActorById(@PathVariable int id){
		ActorVO vo=actorSer.showActorById(id);
		return new ResponseEntity<ActorVO>(vo,HttpStatus.OK);
	}
	
	@GetMapping("/find/{start}/{end}")
	public ResponseEntity<List<ActorVO>> showActorByRemuneration(@PathVariable double start,@PathVariable double end){
		List<ActorVO> list=actorSer.showActorByRemuneration(start, end);
		return new ResponseEntity<List<ActorVO>>(list,HttpStatus.OK);
	}
	
	@PatchMapping("/update/{aid}/{percent}")
	public ResponseEntity<String> updateActorRemunaration(@PathVariable int aid,@PathVariable double percent){
		String msg=actorSer.updateActorRemunaration(aid, percent);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> updateActorData(@RequestBody ActorVO vo){
		String msg=actorSer.updateActor(vo);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteActor(@PathVariable int id){
		String msg=actorSer.removeActorById(id);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{cat1}/{cat2}/{cat3}")
	public ResponseEntity<String> removeActorsBycategory(@PathVariable String cat1,@PathVariable String cat2,@PathVariable String cat3){
		String msg=actorSer.removeActorsBycategory(cat1, cat2, cat3);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
}
