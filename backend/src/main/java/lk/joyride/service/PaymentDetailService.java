package lk.joyride.service;

import lk.joyride.dto.PaymentDetailDTO;

import java.util.ArrayList;

public interface PaymentDetailService {
    void savePaymentDetail(PaymentDetailDTO dto);

    String generateNewPaymentId();

    ArrayList<PaymentDetailDTO> getAllPaymentDetails();
}
