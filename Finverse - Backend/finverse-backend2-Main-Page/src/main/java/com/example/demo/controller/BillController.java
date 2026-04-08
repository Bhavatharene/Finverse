//done
package com.example.demo.controller;

import com.example.demo.model.Bill;
import com.example.demo.service.BillService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bills")
@CrossOrigin("*")
public class BillController {

    @Autowired
    private BillService service;

    // ✅ Get all bills for user
    @GetMapping
    public List<Bill> getBills(@RequestParam Long userId) {
        return service.getByUser(userId);
    }

    // ✅ Add bill
    @PostMapping
    public Bill addBill(@RequestBody Bill bill) {
        return service.save(bill);
    }

    // ✅ Delete bill
    @DeleteMapping("/{id}")
    public void deleteBill(@PathVariable Long id) {
        service.delete(id);
    }

    // ✅ Mark as paid
    @PutMapping("/{id}/pay")
    public Bill markPaid(@PathVariable Long id) {
        return service.markPaid(id);
    }
}