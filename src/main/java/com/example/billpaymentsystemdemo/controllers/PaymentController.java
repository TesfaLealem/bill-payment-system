package com.example.billpaymentsystemdemo.controllers;
import com.example.billpaymentsystemdemo.dtos.requestDtos.PaymentRequestDTO;
import com.example.billpaymentsystemdemo.dtos.responseDtos.ResponseDto;
import com.example.billpaymentsystemdemo.models.Payment;
import com.example.billpaymentsystemdemo.models.Voucher;
import com.example.billpaymentsystemdemo.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/generate-voucher")
    public ResponseDto generateVoucher(@RequestBody List<PaymentRequestDTO> paymentRequestDTOs, @RequestParam Long customerId) {
        List<Payment> payments = paymentRequestDTOs.stream().map(dto -> Payment.builder()
                .paymentType(dto.getPaymentType())
                .amount(dto.getAmount())
                .fee(dto.getFee())
                .build()).collect(Collectors.toList());

        return paymentService.generateVoucher(customerId, payments);
    }

    @GetMapping("/voucher/{voucherCode}")
    public ResponseDto getPaymentsByVoucher(@PathVariable String voucherCode) {
        return paymentService.getPaymentsByVoucher(voucherCode);

    }
}
