package com.example.controller;


import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.pojo.Loans;
import com.example.repository.LoanRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoansController {

	@Autowired
    private final LoanRepository loanRepository;
    
	
	LoansController(LoanRepository loanRepository)
	{
		this.loanRepository=loanRepository;
	}

    @GetMapping("/myLoans")
    @PostAuthorize("hasRole('USER')")
    public List<Loans> getLoanDetails(@RequestParam long id) {
        List<Loans> loans = loanRepository.findByCustomerIdOrderByStartDtDesc(id);
        if (loans != null) {
            return loans;
        } else {
            return null;
        }
    }

}