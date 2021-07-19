package com.example.demo.Controllerr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.MyUser;
import com.example.demo.Repo.MyRepo;
import com.example.demo.UserExcep.UserNotFoundException;

@RestController
@RequestMapping("/data")
public class MyController {
	
	@Autowired
	private MyRepo repo;

	@GetMapping("/getData")
	ResponseEntity<?> getData(){
//		Return data
		List<MyUser> uu=repo.findAll();
//		return new ResponseEntity<List<MyUser>>(uu,HttpStatus.OK);
//		Return Cutomize Data
		List det=new ArrayList();
		
		for(MyUser u:uu) {
			System.out.println(u.getId());
			Map l=new HashMap();
			l.put("Username", u.getName());
			l.put("password", u.getPassword());
			
			Map ll=new HashMap();
			ll.put("Address", u.getAddress());
			ll.put("Roll", u.getRoll());
			l.put("Details", ll);
			det.add(l);		
		}
		return new ResponseEntity(det,HttpStatus.OK);
		
	}
	
	@PostMapping("/addData")
	ResponseEntity<String> addData(@RequestBody MyUser user){
		repo.save(user);
		return new ResponseEntity<String>("Value added",HttpStatus.OK);
	}
	
	@GetMapping("/getDataById/{id}")
	ResponseEntity<Map<?, ?>> get(@PathVariable("id") int id) {
//		With Exception Handling
//		return get value
//		return this.repo.findById(id).orElseThrow(()-> new UserNotFoundException("Not Found"));
		
		
//		Customize getvalue
		MyUser u=this.repo.findById(id).orElseThrow(()-> new UserNotFoundException("Not Found"));
		Map l=new HashMap();
		l.put("Username", u.getName());
		l.put("password", u.getPassword());
		
		Map ll=new HashMap();
		ll.put("Address", u.getAddress());
		ll.put("Roll", u.getRoll());
		l.put("Details", ll);
		return new ResponseEntity<Map<?,?>>(l,HttpStatus.OK);
		
		
//		Without Exception Handling
//		return this.repo.findById(id).get();
	}
	
	
	
	@GetMapping("/getDetails")
	List<MyUser> getDerails(){
		
		return this.repo.findAll();
	}
}
