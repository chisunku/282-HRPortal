package com.project.hrPortal;

import com.project.hrPortal.Entity.Employees;
import com.project.hrPortal.Entity.Users;
import com.project.hrPortal.Service.EmployeeService;
import com.project.hrPortal.Service.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(path = "/HrPortal")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    @Autowired
    UserLogin userlogin;

    @Autowired
    EmployeeService employeeService;

    @GetMapping(path = "/")
    public String verifyConnection(){
        return "connected";
    }

    @PostMapping(path = "/addUser")
    public String addUsers (@RequestParam String empId, String password){
        Users user = new Users();
        user.setEmpId(Integer.parseInt(empId));
        user.setPassword(password);
        
        Users u = null;
        try {
            u = userlogin.save(user);
        }catch(Exception e){
            e.printStackTrace();
        }
        if(u==null)
            System.out.println("not added ig");
        return "result: user Created";
    }

    @GetMapping(path = "/getAllUsers")
    public List<Users> fetchAll(){
        System.out.println("coming here");
        ArrayList<Users> c = (ArrayList<Users>) userlogin.findAll();
        for(Users u : c){
            System.out.println(u.getEmpId()+" "+u.getPassword());
        }
        return userlogin.findAll();
    }

    //check the login thing
    @GetMapping(path = "/checkUser")
    @ResponseBody
    public String verifyUser(@RequestParam String empId, String password) {
        Users usr;
        HashMap<String, String> map = new HashMap<>();
        try {
            usr = userlogin.findUserByEmpId(Integer.parseInt(empId));
        } catch (Exception e) {
//            map.put("res", "user not found");
//            return map;
            return "user not found";
        }
        if (usr == null) {
//            map.put("res", "User not found");
//            return map;
            return "User not found";
        }
        if (usr.getPassword().equals(password)){
//            map.put("res","verified");
            return "verified";
        }
        else {
//            map.put("res","Wrong credentials entered");
            return "Wrong credentials entered";
        }
//        return map;
    }

    @GetMapping("/getEmpByEmpNo")
    @ResponseBody
    public ResponseEntity<Employees> getEmployeeByEmpNo(@RequestParam int empNo){
        Employees emp = null;
        try{
            emp = employeeService.findEmployeeByEmpNo(empNo);
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("fetched: "+emp.getLastName()+" "+emp.getFirstName());
        return new ResponseEntity(emp, HttpStatus.OK);
    }

    @PostMapping("/addEmployee")
    public Employees addEmployee(@RequestBody Employees emp){
//        System.out.println(empNo+" "+birthDate+" "+firstName+" "+lastName+" "+gender+" "+hireDate);
        System.out.println(emp.getEmpNo()+" "+emp.getGender()+" "+emp.getBirthDate());
        Employees e = new Employees();
        e.setEmpNo(emp.getEmpNo());
        e.setBirthDate(emp.getBirthDate());
        e.setFirstName(emp.getFirstName());
        e.setLastName(emp.getLastName());
        e.setGender(emp.getGender());
        e.setHireDate(emp.getHireDate());
        return employeeService.save(e);
    }

    @GetMapping("/testingQuery")
    public void testingQuery(){
        List<Users> ans = userlogin.findUsers();
        for(Users u : ans){
            System.out.println(u.getEmpId()+" "+u.getPassword());
        }
    }

}
