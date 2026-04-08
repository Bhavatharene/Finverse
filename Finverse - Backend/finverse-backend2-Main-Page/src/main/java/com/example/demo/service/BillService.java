//done
package com.example.demo.service;

import com.example.demo.model.Bill;
import com.example.demo.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {

    @Autowired
    private BillRepository repo;

    public List<Bill> getByUser(Long userId) {
        return repo.findByUserId(userId);
    }

    public Bill save(Bill bill) {
        return repo.save(bill);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public Bill markPaid(Long id) {
        Bill bill = repo.findById(id).orElseThrow();
        bill.setPaid(true);
        return repo.save(bill);
    }
}