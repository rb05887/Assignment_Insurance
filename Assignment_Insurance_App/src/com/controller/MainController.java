package com.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.Buy;
import com.model.Policy;
import com.model.PolicyDetails;
import com.model.PolicyHolder;

import com.service.Service;

/**
 * Servlet implementation class MainController
 */
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Service phService = new Service();
	HttpSession session;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		session = request.getSession();
		String page = request.getParameter("page");
		if (page == null)
		{
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
		if (page.equals("login")) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		if (page.equals("sign_up")) {
			request.getRequestDispatcher("signUp.jsp").forward(request, response);
			return;
		}
		if (page.equals("details")) {
			// fetch all entries from cart table and give count and list to cart
			String username = (String) session.getAttribute("username");
			int id = Integer.parseInt(request.getParameter("id"));
			PolicyDetails det = phService.fetchPolicyDetails(id);
			request.setAttribute("details", det);

			Buy buy = new Buy();
			Policy policy = new Policy();
			PolicyHolder policyHolder = new PolicyHolder();
			LocalDate date = LocalDate.now();
			int day = date.getDayOfMonth();
			int month = date.getMonthValue();
			int year = date.getYear();
			String dop = day + "/" + month + "/" + year;
			Buy uid = phService.fetchUserId(username);

			buy.setUser_id(uid.getUser_id());
			buy.setPolicy_id(id);
			buy.setDate_of_purchase(dop);
			phService.buyPolicy(buy);

			request.getRequestDispatcher("details.jsp").forward(request, response);
			return;
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		session = request.getSession();

		String page = request.getParameter("page");
		if (page.equalsIgnoreCase("sign_up_form")) {
			String name = request.getParameter("name");
			Integer age = Integer.parseInt(request.getParameter("age"));
			String userName = request.getParameter("username");
			String password = request.getParameter("password");

			PolicyHolder policyHolder = new PolicyHolder();

			policyHolder.setName(name);
			policyHolder.setAge(age);
			policyHolder.setUsername(userName);
			policyHolder.setPassword(password);

			phService.insertUser(policyHolder);

			request.setAttribute("msg", "Sign Up Seccess!");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;

		}
		if (page.equalsIgnoreCase("login_form")) {
			String userName = request.getParameter("username");
			String password = request.getParameter("password");
			boolean status = phService.doLogin(userName, password);
			if (status == true) {
				// add username to session
				session.setAttribute("username", userName);
				List<Policy> list = phService.fetchAllPolicies();

				request.setAttribute("list_products", list);
				request.getRequestDispatcher("customer_dashboard.jsp").forward(request, response);
				return;
			} else {
				request.setAttribute("msg", "Invalid Credientials!");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return;
			}
		}

	}

}
