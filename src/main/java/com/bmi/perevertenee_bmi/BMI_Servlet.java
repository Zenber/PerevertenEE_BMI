package com.bmi.perevertenee_bmi;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "bmi", value = "/bmi-servlet")
public class BMI_Servlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html;charset=UTF-8");

        double weight = Double.parseDouble(request.getParameter("weight"));
        double heightCm = Double.parseDouble(request.getParameter("height"));
        double heightM = heightCm / 100.0;

        double bmi = weight / (heightM * heightM);

        String category;
        if (bmi < 18.5) {
            category = "Podváha";
        } else if (bmi < 24.9) {
            category = "Normální váha";
        } else if (bmi < 29.9) {
            category = "Nadváha";
        } else {
            category = "Obezita";
        }

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html lang='cs'>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<title>Výsledek BMI</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; background-color: #e9f7ef; color: #2f4f2f; text-align: center; padding: 50px; }");
            out.println(".result { background: #f0fff0; border: 2px solid #4CAF50; border-radius: 12px; display: inline-block; padding: 30px; box-shadow: 0 4px 12px rgba(0,0,0,0.1); }");
            out.println("h2 { color: #2f4f2f; margin-bottom: 15px; }");
            out.println("p { font-size: 18px; margin: 10px 0; }");
            out.println("a { display: inline-block; margin-top: 20px; text-decoration: none; color: white; background-color: #4CAF50; padding: 10px 20px; border-radius: 8px; }");
            out.println("a:hover { background-color: #45a049; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='result'>");
            out.println("<h2>Váš BMI: " + String.format("%.2f", bmi) + "</h2>");
            out.println("<p>Kategorie: <b>" + category + "</b></p>");
            out.println("<a href='index.html'>Zpět</a>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

}