package Assign_1;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    // Predefined user credentials//UC2
    private static final String USERNAME = "riya";
    private static final String PASSWORD = "RiGo@1215";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter write = response.getWriter();
        // Get input values
        String name = request.getParameter("name");
        String user = request.getParameter("username");
        String pass = request.getParameter("password");
        // Validate Name//UC3
        if (!isValidName(name)) {
            write.println("<h2>Invalid! Use the correct format</h2>");
            write.println("<p>Name must starts with 'Cap'</p>");
            write.println("<a href='src/main/webapp/login.html'>oops..Try Again</a>");
            return;
        }
        // Validate Password//UC4
        if (!isValidPassword(pass)) {
            write.println("<h2>Wrong Password!</h2>");
            write.println("<p>Password must follow the criteria:</p>");
            write.println("<ul>");
            write.println("<li>Minimum 8 characters</li>");
            write.println("<li>At least 1 uppercase letter</li>");
            write.println("<li>At least 1 numeric digit</li>");
            write.println("<li>Exactly 1 special character</li>");
            write.println("</ul>");
            write.println("<a href='src/main/webapp/login.html'>Try Again</a>");
            return;
        }
        // Check username and password
        if (USERNAME.equals(user) && PASSWORD.equals(pass)) {
            write.println("<h2>Logged Successfully</h2>");
            write.println("<p>Hey, " + name + "!</p>");
            write.println("<a href='welcome.html'>Go To Welcome Page..</a>");
        }
        else {
            write.println("<h2>Wrong Credentials!!</h2>");
            write.println("<a href='src/main/webapp/login.html'>Try Again!</a>");
        }
    }
    // Method to validate Name
    private boolean isValidName(String name) {
        return name != null && name.matches("^Cap.{2,}");
    }
    // Method to validate Password
    private boolean isValidPassword(String password) {
        if (password == null) return false;
        String passRegex = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+=!])(?!.*[@#$%^&+=!].*[@#$%^&+=!]).{8,}$";
        Pattern pattern = Pattern.compile(passRegex);
        Matcher m = pattern.matcher(password);
        return m.matches();
    }
}
