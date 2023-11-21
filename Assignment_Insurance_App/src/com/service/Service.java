package com.service;

import java.util.List;

import com.model.Buy;
import com.model.Policy;
import com.model.PolicyDetails;
import com.model.PolicyHolder;
import com.repository.PolicyHolderRepository;

public class Service {

	private PolicyHolderRepository policyHolderRepository=new PolicyHolderRepository();
	public void insertUser(PolicyHolder policyHolder) {

		policyHolderRepository.insertUser(policyHolder);
	}

	public boolean doLogin(String userName, String password) {

		return policyHolderRepository.doLogin(userName, password);
	}
	public List<Policy> fetchAllPolicies() {

		return policyHolderRepository.fetchAllPolicies();
	}

	public PolicyDetails fetchPolicyDetails(int id) {
		// TODO Auto-generated method stub
		return policyHolderRepository.fetchPolicyDetails(id);
	}
	public void buyPolicy(Buy buy) {
		policyHolderRepository.buyPolicy(buy);
	}
	public Buy fetchUserId(String username) {
		return policyHolderRepository.fetchUserId(username);
	}
}