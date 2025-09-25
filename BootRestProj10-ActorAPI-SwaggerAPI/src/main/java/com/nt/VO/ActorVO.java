package com.nt.VO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class ActorVO {
	
	private Integer aid;
	@NonNull
	private String aname;
	@NonNull
	private String category;
	@NonNull
	private String addrs;
	@NonNull
	private Double remunaration;
	
}
