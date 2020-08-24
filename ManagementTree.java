package com.abc;

import java.util.*;

public class ManagementTree {

    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(10l, "Tom", 0l));
        employees.add(new Employee(2l, "Mickey", 10l));
        employees.add(new Employee(3l, "Jerry", 10l));
        employees.add(new Employee(7l, "John", 2l));
        employees.add(new Employee(5l, "Sarah", 10l));
        employees.add(new Employee(6l, "Alex", 3l));
        employees.add(new Employee(9l, "Ben", 5l));
        employees.add(new Employee(11l, "Sandra", 7l));

        new ManagementTree().displayManagementTree(employees);

    }

    void displayManagementTree(List<Employee> employees ){
          Map<Long , List<EmployeeNode>>  map = new HashMap<>();

          for(Employee employee : employees ){
                 Long managerId = employee.getManagerId();
                 EmployeeNode node = new EmployeeNode(employee);
                 if(map.get(managerId) == null){
                     map.put(managerId, new ArrayList<>());
                 }
                map.get(managerId).add(node);
          }
          EmployeeNode root = map.remove(Long.valueOf(0)).get(0);
          buildTree(root, map);
          String s = "->";
          printTree(root, s);
    }

    private void printTree(EmployeeNode root, String s) {
        if(root == null ) return ;
        System.out.println(s + root.getName());
        if(root.getEmployeeNodes() != null && root.getEmployeeNodes().size() > 0){
            for(EmployeeNode em : root.employeeNodes){
                printTree(em, s + "->");
            }
        }
    }

    private void buildTree(EmployeeNode current, Map<Long, List<EmployeeNode>> map) {
        if(map.size() == 0 )
          return ;
        List<EmployeeNode> employeeNodes = map.remove(current.getId());
        if(employeeNodes == null || employeeNodes.size() == 0) return ;
        current.setEmployeeNodes(employeeNodes);

        for(EmployeeNode em : employeeNodes){
            buildTree(em, map);
        }

    }


}

 class EmployeeNode extends Employee{
    List<EmployeeNode> employeeNodes = null;

     public List<EmployeeNode> getEmployeeNodes() {
         return employeeNodes;
     }
     public void setEmployeeNodes(List<EmployeeNode> employeeNodes){
         this.employeeNodes = employeeNodes;
     }

     public EmployeeNode(Employee employee){
        super(employee.getId(), employee.getName(),employee.getManagerId());
    }
 }

 class Employee {
    private Long id;
    private String name;
    private Long managerId;
   public Employee(){

   }
     public Employee(Long id, String name, Long managerId) {
         this.id = id;
         this.name = name;
         this.managerId = managerId;
     }

     public Long getId() {
         return id;
     }

     public void setId(Long id) {
         this.id = id;
     }

     public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }

     public Long getManagerId() {
         return managerId;
     }

     public void setManagerId(Long managerId) {
         this.managerId = managerId;
     }
 }
