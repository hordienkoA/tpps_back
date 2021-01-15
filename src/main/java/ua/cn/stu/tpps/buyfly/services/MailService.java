package ua.cn.stu.tpps.buyfly.services;

import ua.cn.stu.tpps.buyfly.domain.BoardingPass;
import ua.cn.stu.tpps.buyfly.domain.Flight;

//TODO To be implemented one day
public interface MailService {
    void sendRegistrationConfirmationEmail(Integer customerId, String uniqueLink);

    void sendBookingConfirmationEmail(Integer customerId, BoardingPass boardingPass);

    void sendPaymentConfirmationEmail(Integer customerId, BoardingPass boardingPass);

    void sendPaymentCancellationEmail(Integer customerId, BoardingPass boardingPass);

    void sendFlightCancellationEmail(Integer customerId, Flight flight);

    void sendFlightDelayEmail(Integer customerId, Flight flight);
}
