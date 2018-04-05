package com.example.backend;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;



    public class SmsSender {
        // Find your Account Sid and Auth Token at twilio.com/console
        public static final String ACCOUNT_SID =
                "AC99d474b87aaec7ef9f61fa82e29669d9";
        public static final String AUTH_TOKEN =
                "f83e6c575ccd3dff4f9d6af315dd4a8f";

        public void sendNotification(Member sender,String phoneNumber) {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

            Message message = Message
                    .creator(new PhoneNumber("+" + phoneNumber), // to
                            new PhoneNumber("+16626256003"), // from
                            sender.memberName +" has notified you as a possible match!!")
                    .create();

            System.out.println(message.getSid());
        }
    }

