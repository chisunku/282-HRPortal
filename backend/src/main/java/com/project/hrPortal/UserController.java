package com.project.hrPortal;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.project.hrPortal.Entity.*;
import com.project.hrPortal.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
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

    @Autowired
    TitlesService titlesService;

    @Autowired
    DepartManagerService deptManagerService;
    @Autowired
    DeptEmployeeService deptEmpService;
    @Autowired
    DepartmentService deptService;
    @Autowired
    salariesService salariesService;

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

    @GetMapping("/getEmployeeHomePage")
    @ResponseBody
    public ObjectNode getEmployeeByEmpNo(@RequestParam int empNo){
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode json = mapper.createObjectNode();
        Employees emp = employeeService.findEmployeeByEmpNo(empNo);
        List<Titles> t = null;
        //get title
        if(emp!=null){
            json.put("empNo", emp.getEmpNo());
            json.put("firstName", emp.getFirstName());
            json.put("lastName", emp.getLastName());
            json.put("gender", emp.getGender());
            json.put("hireDate", emp.getHireDate().toString());
//            System.out.println("emp det : "+emp.getEmpNo()+" "+emp.getFirstName());
            t = titlesService.findTitlesByEmployee(emp);
            if(t.size()>0) {
                Collections.sort(t, (a, b) -> a.getFromDate().compareTo(b.getFromDate()));
                json.put("title", t.get(0).getTitle());
                json.put("Titlefrom", t.get(0).getFromDate().toString());
                json.put("TitleTo", t.get(0).getToDate().toString());
            }
            else{
                json.put("title", "");
                json.put("Titlefrom", "");
                json.put("TitleTo", "");
            }
        }
        else{
            json.put("empNo", "");
            json.put("name", "");
            json.put("gender", "");
            json.put("hireDate", "");
        }
        //get dept for emp
        List<deptEmployee> deptEmp = null;
        if(emp!=null){
            deptEmp = deptEmpService.findDepartmentByEmpNo(emp.getEmpNo());
//            Collections.sort(deptEmp, (a,b) -> a.getFromDate().compareTo(b.getFromDate()));
//            System.out.println(deptEmp.get(0).getDeptNo());
        }
        //get manager
        List<DeptManager> mgr = null;
        if(deptEmp!=null){
            mgr = deptManagerService.findManagersByDeptNo(deptEmp.get(0).getDeptNo());
            Collections.sort(mgr, (a,b) -> a.getFromDate().compareTo(b.getFromDate()));
//            System.out.println(mgr.get(0).getEmpNo());
        }
        //get manager details
        Employees manager = null;
        if(deptEmp!=null){
            manager = employeeService.findEmployeeByEmpNo(mgr.get(0).getEmpNo());
            if(manager != null) {
//                System.out.println("manager: " + manager.getEmpNo() + " " + manager.getFirstName());
                json.put("manager", manager.getFirstName() + " " + manager.getLastName());
                json.put("manager_empNo", manager.getEmpNo());
            }
            else{
                json.put("manager", "");
                json.put("manager_empNo", "");
            }
        }
        //get department name
        Departments dept = null;
        if(deptEmp!=null){
            dept = deptService.findDepartmentByDeptNo(deptEmp.get(0).getDeptNo());
            if(dept!=null) {
//                System.out.println("depart name : " + dept.getDeptName());
                json.put("department", dept.getDeptName());
            }
            else{
                json.put("department", "");
            }
        }
        //salary
        List<salaries> salary = null;
        if(emp!=null){
            salary = salariesService.findSalaryByEmpNo(emp.getEmpNo());
            if(salary.size()>0){
                Collections.sort(salary, (a,b)->a.getFromDate().compareTo(b.getFromDate()));
//                System.out.println(salary.size());
                json.put("salary", salary.get(0).getSalary());
            }
            else{
                json.put("salary", "");
            }
        }
        return json;
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
    public List<Titles> testingQuery(int empNo){
        Employees emp = employeeService.findEmployeeByEmpNo(empNo);
        if(emp!=null){
            System.out.println("emp det : "+emp.getEmpNo()+" "+emp.getFirstName());
            return titlesService.findTitlesByEmployee(emp);
        }
        return null;
    }

    @GetMapping("/getManagerInfo")
    public ObjectNode managerInfo(@RequestParam int empNo){
        System.out.println("-----getManagerInfo-------");
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode json = mapper.createObjectNode();
        List<deptEmployee> deptEmp =  deptEmpService.findDepartmentByEmpNo(empNo);
        if(deptEmp.size()>0)
            Collections.sort(deptEmp, (a,b)->a.getFromDate().compareTo(b.getFromDate()));
        //manager and dept
        List<DeptManager> manager = deptManagerService.findManagersByDeptNo(deptEmp.get(0).getDeptNo());
        if(manager.size()>0){
            Collections.sort(manager, (a,b)->a.getFromDate().compareTo(b.getFromDate()));
            System.out.println(manager.get(0).getEmpNo());
        }
        //get manager details
        Employees mgr = employeeService.findEmployeeByEmpNo(manager.get(0).getEmpNo());
        if(mgr!=null){
            json.put("managerEmpId", mgr.getEmpNo());
            json.put("managerFirstName", mgr.getFirstName());
            json.put("managerLastName", mgr.getLastName());
            json.put("managerGender", mgr.getGender());
            json.put("departmentNo", deptEmp.get(0).getDeptNo());
        }
        //depart info
        Departments dept = deptService.findDepartmentByDeptNo(deptEmp.get(0).getDeptNo());
        if(dept!=null){
            json.put("department", dept.getDeptName());
        }
        return json;
    }

    @GetMapping("/getTeamInfo")
    public ObjectNode teamInfo(@RequestParam int empNo){
        System.out.println("-----getTeamInfo-------");
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode json = mapper.createObjectNode();
        List<deptEmployee> deptEmp =  deptEmpService.findDepartmentByEmpNo(empNo);
        if(deptEmp.size()>0)
            Collections.sort(deptEmp, (a,b)->a.getFromDate().compareTo(b.getFromDate()));
        //manager and dept
        //depart info
        Departments dept = deptService.findDepartmentByDeptNo(deptEmp.get(0).getDeptNo());
        if(dept==null)
            return json;
        List<deptEmployee> team = deptEmpService.findAllByDeptNo(dept.getDeptNo());
        List<Employees> emps = new ArrayList<>();
        ArrayNode employeeNode = json.putArray("employees");
        for(deptEmployee d : team){
//            System.out.println(d.getEmpNo());
            Employees e = employeeService.findEmployeeByEmpNo(d.getEmpNo());
            emps.add(e);
            ObjectNode empNode = mapper.createObjectNode();
            empNode.put("empNo",e.getEmpNo());
            empNode.put("empFirstName", e.getFirstName());
            empNode.put("empLastName",e.getLastName());
            List<Titles> t = titlesService.findTitlesByEmployee(e);
            Collections.sort(t,(a,b)->a.getFromDate().compareTo(b.getFromDate()));
            if(t.size()>0){
                empNode.put("empTitle", t.get(0).getTitle());
            }
            employeeNode.add(empNode);
        }
        System.out.println("emp size: "+emps.size());
//        if(emps.size()>0){
//            json.putArray("Team",emps);
//        }
        return json;
    }

}
