package com.practice.springboot_mongodb_CRUD;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController 
{
	@Autowired
	HostelRepository hrepo;

	@RequestMapping("/test")
	public String test()
	{
		return "CRUD Operation on MongoDB";
	}
	@RequestMapping("/save")
	public String saveData(@RequestBody HostelDTO hosteldto)
	{
		Hostel hostel=new Hostel();
		hostel.setId(hosteldto.getId());
		hostel.setName(hosteldto.getName());
		hostel.setRoomno(hosteldto.getRoomno());
		hostel.setRent(hosteldto.getRent());
		hrepo.save(hostel);
		return "Data is saved";
	}
	
	@RequestMapping("/all")
	public List<HostelDTO> findAllData()
	{
		return hrepo.findAll()
				    .stream()
				    .map(hostel->{
				    	HostelDTO hosteldto=new HostelDTO();
				    	hosteldto.setId(hostel.getId());
				    	hosteldto.setName(hostel.getName());
				    	hosteldto.setRoomno(hostel.getRoomno());
				    	hosteldto.setRent(hostel.getRent());
				    	return hosteldto;
				    })
				    .collect(Collectors.toList());
	}
	@RequestMapping("/{id}")
	public HostelDTO byId(@PathVariable int id)
	{
		Hostel hostel=hrepo.findById(id).get();
		HostelDTO hosteldto=new HostelDTO();
		BeanUtils.copyProperties(hostel, hosteldto);
		
		return hosteldto;
		
	}
	@RequestMapping("/name/{name}")
	public HostelDTO byName(@PathVariable String name)
	{
		Hostel hostel=hrepo.findByName(name).get();
		HostelDTO hosteldto=new HostelDTO();
		BeanUtils.copyProperties(hostel, hosteldto);
		return hosteldto;
	}
	@RequestMapping("/roomno/{roomno}")
	public HostelDTO byRoomno(@PathVariable int roomno)
	{
		Hostel hostel=hrepo.findByRoomno(roomno).get();
		HostelDTO hosteldto=new HostelDTO();
		BeanUtils.copyProperties(hostel, hosteldto);
		return hosteldto;
	}
	
	//in put we can update all fields 
	
	/*
	@PutMapping("/upd/{id}")
	public String update(@RequestBody HostelDTO hosteldto,@PathVariable int id)
	{
		Hostel hostel=hrepo.findById(id).get();
		//hostel.setId(hosteldto.getId());
		hostel.setName(hosteldto.getName());
		hostel.setRoomno(hosteldto.getRoomno());
		hostel.setRent(hosteldto.getRent());
		hrepo.save(hostel);
		return "data is updated";
	}
	*/
//in patch we can only update one field at a time 
	@PatchMapping("/upd/name/{id}")
	public String update(@RequestBody HostelDTO hosteldto,@PathVariable int id)
	{
		Hostel hostel=hrepo.findById(id).get();
		hostel.setName(hosteldto.getName());
		hrepo.save(hostel);
		return "Data is update";
	}
	/*
	@PatchMapping("/upd/{roomno}")
	public String updatebyroom(@RequestBody HostelDTO hosteldto,@PathVariable int roomno)
	{
		Hostel hostel=hrepo.findByRoomno(roomno).get();
		hostel.setRoomno(hosteldto.getRoomno());
		hrepo.save(hostel);
		return "Data is update";
	}
	*/
	
	@DeleteMapping("/del/{id}")
	public String delete(@PathVariable int id)
	{
		Hostel hostel=hrepo.findById(id).get();
	
		hrepo.deleteById(id);
		return "Data is deleted";
	}
}
