package com.example.billpaymentsystemdemo.services;

import com.example.billpaymentsystemdemo.dtos.responseDtos.ResponseDto;
import com.example.billpaymentsystemdemo.dtos.restData.ListPayment;
import com.example.billpaymentsystemdemo.dtos.restData.VoucherResponseDto;
import com.example.billpaymentsystemdemo.models.Payment;
import com.example.billpaymentsystemdemo.models.Users;
import com.example.billpaymentsystemdemo.models.Voucher;
import com.example.billpaymentsystemdemo.repositories.PaymentRepository;
import com.example.billpaymentsystemdemo.repositories.UserRepository;
import com.example.billpaymentsystemdemo.repositories.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private VoucherRepository voucherRepository;

    @Autowired
    private UserRepository customerRepository;

    public ResponseDto generateVoucher(Long customerId, List<Payment> payments) {
        Users customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));

        Voucher voucher = new Voucher();
        voucher.setUser(customer);
        voucher.setVoucherCode(UUID.randomUUID().toString());
        voucher.setPayments(payments);


        payments.forEach(payment -> {
            payment.setVoucher(voucher);
            payment.setCustomer(customer);
        });

        voucherRepository.save(voucher);
        paymentRepository.saveAll(payments);

        VoucherResponseDto voucherResponseDto = new VoucherResponseDto();
        voucherResponseDto.setId(voucher.getId());
        voucherResponseDto.setVoucherCode(voucher.getVoucherCode());

        return new ResponseDto(true,"Voucher generated successfully",voucherResponseDto);
    }

    public ResponseDto getPaymentsByVoucher(String voucherCode) {
        Voucher voucher = voucherRepository.findByVoucherCode(voucherCode).orElseThrow(() -> new RuntimeException("Voucher not found"));

        List<ListPayment> listPayments = new ArrayList<>();
        List<Payment> payments = voucher.getPayments();
        for(Payment payment : payments){
            ListPayment listPayment = new ListPayment();
            listPayment.setPaymentType(payment.getPaymentType().toString());
            listPayment.setAmount(payment.getAmount());
            listPayment.setFee(payment.getFee());
            listPayment.setId(payment.getId());
            listPayment.setVoucherCode(voucher.getVoucherCode());
            listPayment.setVoucherId(voucher.getId());
            listPayments.add(listPayment);
        }
        return new ResponseDto(true,"Payments fetched successfully",listPayments);
    }
}
