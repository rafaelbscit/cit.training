package br.com.cit.contacts.api.service.validation;


import br.com.cit.contacts.model.Contact;
import br.com.cit.contacts.model.Mail;
import com.google.common.base.Strings;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContactValidation extends Validation<Contact> {

    @Override
    public Boolean shouldProcessEntity(Contact entity) {

        boolean step1 = !Strings.isNullOrEmpty(entity.getName());

        List<Mail> mails = entity.getMails();
        boolean step2 = mails != null && !mails.isEmpty();

        boolean step3 = step2;
        if (step3) {
            for (Mail mail : mails) {
                if (mail == null || Strings.isNullOrEmpty(mail.getMail())) {
                    step3 = false;
                    break;
                }
            }
        }

        return step1 && step2 && step3;
    }

    @Override
    public String getMessageValidationFailed() {
        return "The fields name and mails cannot be nulls!";
    }
}
