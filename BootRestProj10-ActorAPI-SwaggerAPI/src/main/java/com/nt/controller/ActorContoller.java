package com.nt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nt.VO.ActorVO;
import com.nt.service.IActorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/actor-api")
@Tag(name = "Actor Controller", description = "CRUD operations for Actor Management")
public class ActorContoller {

	@Autowired
	private IActorService actorSer;

	@PostMapping("/save")
	@Operation(summary = "Save a new actor", description = "Registers a new actor into the system.")
	public ResponseEntity<String> saveActor(@RequestBody ActorVO vo) {
		String msg = actorSer.registerActor(vo);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

	@GetMapping("/all")
	@Operation(summary = "Get all actors", description = "Returns a list of all actors.")
	public ResponseEntity<List<ActorVO>> showAllActors() {
		List<ActorVO> listVO = actorSer.showAllActors();
		return new ResponseEntity<>(listVO, HttpStatus.OK);
	}

	@GetMapping("/find/{id}")
	@Operation(summary = "Get actor by ID", description = "Returns details of an actor by ID.")
	public ResponseEntity<ActorVO> showActorById(
		@Parameter(description = "Actor ID") @PathVariable int id) {
		System.out.println("Current thread name::"+Thread.currentThread().getName());
		ActorVO vo = actorSer.showActorById(id);
		return new ResponseEntity<>(vo, HttpStatus.OK);
	}

	@GetMapping("/find/{start}/{end}")
	@Operation(summary = "Get actors by remuneration range", description = "Returns actors whose remuneration falls between start and end range.")
	public ResponseEntity<List<ActorVO>> showActorByRemuneration(
		@Parameter(description = "Start remuneration") @PathVariable double start,
		@Parameter(description = "End remuneration") @PathVariable double end) {
		List<ActorVO> list = actorSer.showActorByRemuneration(start, end);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@PatchMapping("/update/{aid}/{percent}")
	@Operation(summary = "Update actor remuneration", description = "Updates the remuneration of an actor by a percentage.")
	public ResponseEntity<String> updateActorRemunaration(
		@Parameter(description = "Actor ID") @PathVariable int aid,
		@Parameter(description = "Percentage to increase") @PathVariable double percent) {
		String msg = actorSer.updateActorRemunaration(aid, percent);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

	@PutMapping("/update")
	@Operation(summary = "Update actor data", description = "Updates full details of an actor.")
	public ResponseEntity<String> updateActorData(@RequestBody ActorVO vo) {
		String msg = actorSer.updateActor(vo);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	@Operation(summary = "Delete actor by ID", description = "Removes an actor from the system by ID.")
	public ResponseEntity<String> deleteActor(
		@Parameter(description = "Actor ID") @PathVariable int id) {
		String msg = actorSer.removeActorById(id);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{cat1}/{cat2}/{cat3}")
	@Operation(summary = "Delete actors by category", description = "Removes multiple actors based on their categories.")
	public ResponseEntity<String> removeActorsBycategory(
		@Parameter(description = "Category 1") @PathVariable String cat1,
		@Parameter(description = "Category 2") @PathVariable String cat2,
		@Parameter(description = "Category 3") @PathVariable String cat3) {
		String msg = actorSer.removeActorsBycategory(cat1, cat2, cat3);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
}
