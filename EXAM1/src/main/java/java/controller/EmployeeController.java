package java.controller;
import com.googlecode.objectify.ObjectifyService;
import entity.Employee;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class EmployeeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/employee/worker.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectifyService.register(Employee.class);
        String fullname = req.getParameter("fullname");
        String address = req.getParameter("address");
        String birthday = req.getParameter("birthday");
        String department = req.getParameter("department");
        String position = req.getParameter("position");



        entity.Employee employee = new Employee();
        employee.setFullname(fullname);
        employee.setAddress(address);
        employee.setBirthday(birthday);
        employee.setDepartment(department);
        employee.setPosition(position);
        ofy().save().entity(employee).now();
        resp.sendRedirect("/employee/list");
    }
}
